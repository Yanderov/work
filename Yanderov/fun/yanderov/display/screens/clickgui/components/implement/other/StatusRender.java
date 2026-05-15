package fun.Yanderov.display.screens.clickgui.components.implement.other;

import fun.Yanderov.common.animation.Animation;
import fun.Yanderov.common.animation.Direction;
import fun.Yanderov.common.animation.implement.Decelerate;
import fun.Yanderov.display.screens.clickgui.components.AbstractComponent;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import fun.Yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class StatusRender extends AbstractComponent {
   private boolean state;
   private Runnable runnable;
   private float alphaMultiplier = 1.0F;
   private final Animation alphaAnimation = (new Decelerate()).setMs(400).setValue((double)100.0F);
   private final Animation stencilAnimation = (new Decelerate()).setMs(200).setValue((double)8.0F);
   private final Animation sliderAnimation = (new Decelerate()).setMs(225).setValue((double)8.0F);

   public StatusRender() {
      this.alphaAnimation.setDirection(this.state ? Direction.FORWARDS : Direction.BACKWARDS);
      this.stencilAnimation.setDirection(this.state ? Direction.FORWARDS : Direction.BACKWARDS);
      this.sliderAnimation.setDirection(this.state ? Direction.FORWARDS : Direction.BACKWARDS);
      this.alphaAnimation.reset();
      this.stencilAnimation.reset();
      this.sliderAnimation.reset();
   }

   public StatusRender position(float x, float y) {
      this.x = x - 8.0F;
      this.y = y;
      return this;
   }

   public StatusRender setAlphaMultiplier(float alphaMultiplier) {
      this.alphaMultiplier = alphaMultiplier;
      return this;
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      class_4587 matrix = context.method_51448();
      this.alphaAnimation.setDirection(this.state ? Direction.FORWARDS : Direction.BACKWARDS);
      this.stencilAnimation.setDirection(this.state ? Direction.FORWARDS : Direction.BACKWARDS);
      this.sliderAnimation.setDirection(this.state ? Direction.FORWARDS : Direction.BACKWARDS);
      int stateColor = (new Color(128, 128, 128, 255)).getRGB();
      int opacity = (int)((float)this.alphaAnimation.getOutput().intValue() * this.alphaMultiplier);
      float sliderX = this.x + this.sliderAnimation.getOutput().floatValue();
      int baseAlpha = (int)(255.0F * this.alphaMultiplier);
      int bgAlpha = (int)(40.0F * this.alphaMultiplier);
      rectangle.render(ShapeProperties.create(matrix, (double)this.x, (double)this.y, (double)16.0F, (double)8.0F).round(4.0F).thickness(0.0F).softness(1.0F).outlineColor((new Color(128, 128, 128, baseAlpha)).getRGB()).color((new Color(128, 128, 128, bgAlpha)).getRGB()).build());
      rectangle.render(ShapeProperties.create(matrix, (double)this.x, (double)this.y, (double)16.0F, (double)8.0F).round(4.0F).thickness(0.0F).softness(1.0F).outlineColor((new Color(128, 128, 128, baseAlpha)).getRGB()).color(Calculate.applyOpacity(stateColor, (float)opacity)).build());
      rectangle.render(ShapeProperties.create(matrix, (double)(sliderX - 0.5F), (double)(this.y - 0.5F), (double)9.0F, (double)9.0F).round(4.5F).thickness(2.0F).softness(1.0F).outlineColor((new Color(155, 155, 165, baseAlpha)).getRGB()).color((new Color(61, 67, 71, baseAlpha)).getRGB(), (new Color(71, 77, 81, baseAlpha)).getRGB(), (new Color(81, 87, 91, baseAlpha)).getRGB(), (new Color(91, 97, 101, baseAlpha)).getRGB()).build());
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      if (Calculate.isHovered(mouseX, mouseY, (double)this.x, (double)this.y, (double)16.0F, (double)8.0F) && button == 0) {
         this.state = !this.state;
         this.runnable.run();
      }

      return super.mouseClicked(mouseX, mouseY, button);
   }

   public StatusRender setState(boolean state) {
      this.state = state;
      return this;
   }

   public StatusRender setRunnable(Runnable runnable) {
      this.runnable = runnable;
      return this;
   }
}

