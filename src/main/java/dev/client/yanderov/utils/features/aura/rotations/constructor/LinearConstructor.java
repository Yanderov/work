package dev.client.yanderov.utils.features.aura.rotations.constructor;

import dev.client.yanderov.utils.features.aura.utils.MathAngle;
import dev.client.yanderov.utils.features.aura.warp.Turns;
import net.minecraft.class_1297;
import net.minecraft.class_243;

public class LinearConstructor extends RotateConstructor {
   public LinearConstructor() {
      super("Linear");
   }

   public Turns limitAngleChange(Turns currentAngle, Turns targetAngle, class_243 vec3d, class_1297 entity) {
      Turns angleDelta = MathAngle.calculateDelta(currentAngle, targetAngle);
      float yawDelta = angleDelta.getYaw();
      float pitchDelta = angleDelta.getPitch();
      float rotationDifference = (float)Math.hypot((double)yawDelta, (double)pitchDelta);
      float straightLineYaw = Math.abs(yawDelta / rotationDifference) * 360.0F;
      float straightLinePitch = Math.abs(pitchDelta / rotationDifference) * 360.0F;
      float newYaw = currentAngle.getYaw() + Math.min(Math.max(yawDelta, -straightLineYaw), straightLineYaw);
      float newPitch = currentAngle.getPitch() + Math.min(Math.max(pitchDelta, -straightLinePitch), straightLinePitch);
      return new Turns(newYaw, newPitch);
   }

   public class_243 randomValue() {
      return new class_243((double)0.0F, (double)0.0F, (double)0.0F);
   }
}

