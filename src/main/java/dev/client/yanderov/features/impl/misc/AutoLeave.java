package dev.client.yanderov.features.impl.misc;

import dev.client.yanderov.common.repository.friend.FriendUtils;
import dev.client.yanderov.display.hud.Notifications;
import dev.client.yanderov.display.hud.StaffList;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.MultiSelectSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.client.packet.network.Network;
import net.minecraft.class_1297;
import net.minecraft.class_2561;

public class AutoLeave extends Module {
   private final SelectSetting leaveType = (new SelectSetting("Ð¢Ð¸Ð¿ Ð²Ñ‹Ñ…Ð¾Ð´Ð°", "ÐŸÐ¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ Ð²Ñ‹Ð±Ñ€Ð°Ñ‚ÑŒ Ñ‚Ð¸Ð¿ Ð²Ñ‹Ñ…Ð¾Ð´Ð°")).value("Hub", "Main Menu").selected("Main Menu");
   private final MultiSelectSetting triggerSetting = (new MultiSelectSetting("Ð¢Ñ€Ð¸Ð³Ð³ÐµÑ€Ñ‹", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ, Ð² ÐºÐ°ÐºÐ¸Ñ… ÑÐ»ÑƒÑ‡Ð°ÑÑ… Ð¿Ñ€Ð¾Ð¸Ð·Ð¾Ð¹Ð´ÐµÑ‚ Ð²Ñ‹Ñ…Ð¾Ð´")).value("Players", "Staff").selected("Players", "Staff");
   private final SliderSettings distanceSetting = (new SliderSettings("ÐœÐ°ÐºÑÐ¸Ð¼Ð°Ð»ÑŒÐ½Ð°Ñ Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ", "ÐœÐ°ÐºÑÐ¸Ð¼Ð°Ð»ÑŒÐ½Ð°Ñ Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð´Ð»Ñ Ð°ÐºÑ‚Ð¸Ð²Ð°Ñ†Ð¸Ð¸ Ð°Ð²Ñ‚Ð¾-Ð²Ñ‹Ñ…Ð¾Ð´Ð°")).setValue(10.0F).range(5, 40).visible(() -> this.triggerSetting.isSelected("Players"));

   public AutoLeave() {
      super("AutoLeave", "Auto Leave", ModuleCategory.MISC);
      this.setup(new Setting[]{this.leaveType, this.triggerSetting, this.distanceSetting});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (!Network.isPvp()) {
         if (this.triggerSetting.isSelected("Players")) {
            mc.field_1687.method_18456().stream().filter((p) -> mc.field_1724.method_5739(p) < this.distanceSetting.getValue() && mc.field_1724 != p && !FriendUtils.isFriend((class_1297)p)).findFirst().ifPresent((p) -> this.leave(p.method_5477().method_27661().method_27693(" - ÐŸÐ¾ÑÐ²Ð¸Ð»ÑÑ Ñ€ÑÐ´Ð¾Ð¼ " + mc.field_1724.method_5739(p) + "Ð¼")));
         }

         if (this.triggerSetting.isSelected("Staff") && !StaffList.getInstance().list.isEmpty()) {
            this.leave(class_2561.method_30163("Ð¡Ñ‚Ð°Ñ„Ñ„ Ð½Ð° ÑÐµÑ€Ð²ÐµÑ€Ðµ"));
         }

      }
   }

   public void leave(class_2561 text) {
      switch (this.leaveType.getSelected()) {
         case "Hub":
            Notifications.getInstance().addList((class_2561)class_2561.method_30163("[AutoLeave] ").method_27661().method_10852(text), 10000L);
            mc.method_1562().method_45730("hub");
            break;
         case "Main Menu":
            mc.method_1562().method_48296().method_10747(class_2561.method_30163("[Auto Leave] \n").method_27661().method_10852(text));
      }

      this.setState(false);
   }
}

