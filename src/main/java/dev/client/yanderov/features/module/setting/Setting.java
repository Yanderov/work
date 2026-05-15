package dev.client.yanderov.features.module.setting;

import java.util.function.Supplier;

public class Setting {
   private final String name;
   private String description;
   private Supplier visible;

   public Setting(String name) {
      this.name = name;
   }

   public Setting(String name, String description) {
      this.name = name;
      this.description = description;
   }

   public boolean isVisible() {
      return this.visible == null || (Boolean)this.visible.get();
   }

   public String getName() {
      return this.name;
   }

   public String getDescription() {
      return this.description;
   }

   public Supplier getVisible() {
      return this.visible;
   }

   public void setVisible(Supplier visible) {
      this.visible = visible;
   }
}

