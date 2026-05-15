package oshi.software.os.windows;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Advapi32;
import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Kernel32Util;
import com.sun.jna.platform.win32.Shell32Util;
import com.sun.jna.platform.win32.VersionHelpers;
import com.sun.jna.platform.win32.Win32Exception;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.COM.WbemcliUtil;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.driver.windows.registry.ProcessPerformanceData;
import oshi.driver.windows.registry.ProcessWtsData;
import oshi.driver.windows.registry.ThreadPerformanceData;
import oshi.driver.windows.wmi.Win32Process;
import oshi.driver.windows.wmi.Win32ProcessCached;
import oshi.jna.ByRef;
import oshi.jna.platform.windows.NtDll;
import oshi.software.common.AbstractOSProcess;
import oshi.software.os.OSProcess;
import oshi.util.GlobalConfig;
import oshi.util.Memoizer;
import oshi.util.ParseUtil;
import oshi.util.platform.windows.WmiUtil;
import oshi.util.tuples.Pair;
import oshi.util.tuples.Triplet;

@ThreadSafe
public class WindowsOSProcess extends AbstractOSProcess {
   private static final Logger LOG = LoggerFactory.getLogger(WindowsOSProcess.class);
   private static final boolean USE_BATCH_COMMANDLINE = GlobalConfig.get("oshi.os.windows.commandline.batch", false);
   private static final boolean USE_PROCSTATE_SUSPENDED = GlobalConfig.get("oshi.os.windows.procstate.suspended", false);
   private static final boolean IS_VISTA_OR_GREATER = VersionHelpers.IsWindowsVistaOrGreater();
   private static final boolean IS_WINDOWS7_OR_GREATER = VersionHelpers.IsWindows7OrGreater();
   private final WindowsOperatingSystem os;
   private Supplier userInfo = Memoizer.memoize(this::queryUserInfo);
   private Supplier groupInfo = Memoizer.memoize(this::queryGroupInfo);
   private Supplier currentWorkingDirectory = Memoizer.memoize(this::queryCwd);
   private Supplier commandLine = Memoizer.memoize(this::queryCommandLine);
   private Supplier args = Memoizer.memoize(this::queryArguments);
   private Supplier cwdCmdEnv = Memoizer.memoize(this::queryCwdCommandlineEnvironment);
   private String name;
   private String path;
   private OSProcess.State state;
   private int parentProcessID;
   private int threadCount;
   private int priority;
   private long virtualSize;
   private long residentSetSize;
   private long kernelTime;
   private long userTime;
   private long startTime;
   private long upTime;
   private long bytesRead;
   private long bytesWritten;
   private long openFiles;
   private int bitness;
   private long pageFaults;

   public WindowsOSProcess(int pid, WindowsOperatingSystem os, Map processMap, Map processWtsMap, Map threadMap) {
      super(pid);
      this.state = OSProcess.State.INVALID;
      this.os = os;
      this.bitness = os.getBitness();
      this.updateAttributes((ProcessPerformanceData.PerfCounterBlock)processMap.get(pid), (ProcessWtsData.WtsInfo)processWtsMap.get(pid), threadMap);
   }

   public String getName() {
      return this.name;
   }

   public String getPath() {
      return this.path;
   }

   public String getCommandLine() {
      return (String)this.commandLine.get();
   }

   public List getArguments() {
      return (List)this.args.get();
   }

   public Map getEnvironmentVariables() {
      return (Map)((Triplet)this.cwdCmdEnv.get()).getC();
   }

   public String getCurrentWorkingDirectory() {
      return (String)this.currentWorkingDirectory.get();
   }

   public String getUser() {
      return (String)((Pair)this.userInfo.get()).getA();
   }

   public String getUserID() {
      return (String)((Pair)this.userInfo.get()).getB();
   }

   public String getGroup() {
      return (String)((Pair)this.groupInfo.get()).getA();
   }

   public String getGroupID() {
      return (String)((Pair)this.groupInfo.get()).getB();
   }

   public OSProcess.State getState() {
      return this.state;
   }

   public int getParentProcessID() {
      return this.parentProcessID;
   }

   public int getThreadCount() {
      return this.threadCount;
   }

   public int getPriority() {
      return this.priority;
   }

   public long getVirtualSize() {
      return this.virtualSize;
   }

   public long getResidentSetSize() {
      return this.residentSetSize;
   }

   public long getKernelTime() {
      return this.kernelTime;
   }

   public long getUserTime() {
      return this.userTime;
   }

   public long getUpTime() {
      return this.upTime;
   }

   public long getStartTime() {
      return this.startTime;
   }

   public long getBytesRead() {
      return this.bytesRead;
   }

   public long getBytesWritten() {
      return this.bytesWritten;
   }

   public long getOpenFiles() {
      return this.openFiles;
   }

   public long getSoftOpenFileLimit() {
      return WindowsFileSystem.MAX_WINDOWS_HANDLES;
   }

   public long getHardOpenFileLimit() {
      return WindowsFileSystem.MAX_WINDOWS_HANDLES;
   }

   public int getBitness() {
      return this.bitness;
   }

   public long getAffinityMask() {
      WinNT.HANDLE pHandle = Kernel32.INSTANCE.OpenProcess(1024, false, this.getProcessID());
      if (pHandle != null) {
         long var4;
         try {
            ByRef.CloseableULONGptrByReference processAffinity = new ByRef.CloseableULONGptrByReference();

            label130: {
               try {
                  ByRef.CloseableULONGptrByReference systemAffinity = new ByRef.CloseableULONGptrByReference();

                  label109: {
                     try {
                        if (!Kernel32.INSTANCE.GetProcessAffinityMask(pHandle, processAffinity, systemAffinity)) {
                           break label109;
                        }

                        var4 = Pointer.nativeValue(processAffinity.getValue().toPointer());
                     } catch (Throwable var14) {
                        try {
                           systemAffinity.close();
                        } catch (Throwable var13) {
                           var14.addSuppressed(var13);
                        }

                        throw var14;
                     }

                     systemAffinity.close();
                     break label130;
                  }

                  systemAffinity.close();
               } catch (Throwable var15) {
                  try {
                     processAffinity.close();
                  } catch (Throwable var12) {
                     var15.addSuppressed(var12);
                  }

                  throw var15;
               }

               processAffinity.close();
               return 0L;
            }

            processAffinity.close();
         } finally {
            Kernel32.INSTANCE.CloseHandle(pHandle);
         }

         return var4;
      } else {
         return 0L;
      }
   }

   public long getMinorFaults() {
      return this.pageFaults;
   }

   public List getThreadDetails() {
      Map<Integer, ThreadPerformanceData.PerfCounterBlock> threads = ThreadPerformanceData.buildThreadMapFromRegistry(Collections.singleton(this.getProcessID()));
      if (threads != null) {
         threads = ThreadPerformanceData.buildThreadMapFromPerfCounters(Collections.singleton(this.getProcessID()));
      }

      return threads == null ? Collections.emptyList() : (List)((Stream)threads.entrySet().stream().parallel()).map((entry) -> new WindowsOSThread(this.getProcessID(), (Integer)entry.getKey(), this.name, (ThreadPerformanceData.PerfCounterBlock)entry.getValue())).collect(Collectors.toList());
   }

   public boolean updateAttributes() {
      Set<Integer> pids = Collections.singleton(this.getProcessID());
      Map<Integer, ProcessPerformanceData.PerfCounterBlock> pcb = ProcessPerformanceData.buildProcessMapFromRegistry((Collection)null);
      if (pcb == null) {
         pcb = ProcessPerformanceData.buildProcessMapFromPerfCounters(pids);
      }

      Map<Integer, ThreadPerformanceData.PerfCounterBlock> tcb = null;
      if (USE_PROCSTATE_SUSPENDED) {
         tcb = ThreadPerformanceData.buildThreadMapFromRegistry((Collection)null);
         if (tcb == null) {
            tcb = ThreadPerformanceData.buildThreadMapFromPerfCounters((Collection)null);
         }
      }

      Map<Integer, ProcessWtsData.WtsInfo> wts = ProcessWtsData.queryProcessWtsMap(pids);
      return this.updateAttributes((ProcessPerformanceData.PerfCounterBlock)pcb.get(this.getProcessID()), (ProcessWtsData.WtsInfo)wts.get(this.getProcessID()), tcb);
   }

   private boolean updateAttributes(ProcessPerformanceData.PerfCounterBlock pcb, ProcessWtsData.WtsInfo wts, Map threadMap) {
      this.name = pcb.getName();
      this.path = wts.getPath();
      this.parentProcessID = pcb.getParentProcessID();
      this.threadCount = wts.getThreadCount();
      this.priority = pcb.getPriority();
      this.virtualSize = wts.getVirtualSize();
      this.residentSetSize = pcb.getResidentSetSize();
      this.kernelTime = wts.getKernelTime();
      this.userTime = wts.getUserTime();
      this.startTime = pcb.getStartTime();
      this.upTime = pcb.getUpTime();
      this.bytesRead = pcb.getBytesRead();
      this.bytesWritten = pcb.getBytesWritten();
      this.openFiles = wts.getOpenFiles();
      this.pageFaults = pcb.getPageFaults();
      this.state = OSProcess.State.RUNNING;
      if (threadMap != null) {
         int pid = this.getProcessID();

         for(ThreadPerformanceData.PerfCounterBlock tcb : threadMap.values()) {
            if (tcb.getOwningProcessID() == pid) {
               if (tcb.getThreadWaitReason() != 5) {
                  this.state = OSProcess.State.RUNNING;
                  break;
               }

               this.state = OSProcess.State.SUSPENDED;
            }
         }
      }

      WinNT.HANDLE pHandle = Kernel32.INSTANCE.OpenProcess(1024, false, this.getProcessID());
      if (pHandle != null) {
         try {
            if (IS_VISTA_OR_GREATER && this.bitness == 64) {
               ByRef.CloseableIntByReference wow64 = new ByRef.CloseableIntByReference();

               try {
                  if (Kernel32.INSTANCE.IsWow64Process(pHandle, wow64) && wow64.getValue() > 0) {
                     this.bitness = 32;
                  }
               } catch (Throwable var15) {
                  try {
                     wow64.close();
                  } catch (Throwable var13) {
                     var15.addSuppressed(var13);
                  }

                  throw var15;
               }

               wow64.close();
            }

            try {
               if (IS_WINDOWS7_OR_GREATER) {
                  this.path = Kernel32Util.QueryFullProcessImageName(pHandle, 0);
               }
            } catch (Win32Exception var14) {
               this.state = OSProcess.State.INVALID;
            }
         } finally {
            Kernel32.INSTANCE.CloseHandle(pHandle);
         }
      }

      return !this.state.equals(OSProcess.State.INVALID);
   }

   private String queryCommandLine() {
      if (!((String)((Triplet)this.cwdCmdEnv.get()).getB()).isEmpty()) {
         return (String)((Triplet)this.cwdCmdEnv.get()).getB();
      } else if (USE_BATCH_COMMANDLINE) {
         return Win32ProcessCached.getInstance().getCommandLine(this.getProcessID(), this.getStartTime());
      } else {
         WbemcliUtil.WmiResult<Win32Process.CommandLineProperty> commandLineProcs = Win32Process.queryCommandLines(Collections.singleton(this.getProcessID()));
         return commandLineProcs.getResultCount() > 0 ? WmiUtil.getString(commandLineProcs, Win32Process.CommandLineProperty.COMMANDLINE, 0) : "";
      }
   }

   private List queryArguments() {
      String cl = this.getCommandLine();
      return !cl.isEmpty() ? Arrays.asList(Shell32Util.CommandLineToArgv(cl)) : Collections.emptyList();
   }

   private String queryCwd() {
      if (!((String)((Triplet)this.cwdCmdEnv.get()).getA()).isEmpty()) {
         return (String)((Triplet)this.cwdCmdEnv.get()).getA();
      } else {
         if (this.getProcessID() == this.os.getProcessId()) {
            String cwd = (new File(".")).getAbsolutePath();
            if (!cwd.isEmpty()) {
               return cwd.substring(0, cwd.length() - 1);
            }
         }

         return "";
      }
   }

   private Pair queryUserInfo() {
      Pair<String, String> pair = null;
      WinNT.HANDLE pHandle = Kernel32.INSTANCE.OpenProcess(1024, false, this.getProcessID());
      if (pHandle != null) {
         ByRef.CloseableHANDLEByReference phToken = new ByRef.CloseableHANDLEByReference();

         try {
            try {
               if (Advapi32.INSTANCE.OpenProcessToken(pHandle, 10, phToken)) {
                  Advapi32Util.Account account = Advapi32Util.getTokenAccount(phToken.getValue());
                  pair = new Pair(account.name, account.sidString);
               } else {
                  int error = Kernel32.INSTANCE.GetLastError();
                  if (error != 5) {
                     LOG.error("Failed to get process token for process {}: {}", this.getProcessID(), Kernel32.INSTANCE.GetLastError());
                  }
               }
            } catch (Win32Exception e) {
               LOG.warn("Failed to query user info for process {} ({}): {}", new Object[]{this.getProcessID(), this.getName(), e.getMessage()});
            } finally {
               WinNT.HANDLE token = phToken.getValue();
               if (token != null) {
                  Kernel32.INSTANCE.CloseHandle(token);
               }

               Kernel32.INSTANCE.CloseHandle(pHandle);
            }
         } catch (Throwable var14) {
            try {
               phToken.close();
            } catch (Throwable var11) {
               var14.addSuppressed(var11);
            }

            throw var14;
         }

         phToken.close();
      }

      return pair == null ? new Pair("unknown", "unknown") : pair;
   }

   private Pair queryGroupInfo() {
      Pair<String, String> pair = null;
      WinNT.HANDLE pHandle = Kernel32.INSTANCE.OpenProcess(1024, false, this.getProcessID());
      if (pHandle != null) {
         ByRef.CloseableHANDLEByReference phToken = new ByRef.CloseableHANDLEByReference();

         try {
            if (Advapi32.INSTANCE.OpenProcessToken(pHandle, 10, phToken)) {
               Advapi32Util.Account account = Advapi32Util.getTokenPrimaryGroup(phToken.getValue());
               pair = new Pair(account.name, account.sidString);
            } else {
               int error = Kernel32.INSTANCE.GetLastError();
               if (error != 5) {
                  LOG.error("Failed to get process token for process {}: {}", this.getProcessID(), Kernel32.INSTANCE.GetLastError());
               }
            }

            WinNT.HANDLE token = phToken.getValue();
            if (token != null) {
               Kernel32.INSTANCE.CloseHandle(token);
            }

            Kernel32.INSTANCE.CloseHandle(pHandle);
         } catch (Throwable var7) {
            try {
               phToken.close();
            } catch (Throwable var6) {
               var7.addSuppressed(var6);
            }

            throw var7;
         }

         phToken.close();
      }

      return pair == null ? new Pair("unknown", "unknown") : pair;
   }

   private Triplet queryCwdCommandlineEnvironment() {
      WinNT.HANDLE h = Kernel32.INSTANCE.OpenProcess(1040, false, this.getProcessID());
      if (h != null) {
         Triplet var25;
         try {
            ByRef.CloseableIntByReference nRead;
            label239: {
               Triplet var26;
               label240: {
                  Triplet var27;
                  label241: {
                     Triplet var13;
                     label242: {
                        if (WindowsOperatingSystem.isX86() != WindowsOperatingSystem.isWow(h)) {
                           return defaultCwdCommandlineEnvironment();
                        }

                        nRead = new ByRef.CloseableIntByReference();

                        Triplet var28;
                        try {
                           label217: {
                              NtDll.PROCESS_BASIC_INFORMATION pbi = new NtDll.PROCESS_BASIC_INFORMATION();
                              int ret = NtDll.INSTANCE.NtQueryInformationProcess(h, 0, pbi.getPointer(), pbi.size(), nRead);
                              if (ret != 0) {
                                 var25 = defaultCwdCommandlineEnvironment();
                                 break label239;
                              }

                              pbi.read();
                              NtDll.PEB peb = new NtDll.PEB();
                              Kernel32.INSTANCE.ReadProcessMemory(h, pbi.PebBaseAddress, peb.getPointer(), peb.size(), nRead);
                              if (nRead.getValue() == 0) {
                                 var26 = defaultCwdCommandlineEnvironment();
                                 break label240;
                              }

                              peb.read();
                              NtDll.RTL_USER_PROCESS_PARAMETERS upp = new NtDll.RTL_USER_PROCESS_PARAMETERS();
                              Kernel32.INSTANCE.ReadProcessMemory(h, peb.ProcessParameters, upp.getPointer(), upp.size(), nRead);
                              if (nRead.getValue() == 0) {
                                 var27 = defaultCwdCommandlineEnvironment();
                                 break label241;
                              }

                              label224: {
                                 upp.read();
                                 String cwd = readUnicodeString(h, upp.CurrentDirectory.DosPath);
                                 String cl = readUnicodeString(h, upp.CommandLine);
                                 int envSize = upp.EnvironmentSize.intValue();
                                 if (envSize > 0) {
                                    buffer = new Memory((long)envSize);

                                    try {
                                       Kernel32.INSTANCE.ReadProcessMemory(h, upp.Environment, buffer, envSize, nRead);
                                       if (nRead.getValue() > 0) {
                                          char[] env = buffer.getCharArray(0L, envSize / 2);
                                          Map<String, String> envMap = ParseUtil.parseCharArrayToStringMap(env);
                                          envMap.remove("");
                                          var13 = new Triplet(cwd, cl, Collections.unmodifiableMap(envMap));
                                          break label224;
                                       }
                                    } catch (Throwable var22) {
                                       try {
                                          buffer.close();
                                       } catch (Throwable var21) {
                                          var22.addSuppressed(var21);
                                       }

                                       throw var22;
                                    }

                                    buffer.close();
                                 }

                                 var28 = new Triplet(cwd, cl, Collections.emptyMap());
                                 break label217;
                              }

                              buffer.close();
                              break label242;
                           }
                        } catch (Throwable var23) {
                           try {
                              nRead.close();
                           } catch (Throwable var20) {
                              var23.addSuppressed(var20);
                           }

                           throw var23;
                        }

                        nRead.close();
                        return var28;
                     }

                     nRead.close();
                     return var13;
                  }

                  nRead.close();
                  return var27;
               }

               nRead.close();
               return var26;
            }

            nRead.close();
         } finally {
            Kernel32.INSTANCE.CloseHandle(h);
         }

         return var25;
      } else {
         return defaultCwdCommandlineEnvironment();
      }
   }

   private static Triplet defaultCwdCommandlineEnvironment() {
      return new Triplet("", "", Collections.emptyMap());
   }

   private static String readUnicodeString(WinNT.HANDLE h, NtDll.UNICODE_STRING s) {
      Memory m;
      String var4;
      label58: {
         if (s.Length > 0) {
            m = new Memory((long)s.Length + 2L);

            try {
               ByRef.CloseableIntByReference nRead = new ByRef.CloseableIntByReference();

               label45: {
                  try {
                     m.clear();
                     Kernel32.INSTANCE.ReadProcessMemory(h, s.Buffer, m, s.Length, nRead);
                     if (nRead.getValue() <= 0) {
                        break label45;
                     }

                     var4 = m.getWideString(0L);
                  } catch (Throwable var8) {
                     try {
                        nRead.close();
                     } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                     }

                     throw var8;
                  }

                  nRead.close();
                  break label58;
               }

               nRead.close();
            } catch (Throwable var9) {
               try {
                  m.close();
               } catch (Throwable var6) {
                  var9.addSuppressed(var6);
               }

               throw var9;
            }

            m.close();
         }

         return "";
      }

      m.close();
      return var4;
   }
}
