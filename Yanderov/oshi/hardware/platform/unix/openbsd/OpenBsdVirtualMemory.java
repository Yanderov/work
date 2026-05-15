package oshi.hardware.platform.unix.openbsd;

import java.util.function.Supplier;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.hardware.common.AbstractVirtualMemory;
import oshi.util.ExecutingCommand;
import oshi.util.Memoizer;
import oshi.util.ParseUtil;
import oshi.util.tuples.Triplet;

@ThreadSafe
final class OpenBsdVirtualMemory extends AbstractVirtualMemory {
   private final OpenBsdGlobalMemory global;
   private final Supplier usedTotalPgin = Memoizer.memoize(OpenBsdVirtualMemory::queryVmstat, Memoizer.defaultExpiration());
   private final Supplier pgout = Memoizer.memoize(OpenBsdVirtualMemory::queryUvm, Memoizer.defaultExpiration());

   OpenBsdVirtualMemory(OpenBsdGlobalMemory freeBsdGlobalMemory) {
      this.global = freeBsdGlobalMemory;
   }

   public long getSwapUsed() {
      return (long)(Integer)((Triplet)this.usedTotalPgin.get()).getA() * this.global.getPageSize();
   }

   public long getSwapTotal() {
      return (long)(Integer)((Triplet)this.usedTotalPgin.get()).getB() * this.global.getPageSize();
   }

   public long getVirtualMax() {
      return this.global.getTotal() + this.getSwapTotal();
   }

   public long getVirtualInUse() {
      return this.global.getTotal() - this.global.getAvailable() + this.getSwapUsed();
   }

   public long getSwapPagesIn() {
      return (long)(Integer)((Triplet)this.usedTotalPgin.get()).getC() * this.global.getPageSize();
   }

   public long getSwapPagesOut() {
      return (long)(Integer)this.pgout.get() * this.global.getPageSize();
   }

   private static Triplet queryVmstat() {
      int used = 0;
      int total = 0;
      int swapIn = 0;

      for(String line : ExecutingCommand.runNative("vmstat -s")) {
         if (line.contains("swap pages in use")) {
            used = ParseUtil.getFirstIntValue(line);
         } else if (line.contains("swap pages")) {
            total = ParseUtil.getFirstIntValue(line);
         } else if (line.contains("pagein operations")) {
            swapIn = ParseUtil.getFirstIntValue(line);
         }
      }

      return new Triplet(used, total, swapIn);
   }

   private static int queryUvm() {
      for(String line : ExecutingCommand.runNative("systat -ab uvm")) {
         if (line.contains("pdpageouts")) {
            return ParseUtil.getFirstIntValue(line);
         }
      }

      return 0;
   }
}
