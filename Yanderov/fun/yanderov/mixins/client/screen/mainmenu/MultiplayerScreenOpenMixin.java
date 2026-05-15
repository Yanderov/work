package fun.Yanderov.mixins.client.screen.mainmenu;

import fun.Yanderov.common.proxy.Config;
import fun.Yanderov.common.proxy.GuiProxy;
import fun.Yanderov.common.proxy.Proxy;
import fun.Yanderov.common.proxy.ProxyServer;
import fun.Yanderov.features.impl.misc.SelfDestruct;
import fun.Yanderov.mixins.client.screen.IScreen;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_4185;
import net.minecraft.class_500;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_500.class})
public class MultiplayerScreenOpenMixin {
   @Inject(
      method = {"init()V"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/gui/screen/multiplayer/MultiplayerScreen;updateButtonActivationStates()V"
)}
   )
   public void multiplayerGuiOpen(CallbackInfo ci) {
      if (!SelfDestruct.unhooked) {
         String playerName = class_310.method_1551().method_1548().method_1676();
         if (!playerName.equals(Config.lastPlayerName)) {
            Config.lastPlayerName = playerName;
            if (Config.accounts.containsKey(playerName)) {
               ProxyServer.proxy = (Proxy)Config.accounts.get(playerName);
            } else if (Config.accounts.containsKey("")) {
               ProxyServer.proxy = (Proxy)Config.accounts.get("");
            } else {
               ProxyServer.proxy = new Proxy();
            }
         }

         class_500 ms = (class_500)this;
         class_310 client = class_310.method_1551();
         int screenWidth = client.method_22683().method_4486();
         String buttonText;
         if (ProxyServer.proxyEnabled && ProxyServer.proxy != null && !ProxyServer.proxy.ipPort.isEmpty()) {
            buttonText = "ÐŸÑ€Ð¾ÐºÑÐ¸: ÐÐºÑ‚Ð¸Ð²ÐµÐ½";
         } else {
            buttonText = "Proxy";
         }

         ProxyServer.proxyMenuButton = class_4185.method_46430(class_2561.method_43470(buttonText), (buttonWidget) -> class_310.method_1551().method_1507(new GuiProxy(ms))).method_46434(5, 5, 100, 20).method_46431();
         IScreen si = (IScreen)ms;
         si.getDrawables().add(ProxyServer.proxyMenuButton);
         si.getSelectables().add(ProxyServer.proxyMenuButton);
         si.getChildren().add(ProxyServer.proxyMenuButton);
      }
   }
}

