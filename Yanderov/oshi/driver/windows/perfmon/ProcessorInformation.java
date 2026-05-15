package oshi.driver.windows.perfmon;

import com.sun.jna.platform.win32.VersionHelpers;
import java.util.Collections;
import java.util.Map;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.util.platform.windows.PerfCounterQuery;
import oshi.util.platform.windows.PerfCounterWildcardQuery;
import oshi.util.tuples.Pair;

@ThreadSafe
public final class ProcessorInformation {
   private static final boolean IS_WIN7_OR_GREATER = VersionHelpers.IsWindows7OrGreater();

   private ProcessorInformation() {
   }

   public static Pair queryProcessorCounters() {
      if (PerfmonDisabled.PERF_OS_DISABLED) {
         return new Pair(Collections.emptyList(), Collections.emptyMap());
      } else {
         return IS_WIN7_OR_GREATER ? PerfCounterWildcardQuery.queryInstancesAndValues(ProcessorTickCountProperty.class, "Processor Information", "Win32_PerfRawData_Counters_ProcessorInformation WHERE NOT Name LIKE \"%_Total\"") : PerfCounterWildcardQuery.queryInstancesAndValues(ProcessorTickCountProperty.class, "Processor", "Win32_PerfRawData_PerfOS_Processor WHERE Name!=\"_Total\"");
      }
   }

   public static Pair queryProcessorCapacityCounters() {
      return PerfmonDisabled.PERF_OS_DISABLED ? new Pair(Collections.emptyList(), Collections.emptyMap()) : PerfCounterWildcardQuery.queryInstancesAndValues(ProcessorUtilityTickCountProperty.class, "Processor Information", "Win32_PerfRawData_Counters_ProcessorInformation WHERE NOT Name LIKE \"%_Total\"");
   }

   public static Map queryInterruptCounters() {
      return PerfmonDisabled.PERF_OS_DISABLED ? Collections.emptyMap() : PerfCounterQuery.queryValues(InterruptsProperty.class, "Processor", "Win32_PerfRawData_PerfOS_Processor WHERE Name=\"_Total\"");
   }

   public static Pair queryFrequencyCounters() {
      return PerfmonDisabled.PERF_OS_DISABLED ? new Pair(Collections.emptyList(), Collections.emptyMap()) : PerfCounterWildcardQuery.queryInstancesAndValues(ProcessorFrequencyProperty.class, "Processor Information", "Win32_PerfRawData_Counters_ProcessorInformation WHERE NOT Name LIKE \"%_Total\"");
   }

   public static enum ProcessorTickCountProperty implements PerfCounterWildcardQuery.PdhCounterWildcardProperty {
      NAME("^*_Total"),
      PERCENTDPCTIME("% DPC Time"),
      PERCENTINTERRUPTTIME("% Interrupt Time"),
      PERCENTPRIVILEGEDTIME("% Privileged Time"),
      PERCENTPROCESSORTIME("% Processor Time"),
      PERCENTUSERTIME("% User Time");

      private final String counter;

      private ProcessorTickCountProperty(String counter) {
         this.counter = counter;
      }

      public String getCounter() {
         return this.counter;
      }

      // $FF: synthetic method
      private static ProcessorTickCountProperty[] $values() {
         return new ProcessorTickCountProperty[]{NAME, PERCENTDPCTIME, PERCENTINTERRUPTTIME, PERCENTPRIVILEGEDTIME, PERCENTPROCESSORTIME, PERCENTUSERTIME};
      }
   }

   public static enum ProcessorUtilityTickCountProperty implements PerfCounterWildcardQuery.PdhCounterWildcardProperty {
      NAME("^*_Total"),
      PERCENTDPCTIME("% DPC Time"),
      PERCENTINTERRUPTTIME("% Interrupt Time"),
      PERCENTPRIVILEGEDTIME("% Privileged Time"),
      PERCENTPROCESSORTIME("% Processor Time"),
      TIMESTAMP_SYS100NS("% Processor Time_Base"),
      PERCENTPRIVILEGEDUTILITY("% Privileged Utility"),
      PERCENTPROCESSORUTILITY("% Processor Utility"),
      PERCENTPROCESSORUTILITY_BASE("% Processor Utility_Base"),
      PERCENTUSERTIME("% User Time");

      private final String counter;

      private ProcessorUtilityTickCountProperty(String counter) {
         this.counter = counter;
      }

      public String getCounter() {
         return this.counter;
      }

      // $FF: synthetic method
      private static ProcessorUtilityTickCountProperty[] $values() {
         return new ProcessorUtilityTickCountProperty[]{NAME, PERCENTDPCTIME, PERCENTINTERRUPTTIME, PERCENTPRIVILEGEDTIME, PERCENTPROCESSORTIME, TIMESTAMP_SYS100NS, PERCENTPRIVILEGEDUTILITY, PERCENTPROCESSORUTILITY, PERCENTPROCESSORUTILITY_BASE, PERCENTUSERTIME};
      }
   }

   public static enum InterruptsProperty implements PerfCounterQuery.PdhCounterProperty {
      INTERRUPTSPERSEC("_Total", "Interrupts/sec");

      private final String instance;
      private final String counter;

      private InterruptsProperty(String instance, String counter) {
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
      private static InterruptsProperty[] $values() {
         return new InterruptsProperty[]{INTERRUPTSPERSEC};
      }
   }

   public static enum ProcessorFrequencyProperty implements PerfCounterWildcardQuery.PdhCounterWildcardProperty {
      NAME("^*_Total"),
      PERCENTOFMAXIMUMFREQUENCY("% of Maximum Frequency");

      private final String counter;

      private ProcessorFrequencyProperty(String counter) {
         this.counter = counter;
      }

      public String getCounter() {
         return this.counter;
      }

      // $FF: synthetic method
      private static ProcessorFrequencyProperty[] $values() {
         return new ProcessorFrequencyProperty[]{NAME, PERCENTOFMAXIMUMFREQUENCY};
      }
   }
}
