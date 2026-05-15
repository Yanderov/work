package oshi.driver.unix.aix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.hardware.HWPartition;
import oshi.util.ExecutingCommand;
import oshi.util.ParseUtil;
import oshi.util.tuples.Pair;

@ThreadSafe
public final class Lspv {
   private static final Map PARTITION_CACHE = new ConcurrentHashMap();

   private Lspv() {
   }

   public static List queryLogicalVolumes(String device, Map majMinMap) {
      return (List)PARTITION_CACHE.computeIfAbsent(device, (d) -> Collections.unmodifiableList((List)computeLogicalVolumes(d, majMinMap).stream().sorted(Comparator.comparing(HWPartition::getMinor).thenComparing(HWPartition::getName)).collect(Collectors.toList())));
   }

   private static List computeLogicalVolumes(String device, Map majMinMap) {
      List<HWPartition> partitions = new ArrayList();
      String stateMarker = "PV STATE:";
      String sizeMarker = "PP SIZE:";
      long ppSize = 0L;

      for(String s : ExecutingCommand.runNative("lspv -L " + device)) {
         if (s.startsWith(stateMarker)) {
            if (!s.contains("active")) {
               return partitions;
            }
         } else if (s.contains(sizeMarker)) {
            ppSize = (long)ParseUtil.getFirstIntValue(s);
         }
      }

      if (ppSize == 0L) {
         return partitions;
      } else {
         ppSize <<= 20;
         Map<String, String> mountMap = new HashMap();
         Map<String, String> typeMap = new HashMap();
         Map<String, Integer> ppMap = new HashMap();

         for(String s : ExecutingCommand.runNative("lspv -p " + device)) {
            String[] split = ParseUtil.whitespaces.split(s.trim());
            if (split.length >= 6 && "used".equals(split[1])) {
               String name = split[split.length - 3];
               mountMap.put(name, split[split.length - 1]);
               typeMap.put(name, split[split.length - 2]);
               int ppCount = 1 + ParseUtil.getNthIntValue(split[0], 2) - ParseUtil.getNthIntValue(split[0], 1);
               ppMap.put(name, ppCount + (Integer)ppMap.getOrDefault(name, 0));
            }
         }

         for(Map.Entry entry : mountMap.entrySet()) {
            String mount = "N/A".equals(entry.getValue()) ? "" : (String)entry.getValue();
            String name = (String)entry.getKey();
            String type = (String)typeMap.get(name);
            long size = ppSize * (long)(Integer)ppMap.get(name);
            Pair<Integer, Integer> majMin = (Pair)majMinMap.get(name);
            int major = majMin == null ? ParseUtil.getFirstIntValue(name) : (Integer)majMin.getA();
            int minor = majMin == null ? ParseUtil.getFirstIntValue(name) : (Integer)majMin.getB();
            partitions.add(new HWPartition(name, name, type, "", size, major, minor, mount));
         }

         return partitions;
      }
   }
}
