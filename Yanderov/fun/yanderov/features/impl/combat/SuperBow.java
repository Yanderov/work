package fun.Yanderov.features.impl.combat;

import fun.Yanderov.display.hud.Notifications;
import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.MoveEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1753;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_2846;
import net.minecraft.class_2848;
import net.minecraft.class_2846.class_2847;
import net.minecraft.class_2848.class_2849;

public class SuperBow extends Module implements QuickImports {
   private final SliderSettings power = (new SliderSettings("Ð¡Ð¸Ð»Ð°", "Ð”ÐµÐ»Ð°Ñ‚ÑŒ ÑÐ¸Ð»Ñƒ Ð»ÑƒÐºÑƒ Ð±Ð¾Ð»ÑŒÑˆÐµ")).setValue(30.0F).range(1.0F, 150.0F);
   private final BooleanSetting onlyWhenCharging = (new BooleanSetting("Ð¢Ð¾Ð»ÑŒÐºÐ¾ Ð¿Ñ€Ð¸ Ð½Ð°Ñ‚ÑÐ³Ðµ", "Ð Ð°Ð±Ð¾Ñ‚Ð°Ñ‚ÑŒ Ñ‚Ð¾Ð»ÑŒÐºÐ¾ ÐºÐ¾Ð³Ð´Ð° Ð½Ð°Ñ‚ÑÐ½ÑƒÑ‚ Ð»ÑƒÐº")).setValue(true);
   private final BooleanSetting slowForward = (new BooleanSetting("ÐœÐµÐ´Ð»ÐµÐ½Ð½Ð¾ Ð²Ð¿ÐµÑ€ÐµÐ´", "ÐžÑ‡ÐµÐ½ÑŒ Ð¼ÐµÐ´Ð»ÐµÐ½Ð½Ð¾Ðµ Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ðµ Ð²Ð¿ÐµÑ€ÐµÐ´ Ð¿Ñ€Ð¸ Ð½Ð°Ñ‚ÑÐ½ÑƒÑ‚Ð¾Ð¼ Ð»ÑƒÐºÐµ")).setValue(false);
   private final BooleanSetting fastCharge = (new BooleanSetting("Ð‘Ñ‹ÑÑ‚Ñ€Ñ‹Ð¹ Ð½Ð°Ñ‚ÑÐ³", "Ð‘Ñ‹ÑÑ‚Ñ€Ð¾ Ð½Ð°Ñ‚ÑÐ³Ð¸Ð²Ð°Ñ‚ÑŒ Ñ‚ÐµÑ‚Ð¸Ð²Ñƒ Ð´Ð¾ 100%")).setValue(false);
   private final BooleanSetting autoShoot = (new BooleanSetting("ÐÐ²Ñ‚Ð¾ Ð²Ñ‹ÑÑ‚Ñ€ÐµÐ»", "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ ÑÑ‚Ñ€ÐµÐ»ÑÑ‚ÑŒ Ð¿Ñ€Ð¸ 100% Ð½Ð°Ñ‚ÑÐ³Ðµ")).setValue(false);
   private final BooleanSetting notifyDamage = (new BooleanSetting("ÐÐ¾Ñ‚Ð¸Ñ„Ñ‹", "ÐŸÐ¾ÐºÐ°Ð·Ñ‹Ð²Ð°Ñ‚ÑŒ ÑƒÑ€Ð¾Ð½ Ð¿Ð¾ Ñ†ÐµÐ»Ð¸ Ð¿Ñ€Ð¸ Ð²Ñ‹ÑÑ‚Ñ€ÐµÐ»Ðµ SuperBow")).setValue(false);
   private boolean chargingBow;
   private boolean pendingHitCheck;
   private long shotTime;
   private final Map preShotHealth = new HashMap();

   public SuperBow() {
      super("SuperBow", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.power, this.onlyWhenCharging, this.slowForward, this.fastCharge, this.autoShoot, this.notifyDamage});
   }

   @EventHandler
   public void onPacket(PacketEvent event) {
      if (this.state && mc != null && mc.field_1724 != null && mc.field_1687 != null && mc.method_1562() != null) {
         if (event.getType() == PacketEvent.Type.SEND) {
            class_2596<?> raw = event.getPacket();
            if (raw instanceof class_2846) {
               class_2846 packet = (class_2846)raw;
               if (packet.method_12363() == class_2847.field_12974) {
                  if (mc.field_1724.method_6047().method_7909() instanceof class_1753 || mc.field_1724.method_6079().method_7909() instanceof class_1753) {
                     if (!this.onlyWhenCharging.isValue() || this.chargingBow) {
                        mc.method_1562().method_52787(new class_2848(mc.field_1724, class_2849.field_12981));
                        double x = mc.field_1724.method_23317();
                        double y = mc.field_1724.method_23318();
                        double z = mc.field_1724.method_23321();
                        float yaw = mc.field_1724.method_36454();
                        float pitch = mc.field_1724.method_36455();
                        boolean onGround = mc.field_1724.method_24828();
                        boolean horiz = mc.field_1724.field_5976;
                        double eps = 1.0E-9;
                        int count = (int)this.power.getValue();

                        for(int i = 0; i < count; ++i) {
                           mc.method_1562().method_52787(new class_2828.class_2829(x, y - eps, z, true, false));
                           mc.method_1562().method_52787(new class_2828.class_2829(x, y + eps, z, false, false));
                        }

                        for(int i = 0; i < 3; ++i) {
                           mc.method_1562().method_52787(new class_2828.class_2830(x, y, z, yaw, pitch, onGround, horiz));
                        }

                        if (this.notifyDamage.isValue()) {
                           this.preShotHealth.clear();

                           for(class_1297 entity : mc.field_1687.method_18112()) {
                              if (entity instanceof class_1309) {
                                 class_1309 living = (class_1309)entity;
                                 if (living != mc.field_1724 && living.method_5805()) {
                                    this.preShotHealth.put(living.method_5628(), living.method_6032());
                                 }
                              }
                           }

                           this.pendingHitCheck = true;
                           this.shotTime = System.currentTimeMillis();
                        }

                        this.chargingBow = false;
                     }
                  }
               }
            }
         }
      }
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.state && mc != null && mc.field_1724 != null && mc.field_1687 != null) {
         boolean using = mc.field_1724.method_6115();
         boolean hasItem = mc.field_1724.method_6030() != null;
         boolean isBow = hasItem && mc.field_1724.method_6030().method_7909() instanceof class_1753;
         this.chargingBow = using && isBow;
         if (isBow && this.fastCharge.isValue() && using && mc.method_1562() != null) {
            double x = mc.field_1724.method_23317();
            double y = mc.field_1724.method_23318();
            double z = mc.field_1724.method_23321();
            float yaw = mc.field_1724.method_36454();
            float pitch = mc.field_1724.method_36455();
            boolean onGround = mc.field_1724.method_24828();
            boolean horiz = mc.field_1724.field_5976;
            int packets = 3;

            for(int i = 0; i < packets; ++i) {
               mc.method_1562().method_52787(new class_2828.class_2830(x, y, z, yaw, pitch, onGround, horiz));
            }
         }

         if (this.autoShoot.isValue() && using && isBow) {
            try {
               if (mc.field_1724.method_6048() >= 20) {
                  mc.field_1724.method_6075();
               }
            } catch (Throwable var17) {
            }
         }

         if (this.pendingHitCheck && this.notifyDamage.isValue()) {
            long now = System.currentTimeMillis();
            if (now - this.shotTime > 1000L) {
               this.pendingHitCheck = false;
               this.preShotHealth.clear();
            } else {
               class_1309 bestTarget = null;
               float bestDamage = 0.0F;

               for(class_1297 entity : mc.field_1687.method_18112()) {
                  if (entity instanceof class_1309) {
                     class_1309 living = (class_1309)entity;
                     if (living != mc.field_1724 && living.method_5805()) {
                        Float before = (Float)this.preShotHealth.get(living.method_5628());
                        if (before != null) {
                           float after = living.method_6032();
                           float delta = before - after;
                           if (delta > 0.1F && delta > bestDamage) {
                              bestDamage = delta;
                              bestTarget = living;
                           }
                        }
                     }
                  }
               }

               if (bestTarget != null) {
                  float hearts = bestDamage / 2.0F;
                  float rounded = (float)Math.round(hearts * 10.0F) / 10.0F;
                  String name = bestTarget.method_5477().getString();
                  String text = name + " Ð±Ñ‹Ð»Ð¾ Ð½Ð°Ð½ÐµÑÐµÐ½Ð¾ " + rounded + " ÑÐµÑ€Ð´ÐµÑ‡ÐµÐº ÑƒÑ€Ð¾Ð½Ð°";
                  class_2561 msg = class_2561.method_43470(text).method_27692(class_124.field_1061);
                  Notifications.getInstance().addList(msg, 3000L);
                  this.pendingHitCheck = false;
                  this.preShotHealth.clear();
               }
            }
         }

      }
   }

   @EventHandler
   public void onMove(MoveEvent e) {
      if (this.state && this.slowForward.isValue()) {
         if (mc != null && mc.field_1724 != null && mc.field_1687 != null) {
            if (this.chargingBow) {
               double speed = 0.02;
               float yawRad = (float)Math.toRadians((double)mc.field_1724.method_36454());
               double dx = -Math.sin((double)yawRad) * speed;
               double dz = Math.cos((double)yawRad) * speed;
               class_243 current = e.getMovement();
               e.setMovement(new class_243(dx, current.field_1351, dz));
            }
         }
      }
   }
}

