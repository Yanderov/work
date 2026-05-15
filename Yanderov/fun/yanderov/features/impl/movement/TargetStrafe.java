package fun.Yanderov.features.impl.movement;

import antidaunleak.api.annotation.Native;
import fun.Yanderov.events.player.InputEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.impl.combat.Aura;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BindSetting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.MultiSelectSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.features.aura.warp.TurnsConnection;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import org.lwjgl.glfw.GLFW;

public class TargetStrafe extends Module {
   private static final class_310 mc = class_310.method_1551();
   public SelectSetting mode = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð¢Ð¸Ð¿ ÑÑ‚Ñ€ÐµÐ¹Ñ„Ð°")).value("Matrix", "Grim", "Custom", "Vanilla").selected("Matrix");
   SelectSetting type = (new SelectSetting("Ð¢Ð¾Ñ‡ÐºÐ° Ñ…Ð¾Ð´ÑŒÐ±Ñ‹", "Ð’Ñ‹Ð±Ð¸Ñ€ÐµÑ‚Ðµ Ñ‚Ð¾Ñ‡ÐºÑƒ ÐºÑƒÐ´Ð° Ð±ÑƒÐ´ÐµÑ‚ Ð¸Ð´Ñ‚Ð¸ ÑÑ‚Ñ€ÐµÐ¹Ñ„")).value("Cube", "Center", "Circle").selected("Cube").visible(() -> this.mode.isSelected("Grim"));
   SelectSetting typeMatrix = (new SelectSetting("Ð¢Ð¾Ñ‡ÐºÐ° Ð´Ð»Ñ Ð¾Ð±Ñ…Ð¾Ð´Ð°", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ñ‚Ð¾Ñ‡ÐºÑƒ Ð¾Ð±Ñ…Ð¾Ð´Ð° Ð² Ñ€ÐµÐ¶Ð¸Ð¼Ðµ Matrix/Custom")).value("Cube", "Circle").selected("Circle").visible(() -> this.mode.isSelected("Matrix") || this.mode.isSelected("Custom"));
   SliderSettings grimRadius = (new SliderSettings("Ð Ð°Ð´Ð¸ÑƒÑ Ð¾Ð±Ñ…Ð¾Ð´Ð°", "Ð Ð°Ð´Ð¸ÑƒÑ Ð¾Ð±Ñ…Ð¾Ð´Ð° Ð²Ð¾ÐºÑ€ÑƒÐ³ Ñ†ÐµÐ»Ð¸")).setValue(0.87F).range(0.1F, 1.5F).visible(() -> this.mode.isSelected("Grim") && (this.type.isSelected("Cube") || this.type.isSelected("Circle")));
   MultiSelectSetting setting = (new MultiSelectSetting("ÐÐ°ÑÑ‚Ñ€Ð¾Ð¹ÐºÐ¸", "ÐŸÐ¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ Ð½Ð°ÑÑ‚Ñ€Ð¾Ð¸Ñ‚ÑŒ Ñ€Ð°Ð±Ð¾Ñ‚Ñƒ ÑÑ‚Ñ€ÐµÐ¹Ñ„Ð¾Ð²")).value("Auto Jump", "Only Key Pressed", "In front of the target", "Direction Mode", "ÐšÐ¾Ñ€Ñ€ÐµÐºÑ†Ð¸Ñ Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ñ").selected("Auto Jump");
   SelectSetting movementCorrectionMode = (new SelectSetting("ÐšÐ¾Ñ€Ñ€ÐµÐºÑ†Ð¸Ñ Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ñ", "ÐšÑƒÐ´Ð° Ð¿Ñ‹Ñ‚Ð°Ñ‚ÑŒÑÑ Ð²ÑÑ‚Ð°Ñ‚ÑŒ")).value("Ð¡Ð¿Ð¸Ð½Ð°", "Ð’Ð·Ð³Ð»ÑÐ´").selected("Ð¡Ð¿Ð¸Ð½Ð°").visible(() -> this.setting.isSelected("ÐšÐ¾Ñ€Ñ€ÐµÐºÑ†Ð¸Ñ Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ñ"));
   SelectSetting directionMode = (new SelectSetting("ÐÐ°Ð¿Ñ€Ð°Ð²Ð»ÐµÐ½Ð¸Ðµ", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ð½Ð°Ð¿Ñ€Ð°Ð²Ð»ÐµÐ½Ð¸Ðµ Ð¾Ð±Ñ…Ð¾Ð´Ð°")).value("Clockwise", "Counterclockwise", "Random").selected("Clockwise").visible(() -> this.setting.isSelected("Direction Mode"));
   SliderSettings radius = (new SliderSettings("Ð Ð°Ð´Ð¸ÑƒÑ", "Ð Ð°Ð´Ð¸ÑƒÑ Ð¾Ð±Ñ…Ð¾Ð´Ð° Ð²Ð¾ÐºÑ€ÑƒÐ³ Ñ†ÐµÐ»Ð¸")).setValue(2.5F).range(0.1F, 7.0F).visible(() -> this.mode.isSelected("Matrix") || this.mode.isSelected("Custom") || this.mode.isSelected("Vanilla"));
   SliderSettings speed = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ ÑÑ‚Ñ€ÐµÐ¹Ñ„Ð°")).setValue(0.3F).range(0.1F, 1.0F).visible(() -> this.mode.isSelected("Matrix") || this.mode.isSelected("Custom"));
   BooleanSetting customAffectSpeed = (new BooleanSetting("Custom Speed", "Ð’Ð»Ð¸ÑÑ‚ÑŒ Ð½Ð° ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð² Ñ€ÐµÐ¶Ð¸Ð¼Ðµ Custom")).setValue(true).visible(() -> this.mode.isSelected("Custom"));
   BooleanSetting hitRunWay = (new BooleanSetting("HitRunWay", "ÐžÑ‚Ñ…Ð¾Ð´Ð¸Ñ‚ÑŒ Ð¿Ð¾ÑÐ»Ðµ ÑƒÐ´Ð°Ñ€Ð° Ð¸ Ð²Ð¾Ð·Ð²Ñ€Ð°Ñ‰Ð°Ñ‚ÑŒÑÑ")).setValue(false);
   SliderSettings hitRunDistance = (new SliderSettings("HitRunDistanceTS", "Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Hit&Run")).setValue(4.0F).range(1.0F, 10.0F).visible(() -> this.hitRunWay.isValue());
   BooleanSetting peragon = (new BooleanSetting("Ð¿ÐµÑ€ÐµÐ³Ð¾Ð½", "Ð”Ð²Ð¸Ð³Ð°Ñ‚ÑŒÑÑ Ð²Ð¿ÐµÑ€Ñ‘Ð´ Ð¿Ð¾ Ð²Ð·Ð³Ð»ÑÐ´Ñƒ Ñ†ÐµÐ»Ð¸")).setValue(false);
   SliderSettings peragonDistance = (new SliderSettings("Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð¿ÐµÑ€ÐµÐ³Ð¾Ð½Ð°", "ÐÐ°ÑÐºÐ¾Ð»ÑŒÐºÐ¾ Ð²Ð¿ÐµÑ€Ñ‘Ð´ Ð¸Ð´Ñ‚Ð¸ Ð¿Ð¾ Ð²Ð·Ð³Ð»ÑÐ´Ñƒ Ñ†ÐµÐ»Ð¸")).setValue(0.5F).range(0.1F, 3.0F).visible(() -> this.peragon.isValue());
   SelectSetting peragonMode = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼ Ð¿ÐµÑ€ÐµÐ³Ð¾Ð½Ð°", "Ð ÐµÐ¶Ð¸Ð¼ Ð°ÐºÑ‚Ð¸Ð²Ð°Ñ†Ð¸Ð¸ Ð¿ÐµÑ€ÐµÐ³Ð¾Ð½Ð°")).value("ÑƒÐ¼Ð½Ñ‹Ð¹", "Ð±Ð¸Ð½Ð´").selected("ÑƒÐ¼Ð½Ñ‹Ð¹").visible(() -> this.peragon.isValue());
   BindSetting peragonBind = (new BindSetting("Ð‘Ð¸Ð½Ð´ Ð¿ÐµÑ€ÐµÐ³Ð¾Ð½Ð°", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð¿ÐµÑ€ÐµÐ³Ð¾Ð½Ð°")).setKey(0).visible(() -> this.peragon.isValue() && this.peragonMode.isSelected("Ð±Ð¸Ð½Ð´"));
   private int grimPointIndex = 0;
   private boolean peragonActive = false;
   private long peragonEndMs = 0L;

   public TargetStrafe() {
      super("TargetStrafe", "Target Strafe", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.mode, this.type, this.typeMatrix, this.grimRadius, this.radius, this.speed, this.customAffectSpeed, this.hitRunWay, this.hitRunDistance, this.setting, this.directionMode, this.movementCorrectionMode, this.peragon, this.peragonDistance, this.peragonMode, this.peragonBind});
   }

   public static TargetStrafe getInstance() {
      return (TargetStrafe)Instance.get(TargetStrafe.class);
   }

   private boolean isPeragonActive() {
      if (!this.peragon.isValue()) {
         return false;
      } else if (this.peragonMode.isSelected("Ð±Ð¸Ð½Ð´")) {
         if (mc.method_22683() == null) {
            return false;
         } else {
            int key = this.peragonBind.getKey();
            if (key <= 0) {
               return false;
            } else {
               boolean pressed = GLFW.glfwGetKey(mc.method_22683().method_4490(), key) == 1;
               this.peragonActive = pressed;
               if (pressed) {
                  this.peragonEndMs = System.currentTimeMillis() + 100L;
               }

               return this.peragonActive;
            }
         }
      } else {
         long now = System.currentTimeMillis();
         if (this.peragonActive && now > this.peragonEndMs) {
            this.peragonActive = false;
         }

         return this.peragonActive;
      }
   }

   private void startSmartPeragon(class_1309 target) {
      if (this.peragon.isValue()) {
         if (this.peragonMode.isSelected("ÑƒÐ¼Ð½Ñ‹Ð¹")) {
            if (!this.isPeragonActive()) {
               double dist = mc.field_1724.method_19538().method_1022(target.method_19538());
               if (!(dist < (double)2.0F)) {
                  class_243 targetPos = target.method_19538();
                  class_243 playerPos = mc.field_1724.method_19538();
                  float yaw = target.method_36454();
                  float yawRad = yaw * ((float)Math.PI / 180F);
                  class_243 look = new class_243((double)(-class_3532.method_15374(yawRad)), (double)0.0F, (double)class_3532.method_15362(yawRad));
                  class_243 toPlayer = playerPos.method_1020(targetPos).method_1029();
                  double dot = look.method_1029().method_1026(toPlayer);
                  boolean backToMe = dot < (double)0.0F;
                  if (backToMe) {
                     this.peragonActive = true;
                     this.peragonEndMs = System.currentTimeMillis() + 400L;
                  }
               }
            }
         }
      }
   }

   private class_243 getPeragonPoint(class_1309 target) {
      if (!this.isPeragonActive()) {
         return null;
      } else {
         float yaw = target.method_36454();
         float yawRad = yaw * ((float)Math.PI / 180F);
         class_243 look = new class_243((double)(-class_3532.method_15374(yawRad)), (double)0.0F, (double)class_3532.method_15362(yawRad));
         double dist = (double)this.peragonDistance.getValue();
         return target.method_19538().method_1019(look.method_1029().method_1021(dist));
      }
   }

   @EventHandler
   public void onInput(InputEvent event) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         class_1309 target = Aura.getInstance().getTarget();
         if (target != null && target.method_5805()) {
            if (this.mode.isSelected("Grim") || this.mode.isSelected("Vanilla")) {
               if (!this.setting.isSelected("Only Key Pressed") || mc.field_1690.field_1894.method_1434() || mc.field_1690.field_1881.method_1434() || mc.field_1690.field_1913.method_1434() || mc.field_1690.field_1849.method_1434()) {
                  if (this.peragon.isValue() && this.peragonMode.isSelected("ÑƒÐ¼Ð½Ñ‹Ð¹")) {
                     this.startSmartPeragon(target);
                  }

                  class_243 playerPos = mc.field_1724.method_19538();
                  class_243 targetPos = target.method_19538();
                  class_243 peragonPoint = this.getPeragonPoint(target);
                  class_243 nextPoint;
                  if (peragonPoint != null) {
                     nextPoint = new class_243(peragonPoint.field_1352, playerPos.field_1351, peragonPoint.field_1350);
                  } else {
                     int directionMultiplier = 1;
                     if (this.directionMode.isSelected("Counterclockwise")) {
                        directionMultiplier = -1;
                     } else if (this.directionMode.isSelected("Random")) {
                        long time = System.currentTimeMillis() / 3000L;
                        directionMultiplier = time % 2L == 0L ? 1 : -1;
                     }

                     if (this.mode.isSelected("Grim")) {
                        double r = this.applyHitRunRadius((double)this.grimRadius.getValue());
                        if (this.setting.isSelected("In front of the target")) {
                           float targetYaw = target.method_36454();
                           if (this.type.isSelected("Center")) {
                              nextPoint = targetPos.method_1031(-Math.sin(Math.toRadians((double)targetYaw)) * r * (double)directionMultiplier, (double)0.0F, Math.cos(Math.toRadians((double)targetYaw)) * r * (double)directionMultiplier);
                           } else {
                              double offset = Math.cos((double)System.currentTimeMillis() / (double)500.0F) * r * (double)directionMultiplier;
                              nextPoint = targetPos.method_1031(-Math.sin(Math.toRadians((double)targetYaw)) * r + Math.cos(Math.toRadians((double)targetYaw)) * offset, (double)0.0F, Math.cos(Math.toRadians((double)targetYaw)) * r + Math.sin(Math.toRadians((double)targetYaw)) * offset);
                           }
                        } else if (this.type.isSelected("Cube")) {
                           class_243[] points = new class_243[]{new class_243(targetPos.field_1352 - r, playerPos.field_1351, targetPos.field_1350 - r), new class_243(targetPos.field_1352 - r, playerPos.field_1351, targetPos.field_1350 + r), new class_243(targetPos.field_1352 + r, playerPos.field_1351, targetPos.field_1350 + r), new class_243(targetPos.field_1352 + r, playerPos.field_1351, targetPos.field_1350 - r)};
                           if (playerPos.method_1022(points[this.grimPointIndex]) < (double)0.5F) {
                              this.grimPointIndex = (this.grimPointIndex + directionMultiplier + points.length) % points.length;
                           }

                           nextPoint = points[this.grimPointIndex];
                        } else if (this.type.isSelected("Circle")) {
                           double baseAngle = (double)(System.currentTimeMillis() % 3600L) / (double)3600.0F * (double)4.0F * Math.PI;
                           double angle = directionMultiplier > 0 ? baseAngle : (Math.PI * 2D) - baseAngle;
                           nextPoint = new class_243(targetPos.field_1352 + Math.cos(angle) * r, playerPos.field_1351, targetPos.field_1350 + Math.sin(angle) * r);
                        } else {
                           nextPoint = new class_243(targetPos.field_1352, playerPos.field_1351, targetPos.field_1350);
                        }

                        if (this.setting.isSelected("ÐšÐ¾Ñ€Ñ€ÐµÐºÑ†Ð¸Ñ Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ñ") && !this.isPeragonActive()) {
                           double sign = this.movementCorrectionMode.isSelected("Ð¡Ð¿Ð¸Ð½Ð°") ? (double)1.0F : (double)-1.0F;
                           double yawRad = Math.toRadians((double)target.method_36454());
                           double x = targetPos.field_1352 - Math.sin(yawRad) * r * sign;
                           double z = targetPos.field_1350 + Math.cos(yawRad) * r * sign;
                           nextPoint = new class_243(x, playerPos.field_1351, z);
                        }
                     } else {
                        double r = this.applyHitRunRadius((double)this.radius.getValue());
                        if (this.setting.isSelected("ÐšÐ¾Ñ€Ñ€ÐµÐºÑ†Ð¸Ñ Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ñ") && !this.isPeragonActive()) {
                           double sign = this.movementCorrectionMode.isSelected("Ð¡Ð¿Ð¸Ð½Ð°") ? (double)1.0F : (double)-1.0F;
                           double yawRad = Math.toRadians((double)target.method_36454());
                           double x = targetPos.field_1352 - Math.sin(yawRad) * r * sign;
                           double z = targetPos.field_1350 + Math.cos(yawRad) * r * sign;
                           nextPoint = new class_243(x, playerPos.field_1351, z);
                        } else {
                           double angle = Math.atan2(playerPos.field_1350 - targetPos.field_1350, playerPos.field_1352 - targetPos.field_1352);
                           double step = 0.8;
                           angle += (double)directionMultiplier * step / Math.max(playerPos.method_1022(targetPos), r);
                           double x = targetPos.field_1352 + r * Math.cos(angle);
                           double z = targetPos.field_1350 + r * Math.sin(angle);
                           nextPoint = new class_243(x, playerPos.field_1351, z);
                        }
                     }
                  }

                  class_243 direction = nextPoint.method_1020(playerPos).method_1029();
                  float yaw = TurnsConnection.INSTANCE.getRotation().getYaw();
                  float movementAngle = (float)Math.toDegrees(Math.atan2(direction.field_1350, direction.field_1352)) - 90.0F;
                  float angleDiff = class_3532.method_15393(movementAngle - yaw);
                  boolean forward = false;
                  boolean back = false;
                  boolean left = false;
                  boolean right = false;
                  if ((double)angleDiff >= (double)-22.5F && (double)angleDiff < (double)22.5F) {
                     forward = true;
                  } else if ((double)angleDiff >= (double)22.5F && (double)angleDiff < (double)67.5F) {
                     forward = true;
                     right = true;
                  } else if ((double)angleDiff >= (double)67.5F && (double)angleDiff < (double)112.5F) {
                     right = true;
                  } else if ((double)angleDiff >= (double)112.5F && (double)angleDiff < (double)157.5F) {
                     back = true;
                     right = true;
                  } else if ((double)angleDiff >= (double)-67.5F && (double)angleDiff < (double)-22.5F) {
                     forward = true;
                     left = true;
                  } else if ((double)angleDiff >= (double)-112.5F && (double)angleDiff < (double)-67.5F) {
                     left = true;
                  } else if ((double)angleDiff >= (double)-157.5F && (double)angleDiff < (double)-112.5F) {
                     back = true;
                     left = true;
                  } else {
                     back = true;
                  }

                  event.setDirectional(forward, back, left, right);
                  if (this.setting.isSelected("Auto Jump") && mc.field_1724.method_24828()) {
                     event.setJumping(true);
                  }

               }
            }
         }
      }
   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginUltra
   )
   public void onTick(TickEvent event) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         class_1309 target = Aura.getInstance().getTarget();
         if (target != null && target.method_5805()) {
            if (this.mode.isSelected("Matrix") || this.mode.isSelected("Custom")) {
               if (this.peragon.isValue() && this.peragonMode.isSelected("ÑƒÐ¼Ð½Ñ‹Ð¹")) {
                  this.startSmartPeragon(target);
               }

               class_243 playerPos = mc.field_1724.method_19538();
               class_243 targetPos = target.method_19538();
               double r = this.applyHitRunRadius((double)this.radius.getValue());
               if (!this.setting.isSelected("Only Key Pressed") || mc.field_1690.field_1894.method_1434() || mc.field_1690.field_1881.method_1434() || mc.field_1690.field_1913.method_1434() || mc.field_1690.field_1849.method_1434()) {
                  if (this.setting.isSelected("Auto Jump") && mc.field_1724.method_24828()) {
                     mc.field_1724.method_6043();
                  }

                  int directionMultiplier = 1;
                  if (this.directionMode.isSelected("Counterclockwise")) {
                     directionMultiplier = -1;
                  } else if (this.directionMode.isSelected("Random")) {
                     long time = System.currentTimeMillis() / 3000L;
                     directionMultiplier = time % 2L == 0L ? 1 : -1;
                  }

                  double motionSpeed;
                  if (this.mode.isSelected("Matrix")) {
                     motionSpeed = (double)this.speed.getValue();
                  } else if (this.customAffectSpeed.isValue()) {
                     motionSpeed = (double)this.speed.getValue();
                  } else {
                     double vx = mc.field_1724.method_18798().field_1352;
                     double vz = mc.field_1724.method_18798().field_1350;
                     motionSpeed = Math.hypot(vx, vz);
                  }

                  class_243 peragonPoint = this.getPeragonPoint(target);
                  if (peragonPoint != null) {
                     class_243 pp = new class_243(peragonPoint.field_1352, playerPos.field_1351, peragonPoint.field_1350);
                     float yaw = (float)Math.toDegrees(Math.atan2(pp.field_1350 - playerPos.field_1350, pp.field_1352 - playerPos.field_1352)) - 90.0F;
                     mc.field_1724.method_18800(-Math.sin(Math.toRadians((double)yaw)) * motionSpeed, mc.field_1724.method_18798().field_1351, Math.cos(Math.toRadians((double)yaw)) * motionSpeed);
                  } else if (this.setting.isSelected("ÐšÐ¾Ñ€Ñ€ÐµÐºÑ†Ð¸Ñ Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ñ") && !this.isPeragonActive()) {
                     double sign = this.movementCorrectionMode.isSelected("Ð¡Ð¿Ð¸Ð½Ð°") ? (double)1.0F : (double)-1.0F;
                     double yawRad = Math.toRadians((double)target.method_36454());
                     double x = targetPos.field_1352 - Math.sin(yawRad) * r * sign;
                     double z = targetPos.field_1350 + Math.cos(yawRad) * r * sign;
                     float yaw = (float)Math.toDegrees(Math.atan2(z - playerPos.field_1350, x - playerPos.field_1352)) - 90.0F;
                     mc.field_1724.method_18800(-Math.sin(Math.toRadians((double)yaw)) * motionSpeed, mc.field_1724.method_18798().field_1351, Math.cos(Math.toRadians((double)yaw)) * motionSpeed);
                  } else if (this.setting.isSelected("In front of the target")) {
                     float targetYaw = target.method_36454();
                     double x = targetPos.field_1352 - Math.sin(Math.toRadians((double)targetYaw)) * r * (double)directionMultiplier;
                     double z = targetPos.field_1350 + Math.cos(Math.toRadians((double)targetYaw)) * r * (double)directionMultiplier;
                     float yaw = (float)Math.toDegrees(Math.atan2(z - playerPos.field_1350, x - playerPos.field_1352)) - 90.0F;
                     mc.field_1724.method_18800(-Math.sin(Math.toRadians((double)yaw)) * motionSpeed, mc.field_1724.method_18798().field_1351, Math.cos(Math.toRadians((double)yaw)) * motionSpeed);
                  } else {
                     if (this.typeMatrix.isSelected("Cube")) {
                        class_243[] points = new class_243[]{new class_243(targetPos.field_1352 - r, playerPos.field_1351, targetPos.field_1350 - r), new class_243(targetPos.field_1352 - r, playerPos.field_1351, targetPos.field_1350 + r), new class_243(targetPos.field_1352 + r, playerPos.field_1351, targetPos.field_1350 + r), new class_243(targetPos.field_1352 + r, playerPos.field_1351, targetPos.field_1350 - r)};
                        if (playerPos.method_1022(points[this.grimPointIndex]) < (double)0.5F) {
                           this.grimPointIndex = (this.grimPointIndex + directionMultiplier + points.length) % points.length;
                        }

                        class_243 nextPoint = points[this.grimPointIndex];
                        class_243 dirVec = nextPoint.method_1020(playerPos).method_1029();
                        float yaw = (float)Math.toDegrees(Math.atan2(dirVec.field_1350, dirVec.field_1352)) - 90.0F;
                        mc.field_1724.method_18800(-Math.sin(Math.toRadians((double)yaw)) * motionSpeed, mc.field_1724.method_18798().field_1351, Math.cos(Math.toRadians((double)yaw)) * motionSpeed);
                     } else if (this.typeMatrix.isSelected("Circle")) {
                        double angle = Math.atan2(playerPos.field_1350 - targetPos.field_1350, playerPos.field_1352 - targetPos.field_1352);
                        double step;
                        if (!this.mode.isSelected("Matrix") && !this.customAffectSpeed.isValue()) {
                           step = motionSpeed;
                        } else {
                           step = (double)this.speed.getValue();
                        }

                        angle += (double)directionMultiplier * step / Math.max(playerPos.method_1022(targetPos), r);
                        double x = targetPos.field_1352 + r * Math.cos(angle);
                        double z = targetPos.field_1350 + r * Math.sin(angle);
                        float yaw = (float)Math.toDegrees(Math.atan2(z - playerPos.field_1350, x - playerPos.field_1352)) - 90.0F;
                        mc.field_1724.method_18800(-Math.sin(Math.toRadians((double)yaw)) * motionSpeed, mc.field_1724.method_18798().field_1351, Math.cos(Math.toRadians((double)yaw)) * motionSpeed);
                     }

                  }
               }
            }
         }
      }
   }

   public void activate() {
      super.activate();
      this.grimPointIndex = 0;
      this.peragonActive = false;
      this.peragonEndMs = 0L;
   }

   private double applyHitRunRadius(double baseRadius) {
      if (this.hitRunWay.isValue() && mc.field_1724 != null) {
         float cooldown = mc.field_1724.method_7261(0.0F);
         return cooldown >= 1.0F ? baseRadius : Math.max(baseRadius, (double)this.hitRunDistance.getValue());
      } else {
         return baseRadius;
      }
   }
}

