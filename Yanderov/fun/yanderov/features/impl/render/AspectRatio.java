package fun.Yanderov.features.impl.render;

import fun.Yanderov.events.render.AspectRatioEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;

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

