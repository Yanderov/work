package oshi.software.os.unix.freebsd;

import java.util.List;
import java.util.Map;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.software.common.AbstractOSThread;
import oshi.software.os.OSProcess;
import oshi.util.ExecutingCommand;
import oshi.util.ParseUtil;

@ThreadSafe
public class FreeBsdOSThread extends AbstractOSThread {
   private int threadId;
   private String name = "";
   private OSProcess.State state;
   private long minorFaults;
   private long majorFaults;
   private long startMemoryAddress;
   private long contextSwitches;
   private long kernelTime;
   private long userTime;
   private long startTime;
   private long upTime;
   private int priority;

   public FreeBsdOSThread(int processId, Map threadMap) {
      super(processId);
      this.state = OSProcess.State.INVALID;
      this.updateAttributes(threadMap);
   }

   public FreeBsdOSThread(int processId, int threadId) {
      super(processId);
      this.state = OSProcess.State.INVALID;
      this.threadId = threadId;
      this.updateAttributes();
   }

   public int getThreadId() {
      return this.threadId;
   }

   public String getName() {
      return this.name;
   }

   public OSProcess.State getState() {
      return this.state;
   }

   public long getStartMemoryAddress() {
      return this.startMemoryAddress;
   }

   public long getContextSwitches() {
      return this.contextSwitches;
   }

   public long getMinorFaults() {
      return this.minorFaults;
   }

   public long getMajorFaults() {
      return this.majorFaults;
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

   public int getPriority() {
      return this.priority;
   }

   public boolean updateAttributes() {
      List<String> threadList = ExecutingCommand.runNative("ps -awwxo " + FreeBsdOSProcess.PS_THREAD_COLUMNS + " -H -p " + this.getOwningProcessId());
      String lwpStr = Integer.toString(this.threadId);

      for(String psOutput : threadList) {
         Map<FreeBsdOSProcess.PsThreadColumns, String> threadMap = ParseUtil.stringToEnumMap(FreeBsdOSProcess.PsThreadColumns.class, psOutput.trim(), ' ');
         if (threadMap.containsKey(FreeBsdOSProcess.PsThreadColumns.PRI) && lwpStr.equals(threadMap.get(FreeBsdOSProcess.PsThreadColumns.LWP))) {
            return this.updateAttributes(threadMap);
         }
      }

      this.state = OSProcess.State.INVALID;
      return false;
   }

   private boolean updateAttributes(Map threadMap) {
      this.name = (String)threadMap.get(FreeBsdOSProcess.PsThreadColumns.TDNAME);
      this.threadId = ParseUtil.parseIntOrDefault((String)threadMap.get(FreeBsdOSProcess.PsThreadColumns.LWP), 0);
      switch (((String)threadMap.get(FreeBsdOSProcess.PsThreadColumns.STATE)).charAt(0)) {
         case 'D':
         case 'L':
         case 'U':
            this.state = OSProcess.State.WAITING;
            break;
         case 'E':
         case 'F':
         case 'G':
         case 'H':
         case 'J':
         case 'K':
         case 'M':
         case 'N':
         case 'O':
         case 'P':
         case 'Q':
         case 'V':
         case 'W':
         case 'X':
         case 'Y':
         default:
            this.state = OSProcess.State.OTHER;
            break;
         case 'I':
         case 'S':
            this.state = OSProcess.State.SLEEPING;
            break;
         case 'R':
            this.state = OSProcess.State.RUNNING;
            break;
         case 'T':
            this.state = OSProcess.State.STOPPED;
            break;
         case 'Z':
            this.state = OSProcess.State.ZOMBIE;
      }

      long elapsedTime = ParseUtil.parseDHMSOrDefault((String)threadMap.get(FreeBsdOSProcess.PsThreadColumns.ETIMES), 0L);
      this.upTime = elapsedTime < 1L ? 1L : elapsedTime;
      long now = System.currentTimeMillis();
      this.startTime = now - this.upTime;
      this.kernelTime = ParseUtil.parseDHMSOrDefault((String)threadMap.get(FreeBsdOSProcess.PsThreadColumns.SYSTIME), 0L);
      this.userTime = ParseUtil.parseDHMSOrDefault((String)threadMap.get(FreeBsdOSProcess.PsThreadColumns.TIME), 0L) - this.kernelTime;
      this.startMemoryAddress = ParseUtil.hexStringToLong((String)threadMap.get(FreeBsdOSProcess.PsThreadColumns.TDADDR), 0L);
      long nonVoluntaryContextSwitches = ParseUtil.parseLongOrDefault((String)threadMap.get(FreeBsdOSProcess.PsThreadColumns.NIVCSW), 0L);
      long voluntaryContextSwitches = ParseUtil.parseLongOrDefault((String)threadMap.get(FreeBsdOSProcess.PsThreadColumns.NVCSW), 0L);
      this.contextSwitches = voluntaryContextSwitches + nonVoluntaryContextSwitches;
      this.majorFaults = ParseUtil.parseLongOrDefault((String)threadMap.get(FreeBsdOSProcess.PsThreadColumns.MAJFLT), 0L);
      this.minorFaults = ParseUtil.parseLongOrDefault((String)threadMap.get(FreeBsdOSProcess.PsThreadColumns.MINFLT), 0L);
      this.priority = ParseUtil.parseIntOrDefault((String)threadMap.get(FreeBsdOSProcess.PsThreadColumns.PRI), 0);
      return true;
   }
}
