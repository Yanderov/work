package fun.Yanderov.features.impl.movement;

import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import net.minecraft.class_1297;
import net.minecraft.class_1690;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_3532;

public class BoatFly extends Module {
   private final SliderSettings boatUpSpeed = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð²Ð²ÐµÑ€Ñ…", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð»Ð¾Ð´ÐºÐ¸ Ð²Ð²ÐµÑ€Ñ…")).range(0.05F, 5.0F).setValue(0.4F);
   private final SliderSettings boatDownSpeed = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð²Ð½Ð¸Ð·", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð»Ð¾Ð´ÐºÐ¸ Ð²Ð½Ð¸Ð·")).range(0.05F, 5.0F).setValue(0.4F);
   private final SliderSettings boatHorizontalSpeed = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð² ÑÑ‚Ð¾Ñ€Ð¾Ð½Ñ‹", "Ð“Ð¾Ñ€Ð¸Ð·Ð¾Ð½Ñ‚Ð°Ð»ÑŒÐ½Ð°Ñ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð»Ð¾Ð´ÐºÐ¸")).range(0.05F, 5.0F).setValue(0.6F);
   private final BooleanSetting boatNoClip = (new BooleanSetting("NoClip", "ÐŸÑ€Ð¾Ñ…Ð¾Ð´Ð¸Ñ‚ÑŒ ÑÐºÐ²Ð¾Ð·ÑŒ Ð±Ð»Ð¾ÐºÐ¸ Ð½Ð° Ð»Ð¾Ð´ÐºÐµ")).setValue(true);
   private final SliderSettings clipBlockSpeed = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð² Ð±Ð»Ð¾ÐºÐ°Ñ…", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð²Ð½ÑƒÑ‚Ñ€Ð¸ Ð±Ð»Ð¾ÐºÐ¾Ð²")).range(0.05F, 5.0F).setValue(0.25F).visible(() -> this.boatNoClip.isValue());
   private final BooleanSetting ignoreCollision = (new BooleanSetting("IgnoreCollision", "Ð˜Ð³Ð½Ð¾Ñ€ ÐºÐ¾Ð»Ð»Ð¸Ð·Ð¸Ð¸ (Ñ‚Ð° Ð¶Ðµ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð² Ð±Ð»Ð¾ÐºÐ°Ñ…)")).setValue(false).visible(() -> this.boatNoClip.isValue());
   private boolean wasNoClip = false;

   public BoatFly() {
      super("BoatFly", "Boat Fly", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.boatUpSpeed, this.boatDownSpeed, this.boatHorizontalSpeed, this.boatNoClip, this.clipBlockSpeed, this.ignoreCollision});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.state && mc.field_1724 != null && mc.field_1687 != null) {
         class_1297 vehicle = mc.field_1724.method_5854();
         if (vehicle instanceof class_1690) {
            class_1690 boat = (class_1690)vehicle;
            boolean sneakPressed = mc.field_1690.field_1832.method_1434();
            mc.field_1690.field_1832.method_23481(false);
            if (this.boatNoClip.isValue()) {
               boat.field_5960 = true;
               boat.method_5875(true);
               this.wasNoClip = true;
            } else if (this.wasNoClip) {
               this.resetBoatFlags();
            }

            boat.method_36456(mc.field_1724.method_36454());
            boat.field_5982 = boat.method_36454();
            double motionX = (double)0.0F;
            double motionY = (double)0.0F;
            double motionZ = (double)0.0F;
            float yaw = boat.method_36454();
            float yawRad = yaw * ((float)Math.PI / 180F);
            if (mc.field_1690.field_1903.method_1434()) {
               motionY += (double)this.boatUpSpeed.getValue();
            }

            if (sneakPressed) {
               motionY -= (double)this.boatDownSpeed.getValue();
            }

            double horizontalSpeed = (double)this.boatHorizontalSpeed.getValue();
            if (this.boatNoClip.isValue() && this.isInsideSolidBlock() && !this.ignoreCollision.isValue()) {
               horizontalSpeed = (double)this.clipBlockSpeed.getValue();
            }

            float forward = 0.0F;
            float strafe = 0.0F;
            if (mc.field_1690.field_1894.method_1434()) {
               ++forward;
            }

            if (mc.field_1690.field_1881.method_1434()) {
               --forward;
            }

            if (mc.field_1690.field_1913.method_1434()) {
               ++strafe;
            }

            if (mc.field_1690.field_1849.method_1434()) {
               --strafe;
            }

            if (forward != 0.0F || strafe != 0.0F) {
               float len = class_3532.method_15355(forward * forward + strafe * strafe);
               if (len > 0.0F) {
                  forward /= len;
                  strafe /= len;
               }

               motionX = (double)(-class_3532.method_15374(yawRad) * forward + class_3532.method_15362(yawRad) * strafe) * horizontalSpeed;
               motionZ = (double)(class_3532.method_15362(yawRad) * forward + class_3532.method_15374(yawRad) * strafe) * horizontalSpeed;
            }

            boat.method_18799(new class_243(motionX, motionY, motionZ));
         }
      }
   }

   private boolean isInsideSolidBlock() {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         class_238 box = mc.field_1724.method_5829().method_1014(0.001);
         int minX = class_3532.method_15357(box.field_1323);
         int minY = class_3532.method_15357(box.field_1322);
         int minZ = class_3532.method_15357(box.field_1321);
         int maxX = class_3532.method_15357(box.field_1320);
         int maxY = class_3532.method_15357(box.field_1325);
         int maxZ = class_3532.method_15357(box.field_1324);

         for(int x = minX; x <= maxX; ++x) {
            for(int y = minY; y <= maxY; ++y) {
               for(int z = minZ; z <= maxZ; ++z) {
                  class_2338 pos = class_2338.method_49637((double)x, (double)y, (double)z);
                  class_2680 state = mc.field_1687.method_8320(pos);
                  if (!state.method_26215() && state.method_51367()) {
                     return true;
                  }
               }
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private void resetBoatFlags() {
      this.wasNoClip = false;
      if (mc.field_1724 != null) {
         class_1297 var2 = mc.field_1724.method_5854();
         if (var2 instanceof class_1690) {
            class_1690 boat = (class_1690)var2;
            boat.field_5960 = false;
            boat.method_5875(false);
         }
      }

   }

   public void deactivate() {
      super.deactivate();
      this.resetBoatFlags();
   }
}

