package fun.Yanderov.features.impl.render;

import fun.Yanderov.events.render.EntityColorEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.color.ColorAssist;

public class SeeInvisible extends Module {
   private final SliderSettings alphaSetting = (new SliderSettings("ÐŸÑ€Ð¾Ð·Ñ€Ð°Ñ‡Ð½Ð¾ÑÑ‚ÑŒ", "ÐŸÑ€Ð¾Ð·Ñ€Ð°Ñ‡Ð½Ð¾ÑÑ‚ÑŒ Ð¸Ð³Ñ€Ð¾ÐºÐ°")).setValue(0.5F).range(0.1F, 1.0F);

   public SeeInvisible() {
      super("SeeInvisible", "See Invisible", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.alphaSetting});
   }

   @EventHandler
   public void onEntityColor(EntityColorEvent e) {
      e.setColor(ColorAssist.multAlpha(e.getColor(), this.alphaSetting.getValue()));
      e.cancel();
   }
}

