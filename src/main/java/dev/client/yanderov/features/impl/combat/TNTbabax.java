package dev.client.yanderov.features.impl.combat;

import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1701;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_3965;
import net.minecraft.class_742;
import net.minecraft.class_746;
import net.minecraft.class_239.class_240;

public class TNTbabax extends Module implements QuickImports {
   private final SliderSettings range = (new SliderSettings("Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ", "Ð Ð°Ð´Ð¸ÑƒÑ Ð¿Ñ€Ð¾Ð²ÐµÑ€ÐºÐ¸ Ð²Ñ€Ð°Ð³Ð°")).setValue(5.0F).range(1.0F, 10.0F);
   private class_2338 railPos;
   private class_1701 minecart;
   private boolean placed;
   private boolean chargingBow;
   private long chargeStartMs;

   public TNTbabax() {
      super("TNTbabax", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.range});
   }

   public void activate() {
      super.activate();
      this.railPos = null;
      this.minecart = null;
      this.placed = false;
      this.chargingBow = false;
      this.chargeStartMs = 0L;
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.isState()) {
         if (e.getType() == 0) {
            if (mc.field_1724 != null && mc.field_1687 != null && mc.field_1761 != null) {
               if (!this.placed) {
                  if (!this.placeRailsAndMinecart()) {
                     this.setState(false);
                  } else {
                     this.placed = true;
                  }
               } else {
                  if (this.minecart == null || !this.minecart.method_5805()) {
                     if (this.railPos != null) {
                        this.locateMinecartAround(this.railPos);
                     }

                     if (this.minecart == null || !this.minecart.method_5805()) {
                        this.setState(false);
                        return;
                     }
                  }

                  if (this.chargingBow) {
                     long elapsed = System.currentTimeMillis() - this.chargeStartMs;
                     if (elapsed > 350L) {
                        mc.field_1724.method_6075();
                        this.chargingBow = false;
                        this.setState(false);
                     }

                  } else {
                     if (this.isEnemyNear(this.minecart.method_19538(), this.range.getValue())) {
                        this.shootArrowAtMinecart();
                     }

                  }
               }
            }
         }
      }
   }

   private boolean placeRailsAndMinecart() {
      class_239 var2 = mc.field_1765;
      if (var2 instanceof class_3965 hit) {
         if (hit.method_17783() == class_240.field_1332) {
            class_2338 target = hit.method_17777();
            class_2350 side = hit.method_17780();
            class_2338 placePos = side == class_2350.field_11036 ? target : target.method_10084();
            int railSlot = this.findHotbarItem(class_1802.field_8129);
            if (railSlot == -1) {
               return false;
            }

            int prev = mc.field_1724.method_31548().field_7545;
            mc.field_1724.method_31548().field_7545 = railSlot;
            class_3965 placeHit = new class_3965(class_243.method_24953(placePos), class_2350.field_11036, placePos, false);
            mc.field_1761.method_2896(mc.field_1724, class_1268.field_5808, placeHit);
            mc.field_1724.method_6104(class_1268.field_5808);
            mc.field_1724.method_31548().field_7545 = prev;
            int tntSlot = this.findHotbarItem(class_1802.field_8069);
            if (tntSlot == -1) {
               return false;
            }

            prev = mc.field_1724.method_31548().field_7545;
            mc.field_1724.method_31548().field_7545 = tntSlot;
            mc.field_1761.method_2896(mc.field_1724, class_1268.field_5808, placeHit);
            mc.field_1724.method_6104(class_1268.field_5808);
            mc.field_1724.method_31548().field_7545 = prev;
            this.railPos = placePos;
            this.locateMinecartAround(placePos);
            return true;
         }
      }

      return false;
   }

   private void locateMinecartAround(class_2338 pos) {
      class_238 box = (new class_238(pos)).method_1014((double)1.5F);

      for(class_1297 e : mc.field_1687.method_8335((class_1297)null, box)) {
         if (e.method_5864() == class_1299.field_6053 && e instanceof class_1701 tnt) {
            this.minecart = tnt;
            return;
         }
      }

   }

   private boolean isEnemyNear(class_243 pos, float dist) {
      for(class_742 p : mc.field_1687.method_18456()) {
         if (p != mc.field_1724 && p.method_19538().method_1025(pos) <= (double)(dist * dist)) {
            return true;
         }
      }

      return false;
   }

   private void shootArrowAtMinecart() {
      class_746 player = mc.field_1724;
      int bow = this.findHotbarItem(class_1802.field_8102);
      boolean hasArrows = this.hasArrows();
      if (bow != -1 && hasArrows) {
         int prev = player.method_31548().field_7545;
         player.method_31548().field_7545 = bow;
         mc.field_1761.method_2919(player, class_1268.field_5808);
         this.chargingBow = true;
         this.chargeStartMs = System.currentTimeMillis();
      }
   }

   private boolean hasArrows() {
      for(int i = 0; i < mc.field_1724.method_31548().method_5439(); ++i) {
         class_1799 st = mc.field_1724.method_31548().method_5438(i);
         if (!st.method_7960() && st.method_31574(class_1802.field_8107)) {
            return true;
         }
      }

      return false;
   }

   private int findHotbarItem(class_1792 item) {
      for(int i = 0; i < 9; ++i) {
         class_1799 s = mc.field_1724.method_31548().method_5438(i);
         if (!s.method_7960() && s.method_31574(item)) {
            return i;
         }
      }

      return -1;
   }
}

