package dev.client.modules.impl.render;

import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import dev.client.modules.settings.impl.FloatSetting;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class SmoothCamera extends Module {
   private final FloatSetting delay = new FloatSetting().name("Delay").minValue(5.0F).maxValue(50.0F).incriment(0.5F).value(5.0F);

   public SmoothCamera() {
      super(new ModuleBranding("SmoothCamera", Category.RENDER, "Добавляет камере от первого лица плавность движения"));
      this.addSetting(this.delay);
   }

   public FloatSetting getDelay() {
      return this.delay;
   }
}

