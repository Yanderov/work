package dev.client.yanderov.features.module.setting.implement;

import dev.client.yanderov.features.module.setting.Setting;
import java.util.function.Supplier;

public class SliderSettings extends Setting {
   private float value;
   private float min;
   private float max;
   private boolean integer;

   public SliderSettings(String name, String description) {
      super(name, description);
   }

   public SliderSettings range(float min, float max) {
      this.min = min;
      this.max = max;
      return this;
   }

   public SliderSettings range(int min, int max) {
      this.min = (float)min;
      this.max = (float)max;
      this.integer = true;
      return this;
   }

   public int getInt() {
      return (int)this.value;
   }

   public SliderSettings visible(Supplier visible) {
      this.setVisible(visible);
      return this;
   }

   public float getValue() {
      return this.value;
   }

   public float getMin() {
      return this.min;
   }

   public float getMax() {
      return this.max;
   }

   public boolean isInteger() {
      return this.integer;
   }

   public SliderSettings setValue(float value) {
      this.value = value;
      return this;
   }

   public SliderSettings setMin(float min) {
      this.min = min;
      return this;
   }

   public SliderSettings setMax(float max) {
      this.max = max;
      return this;
   }

   public SliderSettings setInteger(boolean integer) {
      this.integer = integer;
      return this;
   }
}

