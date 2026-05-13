package dev.client.modules.impl.movement;

import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.PlayerModel;
import dev.client.modules.settings.impl.FloatSetting;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ElytraBooster extends Module {
   public final FloatSetting boostValue = new FloatSetting().name("Value").value(1.5F).minValue(1.5F).maxValue(5.0F).incriment(0.01F);

   public ElytraBooster() {
      super(new PlayerModel("ElytraBooster", Category.MOVEMENT, "Ускоряет полет игрока на элитре"));
      this.addSetting(this.boostValue);
   }
}

