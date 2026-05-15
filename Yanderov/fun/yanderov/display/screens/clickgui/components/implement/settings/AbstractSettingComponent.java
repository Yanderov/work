package fun.Yanderov.display.screens.clickgui.components.implement.settings;

import fun.Yanderov.display.screens.clickgui.components.AbstractComponent;
import fun.Yanderov.features.module.setting.Setting;

public abstract class AbstractSettingComponent extends AbstractComponent {
   protected final Setting setting;

   public Setting getSetting() {
      return this.setting;
   }

   public AbstractSettingComponent(Setting setting) {
      this.setting = setting;
   }
}

