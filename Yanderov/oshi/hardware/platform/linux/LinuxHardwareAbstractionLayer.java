package oshi.hardware.platform.linux;

import java.util.List;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.GlobalMemory;
import oshi.hardware.Sensors;
import oshi.hardware.common.AbstractHardwareAbstractionLayer;
import oshi.hardware.platform.unix.UnixDisplay;

@ThreadSafe
public final class LinuxHardwareAbstractionLayer extends AbstractHardwareAbstractionLayer {
   public ComputerSystem createComputerSystem() {
      return new LinuxComputerSystem();
   }

   public GlobalMemory createMemory() {
      return new LinuxGlobalMemory();
   }

   public CentralProcessor createProcessor() {
      return new LinuxCentralProcessor();
   }

   public Sensors createSensors() {
      return new LinuxSensors();
   }

   public List getPowerSources() {
      return LinuxPowerSource.getPowerSources();
   }

   public List getDiskStores() {
      return LinuxHWDiskStore.getDisks();
   }

   public List getLogicalVolumeGroups() {
      return LinuxLogicalVolumeGroup.getLogicalVolumeGroups();
   }

   public List getDisplays() {
      return UnixDisplay.getDisplays();
   }

   public List getNetworkIFs(boolean includeLocalInterfaces) {
      return LinuxNetworkIF.getNetworks(includeLocalInterfaces);
   }

   public List getUsbDevices(boolean tree) {
      return LinuxUsbDevice.getUsbDevices(tree);
   }

   public List getSoundCards() {
      return LinuxSoundCard.getSoundCards();
   }

   public List getGraphicsCards() {
      return LinuxGraphicsCard.getGraphicsCards();
   }
}
