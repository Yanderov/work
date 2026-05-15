package oshi.driver.windows.wmi;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import java.util.Objects;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.util.platform.windows.WmiQueryHandler;

@ThreadSafe
public final class Win32Bios {
   private static final String WIN32_BIOS_WHERE_PRIMARY_BIOS_TRUE = "Win32_BIOS where PrimaryBIOS=true";

   private Win32Bios() {
   }

   public static WbemcliUtil.WmiResult querySerialNumber() {
      WbemcliUtil.WmiQuery<BiosSerialProperty> serialNumQuery = new WbemcliUtil.WmiQuery("Win32_BIOS where PrimaryBIOS=true", BiosSerialProperty.class);
      return ((WmiQueryHandler)Objects.requireNonNull(WmiQueryHandler.createInstance())).queryWMI(serialNumQuery);
   }

   public static WbemcliUtil.WmiResult queryBiosInfo() {
      WbemcliUtil.WmiQuery<BiosProperty> biosQuery = new WbemcliUtil.WmiQuery("Win32_BIOS where PrimaryBIOS=true", BiosProperty.class);
      return ((WmiQueryHandler)Objects.requireNonNull(WmiQueryHandler.createInstance())).queryWMI(biosQuery);
   }

   public static enum BiosSerialProperty {
      SERIALNUMBER;

      // $FF: synthetic method
      private static BiosSerialProperty[] $values() {
         return new BiosSerialProperty[]{SERIALNUMBER};
      }
   }

   public static enum BiosProperty {
      MANUFACTURER,
      NAME,
      DESCRIPTION,
      VERSION,
      RELEASEDATE;

      // $FF: synthetic method
      private static BiosProperty[] $values() {
         return new BiosProperty[]{MANUFACTURER, NAME, DESCRIPTION, VERSION, RELEASEDATE};
      }
   }
}
