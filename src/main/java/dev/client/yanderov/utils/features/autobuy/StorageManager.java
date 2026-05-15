package dev.client.yanderov.utils.features.autobuy;

import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.util.AuctionUtils;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.utils.math.time.TimerUtil;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1707;
import net.minecraft.class_1713;
import net.minecraft.class_1733;
import net.minecraft.class_1735;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_2480;
import net.minecraft.class_310;
import net.minecraft.class_437;
import net.minecraft.class_476;
import net.minecraft.class_490;
import net.minecraft.class_495;
import net.minecraft.class_5537;

public class StorageManager {
   private static final int MAX_SHULKERS = 3;
   private BooleanSetting autoStorage;
   private TimerUtil storageTimer = TimerUtil.create();
   private TimerUtil storageActionTimer = TimerUtil.create();
   private TimerUtil auctionEnterTimer = TimerUtil.create();
   private TimerUtil postStorageTimer = TimerUtil.create();
   private boolean storageActive = false;
   private int storageStep = 0;
   private int storageAttempts = 0;
   private boolean waitingForAuctionClose = false;
   private boolean searchingShulker = false;
   private boolean buyingShulker = false;
   private int currentShulkerIndex = 0;
   private List shulkerSlots = new ArrayList();
   private boolean reachedMaxShulkers = false;
   private boolean canStartStorage = false;
   private boolean storageCompleted = false;

   public StorageManager(BooleanSetting autoStorage) {
      this.autoStorage = autoStorage;
   }

   public void resetTimers() {
      this.storageTimer.resetCounter();
      this.storageActionTimer.resetCounter();
      this.auctionEnterTimer.resetCounter();
      this.postStorageTimer.resetCounter();
   }

   public void reset() {
      this.storageActive = false;
      this.storageStep = 0;
      this.storageAttempts = 0;
      this.waitingForAuctionClose = false;
      this.searchingShulker = false;
      this.buyingShulker = false;
      this.currentShulkerIndex = 0;
      this.shulkerSlots.clear();
      this.reachedMaxShulkers = false;
      this.canStartStorage = false;
      this.storageCompleted = false;
   }

   public void handle(class_310 mc, boolean inAuction) {
      if (this.autoStorage.isValue()) {
         if (!this.reachedMaxShulkers) {
            if (this.canStartStorage) {
               if (!this.storageActive) {
                  int freeSlots = this.getFreeInventorySlots(mc);
                  if (freeSlots <= 9 && this.hasResourcesInInventory(mc)) {
                     this.startStorage();
                  }

               } else if (this.storageActionTimer.hasTimeElapsed(300L)) {
                  this.processStorageStep(mc);
               }
            }
         }
      }
   }

   private void startStorage() {
      this.storageActive = true;
      this.storageStep = 0;
      this.storageAttempts = 0;
      this.waitingForAuctionClose = false;
      this.searchingShulker = false;
      this.buyingShulker = false;
      this.currentShulkerIndex = 0;
      this.shulkerSlots.clear();
      this.storageCompleted = false;
      this.storageTimer.resetCounter();
      this.storageActionTimer.resetCounter();
   }

   private void processStorageStep(class_310 mc) {
      switch (this.storageStep) {
         case 0 -> this.handleStep0(mc);
         case 1 -> this.handleStep1(mc);
         case 2 -> this.handleStep2(mc);
         case 15 -> this.handleStep15();
         case 20 -> this.handleStep20(mc);
         case 21 -> this.handleStep21(mc);
         case 22 -> this.handleStep22(mc);
         case 23 -> this.handleStep23(mc);
         case 24 -> this.handleStep24(mc);
         case 25 -> this.handleStep25(mc);
         case 26 -> this.handleStep26(mc);
         case 100 -> this.handleStep100(mc);
         case 101 -> this.handleStep101(mc);
         case 102 -> this.handleStep102();
         case 103 -> this.handleStep103(mc);
         case 104 -> this.handleStep104(mc);
         case 105 -> this.handleStep105();
         case 201 -> this.handleStep201(mc);
      }

   }

   private void handleStep0(class_310 mc) {
      if (mc.field_1755 instanceof class_476) {
         mc.field_1724.method_7346();
         this.waitingForAuctionClose = true;
         this.storageAttempts = 0;
         this.storageTimer.resetCounter();
         this.storageStep = 1;
      } else {
         this.storageStep = 2;
      }

      this.storageActionTimer.resetCounter();
   }

   private void handleStep1(class_310 mc) {
      if (!(mc.field_1755 instanceof class_476)) {
         this.waitingForAuctionClose = false;
         this.storageTimer.resetCounter();
         this.storageStep = 15;
      } else if (this.storageTimer.hasTimeElapsed(5000L)) {
         this.waitingForAuctionClose = false;
         this.storageTimer.resetCounter();
         this.storageStep = 15;
      } else {
         ++this.storageAttempts;
         if (this.storageAttempts > 3) {
            mc.field_1724.method_7346();
            this.storageTimer.resetCounter();
         }
      }

      this.storageActionTimer.resetCounter();
   }

   private void handleStep15() {
      if (this.storageTimer.hasTimeElapsed(500L)) {
         this.storageStep = 2;
      }

      this.storageActionTimer.resetCounter();
   }

   private void handleStep2(class_310 mc) {
      this.currentShulkerIndex = 0;
      this.shulkerSlots.clear();

      for(int i = 0; i < 36; ++i) {
         if (this.isShulkerBox(mc.field_1724.method_31548().method_5438(i))) {
            this.shulkerSlots.add(i);
         }
      }

      if (this.shulkerSlots.isEmpty()) {
         this.storageStep = 100;
      } else {
         this.storageStep = 20;
      }

      this.storageActionTimer.resetCounter();
   }

   private void handleStep20(class_310 mc) {
      if (mc.field_1755 == null) {
         mc.method_1507(new class_490(mc.field_1724));
         this.storageTimer.resetCounter();
         this.storageStep = 21;
      } else if (mc.field_1755 instanceof class_490) {
         this.storageTimer.resetCounter();
         this.storageStep = 21;
      } else {
         mc.field_1724.method_7346();
         this.storageTimer.resetCounter();
         this.storageStep = 201;
      }

      this.storageActionTimer.resetCounter();
   }

   private void handleStep201(class_310 mc) {
      if (this.storageTimer.hasTimeElapsed(500L)) {
         mc.method_1507(new class_490(mc.field_1724));
         this.storageTimer.resetCounter();
         this.storageStep = 21;
      }

      this.storageActionTimer.resetCounter();
   }

   private void handleStep21(class_310 mc) {
      if (!(mc.field_1755 instanceof class_490)) {
         if (this.storageTimer.hasTimeElapsed(2000L)) {
            if (mc.field_1755 != null) {
               mc.field_1724.method_7346();
            }

            this.storageTimer.resetCounter();
            this.storageStep = 201;
         }

         this.storageActionTimer.resetCounter();
      } else {
         if (this.storageTimer.hasTimeElapsed(800L)) {
            this.storageTimer.resetCounter();
            this.storageStep = 22;
         }

         this.storageActionTimer.resetCounter();
      }
   }

   private void handleStep22(class_310 mc) {
      if (!(mc.field_1755 instanceof class_490)) {
         this.storageStep = 20;
         this.storageActionTimer.resetCounter();
      } else {
         if (this.storageTimer.hasTimeElapsed(300L)) {
            if (this.currentShulkerIndex >= this.shulkerSlots.size()) {
               if (!this.hasResourcesInInventory(mc)) {
                  this.finishStorage(mc);
               } else {
                  this.storageStep = 100;
               }
            } else {
               int shulkerInventorySlot = (Integer)this.shulkerSlots.get(this.currentShulkerIndex);
               int slotId = this.getSlotId(shulkerInventorySlot);
               if (slotId == -1) {
                  ++this.currentShulkerIndex;
                  this.storageTimer.resetCounter();
                  return;
               }

               mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, slotId, 1, class_1713.field_7790, mc.field_1724);
               this.storageTimer.resetCounter();
               this.storageStep = 23;
            }
         }

         this.storageActionTimer.resetCounter();
      }
   }

   private void handleStep23(class_310 mc) {
      if (mc.field_1755 instanceof class_495) {
         this.storageTimer.resetCounter();
         this.storageStep = 24;
      } else if (this.storageTimer.hasTimeElapsed(2000L)) {
         ++this.currentShulkerIndex;
         this.storageTimer.resetCounter();
         this.storageStep = 22;
      }

      this.storageActionTimer.resetCounter();
   }

   private void handleStep24(class_310 mc) {
      if (!(mc.field_1755 instanceof class_495)) {
         ++this.currentShulkerIndex;
         this.storageTimer.resetCounter();
         this.storageStep = 22;
         this.storageActionTimer.resetCounter();
      } else {
         if (this.storageTimer.hasTimeElapsed(500L)) {
            this.storageStep = 25;
         }

         this.storageActionTimer.resetCounter();
      }
   }

   private void handleStep25(class_310 mc) {
      class_437 var3 = mc.field_1755;
      if (!(var3 instanceof class_495 shulkerScreen)) {
         ++this.currentShulkerIndex;
         this.storageTimer.resetCounter();
         this.storageStep = 22;
         this.storageActionTimer.resetCounter();
      } else {
         class_2371 var9 = ((class_1733)shulkerScreen.method_17577()).field_7761;
         if (this.isShulkerFull(var9)) {
            ++this.currentShulkerIndex;
            this.storageTimer.resetCounter();
            this.storageStep = 26;
            this.storageActionTimer.resetCounter();
         } else {
            boolean itemMoved = false;

            for(int i = 27; i < var9.size(); ++i) {
               class_1735 slot = (class_1735)var9.get(i);
               class_1799 stack = slot.method_7677();
               if (!stack.method_7960() && !this.isShulkerBox(stack) && !this.isBag(stack)) {
                  int syncId = ((class_1733)shulkerScreen.method_17577()).field_7763;
                  mc.field_1761.method_2906(syncId, slot.field_7874, 0, class_1713.field_7794, mc.field_1724);
                  itemMoved = true;
                  this.storageTimer.resetCounter();
                  break;
               }
            }

            if (!itemMoved) {
               ++this.currentShulkerIndex;
               this.storageTimer.resetCounter();
               this.storageStep = 26;
            }

            this.storageActionTimer.resetCounter();
         }
      }
   }

   private void handleStep26(class_310 mc) {
      if (this.storageTimer.hasTimeElapsed(300L)) {
         if (this.currentShulkerIndex >= this.shulkerSlots.size()) {
            if (!this.hasResourcesInInventory(mc)) {
               if (mc.field_1755 instanceof class_495) {
                  mc.field_1724.method_7346();
               }

               this.finishStorage(mc);
            } else {
               if (mc.field_1755 instanceof class_495) {
                  mc.field_1724.method_7346();
               }

               this.storageTimer.resetCounter();
               this.storageStep = 100;
            }
         } else {
            int nextShulkerInventorySlot = (Integer)this.shulkerSlots.get(this.currentShulkerIndex);
            int slotId = this.getSlotId(nextShulkerInventorySlot);
            if (slotId == -1) {
               ++this.currentShulkerIndex;
               this.storageTimer.resetCounter();
               return;
            }

            mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, slotId, 1, class_1713.field_7790, mc.field_1724);
            this.storageTimer.resetCounter();
            this.storageStep = 23;
         }
      }

      this.storageActionTimer.resetCounter();
   }

   private void handleStep100(class_310 mc) {
      int totalShulkers = this.countTotalShulkers(mc);
      if (totalShulkers >= 3) {
         if (mc.field_1755 != null) {
            mc.field_1724.method_7346();
         }

         this.reachedMaxShulkers = true;
         this.finishStorage(mc);
      } else {
         if (mc.field_1755 != null) {
            mc.field_1724.method_7346();
         }

         this.storageTimer.resetCounter();
         this.storageStep = 101;
      }

      this.storageActionTimer.resetCounter();
   }

   private void handleStep101(class_310 mc) {
      if (this.storageTimer.hasTimeElapsed(500L)) {
         if (!this.searchingShulker) {
            CommandSender.sendCommand(mc.field_1724, "/ah search Ð¨Ð°Ð»ÐºÐµÑ€ Ð¿ÑƒÑÑ‚Ð¾Ð¹");
            this.searchingShulker = true;
            this.storageTimer.resetCounter();
         }

         if (mc.field_1755 instanceof class_476) {
            this.storageTimer.resetCounter();
            this.storageStep = 102;
         } else if (this.storageTimer.hasTimeElapsed(6000L)) {
            this.searchingShulker = false;
            this.storageStep = 101;
         }
      }

      this.storageActionTimer.resetCounter();
   }

   private void handleStep102() {
      if (this.storageTimer.hasTimeElapsed(3000L)) {
         this.storageStep = 103;
      }

      this.storageActionTimer.resetCounter();
   }

   private void handleStep103(class_310 mc) {
      class_437 var3 = mc.field_1755;
      if (var3 instanceof class_476 screen) {
         List<class_1735> slots = ((class_1707)screen.method_17577()).field_7761;
         class_1735 cheapestShulker = null;
         int lowestPrice = 100001;

         for(int i = 0; i <= 44; ++i) {
            class_1735 slot = (class_1735)slots.get(i);
            class_1799 stack = slot.method_7677();
            if (this.isShulkerBox(stack)) {
               int price = AuctionUtils.getPrice(stack);
               if (price > 0 && price <= 100000 && price < lowestPrice) {
                  cheapestShulker = slot;
                  lowestPrice = price;
               }
            }
         }

         if (cheapestShulker != null) {
            int syncId = ((class_1707)screen.method_17577()).field_7763;
            mc.field_1761.method_2906(syncId, cheapestShulker.field_7874, 0, class_1713.field_7794, mc.field_1724);
            this.buyingShulker = true;
            this.storageTimer.resetCounter();
            this.storageStep = 104;
         } else {
            int syncId = ((class_1707)screen.method_17577()).field_7763;
            mc.field_1761.method_2906(syncId, 49, 0, class_1713.field_7794, mc.field_1724);
            this.storageTimer.resetCounter();
         }
      }

      this.storageActionTimer.resetCounter();
   }

   private void handleStep104(class_310 mc) {
      if (this.storageTimer.hasTimeElapsed(2500L)) {
         if (mc.field_1755 instanceof class_476) {
            mc.field_1724.method_7346();
         }

         this.storageTimer.resetCounter();
         this.storageStep = 105;
      }

      this.storageActionTimer.resetCounter();
   }

   private void handleStep105() {
      if (this.storageTimer.hasTimeElapsed(1000L)) {
         this.searchingShulker = false;
         this.buyingShulker = false;
         this.storageStep = 2;
      }

      this.storageActionTimer.resetCounter();
   }

   private void finishStorage(class_310 mc) {
      this.storageActive = false;
      this.storageCompleted = true;
      this.postStorageTimer.resetCounter();
      this.canStartStorage = false;
      this.storageStep = 0;
   }

   private int getFreeInventorySlots(class_310 mc) {
      int freeSlots = 0;

      for(int i = 9; i < 36; ++i) {
         if (mc.field_1724.method_31548().method_5438(i).method_7960()) {
            ++freeSlots;
         }
      }

      return freeSlots;
   }

   private boolean isShulkerBox(class_1799 stack) {
      if (stack.method_7960()) {
         return false;
      } else {
         class_1792 var3 = stack.method_7909();
         if (var3 instanceof class_1747) {
            class_1747 blockItem = (class_1747)var3;
            return blockItem.method_7711() instanceof class_2480;
         } else {
            return false;
         }
      }
   }

   private boolean isBag(class_1799 stack) {
      return stack.method_7960() ? false : stack.method_7909() instanceof class_5537;
   }

   private int countTotalShulkers(class_310 mc) {
      int count = 0;

      for(int i = 0; i < 36; ++i) {
         class_1799 stack = mc.field_1724.method_31548().method_5438(i);
         if (this.isShulkerBox(stack)) {
            ++count;
         }
      }

      return count;
   }

   private boolean isShulkerFull(List slots) {
      for(int i = 0; i < 27; ++i) {
         if (i < slots.size() && ((class_1735)slots.get(i)).method_7677().method_7960()) {
            return false;
         }
      }

      return true;
   }

   private boolean hasResourcesInInventory(class_310 mc) {
      for(int i = 9; i < 36; ++i) {
         class_1799 stack = mc.field_1724.method_31548().method_5438(i);
         if (!stack.method_7960() && !this.isShulkerBox(stack) && !this.isBag(stack)) {
            return true;
         }
      }

      return false;
   }

   private int getSlotId(int inventorySlot) {
      if (inventorySlot >= 0 && inventorySlot < 9) {
         return inventorySlot + 36;
      } else {
         return inventorySlot >= 9 && inventorySlot < 36 ? inventorySlot : -1;
      }
   }

   public boolean isActive() {
      return this.storageActive;
   }

   public void notifyAuctionEnter() {
      this.auctionEnterTimer.resetCounter();
   }

   public void handleAuctionEnter() {
      if (this.autoStorage.isValue() && !this.canStartStorage && this.auctionEnterTimer.hasTimeElapsed(5000L)) {
         this.canStartStorage = true;
      }

   }

   public boolean handlePostStorage(class_310 mc, TimerUtil enterDelayTimer, TimerUtil ahSpamTimer) {
      if (this.storageCompleted && this.postStorageTimer.hasTimeElapsed(1500L)) {
         this.storageCompleted = false;
         this.canStartStorage = false;
         enterDelayTimer.resetCounter();
         ahSpamTimer.resetCounter();
         if (!(mc.field_1755 instanceof class_476)) {
            CommandSender.sendCommand(mc.field_1724, "/ah");
         }

         return true;
      } else {
         return false;
      }
   }

   public TimerUtil getPostStorageTimer() {
      return this.postStorageTimer;
   }

   public void clearStorageCompleted() {
      this.storageCompleted = false;
   }

   public void disableStartStorage() {
      this.canStartStorage = false;
   }

   public boolean hasReachedMaxShulkers() {
      return this.reachedMaxShulkers;
   }

   public void resetMaxShulkers() {
      this.reachedMaxShulkers = false;
      this.canStartStorage = false;
   }
}

