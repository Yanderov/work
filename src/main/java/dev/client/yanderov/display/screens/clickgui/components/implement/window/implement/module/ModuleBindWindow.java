package dev.client.yanderov.display.screens.clickgui.components.implement.window.implement.module;

import dev.client.yanderov.display.screens.clickgui.components.implement.window.implement.AbstractBindWindow;
import dev.client.yanderov.features.module.Module;

public class ModuleBindWindow extends AbstractBindWindow {
   private final Module module;

   protected int getKey() {
      return this.module.getKey();
   }

   protected void setKey(int key) {
      this.module.setKey(key);
   }

   protected int getType() {
      return this.module.getType();
   }

   protected void setType(int type) {
      this.module.setType(type);
   }

   public ModuleBindWindow(Module module) {
      this.module = module;
   }

   public Module getModule() {
      return this.module;
   }
}

