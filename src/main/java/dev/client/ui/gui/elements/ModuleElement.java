package dev.client.ui.gui.elements;

import dev.client.WildClient;
import dev.client.managers.FontManager;
import dev.client.modules.Module;
import dev.client.modules.settings.Setting;
import dev.client.modules.settings.impl.BooleanSetting;
import dev.client.modules.settings.impl.FloatSetting;
import dev.client.modules.settings.impl.KeySetting;
import dev.client.modules.settings.impl.ModeSetting;
import dev.client.modules.settings.impl.MultiBoxSetting;
import dev.client.modules.settings.impl.StringSetting;
import dev.client.ui.gui.IElementable;
import dev.client.ui.gui.IKeyPressible;
import dev.client.ui.gui.IMouseReleable;
import dev.client.util.animations.Direction;
import dev.client.util.color.ColorUtil;
import dev.client.util.math.MathUtil;
import dev.client.util.math.MouseUtil;
import dev.client.util.render.Scissor;
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
public class ModuleElement implements IElementable, IMouseReleable, IKeyPressible {
   private Module module;
   private double animation;
   private final BooleanElement booleanElement = new BooleanElement();
   private final FloatElement floatElement = new FloatElement();
   private final ModeElement modeElement = new ModeElement();
   private final MultiBoxElement multiBoxElement = new MultiBoxElement();
   private final StringElement stringElement = new StringElement();
   private final KeyElement keyElement = new KeyElement();
   private int key = 0;

   public void mouseClick(int click) {
      switch (click) {
         case 0:
            this.module.setEnabled(!this.module.isEnabled());
            break;
         case 1:
            if (!this.module.getSettings().isEmpty()) {
               this.module.setOpened(!this.module.isOpened());
            }
            break;
         case 2:
            for(Module m : WildClient.INSTANCE.getModuleManager().getModules()) {
               m.setBinded(false);
            }

            this.module.setBinded(true);
      }

   }

   public void render(double x, double y, double width, double height, double mouseX, double mouseY, DrawContext drawContext) {
      Matrix4f matrix = drawContext.getMatrices().peek().getPositionMatrix();
      boolean animationFinished = !this.module.getAnimation().finished(Direction.BACKWARDS);
      Color color = ColorUtil.setAlpha(this.animation, ColorUtil.colorAlpha(Color.WHITE, (int)(1.0D + (animationFinished ? 1.0D * this.module.getAnimation().getOutput() : 0.0D))));
      Color colorText = ColorUtil.setAlpha(this.animation, ColorUtil.colorAlpha(Color.WHITE, (int)(122.4 + (animationFinished ? 132.6 * this.module.getAnimation().getOutput() : 0.0D))));
      float round = this.module.isOpened() ? 0.0F : 5.0F;
      BuiltRectangle rectangle = Builder.rectangle().size(new SizeState(width, height)).color(new QuadColorState(new Color(0, 0, 0, 3))).radius(new QuadRadiusState(5.0F)).smoothness(1.15F).build();
      rectangle.render(matrix, x, y);
      BuiltBorder border = (BuiltBorder)Builder.border().size(new SizeState(width, height)).color(new QuadColorState(ColorUtil.colorAlpha(Color.black, 3))).radius(new QuadRadiusState(5.0F)).thickness(0.01F).smoothness(0.65F, 0.65F).build();
      border.render(matrix, x, y);
      rectangle = Builder.rectangle().size(new SizeState(width, this.module.getDefaultHeight())).color(new QuadColorState(color.brighter().brighter(), color.brighter().brighter(), color.darker().darker(), color.darker().darker())).radius(new QuadRadiusState(5.0F, round, round, 5.0F)).smoothness(1.15F).build();
      rectangle.render(matrix, x, y);
      if (animationFinished) {
         Color color1 = ColorUtil.setAlpha(this.animation * this.module.getAnimation().getOutput() * 0.05, Color.white);
         rectangle = Builder.rectangle().size(new SizeState(width, this.module.getDefaultHeight())).color(new QuadColorState(new Color(0, 0, 0, 5), new Color(0, 0, 0, 5), color1, color1)).radius(new QuadRadiusState(5.0F, round, round, 5.0F)).smoothness(1.15F).build();
         rectangle.render(matrix, x, y);
      }

      border = (BuiltBorder)Builder.border().size(new SizeState(width, this.module.getDefaultHeight())).color(new QuadColorState(ColorUtil.colorAlpha(Color.WHITE, (int)(3.0D + (animationFinished ? 2.0D * this.module.getAnimation().getOutput() : 0.0D))))).radius(new QuadRadiusState(5.0F, round, round, 5.0F)).thickness(0.01F).smoothness(0.65F, 0.65F).build();
      border.render(matrix, x, y);
      if (animationFinished) {
         rectangle = Builder.rectangle().size(new SizeState(1.0F, 8.0F)).color(new QuadColorState(ColorUtil.setAlpha(this.module.getAnimation().getOutput() * this.animation, Color.white))).radius(new QuadRadiusState(0.5D)).smoothness(0.1F).build();
         rectangle.render(matrix, x + 5.0D + this.module.getAnimation().getOutput() * 5.0D, y + 9.5D);
      }

      BuiltText text = (BuiltText)Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text(this.module.isBinded() ? "Press key" : this.module.getModuleBranding().name()).color(colorText).size(7.5F).thickness(0.05F).build();
      text.render(matrix, x + 9.0D + this.module.getAnimation().getOutput() * 5.0D, y + 9.0D);
      Color colorDesc = ColorUtil.setAlpha(this.animation, new Color(255, 255, 255, 61));
      Color colorDescEnabled = ColorUtil.setAlpha(this.animation * this.module.getAnimation().getOutput(), new Color(255, 255, 255, 122));
      Color colorImageEnable = ColorUtil.setAlpha(this.animation * this.module.getAnimation().getOutput(), new Color(255, 255, 255, 61));
      text = (BuiltText)Builder.text().font(FontManager.SUISSEINTREGULAR.get()).text(this.module.getDesc()[0]).color(colorDesc).size(5.8F).thickness(0.05F).build();
      text.render(matrix, x + 9.0D, y + 23.5D);
      if (this.module.isDoubleDesc()) {
         text = (BuiltText)Builder.text().font(FontManager.SUISSEINTREGULAR.get()).text(this.module.getDesc()[1]).color(colorDesc).size(5.8F).thickness(0.05F).build();
         text.render(matrix, x + 9.0D, y + 30.5D);
      }

      if (animationFinished) {
         text = (BuiltText)Builder.text().font(FontManager.SUISSEINTREGULAR.get()).text(this.module.getDesc()[0]).color(colorDescEnabled).size(5.8F).thickness(0.05F).build();
         text.render(matrix, x + 9.0D, y + 23.5D);
         if (this.module.isDoubleDesc()) {
            text = (BuiltText)Builder.text().font(FontManager.SUISSEINTREGULAR.get()).text(this.module.getDesc()[1]).color(colorDescEnabled).size(5.8F).thickness(0.05F).build();
            text.render(matrix, x + 9.0D, y + 30.5D);
         }
      }

      rectangle = Builder.rectangle().size(new SizeState(16.5D, 9.5D)).color(new QuadColorState(ColorUtil.setAlpha(this.animation, new Color(0, 0, 0, 41)))).radius(new QuadRadiusState(3.8)).smoothness(1.15F).build();
      rectangle.render(matrix, x + width - 25.5D, y + 8.5D);
      if (animationFinished) {
         rectangle = Builder.rectangle().size(new SizeState(16.5D, 9.5D)).color(new QuadColorState(ColorUtil.setAlpha(this.animation * this.module.getAnimation().getOutput(), Color.white))).radius(new QuadRadiusState(3.8)).smoothness(1.15F).build();
         rectangle.render(matrix, x + width - 25.5D, y + 8.5D);
      }

      double roundX = x + width - 24.25D + this.module.getAnimation().getOutput() * 6.5D;
      rectangle = Builder.rectangle().size(new SizeState(7.5D, 7.5D)).color(new QuadColorState(ColorUtil.setAlpha(this.animation, Color.white))).radius(new QuadRadiusState(2.8)).smoothness(1.15F).build();
      rectangle.render(matrix, roundX, y + 9.5D);
      if (animationFinished) {
         rectangle = Builder.rectangle().size(new SizeState(7.5D, 7.5D)).color(new QuadColorState(ColorUtil.setAlpha(this.animation * this.module.getAnimation().getOutput(), new Color(0, 0, 0, 8)))).radius(new QuadRadiusState(2.8)).smoothness(1.15F).build();
         rectangle.render(matrix, roundX, y + 9.5D);
      }

      boolean isEmpty = !this.module.getSettings().isEmpty();
      if (isEmpty) {
         text = (BuiltText)Builder.text().font(FontManager.CATEGORY.get()).text("E").color(ColorUtil.setAlpha(this.animation, new Color(255, 255, 255, 61))).size(7.75F).thickness(0.05F).build();
         text.render(matrix, x + width - 40.0D, y + 9.25D);
      }

      double xBind = width - 23.0D - (double)FontManager.SUISSEINTMEDIUM.get().getWidth(this.module.getNameBind(), 5.8F);
      text = (BuiltText)Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text(this.module.getNameBind()).color(colorDesc).size(5.8F).thickness(0.05F).build();
      text.render(matrix, x + xBind, y + 23.5D);
      text = (BuiltText)Builder.text().font(FontManager.WILD.get()).text("R").color(ColorUtil.setAlpha(this.animation, new Color(255, 255, 255, 31))).size(5.3F).thickness(0.05F).build();
      text.render(matrix, x + width - 16.0D, y + 23.4);
      if (animationFinished) {
         text = (BuiltText)Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text(this.module.getNameBind()).color(colorDescEnabled).size(5.8F).thickness(0.05F).build();
         text.render(matrix, x + xBind, y + 23.5D);
         text = (BuiltText)Builder.text().font(FontManager.WILD.get()).text("R").color(ColorUtil.setAlpha(this.animation * this.module.getAnimation().getOutput(), colorImageEnable)).size(5.3F).thickness(0.05F).build();
         text.render(matrix, x + width - 16.0D, y + 23.4);
      }

      if (!this.module.getAnimSettings().finished(Direction.BACKWARDS)) {
         if (isEmpty) {
            text = (BuiltText)Builder.text().font(FontManager.CATEGORY.get()).text("E").color(ColorUtil.setAlpha(this.animation * this.module.getAnimSettings().getOutput(), new Color(255, 255, 255, 122))).size(7.75F).thickness(0.05F).build();
            text.render(matrix, x + width - 40.0D, y + 9.25D);
         }

         if (animationFinished) {
            text = (BuiltText)Builder.text().font(FontManager.CATEGORY.get()).text("E").color(ColorUtil.setAlpha(this.animation * this.module.getAnimSettings().getOutput() * this.module.getAnimation().getOutput(), Color.white)).size(7.75F).thickness(0.05F).build();
            text.render(matrix, x + width - 40.0D, y + 9.25D);
         }
      }

   }

   public void button(double x, double y, double width, double height, double mouseX, double mouseY, double xStart, double yStart, double xEnd, double yEnd, DrawContext drawContext, int click) {
      this.render(x, y, width, height, mouseX, mouseY, drawContext);
      if (MouseUtil.isHovered(x, y, width, this.module.getDefaultHeight(), mouseX, mouseY) && MouseUtil.isHovered2(xStart, yStart, xEnd, yEnd, mouseX, mouseY)) {
         this.mouseClick(click);
      }

      float value = Math.max(0.0F, (float)this.module.getHeight() - 42.0F);
      if (this.module.isOpened()) {
         Scissor.StartScissor((float)(x + 9.0D), (float)y, (float)(width - 18.0D), (float)this.module.getHeight() - 3.8F);
         this.renderSettings(x + 9.0D, y + 39.5D, width - 18.0D, mouseX, mouseY, xStart, yStart, xEnd, yEnd, drawContext, click);
         Scissor.stopScissor();
      } else {
         this.module.setHeight((double)MathUtil.fast((float)this.module.getHeight(), (float)this.module.getDefaultHeight(), 10.0F));
      }

   }

   private void renderSettings(double x, double y, double width, double mouseX, double mouseY, double xStart, double yStart, double xEnd, double yEnd, DrawContext drawContext, int click) {
      double modulAnimation = this.module.getAnimation().getOutput();
      this.booleanElement.setAnimation(this.animation);
      this.booleanElement.setAnimationEnabled(modulAnimation);
      this.floatElement.setAnimation(this.animation);
      this.floatElement.setAnimationEnabled(modulAnimation);
      this.modeElement.setAnimation(this.animation);
      this.modeElement.setAnimationEnabled(modulAnimation);
      this.multiBoxElement.setAnimation(this.animation);
      this.multiBoxElement.setAnimationEnabled(modulAnimation);
      this.keyElement.setAnimation(this.animation);
      this.keyElement.setKey(this.key);
      this.keyElement.setAnimationEnabled(modulAnimation);
      float offset = this.module.getDefaultHeight() == 38.5D ? 4.0F : 10.5F;

      for(Setting setting : this.module.getSettings()) {
         if (setting.isVisible()) {
            if (setting instanceof BooleanSetting) {
               BooleanSetting booleanSetting = (BooleanSetting)setting;
               this.booleanElement.setBooleanSetting(booleanSetting);
               this.booleanElement.button(x, y + (double)offset, width, 15.0D, mouseX, mouseY, xStart, yStart, xEnd, yEnd, drawContext, click);
               offset += 19.0F;
            } else if (setting instanceof FloatSetting) {
               FloatSetting floatSetting = (FloatSetting)setting;
               this.floatElement.setFloatSetting(floatSetting);
               this.floatElement.button(x, y + (double)offset, width, 19.0D, mouseX, mouseY, xStart, yStart, xEnd, yEnd, drawContext, click);
               offset += 23.0F;
            } else if (setting instanceof ModeSetting) {
               ModeSetting modeSetting = (ModeSetting)setting;
               this.modeElement.setModeSetting(modeSetting);
               this.modeElement.button(x, y + (double)offset, width, 15.0D, mouseX, mouseY, xStart, yStart, xEnd, yEnd, drawContext, click);
               offset += 36.0F + modeSetting.getOffset();
            } else if (setting instanceof MultiBoxSetting) {
               MultiBoxSetting multiBoxSetting = (MultiBoxSetting)setting;
               this.multiBoxElement.setMultiBoxSetting(multiBoxSetting);
               this.multiBoxElement.button(x, y + (double)offset, width, 15.0D, mouseX, mouseY, xStart, yStart, xEnd, yEnd, drawContext, click);
               offset += 36.0F + multiBoxSetting.getOffset();
            } else if (setting instanceof StringSetting) {
               StringSetting stringSetting = (StringSetting)setting;
            } else if (setting instanceof KeySetting) {
               KeySetting keySetting = (KeySetting)setting;
               this.keyElement.setKeySetting(keySetting);
               this.keyElement.button(x, y + (double)offset, width, 18.0D, mouseX, mouseY, xStart, yStart, xEnd, yEnd, drawContext, click);
               offset += 19.0F;
            }
         }
      }

      this.module.setHeight((double)MathUtil.fast((float)this.module.getHeight(), (float)((double)offset + this.module.getDefaultHeight() + 5.0D - (double)(this.module.getDefaultHeight() == 38.5D ? 4.0F : 10.5F)), 10.0F));
   }

   public void setAnimation(double animation) {
      this.animation = animation;
   }

   public void setModule(Module module) {
      this.module = module;
   }

   public void onMouseRelease() {
   }

   public void keyPressed(int keyCode, int scanCode, int modifiers) {
   }

   public void setKey(int key) {
      this.key = key;
   }
}

