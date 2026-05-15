package oshi.hardware.platform.windows;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.VersionHelpers;
import com.sun.jna.platform.win32.Win32Exception;
import com.sun.jna.platform.win32.WinReg;
import com.sun.jna.platform.win32.COM.WbemcliUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.driver.windows.LogicalProcessorInformation;
import oshi.driver.windows.perfmon.LoadAverage;
import oshi.driver.windows.perfmon.ProcessorInformation;
import oshi.driver.windows.perfmon.SystemInformation;
import oshi.driver.windows.wmi.Win32Processor;
import oshi.hardware.CentralProcessor;
import oshi.hardware.common.AbstractCentralProcessor;
import oshi.jna.Struct;
import oshi.jna.platform.windows.PowrProf;
import oshi.util.GlobalConfig;
import oshi.util.Memoizer;
import oshi.util.ParseUtil;
import oshi.util.platform.windows.WmiUtil;
import oshi.util.tuples.Pair;
import oshi.util.tuples.Triplet;

@ThreadSafe
final class WindowsCentralProcessor extends AbstractCentralProcessor {
   private static final Logger LOG = LoggerFactory.getLogger(WindowsCentralProcessor.class);
   private Map numaNodeProcToLogicalProcMap;
   private static final boolean USE_LOAD_AVERAGE = GlobalConfig.get("oshi.os.windows.loadaverage", false);
   private static final boolean USE_CPU_UTILITY;
   private final Supplier processorUtilityCounters;
   private Map initialUtilityCounters;
   private Long utilityBaseMultiplier;

   WindowsCentralProcessor() {
      this.processorUtilityCounters = USE_CPU_UTILITY ? Memoizer.memoize(WindowsCentralProcessor::queryProcessorUtilityCounters, TimeUnit.MILLISECONDS.toNanos(300L)) : null;
      this.initialUtilityCounters = USE_CPU_UTILITY ? (Map)((Pair)this.processorUtilityCounters.get()).getB() : null;
      this.utilityBaseMultiplier = null;
   }

   protected CentralProcessor.ProcessorIdentifier queryProcessorId() {
      String cpuVendor = "";
      String cpuName = "";
      String cpuIdentifier = "";
      String cpuFamily = "";
      String cpuModel = "";
      String cpuStepping = "";
      long cpuVendorFreq = 0L;
      boolean cpu64bit = false;
      String cpuRegistryRoot = "HARDWARE\\DESCRIPTION\\System\\CentralProcessor\\";
      String[] processorIds = Advapi32Util.registryGetKeys(WinReg.HKEY_LOCAL_MACHINE, "HARDWARE\\DESCRIPTION\\System\\CentralProcessor\\");
      if (processorIds.length > 0) {
         String cpuRegistryPath = "HARDWARE\\DESCRIPTION\\System\\CentralProcessor\\" + processorIds[0];
         cpuVendor = Advapi32Util.registryGetStringValue(WinReg.HKEY_LOCAL_MACHINE, cpuRegistryPath, "VendorIdentifier");
         cpuName = Advapi32Util.registryGetStringValue(WinReg.HKEY_LOCAL_MACHINE, cpuRegistryPath, "ProcessorNameString");
         cpuIdentifier = Advapi32Util.registryGetStringValue(WinReg.HKEY_LOCAL_MACHINE, cpuRegistryPath, "Identifier");

         try {
            cpuVendorFreq = (long)Advapi32Util.registryGetIntValue(WinReg.HKEY_LOCAL_MACHINE, cpuRegistryPath, "~MHz") * 1000000L;
         } catch (Win32Exception var17) {
         }
      }

      if (!cpuIdentifier.isEmpty()) {
         cpuFamily = parseIdentifier(cpuIdentifier, "Family");
         cpuModel = parseIdentifier(cpuIdentifier, "Model");
         cpuStepping = parseIdentifier(cpuIdentifier, "Stepping");
      }

      Struct.CloseableSystemInfo sysinfo = new Struct.CloseableSystemInfo();

      try {
         Kernel32.INSTANCE.GetNativeSystemInfo(sysinfo);
         int processorArchitecture = sysinfo.processorArchitecture.pi.wProcessorArchitecture.intValue();
         if (processorArchitecture == 9 || processorArchitecture == 12 || processorArchitecture == 6) {
            cpu64bit = true;
         }
      } catch (Throwable var18) {
         try {
            sysinfo.close();
         } catch (Throwable var16) {
            var18.addSuppressed(var16);
         }

         throw var18;
      }

      sysinfo.close();
      WbemcliUtil.WmiResult<Win32Processor.ProcessorIdProperty> processorId = Win32Processor.queryProcessorId();
      String processorID;
      if (processorId.getResultCount() > 0) {
         processorID = WmiUtil.getString(processorId, Win32Processor.ProcessorIdProperty.PROCESSORID, 0);
      } else {
         processorID = createProcessorID(cpuStepping, cpuModel, cpuFamily, cpu64bit ? new String[]{"ia64"} : new String[0]);
      }

      return new CentralProcessor.ProcessorIdentifier(cpuVendor, cpuName, cpuFamily, cpuModel, cpuStepping, processorID, cpu64bit, cpuVendorFreq);
   }

   private static String parseIdentifier(String identifier, String key) {
      String[] idSplit = ParseUtil.whitespaces.split(identifier);
      boolean found = false;

      for(String s : idSplit) {
         if (found) {
            return s;
         }

         found = s.equals(key);
      }

      return "";
   }

   protected Triplet initProcessorCounts() {
      if (VersionHelpers.IsWindows7OrGreater()) {
         Triplet<List<CentralProcessor.LogicalProcessor>, List<CentralProcessor.PhysicalProcessor>, List<CentralProcessor.ProcessorCache>> procs = LogicalProcessorInformation.getLogicalProcessorInformationEx();
         int curNode = -1;
         int procNum = 0;
         int lp = 0;
         this.numaNodeProcToLogicalProcMap = new HashMap();

         for(CentralProcessor.LogicalProcessor logProc : (List)procs.getA()) {
            int node = logProc.getNumaNode();
            if (node != curNode) {
               curNode = node;
               procNum = 0;
            }

            this.numaNodeProcToLogicalProcMap.put(String.format("%d,%d", logProc.getNumaNode(), procNum++), lp++);
         }

         return procs;
      } else {
         return LogicalProcessorInformation.getLogicalProcessorInformation();
      }
   }

   public long[] querySystemCpuLoadTicks() {
      long[] ticks = new long[CentralProcessor.TickType.values().length];
      long[][] procTicks = this.getProcessorCpuLoadTicks();

      for(int i = 0; i < ticks.length; ++i) {
         for(long[] procTick : procTicks) {
            ticks[i] += procTick[i];
         }
      }

      return ticks;
   }

   public long[] queryCurrentFreq() {
      if (VersionHelpers.IsWindows7OrGreater()) {
         Pair<List<String>, Map<ProcessorInformation.ProcessorFrequencyProperty, List<Long>>> instanceValuePair = ProcessorInformation.queryFrequencyCounters();
         List<String> instances = (List)instanceValuePair.getA();
         Map<ProcessorInformation.ProcessorFrequencyProperty, List<Long>> valueMap = (Map)instanceValuePair.getB();
         List<Long> percentMaxList = (List)valueMap.get(ProcessorInformation.ProcessorFrequencyProperty.PERCENTOFMAXIMUMFREQUENCY);
         if (!instances.isEmpty()) {
            long maxFreq = this.getMaxFreq();
            long[] freqs = new long[this.getLogicalProcessorCount()];

            for(String instance : instances) {
               int cpu = instance.contains(",") ? (Integer)this.numaNodeProcToLogicalProcMap.getOrDefault(instance, 0) : ParseUtil.parseIntOrDefault(instance, 0);
               if (cpu < this.getLogicalProcessorCount()) {
                  freqs[cpu] = (Long)percentMaxList.get(cpu) * maxFreq / 100L;
               }
            }

            return freqs;
         }
      }

      return this.queryNTPower(2);
   }

   public long queryMaxFreq() {
      long[] freqs = this.queryNTPower(1);
      return Arrays.stream(freqs).max().orElse(-1L);
   }

   private long[] queryNTPower(int fieldIndex) {
      PowrProf.ProcessorPowerInformation ppi = new PowrProf.ProcessorPowerInformation();
      PowrProf.ProcessorPowerInformation[] ppiArray = (PowrProf.ProcessorPowerInformation[])ppi.toArray(this.getLogicalProcessorCount());
      long[] freqs = new long[this.getLogicalProcessorCount()];
      if (0 != PowrProf.INSTANCE.CallNtPowerInformation(11, (Pointer)null, 0, ppiArray[0].getPointer(), ppi.size() * ppiArray.length)) {
         LOG.error("Unable to get Processor Information");
         Arrays.fill(freqs, -1L);
         return freqs;
      } else {
         for(int i = 0; i < freqs.length; ++i) {
            if (fieldIndex == 1) {
               freqs[i] = (long)ppiArray[i].maxMhz * 1000000L;
            } else if (fieldIndex == 2) {
               freqs[i] = (long)ppiArray[i].currentMhz * 1000000L;
            } else {
               freqs[i] = -1L;
            }
         }

         return freqs;
      }
   }

   public double[] getSystemLoadAverage(int nelem) {
      if (nelem >= 1 && nelem <= 3) {
         return LoadAverage.queryLoadAverage(nelem);
      } else {
         throw new IllegalArgumentException("Must include from one to three elements.");
      }
   }

   public long[][] queryProcessorCpuLoadTicks() {
      List<Long> baseList = null;
      List<Long> systemUtility = null;
      List<Long> processorUtility = null;
      List<Long> processorUtilityBase = null;
      List<Long> initSystemList = null;
      List<Long> initUserList = null;
      List<Long> initBase = null;
      List<Long> initSystemUtility = null;
      List<Long> initProcessorUtility = null;
      List<Long> initProcessorUtilityBase = null;
      List<String> instances;
      List<Long> systemList;
      List<Long> userList;
      List<Long> irqList;
      List<Long> softIrqList;
      List<Long> idleList;
      if (USE_CPU_UTILITY) {
         Pair<List<String>, Map<ProcessorInformation.ProcessorUtilityTickCountProperty, List<Long>>> instanceValuePair = (Pair)this.processorUtilityCounters.get();
         instances = (List)instanceValuePair.getA();
         Map<ProcessorInformation.ProcessorUtilityTickCountProperty, List<Long>> valueMap = (Map)instanceValuePair.getB();
         systemList = (List)valueMap.get(ProcessorInformation.ProcessorUtilityTickCountProperty.PERCENTPRIVILEGEDTIME);
         userList = (List)valueMap.get(ProcessorInformation.ProcessorUtilityTickCountProperty.PERCENTUSERTIME);
         irqList = (List)valueMap.get(ProcessorInformation.ProcessorUtilityTickCountProperty.PERCENTINTERRUPTTIME);
         softIrqList = (List)valueMap.get(ProcessorInformation.ProcessorUtilityTickCountProperty.PERCENTDPCTIME);
         idleList = (List)valueMap.get(ProcessorInformation.ProcessorUtilityTickCountProperty.PERCENTPROCESSORTIME);
         baseList = (List)valueMap.get(ProcessorInformation.ProcessorUtilityTickCountProperty.TIMESTAMP_SYS100NS);
         systemUtility = (List)valueMap.get(ProcessorInformation.ProcessorUtilityTickCountProperty.PERCENTPRIVILEGEDUTILITY);
         processorUtility = (List)valueMap.get(ProcessorInformation.ProcessorUtilityTickCountProperty.PERCENTPROCESSORUTILITY);
         processorUtilityBase = (List)valueMap.get(ProcessorInformation.ProcessorUtilityTickCountProperty.PERCENTPROCESSORUTILITY_BASE);
         initSystemList = (List)this.initialUtilityCounters.get(ProcessorInformation.ProcessorUtilityTickCountProperty.PERCENTPRIVILEGEDTIME);
         initUserList = (List)this.initialUtilityCounters.get(ProcessorInformation.ProcessorUtilityTickCountProperty.PERCENTUSERTIME);
         initBase = (List)this.initialUtilityCounters.get(ProcessorInformation.ProcessorUtilityTickCountProperty.TIMESTAMP_SYS100NS);
         initSystemUtility = (List)this.initialUtilityCounters.get(ProcessorInformation.ProcessorUtilityTickCountProperty.PERCENTPRIVILEGEDUTILITY);
         initProcessorUtility = (List)this.initialUtilityCounters.get(ProcessorInformation.ProcessorUtilityTickCountProperty.PERCENTPROCESSORUTILITY);
         initProcessorUtilityBase = (List)this.initialUtilityCounters.get(ProcessorInformation.ProcessorUtilityTickCountProperty.PERCENTPROCESSORUTILITY_BASE);
      } else {
         Pair<List<String>, Map<ProcessorInformation.ProcessorTickCountProperty, List<Long>>> instanceValuePair = ProcessorInformation.queryProcessorCounters();
         instances = (List)instanceValuePair.getA();
         Map<ProcessorInformation.ProcessorTickCountProperty, List<Long>> valueMap = (Map)instanceValuePair.getB();
         systemList = (List)valueMap.get(ProcessorInformation.ProcessorTickCountProperty.PERCENTPRIVILEGEDTIME);
         userList = (List)valueMap.get(ProcessorInformation.ProcessorTickCountProperty.PERCENTUSERTIME);
         irqList = (List)valueMap.get(ProcessorInformation.ProcessorTickCountProperty.PERCENTINTERRUPTTIME);
         softIrqList = (List)valueMap.get(ProcessorInformation.ProcessorTickCountProperty.PERCENTDPCTIME);
         idleList = (List)valueMap.get(ProcessorInformation.ProcessorTickCountProperty.PERCENTPROCESSORTIME);
      }

      int ncpu = this.getLogicalProcessorCount();
      long[][] ticks = new long[ncpu][CentralProcessor.TickType.values().length];
      if (!instances.isEmpty() && systemList != null && userList != null && irqList != null && softIrqList != null && idleList != null && (!USE_CPU_UTILITY || baseList != null && systemUtility != null && processorUtility != null && processorUtilityBase != null && initSystemList != null && initUserList != null && initBase != null && initSystemUtility != null && initProcessorUtility != null && initProcessorUtilityBase != null)) {
         for(String instance : instances) {
            int cpu = instance.contains(",") ? (Integer)this.numaNodeProcToLogicalProcMap.getOrDefault(instance, 0) : ParseUtil.parseIntOrDefault(instance, 0);
            if (cpu < ncpu) {
               ticks[cpu][CentralProcessor.TickType.SYSTEM.getIndex()] = (Long)systemList.get(cpu);
               ticks[cpu][CentralProcessor.TickType.USER.getIndex()] = (Long)userList.get(cpu);
               ticks[cpu][CentralProcessor.TickType.IRQ.getIndex()] = (Long)irqList.get(cpu);
               ticks[cpu][CentralProcessor.TickType.SOFTIRQ.getIndex()] = (Long)softIrqList.get(cpu);
               ticks[cpu][CentralProcessor.TickType.IDLE.getIndex()] = (Long)idleList.get(cpu);
               if (USE_CPU_UTILITY) {
                  long deltaT = (Long)baseList.get(cpu) - (Long)initBase.get(cpu);
                  if (deltaT > 0L) {
                     long deltaBase = (Long)processorUtilityBase.get(cpu) - (Long)initProcessorUtilityBase.get(cpu);
                     long multiplier = this.lazilyCalculateMultiplier(deltaBase, deltaT);
                     if (multiplier > 0L) {
                        long deltaProc = (Long)processorUtility.get(cpu) - (Long)initProcessorUtility.get(cpu);
                        long deltaSys = (Long)systemUtility.get(cpu) - (Long)initSystemUtility.get(cpu);
                        long newUser = (Long)initUserList.get(cpu) + multiplier * (deltaProc - deltaSys) / 100L;
                        long newSystem = (Long)initSystemList.get(cpu) + multiplier * deltaSys / 100L;
                        long delta = newUser - ticks[cpu][CentralProcessor.TickType.USER.getIndex()];
                        ticks[cpu][CentralProcessor.TickType.USER.getIndex()] = newUser;
                        delta += newSystem - ticks[cpu][CentralProcessor.TickType.SYSTEM.getIndex()];
                        ticks[cpu][CentralProcessor.TickType.SYSTEM.getIndex()] = newSystem;
                        long[] var10000 = ticks[cpu];
                        int var10001 = CentralProcessor.TickType.IDLE.getIndex();
                        var10000[var10001] -= delta;
                     }
                  }
               }

               long[] var43 = ticks[cpu];
               int var49 = CentralProcessor.TickType.SYSTEM.getIndex();
               var43[var49] -= ticks[cpu][CentralProcessor.TickType.IRQ.getIndex()] + ticks[cpu][CentralProcessor.TickType.SOFTIRQ.getIndex()];
               var43 = ticks[cpu];
               var49 = CentralProcessor.TickType.SYSTEM.getIndex();
               var43[var49] /= 10000L;
               var43 = ticks[cpu];
               var49 = CentralProcessor.TickType.USER.getIndex();
               var43[var49] /= 10000L;
               var43 = ticks[cpu];
               var49 = CentralProcessor.TickType.IRQ.getIndex();
               var43[var49] /= 10000L;
               var43 = ticks[cpu];
               var49 = CentralProcessor.TickType.SOFTIRQ.getIndex();
               var43[var49] /= 10000L;
               var43 = ticks[cpu];
               var49 = CentralProcessor.TickType.IDLE.getIndex();
               var43[var49] /= 10000L;
            }
         }

         return ticks;
      } else {
         return ticks;
      }
   }

   private synchronized long lazilyCalculateMultiplier(long deltaBase, long deltaT) {
      if (this.utilityBaseMultiplier == null) {
         if (deltaT >> 32 > 0L) {
            this.initialUtilityCounters = (Map)((Pair)this.processorUtilityCounters.get()).getB();
            return 0L;
         }

         if (deltaBase <= 0L) {
            deltaBase += 4294967296L;
         }

         long multiplier = Math.round((double)deltaT / (double)deltaBase);
         if (deltaT < 50000000L) {
            return multiplier;
         }

         this.utilityBaseMultiplier = multiplier;
      }

      return this.utilityBaseMultiplier;
   }

   private static Pair queryProcessorUtilityCounters() {
      return ProcessorInformation.queryProcessorCapacityCounters();
   }

   public long queryContextSwitches() {
      return (Long)SystemInformation.queryContextSwitchCounters().getOrDefault(SystemInformation.ContextSwitchProperty.CONTEXTSWITCHESPERSEC, 0L);
   }

   public long queryInterrupts() {
      return (Long)ProcessorInformation.queryInterruptCounters().getOrDefault(ProcessorInformation.InterruptsProperty.INTERRUPTSPERSEC, 0L);
   }

   static {
      if (USE_LOAD_AVERAGE) {
         LoadAverage.startDaemon();
      }

      USE_CPU_UTILITY = VersionHelpers.IsWindows8OrGreater() && GlobalConfig.get("oshi.os.windows.cpu.utility", false);
   }
}
