package dev.client.modules.impl.movement;

import dev.client.WildClient;
import dev.client.event.classes.TickEvent;
import dev.client.event.interfaces.ITickable;
import dev.client.modules.Category;
import dev.client.modules.IDisableable;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import dev.client.modules.settings.impl.FloatSetting;
import dev.client.modules.settings.impl.ModeSetting;
import dev.client.util.IUtil;
import dev.client.util.math.TimerUtil;
import dev.client.util.player.MovementUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.Vec3d;

@Environment(EnvType.CLIENT)
public class Speed extends Module implements ITickable, IDisableable, IUtil {
   private final ModeSetting mode = new ModeSetting().name("Mode").value("Vanilla").modes("Vanilla", "Matrix", "Timer", "Sunrise DMG", "NCP", "Boat");
   public FloatSetting speed = new FloatSetting().name("Speed").minValue(0.1F).value(0.5F).maxValue(2.0F).incriment(0.01F);
   
   private final TimerUtil timerUtil = new TimerUtil();

   public Speed() {
      super(new ModuleBranding("Speed", Category.MOVEMENT, "Ускоряет перемещение игрока"));
      this.addSetting(this.mode, this.speed);
   }

   public void onTick(TickEvent event) {
      if (mc.player == null) return;
      
      switch (this.mode.getValue()) {
         case "Vanilla":
            if (MovementUtil.isMove()) {
               MovementUtil.setStrafe(this.speed.getValue());
            }
            break;
         case "Matrix":
            if (MovementUtil.isMove() && mc.player.isOnGround()) {
               mc.player.jump();
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
            if (mc.world != null) {
               for(Entity entity : mc.world.getEntities()) {
                  if (entity instanceof BoatEntity boatEntity) {
                     if (mc.player.distanceTo(boatEntity) < 2.0F) {
                        Vec3d velocity = mc.player.getVelocity();
                        mc.player.setVelocity(velocity.x * 2.0D, velocity.y + (mc.player.isOnGround() ? 0.5D : 0.0D), velocity.z * 2.0D);
                     }
                  }
               }
            }
            break;
      }
   }

   public void onDisable() {
      WildClient.INSTANCE.setTimerValue(1.0f);
   }
}
