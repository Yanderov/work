package dev.client.modules.impl.util;

import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import dev.client.modules.settings.impl.BooleanSetting;
import dev.client.modules.settings.impl.MultiBoxSetting;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class NoRender extends Module {
   public final MultiBoxSetting options = new MultiBoxSetting().name("Options").booleanSettings(new BooleanSetting().name("Fire").value(false), new BooleanSetting().name("Water").value(false), new BooleanSetting().name("Wall").value(false), new BooleanSetting().name("NoHurtCum").value(false), new BooleanSetting().name("Nausea").value(false), new BooleanSetting().name("Darkness").value(false));

   public NoRender() {
      super(new ModuleBranding("NoRender", Category.UTIL, "Убирает отрисовку выбранных объектов"));
      this.addSetting(this.options);
   }
}

