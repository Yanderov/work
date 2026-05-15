package oshi.software.os.unix.aix;

import com.sun.jna.Native;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.jna.platform.unix.AixLibc;
import oshi.software.common.AbstractNetworkParams;
import oshi.util.ExecutingCommand;
import oshi.util.ParseUtil;

@ThreadSafe
final class AixNetworkParams extends AbstractNetworkParams {
   private static final AixLibc LIBC;

   public String getHostName() {
      byte[] hostnameBuffer = new byte[256];
      return 0 != LIBC.gethostname(hostnameBuffer, hostnameBuffer.length) ? super.getHostName() : Native.toString(hostnameBuffer);
   }

   public String getIpv4DefaultGateway() {
      return getDefaultGateway("netstat -rnf inet");
   }

   public String getIpv6DefaultGateway() {
      return getDefaultGateway("netstat -rnf inet6");
   }

   private static String getDefaultGateway(String netstat) {
      for(String line : ExecutingCommand.runNative(netstat)) {
         String[] split = ParseUtil.whitespaces.split(line);
         if (split.length > 7 && "default".equals(split[0])) {
            return split[1];
         }
      }

      return "unknown";
   }

   static {
      LIBC = AixLibc.INSTANCE;
   }
}
