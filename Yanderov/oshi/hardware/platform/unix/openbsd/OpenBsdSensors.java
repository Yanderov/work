package oshi.hardware.platform.unix.openbsd;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.hardware.common.AbstractSensors;
import oshi.util.ExecutingCommand;
import oshi.util.Memoizer;
import oshi.util.ParseUtil;
import oshi.util.tuples.Triplet;

@ThreadSafe
final class OpenBsdSensors extends AbstractSensors {
   private final Supplier tempFanVolts = Memoizer.memoize(OpenBsdSensors::querySensors, Memoizer.defaultExpiration());

   public double queryCpuTemperature() {
      return (Double)((Triplet)this.tempFanVolts.get()).getA();
   }

   public int[] queryFanSpeeds() {
      return (int[])((Triplet)this.tempFanVolts.get()).getB();
   }

   public double queryCpuVoltage() {
      return (Double)((Triplet)this.tempFanVolts.get()).getC();
   }

   private static Triplet querySensors() {
      double volts = (double)0.0F;
      List<Double> cpuTemps = new ArrayList();
      List<Double> allTemps = new ArrayList();
      List<Integer> fanRPMs = new ArrayList();

      for(String line : ExecutingCommand.runNative("systat -ab sensors")) {
         String[] split = ParseUtil.whitespaces.split(line);
         if (split.length > 1) {
            if (split[0].contains("cpu")) {
               if (split[0].contains("temp0")) {
                  cpuTemps.add(ParseUtil.parseDoubleOrDefault(split[1], Double.NaN));
               } else if (split[0].contains("volt0")) {
                  volts = ParseUtil.parseDoubleOrDefault(split[1], (double)0.0F);
               }
            } else if (split[0].contains("temp0")) {
               allTemps.add(ParseUtil.parseDoubleOrDefault(split[1], Double.NaN));
            } else if (split[0].contains("fan")) {
               fanRPMs.add(ParseUtil.parseIntOrDefault(split[1], 0));
            }
         }
      }

      double temp = cpuTemps.isEmpty() ? listAverage(allTemps) : listAverage(cpuTemps);
      int[] fans = new int[fanRPMs.size()];

      for(int i = 0; i < fans.length; ++i) {
         fans[i] = (Integer)fanRPMs.get(i);
      }

      return new Triplet(temp, fans, volts);
   }

   private static double listAverage(List doubles) {
      double sum = (double)0.0F;
      int count = 0;

      for(Double d : doubles) {
         if (!d.isNaN()) {
            sum += d;
            ++count;
         }
      }

      return count > 0 ? sum / (double)count : (double)0.0F;
   }
}
