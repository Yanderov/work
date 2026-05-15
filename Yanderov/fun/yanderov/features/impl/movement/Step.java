package fun.Yanderov.features.impl.movement;

import fun.Yanderov.display.hud.Notifications;
import fun.Yanderov.events.player.MoveEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2261;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3414;
import net.minecraft.class_2350.class_2351;

public class Step extends Module implements QuickImports {
   public static boolean holeTick;
   public static int holeTicks;
   private long lastMatrixStepMs = 0L;
   private final BooleanSetting anchorHole = (new BooleanSetting("AnchoorHole", "ÐžÑÑ‚Ð°Ð½Ð°Ð²Ð»Ð¸Ð²Ð°Ñ‚ÑŒÑÑ Ð² ÑÐ¼Ðµ")).setValue(true);
   private final BooleanSetting reverseStep = (new BooleanSetting("ReverseStep", "Ð ÐµÐ²ÐµÑ€Ñ-ÑÑ‚ÐµÐ¿")).setValue(false);
   private final BooleanSetting stepHeight = (new BooleanSetting("StepHeight", "Ð¨Ð°Ð³")).setValue(true);
   private final BooleanSetting pauseInLiquids = (new BooleanSetting("PauseInLiquids", "ÐŸÐ°ÑƒÐ·Ð° Ð² Ð²Ð¾Ð´Ðµ/Ð»Ð°Ð²Ðµ")).setValue(false);
   private final SelectSetting stepHeightMode = (new SelectSetting("StepMode", "Ð ÐµÐ¶Ð¸Ð¼ ÑˆÐ°Ð³Ð°")).value("Vanilla", "Matrix").selected("Vanilla").visible(() -> this.stepHeight.isValue());

   public Step() {
      super("Step", "Step", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.anchorHole, this.reverseStep, this.stepHeight, this.pauseInLiquids, this.stepHeightMode});
   }

   public void onDisable() {
      this.resetStepHeight();
   }

   @EventHandler
   public void onMove(MoveEvent event) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (!this.pauseInLiquids.isValue() || !mc.field_1724.method_5799() && !mc.field_1724.method_5771()) {
            if (!this.anchorHole.isValue() && !this.reverseStep.isValue() && !this.stepHeight.isValue()) {
               this.setState(false);
               Notifications.getInstance().addList((String)"Step: Ñ…Ð¾Ñ‚Ñ Ð±Ñ‹ Ð¾Ð´Ð¸Ð½ Ñ€ÐµÐ¶Ð¸Ð¼ Ð´Ð¾Ð»Ð¶ÐµÐ½ Ð±Ñ‹Ñ‚ÑŒ Ð²ÐºÐ»ÑŽÑ‡Ñ‘Ð½", 3000L, (class_3414)null);
            } else {
               if (this.anchorHole.isValue()) {
                  holeTick = false;
                  float w = mc.field_1724.method_17681() / 2.0F;
                  double x = mc.field_1724.method_23317();
                  double y = mc.field_1724.method_23318() + (mc.field_1724.field_6036 - mc.field_1724.method_23318()) / (double)2.0F;
                  double z = mc.field_1724.method_23321();
                  if ((this.isHoledPosFull(class_2338.method_49637(x, y, z)) || this.isHoledPosFull(class_2338.method_49637(x, y - (double)1.0F, z)) || this.isHoledPosFull(class_2338.method_49637(x, y - 1.3, z))) && getBlockFullWithExpand(w, x, y - (double)1.0F, z, class_2246.field_10124) && getBlockFullWithExpand(w, x, y - 1.3, z, class_2246.field_10124)) {
                     Step.MoveUtility.setSpeed((double)0.0F);
                     Step.MoveUtility.setCuttingSpeed((double)0.0F);
                     if (this.reverseStep.isValue() && this.stepHeight.isValue()) {
                        mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, (double)-3.0F, mc.field_1724.method_18798().field_1350);
                        holeTicks = 0;
                     } else {
                        holeTicks = -10;
                     }

                     holeTick = true;
                  }
               }

               if (this.stepHeight.isValue() && this.stepHeightMode.isSelected("Matrix") && !mc.field_1724.method_5715() && mc.field_1724.field_5976 && Step.MoveUtility.isMoving() && mc.field_1724.method_24828() && !mc.field_1690.field_1903.method_1434()) {
                  long now = System.currentTimeMillis();
                  if (now - this.lastMatrixStepMs > 180L) {
                     class_2350 moveDir = this.getMovementDirection();
                     if (moveDir == null) {
                        moveDir = mc.field_1724.method_5735();
                     }

                     double actualH = this.getObstacleHeightAhead(moveDir, 8);
                     if (actualH > (double)0.0F && actualH <= (double)4.0F) {
                        if (this.canFitAtHeight(actualH)) {
                           mc.field_1724.method_30634(mc.field_1724.method_23317(), mc.field_1724.method_23318() + actualH, mc.field_1724.method_23321());
                           mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, (double)0.0F, mc.field_1724.method_18798().field_1350);
                           this.lastMatrixStepMs = now;
                        }
                     } else if (actualH > (double)4.0F) {
                        this.lastMatrixStepMs = now;
                     }
                  }
               }

               if (this.reverseStep.isValue() && mc.field_1724.method_24828() && mc.field_1724.field_5992 && mc.field_1724.method_18798().field_1351 < (double)0.0F && !posBlock(mc.field_1724.method_23317(), mc.field_1724.method_23318() - 0.6, mc.field_1724.method_23321()) && getDistanceToFall() < (double)4.0F && !mc.field_1724.method_5715()) {
                  mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, (double)-3.0F, mc.field_1724.method_18798().field_1350);
                  holeTicks = 0;
               }

               if (this.stepHeight.isValue() && this.stepHeightMode.isSelected("Vanilla")) {
                  if (!mc.field_1724.method_5715() && Step.MoveUtility.moveKeysPressed()) {
                     ++holeTicks;
                  }

                  if (holeTicks > 5 && !mc.field_1724.method_5715() && Step.MoveUtility.moveKeysPressed()) {
                  }
               }

            }
         } else {
            this.resetStepHeight();
         }
      }
   }

   private static boolean getBlockFullWithExpand(float expand, double x, double y, double z, class_2248 block) {
      class_2338 pos = class_2338.method_49637(x, y, z);
      return mc.field_1687.method_8320(pos).method_26204() == block;
   }

   private static double getDistanceToFall() {
      for(int i = 0; i < 500; ++i) {
         if (posBlock(mc.field_1724.method_23317(), mc.field_1724.method_23318() - (double)i, mc.field_1724.method_23321())) {
            return (double)i;
         }
      }

      return (double)0.0F;
   }

   private static boolean posBlock(double x, double y, double z) {
      class_2248 block = mc.field_1687.method_8320(class_2338.method_49637(x, y, z)).method_26204();
      return block != class_2246.field_10124 && block != class_2246.field_10382 && block != class_2246.field_10164 && block != class_2246.field_10183 && block != class_2246.field_10214 && block != class_2246.field_10494 && block != class_2246.field_10495 && block != class_2246.field_10528 && block != class_2246.field_10597 && block != class_2246.field_10144 && block != class_2246.field_10457 && block != class_2246.field_10299 && block != class_2246.field_10513 && block != class_2246.field_10132 && block != class_2246.field_10196 && block != class_2246.field_10319 && block != class_2246.field_10041 && block != class_2246.field_10364 && block != class_2246.field_10620 && block != class_2246.field_10188 && block != class_2246.field_10020 && block != class_2246.field_10291 && block != class_2246.field_10485 && block != class_2246.field_10398 && block != class_2246.field_10429 && block != class_2246.field_10175 && block != class_2246.field_10454 && block != class_2246.field_10091 && block != class_2246.field_10336 && block != class_2246.field_10091;
   }

   private boolean isHoled(class_2338 position) {
      return this.isCurrentBlock(position.method_10069(1, 0, 0)) && this.isCurrentBlock(position.method_10069(-1, 0, 0)) && this.isCurrentBlock(position.method_10069(0, 0, 1)) && this.isCurrentBlock(position.method_10069(0, 0, -1)) && posBlock((double)position.method_10069(0, -1, 0).method_10263(), (double)position.method_10069(0, -1, 0).method_10264(), (double)position.method_10069(0, -1, 0).method_10260()) && this.getBlock(position) == class_2246.field_10124 && this.getBlock(position.method_10084()) == class_2246.field_10124 && this.getBlock(position.method_10086(2)) == class_2246.field_10124;
   }

   private boolean isHoledPosFull(class_2338 pos) {
      return this.isHoled(new class_2338(pos.method_10263(), pos.method_10264(), pos.method_10260())) && !posBlock((double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260());
   }

   private boolean isCurrentBlock(class_2338 pos) {
      return mc.field_1687.method_8320(pos).method_26204() == class_2246.field_10124;
   }

   private class_2248 getBlock(class_2338 pos) {
      return mc.field_1687.method_8320(pos).method_26204();
   }

   private double getObstacleHeightAhead(class_2350 facing, int maxUp) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         class_2338 base = class_2338.method_49637(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321());
         class_2338 front = base.method_10093(facing);
         double sum = (double)0.0F;
         boolean started = false;

         for(int i = 0; i < maxUp; ++i) {
            class_2338 p = front.method_10086(i);
            class_2680 state = mc.field_1687.method_8320(p);
            if (started || !this.isIgnorableForStep(state, p)) {
               double hLocal = this.getCollisionHeight(state, p);
               if (hLocal <= (double)0.0F) {
                  if (started) {
                     break;
                  }
               } else {
                  started = true;
                  sum += hLocal;
                  if (sum > (double)4.0F) {
                     return sum;
                  }
               }
            }
         }

         return sum;
      } else {
         return (double)0.0F;
      }
   }

   private double getCollisionHeight(class_2680 state, class_2338 pos) {
      class_265 shape = state.method_26220(mc.field_1687, pos);
      if (shape.method_1110()) {
         return (double)0.0F;
      } else {
         double maxY = shape.method_1105(class_2351.field_11052);
         if (!Double.isNaN(maxY) && !(maxY <= (double)0.0F)) {
            if (maxY > (double)1.0F) {
               maxY = (double)1.0F;
            }

            return maxY;
         } else {
            return (double)0.0F;
         }
      }
   }

   private boolean isIgnorableForStep(class_2680 state, class_2338 pos) {
      if (state.method_26215()) {
         return true;
      } else if (!state.method_26227().method_15769()) {
         return true;
      } else {
         class_2248 b = state.method_26204();
         if (b instanceof class_2261) {
            return true;
         } else {
            class_265 shape = state.method_26220(mc.field_1687, pos);
            if (!shape.method_1110()) {
               double maxY = shape.method_1105(class_2351.field_11052);
               if (!Double.isNaN(maxY) && maxY <= 0.0626) {
                  return true;
               }
            }

            return b == class_2246.field_10214 || b == class_2246.field_10112 || b == class_2246.field_10313 || b == class_2246.field_10428 || b == class_2246.field_10376 || b == class_2246.field_10238 || b == class_2246.field_10091 || b == class_2246.field_10336 || b == class_2246.field_10099 || b == class_2246.field_22092 || b == class_2246.field_22093 || b == class_2246.field_10597 || b == class_2246.field_10477 || b == class_2246.field_9983 || b == class_2246.field_10343 || b == class_2246.field_10494 || b == class_2246.field_23864 || b == class_2246.field_10363 || b == class_2246.field_10495 || b == class_2246.field_10183;
         }
      }
   }

   private boolean canFitAtHeight(double h) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         class_238 upBox = mc.field_1724.method_5829().method_989((double)0.0F, h - 0.001, (double)0.0F);
         return mc.field_1687.method_8587(mc.field_1724, upBox);
      } else {
         return false;
      }
   }

   private void setPlayerStepHeight(float value) {
   }

   private void resetStepHeight() {
   }

   private class_2350 getMovementDirection() {
      if (mc.field_1724 != null && mc.field_1690 != null) {
         boolean f = mc.field_1690.field_1894.method_1434();
         boolean b = mc.field_1690.field_1881.method_1434();
         boolean l = mc.field_1690.field_1913.method_1434();
         boolean r = mc.field_1690.field_1849.method_1434();
         double vx = (double)0.0F;
         double vz = (double)0.0F;
         if (!f && !b && !l && !r) {
            class_243 vel = mc.field_1724.method_18798();
            vx = vel.field_1352;
            vz = vel.field_1350;
            if (Math.abs(vx) < 0.001 && Math.abs(vz) < 0.001) {
               return null;
            }
         } else {
            double yaw = Math.toRadians((double)mc.field_1724.method_36454());
            double fx = -Math.sin(yaw);
            double fz = Math.cos(yaw);
            double lx = Math.cos(yaw);
            double lz = Math.sin(yaw);
            if (f) {
               vx += fx;
               vz += fz;
            }

            if (b) {
               vx -= fx;
               vz -= fz;
            }

            if (l) {
               vx += lx;
               vz += lz;
            }

            if (r) {
               vx -= lx;
               vz -= lz;
            }
         }

         if (Math.abs(vx) > Math.abs(vz)) {
            return vx > (double)0.0F ? class_2350.field_11034 : class_2350.field_11039;
         } else {
            return vz > (double)0.0F ? class_2350.field_11035 : class_2350.field_11043;
         }
      } else {
         return null;
      }
   }

   private static class MoveUtility {
      private static void setSpeed(double speed) {
         if (QuickImports.mc.field_1724 != null) {
            QuickImports.mc.field_1724.method_18800((double)0.0F, QuickImports.mc.field_1724.method_18798().field_1351, (double)0.0F);
         }
      }

      private static void setCuttingSpeed(double speed) {
         setSpeed(speed);
      }

      private static boolean isMoving() {
         return moveKeysPressed();
      }

      private static boolean moveKeysPressed() {
         if (QuickImports.mc.field_1690 == null) {
            return false;
         } else {
            return QuickImports.mc.field_1690.field_1894.method_1434() || QuickImports.mc.field_1690.field_1881.method_1434() || QuickImports.mc.field_1690.field_1913.method_1434() || QuickImports.mc.field_1690.field_1849.method_1434();
         }
      }

      private static double getMotionYaw() {
         return QuickImports.mc.field_1724 == null ? (double)0.0F : (double)QuickImports.mc.field_1724.method_36454();
      }
   }
}

