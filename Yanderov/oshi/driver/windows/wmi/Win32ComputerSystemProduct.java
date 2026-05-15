package oshi.driver.windows.wmi;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import java.util.Objects;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.util.platform.windows.WmiQueryHandler;

@ThreadSafe
public final class Win32ComputerSystemProduct {
   private static final String WIN32_COMPUTER_SYSTEM_PRODUCT = "Win32_ComputerSystemProduct";

   private Win32ComputerSystemProduct() {
   }

   public static WbemcliUtil.WmiResult queryIdentifyingNumberUUID() {
      WbemcliUtil.WmiQuery<ComputerSystemProductProperty> identifyingNumberQuery = new WbemcliUtil.WmiQuery("Win32_ComputerSystemProduct", ComputerSystemProductProperty.class);
      return ((WmiQueryHandler)Objects.requireNonNull(WmiQueryHandler.createInstance())).queryWMI(identifyingNumberQuery);
   }

   public static enum ComputerSystemProductProperty {
      IDENTIFYINGNUMBER,
      UUID;

      // $FF: synthetic method
      private static ComputerSystemProductProperty[] $values() {
         return new ComputerSystemProductProperty[]{IDENTIFYINGNUMBER, UUID};
      }
   }
}
