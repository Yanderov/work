package oshi.util.platform.windows;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import java.time.OffsetDateTime;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.util.Constants;
import oshi.util.ParseUtil;

@ThreadSafe
public final class WmiUtil {
   public static final String OHM_NAMESPACE = "ROOT\\OpenHardwareMonitor";
   private static final String CLASS_CAST_MSG = "%s is not a %s type. CIM Type is %d and VT type is %d";

   private WmiUtil() {
   }

   public static String queryToString(WbemcliUtil.WmiQuery query) {
      T[] props = (T[])((Enum[])query.getPropertyEnum().getEnumConstants());
      StringBuilder sb = new StringBuilder("SELECT ");
      sb.append(props[0].name());

      for(int i = 1; i < props.length; ++i) {
         sb.append(',').append(props[i].name());
      }

      sb.append(" FROM ").append(query.getWmiClassName());
      return sb.toString();
   }

   public static String getString(WbemcliUtil.WmiResult result, Enum property, int index) {
      if (result.getCIMType(property) == 8) {
         return getStr(result, property, index);
      } else {
         throw new ClassCastException(String.format("%s is not a %s type. CIM Type is %d and VT type is %d", property.name(), "String", result.getCIMType(property), result.getVtType(property)));
      }
   }

   public static String getDateString(WbemcliUtil.WmiResult result, Enum property, int index) {
      OffsetDateTime dateTime = getDateTime(result, property, index);
      return dateTime.equals(Constants.UNIX_EPOCH) ? "" : dateTime.toLocalDate().toString();
   }

   public static OffsetDateTime getDateTime(WbemcliUtil.WmiResult result, Enum property, int index) {
      if (result.getCIMType(property) == 101) {
         return ParseUtil.parseCimDateTimeToOffset(getStr(result, property, index));
      } else {
         throw new ClassCastException(String.format("%s is not a %s type. CIM Type is %d and VT type is %d", property.name(), "DateTime", result.getCIMType(property), result.getVtType(property)));
      }
   }

   public static String getRefString(WbemcliUtil.WmiResult result, Enum property, int index) {
      if (result.getCIMType(property) == 102) {
         return getStr(result, property, index);
      } else {
         throw new ClassCastException(String.format("%s is not a %s type. CIM Type is %d and VT type is %d", property.name(), "Reference", result.getCIMType(property), result.getVtType(property)));
      }
   }

   private static String getStr(WbemcliUtil.WmiResult result, Enum property, int index) {
      Object o = result.getValue(property, index);
      if (o == null) {
         return "";
      } else if (result.getVtType(property) == 8) {
         return (String)o;
      } else {
         throw new ClassCastException(String.format("%s is not a %s type. CIM Type is %d and VT type is %d", property.name(), "String-mapped", result.getCIMType(property), result.getVtType(property)));
      }
   }

   public static long getUint64(WbemcliUtil.WmiResult result, Enum property, int index) {
      Object o = result.getValue(property, index);
      if (o == null) {
         return 0L;
      } else if (result.getCIMType(property) == 21 && result.getVtType(property) == 8) {
         return ParseUtil.parseLongOrDefault((String)o, 0L);
      } else {
         throw new ClassCastException(String.format("%s is not a %s type. CIM Type is %d and VT type is %d", property.name(), "UINT64", result.getCIMType(property), result.getVtType(property)));
      }
   }

   public static int getUint32(WbemcliUtil.WmiResult result, Enum property, int index) {
      if (result.getCIMType(property) == 19) {
         return getInt(result, property, index);
      } else {
         throw new ClassCastException(String.format("%s is not a %s type. CIM Type is %d and VT type is %d", property.name(), "UINT32", result.getCIMType(property), result.getVtType(property)));
      }
   }

   public static long getUint32asLong(WbemcliUtil.WmiResult result, Enum property, int index) {
      if (result.getCIMType(property) == 19) {
         return (long)getInt(result, property, index) & 4294967295L;
      } else {
         throw new ClassCastException(String.format("%s is not a %s type. CIM Type is %d and VT type is %d", property.name(), "UINT32", result.getCIMType(property), result.getVtType(property)));
      }
   }

   public static int getSint32(WbemcliUtil.WmiResult result, Enum property, int index) {
      if (result.getCIMType(property) == 3) {
         return getInt(result, property, index);
      } else {
         throw new ClassCastException(String.format("%s is not a %s type. CIM Type is %d and VT type is %d", property.name(), "SINT32", result.getCIMType(property), result.getVtType(property)));
      }
   }

   public static int getUint16(WbemcliUtil.WmiResult result, Enum property, int index) {
      if (result.getCIMType(property) == 18) {
         return getInt(result, property, index);
      } else {
         throw new ClassCastException(String.format("%s is not a %s type. CIM Type is %d and VT type is %d", property.name(), "UINT16", result.getCIMType(property), result.getVtType(property)));
      }
   }

   private static int getInt(WbemcliUtil.WmiResult result, Enum property, int index) {
      Object o = result.getValue(property, index);
      if (o == null) {
         return 0;
      } else if (result.getVtType(property) == 3) {
         return (Integer)o;
      } else {
         throw new ClassCastException(String.format("%s is not a %s type. CIM Type is %d and VT type is %d", property.name(), "32-bit integer", result.getCIMType(property), result.getVtType(property)));
      }
   }

   public static float getFloat(WbemcliUtil.WmiResult result, Enum property, int index) {
      Object o = result.getValue(property, index);
      if (o == null) {
         return 0.0F;
      } else if (result.getCIMType(property) == 4 && result.getVtType(property) == 4) {
         return (Float)o;
      } else {
         throw new ClassCastException(String.format("%s is not a %s type. CIM Type is %d and VT type is %d", property.name(), "Float", result.getCIMType(property), result.getVtType(property)));
      }
   }
}
