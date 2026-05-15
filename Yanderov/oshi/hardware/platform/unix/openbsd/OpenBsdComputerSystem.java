package oshi.hardware.platform.unix.openbsd;

import java.util.function.Supplier;
import oshi.annotation.concurrent.Immutable;
import oshi.hardware.Baseboard;
import oshi.hardware.Firmware;
import oshi.hardware.common.AbstractComputerSystem;
import oshi.hardware.platform.unix.UnixBaseboard;
import oshi.util.Memoizer;
import oshi.util.platform.unix.openbsd.OpenBsdSysctlUtil;

@Immutable
public class OpenBsdComputerSystem extends AbstractComputerSystem {
   private final Supplier manufacturer = Memoizer.memoize(OpenBsdComputerSystem::queryManufacturer);
   private final Supplier model = Memoizer.memoize(OpenBsdComputerSystem::queryModel);
   private final Supplier serialNumber = Memoizer.memoize(OpenBsdComputerSystem::querySerialNumber);
   private final Supplier uuid = Memoizer.memoize(OpenBsdComputerSystem::queryUUID);

   public String getManufacturer() {
      return (String)this.manufacturer.get();
   }

   public String getModel() {
      return (String)this.model.get();
   }

   public String getSerialNumber() {
      return (String)this.serialNumber.get();
   }

   public String getHardwareUUID() {
      return (String)this.uuid.get();
   }

   protected Firmware createFirmware() {
      return new OpenBsdFirmware();
   }

   protected Baseboard createBaseboard() {
      return new UnixBaseboard((String)this.manufacturer.get(), (String)this.model.get(), (String)this.serialNumber.get(), OpenBsdSysctlUtil.sysctl("hw.product", "unknown"));
   }

   private static String queryManufacturer() {
      return OpenBsdSysctlUtil.sysctl("hw.vendor", "unknown");
   }

   private static String queryModel() {
      return OpenBsdSysctlUtil.sysctl("hw.version", "unknown");
   }

   private static String querySerialNumber() {
      return OpenBsdSysctlUtil.sysctl("hw.serialno", "unknown");
   }

   private static String queryUUID() {
      return OpenBsdSysctlUtil.sysctl("hw.uuid", "unknown");
   }
}
