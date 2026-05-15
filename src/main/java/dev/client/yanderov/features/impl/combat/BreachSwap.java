package dev.client.yanderov.features.impl.combat;

import antidaunleak.api.annotation.Native;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.interactions.inv.InventoryToolkit;
import net.minecraft.class_1792;
import net.minecraft.class_1799;

@Native
public class BreachSwap extends Module {
   private final BooleanSetting onlyWithAura = (new BooleanSetting("Ñ‚Ð¾Ð»ÑŒÐºÐ¾ Ñ Aura", "Ð Ð°Ð±Ð¾Ñ‚Ð°Ñ‚ÑŒ Ñ‚Ð¾Ð»ÑŒÐºÐ¾ Ñ Aura")).setValue(true);
   private final SliderSettings holdTicks = (new SliderSettings("Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð±ÑƒÐ»Ð°Ð²Ñ‹", "Ð¡ÐºÐ¾Ð»ÑŒÐºÐ¾ Ñ‚Ð¸ÐºÐ¾Ð² Ð´ÐµÑ€Ð¶Ð°Ñ‚ÑŒ Ð±ÑƒÐ»Ð°Ð²Ñƒ")).setValue(2.0F).range(1.0F, 10.0F);
   private boolean swapping;
   private int swapTicksLeft;
   private int savedSlot = -1;

   public BreachSwap() {
      super("BreachSwap", "Breach Swap", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.onlyWithAura, this.holdTicks});
   }

   public void activate() {
      this.swapping = false;
      this.swapTicksLeft = 0;
      this.savedSlot = -1;
   }

   public void deactivate() {
      this.stopSwapAndRestore();
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.isState()) {
         if (mc != null && mc.field_1724 != null && mc.field_1687 != null) {
            if (this.onlyWithAura.isValue()) {
               Aura aura = Aura.getInstance();
               if (aura == null || !aura.isState() || aura.getCurrentTarget() == null) {
                  if (this.swapping) {
                     this.stopSwapAndRestore();
                  }

                  return;
               }
            }

            if (this.swapping) {
               if (this.swapTicksLeft > 0) {
                  --this.swapTicksLeft;
               } else {
                  this.stopSwapAndRestore();
               }

            } else if (!mc.field_1724.method_24828()) {
               if (!(mc.field_1724.method_18798().field_1351 >= (double)0.0F)) {
                  float cooldown = mc.field_1724.method_7261(0.5F);
                  if (!(cooldown < 0.9F)) {
                     class_1799 mainHand = mc.field_1724.method_6047();
                     if (!this.isMace(mainHand)) {
                        int maceSlot = this.findMaceInHotbar();
                        if (maceSlot != -1) {
                           this.savedSlot = mc.field_1724.method_31548().field_7545;
                           if (this.savedSlot != maceSlot) {
                              InventoryToolkit.switchTo(maceSlot);
                              this.swapping = true;
                              this.swapTicksLeft = this.holdTicks.getInt();
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void stopSwapAndRestore() {
      if (this.swapping) {
         this.swapping = false;
         this.swapTicksLeft = 0;
         if (mc != null && mc.field_1724 != null) {
            if (this.savedSlot >= 0 && this.savedSlot < 9) {
               InventoryToolkit.switchTo(this.savedSlot);
            }

            this.savedSlot = -1;
         }
      }
   }

   private boolean isMace(class_1799 stack) {
      if (stack != null && !stack.method_7960()) {
         class_1792 item = stack.method_7909();
         String key = item.method_7876().toLowerCase();
         return key.contains("mace") || key.contains("Ð±ÑƒÐ»Ð°Ð²Ð°") || key.contains("mace_item");
      } else {
         return false;
      }
   }

   private int findMaceInHotbar() {
      if (mc != null && mc.field_1724 != null) {
         for(int i = 0; i < 9; ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (this.isMace(stack)) {
               return i;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   public BooleanSetting getOnlyWithAura() {
      return this.onlyWithAura;
   }

   public SliderSettings getHoldTicks() {
      return this.holdTicks;
   }

   public boolean isSwapping() {
      return this.swapping;
   }

   public int getSwapTicksLeft() {
      return this.swapTicksLeft;
   }

   public int getSavedSlot() {
      return this.savedSlot;
   }
}

