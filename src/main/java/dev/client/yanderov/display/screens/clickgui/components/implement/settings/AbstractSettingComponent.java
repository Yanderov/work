package dev.client.yanderov.display.screens.clickgui.components.implement.settings;

import dev.client.yanderov.display.screens.clickgui.components.AbstractComponent;
import dev.client.yanderov.features.module.setting.Setting;

public abstract class AbstractSettingComponent extends AbstractComponent {
   protected final Setting setting;

   public Setting getSetting() {
      return this.setting;
   }

   public AbstractSettingComponent(Setting setting) {
      this.setting = setting;
   }
}

