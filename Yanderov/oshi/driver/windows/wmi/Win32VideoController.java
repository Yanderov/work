package oshi.driver.windows.wmi;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import java.util.Objects;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.util.platform.windows.WmiQueryHandler;

@ThreadSafe
public final class Win32VideoController {
   private static final String WIN32_VIDEO_CONTROLLER = "Win32_VideoController";

   private Win32VideoController() {
   }

   public static WbemcliUtil.WmiResult queryVideoController() {
      WbemcliUtil.WmiQuery<VideoControllerProperty> videoControllerQuery = new WbemcliUtil.WmiQuery("Win32_VideoController", VideoControllerProperty.class);
      return ((WmiQueryHandler)Objects.requireNonNull(WmiQueryHandler.createInstance())).queryWMI(videoControllerQuery);
   }

   public static enum VideoControllerProperty {
      ADAPTERCOMPATIBILITY,
      ADAPTERRAM,
      DRIVERVERSION,
      NAME,
      PNPDEVICEID;

      // $FF: synthetic method
      private static VideoControllerProperty[] $values() {
         return new VideoControllerProperty[]{ADAPTERCOMPATIBILITY, ADAPTERRAM, DRIVERVERSION, NAME, PNPDEVICEID};
      }
   }
}
