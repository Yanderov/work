package oshi.software.os.mac;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.mac.SystemB;
import com.sun.jna.platform.unix.LibCAPI;
import com.sun.jna.platform.unix.Resource;
import com.sun.jna.platform.unix.LibCAPI.size_t;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.driver.mac.ThreadInfo;
import oshi.jna.Struct;
import oshi.software.common.AbstractOSProcess;
import oshi.software.os.OSProcess;
import oshi.util.GlobalConfig;
import oshi.util.Memoizer;
import oshi.util.platform.mac.SysctlUtil;
import oshi.util.tuples.Pair;

@ThreadSafe
public class MacOSProcess extends AbstractOSProcess {
   private static final Logger LOG = LoggerFactory.getLogger(MacOSProcess.class);
   private static final int ARGMAX = SysctlUtil.sysctl("kern.argmax", 0);
   private static final boolean LOG_MAC_SYSCTL_WARNING = GlobalConfig.get("oshi.os.mac.sysctl.logwarning", false);
   private static final int MAC_RLIMIT_NOFILE = 8;
   private static final int P_LP64 = 4;
   private static final int SSLEEP = 1;
   private static final int SWAIT = 2;
   private static final int SRUN = 3;
   private static final int SIDL = 4;
   private static final int SZOMB = 5;
   private static final int SSTOP = 6;
   private int majorVersion;
   private int minorVersion;
   private final MacOperatingSystem os;
   private Supplier commandLine = Memoizer.memoize(this::queryCommandLine);
   private Supplier argsEnviron = Memoizer.memoize(this::queryArgsAndEnvironment);
   private String name = "";
   private String path = "";
   private String currentWorkingDirectory;
   private String user;
   private String userID;
   private String group;
   private String groupID;
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
   private long minorFaults;
   private long majorFaults;
   private long contextSwitches;

   public MacOSProcess(int pid, int major, int minor, MacOperatingSystem os) {
      super(pid);
      this.state = OSProcess.State.INVALID;
      this.majorVersion = major;
      this.minorVersion = minor;
      this.os = os;
      this.updateAttributes();
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

   private String queryCommandLine() {
      return String.join(" ", this.getArguments());
   }

   public List getArguments() {
      return (List)((Pair)this.argsEnviron.get()).getA();
   }

   public Map getEnvironmentVariables() {
      return (Map)((Pair)this.argsEnviron.get()).getB();
   }

   private Pair queryArgsAndEnvironment() {
      int pid = this.getProcessID();
      List<String> args = new ArrayList();
      Map<String, String> env = new LinkedHashMap();
      int[] mib = new int[]{1, 49, pid};
      Memory procargs = new Memory((long)ARGMAX);

      try {
         procargs.clear();
         LibCAPI.size_t.ByReference size = new LibCAPI.size_t.ByReference((long)ARGMAX);
         if (0 == SystemB.INSTANCE.sysctl(mib, mib.length, procargs, size, (Pointer)null, size_t.ZERO)) {
            int nargs = procargs.getInt(0L);
            if (nargs > 0 && nargs <= 1024) {
               long offset = (long)SystemB.INT_SIZE;

               String arg;
               for(long var14 = offset + (long)procargs.getString(offset).length(); var14 < size.longValue(); var14 += (long)arg.length()) {
                  while(procargs.getByte(var14) == 0 && ++var14 < size.longValue()) {
                  }

                  arg = procargs.getString(var14);
                  if (nargs-- > 0) {
                     args.add(arg);
                  } else {
                     int idx = arg.indexOf(61);
                     if (idx > 0) {
                        env.put(arg.substring(0, idx), arg.substring(idx + 1));
                     }
                  }
               }
            }
         } else if (pid > 0 && LOG_MAC_SYSCTL_WARNING) {
            LOG.warn("Failed sysctl call for process arguments (kern.procargs2), process {} may not exist. Error code: {}", pid, Native.getLastError());
         }
      } catch (Throwable var13) {
         try {
            procargs.close();
         } catch (Throwable var12) {
            var13.addSuppressed(var12);
         }

         throw var13;
      }

      procargs.close();
      return new Pair(Collections.unmodifiableList(args), Collections.unmodifiableMap(env));
   }

   public String getCurrentWorkingDirectory() {
      return this.currentWorkingDirectory;
   }

   public String getUser() {
      return this.user;
   }

   public String getUserID() {
      return this.userID;
   }

   public String getGroup() {
      return this.group;
   }

   public String getGroupID() {
      return this.groupID;
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

   public List getThreadDetails() {
      long now = System.currentTimeMillis();
      return (List)((Stream)ThreadInfo.queryTaskThreads(this.getProcessID()).stream().parallel()).map((stat) -> {
         long start = Math.max(now - stat.getUpTime(), this.getStartTime());
         return new MacOSThread(this.getProcessID(), stat.getThreadId(), stat.getState(), stat.getSystemTime(), stat.getUserTime(), start, now - start, stat.getPriority());
      }).collect(Collectors.toList());
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
      if (this.getProcessID() == this.os.getProcessId()) {
         Resource.Rlimit rlimit = new Resource.Rlimit();
         SystemB.INSTANCE.getrlimit(8, rlimit);
         return rlimit.rlim_cur;
      } else {
         return -1L;
      }
   }

   public long getHardOpenFileLimit() {
      if (this.getProcessID() == this.os.getProcessId()) {
         Resource.Rlimit rlimit = new Resource.Rlimit();
         SystemB.INSTANCE.getrlimit(8, rlimit);
         return rlimit.rlim_max;
      } else {
         return -1L;
      }
   }

   public int getBitness() {
      return this.bitness;
   }

   public long getAffinityMask() {
      int logicalProcessorCount = SysctlUtil.sysctl("hw.logicalcpu", 1);
      return logicalProcessorCount < 64 ? (1L << logicalProcessorCount) - 1L : -1L;
   }

   public long getMinorFaults() {
      return this.minorFaults;
   }

   public long getMajorFaults() {
      return this.majorFaults;
   }

   public long getContextSwitches() {
      return this.contextSwitches;
   }

   public boolean updateAttributes() {
      long now = System.currentTimeMillis();
      Struct.CloseableProcTaskAllInfo taskAllInfo = new Struct.CloseableProcTaskAllInfo();

      boolean var18;
      label143: {
         try {
            if (0 > SystemB.INSTANCE.proc_pidinfo(this.getProcessID(), 2, 0L, taskAllInfo, taskAllInfo.size()) || taskAllInfo.ptinfo.pti_threadnum < 1) {
               this.state = OSProcess.State.INVALID;
               var18 = false;
               break label143;
            }

            Memory buf = new Memory(4096L);

            try {
               if (0 < SystemB.INSTANCE.proc_pidpath(this.getProcessID(), buf, 4096)) {
                  this.path = buf.getString(0L).trim();
                  String[] pathSplit = this.path.split("/");
                  if (pathSplit.length > 0) {
                     this.name = pathSplit[pathSplit.length - 1];
                  }
               }
            } catch (Throwable var11) {
               try {
                  buf.close();
               } catch (Throwable var10) {
                  var11.addSuppressed(var10);
               }

               throw var11;
            }

            buf.close();
            if (this.name.isEmpty()) {
               this.name = Native.toString(taskAllInfo.pbsd.pbi_comm, StandardCharsets.UTF_8);
            }

            switch (taskAllInfo.pbsd.pbi_status) {
               case 1:
                  this.state = OSProcess.State.SLEEPING;
                  break;
               case 2:
                  this.state = OSProcess.State.WAITING;
                  break;
               case 3:
                  this.state = OSProcess.State.RUNNING;
                  break;
               case 4:
                  this.state = OSProcess.State.NEW;
                  break;
               case 5:
                  this.state = OSProcess.State.ZOMBIE;
                  break;
               case 6:
                  this.state = OSProcess.State.STOPPED;
                  break;
               default:
                  this.state = OSProcess.State.OTHER;
            }

            this.parentProcessID = taskAllInfo.pbsd.pbi_ppid;
            this.userID = Integer.toString(taskAllInfo.pbsd.pbi_uid);
            SystemB.Passwd pwuid = SystemB.INSTANCE.getpwuid(taskAllInfo.pbsd.pbi_uid);
            if (pwuid != null) {
               this.user = pwuid.pw_name;
            }

            this.groupID = Integer.toString(taskAllInfo.pbsd.pbi_gid);
            SystemB.Group grgid = SystemB.INSTANCE.getgrgid(taskAllInfo.pbsd.pbi_gid);
            if (grgid != null) {
               this.group = grgid.gr_name;
            }

            this.threadCount = taskAllInfo.ptinfo.pti_threadnum;
            this.priority = taskAllInfo.ptinfo.pti_priority;
            this.virtualSize = taskAllInfo.ptinfo.pti_virtual_size;
            this.residentSetSize = taskAllInfo.ptinfo.pti_resident_size;
            this.kernelTime = taskAllInfo.ptinfo.pti_total_system / 1000000L;
            this.userTime = taskAllInfo.ptinfo.pti_total_user / 1000000L;
            this.startTime = taskAllInfo.pbsd.pbi_start_tvsec * 1000L + taskAllInfo.pbsd.pbi_start_tvusec / 1000L;
            this.upTime = now - this.startTime;
            this.openFiles = (long)taskAllInfo.pbsd.pbi_nfiles;
            this.bitness = (taskAllInfo.pbsd.pbi_flags & 4) == 0 ? 32 : 64;
            this.majorFaults = (long)taskAllInfo.ptinfo.pti_pageins;
            this.minorFaults = (long)(taskAllInfo.ptinfo.pti_faults - taskAllInfo.ptinfo.pti_pageins);
            this.contextSwitches = (long)taskAllInfo.ptinfo.pti_csw;
         } catch (Throwable var14) {
            try {
               taskAllInfo.close();
            } catch (Throwable var9) {
               var14.addSuppressed(var9);
            }

            throw var14;
         }

         taskAllInfo.close();
         if (this.majorVersion > 10 || this.minorVersion >= 9) {
            Struct.CloseableRUsageInfoV2 rUsageInfoV2 = new Struct.CloseableRUsageInfoV2();

            try {
               if (0 == SystemB.INSTANCE.proc_pid_rusage(this.getProcessID(), 2, rUsageInfoV2)) {
                  this.bytesRead = rUsageInfoV2.ri_diskio_bytesread;
                  this.bytesWritten = rUsageInfoV2.ri_diskio_byteswritten;
               }
            } catch (Throwable var13) {
               try {
                  rUsageInfoV2.close();
               } catch (Throwable var8) {
                  var13.addSuppressed(var8);
               }

               throw var13;
            }

            rUsageInfoV2.close();
         }

         Struct.CloseableVnodePathInfo vpi = new Struct.CloseableVnodePathInfo();

         try {
            if (0 < SystemB.INSTANCE.proc_pidinfo(this.getProcessID(), 9, 0L, vpi, vpi.size())) {
               this.currentWorkingDirectory = Native.toString(vpi.pvi_cdir.vip_path, StandardCharsets.US_ASCII);
            }
         } catch (Throwable var12) {
            try {
               vpi.close();
            } catch (Throwable var7) {
               var12.addSuppressed(var7);
            }

            throw var12;
         }

         vpi.close();
         return true;
      }

      taskAllInfo.close();
      return var18;
   }
}
