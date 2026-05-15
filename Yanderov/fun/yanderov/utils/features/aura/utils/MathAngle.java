package fun.Yanderov.utils.features.aura.utils;

import fun.Yanderov.features.impl.combat.Aura;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.features.aura.warp.Turns;
import net.minecraft.class_1297;
import net.minecraft.class_1675;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3966;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.jetbrains.annotations.NotNull;

public final class MathAngle implements QuickImports {
   public static Turns fromVec2f(class_241 vector2f) {
      return new Turns(vector2f.field_1342, vector2f.field_1343);
   }

   public static float computeAngleDifference(float a, float b) {
      return class_3532.method_15393(a - b);
   }

   public static Turns fromVec3d(class_243 vector) {
      return new Turns((float)class_3532.method_15338(Math.toDegrees(Math.atan2(vector.field_1350, vector.field_1352)) - (double)90.0F), (float)class_3532.method_15338(Math.toDegrees(-Math.atan2(vector.field_1351, Math.hypot(vector.field_1352, vector.field_1350)))));
   }

   public static Turns calculateDelta(Turns start, Turns end) {
      float deltaYaw = class_3532.method_15393(end.getYaw() - start.getYaw());
      float deltaPitch = class_3532.method_15393(end.getPitch() - start.getPitch());
      return new Turns(deltaYaw, deltaPitch);
   }

   public static Turns calculateAngle(class_243 to) {
      return fromVec3d(to.method_1020(mc.field_1724.method_33571()));
   }

   public static Turns pitch(float pitch) {
      return new Turns(mc.field_1724.method_36454(), pitch);
   }

   public static Turns cameraAngle() {
      return new Turns(mc.field_1724.method_36454(), mc.field_1724.method_36455());
   }

   public static boolean rayTrace(float yaw, float pitch, float distance, float wallDistance, class_1297 entity) {
      class_239 result = rayTrace((double)distance, yaw, pitch);
      class_243 startPoint = mc.field_1724.method_19538().method_1031((double)0.0F, (double)mc.field_1724.method_18381(mc.field_1724.method_18376()), (double)0.0F);
      double distancePow2 = Math.pow((double)distance, (double)2.0F);
      Aura attackAuraModule1 = Aura.getInstance();
      if (result != null) {
         distancePow2 = startPoint.method_1025(result.method_17784());
      }

      class_243 rotationVector = getRotationVector(pitch, yaw).method_1021((double)distance);
      class_243 endPoint = startPoint.method_1019(rotationVector);
      class_238 entityArea = mc.field_1724.method_5829().method_18804(rotationVector).method_1009((double)1.0F, (double)1.0F, (double)1.0F);
      double maxDistance = Math.max(distancePow2, Math.pow((double)wallDistance, (double)2.0F));
      class_3966 ehr = class_1675.method_18075(mc.field_1724, startPoint, endPoint, entityArea, (e) -> !e.method_7325() && e.method_5863() && e == entity, maxDistance);
      if (ehr != null) {
         boolean allowedWallDistance = startPoint.method_1025(ehr.method_17784()) <= Math.pow((double)wallDistance, (double)2.0F);
         boolean wallMissing = result == null;
         boolean var10000 = startPoint.method_1025(ehr.method_17784()) < distancePow2;
         if (startPoint.method_1025(ehr.method_17784()) <= Math.pow((double)distance, (double)2.0F)) {
            double minY = entity.method_23318();
            double targetHeight = (double)entity.method_17682();
            double minHitY = minY + targetHeight * 0.3;
            if (ehr.method_17784().field_1351 >= minHitY) {
               return ehr.method_17782() == entity;
            }
         }
      }

      return false;
   }

   public static class_239 rayTrace(double dst, float yaw, float pitch) {
      class_243 vec3d = mc.field_1724.method_5836(1.0F);
      class_243 vec3d2 = getRotationVector(pitch, yaw);
      class_243 vec3d3 = vec3d.method_1031(vec3d2.field_1352 * dst, vec3d2.field_1351 * dst, vec3d2.field_1350 * dst);
      return mc.field_1687.method_17742(new class_3959(vec3d, vec3d3, class_3960.field_17559, class_242.field_1348, mc.field_1724));
   }

   public static @NotNull class_243 getRotationVector(float yaw, float pitch) {
      return new class_243((double)(class_3532.method_15374(-pitch * ((float)Math.PI / 180F)) * class_3532.method_15362(yaw * ((float)Math.PI / 180F))), (double)(-class_3532.method_15374(yaw * ((float)Math.PI / 180F))), (double)(class_3532.method_15362(-pitch * ((float)Math.PI / 180F)) * class_3532.method_15362(yaw * ((float)Math.PI / 180F))));
   }

   private MathAngle() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}

