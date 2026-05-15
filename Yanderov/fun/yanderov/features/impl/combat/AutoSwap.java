package fun.Yanderov.features.impl.combat;

import fun.Yanderov.events.keyboard.KeyEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.impl.movement.AutoSprint;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BindSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.interactions.inv.InventoryTask;
import java.util.Comparator;
import java.util.function.Predicate;
import net.minecraft.class_1268;
import net.minecraft.class_1735;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_3675;

public class AutoSwap extends Module {
   private final SelectSetting modeSetting = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð¡Ð¿Ð¾ÑÐ¾Ð± Ð¾Ð±Ñ…Ð¾Ð´Ð°")).value("Default", "Legit").selected("Default");
   private final BindSetting bind = new BindSetting("ÐšÐ½Ð¾Ð¿ÐºÐ° Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ñ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚Ð°", "Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·ÑƒÐµÑ‚ ÑÐ»ÐµÐ¼ÐµÐ½Ñ‚ Ð¿Ñ€Ð¸ Ð½Ð°Ð¶Ð°Ñ‚Ð¸Ð¸");
   private final SelectSetting firstItem = (new SelectSetting("ÐžÑÐ½Ð¾Ð²Ð½Ð¾Ð¹ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ð¿ÐµÑ€Ð²Ñ‹Ð¹ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚ Ð´Ð»Ñ Ð¾Ð±Ð¼ÐµÐ½Ð°.")).value("Totem of Undying", "Player Head", "Golden Apple", "Shield");
   private final SelectSetting secondItem = (new SelectSetting("Ð’Ñ‚Ð¾Ñ€Ð¸Ñ‡Ð½Ñ‹Ð¹ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ð²Ñ‚Ð¾Ñ€Ð¾Ð¹ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚ Ð´Ð»Ñ Ð¾Ð±Ð¼ÐµÐ½Ð°.")).value("Totem of Undying", "Player Head", "Golden Apple", "Shield");
   private SwapPhase swapPhase;
   private class_1735 targetSlot;
   private long actionStartTime;
   private boolean playerFullyStopped;
   private boolean wasForwardPressed;
   private boolean wasBackPressed;
   private boolean wasLeftPressed;
   private boolean wasRightPressed;
   private boolean wasJumpPressed;
   private boolean keysOverridden;

   public AutoSwap() {
      super("AutoSwap", "Auto Swap", ModuleCategory.COMBAT);
      this.swapPhase = AutoSwap.SwapPhase.READY;
      this.targetSlot = null;
      this.actionStartTime = 0L;
      this.playerFullyStopped = false;
      this.keysOverridden = false;
      this.setup(new Setting[]{this.modeSetting, this.firstItem, this.secondItem, this.bind});
   }

   @EventHandler
   public void onKey(KeyEvent e) {
      if (e.isKeyDown(this.bind.getKey()) && this.swapPhase == AutoSwap.SwapPhase.READY) {
         if (this.modeSetting.getSelected().equals("Default")) {
            this.executeDefaultSwap();
         } else {
            class_1735 hotbarSlot = this.findValidSlot((s) -> s.field_7874 >= 36 && s.field_7874 <= 44);
            if (hotbarSlot != null) {
               this.startLegitSwap(hotbarSlot);
            } else {
               class_1735 inventorySlot = this.findValidSlot((s) -> s.field_7874 >= 0 && s.field_7874 <= 35);
               if (inventorySlot != null) {
                  this.startLegitSwap(inventorySlot);
               }
            }
         }
      }

   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.modeSetting.getSelected().equals("Legit") && this.swapPhase != AutoSwap.SwapPhase.READY) {
         this.processLegitSwap();
      }

   }

   private void startLegitSwap(class_1735 slotToSwap) {
      this.targetSlot = slotToSwap;
      if (this.targetSlot != null) {
         this.wasForwardPressed = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1894.method_1429().method_1444());
         this.wasBackPressed = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1881.method_1429().method_1444());
         this.wasLeftPressed = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1913.method_1429().method_1444());
         this.wasRightPressed = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1849.method_1429().method_1444());
         this.wasJumpPressed = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1903.method_1429().method_1444());
         this.swapPhase = AutoSwap.SwapPhase.SLOWING_DOWN;
         this.actionStartTime = System.currentTimeMillis();
         this.playerFullyStopped = false;
         this.keysOverridden = false;
      }
   }

   private void processLegitSwap() {
      if (mc.field_1724 != null && mc.field_1755 == null) {
         long elapsed = System.currentTimeMillis() - this.actionStartTime;
         switch (this.swapPhase.ordinal()) {
            case 1:
               mc.field_1724.field_3913.field_3905 = 0.0F;
               mc.field_1724.field_3913.field_3907 = 0.0F;
               if (mc.field_1724.method_5624()) {
                  mc.field_1724.method_5728(false);
                  AutoSprint.tickStop = 5;
               }

               if (!this.keysOverridden) {
                  mc.field_1690.field_1894.method_23481(false);
                  mc.field_1690.field_1881.method_23481(false);
                  mc.field_1690.field_1913.method_23481(false);
                  mc.field_1690.field_1849.method_23481(false);
                  mc.field_1690.field_1903.method_23481(false);
                  this.keysOverridden = true;
               }

               if (elapsed > 1L) {
                  this.swapPhase = AutoSwap.SwapPhase.WAITING_STOP;
               }
               break;
            case 2:
               mc.field_1724.field_3913.field_3905 = 0.0F;
               mc.field_1724.field_3913.field_3907 = 0.0F;
               double velocityX = Math.abs(mc.field_1724.method_18798().field_1352);
               double velocityZ = Math.abs(mc.field_1724.method_18798().field_1350);
               if (velocityX < 0.001 && velocityZ < 0.001 || elapsed > 15L) {
                  this.playerFullyStopped = true;
                  this.swapPhase = AutoSwap.SwapPhase.SWAP;
               }
               break;
            case 3:
               if (this.playerFullyStopped) {
                  if (this.targetSlot != null) {
                     InventoryTask.moveItem(this.targetSlot, 45, false, false);
                  }

                  this.swapPhase = AutoSwap.SwapPhase.SPEEDING_UP;
                  this.actionStartTime = System.currentTimeMillis();
                  if (this.keysOverridden) {
                     this.restoreKeyStates();
                  }
               }
               break;
            case 4:
               long speedupElapsed = System.currentTimeMillis() - this.actionStartTime;
               float speedupProgress = Math.min(1.0F, (float)speedupElapsed / 20.0F);
               if (mc.field_1724.field_3913 != null) {
                  boolean forward = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1894.method_1429().method_1444());
                  float targetForward = forward ? 1.0F : 0.0F;
                  mc.field_1724.field_3913.field_3905 = this.lerp(mc.field_1724.field_3913.field_3905, targetForward * speedupProgress, 0.4F);
                  if (speedupProgress > 0.4F && forward && !mc.field_1724.method_5624()) {
                     mc.field_1724.method_5728(true);
                  }
               }

               if (speedupElapsed > 25L) {
                  this.swapPhase = AutoSwap.SwapPhase.FINISHED;
               }
               break;
            case 5:
               this.resetState();
         }

      } else {
         this.resetState();
      }
   }

   private void executeDefaultSwap() {
      class_1735 validSlot = this.findValidSlot((s) -> true);
      if (validSlot != null) {
         InventoryTask.swapHand(validSlot, class_1268.field_5810, true, true);
      }

   }

   private void restoreKeyStates() {
      boolean currentForward = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1894.method_1429().method_1444());
      boolean currentBack = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1881.method_1429().method_1444());
      boolean currentLeft = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1913.method_1429().method_1444());
      boolean currentRight = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1849.method_1429().method_1444());
      boolean currentJump = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1903.method_1429().method_1444());
      mc.field_1690.field_1894.method_23481(this.wasForwardPressed && currentForward);
      mc.field_1690.field_1881.method_23481(this.wasBackPressed && currentBack);
      mc.field_1690.field_1913.method_23481(this.wasLeftPressed && currentLeft);
      mc.field_1690.field_1849.method_23481(this.wasRightPressed && currentRight);
      mc.field_1690.field_1903.method_23481(this.wasJumpPressed && currentJump);
      this.keysOverridden = false;
   }

   private float lerp(float start, float end, float delta) {
      return start + (end - start) * delta;
   }

   private class_1735 findValidSlot(Predicate slotPredicate) {
      Predicate<class_1735> combinedPredicate = (s) -> s.field_7874 != 45 && slotPredicate.test(s);
      class_1792 firstType = this.getItemByType(this.firstItem.getSelected());
      class_1792 secondType = this.getItemByType(this.secondItem.getSelected());
      class_1792 offHandItem = mc.field_1724.method_6079().method_7909();
      String offHandItemName = mc.field_1724.method_6079().method_7964().getString();
      if (offHandItem == firstType) {
         class_1735 second = InventoryTask.getSlot(secondType, Comparator.comparing((s) -> s.method_7677().method_7942()), combinedPredicate.and((s) -> s.method_7677().method_7909() == secondType && !s.method_7677().method_7964().getString().equals(offHandItemName)));
         if (second != null) {
            return second;
         }
      }

      if (offHandItem == secondType) {
         class_1735 first = InventoryTask.getSlot(firstType, Comparator.comparing((s) -> s.method_7677().method_7942()), combinedPredicate.and((s) -> s.method_7677().method_7909() == firstType && !s.method_7677().method_7964().getString().equals(offHandItemName)));
         if (first != null) {
            return first;
         }
      }

      if (offHandItem != firstType && offHandItem != secondType) {
         class_1735 first = InventoryTask.getSlot(firstType, Comparator.comparing((s) -> s.method_7677().method_7942()), combinedPredicate.and((s) -> s.method_7677().method_7909() == firstType && !s.method_7677().method_7964().getString().equals(offHandItemName)));
         if (first != null) {
            return first;
         }

         class_1735 second = InventoryTask.getSlot(secondType, Comparator.comparing((s) -> s.method_7677().method_7942()), combinedPredicate.and((s) -> s.method_7677().method_7909() == secondType && !s.method_7677().method_7964().getString().equals(offHandItemName)));
         if (second != null) {
            return second;
         }
      }

      return null;
   }

   private void resetState() {
      if (this.keysOverridden) {
         this.restoreKeyStates();
      }

      this.swapPhase = AutoSwap.SwapPhase.READY;
      this.targetSlot = null;
      this.playerFullyStopped = false;
   }

   private class_1792 getItemByType(String itemType) {
      class_1792 var10000;
      switch (itemType) {
         case "Totem of Undying" -> var10000 = class_1802.field_8288;
         case "Player Head" -> var10000 = class_1802.field_8575;
         case "Golden Apple" -> var10000 = class_1802.field_8463;
         case "Shield" -> var10000 = class_1802.field_8255;
         default -> var10000 = class_1802.field_8162;
      }

      return var10000;
   }

   public SelectSetting getModeSetting() {
      return this.modeSetting;
   }

   public BindSetting getBind() {
      return this.bind;
   }

   public SelectSetting getFirstItem() {
      return this.firstItem;
   }

   public SelectSetting getSecondItem() {
      return this.secondItem;
   }

   public SwapPhase getSwapPhase() {
      return this.swapPhase;
   }

   public class_1735 getTargetSlot() {
      return this.targetSlot;
   }

   public long getActionStartTime() {
      return this.actionStartTime;
   }

   public boolean isPlayerFullyStopped() {
      return this.playerFullyStopped;
   }

   public boolean isWasForwardPressed() {
      return this.wasForwardPressed;
   }

   public boolean isWasBackPressed() {
      return this.wasBackPressed;
   }

   public boolean isWasLeftPressed() {
      return this.wasLeftPressed;
   }

   public boolean isWasRightPressed() {
      return this.wasRightPressed;
   }

   public boolean isWasJumpPressed() {
      return this.wasJumpPressed;
   }

   public boolean isKeysOverridden() {
      return this.keysOverridden;
   }

   static enum SwapPhase {
      READY,
      SLOWING_DOWN,
      WAITING_STOP,
      SWAP,
      SPEEDING_UP,
      FINISHED;

      // $FF: synthetic method
      private static SwapPhase[] $values() {
         return new SwapPhase[]{READY, SLOWING_DOWN, WAITING_STOP, SWAP, SPEEDING_UP, FINISHED};
      }
   }
}

