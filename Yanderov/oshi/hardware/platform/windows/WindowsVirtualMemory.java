package oshi.hardware.platform.windows;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Psapi;
import java.util.Map;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.driver.windows.perfmon.MemoryInformation;
import oshi.driver.windows.perfmon.PagingFile;
import oshi.hardware.common.AbstractVirtualMemory;
import oshi.jna.Struct;
import oshi.util.Memoizer;
import oshi.util.tuples.Pair;
import oshi.util.tuples.Triplet;

@ThreadSafe
final class WindowsVirtualMemory extends AbstractVirtualMemory {
   private static final Logger LOG = LoggerFactory.getLogger(WindowsVirtualMemory.class);
   private final WindowsGlobalMemory global;
   private final Supplier used = Memoizer.memoize(WindowsVirtualMemory::querySwapUsed, Memoizer.defaultExpiration());
   private final Supplier totalVmaxVused = Memoizer.memoize(WindowsVirtualMemory::querySwapTotalVirtMaxVirtUsed, Memoizer.defaultExpiration());
   private final Supplier swapInOut = Memoizer.memoize(WindowsVirtualMemory::queryPageSwaps, Memoizer.defaultExpiration());

   WindowsVirtualMemory(WindowsGlobalMemory windowsGlobalMemory) {
      this.global = windowsGlobalMemory;
   }

   public long getSwapUsed() {
      return this.global.getPageSize() * (Long)this.used.get();
   }

   public long getSwapTotal() {
      return this.global.getPageSize() * (Long)((Triplet)this.totalVmaxVused.get()).getA();
   }

   public long getVirtualMax() {
      return this.global.getPageSize() * (Long)((Triplet)this.totalVmaxVused.get()).getB();
   }

   public long getVirtualInUse() {
      return this.global.getPageSize() * (Long)((Triplet)this.totalVmaxVused.get()).getC();
   }

   public long getSwapPagesIn() {
      return (Long)((Pair)this.swapInOut.get()).getA();
   }

   public long getSwapPagesOut() {
      return (Long)((Pair)this.swapInOut.get()).getB();
   }

   private static long querySwapUsed() {
      return (Long)PagingFile.querySwapUsed().getOrDefault(PagingFile.PagingPercentProperty.PERCENTUSAGE, 0L);
   }

   private static Triplet querySwapTotalVirtMaxVirtUsed() {
      Struct.CloseablePerformanceInformation perfInfo = new Struct.CloseablePerformanceInformation();

      Triplet var5;
      label29: {
         try {
            if (!Psapi.INSTANCE.GetPerformanceInfo(perfInfo, perfInfo.size())) {
               LOG.error("Failed to get Performance Info. Error code: {}", Kernel32.INSTANCE.GetLastError());
               var5 = new Triplet(0L, 0L, 0L);
               break label29;
            }

            var5 = new Triplet(perfInfo.CommitLimit.longValue() - perfInfo.PhysicalTotal.longValue(), perfInfo.CommitLimit.longValue(), perfInfo.CommitTotal.longValue());
         } catch (Throwable var4) {
            try {
               perfInfo.close();
            } catch (Throwable var3) {
               var4.addSuppressed(var3);
            }

            throw var4;
         }

         perfInfo.close();
         return var5;
      }

      perfInfo.close();
      return var5;
   }

   private static Pair queryPageSwaps() {
      Map<MemoryInformation.PageSwapProperty, Long> valueMap = MemoryInformation.queryPageSwaps();
      return new Pair((Long)valueMap.getOrDefault(MemoryInformation.PageSwapProperty.PAGESINPUTPERSEC, 0L), (Long)valueMap.getOrDefault(MemoryInformation.PageSwapProperty.PAGESOUTPUTPERSEC, 0L));
   }
}
