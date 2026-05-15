package oshi.hardware.common;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import oshi.hardware.LogicalVolumeGroup;

public class AbstractLogicalVolumeGroup implements LogicalVolumeGroup {
   private final String name;
   private final Map lvMap;
   private final Set pvSet;

   protected AbstractLogicalVolumeGroup(String name, Map lvMap, Set pvSet) {
      this.name = name;

      for(Map.Entry entry : lvMap.entrySet()) {
         lvMap.put((String)entry.getKey(), Collections.unmodifiableSet((Set)entry.getValue()));
      }

      this.lvMap = Collections.unmodifiableMap(lvMap);
      this.pvSet = Collections.unmodifiableSet(pvSet);
   }

   public String getName() {
      return this.name;
   }

   public Map getLogicalVolumes() {
      return this.lvMap;
   }

   public Set getPhysicalVolumes() {
      return this.pvSet;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("Logical Volume Group: ");
      sb.append(this.name).append("\n |-- PVs: ");
      sb.append(this.pvSet.toString());

      for(Map.Entry entry : this.lvMap.entrySet()) {
         sb.append("\n |-- LV: ").append((String)entry.getKey());
         Set<String> mappedPVs = (Set)entry.getValue();
         if (!mappedPVs.isEmpty()) {
            sb.append(" --> ").append(mappedPVs);
         }
      }

      return sb.toString();
   }
}
