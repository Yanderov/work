package oshi.hardware.platform.windows;

import com.sun.jna.platform.win32.VersionHelpers;
import com.sun.jna.platform.win32.COM.COMException;
import com.sun.jna.platform.win32.COM.WbemcliUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.driver.windows.wmi.MSFTStorage;
import oshi.hardware.LogicalVolumeGroup;
import oshi.hardware.common.AbstractLogicalVolumeGroup;
import oshi.util.ParseUtil;
import oshi.util.platform.windows.WmiQueryHandler;
import oshi.util.platform.windows.WmiUtil;
import oshi.util.tuples.Pair;

final class WindowsLogicalVolumeGroup extends AbstractLogicalVolumeGroup {
   private static final Logger LOG = LoggerFactory.getLogger(WindowsLogicalVolumeGroup.class);
   private static final Pattern SP_OBJECT_ID = Pattern.compile(".*ObjectId=.*SP:(\\{.*\\}).*");
   private static final Pattern PD_OBJECT_ID = Pattern.compile(".*ObjectId=.*PD:(\\{.*\\}).*");
   private static final Pattern VD_OBJECT_ID = Pattern.compile(".*ObjectId=.*VD:(\\{.*\\})(\\{.*\\}).*");
   private static final boolean IS_WINDOWS8_OR_GREATER = VersionHelpers.IsWindows8OrGreater();

   WindowsLogicalVolumeGroup(String name, Map lvMap, Set pvSet) {
      super(name, lvMap, pvSet);
   }

   static List getLogicalVolumeGroups() {
      if (!IS_WINDOWS8_OR_GREATER) {
         return Collections.emptyList();
      } else {
         WmiQueryHandler h = (WmiQueryHandler)Objects.requireNonNull(WmiQueryHandler.createInstance());
         boolean comInit = false;

         List var4;
         try {
            comInit = h.initCOM();
            WbemcliUtil.WmiResult<MSFTStorage.StoragePoolProperty> sp = MSFTStorage.queryStoragePools(h);
            int count = sp.getResultCount();
            if (count != 0) {
               Map<String, String> vdMap = new HashMap();
               WbemcliUtil.WmiResult<MSFTStorage.VirtualDiskProperty> vds = MSFTStorage.queryVirtualDisks(h);
               count = vds.getResultCount();

               for(int i = 0; i < count; ++i) {
                  String vdObjectId = WmiUtil.getString(vds, MSFTStorage.VirtualDiskProperty.OBJECTID, i);
                  Matcher m = VD_OBJECT_ID.matcher(vdObjectId);
                  if (m.matches()) {
                     vdObjectId = m.group(2) + " " + m.group(1);
                  }

                  vdMap.put(vdObjectId, WmiUtil.getString(vds, MSFTStorage.VirtualDiskProperty.FRIENDLYNAME, i));
               }

               Map<String, Pair<String, String>> pdMap = new HashMap();
               WbemcliUtil.WmiResult<MSFTStorage.PhysicalDiskProperty> pds = MSFTStorage.queryPhysicalDisks(h);
               count = pds.getResultCount();

               for(int i = 0; i < count; ++i) {
                  String pdObjectId = WmiUtil.getString(pds, MSFTStorage.PhysicalDiskProperty.OBJECTID, i);
                  Matcher m = PD_OBJECT_ID.matcher(pdObjectId);
                  if (m.matches()) {
                     pdObjectId = m.group(1);
                  }

                  pdMap.put(pdObjectId, new Pair(WmiUtil.getString(pds, MSFTStorage.PhysicalDiskProperty.FRIENDLYNAME, i), WmiUtil.getString(pds, MSFTStorage.PhysicalDiskProperty.PHYSICALLOCATION, i)));
               }

               Map<String, String> sppdMap = new HashMap();
               WbemcliUtil.WmiResult<MSFTStorage.StoragePoolToPhysicalDiskProperty> sppd = MSFTStorage.queryStoragePoolPhysicalDisks(h);
               count = sppd.getResultCount();

               for(int i = 0; i < count; ++i) {
                  String spObjectId = WmiUtil.getRefString(sppd, MSFTStorage.StoragePoolToPhysicalDiskProperty.STORAGEPOOL, i);
                  Matcher m = SP_OBJECT_ID.matcher(spObjectId);
                  if (m.matches()) {
                     spObjectId = m.group(1);
                  }

                  String pdObjectId = WmiUtil.getRefString(sppd, MSFTStorage.StoragePoolToPhysicalDiskProperty.PHYSICALDISK, i);
                  m = PD_OBJECT_ID.matcher(pdObjectId);
                  if (m.matches()) {
                     pdObjectId = m.group(1);
                  }

                  sppdMap.put(spObjectId + " " + pdObjectId, pdObjectId);
               }

               List<LogicalVolumeGroup> lvgList = new ArrayList();
               count = sp.getResultCount();

               for(int i = 0; i < count; ++i) {
                  String name = WmiUtil.getString(sp, MSFTStorage.StoragePoolProperty.FRIENDLYNAME, i);
                  String spObjectId = WmiUtil.getString(sp, MSFTStorage.StoragePoolProperty.OBJECTID, i);
                  Matcher m = SP_OBJECT_ID.matcher(spObjectId);
                  if (m.matches()) {
                     spObjectId = m.group(1);
                  }

                  Set<String> physicalVolumeSet = new HashSet();

                  for(Map.Entry entry : sppdMap.entrySet()) {
                     if (((String)entry.getKey()).contains(spObjectId)) {
                        String pdObjectId = (String)entry.getValue();
                        Pair<String, String> nameLoc = (Pair)pdMap.get(pdObjectId);
                        if (nameLoc != null) {
                           physicalVolumeSet.add((String)nameLoc.getA() + " @ " + (String)nameLoc.getB());
                        }
                     }
                  }

                  Map<String, Set<String>> logicalVolumeMap = new HashMap();

                  for(Map.Entry entry : vdMap.entrySet()) {
                     if (((String)entry.getKey()).contains(spObjectId)) {
                        String vdObjectId = ParseUtil.whitespaces.split((CharSequence)entry.getKey())[0];
                        logicalVolumeMap.put((String)entry.getValue() + " " + vdObjectId, physicalVolumeSet);
                     }
                  }

                  lvgList.add(new WindowsLogicalVolumeGroup(name, logicalVolumeMap, physicalVolumeSet));
               }

               Object var39 = lvgList;
               return (List)var39;
            }

            var4 = Collections.emptyList();
         } catch (COMException e) {
            LOG.warn("COM exception: {}", e.getMessage());
            List count = Collections.emptyList();
            return count;
         } finally {
            if (comInit) {
               h.unInitCOM();
            }

         }

         return var4;
      }
   }
}
