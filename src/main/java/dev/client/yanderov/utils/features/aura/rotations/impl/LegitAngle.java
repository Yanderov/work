package dev.client.yanderov.utils.features.aura.rotations.impl;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.features.impl.combat.Aura;
import dev.client.yanderov.utils.features.aura.point.Vector;
import dev.client.yanderov.utils.features.aura.rotations.constructor.RotateConstructor;
import dev.client.yanderov.utils.features.aura.striking.StrikeManager;
import dev.client.yanderov.utils.features.aura.utils.MathAngle;
import dev.client.yanderov.utils.features.aura.warp.Turns;
import dev.client.yanderov.utils.math.time.StopWatch;
import java.security.SecureRandom;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class LegitAngle extends RotateConstructor {
   private boolean isPullingBack = false;
   private long pullBackStartTime = 0L;
   private final SecureRandom random = new SecureRandom();

   public LegitAngle() {
      super("Matrix");
   }

   public Turns limitAngleChange(Turns currentAngle, Turns targetAngle, class_243 vec3d, class_1297 entity) {
      StrikeManager attackHandler = YanderovIntegration.getInstance().getAttackPerpetrator().getAttackHandler();
      Aura aura = Aura.getInstance();
      StopWatch attackTimer = attackHandler.getAttackTimer();
      if (entity != null) {
         class_243 aimPoint = Vector.hitbox(entity, 1.0F, 1.2F, 1.0F, 4.0F);
         targetAngle = MathAngle.calculateAngle(aimPoint);
      }

      Turns angleDelta = MathAngle.calculateDelta(currentAngle, targetAngle);
      float yawDelta = angleDelta.getYaw();
      float pitchDelta = angleDelta.getPitch();
      float rotationDifference = (float)Math.hypot((double)Math.abs(yawDelta), (double)Math.abs(pitchDelta));
      boolean canAttack = entity != null && attackHandler.canAttack(aura.getConfig(), 0);
      float preAttackSpeed = 0.75F;
      float speed = !attackTimer.finished((double)250.0F) ? 0.1F : 0.7F;
      if (!attackTimer.finished((double)350.0F)) {
         speed = 0.15F;
      }

      if (!attackTimer.finished((double)500.0F)) {
         speed = 0.25F;
      }

      float lineYaw = Math.abs(yawDelta / rotationDifference) * (float)(canAttack ? 360 : 100);
      float linePitch = Math.abs(pitchDelta / rotationDifference) * 180.0F;
      float jitterYaw = canAttack ? 0.0F : (float)((double)3.0F * Math.sin((double)System.currentTimeMillis() / (double)85.0F));
      float jitterPitch = canAttack ? 0.0F : (float)((double)2.0F * Math.cos((double)System.currentTimeMillis() / (double)85.0F));
      if (!aura.isState() || entity == null) {
         jitterYaw = 0.0F;
         jitterPitch = 0.0F;
      }

      float moveYaw = class_3532.method_15363(yawDelta, -lineYaw, lineYaw);
      float movePitch = class_3532.method_15363(pitchDelta, -linePitch, linePitch);
      Turns moveAngle = new Turns(currentAngle.getYaw(), currentAngle.getPitch());
      moveAngle.setYaw(class_3532.method_16439(this.randomLerp(speed, speed), currentAngle.getYaw(), currentAngle.getYaw() + moveYaw) + jitterYaw);
      moveAngle.setPitch(class_3532.method_16439(this.randomLerp(speed, speed), currentAngle.getPitch(), currentAngle.getPitch() + movePitch) + jitterPitch);
      return moveAngle;
   }

   private float randomLerp(float min, float max) {
      return class_3532.method_16439(this.random.nextFloat(), min, max);
   }

   public class_243 randomValue() {
      return new class_243((double)0.0F, (double)0.0F, (double)0.0F);
   }
}

