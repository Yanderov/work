package fun.Yanderov.utils.features.aura.rotations.impl;

import fun.Yanderov.Yanderov;
import fun.Yanderov.features.impl.combat.Aura;
import fun.Yanderov.utils.features.aura.rotations.constructor.RotateConstructor;
import fun.Yanderov.utils.features.aura.striking.StrikeManager;
import fun.Yanderov.utils.features.aura.utils.MathAngle;
import fun.Yanderov.utils.features.aura.warp.Turns;
import fun.Yanderov.utils.math.time.StopWatch;
import java.security.SecureRandom;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class MatrixAngle extends RotateConstructor {
   public MatrixAngle() {
      super("Matrix");
   }

   public Turns limitAngleChange(Turns currentAngle, Turns targetAngle, class_243 vec3d, class_1297 entity) {
      StrikeManager attackHandler = Yanderov.getInstance().getAttackPerpetrator().getAttackHandler();
      Aura aura = Aura.getInstance();
      StopWatch attackTimer = attackHandler.getAttackTimer();
      Turns angleDelta = MathAngle.calculateDelta(currentAngle, targetAngle);
      float yawDelta = angleDelta.getYaw();
      float pitchDelta = angleDelta.getPitch();
      float rotationDifference = (float)Math.hypot((double)Math.abs(yawDelta), (double)Math.abs(pitchDelta));
      boolean canAttack = entity != null && attackHandler.canAttack(aura.getConfig(), 0);
      float preAttackSpeed = 1.0F;
      float postAttackSpeed = this.randomLerp(0.0F, 0.5F);
      float speed = canAttack ? preAttackSpeed : postAttackSpeed;
      float lineYaw = Math.abs(yawDelta / rotationDifference) * (float)(canAttack ? 360 : 100);
      float linePitch = Math.abs(pitchDelta / rotationDifference) * 180.0F;
      float jitterYaw = canAttack ? 0.0F : (float)((double)this.randomLerp(0.0F, 6.0F) * Math.sin((double)((float)System.currentTimeMillis() / this.randomLerp(15.0F, 145.0F))));
      float jitterPitch = canAttack ? 0.0F : (float)((double)this.randomLerp(1.0F, 3.0F) * Math.sin((double)((float)System.currentTimeMillis() / this.randomLerp(15.0F, 145.0F))));
      if (!aura.isState() || entity == null) {
         speed = 0.7F;
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
      return class_3532.method_16439((new SecureRandom()).nextFloat(), min, max);
   }

   public class_243 randomValue() {
      return new class_243((double)0.0F, (double)0.0F, (double)0.0F);
   }
}

