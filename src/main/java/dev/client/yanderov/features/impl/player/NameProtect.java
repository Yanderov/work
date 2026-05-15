package dev.client.yanderov.features.impl.player;

import dev.client.yanderov.common.repository.friend.FriendUtils;
import dev.client.yanderov.events.render.TextFactoryEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.TextSetting;
import dev.client.yanderov.utils.client.managers.event.EventHandler;

public class NameProtect extends Module {
   private final TextSetting nameSetting = (new TextSetting("Ð˜Ð¼Ñ", "ÐÐ¸ÐºÐ½ÐµÐ¹Ð¼, ÐºÐ¾Ñ‚Ð¾Ñ€Ñ‹Ð¹ Ð±ÑƒÐ´ÐµÑ‚ Ð·Ð°Ð¼ÐµÐ½ÐµÐ½ Ð½Ð° Ð²Ð°Ñˆ")).setText("t.me/HellsDLC").setMax(32);
   private final BooleanSetting friendsSetting = (new BooleanSetting("Ð”Ñ€ÑƒÐ·ÑŒÑ", "Ð¡ÐºÑ€Ñ‹Ð²Ð°ÐµÑ‚ Ð½Ð¸ÐºÐ½ÐµÐ¹Ð¼Ñ‹ Ð´Ñ€ÑƒÐ·ÐµÐ¹")).setValue(true);

   public NameProtect() {
      super("NameProtect", "Name Protect", ModuleCategory.PLAYER);
      this.setup(new Setting[]{this.friendsSetting});
   }

   @EventHandler
   public void onTextFactory(TextFactoryEvent e) {
      e.replaceText(mc.method_1548().method_1676(), this.nameSetting.getText());
      if (this.friendsSetting.isValue()) {
         FriendUtils.getFriends().forEach((friend) -> e.replaceText(friend.getName(), this.nameSetting.getText()));
      }

   }
}

