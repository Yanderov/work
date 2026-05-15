package fun.Yanderov.features.impl.misc;

import antidaunleak.api.annotation.Native;
import fun.Yanderov.common.repository.friend.FriendUtils;
import fun.Yanderov.events.keyboard.KeyEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BindSetting;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_239;
import net.minecraft.class_3966;

public class ClickFriend extends Module {
   private final BindSetting friendBind = new BindSetting("Ð”Ð¾Ð±Ð°Ð²Ð¸Ñ‚ÑŒ Ð´Ñ€ÑƒÐ³Ð°", "Ð”Ð¾Ð±Ð°Ð²Ð¸Ñ‚ÑŒ/ÑƒÐ´Ð°Ð»Ð¸Ñ‚ÑŒ Ð´Ñ€ÑƒÐ³Ð°");

   public ClickFriend() {
      super("ClickFriend", "Click Friend", ModuleCategory.MISC);
      this.setup(new Setting[]{this.friendBind});
   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginMutation
   )
   public void onKey(KeyEvent e) {
      if (e.isKeyDown(this.friendBind.getKey())) {
         class_239 var4 = mc.field_1765;
         if (var4 instanceof class_3966) {
            class_3966 result = (class_3966)var4;
            class_1297 var5 = result.method_17782();
            if (var5 instanceof class_1657) {
               class_1657 player = (class_1657)var5;
               if (FriendUtils.isFriend((class_1297)player)) {
                  FriendUtils.removeFriend(player);
               } else {
                  FriendUtils.addFriend(player);
               }
            }
         }
      }

   }
}

