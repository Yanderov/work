package oshi.hardware.platform.unix.aix;

import java.util.List;
import java.util.function.Supplier;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.driver.unix.aix.Lscfg;
import oshi.driver.unix.aix.perfstat.PerfstatDisk;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.GlobalMemory;
import oshi.hardware.Sensors;
import oshi.hardware.common.AbstractHardwareAbstractionLayer;
import oshi.hardware.platform.unix.UnixDisplay;
import oshi.util.Memoizer;

@ThreadSafe
public final class AixHardwareAbstractionLayer extends AbstractHardwareAbstractionLayer {
   private final Supplier lscfg = Memoizer.memoize(Lscfg::queryAllDevices, Memoizer.defaultExpiration());
   private final Supplier diskStats = Memoizer.memoize(PerfstatDisk::queryDiskStats, Memoizer.defaultExpiration());

   public ComputerSystem createComputerSystem() {
      return new AixComputerSystem(this.lscfg);
   }

   public GlobalMemory createMemory() {
      return new AixGlobalMemory(this.lscfg);
   }

   public CentralProcessor createProcessor() {
      return new AixCentralProcessor();
   }

   public Sensors createSensors() {
      return new AixSensors(this.lscfg);
   }

   public List getPowerSources() {
      return AixPowerSource.getPowerSources();
   }

   public List getDiskStores() {
      return AixHWDiskStore.getDisks(this.diskStats);
   }

   public List getDisplays() {
      return UnixDisplay.getDisplays();
   }

   public List getNetworkIFs(boolean includeLocalInterfaces) {
      return AixNetworkIF.getNetworks(includeLocalInterfaces);
   }

   public List getUsbDevices(boolean tree) {
      return AixUsbDevice.getUsbDevices(tree, this.lscfg);
   }

   public List getSoundCards() {
      return AixSoundCard.getSoundCards(this.lscfg);
   }

   public List getGraphicsCards() {
      return AixGraphicsCard.getGraphicsCards(this.lscfg);
   }
}
