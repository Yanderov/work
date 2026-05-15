package fun.Yanderov.features.impl.misc;

import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.impl.combat.Aura;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_3965;

public class Extinguish extends Module implements QuickImports {
   private final SliderSettings cooldown = (new SliderSettings("Cooldown", "ÐšÐ” Ð¼ÐµÐ¶Ð´Ñƒ Ð¿Ð¾Ð¿Ñ‹Ñ‚ÐºÐ°Ð¼Ð¸ (ÑÐµÐº)")).setValue(1.0F).range(0.0F, 20.0F);
   private final BooleanSetting notDuringCombat = (new BooleanSetting("NotDuringCombat", "ÐÐµ Ñ‚ÑƒÑˆÐ¸Ñ‚ÑŒ Ð² Ð±Ð¾ÑŽ")).setValue(true);
   private final BooleanSetting pickup = (new BooleanSetting("Pickup", "ÐŸÐ¾Ð´Ð±Ð¸Ñ€Ð°Ñ‚ÑŒ Ð²Ð¾Ð´Ñƒ Ð¿Ð¾ÑÐ»Ðµ Ñ‚ÑƒÑˆÐµÐ½Ð¸Ñ")).setValue(true);
   private final SliderSettings pickupSpanMin = (new SliderSettings("PickupSpanMin", "ÐœÐ¸Ð½. Ð²Ñ€ÐµÐ¼Ñ Ð´Ð¾ Ð¿Ð¾Ð´Ð±Ð¾Ñ€Ð° (ÑÐµÐº)")).setValue(0.1F).range(0.0F, 20.0F).visible(() -> this.pickup.isValue());
   private final SliderSettings pickupSpanMax = (new SliderSettings("PickupSpanMax", "ÐœÐ°ÐºÑ. Ð²Ñ€ÐµÐ¼Ñ Ð´Ð¾ Ð¿Ð¾Ð´Ð±Ð¾Ñ€Ð° (ÑÐµÐº)")).setValue(10.0F).range(0.0F, 20.0F).visible(() -> this.pickup.isValue());
   private class_2338 lastExtinguishPos = null;
   private long lastAttemptAt = 0L;
   private long lastCooldownAt = 0L;

   public Extinguish() {
      super("Extinguish", ModuleCategory.MISC);
      this.setup(new Setting[]{this.cooldown, this.notDuringCombat, this.pickup, this.pickupSpanMin, this.pickupSpanMax});
   }

   public void activate() {
      super.activate();
      this.lastExtinguishPos = null;
      this.lastAttemptAt = 0L;
      this.lastCooldownAt = 0L;
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc.field_1724 != null && mc.field_1687 != null && mc.field_1761 != null) {
            if (!this.isUltrawarm()) {
               long now = System.currentTimeMillis();
               if (this.lastExtinguishPos != null && this.pickup.isValue()) {
                  long maxMs = (long)(this.pickupSpanMax.getValue() * 1000.0F);
                  if (now - this.lastAttemptAt > maxMs) {
                     this.lastExtinguishPos = null;
                  }
               }

               if (this.lastExtinguishPos != null && this.pickup.isValue()) {
                  long minMs = (long)(this.pickupSpanMin.getValue() * 1000.0F);
                  if (now - this.lastAttemptAt >= minMs && this.tryPickup(this.lastExtinguishPos)) {
                     this.lastExtinguishPos = null;
                  }
               }

               if (mc.field_1724.method_5809()) {
                  if (!this.notDuringCombat.isValue() || !this.isInCombat()) {
                     long cdMs = (long)(this.cooldown.getValue() * 1000.0F);
                     if (now - this.lastCooldownAt >= cdMs) {
                        class_2338 placePos = mc.field_1724.method_24515();
                        if (!this.tryPlaceWaterAt(placePos)) {
                           this.tryPlaceWaterAt(placePos.method_10074());
                        }

                     }
                  }
               }
            }
         }
      }
   }

   private boolean tryPlaceWaterAt(class_2338 pos) {
      if (!this.hasInHandsOrHotbar(class_1802.field_8705)) {
         return false;
      } else {
         try {
            class_243 hit = class_243.method_24953(pos);
            class_2350 face = class_2350.field_11036;
            class_3965 bhr = new class_3965(hit, face, pos, false);
            class_1268 hand = this.getHandFor(class_1802.field_8705);
            if (hand == null) {
               return false;
            }

            class_1269 ok = mc.field_1761.method_2896(mc.field_1724, hand, bhr);
            mc.field_1724.method_6104(hand);
            if (ok.method_23665()) {
               this.lastCooldownAt = System.currentTimeMillis();
               this.lastAttemptAt = this.lastCooldownAt;
               this.lastExtinguishPos = pos;
               return true;
            }
         } catch (Throwable var7) {
         }

         return false;
      }
   }

   private boolean tryPickup(class_2338 pos) {
      if (!this.hasInHandsOrHotbar(class_1802.field_8550)) {
         return false;
      } else {
         try {
            class_243 hit = class_243.method_24953(pos);
            class_3965 bhr = new class_3965(hit, class_2350.field_11036, pos, false);
            class_1268 hand = this.getHandFor(class_1802.field_8550);
            if (hand == null) {
               return false;
            } else {
               class_1269 ok = mc.field_1761.method_2896(mc.field_1724, hand, bhr);
               mc.field_1724.method_6104(hand);
               return ok.method_23665();
            }
         } catch (Throwable var6) {
            return false;
         }
      }
   }

   private boolean hasInHandsOrHotbar(class_1792 item) {
      try {
         if (mc.field_1724.method_6047().method_31574(item) || mc.field_1724.method_6079().method_31574(item)) {
            return true;
         }

         for(int i = 0; i < 9; ++i) {
            if (mc.field_1724.method_31548().method_5438(i).method_31574(item)) {
               return true;
            }
         }
      } catch (Throwable var3) {
      }

      return false;
   }

   private class_1268 getHandFor(class_1792 item) {
      try {
         if (mc.field_1724.method_6047().method_31574(item)) {
            return class_1268.field_5808;
         }

         if (mc.field_1724.method_6079().method_31574(item)) {
            return class_1268.field_5810;
         }

         for(int i = 0; i < 9; ++i) {
            if (mc.field_1724.method_31548().method_5438(i).method_31574(item)) {
               mc.field_1724.method_31548().field_7545 = i;
               return class_1268.field_5808;
            }
         }
      } catch (Throwable var3) {
      }

      return null;
   }

   private boolean isInCombat() {
      try {
         return Aura.getInstance().isState();
      } catch (Throwable var2) {
         return false;
      }
   }

   private boolean isUltrawarm() {
      try {
         Object dim = mc.field_1687.method_8597();

         try {
            return (Boolean)dim.getClass().getMethod("ultrawarm").invoke(dim);
         } catch (Throwable var4) {
            try {
               return (Boolean)dim.getClass().getMethod("isUltrawarm").invoke(dim);
            } catch (Throwable var3) {
            }
         }
      } catch (Throwable var5) {
      }

      return false;
   }
}

