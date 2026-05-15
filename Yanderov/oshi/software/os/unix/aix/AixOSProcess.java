package oshi.software.os.unix.aix;

import com.sun.jna.Native;
import com.sun.jna.platform.unix.Resource;
import com.sun.jna.platform.unix.aix.Perfstat;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.driver.unix.aix.PsInfo;
import oshi.driver.unix.aix.perfstat.PerfstatCpu;
import oshi.jna.platform.unix.AixLibc;
import oshi.software.common.AbstractOSProcess;
import oshi.software.os.OSProcess;
import oshi.software.os.OSThread;
import oshi.util.Constants;
import oshi.util.ExecutingCommand;
import oshi.util.Memoizer;
import oshi.util.ParseUtil;
import oshi.util.UserGroupInfo;
import oshi.util.tuples.Pair;

@ThreadSafe
public class AixOSProcess extends AbstractOSProcess {
   private static final Logger LOG = LoggerFactory.getLogger(AixOSProcess.class);
   private Supplier bitness = Memoizer.memoize(this::queryBitness);
   private Supplier psinfo = Memoizer.memoize(this::queryPsInfo, Memoizer.defaultExpiration());
   private Supplier commandLine = Memoizer.memoize(this::queryCommandLine);
   private Supplier cmdEnv = Memoizer.memoize(this::queryCommandlineEnvironment);
   private final Supplier affinityMask = Memoizer.memoize(PerfstatCpu::queryCpuAffinityMask, Memoizer.defaultExpiration());
   private String name;
   private String path = "";
   private String commandLineBackup;
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
   private Supplier procCpu;
   private final AixOperatingSystem os;

   public AixOSProcess(int pid, Pair userSysCpuTime, Supplier procCpu, AixOperatingSystem os) {
      super(pid);
      this.state = OSProcess.State.INVALID;
      this.procCpu = procCpu;
      this.os = os;
      this.updateAttributes(userSysCpuTime);
   }

   private AixLibc.AixPsInfo queryPsInfo() {
      return PsInfo.queryPsInfo(this.getProcessID());
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
      String cl = String.join(" ", this.getArguments());
      return cl.isEmpty() ? this.commandLineBackup : cl;
   }

   public List getArguments() {
      return (List)((Pair)this.cmdEnv.get()).getA();
   }

   public Map getEnvironmentVariables() {
      return (Map)((Pair)this.cmdEnv.get()).getB();
   }

   private Pair queryCommandlineEnvironment() {
      return PsInfo.queryArgsEnv(this.getProcessID(), (AixLibc.AixPsInfo)this.psinfo.get());
   }

   public String getCurrentWorkingDirectory() {
      try {
         String cwdLink = "/proc" + this.getProcessID() + "/cwd";
         String cwd = (new File(cwdLink)).getCanonicalPath();
         if (!cwd.equals(cwdLink)) {
            return cwd;
         }
      } catch (IOException e) {
         LOG.trace("Couldn't find cwd for pid {}: {}", this.getProcessID(), e.getMessage());
      }

      return "";
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
      try {
         Stream<Path> fd = Files.list(Paths.get("/proc/" + this.getProcessID() + "/fd"));

         long var2;
         try {
            var2 = fd.count();
         } catch (Throwable var5) {
            if (fd != null) {
               try {
                  fd.close();
               } catch (Throwable var4) {
                  var5.addSuppressed(var4);
               }
            }

            throw var5;
         }

         if (fd != null) {
            fd.close();
         }

         return var2;
      } catch (IOException var6) {
         return 0L;
      }
   }

   public long getSoftOpenFileLimit() {
      if (this.getProcessID() == this.os.getProcessId()) {
         Resource.Rlimit rlimit = new Resource.Rlimit();
         AixLibc.INSTANCE.getrlimit(7, rlimit);
         return rlimit.rlim_cur;
      } else {
         return -1L;
      }
   }

   public long getHardOpenFileLimit() {
      if (this.getProcessID() == this.os.getProcessId()) {
         Resource.Rlimit rlimit = new Resource.Rlimit();
         AixLibc.INSTANCE.getrlimit(7, rlimit);
         return rlimit.rlim_max;
      } else {
         return -1L;
      }
   }

   public int getBitness() {
      return (Integer)this.bitness.get();
   }

   private int queryBitness() {
      for(String line : ExecutingCommand.runNative("pflags " + this.getProcessID())) {
         if (line.contains("data model")) {
            if (line.contains("LP32")) {
               return 32;
            }

            if (line.contains("LP64")) {
               return 64;
            }
         }
      }

      return 0;
   }

   public long getAffinityMask() {
      long mask = 0L;
      File directory = new File(String.format("/proc/%d/lwp", this.getProcessID()));
      File[] numericFiles = directory.listFiles((file) -> Constants.DIGITS.matcher(file.getName()).matches());
      if (numericFiles == null) {
         return mask;
      } else {
         for(File lwpidFile : numericFiles) {
            int lwpidNum = ParseUtil.parseIntOrDefault(lwpidFile.getName(), 0);
            AixLibc.AixLwpsInfo info = PsInfo.queryLwpsInfo(this.getProcessID(), lwpidNum);
            if (info != null) {
               mask |= (long)info.pr_bindpro;
            }
         }

         mask &= (Long)this.affinityMask.get();
         return mask;
      }
   }

   public List getThreadDetails() {
      File directory = new File(String.format("/proc/%d/lwp", this.getProcessID()));
      File[] numericFiles = directory.listFiles((file) -> Constants.DIGITS.matcher(file.getName()).matches());
      return numericFiles == null ? Collections.emptyList() : (List)((Stream)Arrays.stream(numericFiles).parallel()).map((lwpidFile) -> new AixOSThread(this.getProcessID(), ParseUtil.parseIntOrDefault(lwpidFile.getName(), 0))).filter(OSThread.ThreadFiltering.VALID_THREAD).collect(Collectors.toList());
   }

   public boolean updateAttributes() {
      Perfstat.perfstat_process_t[] perfstat = (Perfstat.perfstat_process_t[])this.procCpu.get();

      for(Perfstat.perfstat_process_t stat : perfstat) {
         int statpid = (int)stat.pid;
         if (statpid == this.getProcessID()) {
            return this.updateAttributes(new Pair((long)stat.ucpu_time, (long)stat.scpu_time));
         }
      }

      this.state = OSProcess.State.INVALID;
      return false;
   }

   private boolean updateAttributes(Pair userSysCpuTime) {
      AixLibc.AixPsInfo info = (AixLibc.AixPsInfo)this.psinfo.get();
      if (info == null) {
         this.state = OSProcess.State.INVALID;
         return false;
      } else {
         long now = System.currentTimeMillis();
         this.state = getStateFromOutput((char)info.pr_lwp.pr_sname);
         this.parentProcessID = (int)info.pr_ppid;
         this.userID = Long.toString(info.pr_euid);
         this.user = UserGroupInfo.getUser(this.userID);
         this.groupID = Long.toString(info.pr_egid);
         this.group = UserGroupInfo.getGroupName(this.groupID);
         this.threadCount = info.pr_nlwp;
         this.priority = info.pr_lwp.pr_pri;
         this.virtualSize = info.pr_size * 1024L;
         this.residentSetSize = info.pr_rssize * 1024L;
         this.startTime = info.pr_start.tv_sec * 1000L + (long)info.pr_start.tv_nsec / 1000000L;
         long elapsedTime = now - this.startTime;
         this.upTime = elapsedTime < 1L ? 1L : elapsedTime;
         this.userTime = (Long)userSysCpuTime.getA();
         this.kernelTime = (Long)userSysCpuTime.getB();
         this.commandLineBackup = Native.toString(info.pr_psargs);
         this.path = ParseUtil.whitespaces.split(this.commandLineBackup)[0];
         this.name = this.path.substring(this.path.lastIndexOf(47) + 1);
         if (this.name.isEmpty()) {
            this.name = Native.toString(info.pr_fname);
         }

         return true;
      }
   }

   static OSProcess.State getStateFromOutput(char stateValue) {
      OSProcess.State state;
      switch (stateValue) {
         case 'A':
         case 'R':
            state = OSProcess.State.RUNNING;
            break;
         case 'B':
         case 'C':
         case 'D':
         case 'E':
         case 'F':
         case 'G':
         case 'H':
         case 'J':
         case 'K':
         case 'L':
         case 'M':
         case 'N':
         case 'P':
         case 'Q':
         case 'U':
         case 'V':
         case 'X':
         case 'Y':
         default:
            state = OSProcess.State.OTHER;
            break;
         case 'I':
            state = OSProcess.State.WAITING;
            break;
         case 'O':
            state = OSProcess.State.INVALID;
            break;
         case 'S':
         case 'W':
            state = OSProcess.State.SLEEPING;
            break;
         case 'T':
            state = OSProcess.State.STOPPED;
            break;
         case 'Z':
            state = OSProcess.State.ZOMBIE;
      }

      return state;
   }
}
