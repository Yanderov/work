package oshi.hardware.platform.unix.openbsd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.hardware.PowerSource;
import oshi.hardware.common.AbstractPowerSource;
import oshi.util.ExecutingCommand;
import oshi.util.ParseUtil;

@ThreadSafe
public final class OpenBsdPowerSource extends AbstractPowerSource {
   public OpenBsdPowerSource(String psName, String psDeviceName, double psRemainingCapacityPercent, double psTimeRemainingEstimated, double psTimeRemainingInstant, double psPowerUsageRate, double psVoltage, double psAmperage, boolean psPowerOnLine, boolean psCharging, boolean psDischarging, PowerSource.CapacityUnits psCapacityUnits, int psCurrentCapacity, int psMaxCapacity, int psDesignCapacity, int psCycleCount, String psChemistry, LocalDate psManufactureDate, String psManufacturer, String psSerialNumber, double psTemperature) {
      super(psName, psDeviceName, psRemainingCapacityPercent, psTimeRemainingEstimated, psTimeRemainingInstant, psPowerUsageRate, psVoltage, psAmperage, psPowerOnLine, psCharging, psDischarging, psCapacityUnits, psCurrentCapacity, psMaxCapacity, psDesignCapacity, psCycleCount, psChemistry, psManufactureDate, psManufacturer, psSerialNumber, psTemperature);
   }

   public static List getPowerSources() {
      Set<String> psNames = new HashSet();

      for(String line : ExecutingCommand.runNative("systat -ab sensors")) {
         if (line.contains(".amphour") || line.contains(".watthour")) {
            int dot = line.indexOf(46);
            psNames.add(line.substring(0, dot));
         }
      }

      List<PowerSource> psList = new ArrayList();

      for(String name : psNames) {
         psList.add(getPowerSource(name));
      }

      return psList;
   }

   private static OpenBsdPowerSource getPowerSource(String name) {
      String psName = name.startsWith("acpi") ? name.substring(4) : name;
      double psRemainingCapacityPercent = (double)1.0F;
      double psTimeRemainingEstimated = (double)-1.0F;
      double psPowerUsageRate = (double)0.0F;
      double psVoltage = (double)-1.0F;
      double psAmperage = (double)0.0F;
      boolean psPowerOnLine = false;
      boolean psCharging = false;
      boolean psDischarging = false;
      PowerSource.CapacityUnits psCapacityUnits = PowerSource.CapacityUnits.RELATIVE;
      int psCurrentCapacity = 0;
      int psMaxCapacity = 1;
      int psDesignCapacity = 1;
      int psCycleCount = -1;
      LocalDate psManufactureDate = null;
      double psTemperature = (double)0.0F;

      for(String line : ExecutingCommand.runNative("systat -ab sensors")) {
         String[] split = ParseUtil.whitespaces.split(line);
         if (split.length > 1 && split[0].startsWith(name)) {
            if (!split[0].contains("volt0") && (!split[0].contains("volt") || !line.contains("current"))) {
               if (split[0].contains("current0")) {
                  psAmperage = ParseUtil.parseDoubleOrDefault(split[1], (double)0.0F);
               } else if (split[0].contains("temp0")) {
                  psTemperature = ParseUtil.parseDoubleOrDefault(split[1], (double)0.0F);
               } else if (split[0].contains("watthour") || split[0].contains("amphour")) {
                  psCapacityUnits = split[0].contains("watthour") ? PowerSource.CapacityUnits.MWH : PowerSource.CapacityUnits.MAH;
                  if (line.contains("remaining")) {
                     psCurrentCapacity = (int)((double)1000.0F * ParseUtil.parseDoubleOrDefault(split[1], (double)0.0F));
                  } else if (line.contains("full")) {
                     psMaxCapacity = (int)((double)1000.0F * ParseUtil.parseDoubleOrDefault(split[1], (double)0.0F));
                  } else if (line.contains("new") || line.contains("design")) {
                     psDesignCapacity = (int)((double)1000.0F * ParseUtil.parseDoubleOrDefault(split[1], (double)0.0F));
                  }
               }
            } else {
               psVoltage = ParseUtil.parseDoubleOrDefault(split[1], (double)-1.0F);
            }
         }
      }

      int state = ParseUtil.parseIntOrDefault(ExecutingCommand.getFirstAnswer("apm -b"), 255);
      if (state < 4) {
         psPowerOnLine = true;
         if (state == 3) {
            psCharging = true;
         } else {
            int time = ParseUtil.parseIntOrDefault(ExecutingCommand.getFirstAnswer("apm -m"), -1);
            psTimeRemainingEstimated = time < 0 ? (double)-1.0F : (double)60.0F * (double)time;
            psDischarging = true;
         }
      }

      int life = ParseUtil.parseIntOrDefault(ExecutingCommand.getFirstAnswer("apm -l"), -1);
      if (life > 0) {
         psRemainingCapacityPercent = (double)life / (double)100.0F;
      }

      if (psMaxCapacity < psDesignCapacity && psMaxCapacity < psCurrentCapacity) {
         psMaxCapacity = psDesignCapacity;
      } else if (psDesignCapacity < psMaxCapacity && psDesignCapacity < psCurrentCapacity) {
         psDesignCapacity = psMaxCapacity;
      }

      String psDeviceName = "unknown";
      String psSerialNumber = "unknown";
      String psChemistry = "unknown";
      String psManufacturer = "unknown";
      if (psVoltage > (double)0.0F) {
         if (psAmperage > (double)0.0F && psPowerUsageRate == (double)0.0F) {
            psPowerUsageRate = psAmperage * psVoltage;
         } else if (psAmperage == (double)0.0F && psPowerUsageRate > (double)0.0F) {
            psAmperage = psPowerUsageRate / psVoltage;
         }
      }

      return new OpenBsdPowerSource(psName, psDeviceName, psRemainingCapacityPercent, psTimeRemainingEstimated, psTimeRemainingEstimated, psPowerUsageRate, psVoltage, psAmperage, psPowerOnLine, psCharging, psDischarging, psCapacityUnits, psCurrentCapacity, psMaxCapacity, psDesignCapacity, psCycleCount, psChemistry, psManufactureDate, psManufacturer, psSerialNumber, psTemperature);
   }
}
