package fun.Yanderov.features.impl.player;

import fun.Yanderov.common.repository.friend.FriendUtils;
import fun.Yanderov.events.render.TextFactoryEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.TextSetting;
import fun.Yanderov.utils.client.managers.event.EventHandler;

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

