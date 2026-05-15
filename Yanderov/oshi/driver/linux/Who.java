package oshi.driver.linux;

import com.sun.jna.Native;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.jna.platform.linux.LinuxLibc;
import oshi.software.os.OSSession;
import oshi.util.ParseUtil;

@ThreadSafe
public final class Who {
   private static final LinuxLibc LIBC;

   private Who() {
   }

   public static synchronized List queryUtxent() {
      List<OSSession> whoList = new ArrayList();
      LIBC.setutxent();

      try {
         LinuxLibc.LinuxUtmpx ut;
         while((ut = LIBC.getutxent()) != null) {
            if (ut.ut_type == 7 || ut.ut_type == 6) {
               String user = Native.toString(ut.ut_user, Charset.defaultCharset());
               String device = Native.toString(ut.ut_line, Charset.defaultCharset());
               String host = ParseUtil.parseUtAddrV6toIP(ut.ut_addr_v6);
               long loginTime = (long)ut.ut_tv.tv_sec * 1000L + (long)ut.ut_tv.tv_usec / 1000L;
               if (user.isEmpty() || device.isEmpty() || loginTime < 0L || loginTime > System.currentTimeMillis()) {
                  List var7 = oshi.driver.unix.Who.queryWho();
                  return var7;
               }

               whoList.add(new OSSession(user, device, loginTime, host));
            }
         }

         return whoList;
      } finally {
         LIBC.endutxent();
      }
   }

   static {
      LIBC = LinuxLibc.INSTANCE;
   }
}
