package dev.client.yanderov.utils.features.aura.rotations.impl;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.features.impl.combat.Aura;
import dev.client.yanderov.utils.features.aura.rotations.constructor.RotateConstructor;
import dev.client.yanderov.utils.features.aura.striking.StrikeManager;
import dev.client.yanderov.utils.features.aura.utils.MathAngle;
import dev.client.yanderov.utils.features.aura.warp.Turns;
import dev.client.yanderov.utils.math.time.StopWatch;
import java.security.SecureRandom;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class LGAngle extends RotateConstructor {
   public LGAngle() {
      super("SlothAi");
   }

   public Turns limitAngleChange(Turns currentAngle, Turns targetAngle, class_243 vec3d, class_1297 entity) {
      StrikeManager attackHandler = Yanderov.instance.getAttackPerpetrator().getAttackHandler();
      Aura aura = Aura.getInstance();
      StopWatch attackTimer = attackHandler.getAttackTimer();
      Turns angleDelta = MathAngle.calculateDelta(currentAngle, targetAngle);
      float yawDelta = angleDelta.getYaw();
      float pitchDelta = angleDelta.getPitch();
      float rotationDifference = (float)Math.hypot((double)Math.abs(yawDelta), (double)Math.abs(pitchDelta));
      boolean canAttack = entity != null && attackHandler.canAttack(aura.getConfig(), 0);
      float distanceToTarget = 0.0F;
      if (entity != null) {
         distanceToTarget = mc.field_1724.method_5739(entity);
      }

      float baseSpeed = canAttack ? 0.93F : 0.56F;
      if (distanceToTarget > 0.0F && distanceToTarget < 0.66F) {
         float closeRangeSpeed = class_3532.method_15363(distanceToTarget / 1.5F * 0.35F, 0.1F, 0.6F);
         if (canAttack) {
            float var10000 = 0.85F;
         } else {
            Math.min(baseSpeed, closeRangeSpeed);
         }
      }

      float lineYaw = Math.abs(yawDelta / rotationDifference) * 180.0F;
      float linePitch = Math.abs(pitchDelta / rotationDifference) * 180.0F;
      float jitterYaw = canAttack ? 0.0F : (float)((double)this.randomLerp(20.0F, 26.0F) * Math.sin((double)System.currentTimeMillis() / (double)25.0F));
      float jitterPitch = canAttack ? 0.0F : (float)((double)this.randomLerp(8.0F, 23.0F) * Math.sin((double)System.currentTimeMillis() / (double)27.0F));
      if ((!aura.isState() || aura.getTarget() == null) && attackHandler.getAttackTimer().finished((double)1000.0F)) {
         baseSpeed = 0.35F;
         jitterYaw = 0.0F;
         jitterPitch = 0.0F;
      }

      float moveYaw = class_3532.method_15363(yawDelta, -lineYaw, lineYaw);
      float movePitch = class_3532.method_15363(pitchDelta, -linePitch, linePitch);
      Turns moveAngle = new Turns(currentAngle.getYaw(), currentAngle.getPitch());
      moveAngle.setYaw(class_3532.method_16439(baseSpeed, currentAngle.getYaw(), currentAngle.getYaw() + moveYaw) + jitterYaw);
      moveAngle.setPitch(class_3532.method_16439(baseSpeed, currentAngle.getPitch(), currentAngle.getPitch() + movePitch) + jitterPitch);
      return moveAngle;
   }

   public static float lerp(float delta, float start, float end) {
      return end;
   }

   private float randomLerp(float min, float max) {
      return class_3532.method_16439((new SecureRandom()).nextFloat(), min, max);
   }

   public class_243 randomValue() {
      return new class_243(0.01, 0.07, 0.02);
   }
}

