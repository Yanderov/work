package dev.client.yanderov.features.impl.render;

import dev.client.yanderov.events.item.HandAnimationEvent;
import dev.client.yanderov.events.item.HandOffsetEvent;
import dev.client.yanderov.features.impl.combat.Aura;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.Instance;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import java.util.Objects;
import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_7833;

public class CustomHand extends Module {
   private final SelectSetting swingMode = (new SelectSetting("ÐÐ½Ð¸Ð¼Ð°Ñ†Ð¸Ñ", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ð°Ð½Ð¸Ð¼Ð°Ñ†Ð¸ÑŽ Ñ€ÑƒÐºÐ¸")).value("Ð”ÐµÑ„Ð¾Ð»Ñ‚Ð½Ð°Ñ", "Ð’Ð·Ð¼Ð°Ñ…", "Ð’Ð·Ð¼Ð°Ñ… Ð²Ð½Ð¸Ð·", "Ð’Ð½Ð¸Ð· Ð‘Ð¾ÐºÐ¾Ð¼", "Ð’Ñ€Ð°Ñ‰ÐµÐ½Ð¸Ðµ", "ÐÐµÐ¾Ð±Ñ‹Ñ‡Ð½Ñ‹Ð¹", "ÐŸÑƒÐ»ÑŒÑ", "Ð’Ñ‹ÐºÐ»ÑŽÑ‡ÐµÐ½Ð¾").selected("Ð’Ñ‹ÐºÐ»ÑŽÑ‡ÐµÐ½Ð¾");
   private final SliderSettings animSpeed = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ ÐÐ½Ð¸Ð¼Ð°Ñ†Ð¸Ð¸", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð°Ð½Ð¸Ð¼Ð°Ñ†Ð¸Ð¸")).setValue(8.0F).range(3.0F, 10.0F);
   private final SliderSettings animSize = (new SliderSettings("Ð Ð°Ð·Ð¼ÐµÑ€ ÐÐ½Ð¸Ð¼Ð°Ñ†Ð¸Ð¸", "Ð Ð°Ð·Ð¼ÐµÑ€ Ð°Ð½Ð¸Ð¼Ð°Ñ†Ð¸Ð¸")).setValue(3.7F).range(1.0F, 10.0F);
   private final BooleanSetting auraOnly = (new BooleanSetting("Ð¢Ð¾Ð»ÑŒÐºÐ¾ ÐÑƒÑ€Ð°", "Ð Ð°Ð±Ð¾Ñ‚Ð°Ñ‚ÑŒ Ñ‚Ð¾Ð»ÑŒÐºÐ¾ Ð¿Ñ€Ð¸ Ð²ÐºÐ»ÑŽÑ‡ÐµÐ½Ð½Ð¾Ð¹ Ð°ÑƒÑ€Ðµ Ð¸ Ñ†ÐµÐ»Ð¸")).setValue(true);
   private final BooleanSetting customHands = (new BooleanSetting("ÐœÐ¾Ð´ÐµÐ»ÑŒ Ð ÑƒÐºÐ¸", "Ð¡Ð´Ð²Ð¸Ð³ Ð¼Ð¾Ð´ÐµÐ»Ð¸ Ñ€ÑƒÐº")).setValue(false);
   private final BooleanSetting symmetYanderovands;
   private final SliderSettings offsetX;
   private final SliderSettings offsetY;
   private final SliderSettings offsetZ;
   private final SliderSettings rightX;
   private final SliderSettings rightY;
   private final SliderSettings rightZ;
   private final SliderSettings leftX;
   private final SliderSettings leftY;
   private final SliderSettings leftZ;

   public static CustomHand getInstance() {
      return (CustomHand)Instance.get(CustomHand.class);
   }

   public CustomHand() {
      super("CustomHand", "Custom Hand", ModuleCategory.RENDER);
      BooleanSetting var10001 = (new BooleanSetting("ÐœÐµÐ½ÑÑ‚ÑŒ Ð¾Ð±Ðµ Ñ€ÑƒÐºÐ¸", "ÐžÐ´Ð¸Ð½Ð°ÐºÐ¾Ð²Ñ‹Ð¹ ÑÐ´Ð²Ð¸Ð³ Ð´Ð»Ñ Ð´Ð²ÑƒÑ… Ñ€ÑƒÐº")).setValue(false);
      BooleanSetting var10002 = this.customHands;
      Objects.requireNonNull(var10002);
      this.symmetYanderovands = var10001.visible(var10002::isValue);
      this.offsetX = (new SliderSettings("X", "Ð¡Ð¼ÐµÑ‰ÐµÐ½Ð¸Ðµ Ð¿Ð¾ X (Ð¾Ð±Ðµ Ñ€ÑƒÐºÐ¸)")).setValue(0.0F).range(-2.0F, 2.0F).visible(() -> this.customHands.isValue() && this.symmetYanderovands.isValue());
      this.offsetY = (new SliderSettings("Y", "Ð¡Ð¼ÐµÑ‰ÐµÐ½Ð¸Ðµ Ð¿Ð¾ Y (Ð¾Ð±Ðµ Ñ€ÑƒÐºÐ¸)")).setValue(0.0F).range(-2.0F, 2.0F).visible(() -> this.customHands.isValue() && this.symmetYanderovands.isValue());
      this.offsetZ = (new SliderSettings("Z", "Ð¡Ð¼ÐµÑ‰ÐµÐ½Ð¸Ðµ Ð¿Ð¾ Z (Ð¾Ð±Ðµ Ñ€ÑƒÐºÐ¸)")).setValue(0.0F).range(-2.0F, 2.0F).visible(() -> this.customHands.isValue() && this.symmetYanderovands.isValue());
      this.rightX = (new SliderSettings("X Ð¿Ñ€Ð°Ð²Ð°Ñ", "Ð¡Ð¼ÐµÑ‰ÐµÐ½Ð¸Ðµ X Ð´Ð»Ñ Ð¿Ñ€Ð°Ð²Ð¾Ð¹ Ñ€ÑƒÐºÐ¸")).setValue(0.0F).range(-2.0F, 2.0F).visible(() -> this.customHands.isValue() && !this.symmetYanderovands.isValue());
      this.rightY = (new SliderSettings("Y Ð¿Ñ€Ð°Ð²Ð°Ñ", "Ð¡Ð¼ÐµÑ‰ÐµÐ½Ð¸Ðµ Y Ð´Ð»Ñ Ð¿Ñ€Ð°Ð²Ð¾Ð¹ Ñ€ÑƒÐºÐ¸")).setValue(0.0F).range(-2.0F, 2.0F).visible(() -> this.customHands.isValue() && !this.symmetYanderovands.isValue());
      this.rightZ = (new SliderSettings("Z Ð¿Ñ€Ð°Ð²Ð°Ñ", "Ð¡Ð¼ÐµÑ‰ÐµÐ½Ð¸Ðµ Z Ð´Ð»Ñ Ð¿Ñ€Ð°Ð²Ð¾Ð¹ Ñ€ÑƒÐºÐ¸")).setValue(0.0F).range(-2.0F, 2.0F).visible(() -> this.customHands.isValue() && !this.symmetYanderovands.isValue());
      this.leftX = (new SliderSettings("X Ð»ÐµÐ²Ð°Ñ", "Ð¡Ð¼ÐµÑ‰ÐµÐ½Ð¸Ðµ X Ð´Ð»Ñ Ð»ÐµÐ²Ð¾Ð¹ Ñ€ÑƒÐºÐ¸")).setValue(0.0F).range(-2.0F, 2.0F).visible(() -> this.customHands.isValue() && !this.symmetYanderovands.isValue());
      this.leftY = (new SliderSettings("Y Ð»ÐµÐ²Ð°Ñ", "Ð¡Ð¼ÐµÑ‰ÐµÐ½Ð¸Ðµ Y Ð´Ð»Ñ Ð»ÐµÐ²Ð¾Ð¹ Ñ€ÑƒÐºÐ¸")).setValue(0.0F).range(-2.0F, 2.0F).visible(() -> this.customHands.isValue() && !this.symmetYanderovands.isValue());
      this.leftZ = (new SliderSettings("Z Ð»ÐµÐ²Ð°Ñ", "Ð¡Ð¼ÐµÑ‰ÐµÐ½Ð¸Ðµ Z Ð´Ð»Ñ Ð»ÐµÐ²Ð¾Ð¹ Ñ€ÑƒÐºÐ¸")).setValue(0.0F).range(-2.0F, 2.0F).visible(() -> this.customHands.isValue() && !this.symmetYanderovands.isValue());
      this.setup(new Setting[]{this.swingMode, this.animSpeed, this.animSize, this.auraOnly, this.customHands, this.symmetYanderovands, this.offsetX, this.offsetY, this.offsetZ, this.rightX, this.rightY, this.rightZ, this.leftX, this.leftY, this.leftZ});
   }

   @EventHandler
   public void onHandAnimation(HandAnimationEvent event) {
      if (this.isState()) {
         if (!this.swingMode.isSelected("Ð’Ñ‹ÐºÐ»ÑŽÑ‡ÐµÐ½Ð¾")) {
            if (event.getHand() == class_1268.field_5808) {
               if (this.auraCheck()) {
                  class_4587 matrix = event.getMatrices();
                  float swingProgress = event.getSwingProgress();
                  int i = mc.field_1724.method_6068() == class_1306.field_6183 ? 1 : -1;
                  float animFactor = (float)Math.sin((double)swingProgress * (Math.PI / 2D) * (double)2.0F);
                  float size = this.animSize.getValue();
                  switch (this.swingMode.getSelected()) {
                     case "Ð”ÐµÑ„Ð¾Ð»Ñ‚Ð½Ð°Ñ":
                        float sin2 = class_3532.method_15374(class_3532.method_15355(swingProgress) * (float)Math.PI);
                        matrix.method_46416((float)i * 0.56F, -0.52F - sin2 * 0.5F, -0.72F);
                        matrix.method_22907(class_7833.field_40716.rotationDegrees(45.0F * (float)i));
                        matrix.method_22907(class_7833.field_40716.rotationDegrees(-45.0F * (float)i));
                        break;
                     case "Ð’Ð·Ð¼Ð°Ñ…":
                        matrix.method_46416((float)i * 0.67F, -0.32F, -1.0F);
                        matrix.method_22907(class_7833.field_40716.rotationDegrees(90.0F));
                        matrix.method_22907(class_7833.field_40718.rotationDegrees(-60.0F));
                        matrix.method_22907(class_7833.field_40714.rotationDegrees(-90.0F - size * 10.0F * animFactor));
                        break;
                     case "Ð’Ð·Ð¼Ð°Ñ… Ð²Ð½Ð¸Ð·":
                        matrix.method_46416((float)i * 0.67F, -0.32F, -1.0F);
                        matrix.method_22907(class_7833.field_40716.rotationDegrees(80.0F));
                        matrix.method_22907(class_7833.field_40718.rotationDegrees(-30.0F));
                        matrix.method_22907(class_7833.field_40714.rotationDegrees(-100.0F - size * 10.0F * animFactor));
                        break;
                     case "Ð’Ð½Ð¸Ð· Ð‘Ð¾ÐºÐ¾Ð¼":
                        matrix.method_46416((float)i * 0.67F, -0.32F, -1.0F);
                        matrix.method_46416(animFactor * -size / 35.0F, 0.0F, animFactor * -size / 35.0F);
                        matrix.method_22907(class_7833.field_40716.rotationDegrees(25.0F));
                        matrix.method_22907(class_7833.field_40713.rotationDegrees(animFactor * size * 5.0F));
                        matrix.method_22907(class_7833.field_40716.rotationDegrees(30.0F));
                        matrix.method_22907(class_7833.field_40714.rotationDegrees(-90.0F));
                        matrix.method_22907(class_7833.field_40716.rotationDegrees(50.0F));
                        break;
                     case "ÐŸÑƒÐ»ÑŒÑ":
                        matrix.method_46416((float)i * 0.56F, -0.52F, -0.72F);
                        matrix.method_22907(class_7833.field_40714.rotationDegrees(animFactor * -size * 10.0F));
                        matrix.method_22907(class_7833.field_40716.rotationDegrees(animFactor * size * (float)i));
                        matrix.method_22907(class_7833.field_40718.rotationDegrees(animFactor * size));
                        break;
                     case "Ð’Ñ€Ð°Ñ‰ÐµÐ½Ð¸Ðµ":
                        matrix.method_46416((float)i * 0.56F, -0.52F, -0.72F);
                        matrix.method_46416(0.0F, 0.1F, 0.0F);
                        matrix.method_22907(class_7833.field_40714.rotationDegrees(swingProgress * 360.0F));
                        matrix.method_46416(0.0F, -0.1F, 0.0F);
                        break;
                     case "ÐÐµÐ¾Ð±Ñ‹Ñ‡Ð½Ñ‹Ð¹":
                        matrix.method_46416((float)i * 0.56F, -0.32F, -0.72F);
                        matrix.method_46416(0.0F, 0.0F, -1.5F * animFactor / 5.0F);
                        matrix.method_22907(class_7833.field_40716.rotationDegrees(80.0F));
                        matrix.method_22907(class_7833.field_40717.rotationDegrees(45.0F));
                        matrix.method_22907(class_7833.field_40716.rotationDegrees(-10.0F));
                        matrix.method_46416(0.0F, 0.0F, -0.4F * animFactor);
                        matrix.method_22907(class_7833.field_40713.rotationDegrees(animFactor * -100.0F));
                        matrix.method_22907(class_7833.field_40714.rotationDegrees(animFactor * -180.0F));
                        matrix.method_22907(class_7833.field_40714.rotationDegrees(-70.0F));
                        break;
                     default:
                        return;
                  }

                  event.cancel();
               }
            }
         }
      }
   }

   @EventHandler
   public void onHandOffset(HandOffsetEvent event) {
      if (this.isState()) {
         if (this.customHands.isValue()) {
            class_4587 matrix = event.getMatrices();
            class_1268 hand = event.getHand();
            boolean rightHand = hand == class_1268.field_5808 ? mc.field_1724.method_6068() == class_1306.field_6183 : mc.field_1724.method_6068() == class_1306.field_6182;
            if (this.symmetYanderovands.isValue()) {
               if (rightHand) {
                  matrix.method_46416(this.offsetX.getValue(), this.offsetY.getValue(), this.offsetZ.getValue());
               } else {
                  matrix.method_46416(-this.offsetX.getValue(), this.offsetY.getValue(), this.offsetZ.getValue());
               }
            } else if (rightHand) {
               matrix.method_46416(this.rightX.getValue(), this.rightY.getValue(), this.rightZ.getValue());
            } else {
               matrix.method_46416(this.leftX.getValue(), this.leftY.getValue(), this.leftZ.getValue());
            }

         }
      }
   }

   public boolean auraCheck() {
      if (!this.auraOnly.isValue()) {
         return true;
      } else {
         return Aura.getInstance().isState() && Aura.getInstance().getCurrentTarget() != null;
      }
   }

   public SelectSetting swingMode() {
      return this.swingMode;
   }

   public SliderSettings animSpeed() {
      return this.animSpeed;
   }

   public SliderSettings animSize() {
      return this.animSize;
   }

   public BooleanSetting auraOnly() {
      return this.auraOnly;
   }

   public BooleanSetting customHands() {
      return this.customHands;
   }

   public BooleanSetting symmetYanderovands() {
      return this.symmetYanderovands;
   }

   public SliderSettings offsetX() {
      return this.offsetX;
   }

   public SliderSettings offsetY() {
      return this.offsetY;
   }

   public SliderSettings offsetZ() {
      return this.offsetZ;
   }

   public SliderSettings rightX() {
      return this.rightX;
   }

   public SliderSettings rightY() {
      return this.rightY;
   }

   public SliderSettings rightZ() {
      return this.rightZ;
   }

   public SliderSettings leftX() {
      return this.leftX;
   }

   public SliderSettings leftY() {
      return this.leftY;
   }

   public SliderSettings leftZ() {
      return this.leftZ;
   }
}

