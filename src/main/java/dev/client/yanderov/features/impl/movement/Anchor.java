package dev.client.yanderov.features.impl.movement;

import dev.client.yanderov.events.player.PlayerTravelEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_265;
import net.minecraft.class_2680;

public class Anchor extends Module implements QuickImports {
   private final SliderSettings maxDistance = (new SliderSettings("MaxDistance", "ÐœÐ°ÐºÑ. Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð´Ð¾ Ñ†ÐµÐ»Ð¸")).setValue(4.0F).range(0.1F, 6.0F);
   private final SliderSettings horizontalSpeed = (new SliderSettings("HorizontalSpeed", "ÐžÐ³Ñ€Ð°Ð½Ð¸Ñ‡ÐµÐ½Ð¸Ðµ Ð³Ð¾Ñ€Ð¸Ð·Ð¾Ð½Ñ‚Ð°Ð»ÑŒÐ½Ð¾Ð¹ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚Ð¸")).setValue(0.3F).range(0.0F, 10.0F);
   private final SliderSettings verticalSpeed = (new SliderSettings("VerticalSpeed", "ÐžÐ³Ñ€Ð°Ð½Ð¸Ñ‡ÐµÐ½Ð¸Ðµ Ð²ÐµÑ€Ñ‚Ð¸ÐºÐ°Ð»ÑŒÐ½Ð¾Ð¹ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚Ð¸")).setValue(0.1F).range(0.0F, 10.0F);
   private class_243 goal = null;

   public Anchor() {
      super("Anchor", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.maxDistance, this.horizontalSpeed, this.verticalSpeed});
   }

   public void activate() {
      super.activate();
      this.goal = null;
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            if (this.isHoleAt(mc.field_1724.method_24515())) {
               class_2338 bp = mc.field_1724.method_24515();
               this.goal = this.centerOf(bp.method_10074());
            } else if (this.goal == null || !(this.goal.method_1025(mc.field_1724.method_19538()) <= this.sq(this.maxDistance.getValue()))) {
               double bestDistSq = Double.MAX_VALUE;
               class_243 best = null;
               int R = (int)Math.ceil((double)this.maxDistance.getValue());
               class_2338 playerPos = mc.field_1724.method_24515();

               for(int dx = -R; dx <= R; ++dx) {
                  for(int dz = -R; dz <= R; ++dz) {
                     for(int dy = 0; dy <= R; ++dy) {
                        class_2338 hp = playerPos.method_10069(dx, -dy, dz);
                        if (this.isHoleAt(hp)) {
                           class_243 c = this.centerOf(hp.method_10074());
                           double dSq = c.method_1025(mc.field_1724.method_19538());
                           if (dSq <= this.sq(this.maxDistance.getValue()) && dSq < bestDistSq && c.field_1351 <= mc.field_1724.method_23318()) {
                              bestDistSq = dSq;
                              best = c;
                           }
                        }
                     }
                  }
               }

               this.goal = best;
            }
         }
      }
   }

   @EventHandler
   public void onTravel(PlayerTravelEvent e) {
      if (e.isPre()) {
         if (mc.field_1724 != null && this.goal != null) {
            class_243 playerPos = mc.field_1724.method_19538();
            class_243 delta = this.goal.method_1020(playerPos);
            class_243 movement = e.getMotion();
            double hSpeedSetting = (double)this.horizontalSpeed.getValue();
            if (hSpeedSetting == (double)0.0F) {
               if (this.withinHoleXZ(this.goal)) {
                  movement = new class_243((double)0.0F, movement.field_1351, (double)0.0F);
               }
            } else {
               double currentH = Math.hypot(movement.field_1352, movement.field_1350);
               double limit = Math.max(hSpeedSetting, currentH);
               double deltaH = Math.hypot(delta.field_1352, delta.field_1350);
               if (deltaH > limit && deltaH > 1.0E-4) {
                  class_243 adj = delta.method_1029().method_18805(limit, (double)0.0F, limit);
                  delta = new class_243(adj.field_1352, delta.field_1351, adj.field_1350);
               }

               movement = new class_243(delta.field_1352, movement.field_1351, delta.field_1350);
            }

            double vSpeedSetting = (double)this.verticalSpeed.getValue();
            if (vSpeedSetting > (double)0.0F) {
               double limitV = Math.max(vSpeedSetting, Math.abs(movement.field_1351));
               double dY = Math.abs(delta.field_1351);
               double newY = delta.field_1351;
               if (dY > limitV && dY > 1.0E-4) {
                  newY = Math.signum(delta.field_1351) * limitV;
               }

               movement = new class_243(movement.field_1352, newY, movement.field_1350);
            }

            e.setMotion(movement);
         }
      }
   }

   private boolean withinHoleXZ(class_243 holeCenter) {
      class_238 bb = mc.field_1724.method_5829();
      return bb.field_1323 > holeCenter.field_1352 - (double)0.5F && bb.field_1320 < holeCenter.field_1352 + (double)0.5F && bb.field_1321 > holeCenter.field_1350 - (double)0.5F && bb.field_1324 < holeCenter.field_1350 + (double)0.5F;
   }

   private double sq(float v) {
      return (double)(v * v);
   }

   private class_243 centerOf(class_2338 pos) {
      return class_243.method_26410(pos, (double)0.0F);
   }

   private boolean isHoleAt(class_2338 topPos) {
      try {
         class_2680 a = mc.field_1687.method_8320(topPos);
         class_2680 b = mc.field_1687.method_8320(topPos.method_10084());
         if (a.method_26215() && b.method_26215()) {
            class_2338 floor = topPos.method_10074();
            if (!this.isSolid(floor)) {
               return false;
            } else {
               return this.isSolid(topPos.method_10095()) && this.isSolid(topPos.method_10072()) && this.isSolid(topPos.method_10078()) && this.isSolid(topPos.method_10067());
            }
         } else {
            return false;
         }
      } catch (Throwable var5) {
         return false;
      }
   }

   private boolean isSolid(class_2338 pos) {
      try {
         class_2680 st = mc.field_1687.method_8320(pos);
         if (st.method_26215()) {
            return false;
         } else {
            class_265 shape = st.method_26220(mc.field_1687, pos);
            if (!shape.method_1110()) {
               class_238 box = shape.method_1107();
               if (box.field_1320 - box.field_1323 >= 0.99 && box.field_1325 - box.field_1322 >= 0.99 && box.field_1324 - box.field_1321 >= 0.99) {
                  return true;
               }
            }

            try {
               if (st.method_26225()) {
                  return true;
               }
            } catch (Throwable var5) {
            }

            return false;
         }
      } catch (Throwable var6) {
         return false;
      }
   }

   public int horizontalDistance() {
      return (int)Math.ceil((double)this.maxDistance.getValue());
   }

   public int verticalDistance() {
      return (int)Math.ceil((double)this.maxDistance.getValue());
   }
}

