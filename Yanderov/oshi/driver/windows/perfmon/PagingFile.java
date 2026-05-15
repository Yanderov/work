package oshi.driver.windows.perfmon;

import java.util.Collections;
import java.util.Map;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.util.platform.windows.PerfCounterQuery;

@ThreadSafe
public final class PagingFile {
   private PagingFile() {
   }

   public static Map querySwapUsed() {
      return PerfmonDisabled.PERF_OS_DISABLED ? Collections.emptyMap() : PerfCounterQuery.queryValues(PagingPercentProperty.class, "Paging File", "Win32_PerfRawData_PerfOS_PagingFile");
   }

   public static enum PagingPercentProperty implements PerfCounterQuery.PdhCounterProperty {
      PERCENTUSAGE("_Total", "% Usage");

      private final String instance;
      private final String counter;

      private PagingPercentProperty(String instance, String counter) {
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
      private static PagingPercentProperty[] $values() {
         return new PagingPercentProperty[]{PERCENTUSAGE};
      }
   }
}
