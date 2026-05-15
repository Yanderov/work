package dev.client.yanderov.utils.features.aura.utils;

import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.features.aura.striking.StrikerConstructor;
import dev.client.yanderov.utils.features.aura.warp.Turns;
import dev.client.yanderov.utils.features.aura.warp.TurnsConnection;
import java.util.Objects;
import java.util.function.Predicate;
import net.minecraft.class_1297;
import net.minecraft.class_1675;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_746;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;

public final class RaycastAngle implements QuickImports {
   public static class_3965 raycast(double range, Turns angle, boolean includeFluids) {
      return raycast(((class_746)Objects.requireNonNull(mc.field_1724)).method_5836(1.0F), range, angle, includeFluids);
   }

   public static class_3965 raycast(class_243 vec, double range, Turns angle, boolean includeFluids) {
      class_1297 entity = mc.field_1719;
      if (entity == null) {
         return null;
      } else {
         class_243 rotationVec = angle.toVector();
         class_243 end = vec.method_1031(rotationVec.field_1352 * range, rotationVec.field_1351 * range, rotationVec.field_1350 * range);
         class_1937 world = mc.field_1687;
         if (world == null) {
            return null;
         } else {
            class_3959.class_242 fluidHandling = includeFluids ? class_242.field_1347 : class_242.field_1348;
            class_3959 context = new class_3959(vec, end, class_3960.field_17559, fluidHandling, entity);
            return world.method_17742(context);
         }
      }
   }

   public static class_3965 raycast(class_243 start, class_243 end, class_3959.class_3960 shapeType) {
      return raycast(start, end, shapeType, mc.field_1724);
   }

   public static class_3965 raycast(class_243 start, class_243 end, class_3959.class_3960 shapeType, class_1297 entity) {
      return raycast(start, end, shapeType, class_242.field_1348, entity);
   }

   public static class_3965 raycast(class_243 start, class_243 end, class_3959.class_3960 shapeType, class_3959.class_242 fluidHandling, class_1297 entity) {
      return mc.field_1687.method_17742(new class_3959(start, end, shapeType, fluidHandling, entity));
   }

   public static class_3966 raytraceEntity(double range, Turns angle, Predicate filter) {
      class_1297 entity = mc.field_1724;
      if (entity == null) {
         return null;
      } else {
         class_243 cameraVec = entity.method_5836(1.0F);
         class_243 rotationVec = angle.toVector();
         class_243 vec3d3 = cameraVec.method_1031(rotationVec.field_1352 * range, rotationVec.field_1351 * range, rotationVec.field_1350 * range);
         class_238 box = entity.method_5829().method_18804(rotationVec.method_1021(range)).method_1009((double)1.0F, (double)1.0F, (double)1.0F);
         return class_1675.method_18075(entity, cameraVec, vec3d3, box, (e) -> !e.method_7325() && filter.test(e), range * range);
      }
   }

   public static boolean rayTrace(StrikerConstructor.AttackPerpetratorConfigurable config) {
      boolean elytraMode = mc.field_1724.method_6128() && config.getTarget().method_6128();
      return elytraMode ? true : rayTrace(TurnsConnection.INSTANCE.getRotation().toVector(), (double)(config.getMaximumRange() - 0.25F), config.getBox());
   }

   public static boolean rayTrace(double range, class_238 box) {
      return rayTrace(TurnsConnection.INSTANCE.getRotation().toVector(), range - (double)0.25F, box);
   }

   public static boolean rayTrace(class_243 clientVec, double range, class_238 box) {
      class_243 cameraVec = ((class_746)Objects.requireNonNull(mc.field_1724)).method_33571();
      return box.method_1006(cameraVec) || box.method_992(cameraVec, cameraVec.method_1019(clientVec.method_1021(range))).isPresent();
   }

   private RaycastAngle() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}

