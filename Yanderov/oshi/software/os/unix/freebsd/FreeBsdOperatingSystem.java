package oshi.software.os.unix.freebsd;

import com.sun.jna.Structure;
import com.sun.jna.ptr.NativeLongByReference;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.driver.unix.freebsd.Who;
import oshi.jna.platform.unix.FreeBsdLibc;
import oshi.software.common.AbstractOperatingSystem;
import oshi.software.os.FileSystem;
import oshi.software.os.InternetProtocolStats;
import oshi.software.os.NetworkParams;
import oshi.software.os.OSProcess;
import oshi.software.os.OSService;
import oshi.software.os.OSThread;
import oshi.software.os.OperatingSystem;
import oshi.util.ExecutingCommand;
import oshi.util.ParseUtil;
import oshi.util.platform.unix.freebsd.BsdSysctlUtil;
import oshi.util.tuples.Pair;

@ThreadSafe
public class FreeBsdOperatingSystem extends AbstractOperatingSystem {
   private static final Logger LOG = LoggerFactory.getLogger(FreeBsdOperatingSystem.class);
   private static final long BOOTTIME = querySystemBootTime();
   static final String PS_COMMAND_ARGS = (String)Arrays.stream(FreeBsdOperatingSystem.PsKeywords.values()).map(Enum::name).map(String::toLowerCase).collect(Collectors.joining(","));

   public String queryManufacturer() {
      return "Unix/BSD";
   }

   public Pair queryFamilyVersionInfo() {
      String family = BsdSysctlUtil.sysctl("kern.ostype", "FreeBSD");
      String version = BsdSysctlUtil.sysctl("kern.osrelease", "");
      String versionInfo = BsdSysctlUtil.sysctl("kern.version", "");
      String buildNumber = versionInfo.split(":")[0].replace(family, "").replace(version, "").trim();
      return new Pair(family, new OperatingSystem.OSVersionInfo(version, (String)null, buildNumber));
   }

   protected int queryBitness(int jvmBitness) {
      return jvmBitness < 64 && ExecutingCommand.getFirstAnswer("uname -m").indexOf("64") == -1 ? jvmBitness : 64;
   }

   public FileSystem getFileSystem() {
      return new FreeBsdFileSystem();
   }

   public InternetProtocolStats getInternetProtocolStats() {
      return new FreeBsdInternetProtocolStats();
   }

   public List getSessions() {
      return USE_WHO_COMMAND ? super.getSessions() : Who.queryUtxent();
   }

   public List queryAllProcesses() {
      return this.getProcessListFromPS(-1);
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
      List<OSProcess> procs = this.getProcessListFromPS(pid);
      return procs.isEmpty() ? null : (OSProcess)procs.get(0);
   }

   private List getProcessListFromPS(int pid) {
      String psCommand = "ps -awwxo " + PS_COMMAND_ARGS;
      if (pid >= 0) {
         psCommand = psCommand + " -p " + pid;
      }

      Predicate<Map<PsKeywords, String>> hasKeywordArgs = (psMap) -> psMap.containsKey(FreeBsdOperatingSystem.PsKeywords.ARGS);
      return (List)((Stream)ExecutingCommand.runNative(psCommand).stream().skip(1L).parallel()).map((proc) -> ParseUtil.stringToEnumMap(PsKeywords.class, proc.trim(), ' ')).filter(hasKeywordArgs).map((psMap) -> new FreeBsdOSProcess(pid < 0 ? ParseUtil.parseIntOrDefault((String)psMap.get(FreeBsdOperatingSystem.PsKeywords.PID), 0) : pid, psMap, this)).filter(OperatingSystem.ProcessFiltering.VALID_PROCESS).collect(Collectors.toList());
   }

   public int getProcessId() {
      return FreeBsdLibc.INSTANCE.getpid();
   }

   public int getProcessCount() {
      List<String> procList = ExecutingCommand.runNative("ps -axo pid");
      return !procList.isEmpty() ? procList.size() - 1 : 0;
   }

   public int getThreadId() {
      NativeLongByReference pTid = new NativeLongByReference();
      return FreeBsdLibc.INSTANCE.thr_self(pTid) < 0 ? 0 : pTid.getValue().intValue();
   }

   public OSThread getCurrentThread() {
      OSProcess proc = this.getCurrentProcess();
      int tid = this.getThreadId();
      return (OSThread)proc.getThreadDetails().stream().filter((t) -> t.getThreadId() == tid).findFirst().orElse(new FreeBsdOSThread(proc.getProcessID(), tid));
   }

   public int getThreadCount() {
      int threads = 0;

      for(String proc : ExecutingCommand.runNative("ps -axo nlwp")) {
         threads += ParseUtil.parseIntOrDefault(proc.trim(), 0);
      }

      return threads;
   }

   public long getSystemUptime() {
      return System.currentTimeMillis() / 1000L - BOOTTIME;
   }

   public long getSystemBootTime() {
      return BOOTTIME;
   }

   private static long querySystemBootTime() {
      FreeBsdLibc.Timeval tv = new FreeBsdLibc.Timeval();
      return BsdSysctlUtil.sysctl("kern.boottime", (Structure)tv) && tv.tv_sec != 0L ? tv.tv_sec : ParseUtil.parseLongOrDefault(ExecutingCommand.getFirstAnswer("sysctl -n kern.boottime").split(",")[0].replaceAll("\\D", ""), System.currentTimeMillis() / 1000L);
   }

   public NetworkParams getNetworkParams() {
      return new FreeBsdNetworkParams();
   }

   public List getServices() {
      List<OSService> services = new ArrayList();
      Set<String> running = new HashSet();

      for(OSProcess p : this.getChildProcesses(1, OperatingSystem.ProcessFiltering.ALL_PROCESSES, OperatingSystem.ProcessSorting.PID_ASC, 0)) {
         OSService s = new OSService(p.getName(), p.getProcessID(), OSService.State.RUNNING);
         services.add(s);
         running.add(p.getName());
      }

      File dir = new File("/etc/rc.d");
      File[] listFiles;
      if (dir.exists() && dir.isDirectory() && (listFiles = dir.listFiles()) != null) {
         for(File f : listFiles) {
            String name = f.getName();
            if (!running.contains(name)) {
               OSService s = new OSService(name, 0, OSService.State.STOPPED);
               services.add(s);
            }
         }
      } else {
         LOG.error("Directory: /etc/init does not exist");
      }

      return services;
   }

   static enum PsKeywords {
      STATE,
      PID,
      PPID,
      USER,
      UID,
      GROUP,
      GID,
      NLWP,
      PRI,
      VSZ,
      RSS,
      ETIMES,
      SYSTIME,
      TIME,
      COMM,
      MAJFLT,
      MINFLT,
      NVCSW,
      NIVCSW,
      ARGS;

      // $FF: synthetic method
      private static PsKeywords[] $values() {
         return new PsKeywords[]{STATE, PID, PPID, USER, UID, GROUP, GID, NLWP, PRI, VSZ, RSS, ETIMES, SYSTIME, TIME, COMM, MAJFLT, MINFLT, NVCSW, NIVCSW, ARGS};
      }
   }
}
