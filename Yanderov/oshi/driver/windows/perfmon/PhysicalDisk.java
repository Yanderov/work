package oshi.driver.windows.perfmon;

import java.util.Collections;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.util.platform.windows.PerfCounterWildcardQuery;
import oshi.util.tuples.Pair;

@ThreadSafe
public final class PhysicalDisk {
   private PhysicalDisk() {
   }

   public static Pair queryDiskCounters() {
      return PerfmonDisabled.PERF_DISK_DISABLED ? new Pair(Collections.emptyList(), Collections.emptyMap()) : PerfCounterWildcardQuery.queryInstancesAndValues(PhysicalDiskProperty.class, "PhysicalDisk", "Win32_PerfRawData_PerfDisk_PhysicalDisk WHERE Name!=\"_Total\"");
   }

   public static enum PhysicalDiskProperty implements PerfCounterWildcardQuery.PdhCounterWildcardProperty {
      NAME("^_Total"),
      DISKREADSPERSEC("Disk Reads/sec"),
      DISKREADBYTESPERSEC("Disk Read Bytes/sec"),
      DISKWRITESPERSEC("Disk Writes/sec"),
      DISKWRITEBYTESPERSEC("Disk Write Bytes/sec"),
      CURRENTDISKQUEUELENGTH("Current Disk Queue Length"),
      PERCENTDISKTIME("% Disk Time");

      private final String counter;

      private PhysicalDiskProperty(String counter) {
         this.counter = counter;
      }

      public String getCounter() {
         return this.counter;
      }

      // $FF: synthetic method
      private static PhysicalDiskProperty[] $values() {
         return new PhysicalDiskProperty[]{NAME, DISKREADSPERSEC, DISKREADBYTESPERSEC, DISKWRITESPERSEC, DISKWRITEBYTESPERSEC, CURRENTDISKQUEUELENGTH, PERCENTDISKTIME};
      }
   }
}
