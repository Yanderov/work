package fun.Yanderov.display.screens.clickgui.components.implement.category;

import fun.Yanderov.Yanderov;
import fun.Yanderov.common.animation.Animation;
import fun.Yanderov.common.animation.Direction;
import fun.Yanderov.common.animation.implement.InOutBack;
import fun.Yanderov.display.screens.clickgui.MenuScreen;
import fun.Yanderov.display.screens.clickgui.components.AbstractComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.module.ModuleComponent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.utils.display.color.ColorAssist;
import fun.Yanderov.utils.display.font.Fonts;
import fun.Yanderov.utils.display.scissor.ScissorAssist;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import fun.Yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import org.joml.Matrix4f;

public class CategoryComponent extends AbstractComponent {
   private final List moduleComponents = new ArrayList();
   private static final Set globalModuleComponents = new HashSet();
   private final ModuleCategory category;
   private final Animation alphaAnimation = (new InOutBack()).setMs(300).setValue((double)1.0F);
   private final Animation scaleAnimation = (new InOutBack()).setMs(300).setValue((double)1.0F);
   private boolean initializedAnimations = false;
   private float scroll = 0.0F;
   private float smoothedScroll = 0.0F;

   private void initializeModules() {
      for(Module module : Yanderov.getInstance().getModuleRepository().modules()) {
         ModuleComponent newComponent = new ModuleComponent(module);
         if (globalModuleComponents.add(newComponent)) {
            this.moduleComponents.add(newComponent);
         }
      }

   }

   public void postInitialize() {
      if (!this.initializedAnimations) {
         if (MenuScreen.INSTANCE.getCategory().equals(this.category)) {
            this.alphaAnimation.setDirection(Direction.FORWARDS);
            this.scaleAnimation.setDirection(Direction.FORWARDS);
            this.alphaAnimation.reset();
            this.scaleAnimation.reset();
            this.alphaAnimation.setMs(0);
            this.scaleAnimation.setMs(0);
         } else {
            this.alphaAnimation.setDirection(Direction.BACKWARDS);
            this.scaleAnimation.setDirection(Direction.BACKWARDS);
            this.alphaAnimation.reset();
            this.scaleAnimation.reset();
            this.alphaAnimation.setMs(0);
            this.scaleAnimation.setMs(0);
         }

         this.initializedAnimations = true;
      }

   }

   public CategoryComponent(ModuleCategory category) {
      this.category = category;
      this.initializeModules();
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      this.postInitialize();
      MenuScreen menuScreen = MenuScreen.INSTANCE;
      globalModuleComponents.clear();
      Matrix4f positionMatrix = context.method_51448().method_23760().method_23761();
      ScissorAssist scissorManager = Yanderov.getInstance().getScissorManager();
      this.drawCategoryTab(context, context.method_51448(), mouseX, mouseY);
      int[] offsets = this.calculateOffsets();
      int columnWidth = 142;
      int column = 0;
      int maxScroll = 0;
      float offsetX = 35.0F;
      float offsetY = 14.0F;
      scissorManager.push(positionMatrix, (float)menuScreen.x + offsetX - 75.0F, (float)menuScreen.y + offsetY + 15.0F, (float)menuScreen.width - offsetX + 150.0F, (float)menuScreen.height - offsetY - 15.0F);
      float renderScroll = (float)Math.round(this.smoothedScroll);

      for(int i = this.moduleComponents.size() - 1; i >= 0; --i) {
         ModuleComponent component = (ModuleComponent)this.moduleComponents.get(i);
         if (this.shouldRenderComponent(component)) {
            int componentHeight = component.getComponentHeight() + 9;
            component.x = (float)(menuScreen.x + 32 + column * (columnWidth + 48));
            component.y = (float)(menuScreen.y + 35 + offsets[column] - componentHeight) + renderScroll;
            component.width = (float)(columnWidth + 40);
            if (component.y > (float)(menuScreen.y - componentHeight) && (float)(menuScreen.y + menuScreen.height + 15) > component.y) {
               component.render(context, mouseX, mouseY, delta);
            }

            offsets[column] -= componentHeight;
            maxScroll = Math.max(maxScroll, offsets[column]);
            column = (column + 1) % 2;
         }
      }

      scissorManager.pop();
      int clamped = class_3532.method_15340(maxScroll - (menuScreen.height / 2 + 35), 0, maxScroll);
      this.scroll = class_3532.method_15363(this.scroll, (float)(-clamped), 0.0F);
      this.smoothedScroll = Calculate.interpolateSmooth((double)2.0F, this.smoothedScroll, this.scroll);
      if (clamped > 0) {
         float scrollbarWidth = 4.0F;
         float scrollbarX = (float)(menuScreen.x + menuScreen.width) - offsetX - scrollbarWidth + 50.0F;
         float scrollbarY = (float)menuScreen.y + offsetY + 22.0F;
         float scrollbarHeight = (float)menuScreen.height - offsetY * 2.0F - 14.0F;
         rectangle.render(ShapeProperties.create(context.method_51448(), (double)scrollbarX, (double)scrollbarY, (double)scrollbarWidth, (double)scrollbarHeight).round(2.0F).color((new Color(35, 35, 35, 75)).getRGB(), (new Color(45, 45, 45, 95)).getRGB(), (new Color(45, 45, 45, 95)).getRGB(), (new Color(35, 35, 35, 75)).getRGB()).build());
         float contentHeight = (float)clamped;
         float viewHeight = (float)menuScreen.height - offsetY * 2.0F;
         float handleHeight = Math.max(20.0F, viewHeight * (viewHeight / (contentHeight + viewHeight)));
         float scrollRatio = -renderScroll / contentHeight;
         float handleY = scrollbarY + (scrollbarHeight - handleHeight) * scrollRatio;
         rectangle.render(ShapeProperties.create(context.method_51448(), (double)scrollbarX, (double)handleY, (double)scrollbarWidth, (double)handleHeight).round(2.0F).color((new Color(44, 44, 44, 75)).getRGB(), (new Color(101, 101, 101, 95)).getRGB(), (new Color(101, 101, 101, 95)).getRGB(), (new Color(44, 44, 44, 75)).getRGB()).build());
      }

   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      MenuScreen menuScreen = MenuScreen.INSTANCE;
      float scale = 0.5F + this.scaleAnimation.getOutput().floatValue() * 0.5F;
      float baseWidth = 20.0F;
      float baseHeight = 20.0F;
      float scaledWidth = baseWidth * scale;
      float scaledHeight = baseHeight * scale;
      float baseX = ModuleCategory.RENDER.equals(this.category) ? this.x + 4.65F : (ModuleCategory.MOVEMENT.equals(this.category) ? this.x + 4.75F : this.x + 5.25F);
      float baseY = this.y;
      float centerX = baseX + baseWidth / 2.0F;
      float centerY = baseY + baseHeight / 2.0F;
      float var10000 = centerX - scaledWidth / 2.0F;
      var10000 = centerY - scaledHeight / 2.0F;
      float hoverX = ModuleCategory.RENDER.equals(this.category) ? this.x + 4.65F : (ModuleCategory.MOVEMENT.equals(this.category) ? this.x + 4.75F : this.x + 5.25F);
      float hoverY = this.y;
      if (Calculate.isHovered(mouseX, mouseY, (double)hoverX, (double)hoverY, (double)baseWidth, (double)baseHeight) && button == 0) {
         MenuScreen.INSTANCE.setCategory(this.category);
         this.alphaAnimation.setMs(300);
         this.scaleAnimation.setMs(300);
         this.alphaAnimation.setDirection(Direction.FORWARDS);
         this.scaleAnimation.setDirection(Direction.FORWARDS);
         return true;
      } else {
         float offsetX = 35.0F;
         float offsetY = 14.0F;
         if (Calculate.isHovered(mouseX, mouseY, (double)((float)menuScreen.x + offsetX - 75.0F), (double)((float)menuScreen.y + offsetY), (double)((float)menuScreen.width - offsetX + 150.0F), (double)((float)menuScreen.height - offsetY + 15.0F))) {
            for(int i = 0; i < this.moduleComponents.size(); ++i) {
               ModuleComponent moduleComponent = (ModuleComponent)this.moduleComponents.get(i);
               if (this.shouldRenderComponent(moduleComponent) && moduleComponent.isHover(mouseX, mouseY)) {
                  return moduleComponent.mouseClicked(mouseX, mouseY, button);
               }
            }
         }

         return super.mouseClicked(mouseX, mouseY, button);
      }
   }

   public boolean isHover(double mouseX, double mouseY) {
      float scale = 0.5F + this.scaleAnimation.getOutput().floatValue() * 0.5F;
      float baseWidth = 20.0F;
      float baseHeight = 20.0F;
      float scaledWidth = baseWidth * scale;
      float scaledHeight = baseHeight * scale;
      float baseX = ModuleCategory.RENDER.equals(this.category) ? this.x + 4.65F : (ModuleCategory.MOVEMENT.equals(this.category) ? this.x + 4.75F : this.x + 5.25F);
      float baseY = this.y;
      float centerX = baseX + baseWidth / 2.0F;
      float centerY = baseY + baseHeight / 2.0F;
      float scaledX = centerX - scaledWidth / 2.0F;
      float scaledY = centerY - scaledHeight / 2.0F;
      boolean isHovered = Calculate.isHovered(mouseX, mouseY, (double)scaledX, (double)scaledY, (double)scaledWidth, (double)scaledHeight);
      if (isHovered) {
         return true;
      } else {
         this.moduleComponents.forEach((moduleComponentx) -> moduleComponentx.isHover(mouseX, mouseY));

         for(ModuleComponent moduleComponent : this.moduleComponents) {
            if (moduleComponent.isHover(mouseX, mouseY)) {
               return true;
            }
         }

         return super.isHover(mouseX, mouseY);
      }
   }

   public boolean mouseReleased(double mouseX, double mouseY, int button) {
      this.moduleComponents.forEach((moduleComponent) -> moduleComponent.mouseReleased(mouseX, mouseY, button));
      return super.mouseReleased(mouseX, mouseY, button);
   }

   public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
      MenuScreen menuScreen = MenuScreen.INSTANCE;
      float offsetX = 35.0F;
      float offsetY = 13.0F;
      if (Calculate.isHovered(mouseX, mouseY, (double)((float)menuScreen.x + offsetX), (double)((float)menuScreen.y + offsetY), (double)((float)menuScreen.width - offsetX + 7.0F), (double)((float)menuScreen.height - offsetY + 15.0F))) {
         this.scroll = (float)((double)this.scroll + amount * (double)20.0F);
      }

      this.moduleComponents.forEach((moduleComponent) -> {
         if (this.shouldRenderComponent(moduleComponent)) {
            moduleComponent.mouseScrolled(mouseX, mouseY, amount);
         }

      });
      return super.mouseScrolled(mouseX, mouseY, amount);
   }

   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      this.moduleComponents.forEach((moduleComponent) -> {
         if (this.shouldRenderComponent(moduleComponent)) {
            moduleComponent.keyPressed(keyCode, scanCode, modifiers);
         }

      });
      return super.keyPressed(keyCode, scanCode, modifiers);
   }

   public boolean charTyped(char chr, int modifiers) {
      this.moduleComponents.forEach((moduleComponent) -> {
         if (this.shouldRenderComponent(moduleComponent)) {
            moduleComponent.charTyped(chr, modifiers);
         }

      });
      return super.charTyped(chr, modifiers);
   }

   private void drawCategoryTab(class_332 context, class_4587 matrix, int mouseX, int mouseY) {
      this.alphaAnimation.setDirection(MenuScreen.INSTANCE.getCategory().equals(this.category) ? Direction.FORWARDS : Direction.BACKWARDS);
      this.scaleAnimation.setDirection(MenuScreen.INSTANCE.getCategory().equals(this.category) ? Direction.FORWARDS : Direction.BACKWARDS);
      float anim = this.alphaAnimation.getOutput().floatValue();
      float scale = 0.5F + this.scaleAnimation.getOutput().floatValue() * 0.5F;
      int alpha = class_3532.method_15340((int)(anim * 135.0F), 0, 135);
      float baseWidth = 20.0F;
      float baseHeight = 20.0F;
      float scaledWidth = baseWidth * scale;
      float scaledHeight = baseHeight * scale;
      float baseX = ModuleCategory.RENDER.equals(this.category) ? this.x + 4.65F : (ModuleCategory.MOVEMENT.equals(this.category) ? this.x + 4.75F : this.x + 5.25F);
      float baseY = this.y;
      float centerX = baseX + baseWidth / 2.0F;
      float centerY = baseY + baseHeight / 2.0F;
      float scaledX = centerX - scaledWidth / 2.0F;
      float scaledY = centerY - scaledHeight / 2.0F;
      float hoverX = ModuleCategory.RENDER.equals(this.category) ? this.x + 4.65F : (ModuleCategory.MOVEMENT.equals(this.category) ? this.x + 4.75F : this.x + 5.25F);
      float hoverY = this.y;
      if (!MenuScreen.INSTANCE.getCategory().equals(this.category) && Calculate.isHovered((double)mouseX, (double)mouseY, (double)hoverX, (double)hoverY, (double)baseWidth, (double)baseHeight)) {
         rectangle.render(ShapeProperties.create(matrix, (double)hoverX, (double)hoverY, (double)baseWidth, (double)baseHeight).round(4.0F).color((new Color(55, 55, 55, 100)).getRGB(), (new Color(85, 85, 100, 100)).getRGB(), (new Color(55, 55, 55, 100)).getRGB(), (new Color(85, 85, 100, 100)).getRGB()).build());
      }

      rectangle.render(ShapeProperties.create(matrix, (double)scaledX, (double)scaledY, (double)scaledWidth, (double)scaledHeight).round(5.0F).color((new Color(21, 21, 21, alpha)).getRGB(), (new Color(61, 61, 61, alpha)).getRGB(), (new Color(61, 61, 61, alpha)).getRGB(), (new Color(21, 21, 21, alpha)).getRGB()).build());
      if (ModuleCategory.COMBAT.equals(this.category)) {
         Fonts.getSize(21, Fonts.Type.ICONSCATEGORY).drawCenteredString(context.method_51448(), "A", (double)(this.x + 16.0F), (double)(this.y + 8.5F), ColorAssist.getText());
      }

      if (ModuleCategory.MOVEMENT.equals(this.category)) {
         Fonts.getSize(23, Fonts.Type.ICONSCATEGORY).drawCenteredString(context.method_51448(), "B", (double)(this.x + 15.0F), (double)(this.y + 7.5F), ColorAssist.getText());
      }

      if (ModuleCategory.RENDER.equals(this.category)) {
         Fonts.getSize(21, Fonts.Type.ICONSCATEGORY).drawCenteredString(context.method_51448(), "C", (double)(this.x + 15.0F), (double)(this.y + 7.5F), ColorAssist.getText());
      }

      if (ModuleCategory.PLAYER.equals(this.category)) {
         Fonts.getSize(23, Fonts.Type.ICONSCATEGORY).drawCenteredString(context.method_51448(), "D", (double)(this.x + 15.0F), (double)(this.y + 7.5F), ColorAssist.getText());
      }

      if (ModuleCategory.MISC.equals(this.category)) {
         Fonts.getSize(21, Fonts.Type.ICONSCATEGORY).drawCenteredString(context.method_51448(), "E", (double)(this.x + 15.5F), (double)(this.y + 7.5F), ColorAssist.getText());
      }

      if (ModuleCategory.CONFIGS.equals(this.category)) {
         Fonts.getSize(21, Fonts.Type.ICONSCATEGORY).drawCenteredString(context.method_51448(), "F", (double)(this.x + 15.5F), (double)(this.y + 7.5F), ColorAssist.getText());
      }

      if (ModuleCategory.AUTOBUY.equals(this.category)) {
         Fonts.getSize(33, Fonts.Type.ICONSCATEGORY).drawCenteredString(context.method_51448(), "H", (double)(this.x + 15.5F), (double)(this.y + 4.0F), ColorAssist.getText());
      }

   }

   private int[] calculateOffsets() {
      int[] offsets = new int[2];
      int column = 0;

      for(int i = this.moduleComponents.size() - 1; i >= 0; --i) {
         ModuleComponent component = (ModuleComponent)this.moduleComponents.get(i);
         if (this.shouldRenderComponent(component)) {
            int componentHeight = component.getComponentHeight() + 9;
            offsets[column] += componentHeight;
            column = (column + 1) % 2;
         }
      }

      return offsets;
   }

   private boolean shouldRenderComponent(ModuleComponent component) {
      MenuScreen menuScreen = MenuScreen.INSTANCE;
      ModuleCategory moduleCategory = component.getModule().getCategory();
      String text = menuScreen.getSearchComponent().getText().toLowerCase();
      String moduleName = component.getModule().getVisibleName().toLowerCase();
      return text.equalsIgnoreCase("") ? moduleCategory.equals(menuScreen.getCategory()) : moduleName.contains(text);
   }
}

