package oshi.hardware.platform.unix.freebsd;

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
public final class FreeBsdHardwareAbstractionLayer extends AbstractHardwareAbstractionLayer {
   public ComputerSystem createComputerSystem() {
      return new FreeBsdComputerSystem();
   }

   public GlobalMemory createMemory() {
      return new FreeBsdGlobalMemory();
   }

   public CentralProcessor createProcessor() {
      return new FreeBsdCentralProcessor();
   }

   public Sensors createSensors() {
      return new FreeBsdSensors();
   }

   public List getPowerSources() {
      return FreeBsdPowerSource.getPowerSources();
   }

   public List getDiskStores() {
      return FreeBsdHWDiskStore.getDisks();
   }

   public List getDisplays() {
      return UnixDisplay.getDisplays();
   }

   public List getNetworkIFs(boolean includeLocalInterfaces) {
      return BsdNetworkIF.getNetworks(includeLocalInterfaces);
   }

   public List getUsbDevices(boolean tree) {
      return FreeBsdUsbDevice.getUsbDevices(tree);
   }

   public List getSoundCards() {
      return FreeBsdSoundCard.getSoundCards();
   }

   public List getGraphicsCards() {
      return FreeBsdGraphicsCard.getGraphicsCards();
   }
}
