package oshi.util.platform.windows;

import com.sun.jna.platform.win32.PdhUtil;
import com.sun.jna.platform.win32.COM.WbemcliUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.util.Util;
import oshi.util.tuples.Pair;

@ThreadSafe
public final class PerfCounterWildcardQuery {
   private static final Logger LOG = LoggerFactory.getLogger(PerfCounterWildcardQuery.class);
   private static final Set FAILED_QUERY_CACHE = ConcurrentHashMap.newKeySet();

   private PerfCounterWildcardQuery() {
   }

   public static Pair queryInstancesAndValues(Class propertyEnum, String perfObject, String perfWmiClass) {
      if (!FAILED_QUERY_CACHE.contains(perfObject)) {
         Pair<List<String>, Map<T, List<Long>>> instancesAndValuesMap = queryInstancesAndValuesFromPDH(propertyEnum, perfObject);
         if (!((List)instancesAndValuesMap.getA()).isEmpty()) {
            return instancesAndValuesMap;
         }

         LOG.warn("Disabling further attempts to query {}.", perfObject);
         FAILED_QUERY_CACHE.add(perfObject);
      }

      return queryInstancesAndValuesFromWMI(propertyEnum, perfWmiClass);
   }

   public static Pair queryInstancesAndValuesFromPDH(Class propertyEnum, String perfObject) {
      T[] props = (T[])((Enum[])propertyEnum.getEnumConstants());
      if (props.length < 2) {
         throw new IllegalArgumentException("Enum " + propertyEnum.getName() + " must have at least two elements, an instance filter and a counter.");
      } else {
         String instanceFilter = ((PdhCounterWildcardProperty)((Enum[])propertyEnum.getEnumConstants())[0]).getCounter().toLowerCase();
         String perfObjectLocalized = PerfCounterQuery.localizeIfNeeded(perfObject, true);
         PdhUtil.PdhEnumObjectItems objectItems = null;

         try {
            objectItems = PdhUtil.PdhEnumObjectItems((String)null, (String)null, perfObjectLocalized, 100);
         } catch (PdhUtil.PdhException e) {
            LOG.warn("Failed to locate performance object for {} in the registry. Performance counters may be corrupt. {}", perfObjectLocalized, e.getMessage());
         }

         if (objectItems == null) {
            return new Pair(Collections.emptyList(), Collections.emptyMap());
         } else {
            List<String> instances = objectItems.getInstances();
            instances.removeIf((ix) -> !Util.wildcardMatch(ix.toLowerCase(), instanceFilter));
            EnumMap<T, List<Long>> valuesMap = new EnumMap(propertyEnum);
            PerfCounterQueryHandler pdhQueryHandler = new PerfCounterQueryHandler();

            Pair var16;
            label78: {
               try {
                  EnumMap<T, List<PerfDataUtil.PerfCounter>> counterListMap = new EnumMap(propertyEnum);

                  for(int i = 1; i < props.length; ++i) {
                     T prop = (T)props[i];
                     List<PerfDataUtil.PerfCounter> counterList = new ArrayList(instances.size());

                     for(String instance : instances) {
                        PerfDataUtil.PerfCounter counter = PerfDataUtil.createCounter(perfObject, instance, ((PdhCounterWildcardProperty)prop).getCounter());
                        if (!pdhQueryHandler.addCounterToQuery(counter)) {
                           var16 = new Pair(Collections.emptyList(), Collections.emptyMap());
                           break label78;
                        }

                        counterList.add(counter);
                     }

                     counterListMap.put(prop, counterList);
                  }

                  if (0L < pdhQueryHandler.updateQuery()) {
                     for(int i = 1; i < props.length; ++i) {
                        T prop = (T)props[i];
                        List<Long> values = new ArrayList();

                        for(PerfDataUtil.PerfCounter counter : (List)counterListMap.get(prop)) {
                           values.add(pdhQueryHandler.queryCounter(counter));
                        }

                        valuesMap.put(prop, values);
                     }
                  }
               } catch (Throwable var19) {
                  try {
                     pdhQueryHandler.close();
                  } catch (Throwable var17) {
                     var19.addSuppressed(var17);
                  }

                  throw var19;
               }

               pdhQueryHandler.close();
               return new Pair(instances, valuesMap);
            }

            pdhQueryHandler.close();
            return var16;
         }
      }
   }

   public static Pair queryInstancesAndValuesFromWMI(Class propertyEnum, String wmiClass) {
      List<String> instances = new ArrayList();
      EnumMap<T, List<Long>> valuesMap = new EnumMap(propertyEnum);
      WbemcliUtil.WmiQuery<T> query = new WbemcliUtil.WmiQuery(wmiClass, propertyEnum);
      WbemcliUtil.WmiResult<T> result = ((WmiQueryHandler)Objects.requireNonNull(WmiQueryHandler.createInstance())).queryWMI(query);
      if (result.getResultCount() > 0) {
         for(Enum prop : (Enum[])propertyEnum.getEnumConstants()) {
            if (prop.ordinal() == 0) {
               for(int i = 0; i < result.getResultCount(); ++i) {
                  instances.add(WmiUtil.getString(result, prop, i));
               }
            } else {
               List<Long> values = new ArrayList();

               for(int i = 0; i < result.getResultCount(); ++i) {
                  switch (result.getCIMType(prop)) {
                     case 18:
                        values.add((long)WmiUtil.getUint16(result, prop, i));
                        break;
                     case 19:
                        values.add(WmiUtil.getUint32asLong(result, prop, i));
                        break;
                     case 21:
                        values.add(WmiUtil.getUint64(result, prop, i));
                        break;
                     case 101:
                        values.add(WmiUtil.getDateTime(result, prop, i).toInstant().toEpochMilli());
                        break;
                     default:
                        throw new ClassCastException("Unimplemented CIM Type Mapping.");
                  }
               }

               valuesMap.put(prop, values);
            }
         }
      }

      return new Pair(instances, valuesMap);
   }

   public interface PdhCounterWildcardProperty {
      String getCounter();
   }
}
