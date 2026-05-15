package fun.Yanderov.mixins.player.inventory;

import fun.Yanderov.features.impl.misc.SelfDestruct;
import net.minecraft.class_1713;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_4185;
import net.minecraft.class_490;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_490.class})
public abstract class InventoryScreenMixin {
   @Inject(
      method = {"init"},
      at = {@At("TAIL")}
   )
   private void addDropAllButton(CallbackInfo ci) {
      if (!SelfDestruct.unhooked) {
         class_310 mc = class_310.method_1551();
         class_490 screen = (class_490)this;
         int x = screen.field_22789 / 2 - 40;
         int y = screen.field_22790 / 2 - 120;
         class_4185 dropAllButton = class_4185.method_46430(class_2561.method_30163("Ð’Ñ‹ÐºÐ¸Ð½ÑƒÑ‚ÑŒ Ð²ÑÑ‘"), (button) -> this.dropAllItems(mc)).method_46433(x, y).method_46437(80, 20).method_46431();
         screen.method_37063(dropAllButton);
      }
   }

   private void dropAllItems(class_310 mc) {
      class_746 player = mc.field_1724;
      if (player != null && player.field_7512 != null) {
         for(int i = 9; i < 36; ++i) {
            class_1799 stack = player.method_31548().method_5438(i);
            if (!stack.method_7960()) {
               mc.field_1761.method_2906(player.field_7512.field_7763, i, 1, class_1713.field_7795, player);
            }
         }

         for(int i = 0; i < 9; ++i) {
            class_1799 stack = player.method_31548().method_5438(i);
            if (!stack.method_7960()) {
               mc.field_1761.method_2906(player.field_7512.field_7763, i + 36, 1, class_1713.field_7795, player);
            }
         }

      }
   }
}

