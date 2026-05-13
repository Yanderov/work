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
import dev.client.util.render.builders.states.QuadRadiusState;
import dev.client.util.render.builders.states.SizeState;
import dev.client.util.render.msdf.MsdfFont;
import dev.client.util.render.renderers.impl.BuiltBorder;
import dev.client.util.render.renderers.impl.BuiltRectangle;
import dev.client.util.render.renderers.impl.BuiltText;
import java.awt.Color;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import org.joml.Matrix4f;

@Environment(EnvType.CLIENT)
public class MultiBoxElement implements IElementable {
   private MultiBoxSetting multiBoxSetting;
   private double animation;
   private double animationEnabled;
   private float offsetX = 0.0F;
   private float offsetY = 0.0F;

   public void mouseClick(int click) {
   }

   public void render(double x, double y, double width, double height, double mouseX, double mouseY, DrawContext drawContext) {
   }

   public void button(double x, double y, double width, double height, double mouseX, double mouseY, double xStart, double yStart, double xEnd, double yEnd, DrawContext drawContext, int click) {
      Matrix4f matrix = drawContext.getMatrices().peek().getPositionMatrix();
      boolean animationFinished = this.animationEnabled > 0.0D;
      Color colorText = ColorUtil.setAlpha(this.animation, ColorUtil.colorAlpha(Color.WHITE, (int)(122.4 + (animationFinished ? 132.6 * this.animationEnabled : 0.0D))));
      BuiltText text = (BuiltText)Builder.text().font(FontManager.MONTSERRAT.get()).text(this.multiBoxSetting.getName()).color(colorText).size(7.5F).thickness(0.05F).build();
      text.render(matrix, x, y + 3.5D);
      BuiltRectangle rectangle = Builder.rectangle().size(new SizeState(width, (double)(this.multiBoxSetting.getOffset() + 16.0F))).color(new QuadColorState(ColorUtil.setAlpha(this.animation, new Color(0, 0, 0, 3)))).radius(new QuadRadiusState(3.0F)).smoothness(1.15F).build();
      rectangle.render(matrix, x, y + 16.0D);
      BuiltBorder border = (BuiltBorder)Builder.border().size(new SizeState(width, (double)(this.multiBoxSetting.getOffset() + 16.0F))).color(new QuadColorState(ColorUtil.colorAlpha(Color.WHITE, (int)(3.0D * this.animation)))).radius(new QuadRadiusState(3.0F)).thickness(0.01F).smoothness(0.65F, 0.65F).build();
      border.render(matrix, x, y + 16.0D);
      this.offsetX = 0.0F;
      this.offsetY = 0.0F;

      for(BooleanSetting booleanSetting : this.multiBoxSetting.getBooleanSettings()) {
         String name = booleanSetting.getName().substring(0, 1).toUpperCase() + booleanSetting.getName().substring(1).toLowerCase();
         if ((double)(this.offsetX + FontManager.SUISSEINTMEDIUM.get().getWidth(name, 7.0F) + 5.0F) > width - 8.0D) {
            this.offsetY += 14.0F;
            this.offsetX = 0.0F;
         }

         this.rendereMultiboxButtonButton(booleanSetting, x + 2.0D + (double)this.offsetX, y + 18.0D + (double)this.offsetY, (double)(FontManager.SUISSEINTMEDIUM.get().getWidth(name, 7.0F) + 5.0F), 12.0D, mouseX, mouseY, xStart, yStart, xEnd, yEnd, click, matrix);
         this.offsetX += FontManager.SUISSEINTMEDIUM.get().getWidth(name, 7.0F) + 8.0F;
      }

      this.multiBoxSetting.setOffset(this.offsetY);
   }

   private void rendereMultiboxButtonButton(BooleanSetting booleanSetting, double x, double y, double width, double height, double mouseX, double mouseY, double xStart, double yStart, double xEnd, double yEnd, int click, Matrix4f matrix) {
      String name = booleanSetting.getName().substring(0, 1).toUpperCase() + booleanSetting.getName().substring(1).toLowerCase();
      boolean animationFinished = this.animationEnabled > 0.0D;
      
      // Default state text color
      Color colorText = ColorUtil.setAlpha(this.animation, ColorUtil.colorAlpha(Color.WHITE, (int)(122.4 + (animationFinished ? 132.6 * this.animationEnabled : 0.0D))));
      
      BuiltText text = (BuiltText)Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text(name).color(colorText).size(7.0F).thickness(0.05F).build();
      text.render(matrix, x + 2.0D, y + 2.0D);
      
      if (!booleanSetting.getAnimation().finished(Direction.BACKWARDS)) {
         BuiltRectangle rect = Builder.rectangle().size(new SizeState(width, height)).color(new QuadColorState(ColorUtil.setAlpha(this.animation * booleanSetting.getAnimation().getOutput(), new Color(255, 255, 255, 20)))).radius(new QuadRadiusState(2.0F)).smoothness(1.2F).build();
         rect.render(matrix, x, y);
         
         BuiltBorder border = (BuiltBorder)Builder.border().size(new SizeState(width, height)).color(new QuadColorState(ColorUtil.setAlpha(this.animation * booleanSetting.getAnimation().getOutput(), Color.WHITE))).radius(new QuadRadiusState(2.0F)).thickness(0.01F).smoothness(0.65F, 0.65F).build();
         border.render(matrix, x, y);
         
         // Highlight state text color
         text = (BuiltText)Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text(name).color(ColorUtil.setAlpha(this.animation * booleanSetting.getAnimation().getOutput(), Color.WHITE)).size(7.0F).thickness(0.05F).build();
         text.render(matrix, x + 2.0D, y + 2.0D);
      }

      if (MouseUtil.isHovered((double)((int)x), (double)((int)y), (double)((int)width), (double)((int)height), (double)((int)mouseX), (double)((int)mouseY)) && click == 0 && MouseUtil.isHovered2(xStart, yStart, xEnd, yEnd, mouseX, mouseY)) {
         booleanSetting.setValue(!booleanSetting.getValue());
      }
   }

   public void setAnimationEnabled(double animationEnabled) {
      this.animationEnabled = animationEnabled;
   }

   public void setAnimation(double animation) {
      this.animation = animation;
   }

   public void setMultiBoxSetting(MultiBoxSetting multiBoxSetting) {
      this.multiBoxSetting = multiBoxSetting;
   }
}
