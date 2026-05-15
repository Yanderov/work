package oshi.driver.windows.wmi;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.util.platform.windows.WmiQueryHandler;

@ThreadSafe
public final class Win32DiskPartition {
   private static final String WIN32_DISK_PARTITION = "Win32_DiskPartition";

   private Win32DiskPartition() {
   }

   public static WbemcliUtil.WmiResult queryPartition(WmiQueryHandler h) {
      WbemcliUtil.WmiQuery<DiskPartitionProperty> partitionQuery = new WbemcliUtil.WmiQuery("Win32_DiskPartition", DiskPartitionProperty.class);
      return h.queryWMI(partitionQuery, false);
   }

   public static enum DiskPartitionProperty {
      INDEX,
      DESCRIPTION,
      DEVICEID,
      DISKINDEX,
      NAME,
      SIZE,
      TYPE;

      // $FF: synthetic method
      private static DiskPartitionProperty[] $values() {
         return new DiskPartitionProperty[]{INDEX, DESCRIPTION, DEVICEID, DISKINDEX, NAME, SIZE, TYPE};
      }
   }
}
