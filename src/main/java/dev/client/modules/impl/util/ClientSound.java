package dev.client.modules.impl.util;

import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import dev.client.modules.settings.impl.FloatSetting;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ClientSound extends Module {
   public final FloatSetting value = new FloatSetting().name("Value").value(15.0F).minValue(1.0F).maxValue(35.0F).incriment(1.0F);

   public ClientSound() {
      super(new ModuleBranding("ClientSound", Category.UTIL, "Проигрывает звук при переключении функции"));
      this.addSetting(this.value);
   }
}

