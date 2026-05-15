package dev.client.yanderov.display.screens.clickgui.components.implement.window.implement.settings.color.component;

import dev.client.yanderov.display.screens.clickgui.components.AbstractComponent;
import dev.client.yanderov.features.module.setting.implement.ColorSetting;
import dev.client.yanderov.utils.math.calc.Calculate;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;

public class ColorEditorComponent extends AbstractComponent {
   private final ColorSetting setting;

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      class_4587 matrix = context.method_51448();
      int displayValue = (int)(this.setting.getAlpha() * 100.0F);
   }

   public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
      float boxW = 22.0F;
      float boxH = 14.0F;
      float boxX = this.x + this.width - boxW - 6.0F;
      float boxY = this.y + 90.5F;
      if (Calculate.isHovered(mouseX, mouseY, (double)boxX, (double)boxY, (double)boxW, (double)boxH)) {
         this.setting.setAlpha(class_3532.method_15363((float)((double)this.setting.getAlpha() - amount * (double)2.0F / (double)100.0F), 0.0F, 1.0F));
      }

      return super.mouseScrolled(mouseX, mouseY, amount);
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      return super.mouseClicked(mouseX, mouseY, button);
   }

   public boolean mouseReleased(double mouseX, double mouseY, int button) {
      return super.mouseReleased(mouseX, mouseY, button);
   }

   public ColorEditorComponent(ColorSetting setting) {
      this.setting = setting;
   }
}

