package oshi.hardware.platform.unix.solaris;

import java.util.List;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.GlobalMemory;
import oshi.hardware.Sensors;
import oshi.hardware.common.AbstractHardwareAbstractionLayer;
import oshi.hardware.platform.unix.UnixDisplay;

@ThreadSafe
public final class SolarisHardwareAbstractionLayer extends AbstractHardwareAbstractionLayer {
   public ComputerSystem createComputerSystem() {
      return new SolarisComputerSystem();
   }

   public GlobalMemory createMemory() {
      return new SolarisGlobalMemory();
   }

   public CentralProcessor createProcessor() {
      return new SolarisCentralProcessor();
   }

   public Sensors createSensors() {
      return new SolarisSensors();
   }

   public List getPowerSources() {
      return SolarisPowerSource.getPowerSources();
   }

   public List getDiskStores() {
      return SolarisHWDiskStore.getDisks();
   }

   public List getDisplays() {
      return UnixDisplay.getDisplays();
   }

   public List getNetworkIFs(boolean includeLocalInterfaces) {
      return SolarisNetworkIF.getNetworks(includeLocalInterfaces);
   }

   public List getUsbDevices(boolean tree) {
      return SolarisUsbDevice.getUsbDevices(tree);
   }

   public List getSoundCards() {
      return SolarisSoundCard.getSoundCards();
   }

   public List getGraphicsCards() {
      return SolarisGraphicsCard.getGraphicsCards();
   }
}
