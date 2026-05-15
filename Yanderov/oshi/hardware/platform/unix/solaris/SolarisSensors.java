package oshi.hardware.platform.unix.solaris;

import java.util.ArrayList;
import java.util.List;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.hardware.common.AbstractSensors;
import oshi.util.ExecutingCommand;
import oshi.util.ParseUtil;

@ThreadSafe
final class SolarisSensors extends AbstractSensors {
   public double queryCpuTemperature() {
      double maxTemp = (double)0.0F;

      for(String line : ExecutingCommand.runNative("/usr/sbin/prtpicl -v -c temperature-sensor")) {
         if (line.trim().startsWith("Temperature:")) {
            int temp = ParseUtil.parseLastInt(line, 0);
            if ((double)temp > maxTemp) {
               maxTemp = (double)temp;
            }
         }
      }

      if (maxTemp > (double)1000.0F) {
         maxTemp /= (double)1000.0F;
      }

      return maxTemp;
   }

   public int[] queryFanSpeeds() {
      List<Integer> speedList = new ArrayList();

      for(String line : ExecutingCommand.runNative("/usr/sbin/prtpicl -v -c fan")) {
         if (line.trim().startsWith("Speed:")) {
            speedList.add(ParseUtil.parseLastInt(line, 0));
         }
      }

      int[] fans = new int[speedList.size()];

      for(int i = 0; i < speedList.size(); ++i) {
         fans[i] = (Integer)speedList.get(i);
      }

      return fans;
   }

   public double queryCpuVoltage() {
      double voltage = (double)0.0F;

      for(String line : ExecutingCommand.runNative("/usr/sbin/prtpicl -v -c voltage-sensor")) {
         if (line.trim().startsWith("Voltage:")) {
            voltage = ParseUtil.parseDoubleOrDefault(line.replace("Voltage:", "").trim(), (double)0.0F);
            break;
         }
      }

      return voltage;
   }
}
