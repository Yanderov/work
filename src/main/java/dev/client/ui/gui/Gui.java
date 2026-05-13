package dev.client.ui.gui;

import dev.client.WildClient;
import dev.client.managers.FontManager;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.impl.render.GuiModule;
import dev.client.modules.settings.impl.FloatSetting;
import dev.client.ui.gui.elements.CategoryElement;
import dev.client.ui.gui.elements.ModuleElement;
import dev.client.ui.gui.elements.SearchElement;
import dev.client.ui.themes.Theme;
import dev.client.util.animations.Animation;
import dev.client.util.animations.Direction;
import dev.client.util.animations.impl.EaseBackIn;
import dev.client.util.color.ColorUtil;
import dev.client.util.math.MathUtil;
import dev.client.util.math.MouseUtil;
import dev.client.util.render.Scissor;
import dev.client.util.render.builders.Builder;
import dev.client.util.render.builders.states.QuadColorState;
import dev.client.util.render.builders.states.QuadRadiusState;
import dev.client.util.render.builders.states.SizeState;
import dev.client.util.render.renderers.impl.BuiltBlur;
import dev.client.util.render.renderers.impl.BuiltBorder;
import dev.client.util.render.renderers.impl.BuiltRectangle;
import dev.client.util.render.renderers.impl.BuiltText;
import dev.client.util.render.renderers.impl.BuiltTexture;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class Gui extends Screen {
   private double x;
   private double y;
   private double widthGui;
   private double heightGui;
   private double r;
   private final Animation animation;
   public int preScroll;
   public int mainScroll;
   private final CategoryElement categoryElement;
   private final ModuleElement moduleElement;
   private final SearchElement searchElement;
   private Category category;
   private int key;
   private String type = "";
   private double offsetX;
   private double offsetY;
   private double offsetY2;
   private int click;
   private final List<Module> modules;

   public Gui() {
      super(Text.of(""));
      this.animation = new EaseBackIn(355, 1.0D, 0.1F, Direction.BACKWARDS);
      this.categoryElement = new CategoryElement();
      this.moduleElement = new ModuleElement();
      this.modules = new ArrayList<>();
      this.searchElement = new SearchElement();
      this.category = Category.COMBAT;
      this.offsetX = 0.0D;
      this.offsetY = 0.0D;
      this.offsetY2 = 0.0D;
      this.click = -1;
      Category.COMBAT.getAnimation().setDirection(Direction.FORWARDS);
      this.updateModules();
      this.preScroll = 0;
      this.mainScroll = 0;
   }

   public void updateModules() {
      this.modules.clear();
      this.modules.addAll(WildClient.INSTANCE.getModuleManager().getByCategory(this.category));
   }

   protected void init() {
      this.animation.setDirection(Direction.FORWARDS);
      this.widthGui = 450.0D;
      this.heightGui = 300.0D;
      this.x = (double)(this.width / 2) - this.widthGui / 2.0D;
      this.y = (double)(this.height / 2) - this.heightGui / 2.0D + 25.0D;
      this.r = 11.0D;
      super.init();
   }

   public void render(DrawContext context, int mouseX, int mouseY, float delta) {
      Matrix4f matrix4f = context.getMatrices().peek().getPositionMatrix();
      Color white = ColorUtil.setAlpha(this.animation.getOutput(), Color.white);
      Color logoColor = ColorUtil.setAlpha(this.animation.getOutput(), Color.white);
      Color nameColor = ColorUtil.setAlpha(this.animation.getOutput(), Color.white);
      Color lineColor = ColorUtil.setAlpha(this.animation.getOutput(), new Color(255, 255, 255, 61));
      Color color = ColorUtil.setAlpha(this.animation.getOutput(), Color.white);
      BuiltBlur blur = Builder.blur().size(new SizeState(this.widthGui, this.heightGui)).radius(new QuadRadiusState(this.r)).blurRadius((float)(18.0D * this.animation.getOutput())).smoothness((float)(1.0D * this.animation.getOutput())).color(new QuadColorState(Color.white)).build();
      blur.render(matrix4f, this.x, this.y);
      BuiltRectangle rectangle = Builder.rectangle().size(new SizeState(this.widthGui, this.heightGui)).color(new QuadColorState(ColorUtil.setAlpha(this.animation.getOutput(), new Color(0, 0, 0, 122)))).radius(new QuadRadiusState(this.r)).smoothness(1.15F).build();
      rectangle.render(matrix4f, this.x, this.y);
      rectangle = Builder.rectangle().size(new SizeState(45.0D, this.heightGui - 10.0D)).color(new QuadColorState(ColorUtil.setAlpha(this.animation.getOutput(), new Color(0, 0, 0, 184)))).radius(new QuadRadiusState(this.r, this.r, 3.0D, 3.0D)).smoothness(1.15F).build();
      rectangle.render(matrix4f, this.x + 5.0D, this.y + 5.0D);
      rectangle = Builder.rectangle().size(new SizeState(this.widthGui - 50.0D - 150.0D, 30.0D)).color(new QuadColorState(ColorUtil.setAlpha(this.animation.getOutput(), new Color(0, 0, 0, 184)))).radius(new QuadRadiusState(3.0F, 3.0F, 3.0F, 3.0F)).smoothness(1.15F).build();
      rectangle.render(matrix4f, this.x + 55.0D, this.y + 5.0D);
      rectangle = Builder.rectangle().size(new SizeState(135.0F, 30.0F)).color(new QuadColorState(ColorUtil.setAlpha(this.animation.getOutput(), new Color(0, 0, 0, 184)))).radius(new QuadRadiusState(3.0D, 3.0D, 3.0D, this.r)).smoothness(1.15F).build();
      rectangle.render(matrix4f, this.x + 60.0D + (this.widthGui - 50.0D - 150.0D), this.y + 5.0D);
      this.searchElement.setAnimation(this.animation.getOutput());
      this.searchElement.button(this.x + 60.0D + (this.widthGui - 50.0D - 150.0D), this.y + 5.0D, 135.0D, 30.0D, (double)mouseX, (double)mouseY, this.x, this.y, this.widthGui, this.heightGui, context, this.click);
      rectangle = Builder.rectangle().size(new SizeState(this.widthGui - 60.0D, this.heightGui - 44.0D)).color(new QuadColorState(ColorUtil.setAlpha(this.animation.getOutput(), new Color(0, 0, 0, 184)))).radius(new QuadRadiusState(3.0D, 3.0D, this.r, 3.0D)).smoothness(1.15F).build();
      rectangle.render(matrix4f, this.x + 55.0D, this.y + 39.0D);
      BuiltText text = null;
      BuiltText icon = (BuiltText)Builder.text().font(FontManager.WILD.get()).text(this.category.getIcon()).color(color).size(8.0F).thickness(0.05F).build();
      icon.render(matrix4f, this.x + 63.0D + (this.category == Category.PLAYER ? 1.5D : 0.0D), this.y + 15.0D);
      text = (BuiltText)Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text(this.category.getName()).color(white).size(8.0F).thickness(0.05F).build();
      text.render(matrix4f, this.x + 76.0D, this.y + 15.0D);
      rectangle = Builder.rectangle().size(new SizeState(32.0F, 32.0F)).color(new QuadColorState(ColorUtil.setAlpha(this.animation.getOutput(), Color.black))).radius(new QuadRadiusState(5.0F)).smoothness(1.15F).build();
      rectangle.render(matrix4f, this.x + 10.0D, this.y + 10.0D);
      
      AbstractTexture rightlogoTexture = MinecraftClient.getInstance().getTextureManager().getTexture(Identifier.of("wild", "images/gui/rightlogo.png"));
      BuiltTexture rightlogo = (BuiltTexture)Builder.texture().size(new SizeState(30.0F, 30.0F)).radius(new QuadRadiusState(4.0F)).texture(0.0F, 0.0F, 1.0F, 1.0F, rightlogoTexture).color(new QuadColorState(Color.white)).build();
      rightlogo.render(matrix4f, (float)(this.x + 11.0D), (float)(this.y + 11.0D), 0.0F);
      rectangle = Builder.rectangle().size(new SizeState(27.0D, 1.9)).color(new QuadColorState(ColorUtil.setAlpha(this.animation.getOutput(), new Color(0, 0, 0, 20)))).radius(new QuadRadiusState(4.0F)).smoothness(1.15F).build();
      rectangle.render(matrix4f, this.x + 13.5D, this.y + 48.5D);
      this.offsetY = 0.0D;
      double xCategory = this.x + 5.0D + 22.5D - 12.5D;

      for(Category category : Category.values()) {
         this.categoryElement.setCategory(category);
         this.categoryElement.setAnimation(this.animation.getOutput());
         this.categoryElement.button(xCategory, this.y + 59.0D + this.offsetY, 25.0D, 25.0D, (double)mouseX, (double)mouseY, this.x, this.y, this.x + this.widthGui, this.y + this.heightGui, context, this.click);
         this.offsetY += 30.0D;
      }

      AbstractTexture abstractTexture = MinecraftClient.getInstance().getTextureManager().getTexture(Identifier.of("wild", "images/gui/avatar.png"));
      BuiltTexture texture = (BuiltTexture)Builder.texture().size(new SizeState(25.0F, 25.0F)).radius(new QuadRadiusState(12.0F)).texture(0.0F, 0.0F, 1.0F, 1.0F, abstractTexture).color(new QuadColorState(white)).build();
      texture.render(matrix4f, (float)(this.x + 5.0D + 22.5D - 12.5D), (float)(this.y + this.heightGui - 42.0D), 0.0F);
      this.offsetX = 0.0D;
      this.offsetY = 0.0D;
      this.offsetY2 = 0.0D;
      this.moduleElement.setAnimation(this.animation.getOutput());
      this.moduleElement.setKey(this.key);
      double xModules = this.x + 65.0D;
      double yModules = this.y + 49.0D;
      double widthMoidule = 182.5D;
      context.getMatrices().push();
      Scissor.StartScissor((float)xModules, (float)yModules - 8.0F, 380.0F, 253.0F);
      if (this.searchElement.getValue().isEmpty()) {
         int yAnim = 0;

         for(Module module : this.modules) {
            this.moduleElement.setModule(module);
            if (this.offsetX == 0.0D) {
               this.moduleElement.button(xModules + this.offsetX, yModules + this.offsetY + (double)this.mainScroll + (double)yAnim, widthMoidule, module.getHeight(), (double)mouseX, (double)mouseY, this.x, yModules, this.x + this.widthGui, this.y + this.heightGui, context, this.click);
               this.offsetY += module.getHeight() + 5.0D;
               this.offsetX += widthMoidule + 5.0D;
            } else {
               this.moduleElement.button(xModules + this.offsetX, yModules + this.offsetY2 + (double)this.mainScroll + (double)yAnim, widthMoidule, module.getHeight(), (double)mouseX, (double)mouseY, this.x, yModules, this.x + this.widthGui, this.y + this.heightGui, context, this.click);
               this.offsetY2 += module.getHeight() + 5.0D;
               this.offsetX = 0.0D;
            }

            if (module.isBinded()) {
               for(int button = 0; button <= 7; ++button) {
                  if (GLFW.glfwGetMouseButton(MinecraftClient.getInstance().getWindow().getHandle(), button) == 1 && button != 2 && button != 0) {
                     module.setBind(1330 + button);
                     module.setBinded(false);
                  }
               }
            }
         }
      } else {
         int yAnim = 0;

         for(Module module : WildClient.INSTANCE.getModuleManager().search(this.searchElement.getValue())) {
            this.moduleElement.setModule(module);
            if (this.offsetX == 0.0D) {
               this.moduleElement.button(xModules + this.offsetX, yModules + this.offsetY + (double)this.mainScroll + (double)yAnim, widthMoidule, module.getHeight(), (double)mouseX, (double)mouseY, this.x, yModules, this.x + this.widthGui, this.y + this.heightGui, context, this.click);
               this.offsetY += module.getHeight() + 5.0D;
               this.offsetX += widthMoidule + 5.0D;
            } else {
               this.moduleElement.button(xModules + this.offsetX, yModules + this.offsetY2 + (double)this.mainScroll + (double)yAnim, widthMoidule, module.getHeight(), (double)mouseX, (double)mouseY, this.x, yModules, this.x + this.widthGui, this.y + this.heightGui, context, this.click);
               this.offsetY2 += module.getHeight() + 5.0D;
               this.offsetX = 0.0D;
            }

            if (module.isBinded()) {
               for(int button = 0; button <= 7; ++button) {
                  if (GLFW.glfwGetMouseButton(MinecraftClient.getInstance().getWindow().getHandle(), button) == 1 && button != 2 && button != 0) {
                     module.setBind(1330 + button);
                     module.setBinded(false);
                  }
               }
            }
         }
      }

      Scissor.stopScissor();
      context.getMatrices().pop();
      double maxScroll = Math.max(this.offsetY, this.offsetY2) - 38.0D;
      if ((double)this.preScroll < -maxScroll) {
         this.preScroll = (int)MathUtil.fast((float)this.preScroll, (float)(-maxScroll), 1000.0F);
      } else if (this.preScroll > 0) {
         this.preScroll = (int)MathUtil.fast((float)this.preScroll, 0.0F, 1000.0F);
      }

      this.mainScroll = (int)MathUtil.fast((float)this.mainScroll, (float)this.preScroll, 13.0F);
      if (this.animation.finished(Direction.BACKWARDS)) {
         WildClient.INSTANCE.getModuleManager().getByClass(GuiModule.class).setEnabled(false);
      }

      this.key = 0;
      this.type = "";
      this.click = -1;
   }

   private void themeButton(double x, double y, double width, double height, double mouseX, double mouseY, Theme theme, Matrix4f matrix) {
      BuiltRectangle rectangle = Builder.rectangle().size(new SizeState(width, height)).color(new QuadColorState(ColorUtil.setAlpha(this.animation.getOutput(), new Color(0, 0, 0, 3)))).radius(new QuadRadiusState(3.0F)).smoothness(1.15F).build();
      rectangle.render(matrix, x, y);
      BuiltBorder border = (BuiltBorder)Builder.border().size(new SizeState(width, height)).color(new QuadColorState(ColorUtil.setAlpha(this.animation.getOutput(), new Color(0, 0, 0, 5)))).radius(new QuadRadiusState(4.0F)).thickness(0.01F).smoothness(0.6F, 0.6F).build();
      border.render(matrix, x, y);
      rectangle = Builder.rectangle().size(new SizeState(width * 0.7, height * 0.7)).color(new QuadColorState(ColorUtil.setAlpha(this.animation.getOutput(), theme.color()))).radius(new QuadRadiusState(4.0F)).smoothness(1.15F).build();
      rectangle.render(matrix, x + width * 0.15, y + height * 0.15);
      if (MouseUtil.isHovered(x, y, width, height, mouseX, mouseY) && this.click == 0) {
         WildClient.INSTANCE.getThemeManager().setTheme(theme);
      }

   }

   public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
   }

   public boolean charTyped(char chr, int modifiers) {
      this.searchElement.charTyped(chr, modifiers);
      this.type = String.valueOf(chr);
      return super.charTyped(chr, modifiers);
   }

   public boolean mouseReleased(double mouseX, double mouseY, int button) {
      this.modules.forEach((module) -> module.getSettings().stream().filter((setting) -> setting instanceof FloatSetting).forEach((setting) -> ((FloatSetting)setting).setSlide(false)));
      return super.mouseReleased(mouseX, mouseY, button);
   }

   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      this.searchElement.keyPressed(keyCode, scanCode, modifiers);
      this.key = keyCode;
      boolean bindKey = false;

      for(Module m : this.modules) {
         if (m != null && m.isBinded()) {
            int endKey = keyCode != 261 && keyCode != 259 && keyCode != 256 ? keyCode : -1;
            bindKey = true;
            m.setBind(endKey);
            m.setBinded(false);
         }
      }

      if (bindKey) {
         return false;
      } else if (keyCode != WildClient.INSTANCE.getModuleManager().getByClass(GuiModule.class).getBind() && keyCode != 256) {
         return super.keyPressed(keyCode, scanCode, modifiers);
      } else {
         this.animation.setDirection(Direction.BACKWARDS);
         return false;
      }
   }

   public Category getCategory() {
      return this.category;
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      double searchX = this.x + 60.0D + (this.widthGui - 50.0D - 150.0D);
      double searchY = this.y + 5.0D;
      double searchWidth = 135.0D;
      double searchHeight = 30.0D;
      
      if (!MouseUtil.isHovered(searchX, searchY, searchWidth, searchHeight, mouseX, mouseY)) {
         this.searchElement.setSelect(false);
      }
      
      this.click = button;
      this.key = 1330 + button;
      return true;
   }

   public void setScroll(double scroll) {
      this.mainScroll = (int)scroll;
      this.preScroll = (int)scroll;
   }

   public void setCategory(Category category) {
      this.category = category;
   }

   public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
      this.preScroll += (int)(verticalAmount * 30.0D);
      return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
   }
}
