package fun.Yanderov.display.screens.clickgui.components.implement.other;

import fun.Yanderov.common.animation.Animation;
import fun.Yanderov.common.animation.Direction;
import fun.Yanderov.common.animation.implement.Decelerate;
import fun.Yanderov.display.screens.clickgui.components.AbstractComponent;
import fun.Yanderov.utils.display.font.Fonts;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import fun.Yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class CheckComponent extends AbstractComponent {
   private boolean state;
   private Runnable runnable;
   private final Animation alphaAnimation = (new Decelerate()).setMs(180).setValue((double)255.0F);
   private final Animation stencilAnimation = (new Decelerate()).setMs(200).setValue((double)8.0F);
   private final Animation sliderAnimation = (new Decelerate()).setMs(225).setValue((double)8.0F);

   public CheckComponent position(float x, float y) {
      this.x = x;
      this.y = y;
      return this;
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      class_4587 matrix = context.method_51448();
      this.alphaAnimation.setDirection(this.state ? Direction.FORWARDS : Direction.BACKWARDS);
      int outline = (new Color(170, 170, 179, 255)).getRGB();
      int fill = (new Color(78, 84, 148, 255)).getRGB();
      int opacity = Math.max(0, Math.min(255, this.alphaAnimation.getOutput().intValue()));
      rectangle.render(ShapeProperties.create(matrix, (double)this.x, (double)this.y, (double)12.0F, (double)12.0F).round(3.0F).thickness(1.5F).softness(0.0F).outlineColor(outline).color((new Color(0, 0, 0, 0)).getRGB()).build());
      if (opacity > 1) {
         rectangle.render(ShapeProperties.create(matrix, (double)(this.x + 2.0F), (double)(this.y + 2.0F), (double)8.0F, (double)8.0F).round(2.5F).softness(0.0F).color(Calculate.applyOpacity(fill, (float)opacity)).build());
         String check = "âœ“";
         if (Fonts.getSize(16, Fonts.Type.ICONS).getStringWidth(check) > 0.0F) {
            Fonts.getSize(16, Fonts.Type.ICONS).drawString(matrix, check, (double)(this.x + 4.0F), (double)(this.y + 3.5F), Calculate.applyOpacity(-1, (float)opacity));
         } else {
            Fonts.getSize(13, Fonts.Type.DEFAULT).drawString(matrix, check, (double)(this.x + 3.25F), (double)(this.y + 9.0F), Calculate.applyOpacity(-1, (float)opacity));
         }
      }

   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      if (Calculate.isHovered(mouseX, mouseY, (double)(this.x - 2.0F), (double)(this.y - 2.0F), (double)16.0F, (double)16.0F) && button == 0) {
         this.runnable.run();
      }

      return super.mouseClicked(mouseX, mouseY, button);
   }

   public CheckComponent setState(boolean state) {
      this.state = state;
      return this;
   }

   public CheckComponent setRunnable(Runnable runnable) {
      this.runnable = runnable;
      return this;
   }
}

