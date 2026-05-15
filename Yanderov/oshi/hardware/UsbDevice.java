package oshi.hardware;

import java.util.List;
import oshi.annotation.concurrent.Immutable;

@Immutable
public interface UsbDevice extends Comparable {
   String getName();

   String getVendor();

   String getVendorId();

   String getProductId();

   String getSerialNumber();

   String getUniqueDeviceId();

   List getConnectedDevices();
}
