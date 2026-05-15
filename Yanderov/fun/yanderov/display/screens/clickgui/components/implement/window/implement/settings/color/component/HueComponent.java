package fun.Yanderov.display.screens.clickgui.components.implement.window.implement.settings.color.component;

import fun.Yanderov.display.screens.clickgui.components.AbstractComponent;
import fun.Yanderov.features.module.setting.implement.ColorSetting;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import fun.Yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;

public class HueComponent extends AbstractComponent {
   private final ColorSetting setting;
   private boolean hueDragging;
   private float X;
   private float Y;
   private float W;
   private float H;

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      class_4587 matrix = context.method_51448();
      this.X = this.x + 6.0F;
      this.Y = this.y + 18.5F;
      this.W = Math.max(0.0F, this.width - 12.0F);
      this.H = 50.0F;
      int[] color = new int[]{-16777216, -1, -16777216, Color.HSBtoRGB(this.setting.getHue(), 1.0F, 1.0F)};
      rectangle.render(ShapeProperties.create(matrix, (double)this.X, (double)this.Y, (double)this.W, (double)this.H).round(2.0F).color(color).build());
      float clampedX = class_3532.method_15363(this.X + this.W * this.setting.getSaturation(), this.X, this.X + this.W - 5.0F);
      float clampedY = class_3532.method_15363(this.Y + this.H * (1.0F - this.setting.getBrightness()), this.Y, this.Y + this.H - 5.0F);
      rectangle.render(ShapeProperties.create(matrix, (double)clampedX, (double)clampedY, (double)5.0F, (double)5.0F).round(2.5F).softness(1.0F).thickness(3.0F).color(16777215).outlineColor(-1).build());
      float min = class_3532.method_15363(((float)mouseX - this.X) / this.W, 0.0F, 1.0F);
      if (this.hueDragging) {
         this.setting.setBrightness(class_3532.method_15363(1.0F - ((float)mouseY - this.Y) / this.H, 0.0F, 1.0F));
         this.setting.setSaturation(min);
      }

   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      this.hueDragging = button == 0 && Calculate.isHovered(mouseX, mouseY, (double)this.X, (double)this.Y, (double)this.W, (double)this.H);
      return super.mouseClicked(mouseX, mouseY, button);
   }

   public boolean mouseReleased(double mouseX, double mouseY, int button) {
      this.hueDragging = false;
      return super.mouseReleased(mouseX, mouseY, button);
   }

   public HueComponent(ColorSetting setting) {
      this.setting = setting;
   }
}

