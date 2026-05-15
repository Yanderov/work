package fun.Yanderov.features.impl.player;

import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.MultiSelectSetting;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;

public class NoDelay extends Module {
   public final MultiSelectSetting ignoreSetting = (new MultiSelectSetting("Ð¢Ð¸Ð¿", "Ð Ð°Ð·Ñ€ÐµÑˆÐ°ÐµÑ‚ Ð²Ñ‹Ð±Ñ€Ð°Ð½Ð½Ñ‹Ðµ Ð²Ð°Ð¼Ð¸ Ð´ÐµÐ¹ÑÑ‚Ð²Ð¸Ñ")).value("Jump", "Right Click", "Break CoolDown");

   public static NoDelay getInstance() {
      return (NoDelay)Instance.get(NoDelay.class);
   }

   public NoDelay() {
      super("NoDelay", "No Delay", ModuleCategory.PLAYER);
      this.setup(new Setting[]{this.ignoreSetting});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.ignoreSetting.isSelected("Break CoolDown")) {
         mc.field_1761.field_3716 = 0;
      }

      if (this.ignoreSetting.isSelected("Jump")) {
         mc.field_1724.field_6228 = 0;
      }

      if (this.ignoreSetting.isSelected("Right Click")) {
         mc.field_1752 = 0;
      }

   }
}

