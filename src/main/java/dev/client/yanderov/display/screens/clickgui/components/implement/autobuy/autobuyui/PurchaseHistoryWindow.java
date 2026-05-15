package dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.autobuyui;

import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.AutoBuyableItem;
import dev.client.yanderov.utils.display.font.Fonts;
import dev.client.yanderov.utils.display.geometry.Render2D;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.display.shape.ShapeProperties;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_1799;
import net.minecraft.class_310;
import net.minecraft.class_332;

public class PurchaseHistoryWindow {
   private static final List purchases = new CopyOnWriteArrayList();
   private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
   private float scroll = 0.0F;
   private float smoothedScroll = 0.0F;
   private final class_310 mc = class_310.method_1551();

   public static void addPurchase(AutoBuyableItem item, int price) {
      String cleanName = item.getDisplayName();
      long currentTime = System.currentTimeMillis();

      for(PurchaseRecord record : purchases) {
         if (record.itemName.equals(cleanName) && record.price == price && currentTime - record.timestamp < 1000L) {
            return;
         }
      }

      class_1799 stackCopy = item.createItemStack();
      stackCopy.method_7939(1);
      purchases.add(0, new PurchaseRecord(stackCopy, cleanName, price, item.getSettings().getMinQuantity(), currentTime));
      if (purchases.size() > 50) {
         purchases.remove(purchases.size() - 1);
      }

   }

   public static void addPurchase(String itemName, int price) {
      String cleanName = itemName;
      long currentTime = System.currentTimeMillis();

      for(PurchaseRecord record : purchases) {
         if (record.itemName.equals(cleanName) && record.price == price && currentTime - record.timestamp < 1000L) {
            return;
         }
      }

      purchases.add(0, new PurchaseRecord((class_1799)null, cleanName, price, 1, currentTime));
      if (purchases.size() > 50) {
         purchases.remove(purchases.size() - 1);
      }

   }

   public void render(class_332 context, int mouseX, int mouseY, float delta, int width, int height, int backgroundWidth, int backgroundHeight) {
      float panelX = (float)(width - backgroundWidth) / 2.0F - 185.0F;
      float panelY = (float)(height - backgroundHeight) / 2.0F;
      float panelWidth = 180.0F;
      float panelHeight = (float)backgroundHeight;
      context.method_51448().method_22903();
      QuickImports.rectangle.render(ShapeProperties.create(context.method_51448(), (double)panelX, (double)panelY, (double)panelWidth, (double)panelHeight).round(6.0F).thickness(2.0F).outlineColor((new Color(54, 54, 56, 255)).getRGB()).color((new Color(12, 12, 12, 200)).getRGB()).build());
      Fonts.getSize(14, Fonts.Type.SEMI).drawString(context.method_51448(), "Ð˜ÑÑ‚Ð¾Ñ€Ð¸Ñ Ð¿Ð¾ÐºÑƒÐ¿Ð¾Ðº", (double)(panelX + 23.0F), (double)(panelY + 10.0F), (new Color(255, 255, 255, 255)).getRGB());
      if (!purchases.isEmpty()) {
         float contentHeight = (float)purchases.size() * 50.0F;
         float maxScroll = Math.max(0.0F, contentHeight - (panelHeight - 30.0F));
         this.scroll = Math.min(0.0F, Math.max(this.scroll, -maxScroll));
         this.smoothedScroll += (this.scroll - this.smoothedScroll) * 0.15F;
         float itemY = panelY + 30.0F + this.smoothedScroll;

         for(PurchaseRecord record : new ArrayList(purchases)) {
            if (itemY + 50.0F >= panelY + 30.0F && itemY <= panelY + panelHeight - 10.0F) {
               if (record.itemStack != null && !record.itemStack.method_7960()) {
                  Render2D.defaultDrawStack(context, record.itemStack, panelX + 6.0F, itemY + 13.5F, false, false, 1.0F);
               }

               String displayName = record.itemName;
               if (displayName.length() > 18) {
                  displayName = displayName.substring(0, 15) + "...";
               }

               Fonts.getSize(12, Fonts.Type.SEMI).drawString(context.method_51448(), displayName, (double)(panelX + 30.0F), (double)(itemY + 5.0F), (new Color(255, 255, 255, 255)).getRGB());
               String var10000 = this.formatPrice(record.price);
               String priceText = "$" + var10000;
               if (record.quantity > 1) {
                  priceText = priceText + " x" + record.quantity;
               }

               Fonts.getSize(11, Fonts.Type.REGULAR).drawString(context.method_51448(), priceText, (double)(panelX + 30.0F), (double)(itemY + 16.0F), (new Color(100, 255, 100, 255)).getRGB());
               String timeText = timeFormat.format(new Date(record.timestamp));
               Fonts.getSize(10, Fonts.Type.REGULAR).drawString(context.method_51448(), timeText, (double)(panelX + 30.0F), (double)(itemY + 26.0F), (new Color(150, 150, 150, 255)).getRGB());
            }

            itemY += 50.0F;
         }

         if (maxScroll > 0.0F) {
            float scrollbarHeight = Math.max(20.0F, (panelHeight - 30.0F) * (panelHeight - 30.0F) / contentHeight);
            float scrollPercent = -this.smoothedScroll / maxScroll;
            float scrollbarY = panelY + 30.0F + scrollPercent * (panelHeight - 30.0F - scrollbarHeight);
            QuickImports.rectangle.render(ShapeProperties.create(context.method_51448(), (double)(panelX + panelWidth - 6.0F), (double)(panelY + 30.0F), (double)3.0F, (double)(panelHeight - 30.0F)).round(1.0F).color((new Color(30, 30, 30, 100)).getRGB()).build());
            QuickImports.rectangle.render(ShapeProperties.create(context.method_51448(), (double)(panelX + panelWidth - 6.0F), (double)scrollbarY, (double)3.0F, (double)scrollbarHeight).round(1.5F).color((new Color(100, 100, 100, 180)).getRGB()).build());
         }
      } else {
         Fonts.getSize(13, Fonts.Type.REGULAR).drawString(context.method_51448(), "ÐŸÐ¾ÐºÑƒÐ¿ÐºÐ¸ Ð¾Ñ‚ÑÑƒÑ‚ÑÑ‚Ð²ÑƒÑŽÑ‚", (double)(panelX + panelWidth / 2.0F - 35.0F), (double)(panelY + 105.0F), (new Color(150, 150, 150, 255)).getRGB());
      }

      context.method_51448().method_22909();
   }

   public boolean mouseScrolled(double mouseX, double mouseY, double amount, int width, int height, int backgroundWidth, int backgroundHeight) {
      if (purchases.isEmpty()) {
         return false;
      } else {
         float panelX = (float)(width - backgroundWidth) / 2.0F - 185.0F;
         float panelY = (float)(height - backgroundHeight) / 2.0F;
         float panelWidth = 180.0F;
         float panelHeight = (float)backgroundHeight;
         if (mouseX >= (double)panelX && mouseX <= (double)(panelX + panelWidth) && mouseY >= (double)panelY && mouseY <= (double)(panelY + panelHeight)) {
            this.scroll = (float)((double)this.scroll + amount * (double)20.0F);
            return true;
         } else {
            return false;
         }
      }
   }

   private String formatPrice(int price) {
      if (price >= 1000000) {
         return String.format("%.1fM", (double)price / (double)1000000.0F);
      } else {
         return price >= 1000 ? String.format("%.1fK", (double)price / (double)1000.0F) : String.valueOf(price);
      }
   }

   public static void clear() {
      purchases.clear();
   }

   public static class PurchaseRecord {
      private final class_1799 itemStack;
      private final String itemName;
      private final int price;
      private final int quantity;
      private final long timestamp;

      public PurchaseRecord(class_1799 itemStack, String itemName, int price, int quantity, long timestamp) {
         this.itemStack = itemStack;
         this.itemName = itemName;
         this.price = price;
         this.quantity = quantity;
         this.timestamp = timestamp;
      }
   }
}

