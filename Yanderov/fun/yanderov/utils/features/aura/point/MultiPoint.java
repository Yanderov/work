package fun.Yanderov.utils.features.aura.point;

import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.features.aura.utils.MathAngle;
import fun.Yanderov.utils.features.aura.utils.RaycastAngle;
import fun.Yanderov.utils.features.aura.warp.Turns;
import java.security.SecureRandom;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import net.minecraft.class_1309;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3545;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_3960;

public class MultiPoint implements QuickImports {
   private final Random random = new SecureRandom();
   private class_243 offset;

   public MultiPoint() {
      this.offset = class_243.field_1353;
   }

   public class_3545 computeVector(class_1309 entity, float maxDistance, Turns initialAngle, class_243 velocity, boolean ignoreWalls) {
      class_3545<List<class_243>, class_238> candidatePoints = this.generateCandidatePoints(entity, maxDistance, ignoreWalls);
      List<class_243> points = (List)candidatePoints.method_15442();
      class_243 selected = this.selectRandomizedBestVector(points, initialAngle, entity);
      this.updateOffset(velocity);
      return new class_3545(selected.method_1019(this.offset), (class_238)candidatePoints.method_15441());
   }

   public class_3545 generateCandidatePoints(class_1309 entity, float maxDistance, boolean ignoreWalls) {
      class_238 entityBox = entity.method_5829();
      double stepY = entityBox.method_17940() / (double)10.0F;
      List<class_243> list = Stream.iterate(entityBox.field_1322, (y) -> y <= entityBox.field_1325, (y) -> y + stepY).map((y) -> new class_243(entityBox.method_1005().field_1352, y, entityBox.method_1005().field_1350)).filter((point) -> this.isValidPoint(mc.field_1724.method_33571(), point, maxDistance, ignoreWalls)).toList();
      return new class_3545(list, entityBox);
   }

   public boolean hasValidPoint(class_1309 entity, float maxDistance, boolean ignoreWalls) {
      class_238 entityBox = entity.method_5829();
      double stepY = entityBox.method_17940() / (double)10.0F;
      return Stream.iterate(entityBox.field_1322, (y) -> y < entityBox.field_1325, (y) -> y + stepY).map((y) -> new class_243(entityBox.method_1005().field_1352, y, entityBox.method_1005().field_1350)).anyMatch((point) -> this.isValidPoint(mc.field_1724.method_33571(), point, maxDistance, ignoreWalls));
   }

   private boolean isValidPoint(class_243 startPoint, class_243 endPoint, float maxDistance, boolean ignoreWalls) {
      return startPoint.method_1022(endPoint) <= (double)maxDistance && (ignoreWalls || !RaycastAngle.raycast(startPoint, endPoint, class_3960.field_17558).method_17783().equals(class_240.field_1332));
   }

   private class_243 selectRandomizedBestVector(List candidatePoints, Turns initialAngle, class_1309 fallbackEntity) {
      if (candidatePoints != null && !candidatePoints.isEmpty()) {
         List<class_243> sorted = candidatePoints.stream().sorted(Comparator.comparing((point) -> this.calculateRotationDifference(mc.field_1724.method_33571(), point, initialAngle))).toList();
         int poolSize = Math.min(5, sorted.size());
         int index = this.random.nextInt(poolSize);
         return (class_243)sorted.get(index);
      } else {
         return fallbackEntity.method_33571();
      }
   }

   private double calculateRotationDifference(class_243 startPoint, class_243 endPoint, Turns initialAngle) {
      Turns targetAngle = MathAngle.fromVec3d(endPoint.method_1020(startPoint));
      Turns delta = MathAngle.calculateDelta(initialAngle, targetAngle);
      return Math.hypot((double)delta.getYaw(), (double)delta.getPitch());
   }

   private void updateOffset(class_243 velocity) {
      this.offset = this.offset.method_1031(this.random.nextGaussian(), this.random.nextGaussian(), this.random.nextGaussian()).method_18806(velocity);
   }

   public Random getRandom() {
      return this.random;
   }

   public class_243 getOffset() {
      return this.offset;
   }
}

