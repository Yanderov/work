package oshi.hardware.platform.mac;

import com.sun.jna.Native;
import com.sun.jna.platform.mac.IOKit;
import com.sun.jna.platform.mac.IOKitUtil;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;
import oshi.annotation.concurrent.Immutable;
import oshi.hardware.common.AbstractBaseboard;
import oshi.util.Memoizer;
import oshi.util.Util;
import oshi.util.tuples.Quartet;

@Immutable
final class MacBaseboard extends AbstractBaseboard {
   private final Supplier manufModelVersSerial = Memoizer.memoize(MacBaseboard::queryPlatform);

   public String getManufacturer() {
      return (String)((Quartet)this.manufModelVersSerial.get()).getA();
   }

   public String getModel() {
      return (String)((Quartet)this.manufModelVersSerial.get()).getB();
   }

   public String getVersion() {
      return (String)((Quartet)this.manufModelVersSerial.get()).getC();
   }

   public String getSerialNumber() {
      return (String)((Quartet)this.manufModelVersSerial.get()).getD();
   }

   private static Quartet queryPlatform() {
      String manufacturer = null;
      String model = null;
      String version = null;
      String serialNumber = null;
      IOKit.IORegistryEntry platformExpert = IOKitUtil.getMatchingService("IOPlatformExpertDevice");
      if (platformExpert != null) {
         byte[] data = platformExpert.getByteArrayProperty("manufacturer");
         if (data != null) {
            manufacturer = Native.toString(data, StandardCharsets.UTF_8);
         }

         data = platformExpert.getByteArrayProperty("board-id");
         if (data != null) {
            model = Native.toString(data, StandardCharsets.UTF_8);
         }

         if (Util.isBlank(model)) {
            data = platformExpert.getByteArrayProperty("model-number");
            if (data != null) {
               model = Native.toString(data, StandardCharsets.UTF_8);
            }
         }

         data = platformExpert.getByteArrayProperty("version");
         if (data != null) {
            version = Native.toString(data, StandardCharsets.UTF_8);
         }

         data = platformExpert.getByteArrayProperty("mlb-serial-number");
         if (data != null) {
            serialNumber = Native.toString(data, StandardCharsets.UTF_8);
         }

         if (Util.isBlank(serialNumber)) {
            serialNumber = platformExpert.getStringProperty("IOPlatformSerialNumber");
         }

         platformExpert.release();
      }

      return new Quartet(Util.isBlank(manufacturer) ? "Apple Inc." : manufacturer, Util.isBlank(model) ? "unknown" : model, Util.isBlank(version) ? "unknown" : version, Util.isBlank(serialNumber) ? "unknown" : serialNumber);
   }
}
