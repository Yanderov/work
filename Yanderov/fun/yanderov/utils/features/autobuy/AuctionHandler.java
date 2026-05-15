package fun.Yanderov.utils.features.autobuy;

import fun.Yanderov.display.screens.clickgui.components.implement.autobuy.items.AutoBuyableItem;
import fun.Yanderov.display.screens.clickgui.components.implement.autobuy.manager.AutoBuyManager;
import fun.Yanderov.display.screens.clickgui.components.implement.autobuy.util.AuctionUtils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_310;

public class AuctionHandler {
   private Set notFoundItems = ConcurrentHashMap.newKeySet();
   private Set processedItems = ConcurrentHashMap.newKeySet();
   private Set sentItems = ConcurrentHashMap.newKeySet();
   private Map lastMessageTime = new ConcurrentHashMap();
   private int failedCount = 0;
   private AutoBuyManager autoBuyManager;

   public AuctionHandler(AutoBuyManager autoBuyManager) {
      this.autoBuyManager = autoBuyManager;
   }

   public void clear() {
      this.notFoundItems.clear();
      this.processedItems.clear();
      this.sentItems.clear();
      this.lastMessageTime.clear();
      this.failedCount = 0;
   }

   public void handleBuyRequest(class_310 mc, int syncId, List slots, BuyRequest request, NetworkManager networkManager) {
      class_1735 targetSlot = this.findSlotByItemAndPrice(slots, request.itemName, request.price);
      if (targetSlot != null) {
         mc.field_1761.method_2906(syncId, targetSlot.field_7874, 0, class_1713.field_7794, mc.field_1724);
         this.failedCount = 0;
      } else {
         String itemKey = request.itemName + "|" + request.price;
         if (!this.notFoundItems.contains(itemKey)) {
            this.notFoundItems.add(itemKey);
         }

         ++this.failedCount;
      }

   }

   public boolean shouldUpdate() {
      return this.failedCount > 3;
   }

   public void updateAuction(class_310 mc, int syncId) {
      mc.field_1761.method_2906(syncId, 49, 0, class_1713.field_7794, mc.field_1724);
      this.notFoundItems.clear();
      this.failedCount = 0;
   }

   public void handleSuspiciousPrice(class_310 mc, int syncId, List slots) {
      class_1735 confirmSlot = (class_1735)slots.stream().filter((slot) -> !slot.method_7677().method_7960()).filter((slot) -> slot.method_7677().method_7909() == class_1802.field_8656).findFirst().orElse((Object)null);
      if (confirmSlot != null) {
         mc.field_1761.method_2906(syncId, confirmSlot.field_7874, 0, class_1713.field_7790, mc.field_1724);
      }

   }

   public List findMatchingSlots(List slots, List cachedEnabledItems) {
      List<class_1735> matching = new ArrayList();

      for(int i = 0; i <= 44; ++i) {
         class_1735 slot = (class_1735)slots.get(i);
         if (!slot.method_7677().method_7960()) {
            class_1799 stack = slot.method_7677();
            if (!AuctionUtils.isArmorItem(stack) || !AuctionUtils.hasThornsEnchantment(stack)) {
               int price = AuctionUtils.getPrice(stack);
               if (price > 0) {
                  for(AutoBuyableItem item : cachedEnabledItems) {
                     int maxPrice = item.getSettings().getBuyBelow();
                     if (price <= maxPrice) {
                        if (item.getSettings().isCanHaveQuantity()) {
                           int stackCount = stack.method_7947();
                           if (stackCount < item.getSettings().getMinQuantity()) {
                              continue;
                           }
                        }

                        if (AuctionUtils.compareItem(stack, item.createItemStack())) {
                           matching.add(slot);
                           break;
                        }
                     }
                  }
               }
            }
         }
      }

      matching.sort(Comparator.comparingInt((slotx) -> AuctionUtils.getPrice(slotx.method_7677())));
      return matching;
   }

   public void processBestSlots(List bestSlots, NetworkManager networkManager) {
      Map<String, Integer> itemCounts = new HashMap();

      for(class_1735 bestSlot : bestSlots) {
         class_1799 stack = bestSlot.method_7677();
         String itemName = stack.method_7964().getString();
         String cleanName = AuctionUtils.funTimePricePattern.matcher(itemName).replaceAll("").trim();
         int price = AuctionUtils.getPrice(stack);
         String itemKey = cleanName + "|" + price;
         itemCounts.put(cleanName, (Integer)itemCounts.getOrDefault(cleanName, 0) + 1);
         if (!this.sentItems.contains(itemKey)) {
            this.sentItems.add(itemKey);
            networkManager.sendBuy(cleanName, price);
         }
      }

      long currentTime = System.currentTimeMillis();

      for(Map.Entry entry : itemCounts.entrySet()) {
         String itemName = (String)entry.getKey();
         Long lastTime = (Long)this.lastMessageTime.get(itemName);
         if (lastTime == null || currentTime - lastTime > 2000L) {
            this.lastMessageTime.put(itemName, currentTime);
         }
      }

   }

   private class_1735 findSlotByItemAndPrice(List slots, String itemName, int expectedPrice) {
      for(int i = 0; i <= 44; ++i) {
         class_1735 slot = (class_1735)slots.get(i);
         if (!slot.method_7677().method_7960()) {
            class_1799 stack = slot.method_7677();
            if (!AuctionUtils.isArmorItem(stack) || !AuctionUtils.hasThornsEnchantment(stack)) {
               String stackName = stack.method_7964().getString();
               stackName = AuctionUtils.funTimePricePattern.matcher(stackName).replaceAll("").trim();
               int price = AuctionUtils.getPrice(stack);
               if (stackName.equals(itemName) && price == expectedPrice) {
                  return slot;
               }
            }
         }
      }

      return null;
   }
}

