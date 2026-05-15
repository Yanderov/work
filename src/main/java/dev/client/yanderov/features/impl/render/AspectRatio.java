package dev.client.yanderov.features.impl.render;

import dev.client.yanderov.events.render.AspectRatioEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;

public class AspectRatio extends Module {
   private final SliderSettings ratioSetting = (new SliderSettings("Ð¡Ð¾Ð¾Ñ‚Ð½Ð¾ÑˆÐµÐ½Ð¸Ðµ", "ÐÐ°ÑÑ‚Ñ€Ð¾Ð¹ÐºÐ° Ð·Ð½Ð°Ñ‡ÐµÐ½Ð¸Ñ ÑÐ¾Ð¾Ñ‚Ð½Ð¾ÑˆÐµÐ½Ð¸Ñ ÑÑ‚Ð¾Ñ€Ð¾Ð½")).setValue(1.0F).range(0.1F, 2.0F);

   public AspectRatio() {
      super("AspectRatio", "Aspect Ratio", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.ratioSetting});
   }

   @EventHandler
   public void onAspectRatio(AspectRatioEvent e) {
      e.setRatio(this.ratioSetting.getValue());
      e.cancel();
   }
}

