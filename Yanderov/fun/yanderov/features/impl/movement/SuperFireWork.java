package fun.Yanderov.features.impl.movement;

import antidaunleak.api.annotation.Native;
import fun.Yanderov.events.player.FireworkEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.features.aura.warp.TurnsConnection;
import net.minecraft.class_243;
import net.minecraft.class_310;

public class SuperFireWork extends Module {
   private final SelectSetting modeSetting = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ñ‚Ð¸Ð¿ Ñ€ÐµÐ¶Ð¸Ð¼Ð°")).value("Grim", "BravoHvH", "Custom", "ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ñ‹Ð¹");
   private final SliderSettings speedSetting = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ð¾Ð»ÐµÑ‚Ð° Ñ„ÐµÐ¹ÐµÑ€Ð²ÐµÑ€ÐºÐ°")).range(1.0F, 50.0F).setValue(20.0F).visible(() -> this.modeSetting.isSelected("Custom"));
   private final BooleanSetting maxspeed = (new BooleanSetting("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ð¾ Ð£Ð³Ð»Ð°Ð¼", "Ð’ÐºÐ»ÑŽÑ‡Ð¸Ñ‚ÑŒ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ð¾ ÑƒÐ³Ð»Ð°Ð¼")).visible(() -> this.modeSetting.isSelected("ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ñ‹Ð¹"));
   private final SliderSettings speedxz = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ XZ", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ð¾ Ð³Ð¾Ñ€Ð¸Ð·Ð¾Ð½Ñ‚Ð°Ð»Ð¸")).range(0.0F, 2.5F).setValue(1.65F).visible(() -> this.modeSetting.isSelected("ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ñ‹Ð¹") && !this.maxspeed.isValue());
   private final SliderSettings speedy = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Y", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ð¾ Ð²ÐµÑ€Ñ‚Ð¸ÐºÐ°Ð»Ð¸")).range(0.0F, 2.5F).setValue(1.59F).visible(() -> this.modeSetting.isSelected("ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ñ‹Ð¹") && !this.maxspeed.isValue());
   private final SliderSettings speed5 = (new SliderSettings("Ð£Ð³Ð¾Ð» 0-5", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ñ€Ð¸ ÑƒÐ³Ð»Ðµ 0-5 Ð³Ñ€Ð°Ð´ÑƒÑÐ¾Ð²")).range(0.0F, 2.5F).setValue(1.6F).visible(() -> this.modeSetting.isSelected("ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ñ‹Ð¹") && this.maxspeed.isValue());
   private final SliderSettings speed10 = (new SliderSettings("Ð£Ð³Ð¾Ð» 5-10", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ñ€Ð¸ ÑƒÐ³Ð»Ðµ 5-10 Ð³Ñ€Ð°Ð´ÑƒÑÐ¾Ð²")).range(0.0F, 2.5F).setValue(1.62F).visible(() -> this.modeSetting.isSelected("ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ñ‹Ð¹") && this.maxspeed.isValue());
   private final SliderSettings speed15 = (new SliderSettings("Ð£Ð³Ð¾Ð» 10-15", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ñ€Ð¸ ÑƒÐ³Ð»Ðµ 10-15 Ð³Ñ€Ð°Ð´ÑƒÑÐ¾Ð²")).range(0.0F, 2.5F).setValue(1.65F).visible(() -> this.modeSetting.isSelected("ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ñ‹Ð¹") && this.maxspeed.isValue());
   private final SliderSettings speed20 = (new SliderSettings("Ð£Ð³Ð¾Ð» 15-20", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ñ€Ð¸ ÑƒÐ³Ð»Ðµ 15-20 Ð³Ñ€Ð°Ð´ÑƒÑÐ¾Ð²")).range(0.0F, 2.5F).setValue(1.68F).visible(() -> this.modeSetting.isSelected("ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ñ‹Ð¹") && this.maxspeed.isValue());
   private final SliderSettings speed25 = (new SliderSettings("Ð£Ð³Ð¾Ð» 20-25", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ñ€Ð¸ ÑƒÐ³Ð»Ðµ 20-25 Ð³Ñ€Ð°Ð´ÑƒÑÐ¾Ð²")).range(0.0F, 2.5F).setValue(1.74F).visible(() -> this.modeSetting.isSelected("ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ñ‹Ð¹") && this.maxspeed.isValue());
   private final SliderSettings speed30 = (new SliderSettings("Ð£Ð³Ð¾Ð» 25-30", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ñ€Ð¸ ÑƒÐ³Ð»Ðµ 25-30 Ð³Ñ€Ð°Ð´ÑƒÑÐ¾Ð²")).range(0.0F, 2.5F).setValue(1.8F).visible(() -> this.modeSetting.isSelected("ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ñ‹Ð¹") && this.maxspeed.isValue());
   private final SliderSettings speed35 = (new SliderSettings("Ð£Ð³Ð¾Ð» 30-35", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ñ€Ð¸ ÑƒÐ³Ð»Ðµ 30-35 Ð³Ñ€Ð°Ð´ÑƒÑÐ¾Ð²")).range(0.0F, 2.5F).setValue(1.8F).visible(() -> this.modeSetting.isSelected("ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ñ‹Ð¹") && this.maxspeed.isValue());
   private final SliderSettings speed40 = (new SliderSettings("Ð£Ð³Ð¾Ð» 35-40", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ñ€Ð¸ ÑƒÐ³Ð»Ðµ 35-40 Ð³Ñ€Ð°Ð´ÑƒÑÐ¾Ð²")).range(0.0F, 2.5F).setValue(1.8F).visible(() -> this.modeSetting.isSelected("ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ñ‹Ð¹") && this.maxspeed.isValue());
   private final SliderSettings speed5y = (new SliderSettings("Ð£Ð³oÐ» 0-5", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Y Ð¿Ñ€Ð¸ ÑƒÐ³Ð»Ðµ 0-5 Ð³Ñ€Ð°Ð´ÑƒÑÐ¾Ð²")).range(0.0F, 2.5F).setValue(1.59F).visible(() -> this.modeSetting.isSelected("ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ñ‹Ð¹") && this.maxspeed.isValue());
   private final SliderSettings speed10y = (new SliderSettings("Ð£Ð³oÐ» 5-10", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Y Ð¿Ñ€Ð¸ ÑƒÐ³Ð»Ðµ 5-10 Ð³Ñ€Ð°Ð´ÑƒÑÐ¾Ð²")).range(0.0F, 2.5F).setValue(1.6F).visible(() -> this.modeSetting.isSelected("ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ñ‹Ð¹") && this.maxspeed.isValue());
   private final SliderSettings speed15y = (new SliderSettings("Ð£Ð³oÐ» 10-15", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Y Ð¿Ñ€Ð¸ ÑƒÐ³Ð»Ðµ 10-15 Ð³Ñ€Ð°Ð´ÑƒÑÐ¾Ð²")).range(0.0F, 2.5F).setValue(1.61F).visible(() -> this.modeSetting.isSelected("ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ñ‹Ð¹") && this.maxspeed.isValue());
   private final SliderSettings speed20y = (new SliderSettings("Ð£Ð³oÐ» 15-20", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Y Ð¿Ñ€Ð¸ ÑƒÐ³Ð»Ðµ 15-20 Ð³Ñ€Ð°Ð´ÑƒÑÐ¾Ð²")).range(0.0F, 2.5F).setValue(1.62F).visible(() -> this.modeSetting.isSelected("ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ñ‹Ð¹") && this.maxspeed.isValue());
   private final SliderSettings speed25y = (new SliderSettings("Ð£Ð³oÐ» 20-25", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Y Ð¿Ñ€Ð¸ ÑƒÐ³Ð»Ðµ 20-25 Ð³Ñ€Ð°Ð´ÑƒÑÐ¾Ð²")).range(0.0F, 2.5F).setValue(1.68F).visible(() -> this.modeSetting.isSelected("ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ñ‹Ð¹") && this.maxspeed.isValue());
   private final SliderSettings speed30y = (new SliderSettings("Ð£Ð³oÐ» 25-30", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Y Ð¿Ñ€Ð¸ ÑƒÐ³Ð»Ðµ 25-30 Ð³Ñ€Ð°Ð´ÑƒÑÐ¾Ð²")).range(0.0F, 2.5F).setValue(1.74F).visible(() -> this.modeSetting.isSelected("ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ñ‹Ð¹") && this.maxspeed.isValue());
   private final SliderSettings speed35y = (new SliderSettings("Ð£Ð³oÐ» 30-35", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Y Ð¿Ñ€Ð¸ ÑƒÐ³Ð»Ðµ 30-35 Ð³Ñ€Ð°Ð´ÑƒÑÐ¾Ð²")).range(0.0F, 2.5F).setValue(1.95F).visible(() -> this.modeSetting.isSelected("ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ñ‹Ð¹") && this.maxspeed.isValue());
   private final SliderSettings speed40y = (new SliderSettings("Ð£Ð³oÐ» 35-40", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Y Ð¿Ñ€Ð¸ ÑƒÐ³Ð»Ðµ 35-40 Ð³Ñ€Ð°Ð´ÑƒÑÐ¾Ð²")).range(0.0F, 2.5F).setValue(2.0F).visible(() -> this.modeSetting.isSelected("ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ñ‹Ð¹") && this.maxspeed.isValue());
   private final BooleanSetting speedLimit = (new BooleanSetting("Ð›Ð¸Ð¼Ð¸Ñ‚ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚Ð¸", "ÐžÐ³Ñ€Ð°Ð½Ð¸Ñ‡Ð¸Ð²Ð°Ñ‚ÑŒ Ð¾Ð±Ñ‰ÑƒÑŽ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ñ€Ð¸ Ð¿Ð¾Ð»Ñ‘Ñ‚Ðµ")).visible(() -> this.modeSetting.isSelected("Custom"));
   private final SliderSettings maxSpeed = (new SliderSettings("ÐœÐ°ÐºÑ. ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ", "ÐœÐ°ÐºÑÐ¸Ð¼Ð°Ð»ÑŒÐ½Ð°Ñ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ð¾Ð»Ñ‘Ñ‚Ð° Ñ Ñ„ÐµÐ¹ÐµÑ€Ð²ÐµÑ€ÐºÐ°Ð¼Ð¸")).range(0.1F, 5.0F).setValue(1.5F).visible(() -> this.modeSetting.isSelected("Custom") && this.speedLimit.isValue());
   private final BooleanSetting limitModeBlock = (new BooleanSetting("Ð‘Ð»Ð¾ÐºÐ¸Ñ€Ð¾Ð²Ð°Ñ‚ÑŒ Ñ„ÐµÐ¹ÐµÑ€Ð²ÐµÑ€Ðº", "ÐÐµ Ð´Ð°Ð²Ð°Ñ‚ÑŒ Ñ„ÐµÐ¹ÐµÑ€Ð²ÐµÑ€ÐºÐ°Ð¼ Ñ€Ð°Ð±Ð¾Ñ‚Ð°Ñ‚ÑŒ, Ð¿Ð¾ÐºÐ° ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð²Ñ‹ÑˆÐµ Ð»Ð¸Ð¼Ð¸Ñ‚Ð°")).visible(() -> this.modeSetting.isSelected("Custom") && this.speedLimit.isValue());
   private final BooleanSetting limitModeClamp = (new BooleanSetting("ÐžÐ³Ñ€Ð°Ð½Ð¸Ñ‡Ð¸Ñ‚ÑŒ ÑƒÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ðµ", "Ð ÐµÐ·Ð°Ñ‚ÑŒ Ð¸Ñ‚Ð¾Ð³Ð¾Ð²ÑƒÑŽ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð´Ð¾ Ð»Ð¸Ð¼Ð¸Ñ‚Ð°")).visible(() -> this.modeSetting.isSelected("Custom") && this.speedLimit.isValue());

   public SuperFireWork() {
      super("SuperFireWork", "Super FireWork", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.modeSetting, this.speedSetting, this.maxspeed, this.speedxz, this.speedy, this.speed5, this.speed10, this.speed15, this.speed20, this.speed25, this.speed30, this.speed35, this.speed40, this.speed5y, this.speed10y, this.speed15y, this.speed20y, this.speed25y, this.speed30y, this.speed35y, this.speed40y, this.speedLimit, this.maxSpeed, this.limitModeBlock, this.limitModeClamp});
   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginUltra
   )
   public void onFirework(FireworkEvent e) {
      if (this.modeSetting.isSelected("Grim")) {
         int ff = TurnsConnection.INSTANCE.getRotation().getYaw() > 0.0F ? 45 : -45;
         double acceleration = (double)(Math.abs((TurnsConnection.INSTANCE.getRotation().getYaw() + (float)ff) % 90.0F - (float)ff) / 45.0F);
         double boost = (double)1.0F + 0.3 * acceleration * acceleration;
         boolean yAcceleration = Math.abs(TurnsConnection.INSTANCE.getMoveRotation().getPitch()) > 60.0F;
         class_243 vec3d = e.getVector();
         e.setVector(new class_243(vec3d.field_1352 * boost, yAcceleration ? vec3d.field_1351 * boost : vec3d.field_1351, vec3d.field_1350 * boost));
      } else if (this.modeSetting.isSelected("Custom")) {
         class_310 mc = class_310.method_1551();
         class_243 currentVel = mc.field_1724 != null ? mc.field_1724.method_18798() : class_243.field_1353;
         double currentSpeed = currentVel.method_1033();
         boolean limitEnabled = this.speedLimit.isValue();
         double limit = (double)this.maxSpeed.getValue();
         if (limitEnabled && this.limitModeBlock.isValue() && !this.limitModeClamp.isValue() && currentSpeed >= limit) {
            return;
         }

         int ff = TurnsConnection.INSTANCE.getRotation().getYaw() > 0.0F ? 45 : -45;
         double acceleration = (double)(Math.abs((TurnsConnection.INSTANCE.getRotation().getYaw() + (float)ff) % 90.0F - (float)ff) / 45.0F);
         double rotationBoost = (double)1.0F + 0.3 * acceleration * acceleration;
         boolean yAcceleration = Math.abs(TurnsConnection.INSTANCE.getMoveRotation().getPitch()) > 60.0F;
         class_243 direction = TurnsConnection.INSTANCE.getMoveRotation().toVector();
         float speed = this.speedSetting.getValue() / 20.0F;
         double finalSpeed = (double)speed * rotationBoost;
         class_243 result = new class_243(direction.field_1352 * finalSpeed, yAcceleration ? direction.field_1351 * finalSpeed : direction.field_1351 * (double)speed, direction.field_1350 * finalSpeed);
         if (limitEnabled && this.limitModeClamp.isValue()) {
            double totalSpeed = result.method_1033();
            if (totalSpeed > limit && totalSpeed > (double)0.0F) {
               double k = limit / totalSpeed;
               result = result.method_1021(k);
            }
         }

         e.setVector(result);
      } else if (this.modeSetting.isSelected("ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ñ‹Ð¹")) {
         class_243 currentVel = e.getVector();
         if (this.maxspeed.isValue()) {
            int ff = TurnsConnection.INSTANCE.getRotation().getYaw() > 0.0F ? 45 : -45;
            double yaw = (double)TurnsConnection.INSTANCE.getRotation().getYaw();
            double angle = Math.abs((yaw + (double)ff) % (double)90.0F - (double)ff);
            double xzSpeed = (double)this.speedxz.getValue();
            if (angle <= (double)5.0F) {
               xzSpeed = (double)this.speed5.getValue();
            } else if (angle <= (double)10.0F) {
               xzSpeed = (double)this.speed10.getValue();
            } else if (angle <= (double)15.0F) {
               xzSpeed = (double)this.speed15.getValue();
            } else if (angle <= (double)20.0F) {
               xzSpeed = (double)this.speed20.getValue();
            } else if (angle <= (double)25.0F) {
               xzSpeed = (double)this.speed25.getValue();
            } else if (angle <= (double)30.0F) {
               xzSpeed = (double)this.speed30.getValue();
            } else if (angle <= (double)35.0F) {
               xzSpeed = (double)this.speed35.getValue();
            } else if (angle <= (double)40.0F) {
               xzSpeed = (double)this.speed40.getValue();
            }

            double ySpeed = (double)this.speedy.getValue();
            double pitch = (double)Math.abs(TurnsConnection.INSTANCE.getMoveRotation().getPitch());
            if (pitch <= (double)5.0F) {
               ySpeed = (double)this.speed5y.getValue();
            } else if (pitch <= (double)10.0F) {
               ySpeed = (double)this.speed10y.getValue();
            } else if (pitch <= (double)15.0F) {
               ySpeed = (double)this.speed15y.getValue();
            } else if (pitch <= (double)20.0F) {
               ySpeed = (double)this.speed20y.getValue();
            } else if (pitch <= (double)25.0F) {
               ySpeed = (double)this.speed25y.getValue();
            } else if (pitch <= (double)30.0F) {
               ySpeed = (double)this.speed30y.getValue();
            } else if (pitch <= (double)35.0F) {
               ySpeed = (double)this.speed35y.getValue();
            } else if (pitch <= (double)40.0F) {
               ySpeed = (double)this.speed40y.getValue();
            }

            class_243 result = new class_243(currentVel.field_1352 * xzSpeed, currentVel.field_1351 * ySpeed, currentVel.field_1350 * xzSpeed);
            e.setVector(result);
         } else {
            class_243 result = new class_243(currentVel.field_1352 * (double)this.speedxz.getValue(), currentVel.field_1351 * (double)this.speedy.getValue(), currentVel.field_1350 * (double)this.speedxz.getValue());
            e.setVector(result);
         }
      } else if (this.modeSetting.isSelected("BravoHvH")) {
         int ff = TurnsConnection.INSTANCE.getRotation().getYaw() > 0.0F ? 45 : -45;
         double yaw = (double)TurnsConnection.INSTANCE.getRotation().getYaw();
         double accelerationNear45 = Math.abs((yaw + (double)ff) % (double)90.0F - (double)ff) / (double)45.0F;
         double strongMultiplier = 0.26;
         double weakMultiplier = strongMultiplier / 2.2;
         double strongBoost = strongMultiplier * accelerationNear45 * accelerationNear45;
         double yawNormalized = Math.abs(yaw % (double)90.0F) / (double)90.0F;
         double weakBoost = weakMultiplier * yawNormalized * yawNormalized;
         double boost = 0.95 + strongBoost + weakBoost;
         boolean yAcceleration = Math.abs(TurnsConnection.INSTANCE.getMoveRotation().getPitch()) > 60.0F;
         class_243 vec3d = e.getVector();
         e.setVector(new class_243(vec3d.field_1352 * boost, yAcceleration ? vec3d.field_1351 * boost : vec3d.field_1351, vec3d.field_1350 * boost));
      }

   }
}

