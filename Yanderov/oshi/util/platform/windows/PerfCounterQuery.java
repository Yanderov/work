package oshi.util.platform.windows;

import com.sun.jna.platform.win32.PdhUtil;
import com.sun.jna.platform.win32.VersionHelpers;
import com.sun.jna.platform.win32.Win32Exception;
import com.sun.jna.platform.win32.COM.WbemcliUtil;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class PerfCounterQuery {
   private static final Logger LOG = LoggerFactory.getLogger(PerfCounterQuery.class);
   private static final boolean IS_VISTA_OR_GREATER = VersionHelpers.IsWindowsVistaOrGreater();
   private static final Set FAILED_QUERY_CACHE = ConcurrentHashMap.newKeySet();
   private static final ConcurrentHashMap LOCALIZE_CACHE = new ConcurrentHashMap();
   public static final String TOTAL_INSTANCE = "_Total";
   public static final String TOTAL_OR_IDLE_INSTANCES = "_Total|Idle";
   public static final String TOTAL_INSTANCES = "*_Total";
   public static final String NOT_TOTAL_INSTANCE = "^_Total";
   public static final String NOT_TOTAL_INSTANCES = "^*_Total";

   private PerfCounterQuery() {
   }

   public static Map queryValues(Class propertyEnum, String perfObject, String perfWmiClass) {
      if (!FAILED_QUERY_CACHE.contains(perfObject)) {
         Map<T, Long> valueMap = queryValuesFromPDH(propertyEnum, perfObject);
         if (!valueMap.isEmpty()) {
            return valueMap;
         }

         LOG.warn("Disabling further attempts to query {}.", perfObject);
         FAILED_QUERY_CACHE.add(perfObject);
      }

      return queryValuesFromWMI(propertyEnum, perfWmiClass);
   }

   public static Map queryValuesFromPDH(Class propertyEnum, String perfObject) {
      T[] props = (T[])((Enum[])propertyEnum.getEnumConstants());
      String perfObjectLocalized = localizeIfNeeded(perfObject, false);
      EnumMap<T, PerfDataUtil.PerfCounter> counterMap = new EnumMap(propertyEnum);
      EnumMap<T, Long> valueMap = new EnumMap(propertyEnum);
      PerfCounterQueryHandler pdhQueryHandler = new PerfCounterQueryHandler();

      EnumMap var12;
      label48: {
         try {
            for(Enum prop : props) {
               PerfDataUtil.PerfCounter counter = PerfDataUtil.createCounter(perfObjectLocalized, ((PdhCounterProperty)prop).getInstance(), ((PdhCounterProperty)prop).getCounter());
               counterMap.put(prop, counter);
               if (!pdhQueryHandler.addCounterToQuery(counter)) {
                  var12 = valueMap;
                  break label48;
               }
            }

            if (0L < pdhQueryHandler.updateQuery()) {
               for(Enum prop : props) {
                  valueMap.put(prop, pdhQueryHandler.queryCounter((PerfDataUtil.PerfCounter)counterMap.get(prop)));
               }
            }
         } catch (Throwable var14) {
            try {
               pdhQueryHandler.close();
            } catch (Throwable var13) {
               var14.addSuppressed(var13);
            }

            throw var14;
         }

         pdhQueryHandler.close();
         return valueMap;
      }

      pdhQueryHandler.close();
      return var12;
   }

   public static Map queryValuesFromWMI(Class propertyEnum, String wmiClass) {
      WbemcliUtil.WmiQuery<T> query = new WbemcliUtil.WmiQuery(wmiClass, propertyEnum);
      WbemcliUtil.WmiResult<T> result = ((WmiQueryHandler)Objects.requireNonNull(WmiQueryHandler.createInstance())).queryWMI(query);
      EnumMap<T, Long> valueMap = new EnumMap(propertyEnum);
      if (result.getResultCount() > 0) {
         for(Enum prop : (Enum[])propertyEnum.getEnumConstants()) {
            switch (result.getCIMType(prop)) {
               case 18:
                  valueMap.put(prop, (long)WmiUtil.getUint16(result, prop, 0));
                  break;
               case 19:
                  valueMap.put(prop, WmiUtil.getUint32asLong(result, prop, 0));
                  break;
               case 21:
                  valueMap.put(prop, WmiUtil.getUint64(result, prop, 0));
                  break;
               case 101:
                  valueMap.put(prop, WmiUtil.getDateTime(result, prop, 0).toInstant().toEpochMilli());
                  break;
               default:
                  throw new ClassCastException("Unimplemented CIM Type Mapping.");
            }
         }
      }

      return valueMap;
   }

   public static String localizeIfNeeded(String perfObject, boolean force) {
      return !force && IS_VISTA_OR_GREATER ? perfObject : (String)LOCALIZE_CACHE.computeIfAbsent(perfObject, PerfCounterQuery::localizeUsingPerfIndex);
   }

   private static String localizeUsingPerfIndex(String perfObject) {
      String localized = perfObject;

      try {
         localized = PdhUtil.PdhLookupPerfNameByIndex((String)null, PdhUtil.PdhLookupPerfIndexByEnglishName(perfObject));
      } catch (Win32Exception e) {
         LOG.warn("Unable to locate English counter names in registry Perflib 009. Assuming English counters. Error {}. {}", String.format("0x%x", e.getHR().intValue()), "See https://support.microsoft.com/en-us/help/300956/how-to-manually-rebuild-performance-counter-library-values");
      } catch (PdhUtil.PdhException e) {
         LOG.warn("Unable to localize {} performance counter.  Error {}.", perfObject, String.format("0x%x", e.getErrorCode()));
      }

      if (localized.isEmpty()) {
         return perfObject;
      } else {
         LOG.debug("Localized {} to {}", perfObject, localized);
         return localized;
      }
   }

   public interface PdhCounterProperty {
      String getInstance();

      String getCounter();
   }
}
