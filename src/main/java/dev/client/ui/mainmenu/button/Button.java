package dev.client.ui.mainmenu.button;

import dev.client.managers.FontManager;
import dev.client.util.animations.Animation;
import dev.client.util.animations.Direction;
import dev.client.util.animations.impl.EaseBackIn;
import dev.client.util.color.ColorUtil;
import dev.client.util.math.MouseUtil;
import dev.client.util.render.builders.Builder;
import dev.client.util.render.builders.states.QuadColorState;
import dev.client.util.render.builders.states.QuadRadiusState;
import dev.client.util.render.builders.states.SizeState;
import dev.client.util.render.msdf.MsdfFont;
import dev.client.util.render.renderers.impl.BuiltBorder;
import dev.client.util.render.renderers.impl.BuiltRectangle;
import dev.client.util.render.renderers.impl.BuiltText;
import java.awt.Color;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.joml.Matrix4f;

@Environment(EnvType.CLIENT)
public class Button {
   private double x;
   private double y;
   private double width = 140.0D;
   private double height = 28.0D;
   private Runnable run;
   private String name;
   private String icon;
   private Animation animation;

   public Button(String name, String icon, Runnable run) {
      this.run = run;
      this.name = name;
      this.icon = icon;
      this.animation = new EaseBackIn(250, 1.0D, 0.1F, Direction.FORWARDS);
   }

   public void isHovered(double mouseX, double mouseY) {
      if (MouseUtil.isHovered(this.x, this.y, this.width, this.height, mouseX, mouseY)) {
         this.animation.setDirection(Direction.FORWARDS);
      } else {
         this.animation.setDirection(Direction.BACKWARDS);
      }

   }

   public void onClick() {
      this.run.run();
   }

   public void render(Matrix4f matrix4f) {
      Builder.rectangle().size(new SizeState(this.width, this.height)).color(new QuadColorState(Color.black)).radius(new QuadRadiusState(8.6)).smoothness(1.15F).build().render(matrix4f, this.x, this.y);
      Builder.border().size(new SizeState(this.width, this.height)).color(new QuadColorState(Color.black)).radius(new QuadRadiusState(8.6)).thickness(0.01F).smoothness(0.6F, 0.6F).build().render(matrix4f, this.x, this.y);
      float widthIcon = FontManager.MAINMENU.get().getWidth(this.icon, 8.0F) + 6.0F;
      Builder.text().font(FontManager.MAINMENU.get()).text(this.icon).color(Color.WHITE).size(8.0F).thickness(0.05F).build().render(matrix4f, this.x + this.width / 2.0D - (double)(FontManager.SUISSEINTREGULAR.get().getWidth(this.getName(), 8.0F) / 2.0F) - (double)(widthIcon / 2.0F), this.y + 10.0D);
      Builder.text().font(FontManager.SUISSEINTREGULAR.get()).text(this.getName()).color(new Color(255, 255, 255, 184)).size(8.0F).thickness(0.05F).build().render(matrix4f, this.x + this.width / 2.0D - (double)(FontManager.SUISSEINTREGULAR.get().getWidth(this.getName(), 8.0F) / 2.0F) + (double)(widthIcon / 2.0F), this.y + 9.0D);
      if (!this.animation.finished(Direction.BACKWARDS)) {
         Builder.rectangle().size(new SizeState(this.width, this.height)).color(new QuadColorState(ColorUtil.setAlpha(this.animation.getOutput(), Color.black))).radius(new QuadRadiusState(8.6)).smoothness(1.15F).build().render(matrix4f, this.x, this.y);
         Builder.border().size(new SizeState(this.width, this.height)).color(new QuadColorState(ColorUtil.setAlpha(this.animation.getOutput(), Color.black))).radius(new QuadRadiusState(8.6)).thickness(0.01F).smoothness(0.6F, 0.6F).build().render(matrix4f, this.x, this.y);
         Builder.text().font(FontManager.MAINMENU.get()).text(this.icon).color(ColorUtil.setAlpha(this.animation.getOutput(), Color.WHITE)).size(8.0F).thickness(0.05F).build().render(matrix4f, this.x + this.width / 2.0D - (double)(FontManager.SUISSEINTREGULAR.get().getWidth(this.getName(), 8.0F) / 2.0F) - (double)(widthIcon / 2.0F), this.y + 10.0D);
         Builder.text().font(FontManager.SUISSEINTREGULAR.get()).text(this.getName()).color(ColorUtil.setAlpha(this.animation.getOutput(), Color.WHITE)).size(8.0F).thickness(0.05F).build().render(matrix4f, this.x + this.width / 2.0D - (double)(FontManager.SUISSEINTREGULAR.get().getWidth(this.getName(), 8.0F) / 2.0F) + (double)(widthIcon / 2.0F), this.y + 9.0D);
      }

   }

   public void updatePos(double x, double y) {
      this.x = x;
      this.y = y;
   }

   public double getWidth() {
      return this.width;
   }

   public double getHeight() {
      return this.height;
   }

   public String getName() {
      return this.name;
   }

   public double getX() {
      return this.x;
   }

   public double getY() {
      return this.y;
   }
}
