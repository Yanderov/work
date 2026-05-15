package oshi.hardware.common;

import java.util.function.Supplier;
import oshi.annotation.concurrent.Immutable;
import oshi.hardware.Baseboard;
import oshi.hardware.ComputerSystem;
import oshi.hardware.Firmware;
import oshi.util.Memoizer;

@Immutable
public abstract class AbstractComputerSystem implements ComputerSystem {
   private final Supplier firmware = Memoizer.memoize(this::createFirmware);
   private final Supplier baseboard = Memoizer.memoize(this::createBaseboard);

   public Firmware getFirmware() {
      return (Firmware)this.firmware.get();
   }

   protected abstract Firmware createFirmware();

   public Baseboard getBaseboard() {
      return (Baseboard)this.baseboard.get();
   }

   protected abstract Baseboard createBaseboard();

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("manufacturer=").append(this.getManufacturer()).append(", ");
      sb.append("model=").append(this.getModel()).append(", ");
      sb.append("serial number=").append(this.getSerialNumber()).append(", ");
      sb.append("uuid=").append(this.getHardwareUUID());
      return sb.toString();
   }
}
