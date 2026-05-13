package dev.client.ui.gui.elements;

import dev.client.WildClient;
import dev.client.managers.FontManager;
import dev.client.modules.Category;
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
public class CategoryElement implements IElementable {
   private Category category;
   private double animation;

   public void mouseClick(int click) {
      if (click == 0) {
         WildClient.INSTANCE.getGui().getCategory().getAnimation().setDirection(Direction.BACKWARDS);
         this.category.getAnimation().setDirection(Direction.FORWARDS);
         WildClient.INSTANCE.getGui().setCategory(this.category);
         WildClient.INSTANCE.getGui().updateModules();
         WildClient.INSTANCE.getGui().setScroll(0.0D);
      }

   }

   public void render(double x, double y, double width, double height, double mouseX, double mouseY, DrawContext drawContext) {
      Matrix4f matrix = drawContext.getMatrices().peek().getPositionMatrix();
      boolean isCategory = this.category == WildClient.INSTANCE.getGui().getCategory();
      if (isCategory) {
         BuiltRectangle rectangle = Builder.rectangle().size(new SizeState(width, height)).color(new QuadColorState(ColorUtil.setAlpha(this.animation, new Color(100663295, true)))).radius(new QuadRadiusState(5.0F)).smoothness(1.15F).build();
         rectangle.render(matrix, x, y);
         BuiltBorder border = (BuiltBorder)Builder.border().size(new SizeState(width, height)).color(new QuadColorState(ColorUtil.setAlpha(this.animation, new Color(184549375, true)))).radius(new QuadRadiusState(5.0F)).thickness(0.01F).smoothness(0.6F, 0.6F).build();
         border.render(matrix, x, y);
      } else {
         BuiltRectangle rectangle = Builder.rectangle().size(new SizeState(width, height)).color(new QuadColorState(ColorUtil.setAlpha(this.animation, new Color(67108863, true)))).radius(new QuadRadiusState(5.0F)).smoothness(1.15F).build();
         rectangle.render(matrix, x, y);
         BuiltBorder border = (BuiltBorder)Builder.border().size(new SizeState(width, height)).color(new QuadColorState(ColorUtil.setAlpha(this.animation, new Color(150994943, true)))).radius(new QuadRadiusState(5.0F)).thickness(0.01F).smoothness(0.6F, 0.6F).build();
         border.render(matrix, x, y);
      }

      Color color = ColorUtil.setAlpha(this.animation, Color.white);
      BuiltText icon = (BuiltText)Builder.text().font(FontManager.WILD.get()).text(this.category.getIcon()).color(color).size(10.0F).thickness(0.05F).build();
      icon.render(matrix, x + 6.8 + (this.category == Category.PLAYER ? 1.5D : 0.0D), y + 7.0D);
      if (!this.category.getAnimation().finished(Direction.BACKWARDS)) {
         Color colorEnd = ColorUtil.setAlpha(this.animation * this.category.getAnimation().getOutput(), Color.white);
         icon = (BuiltText)Builder.text().font(FontManager.WILD.get()).text(this.category.getIcon()).color(colorEnd).size(10.0F).thickness(0.05F).build();
         icon.render(matrix, x + 6.5D + (this.category == Category.PLAYER ? 1.5D : 0.0D), y + 7.0D);
      }

   }

   public void button(double x, double y, double width, double height, double mouseX, double mouseY, double xStart, double yStart, double xEnd, double yEnd, DrawContext drawContext, int click) {
      this.render(x, y, width, height, mouseX, mouseY, drawContext);
      if (MouseUtil.isHovered(x, y, width, height, mouseX, mouseY)) {
         this.mouseClick(click);
      }

   }

   public void setCategory(Category category) {
      this.category = category;
   }

   public void setAnimation(double animation) {
      this.animation = animation;
   }
}
