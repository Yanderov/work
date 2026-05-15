package oshi.hardware.platform.linux;

import com.sun.jna.platform.linux.Udev;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.hardware.PowerSource;
import oshi.hardware.common.AbstractPowerSource;
import oshi.software.os.linux.LinuxOperatingSystem;
import oshi.util.ParseUtil;

@ThreadSafe
public final class LinuxPowerSource extends AbstractPowerSource {
   private static final Logger LOG = LoggerFactory.getLogger(LinuxPowerSource.class);

   public LinuxPowerSource(String psName, String psDeviceName, double psRemainingCapacityPercent, double psTimeRemainingEstimated, double psTimeRemainingInstant, double psPowerUsageRate, double psVoltage, double psAmperage, boolean psPowerOnLine, boolean psCharging, boolean psDischarging, PowerSource.CapacityUnits psCapacityUnits, int psCurrentCapacity, int psMaxCapacity, int psDesignCapacity, int psCycleCount, String psChemistry, LocalDate psManufactureDate, String psManufacturer, String psSerialNumber, double psTemperature) {
      super(psName, psDeviceName, psRemainingCapacityPercent, psTimeRemainingEstimated, psTimeRemainingInstant, psPowerUsageRate, psVoltage, psAmperage, psPowerOnLine, psCharging, psDischarging, psCapacityUnits, psCurrentCapacity, psMaxCapacity, psDesignCapacity, psCycleCount, psChemistry, psManufactureDate, psManufacturer, psSerialNumber, psTemperature);
   }

   public static List getPowerSources() {
      if (!LinuxOperatingSystem.HAS_UDEV) {
         LOG.warn("Power Source information requires libudev, which is not present.");
         return Collections.emptyList();
      } else {
         double psRemainingCapacityPercent = (double)-1.0F;
         double psTimeRemainingEstimated = (double)-1.0F;
         double psTimeRemainingInstant = (double)-1.0F;
         double psPowerUsageRate = (double)0.0F;
         double psVoltage = (double)-1.0F;
         double psAmperage = (double)0.0F;
         boolean psPowerOnLine = false;
         boolean psCharging = false;
         boolean psDischarging = false;
         PowerSource.CapacityUnits psCapacityUnits = PowerSource.CapacityUnits.RELATIVE;
         int psCurrentCapacity = -1;
         int psMaxCapacity = -1;
         int psDesignCapacity = -1;
         int psCycleCount = -1;
         LocalDate psManufactureDate = null;
         double psTemperature = (double)0.0F;
         List<PowerSource> psList = new ArrayList();
         Udev.UdevContext udev = Udev.INSTANCE.udev_new();

         try {
            Udev.UdevEnumerate enumerate = udev.enumerateNew();

            try {
               enumerate.addMatchSubsystem("power_supply");
               enumerate.scanDevices();

               for(Udev.UdevListEntry entry = enumerate.getListEntry(); entry != null; entry = entry.getNext()) {
                  String syspath = entry.getName();
                  String name = syspath.substring(syspath.lastIndexOf(File.separatorChar) + 1);
                  if (!name.startsWith("ADP") && !name.startsWith("AC")) {
                     Udev.UdevDevice device = udev.deviceNewFromSyspath(syspath);
                     if (device != null) {
                        try {
                           if (ParseUtil.parseIntOrDefault(device.getPropertyValue("POWER_SUPPLY_PRESENT"), 1) > 0 && ParseUtil.parseIntOrDefault(device.getPropertyValue("POWER_SUPPLY_ONLINE"), 1) > 0) {
                              String psName = getOrDefault(device, "POWER_SUPPLY_NAME", name);
                              String status = device.getPropertyValue("POWER_SUPPLY_STATUS");
                              psCharging = "Charging".equals(status);
                              psDischarging = "Discharging".equals(status);
                              psRemainingCapacityPercent = (double)ParseUtil.parseIntOrDefault(device.getPropertyValue("POWER_SUPPLY_CAPACITY"), -100) / (double)100.0F;
                              psCurrentCapacity = ParseUtil.parseIntOrDefault(device.getPropertyValue("POWER_SUPPLY_ENERGY_NOW"), -1);
                              if (psCurrentCapacity < 0) {
                                 psCurrentCapacity = ParseUtil.parseIntOrDefault(device.getPropertyValue("POWER_SUPPLY_CHARGE_NOW"), -1);
                              }

                              psMaxCapacity = ParseUtil.parseIntOrDefault(device.getPropertyValue("POWER_SUPPLY_ENERGY_FULL"), 1);
                              if (psMaxCapacity < 0) {
                                 psMaxCapacity = ParseUtil.parseIntOrDefault(device.getPropertyValue("POWER_SUPPLY_CHARGE_FULL"), 1);
                              }

                              psDesignCapacity = ParseUtil.parseIntOrDefault(device.getPropertyValue("POWER_SUPPLY_ENERGY_FULL_DESIGN"), 1);
                              if (psDesignCapacity < 0) {
                                 psDesignCapacity = ParseUtil.parseIntOrDefault(device.getPropertyValue("POWER_SUPPLY_CHARGE_FULL_DESIGN"), 1);
                              }

                              psVoltage = (double)ParseUtil.parseIntOrDefault(device.getPropertyValue("POWER_SUPPLY_VOLTAGE_NOW"), -1);
                              if (psVoltage > (double)0.0F) {
                                 String power = device.getPropertyValue("POWER_SUPPLY_POWER_NOW");
                                 String current = device.getPropertyValue("POWER_SUPPLY_CURRENT_NOW");
                                 if (power == null) {
                                    psAmperage = (double)ParseUtil.parseIntOrDefault(current, 0);
                                    psPowerUsageRate = psAmperage * psVoltage;
                                 } else if (current == null) {
                                    psPowerUsageRate = (double)ParseUtil.parseIntOrDefault(power, 0);
                                    psAmperage = psPowerUsageRate / psVoltage;
                                 } else {
                                    psAmperage = (double)ParseUtil.parseIntOrDefault(current, 0);
                                    psPowerUsageRate = (double)ParseUtil.parseIntOrDefault(power, 0);
                                 }
                              }

                              psCycleCount = ParseUtil.parseIntOrDefault(device.getPropertyValue("POWER_SUPPLY_CYCLE_COUNT"), -1);
                              String psChemistry = getOrDefault(device, "POWER_SUPPLY_TECHNOLOGY", "unknown");
                              String psDeviceName = getOrDefault(device, "POWER_SUPPLY_MODEL_NAME", "unknown");
                              String psManufacturer = getOrDefault(device, "POWER_SUPPLY_MANUFACTURER", "unknown");
                              String psSerialNumber = getOrDefault(device, "POWER_SUPPLY_SERIAL_NUMBER", "unknown");
                              psList.add(new LinuxPowerSource(psName, psDeviceName, psRemainingCapacityPercent, psTimeRemainingEstimated, psTimeRemainingInstant, psPowerUsageRate, psVoltage, psAmperage, psPowerOnLine, psCharging, psDischarging, psCapacityUnits, psCurrentCapacity, psMaxCapacity, psDesignCapacity, psCycleCount, psChemistry, psManufactureDate, psManufacturer, psSerialNumber, psTemperature));
                           }
                        } finally {
                           device.unref();
                        }
                     }
                  }
               }
            } finally {
               enumerate.unref();
            }
         } finally {
            udev.unref();
         }

         return psList;
      }
   }

   private static String getOrDefault(Udev.UdevDevice device, String property, String def) {
      String value = device.getPropertyValue(property);
      return value == null ? def : value;
   }
}
