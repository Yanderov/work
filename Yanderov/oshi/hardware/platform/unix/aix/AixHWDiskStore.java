package oshi.hardware.platform.unix.aix;

import com.sun.jna.Native;
import com.sun.jna.platform.unix.aix.Perfstat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.driver.unix.aix.Ls;
import oshi.driver.unix.aix.Lscfg;
import oshi.driver.unix.aix.Lspv;
import oshi.hardware.HWPartition;
import oshi.hardware.common.AbstractHWDiskStore;
import oshi.util.tuples.Pair;

@ThreadSafe
public final class AixHWDiskStore extends AbstractHWDiskStore {
   private final Supplier diskStats;
   private long reads = 0L;
   private long readBytes = 0L;
   private long writes = 0L;
   private long writeBytes = 0L;
   private long currentQueueLength = 0L;
   private long transferTime = 0L;
   private long timeStamp = 0L;
   private List partitionList;

   private AixHWDiskStore(String name, String model, String serial, long size, Supplier diskStats) {
      super(name, model, serial, size);
      this.diskStats = diskStats;
   }

   public synchronized long getReads() {
      return this.reads;
   }

   public synchronized long getReadBytes() {
      return this.readBytes;
   }

   public synchronized long getWrites() {
      return this.writes;
   }

   public synchronized long getWriteBytes() {
      return this.writeBytes;
   }

   public synchronized long getCurrentQueueLength() {
      return this.currentQueueLength;
   }

   public synchronized long getTransferTime() {
      return this.transferTime;
   }

   public synchronized long getTimeStamp() {
      return this.timeStamp;
   }

   public List getPartitions() {
      return this.partitionList;
   }

   public synchronized boolean updateAttributes() {
      long now = System.currentTimeMillis();

      for(Perfstat.perfstat_disk_t stat : (Perfstat.perfstat_disk_t[])this.diskStats.get()) {
         String name = Native.toString(stat.name);
         if (name.equals(this.getName())) {
            long blks = stat.rblks + stat.wblks;
            if (blks == 0L) {
               this.reads = stat.xfers;
               this.writes = 0L;
            } else {
               long approximateReads = Math.round((double)(stat.xfers * stat.rblks) / (double)blks);
               long approximateWrites = stat.xfers - approximateReads;
               if (approximateReads > this.reads) {
                  this.reads = approximateReads;
               }

               if (approximateWrites > this.writes) {
                  this.writes = approximateWrites;
               }
            }

            this.readBytes = stat.rblks * stat.bsize;
            this.writeBytes = stat.wblks * stat.bsize;
            this.currentQueueLength = stat.qdepth;
            this.transferTime = stat.time;
            this.timeStamp = now;
            return true;
         }
      }

      return false;
   }

   public static List getDisks(Supplier diskStats) {
      Map<String, Pair<Integer, Integer>> majMinMap = Ls.queryDeviceMajorMinor();
      List<AixHWDiskStore> storeList = new ArrayList();

      for(Perfstat.perfstat_disk_t disk : (Perfstat.perfstat_disk_t[])diskStats.get()) {
         String storeName = Native.toString(disk.name);
         Pair<String, String> ms = Lscfg.queryModelSerial(storeName);
         String model = ms.getA() == null ? Native.toString(disk.description) : (String)ms.getA();
         String serial = ms.getB() == null ? "unknown" : (String)ms.getB();
         storeList.add(createStore(storeName, model, serial, disk.size << 20, diskStats, majMinMap));
      }

      return (List)storeList.stream().sorted(Comparator.comparingInt((s) -> s.getPartitions().isEmpty() ? Integer.MAX_VALUE : ((HWPartition)s.getPartitions().get(0)).getMajor())).collect(Collectors.toList());
   }

   private static AixHWDiskStore createStore(String diskName, String model, String serial, long size, Supplier diskStats, Map majMinMap) {
      AixHWDiskStore store = new AixHWDiskStore(diskName, model.isEmpty() ? "unknown" : model, serial, size, diskStats);
      store.partitionList = Lspv.queryLogicalVolumes(diskName, majMinMap);
      store.updateAttributes();
      return store;
   }
}
