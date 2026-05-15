package oshi.software.os.unix.solaris;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import java.util.function.Supplier;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.driver.unix.solaris.PsInfo;
import oshi.jna.platform.unix.SolarisLibc;
import oshi.software.common.AbstractOSThread;
import oshi.software.os.OSProcess;
import oshi.util.Memoizer;
import oshi.util.Util;

@ThreadSafe
public class SolarisOSThread extends AbstractOSThread {
   private Supplier lwpsinfo = Memoizer.memoize(this::queryLwpsInfo, Memoizer.defaultExpiration());
   private Supplier prusage = Memoizer.memoize(this::queryPrUsage, Memoizer.defaultExpiration());
   private String name;
   private int threadId;
   private OSProcess.State state;
   private long startMemoryAddress;
   private long contextSwitches;
   private long kernelTime;
   private long userTime;
   private long startTime;
   private long upTime;
   private int priority;

   public SolarisOSThread(int pid, int lwpid) {
      super(pid);
      this.state = OSProcess.State.INVALID;
      this.threadId = lwpid;
      this.updateAttributes();
   }

   private SolarisLibc.SolarisLwpsInfo queryLwpsInfo() {
      return PsInfo.queryLwpsInfo(this.getOwningProcessId(), this.getThreadId());
   }

   private SolarisLibc.SolarisPrUsage queryPrUsage() {
      return PsInfo.queryPrUsage(this.getOwningProcessId(), this.getThreadId());
   }

   public String getName() {
      return this.name != null ? this.name : "";
   }

   public int getThreadId() {
      return this.threadId;
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
      SolarisLibc.SolarisLwpsInfo info = (SolarisLibc.SolarisLwpsInfo)this.lwpsinfo.get();
      if (info == null) {
         this.state = OSProcess.State.INVALID;
         return false;
      } else {
         SolarisLibc.SolarisPrUsage usage = (SolarisLibc.SolarisPrUsage)this.prusage.get();
         long now = System.currentTimeMillis();
         this.state = SolarisOSProcess.getStateFromOutput((char)info.pr_sname);
         this.startTime = info.pr_start.tv_sec.longValue() * 1000L + info.pr_start.tv_nsec.longValue() / 1000000L;
         long elapsedTime = now - this.startTime;
         this.upTime = elapsedTime < 1L ? 1L : elapsedTime;
         this.kernelTime = 0L;
         this.userTime = info.pr_time.tv_sec.longValue() * 1000L + info.pr_time.tv_nsec.longValue() / 1000000L;
         this.startMemoryAddress = Pointer.nativeValue(info.pr_addr);
         this.priority = info.pr_pri;
         if (usage != null) {
            this.userTime = usage.pr_utime.tv_sec.longValue() * 1000L + usage.pr_utime.tv_nsec.longValue() / 1000000L;
            this.kernelTime = usage.pr_stime.tv_sec.longValue() * 1000L + usage.pr_stime.tv_nsec.longValue() / 1000000L;
            this.contextSwitches = usage.pr_ictx.longValue() + usage.pr_vctx.longValue();
         }

         this.name = Native.toString(info.pr_name);
         if (Util.isBlank(this.name)) {
            this.name = Native.toString(info.pr_oldname);
         }

         return true;
      }
   }
}
