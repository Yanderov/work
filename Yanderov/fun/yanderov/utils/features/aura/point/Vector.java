package fun.Yanderov.utils.features.aura.point;

import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.math.time.StopWatch;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class Vector {
   private static final Random random = new Random();
   private static final StopWatch pointTimer = new StopWatch();
   private static final StopWatch updateTimer = new StopWatch();
   private static List cachedPoints = new ArrayList();
   private static int currentPointIndex = 0;

   public static class_243 hitbox(class_1297 entity, float X, float Y, float Z, float WIDTH) {
      double wHalf = (double)(entity.method_17681() / WIDTH);
      double yExpand = class_3532.method_15350(entity.method_23320() - entity.method_23318(), (double)0.0F, (double)entity.method_17682());
      double xExpand = class_3532.method_15350(QuickImports.mc.field_1724.method_23317() - entity.method_23317(), -wHalf, wHalf);
      double zExpand = class_3532.method_15350(QuickImports.mc.field_1724.method_23321() - entity.method_23321(), -wHalf, wHalf);
      return new class_243(entity.method_23317() + xExpand / (double)X, entity.method_23318() + yExpand / (double)Y, entity.method_23321() + zExpand / (double)Z);
   }

   public static class_243 brain(class_1297 entity, float min, float max) {
      double distance = QuickImports.mc.field_1724.method_19538().method_1022(entity.method_19538());
      double normalizedDistance = class_3532.method_15350((distance - (double)min) / (double)(max - min), (double)0.0F, (double)1.0F);
      double minHeight = 0.2;
      double maxHeight = 0.8;
      double targetHeight = minHeight + (maxHeight - minHeight) * normalizedDistance;
      double targetY = entity.method_23318() + (double)entity.method_17682() * targetHeight;
      return new class_243(entity.method_23317(), targetY, entity.method_23321());
   }

   public static class_243 custom(class_1297 entity, int pointCount, float switchDelay) {
      if (updateTimer.every((double)1000.0F) || cachedPoints.isEmpty()) {
         generateRandomPoints(entity, pointCount);
         currentPointIndex = 0;
         pointTimer.reset();
      }

      if (pointTimer.finished((double)switchDelay)) {
         currentPointIndex = (currentPointIndex + 1) % cachedPoints.size();
         pointTimer.reset();
      }

      return cachedPoints.isEmpty() ? entity.method_19538() : (class_243)cachedPoints.get(currentPointIndex);
   }

   private static void generateRandomPoints(class_1297 entity, int pointCount) {
      cachedPoints.clear();
      double width = (double)entity.method_17681();
      double height = (double)entity.method_17682();
      class_243 entityPos = entity.method_19538();

      for(int i = 0; i < pointCount; ++i) {
         double x = entityPos.field_1352 + (random.nextDouble() - (double)0.5F) * width;
         double y = entityPos.field_1351 + random.nextDouble() * height;
         double z = entityPos.field_1350 + (random.nextDouble() - (double)0.5F) * width;
         cachedPoints.add(new class_243(x, y, z));
      }

   }

   public static List getAllCachedPoints() {
      return new ArrayList(cachedPoints);
   }

   public static int getCurrentPointIndex() {
      return currentPointIndex;
   }

   public static long getTimeSinceLastSwitch() {
      return pointTimer.elapsedTime();
   }

   public static void clearCache() {
      cachedPoints.clear();
      currentPointIndex = 0;
      pointTimer.reset();
      updateTimer.reset();
   }

   public static void forceUpdate(class_1297 entity, int pointCount) {
      generateRandomPoints(entity, pointCount);
      currentPointIndex = 0;
      pointTimer.reset();
      updateTimer.reset();
   }
}

