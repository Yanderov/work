package dev.client.yanderov.utils.features.aura.rotations.impl;

import dev.client.yanderov.utils.features.aura.rotations.constructor.RotateConstructor;
import dev.client.yanderov.utils.features.aura.warp.Turns;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class SPAngle extends RotateConstructor {
   private float attackTick = 0.0F;

   public SPAngle() {
      super("SpookyTime");
   }

   public Turns limitAngleChange(Turns currentAngle, Turns targetAngle, class_243 vec3d, class_1297 entity) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (entity instanceof class_1309) {
            class_1309 living = (class_1309)entity;
            class_243 eyePos = mc.field_1724.method_33571();
            class_243 targetPos = living.method_19538();
            long time = System.currentTimeMillis();
            float timeVar = (float)Math.cos((double)time / (double)900.0F);
            float addyVact = 0.15F * timeVar;
            float timeVarZ = (float)Math.cos((double)time / (double)1100.0F);
            float addyVacZ = 0.15F * timeVarZ;
            float timeVarX = (float)Math.cos((double)time / (double)800.0F);
            float addyVacX = 0.25F * timeVarX;
            class_243 aimPoint = targetPos.method_1031((double)addyVacZ, class_3532.method_15350(eyePos.field_1351 - targetPos.field_1351, (double)0.0F, (double)(1.0F + addyVact)), (double)addyVacX);
            class_243 dir = aimPoint.method_1020(eyePos);
            if (dir.method_1027() < 1.0E-6) {
               return targetAngle;
            } else {
               class_243 directionVec = dir.method_1029();
               float baseYaw = (float)Math.toDegrees(Math.atan2(-directionVec.field_1352, directionVec.field_1350));
               float basePitch = (float)(-Math.toDegrees(Math.atan2(directionVec.field_1351, Math.hypot(directionVec.field_1352, directionVec.field_1350))));
               basePitch = class_3532.method_15363(basePitch, -89.0F, 89.0F);
               float waveA = (float)Math.cos((double)time / (double)70.0F);
               float waveB = (float)Math.sin((double)time / (double)80.0F);
               float yawJitter = (float)(Math.random() * (double)2.0F) * waveB + waveA * (float)(Math.random() * (double)3.0F);
               float pitchJitter = (float)(Math.random() * (double)1.0F) * waveA + waveB * this.smoothRandom(1.0F, 3.0F, 1.0F);
               float randomAttackShift = 0.0F;
               if (this.attackTick > 0.0F) {
                  randomAttackShift = ThreadLocalRandom.current().nextFloat(-3.0F, 3.0F);
                  --this.attackTick;
               }

               float newYaw = baseYaw + yawJitter + randomAttackShift;
               float newPitch = basePitch + pitchJitter + randomAttackShift;
               Turns result = new Turns(newYaw, newPitch);
               return result.adjustSensitivity();
            }
         } else {
            return targetAngle;
         }
      } else {
         return targetAngle;
      }
   }

   private float smoothRandom(float min, float max, float scale) {
      float r = (float)Math.sin((double)System.currentTimeMillis() / (double)250.0F * (double)scale);
      float n = (r + 1.0F) * 0.5F;
      return min + (max - min) * n;
   }

   public class_243 randomValue() {
      return new class_243(0.1, 0.1, 0.1);
   }
}

