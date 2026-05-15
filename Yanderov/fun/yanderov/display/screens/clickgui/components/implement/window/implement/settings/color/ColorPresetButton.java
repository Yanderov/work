package fun.Yanderov.display.screens.clickgui.components.implement.window.implement.settings.color;

import fun.Yanderov.display.screens.clickgui.components.AbstractComponent;
import fun.Yanderov.features.module.setting.implement.ColorSetting;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import fun.Yanderov.utils.math.calc.Calculate;
import net.minecraft.class_332;

public class ColorPresetButton extends AbstractComponent {
   private final ColorSetting setting;
   private final int color;

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      rectangle.render(ShapeProperties.create(context.method_51448(), (double)this.x, (double)this.y, (double)8.0F, (double)8.0F).round(2.0F).color(this.color).build());
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      if (Calculate.isHovered(mouseX, mouseY, (double)this.x, (double)this.y, (double)8.0F, (double)8.0F) && button == 0) {
         this.setting.setColor(this.color);
      }

      return super.mouseClicked(mouseX, mouseY, button);
   }

   public ColorPresetButton(ColorSetting setting, int color) {
      this.setting = setting;
      this.color = color;
   }
}

