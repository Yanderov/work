package fun.Yanderov.utils.features.aura.target;

import fun.Yanderov.common.repository.friend.FriendUtils;
import fun.Yanderov.features.impl.combat.AntiBot;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.features.aura.point.MultiPoint;
import fun.Yanderov.utils.features.aura.rotations.constructor.LinearConstructor;
import fun.Yanderov.utils.features.aura.utils.MathAngle;
import fun.Yanderov.utils.features.aura.utils.RaycastAngle;
import fun.Yanderov.utils.features.aura.warp.TurnsConnection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import net.minecraft.class_1297;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1429;
import net.minecraft.class_1531;
import net.minecraft.class_1657;
import net.minecraft.class_243;

public class TargetFinder implements QuickImports {
   private final MultiPoint pointFinder = new MultiPoint();
   private class_1309 currentTarget = null;
   private Stream potentialTargets;

   public void lockTarget(class_1309 target) {
      if (this.currentTarget == null) {
         this.currentTarget = target;
      }

   }

   public void releaseTarget() {
      this.currentTarget = null;
   }

   public void validateTarget(Predicate predicate) {
      this.findFirstMatch(predicate).ifPresent(this::lockTarget);
      if (this.currentTarget != null && !predicate.test(this.currentTarget)) {
         this.releaseTarget();
      }

   }

   public void searchTargets(Iterable entities, float maxDistance, float maxFov, boolean ignoreWalls) {
      if (this.currentTarget != null && (!this.pointFinder.hasValidPoint(this.currentTarget, maxDistance, ignoreWalls) || this.getFov(this.currentTarget, maxDistance, ignoreWalls) > (double)maxFov)) {
         this.releaseTarget();
      }

      this.potentialTargets = this.createStreamFromEntities(entities, maxDistance, maxFov, ignoreWalls);
   }

   private double getFov(class_1309 entity, float maxDistance, boolean ignoreWalls) {
      class_243 attackVector = (class_243)this.pointFinder.computeVector(entity, maxDistance, TurnsConnection.INSTANCE.getRotation(), (new LinearConstructor()).randomValue(), ignoreWalls).method_15442();
      return RaycastAngle.rayTrace((double)maxDistance, entity.method_5829()) ? (double)0.0F : TurnsConnection.computeRotationDifference(MathAngle.cameraAngle(), MathAngle.calculateAngle(attackVector));
   }

   private Stream createStreamFromEntities(Iterable entities, float maxDistance, float maxFov, boolean ignoreWalls) {
      Stream var10000 = StreamSupport.stream(entities.spliterator(), false);
      Objects.requireNonNull(class_1309.class);
      var10000 = var10000.filter(class_1309.class::isInstance);
      Objects.requireNonNull(class_1309.class);
      return var10000.map(class_1309.class::cast).filter((entity) -> this.pointFinder.hasValidPoint(entity, maxDistance, ignoreWalls) && this.getFov(entity, maxDistance, ignoreWalls) < (double)maxFov).sorted(Comparator.comparingDouble((entity) -> (double)entity.method_5739(mc.field_1724)));
   }

   private Optional findFirstMatch(Predicate predicate) {
      return this.potentialTargets.filter(predicate).findFirst();
   }

   public MultiPoint getPointFinder() {
      return this.pointFinder;
   }

   public class_1309 getCurrentTarget() {
      return this.currentTarget;
   }

   public Stream getPotentialTargets() {
      return this.potentialTargets;
   }

   public static class EntityFilter {
      private final List targetSettings;

      public boolean isValid(class_1309 entity) {
         if (this.isLocalPlayer(entity)) {
            return false;
         } else if (this.isInvalidHealth(entity)) {
            return false;
         } else {
            return this.isBotPlayer(entity) ? false : this.isValidEntityType(entity);
         }
      }

      private boolean isLocalPlayer(class_1309 entity) {
         return entity == QuickImports.mc.field_1724;
      }

      private boolean isInvalidHealth(class_1309 entity) {
         return !entity.method_5805() || entity.method_6032() <= 0.0F;
      }

      private boolean isBotPlayer(class_1309 entity) {
         boolean var10000;
         if (entity instanceof class_1657 player) {
            if (AntiBot.getInstance().isBot(player)) {
               var10000 = true;
               return var10000;
            }
         }

         var10000 = false;
         return var10000;
      }

      private boolean isNakedPlayer(class_1309 entity) {
         return entity.method_31747();
      }

      private boolean isValidEntityType(class_1309 entity) {
         Objects.requireNonNull(entity);
         class_1309 var2 = entity;
         byte var3 = 0;

         while(true) {
            boolean var10000;
            //$FF: var3->value
            //0->net/minecraft/class_1657
            //1->net/minecraft/class_1429
            //2->net/minecraft/class_1308
            //3->net/minecraft/class_1531
            switch (var2.typeSwitch<invokedynamic>(var2, var3)) {
               case 0:
                  class_1657 player = (class_1657)var2;
                  if (!this.targetSettings.contains("Friends") && FriendUtils.isFriend((class_1297)player)) {
                     var3 = 1;
                     break;
                  }

                  var10000 = this.targetSettings.contains("Players");
                  return var10000;
               case 1:
                  class_1429 animal = (class_1429)var2;
                  var10000 = this.targetSettings.contains("Animals");
                  return var10000;
               case 2:
                  class_1308 mob = (class_1308)var2;
                  var10000 = this.targetSettings.contains("Mobs");
                  return var10000;
               case 3:
                  class_1531 armorStand = (class_1531)var2;
                  var10000 = this.targetSettings.contains("Armor Stand");
                  return var10000;
               default:
                  var10000 = false;
                  return var10000;
            }
         }
      }

      public EntityFilter(List targetSettings) {
         this.targetSettings = targetSettings;
      }
   }
}

