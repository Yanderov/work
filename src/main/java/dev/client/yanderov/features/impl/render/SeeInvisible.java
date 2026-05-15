package dev.client.yanderov.features.impl.render;

import dev.client.yanderov.events.render.EntityColorEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.color.ColorAssist;

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

