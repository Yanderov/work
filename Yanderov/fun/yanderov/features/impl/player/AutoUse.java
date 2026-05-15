package fun.Yanderov.features.impl.player;

import antidaunleak.api.annotation.Native;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.MultiSelectSetting;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import fun.Yanderov.utils.interactions.inv.InventoryFlowManager;
import fun.Yanderov.utils.interactions.inv.InventoryTask;
import fun.Yanderov.utils.interactions.item.ItemToolkit;
import fun.Yanderov.utils.math.script.Script;
import net.minecraft.class_1268;
import net.minecraft.class_1294;
import net.minecraft.class_1735;
import net.minecraft.class_1799;

public class AutoUse extends Module {
   private final Script script = new Script();
   private final MultiSelectSetting multiSetting = (new MultiSelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ, Ñ‡Ñ‚Ð¾ Ð±ÑƒÐ´ÐµÑ‚ Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ñ‚ÑŒÑÑ")).value("Eat", "Invisibility");

   public AutoUse() {
      super("AutoUse", "Auto Use", ModuleCategory.PLAYER);
      this.setup(new Setting[]{this.multiSetting});
   }

   public void deactivate() {
      this.script.update();
   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginUltra
   )
   public void onTick(TickEvent e) {
      for(String string : this.multiSetting.getSelected()) {
         switch (string) {
            case "Eat":
               class_1735 slot = InventoryTask.getFoodMaxSaturationSlot();
               if (slot != null && mc.field_1724.method_7344().method_7587() && this.swapAndEat(slot)) {
                  return;
               }
               break;
            case "Invisibility":
               class_1735 slot = InventoryTask.getPotion(class_1294.field_5905);
               if (slot != null && !PlayerInteractionHelper.isPotionActive(class_1294.field_5905) && this.swapAndEat(slot)) {
                  return;
               }
         }
      }

      this.script.update();
   }

   public boolean swapAndEat(class_1735 slot) {
      class_1799 stack = slot.method_7677();
      if (!mc.field_1724.method_7357().method_7904(stack)) {
         if (!mc.field_1724.method_6079().equals(stack)) {
            if (InventoryFlowManager.script.isFinished()) {
               InventoryTask.swapHand(slot, class_1268.field_5810, true, true);
               this.script.cleanup().addTickStep(0, () -> InventoryTask.swapHand(slot, class_1268.field_5810, true, true));
            }
         } else {
            ItemToolkit.INSTANCE.useHand(class_1268.field_5810);
         }

         return true;
      } else {
         return false;
      }
   }
}

