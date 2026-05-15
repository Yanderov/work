package oshi.hardware.platform.mac;

import java.util.List;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.GlobalMemory;
import oshi.hardware.Sensors;
import oshi.hardware.common.AbstractHardwareAbstractionLayer;

@ThreadSafe
public final class MacHardwareAbstractionLayer extends AbstractHardwareAbstractionLayer {
   public ComputerSystem createComputerSystem() {
      return new MacComputerSystem();
   }

   public GlobalMemory createMemory() {
      return new MacGlobalMemory();
   }

   public CentralProcessor createProcessor() {
      return new MacCentralProcessor();
   }

   public Sensors createSensors() {
      return new MacSensors();
   }

   public List getPowerSources() {
      return MacPowerSource.getPowerSources();
   }

   public List getDiskStores() {
      return MacHWDiskStore.getDisks();
   }

   public List getLogicalVolumeGroups() {
      return MacLogicalVolumeGroup.getLogicalVolumeGroups();
   }

   public List getDisplays() {
      return MacDisplay.getDisplays();
   }

   public List getNetworkIFs(boolean includeLocalInterfaces) {
      return MacNetworkIF.getNetworks(includeLocalInterfaces);
   }

   public List getUsbDevices(boolean tree) {
      return MacUsbDevice.getUsbDevices(tree);
   }

   public List getSoundCards() {
      return MacSoundCard.getSoundCards();
   }

   public List getGraphicsCards() {
      return MacGraphicsCard.getGraphicsCards();
   }
}
