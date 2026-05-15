package dev.client.yanderov.features.impl.combat;

import antidaunleak.api.annotation.Native;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.interactions.inv.InventoryResult;
import dev.client.yanderov.utils.interactions.inv.InventoryToolkit;
import net.minecraft.class_1511;
import net.minecraft.class_1713;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_238;
import net.minecraft.class_310;
import net.minecraft.class_3675;

public class AutoTotem extends Module {
   private static final class_310 MC = class_310.method_1551();
   private final SelectSetting modeSetting = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð¡Ð¿Ð¾ÑÐ¾Ð± ÑÐ²Ð¾Ð¿Ð°")).value("Default", "Legit").selected("Default");
   private final SliderSettings healthThreshold = (new SliderSettings("ÐŸÐ¾Ñ€Ð¾Ð³ Ð·Ð´Ð¾Ñ€Ð¾Ð²ÑŒÑ", "ÐœÐ¸Ð½Ð¸Ð¼Ð°Ð»ÑŒÐ½Ð¾Ðµ Ð·Ð´Ð¾Ñ€Ð¾Ð²ÑŒÐµ Ð´Ð»Ñ ÑÐºÐ¸Ð¿Ð¸Ñ€Ð¾Ð²ÐºÐ¸ Ñ‚Ð¾Ñ‚ÐµÐ¼Ð°")).setValue(4.5F).range(1.0F, 20.0F);
   private final SliderSettings elytraHealth = (new SliderSettings("Ð—Ð´Ð¾Ñ€Ð¾Ð²ÑŒÐµ Ð½Ð° ÑÐ»Ð¸Ñ‚Ñ€Ðµ", "ÐœÐ¸Ð½Ð¸Ð¼Ð°Ð»ÑŒÐ½Ð¾Ðµ Ð·Ð´Ð¾Ñ€Ð¾Ð²ÑŒÐµ Ð¿Ñ€Ð¸ Ð¿Ð¾Ð»ÐµÑ‚Ðµ")).setValue(8.5F).range(1.0F, 20.0F);
   private final SliderSettings crystalDistance = (new SliderSettings("Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð´Ð¾ ÐºÑ€Ð¸ÑÑ‚Ð°Ð»Ð»Ð°", "ÐœÐ°ÐºÑ Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð´Ð¾ ÐºÑ€Ð¸ÑÑ‚Ð°Ð»Ð»Ð°")).setValue(4.0F).range(1.0F, 6.0F);
   private final BooleanSetting fallCheck = (new BooleanSetting("ÐŸÐ°Ð´ÐµÐ½Ð¸Ðµ", "Ð­ÐºÐ¸Ð¿Ð¸Ñ€Ð¾Ð²Ð°Ñ‚ÑŒ Ñ‚Ð¾Ñ‚ÐµÐ¼ Ð¿Ñ€Ð¸ Ð¿Ð°Ð´ÐµÐ½Ð¸Ð¸")).setValue(true);
   private final BooleanSetting saveTaliks = (new BooleanSetting("Ð¡ÐµÐ¹Ð² Ñ‚Ð°Ð»Ð¸ÐºÐ¾Ð²", "Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ñ‚ÑŒ Ð¾Ð±Ñ‹Ñ‡Ð½Ñ‹Ðµ Ñ‚Ð¾Ñ‚ÐµÐ¼Ñ‹ Ð±ÐµÐ· Ñ‡Ð°Ñ€")).setValue(true);
   private final BooleanSetting returnItem = (new BooleanSetting("Ð’Ð¾Ð·Ð²Ñ€Ð°Ñ‰Ð°Ñ‚ÑŒ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚", "Ð’ÐµÑ€Ð½ÑƒÑ‚ÑŒ Ð¿Ñ€ÐµÐ´Ñ‹Ð´ÑƒÑ‰Ð¸Ð¹ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚ Ð² Ñ€ÑƒÐºÑƒ/Ñ…Ð¾Ñ‚Ð±Ð°Ñ€ Ð¿Ð¾ÑÐ»Ðµ Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ñ Ñ‚Ð¾Ñ‚ÐµÐ¼Ð°")).setValue(true);
   private int savedSlot = -1;
   private int totemSlot = -1;
   private long actionStartTime = 0L;
   private boolean keysOverridden = false;
   private boolean wasForwardPressed;
   private boolean wasBackPressed;
   private boolean wasLeftPressed;
   private boolean wasRightPressed;
   private boolean wasJumpPressed;
   private boolean playerFullyStopped = false;
   private Phase phase;
   private class_1799 previousMainHandStack;
   private int previousMainHandSlot;
   private boolean needsReturn;

   public AutoTotem() {
      super("AutoTotem", "Auto Totem", ModuleCategory.COMBAT);
      this.phase = AutoTotem.Phase.READY;
      this.previousMainHandStack = class_1799.field_8037;
      this.previousMainHandSlot = -1;
      this.needsReturn = false;
      this.setup(new Setting[]{this.modeSetting, this.healthThreshold, this.elytraHealth, this.crystalDistance, this.fallCheck, this.saveTaliks, this.returnItem});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (MC.field_1724 != null && MC.field_1687 != null) {
         if (this.phase != AutoTotem.Phase.READY) {
            this.execute();
         } else {
            float health = MC.field_1724.method_6032();
            boolean shouldEquip = false;
            if (MC.field_1724.method_6128() && health <= this.elytraHealth.getValue()) {
               shouldEquip = true;
            } else if (health <= this.healthThreshold.getValue()) {
               shouldEquip = true;
            } else if (this.fallCheck.isValue() && MC.field_1724.field_6017 > 10.0F) {
               shouldEquip = true;
            } else if (this.getClosestCrystalDistance() <= (double)this.crystalDistance.getValue()) {
               shouldEquip = true;
            }

            if (shouldEquip) {
               this.tryEquipTotem();
            }

            if (this.phase == AutoTotem.Phase.READY && this.needsReturn && this.returnItem.isValue() && this.previousMainHandSlot >= 0 && !this.previousMainHandStack.method_7960() && MC.field_1724.method_6079().method_7909() != class_1802.field_8288) {
               this.attemptReturnPreviousItem();
               this.previousMainHandStack = class_1799.field_8037;
               this.previousMainHandSlot = -1;
               this.needsReturn = false;
            }

         }
      } else {
         this.resetState();
      }
   }

   @Native(
      type = Native.Type.VMProtectBeginUltra
   )
   private void tryEquipTotem() {
      if (this.phase == AutoTotem.Phase.READY) {
         if (!this.isTotemInOffhand()) {
            if (MC.field_1755 == null) {
               this.savedSlot = MC.field_1724.method_31548().field_7545;
               InventoryResult hotbar = InventoryToolkit.findItemInHotBar(class_1802.field_8288);
               if (hotbar.found()) {
                  this.totemSlot = hotbar.slot();
                  if (this.modeSetting.getSelected().equals("Default")) {
                     this.executeDefaultSwap();
                  } else {
                     this.startLegitEquip();
                  }

               } else {
                  InventoryResult inv;
                  if (this.saveTaliks.isValue()) {
                     inv = this.findTotemWithSaveTalics();
                  } else {
                     inv = InventoryToolkit.findItemInInventory(class_1802.field_8288);
                  }

                  if (inv.found()) {
                     this.totemSlot = inv.slot();
                     this.previousMainHandSlot = MC.field_1724.method_31548().field_7545;
                     this.previousMainHandStack = MC.field_1724.method_6047().method_7972();
                     this.needsReturn = true;
                     if (this.modeSetting.getSelected().equals("Legit")) {
                        this.startLegitEquip();
                     } else {
                        this.executeDefaultSwap();
                     }
                  }

               }
            }
         }
      }
   }

   private void executeDefaultSwap() {
      if (this.totemSlot < 0) {
         this.resetState();
      } else {
         int slotIndex = this.totemSlot;
         if (slotIndex >= 0 && slotIndex <= 8) {
            slotIndex += 36;
         }

         if (MC.field_1761 != null && MC.field_1724.field_7498 != null) {
            MC.field_1761.method_2906(MC.field_1724.field_7498.field_7763, slotIndex, 40, class_1713.field_7791, MC.field_1724);
         }

         if (this.returnItem.isValue() && this.needsReturn && this.previousMainHandSlot >= 0) {
            MC.field_1724.method_31548().field_7545 = this.previousMainHandSlot;
         } else if (this.savedSlot >= 0) {
            MC.field_1724.method_31548().field_7545 = this.savedSlot;
         }

         this.resetState();
      }
   }

   @Native(
      type = Native.Type.VMProtectBeginMutation
   )
   private void startLegitEquip() {
      this.wasForwardPressed = class_3675.method_15987(MC.method_22683().method_4490(), MC.field_1690.field_1894.method_1429().method_1444());
      this.wasBackPressed = class_3675.method_15987(MC.method_22683().method_4490(), MC.field_1690.field_1881.method_1429().method_1444());
      this.wasLeftPressed = class_3675.method_15987(MC.method_22683().method_4490(), MC.field_1690.field_1913.method_1429().method_1444());
      this.wasRightPressed = class_3675.method_15987(MC.method_22683().method_4490(), MC.field_1690.field_1849.method_1429().method_1444());
      this.wasJumpPressed = class_3675.method_15987(MC.method_22683().method_4490(), MC.field_1690.field_1903.method_1429().method_1444());
      this.phase = AutoTotem.Phase.SLOWING_DOWN;
      this.actionStartTime = System.currentTimeMillis();
      this.playerFullyStopped = false;
      this.keysOverridden = false;
   }

   private void execute() {
      if (MC.field_1724 != null && MC.field_1755 == null) {
         long elapsed = System.currentTimeMillis() - this.actionStartTime;
         switch (this.phase.ordinal()) {
            case 1:
               MC.field_1724.field_3913.field_3905 = 0.0F;
               MC.field_1724.field_3913.field_3907 = 0.0F;
               if (MC.field_1724.method_5624()) {
                  MC.field_1724.method_5728(false);
               }

               if (!this.keysOverridden) {
                  MC.field_1690.field_1894.method_23481(false);
                  MC.field_1690.field_1881.method_23481(false);
                  MC.field_1690.field_1913.method_23481(false);
                  MC.field_1690.field_1849.method_23481(false);
                  MC.field_1690.field_1903.method_23481(false);
                  this.keysOverridden = true;
               }

               if (elapsed > 1L) {
                  this.phase = AutoTotem.Phase.SWAP_TOTEM;
                  this.actionStartTime = System.currentTimeMillis();
               }
               break;
            case 2:
               if (elapsed > 25L) {
                  if (this.totemSlot < 0) {
                     this.resetState();
                     return;
                  }

                  int slotIndex = this.totemSlot;
                  if (slotIndex >= 0 && slotIndex <= 8) {
                     slotIndex += 36;
                  }

                  if (MC.field_1761 != null && MC.field_1724.field_7498 != null) {
                     MC.field_1761.method_2906(MC.field_1724.field_7498.field_7763, slotIndex, 40, class_1713.field_7791, MC.field_1724);
                  }

                  this.phase = AutoTotem.Phase.AWAIT_SWITCH;
                  this.actionStartTime = System.currentTimeMillis();
               }
               break;
            case 3:
               if (this.isTotemInOffhand() || elapsed > 50L) {
                  this.phase = AutoTotem.Phase.RESTORE_SLOT;
                  this.actionStartTime = System.currentTimeMillis();
               }
               break;
            case 4:
               if (elapsed > 25L) {
                  InventoryToolkit.switchTo(this.savedSlot);
                  if (this.modeSetting.getSelected().equals("Legit")) {
                     if (this.keysOverridden) {
                        this.restoreKeyStates();
                     }

                     this.actionStartTime = System.currentTimeMillis();
                     this.phase = AutoTotem.Phase.SPEEDING_UP;
                  } else {
                     this.phase = AutoTotem.Phase.FINISH;
                  }
               }
               break;
            case 5:
               long speedupElapsed = System.currentTimeMillis() - this.actionStartTime;
               float speedupProgress = Math.min(1.0F, (float)speedupElapsed / 20.0F);
               if (MC.field_1724.field_3913 != null) {
                  boolean forward = class_3675.method_15987(MC.method_22683().method_4490(), MC.field_1690.field_1894.method_1429().method_1444());
                  float targetForward = forward ? 1.0F : 0.0F;
                  MC.field_1724.field_3913.field_3905 = this.lerp(MC.field_1724.field_3913.field_3905, targetForward * speedupProgress, 0.4F);
                  if (speedupProgress > 0.4F && forward && !MC.field_1724.method_5624()) {
                     MC.field_1724.method_5728(true);
                  }
               }

               if (speedupElapsed > 25L) {
                  this.phase = AutoTotem.Phase.FINISH;
               }
               break;
            case 6:
               this.resetState();
         }

      } else {
         this.resetState();
      }
   }

   private float lerp(float start, float end, float delta) {
      return start + (end - start) * delta;
   }

   private InventoryResult findTotemWithSaveTalics() {
      InventoryResult nonEnchanted = InventoryToolkit.findInInventory((i) -> i.method_7909() == class_1802.field_8288 && !i.method_7942());
      return nonEnchanted.found() ? nonEnchanted : InventoryToolkit.findItemInInventory(class_1802.field_8288);
   }

   private double getClosestCrystalDistance() {
      double minDist = Double.MAX_VALUE;
      if (MC.field_1724 != null && MC.field_1687 != null) {
         class_238 box = MC.field_1724.method_5829().method_1014((double)this.crystalDistance.getValue());

         for(class_1511 crystal : MC.field_1687.method_8390(class_1511.class, box, (e) -> true)) {
            double dist = MC.field_1724.method_19538().method_1022(crystal.method_19538());
            if (dist < minDist) {
               minDist = dist;
            }
         }

         return minDist;
      } else {
         return minDist;
      }
   }

   private boolean isTotemInOffhand() {
      class_1799 stack = MC.field_1724.method_6079();
      return stack.method_7909() == class_1802.field_8288;
   }

   private void restoreKeyStates() {
      boolean currentForward = class_3675.method_15987(MC.method_22683().method_4490(), MC.field_1690.field_1894.method_1429().method_1444());
      boolean currentBack = class_3675.method_15987(MC.method_22683().method_4490(), MC.field_1690.field_1881.method_1429().method_1444());
      boolean currentLeft = class_3675.method_15987(MC.method_22683().method_4490(), MC.field_1690.field_1913.method_1429().method_1444());
      boolean currentRight = class_3675.method_15987(MC.method_22683().method_4490(), MC.field_1690.field_1849.method_1429().method_1444());
      boolean currentJump = class_3675.method_15987(MC.method_22683().method_4490(), MC.field_1690.field_1903.method_1429().method_1444());
      MC.field_1690.field_1894.method_23481(this.wasForwardPressed && currentForward);
      MC.field_1690.field_1881.method_23481(this.wasBackPressed && currentBack);
      MC.field_1690.field_1913.method_23481(this.wasLeftPressed && currentLeft);
      MC.field_1690.field_1849.method_23481(this.wasRightPressed && currentRight);
      MC.field_1690.field_1903.method_23481(this.wasJumpPressed && currentJump);
      this.keysOverridden = false;
   }

   private void attemptReturnPreviousItem() {
      if (this.returnItem.isValue()) {
         if (this.previousMainHandStack != null && !this.previousMainHandStack.method_7960()) {
            if (MC.field_1724 != null && MC.field_1724.field_7498 != null && MC.field_1761 != null) {
               int targetHotbar = this.previousMainHandSlot;
               if (targetHotbar < 0 || targetHotbar > 8) {
                  targetHotbar = this.savedSlot >= 0 ? this.savedSlot : -1;
               }

               if (targetHotbar >= 0) {
                  class_1799 currentStackInTarget = MC.field_1724.method_31548().method_5438(targetHotbar);
                  if (!currentStackInTarget.method_7960() && currentStackInTarget.method_7909() == this.previousMainHandStack.method_7909()) {
                     InventoryToolkit.switchTo(targetHotbar);
                  } else {
                     InventoryResult found = InventoryToolkit.findInInventory((i) -> i.method_7909() == this.previousMainHandStack.method_7909());
                     if (!found.found()) {
                        InventoryToolkit.switchTo(targetHotbar);
                     } else {
                        int fromSlot = found.slot();
                        int fromIndex = fromSlot;
                        if (fromSlot >= 0 && fromSlot <= 8) {
                           fromIndex = fromSlot + 36;
                        }

                        int toIndex = targetHotbar;
                        if (targetHotbar >= 0 && targetHotbar <= 8) {
                           toIndex = targetHotbar + 36;
                        }

                        MC.field_1761.method_2906(MC.field_1724.field_7498.field_7763, fromIndex, toIndex, class_1713.field_7791, MC.field_1724);
                        InventoryToolkit.switchTo(targetHotbar);
                     }
                  }
               }
            }
         }
      }
   }

   private void resetState() {
      if (this.keysOverridden) {
         this.restoreKeyStates();
      }

      this.totemSlot = -1;
      this.savedSlot = -1;
      this.actionStartTime = 0L;
      this.phase = AutoTotem.Phase.READY;
      this.playerFullyStopped = false;
   }

   public void deactivate() {
      this.resetState();
      this.previousMainHandStack = class_1799.field_8037;
      this.previousMainHandSlot = -1;
      this.needsReturn = false;
      super.deactivate();
   }

   private static enum Phase {
      READY,
      SLOWING_DOWN,
      SWAP_TOTEM,
      AWAIT_SWITCH,
      RESTORE_SLOT,
      SPEEDING_UP,
      FINISH;

      // $FF: synthetic method
      private static Phase[] $values() {
         return new Phase[]{READY, SLOWING_DOWN, SWAP_TOTEM, AWAIT_SWITCH, RESTORE_SLOT, SPEEDING_UP, FINISH};
      }
   }
}

