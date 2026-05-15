package oshi.driver.windows.perfmon;

import java.util.Collections;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.util.platform.windows.PerfCounterWildcardQuery;
import oshi.util.tuples.Pair;

@ThreadSafe
public final class ProcessInformation {
   private ProcessInformation() {
   }

   public static Pair queryProcessCounters() {
      return PerfmonDisabled.PERF_PROC_DISABLED ? new Pair(Collections.emptyList(), Collections.emptyMap()) : PerfCounterWildcardQuery.queryInstancesAndValues(ProcessPerformanceProperty.class, "Process", "Win32_PerfRawData_PerfProc_Process WHERE NOT Name LIKE \"%_Total\"");
   }

   public static Pair queryHandles() {
      return PerfmonDisabled.PERF_PROC_DISABLED ? new Pair(Collections.emptyList(), Collections.emptyMap()) : PerfCounterWildcardQuery.queryInstancesAndValues(HandleCountProperty.class, "Process", "Win32_PerfRawData_PerfProc_Process");
   }

   public static Pair queryIdleProcessCounters() {
      return PerfmonDisabled.PERF_OS_DISABLED ? new Pair(Collections.emptyList(), Collections.emptyMap()) : PerfCounterWildcardQuery.queryInstancesAndValues(IdleProcessorTimeProperty.class, "Process", "Win32_PerfRawData_PerfProc_Process WHERE IDProcess=0");
   }

   public static enum ProcessPerformanceProperty implements PerfCounterWildcardQuery.PdhCounterWildcardProperty {
      NAME("^*_Total"),
      PRIORITYBASE("Priority Base"),
      ELAPSEDTIME("Elapsed Time"),
      IDPROCESS("ID Process"),
      CREATINGPROCESSID("Creating Process ID"),
      IOREADBYTESPERSEC("IO Read Bytes/sec"),
      IOWRITEBYTESPERSEC("IO Write Bytes/sec"),
      PRIVATEBYTES("Working Set - Private"),
      PAGEFAULTSPERSEC("Page Faults/sec");

      private final String counter;

      private ProcessPerformanceProperty(String counter) {
         this.counter = counter;
      }

      public String getCounter() {
         return this.counter;
      }

      // $FF: synthetic method
      private static ProcessPerformanceProperty[] $values() {
         return new ProcessPerformanceProperty[]{NAME, PRIORITYBASE, ELAPSEDTIME, IDPROCESS, CREATINGPROCESSID, IOREADBYTESPERSEC, IOWRITEBYTESPERSEC, PRIVATEBYTES, PAGEFAULTSPERSEC};
      }
   }

   public static enum HandleCountProperty implements PerfCounterWildcardQuery.PdhCounterWildcardProperty {
      NAME("_Total"),
      HANDLECOUNT("Handle Count");

      private final String counter;

      private HandleCountProperty(String counter) {
         this.counter = counter;
      }

      public String getCounter() {
         return this.counter;
      }

      // $FF: synthetic method
      private static HandleCountProperty[] $values() {
         return new HandleCountProperty[]{NAME, HANDLECOUNT};
      }
   }

   public static enum IdleProcessorTimeProperty implements PerfCounterWildcardQuery.PdhCounterWildcardProperty {
      NAME("_Total|Idle"),
      PERCENTPROCESSORTIME("% Processor Time"),
      ELAPSEDTIME("Elapsed Time");

      private final String counter;

      private IdleProcessorTimeProperty(String counter) {
         this.counter = counter;
      }

      public String getCounter() {
         return this.counter;
      }

      // $FF: synthetic method
      private static IdleProcessorTimeProperty[] $values() {
         return new IdleProcessorTimeProperty[]{NAME, PERCENTPROCESSORTIME, ELAPSEDTIME};
      }
   }
}
