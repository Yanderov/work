package fun.Yanderov.features.impl.movement;

import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BindSetting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import fun.Yanderov.utils.interactions.simulate.Simulations;
import fun.Yanderov.utils.math.time.StopWatch;
import java.util.Objects;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_2828;
import net.minecraft.class_3486;

public class Jesus extends Module {
   private final SelectSetting mode = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ñ€ÐµÐ¶Ð¸Ð¼ Ð¿ÐµÑ€ÐµÐ´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ñ Ð¿Ð¾ Ð²Ð¾Ð´Ðµ")).value("Matrix", "Vanilla", "NCP", "AAC", "AAC3.3.11", "AACFly", "Spartan", "Dolphin", "MetaHvH", "FunTime", "FunSky HVH").selected("Matrix");
   private final StopWatch timer = new StopWatch();
   private boolean isMoving;
   private final float melonBallSpeed = 0.47F;
   private final SliderSettings metaHvhSpeed0 = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ 0", "MetaHvH ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð±ÐµÐ· Speed")).setValue(0.36F).range(0.05F, 2.0F).visible(() -> this.mode.isSelected("MetaHvH"));
   private final SliderSettings metaHvhSpeed1 = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ 1", "MetaHvH ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Speed I")).setValue(0.44F).range(0.05F, 2.0F).visible(() -> this.mode.isSelected("MetaHvH"));
   private final SliderSettings metaHvhSpeed2 = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ 2", "MetaHvH ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Speed II")).setValue(0.52F).range(0.05F, 2.0F).visible(() -> this.mode.isSelected("MetaHvH"));
   private final SliderSettings metaHvhSpeed3 = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ 3", "MetaHvH ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Speed III+")).setValue(0.6F).range(0.05F, 2.0F).visible(() -> this.mode.isSelected("MetaHvH"));
   private final SliderSettings funSpeed = (new SliderSettings("FunSpeed", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ FunTime")).setValue(0.08F).range(0.02F, 0.5F).visible(() -> this.mode.isSelected("FunTime"));
   private final BooleanSetting noJump = (new BooleanSetting("NoJump", "Ð‘Ð»Ð¾ÐºÐ¸Ñ€Ð¾Ð²Ð°Ñ‚ÑŒ Ð¾Ð±Ñ‹Ñ‡Ð½Ñ‹Ð¹ Ð¿Ñ€Ñ‹Ð¶Ð¾Ðº Ð½Ð° Ð²Ð¾Ð´Ðµ")).setValue(false);
   private int ftTickCounter = 0;
   private final BooleanSetting funskyBoost = (new BooleanSetting("FunSky: Boost", "Ð£ÑÐ¸Ð»ÐµÐ½Ð¸Ðµ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚Ð¸ Ð¿Ð¾ ÐºÐ»Ð°Ð²Ð¸ÑˆÐµ")).setValue(false).visible(() -> this.mode.isSelected("FunSky HVH"));
   private final SliderSettings funskyBoostMultiplier = (new SliderSettings("FunSky Boost Multiplier", "ÐšÐ¾ÑÑ„Ñ„Ð¸Ñ†Ð¸ÐµÐ½Ñ‚ ÑƒÑÐ¸Ð»ÐµÐ½Ð¸Ñ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚Ð¸")).setValue(2.0F).range(1.0F, 10.0F).visible(() -> this.mode.isSelected("FunSky HVH") && this.funskyBoost.isValue());
   private final BindSetting funskyBoostKey = (new BindSetting("FunSky Boost Key", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° ÑƒÑÐ¸Ð»ÐµÐ½Ð¸Ñ")).visible(() -> this.mode.isSelected("FunSky HVH") && this.funskyBoost.isValue());
   private final BooleanSetting funskyTeleportBoost = (new BooleanSetting("FunSky: Teleport Boost", "Ð¢ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚-Ð±ÑƒÑÑ‚ Ð¿Ð¾ ÐºÐ»Ð°Ð²Ð¸ÑˆÐµ")).setValue(false).visible(() -> this.mode.isSelected("FunSky HVH"));
   private final SliderSettings funskyTeleportDistance = (new SliderSettings("FunSky Teleport Distance", "Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ñ‚ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚-Ð±ÑƒÑÑ‚Ð°")).setValue(5.0F).range(1.0F, 30.0F).visible(() -> this.mode.isSelected("FunSky HVH") && this.funskyTeleportBoost.isValue());
   private final BindSetting funskyTeleportKey = (new BindSetting("FunSky Teleport Key", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ñ‚ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚-Ð±ÑƒÑÑ‚Ð°")).visible(() -> this.mode.isSelected("FunSky HVH") && this.funskyTeleportBoost.isValue());
   private boolean funskyTeleportKeyLast = false;
   private final BooleanSetting debuffSpeed = (new BooleanSetting("Debuff Speed", "Ð¤Ð¸ÐºÑ. ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ñ€Ð¸ Ð·Ð°Ð¼ÐµÐ´Ð»ÐµÐ½Ð¸Ð¸")).setValue(false).visible(() -> this.mode.isSelected("FunSky HVH"));
   private final SliderSettings debuffSpeedValue = (new SliderSettings("Debuff Speed Value", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ñ€Ð¸ Slowness")).setValue(0.3F).range(0.05F, 1.0F).visible(() -> this.mode.isSelected("FunSky HVH") && this.debuffSpeed.isValue());
   private final BooleanSetting debuffSpeedOff = (new BooleanSetting("Debuff: Off (vanilla)", "Ð˜Ð³Ð½Ð¾Ñ€Ð¸Ñ€Ð¾Ð²Ð°Ñ‚ÑŒ Ð·Ð°Ð´Ð°Ð½Ð½ÑƒÑŽ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ, Ð¾ÑÑ‚Ð°Ð²Ð¸Ñ‚ÑŒ Ð²Ð°Ð½Ð¸Ð»ÑŒ")).setValue(false).visible(() -> this.mode.isSelected("FunSky HVH") && this.debuffSpeed.isValue());
   private final SliderSettings funTimeSpeed = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð² Ð²Ð¾Ð´Ðµ", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð² Ð²Ð¾Ð´Ðµ")).range(1.0F, 1.2F).setValue(1.175F).visible(() -> this.mode.isSelected("FunTime"));
   private final BooleanSetting funTimeOnlyFalling = (new BooleanSetting("Ð¢Ð¾Ð»ÑŒÐºÐ¾ Ð¿Ñ€Ð¸ Ð¿Ð°Ð´ÐµÐ½Ð¸Ð¸", "Ð¢Ð¾Ð»ÑŒÐºÐ¾ Ð¿Ñ€Ð¸ Ð¿Ð°Ð´ÐµÐ½Ð¸Ð¸")).setValue(true).visible(() -> this.mode.isSelected("FunTime"));
   private final BooleanSetting funTimeInLava = (new BooleanSetting("Ð’ Ð»Ð°Ð²Ðµ", "Ð Ð°Ð±Ð¾Ñ‚Ð°ÐµÑ‚ Ð² Ð»Ð°Ð²Ðµ")).setValue(false).visible(() -> this.mode.isSelected("FunTime"));
   private int funTimeWaterTicks = 0;

   public Jesus() {
      super("Jesus", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.mode, this.metaHvhSpeed0, this.metaHvhSpeed1, this.metaHvhSpeed2, this.metaHvhSpeed3, this.funSpeed, this.noJump, this.funskyBoost, this.funskyBoostMultiplier, this.funskyBoostKey, this.funskyTeleportBoost, this.funskyTeleportDistance, this.funskyTeleportKey, this.debuffSpeed, this.debuffSpeedValue, this.debuffSpeedOff, this.funTimeSpeed, this.funTimeOnlyFalling, this.funTimeInLava});
   }

   public void deactivate() {
      if (this.mode.isSelected("Matrix")) {
      }

      this.funTimeWaterTicks = 0;
   }

   @EventHandler
   public void tick(TickEvent event) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         boolean inWater = mc.field_1724.method_5799();
         boolean submerged = mc.field_1724.method_5869();
         boolean inLava = mc.field_1724.method_5771();
         class_2338 posBelow = mc.field_1724.method_24515().method_10074();
         class_2680 stateBelow = mc.field_1687.method_8320(posBelow);
         boolean waterBelow = stateBelow.method_26227().method_15767(class_3486.field_15517);
         if (this.mode.isSelected("Matrix")) {
            if (inWater || inLava) {
               class_1293 speedEffect = mc.field_1724.method_6112(class_1294.field_5904);
               class_1293 slowEffect = mc.field_1724.method_6112(class_1294.field_5909);
               class_1799 offHandItem = mc.field_1724.method_6079();
               String itemName = offHandItem.method_7964().getString();
               float appliedSpeed = 0.0F;
               if (itemName.contains("Ð›Ð¾Ð¼Ñ‚Ð¸Ðº Ð”Ñ‹Ð½Ð¸") && speedEffect != null && speedEffect.method_5578() == 2) {
                  appliedSpeed = 0.49254498F;
               } else if (speedEffect != null) {
                  if (speedEffect.method_5578() == 2) {
                     appliedSpeed = 0.5405F;
                  } else if (speedEffect.method_5578() == 1) {
                     appliedSpeed = 0.47F;
                  } else {
                     appliedSpeed = 0.31960002F;
                  }
               } else {
                  appliedSpeed = 0.31960002F;
               }

               if (slowEffect != null) {
                  appliedSpeed *= 0.85F;
               }

               this.isMoving = mc.field_1690.field_1894.method_1434() || mc.field_1690.field_1881.method_1434() || mc.field_1690.field_1913.method_1434() || mc.field_1690.field_1849.method_1434();
               if (this.isMoving) {
                  Simulations.setVelocity((double)appliedSpeed);
               } else {
                  mc.field_1724.method_18800((double)0.0F, mc.field_1724.method_18798().field_1351, (double)0.0F);
               }

               double yMotion = mc.field_1690.field_1903.method_1434() ? 0.019 : 0.003;
               mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, yMotion, mc.field_1724.method_18798().field_1350);
            }

         } else if (this.mode.isSelected("FunSky HVH")) {
            if (inWater || inLava) {
               class_1293 spd = mc.field_1724.method_6112(class_1294.field_5904);
               class_1293 slow = mc.field_1724.method_6112(class_1294.field_5909);
               boolean hasSpeed = spd != null;
               int amp = 0;

               try {
                  if (hasSpeed) {
                     amp = Math.max(0, spd.method_5578());
                  }
               } catch (Throwable var25) {
               }

               float applied;
               if (!hasSpeed) {
                  applied = 0.11F;
               } else if (amp == 0) {
                  applied = 0.17F;
               } else if (amp == 1) {
                  applied = 0.29F;
               } else {
                  applied = 0.44F;
               }

               try {
                  if (this.debuffSpeed.isValue() && slow != null) {
                     if (this.debuffSpeedOff.isValue()) {
                        return;
                     }

                     applied = this.debuffSpeedValue.getValue();
                  }
               } catch (Throwable var24) {
               }

               if (this.funskyBoost.isValue()) {
                  int code = this.funskyBoostKey.getKey();
                  if (code != -1 && PlayerInteractionHelper.isKey(PlayerInteractionHelper.getKeyType(code), code)) {
                     applied *= this.funskyBoostMultiplier.getValue();
                  }
               }

               if (!this.funskyTeleportBoost.isValue()) {
                  this.funskyTeleportKeyLast = false;
               } else {
                  int tKey = this.funskyTeleportKey.getKey();
                  boolean pressed = tKey != -1 && PlayerInteractionHelper.isKey(PlayerInteractionHelper.getKeyType(tKey), tKey);
                  if (pressed && !this.funskyTeleportKeyLast) {
                     double dist = (double)this.funskyTeleportDistance.getValue();
                     class_243 look = class_243.method_1030(0.0F, mc.field_1724.method_36454()).method_1029();
                     double nx = mc.field_1724.method_23317() + look.field_1352 * dist;
                     double nz = mc.field_1724.method_23321() + look.field_1350 * dist;
                     double ny = mc.field_1724.method_23318();
                     if (mc.method_1562() != null) {
                        mc.method_1562().method_52787(new class_2828.class_2829(nx, ny, nz, mc.field_1724.method_24828(), false));
                     }

                     mc.field_1724.method_30634(nx, ny, nz);
                  }

                  this.funskyTeleportKeyLast = pressed;
               }

               boolean moving = mc.field_1724.field_3913 != null && (mc.field_1724.field_3913.field_3905 != 0.0F || mc.field_1724.field_3913.field_3907 != 0.0F);
               if (moving) {
                  this.applyWaterMove(applied);
               } else {
                  this.setXZ((double)0.0F, (double)0.0F);
               }

               boolean jump = mc.field_1690.field_1903 != null && mc.field_1690.field_1903.method_1434();
               mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, jump ? 0.019 : 0.003, mc.field_1724.method_18798().field_1350);
            }

         } else if (!this.mode.isSelected("NCP") && !this.mode.isSelected("Vanilla")) {
            if (this.mode.isSelected("AAC")) {
               if (!mc.field_1724.method_24828() && (waterBelow || inWater)) {
                  this.dampenXZ(0.99999);
                  mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, (double)0.0F, mc.field_1724.method_18798().field_1350);
                  if (mc.field_1724.field_5976) {
                     double frac = (double)((int)(mc.field_1724.method_23318() - (double)((int)(mc.field_1724.method_23318() - (double)1.0F)))) / (double)8.0F;
                     mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, frac, mc.field_1724.method_18798().field_1350);
                  }

                  if (mc.field_1724.field_6017 >= 4.0F) {
                     mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, -0.004, mc.field_1724.method_18798().field_1350);
                  } else if (inWater) {
                     mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, 0.09, mc.field_1724.method_18798().field_1350);
                  }
               }

            } else if (this.mode.isSelected("Spartan")) {
               if (inWater) {
                  if (mc.field_1724.field_5976) {
                     mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, mc.field_1724.method_18798().field_1351 + 0.15, mc.field_1724.method_18798().field_1350);
                     return;
                  }

                  class_2680 up = mc.field_1687.method_8320(mc.field_1724.method_24515().method_10084());
                  class_2680 upHigh = mc.field_1687.method_8320(class_2338.method_49637(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 1.1, mc.field_1724.method_23321()));
                  boolean upIsWater = up.method_26227().method_15767(class_3486.field_15517);
                  boolean upHighIsWater = upHigh.method_26227().method_15767(class_3486.field_15517);
                  if (upHighIsWater) {
                     mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, 0.1, mc.field_1724.method_18798().field_1350);
                  } else if (upIsWater) {
                     mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, (double)0.0F, mc.field_1724.method_18798().field_1350);
                  }

                  this.setXZ(mc.field_1724.method_18798().field_1352 * 1.085, mc.field_1724.method_18798().field_1350 * 1.085);
               }

            } else if (this.mode.isSelected("AAC3.3.11")) {
               if (inWater) {
                  this.setXZ(mc.field_1724.method_18798().field_1352 * 1.17, mc.field_1724.method_18798().field_1350 * 1.17);
                  if (mc.field_1724.field_5976) {
                     mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, 0.24, mc.field_1724.method_18798().field_1350);
                  } else if (!mc.field_1687.method_8320(mc.field_1724.method_24515().method_10084()).method_26215()) {
                     mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, mc.field_1724.method_18798().field_1351 + 0.04, mc.field_1724.method_18798().field_1350);
                  }
               }

            } else if (this.mode.isSelected("Dolphin")) {
               if (inWater) {
                  mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, mc.field_1724.method_18798().field_1351 + 0.04, mc.field_1724.method_18798().field_1350);
               }

            } else if (this.mode.isSelected("FunTime")) {
               if (inWater || inLava && this.funTimeInLava.isValue()) {
                  ++this.funTimeWaterTicks;
                  if (!this.funTimeOnlyFalling.isValue() || !(mc.field_1724.method_18798().field_1351 >= (double)0.0F)) {
                     this.handleFunTimeSpeed();
                  }
               } else {
                  this.funTimeWaterTicks = 0;
               }
            } else if (!this.mode.isSelected("MetaHvH")) {
               if (this.mode.isSelected("AACFly") && inWater) {
                  double y = (double)0.5F;
                  class_243 v = mc.field_1724.method_18798();
                  mc.field_1724.method_18800(v.field_1352, y, v.field_1350);
               }

            } else {
               if (inWater || inLava) {
                  class_1293 spd = mc.field_1724.method_6112(class_1294.field_5904);
                  class_1293 slow = mc.field_1724.method_6112(class_1294.field_5909);
                  class_1799 off = mc.field_1724.method_6079();
                  String itemName = off != null ? off.method_7964().getString() : "";
                  float melonBall = 0.44F;
                  float applied;
                  if (itemName.contains("Ð›Ð¾Ð¼Ñ‚Ð¸Ðº Ð”Ñ‹Ð½Ð¸") && spd != null && spd.method_5578() == 2) {
                     applied = 0.49254498F;
                  } else if (spd != null) {
                     int amp = spd.method_5578();
                     if (amp == 2) {
                        applied = melonBall * 1.15F;
                     } else if (amp == 1) {
                        applied = melonBall;
                     } else {
                        applied = melonBall * (1.0F + 0.2F * (float)(amp + 1));
                     }
                  } else {
                     applied = melonBall * 0.68F;
                  }

                  if (slow != null) {
                     applied *= 0.85F;
                  }

                  int amp = 0;

                  try {
                     amp = spd != null ? Math.max(0, spd.method_5578()) : 0;
                  } catch (Throwable var26) {
                  }

                  if (spd == null) {
                     applied = this.metaHvhSpeed0.getValue();
                  } else if (amp == 0) {
                     applied = this.metaHvhSpeed1.getValue();
                  } else if (amp == 1) {
                     applied = this.metaHvhSpeed2.getValue();
                  } else {
                     applied = this.metaHvhSpeed3.getValue();
                  }

                  this.applyWaterMove(applied);
                  boolean moving = mc.field_1724.field_3913 != null && (mc.field_1724.field_3913.field_3905 != 0.0F || mc.field_1724.field_3913.field_3907 != 0.0F);
                  if (!moving) {
                     this.setXZ((double)0.0F, (double)0.0F);
                  }

                  boolean jump = mc.field_1690.field_1903 != null && mc.field_1690.field_1903.method_1434();
                  mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, jump ? 0.019 : 0.003, mc.field_1724.method_18798().field_1350);
               }

            }
         } else {
            if ((inWater || waterBelow) && !submerged && !mc.field_1724.method_5715()) {
               mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, 0.08, mc.field_1724.method_18798().field_1350);
            }

         }
      }
   }

   private void handleFunTimeSpeed() {
      float speed = this.funTimeSpeed.getValue();
      class_1293 slow = mc.field_1724.method_6112(class_1294.field_5909);
      if (slow != null) {
         speed = 1.1F;
      }

      class_243 motion = mc.field_1724.method_18798();
      if (motion.field_1351 < (double)0.0F) {
         mc.field_1724.method_18800(motion.field_1352, (double)0.0F, motion.field_1350);
         motion = mc.field_1724.method_18798();
      }

      if (motion.field_1351 == (double)0.0F && !mc.field_1724.method_24828()) {
         mc.field_1724.method_18800(motion.field_1352, 0.001, motion.field_1350);
         motion = mc.field_1724.method_18798();
      }

      mc.field_1724.method_18800(motion.field_1352 * (double)speed, motion.field_1351, motion.field_1350 * (double)speed);
   }

   private void dampenXZ(double mult) {
      this.setXZ(mc.field_1724.method_18798().field_1352 * mult, mc.field_1724.method_18798().field_1350 * mult);
   }

   private void setXZ(double x, double z) {
      mc.field_1724.method_18800(x, mc.field_1724.method_18798().field_1351, z);
   }

   private void applyWaterMove(float speed) {
      if (mc.field_1724.field_3913 != null) {
         float fwd = mc.field_1724.field_3913.field_3905;
         float str = mc.field_1724.field_3913.field_3907;
         if (fwd != 0.0F || str != 0.0F) {
            double yaw = Math.toRadians((double)mc.field_1724.method_36454());
            double sin = Math.sin(yaw);
            double cos = Math.cos(yaw);
            double len = Math.hypot((double)fwd, (double)str);
            if (len > (double)1.0F) {
               fwd = (float)((double)fwd / len);
               str = (float)((double)str / len);
            }

            double vx = ((double)fwd * -sin + (double)str * cos) * (double)speed;
            double vz = ((double)fwd * cos + (double)str * sin) * (double)speed;
            this.setXZ(vx, vz);
         }
      }
   }

   public SelectSetting getMode() {
      return this.mode;
   }

   public StopWatch getTimer() {
      return this.timer;
   }

   public boolean isMoving() {
      return this.isMoving;
   }

   public float getMelonBallSpeed() {
      Objects.requireNonNull(this);
      return 0.47F;
   }

   public SliderSettings getMetaHvhSpeed0() {
      return this.metaHvhSpeed0;
   }

   public SliderSettings getMetaHvhSpeed1() {
      return this.metaHvhSpeed1;
   }

   public SliderSettings getMetaHvhSpeed2() {
      return this.metaHvhSpeed2;
   }

   public SliderSettings getMetaHvhSpeed3() {
      return this.metaHvhSpeed3;
   }

   public SliderSettings getFunSpeed() {
      return this.funSpeed;
   }

   public BooleanSetting getNoJump() {
      return this.noJump;
   }

   public int getFtTickCounter() {
      return this.ftTickCounter;
   }

   public BooleanSetting getFunskyBoost() {
      return this.funskyBoost;
   }

   public SliderSettings getFunskyBoostMultiplier() {
      return this.funskyBoostMultiplier;
   }

   public BindSetting getFunskyBoostKey() {
      return this.funskyBoostKey;
   }

   public BooleanSetting getFunskyTeleportBoost() {
      return this.funskyTeleportBoost;
   }

   public SliderSettings getFunskyTeleportDistance() {
      return this.funskyTeleportDistance;
   }

   public BindSetting getFunskyTeleportKey() {
      return this.funskyTeleportKey;
   }

   public boolean isFunskyTeleportKeyLast() {
      return this.funskyTeleportKeyLast;
   }

   public BooleanSetting getDebuffSpeed() {
      return this.debuffSpeed;
   }

   public SliderSettings getDebuffSpeedValue() {
      return this.debuffSpeedValue;
   }

   public BooleanSetting getDebuffSpeedOff() {
      return this.debuffSpeedOff;
   }

   public SliderSettings getFunTimeSpeed() {
      return this.funTimeSpeed;
   }

   public BooleanSetting getFunTimeOnlyFalling() {
      return this.funTimeOnlyFalling;
   }

   public BooleanSetting getFunTimeInLava() {
      return this.funTimeInLava;
   }

   public int getFunTimeWaterTicks() {
      return this.funTimeWaterTicks;
   }
}

