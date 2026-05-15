package dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.autobuyui;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.display.screens.clickgui.MenuScreen;
import dev.client.yanderov.display.screens.clickgui.components.AbstractComponent;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.AutoBuyableItem;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.manager.AutoBuyManager;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.originalitems.ItemRegistry;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.window.AutoBuyItemSettingsWindow;
import dev.client.yanderov.display.screens.clickgui.components.implement.other.StatusRender;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.font.FontRenderer;
import dev.client.yanderov.utils.display.font.Fonts;
import dev.client.yanderov.utils.display.geometry.Render2D;
import dev.client.yanderov.utils.display.scissor.ScissorAssist;
import dev.client.yanderov.utils.display.shape.ShapeProperties;
import dev.client.yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1799;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import org.joml.Matrix4f;

public class AutoBuyGuiComponent extends AbstractComponent {
   private float scroll = 0.0F;
   private float smoothedScroll = 0.0F;
   private final List itemStatusRenders = new ArrayList();
   private final AutoBuyManager autoBuyManager = AutoBuyManager.getInstance();

   public AutoBuyGuiComponent() {
      this.updateItemStatusRenders();
   }

   private void updateItemStatusRenders() {
      this.itemStatusRenders.clear();

      for(AutoBuyableItem item : ItemRegistry.getAllItems()) {
         StatusRender itemStatus = new StatusRender();
         itemStatus.setState(item.isEnabled()).setRunnable(() -> {
            this.autoBuyManager.toggleItem(item);
            this.updateItemStatusRenders();
         });
         this.itemStatusRenders.add(itemStatus);
      }

   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      if (MenuScreen.INSTANCE.getCategory() == ModuleCategory.AUTOBUY) {
         class_4587 matrix = context.method_51448();
         this.renderAllItems(context, matrix, mouseX, mouseY, delta);
      }
   }

   private void renderAllItems(class_332 context, class_4587 matrix, int mouseX, int mouseY, float delta) {
      Matrix4f positionMatrix = matrix.method_23760().method_23761();
      ScissorAssist scissorManager = YanderovIntegration.getInstance().getScissorManager();
      float listX = this.x + 55.0F;
      float listY = this.y + 25.0F;
      float listWidth = this.width - 43.0F - 15.0F;
      float listHeight = this.height - 48.0F;
      float contentHeight = this.calculateContentHeight();
      float maxScrollAmount = Math.max(0.0F, contentHeight - listHeight);
      this.scroll = class_3532.method_15363(this.scroll, -maxScrollAmount, 0.0F);
      this.smoothedScroll = Calculate.interpolate(this.smoothedScroll, this.scroll, 0.15F);
      scissorManager.push(positionMatrix, listX, listY + 4.0F, listWidth, listHeight - 11.0F);
      float itemY = listY + 10.0F + this.smoothedScroll;
      int globalIndex = 0;
      List<AutoBuyableItem> krushItems = ItemRegistry.getKrush();
      if (!krushItems.isEmpty()) {
         this.renderCategoryHeader(matrix, listX, itemY, listWidth, "ÐšÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»ÑŒ");
         itemY += 20.0F;

         for(int i = 0; i < krushItems.size(); ++i) {
            AutoBuyableItem item = (AutoBuyableItem)krushItems.get(i);
            float itemX = listX + (float)(i % 2 * 190);
            if (i % 2 == 0 && i > 0) {
               itemY += 50.0F;
            }

            this.renderItem(context, matrix, item, itemX, itemY, mouseX, mouseY, delta, globalIndex);
            ++globalIndex;
         }

         itemY += 50.0F;
         itemY += 25.0F;
      }

      List<AutoBuyableItem> talismanItems = ItemRegistry.getTalismans();
      if (!talismanItems.isEmpty()) {
         this.renderCategoryHeader(matrix, listX, itemY, listWidth, "Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½Ñ‹");
         itemY += 20.0F;

         for(int i = 0; i < talismanItems.size(); ++i) {
            AutoBuyableItem item = (AutoBuyableItem)talismanItems.get(i);
            float itemX = listX + (float)(i % 2 * 190);
            if (i % 2 == 0 && i > 0) {
               itemY += 50.0F;
            }

            this.renderItem(context, matrix, item, itemX, itemY, mouseX, mouseY, delta, globalIndex);
            ++globalIndex;
         }

         itemY += 50.0F;
         itemY += 25.0F;
      }

      List<AutoBuyableItem> sphereItems = ItemRegistry.getSpheres();
      if (!sphereItems.isEmpty()) {
         this.renderCategoryHeader(matrix, listX, itemY, listWidth, "Ð¡Ñ„ÐµÑ€Ñ‹");
         itemY += 20.0F;

         for(int i = 0; i < sphereItems.size(); ++i) {
            AutoBuyableItem item = (AutoBuyableItem)sphereItems.get(i);
            float itemX = listX + (float)(i % 2 * 190);
            if (i % 2 == 0 && i > 0) {
               itemY += 50.0F;
            }

            this.renderItem(context, matrix, item, itemX, itemY, mouseX, mouseY, delta, globalIndex);
            ++globalIndex;
         }

         itemY += 50.0F;
         itemY += 25.0F;
      }

      List<AutoBuyableItem> miscItems = ItemRegistry.getMisc();
      if (!miscItems.isEmpty()) {
         this.renderCategoryHeader(matrix, listX, itemY, listWidth, "Ð Ð°Ð·Ð½Ð¾Ðµ");
         itemY += 20.0F;

         for(int i = 0; i < miscItems.size(); ++i) {
            AutoBuyableItem item = (AutoBuyableItem)miscItems.get(i);
            float itemX = listX + (float)(i % 2 * 190);
            if (i % 2 == 0 && i > 0) {
               itemY += 50.0F;
            }

            this.renderItem(context, matrix, item, itemX, itemY, mouseX, mouseY, delta, globalIndex);
            ++globalIndex;
         }

         itemY += 50.0F;
         itemY += 25.0F;
      }

      List<AutoBuyableItem> donatorItems = ItemRegistry.getDonator();
      if (!donatorItems.isEmpty()) {
         this.renderCategoryHeader(matrix, listX, itemY, listWidth, "Ð”Ð¾Ð½Ð°Ñ‚Ð¾Ñ€ÑÐºÐ¸Ðµ");
         itemY += 20.0F;

         for(int i = 0; i < donatorItems.size(); ++i) {
            AutoBuyableItem item = (AutoBuyableItem)donatorItems.get(i);
            float itemX = listX + (float)(i % 2 * 190);
            if (i % 2 == 0 && i > 0) {
               itemY += 50.0F;
            }

            this.renderItem(context, matrix, item, itemX, itemY, mouseX, mouseY, delta, globalIndex);
            ++globalIndex;
         }

         itemY += 50.0F;
         itemY += 25.0F;
      }

      List<AutoBuyableItem> potionItems = ItemRegistry.getPotions();
      if (!potionItems.isEmpty()) {
         this.renderCategoryHeader(matrix, listX, itemY, listWidth, "Ð—ÐµÐ»ÑŒÑ");
         itemY += 20.0F;

         for(int i = 0; i < potionItems.size(); ++i) {
            AutoBuyableItem item = (AutoBuyableItem)potionItems.get(i);
            float itemX = listX + (float)(i % 2 * 190);
            if (i % 2 == 0 && i > 0) {
               itemY += 50.0F;
            }

            this.renderItem(context, matrix, item, itemX, itemY, mouseX, mouseY, delta, globalIndex);
            ++globalIndex;
         }

         itemY += 50.0F;
         itemY += 10.0F;
      }

      scissorManager.pop();
   }

   private float calculateContentHeight() {
      List<AutoBuyableItem> krushItems = ItemRegistry.getKrush();
      List<AutoBuyableItem> talismanItems = ItemRegistry.getTalismans();
      List<AutoBuyableItem> sphereItems = ItemRegistry.getSpheres();
      List<AutoBuyableItem> miscItems = ItemRegistry.getMisc();
      List<AutoBuyableItem> donatorItems = ItemRegistry.getDonator();
      List<AutoBuyableItem> potionItems = ItemRegistry.getPotions();
      float totalHeight = 10.0F;
      if (!krushItems.isEmpty()) {
         totalHeight += 20.0F + (float)((krushItems.size() + 1) / 2) * 50.0F + 25.0F;
      }

      if (!talismanItems.isEmpty()) {
         totalHeight += 20.0F + (float)((talismanItems.size() + 1) / 2) * 50.0F + 25.0F;
      }

      if (!sphereItems.isEmpty()) {
         totalHeight += 20.0F + (float)((sphereItems.size() + 1) / 2) * 50.0F + 25.0F;
      }

      if (!miscItems.isEmpty()) {
         totalHeight += 20.0F + (float)((miscItems.size() + 1) / 2) * 50.0F + 25.0F;
      }

      if (!donatorItems.isEmpty()) {
         totalHeight += 20.0F + (float)((donatorItems.size() + 1) / 2) * 50.0F + 25.0F;
      }

      if (!potionItems.isEmpty()) {
         totalHeight += 20.0F + (float)((potionItems.size() + 1) / 2) * 50.0F + 10.0F;
      }

      return totalHeight;
   }

   private void renderCategoryHeader(class_4587 matrix, float x, float y, float width, String categoryName) {
      float textWidth = Fonts.getSize(14, Fonts.Type.SEMI).getStringWidth(categoryName);
      float lineWidth = (width - textWidth - 20.0F) / 2.0F;
      rectangle.render(ShapeProperties.create(matrix, (double)x, (double)(y + 6.0F), (double)(lineWidth - 10.0F), (double)1.0F).color((new Color(54, 54, 56, 255)).getRGB()).build());
      Fonts.getSize(14, Fonts.Type.SEMI).drawString(matrix, categoryName, (double)(x + lineWidth), (double)(y + 4.0F), ColorAssist.getText(1.0F));
      rectangle.render(ShapeProperties.create(matrix, (double)(x + lineWidth + textWidth + 10.0F), (double)(y + 6.0F), (double)(lineWidth - 6.0F), (double)1.0F).color((new Color(54, 54, 56, 255)).getRGB()).build());
   }

   private void renderItem(class_332 context, class_4587 matrix, AutoBuyableItem item, float itemX, float itemY, int mouseX, int mouseY, float delta, int index) {
      FontRenderer font = Fonts.getSize(16, Fonts.Type.SEMI);
      class_1799 itemStack = item.createItemStack();
      blur.render(ShapeProperties.create(matrix, (double)itemX, (double)itemY, (double)175.0F, (double)45.0F).round(6.0F).quality(64.0F).color((new Color(0, 0, 0, 200)).getRGB()).build());
      rectangle.render(ShapeProperties.create(matrix, (double)itemX, (double)itemY, (double)175.0F, (double)45.0F).round(6.0F).softness(2.0F).thickness(0.1F).outlineColor((new Color(18, 19, 20, 225)).getRGB()).color((new Color(18, 19, 20, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(18, 19, 20, 175)).getRGB()).build());
      Render2D.defaultDrawStack(context, itemStack, itemX + 6.0F, itemY + 13.5F, false, false, 1.0F);
      Fonts.getSize(14, Fonts.Type.SEMI).drawGradientString(matrix, item.getDisplayName(), (double)(itemX + 30.0F), (double)(itemY + 14.0F), ColorAssist.getText(), ColorAssist.getText(0.65F));
      Fonts.getSize(12, Fonts.Type.REGULAR).drawString(matrix, "Ð¦ÐµÐ½Ð° Ð¿Ð¾ÐºÑƒÐ¿ÐºÐ¸: $" + item.getSettings().getBuyBelow(), (double)(itemX + 30.0F), (double)(itemY + 23.0F), ColorAssist.getText(0.65F));
      Fonts.getSize(12, Fonts.Type.REGULAR).drawString(matrix, "ÐšÐ°Ð»Ð¸Ñ‡ÐµÑÑ‚Ð²Ð¾ Ð¿Ð¾ÐºÑƒÐ¿ÐºÐ¸ Ð¾Ñ‚: " + item.getSettings().getMinQuantity(), (double)(itemX + 30.0F), (double)(itemY + 30.0F), ColorAssist.getText(0.65F));
      if (index < this.itemStatusRenders.size()) {
         ((StatusRender)this.itemStatusRenders.get(index)).position(itemX + 160.0F, itemY + 18.5F).render(context, mouseX, mouseY, delta);
      }

   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      if (MenuScreen.INSTANCE.getCategory() != ModuleCategory.AUTOBUY) {
         return super.mouseClicked(mouseX, mouseY, button);
      } else {
         float listX = this.x + 55.0F;
         float listY = this.y + 25.0F;
         float itemY = listY + 10.0F + this.smoothedScroll;
         int globalIndex = 0;
         List<AutoBuyableItem> krushItems = ItemRegistry.getKrush();
         if (!krushItems.isEmpty()) {
            itemY += 20.0F;

            for(int i = 0; i < krushItems.size(); ++i) {
               AutoBuyableItem item = (AutoBuyableItem)krushItems.get(i);
               float itemX = listX + (float)(i % 2 * 190);
               if (i % 2 == 0 && i > 0) {
                  itemY += 50.0F;
               }

               if (button == 1 && Calculate.isHovered(mouseX, mouseY, (double)itemX, (double)itemY, (double)175.0F, (double)45.0F)) {
                  this.openSettingsWindow(item);
                  return true;
               }

               if (button == 0 && globalIndex < this.itemStatusRenders.size()) {
                  StatusRender status = (StatusRender)this.itemStatusRenders.get(globalIndex);
                  status.position(itemX + 160.0F, itemY + 18.5F);
                  if (status.mouseClicked(mouseX, mouseY, button)) {
                     this.autoBuyManager.toggleItem(item);
                     this.updateItemStatusRenders();
                     return true;
                  }
               }

               ++globalIndex;
            }

            itemY += 50.0F;
            itemY += 25.0F;
         }

         List<AutoBuyableItem> talismanItems = ItemRegistry.getTalismans();
         if (!talismanItems.isEmpty()) {
            itemY += 20.0F;

            for(int i = 0; i < talismanItems.size(); ++i) {
               AutoBuyableItem item = (AutoBuyableItem)talismanItems.get(i);
               float itemX = listX + (float)(i % 2 * 190);
               if (i % 2 == 0 && i > 0) {
                  itemY += 50.0F;
               }

               if (button == 1 && Calculate.isHovered(mouseX, mouseY, (double)itemX, (double)itemY, (double)175.0F, (double)45.0F)) {
                  this.openSettingsWindow(item);
                  return true;
               }

               if (button == 0 && globalIndex < this.itemStatusRenders.size()) {
                  StatusRender status = (StatusRender)this.itemStatusRenders.get(globalIndex);
                  status.position(itemX + 160.0F, itemY + 18.5F);
                  if (status.mouseClicked(mouseX, mouseY, button)) {
                     this.autoBuyManager.toggleItem(item);
                     this.updateItemStatusRenders();
                     return true;
                  }
               }

               ++globalIndex;
            }

            itemY += 50.0F;
            itemY += 25.0F;
         }

         List<AutoBuyableItem> sphereItems = ItemRegistry.getSpheres();
         if (!sphereItems.isEmpty()) {
            itemY += 20.0F;

            for(int i = 0; i < sphereItems.size(); ++i) {
               AutoBuyableItem item = (AutoBuyableItem)sphereItems.get(i);
               float itemX = listX + (float)(i % 2 * 190);
               if (i % 2 == 0 && i > 0) {
                  itemY += 50.0F;
               }

               if (button == 1 && Calculate.isHovered(mouseX, mouseY, (double)itemX, (double)itemY, (double)175.0F, (double)45.0F)) {
                  this.openSettingsWindow(item);
                  return true;
               }

               if (button == 0 && globalIndex < this.itemStatusRenders.size()) {
                  StatusRender status = (StatusRender)this.itemStatusRenders.get(globalIndex);
                  status.position(itemX + 160.0F, itemY + 18.5F);
                  if (status.mouseClicked(mouseX, mouseY, button)) {
                     this.autoBuyManager.toggleItem(item);
                     this.updateItemStatusRenders();
                     return true;
                  }
               }

               ++globalIndex;
            }

            itemY += 50.0F;
            itemY += 25.0F;
         }

         List<AutoBuyableItem> miscItems = ItemRegistry.getMisc();
         if (!miscItems.isEmpty()) {
            itemY += 20.0F;

            for(int i = 0; i < miscItems.size(); ++i) {
               AutoBuyableItem item = (AutoBuyableItem)miscItems.get(i);
               float itemX = listX + (float)(i % 2 * 190);
               if (i % 2 == 0 && i > 0) {
                  itemY += 50.0F;
               }

               if (button == 1 && Calculate.isHovered(mouseX, mouseY, (double)itemX, (double)itemY, (double)175.0F, (double)45.0F)) {
                  this.openSettingsWindow(item);
                  return true;
               }

               if (button == 0 && globalIndex < this.itemStatusRenders.size()) {
                  StatusRender status = (StatusRender)this.itemStatusRenders.get(globalIndex);
                  status.position(itemX + 160.0F, itemY + 18.5F);
                  if (status.mouseClicked(mouseX, mouseY, button)) {
                     this.autoBuyManager.toggleItem(item);
                     this.updateItemStatusRenders();
                     return true;
                  }
               }

               ++globalIndex;
            }

            itemY += 50.0F;
            itemY += 25.0F;
         }

         List<AutoBuyableItem> donatorItems = ItemRegistry.getDonator();
         if (!donatorItems.isEmpty()) {
            itemY += 20.0F;

            for(int i = 0; i < donatorItems.size(); ++i) {
               AutoBuyableItem item = (AutoBuyableItem)donatorItems.get(i);
               float itemX = listX + (float)(i % 2 * 190);
               if (i % 2 == 0 && i > 0) {
                  itemY += 50.0F;
               }

               if (button == 1 && Calculate.isHovered(mouseX, mouseY, (double)itemX, (double)itemY, (double)175.0F, (double)45.0F)) {
                  this.openSettingsWindow(item);
                  return true;
               }

               if (button == 0 && globalIndex < this.itemStatusRenders.size()) {
                  StatusRender status = (StatusRender)this.itemStatusRenders.get(globalIndex);
                  status.position(itemX + 160.0F, itemY + 18.5F);
                  if (status.mouseClicked(mouseX, mouseY, button)) {
                     this.autoBuyManager.toggleItem(item);
                     this.updateItemStatusRenders();
                     return true;
                  }
               }

               ++globalIndex;
            }

            itemY += 50.0F;
            itemY += 25.0F;
         }

         List<AutoBuyableItem> potionItems = ItemRegistry.getPotions();
         if (!potionItems.isEmpty()) {
            itemY += 20.0F;

            for(int i = 0; i < potionItems.size(); ++i) {
               AutoBuyableItem item = (AutoBuyableItem)potionItems.get(i);
               float itemX = listX + (float)(i % 2 * 190);
               if (i % 2 == 0 && i > 0) {
                  itemY += 50.0F;
               }

               if (button == 1 && Calculate.isHovered(mouseX, mouseY, (double)itemX, (double)itemY, (double)175.0F, (double)45.0F)) {
                  this.openSettingsWindow(item);
                  return true;
               }

               if (button == 0 && globalIndex < this.itemStatusRenders.size()) {
                  StatusRender status = (StatusRender)this.itemStatusRenders.get(globalIndex);
                  status.position(itemX + 160.0F, itemY + 18.5F);
                  if (status.mouseClicked(mouseX, mouseY, button)) {
                     this.autoBuyManager.toggleItem(item);
                     this.updateItemStatusRenders();
                     return true;
                  }
               }

               ++globalIndex;
            }
         }

         return super.mouseClicked(mouseX, mouseY, button);
      }
   }

   private void openSettingsWindow(AutoBuyableItem item) {
      if (MenuScreen.windowManager.getWindows().stream().noneMatch((w) -> w instanceof AutoBuyItemSettingsWindow && ((AutoBuyItemSettingsWindow)w).item.equals(item))) {
         AutoBuyItemSettingsWindow settingsWindow = new AutoBuyItemSettingsWindow(item, item.getSettings());
         settingsWindow.position((float)(MenuScreen.INSTANCE.x + MenuScreen.INSTANCE.width + 24), (float)MenuScreen.INSTANCE.y).size(180.0F, (float)settingsWindow.getComponentHeight());
         MenuScreen.windowManager.add(settingsWindow);
      }

   }

   public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
      if (MenuScreen.INSTANCE.getCategory() == ModuleCategory.AUTOBUY && Calculate.isHovered(mouseX, mouseY, (double)(this.x + 55.0F), (double)(this.y + 38.0F), (double)(this.width - 43.0F - 15.0F), (double)(this.height - 48.0F))) {
         this.scroll = (float)((double)this.scroll + amount * (double)20.0F);
         return true;
      } else {
         return super.mouseScrolled(mouseX, mouseY, amount);
      }
   }

   public AutoBuyGuiComponent setScroll(float scroll) {
      this.scroll = scroll;
      return this;
   }

   public AutoBuyGuiComponent setSmoothedScroll(float smoothedScroll) {
      this.smoothedScroll = smoothedScroll;
      return this;
   }
}

