package dev.client.yanderov.utils.features.aura.warp;

import dev.client.yanderov.utils.math.calc.Calculate;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class Turns {
   public static Turns DEFAULT = new Turns(0.0F, 0.0F);
   private float yaw;
   private float pitch;

   public static Turns fromTargetHead(class_243 playerPos, class_243 targetPos, double targetHeight) {
      double headY = targetPos.field_1351 + targetHeight * 0.9;
      double deltaX = targetPos.field_1352 - playerPos.field_1352;
      double deltaY = headY - (playerPos.field_1351 + (double)1.5F);
      double deltaZ = targetPos.field_1350 - playerPos.field_1350;
      float yaw = (float)Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90.0F;
      yaw = class_3532.method_15393(yaw);
      double horizontalDistance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
      float pitch = (float)Math.toDegrees(-Math.atan2(deltaY, horizontalDistance));
      pitch = class_3532.method_15363(pitch, -90.0F, 90.0F);
      return new Turns(yaw, pitch);
   }

   public Turns adjustSensitivity() {
      double gcd = Calculate.computeGcd();
      Turns previousAngle = TurnsConnection.INSTANCE.getServerAngle();
      float adjustedYaw = this.adjustAxis(this.yaw, previousAngle.yaw, gcd);
      float adjustedPitch = this.adjustAxis(this.pitch, previousAngle.pitch, gcd);
      return new Turns(adjustedYaw, class_3532.method_15363(adjustedPitch, -90.0F, 90.0F));
   }

   public Turns random(float f) {
      return new Turns(this.yaw + Calculate.getRandom(-f, f), this.pitch + Calculate.getRandom(-f, f));
   }

   private float adjustAxis(float axisValue, float previousValue, double gcd) {
      float delta = axisValue - previousValue;
      return previousValue + (float)Math.round((double)delta / gcd) * (float)gcd;
   }

   public final class_243 toVector() {
      float f = this.pitch * ((float)Math.PI / 180F);
      float g = -this.yaw * ((float)Math.PI / 180F);
      float h = class_3532.method_15362(g);
      float i = class_3532.method_15374(g);
      float j = class_3532.method_15362(f);
      float k = class_3532.method_15374(f);
      return new class_243((double)(i * j), (double)(-k), (double)(h * j));
   }

   public Turns addYaw(float yaw) {
      return new Turns(this.yaw + yaw, this.pitch);
   }

   public Turns addPitch(float pitch) {
      this.pitch = class_3532.method_15363(this.pitch + pitch, -90.0F, 90.0F);
      return this;
   }

   public Turns of(Turns angle) {
      return new Turns(angle.getYaw(), angle.getPitch());
   }

   public float getYaw() {
      return this.yaw;
   }

   public float getPitch() {
      return this.pitch;
   }

   public void setYaw(float yaw) {
      this.yaw = yaw;
   }

   public void setPitch(float pitch) {
      this.pitch = pitch;
   }

   public String toString() {
      float var10000 = this.getYaw();
      return "Turns(yaw=" + var10000 + ", pitch=" + this.getPitch() + ")";
   }

   public Turns(float yaw, float pitch) {
      this.yaw = yaw;
      this.pitch = pitch;
   }

   public static class VecRotation {
      private final Turns angle;
      private final class_243 vec;

      public String toString() {
         String var10000 = String.valueOf(this.getAngle());
         return "Turns.VecRotation(angle=" + var10000 + ", vec=" + String.valueOf(this.getVec()) + ")";
      }

      public Turns getAngle() {
         return this.angle;
      }

      public class_243 getVec() {
         return this.vec;
      }

      public VecRotation(Turns angle, class_243 vec) {
         this.angle = angle;
         this.vec = vec;
      }
   }
}

