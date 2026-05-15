package fun.Yanderov.features.impl.combat;

import fun.Yanderov.events.player.AttackEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import net.minecraft.class_310;

public class ShiftTap extends Module {
   private long shiftTapEndTime = 0L;
   private boolean isModuleControllingSneak = false;
   private int shiftTapDuration = 100;
   private final class_310 mc = class_310.method_1551();

   public ShiftTap() {
      super("ShiftTap", "Shift Tap", ModuleCategory.COMBAT);
   }

   private void startShiftTap() {
      this.shiftTapEndTime = System.currentTimeMillis() + 25L;
      if (!this.isModuleControllingSneak) {
         this.mc.field_1690.field_1832.method_23481(true);
         this.isModuleControllingSneak = true;
      }

   }

   private void stopShiftTap() {
      if (this.isModuleControllingSneak) {
         this.mc.field_1690.field_1832.method_23481(false);
         this.isModuleControllingSneak = false;
      }

   }

   @EventHandler
   public void onAttack(AttackEvent event) {
      if (this.mc.field_1724 != null) {
         this.startShiftTap();
      }
   }

   @EventHandler
   public void onTick(TickEvent event) {
      if (this.mc.field_1724 != null && !this.mc.field_1724.method_7325()) {
         long currentTime = System.currentTimeMillis();
         if (this.isModuleControllingSneak && currentTime > this.shiftTapEndTime) {
            this.stopShiftTap();
         }

      } else {
         this.stopShiftTap();
      }
   }
}

