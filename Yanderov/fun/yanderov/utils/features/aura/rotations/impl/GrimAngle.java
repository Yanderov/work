package fun.Yanderov.utils.features.aura.rotations.impl;

import fun.Yanderov.utils.features.aura.rotations.constructor.RotateConstructor;
import fun.Yanderov.utils.features.aura.warp.Turns;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class GrimAngle extends RotateConstructor {
   public GrimAngle() {
      super("Grim");
   }

   public Turns limitAngleChange(Turns currentTurns, Turns targetTurns, class_243 vec3d, class_1297 entity) {
      float baseYaw = currentTurns.getYaw();
      float basePitch = currentTurns.getPitch();
      float yaw = targetTurns.getYaw();
      float pitch = class_3532.method_15363(targetTurns.getPitch(), -89.0F, 89.0F);
      float shakeIntensity = 0.08F;
      float shakeFrequency = 0.05F;
      if (mc.field_1724 != null && mc.field_1724.field_6012 % Math.max(1, (int)(shakeFrequency * 20.0F)) == 0) {
         yaw += (float)(ThreadLocalRandom.current().nextDouble() - (double)0.5F) * shakeIntensity;
         pitch += (float)(ThreadLocalRandom.current().nextDouble() - (double)0.5F) * shakeIntensity;
      }

      if (mc.field_1724 != null) {
         float time = (float)mc.field_1724.field_6012 * 0.6F;
         float circleAmplitude = 4.2F;
         yaw += (float)Math.sin((double)time) * circleAmplitude;
         pitch += (float)Math.cos((double)time) * circleAmplitude;
      }

      yaw += (float)(ThreadLocalRandom.current().nextDouble() - (double)0.5F) * 0.02F;
      pitch += (float)(ThreadLocalRandom.current().nextDouble() - (double)0.5F) * 0.02F;
      float gcd = 6.6666665F;
      float gcdRandomizer = (float)(ThreadLocalRandom.current().nextDouble() * (double)0.005F + (double)0.9975F);
      yaw -= (yaw - baseYaw) % (gcd * gcdRandomizer);
      pitch -= (pitch - basePitch) % (gcd * gcdRandomizer);
      float maxYawChange = 90.0F;
      float maxPitchChange = 90.0F;
      yaw = baseYaw + class_3532.method_15363(yaw - baseYaw, -maxYawChange, maxYawChange);
      pitch = basePitch + class_3532.method_15363(pitch - basePitch, -maxPitchChange, maxPitchChange);
      return new Turns(yaw, pitch);
   }

   public class_243 randomValue() {
      return class_243.field_1353;
   }
}

