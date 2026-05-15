package dev.client.yanderov.features.impl.misc;

import dev.client.yanderov.events.keyboard.KeyEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import net.minecraft.class_304;
import net.minecraft.class_315;

public class LockSlot extends Module {
   private final BooleanSetting slot1 = (new BooleanSetting("Slot1", "Ð›Ð¾Ñ‡Ð¸Ñ‚ÑŒ ÑÐ»Ð¾Ñ‚ 1")).setValue(false);
   private final BooleanSetting slot2 = (new BooleanSetting("Slot2", "Ð›Ð¾Ñ‡Ð¸Ñ‚ÑŒ ÑÐ»Ð¾Ñ‚ 2")).setValue(false);
   private final BooleanSetting slot3 = (new BooleanSetting("Slot3", "Ð›Ð¾Ñ‡Ð¸Ñ‚ÑŒ ÑÐ»Ð¾Ñ‚ 3")).setValue(false);
   private final BooleanSetting slot4 = (new BooleanSetting("Slot4", "Ð›Ð¾Ñ‡Ð¸Ñ‚ÑŒ ÑÐ»Ð¾Ñ‚ 4")).setValue(false);
   private final BooleanSetting slot5 = (new BooleanSetting("Slot5", "Ð›Ð¾Ñ‡Ð¸Ñ‚ÑŒ ÑÐ»Ð¾Ñ‚ 5")).setValue(false);
   private final BooleanSetting slot6 = (new BooleanSetting("Slot6", "Ð›Ð¾Ñ‡Ð¸Ñ‚ÑŒ ÑÐ»Ð¾Ñ‚ 6")).setValue(false);
   private final BooleanSetting slot7 = (new BooleanSetting("Slot7", "Ð›Ð¾Ñ‡Ð¸Ñ‚ÑŒ ÑÐ»Ð¾Ñ‚ 7")).setValue(false);
   private final BooleanSetting slot8 = (new BooleanSetting("Slot8", "Ð›Ð¾Ñ‡Ð¸Ñ‚ÑŒ ÑÐ»Ð¾Ñ‚ 8")).setValue(false);
   private final BooleanSetting slot9 = (new BooleanSetting("Slot9", "Ð›Ð¾Ñ‡Ð¸Ñ‚ÑŒ ÑÐ»Ð¾Ñ‚ 9")).setValue(false);

   public LockSlot() {
      super("LockSlot", "Lock hotbar slots from dropping", ModuleCategory.MISC);
      this.setup(new Setting[]{this.slot1, this.slot2, this.slot3, this.slot4, this.slot5, this.slot6, this.slot7, this.slot8, this.slot9});
   }

   @EventHandler
   public void onKey(KeyEvent e) {
      if (mc != null && mc.field_1724 != null && mc.field_1687 != null) {
         class_315 options = mc.field_1690;
         if (options != null) {
            class_304 dropKey = options.field_1869;
            if (dropKey != null) {
               int dropKeyCode = dropKey.method_1429().method_1444();
               if (e.isKeyDown(dropKeyCode)) {
                  int selected = mc.field_1724.method_31548().field_7545;
                  int slotNumber = selected + 1;
                  if (this.isLocked(slotNumber)) {
                  }

               }
            }
         }
      }
   }

   private boolean isLocked(int slotNumber) {
      boolean var10000;
      switch (slotNumber) {
         case 1 -> var10000 = this.slot1.isValue();
         case 2 -> var10000 = this.slot2.isValue();
         case 3 -> var10000 = this.slot3.isValue();
         case 4 -> var10000 = this.slot4.isValue();
         case 5 -> var10000 = this.slot5.isValue();
         case 6 -> var10000 = this.slot6.isValue();
         case 7 -> var10000 = this.slot7.isValue();
         case 8 -> var10000 = this.slot8.isValue();
         case 9 -> var10000 = this.slot9.isValue();
         default -> var10000 = false;
      }

      return var10000;
   }
}

