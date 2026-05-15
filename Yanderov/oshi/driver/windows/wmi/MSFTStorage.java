package oshi.driver.windows.wmi;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.util.platform.windows.WmiQueryHandler;

@ThreadSafe
public final class MSFTStorage {
   private static final String STORAGE_NAMESPACE = "ROOT\\Microsoft\\Windows\\Storage";
   private static final String MSFT_STORAGE_POOL_WHERE_IS_PRIMORDIAL_FALSE = "MSFT_StoragePool WHERE IsPrimordial=FALSE";
   private static final String MSFT_STORAGE_POOL_TO_PHYSICAL_DISK = "MSFT_StoragePoolToPhysicalDisk";
   private static final String MSFT_PHYSICAL_DISK = "MSFT_PhysicalDisk";
   private static final String MSFT_VIRTUAL_DISK = "MSFT_VirtualDisk";

   private MSFTStorage() {
   }

   public static WbemcliUtil.WmiResult queryStoragePools(WmiQueryHandler h) {
      WbemcliUtil.WmiQuery<StoragePoolProperty> storagePoolQuery = new WbemcliUtil.WmiQuery("ROOT\\Microsoft\\Windows\\Storage", "MSFT_StoragePool WHERE IsPrimordial=FALSE", StoragePoolProperty.class);
      return h.queryWMI(storagePoolQuery, false);
   }

   public static WbemcliUtil.WmiResult queryStoragePoolPhysicalDisks(WmiQueryHandler h) {
      WbemcliUtil.WmiQuery<StoragePoolToPhysicalDiskProperty> storagePoolToPhysicalDiskQuery = new WbemcliUtil.WmiQuery("ROOT\\Microsoft\\Windows\\Storage", "MSFT_StoragePoolToPhysicalDisk", StoragePoolToPhysicalDiskProperty.class);
      return h.queryWMI(storagePoolToPhysicalDiskQuery, false);
   }

   public static WbemcliUtil.WmiResult queryPhysicalDisks(WmiQueryHandler h) {
      WbemcliUtil.WmiQuery<PhysicalDiskProperty> physicalDiskQuery = new WbemcliUtil.WmiQuery("ROOT\\Microsoft\\Windows\\Storage", "MSFT_PhysicalDisk", PhysicalDiskProperty.class);
      return h.queryWMI(physicalDiskQuery, false);
   }

   public static WbemcliUtil.WmiResult queryVirtualDisks(WmiQueryHandler h) {
      WbemcliUtil.WmiQuery<VirtualDiskProperty> virtualDiskQuery = new WbemcliUtil.WmiQuery("ROOT\\Microsoft\\Windows\\Storage", "MSFT_VirtualDisk", VirtualDiskProperty.class);
      return h.queryWMI(virtualDiskQuery, false);
   }

   public static enum StoragePoolProperty {
      FRIENDLYNAME,
      OBJECTID;

      // $FF: synthetic method
      private static StoragePoolProperty[] $values() {
         return new StoragePoolProperty[]{FRIENDLYNAME, OBJECTID};
      }
   }

   public static enum StoragePoolToPhysicalDiskProperty {
      STORAGEPOOL,
      PHYSICALDISK;

      // $FF: synthetic method
      private static StoragePoolToPhysicalDiskProperty[] $values() {
         return new StoragePoolToPhysicalDiskProperty[]{STORAGEPOOL, PHYSICALDISK};
      }
   }

   public static enum PhysicalDiskProperty {
      FRIENDLYNAME,
      PHYSICALLOCATION,
      OBJECTID;

      // $FF: synthetic method
      private static PhysicalDiskProperty[] $values() {
         return new PhysicalDiskProperty[]{FRIENDLYNAME, PHYSICALLOCATION, OBJECTID};
      }
   }

   public static enum VirtualDiskProperty {
      FRIENDLYNAME,
      OBJECTID;

      // $FF: synthetic method
      private static VirtualDiskProperty[] $values() {
         return new VirtualDiskProperty[]{FRIENDLYNAME, OBJECTID};
      }
   }
}
