package oshi.hardware.platform.mac;

import com.sun.jna.Native;
import com.sun.jna.platform.mac.IOKit;
import com.sun.jna.platform.mac.IOKitUtil;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;
import oshi.annotation.concurrent.Immutable;
import oshi.hardware.common.AbstractFirmware;
import oshi.util.Memoizer;
import oshi.util.Util;
import oshi.util.tuples.Quintet;

@Immutable
final class MacFirmware extends AbstractFirmware {
   private final Supplier manufNameDescVersRelease = Memoizer.memoize(MacFirmware::queryEfi);

   public String getManufacturer() {
      return (String)((Quintet)this.manufNameDescVersRelease.get()).getA();
   }

   public String getName() {
      return (String)((Quintet)this.manufNameDescVersRelease.get()).getB();
   }

   public String getDescription() {
      return (String)((Quintet)this.manufNameDescVersRelease.get()).getC();
   }

   public String getVersion() {
      return (String)((Quintet)this.manufNameDescVersRelease.get()).getD();
   }

   public String getReleaseDate() {
      return (String)((Quintet)this.manufNameDescVersRelease.get()).getE();
   }

   private static Quintet queryEfi() {
      String manufacturer = null;
      String name = null;
      String description = null;
      String version = null;
      String releaseDate = null;
      IOKit.IORegistryEntry platformExpert = IOKitUtil.getMatchingService("IOPlatformExpertDevice");
      if (platformExpert != null) {
         IOKit.IOIterator iter = platformExpert.getChildIterator("IODeviceTree");
         if (iter != null) {
            for(IOKit.IORegistryEntry entry = iter.next(); entry != null; entry = iter.next()) {
               switch (entry.getName()) {
                  case "rom":
                     byte[] data = entry.getByteArrayProperty("vendor");
                     if (data != null) {
                        manufacturer = Native.toString(data, StandardCharsets.UTF_8);
                     }

                     data = entry.getByteArrayProperty("version");
                     if (data != null) {
                        version = Native.toString(data, StandardCharsets.UTF_8);
                     }

                     data = entry.getByteArrayProperty("release-date");
                     if (data != null) {
                        releaseDate = Native.toString(data, StandardCharsets.UTF_8);
                     }
                     break;
                  case "chosen":
                     byte[] data = entry.getByteArrayProperty("booter-name");
                     if (data != null) {
                        name = Native.toString(data, StandardCharsets.UTF_8);
                     }
                     break;
                  case "efi":
                     byte[] data = entry.getByteArrayProperty("firmware-abi");
                     if (data != null) {
                        description = Native.toString(data, StandardCharsets.UTF_8);
                     }
                     break;
                  default:
                     if (Util.isBlank(name)) {
                        name = entry.getStringProperty("IONameMatch");
                     }
               }

               entry.release();
            }

            iter.release();
         }

         if (Util.isBlank(manufacturer)) {
            byte[] data = platformExpert.getByteArrayProperty("manufacturer");
            if (data != null) {
               manufacturer = Native.toString(data, StandardCharsets.UTF_8);
            }
         }

         if (Util.isBlank(version)) {
            byte[] data = platformExpert.getByteArrayProperty("target-type");
            if (data != null) {
               version = Native.toString(data, StandardCharsets.UTF_8);
            }
         }

         if (Util.isBlank(name)) {
            byte[] data = platformExpert.getByteArrayProperty("device_type");
            if (data != null) {
               name = Native.toString(data, StandardCharsets.UTF_8);
            }
         }

         platformExpert.release();
      }

      return new Quintet(Util.isBlank(manufacturer) ? "unknown" : manufacturer, Util.isBlank(name) ? "unknown" : name, Util.isBlank(description) ? "unknown" : description, Util.isBlank(version) ? "unknown" : version, Util.isBlank(releaseDate) ? "unknown" : releaseDate);
   }
}
