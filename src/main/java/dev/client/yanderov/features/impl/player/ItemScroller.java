package dev.client.yanderov.features.impl.player;

import antidaunleak.api.annotation.Native;
import dev.client.yanderov.events.container.HandledScreenEvent;
import dev.client.yanderov.events.item.ClickSlotEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import dev.client.yanderov.utils.interactions.inv.InventoryTask;
import dev.client.yanderov.utils.math.time.StopWatch;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1792;

public class ItemScroller extends Module {
   private final StopWatch stopWatch = new StopWatch();
   private final SliderSettings scrollerSetting = (new SliderSettings("Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿Ñ€Ð¾ÐºÑ€ÑƒÑ‚ÐºÐ¸ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚Ð¾Ð²", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ð·Ð°Ð´ÐµÑ€Ð¶ÐºÑƒ Ð¿Ñ€Ð¾ÐºÑ€ÑƒÑ‚ÐºÐ¸ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚Ð¾Ð²")).setValue(50.0F).range(0, 200);

   public ItemScroller() {
      super("ItemScroller", "Item Scroller", ModuleCategory.PLAYER);
      this.setup(new Setting[]{this.scrollerSetting});
   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginMutation
   )
   public void onHandledScreen(HandledScreenEvent e) {
      class_1735 hoverSlot = e.getSlotHover();
      class_1713 actionType = PlayerInteractionHelper.isKey(mc.field_1690.field_1869) ? class_1713.field_7795 : (PlayerInteractionHelper.isKey(mc.field_1690.field_1886) ? class_1713.field_7794 : null);
      if (PlayerInteractionHelper.isKey(mc.field_1690.field_1832) && !PlayerInteractionHelper.isKey(mc.field_1690.field_1867) && hoverSlot != null && hoverSlot.method_7681() && actionType != null && this.stopWatch.every((double)this.scrollerSetting.getValue())) {
         mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, hoverSlot.field_7874, actionType.equals(class_1713.field_7795) ? 1 : 0, actionType, mc.field_1724);
      }

   }

   @EventHandler
   public void onClickSlot(ClickSlotEvent e) {
      int slotId = e.getSlotId();
      if (slotId >= 0 && slotId <= mc.field_1724.field_7512.field_7761.size()) {
         class_1735 slot = mc.field_1724.field_7512.method_7611(slotId);
         class_1792 item = slot.method_7677().method_7909();
         if (item != null && PlayerInteractionHelper.isKey(mc.field_1690.field_1832) && PlayerInteractionHelper.isKey(mc.field_1690.field_1867) && this.stopWatch.every((double)50.0F)) {
            InventoryTask.slots().filter((s) -> s.method_7677().method_7909().equals(item) && s.field_7871.equals(slot.field_7871)).forEach((s) -> mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, s.field_7874, 1, e.getActionType(), mc.field_1724));
         }

      }
   }
}

