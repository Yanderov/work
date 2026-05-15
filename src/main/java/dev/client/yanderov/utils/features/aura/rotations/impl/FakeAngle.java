package dev.client.yanderov.utils.features.aura.rotations.impl;

import dev.client.yanderov.utils.features.aura.point.Vector;
import dev.client.yanderov.utils.features.aura.rotations.constructor.RotateConstructor;
import dev.client.yanderov.utils.features.aura.utils.MathAngle;
import dev.client.yanderov.utils.features.aura.warp.Turns;
import dev.client.yanderov.utils.math.time.TimerUtil;
import java.security.SecureRandom;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class FakeAngle extends RotateConstructor {
   private int swingCount = 0;
   private boolean hasSwungTwice = false;
   private boolean hasSwung = false;
   private boolean disableRotation = false;
   TimerUtil timer = new TimerUtil();

   public FakeAngle() {
      super("FakeAngle to LonyGrief");
   }

   public Turns limitAngleChange(Turns currentAngle, Turns targetAngle, class_243 vec3d, class_1297 entity) {
      if (entity != null) {
         class_243 aimPoint = Vector.brain(entity, 0.0F, 5.0F);
         targetAngle = MathAngle.calculateAngle(aimPoint);
      }

      Turns angleDelta = MathAngle.calculateDelta(currentAngle, targetAngle);
      float yawDelta = angleDelta.getYaw();
      float pitchDelta = angleDelta.getPitch();
      float rotationDifference = (float)Math.hypot((double)yawDelta, (double)pitchDelta);
      float straightLineYaw = Math.abs(yawDelta / rotationDifference) * 360.0F;
      float straightLinePitch = Math.abs(pitchDelta / rotationDifference) * 360.0F;
      float jitterYaw = (float)((double)8.0F * Math.sin((double)System.currentTimeMillis() / (double)85.0F));
      float jitterPitch = (float)((double)8.0F * Math.sin((double)System.currentTimeMillis() / (double)95.0F));
      float newYaw = currentAngle.getYaw() + Math.min(Math.max(yawDelta, -straightLineYaw), straightLineYaw);
      float newPitch = currentAngle.getPitch() + Math.min(Math.max(pitchDelta, -straightLinePitch), straightLinePitch);
      return new Turns(newYaw, newPitch);
   }

   public class_243 randomValue() {
      return new class_243(0.05, 0.1, 0.02);
   }

   private float randomLerp(float min, float max) {
      return class_3532.method_16439((new SecureRandom()).nextFloat(), min, max);
   }
}

