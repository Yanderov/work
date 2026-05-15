package fun.Yanderov.display.screens.clickgui.components.implement.autobuy.window;

import fun.Yanderov.Yanderov;
import fun.Yanderov.display.screens.clickgui.components.AbstractComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.autobuy.items.AutoBuyableItem;
import fun.Yanderov.display.screens.clickgui.components.implement.autobuy.settings.AutoBuyItemSettings;
import fun.Yanderov.display.screens.clickgui.components.implement.autobuy.settings.AutoBuySettingsComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.window.AbstractWindow;
import fun.Yanderov.utils.display.color.ColorAssist;
import fun.Yanderov.utils.display.font.Fonts;
import fun.Yanderov.utils.display.geometry.Render2D;
import fun.Yanderov.utils.display.scissor.ScissorAssist;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import fun.Yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1799;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;

public class AutoBuyItemSettingsWindow extends AbstractWindow {
   private final List components = new ArrayList();
   public final AutoBuyableItem item;
   private final AutoBuyItemSettings settings;

   public AutoBuyItemSettingsWindow(AutoBuyableItem item, AutoBuyItemSettings settings) {
      this.item = item;
      this.settings = settings;
      this.initializeComponents();
      this.draggable(true);
   }

   private void initializeComponents() {
      this.components.add(new AutoBuySettingsComponent.BuyBelowComponent(this.settings));
      if (this.settings.isCanHaveQuantity()) {
         this.components.add(new AutoBuySettingsComponent.MinQuantityComponent(this.settings));
      }

   }

   public void drawWindow(class_332 context, int mouseX, int mouseY, float delta) {
      class_4587 matrix = context.method_51448();
      ScissorAssist scissorManager = Yanderov.getInstance().getScissorManager();
      this.height = (float)class_3532.method_15340(this.getComponentHeight() + 5, 0, 200);
      blur.render(ShapeProperties.create(context.method_51448(), (double)this.x, (double)this.y, (double)this.width, (double)this.height).round(8.0F).quality(64.0F).color((new Color(0, 0, 0, 200)).getRGB()).build());
      rectangle.render(ShapeProperties.create(context.method_51448(), (double)this.x, (double)this.y, (double)this.width, (double)this.height).round(8.0F).softness(2.0F).thickness(0.5F).outlineColor((new Color(18, 19, 20, 225)).getRGB()).color((new Color(18, 19, 20, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(18, 19, 20, 175)).getRGB()).build());
      rectangle.render(ShapeProperties.create(matrix, (double)this.x, (double)(this.y + 22.0F), (double)this.width, (double)0.5F).round(8.0F).color((new Color(155, 155, 155, 55)).getRGB()).build());
      class_1799 itemStack = this.item.createItemStack();
      Render2D.defaultDrawStack(context, itemStack, this.x + 7.0F, this.y + 4.0F, false, false, 0.8F);
      String title = this.item.getDisplayName();
      Fonts.getSize(15, Fonts.Type.SEMI).drawGradientString(context.method_51448(), title, (double)(this.x + 25.0F), (double)(this.y + 10.0F), ColorAssist.getText(), (new Color(165, 165, 165, 255)).getRGB());
      Fonts.getSize(17, Fonts.Type.ICONS).drawString(context.method_51448(), "K", (double)(this.x + this.width - 15.0F), (double)(this.y + 10.0F), ColorAssist.getText(0.5F));
      boolean isLimitedHeight = class_3532.method_15363(this.height, 0.0F, 200.0F) == 200.0F;
      if (isLimitedHeight) {
         scissorManager.push(matrix.method_23760().method_23761(), this.x, this.y + 23.0F, this.width, this.height - 24.0F);
      }

      float offset = 0.0F;
      int totalHeight = 0;

      for(int i = this.components.size() - 1; i >= 0; --i) {
         AutoBuySettingsComponent component = (AutoBuySettingsComponent)this.components.get(i);
         component.x = this.x;
         component.y = (float)((double)(this.y + 22.0F + offset + ((float)(this.getComponentHeight() - 25) - component.height)) + this.smoothedScroll);
         component.width = this.width;
         component.render(context, mouseX, mouseY, delta);
         offset -= component.height;
         totalHeight += (int)component.height;
      }

      if (isLimitedHeight) {
         scissorManager.pop();
      }

      int maxScroll = (int)Math.max(0.0F, (float)totalHeight - (this.height - 28.0F));
      this.scroll = class_3532.method_15350(this.scroll, (double)(-maxScroll), (double)0.0F);
      this.smoothedScroll = class_3532.method_16436((double)0.1F, this.smoothedScroll, this.scroll);
      if (isLimitedHeight) {
         float viewableHeight = this.height - 30.0F;
         float scrollbarHeight = Math.max(20.0F, viewableHeight / (float)totalHeight * viewableHeight);
         float scrollPercent = (float)(-this.smoothedScroll / (double)((float)maxScroll));
         float scrollbarY = this.y + 30.0F + scrollPercent * (viewableHeight - scrollbarHeight);
         float scrollbarX = this.x + this.width - 6.0F;
         float scrollbarWidth = 3.0F;
         rectangle.render(ShapeProperties.create(matrix, (double)scrollbarX, (double)(this.y + 30.0F), (double)scrollbarWidth, (double)(viewableHeight - 6.0F)).round(1.0F).color((new Color(30, 30, 30, 100)).getRGB()).build());
         rectangle.render(ShapeProperties.create(matrix, (double)scrollbarX, (double)scrollbarY, (double)scrollbarWidth, (double)(scrollbarHeight - 6.0F)).round(1.5F).color((new Color(100, 100, 100, 180)).getRGB()).build());
      }

   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      if (button == 0) {
         if (Calculate.isHovered(mouseX, mouseY, (double)(this.x + this.width - 20.0F), (double)(this.y + 5.0F), (double)15.0F, (double)15.0F)) {
            this.startCloseAnimation();
            return true;
         }

         if (Calculate.isHovered(mouseX, mouseY, (double)this.x, (double)this.y, (double)this.width, (double)19.0F)) {
            this.dragging = true;
            this.dragX = (int)((double)this.x - mouseX);
            this.dragY = (int)((double)this.y - mouseY);
            return true;
         }
      }

      boolean isAnyComponentHovered = this.components.stream().anyMatch((abstractComponent) -> abstractComponent.isHover(mouseX, mouseY));
      if (isAnyComponentHovered) {
         this.components.forEach((abstractComponent) -> {
            if (abstractComponent.isHover(mouseX, mouseY)) {
               abstractComponent.mouseClicked(mouseX, mouseY, button);
            }

         });
         return true;
      } else {
         this.components.forEach((abstractComponent) -> abstractComponent.mouseClicked(mouseX, mouseY, button));
         return true;
      }
   }

   public boolean isHover(double mouseX, double mouseY) {
      this.components.forEach((abstractComponentx) -> abstractComponentx.isHover(mouseX, mouseY));

      for(AbstractComponent abstractComponent : this.components) {
         if (abstractComponent.isHover(mouseX, mouseY)) {
            return true;
         }
      }

      return super.isHovered(mouseX, mouseY);
   }

   public boolean mouseReleased(double mouseX, double mouseY, int button) {
      this.dragging = false;
      this.components.forEach((abstractComponent) -> abstractComponent.mouseReleased(mouseX, mouseY, button));
      return super.mouseReleased(mouseX, mouseY, button);
   }

   public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
      boolean scrolled = class_3532.method_15363(this.height, 0.0F, 200.0F) == 200.0F && Calculate.isHovered(mouseX, mouseY, (double)this.x, (double)this.y, (double)this.width, (double)this.height);
      if (scrolled) {
         this.scroll += amount * (double)20.0F;
      }

      this.components.forEach((abstractComponent) -> abstractComponent.mouseScrolled(mouseX, mouseY, amount));
      return scrolled;
   }

   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      this.components.forEach((abstractComponent) -> abstractComponent.keyPressed(keyCode, scanCode, modifiers));
      return super.keyPressed(keyCode, scanCode, modifiers);
   }

   public boolean charTyped(char chr, int modifiers) {
      this.components.forEach((abstractComponent) -> abstractComponent.charTyped(chr, modifiers));
      return super.charTyped(chr, modifiers);
   }

   public int getComponentHeight() {
      float offsetY = 0.0F;

      for(AutoBuySettingsComponent component : this.components) {
         offsetY += component.height;
      }

      return (int)(offsetY + 25.0F);
   }
}

