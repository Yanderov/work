package oshi.hardware;

import java.util.Collections;
import java.util.List;
import oshi.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface HardwareAbstractionLayer {
   ComputerSystem getComputerSystem();

   CentralProcessor getProcessor();

   GlobalMemory getMemory();

   List getPowerSources();

   List getDiskStores();

   default List getLogicalVolumeGroups() {
      return Collections.emptyList();
   }

   List getNetworkIFs();

   List getNetworkIFs(boolean var1);

   List getDisplays();

   Sensors getSensors();

   List getUsbDevices(boolean var1);

   List getSoundCards();

   List getGraphicsCards();
}
