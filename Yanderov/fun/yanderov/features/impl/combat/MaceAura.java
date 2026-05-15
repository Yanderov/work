package fun.Yanderov.features.impl.combat;

import antidaunleak.api.annotation.Native;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.interactions.inv.InventoryToolkit;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;

@Native
public class MaceAura extends Module {
   private final SliderSettings fallDistance = (new SliderSettings("Distance", "Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ (Ð¿Ð¾ Ð²ÐµÑ€Ñ‚Ð¸ÐºÐ°Ð»Ð¸)")).setValue(3.0F).range(1.0F, 20.0F);
   private boolean maceActive;
   private boolean crossedDistance;

   public MaceAura() {
      super("MaceAura", "Mace Aura", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.fallDistance});
   }

   public void activate() {
      this.maceActive = false;
      this.crossedDistance = false;
   }

   public void deactivate() {
      if (mc != null && mc.field_1724 != null) {
         InventoryToolkit.switchTo(0);
      }

      this.maceActive = false;
      this.crossedDistance = false;
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.isState()) {
         if (mc != null && mc.field_1724 != null && mc.field_1687 != null) {
            class_1657 player = mc.field_1724;
            if (player.method_24828()) {
               if (this.maceActive) {
                  InventoryToolkit.switchTo(0);
               }

               this.maceActive = false;
               this.crossedDistance = false;
            } else {
               class_1309 target = this.getNearestPlayerUnderMe();
               if (target != null) {
                  double dy = player.method_23318() - target.method_23318();
                  if (!(dy <= (double)0.0F)) {
                     float trigger = this.fallDistance.getValue();
                     if (!this.crossedDistance && dy >= (double)trigger) {
                        int maceSlot = this.findMaceInHotbar();
                        if (maceSlot != -1) {
                           InventoryToolkit.switchTo(maceSlot);
                           this.maceActive = true;
                           this.crossedDistance = true;
                        }
                     }

                  }
               }
            }
         }
      }
   }

   private class_1309 getNearestPlayerUnderMe() {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         class_1657 self = mc.field_1724;
         class_1309 nearest = null;
         double bestDistSq = Double.MAX_VALUE;

         for(class_1657 other : mc.field_1687.method_18456()) {
            if (other != self && other.method_5805()) {
               double dx = other.method_23317() - self.method_23317();
               double dz = other.method_23321() - self.method_23321();
               double distSq = dx * dx + dz * dz;
               if (distSq <= (double)25.0F && (nearest == null || distSq < bestDistSq)) {
                  nearest = other;
                  bestDistSq = distSq;
               }
            }
         }

         if (nearest == null) {
            return null;
         } else if (self.method_23318() <= nearest.method_23318()) {
            return null;
         } else {
            return nearest;
         }
      } else {
         return null;
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

   public SliderSettings getFallDistance() {
      return this.fallDistance;
   }

   public boolean isMaceActive() {
      return this.maceActive;
   }

   public boolean isCrossedDistance() {
      return this.crossedDistance;
   }
}

