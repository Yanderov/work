package oshi.hardware.platform.unix.aix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import oshi.annotation.concurrent.Immutable;
import oshi.hardware.UsbDevice;
import oshi.hardware.common.AbstractUsbDevice;
import oshi.util.ParseUtil;

@Immutable
public class AixUsbDevice extends AbstractUsbDevice {
   public AixUsbDevice(String name, String vendor, String vendorId, String productId, String serialNumber, String uniqueDeviceId, List connectedDevices) {
      super(name, vendor, vendorId, productId, serialNumber, uniqueDeviceId, connectedDevices);
   }

   public static List getUsbDevices(boolean tree, Supplier lscfg) {
      List<UsbDevice> deviceList = new ArrayList();

      for(String line : (List)lscfg.get()) {
         String s = line.trim();
         if (s.startsWith("usb")) {
            String[] split = ParseUtil.whitespaces.split(s, 3);
            if (split.length == 3) {
               deviceList.add(new AixUsbDevice(split[2], "unknown", "unknown", "unknown", "unknown", split[0], Collections.emptyList()));
            }
         }
      }

      if (tree) {
         return Arrays.asList(new AixUsbDevice("USB Controller", "", "0000", "0000", "", "", deviceList));
      } else {
         return deviceList;
      }
   }
}
