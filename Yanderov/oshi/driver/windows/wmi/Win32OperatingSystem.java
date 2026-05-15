package oshi.driver.windows.wmi;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import java.util.Objects;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.util.platform.windows.WmiQueryHandler;

@ThreadSafe
public final class Win32OperatingSystem {
   private static final String WIN32_OPERATING_SYSTEM = "Win32_OperatingSystem";

   private Win32OperatingSystem() {
   }

   public static WbemcliUtil.WmiResult queryOsVersion() {
      WbemcliUtil.WmiQuery<OSVersionProperty> osVersionQuery = new WbemcliUtil.WmiQuery("Win32_OperatingSystem", OSVersionProperty.class);
      return ((WmiQueryHandler)Objects.requireNonNull(WmiQueryHandler.createInstance())).queryWMI(osVersionQuery);
   }

   public static enum OSVersionProperty {
      VERSION,
      PRODUCTTYPE,
      BUILDNUMBER,
      CSDVERSION,
      SUITEMASK;

      // $FF: synthetic method
      private static OSVersionProperty[] $values() {
         return new OSVersionProperty[]{VERSION, PRODUCTTYPE, BUILDNUMBER, CSDVERSION, SUITEMASK};
      }
   }
}
