package oshi.software.common;

import java.util.function.Supplier;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.software.os.OSProcess;
import oshi.util.Memoizer;

@ThreadSafe
public abstract class AbstractOSProcess implements OSProcess {
   private final Supplier cumulativeCpuLoad = Memoizer.memoize(this::queryCumulativeCpuLoad, Memoizer.defaultExpiration());
   private int processID;

   protected AbstractOSProcess(int pid) {
      this.processID = pid;
   }

   public int getProcessID() {
      return this.processID;
   }

   public double getProcessCpuLoadCumulative() {
      return (Double)this.cumulativeCpuLoad.get();
   }

   private double queryCumulativeCpuLoad() {
      return (double)this.getUpTime() > (double)0.0F ? (double)(this.getKernelTime() + this.getUserTime()) / (double)this.getUpTime() : (double)0.0F;
   }

   public double getProcessCpuLoadBetweenTicks(OSProcess priorSnapshot) {
      return priorSnapshot != null && this.processID == priorSnapshot.getProcessID() && this.getUpTime() > priorSnapshot.getUpTime() ? (double)(this.getUserTime() - priorSnapshot.getUserTime() + this.getKernelTime() - priorSnapshot.getKernelTime()) / (double)(this.getUpTime() - priorSnapshot.getUpTime()) : this.getProcessCpuLoadCumulative();
   }

   public String toString() {
      StringBuilder builder = new StringBuilder("OSProcess@");
      builder.append(Integer.toHexString(this.hashCode()));
      builder.append("[processID=").append(this.processID);
      builder.append(", name=").append(this.getName()).append(']');
      return builder.toString();
   }
}
