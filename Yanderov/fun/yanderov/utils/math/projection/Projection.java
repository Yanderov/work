package fun.Yanderov.utils.math.projection;

import fun.Yanderov.utils.display.geometry.Render3D;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.features.aura.utils.MathAngle;
import fun.Yanderov.utils.features.aura.warp.Turns;
import fun.Yanderov.utils.math.calc.Calculate;
import net.minecraft.class_1297;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4604;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4d;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;

public final class Projection implements QuickImports {
   public static @NotNull class_243 worldSpaceToScreenSpace(class_243 pos) {
      Vector3f delta = pos.method_46409();
      int[] viewport = new int[4];
      GL11.glGetIntegerv(2978, viewport);
      Vector3f target = new Vector3f();
      Vector4f transformedCoordinates = (new Vector4f(delta.x, delta.y, delta.z, 1.0F)).mul(Render3D.lastWorldSpaceMatrix.method_23761());
      Matrix4f matrixProj = new Matrix4f(Render3D.lastProjMat);
      matrixProj.project(transformedCoordinates.x(), transformedCoordinates.y(), transformedCoordinates.z(), viewport, target);
      return new class_243((double)target.x / mc.method_22683().method_4495(), (double)((float)mc.method_22683().method_4507() - target.y) / mc.method_22683().method_4495(), (double)target.z);
   }

   public static double getDistanceToGround() {
      for(double y = mc.field_1724.method_23318(); y > (double)0.0F; y -= 0.1) {
         if (!mc.field_1687.method_8320(mc.field_1724.method_24515().method_10087((int)(mc.field_1724.method_23318() - y + (double)1.0F))).method_26215()) {
            return mc.field_1724.method_23318() - y;
         }
      }

      return (double)256.0F;
   }

   public static Vector4d getVector4D(class_1297 ent) {
      Vector4d position = null;
      if (ent != null) {
         for(class_243 vector : getVec3ds(ent, Calculate.interpolate(ent))) {
            vector = worldSpaceToScreenSpace(new class_243(vector.field_1352, vector.field_1351, vector.field_1350));
            if (vector.field_1350 > (double)0.0F && vector.field_1350 < (double)1.0F) {
               if (position == null) {
                  position = new Vector4d(vector.field_1352, vector.field_1351, vector.field_1350, (double)0.0F);
               }

               position.x = Math.min(vector.field_1352, position.x);
               position.y = Math.min(vector.field_1351, position.y);
               position.z = Math.max(vector.field_1352, position.z);
               position.w = Math.max(vector.field_1351, position.w);
            }
         }
      }

      return position;
   }

   public static @NotNull class_243[] getVec3ds(class_1297 ent, class_243 pos) {
      class_238 axisAlignedBB2 = ent.method_5829();
      class_238 axisAlignedBB = new class_238(axisAlignedBB2.field_1323 - ent.method_23317() + pos.field_1352 - (double)0.1F, axisAlignedBB2.field_1322 - ent.method_23318() + pos.field_1351 - (double)0.1F, axisAlignedBB2.field_1321 - ent.method_23321() + pos.field_1350 - (double)0.1F, axisAlignedBB2.field_1320 - ent.method_23317() + pos.field_1352 + (double)0.1F, axisAlignedBB2.field_1325 - ent.method_23318() + pos.field_1351 + (double)0.1F, axisAlignedBB2.field_1324 - ent.method_23321() + pos.field_1350 + (double)0.1F);
      return new class_243[]{new class_243(axisAlignedBB.field_1323, axisAlignedBB.field_1322, axisAlignedBB.field_1321), new class_243(axisAlignedBB.field_1323, axisAlignedBB.field_1325, axisAlignedBB.field_1321), new class_243(axisAlignedBB.field_1320, axisAlignedBB.field_1322, axisAlignedBB.field_1321), new class_243(axisAlignedBB.field_1320, axisAlignedBB.field_1325, axisAlignedBB.field_1321), new class_243(axisAlignedBB.field_1323, axisAlignedBB.field_1322, axisAlignedBB.field_1324), new class_243(axisAlignedBB.field_1323, axisAlignedBB.field_1325, axisAlignedBB.field_1324), new class_243(axisAlignedBB.field_1320, axisAlignedBB.field_1322, axisAlignedBB.field_1324), new class_243(axisAlignedBB.field_1320, axisAlignedBB.field_1325, axisAlignedBB.field_1324)};
   }

   public static boolean canSee(class_243 vec3d) {
      class_4184 camera = mc.method_1561().field_4686;
      Turns angle = MathAngle.fromVec3d(vec3d.method_1020(camera.method_19326()));
      return Math.abs(class_3532.method_15393(angle.getYaw() - camera.method_19330())) < 90.0F && Math.abs(class_3532.method_15393(angle.getPitch() - camera.method_19329())) < 60.0F || canSee(new class_238(class_2338.method_49638(vec3d)));
   }

   public static boolean canSee(class_238 box) {
      class_4604 frustum = mc.field_1769.field_27740;
      return box != null && frustum != null && frustum.method_23093(box);
   }

   public static boolean cantSee(Vector4d vec) {
      return vec == null || vec.x < (double)0.0F && vec.z < (double)1.0F || vec.y < (double)0.0F && vec.w < (double)1.0F;
   }

   public static double centerX(Vector4d vec) {
      return vec.x + (vec.z - vec.x) / (double)2.0F;
   }

   private Projection() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}

