package dev.client.yanderov.features.impl.movement;

import antidaunleak.api.annotation.Native;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.MultiSelectSetting;
import dev.client.yanderov.utils.client.Instance;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import net.minecraft.class_1294;

public class AutoSprint extends Module {
   public static int tickStop;
   private MultiSelectSetting settings = (new MultiSelectSetting("Ð˜Ð³Ð½Ð¾Ñ€Ð¸Ñ€Ð¾Ð²Ð°Ñ‚ÑŒ", "ÐÐµ Ð´Ð°ÐµÑ‚ ÑÐ¿Ñ€Ð¸Ð½Ñ‚Ð¸Ñ‚ÑŒÑÑ Ð¿Ñ€Ð¸ ÑÑ„Ñ„ÐµÐºÑ‚Ð°Ñ…")).value("Slowness", "Blindness");

   public static AutoSprint getInstance() {
      return (AutoSprint)Instance.get(AutoSprint.class);
   }

   public AutoSprint() {
      super("AutoSprint", "Auto Sprint", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.settings});
   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginMutation
   )
   public void onTick(TickEvent e) {
      boolean hasSlowness = mc.field_1724.method_6059(class_1294.field_5909);
      boolean hasBlindness = mc.field_1724.method_6059(class_1294.field_5919);
      boolean shouldCancelSprintDueToSlowness = hasSlowness && !this.settings.isSelected("Slowness");
      boolean shouldCancelSprintDueToBlindness = hasBlindness && !this.settings.isSelected("Blindness");
      boolean horizontal = mc.field_1724.field_5976 && !mc.field_1724.field_34927;
      boolean sneaking = mc.field_1724.method_5715() && !mc.field_1724.method_5681();
      if (tickStop <= 0 && !sneaking && !shouldCancelSprintDueToSlowness && !shouldCancelSprintDueToBlindness) {
         if (!horizontal && mc.field_1724.field_6250 > 0.0F && !mc.field_1690.field_1867.method_1434()) {
            mc.field_1724.method_5728(true);
         }
      } else {
         mc.field_1724.method_5728(false);
      }

      --tickStop;
   }
}

