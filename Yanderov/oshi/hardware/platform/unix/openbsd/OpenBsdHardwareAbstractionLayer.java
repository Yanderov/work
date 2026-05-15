package oshi.hardware.platform.unix.openbsd;

import java.util.List;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.GlobalMemory;
import oshi.hardware.Sensors;
import oshi.hardware.common.AbstractHardwareAbstractionLayer;
import oshi.hardware.platform.unix.BsdNetworkIF;
import oshi.hardware.platform.unix.UnixDisplay;

@ThreadSafe
public final class OpenBsdHardwareAbstractionLayer extends AbstractHardwareAbstractionLayer {
   public ComputerSystem createComputerSystem() {
      return new OpenBsdComputerSystem();
   }

   public GlobalMemory createMemory() {
      return new OpenBsdGlobalMemory();
   }

   public CentralProcessor createProcessor() {
      return new OpenBsdCentralProcessor();
   }

   public Sensors createSensors() {
      return new OpenBsdSensors();
   }

   public List getPowerSources() {
      return OpenBsdPowerSource.getPowerSources();
   }

   public List getDiskStores() {
      return OpenBsdHWDiskStore.getDisks();
   }

   public List getDisplays() {
      return UnixDisplay.getDisplays();
   }

   public List getNetworkIFs(boolean includeLocalInterfaces) {
      return BsdNetworkIF.getNetworks(includeLocalInterfaces);
   }

   public List getUsbDevices(boolean tree) {
      return OpenBsdUsbDevice.getUsbDevices(tree);
   }

   public List getSoundCards() {
      return OpenBsdSoundCard.getSoundCards();
   }

   public List getGraphicsCards() {
      return OpenBsdGraphicsCard.getGraphicsCards();
   }
}
