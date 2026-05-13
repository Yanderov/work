package dev.client.ui.gui.elements;

import dev.client.WildClient;
import dev.client.managers.FontManager;
import dev.client.modules.settings.impl.BooleanSetting;
import dev.client.modules.settings.impl.MultiBoxSetting;
import dev.client.ui.gui.IElementable;
import dev.client.util.animations.Direction;
import dev.client.util.color.ColorUtil;
import dev.client.util.math.MouseUtil;
import dev.client.util.render.builders.Builder;
import dev.client.util.render.builders.states.QuadColorState;
import dev.client.util.render.builders.states.SizeState;
import dev.client.util.render.renderers.impl.BuiltBorder;
import dev.client.util.render.renderers.impl.BuiltRectangle;
import dev.client.util.render.renderers.impl.BuiltText;
import java.awt.Color;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import org.joml.Matrix4f;
import dev.client.util.render.builders.states.QuadRadiusState;

@Environment(EnvType.CLIENT)
public class BooleanElement implements IElementable {
   private BooleanSetting booleanSetting;
   private double animation;
   private double animationEnabled;

   public void mouseClick(int click) {
   }

   public void render(double x, double y, double width, double height, double mouseX, double mouseY, DrawContext drawContext) {
      Matrix4f matrix = drawContext.getMatrices().peek().getPositionMatrix();
      boolean animationFinished = this.animationEnabled > 0.0D;
      Color colorText = ColorUtil.setAlpha(this.animation, ColorUtil.colorAlpha(Color.WHITE, (int)(122.4 + (animationFinished ? 132.6 * this.animationEnabled : 0.0D))));
      BuiltText text = (BuiltText)Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text(this.booleanSetting.getName()).color(colorText).size(7.5F).thickness(0.05F).build();
      text.render(matrix, x, y + 2.5D);
      
      BuiltRectangle rectangle = Builder.rectangle().size(new SizeState(8.5D, 8.5D)).color(new QuadColorState(ColorUtil.setAlpha(this.animation, Color.black))).radius(new QuadRadiusState(2.0F)).smoothness(1.15F).build();
      rectangle.render(matrix, x + width - 8.5D, y + 2.5D);
      
      BuiltBorder border = (BuiltBorder)Builder.border().size(new SizeState(8.5D, 8.5D)).color(new QuadColorState(ColorUtil.setAlpha(this.animation, Color.black))).radius(new QuadRadiusState(2.0F)).thickness(0.01F).smoothness(0.65F, 0.65F).build();
      border.render(matrix, x + width - 8.5D, y + 2.5D);
      
      if (!this.booleanSetting.getAnimation().finished(Direction.BACKWARDS)) {
         rectangle = Builder.rectangle().size(new SizeState(8.5D, 8.5D)).color(new QuadColorState(ColorUtil.setAlpha(this.animation * this.booleanSetting.getAnimation().getOutput(), Color.white))).radius(new QuadRadiusState(2.0F)).smoothness(1.15F).build();
         rectangle.render(matrix, x + width - 8.5D, y + 2.5D);
         
         text = (BuiltText)Builder.text().font(FontManager.WILD.get()).text("L").color(ColorUtil.setAlpha(this.animation * this.booleanSetting.getAnimation().getOutput(), Color.black)).size(4.25F).thickness(0.05F).build();
         text.render(matrix, x + width - 6.9, y + 4.25D);
         
         if (animationFinished) {
            text = (BuiltText)Builder.text().font(FontManager.WILD.get()).text("L").color(ColorUtil.setAlpha(this.animation * this.animationEnabled * this.booleanSetting.getAnimation().getOutput(), Color.black)).size(4.25F).thickness(0.05F).build();
            text.render(matrix, x + width - 6.9, y + 4.25D);
         }
      }
   }

   public void button(double x, double y, double width, double height, double mouseX, double mouseY, double xStart, double yStart, double xEnd, double yEnd, DrawContext drawContext, int click) {
      this.render(x, y, width, height, mouseX, mouseY, drawContext);
      if (MouseUtil.isHovered(x, y, width, height, mouseX, mouseY) && click == 0 && MouseUtil.isHovered2(xStart, yStart, xEnd, yEnd, mouseX, mouseY)) {
         this.booleanSetting.setValue(!this.booleanSetting.getValue());
      }
   }

   public void setAnimation(double animation) {
      this.animation = animation;
   }

   public void setAnimationEnabled(double animationEnabled) {
      this.animationEnabled = animationEnabled;
   }

   public void setBooleanSetting(BooleanSetting booleanSetting) {
      this.booleanSetting = booleanSetting;
   }
}
