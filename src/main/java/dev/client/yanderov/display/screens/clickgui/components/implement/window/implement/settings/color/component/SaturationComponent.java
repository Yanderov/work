package dev.client.yanderov.display.screens.clickgui.components.implement.window.implement.settings.color.component;

import dev.client.yanderov.display.screens.clickgui.components.AbstractComponent;
import dev.client.yanderov.features.module.setting.implement.ColorSetting;
import dev.client.yanderov.utils.display.shape.ShapeProperties;
import dev.client.yanderov.utils.math.calc.Calculate;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;

public class SaturationComponent extends AbstractComponent {
   private final ColorSetting setting;
   private boolean saturationDragging;
   private float X;
   private float Y;
   private float W;
   private float H;

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      class_4587 matrix = context.method_51448();
      this.X = this.x + 6.0F;
      this.Y = this.y + 73.5F;
      this.W = Math.max(0.0F, this.width - 12.0F);
      this.H = 4.0F;
      float clampedX = class_3532.method_15363(this.X + this.W * this.setting.getHue(), this.X, this.X + this.W - 4.0F);
      float min = class_3532.method_15363(((float)mouseX - this.X) / this.W, 0.0F, 1.0F);
      image.setTexture("textures/gui/sliderhue.png").render(ShapeProperties.create(matrix, (double)this.X, (double)this.Y + (double)0.5F, (double)this.W, (double)(this.H - 1.0F)).build());
      rectangle.render(ShapeProperties.create(matrix, (double)clampedX, (double)this.Y, (double)this.H, (double)this.H).round(this.H / 2.0F).thickness(3.0F).color(16777215).outlineColor(-1).build());
      if (this.saturationDragging) {
         this.setting.setHue(min);
      }

   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      this.saturationDragging = button == 0 && Calculate.isHovered(mouseX, mouseY, (double)this.X, (double)this.Y, (double)this.W, (double)this.H);
      return super.mouseClicked(mouseX, mouseY, button);
   }

   public boolean mouseReleased(double mouseX, double mouseY, int button) {
      this.saturationDragging = false;
      return super.mouseReleased(mouseX, mouseY, button);
   }

   public SaturationComponent(ColorSetting setting) {
      this.setting = setting;
   }
}

