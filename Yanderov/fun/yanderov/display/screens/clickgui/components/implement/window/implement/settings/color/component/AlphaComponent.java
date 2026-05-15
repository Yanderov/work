package fun.Yanderov.display.screens.clickgui.components.implement.window.implement.settings.color.component;

import fun.Yanderov.display.screens.clickgui.components.AbstractComponent;
import fun.Yanderov.features.module.setting.implement.ColorSetting;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import fun.Yanderov.utils.math.calc.Calculate;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;

public class AlphaComponent extends AbstractComponent {
   private final ColorSetting setting;
   private boolean alphaDragging;
   private float X;
   private float Y;
   private float W;
   private float H;

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      class_4587 matrix = context.method_51448();
      this.X = this.x + 6.0F;
      this.Y = this.y + 83.5F;
      this.W = Math.max(0.0F, this.width - 12.0F);
      this.H = 4.0F;
      float clampedX = class_3532.method_15363(this.X + this.W * this.setting.getAlpha(), this.X, this.X + this.W - 4.0F);
      float min = class_3532.method_15363(((float)mouseX - this.X) / this.W, 0.0F, 1.0F);
      image.setTexture("textures/gui/alphabar.png").render(ShapeProperties.create(matrix, (double)this.X, (double)this.Y, (double)this.W, (double)this.H).round(4.0F).build());
      rectangle.render(ShapeProperties.create(matrix, (double)this.X, (double)this.Y - 0.2, (double)this.W + (double)0.5F, (double)this.H).round(1.5F).color(Integer.MIN_VALUE, 134217728, this.setting.getColorWithAlpha(), this.setting.getColorWithAlpha()).build());
      rectangle.render(ShapeProperties.create(matrix, (double)clampedX, (double)this.Y, (double)this.H, (double)this.H).round(this.H / 2.0F).thickness(3.0F).color(16777215).outlineColor(-1).build());
      if (this.alphaDragging) {
         this.setting.setAlpha(min);
      }

   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      this.alphaDragging = button == 0 && Calculate.isHovered(mouseX, mouseY, (double)this.X, (double)this.Y, (double)this.W, (double)this.H);
      return super.mouseClicked(mouseX, mouseY, button);
   }

   public boolean mouseReleased(double mouseX, double mouseY, int button) {
      this.alphaDragging = false;
      return super.mouseReleased(mouseX, mouseY, button);
   }

   public AlphaComponent(ColorSetting setting) {
      this.setting = setting;
   }
}

