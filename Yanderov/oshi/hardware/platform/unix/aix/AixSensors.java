package oshi.hardware.platform.unix.aix;

import java.util.List;
import java.util.function.Supplier;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.hardware.common.AbstractSensors;

@ThreadSafe
final class AixSensors extends AbstractSensors {
   private final Supplier lscfg;

   AixSensors(Supplier lscfg) {
      this.lscfg = lscfg;
   }

   public double queryCpuTemperature() {
      return (double)0.0F;
   }

   public int[] queryFanSpeeds() {
      int fans = 0;

      for(String s : (List)this.lscfg.get()) {
         if (s.contains("Air Mover")) {
            ++fans;
         }
      }

      return new int[fans];
   }

   public double queryCpuVoltage() {
      return (double)0.0F;
   }
}
