package fun.Yanderov.display.screens.clickgui.components.implement.window.implement.settings;

import fun.Yanderov.display.screens.clickgui.components.implement.window.implement.AbstractBindWindow;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;

public class BindCheckboxWindow extends AbstractBindWindow {
   private final BooleanSetting setting;

   protected int getKey() {
      return this.setting.getKey();
   }

   protected void setKey(int key) {
      this.setting.setKey(key);
   }

   protected int getType() {
      return this.setting.getType();
   }

   protected void setType(int type) {
      this.setting.setType(type);
   }

   public BindCheckboxWindow(BooleanSetting setting) {
      this.setting = setting;
   }
}

