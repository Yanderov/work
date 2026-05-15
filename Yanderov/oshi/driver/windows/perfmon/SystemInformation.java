package oshi.driver.windows.perfmon;

import java.util.Collections;
import java.util.Map;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.util.platform.windows.PerfCounterQuery;

@ThreadSafe
public final class SystemInformation {
   private SystemInformation() {
   }

   public static Map queryContextSwitchCounters() {
      return PerfmonDisabled.PERF_OS_DISABLED ? Collections.emptyMap() : PerfCounterQuery.queryValues(ContextSwitchProperty.class, "System", "Win32_PerfRawData_PerfOS_System");
   }

   public static Map queryProcessorQueueLength() {
      return PerfmonDisabled.PERF_OS_DISABLED ? Collections.emptyMap() : PerfCounterQuery.queryValues(ProcessorQueueLengthProperty.class, "System", "Win32_PerfRawData_PerfOS_System");
   }

   public static enum ContextSwitchProperty implements PerfCounterQuery.PdhCounterProperty {
      CONTEXTSWITCHESPERSEC((String)null, "Context Switches/sec");

      private final String instance;
      private final String counter;

      private ContextSwitchProperty(String instance, String counter) {
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
      private static ContextSwitchProperty[] $values() {
         return new ContextSwitchProperty[]{CONTEXTSWITCHESPERSEC};
      }
   }

   public static enum ProcessorQueueLengthProperty implements PerfCounterQuery.PdhCounterProperty {
      PROCESSORQUEUELENGTH((String)null, "Processor Queue Length");

      private final String instance;
      private final String counter;

      private ProcessorQueueLengthProperty(String instance, String counter) {
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
      private static ProcessorQueueLengthProperty[] $values() {
         return new ProcessorQueueLengthProperty[]{PROCESSORQUEUELENGTH};
      }
   }
}
