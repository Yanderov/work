package dev.client.modules.impl.movement;

import dev.client.WildClient;
import dev.client.event.classes.TickEvent;
import dev.client.event.interfaces.ITickable;
import dev.client.modules.Category;
import dev.client.modules.IDisableable;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import dev.client.modules.impl.combat.Aura;
import dev.client.modules.settings.impl.BooleanSetting;
import dev.client.modules.settings.impl.FloatSetting;
import dev.client.modules.settings.impl.ModeSetting;
import dev.client.util.IUtil;
import dev.client.util.math.TimerUtil;
import dev.client.util.player.MovementUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Environment(EnvType.CLIENT)
public class Speed extends Module implements ITickable, IDisableable, IUtil {
   // Основные настройки
   private final ModeSetting mode = new ModeSetting()
           .name("Mode")
           .value("Vanilla")
           .modes("Vanilla", "Matrix", "Timer", "Sunrise DMG", "NCP", "Boat", "Grim", "HolyWorld", "45Degree", "Collision", "MetaHvH");
   
   public FloatSetting speed = new FloatSetting() {
      public boolean isVisible() {
         return Speed.this.mode.is("Vanilla");
      }
   }.name("Speed").minValue(0.1F).value(0.5F).maxValue(2.0F).incriment(0.01F);
   
   // Новые настройки из предоставленного файла
   private final FloatSetting matrixSpeed = new FloatSetting() {
      public boolean isVisible() {
         return Speed.this.mode.is("Matrix");
      }
   }.name("Matrix Speed").value(0.36F).minValue(0.1F).maxValue(0.7F).incriment(0.01F);

   private final FloatSetting collisionSpeed = new FloatSetting() {
      public boolean isVisible() {
         return Speed.this.mode.is("Collision");
      }
   }.name("Collision Speed").value(1.1F).minValue(0.5F).maxValue(2.0F).incriment(0.05F);

   private final FloatSetting holySpeed = new FloatSetting() {
      public boolean isVisible() {
         return Speed.this.mode.is("HolyWorld");
      }
   }.name("Holy Speed").value(1.35F).minValue(1.1F).maxValue(4.0F).incriment(0.05F);

   private final FloatSetting collisionDist = new FloatSetting() {
      public boolean isVisible() {
         return Speed.this.mode.is("HolyWorld");
      }
   }.name("Collision Dist").value(0.244F).minValue(0.2F).maxValue(0.95F).incriment(0.01F);

   private final BooleanSetting autoJump = new BooleanSetting() {
      public boolean isVisible() {
         return Speed.this.mode.is("Matrix") || Speed.this.mode.is("MetaHvH");
      }
   }.name("Auto Jump").value(true);

   // Настройки для Grim режима
   private final BooleanSetting grimBoost = new BooleanSetting() {
      public boolean isVisible() {
         return Speed.this.mode.is("Grim");
      }
   }.name("AuraBoost").value(true);
   
   private final FloatSetting grimStrength = new FloatSetting() {
      public boolean isVisible() {
         return Speed.this.mode.is("Grim") && Speed.this.grimBoost.getValue();
      }
   }.name("Strength").value(1.5F).minValue(1.0F).maxValue(6.0F).incriment(0.1F);
   
   // Настройки для HolyWorld режима
   private final FloatSetting holyLowBoost = new FloatSetting() {
      public boolean isVisible() {
         return Speed.this.mode.is("HolyWorld");
      }
   }.name("LowBoost").value(0.02F).minValue(0.0F).maxValue(0.1F).incriment(0.001F);
   
   private final FloatSetting holyHighBoost = new FloatSetting() {
      public boolean isVisible() {
         return Speed.this.mode.is("HolyWorld");
      }
   }.name("HighBoost").value(0.036F).minValue(0.0F).maxValue(0.1F).incriment(0.001F);
   
   private final FloatSetting holyScanRadius = new FloatSetting() {
      public boolean isVisible() {
         return Speed.this.mode.is("HolyWorld");
      }
   }.name("ScanRadius").value(5.0F).minValue(1.0F).maxValue(10.0F).incriment(0.5F);
   
   // Внутренние переменные
   private final TimerUtil timerUtil = new TimerUtil();
   private final Map<Entity, Vec3d> previousPositions = new HashMap<>();

   public Speed() {
      super(new ModuleBranding("Speed", Category.MOVEMENT, "Ускоряет перемещение игрока"));
      this.addSetting(
              this.mode,
              this.autoJump,
              this.speed,
              this.matrixSpeed,
              this.collisionSpeed,
              this.holySpeed,
              this.collisionDist,
              this.grimBoost,
              this.grimStrength,
              this.holyLowBoost,
              this.holyHighBoost,
              this.holyScanRadius
      );
   }

   public void onTick(TickEvent event) {
      if (mc.player == null || mc.world == null || mc.player.getAbilities().flying) return;
      
      switch (this.mode.getValue()) {
         case "Vanilla":
            if (MovementUtil.isMove()) {
               MovementUtil.setStrafe(this.speed.getValue());
            }
            break;
            
         case "Matrix":
            if (MovementUtil.isMove()) {
                if (!mc.player.isGliding() && !mc.player.isTouchingWater() && !mc.player.isSubmergedIn(FluidTags.WATER) && !mc.player.isOnGround()) {
                    MovementUtil.setStrafe(this.matrixSpeed.getValue());
                }
                if (this.autoJump.getValue() && mc.player.isOnGround() && !mc.options.jumpKey.isPressed()) {
                    mc.player.jump();
                }
            }
            break;
            
         case "MetaHvH":
            if (MovementUtil.isMove()) {
                float currentSpeed = 0.358f;
                if (mc.player.hasStatusEffect(StatusEffects.SPEED)) {
                    int amp = mc.player.getStatusEffect(StatusEffects.SPEED).getAmplifier();
                    if (amp == 0) {
                        currentSpeed *= 1.2630f;
                    } else if (amp == 1) {
                        currentSpeed *= 1.4530f;
                    } else if (amp >= 2) {
                        currentSpeed *= 1.6520f;
                    }
                }
                
                if (!mc.player.isGliding() && !mc.player.isTouchingWater() && !mc.player.isSubmergedIn(FluidTags.WATER) && !mc.player.isOnGround()) {
                    MovementUtil.setStrafe(currentSpeed);
                }
                if (this.autoJump.getValue() && mc.player.isOnGround() && !mc.options.jumpKey.isPressed()) {
                    mc.player.jump();
                }
            }
            break;

         case "Collision":
            if (MovementUtil.isMove()) {
                boolean canBoost = mc.world.getEntitiesByClass(LivingEntity.class, mc.player.getBoundingBox().expand(0.1D), (e) -> e != mc.player).size() > 0;
                if (canBoost && !mc.player.isOnGround()) {
                    mc.player.airStruggle = this.collisionSpeed.getValue() / 10.0F;
                }
            }
            break;
            
         case "Timer":
            if (MovementUtil.isMove()) {
                if (mc.player.isOnGround()) {
                    mc.player.jump();
                } else {
                    float timerValue = mc.player.fallDistance <= 0.1f ? 1.34f : (mc.player.fallDistance > 1.0f ? 0.6f : 1.0f);
                    WildClient.INSTANCE.setTimerValue(timerValue);
                }
            } else {
                WildClient.INSTANCE.setTimerValue(1.0f);
            }
            break;
            
         case "Sunrise DMG":
            if (MovementUtil.isMove()) {
                double radians = MovementUtil.getDirection();
                double strength = mc.player.isOnGround() || mc.player.isTouchingWater() ? 9.5 / 24.5 : 0.11 / 24.5;
                mc.player.addVelocity(-Math.sin(radians) * strength, 0.0, Math.cos(radians) * strength);
                MovementUtil.setStrafe(MovementUtil.getDirection());
            }
            break;
            
         case "NCP":
            if (MovementUtil.isMove()) {
                if (!mc.player.isOnGround() && !mc.player.isTouchingWater()) {
                    MovementUtil.setStrafe(0.36);
                }
                if (mc.player.isOnGround()) {
                    mc.player.jump();
                }
            }
            break;
            
         case "Boat":
            for(Entity entity : mc.world.getEntities()) {
               if (entity instanceof BoatEntity boatEntity) {
                  if (mc.player.distanceTo(boatEntity) < 2.0F) {
                     Vec3d velocity = mc.player.getVelocity();
                     mc.player.setVelocity(velocity.x * 2.0D, velocity.y + (mc.player.isOnGround() ? 0.5D : 0.0D), velocity.z * 2.0D);
                  }
               }
            }
            break;
            
         case "Grim":
            if (MovementUtil.isMove()) {
               int collisions = 0;
               float box = 0.4F;
               
               // Усиление при наличии цели в Aura
               Aura aura = WildClient.INSTANCE.getModuleManager().getByClass(Aura.class);
               if (aura != null && aura.isEnabled() && aura.getTarget() != null && 
                   aura.getTarget().isAlive() && mc.player.isAlive() && this.grimBoost.getValue()) {
                  box = this.grimStrength.getValue();
               }
               
               // Подсчет столкновений с сущностями
               for(Entity ent : mc.world.getEntities()) {
                  if (ent != mc.player && !(ent instanceof ArmorStandEntity) && 
                      (ent instanceof LivingEntity || ent instanceof PassiveEntity) && 
                      mc.player.getBoundingBox().expand(box).intersects(ent.getBoundingBox())) {
                     collisions++;
                  }
               }
               
               if (collisions > 0) {
                  double[] motion = MovementUtil.calculateDirection(0.08 * collisions);
                  mc.player.addVelocity(motion[0], 0.0, motion[1]);
               }
            }
            break;
            
         case "HolyWorld":
            if (MovementUtil.isMove() && mc.player.isAlive()) {
               // Новая логика из файла: коллизии через expand и добавление скорости
               int collisions = 0;
               if (mc.world.getEntitiesByClass(LivingEntity.class, mc.player.getBoundingBox().expand(this.collisionDist.getValue()), (e) -> e != mc.player).size() > 0) {
                  collisions++;
               }
               
               if (collisions > 0) {
                  double[] motion = MovementUtil.calculateDirection(this.holySpeed.getValue() * 2.05D * 0.01D * collisions);
                  mc.player.addVelocity(motion[0], 0.0, motion[1]);
               } else {
                  // Старая логика как запасная или если нет прямых коллизий
                  double scanRadius = this.holyScanRadius.getValue();
                  for(Entity entity : mc.world.getEntities()) {
                     if (entity != mc.player && (entity instanceof LivingEntity || entity instanceof PassiveEntity) && 
                         !(entity instanceof ArmorStandEntity) && 
                         mc.player.getBoundingBox().expand(scanRadius).intersects(entity.getBoundingBox())) {
                        
                        double distanceX = Math.abs(mc.player.getX() - entity.getX());
                        double distanceZ = Math.abs(mc.player.getZ() - entity.getZ());
                        
                        if (distanceX <= 2.1 && distanceZ <= 1.3) {
                           double entitySpeed = getEntitySpeed(entity);
                           double boostAmount = entitySpeed < 5.0 ? this.holyLowBoost.getValue() : this.holyHighBoost.getValue();
                           
                           double[] motion2 = MovementUtil.calculateDirection(boostAmount);
                           mc.player.addVelocity(motion2[0], 0.0, motion2[1]);
                           break;
                        }
                     }
                  }
               }
            }
            break;
            
         case "45Degree":
            // 45Degree режим работает через пакеты, логика в onTick минимальна
            if (MovementUtil.isMove()) {
               MovementUtil.setStrafe(0.28F);
            }
            break;
      }
   }
   
   private double getEntitySpeed(Entity entity) {
      Vec3d currentPos = entity.getPos();
      Vec3d previousPos = this.previousPositions.getOrDefault(entity, currentPos);
      double dx = currentPos.x - previousPos.x;
      double dz = currentPos.z - previousPos.z;
      double speedValue = Math.sqrt(dx * dx + dz * dz) * 20.0;
      this.previousPositions.put(entity, currentPos);
      return speedValue;
   }

   public void onDisable() {
      WildClient.INSTANCE.setTimerValue(1.0f);
   }
}
