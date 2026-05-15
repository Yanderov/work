package dev.client.yanderov.utils.features.aura.rotations.impl;

import dev.client.yanderov.utils.features.aura.rotations.constructor.RotateConstructor;
import dev.client.yanderov.utils.features.aura.warp.Turns;
import dev.client.yanderov.utils.math.SensUtils;
import net.minecraft.class_1297;
import net.minecraft.class_238;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class PolarNewAngle extends RotateConstructor {
   private float lastYaw;
   private float lastPitch;
   private class_241 rotateVector = new class_241(0.0F, 0.0F);

   public PolarNewAngle() {
      super("PolarNew");
   }

   public Turns limitAngleChange(Turns currentTurns, Turns targetTurns, class_243 vec3d, class_1297 entity) {
      if (mc.field_1724 != null && entity != null) {
         this.rotateVector = new class_241(currentTurns.getYaw(), currentTurns.getPitch());
         class_243 eyes = mc.field_1724.method_33571();
         class_238 targetBox = entity.method_5829();
         double width = targetBox.field_1320 - targetBox.field_1323;
         double height = targetBox.field_1325 - targetBox.field_1322;
         double depth = targetBox.field_1324 - targetBox.field_1321;
         class_243 targetPos;
         if (mc.field_1724.field_6012 % 8 == 0) {
            double randX = targetBox.field_1323 + Math.random() * width;
            double randY = targetBox.field_1322 + Math.random() * height;
            double randZ = targetBox.field_1321 + Math.random() * depth;
            targetPos = new class_243(randX, randY, randZ);
         } else {
            targetPos = targetBox.method_1005();
         }

         class_243 diff = targetPos.method_1020(eyes);
         double distXZ = Math.sqrt(diff.field_1352 * diff.field_1352 + diff.field_1350 * diff.field_1350);
         float targetYaw = (float)class_3532.method_15338(Math.toDegrees(Math.atan2(diff.field_1350, diff.field_1352)) - (double)90.0F);
         float targetPitch = (float)(-Math.toDegrees(Math.atan2(diff.field_1351, distXZ)));
         float yawDelta = class_3532.method_15393(targetYaw - this.rotateVector.field_1343);
         float pitchDelta = class_3532.method_15393(targetPitch - this.rotateVector.field_1342);
         float maxYawDelta = 60.0F + (float)Math.random() * 1.0329834F;
         yawDelta = Math.min(Math.abs(yawDelta), maxYawDelta) * Math.signum(yawDelta);
         float maxPitchDelta = 23.133F + (float)Math.random() * 3.344F;
         pitchDelta = Math.min(Math.abs(pitchDelta), maxPitchDelta) * Math.signum(pitchDelta);
         if (Math.abs(yawDelta) == 0.0F && Math.abs(pitchDelta) > 0.0F) {
            yawDelta += (0.1F + (float)Math.random() * 0.4F) * 1.0313F;
         }

         if (Math.abs(pitchDelta) == 0.0F && Math.abs(yawDelta) > 0.0F) {
            pitchDelta += (0.1F + (float)Math.random() * 0.4F) * 1.0313F;
         }

         float baseSpeed = 0.65F + (float)Math.random() * 0.1F;
         float speedVariation = 0.9F + (float)Math.random() * 0.2F;
         float finalSpeed = baseSpeed * speedVariation;
         float yawSpeed = finalSpeed * (0.95F + (float)Math.random() * 0.1F);
         float pitchSpeed = finalSpeed * (0.95F + (float)Math.random() * 0.1F);
         float moveYaw = yawDelta * yawSpeed;
         float movePitch = pitchDelta * pitchSpeed;
         float lerpFactor = 0.7F + (float)Math.random() * 0.2F;
         float finalYaw = class_3532.method_16439(lerpFactor, this.rotateVector.field_1343, this.rotateVector.field_1343 + moveYaw);
         float finalPitch = class_3532.method_16439(lerpFactor, this.rotateVector.field_1342, this.rotateVector.field_1342 + movePitch);
         finalPitch = class_3532.method_15363(finalPitch, -89.0F, 89.0F);
         finalYaw = class_3532.method_15393(finalYaw);
         if (Float.isNaN(finalYaw) || Float.isInfinite(finalYaw)) {
            finalYaw = this.rotateVector.field_1343;
         }

         if (Float.isNaN(finalPitch) || Float.isInfinite(finalPitch)) {
            finalPitch = this.rotateVector.field_1342;
         }

         float gcd = SensUtils.getGCDValue();
         if (gcd > 0.0F && gcd < 10.0F && !Float.isInfinite(gcd) && !Float.isNaN(gcd)) {
            finalYaw -= (finalYaw - this.rotateVector.field_1343) % gcd;
            finalPitch -= (finalPitch - this.rotateVector.field_1342) % gcd;
         }

         this.lastYaw = finalYaw;
         this.lastPitch = finalPitch;
         this.rotateVector = new class_241(finalYaw, finalPitch);
         return new Turns(finalYaw, finalPitch);
      } else {
         return targetTurns;
      }
   }

   public class_243 randomValue() {
      return class_243.field_1353;
   }
}

