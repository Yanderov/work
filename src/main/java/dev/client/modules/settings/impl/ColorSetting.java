package dev.client.modules.settings.impl;

import dev.client.modules.settings.Setting;
import java.awt.Color;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ColorSetting extends Setting<ColorSetting> {
   private FloatSetting bright = new FloatSetting().name("Bright").minValue(0.0F).maxValue(1.0F).value(1.0F);
   private boolean isHovered = false;
   private FloatSetting alpha = new FloatSetting().name("Alpha").minValue(0.0F).maxValue(255.0F).value(255.0F);
   private Color color;

   public ColorSetting color(Color color) {
      this.color = color;
      this.alpha.setValue((float)color.getAlpha());
      return this;
   }

   public boolean isHovered() {
      return this.isHovered;
   }

   public void setHovered(boolean hovered) {
      this.isHovered = hovered;
   }

   public Color getColor() {
      if (this.color == null) return Color.WHITE;
      if (this.alpha == null) return this.color;
      return new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), (int)this.alpha.getValue());
   }

   public void setColor(Color color) {
      this.color = color;
      if (this.alpha != null) {
          this.alpha.setValue((float)color.getAlpha());
      }
   }

   public FloatSetting getAlpha() {
      return this.alpha;
   }

   public Color getDefColor() {
      return this.color;
   }

   public FloatSetting getBright() {
      return this.bright;
   }
}
