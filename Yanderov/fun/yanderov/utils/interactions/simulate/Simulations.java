package fun.Yanderov.utils.interactions.simulate;

import fun.Yanderov.features.impl.combat.Aura;
import fun.Yanderov.features.impl.misc.SelfDestruct;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.features.aura.warp.TurnsConnection;
import java.util.Objects;
import net.minecraft.class_10185;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_746;

public final class Simulations implements QuickImports {
   public static boolean hasPlayerMovement() {
      return mc.field_1724.field_3913.field_3905 != 0.0F || mc.field_1724.field_3913.field_3907 != 0.0F;
   }

   public static double[] calculateDirection(double distance) {
      return calculateDirection(mc.field_1724.field_3913.field_3905, mc.field_1724.field_3913.field_3907, distance);
   }

   public static final boolean moveKeyPressed(int keyNumber) {
      boolean w = mc.field_1690.field_1894.method_1434();
      boolean a = mc.field_1690.field_1913.method_1434();
      boolean s = mc.field_1690.field_1881.method_1434();
      boolean d = mc.field_1690.field_1849.method_1434();
      return keyNumber == 0 ? w : (keyNumber == 1 ? a : (keyNumber == 2 ? s : keyNumber == 3 && d));
   }

   public static final boolean w() {
      return moveKeyPressed(0);
   }

   public static final boolean a() {
      return moveKeyPressed(1);
   }

   public static final boolean s() {
      return moveKeyPressed(2);
   }

   public static final boolean d() {
      return moveKeyPressed(3);
   }

   public static final float moveYaw(float entityYaw) {
      return entityYaw + (float)(!a() || !d() || w() && s() || !w() && !s() ? (!w() || !s() || a() && d() || !a() && !d() ? ((!a() || !d() || w() && s()) && (!w() || !s() || a() && d()) ? (!a() && !d() && !s() ? 0 : (w() && !s() ? 45 : (s() && !w() ? (!a() && !d() ? 180 : 135) : (!w() && !s() || w() && s() ? 90 : 0))) * (a() ? -1 : 1)) : 0) : (a() ? -90 : (d() ? 90 : 0))) : (w() ? 0 : (s() ? 180 : 0)));
   }

   public static double[] forward(double d) {
      float f = mc.field_1724.field_3913.field_3905;
      float f2 = mc.field_1724.field_3913.field_3907;
      float f3 = TurnsConnection.INSTANCE.getRotation().getYaw();
      if (f != 0.0F) {
         if (f2 > 0.0F) {
            f3 += (float)(f > 0.0F ? -45 : 45);
         } else if (f2 < 0.0F) {
            f3 += (float)(f > 0.0F ? 45 : -45);
         }

         f2 = 0.0F;
         if (f > 0.0F) {
            f = 1.0F;
         } else if (f < 0.0F) {
            f = -1.0F;
         }
      }

      double d2 = Math.sin(Math.toRadians((double)(f3 + 90.0F)));
      double d3 = Math.cos(Math.toRadians((double)(f3 + 90.0F)));
      double d4 = (double)f * d * d3 + (double)f2 * d * d2;
      double d5 = (double)f * d * d2 - (double)f2 * d * d3;
      return new double[]{d4, d5};
   }

   public static float calculateBodyYaw(float yaw, float prevBodyYaw, double prevX, double prevZ, double currentX, double currentZ, float handSwingProgress) {
      if (Aura.fakeRotate && Aura.getInstance().getTarget() != null) {
         yaw = TurnsConnection.INSTANCE.getFakeAngle().getYaw();
      } else {
         yaw = TurnsConnection.INSTANCE.getRotation().getYaw();
      }

      double motionX = currentX - prevX;
      double motionZ = currentZ - prevZ;
      float motionSquared = (float)(motionX * motionX + motionZ * motionZ);
      float bodyYaw = prevBodyYaw;
      float swing = mc.field_1724.field_6251;
      if (motionSquared > 0.0025000002F) {
         float movementYaw = (float)class_3532.method_15349(motionZ, motionX) * (180F / (float)Math.PI) - 90.0F;
         float yawDiff = class_3532.method_15379(class_3532.method_15393(yaw) - movementYaw);
         if (95.0F < yawDiff && yawDiff < 265.0F) {
            bodyYaw = movementYaw - 180.0F;
         } else {
            bodyYaw = movementYaw;
         }
      }

      if (mc.field_1724 != null && mc.field_1724.field_6251 - 0.2F > 0.0F && !Aura.getInstance().getAimMode().isSelected("LonyGrief")) {
         bodyYaw = yaw;
      }

      float deltaYaw = class_3532.method_15393(bodyYaw - prevBodyYaw);
      bodyYaw = prevBodyYaw + deltaYaw * 0.3F;
      float yawOffsetDiff = class_3532.method_15393(yaw - bodyYaw);
      float maxHeadRotation = 52.0F;
      if (Math.abs(yawOffsetDiff) > maxHeadRotation) {
         bodyYaw += yawOffsetDiff - (float)class_3532.method_17822((double)yawOffsetDiff) * maxHeadRotation;
      }

      return bodyYaw;
   }

   public static double kizdamati() {
      return (double)1488.0F;
   }

   public static String fpsADDS() {
      return SelfDestruct.unhooked ? "fabric" : "lunarclient:v2.21.5-2540";
   }

   public static double[] calculateDirection(float forward, float sideways, double distance) {
      float yaw = TurnsConnection.INSTANCE.getRotation().getYaw();
      if (forward != 0.0F) {
         if (sideways > 0.0F) {
            yaw += forward > 0.0F ? -45.0F : 45.0F;
         } else if (sideways < 0.0F) {
            yaw += forward > 0.0F ? 45.0F : -45.0F;
         }

         sideways = 0.0F;
         forward = forward > 0.0F ? 1.0F : -1.0F;
      }

      double sinYaw = Math.sin(Math.toRadians((double)(yaw + 90.0F)));
      double cosYaw = Math.cos(Math.toRadians((double)(yaw + 90.0F)));
      double xMovement = (double)forward * distance * cosYaw + (double)sideways * distance * sinYaw;
      double zMovement = (double)forward * distance * sinYaw - (double)sideways * distance * cosYaw;
      return new double[]{xMovement, zMovement};
   }

   public static double getSpeedSqrt(class_1297 entity) {
      return Math.sqrt(entity.method_5707(new class_243(entity.field_6014, entity.field_6036, entity.field_5969)));
   }

   public static void setVelocity(double velocity) {
      double[] direction = calculateDirection(velocity);
      ((class_746)Objects.requireNonNull(mc.field_1724)).method_18800(direction[0], mc.field_1724.method_18798().method_10214(), direction[1]);
   }

   public static void setVelocity(double velocity, double y) {
      double[] direction = calculateDirection(velocity);
      ((class_746)Objects.requireNonNull(mc.field_1724)).method_18800(direction[0], y, direction[1]);
   }

   public static double getDegreesRelativeToView(class_243 positionRelativeToPlayer, float yaw) {
      float optimalYaw = (float)Math.atan2(-positionRelativeToPlayer.field_1352, positionRelativeToPlayer.field_1350);
      double currentYaw = Math.toRadians((double)class_3532.method_15393(yaw));
      return Math.toDegrees(class_3532.method_15338((double)optimalYaw - currentYaw));
   }

   public static class_10185 getDirectionalInputForDegrees(class_10185 input, double dgs, float deadAngle) {
      boolean forwards = input.comp_3159();
      boolean backwards = input.comp_3160();
      boolean left = input.comp_3161();
      boolean right = input.comp_3162();
      if (dgs >= (double)(-90.0F + deadAngle) && dgs <= (double)(90.0F - deadAngle)) {
         forwards = true;
      } else if (dgs < (double)(-90.0F - deadAngle) || dgs > (double)(90.0F + deadAngle)) {
         backwards = true;
      }

      if (dgs >= (double)(0.0F + deadAngle) && dgs <= (double)(180.0F - deadAngle)) {
         right = true;
      } else if (dgs >= (double)(-180.0F + deadAngle) && dgs <= (double)(0.0F - deadAngle)) {
         left = true;
      }

      return new class_10185(forwards, backwards, left, right, input.comp_3163(), input.comp_3164(), input.comp_3165());
   }

   public static class_10185 getDirectionalInputForDegrees(class_10185 input, double dgs) {
      return getDirectionalInputForDegrees(input, dgs, 20.0F);
   }

   private Simulations() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}

