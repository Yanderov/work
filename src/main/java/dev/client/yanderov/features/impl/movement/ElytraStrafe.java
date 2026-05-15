package dev.client.yanderov.features.impl.movement;

import antidaunleak.api.annotation.Native;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import dev.client.yanderov.utils.interactions.inv.InventoryTask;
import java.util.List;
import net.minecraft.class_1304;
import net.minecraft.class_1735;
import net.minecraft.class_1802;
import net.minecraft.class_243;

public class ElytraStrafe extends Module implements QuickImports {
   private boolean swappedThisTick = false;

   public ElytraStrafe() {
      super("ElytraStrafe", "Elytra Strafe", ModuleCategory.MOVEMENT);
      this.setup(new Setting[0]);
   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginUltra
   )
   public void onTick(TickEvent e) {
      if (this.state && mc.field_1724 != null && mc.field_1687 != null) {
         if (mc.field_1724.method_24828()) {
            this.swappedThisTick = false;
         } else {
            boolean wearingElytra = mc.field_1724.method_6118(class_1304.field_6174).method_7909().equals(class_1802.field_8833);
            class_1735 elytraSlot = InventoryTask.getSlot(class_1802.field_8833);
            if (elytraSlot != null) {
               if (!wearingElytra && !mc.field_1724.method_6128()) {
                  InventoryTask.moveItem(elytraSlot, 6, false, true);
                  PlayerInteractionHelper.startFallFlying();
                  class_243 v = mc.field_1724.method_18798();
                  mc.field_1724.method_18800(v.field_1352 * 0.8, v.field_1351, v.field_1350 * 0.8);
                  mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352 * 1.1, mc.field_1724.method_18798().field_1351, mc.field_1724.method_18798().field_1350 * 1.1);
                  this.swappedThisTick = true;
               }

               if (this.swappedThisTick) {
                  class_1735 chest = InventoryTask.getSlot(List.of(class_1802.field_22028, class_1802.field_8058, class_1802.field_8523, class_1802.field_8678, class_1802.field_8873, class_1802.field_8577));
                  if (chest != null) {
                     InventoryTask.moveItem(chest, 6, false, true);
                  }

                  this.swappedThisTick = false;
               }

            }
         }
      }
   }
}

