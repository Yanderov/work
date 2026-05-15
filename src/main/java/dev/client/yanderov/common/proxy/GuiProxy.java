package dev.client.yanderov.common.proxy;

import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_342;
import net.minecraft.class_4185;
import net.minecraft.class_4286;
import net.minecraft.class_437;
import net.minecraft.class_442;
import net.minecraft.class_500;
import org.apache.commons.lang3.StringUtils;

public class GuiProxy extends class_437 {
   private boolean isSocks4 = false;
   private class_342 ipPort;
   private class_342 username;
   private class_342 password;
   private class_4286 enabledCheck;
   private class_437 parentScreen;
   private String msg = "";
   private int[] positionY;
   private int positionX;
   private static String text_proxy = class_2561.method_43471("PROXY").getString();

   public GuiProxy(class_437 parentScreen) {
      super(class_2561.method_43470(text_proxy));
      this.parentScreen = parentScreen;
   }

   private static boolean isValidIpPort(String ipP) {
      if (ipP != null && !ipP.isEmpty()) {
         String[] split = ipP.split(":");
         if (split.length > 1) {
            if (!StringUtils.isNumeric(split[1])) {
               return false;
            } else {
               try {
                  int port = Integer.parseInt(split[1]);
                  return port >= 0 && port <= 65535;
               } catch (NumberFormatException var3) {
                  return false;
               }
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private boolean checkProxy() {
      if (!isValidIpPort(this.ipPort.method_1882())) {
         this.ipPort.method_25365(true);
         return false;
      } else {
         return true;
      }
   }

   private void centerButtons(int amount, int buttonLength, int gap) {
      this.positionX = this.field_22789 / 2 - buttonLength / 2;
      this.positionY = new int[amount];
      int center = (this.field_22790 + amount * gap) / 2;
      int buttonStarts = center - amount * gap;

      for(int i = 0; i != amount; ++i) {
         this.positionY[i] = buttonStarts + gap * i;
      }

   }

   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
      if (keyCode == 256) {
         class_310.method_1551().method_1507(this.parentScreen);
         return true;
      } else {
         super.method_25404(keyCode, scanCode, modifiers);
         this.msg = "";
         return true;
      }
   }

   public void method_25394(class_332 context, int mouseX, int mouseY, float partialTicks) {
      super.method_25394(context, mouseX, mouseY, partialTicks);
      if (this.enabledCheck.method_20372() && !isValidIpPort(this.ipPort.method_1882())) {
         this.enabledCheck.method_25306();
      }

      context.method_25303(this.field_22793, class_2561.method_43471("Ð’Ð²ÐµÐ´Ð¸Ñ‚Ðµ Ð°Ð¹Ð¿Ð¸ Ð°Ð´Ñ€ÐµÑ Ð¸ Ð¿Ð¾Ñ€Ñ‚. ÐŸÑ€Ð¸Ð¼ÐµÑ€ Ð½Ð¸Ð¶Ðµ").getString(), this.field_22789 / 2 - 106, this.positionY[3] - 15, 10526880);
      context.method_25303(this.field_22793, class_2561.method_43471("ÐÐ¹Ð¿Ð¸:ÐŸÐ¾Ñ€Ñ‚ â–¸").getString(), this.field_22789 / 2 - 140, this.positionY[3] + 15, 10526880);
      this.ipPort.method_25394(context, mouseX, mouseY, partialTicks);
      context.method_25303(this.field_22793, class_2561.method_43471("ÐÐ¸ÐºÐ½ÐµÐ¹Ð¼ â–¸").getString(), this.field_22789 / 2 - 131, this.positionY[4] + 15, 10526880);
      context.method_25303(this.field_22793, class_2561.method_43471("ÐŸÐ°Ñ€Ð¾Ð»ÑŒ â–¸").getString(), this.field_22789 / 2 - 126, this.positionY[5] + 15, 10526880);
      this.username.method_25394(context, mouseX, mouseY, partialTicks);
      this.password.method_25394(context, mouseX, mouseY, partialTicks);
      context.method_25300(this.field_22793, this.msg, this.field_22789 / 2, this.positionY[6] + 5, 10526880);
   }

   public void method_25426() {
      int buttonLength = 160;
      this.centerButtons(10, buttonLength, 26);
      this.isSocks4 = ProxyServer.proxy.type == Proxy.ProxyType.SOCKS4;
      this.ipPort = new class_342(this.field_22793, this.positionX, this.positionY[3] + 10, buttonLength, 20, class_2561.method_43470(""));
      this.ipPort.method_1852(ProxyServer.proxy.ipPort);
      this.ipPort.method_1880(1024);
      this.ipPort.method_25365(true);
      this.method_25429(this.ipPort);
      this.username = new class_342(this.field_22793, this.positionX, this.positionY[4] + 10, buttonLength, 20, class_2561.method_43470(""));
      this.username.method_1880(255);
      this.username.method_1852(ProxyServer.proxy.username);
      this.method_25429(this.username);
      this.password = new class_342(this.field_22793, this.positionX, this.positionY[5] + 10, buttonLength, 20, class_2561.method_43470(""));
      this.password.method_1880(255);
      this.password.method_1852(ProxyServer.proxy.password);
      this.method_25429(this.password);
      int posXButtons = this.field_22789 / 2 - buttonLength / 2 * 3 / 2;
      class_4185 apply = class_4185.method_46430(class_2561.method_43471("ÐŸÑ€Ð¸Ð¼ÐµÐ½Ð¸Ñ‚ÑŒ"), (button) -> {
         if (this.enabledCheck.method_20372()) {
            if (this.checkProxy()) {
               ProxyServer.proxy = new Proxy(this.isSocks4, this.ipPort.method_1882(), this.username.method_1882(), this.password.method_1882());
               ProxyServer.proxyEnabled = true;
               Config.setDefaultProxy(ProxyServer.proxy);
               Config.saveConfig();
               class_310.method_1551().method_1507(new class_500(new class_442()));
            }
         } else {
            ProxyServer.proxy = new Proxy(this.isSocks4, this.ipPort.method_1882(), this.username.method_1882(), this.password.method_1882());
            ProxyServer.proxyEnabled = false;
            Config.setDefaultProxy(ProxyServer.proxy);
            Config.saveConfig();
            class_310.method_1551().method_1507(new class_500(new class_442()));
         }

      }).method_46434(posXButtons + (buttonLength / 2 - 62) * 2, this.positionY[7] - 10, buttonLength / 2 + 3, 20).method_46431();
      this.method_37063(apply);
      class_4286.class_8929 checkboxBuilder = class_4286.method_54787(class_2561.method_43471("Ð’ÐºÐ»ÑŽÑ‡Ð¸Ñ‚ÑŒ Ð¿Ñ€Ð¾ÐºÑÐ¸"), this.field_22793);
      checkboxBuilder.method_54789(this.field_22789 / 2 - 34 - (13 + this.field_22793.method_27525(class_2561.method_43471("Ð’ÐºÐ»ÑŽÑ‡Ð¸Ñ‚ÑŒ Ð¿Ñ€Ð¾ÐºÑÐ¸"))) / 2, this.positionY[7] + 15);
      if (ProxyServer.proxyEnabled) {
         checkboxBuilder.method_54794(ProxyServer.proxyEnabled);
      }

      this.enabledCheck = checkboxBuilder.method_54788();
      this.method_37063(this.enabledCheck);
      class_4185 cancel = class_4185.method_46430(class_2561.method_43471("ÐžÑ‚Ð¼ÐµÐ½Ð¸Ñ‚ÑŒ"), (button) -> class_310.method_1551().method_1507(this.parentScreen)).method_46434(posXButtons + (buttonLength / 2 - 16) * 2, this.positionY[7] - 10, buttonLength / 2 - 3, 20).method_46431();
      this.method_37063(cancel);
   }

   public void method_25419() {
      this.msg = "";
      class_310.method_1551().method_1507(this.parentScreen);
   }
}

