package oshi.hardware.platform.unix.freebsd;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.hardware.PowerSource;
import oshi.hardware.common.AbstractPowerSource;
import oshi.util.ExecutingCommand;
import oshi.util.ParseUtil;
import oshi.util.platform.unix.freebsd.BsdSysctlUtil;

@ThreadSafe
public final class FreeBsdPowerSource extends AbstractPowerSource {
   public FreeBsdPowerSource(String psName, String psDeviceName, double psRemainingCapacityPercent, double psTimeRemainingEstimated, double psTimeRemainingInstant, double psPowerUsageRate, double psVoltage, double psAmperage, boolean psPowerOnLine, boolean psCharging, boolean psDischarging, PowerSource.CapacityUnits psCapacityUnits, int psCurrentCapacity, int psMaxCapacity, int psDesignCapacity, int psCycleCount, String psChemistry, LocalDate psManufactureDate, String psManufacturer, String psSerialNumber, double psTemperature) {
      super(psName, psDeviceName, psRemainingCapacityPercent, psTimeRemainingEstimated, psTimeRemainingInstant, psPowerUsageRate, psVoltage, psAmperage, psPowerOnLine, psCharging, psDischarging, psCapacityUnits, psCurrentCapacity, psMaxCapacity, psDesignCapacity, psCycleCount, psChemistry, psManufactureDate, psManufacturer, psSerialNumber, psTemperature);
   }

   public static List getPowerSources() {
      return Arrays.asList(getPowerSource("BAT0"));
   }

   private static FreeBsdPowerSource getPowerSource(String name) {
      double psRemainingCapacityPercent = (double)1.0F;
      double psTimeRemainingEstimated = (double)-1.0F;
      double psPowerUsageRate = (double)0.0F;
      int psVoltage = -1;
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
      int state = BsdSysctlUtil.sysctl("hw.acpi.battery.state", 0);
      if (state == 2) {
         psCharging = true;
      } else {
         int time = BsdSysctlUtil.sysctl("hw.acpi.battery.time", -1);
         psTimeRemainingEstimated = time < 0 ? (double)-1.0F : (double)60.0F * (double)time;
         if (state == 1) {
            psDischarging = true;
         }
      }

      int life = BsdSysctlUtil.sysctl("hw.acpi.battery.life", -1);
      if (life > 0) {
         psRemainingCapacityPercent = (double)life / (double)100.0F;
      }

      List<String> acpiconf = ExecutingCommand.runNative("acpiconf -i 0");
      Map<String, String> psMap = new HashMap();

      for(String line : acpiconf) {
         String[] split = line.split(":", 2);
         if (split.length > 1) {
            String value = split[1].trim();
            if (!value.isEmpty()) {
               psMap.put(split[0], value);
            }
         }
      }

      String psDeviceName = (String)psMap.getOrDefault("Model number", "unknown");
      String psSerialNumber = (String)psMap.getOrDefault("Serial number", "unknown");
      String psChemistry = (String)psMap.getOrDefault("Type", "unknown");
      String psManufacturer = (String)psMap.getOrDefault("OEM info", "unknown");
      String cap = (String)psMap.get("Design capacity");
      if (cap != null) {
         psDesignCapacity = ParseUtil.getFirstIntValue(cap);
         if (cap.toLowerCase().contains("mah")) {
            psCapacityUnits = PowerSource.CapacityUnits.MAH;
         } else if (cap.toLowerCase().contains("mwh")) {
            psCapacityUnits = PowerSource.CapacityUnits.MWH;
         }
      }

      cap = (String)psMap.get("Last full capacity");
      if (cap != null) {
         psMaxCapacity = ParseUtil.getFirstIntValue(cap);
      } else {
         psMaxCapacity = psDesignCapacity;
      }

      double psTimeRemainingInstant = psTimeRemainingEstimated;
      String time = (String)psMap.get("Remaining time");
      if (time != null) {
         String[] hhmm = time.split(":");
         if (hhmm.length == 2) {
            psTimeRemainingInstant = (double)3600.0F * (double)ParseUtil.parseIntOrDefault(hhmm[0], 0) + (double)60.0F * (double)ParseUtil.parseIntOrDefault(hhmm[1], 0);
         }
      }

      String rate = (String)psMap.get("Present rate");
      if (rate != null) {
         psPowerUsageRate = (double)ParseUtil.getFirstIntValue(rate);
      }

      String volts = (String)psMap.get("Present voltage");
      if (volts != null) {
         psVoltage = ParseUtil.getFirstIntValue(volts);
         if (psVoltage != 0) {
            psAmperage = psPowerUsageRate / (double)psVoltage;
         }
      }

      return new FreeBsdPowerSource(name, psDeviceName, psRemainingCapacityPercent, psTimeRemainingEstimated, psTimeRemainingInstant, psPowerUsageRate, (double)psVoltage, psAmperage, psPowerOnLine, psCharging, psDischarging, psCapacityUnits, psCurrentCapacity, psMaxCapacity, psDesignCapacity, psCycleCount, psChemistry, psManufactureDate, psManufacturer, psSerialNumber, psTemperature);
   }
}
