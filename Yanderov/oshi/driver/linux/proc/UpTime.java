package oshi.driver.linux.proc;

import oshi.annotation.concurrent.ThreadSafe;
import oshi.util.FileUtil;
import oshi.util.platform.linux.ProcPath;

@ThreadSafe
public final class UpTime {
   private UpTime() {
   }

   public static double getSystemUptimeSeconds() {
      String uptime = FileUtil.getStringFromFile(ProcPath.UPTIME);
      int spaceIndex = uptime.indexOf(32);

      try {
         return spaceIndex < 0 ? (double)0.0F : Double.parseDouble(uptime.substring(0, spaceIndex));
      } catch (NumberFormatException var3) {
         return (double)0.0F;
      }
   }
}
