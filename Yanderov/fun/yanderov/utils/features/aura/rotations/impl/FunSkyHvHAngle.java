package fun.Yanderov.utils.features.aura.rotations.impl;

import fun.Yanderov.utils.features.aura.rotations.constructor.RotateConstructor;
import fun.Yanderov.utils.features.aura.utils.MathAngle;
import fun.Yanderov.utils.features.aura.warp.Turns;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class FunSkyHvHAngle extends RotateConstructor {
   public FunSkyHvHAngle() {
      super("FunSky HVH");
   }

   public Turns limitAngleChange(Turns currentAngle, Turns targetAngle, class_243 vec3d, class_1297 entity) {
      if (currentAngle == null) {
         currentAngle = MathAngle.cameraAngle();
      }

      float spinSpeed = 25.0F;
      float newYaw = currentAngle.getYaw() + spinSpeed;
      newYaw = class_3532.method_15393(newYaw);
      float newPitch = currentAngle.getPitch();
      return new Turns(newYaw, newPitch);
   }

   public class_243 randomValue() {
      return new class_243(0.01, 0.01, 0.01);
   }
}

