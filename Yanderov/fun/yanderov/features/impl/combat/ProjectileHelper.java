package fun.Yanderov.features.impl.combat;

import fun.Yanderov.common.repository.friend.FriendUtils;
import fun.Yanderov.events.player.RotationUpdateEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.MultiSelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.features.aura.target.TargetFinder;
import fun.Yanderov.utils.features.aura.warp.Turns;
import fun.Yanderov.utils.features.aura.warp.TurnsConfig;
import fun.Yanderov.utils.features.aura.warp.TurnsConnection;
import fun.Yanderov.utils.math.calc.Calculate;
import fun.Yanderov.utils.math.task.TaskPriority;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import net.minecraft.class_1297;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1429;
import net.minecraft.class_1531;
import net.minecraft.class_1657;
import net.minecraft.class_1753;
import net.minecraft.class_1764;
import net.minecraft.class_1799;
import net.minecraft.class_1835;
import net.minecraft.class_1937;
import net.minecraft.class_243;

public class ProjectileHelper extends Module {
   private final SliderSettings searchDistance = (new SliderSettings("Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð¿Ð¾Ð¸ÑÐºÐ°", "Ð Ð°Ð´Ð¸ÑƒÑ Ð¿Ð¾Ð¸ÑÐºÐ° Ñ†ÐµÐ»Ð¸ Ð²Ð¾ÐºÑ€ÑƒÐ³ Ð¸Ð³Ñ€Ð¾ÐºÐ°")).setValue(16.0F).range(5.0F, 64.0F);
   private final MultiSelectSetting targetType = (new MultiSelectSetting("Ð¢Ð¸Ð¿ Ñ‚Ð°Ñ€Ð³ÐµÑ‚Ð°", "Ð¤Ð¸Ð»ÑŒÑ‚Ñ€ÑƒÐµÑ‚ Ñ†ÐµÐ»Ð¸ Ð¿Ð¾ Ñ‚Ð¸Ð¿Ñƒ")).value("Players", "Mobs", "Animals", "Armor Stand").selected("Players", "Mobs", "Animals");
   private final TargetFinder targetFinder = new TargetFinder();
   private class_1309 currentTarget;

   public ProjectileHelper() {
      super("ProjectileHelper", "Projectile Helper", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.searchDistance, this.targetType});
   }

   public class_1309 getTarget(class_1937 world, Iterable entities) {
      List<class_1297> entityList = (List)StreamSupport.stream(entities.spliterator(), false).collect(Collectors.toList());
      List<class_1309> validTargets = (List)entityList.stream().filter((e) -> e instanceof class_1309).map((e) -> (class_1309)e).filter(this::isValidTarget).collect(Collectors.toList());
      class_1309 nearestTarget = null;
      double nearestDistance = Double.MAX_VALUE;
      class_243 playerPos = mc.field_1724.method_19538();

      for(class_1309 target : validTargets) {
         double distance = target.method_19538().method_1022(playerPos);
         if (distance < nearestDistance && distance <= (double)this.searchDistance.getValue()) {
            nearestDistance = distance;
            nearestTarget = target;
         }
      }

      this.currentTarget = nearestTarget;
      return this.currentTarget;
   }

   private boolean isValidTarget(class_1309 entity) {
      if (entity == null) {
         return false;
      } else if (entity == mc.field_1724) {
         return false;
      } else if (!entity.method_5805()) {
         return false;
      } else if (!this.targetType.isSelected("Players") && entity instanceof class_1657) {
         return false;
      } else if (!this.targetType.isSelected("Mobs") && entity instanceof class_1308) {
         return false;
      } else if (!this.targetType.isSelected("Animals") && entity instanceof class_1429) {
         return false;
      } else {
         return this.targetType.isSelected("Armor Stand") || !(entity instanceof class_1531);
      }
   }

   public class_243 getPredictedPosition(class_1309 target, class_243 shooterPos, float projectileSpeed, float gravity) {
      class_243 targetPos = target.method_19538().method_1031((double)0.0F, (double)target.method_17682() * (double)0.5F, (double)0.0F);
      class_243 targetVelocity = target.method_18798();
      class_243 delta = targetPos.method_1020(shooterPos);
      double a = (double)(projectileSpeed * projectileSpeed) - targetVelocity.method_1027();
      double b = (double)-2.0F * delta.method_1026(targetVelocity);
      double c = -delta.method_1027();
      double discriminant = b * b - (double)4.0F * a * c;
      double t;
      if (discriminant > (double)0.0F) {
         double t1 = (-b + Math.sqrt(discriminant)) / ((double)2.0F * a);
         double t2 = (-b - Math.sqrt(discriminant)) / ((double)2.0F * a);
         t = Math.max(t1, t2);
      } else {
         t = delta.method_1033() / (double)projectileSpeed;
      }

      class_243 predicted = targetPos.method_1019(targetVelocity.method_1021(t));
      predicted = predicted.method_1031((double)0.0F, (double)0.5F * (double)gravity * t * t, (double)0.0F);
      return predicted;
   }

   private boolean isHoldingProjectile() {
      class_1799 main = mc.field_1724.method_6047();
      return main.method_7909() instanceof class_1753 || main.method_7909() instanceof class_1764 || main.method_7909() instanceof class_1835;
   }

   @EventHandler
   public void onRotationUpdate(RotationUpdateEvent e) {
      if (e.getType() == 0) {
         class_1799 stack;
         boolean holdingBow;
         boolean var20;
         label49: {
            stack = mc.field_1724.method_6047();
            holdingBow = stack.method_7909() instanceof class_1753;
            if (stack.method_7909() instanceof class_1764) {
               class_1764 var10000 = (class_1764)stack.method_7909();
               if (class_1764.method_7781(stack)) {
                  var20 = true;
                  break label49;
               }
            }

            var20 = false;
         }

         boolean holdingCrossbow = var20;
         boolean holdingTrident = stack.method_7909() instanceof class_1835;
         if (!holdingBow && !holdingCrossbow && !holdingTrident) {
            this.currentTarget = null;
         } else if (holdingBow && mc.field_1724.method_6030() != stack) {
            this.currentTarget = null;
         } else {
            if (this.currentTarget != null && !this.currentTarget.method_5805()) {
               this.currentTarget = null;
            }

            if (this.currentTarget == null) {
               this.currentTarget = this.getTarget(mc.field_1687, mc.field_1687.method_18112());
               if (this.currentTarget == mc.field_1724) {
                  this.currentTarget = null;
               }
            }

            if (FriendUtils.isFriend((class_1297)this.currentTarget)) {
               this.currentTarget = null;
            }

            if (this.currentTarget != null) {
               class_243 shooterPos = mc.field_1724.method_19538().method_1031((double)0.0F, (double)mc.field_1724.method_18381(mc.field_1724.method_18376()), (double)0.0F).method_1019(mc.field_1724.method_18798());
               float projectileSpeed = 6.0F;
               float gravity = 0.02F;
               class_243 predictedPos = this.getPredictedPosition(this.currentTarget, shooterPos, projectileSpeed, gravity);
               double dx = predictedPos.field_1352 - shooterPos.field_1352;
               double dy = predictedPos.field_1351 - shooterPos.field_1351;
               double dz = predictedPos.field_1350 - shooterPos.field_1350;
               double distanceXZ = Math.sqrt(dx * dx + dz * dz);
               float yaw = (float)Math.toDegrees(Math.atan2(dz, dx)) - 90.0F + (float)Calculate.getRandom(-1, 1);
               float pitch = (float)(-Math.toDegrees(Math.atan2(dy, distanceXZ))) + (float)Calculate.getRandom(-1, 1);
               TurnsConnection.INSTANCE.rotateTo(new Turns(yaw, pitch), TurnsConfig.DEFAULT, TaskPriority.HIGH_IMPORTANCE_1, this);
            }

         }
      }
   }
}

