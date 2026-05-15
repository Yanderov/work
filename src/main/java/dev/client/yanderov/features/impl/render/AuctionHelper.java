package dev.client.yanderov.features.impl.render;

import dev.client.yanderov.events.container.HandledScreenEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.ColorSetting;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.shape.ShapeProperties;
import dev.client.yanderov.utils.features.price.PriceParser;
import java.util.List;
import net.minecraft.class_1707;
import net.minecraft.class_1735;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_476;

public class AuctionHelper extends Module {
   private final PriceParser priceParser = new PriceParser();
   private final BooleanSetting threeSlots = new BooleanSetting("ÐŸÐ¾Ð´ÑÐ²ÐµÑ‡Ð¸Ð²Ð°Ñ‚ÑŒ 3 ÑÐ»Ð¾Ñ‚Ð°", "Ð¿Ð¾Ð´ÑÐ²ÐµÑ‡Ð¸Ð²Ð°Ñ‚ÑŒ 3 Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚Ð°");
   private final ColorSetting cheapestColor = (new ColorSetting("Ð¡Ð°Ð¼Ñ‹Ð¹ Ð´ÐµÑˆÐµÐ²Ñ‹Ð¹", "Ð¦Ð²ÐµÑ‚ ÑÐ°Ð¼Ð¾Ð³Ð¾ Ð´ÐµÑˆÐµÐ²Ð¾Ð³Ð¾ ÑÐ»Ð¾Ñ‚Ð°")).setColor(-11796661);
   private final ColorSetting secondColor = (new ColorSetting("2â€‘Ð¹ Ð´ÐµÑˆÐµÐ²Ñ‹Ð¹", "Ð¦Ð²ÐµÑ‚ Ð²Ñ‚Ð¾Ñ€Ð¾Ð³Ð¾ ÑÐ»Ð¾Ñ‚Ð°")).setColor(-11842561);
   private final ColorSetting thirdColor = (new ColorSetting("3â€‘Ð¹ Ð´ÐµÑˆÐµÐ²Ñ‹Ð¹", "Ð¦Ð²ÐµÑ‚ Ñ‚Ñ€ÐµÑ‚ÑŒÐµÐ³Ð¾ ÑÐ»Ð¾Ñ‚Ð°")).setColor(-46261);
   private class_1735 slot1;
   private class_1735 slot2;
   private class_1735 slot3;

   public AuctionHelper() {
      super("AuctionHelper", "Auction Helper", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.threeSlots, this.cheapestColor, this.secondColor, this.thirdColor});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      class_437 var3 = mc.field_1755;
      if (!(var3 instanceof class_476 screen)) {
         this.slot1 = this.slot2 = this.slot3 = null;
      } else {
         String title = screen.method_25440().getString();
         if (!title.contains("ÐÑƒÐºÑ†Ð¸Ð¾Ð½") && !title.contains("ÐŸÐ¾Ð¸ÑÐº:")) {
            this.slot1 = this.slot2 = this.slot3 = null;
         } else {
            List<class_1735> slots = ((class_1707)screen.method_17577()).field_7761;
            class_1735 best1 = null;
            class_1735 best2 = null;
            class_1735 best3 = null;
            double fsPrice = Double.MAX_VALUE;
            double medPrice = Double.MAX_VALUE;
            double thPrice = Double.MAX_VALUE;

            for(class_1735 slot : slots) {
               if (slot.field_7874 <= 44 && slot.method_7681()) {
                  int totalPrice = this.priceParser.getPrice(slot.method_7677());
                  int count = slot.method_7677().method_7947();
                  if (totalPrice != -1 && count > 0) {
                     double pricePerItem = (double)totalPrice / (double)count;
                     if (pricePerItem < fsPrice) {
                        thPrice = medPrice;
                        best3 = best2;
                        medPrice = fsPrice;
                        best2 = best1;
                        fsPrice = pricePerItem;
                        best1 = slot;
                     } else if (this.threeSlots.isValue() && pricePerItem < medPrice) {
                        thPrice = medPrice;
                        best3 = best2;
                        medPrice = pricePerItem;
                        best2 = slot;
                     } else if (this.threeSlots.isValue() && pricePerItem < thPrice) {
                        thPrice = pricePerItem;
                        best3 = slot;
                     }
                  }
               }
            }

            this.slot1 = best1;
            this.slot2 = best2;
            this.slot3 = best3;
         }
      }
   }

   @EventHandler
   public void onHandledScreen(HandledScreenEvent e) {
      class_437 var3 = mc.field_1755;
      if (var3 instanceof class_476 screen) {
         String title = screen.method_25440().getString();
         if (title.contains("ÐÑƒÐºÑ†Ð¸Ð¾Ð½") || title.contains("ÐŸÐ¾Ð¸ÑÐº:")) {
            class_332 context = e.getDrawContext();
            class_4587 matrix = context.method_51448();
            int offsetX = (screen.field_22789 - e.getBackgroundWidth()) / 2;
            int offsetY = (screen.field_22790 - e.getBackgroundHeight()) / 2;
            matrix.method_22903();
            matrix.method_46416((float)offsetX, (float)offsetY, 0.0F);
            if (this.slot1 != null) {
               this.highlightSlot(context, this.slot1, this.getBlinkingColor(this.cheapestColor.getColor()));
            }

            if (this.threeSlots.isValue() && this.slot2 != null) {
               this.highlightSlot(context, this.slot2, this.getBlinkingColor(this.secondColor.getColor()));
            }

            if (this.threeSlots.isValue() && this.slot3 != null) {
               this.highlightSlot(context, this.slot3, this.getBlinkingColor(this.thirdColor.getColor()));
            }

            matrix.method_22909();
         }
      }
   }

   private int getBlinkingColor(int color) {
      float alpha = (float)Math.abs(Math.sin((double)System.currentTimeMillis() / (double)180.0F));
      return ColorAssist.multAlpha(color, alpha);
   }

   private void highlightSlot(class_332 context, class_1735 slot, int color) {
      if (slot != null) {
         rectangle.render(ShapeProperties.create(context.method_51448(), (double)slot.field_7873, (double)slot.field_7872, (double)16.0F, (double)16.0F).color(color).build());
      }
   }
}

