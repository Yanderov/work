package oshi.driver.windows.perfmon;

import java.util.Collections;
import java.util.Map;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.util.platform.windows.PerfCounterQuery;

@ThreadSafe
public final class MemoryInformation {
   private MemoryInformation() {
   }

   public static Map queryPageSwaps() {
      return PerfmonDisabled.PERF_OS_DISABLED ? Collections.emptyMap() : PerfCounterQuery.queryValues(PageSwapProperty.class, "Memory", "Win32_PerfRawData_PerfOS_Memory");
   }

   public static enum PageSwapProperty implements PerfCounterQuery.PdhCounterProperty {
      PAGESINPUTPERSEC((String)null, "Pages Input/sec"),
      PAGESOUTPUTPERSEC((String)null, "Pages Output/sec");

      private final String instance;
      private final String counter;

      private PageSwapProperty(String instance, String counter) {
         this.instance = instance;
         this.counter = counter;
      }

      public String getInstance() {
         return this.instance;
      }

      public String getCounter() {
         return this.counter;
      }

      // $FF: synthetic method
      private static PageSwapProperty[] $values() {
         return new PageSwapProperty[]{PAGESINPUTPERSEC, PAGESOUTPUTPERSEC};
      }
   }
}
