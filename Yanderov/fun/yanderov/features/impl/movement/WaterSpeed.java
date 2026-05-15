package fun.Yanderov.features.impl.movement;

import antidaunleak.api.annotation.Native;
import fun.Yanderov.events.player.SwimmingEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.features.aura.warp.TurnsConnection;
import net.minecraft.class_1294;
import net.minecraft.class_3532;

public class WaterSpeed extends Module {
   private final SelectSetting modeSetting = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ñ€ÐµÐ¶Ð¸Ð¼ Ð¾Ð±Ñ…Ð¾Ð´Ð°")).value("FunTime", "FunSky");
   private final BooleanSetting funskyWaterSpeed = (new BooleanSetting("WaterSpeed", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ñ‚Ð¾Ð»ÑŒÐºÐ¾ ÐºÐ¾Ð³Ð´Ð° Ð¿Ð»Ñ‹Ð²Ñ‘Ñ‚Ðµ")).setValue(true).visible(() -> this.modeSetting.isSelected("FunSky"));
   private final SliderSettings funskyWater0 = (new SliderSettings("Water 0", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð±ÐµÐ· ÑÑ„Ñ„ÐµÐºÑ‚Ð°, Ð² Ð¿Ð»Ð°Ð²Ð°Ð½Ð¸Ð¸")).range(0.1F, 2.0F).setValue(0.25F).visible(() -> this.modeSetting.isSelected("FunSky") && this.funskyWaterSpeed.isValue());
   private final SliderSettings funskyWater1 = (new SliderSettings("Water 1", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Speed 1, Ð² Ð¿Ð»Ð°Ð²Ð°Ð½Ð¸Ð¸")).range(0.1F, 2.0F).setValue(0.25F).visible(() -> this.modeSetting.isSelected("FunSky") && this.funskyWaterSpeed.isValue());
   private final SliderSettings funskyWater2 = (new SliderSettings("Water 2", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Speed 2, Ð² Ð¿Ð»Ð°Ð²Ð°Ð½Ð¸Ð¸")).range(0.1F, 2.0F).setValue(0.4F).visible(() -> this.modeSetting.isSelected("FunSky") && this.funskyWaterSpeed.isValue());
   private final SliderSettings funskyWater3 = (new SliderSettings("Water 3", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Speed 3, Ð² Ð¿Ð»Ð°Ð²Ð°Ð½Ð¸Ð¸")).range(0.1F, 2.0F).setValue(0.56F).visible(() -> this.modeSetting.isSelected("FunSky") && this.funskyWaterSpeed.isValue());

   public WaterSpeed() {
      super("WaterSpeed", "WaterSpeed", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.modeSetting, this.funskyWaterSpeed, this.funskyWater0, this.funskyWater1, this.funskyWater2, this.funskyWater3});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.modeSetting.isSelected("FunTime") && mc.field_1724 != null && mc.field_1724.method_5681() && mc.field_1724.method_24828()) {
         mc.field_1724.method_6043();
         mc.field_1724.field_18276.field_1351 = 0.1;
      }

   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginUltra
   )
   public void onSwimming(SwimmingEvent e) {
      if (mc.field_1724 != null) {
         if (this.modeSetting.isSelected("FunTime")) {
            if (mc.field_1690.field_1903.method_1434()) {
               float pitch = TurnsConnection.INSTANCE.getRotation().getPitch();
               float boost = pitch >= 0.0F ? class_3532.method_15363(pitch / 45.0F, 1.0F, 2.0F) : 0.8F;
               e.getVector().field_1351 = (double)0.5F * (double)boost;
            } else if (mc.field_1690.field_1832.method_1434()) {
               e.getVector().field_1351 = -0.8;
            }

         } else {
            if (this.modeSetting.isSelected("FunSky") && this.funskyWaterSpeed.isValue()) {
               int amp = 0;
               boolean hasSpeed = false;

               try {
                  hasSpeed = mc.field_1724.method_6059(class_1294.field_5904);
                  if (hasSpeed) {
                     amp = mc.field_1724.method_6112(class_1294.field_5904).method_5578();
                  }
               } catch (Throwable var17) {
               }

               float baseSpeed;
               if (!hasSpeed) {
                  baseSpeed = this.funskyWater0.getValue();
               } else if (amp == 0) {
                  baseSpeed = this.funskyWater1.getValue();
               } else if (amp == 1) {
                  baseSpeed = this.funskyWater2.getValue();
               } else {
                  baseSpeed = this.funskyWater3.getValue();
               }

               double x = e.getVector().field_1352;
               double z = e.getVector().field_1350;
               double horizLen = Math.sqrt(x * x + z * z);
               if (horizLen > 1.0E-4) {
                  double nx = x / horizLen;
                  double nz = z / horizLen;
                  double scaled = (double)baseSpeed;
                  e.getVector().field_1352 = nx * scaled;
                  e.getVector().field_1350 = nz * scaled;
               }
            }

         }
      }
   }
}

