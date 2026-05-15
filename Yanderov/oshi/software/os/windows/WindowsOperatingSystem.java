package oshi.software.os.windows;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.Advapi32;
import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Psapi;
import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.platform.win32.VersionHelpers;
import com.sun.jna.platform.win32.W32ServiceManager;
import com.sun.jna.platform.win32.Win32Exception;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.Winsvc;
import com.sun.jna.platform.win32.COM.WbemcliUtil;
import com.sun.jna.ptr.IntByReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.driver.windows.EnumWindows;
import oshi.driver.windows.registry.HkeyUserData;
import oshi.driver.windows.registry.NetSessionData;
import oshi.driver.windows.registry.ProcessPerformanceData;
import oshi.driver.windows.registry.ProcessWtsData;
import oshi.driver.windows.registry.SessionWtsData;
import oshi.driver.windows.registry.ThreadPerformanceData;
import oshi.driver.windows.wmi.Win32OperatingSystem;
import oshi.driver.windows.wmi.Win32Processor;
import oshi.jna.ByRef;
import oshi.jna.Struct;
import oshi.jna.platform.windows.WinNT;
import oshi.software.common.AbstractOperatingSystem;
import oshi.software.os.FileSystem;
import oshi.software.os.InternetProtocolStats;
import oshi.software.os.NetworkParams;
import oshi.software.os.OSProcess;
import oshi.software.os.OSService;
import oshi.software.os.OSSession;
import oshi.software.os.OSThread;
import oshi.software.os.OperatingSystem;
import oshi.util.GlobalConfig;
import oshi.util.Memoizer;
import oshi.util.platform.windows.WmiUtil;
import oshi.util.tuples.Pair;

@ThreadSafe
public class WindowsOperatingSystem extends AbstractOperatingSystem {
   private static final Logger LOG = LoggerFactory.getLogger(WindowsOperatingSystem.class);
   private static final boolean USE_PROCSTATE_SUSPENDED = GlobalConfig.get("oshi.os.windows.procstate.suspended", false);
   private static final boolean IS_VISTA_OR_GREATER = VersionHelpers.IsWindowsVistaOrGreater();
   private static final int TOKENELEVATION = 20;
   private static Supplier systemLog;
   private static final long BOOTTIME;
   private static final boolean X86;
   private static final boolean WOW;
   private Supplier processMapFromRegistry = Memoizer.memoize(WindowsOperatingSystem::queryProcessMapFromRegistry, Memoizer.defaultExpiration());
   private Supplier processMapFromPerfCounters = Memoizer.memoize(WindowsOperatingSystem::queryProcessMapFromPerfCounters, Memoizer.defaultExpiration());
   private Supplier threadMapFromRegistry = Memoizer.memoize(WindowsOperatingSystem::queryThreadMapFromRegistry, Memoizer.defaultExpiration());
   private Supplier threadMapFromPerfCounters = Memoizer.memoize(WindowsOperatingSystem::queryThreadMapFromPerfCounters, Memoizer.defaultExpiration());

   public String queryManufacturer() {
      return "Microsoft";
   }

   public Pair queryFamilyVersionInfo() {
      String version = System.getProperty("os.name");
      if (version.startsWith("Windows ")) {
         version = version.substring(8);
      }

      String sp = null;
      int suiteMask = 0;
      String buildNumber = "";
      WbemcliUtil.WmiResult<Win32OperatingSystem.OSVersionProperty> versionInfo = Win32OperatingSystem.queryOsVersion();
      if (versionInfo.getResultCount() > 0) {
         sp = WmiUtil.getString(versionInfo, Win32OperatingSystem.OSVersionProperty.CSDVERSION, 0);
         if (!sp.isEmpty() && !"unknown".equals(sp)) {
            version = version + " " + sp.replace("Service Pack ", "SP");
         }

         suiteMask = WmiUtil.getUint32(versionInfo, Win32OperatingSystem.OSVersionProperty.SUITEMASK, 0);
         buildNumber = WmiUtil.getString(versionInfo, Win32OperatingSystem.OSVersionProperty.BUILDNUMBER, 0);
      }

      String codeName = parseCodeName(suiteMask);
      if ("10".equals(version) && buildNumber.compareTo("22000") >= 0) {
         version = "11";
      } else if ("Server 2019".equals(version) && buildNumber.compareTo("20347") > 0) {
         version = "Server 2022";
      }

      return new Pair("Windows", new OperatingSystem.OSVersionInfo(version, codeName, buildNumber));
   }

   private static String parseCodeName(int suiteMask) {
      List<String> suites = new ArrayList();
      if ((suiteMask & 2) != 0) {
         suites.add("Enterprise");
      }

      if ((suiteMask & 4) != 0) {
         suites.add("BackOffice");
      }

      if ((suiteMask & 8) != 0) {
         suites.add("Communications Server");
      }

      if ((suiteMask & 128) != 0) {
         suites.add("Datacenter");
      }

      if ((suiteMask & 512) != 0) {
         suites.add("Home");
      }

      if ((suiteMask & 1024) != 0) {
         suites.add("Web Server");
      }

      if ((suiteMask & 8192) != 0) {
         suites.add("Storage Server");
      }

      if ((suiteMask & 16384) != 0) {
         suites.add("Compute Cluster");
      }

      if ((suiteMask & '耀') != 0) {
         suites.add("Home Server");
      }

      return String.join(",", suites);
   }

   protected int queryBitness(int jvmBitness) {
      if (jvmBitness < 64 && System.getenv("ProgramFiles(x86)") != null && IS_VISTA_OR_GREATER) {
         WbemcliUtil.WmiResult<Win32Processor.BitnessProperty> bitnessMap = Win32Processor.queryBitness();
         if (bitnessMap.getResultCount() > 0) {
            return WmiUtil.getUint16(bitnessMap, Win32Processor.BitnessProperty.ADDRESSWIDTH, 0);
         }
      }

      return jvmBitness;
   }

   public boolean isElevated() {
      ByRef.CloseableHANDLEByReference hToken = new ByRef.CloseableHANDLEByReference();

      boolean var22;
      label176: {
         boolean var5;
         label175: {
            try {
               ByRef.CloseableIntByReference returnLength = new ByRef.CloseableIntByReference();

               label171: {
                  label170: {
                     try {
                        boolean success = Advapi32.INSTANCE.OpenProcessToken(Kernel32.INSTANCE.GetCurrentProcess(), 8, hToken);
                        if (!success) {
                           LOG.error("OpenProcessToken failed. Error: {}", Native.getLastError());
                           var22 = false;
                           break label170;
                        }

                        try {
                           WinNT.TOKEN_ELEVATION elevation = new WinNT.TOKEN_ELEVATION();

                           label161: {
                              try {
                                 if (Advapi32.INSTANCE.GetTokenInformation(hToken.getValue(), 20, elevation, elevation.size(), returnLength)) {
                                    var5 = elevation.TokenIsElevated > 0;
                                    break label161;
                                 }
                              } catch (Throwable var18) {
                                 try {
                                    elevation.close();
                                 } catch (Throwable var17) {
                                    var18.addSuppressed(var17);
                                 }

                                 throw var18;
                              }

                              elevation.close();
                              break label171;
                           }

                           elevation.close();
                        } finally {
                           Kernel32.INSTANCE.CloseHandle(hToken.getValue());
                        }
                     } catch (Throwable var20) {
                        try {
                           returnLength.close();
                        } catch (Throwable var16) {
                           var20.addSuppressed(var16);
                        }

                        throw var20;
                     }

                     returnLength.close();
                     break label175;
                  }

                  returnLength.close();
                  break label176;
               }

               returnLength.close();
            } catch (Throwable var21) {
               try {
                  hToken.close();
               } catch (Throwable var15) {
                  var21.addSuppressed(var15);
               }

               throw var21;
            }

            hToken.close();
            return false;
         }

         hToken.close();
         return var5;
      }

      hToken.close();
      return var22;
   }

   public FileSystem getFileSystem() {
      return new WindowsFileSystem();
   }

   public InternetProtocolStats getInternetProtocolStats() {
      return new WindowsInternetProtocolStats();
   }

   public List getSessions() {
      List<OSSession> whoList = HkeyUserData.queryUserSessions();
      whoList.addAll(SessionWtsData.queryUserSessions());
      whoList.addAll(NetSessionData.queryUserSessions());
      return whoList;
   }

   public List getProcesses(Collection pids) {
      return this.processMapToList(pids);
   }

   public List queryAllProcesses() {
      return this.processMapToList((Collection)null);
   }

   public List queryChildProcesses(int parentPid) {
      Set<Integer> descendantPids = getChildrenOrDescendants(getParentPidsFromSnapshot(), parentPid, false);
      return this.processMapToList(descendantPids);
   }

   public List queryDescendantProcesses(int parentPid) {
      Set<Integer> descendantPids = getChildrenOrDescendants(getParentPidsFromSnapshot(), parentPid, true);
      return this.processMapToList(descendantPids);
   }

   private static Map getParentPidsFromSnapshot() {
      Map<Integer, Integer> parentPidMap = new HashMap();
      ByRef.CloseablePROCESSENTRY32ByReference processEntry = new ByRef.CloseablePROCESSENTRY32ByReference();

      try {
         com.sun.jna.platform.win32.WinNT.HANDLE snapshot = Kernel32.INSTANCE.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS, new WinDef.DWORD(0L));

         try {
            while(Kernel32.INSTANCE.Process32Next(snapshot, processEntry)) {
               parentPidMap.put(processEntry.th32ProcessID.intValue(), processEntry.th32ParentProcessID.intValue());
            }
         } finally {
            Kernel32.INSTANCE.CloseHandle(snapshot);
         }
      } catch (Throwable var9) {
         try {
            processEntry.close();
         } catch (Throwable var7) {
            var9.addSuppressed(var7);
         }

         throw var9;
      }

      processEntry.close();
      return parentPidMap;
   }

   public OSProcess getProcess(int pid) {
      List<OSProcess> procList = this.processMapToList(Arrays.asList(pid));
      return procList.isEmpty() ? null : (OSProcess)procList.get(0);
   }

   private List processMapToList(Collection pids) {
      Map<Integer, ProcessPerformanceData.PerfCounterBlock> processMap = (Map)this.processMapFromRegistry.get();
      if (processMap == null || processMap.isEmpty()) {
         processMap = pids == null ? (Map)this.processMapFromPerfCounters.get() : ProcessPerformanceData.buildProcessMapFromPerfCounters(pids);
      }

      Map<Integer, ThreadPerformanceData.PerfCounterBlock> threadMap = null;
      if (USE_PROCSTATE_SUSPENDED) {
         threadMap = (Map)this.threadMapFromRegistry.get();
         if (threadMap == null || threadMap.isEmpty()) {
            threadMap = pids == null ? (Map)this.threadMapFromPerfCounters.get() : ThreadPerformanceData.buildThreadMapFromPerfCounters(pids);
         }
      }

      Map<Integer, ProcessWtsData.WtsInfo> processWtsMap = ProcessWtsData.queryProcessWtsMap(pids);
      Set<Integer> mapKeys = new HashSet(processWtsMap.keySet());
      mapKeys.retainAll(processMap.keySet());
      return (List)((Stream)mapKeys.stream().parallel()).map((pid) -> new WindowsOSProcess(pid, this, processMap, processWtsMap, threadMap)).filter(OperatingSystem.ProcessFiltering.VALID_PROCESS).collect(Collectors.toList());
   }

   private static Map queryProcessMapFromRegistry() {
      return ProcessPerformanceData.buildProcessMapFromRegistry((Collection)null);
   }

   private static Map queryProcessMapFromPerfCounters() {
      return ProcessPerformanceData.buildProcessMapFromPerfCounters((Collection)null);
   }

   private static Map queryThreadMapFromRegistry() {
      return ThreadPerformanceData.buildThreadMapFromRegistry((Collection)null);
   }

   private static Map queryThreadMapFromPerfCounters() {
      return ThreadPerformanceData.buildThreadMapFromPerfCounters((Collection)null);
   }

   public int getProcessId() {
      return Kernel32.INSTANCE.GetCurrentProcessId();
   }

   public int getProcessCount() {
      Struct.CloseablePerformanceInformation perfInfo = new Struct.CloseablePerformanceInformation();

      int var6;
      label29: {
         try {
            if (!Psapi.INSTANCE.GetPerformanceInfo(perfInfo, perfInfo.size())) {
               LOG.error("Failed to get Performance Info. Error code: {}", Kernel32.INSTANCE.GetLastError());
               var6 = 0;
               break label29;
            }

            var6 = perfInfo.ProcessCount.intValue();
         } catch (Throwable var5) {
            try {
               perfInfo.close();
            } catch (Throwable var4) {
               var5.addSuppressed(var4);
            }

            throw var5;
         }

         perfInfo.close();
         return var6;
      }

      perfInfo.close();
      return var6;
   }

   public int getThreadId() {
      return Kernel32.INSTANCE.GetCurrentThreadId();
   }

   public OSThread getCurrentThread() {
      OSProcess proc = this.getCurrentProcess();
      int tid = this.getThreadId();
      return (OSThread)proc.getThreadDetails().stream().filter((t) -> t.getThreadId() == tid).findFirst().orElse(new WindowsOSThread(proc.getProcessID(), tid, (String)null, (ThreadPerformanceData.PerfCounterBlock)null));
   }

   public int getThreadCount() {
      Struct.CloseablePerformanceInformation perfInfo = new Struct.CloseablePerformanceInformation();

      int var6;
      label29: {
         try {
            if (!Psapi.INSTANCE.GetPerformanceInfo(perfInfo, perfInfo.size())) {
               LOG.error("Failed to get Performance Info. Error code: {}", Kernel32.INSTANCE.GetLastError());
               var6 = 0;
               break label29;
            }

            var6 = perfInfo.ThreadCount.intValue();
         } catch (Throwable var5) {
            try {
               perfInfo.close();
            } catch (Throwable var4) {
               var5.addSuppressed(var4);
            }

            throw var5;
         }

         perfInfo.close();
         return var6;
      }

      perfInfo.close();
      return var6;
   }

   public long getSystemUptime() {
      return querySystemUptime();
   }

   private static long querySystemUptime() {
      return IS_VISTA_OR_GREATER ? Kernel32.INSTANCE.GetTickCount64() / 1000L : (long)Kernel32.INSTANCE.GetTickCount() / 1000L;
   }

   public long getSystemBootTime() {
      return BOOTTIME;
   }

   private static long querySystemBootTime() {
      String eventLog = (String)systemLog.get();
      if (eventLog != null) {
         try {
            Advapi32Util.EventLogIterator iter = new Advapi32Util.EventLogIterator((String)null, eventLog, 8);
            long event6005Time = 0L;

            while(iter.hasNext()) {
               Advapi32Util.EventLogRecord logRecord = iter.next();
               if (logRecord.getStatusCode() == 12) {
                  return logRecord.getRecord().TimeGenerated.longValue();
               }

               if (logRecord.getStatusCode() == 6005) {
                  if (event6005Time > 0L) {
                     return event6005Time;
                  }

                  event6005Time = logRecord.getRecord().TimeGenerated.longValue();
               }
            }

            if (event6005Time > 0L) {
               return event6005Time;
            }
         } catch (Win32Exception var5) {
            LOG.warn("Can't open event log \"{}\".", eventLog);
         }
      }

      return System.currentTimeMillis() / 1000L - querySystemUptime();
   }

   public NetworkParams getNetworkParams() {
      return new WindowsNetworkParams();
   }

   private static boolean enableDebugPrivilege() {
      ByRef.CloseableHANDLEByReference hToken = new ByRef.CloseableHANDLEByReference();

      boolean var15;
      label121: {
         boolean var16;
         label120: {
            boolean var17;
            label119: {
               label118: {
                  try {
                     boolean success = Advapi32.INSTANCE.OpenProcessToken(Kernel32.INSTANCE.GetCurrentProcess(), 40, hToken);
                     if (!success) {
                        LOG.error("OpenProcessToken failed. Error: {}", Native.getLastError());
                        var15 = false;
                        break label121;
                     }

                     try {
                        com.sun.jna.platform.win32.WinNT.LUID luid = new com.sun.jna.platform.win32.WinNT.LUID();
                        success = Advapi32.INSTANCE.LookupPrivilegeValue((String)null, "SeDebugPrivilege", luid);
                        if (!success) {
                           LOG.error("LookupPrivilegeValue failed. Error: {}", Native.getLastError());
                           var16 = false;
                           break label120;
                        }

                        com.sun.jna.platform.win32.WinNT.TOKEN_PRIVILEGES tkp = new com.sun.jna.platform.win32.WinNT.TOKEN_PRIVILEGES(1);
                        tkp.Privileges[0] = new com.sun.jna.platform.win32.WinNT.LUID_AND_ATTRIBUTES(luid, new WinDef.DWORD(2L));
                        success = Advapi32.INSTANCE.AdjustTokenPrivileges(hToken.getValue(), false, tkp, 0, (com.sun.jna.platform.win32.WinNT.TOKEN_PRIVILEGES)null, (IntByReference)null);
                        int err = Native.getLastError();
                        if (!success) {
                           LOG.error("AdjustTokenPrivileges failed. Error: {}", err);
                           var17 = false;
                           break label119;
                        }

                        if (err == 1300) {
                           LOG.debug("Debug privileges not enabled.");
                           var17 = false;
                           break label118;
                        }
                     } finally {
                        Kernel32.INSTANCE.CloseHandle(hToken.getValue());
                     }
                  } catch (Throwable var12) {
                     try {
                        hToken.close();
                     } catch (Throwable var10) {
                        var12.addSuppressed(var10);
                     }

                     throw var12;
                  }

                  hToken.close();
                  return true;
               }

               hToken.close();
               return var17;
            }

            hToken.close();
            return var17;
         }

         hToken.close();
         return var16;
      }

      hToken.close();
      return var15;
   }

   public List getServices() {
      try {
         W32ServiceManager sm = new W32ServiceManager();

         Object var12;
         try {
            sm.open(4);
            Winsvc.ENUM_SERVICE_STATUS_PROCESS[] services = sm.enumServicesStatusExProcess(48, 3, (String)null);
            List<OSService> svcArray = new ArrayList();

            for(Winsvc.ENUM_SERVICE_STATUS_PROCESS service : services) {
               OSService.State state;
               switch (service.ServiceStatusProcess.dwCurrentState) {
                  case 1:
                     state = OSService.State.STOPPED;
                     break;
                  case 4:
                     state = OSService.State.RUNNING;
                     break;
                  default:
                     state = OSService.State.OTHER;
               }

               svcArray.add(new OSService(service.lpDisplayName, service.ServiceStatusProcess.dwProcessId, state));
            }

            var12 = svcArray;
         } catch (Throwable var10) {
            try {
               sm.close();
            } catch (Throwable var9) {
               var10.addSuppressed(var9);
            }

            throw var10;
         }

         sm.close();
         return (List)var12;
      } catch (Win32Exception ex) {
         LOG.error("Win32Exception: {}", ex.getMessage());
         return Collections.emptyList();
      }
   }

   private static String querySystemLog() {
      String systemLog = GlobalConfig.get("oshi.os.windows.eventlog", "System");
      if (systemLog.isEmpty()) {
         return null;
      } else {
         com.sun.jna.platform.win32.WinNT.HANDLE h = Advapi32.INSTANCE.OpenEventLog((String)null, systemLog);
         if (h == null) {
            LOG.warn("Unable to open configured system Event log \"{}\". Calculating boot time from uptime.", systemLog);
            return null;
         } else {
            return systemLog;
         }
      }
   }

   public List getDesktopWindows(boolean visibleOnly) {
      return EnumWindows.queryDesktopWindows(visibleOnly);
   }

   static boolean isX86() {
      return X86;
   }

   private static boolean isCurrentX86() {
      Struct.CloseableSystemInfo sysinfo = new Struct.CloseableSystemInfo();

      boolean var1;
      try {
         Kernel32.INSTANCE.GetNativeSystemInfo(sysinfo);
         var1 = 0 == sysinfo.processorArchitecture.pi.wProcessorArchitecture.intValue();
      } catch (Throwable var4) {
         try {
            sysinfo.close();
         } catch (Throwable var3) {
            var4.addSuppressed(var3);
         }

         throw var4;
      }

      sysinfo.close();
      return var1;
   }

   static boolean isWow() {
      return WOW;
   }

   static boolean isWow(com.sun.jna.platform.win32.WinNT.HANDLE h) {
      if (X86) {
         return true;
      } else {
         ByRef.CloseableIntByReference isWow = new ByRef.CloseableIntByReference();

         boolean var2;
         try {
            Kernel32.INSTANCE.IsWow64Process(h, isWow);
            var2 = isWow.getValue() != 0;
         } catch (Throwable var5) {
            try {
               isWow.close();
            } catch (Throwable var4) {
               var5.addSuppressed(var4);
            }

            throw var5;
         }

         isWow.close();
         return var2;
      }
   }

   private static boolean isCurrentWow() {
      if (X86) {
         return true;
      } else {
         com.sun.jna.platform.win32.WinNT.HANDLE h = Kernel32.INSTANCE.GetCurrentProcess();
         return h == null ? false : isWow(h);
      }
   }

   static {
      systemLog = Memoizer.memoize(WindowsOperatingSystem::querySystemLog, TimeUnit.HOURS.toNanos(1L));
      BOOTTIME = querySystemBootTime();
      enableDebugPrivilege();
      X86 = isCurrentX86();
      WOW = isCurrentWow();
   }
}
