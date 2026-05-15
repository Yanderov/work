package dev.client.yanderov.features.impl.combat;

import dev.client.yanderov.common.repository.friend.FriendUtils;
import dev.client.yanderov.events.player.BoundingBoxControlEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_238;

public class HitBoxModule extends Module {
   private final SliderSettings xzExpandSetting = (new SliderSettings("Ð Ð°ÑÑˆÐ¸Ñ€ÐµÐ½Ð¸Ðµ XZ", "ÐŸÐ¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ Ñ€Ð°ÑÑˆÐ¸Ñ€Ð¸Ñ‚ÑŒ Ñ…Ð¸Ñ‚Ð±Ð¾ÐºÑ Ð¿Ð¾ Ð¾ÑÑÐ¼ XZ")).setValue(0.2F).range(0.0F, 3.0F);
   private final SliderSettings yExpandSetting = (new SliderSettings("Ð Ð°ÑÑˆÐ¸Ñ€ÐµÐ½Ð¸Ðµ Y", "ÐŸÐ¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ Ñ€Ð°ÑÑˆÐ¸Ñ€Ð¸Ñ‚ÑŒ Ñ…Ð¸Ñ‚Ð±Ð¾ÐºÑ Ð¿Ð¾ Ð¾ÑÐ¸ Y")).setValue(0.0F).range(0.0F, 3.0F);

   public HitBoxModule() {
      super("HitBox", "Hit Box", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.xzExpandSetting, this.yExpandSetting});
   }

   @EventHandler
   public void onBoundingBoxControl(BoundingBoxControlEvent event) {
      class_1297 var3 = event.getEntity();
      if (var3 instanceof class_1309 living) {
         class_238 box = event.getBox();
         float xzExpand = this.xzExpandSetting.getValue();
         float yExpand = this.yExpandSetting.getValue();
         class_238 changedBox = new class_238(box.field_1323 - (double)(xzExpand / 2.0F), box.field_1322 - (double)(yExpand / 2.0F), box.field_1321 - (double)(xzExpand / 2.0F), box.field_1320 + (double)(xzExpand / 2.0F), box.field_1325 + (double)(yExpand / 2.0F), box.field_1324 + (double)(xzExpand / 2.0F));
         if (living != mc.field_1724 && !FriendUtils.isFriend((class_1297)living)) {
            event.setBox(changedBox);
         }
      }

   }
}

