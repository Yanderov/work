package dev.client.yanderov.display.screens.clickgui.components.implement.module;

import dev.client.yanderov.common.animation.Animation;
import dev.client.yanderov.common.animation.Direction;
import dev.client.yanderov.common.animation.implement.Decelerate;
import dev.client.yanderov.display.screens.clickgui.MenuScreen;
import dev.client.yanderov.display.screens.clickgui.components.AbstractComponent;
import dev.client.yanderov.display.screens.clickgui.components.implement.other.StatusRender;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.setting.SettingComponentAdder;
import dev.client.yanderov.utils.client.chat.StringHelper;
import dev.client.yanderov.utils.display.font.Fonts;
import dev.client.yanderov.utils.display.shape.ShapeProperties;
import dev.client.yanderov.utils.display.shape.implement.Rectangle;
import dev.client.yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_332;
import net.minecraft.class_9848;

public class ModuleComponent extends AbstractComponent {
   private final List components = new ArrayList();
   private final StatusRender statusRender = new StatusRender();
   private final Module module;
   private boolean binding;
   private final Rectangle rectangle = new Rectangle();
   private final Animation colorAnimation = (new Decelerate()).setMs(400).setValue((double)9.0F);
   private final Animation alphaAnimation = (new Decelerate()).setMs(400).setValue((double)105.0F);

   public ModuleComponent(Module module) {
      this.module = module;
      (new SettingComponentAdder()).addSettingComponent(module.settings(), this.components);
      this.colorAnimation.setDirection(module.isState() ? Direction.FORWARDS : Direction.BACKWARDS);
      this.alphaAnimation.setDirection(module.isState() ? Direction.FORWARDS : Direction.BACKWARDS);
      this.colorAnimation.reset();
      this.alphaAnimation.reset();
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      boolean noSettings = this.module.settings().isEmpty();
      float headerHeight = noSettings ? 18.0F : 18.0F;
      float nameY = noSettings ? 9.5F : 8.0F;
      String point = "â€¢ ";
      String description = ModuleDescriptions.getDescription(this.module);
      float maxWidth = this.width - 25.0F;
      float currentX = this.x + 10.0F;
      float currentY = this.y + 15.0F;
      String[] words = description.split(" ");
      new StringBuilder();
      int lineCount = 1;

      for(String word : words) {
         float wordWidth = Fonts.getSize(12, Fonts.Type.DEFAULT).getStringWidth(word + " ");
         if (currentX + wordWidth > this.x + maxWidth) {
            ++lineCount;
            currentX = this.x + 10.0F;
         }

         currentX += wordWidth;
      }

      float descHeight = lineCount == 1 ? (float)lineCount * Fonts.getSize(12, Fonts.Type.DEFAULT).getStringHeight(" ") - 13.0F : (float)lineCount * Fonts.getSize(12, Fonts.Type.DEFAULT).getStringHeight(" ") - 20.0F;
      this.colorAnimation.setDirection(this.module.isState() ? Direction.FORWARDS : Direction.BACKWARDS);
      this.alphaAnimation.setDirection(this.module.isState() ? Direction.FORWARDS : Direction.BACKWARDS);
      int brightnessOffset = this.colorAnimation.getOutput().intValue();
      int alphaOffset = 150 + this.alphaAnimation.getOutput().intValue();
      blur.render(ShapeProperties.create(context.method_51448(), (double)this.x, (double)this.y, (double)this.width, (double)(this.height = (float)this.getComponentHeight())).round(5.0F).color((new Color(0, 0, 0, 200)).getRGB()).build());
      this.rectangle.render(ShapeProperties.create(context.method_51448(), (double)this.x, (double)this.y, (double)this.width, (double)(this.height = (float)this.getComponentHeight())).round(5.0F).color((new Color(23, 24, 25, 145)).getRGB(), (new Color(Math.min(19 + brightnessOffset, 255), Math.min(19 + brightnessOffset, 255), Math.min(21 + brightnessOffset, 255), 255)).getRGB(), (new Color(10, 12, 15, 145)).getRGB(), (new Color(Math.min(19 + brightnessOffset, 255), Math.min(19 + brightnessOffset, 255), Math.min(21 + brightnessOffset, 255), 255)).getRGB()).build());
      this.rectangle.render(ShapeProperties.create(context.method_51448(), (double)this.x, (double)(this.y + descHeight + 25.0F), (double)this.width, (double)1.0F).color((new Color(25, 25, 40, 155)).getRGB(), (new Color(55, 55, 60, 155)).getRGB(), (new Color(55, 55, 60, 155)).getRGB(), (new Color(25, 25, 40, 155)).getRGB()).build());
      if (!this.module.settings().isEmpty()) {
         Fonts.getSize(18, Fonts.Type.GUIICONS).drawString(context.method_51448(), "A", (double)(this.x + 7.0F), (double)(this.y + descHeight + 6.0F + 27.0F), (new Color(128, 128, 128, 255)).getRGB());
         Fonts.getSize(16, Fonts.Type.GUIICONS).drawString(context.method_51448(), "B", (double)(this.x + 20.0F), (double)(this.y + descHeight + 6.0F + 27.5F), (new Color(128, 128, 128, 255)).getRGB());
      } else {
         Fonts.getSize(18, Fonts.Type.GUIICONS).drawString(context.method_51448(), "A", (double)(this.x + 7.0F), (double)(this.y + descHeight + 6.0F + 27.0F), (new Color(128, 128, 128, 255)).getRGB());
      }

      StatusRender var10000 = this.statusRender.position(this.x + this.width - 16.0F, this.y + descHeight + 5.5F + 25.5F);
      Module var10001 = this.module;
      Objects.requireNonNull(var10001);
      var10000.setRunnable(var10001::switchState).setState(this.module.isState()).render(context, mouseX, mouseY, delta);
      Fonts.getSize(15, Fonts.Type.DEFAULT).drawString(context.method_51448(), point + this.module.getVisibleName(), (double)(this.x + 11.0F), (double)(this.y + nameY - 1.0F), (new Color(255, 255, 255, alphaOffset)).getRGB());
      currentX = this.x + 10.0F;
      currentY = this.y + 19.0F;
      StringBuilder line = new StringBuilder();
      int currentLine = 1;

      for(String word : words) {
         float wordWidth = Fonts.getSize(12, Fonts.Type.DEFAULT).getStringWidth(word + " ");
         if (currentX + wordWidth > this.x + maxWidth) {
            if (currentLine == 1) {
               Fonts.getSize(14, Fonts.Type.GUIICONS).drawString(context.method_51448(), "C", (double)(this.x + 6.5F), (double)(currentY + 0.5F), (new Color(128, 128, 128, 255)).getRGB());
               Fonts.getSize(12, Fonts.Type.DEFAULT).drawString(context.method_51448(), line.toString(), (double)(this.x + 15.0F), (double)currentY, (new Color(128, 128, 128, 186)).getRGB());
            } else {
               Fonts.getSize(12, Fonts.Type.DEFAULT).drawString(context.method_51448(), line.toString(), (double)(this.x + 5.0F), (double)currentY, (new Color(128, 128, 128, 186)).getRGB());
            }

            line = new StringBuilder();
            currentY += Fonts.getSize(12, Fonts.Type.DEFAULT).getStringHeight(word) - 7.0F;
            currentX = this.x + 10.0F;
            ++currentLine;
         }

         line.append(word).append(" ");
         currentX += wordWidth;
      }

      if (!line.isEmpty()) {
         if (currentLine == 1) {
            Fonts.getSize(14, Fonts.Type.GUIICONS).drawString(context.method_51448(), "C", (double)(this.x + 6.5F), (double)(currentY + 0.5F), (new Color(128, 128, 128, 255)).getRGB());
            Fonts.getSize(12, Fonts.Type.DEFAULT).drawString(context.method_51448(), line.toString(), (double)(this.x + 15.0F), (double)currentY, (new Color(128, 128, 128, 186)).getRGB());
         } else {
            Fonts.getSize(12, Fonts.Type.DEFAULT).drawString(context.method_51448(), line.toString(), (double)(this.x + 7.0F), (double)currentY, (new Color(128, 128, 128, 186)).getRGB());
         }
      }

      this.drawBind(context, descHeight);
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      if (Calculate.isHovered(mouseX, mouseY, (double)this.x, (double)this.y, (double)this.width, (double)this.getComponentHeight()) && button == 1 && !this.module.settings().isEmpty()) {
         if (MenuScreen.windowManager.getWindows().stream().noneMatch((w) -> w instanceof ModuleSettingsWindow && ((ModuleSettingsWindow)w).module.equals(this.module))) {
            ModuleSettingsWindow settingsWindow = new ModuleSettingsWindow(this.module);
            settingsWindow.position((float)(MenuScreen.INSTANCE.x + MenuScreen.INSTANCE.width + 24), (float)MenuScreen.INSTANCE.y).size(160.0F, (float)settingsWindow.getComponentHeight());
            MenuScreen.windowManager.add(settingsWindow);
         }

         return true;
      } else {
         String bindName = StringHelper.getBindName(this.module.getKey());
         String description = ModuleDescriptions.getDescription(this.module);
         float maxWidth = this.width - 25.0F;
         float currentX = this.x + 10.0F;
         int lineCount = 1;
         String[] words = description.split(" ");

         for(String word : words) {
            float wordWidth = Fonts.getSize(12, Fonts.Type.DEFAULT).getStringWidth(word + " ");
            if (currentX + wordWidth > this.x + maxWidth) {
               ++lineCount;
               currentX = this.x + 10.0F;
            }

            currentX += wordWidth;
         }

         float descHeight = lineCount == 1 ? (float)lineCount * Fonts.getSize(12, Fonts.Type.DEFAULT).getStringHeight(" ") - 13.0F : (float)lineCount * Fonts.getSize(12, Fonts.Type.DEFAULT).getStringHeight(" ") - 20.0F;
         float stringWidth = this.module.getKey() < 0 ? 10.0F : Fonts.getSize(12, Fonts.Type.DEFAULT).getStringWidth(bindName);
         float bindX = this.module.settings().isEmpty() ? this.x + this.width - 37.5F - stringWidth : this.x + this.width - 37.5F - stringWidth;
         float bindY = this.module.settings().isEmpty() ? this.y + descHeight + 5.5F + 27.0F : this.y + descHeight + 5.5F + 27.0F;
         if (Calculate.isHovered(mouseX, mouseY, (double)bindX, (double)bindY, (double)(stringWidth + 6.0F), (double)9.0F) && button == 0) {
            this.binding = !this.binding;
         } else if (this.binding) {
            this.module.setKey(button);
            this.binding = false;
         }

         this.statusRender.mouseClicked(mouseX, mouseY, button);
         return super.mouseClicked(mouseX, mouseY, button);
      }
   }

   public boolean isHover(double mouseX, double mouseY) {
      return Calculate.isHovered(mouseX, mouseY, (double)this.x, (double)this.y, (double)this.width, (double)this.height);
   }

   public void tick() {
      this.components.forEach(AbstractComponent::tick);
      super.tick();
   }

   public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
      return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
   }

   public boolean mouseReleased(double mouseX, double mouseY, int button) {
      return super.mouseReleased(mouseX, mouseY, button);
   }

   public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
      return super.mouseScrolled(mouseX, mouseY, amount);
   }

   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      int key = keyCode == 261 ? -1 : keyCode;
      if (this.binding) {
         this.module.setKey(key);
         this.binding = false;
      }

      return super.keyPressed(keyCode, scanCode, modifiers);
   }

   public boolean charTyped(char chr, int modifiers) {
      return super.charTyped(chr, modifiers);
   }

   public int getComponentHeight() {
      String description = ModuleDescriptions.getDescription(this.module);
      float maxWidth = this.width - 25.0F;
      float currentX = this.x + 10.0F;
      int lineCount = 1;
      String[] words = description.split(" ");

      for(String word : words) {
         float wordWidth = Fonts.getSize(12, Fonts.Type.DEFAULT).getStringWidth(word + " ");
         if (currentX + wordWidth > this.x + maxWidth) {
            ++lineCount;
            currentX = this.x + 10.0F;
         }

         currentX += wordWidth;
      }

      float descHeight = lineCount == 1 ? (float)lineCount * Fonts.getSize(12, Fonts.Type.DEFAULT).getStringHeight(" ") - 13.0F : (float)lineCount * Fonts.getSize(12, Fonts.Type.DEFAULT).getStringHeight(" ") - 20.0F;
      return (int)(this.module.settings().isEmpty() ? 45.0F + descHeight : 45.0F + descHeight);
   }

   private void drawBind(class_332 context, float descHeight) {
      String bindName = StringHelper.getBindName(this.module.getKey());
      String name = this.binding ? "..." : bindName;
      float stringWidth = this.module.getKey() < 0 && !this.binding ? 10.0F : Fonts.getSize(12, Fonts.Type.DEFAULT).getStringWidth(name);
      float bindX = this.module.settings().isEmpty() ? this.x + this.width - 37.5F - stringWidth : this.x + this.width - 37.5F - stringWidth;
      float back = this.module.settings().isEmpty() ? this.y + descHeight + 6.0F + 23.75F : this.y + descHeight + 6.0F + 23.75F;
      this.rectangle.render(ShapeProperties.create(context.method_51448(), (double)(bindX + 0.25F), (double)back, (double)(stringWidth + 6.0F), (double)10.0F).round(3.0F).outlineColor((new Color(155, 155, 165, 255)).getRGB()).color((new Color(61, 67, 71, 80)).getRGB(), (new Color(71, 77, 81, 80)).getRGB(), (new Color(81, 87, 91, 80)).getRGB(), (new Color(91, 97, 101, 80)).getRGB()).build());
      int bindingColor = class_9848.method_61324(255, 135, 136, 148);
      float textX = this.module.settings().isEmpty() ? this.x + this.width - 34.5F - stringWidth : this.x + this.width - 34.5F - stringWidth;
      float textY = this.module.settings().isEmpty() ? this.y + descHeight + 6.0F + 28.0F : this.y + descHeight + 6.0F + 28.0F;
      if (this.module.getKey() < 0 && !this.binding) {
         Fonts.getSize(22, Fonts.Type.GUIICONS).drawString(context.method_51448(), "G", (double)(this.x + this.width - 34.5F - 10.0F), (double)(this.y + descHeight + 6.0F + 26.0F), (new Color(128, 128, 128, 255)).getRGB());
      } else {
         Fonts.getSize(12, Fonts.Type.DEFAULT).drawString(context.method_51448(), name, (double)textX, (double)textY, bindingColor);
      }

   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         ModuleComponent that = (ModuleComponent)o;
         return this.module.equals(that.module);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.module});
   }

   public List getComponents() {
      return this.components;
   }

   public StatusRender getStatusRender() {
      return this.statusRender;
   }

   public Module getModule() {
      return this.module;
   }

   public boolean isBinding() {
      return this.binding;
   }

   public Rectangle getRectangle() {
      return this.rectangle;
   }

   public Animation getColorAnimation() {
      return this.colorAnimation;
   }

   public Animation getAlphaAnimation() {
      return this.alphaAnimation;
   }
}

