package fun.Yanderov.utils.features.aura.rotations.impl;

import fun.Yanderov.Yanderov;
import fun.Yanderov.features.impl.combat.Aura;
import fun.Yanderov.utils.features.aura.point.Vector;
import fun.Yanderov.utils.features.aura.rotations.constructor.RotateConstructor;
import fun.Yanderov.utils.features.aura.striking.StrikeManager;
import fun.Yanderov.utils.features.aura.utils.MathAngle;
import fun.Yanderov.utils.features.aura.warp.Turns;
import fun.Yanderov.utils.math.calc.Calculate;
import java.security.SecureRandom;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class HAngle extends RotateConstructor {
   private static long tickCounter = 0L;
   private float resetProgress = 0.0F;
   private final SecureRandom secureRandom = new SecureRandom();
   private boolean jitterApplied = false;

   public HAngle() {
      super("HvH");
   }

   public Turns limitAngleChange(Turns currentAngle, Turns targetAngle, class_243 vec3d, class_1297 entity) {
      Aura aura = Aura.getInstance();
      StrikeManager attackHandler = Yanderov.instance.getAttackPerpetrator().getAttackHandler();
      if (entity != null) {
         class_243 aimPoint = Vector.hitbox(entity, 1.0F, 1.3F, 1.0F, 6.0F);
         targetAngle = MathAngle.calculateAngle(aimPoint);
      }

      boolean canAttack = entity != null && attackHandler.canAttack(aura.getConfig(), 0);
      Turns angleDelta = MathAngle.calculateDelta(currentAngle, targetAngle);
      float yawDelta = angleDelta.getYaw();
      float pitchDelta = angleDelta.getPitch();
      float rotationDifference = (float)Math.hypot((double)Math.abs(yawDelta), (double)Math.abs(pitchDelta));
      float speed = 1.0F;
      float jitterYaw = canAttack ? 0.0F : (float)((double)5.0F * Math.sin((double)System.currentTimeMillis() / (double)45.0F));
      float jitterPitch = canAttack ? 0.0F : (float)((double)5.0F * Math.sin((double)System.currentTimeMillis() / (double)45.0F));
      float maxRotation = 360.0F;
      float lineYaw = Math.abs(yawDelta / rotationDifference) * maxRotation;
      float linePitch = Math.abs(pitchDelta / rotationDifference) * maxRotation;
      float moveYaw = class_3532.method_15363(yawDelta, -lineYaw, lineYaw);
      float movePitch = class_3532.method_15363(pitchDelta, -linePitch, linePitch);
      Turns moveAngle = new Turns(currentAngle.getYaw(), currentAngle.getPitch());
      moveAngle.setYaw(class_3532.method_16439(Calculate.getRandom(speed, speed + 0.2F), currentAngle.getYaw(), currentAngle.getYaw() + moveYaw) + jitterYaw);
      moveAngle.setPitch(class_3532.method_16439(Calculate.getRandom(speed, speed + 0.2F), currentAngle.getPitch(), currentAngle.getPitch() + movePitch) + jitterPitch);
      return new Turns(moveAngle.getYaw(), moveAngle.getPitch());
   }

   private float applyGaussianJitter(float rotation, float strength) {
      return rotation + (float)(this.secureRandom.nextGaussian() * (double)strength);
   }

   private float randomLerp(float min, float max) {
      return class_3532.method_16439((new SecureRandom()).nextFloat(), min, max);
   }

   public class_243 randomValue() {
      return new class_243((double)0.0F, (double)0.0F, (double)0.0F);
   }
}

