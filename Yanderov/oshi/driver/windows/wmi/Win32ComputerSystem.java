package oshi.driver.windows.wmi;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import java.util.Objects;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.util.platform.windows.WmiQueryHandler;

@ThreadSafe
public final class Win32ComputerSystem {
   private static final String WIN32_COMPUTER_SYSTEM = "Win32_ComputerSystem";

   private Win32ComputerSystem() {
   }

   public static WbemcliUtil.WmiResult queryComputerSystem() {
      WbemcliUtil.WmiQuery<ComputerSystemProperty> computerSystemQuery = new WbemcliUtil.WmiQuery("Win32_ComputerSystem", ComputerSystemProperty.class);
      return ((WmiQueryHandler)Objects.requireNonNull(WmiQueryHandler.createInstance())).queryWMI(computerSystemQuery);
   }

   public static enum ComputerSystemProperty {
      MANUFACTURER,
      MODEL;

      // $FF: synthetic method
      private static ComputerSystemProperty[] $values() {
         return new ComputerSystemProperty[]{MANUFACTURER, MODEL};
      }
   }
}
