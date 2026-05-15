package dev.client.yanderov.utils.features.aura.rotations.impl;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.features.impl.combat.Aura;
import dev.client.yanderov.utils.features.aura.point.Vector;
import dev.client.yanderov.utils.features.aura.rotations.constructor.RotateConstructor;
import dev.client.yanderov.utils.features.aura.striking.StrikeManager;
import dev.client.yanderov.utils.features.aura.utils.MathAngle;
import dev.client.yanderov.utils.features.aura.warp.Turns;
import java.security.SecureRandom;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class SnapAngle extends RotateConstructor {
   public SnapAngle() {
      super("Snap");
   }

   public Turns limitAngleChange(Turns currentAngle, Turns targetAngle, class_243 vec3d, class_1297 entity) {
      if (entity != null && Aura.getInstance().getAimMode().isSelected("Snap")) {
         class_243 aimPoint = Vector.hitbox(entity, 1.0F, 1.0F, 1.0F, 2.0F);
         targetAngle = MathAngle.calculateAngle(aimPoint);
      }

      StrikeManager attackHandler = Yanderov.instance.getAttackPerpetrator().getAttackHandler();
      Aura aura = Aura.getInstance();
      Turns angleDelta = MathAngle.calculateDelta(currentAngle, targetAngle);
      float yawDelta = angleDelta.getYaw();
      float pitchDelta = angleDelta.getPitch();
      float rotationDifference = (float)Math.hypot((double)Math.abs(yawDelta), (double)Math.abs(pitchDelta));
      if (entity != null && attackHandler.canAttack(aura.getConfig(), 0)) {
         boolean var20 = true;
      } else {
         boolean var10000 = false;
      }

      float speed = 1.0F;
      float maxRotation = 180.0F;
      float lineYaw = Math.abs(yawDelta / rotationDifference) * maxRotation;
      float linePitch = Math.abs(pitchDelta / rotationDifference) * maxRotation;
      float moveYaw = class_3532.method_15363(yawDelta, -lineYaw, lineYaw);
      float movePitch = class_3532.method_15363(pitchDelta, -linePitch, linePitch);
      Turns moveAngle = new Turns(currentAngle.getYaw(), currentAngle.getPitch());
      moveAngle.setYaw(class_3532.method_16439(speed, currentAngle.getYaw(), currentAngle.getYaw() + moveYaw));
      moveAngle.setPitch(class_3532.method_16439(speed, currentAngle.getPitch(), currentAngle.getPitch() + movePitch));
      return new Turns(moveAngle.getYaw(), moveAngle.getPitch());
   }

   private float randomLerp(float min, float max) {
      return class_3532.method_16439((new SecureRandom()).nextFloat(), min, max);
   }

   public class_243 randomValue() {
      return new class_243((double)0.0F, (double)0.0F, (double)0.0F);
   }
}

