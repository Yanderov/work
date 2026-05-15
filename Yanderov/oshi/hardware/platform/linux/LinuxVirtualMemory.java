package oshi.hardware.platform.linux;

import java.util.function.Supplier;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.hardware.common.AbstractVirtualMemory;
import oshi.util.FileUtil;
import oshi.util.Memoizer;
import oshi.util.ParseUtil;
import oshi.util.platform.linux.ProcPath;
import oshi.util.tuples.Pair;
import oshi.util.tuples.Triplet;

@ThreadSafe
final class LinuxVirtualMemory extends AbstractVirtualMemory {
   private final LinuxGlobalMemory global;
   private final Supplier usedTotalCommitLim = Memoizer.memoize(LinuxVirtualMemory::queryMemInfo, Memoizer.defaultExpiration());
   private final Supplier inOut = Memoizer.memoize(LinuxVirtualMemory::queryVmStat, Memoizer.defaultExpiration());

   LinuxVirtualMemory(LinuxGlobalMemory linuxGlobalMemory) {
      this.global = linuxGlobalMemory;
   }

   public long getSwapUsed() {
      return (Long)((Triplet)this.usedTotalCommitLim.get()).getA();
   }

   public long getSwapTotal() {
      return (Long)((Triplet)this.usedTotalCommitLim.get()).getB();
   }

   public long getVirtualMax() {
      return (Long)((Triplet)this.usedTotalCommitLim.get()).getC();
   }

   public long getVirtualInUse() {
      return this.global.getTotal() - this.global.getAvailable() + this.getSwapUsed();
   }

   public long getSwapPagesIn() {
      return (Long)((Pair)this.inOut.get()).getA();
   }

   public long getSwapPagesOut() {
      return (Long)((Pair)this.inOut.get()).getB();
   }

   private static Triplet queryMemInfo() {
      long swapFree = 0L;
      long swapTotal = 0L;
      long commitLimit = 0L;

      for(String checkLine : FileUtil.readFile(ProcPath.MEMINFO)) {
         String[] memorySplit = ParseUtil.whitespaces.split(checkLine);
         if (memorySplit.length > 1) {
            switch (memorySplit[0]) {
               case "SwapTotal:":
                  swapTotal = parseMeminfo(memorySplit);
                  break;
               case "SwapFree:":
                  swapFree = parseMeminfo(memorySplit);
                  break;
               case "CommitLimit:":
                  commitLimit = parseMeminfo(memorySplit);
            }
         }
      }

      return new Triplet(swapTotal - swapFree, swapTotal, commitLimit);
   }

   private static Pair queryVmStat() {
      long swapPagesIn = 0L;
      long swapPagesOut = 0L;

      for(String checkLine : FileUtil.readFile(ProcPath.VMSTAT)) {
         String[] memorySplit = ParseUtil.whitespaces.split(checkLine);
         if (memorySplit.length > 1) {
            switch (memorySplit[0]) {
               case "pswpin":
                  swapPagesIn = ParseUtil.parseLongOrDefault(memorySplit[1], 0L);
                  break;
               case "pswpout":
                  swapPagesOut = ParseUtil.parseLongOrDefault(memorySplit[1], 0L);
            }
         }
      }

      return new Pair(swapPagesIn, swapPagesOut);
   }

   private static long parseMeminfo(String[] memorySplit) {
      if (memorySplit.length < 2) {
         return 0L;
      } else {
         long memory = ParseUtil.parseLongOrDefault(memorySplit[1], 0L);
         if (memorySplit.length > 2 && "kB".equals(memorySplit[2])) {
            memory *= 1024L;
         }

         return memory;
      }
   }
}
