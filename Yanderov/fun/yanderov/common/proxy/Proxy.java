package fun.Yanderov.common.proxy;

import com.google.gson.annotations.SerializedName;

public class Proxy {
   @SerializedName("IP:PORT")
   public String ipPort = "";
   public ProxyType type;
   public String username;
   public String password;

   public Proxy() {
      this.type = Proxy.ProxyType.SOCKS5;
      this.username = "";
      this.password = "";
   }

   public Proxy(boolean isSocks4, String ipPort, String username, String password) {
      this.type = Proxy.ProxyType.SOCKS5;
      this.username = "";
      this.password = "";
      this.type = isSocks4 ? Proxy.ProxyType.SOCKS4 : Proxy.ProxyType.SOCKS5;
      this.ipPort = ipPort;
      this.username = username;
      this.password = password;
   }

   public int getPort() {
      if (this.ipPort != null && !this.ipPort.isEmpty() && this.ipPort.contains(":")) {
         try {
            return Integer.parseInt(this.ipPort.split(":")[1]);
         } catch (ArrayIndexOutOfBoundsException | NumberFormatException var2) {
            return 0;
         }
      } else {
         return 0;
      }
   }

   public String getIp() {
      return this.ipPort != null && !this.ipPort.isEmpty() && this.ipPort.contains(":") ? this.ipPort.split(":")[0] : "";
   }

   public static enum ProxyType {
      SOCKS4,
      SOCKS5;

      // $FF: synthetic method
      private static ProxyType[] $values() {
         return new ProxyType[]{SOCKS4, SOCKS5};
      }
   }
}

