package dev.client.yanderov.features.impl.player;

import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import java.util.Comparator;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1548;
import net.minecraft.class_243;
import net.minecraft.class_2561;

public class AutoCreeperFarm extends Module implements QuickImports {
   private final SliderSettings searchRadius = (new SliderSettings("Ð Ð°Ð´Ð¸ÑƒÑ Ð¿Ð¾Ð¸ÑÐºÐ°", "Ð¡ÐºÐ°Ð½ Ñ€Ð°Ð´Ð¸ÑƒÑ ÐºÑ€Ð¸Ð¿ÐµÑ€Ð¾Ð²")).setValue(50.0F).range(10.0F, 120.0F);
   private final SliderSettings attackReach = (new SliderSettings("Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð°Ñ‚Ð°ÐºÐ¸", "Ð ÐµÐ°Ð»ÑŒÐ½Ð°Ñ Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð´Ð¾ Ñ‚Ð¾Ñ‡ÐºÐ¸ ÑƒÐ´Ð°Ñ€Ð°")).setValue(3.0F).range(2.0F, 6.0F);
   private final SliderSettings maxDistance = (new SliderSettings("ÐœÐ°ÐºÑ. Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ", "ÐŸÑ€ÐµÐ´ÐµÐ» Ð¿Ð¾Ð´Ñ…Ð¾Ð´Ð° Ðº ÐºÑ€Ð¸Ð¿ÐµÑ€Ñƒ Ð¿ÐµÑ€ÐµÐ´ Ð¾Ñ‚ÐºÐ°Ñ‚Ð¾Ð¼")).setValue(6.0F).range(3.0F, 12.0F);
   private final BooleanSetting useBaritone = (new BooleanSetting("Ð‘Ð°Ñ€Ð¸Ñ‚Ð¾Ð½: ÑÐ»ÐµÐ´Ð¾Ð²Ð°Ñ‚ÑŒ", "ÐžÑ‚Ð¿Ñ€Ð°Ð²Ð»ÑÑ‚ÑŒ ÐºÐ¾Ð¼Ð°Ð½Ð´Ñ‹ #follow/#stop")).setValue(false);
   private boolean retreating = false;
   private long lastHitTime = 0L;

   public AutoCreeperFarm() {
      super("CreeperFarm", ModuleCategory.PLAYER);
      this.setup(new Setting[]{this.searchRadius, this.attackReach, this.maxDistance, this.useBaritone});
   }

   public void activate() {
      super.activate();
      this.retreating = false;
      if (this.useBaritone.isValue()) {
         this.sendChatSilent("#follow entity creeper");
         this.sendChatSilent("#allowBreak false");
      }

   }

   public void deactivate() {
      super.deactivate();
      if (this.useBaritone.isValue()) {
         this.sendChatSilent("#stop");
      }

      this.retreating = false;
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc != null && mc.field_1724 != null && mc.field_1687 != null && mc.field_1761 != null) {
            class_1297 target = this.findCreeper(this.searchRadius.getValue());
            if (target == null) {
               this.retreating = false;
            } else {
               long now = System.currentTimeMillis();
               if (this.retreating) {
                  double dx = mc.field_1724.method_23317() - target.method_23317();
                  double dz = mc.field_1724.method_23321() - target.method_23321();
                  double dist = Math.sqrt(dx * dx + dz * dz);
                  if (!(dist > (double)this.maxDistance.getValue()) && now - this.lastHitTime <= 300L) {
                     if (dist > 1.0E-4) {
                        double vx = dx / dist * 0.3;
                        double vz = dz / dist * 0.3;
                        mc.field_1724.method_18800(vx, mc.field_1724.method_18798().field_1351, vz);
                        return;
                     }
                  } else {
                     this.retreating = false;
                  }
               }

               boolean inRange = mc.field_1724.method_5739(target) <= this.maxDistance.getValue();
               boolean reachOK = target instanceof class_1309 && this.distancePoint((class_1309)target) <= (double)this.attackReach.getValue();
               boolean canSee = mc.field_1724.method_6057(target);
               float cd = mc.field_1724.method_7261(0.0F);
               boolean grounded = (mc.field_1690 == null || !mc.field_1690.field_1903.method_1434()) && mc.field_1724.method_24828() || mc.field_1724.field_6017 > 0.0F;
               if (grounded && cd >= 1.0F && inRange && reachOK && canSee) {
                  mc.field_1761.method_2918(mc.field_1724, target);
                  mc.field_1724.method_6104(class_1268.field_5808);
                  this.lastHitTime = now;
                  this.retreating = true;
               }

            }
         }
      }
   }

   private class_1297 findCreeper(float radius) {
      double r2 = (double)(radius * radius);
      return (class_1297)mc.field_1687.method_8390(class_1548.class, mc.field_1724.method_5829().method_1014((double)radius), (e) -> e != null && mc.field_1724.method_5858(e) <= r2).stream().min(Comparator.comparingDouble((e) -> mc.field_1724.method_5858(e))).orElse((Object)null);
   }

   private double distancePoint(class_1309 ent) {
      class_243 eyes = ent.method_19538().method_1031((double)0.0F, (double)ent.method_17682() * (double)0.5F, (double)0.0F);
      return mc.field_1724.method_19538().method_1031((double)0.0F, (double)mc.field_1724.method_17682() * (double)0.5F, (double)0.0F).method_1022(eyes);
   }

   private void sendChatSilent(String msg) {
      try {
         if (mc.field_1724 != null && mc.field_1724.field_3944 != null) {
            try {
               mc.field_1724.field_3944.getClass().getMethod("sendChatMessage", String.class).invoke(mc.field_1724.field_3944, msg);
               return;
            } catch (Throwable var3) {
               if (mc.field_1705 != null) {
                  mc.field_1705.method_1743().method_1812(class_2561.method_30163(msg));
               }
            }
         }
      } catch (Throwable var4) {
      }

   }
}

