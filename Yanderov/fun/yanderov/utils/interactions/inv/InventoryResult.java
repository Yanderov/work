package fun.Yanderov.utils.interactions.inv;

import fun.Yanderov.utils.display.interfaces.QuickImports;
import net.minecraft.class_1799;
import org.jetbrains.annotations.NotNull;

public record InventoryResult(int slot, boolean found, class_1799 stack) {
   private static final InventoryResult NOT_FOUND_RESULT = new InventoryResult(-1, false, (class_1799)null);

   public static InventoryResult notFound() {
      return NOT_FOUND_RESULT;
   }

   public static @NotNull InventoryResult inOffhand(class_1799 stack) {
      return new InventoryResult(999, true, stack);
   }

   public boolean isHolding() {
      if (QuickImports.mc.field_1724 == null) {
         return false;
      } else {
         return QuickImports.mc.field_1724.method_31548().field_7545 == this.slot;
      }
   }

   public boolean isInHotBar() {
      return this.slot < 9;
   }

   public void switchTo() {
      if (this.found && this.isInHotBar()) {
         InventoryToolkit.switchTo(this.slot);
      }

   }

   public void switchToSilent() {
      if (this.found && this.isInHotBar()) {
         InventoryToolkit.switchToSilent(this.slot);
      }

   }
}

