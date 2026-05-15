package oshi.hardware.platform.windows;

import java.util.List;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.GlobalMemory;
import oshi.hardware.Sensors;
import oshi.hardware.common.AbstractHardwareAbstractionLayer;

@ThreadSafe
public final class WindowsHardwareAbstractionLayer extends AbstractHardwareAbstractionLayer {
   public ComputerSystem createComputerSystem() {
      return new WindowsComputerSystem();
   }

   public GlobalMemory createMemory() {
      return new WindowsGlobalMemory();
   }

   public CentralProcessor createProcessor() {
      return new WindowsCentralProcessor();
   }

   public Sensors createSensors() {
      return new WindowsSensors();
   }

   public List getPowerSources() {
      return WindowsPowerSource.getPowerSources();
   }

   public List getDiskStores() {
      return WindowsHWDiskStore.getDisks();
   }

   public List getLogicalVolumeGroups() {
      return WindowsLogicalVolumeGroup.getLogicalVolumeGroups();
   }

   public List getDisplays() {
      return WindowsDisplay.getDisplays();
   }

   public List getNetworkIFs(boolean includeLocalInterfaces) {
      return WindowsNetworkIF.getNetworks(includeLocalInterfaces);
   }

   public List getUsbDevices(boolean tree) {
      return WindowsUsbDevice.getUsbDevices(tree);
   }

   public List getSoundCards() {
      return WindowsSoundCard.getSoundCards();
   }

   public List getGraphicsCards() {
      return WindowsGraphicsCard.getGraphicsCards();
   }
}
