package dev.client.yanderov.features.impl.render;

import dev.client.yanderov.events.item.HandOffsetEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1764;
import net.minecraft.class_4587;

public class ViewModel extends Module {
   private final SliderSettings mainHandXSetting = (new SliderSettings("ÐžÑÐ½Ð¾Ð²Ð½Ð°Ñ Ñ€ÑƒÐºÐ° X", "ÐÐ°ÑÑ‚Ñ€Ð¾Ð¹ÐºÐ° Ð·Ð½Ð°Ñ‡ÐµÐ½Ð¸Ñ X Ð´Ð»Ñ Ð¾ÑÐ½Ð¾Ð²Ð½Ð¾Ð¹ Ñ€ÑƒÐºÐ¸")).setValue(0.0F).range(-1.0F, 1.0F);
   private final SliderSettings mainHandYSetting = (new SliderSettings("ÐžÑÐ½Ð¾Ð²Ð½Ð°Ñ Ñ€ÑƒÐºÐ° Y", "ÐÐ°ÑÑ‚Ñ€Ð¾Ð¹ÐºÐ° Ð·Ð½Ð°Ñ‡ÐµÐ½Ð¸Ñ Y Ð´Ð»Ñ Ð¾ÑÐ½Ð¾Ð²Ð½Ð¾Ð¹ Ñ€ÑƒÐºÐ¸")).setValue(0.0F).range(-1.0F, 1.0F);
   private final SliderSettings mainHandZSetting = (new SliderSettings("ÐžÑÐ½Ð¾Ð²Ð½Ð°Ñ Ñ€ÑƒÐºÐ° Z", "ÐÐ°ÑÑ‚Ñ€Ð¾Ð¹ÐºÐ° Ð·Ð½Ð°Ñ‡ÐµÐ½Ð¸Ñ Z Ð´Ð»Ñ Ð¾ÑÐ½Ð¾Ð²Ð½Ð¾Ð¹ Ñ€ÑƒÐºÐ¸")).setValue(0.0F).range(-2.5F, 2.5F);
   private final SliderSettings offHandXSetting = (new SliderSettings("Ð’Ñ‚Ð¾Ñ€Ð¾ÑÑ‚ÐµÐ¿ÐµÐ½Ð½Ð°Ñ Ñ€ÑƒÐºÐ° X", "ÐÐ°ÑÑ‚Ñ€Ð¾Ð¹ÐºÐ° Ð·Ð½Ð°Ñ‡ÐµÐ½Ð¸Ñ X Ð´Ð»Ñ Ð²Ñ‚Ð¾Ñ€Ð¾ÑÑ‚ÐµÐ¿ÐµÐ½Ð½Ð¾Ð¹ Ñ€ÑƒÐºÐ¸")).setValue(0.0F).range(-1.0F, 1.0F);
   private final SliderSettings offHandYSetting = (new SliderSettings("Ð’Ñ‚Ð¾Ñ€Ð¾ÑÑ‚ÐµÐ¿ÐµÐ½Ð½Ð°Ñ Ñ€ÑƒÐºÐ° Y", "ÐÐ°ÑÑ‚Ñ€Ð¾Ð¹ÐºÐ° Ð·Ð½Ð°Ñ‡ÐµÐ½Ð¸Ñ Y Ð´Ð»Ñ Ð²Ñ‚Ð¾Ñ€Ð¾ÑÑ‚ÐµÐ¿ÐµÐ½Ð½Ð¾Ð¹ Ñ€ÑƒÐºÐ¸")).setValue(0.0F).range(-1.0F, 1.0F);
   private final SliderSettings offHandZSetting = (new SliderSettings("Ð’Ñ‚Ð¾Ñ€Ð¾ÑÑ‚ÐµÐ¿ÐµÐ½Ð½Ð°Ñ Ñ€ÑƒÐºÐ° Z", "ÐÐ°ÑÑ‚Ñ€Ð¾Ð¹ÐºÐ° Ð·Ð½Ð°Ñ‡ÐµÐ½Ð¸Ñ Z Ð´Ð»Ñ Ð²Ñ‚Ð¾Ñ€Ð¾ÑÑ‚ÐµÐ¿ÐµÐ½Ð½Ð¾Ð¹ Ñ€ÑƒÐºÐ¸")).setValue(0.0F).range(-2.5F, 2.5F);

   public ViewModel() {
      super("ViewModel", "View Model", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.mainHandXSetting, this.mainHandYSetting, this.mainHandZSetting, this.offHandXSetting, this.offHandYSetting, this.offHandZSetting});
   }

   @EventHandler
   public void onHandOffset(HandOffsetEvent e) {
      class_1268 hand = e.getHand();
      if (!hand.equals(class_1268.field_5808) || !(e.getStack().method_7909() instanceof class_1764)) {
         class_4587 matrix = e.getMatrices();
         if (hand.equals(class_1268.field_5808)) {
            matrix.method_46416(this.mainHandXSetting.getValue(), this.mainHandYSetting.getValue(), this.mainHandZSetting.getValue());
         } else {
            matrix.method_46416(this.offHandXSetting.getValue(), this.offHandYSetting.getValue(), this.offHandZSetting.getValue());
         }

      }
   }
}

