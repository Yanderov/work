package fun.Yanderov.features.impl.movement;

import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.math.time.StopWatch;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1304;
import net.minecraft.class_1802;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class Fly extends Module {
   private final SelectSetting mode = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ñ€ÐµÐ¶Ð¸Ð¼ Ð¿Ð¾Ð»ÐµÑ‚Ð°")).value("Normal", "Dragon Fly", "MetaHVH", "Grim").selected("Normal");
   private final SliderSettings speedXZ = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ XZ", "Ð“Ð¾Ñ€Ð¸Ð·Ð¾Ð½Ñ‚Ð°Ð»ÑŒÐ½Ð°Ñ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ")).setValue(1.5F).range(1.0F, 10.0F).visible(() -> !this.mode.isSelected("FunTime Up") && !this.mode.isSelected("Grim"));
   private final SliderSettings speedY = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Y", "Ð’ÐµÑ€Ñ‚Ð¸ÐºÐ°Ð»ÑŒÐ½Ð°Ñ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ")).setValue(1.5F).range(0.0F, 10.0F).visible(() -> !this.mode.isSelected("FunTime Up") && !this.mode.isSelected("Grim"));
   private StopWatch timer = new StopWatch();
   private final BooleanSetting mhTimer = (new BooleanSetting("Timer", "Ð£ÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ðµ Ñ‚Ð°Ð¹Ð¼ÐµÑ€Ð¾Ð¼")).setValue(true).visible(() -> this.mode.isSelected("MetaHVH"));
   private final SliderSettings mhTimerSpeed = (new SliderSettings("TimerSpeed", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ñ‚Ð°Ð¹Ð¼ÐµÑ€Ð° (1-10)")).setValue(10.0F).range(1.0F, 10.0F).visible(() -> this.mode.isSelected("MetaHVH") && this.mhTimer.isValue());
   private final BooleanSetting mhPullDown = (new BooleanSetting("PullDown", "ÐŸÑ€Ð¸Ñ‚ÑÐ¶ÐµÐ½Ð¸Ðµ Ð²Ð½Ð¸Ð· Ð½Ð° N-Ð¾Ð¼ Ñ‚Ð¸ÐºÐµ")).setValue(true).visible(() -> this.mode.isSelected("MetaHVH"));
   private final SliderSettings mhMotionMultiplier = (new SliderSettings("MotionMultiplier", "ÐœÐ½Ð¾Ð¶Ð¸Ñ‚ÐµÐ»ÑŒ Ð¿Ñ€Ð¸Ñ‚ÑÐ¶ÐµÐ½Ð¸Ñ")).setValue(1.0F).range(0.01F, 10.0F).visible(() -> this.mode.isSelected("MetaHVH") && this.mhPullDown.isValue());
   private final SliderSettings mhOnTick = (new SliderSettings("OnTick", "Ð¢Ð¸Ðº ÑÑ€Ð°Ð±Ð°Ñ‚Ñ‹Ð²Ð°Ð½Ð¸Ñ Ð¿Ñ€Ð¸Ñ‚ÑÐ¶ÐµÐ½Ð¸Ñ")).setValue(5.0F).range(1.0F, 9.0F).visible(() -> this.mode.isSelected("MetaHVH") && this.mhPullDown.isValue());
   private final BooleanSetting mhOnHurt = (new BooleanSetting("OnHurt", "Ð”Ð¾Ð¿. Ð¿Ñ€Ð¸Ñ‚ÑÐ¶ÐµÐ½Ð¸Ðµ Ð¿Ñ€Ð¸ ÑƒÑ€Ð¾Ð½Ðµ")).setValue(true).visible(() -> this.mode.isSelected("MetaHVH") && this.mhPullDown.isValue());
   private final BooleanSetting mhBoost = (new BooleanSetting("Boost", "Ð¡Ñ‚Ð°Ñ€Ñ‚Ð¾Ð²Ñ‹Ð¹ Ð±ÑƒÑÑ‚ Ð¿Ñ€Ð¸ Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ð¸")).setValue(true).visible(() -> this.mode.isSelected("MetaHVH"));
   private final SliderSettings mhInitialBoostMultiplier = (new SliderSettings("InitialBoostMultiplier", "ÐœÐ½Ð¾Ð¶Ð¸Ñ‚ÐµÐ»ÑŒ ÑÑ‚Ð°Ñ€Ñ‚Ð¾Ð²Ð¾Ð³Ð¾ Ð±ÑƒÑÑ‚Ð°")).setValue(1.0F).range(0.01F, 10.0F).visible(() -> this.mode.isSelected("MetaHVH") && this.mhBoost.isValue());
   private final BooleanSetting mhAirStrafe = (new BooleanSetting("AirStrafe", "ÐœÐ¸Ð½Ð¸Ð¼Ð°Ð»ÑŒÐ½Ð°Ñ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð² Ð²Ð¾Ð·Ð´ÑƒÑ…Ðµ")).setValue(true).visible(() -> this.mode.isSelected("MetaHVH"));
   private final BooleanSetting mhDamageBoost = (new BooleanSetting("DamageBoost", "ÐœÐ¸Ð½Ð¸Ð¼Ð°Ð»ÑŒÐ½Ð°Ñ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ñ€Ð¸ ÑƒÑ€Ð¾Ð½Ðµ")).setValue(true).visible(() -> this.mode.isSelected("MetaHVH"));
   private int ticksInAir = 0;
   private final BooleanSetting grimSyncWithInventoryMove = (new BooleanSetting("ÐžÐ±Ñ…Ð¾Ð´ ScreenWalk", "Ð¡Ð¸Ð½Ñ…Ñ€Ð¾Ð½Ð¸Ð·Ð¸Ñ€Ð¾Ð²Ð°Ñ‚ÑŒ Ñ InventoryMove/ScreenWalk")).setValue(true).visible(() -> this.mode.isSelected("Grim"));

   public static Fly getInstance() {
      return (Fly)Instance.get(Fly.class);
   }

   public Fly() {
      super("Fly", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.mode, this.speedXZ, this.speedY, this.mhTimer, this.mhTimerSpeed, this.mhPullDown, this.mhMotionMultiplier, this.mhOnTick, this.mhOnHurt, this.mhBoost, this.mhInitialBoostMultiplier, this.mhAirStrafe, this.mhDamageBoost, this.grimSyncWithInventoryMove});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.state && mc.field_1724 != null && mc.field_1687 != null) {
         if (this.mode.isSelected("Normal")) {
            double motionY = this.getMotionY();
            this.setMotion(this.speedXZ.getValue());
            class_243 v = mc.field_1724.method_18798();
            mc.field_1724.method_18800(v.field_1352, motionY, v.field_1350);
         } else if (this.mode.isSelected("Dragon Fly")) {
            if (mc.field_1724.method_31549().field_7479) {
               this.setMotion(this.speedXZ.getValue());
               double motionY = (double)0.0F;
               if (mc.field_1690.field_1903.method_1434()) {
                  motionY = (double)this.speedY.getValue();
               }

               if (mc.field_1690.field_1832.method_1434()) {
                  motionY = (double)(-this.speedY.getValue());
               }

               class_243 v = mc.field_1724.method_18798();
               mc.field_1724.method_18800(v.field_1352, motionY, v.field_1350);
            }
         } else if (this.mode.isSelected("MetaHVH")) {
            if (this.mhTimer.isValue()) {
               float sp = Math.min(10.0F, this.mhTimerSpeed.getValue());
               float factor = 1.0F + (sp - 1.0F) * 0.02F;
               class_243 v = mc.field_1724.method_18798();
               mc.field_1724.method_18800(v.field_1352 * (double)factor, v.field_1351, v.field_1350 * (double)factor);
            }

            boolean moving = mc.field_1724.field_6250 != 0.0F || mc.field_1724.field_6212 != 0.0F;
            if (mc.field_1724.method_24828()) {
               this.ticksInAir = 0;
            } else {
               ++this.ticksInAir;
               if (this.mhPullDown.isValue()) {
                  int onTick = Math.max(1, Math.min(9, Math.round(this.mhOnTick.getValue())));
                  if (this.ticksInAir == onTick) {
                     double delta = 0.1523351824467155 * (double)this.mhMotionMultiplier.getValue();
                     mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, mc.field_1724.method_18798().field_1351 - delta, mc.field_1724.method_18798().field_1350);
                  }

                  if (this.mhOnHurt.isValue() && mc.field_1724.field_6235 >= 5 && mc.field_1724.method_18798().field_1351 >= (double)0.0F) {
                     mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, mc.field_1724.method_18798().field_1351 - 0.1, mc.field_1724.method_18798().field_1350);
                  }
               }
            }

            if (moving && this.mhBoost.isValue()) {
               double BOOST_CONSTANT = 0.00718;
               double mul = (double)1.0F + 0.00718 * (double)this.mhInitialBoostMultiplier.getValue();
               class_243 v = mc.field_1724.method_18798();
               mc.field_1724.method_18800(v.field_1352 * mul, v.field_1351, v.field_1350 * mul);
            }

            int speedAmp = 0;

            try {
               class_1293 inst = mc.field_1724.method_6112(class_1294.field_5904);
               if (inst != null) {
                  speedAmp = inst.method_5578();
               }
            } catch (Throwable var20) {
            }

            double SPEED_CONSTANT = 0.199999999;
            double GROUND_CONSTANT = 0.281;
            double AIR_CONSTANT = 0.2;
            double vx = mc.field_1724.method_18798().field_1352;
            double vz = mc.field_1724.method_18798().field_1350;
            double horiz = Math.sqrt(vx * vx + vz * vz);
            if (moving) {
               if (mc.field_1724.method_24828()) {
                  double groundMin = 0.281 + 0.199999999 * (double)speedAmp;
                  if (horiz < groundMin && horiz > 1.0E-6) {
                     double scale = groundMin / horiz;
                     mc.field_1724.method_18800(vx * scale, mc.field_1724.method_18798().field_1351, vz * scale);
                  }
               } else if (this.mhAirStrafe.isValue()) {
                  double airMin = 0.2 + 0.199999999 * (double)speedAmp;
                  vx = mc.field_1724.method_18798().field_1352;
                  vz = mc.field_1724.method_18798().field_1350;
                  horiz = Math.sqrt(vx * vx + vz * vz);
                  if (horiz < airMin && horiz > 1.0E-6) {
                     double scale = airMin / horiz;
                     mc.field_1724.method_18800(vx * scale, mc.field_1724.method_18798().field_1351, vz * scale);
                  }
               }
            }

            if (mc.field_1724.field_6235 >= 1 && this.mhDamageBoost.isValue()) {
               vx = mc.field_1724.method_18798().field_1352;
               vz = mc.field_1724.method_18798().field_1350;
               horiz = Math.sqrt(vx * vx + vz * vz);
               double minHurt = (double)0.5F;
               if (horiz < minHurt && horiz > 1.0E-6) {
                  double scale = minHurt / horiz;
                  mc.field_1724.method_18800(vx * scale, mc.field_1724.method_18798().field_1351, vz * scale);
               }
            }
         } else if (this.mode.isSelected("Grim")) {
            if (mc.field_1724.method_24828() || mc.field_1724.method_5799()) {
               this.setState(false);
               return;
            }

            if (!mc.field_1724.method_6118(class_1304.field_6174).method_31574(class_1802.field_8833)) {
               return;
            }

            class_243 v = mc.field_1724.method_18798();
            mc.field_1724.method_18800(v.field_1352, (double)0.5F, v.field_1350);
         }

      }
   }

   private double getMotionY() {
      if (mc.field_1690.field_1832.method_1434()) {
         return (double)(-this.speedY.getValue());
      } else {
         return mc.field_1690.field_1903.method_1434() ? (double)this.speedY.getValue() : (double)0.0F;
      }
   }

   private void setMotion(float speed) {
      float yaw = mc.field_1724.method_36454();
      float f = mc.field_1724.field_6250;
      float s = mc.field_1724.field_6212;
      float speedScale = speed / 3.0F;
      double x = (double)0.0F;
      double z = (double)0.0F;
      if (f != 0.0F || s != 0.0F) {
         float yawRad = yaw * ((float)Math.PI / 180F);
         x = (double)(-class_3532.method_15374(yawRad) * speedScale * f + class_3532.method_15362(yawRad) * speedScale * s);
         z = (double)(class_3532.method_15362(yawRad) * speedScale * f + class_3532.method_15374(yawRad) * speedScale * s);
      }

      mc.field_1724.method_18800(x, mc.field_1724.method_18798().field_1351, z);
   }

   public void deactivate() {
      super.deactivate();
      this.timer.reset();
   }

   public SelectSetting getMode() {
      return this.mode;
   }

   public SliderSettings getSpeedXZ() {
      return this.speedXZ;
   }

   public SliderSettings getSpeedY() {
      return this.speedY;
   }

   public StopWatch getTimer() {
      return this.timer;
   }

   public BooleanSetting getMhTimer() {
      return this.mhTimer;
   }

   public SliderSettings getMhTimerSpeed() {
      return this.mhTimerSpeed;
   }

   public BooleanSetting getMhPullDown() {
      return this.mhPullDown;
   }

   public SliderSettings getMhMotionMultiplier() {
      return this.mhMotionMultiplier;
   }

   public SliderSettings getMhOnTick() {
      return this.mhOnTick;
   }

   public BooleanSetting getMhOnHurt() {
      return this.mhOnHurt;
   }

   public BooleanSetting getMhBoost() {
      return this.mhBoost;
   }

   public SliderSettings getMhInitialBoostMultiplier() {
      return this.mhInitialBoostMultiplier;
   }

   public BooleanSetting getMhAirStrafe() {
      return this.mhAirStrafe;
   }

   public BooleanSetting getMhDamageBoost() {
      return this.mhDamageBoost;
   }

   public int getTicksInAir() {
      return this.ticksInAir;
   }

   public BooleanSetting getGrimSyncWithInventoryMove() {
      return this.grimSyncWithInventoryMove;
   }
}

