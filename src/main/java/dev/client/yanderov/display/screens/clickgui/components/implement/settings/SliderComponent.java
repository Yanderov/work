package dev.client.yanderov.display.screens.clickgui.components.implement.settings;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.display.font.Fonts;
import dev.client.yanderov.utils.display.scissor.ScissorAssist;
import dev.client.yanderov.utils.display.shape.ShapeProperties;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import org.lwjgl.glfw.GLFW;

public class SliderComponent extends AbstractSettingComponent {
   public static final int SLIDER_WIDTH = 65;
   private final SliderSettings setting;
   private boolean dragging;
   private double animation;
   private int dragButton = -1;
   private boolean typing;
   private String inputText = "";

   public SliderComponent(SliderSettings setting) {
      super(setting);
      this.setting = setting;
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      class_4587 matrix = context.method_51448();
      this.height = 28.0F;
      if (this.dragging) {
         long handle = mc.method_22683().method_4490();
         if (GLFW.glfwGetMouseButton(handle, 0) == 0) {
            this.dragging = false;
            this.dragButton = -1;
         }
      }

      String displayValue = this.typing ? this.inputText : String.valueOf(this.setting.getValue());
      float valueW = Fonts.getSize(14, Fonts.Type.BOLD).getStringWidth(displayValue);
      float nameX = this.x + 8.0F;
      float nameY = this.y + 9.0F;
      float maxNameW = Math.max(0.0F, this.x + this.width - 9.0F - valueW - 6.0F - nameX);
      float nameWidth = Fonts.getSize(14, Fonts.Type.DEFAULT).getStringWidth(this.setting.getName());
      if (nameWidth > maxNameW) {
         ScissorAssist scissor = YanderovIntegration.getInstance().getScissorManager();
         scissor.push(matrix.method_23760().method_23761(), nameX, this.y + 3.0F, maxNameW, 14.0F);
         Fonts.getSize(14, Fonts.Type.DEFAULT).drawStringWithScroll(matrix, this.setting.getName(), (double)nameX, (double)nameY, maxNameW, (new Color(225, 225, 225, 225)).getRGB());
         scissor.pop();
      } else {
         Fonts.getSize(14, Fonts.Type.DEFAULT).drawString(matrix, this.setting.getName(), (double)nameX, (double)nameY, -2828575);
      }

      int valueColor = this.typing ? -1 : -9734690;
      float valueX = this.x + this.width - 9.0F - valueW;
      Fonts.getSize(14, Fonts.Type.BOLD).drawString(matrix, displayValue, (double)valueX, (double)nameY, valueColor);
      float sliderX = this.x + 8.0F;
      float sliderY = this.y + 22.0F;
      float sliderW = Math.max(40.0F, this.width - 17.0F);
      float storedPixels = sliderW * (this.setting.getValue() - this.setting.getMin()) / (this.setting.getMax() - this.setting.getMin());
      if (this.dragging) {
         float difference = class_3532.method_15363((float)mouseX - sliderX, 0.0F, sliderW);
         BigDecimal bd = BigDecimal.valueOf((double)(difference / sliderW * (this.setting.getMax() - this.setting.getMin()) + this.setting.getMin())).setScale(2, RoundingMode.HALF_UP);
         float value = difference == 0.0F ? this.setting.getMin() : bd.floatValue();
         if (this.setting.isInteger()) {
            value = (float)((int)value);
         }

         this.setting.setValue(value);
         this.animation = (double)difference;
      } else {
         this.animation = (double)storedPixels;
      }

      rectangle.render(ShapeProperties.create(matrix, (double)sliderX, (double)sliderY, (double)sliderW, (double)3.0F).round(1.0F).color(758006093).build());
      rectangle.render(ShapeProperties.create(matrix, (double)sliderX, (double)sliderY, (double)((float)this.animation), (double)3.0F).round(1.0F).color((new Color(78, 84, 148, 175)).getRGB()).build());
      float v = class_3532.method_15363((float)((double)sliderX + this.animation), sliderX, sliderX + sliderW);
      blur.render(ShapeProperties.create(matrix, (double)(v - 3.0F), (double)(sliderY - 1.0F), (double)5.0F, (double)5.0F).round(3.0F).softness(0.0F).color((new Color(255, 255, 255, 220)).getRGB()).build());
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      String current = this.typing ? this.inputText : String.valueOf(this.setting.getValue());
      float valueW = Fonts.getSize(14, Fonts.Type.BOLD).getStringWidth(current);
      float valueX = this.x + this.width - 9.0F - valueW;
      float valueY = this.y + 9.0F;
      float valueH = 10.0F;
      boolean overValue = mouseX >= (double)valueX && mouseX <= (double)(valueX + valueW) && mouseY >= (double)valueY && mouseY <= (double)(valueY + valueH);
      if (button == 0 && overValue) {
         this.typing = true;
         this.dragging = false;
         this.inputText = String.valueOf(this.setting.getValue());
         return true;
      } else {
         float sliderX = this.x + 8.0F;
         float sliderY = this.y + 22.0F;
         float sliderW = Math.max(40.0F, this.width - 17.0F);
         float storedPixels = sliderW * (this.setting.getValue() - this.setting.getMin()) / (this.setting.getMax() - this.setting.getMin());
         float knobX = sliderX + storedPixels;
         float knobW = 8.0F;
         float knobH = 8.0F;
         boolean overKnob = mouseX >= (double)(knobX - knobW / 2.0F) && mouseX <= (double)(knobX + knobW / 2.0F) && mouseY >= (double)(sliderY - knobH / 2.0F) && mouseY <= (double)(sliderY + knobH / 2.0F);
         boolean canDrag = !this.typing && button == 0 && overKnob;
         if (canDrag) {
            this.dragging = true;
            this.dragButton = button;
            return true;
         } else {
            return super.mouseClicked(mouseX, mouseY, button);
         }
      }
   }

   public boolean mouseReleased(double mouseX, double mouseY, int button) {
      if (this.dragButton != -1 && button == this.dragButton) {
         this.dragging = false;
         this.dragButton = -1;
         return true;
      } else {
         return super.mouseReleased(mouseX, mouseY, button);
      }
   }

   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      if (!this.typing) {
         return false;
      } else if (keyCode != 257 && keyCode != 335) {
         if (keyCode == 256) {
            this.typing = false;
            return true;
         } else if (keyCode == 259) {
            if (!this.inputText.isEmpty()) {
               this.inputText = this.inputText.substring(0, this.inputText.length() - 1);
            }

            return true;
         } else {
            return false;
         }
      } else {
         this.applyTypedValue();
         this.typing = false;
         return true;
      }
   }

   public boolean charTyped(char chr, int modifiers) {
      if (!this.typing) {
         return false;
      } else if ((chr < '0' || chr > '9') && chr != '.' && chr != ',') {
         return false;
      } else {
         if (chr == ',') {
            chr = '.';
         }

         this.inputText = this.inputText + chr;
         return true;
      }
   }

   private void applyTypedValue() {
      if (this.inputText != null && !this.inputText.isEmpty()) {
         try {
            float v = Float.parseFloat(this.inputText);
            v = Math.max(this.setting.getMin(), Math.min(this.setting.getMax(), v));
            if (this.setting.isInteger()) {
               v = (float)((int)v);
            }

            this.setting.setValue(v);
         } catch (NumberFormatException var2) {
         }

      }
   }
}

