package fun.Yanderov.display.screens.clickgui.components.implement.other;

import fun.Yanderov.display.screens.clickgui.components.AbstractComponent;
import fun.Yanderov.utils.display.font.Fonts;
import fun.Yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import net.minecraft.class_332;

public class SettingComponent extends AbstractComponent {
   private Runnable runnable;

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      Fonts.getSize(15, Fonts.Type.GUIICONS).drawString(context.method_51448(), "B", (double)(this.x - 5.0F), (double)(this.y + 6.0F), (new Color(128, 128, 128, 255)).getRGB());
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      if (Calculate.isHovered(mouseX, mouseY, (double)(this.x - 5.0F), (double)(this.y + 6.0F), (double)7.0F, (double)7.0F) && button == 0) {
         this.runnable.run();
      }

      return super.mouseClicked(mouseX, mouseY, button);
   }

   public SettingComponent setRunnable(Runnable runnable) {
      this.runnable = runnable;
      return this;
   }
}

