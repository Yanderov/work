package oshi.driver.windows.wmi;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import java.util.Objects;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.util.platform.windows.WmiQueryHandler;

@ThreadSafe
public final class Win32PhysicalMemory {
   private static final String WIN32_PHYSICAL_MEMORY = "Win32_PhysicalMemory";

   private Win32PhysicalMemory() {
   }

   public static WbemcliUtil.WmiResult queryphysicalMemory() {
      WbemcliUtil.WmiQuery<PhysicalMemoryProperty> physicalMemoryQuery = new WbemcliUtil.WmiQuery("Win32_PhysicalMemory", PhysicalMemoryProperty.class);
      return ((WmiQueryHandler)Objects.requireNonNull(WmiQueryHandler.createInstance())).queryWMI(physicalMemoryQuery);
   }

   public static WbemcliUtil.WmiResult queryphysicalMemoryWin8() {
      WbemcliUtil.WmiQuery<PhysicalMemoryPropertyWin8> physicalMemoryQuery = new WbemcliUtil.WmiQuery("Win32_PhysicalMemory", PhysicalMemoryPropertyWin8.class);
      return ((WmiQueryHandler)Objects.requireNonNull(WmiQueryHandler.createInstance())).queryWMI(physicalMemoryQuery);
   }

   public static enum PhysicalMemoryProperty {
      BANKLABEL,
      CAPACITY,
      SPEED,
      MANUFACTURER,
      SMBIOSMEMORYTYPE;

      // $FF: synthetic method
      private static PhysicalMemoryProperty[] $values() {
         return new PhysicalMemoryProperty[]{BANKLABEL, CAPACITY, SPEED, MANUFACTURER, SMBIOSMEMORYTYPE};
      }
   }

   public static enum PhysicalMemoryPropertyWin8 {
      BANKLABEL,
      CAPACITY,
      SPEED,
      MANUFACTURER,
      MEMORYTYPE;

      // $FF: synthetic method
      private static PhysicalMemoryPropertyWin8[] $values() {
         return new PhysicalMemoryPropertyWin8[]{BANKLABEL, CAPACITY, SPEED, MANUFACTURER, MEMORYTYPE};
      }
   }
}
