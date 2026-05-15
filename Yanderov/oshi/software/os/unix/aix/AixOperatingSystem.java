package oshi.software.os.unix.aix;

import com.sun.jna.Native;
import com.sun.jna.platform.unix.aix.Perfstat;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.driver.unix.aix.Uptime;
import oshi.driver.unix.aix.Who;
import oshi.driver.unix.aix.perfstat.PerfstatConfig;
import oshi.driver.unix.aix.perfstat.PerfstatProcess;
import oshi.jna.platform.unix.AixLibc;
import oshi.software.common.AbstractOperatingSystem;
import oshi.software.os.FileSystem;
import oshi.software.os.InternetProtocolStats;
import oshi.software.os.NetworkParams;
import oshi.software.os.OSProcess;
import oshi.software.os.OSService;
import oshi.software.os.OSThread;
import oshi.software.os.OperatingSystem;
import oshi.util.ExecutingCommand;
import oshi.util.Memoizer;
import oshi.util.ParseUtil;
import oshi.util.Util;
import oshi.util.tuples.Pair;

@ThreadSafe
public class AixOperatingSystem extends AbstractOperatingSystem {
   private final Supplier config = Memoizer.memoize(PerfstatConfig::queryConfig);
   private final Supplier procCpu = Memoizer.memoize(PerfstatProcess::queryProcesses, Memoizer.defaultExpiration());
   private static final long BOOTTIME = querySystemBootTimeMillis() / 1000L;

   public String queryManufacturer() {
      return "IBM";
   }

   public Pair queryFamilyVersionInfo() {
      Perfstat.perfstat_partition_config_t cfg = (Perfstat.perfstat_partition_config_t)this.config.get();
      String systemName = System.getProperty("os.name");
      String archName = System.getProperty("os.arch");
      String versionNumber = System.getProperty("os.version");
      if (Util.isBlank(versionNumber)) {
         versionNumber = ExecutingCommand.getFirstAnswer("oslevel");
      }

      String releaseNumber = Native.toString(cfg.OSBuild);
      if (Util.isBlank(releaseNumber)) {
         releaseNumber = ExecutingCommand.getFirstAnswer("oslevel -s");
      } else {
         int idx = releaseNumber.lastIndexOf(32);
         if (idx > 0 && idx < releaseNumber.length()) {
            releaseNumber = releaseNumber.substring(idx + 1);
         }
      }

      return new Pair(systemName, new OperatingSystem.OSVersionInfo(versionNumber, archName, releaseNumber));
   }

   protected int queryBitness(int jvmBitness) {
      if (jvmBitness == 64) {
         return 64;
      } else {
         return (((Perfstat.perfstat_partition_config_t)this.config.get()).conf & 8388608) > 0 ? 64 : 32;
      }
   }

   public FileSystem getFileSystem() {
      return new AixFileSystem();
   }

   public InternetProtocolStats getInternetProtocolStats() {
      return new AixInternetProtocolStats();
   }

   public List queryAllProcesses() {
      return this.getProcessListFromProcfs(-1);
   }

   public List queryChildProcesses(int parentPid) {
      List<OSProcess> allProcs = this.queryAllProcesses();
      Set<Integer> descendantPids = getChildrenOrDescendants(allProcs, parentPid, false);
      return (List)allProcs.stream().filter((p) -> descendantPids.contains(p.getProcessID())).collect(Collectors.toList());
   }

   public List queryDescendantProcesses(int parentPid) {
      List<OSProcess> allProcs = this.queryAllProcesses();
      Set<Integer> descendantPids = getChildrenOrDescendants(allProcs, parentPid, true);
      return (List)allProcs.stream().filter((p) -> descendantPids.contains(p.getProcessID())).collect(Collectors.toList());
   }

   public OSProcess getProcess(int pid) {
      List<OSProcess> procs = this.getProcessListFromProcfs(pid);
      return procs.isEmpty() ? null : (OSProcess)procs.get(0);
   }

   private List getProcessListFromProcfs(int pid) {
      List<OSProcess> procs = new ArrayList();
      Perfstat.perfstat_process_t[] perfstat = (Perfstat.perfstat_process_t[])this.procCpu.get();
      Map<Integer, Pair<Long, Long>> cpuMap = new HashMap();

      for(Perfstat.perfstat_process_t stat : perfstat) {
         int statpid = (int)stat.pid;
         if (pid < 0 || statpid == pid) {
            cpuMap.put(statpid, new Pair((long)stat.ucpu_time, (long)stat.scpu_time));
         }
      }

      for(Map.Entry entry : cpuMap.entrySet()) {
         OSProcess proc = new AixOSProcess((Integer)entry.getKey(), (Pair)entry.getValue(), this.procCpu, this);
         if (proc.getState() != OSProcess.State.INVALID) {
            procs.add(proc);
         }
      }

      return procs;
   }

   public int getProcessId() {
      return AixLibc.INSTANCE.getpid();
   }

   public int getProcessCount() {
      return ((Perfstat.perfstat_process_t[])this.procCpu.get()).length;
   }

   public int getThreadId() {
      return AixLibc.INSTANCE.thread_self();
   }

   public OSThread getCurrentThread() {
      OSProcess proc = this.getCurrentProcess();
      int tid = this.getThreadId();
      return (OSThread)proc.getThreadDetails().stream().filter((t) -> t.getThreadId() == tid).findFirst().orElse(new AixOSThread(proc.getProcessID(), tid));
   }

   public int getThreadCount() {
      long tc = 0L;

      for(Perfstat.perfstat_process_t proc : (Perfstat.perfstat_process_t[])this.procCpu.get()) {
         tc += proc.num_threads;
      }

      return (int)tc;
   }

   public long getSystemUptime() {
      return System.currentTimeMillis() / 1000L - BOOTTIME;
   }

   public long getSystemBootTime() {
      return BOOTTIME;
   }

   private static long querySystemBootTimeMillis() {
      long bootTime = Who.queryBootTime();
      return bootTime >= 1000L ? bootTime : System.currentTimeMillis() - Uptime.queryUpTime();
   }

   public NetworkParams getNetworkParams() {
      return new AixNetworkParams();
   }

   public List getServices() {
      List<OSService> services = new ArrayList();
      List<String> systemServicesInfoList = ExecutingCommand.runNative("lssrc -a");
      if (systemServicesInfoList.size() > 1) {
         systemServicesInfoList.remove(0);

         for(String systemService : systemServicesInfoList) {
            String[] serviceSplit = ParseUtil.whitespaces.split(systemService.trim());
            if (systemService.contains("active")) {
               if (serviceSplit.length == 4) {
                  services.add(new OSService(serviceSplit[0], ParseUtil.parseIntOrDefault(serviceSplit[2], 0), OSService.State.RUNNING));
               } else if (serviceSplit.length == 3) {
                  services.add(new OSService(serviceSplit[0], ParseUtil.parseIntOrDefault(serviceSplit[1], 0), OSService.State.RUNNING));
               }
            } else if (systemService.contains("inoperative")) {
               services.add(new OSService(serviceSplit[0], 0, OSService.State.STOPPED));
            }
         }
      }

      File dir = new File("/etc/rc.d/init.d");
      File[] listFiles;
      if (dir.exists() && dir.isDirectory() && (listFiles = dir.listFiles()) != null) {
         for(File file : listFiles) {
            String installedService = ExecutingCommand.getFirstAnswer(file.getAbsolutePath() + " status");
            if (installedService.contains("running")) {
               services.add(new OSService(file.getName(), ParseUtil.parseLastInt(installedService, 0), OSService.State.RUNNING));
            } else {
               services.add(new OSService(file.getName(), 0, OSService.State.STOPPED));
            }
         }
      }

      return services;
   }
}
