package dev.client.yanderov.utils.features.aura.rotations.impl;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.features.impl.combat.Aura;
import dev.client.yanderov.utils.features.aura.rotations.constructor.RotateConstructor;
import dev.client.yanderov.utils.features.aura.striking.StrikeManager;
import dev.client.yanderov.utils.features.aura.utils.MathAngle;
import dev.client.yanderov.utils.features.aura.warp.Turns;
import dev.client.yanderov.utils.math.time.StopWatch;
import dev.client.yanderov.utils.math.time.TimerUtil;
import java.security.SecureRandom;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class FTAngle extends RotateConstructor {
   private int swingCount = 0;
   private boolean hasSwungTwice = false;
   private boolean hasSwung = false;
   private boolean disableRotation = false;
   TimerUtil timer = new TimerUtil();

   public FTAngle() {
      super("FunTime");
   }

   public Turns limitAngleChange(Turns currentTurns, Turns targetTurns, class_243 vec3d, class_1297 entity) {
      StrikeManager attackHandler = Yanderov.instance.getAttackPerpetrator().getAttackHandler();
      StopWatch attackTimer = attackHandler.getAttackTimer();
      int count = attackHandler.getCount();
      Turns TurnsDelta = MathAngle.calculateDelta(currentTurns, targetTurns);
      float yawDelta = TurnsDelta.getYaw();
      float pitchDelta = TurnsDelta.getPitch();
      float rotationDifference = (float)Math.hypot((double)Math.abs(yawDelta), (double)Math.abs(pitchDelta));
      if (entity != null) {
         float speed = attackHandler.canAttack(Aura.getInstance().getConfig(), 0) ? 1.0F : ((new SecureRandom()).nextBoolean() ? 0.4F : 0.2F);
         float lineYaw = Math.abs(yawDelta / rotationDifference) * 180.0F;
         float linePitch = Math.abs(pitchDelta / rotationDifference) * 180.0F;
         float moveYaw = class_3532.method_15363(yawDelta, -lineYaw, lineYaw);
         float movePitch = class_3532.method_15363(pitchDelta, -linePitch, linePitch);
         Turns moveTurns = new Turns(currentTurns.getYaw(), currentTurns.getPitch());
         moveTurns.setYaw(class_3532.method_16439(this.randomLerp(speed, speed + 0.2F), currentTurns.getYaw(), currentTurns.getYaw() + moveYaw));
         moveTurns.setPitch(class_3532.method_16439(this.randomLerp(speed, speed + 0.2F), currentTurns.getPitch(), currentTurns.getPitch() + movePitch));
         return moveTurns;
      } else {
         int suck = count % 3;
         float speed = attackTimer.finished((double)430.0F) ? ((new SecureRandom()).nextBoolean() ? 0.4F : 0.2F) : -0.2F;
         float random = (float)attackTimer.elapsedTime() / 40.0F + (float)(count % 6);
         Turns var10000;
         switch (suck) {
            case 0 -> var10000 = new Turns((float)Math.cos((double)random), (float)Math.sin((double)random));
            case 1 -> var10000 = new Turns((float)Math.sin((double)random), (float)Math.cos((double)random));
            case 2 -> var10000 = new Turns((float)Math.sin((double)random), (float)(-Math.cos((double)random)));
            default -> var10000 = new Turns((float)(-Math.cos((double)random)), (float)Math.sin((double)random));
         }

         Turns randomTurns = var10000;
         float yaw = !attackTimer.finished((double)2000.0F) ? this.randomLerp(12.0F, 24.0F) * randomTurns.getYaw() : 0.0F;
         float pitch2 = this.randomLerp(0.0F, 2.0F) * (float)Math.cos((double)System.currentTimeMillis() / (double)5000.0F);
         float pitch = !attackTimer.finished((double)2000.0F) ? this.randomLerp(2.0F, 6.0F) * randomTurns.getPitch() + pitch2 : 0.0F;
         float lineYaw = Math.abs(yawDelta / rotationDifference) * 180.0F;
         float linePitch = Math.abs(pitchDelta / rotationDifference) * 180.0F;
         float moveYaw = class_3532.method_15363(yawDelta, -lineYaw, lineYaw);
         float movePitch = class_3532.method_15363(pitchDelta, -linePitch, linePitch);
         Turns moveTurns = new Turns(currentTurns.getYaw(), currentTurns.getPitch());
         moveTurns.setYaw(class_3532.method_16439(Math.clamp(this.randomLerp(speed, speed + 0.2F), 0.0F, 1.0F), currentTurns.getYaw(), currentTurns.getYaw() + moveYaw) + yaw);
         moveTurns.setPitch(class_3532.method_16439(Math.clamp(this.randomLerp(speed, speed + 0.2F), 0.0F, 1.0F), currentTurns.getPitch(), currentTurns.getPitch() + movePitch) + pitch);
         return moveTurns;
      }
   }

   public class_243 randomValue() {
      return new class_243((double)0.0F, (double)0.0F, (double)0.0F);
   }

   private float randomLerp(float min, float max) {
      return class_3532.method_16439((new SecureRandom()).nextFloat(), min, max);
   }
}

