package dev.client.yanderov.features.impl.combat;

import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.events.player.PlayerTravelEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.Instance;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import java.util.Random;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2663;
import net.minecraft.class_2824;
import net.minecraft.class_3532;

public class MoveHelper extends Module implements QuickImports {
   private final SelectSetting mode = (new SelectSetting("Mode", "Ð ÐµÐ¶Ð¸Ð¼ Ñ€Ð°Ð±Ð¾Ñ‚Ñ‹")).value("SmartSpeed", "ComboMode").selected("SmartSpeed");
   private final SelectSetting smartBehavior = (new SelectSetting("Behavior", "ÐŸÐ¾Ð²ÐµÐ´ÐµÐ½Ð¸Ðµ SmartSpeed")).value("PlayerBack", "PlayerFace").selected("PlayerBack").visible(() -> this.mode.isSelected("SmartSpeed"));
   private final SliderSettings smartDistance = (new SliderSettings("Distance", "Ð–ÐµÐ»Ð°ÐµÐ¼Ð°Ñ Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð´Ð¾ Ñ†ÐµÐ»Ð¸")).setValue(2.6F).range(0.5F, 6.0F).visible(() -> this.mode.isSelected("SmartSpeed"));
   private final BooleanSetting sTapCombo = (new BooleanSetting("STapCombo", "ÐšÐ¾Ð¼Ð±Ð¾ S-Tap")).setValue(true).visible(() -> this.mode.isSelected("ComboMode"));
   private final SliderSettings stapDelayMs = (new SliderSettings("stapDelay", "Ð’Ñ€ÐµÐ¼Ñ Ð·Ð°Ð¶Ð°Ñ‚Ð¸Ñ S (Ð¼Ñ)")).setValue(120.0F).range(20.0F, 400.0F).visible(() -> this.mode.isSelected("ComboMode") && this.sTapCombo.isValue());
   private final SliderSettings stapChance = (new SliderSettings("chance", "Ð¨Ð°Ð½Ñ ÑÑ€Ð°Ð±Ð°Ñ‚Ñ‹Ð²Ð°Ð½Ð¸Ñ, %")).setValue(65.0F).range(0.0F, 100.0F).visible(() -> this.mode.isSelected("ComboMode") && this.sTapCombo.isValue());
   private final SliderSettings stapDistance = (new SliderSettings("distance", "Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð°ÐºÑ‚Ð¸Ð²Ð°Ñ†Ð¸Ð¸ S-Tap")).setValue(3.2F).range(0.5F, 6.0F).visible(() -> this.mode.isSelected("ComboMode") && this.sTapCombo.isValue());
   private final BooleanSetting comboBreaker = (new BooleanSetting("ComboBreaker", "ÐŸÑ€ÐµÑ€Ñ‹Ð²Ð°Ñ‚ÑŒ Ñ‡ÑƒÐ¶Ð¾Ðµ ÐºÐ¾Ð¼Ð±Ð¾")).setValue(true).visible(() -> this.mode.isSelected("ComboMode"));
   private final SliderSettings breakerHits = (new SliderSettings("hit", "ÐšÐ¾Ð»-Ð²Ð¾ Ñ‡ÑƒÐ¶Ð¸Ñ… Ð¿Ð¾Ð´Ñ€ÑÐ´ ÑƒÐ´Ð°Ñ€Ð¾Ð²")).setValue(3.0F).range(1.0F, 10.0F).visible(() -> this.mode.isSelected("ComboMode") && this.comboBreaker.isValue());
   private final SliderSettings freezeDelayMs = (new SliderSettings("freezeDelay", "Ð”Ð»Ð¸Ñ‚ÐµÐ»ÑŒÐ½Ð¾ÑÑ‚ÑŒ AirStuck (Ð¼Ñ)")).setValue(220.0F).range(50.0F, 700.0F).visible(() -> this.mode.isSelected("ComboMode") && this.comboBreaker.isValue());
   private final BooleanSetting overrideMovement = (new BooleanSetting("Override Movement", "ÐŸÐµÑ€ÐµÐ¾Ð¿Ñ€ÐµÐ´ÐµÐ»ÑÑ‚ÑŒ Ð´Ñ€ÑƒÐ³Ð¸Ðµ Ð¼ÑƒÐ²Ð¼ÐµÐ½Ñ‚ Ñ„ÑƒÐ½ÐºÑ†Ð¸Ð¸")).setValue(true);
   private long stapUntilMs = 0L;
   private long airStuckUntilMs = 0L;
   private int enemyHitStreak = 0;
   private long lastOurHitMs = 0L;
   private final Random rnd = new Random();

   public MoveHelper() {
      super("MoveHelper", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.mode, this.smartBehavior, this.smartDistance, this.sTapCombo, this.stapDelayMs, this.stapChance, this.stapDistance, this.comboBreaker, this.breakerHits, this.freezeDelayMs, this.overrideMovement});
   }

   private class_1309 resolveTarget() {
      try {
         Aura aura = Aura.getInstance();
         if (aura != null && aura.getTarget() != null) {
            return aura.getTarget();
         }
      } catch (Throwable var9) {
      }

      if (mc.field_1687 != null && mc.field_1724 != null) {
         class_1309 best = null;
         double bestD = Double.MAX_VALUE;

         for(class_1297 e : mc.field_1687.method_18112()) {
            if (e instanceof class_1309) {
               class_1309 le = (class_1309)e;
               if (le != mc.field_1724 && !le.method_31481() && !le.method_29504()) {
                  double d = (double)le.method_5739(mc.field_1724);
                  if (d < bestD) {
                     bestD = d;
                     best = le;
                  }
               }
            }
         }

         return best;
      } else {
         return null;
      }
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (mc.field_1724 != null) {
         if (e.isSend()) {
            class_2596 var3 = e.getPacket();
            if (var3 instanceof class_2824) {
               class_2824 pkt = (class_2824)var3;

               try {
                  Object it = this.getInteractTypeCompat(pkt);
                  if (it != null && String.valueOf(it).toUpperCase().contains("ATTACK")) {
                     this.lastOurHitMs = System.currentTimeMillis();
                     this.enemyHitStreak = 0;
                     if (this.mode.isSelected("ComboMode") && this.sTapCombo.isValue()) {
                        class_1309 t = this.resolveTarget();
                        double dist = t != null ? (double)t.method_5739(mc.field_1724) : (double)999.0F;
                        if (dist <= (double)this.stapDistance.getValue() && this.rnd.nextDouble() * (double)100.0F <= (double)this.stapChance.getValue()) {
                           this.stapUntilMs = System.currentTimeMillis() + (long)this.stapDelayMs.getValue();
                        }
                     }
                  }
               } catch (Throwable var7) {
               }
            }
         }

         if (!e.isSend()) {
            class_2596 var10 = e.getPacket();
            if (var10 instanceof class_2663) {
               class_2663 s2c = (class_2663)var10;
               class_1297 ent = s2c.method_11469(mc.field_1687);
               if (ent != null && ent.equals(mc.field_1724)) {
                  byte st = s2c.method_11470();
                  if (st == 2 || st == 33) {
                     ++this.enemyHitStreak;
                     if (this.mode.isSelected("ComboMode") && this.comboBreaker.isValue() && this.enemyHitStreak >= (int)this.breakerHits.getValue()) {
                        this.airStuckUntilMs = System.currentTimeMillis() + (long)this.freezeDelayMs.getValue();
                        this.enemyHitStreak = 0;
                     }
                  }
               }
            }
         }

      }
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc.field_1724 != null) {
            long now = System.currentTimeMillis();
            if (now > this.stapUntilMs) {
               this.stapUntilMs = 0L;
            }

            if (now > this.airStuckUntilMs) {
               this.airStuckUntilMs = 0L;
            }

         }
      }
   }

   @EventHandler
   public void onTravel(PlayerTravelEvent e) {
      if (this.isState() && mc.field_1724 != null && mc.field_1687 != null) {
         if (e.isPre()) {
            class_243 m = e.getMotion();
            if (this.mode.isSelected("ComboMode") && this.airStuckUntilMs > System.currentTimeMillis()) {
               e.setMotion(new class_243((double)0.0F, m.field_1351, (double)0.0F));
               if (this.overrideMovement.isValue()) {
                  e.cancel();
               }

            } else if (this.mode.isSelected("ComboMode") && this.stapUntilMs > System.currentTimeMillis()) {
               float yaw = mc.field_1724.method_36454();
               double bx = (double)(-class_3532.method_15374((float)Math.toRadians((double)yaw))) * (double)0.25F;
               double bz = (double)class_3532.method_15362((float)Math.toRadians((double)yaw)) * (double)0.25F;
               e.setMotion(new class_243(m.field_1352 + bx, m.field_1351, m.field_1350 + bz));
               if (this.overrideMovement.isValue()) {
                  e.cancel();
               }

            } else {
               if (this.mode.isSelected("SmartSpeed")) {
                  class_1309 t = this.resolveTarget();
                  if (t != null) {
                     class_243 playerPos = mc.field_1724.method_19538();
                     class_243 targetPos = t.method_19538();
                     class_243 toTarget = targetPos.method_1020(playerPos);
                     double dist = toTarget.method_1033();
                     if (dist < 0.001) {
                        return;
                     }

                     class_243 dirToTarget = toTarget.method_1029();
                     class_243 desiredDir;
                     if (this.smartBehavior.isSelected("PlayerBack")) {
                        float tyaw = t.method_36454();
                        desiredDir = new class_243((double)(-class_3532.method_15374((float)Math.toRadians((double)tyaw))), (double)0.0F, (double)class_3532.method_15362((float)Math.toRadians((double)tyaw)));
                     } else {
                        float tyaw = t.method_36454();
                        desiredDir = new class_243((double)class_3532.method_15374((float)Math.toRadians((double)tyaw)), (double)0.0F, (double)(-class_3532.method_15362((float)Math.toRadians((double)tyaw))));
                     }

                     double desired = (double)this.smartDistance.getValue();
                     class_243 adjust = class_243.field_1353;
                     if (dist > desired + 0.2) {
                        adjust = adjust.method_1019(dirToTarget.method_1021((double)0.25F));
                     }

                     if (dist < desired - 0.2) {
                        adjust = adjust.method_1019(dirToTarget.method_1021((double)-0.25F));
                     }

                     class_243 currentDir = m.method_1027() > (double)0.0F ? m.method_1029() : class_243.field_1353;
                     class_243 blendedDir = desiredDir.method_1021(0.55).method_1019(currentDir.method_1021(0.45)).method_1019(adjust);
                     if (blendedDir.method_1027() > 1.0E-6) {
                        class_243 finalDir = blendedDir.method_1029();
                        double speed = m.method_1033();
                        e.setMotion(finalDir.method_1021(speed));
                        if (this.overrideMovement.isValue()) {
                           e.cancel();
                        }
                     }
                  }
               }

            }
         }
      }
   }

   private Object getInteractTypeCompat(class_2824 pkt) {
      try {
         return class_2824.class.getMethod("getType").invoke(pkt);
      } catch (Throwable var5) {
         try {
            return class_2824.class.getMethod("getInteractionType").invoke(pkt);
         } catch (Throwable var4) {
            try {
               return class_2824.class.getMethod("getAction").invoke(pkt);
            } catch (Throwable var3) {
               return null;
            }
         }
      }
   }

   public static MoveHelper getInstance() {
      return (MoveHelper)Instance.get(MoveHelper.class);
   }
}

