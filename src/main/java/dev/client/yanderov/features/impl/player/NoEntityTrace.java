package dev.client.yanderov.features.impl.player;

import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.utils.client.Instance;
import net.minecraft.class_1829;

public class NoEntityTrace extends Module {
   private final BooleanSetting noSword = (new BooleanSetting("Ð‘ÐµÐ· Ð¼ÐµÑ‡Ð°", "ÐÐµ Ñ€Ð°Ð±Ð¾Ñ‚Ð°ÐµÑ‚ Ñ Ð¼ÐµÑ‡Ð¾Ð¼ Ð² Ñ€ÑƒÐºÐµ")).setValue(true);

   public NoEntityTrace() {
      super("NoEntityTrace", "No Entity Trace", ModuleCategory.PLAYER);
      this.setup(new Setting[]{this.noSword});
   }

   public static NoEntityTrace getInstance() {
      return (NoEntityTrace)Instance.get(NoEntityTrace.class);
   }

   public boolean shouldIgnoreEntityTrace() {
      return this.isState() && (!(mc.field_1724.method_6047().method_7909() instanceof class_1829) || !this.noSword.isValue());
   }
}

