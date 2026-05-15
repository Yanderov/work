package dev.client.yanderov.utils.features.aura.rotations.impl;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.features.impl.combat.Aura;
import dev.client.yanderov.utils.features.aura.rotations.constructor.RotateConstructor;
import dev.client.yanderov.utils.features.aura.striking.StrikeManager;
import dev.client.yanderov.utils.features.aura.utils.MathAngle;
import dev.client.yanderov.utils.features.aura.warp.Turns;
import java.security.SecureRandom;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;

public class AdvancedAngle extends RotateConstructor {
   private final SecureRandom rng = new SecureRandom();

   public AdvancedAngle() {
      super("Advanced");
   }

   public Turns limitAngleChange(Turns currentAngle, Turns targetAngle, class_243 vec3d, class_1297 entity) {
      Aura aura = Aura.getInstance();
      StrikeManager attackHandler = Yanderov.instance.getAttackPerpetrator().getAttackHandler();
      boolean canAttack = entity != null && attackHandler.canAttack(aura.getConfig(), 0);
      String profile = aura.getAdvancedProfile().getSelected();
      return "Lite".equals(profile) ? this.limitAngleChangeLite(currentAngle, targetAngle, entity, aura) : this.limitAngleChangeHard(currentAngle, targetAngle, entity, canAttack, aura);
   }

   private Turns limitAngleChangeHard(Turns currentAngle, Turns targetAngle, class_1297 entity, boolean canAttack, Aura aura) {
      String rotType = aura.getAdvancedRotationType().getSelected();
      float bodyBias = 0.0F;
      String body = aura.getGaussianBodyPart().getSelected();
      if ("Head".equals(body)) {
         bodyBias = -2.5F;
      } else if ("Legs".equals(body)) {
         bodyBias = 3.0F;
      }

      float horizRndPct = aura.getHorizontalRandomize().getValue();
      float vertRndPct = aura.getVerticalRandomize().getValue();
      float rndYaw = (this.rng.nextFloat() * 2.0F - 1.0F) * horizRndPct;
      float rndPitch = (this.rng.nextFloat() * 2.0F - 1.0F) * vertRndPct;
      float gYaw = 0.0F;
      float gPitch = 0.0F;
      float freq = aura.getGaussianFrequency().getValue();
      if (this.rng.nextFloat() * 100.0F < freq) {
         float gSpeed = aura.getGaussianSpeed().getValue();
         gYaw = (float)(this.rng.nextGaussian() * (double)gSpeed);
         gPitch = (float)(this.rng.nextGaussian() * (double)gSpeed);
      }

      float yawRandomVal = aura.getYawRandom().getValue();
      float pitchRandomVal = aura.getPitchRandom().getValue();
      float jitterFreq = aura.getRandomJitterFrequency().getValue();
      boolean snapOnAttack = aura.getSnapOnAttack().isValue();
      float extraYawJitter = 0.0F;
      float extraPitchJitter = 0.0F;
      if ((!snapOnAttack || !canAttack) && (yawRandomVal > 0.0F || pitchRandomVal > 0.0F) && this.rng.nextFloat() * 100.0F < jitterFreq) {
         extraYawJitter = (this.rng.nextFloat() * 2.0F - 1.0F) * yawRandomVal;
         extraPitchJitter = (this.rng.nextFloat() * 2.0F - 1.0F) * pitchRandomVal;
      }

      Turns baseDelta = MathAngle.calculateDelta(currentAngle, targetAngle);
      float yawDelta = baseDelta.getYaw();
      float pitchDelta = baseDelta.getPitch();
      yawDelta += rndYaw + gYaw + extraYawJitter;
      pitchDelta += rndPitch + gPitch + bodyBias + extraPitchJitter;
      float yawSpeed = aura.getHorizontalSpeed().getValue();
      float pitchSpeed = aura.getVerticalSpeed().getValue();
      float yawAccel = aura.getHorizontalAcceleration().getValue();
      float pitchAccel = aura.getVerticalAcceleration().getValue();
      float mag = (float)Math.hypot((double)yawDelta, (double)pitchDelta);
      float accelFactor = class_3532.method_15363(mag / 180.0F, 0.0F, 1.0F);
      float stepYaw = yawSpeed + yawAccel * accelFactor;
      float stepPitch = pitchSpeed + pitchAccel * accelFactor;
      if (aura.getClampSteps().isValue()) {
         stepYaw = Math.min(stepYaw, aura.getMaxYawStep().getValue());
         stepPitch = Math.min(stepPitch, aura.getMaxPitchStep().getValue());
      }

      if ("Snap".equals(rotType)) {
         float speedFactor = canAttack ? this.randomLerp(0.86F, 0.96F) : this.randomLerp(0.1F, 0.4F);
         stepYaw *= speedFactor;
         stepPitch *= speedFactor;
      }

      float moveYaw = class_3532.method_15363(yawDelta, -stepYaw, stepYaw);
      float movePitch = class_3532.method_15363(pitchDelta, -stepPitch, stepPitch);
      Turns out = new Turns(currentAngle.getYaw(), currentAngle.getPitch());
      out.setYaw(currentAngle.getYaw() + moveYaw);
      out.setPitch(currentAngle.getPitch() + movePitch);
      return out;
   }

   private Turns limitAngleChangeLite(Turns currentAngle, Turns targetAngle, class_1297 entity, Aura aura) {
      if (entity instanceof class_1309 living) {
         class_310 mc = class_310.method_1551();
         if (mc.field_1724 == null) {
            return targetAngle;
         } else {
            class_243 eyePos = mc.field_1724.method_33571();
            class_243 hitboxCenter = new class_243(living.method_23317(), living.method_23318() + (double)(living.method_17682() / 2.0F), living.method_23321());
            class_243 vec = hitboxCenter.method_1020(eyePos);
            float yaw = (float)Math.toDegrees(Math.atan2(-vec.field_1352, vec.field_1350));
            float pitch = (float)(-Math.toDegrees(Math.atan2(vec.field_1351, Math.hypot(vec.field_1352, vec.field_1350))));
            float curYaw = currentAngle.getYaw();
            float curPitch = currentAngle.getPitch();
            float yawDelta = class_3532.method_15393(yaw - curYaw);
            float pitchDelta = pitch - curPitch;
            float smoothH = aura.getLiteSmoothnessH().getValue();
            float smoothV = aura.getLiteSmoothnessV().getValue();
            float initialAimSpeed = aura.getLiteInitialAimSpeed().getValue();
            float targetTrackSpeed = aura.getLiteTargetTrackSpeed().getValue();
            float shakeIntYaw = aura.getLiteShakeIntensityYaw().getValue();
            float shakeIntPitch = aura.getLiteShakeIntensityPitch().getValue();
            float shakeSpeedYaw = aura.getLiteShakeSpeedYaw().getValue();
            float shakeSpeedPitch = aura.getLiteShakeSpeedPitch().getValue();
            boolean takePlayerPitch = aura.getLiteTakePlayerPitch().isValue();
            if (initialAimSpeed >= 350.0F) {
               smoothH = Math.max(smoothH, 0.1F);
               smoothV = Math.max(smoothV, 0.1F);
               initialAimSpeed = 720.0F;
               targetTrackSpeed = 720.0F;
            }

            float fracYaw = class_3532.method_15363(Math.abs(yawDelta) / 180.0F, 0.0F, 1.0F);
            float speedYaw = class_3532.method_16439(fracYaw, initialAimSpeed, targetTrackSpeed);
            float fracPitch = class_3532.method_15363(Math.abs(pitchDelta) / 90.0F, 0.0F, 1.0F);
            float speedPitch = class_3532.method_16439(fracPitch, initialAimSpeed, targetTrackSpeed);
            float maxStepYaw = speedYaw / 20.0F;
            float maxStepPitch = speedPitch / 20.0F;
            float yawStep = yawDelta / Math.max(smoothH, 0.01F);
            float pitchStep = pitchDelta / Math.max(smoothV, 0.01F);
            yawStep = class_3532.method_15363(yawStep, -maxStepYaw, maxStepYaw);
            pitchStep = class_3532.method_15363(pitchStep, -maxStepPitch, maxStepPitch);
            float noiseYaw = this.generateLiteNoise(aura.getLiteNoiseMode().getSelected(), "yaw") * shakeIntYaw * 0.05F;
            float noisePitch = this.generateLiteNoise(aura.getLiteNoiseMode().getSelected(), "pitch") * shakeIntPitch * 0.05F;
            float time = (float)(System.currentTimeMillis() % 10000L) / 1000.0F;
            float waveYaw = (float)(Math.sin((double)(time * shakeSpeedYaw)) * (double)0.5F);
            float wavePitch = (float)(Math.cos((double)(time * shakeSpeedPitch)) * (double)0.5F);
            noiseYaw += waveYaw * 0.3F;
            noisePitch += wavePitch * 0.3F;
            float outYaw = curYaw + yawStep + noiseYaw;
            float outPitch = curPitch + pitchStep + noisePitch;
            if (takePlayerPitch && mc.field_1724 != null) {
               outPitch = mc.field_1724.method_36455();
            }

            outPitch = class_3532.method_15363(outPitch, -90.0F, 90.0F);
            Turns out = new Turns(currentAngle.getYaw(), currentAngle.getPitch());
            out.setYaw(outYaw);
            out.setPitch(outPitch);
            return out;
         }
      } else {
         return targetAngle;
      }
   }

   public class_243 randomValue() {
      return new class_243((double)0.0F, (double)0.0F, (double)0.0F);
   }

   private float randomLerp(float min, float max) {
      return class_3532.method_16439(this.rng.nextFloat(), min, max);
   }

   private float generateLiteNoise(String mode, String axis) {
      switch (mode) {
         case "Ð Ð°Ð²Ð½Ð¾Ð¼ÐµÑ€Ð½Ñ‹Ð¹":
            return (this.rng.nextFloat() - 0.5F) * 2.0F;
         case "Ð“Ð°ÑƒÑÑÐ¾Ð²ÑÐºÐ¸Ð¹":
            return (float)this.rng.nextGaussian();
         case "ÐŸÐµÑ€Ð»Ð¸Ð½":
            return (float)(Math.sin((double)System.nanoTime() / (double)1.0E7F + (double)("yaw".equals(axis) ? 0 : 1000)) * (double)0.5F);
         case "Ð¡Ð»ÑƒÑ‡Ð°Ð¹Ð½Ñ‹Ð¹ ÑˆÐ°Ð³":
            return (float)(this.rng.nextBoolean() ? 1 : -1) * this.rng.nextFloat();
         case "Ð¡Ð»ÑƒÑ‡Ð°Ð¹Ð½Ñ‹Ð¹ Ð²Ñ‹Ð±Ð¾Ñ€":
            float[] options = new float[]{-1.0F, -0.5F, 0.0F, 0.5F, 1.0F};
            return options[this.rng.nextInt(options.length)];
         default:
            return 0.0F;
      }
   }
}

