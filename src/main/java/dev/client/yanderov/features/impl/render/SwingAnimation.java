package dev.client.yanderov.features.impl.render;

import dev.client.yanderov.events.item.HandAnimationEvent;
import dev.client.yanderov.events.item.SwingDurationEvent;
import dev.client.yanderov.features.impl.combat.Aura;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_7833;

public class SwingAnimation extends Module {
   private final SelectSetting swingType = (new SelectSetting("Ð¢Ð¸Ð¿ Ð²Ð·Ð¼Ð°Ñ…Ð°", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ñ‚Ð¸Ð¿ Ð²Ð·Ð¼Ð°Ñ…Ð°")).value("Swipe", "Down", "Smooth", "Smooth 2", "Power", "Feast", "Twist", "Default");
   private final SliderSettings hitStrengthSetting = (new SliderSettings("Ð¡Ð¸Ð»Ð° Ð²Ð·Ð¼Ð°Ñ…Ð°", "Ð¡Ð¸Ð»Ð° Ð°Ð½Ð¸Ð¼Ð°Ñ†Ð¸Ð¸ Ð²Ð·Ð¼Ð°Ñ…Ð°")).setValue(1.0F).range(0.5F, 3.0F);
   private final SliderSettings swingSpeedSetting = (new SliderSettings("Ð”Ð»Ð¸Ñ‚ÐµÐ»ÑŒÐ½Ð¾ÑÑ‚ÑŒ Ð²Ð·Ð¼Ð°Ñ…Ð°", "Ð”Ð»Ð¸Ñ‚ÐµÐ»ÑŒÐ½Ð¾ÑÑ‚ÑŒ Ð°Ð½Ð¸Ð¼Ð°Ñ†Ð¸Ð¸ ÑƒÐ´Ð°Ñ€Ð°")).setValue(1.0F).range(0.5F, 4.0F);
   private final BooleanSetting onlySwing = (new BooleanSetting("Ð¢Ð¾Ð»ÑŒÐºÐ¾ Ð¿Ñ€Ð¸ Ð²Ð·Ð¼Ð°Ñ…Ðµ", "ÐŸÐ¾ÐºÐ°Ð·Ñ‹Ð²Ð°ÐµÑ‚ Ð°Ð½Ð¸Ð¼Ð°Ñ†Ð¸ÑŽ Ñ‚Ð¾Ð»ÑŒÐºÐ¾ Ð¿Ñ€Ð¸ Ð²Ð·Ð¼Ð°Ñ…Ðµ")).setValue(false);
   private final BooleanSetting onlyAura = (new BooleanSetting("Ð¢Ð¾Ð»ÑŒÐºÐ¾ Ð¿Ñ€Ð¸ Ð²ÐºÐ»ÑŽÑ‡ÐµÐ½Ð½Ð¾Ð¹ ÐšÐ¸Ð»Ð»ÐÑƒÑ€Ðµ", "ÐŸÐ¾ÐºÐ°Ð·Ñ‹Ð²Ð°ÐµÑ‚ Ð°Ð½Ð¸Ð¼Ð°Ñ†Ð¸ÑŽ Ñ‚Ð¾Ð»ÑŒÐºÐ¾ Ð¿Ñ€Ð¸ Ð²ÐºÐ»ÑŽÑ‡ÐµÐ½Ð½Ð¾Ð¹ ÐºÐ¸Ð»Ð»Ð°ÑƒÑ€Ðµ")).setValue(false);
   private float spinAngle = 0.0F;
   private float spinBackTimer = 0.0F;
   private boolean wasSwinging = false;

   public SwingAnimation() {
      super("SwingAnimation", "Swing Animation", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.swingType, this.hitStrengthSetting, this.swingSpeedSetting, this.onlySwing, this.onlyAura});
   }

   @EventHandler
   public void onSwingDuration(SwingDurationEvent e) {
      if (!this.onlyAura.isValue() || Aura.getInstance().isState() && Aura.getInstance().getTarget() != null) {
         e.setAnimation(this.swingSpeedSetting.getValue());
         e.cancel();
      }

   }

   @EventHandler
   public void onHandAnimation(HandAnimationEvent e) {
      boolean isMainHand = e.getHand().equals(class_1268.field_5808);
      if (isMainHand) {
         class_4587 matrix = e.getMatrices();
         float swingProgress = e.getSwingProgress();
         int i = mc.field_1724.method_6068().equals(class_1306.field_6183) ? 1 : -1;
         float sin1 = class_3532.method_15374(swingProgress * swingProgress * (float)Math.PI);
         float sin2 = class_3532.method_15374(class_3532.method_15355(swingProgress) * (float)Math.PI);
         float sinSmooth = (float)(Math.sin((double)swingProgress * Math.PI) * (double)0.5F);
         float strength = this.hitStrengthSetting.getValue();
         if (this.onlyAura.isValue() && (!Aura.getInstance().isState() || Aura.getInstance().getTarget() == null)) {
            return;
         }

         if (this.onlySwing.isValue() && mc.field_1724.field_6279 == 0) {
            matrix.method_46416((float)i * 0.56F, -0.52F, -0.72F);
         } else {
            switch (this.swingType.getSelected()) {
               case "Twist":
                  matrix.method_46416((float)i * 0.56F, -0.36F, -0.72F);
                  matrix.method_22907(class_7833.field_40716.rotationDegrees((float)(80 * i)));
                  matrix.method_22907(class_7833.field_40714.rotationDegrees(sin2 * -90.0F * strength));
                  matrix.method_22907(class_7833.field_40718.rotationDegrees((sin1 - sin2) * 60.0F * (float)i * strength));
                  matrix.method_22907(class_7833.field_40714.rotationDegrees(-30.0F));
                  matrix.method_46416(0.0F, -0.1F, 0.05F);
                  break;
               case "Swipe":
                  matrix.method_46416(0.56F * (float)i, -0.32F, -0.72F);
                  matrix.method_22907(class_7833.field_40716.rotationDegrees((float)(70 * i)));
                  matrix.method_22907(class_7833.field_40718.rotationDegrees((float)(-20 * i)));
                  matrix.method_22907(class_7833.field_40716.rotationDegrees(sin2 * sin1 * -5.0F * strength));
                  matrix.method_22907(class_7833.field_40714.rotationDegrees(sin2 * sin1 * -120.0F * strength));
                  matrix.method_22907(class_7833.field_40714.rotationDegrees(-70.0F));
                  break;
               case "Default":
                  matrix.method_46416((float)i * 0.56F, -0.52F - sin2 * 0.5F * strength, -0.72F);
                  matrix.method_22907(class_7833.field_40716.rotationDegrees((float)(45 * i)));
                  matrix.method_22907(class_7833.field_40716.rotationDegrees((float)(-45 * i)));
                  break;
               case "Down":
                  matrix.method_46416((float)i * 0.56F, -0.32F, -0.72F);
                  matrix.method_22907(class_7833.field_40716.rotationDegrees((float)(76 * i)));
                  matrix.method_22907(class_7833.field_40716.rotationDegrees(sin2 * -5.0F * strength));
                  matrix.method_22907(class_7833.field_40713.rotationDegrees(sin2 * -100.0F * strength));
                  matrix.method_22907(class_7833.field_40714.rotationDegrees(sin2 * -155.0F * strength));
                  matrix.method_22907(class_7833.field_40714.rotationDegrees(-100.0F));
                  break;
               case "Smooth":
                  matrix.method_46416((float)i * 0.56F, -0.42F, -0.72F);
                  matrix.method_22907(class_7833.field_40716.rotationDegrees((float)i * (45.0F + sin1 * -20.0F * strength)));
                  matrix.method_22907(class_7833.field_40718.rotationDegrees((float)i * sin2 * -20.0F * strength));
                  matrix.method_22907(class_7833.field_40714.rotationDegrees(sin2 * -80.0F * strength));
                  matrix.method_22907(class_7833.field_40716.rotationDegrees((float)i * -45.0F));
                  matrix.method_22904((double)0.0F, -0.1, (double)0.0F);
                  break;
               case "Smooth 2":
                  matrix.method_46416((float)i * 0.56F, -0.42F, -0.72F);
                  matrix.method_22907(class_7833.field_40714.rotationDegrees(sin2 * -80.0F * strength));
                  matrix.method_22904((double)0.0F, -0.1, (double)0.0F);
                  break;
               case "Power":
                  matrix.method_46416((float)i * 0.56F, -0.32F, -0.72F);
                  matrix.method_46416(-sinSmooth * sinSmooth * sin1 * (float)i * strength, 0.0F, 0.0F);
                  matrix.method_22907(class_7833.field_40716.rotationDegrees((float)(61 * i)));
                  matrix.method_22907(class_7833.field_40718.rotationDegrees(sin2 * strength));
                  matrix.method_22907(class_7833.field_40716.rotationDegrees(sin2 * sin1 * -5.0F * strength));
                  matrix.method_22907(class_7833.field_40714.rotationDegrees(sin2 * sin1 * -30.0F * strength));
                  matrix.method_22907(class_7833.field_40714.rotationDegrees(-60.0F));
                  matrix.method_22907(class_7833.field_40714.rotationDegrees(sinSmooth * -60.0F * strength));
                  break;
               case "Feast":
                  matrix.method_46416((float)i * 0.56F, -0.32F, -0.72F);
                  matrix.method_22907(class_7833.field_40716.rotationDegrees((float)(30 * i)));
                  matrix.method_22907(class_7833.field_40716.rotationDegrees(sin2 * 75.0F * (float)i * strength));
                  matrix.method_22907(class_7833.field_40714.rotationDegrees(sin2 * -45.0F * strength));
                  matrix.method_22907(class_7833.field_40716.rotationDegrees((float)(30 * i)));
                  matrix.method_22907(class_7833.field_40714.rotationDegrees(-80.0F));
                  matrix.method_22907(class_7833.field_40716.rotationDegrees((float)(35 * i)));
            }
         }

         e.cancel();
      }

   }
}

