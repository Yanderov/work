package dev.client.modules.impl.util;

import dev.client.event.classes.ClickEvent;
import dev.client.event.interfaces.IClickaable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.PlayerModel;
import dev.client.modules.settings.impl.KeySetting;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayNetworkHandler;

@Environment(EnvType.CLIENT)
public class CordDropper extends Module implements IClickaable, IUtil {
   private final KeySetting bind = new KeySetting().name("Bind").value(0);

   public CordDropper() {
      super(new PlayerModel("CordDropper", Category.UTIL, "Отправляет координаты в чат по нажатию кнопки"));
      this.addSetting(this.bind);
   }

   public void onClick(ClickEvent event) {
      if (mc.currentScreen == null && event.getAction() == 1 && event.getKey() == this.bind.getValue()) {
         ClientPlayNetworkHandler networkHandler = mc.getNetworkHandler();
         int x = (int)mc.player.getX();
         networkHandler.sendChatMessage("!x: " + x + " y: " + (int)mc.player.getY() + " z: " + (int)mc.player.getZ());
      }

   }
}

