package fun.Yanderov.features.impl.render;

import fun.Yanderov.events.render.FogEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.MultiSelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.color.ColorAssist;

public class WorldTweaks extends Module {
   public final MultiSelectSetting modeSetting = (new MultiSelectSetting("ÐÐ°ÑÑ‚Ñ€Ð¾Ð¹ÐºÐ¸ Ð¼Ð¸Ñ€Ð°", "ÐŸÐ¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ Ð½Ð°ÑÑ‚Ñ€Ð¾Ð¸Ñ‚ÑŒ Ð¼Ð¸Ñ€")).value("Bright", "Time", "Fog");
   public final SliderSettings brightSetting = (new SliderSettings("Ð¯Ñ€ÐºÐ¾ÑÑ‚ÑŒ", "Ð£ÑÑ‚Ð°Ð½Ð°Ð²Ð»Ð¸Ð²Ð°ÐµÑ‚ Ð·Ð½Ð°Ñ‡ÐµÐ½Ð¸Ðµ Ð¼Ð°ÐºÑÐ¸Ð¼Ð°Ð»ÑŒÐ½Ð¾Ð¹ ÑÑ€ÐºÐ¾ÑÑ‚Ð¸")).setValue(1.0F).range(0.0F, 1.0F).visible(() -> this.modeSetting.isSelected("Bright"));
   public final SliderSettings timeSetting = (new SliderSettings("Ð’Ñ€ÐµÐ¼Ñ", "Ð£ÑÑ‚Ð°Ð½Ð°Ð²Ð»Ð¸Ð²Ð°ÐµÑ‚ Ð·Ð½Ð°Ñ‡ÐµÐ½Ð¸Ðµ Ð²Ñ€ÐµÐ¼ÐµÐ½Ð¸")).setValue(12.0F).range(0, 24).visible(() -> this.modeSetting.isSelected("Time"));
   public final SliderSettings distanceSetting = (new SliderSettings("Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ñ‚ÑƒÐ¼Ð°Ð½Ð°", "Ð£ÑÑ‚Ð°Ð½Ð°Ð²Ð»Ð¸Ð²Ð°ÐµÑ‚ Ñ€Ð°ÑÑÑ‚Ð¾ÑÐ½Ð¸Ðµ Ñ‚ÑƒÐ¼Ð°Ð½Ð°")).setValue(100.0F).range(20, 200).visible(() -> this.modeSetting.isSelected("Fog"));

   public static WorldTweaks getInstance() {
      return (WorldTweaks)Instance.get(WorldTweaks.class);
   }

   public WorldTweaks() {
      super("WorldTweaks", "World Tweaks", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.modeSetting, this.brightSetting, this.timeSetting, this.distanceSetting});
   }

   public void deactivate() {
      super.deactivate();
   }

   @EventHandler
   public void onFog(FogEvent e) {
      if (this.modeSetting.isSelected("Fog")) {
         e.setDistance(this.distanceSetting.getValue());
         e.setColor(ColorAssist.getClientColor());
         e.cancel();
      }

   }
}

