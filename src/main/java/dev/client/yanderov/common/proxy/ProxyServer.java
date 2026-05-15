package dev.client.yanderov.common.proxy;

import net.minecraft.class_4185;

public class ProxyServer {
   public static boolean proxyEnabled = false;
   public static Proxy proxy = new Proxy();
   public static Proxy lastUsedProxy = new Proxy();
   public static class_4185 proxyMenuButton;

   public static String getLastUsedProxyIp() {
      return lastUsedProxy.ipPort.isEmpty() ? "none" : lastUsedProxy.getIp();
   }
}

