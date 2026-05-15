package fun.Yanderov.utils.features.aura.rotations.impl;

import fun.Yanderov.Yanderov;
import fun.Yanderov.features.impl.combat.Aura;
import fun.Yanderov.utils.features.aura.point.Vector;
import fun.Yanderov.utils.features.aura.rotations.constructor.RotateConstructor;
import fun.Yanderov.utils.features.aura.striking.StrikeManager;
import fun.Yanderov.utils.features.aura.utils.MathAngle;
import fun.Yanderov.utils.features.aura.warp.Turns;
import fun.Yanderov.utils.math.time.StopWatch;
import java.security.SecureRandom;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class RWAngle extends RotateConstructor {
   public RWAngle() {
      super("ReallyWorld");
   }

   public Turns limitAngleChange(Turns currentAngle, Turns targetAngle, class_243 vec3d, class_1297 entity) {
      StrikeManager attackHandler = Yanderov.instance.getAttackPerpetrator().getAttackHandler();
      Aura aura = Aura.getInstance();
      if (entity != null) {
         class_243 aimPoint = Vector.brain(entity, 1.0F, 3.5F);
         targetAngle = MathAngle.calculateAngle(aimPoint);
      }

      int count = attackHandler.getCount();
      StopWatch attackTimer = attackHandler.getAttackTimer();
      Turns angleDelta = MathAngle.calculateDelta(currentAngle, targetAngle);
      float yawDelta = angleDelta.getYaw();
      float pitchDelta = angleDelta.getPitch();
      float rotationDifference = (float)Math.hypot((double)Math.abs(yawDelta), (double)Math.abs(pitchDelta));
      boolean canAttack = entity != null && attackHandler.canAttack(aura.getConfig(), 0);
      float preAttackSpeed = 1.0F;
      float postAttackSpeed = 1.0F;
      float speed = canAttack ? preAttackSpeed : postAttackSpeed;
      float lineYaw = Math.abs(yawDelta / rotationDifference) * 180.0F;
      float linePitch = Math.abs(pitchDelta / rotationDifference) * 180.0F;
      float jitterYaw = canAttack ? 0.0F : (float)((double)-6.0F * Math.cos((double)System.currentTimeMillis() / (double)90.0F));
      float jitterPitch = canAttack ? 0.0F : (float)((double)6.0F * Math.sin((double)System.currentTimeMillis() / (double)90.0F));
      if (!aura.isState() || entity == null) {
         speed = 1.0F;
         jitterYaw = 0.0F;
         jitterPitch = 0.0F;
      }

      float moveYaw = class_3532.method_15363(yawDelta, -lineYaw, lineYaw);
      float movePitch = class_3532.method_15363(pitchDelta, -linePitch, linePitch);
      Turns moveAngle = new Turns(currentAngle.getYaw(), currentAngle.getPitch());
      moveAngle.setYaw(class_3532.method_16439(this.randomLerp(speed, speed + 0.2F), currentAngle.getYaw(), currentAngle.getYaw() + moveYaw) + jitterYaw);
      moveAngle.setPitch(class_3532.method_16439(this.randomLerp(speed, speed + 0.2F), currentAngle.getPitch(), currentAngle.getPitch() + movePitch) + jitterPitch);
      if (count > 0 && count % 50 == 0 && !attackTimer.finished((double)200.0F)) {
         moveAngle.setPitch(class_3532.method_16439(0.55F, currentAngle.getPitch(), currentAngle.getPitch() - 90.0F) + jitterPitch);
      }

      return moveAngle;
   }

   private float randomLerp(float min, float max) {
      return class_3532.method_16439((new SecureRandom()).nextFloat(), min, max);
   }

   public class_243 randomValue() {
      return new class_243((double)0.0F, (double)0.0F, (double)0.0F);
   }
}

