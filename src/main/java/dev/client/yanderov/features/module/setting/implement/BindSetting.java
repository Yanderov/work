package dev.client.yanderov.features.module.setting.implement;

import dev.client.yanderov.features.module.setting.Setting;
import java.util.function.Supplier;

public class BindSetting extends Setting {
   private int key = -1;
   private int type = 1;

   public BindSetting(String name, String description) {
      super(name, description);
   }

   public BindSetting visible(Supplier visible) {
      this.setVisible(visible);
      return this;
   }

   public int getKey() {
      return this.key;
   }

   public int getType() {
      return this.type;
   }

   public BindSetting setKey(int key) {
      this.key = key;
      return this;
   }

   public BindSetting setType(int type) {
      this.type = type;
      return this;
   }
}

