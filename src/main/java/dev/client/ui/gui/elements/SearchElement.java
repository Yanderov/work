package dev.client.ui.gui.elements;

import dev.client.managers.FontManager;
import dev.client.ui.gui.IElementable;
import dev.client.util.color.ColorUtil;
import dev.client.util.math.MouseUtil;
import dev.client.util.other.UtfUtil;
import dev.client.util.render.builders.Builder;
import dev.client.util.render.msdf.MsdfFont;
import dev.client.util.render.renderers.impl.BuiltText;
import java.awt.Color;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import org.joml.Matrix4f;

@Environment(EnvType.CLIENT)
public class SearchElement implements IElementable {
   private boolean select = false;
   private String value = "";
   private double animation;

   public void mouseClick(int click) {
      if (click == 0) {
         this.select = true;
      }
   }

   public void render(double x, double y, double width, double height, double mouseX, double mouseY, DrawContext drawContext) {
      Color color = ColorUtil.setAlpha(this.animation, Color.WHITE);
      Matrix4f matrix = drawContext.getMatrices().peek().getPositionMatrix();
      String endValue = this.value.isEmpty() && !this.select ? "Search" : (!this.select ? this.value : this.value + "_");
      if (!endValue.isEmpty()) {
         BuiltText text = (BuiltText)Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text(endValue).color(color).size(8.0F).thickness(0.05F).build();
         text.render(matrix, x + 8.0D, y + 10.0D);
      }
   }

   public void button(double x, double y, double width, double height, double mouseX, double mouseY, double xStart, double yStart, double xEnd, double yEnd, DrawContext drawContext, int click) {
      this.render(x, y, width, height, mouseX, mouseY, drawContext);
      if (MouseUtil.isHovered(x, y, width, height, mouseX, mouseY)) {
         this.mouseClick(click);
      }
   }

   public void charTyped(char chr, int modifiers) {
      if (this.select) {
         String typed = String.valueOf(chr);
         if (!typed.isEmpty() && this.value.length() < 23 && !UtfUtil.containsRussianLetter(typed)) {
            this.value = this.value + typed;
         }
      }
   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
      if (this.select) {
         if (keyCode == 259) { // Backspace
            if (this.value.length() > 0) {
               this.value = this.value.substring(0, this.value.length() - 1);
            }
         }
         if (keyCode == 32) { // Space
            if (this.value.length() < 23) {
               this.value = this.value + " ";
            }
         }
         if (keyCode == 257 || keyCode == 256) { // Enter or Esc
            this.select = false;
         }
      }
   }

   public void setAnimation(double animation) {
      this.animation = animation;
   }

   public String getValue() {
      return this.value;
   }

   public void setSelect(boolean select) {
      this.select = select;
   }

   // Removed redundant setters that caused spam
   public void setType(String type) {}
   public void setKey(int key) {}
}
