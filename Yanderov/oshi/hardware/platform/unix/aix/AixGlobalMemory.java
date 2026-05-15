package oshi.hardware.platform.unix.aix;

import com.sun.jna.platform.unix.aix.Perfstat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.driver.unix.aix.perfstat.PerfstatMemory;
import oshi.hardware.PhysicalMemory;
import oshi.hardware.VirtualMemory;
import oshi.hardware.common.AbstractGlobalMemory;
import oshi.util.Memoizer;
import oshi.util.ParseUtil;

@ThreadSafe
final class AixGlobalMemory extends AbstractGlobalMemory {
   private final Supplier perfstatMem = Memoizer.memoize(AixGlobalMemory::queryPerfstat, Memoizer.defaultExpiration());
   private final Supplier lscfg;
   private static final long PAGESIZE = 4096L;
   private final Supplier vm = Memoizer.memoize(this::createVirtualMemory);

   AixGlobalMemory(Supplier lscfg) {
      this.lscfg = lscfg;
   }

   public long getAvailable() {
      return ((Perfstat.perfstat_memory_total_t)this.perfstatMem.get()).real_avail * 4096L;
   }

   public long getTotal() {
      return ((Perfstat.perfstat_memory_total_t)this.perfstatMem.get()).real_total * 4096L;
   }

   public long getPageSize() {
      return 4096L;
   }

   public VirtualMemory getVirtualMemory() {
      return (VirtualMemory)this.vm.get();
   }

   public List getPhysicalMemory() {
      List<PhysicalMemory> pmList = new ArrayList();
      boolean isMemModule = false;
      String bankLabel = "unknown";
      String locator = "";
      long capacity = 0L;

      for(String line : (List)this.lscfg.get()) {
         String s = line.trim();
         if (s.endsWith("memory-module")) {
            isMemModule = true;
         } else if (isMemModule) {
            if (s.startsWith("Node:")) {
               bankLabel = s.substring(5).trim();
               if (bankLabel.startsWith("IBM,")) {
                  bankLabel = bankLabel.substring(4);
               }
            } else if (s.startsWith("Physical Location:")) {
               locator = "/" + s.substring(18).trim();
            } else if (s.startsWith("Size")) {
               capacity = ParseUtil.parseLongOrDefault(ParseUtil.removeLeadingDots(s.substring(4).trim()), 0L) << 20;
            } else if (s.startsWith("Hardware Location Code")) {
               if (capacity > 0L) {
                  pmList.add(new PhysicalMemory(bankLabel + locator, capacity, 0L, "IBM", "unknown"));
               }

               bankLabel = "unknown";
               locator = "";
               capacity = 0L;
               isMemModule = false;
            }
         }
      }

      return pmList;
   }

   private static Perfstat.perfstat_memory_total_t queryPerfstat() {
      return PerfstatMemory.queryMemoryTotal();
   }

   private VirtualMemory createVirtualMemory() {
      return new AixVirtualMemory(this.perfstatMem);
   }
}
