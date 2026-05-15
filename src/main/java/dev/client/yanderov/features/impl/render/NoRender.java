package dev.client.yanderov.features.impl.render;

import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.MultiSelectSetting;
import dev.client.yanderov.utils.client.Instance;

public class NoRender extends Module {
   public final MultiSelectSetting modeSetting = (new MultiSelectSetting("Ð­Ð»ÐµÐ¼ÐµÐ½Ñ‚Ñ‹", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ ÑÐ»ÐµÐ¼ÐµÐ½Ñ‚Ñ‹ Ð´Ð»Ñ Ð¸Ð³Ð½Ð¾Ñ€Ð¸Ñ€Ð¾Ð²Ð°Ð½Ð¸Ñ")).value("Fire", "Bad Effects", "Block Overlay", "Darkness", "Damage").selected("Fire", "Bad Effects", "Block Overlay", "Darkness", "Damage");

   public static NoRender getInstance() {
      return (NoRender)Instance.get(NoRender.class);
   }

   public NoRender() {
      super("NoRender", "No Render", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.modeSetting});
   }
}

