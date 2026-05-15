package fun.Yanderov.features.impl.combat;

import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.MoveEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2664;
import net.minecraft.class_2708;
import net.minecraft.class_2743;
import net.minecraft.class_2828;
import net.minecraft.class_2846;
import net.minecraft.class_3532;
import net.minecraft.class_2846.class_2847;

public class Velocity extends Module {
   private final SelectSetting mode = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ñ€ÐµÐ¶Ð¸Ð¼ ÑƒÐ¼ÐµÐ½ÑŒÑˆÐµÐ½Ð¸Ñ Ð¾Ñ‚Ð´Ð°Ñ‡Ð¸")).value("Normal", "Grim-Skip", "Grim-Normal", "Grim-Normal2", "NewGrim", "FunTime-Old", "OldGrim", "Matrix", "GrimLegit").selected("NewGrim");
   private boolean flag;
   private int grimTicks;
   private int ccCooldown;
   private int skip;
   private boolean cancelFlag;
   private boolean damaged;
   private final SliderSettings untilCount = (new SliderSettings("Until", "Until")).setValue(4.0F).range(2.0F, 8.0F);
   private final SliderSettings afterCount = (new SliderSettings("After", "After")).setValue(2.0F).range(1.0F, 10.0F);
   private final BooleanSetting countScirr = (new BooleanSetting("VelCount counter", "VelCount counter")).setValue(false);
   private final BooleanSetting workOnlyInNetherArmor = (new BooleanSetting("Work only INA", "Work only INA")).setValue(false);
   private final BooleanSetting log = (new BooleanSetting("Logging", "Logging")).setValue(true);
   private int count;
   private int sneakCount;
   private class_243 lastMotion;

   public static Velocity getInstance() {
      return (Velocity)Instance.get(Velocity.class);
   }

   public Velocity() {
      super("Velocity", ModuleCategory.COMBAT);
      this.lastMotion = class_243.field_1353;
      this.setup(new Setting[]{this.mode, this.untilCount, this.afterCount, this.countScirr, this.workOnlyInNetherArmor, this.log});
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (this.state) {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            if (e.getType() == PacketEvent.Type.RECEIVE) {
               Object pkt = e.getPacket();
               if (pkt instanceof class_2743) {
                  class_2743 vel = (class_2743)pkt;
                  if (vel.method_11818() == mc.field_1724.method_5628()) {
                     if (this.mode.isSelected("GrimLegit")) {
                        this.lastMotion = new class_243(vel.method_11815(), vel.method_11816(), vel.method_11819());
                     }

                     if (this.mode.isSelected("Normal")) {
                        e.setCancelled(true);
                        return;
                     }
                  }
               }

               if (this.mode.isSelected("Grim-Skip") && pkt instanceof class_2743) {
                  class_2743 vel = (class_2743)pkt;
                  if (vel.method_11818() == mc.field_1724.method_5628()) {
                     this.skip = 6;
                     e.setCancelled(true);
                     return;
                  }
               }

               if (this.mode.isSelected("Grim-Normal")) {
                  if (pkt instanceof class_2743) {
                     class_2743 vel = (class_2743)pkt;
                     if (vel.method_11818() == mc.field_1724.method_5628()) {
                        e.setCancelled(true);
                        this.cancelFlag = true;
                        return;
                     }
                  }

                  if (pkt instanceof class_2708) {
                     this.skip = 3;
                     return;
                  }
               }

               if (this.mode.isSelected("Grim-Normal2")) {
                  if (pkt instanceof class_2743) {
                     class_2743 vel = (class_2743)pkt;
                     if (vel.method_11818() == mc.field_1724.method_5628()) {
                        this.skip = 8;
                        e.setCancelled(true);
                        return;
                     }
                  }

                  if (pkt instanceof class_2708) {
                     this.skip = -8;
                     return;
                  }

                  if (this.isConfirmScreenAction(pkt)) {
                     if (this.skip < 0) {
                        ++this.skip;
                     } else if (this.skip > 1) {
                        --this.skip;
                        e.setCancelled(true);
                     }

                     return;
                  }
               }

               if (this.mode.isSelected("FunTime-Old")) {
                  if (pkt instanceof class_2743) {
                     class_2743 vel = (class_2743)pkt;
                     if (vel.method_11818() == mc.field_1724.method_5628()) {
                        if (this.skip >= 2) {
                           return;
                        }

                        e.setCancelled(true);
                        this.damaged = true;
                        return;
                     }
                  }

                  if (pkt instanceof class_2708) {
                     this.skip = 3;
                     return;
                  }
               }

               if (this.mode.isSelected("NewGrim")) {
                  if (this.ccCooldown <= 0) {
                     if (pkt instanceof class_2743) {
                        class_2743 vel = (class_2743)pkt;
                        if (vel.method_11818() == mc.field_1724.method_5628()) {
                           e.setCancelled(true);
                           this.flag = true;
                        }
                     }

                     if (pkt instanceof class_2664) {
                        e.setCancelled(true);
                        this.flag = true;
                     }
                  }

                  if (pkt instanceof class_2708) {
                     this.ccCooldown = 5;
                  }

                  return;
               }

               if (this.mode.isSelected("OldGrim") && pkt instanceof class_2743) {
                  class_2743 vel = (class_2743)pkt;
                  if (vel.method_11818() == mc.field_1724.method_5628()) {
                     e.setCancelled(true);
                     this.grimTicks = 6;
                     return;
                  }
               }
            }

            if (e.getType() == PacketEvent.Type.SEND) {
               Object pkt = e.getPacket();
               if (this.mode.isSelected("Grim-Skip") && pkt instanceof class_2828 && this.skip > 0) {
                  --this.skip;
                  e.setCancelled(true);
                  return;
               }

               if (this.mode.isSelected("Grim-Normal") && pkt instanceof class_2828) {
                  --this.skip;
                  if (this.cancelFlag && this.skip <= 0) {
                     this.sendFixPackets();
                     this.cancelFlag = false;
                  }

                  return;
               }
            }

         }
      }
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.state && mc.field_1724 != null && mc.field_1687 != null) {
         if (this.mode.isSelected("Matrix") && mc.field_1724.field_6235 > 0 && !mc.field_1724.method_24828()) {
            double var3 = (double)(mc.field_1724.method_36454() * ((float)Math.PI / 180F));
            double var5 = Math.sqrt(mc.field_1724.method_18798().field_1352 * mc.field_1724.method_18798().field_1352 + mc.field_1724.method_18798().field_1350 * mc.field_1724.method_18798().field_1350);
            mc.field_1724.method_18800(-Math.sin(var3) * var5, mc.field_1724.method_18798().field_1351, Math.cos(var3) * var5);
            mc.field_1724.method_5728(mc.field_1724.field_6012 % 2 != 0);
         }

         if (this.mode.isSelected("NewGrim") && this.flag) {
            if (this.ccCooldown <= 0) {
               this.sendFixPackets();
            }

            this.flag = false;
         }

         if (this.mode.isSelected("OldGrim") && this.grimTicks > 0) {
            --this.grimTicks;
         }

         if (this.mode.isSelected("FunTime-Old")) {
            --this.skip;
            if (this.damaged) {
               this.sendFixPackets();
               this.damaged = false;
            }
         }

         if (this.ccCooldown > 0) {
            --this.ccCooldown;
         }

         if (this.mode.isSelected("GrimLegit")) {
            if (mc.field_1724.field_6235 == 9) {
               ++this.count;
               ++this.sneakCount;
            }

            if (this.sneakCount > 5) {
               this.sneakCount = 0;
            }

            if ((float)this.count > this.untilCount.getValue() && (float)this.count < this.untilCount.getValue() + 2.0F) {
               this.count = (int)(this.untilCount.getValue() + 2.0F);
            }

            if ((float)this.count > this.untilCount.getValue() + this.afterCount.getValue()) {
               this.count = 0;
            }
         }

      }
   }

   public void activate() {
      super.activate();
      this.grimTicks = 0;
      this.flag = false;
      this.ccCooldown = 0;
      this.skip = 0;
      this.cancelFlag = false;
      this.damaged = false;
      this.count = 0;
      this.sneakCount = 0;
      this.lastMotion = class_243.field_1353;
   }

   @EventHandler
   public void onMove(MoveEvent e) {
      if (this.state && this.mode.isSelected("GrimLegit")) {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            if (this.hasVelocityHandle()) {
               class_243 future = mc.field_1724.method_19538().method_1019(this.lastMotion);
               class_241 targetRot = this.calculate(mc.field_1724.method_19538(), future);
               float direction = class_3532.method_15393(mc.field_1724.method_36454() - targetRot.field_1343);
               float forward = 0.0F;
               float strafe = 0.0F;
               if ((direction > 120.0F || direction < -120.0F) && mc.field_1724.field_6235 >= 8) {
                  forward = 1.0F;
               }

               if (direction > -150.0F && direction < -60.0F && mc.field_1724.field_6235 >= 8) {
                  strafe = 1.0F;
               }

               if (direction > -60.0F && direction < 60.0F && mc.field_1724.field_6235 >= 9) {
                  forward = -1.0F;
               }

               if (direction > 60.0F && direction < 150.0F && mc.field_1724.field_6235 >= 8) {
                  strafe = -1.0F;
               }

               class_243 move = e.getMovement();
               double speed = Math.sqrt(move.field_1352 * move.field_1352 + move.field_1350 * move.field_1350);
               if (speed < 0.1) {
                  speed = 0.1;
               }

               if (forward != 0.0F || strafe != 0.0F) {
                  float yawRad = (float)Math.toRadians((double)mc.field_1724.method_36454());
                  double sin = Math.sin((double)yawRad);
                  double cos = Math.cos((double)yawRad);
                  double dx = ((double)forward * -sin + (double)strafe * cos) * speed;
                  double dz = ((double)forward * cos + (double)strafe * sin) * speed;
                  double dy = move.field_1351;
                  if (mc.field_1724.method_24828()) {
                     dy = 0.42;
                  }

                  e.setMovement(new class_243(dx, dy, dz));
               }

            }
         }
      }
   }

   private boolean hasVelocityHandle() {
      return mc.field_1724.field_6235 > 0 && this.counter() && this.handleNether() && this.falsePosCheck();
   }

   private boolean counter() {
      if (!this.countScirr.isValue()) {
         return true;
      } else {
         return this.count >= 0 && (float)this.count <= this.untilCount.getValue();
      }
   }

   private boolean handleNether() {
      return this.workOnlyInNetherArmor.isValue() ? this.isNether() : true;
   }

   private boolean falsePosCheck() {
      return !mc.field_1724.method_5799() && !mc.field_1724.method_5771();
   }

   private boolean isNether() {
      for(class_1799 armor : mc.field_1724.method_5661()) {
         if (!armor.method_7960() && armor.method_7909() instanceof class_1792) {
            String name = armor.method_7909().method_7876().toLowerCase();
            if (name.contains("netherite")) {
               return true;
            }
         }
      }

      return false;
   }

   private class_241 calculate(class_243 from, class_243 to) {
      class_243 diff = to.method_1020(from);
      double distance = Math.hypot(diff.field_1352, diff.field_1350);
      float yaw = (float)(class_3532.method_15349(diff.field_1350, diff.field_1352) * (double)180.0F / Math.PI) - 90.0F;
      float pitch = (float)(-(class_3532.method_15349(diff.field_1351, distance) * (double)180.0F / Math.PI));
      return new class_241(yaw, pitch);
   }

   private void setVelocityX(class_2743 packet, int value) {
      try {
         Field field = class_2743.class.getDeclaredField("velocityX");
         field.setAccessible(true);
         field.setInt(packet, value);
      } catch (Exception ex) {
         ex.printStackTrace();
      }

   }

   private void setVelocityZ(class_2743 packet, int value) {
      try {
         Field field = class_2743.class.getDeclaredField("velocityZ");
         field.setAccessible(true);
         field.setInt(packet, value);
      } catch (Exception ex) {
         ex.printStackTrace();
      }

   }

   private void sendFixPackets() {
      if (mc.method_1562() != null) {
         try {
            class_2596<?> move = this.buildMovePacketReflective();
            if (move != null) {
               mc.method_1562().method_52787(move);
            }

            class_2338 bp = class_2338.method_49637(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321());
            mc.method_1562().method_52787(new class_2846(class_2847.field_12973, bp, class_2350.field_11036));
         } catch (Throwable var3) {
         }

      }
   }

   private class_2596 buildMovePacketReflective() {
      try {
         double x = mc.field_1724.method_23317();
         double y = mc.field_1724.method_23318();
         double z = mc.field_1724.method_23321();
         float yaw = mc.field_1724.method_36454();
         float pitch = mc.field_1724.method_36455();
         boolean onGround = mc.field_1724.method_24828();
         Class<?> outer = class_2828.class;

         for(Class c : outer.getDeclaredClasses()) {
            for(Constructor ctor : c.getDeclaredConstructors()) {
               try {
                  ctor.setAccessible(true);
                  Class<?>[] pt = ctor.getParameterTypes();
                  if (pt.length != 0) {
                     Object[] args = new Object[pt.length];
                     int di = 0;
                     int fi = 0;
                     int bi = 0;
                     boolean supported = true;

                     for(int i = 0; i < pt.length; ++i) {
                        Class<?> t = pt[i];
                        if (t != Double.TYPE && t != Double.class) {
                           if (t != Float.TYPE && t != Float.class) {
                              if (t != Boolean.TYPE && t != Boolean.class) {
                                 supported = false;
                                 break;
                              }

                              args[i] = bi == 0 ? onGround : false;
                              ++bi;
                           } else {
                              float val = fi == 0 ? yaw : pitch;
                              if (fi > 1) {
                                 supported = false;
                                 break;
                              }

                              args[i] = val;
                              ++fi;
                           }
                        } else {
                           double val = di == 0 ? x : (di == 1 ? y : z);
                           if (di > 2) {
                              supported = false;
                              break;
                           }

                           args[i] = val;
                           ++di;
                        }
                     }

                     if (supported) {
                        Object pkt = ctor.newInstance(args);
                        if (pkt instanceof class_2596) {
                           class_2596<?> p = (class_2596)pkt;
                           return p;
                        }
                     }
                  }
               } catch (Throwable var29) {
               }
            }
         }
      } catch (Throwable var30) {
      }

      return null;
   }

   private boolean isConfirmScreenAction(Object p) {
      String name = p.getClass().getName();
      return name.endsWith("SConfirmTransactionPacket") || name.endsWith("ConfirmScreenActionS2CPacket");
   }

   public void setFlag(boolean flag) {
      this.flag = flag;
   }

   public void setGrimTicks(int grimTicks) {
      this.grimTicks = grimTicks;
   }

   public void setCcCooldown(int ccCooldown) {
      this.ccCooldown = ccCooldown;
   }

   public void setSkip(int skip) {
      this.skip = skip;
   }

   public void setCancelFlag(boolean cancelFlag) {
      this.cancelFlag = cancelFlag;
   }

   public void setDamaged(boolean damaged) {
      this.damaged = damaged;
   }

   public void setCount(int count) {
      this.count = count;
   }

   public void setSneakCount(int sneakCount) {
      this.sneakCount = sneakCount;
   }

   public void setLastMotion(class_243 lastMotion) {
      this.lastMotion = lastMotion;
   }

   public SelectSetting getMode() {
      return this.mode;
   }

   public boolean isFlag() {
      return this.flag;
   }

   public int getGrimTicks() {
      return this.grimTicks;
   }

   public int getCcCooldown() {
      return this.ccCooldown;
   }

   public int getSkip() {
      return this.skip;
   }

   public boolean isCancelFlag() {
      return this.cancelFlag;
   }

   public boolean isDamaged() {
      return this.damaged;
   }

   public SliderSettings getUntilCount() {
      return this.untilCount;
   }

   public SliderSettings getAfterCount() {
      return this.afterCount;
   }

   public BooleanSetting getCountScirr() {
      return this.countScirr;
   }

   public BooleanSetting getWorkOnlyInNetherArmor() {
      return this.workOnlyInNetherArmor;
   }

   public BooleanSetting getLog() {
      return this.log;
   }

   public int getCount() {
      return this.count;
   }

   public int getSneakCount() {
      return this.sneakCount;
   }

   public class_243 getLastMotion() {
      return this.lastMotion;
   }
}

