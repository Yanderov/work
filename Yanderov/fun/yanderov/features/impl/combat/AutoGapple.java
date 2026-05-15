package fun.Yanderov.features.impl.combat;

import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.MultiSelectSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.interactions.inv.InventoryTask;
import fun.Yanderov.utils.interactions.item.ItemToolkit;
import net.minecraft.class_1268;
import net.minecraft.class_1304;
import net.minecraft.class_1735;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;

public class AutoGapple extends Module {
   private final SliderSettings healthThreshold = (new SliderSettings("Ð£Ñ€Ð¾Ð²ÐµÐ½ÑŒ Ð·Ð´Ð¾Ñ€Ð¾Ð²ÑŒÑ", "ÐÐ¸Ð¶Ðµ ÐºÐ°ÐºÐ¾Ð³Ð¾ Ð·Ð´Ð¾Ñ€Ð¾Ð²ÑŒÑ ÐµÑÑ‚ÑŒ Ð³ÑÐ¿Ð¿Ð»")).setValue(18.0F).range(4.0F, 20.0F);
   private final BooleanSetting predictHeal = (new BooleanSetting("Predict Heal", "Ð•Ð´Ð¸Ð¼ ÑÐ±Ð»Ð¾ÐºÐ¾ ÐµÑÐ»Ð¸ ÐµÑÑ‚ÑŒ Ñ‚Ð°Ñ€Ð³ÐµÑ‚ Ð¸Ð· Aura, Ð´Ð°Ð¶Ðµ Ð¿Ñ€Ð¸ Ð¿Ð¾Ð»Ð½Ð¾Ð¼ Ñ…Ð¿")).setValue(false);
   private final SelectSetting handMode = (new SelectSetting("Ð ÑƒÐºÐ°", "ÐšÐ°Ðº ÐµÑÑ‚ÑŒ ÑÐ±Ð»Ð¾ÐºÐ¾")).value("MainHand", "OffHand").selected("OffHand");
   private final SelectSetting swapMode = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼ ÑÐ²Ð°Ð¿Ð°", "Ð¢Ð¸Ð¿ ÑÐ²Ð°Ð¿Ð° Ð² offhand")).value("Default", "Legit").selected("Default").visible(() -> this.handMode.isSelected("OffHand"));
   private final MultiSelectSetting noSwapItems = (new MultiSelectSetting("Ð—Ð°Ð¿Ñ€ÐµÑ‚ ÑÐ²Ð°Ð¿Ð°", "ÐŸÑ€Ð¸ ÑÑ‚Ð¸Ñ… Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚Ð°Ñ… Ð² Ñ€ÑƒÐºÐµ Ð³ÐµÐ¿Ð¿Ð» Ð½Ðµ ÑÐ²Ð°Ð¿Ð°ÐµÑ‚ÑÑ")).value("Totem", "Shield", "Crystal", "Bed", "Elytra").selected("Totem", "Shield");
   private SwapPhase swapPhase;
   private class_1735 gappleSlotLegit;
   private boolean active;

   public AutoGapple() {
      super("AutoGapple", "Auto Gapple", ModuleCategory.COMBAT);
      this.swapPhase = AutoGapple.SwapPhase.READY;
      this.gappleSlotLegit = null;
      this.active = false;
      this.setup(new Setting[]{this.healthThreshold, this.predictHeal, this.handMode, this.swapMode, this.noSwapItems});
   }

   public void deactivate() {
      this.active = false;
      this.swapPhase = AutoGapple.SwapPhase.READY;
      this.gappleSlotLegit = null;
      super.deactivate();
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc != null && mc.field_1724 != null && mc.field_1687 != null) {
            if (this.handMode.isSelected("OffHand") && this.swapMode.isSelected("Legit") && this.swapPhase != AutoGapple.SwapPhase.READY) {
               this.processLegitSwap();
            } else {
               boolean hasAnyGapple = this.hasAnyGoldenApple();
               if (!hasAnyGapple) {
                  this.active = false;
               } else {
                  boolean lowHp = mc.field_1724.method_6032() <= this.healthThreshold.getValue();
                  boolean auraTarget = false;
                  if (this.predictHeal.isValue()) {
                     Aura aura = Aura.getInstance();
                     auraTarget = aura != null && aura.isState() && aura.getCurrentTarget() != null;
                  }

                  boolean shouldEat = lowHp || auraTarget;
                  if (!shouldEat) {
                     this.active = false;
                  } else if (this.isSwapBlockedByHeldItem()) {
                     this.active = false;
                  } else {
                     if (this.handMode.isSelected("MainHand")) {
                        this.eatFromMainHand();
                     } else {
                        this.eatFromOffHand();
                     }

                  }
               }
            }
         } else {
            this.active = false;
            this.swapPhase = AutoGapple.SwapPhase.READY;
            this.gappleSlotLegit = null;
         }
      }
   }

   private void eatFromMainHand() {
      InventoryTask.swapAndUse(class_1802.field_8463);
      this.active = true;
   }

   private void eatFromOffHand() {
      if (this.swapMode.isSelected("Default")) {
         this.defaultOffhandEat();
      } else {
         this.startLegitOffhandEat();
      }

   }

   private void defaultOffhandEat() {
      class_1735 gappleSlot = InventoryTask.getSlot(class_1802.field_8463);
      if (gappleSlot != null) {
         InventoryTask.swapHand(gappleSlot, class_1268.field_5810, false, true);
         ItemToolkit.INSTANCE.useHand(class_1268.field_5810);
         InventoryTask.swapHand(gappleSlot, class_1268.field_5810, false, true);
         this.active = true;
      }
   }

   private void startLegitOffhandEat() {
      if (this.swapPhase == AutoGapple.SwapPhase.READY) {
         this.gappleSlotLegit = InventoryTask.getSlot(class_1802.field_8463);
         if (this.gappleSlotLegit != null) {
            this.swapPhase = AutoGapple.SwapPhase.SLOWING_DOWN;
         }
      }
   }

   private void processLegitSwap() {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         switch (this.swapPhase.ordinal()) {
            case 0:
            default:
               break;
            case 1:
               this.swapPhase = AutoGapple.SwapPhase.SWAP;
               break;
            case 2:
               if (this.gappleSlotLegit == null) {
                  this.swapPhase = AutoGapple.SwapPhase.READY;
                  return;
               }

               InventoryTask.swapHand(this.gappleSlotLegit, class_1268.field_5810, false, true);
               ItemToolkit.INSTANCE.useHand(class_1268.field_5810);
               InventoryTask.swapHand(this.gappleSlotLegit, class_1268.field_5810, false, true);
               this.active = true;
               this.swapPhase = AutoGapple.SwapPhase.FINISHED;
               break;
            case 3:
               this.swapPhase = AutoGapple.SwapPhase.READY;
               this.gappleSlotLegit = null;
         }

      } else {
         this.swapPhase = AutoGapple.SwapPhase.READY;
         this.gappleSlotLegit = null;
      }
   }

   private boolean hasAnyGoldenApple() {
      if (mc.field_1724 == null) {
         return false;
      } else if (mc.field_1724.method_6079().method_31574(class_1802.field_8463)) {
         return true;
      } else {
         return InventoryTask.getInventoryCount(class_1802.field_8463) > 0;
      }
   }

   private boolean isSwapBlockedByHeldItem() {
      if (mc.field_1724 == null) {
         return false;
      } else {
         class_1799 held = mc.field_1724.method_6118(class_1304.field_6173);
         class_1792 item = held.method_7909();
         if (item == class_1802.field_8288 && this.noSwapItems.isSelected("Totem")) {
            return true;
         } else if (item == class_1802.field_8255 && this.noSwapItems.isSelected("Shield")) {
            return true;
         } else if (item == class_1802.field_8301 && this.noSwapItems.isSelected("Crystal")) {
            return true;
         } else if ((item == class_1802.field_8789 || item == class_1802.field_8258 || item == class_1802.field_8112) && this.noSwapItems.isSelected("Bed")) {
            return true;
         } else {
            return item == class_1802.field_8833 && this.noSwapItems.isSelected("Elytra");
         }
      }
   }

   private static enum SwapPhase {
      READY,
      SLOWING_DOWN,
      SWAP,
      FINISHED;

      // $FF: synthetic method
      private static SwapPhase[] $values() {
         return new SwapPhase[]{READY, SLOWING_DOWN, SWAP, FINISHED};
      }
   }
}

