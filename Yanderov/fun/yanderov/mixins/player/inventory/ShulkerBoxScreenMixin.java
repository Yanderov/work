package fun.Yanderov.mixins.player.inventory;

import fun.Yanderov.features.impl.misc.SelfDestruct;
import fun.Yanderov.utils.display.widget.ContainerBackgroundRender;
import net.minecraft.class_1661;
import net.minecraft.class_1713;
import net.minecraft.class_1733;
import net.minecraft.class_1735;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2480;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_4185;
import net.minecraft.class_465;
import net.minecraft.class_495;
import net.minecraft.class_5537;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_495.class})
public abstract class ShulkerBoxScreenMixin extends class_465 {
   private class_4185 takeAllButton;
   private class_4185 dropAllButton;
   private class_4185 storeAllButton;
   private boolean buttonsAdded = false;
   @Unique
   private static final ContainerBackgroundRender BACKGROUND_RENDER = new ContainerBackgroundRender();

   public ShulkerBoxScreenMixin(class_1733 handler, class_1661 inventory, class_2561 title) {
      super(handler, inventory, title);
   }

   @Inject(
      method = {"render"},
      at = {@At("TAIL")}
   )
   private void onRender(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      if (!SelfDestruct.unhooked) {
         class_310 mc = class_310.method_1551();
         if (!this.buttonsAdded) {
            this.addButtons(mc);
            this.buttonsAdded = true;
         }

      }
   }

   private void addButtons(class_310 mc) {
      int baseX = (this.field_22789 + this.field_2792) / 2;
      int baseY = (this.field_22790 - this.field_2779) / 2;
      this.dropAllButton = class_4185.method_46430(class_2561.method_43470("Ð’Ñ‹Ð±Ñ€Ð¾ÑÐ¸Ñ‚ÑŒ"), (button) -> this.dropAll(mc)).method_46434(baseX, baseY, 80, 20).method_46431();
      this.takeAllButton = class_4185.method_46430(class_2561.method_43470("Ð’Ð·ÑÑ‚ÑŒ Ð²ÑÑ‘"), (button) -> this.takeAll(mc)).method_46434(baseX, baseY + 22, 80, 20).method_46431();
      this.storeAllButton = class_4185.method_46430(class_2561.method_43470("Ð¡Ð»Ð¾Ð¶Ð¸Ñ‚ÑŒ Ð²ÑÑ‘"), (button) -> this.storeAll(mc)).method_46434(baseX, baseY + 44, 80, 20).method_46431();
      this.method_37063(this.dropAllButton);
      this.method_37063(this.takeAllButton);
      this.method_37063(this.storeAllButton);
   }

   private void takeAll(class_310 mc) {
      class_746 player = mc.field_1724;
      if (player != null && player.field_7512 != null) {
         for(class_1735 slot : player.field_7512.field_7761) {
            if (slot.field_7871 != player.method_31548() && slot.method_7681()) {
               mc.field_1761.method_2906(player.field_7512.field_7763, slot.field_7874, 0, class_1713.field_7794, player);
            }
         }

      }
   }

   private void dropAll(class_310 mc) {
      class_746 player = mc.field_1724;
      if (player != null && player.field_7512 != null) {
         for(class_1735 slot : player.field_7512.field_7761) {
            if (slot.field_7871 != player.method_31548() && slot.method_7681()) {
               mc.field_1761.method_2906(player.field_7512.field_7763, slot.field_7874, 1, class_1713.field_7795, player);
            }
         }

      }
   }

   private void storeAll(class_310 mc) {
      class_746 player = mc.field_1724;
      if (player != null && player.field_7512 != null) {
         for(class_1735 slot : player.field_7512.field_7761) {
            if (slot.field_7871 == player.method_31548() && slot.method_7681() && !this.isShulkerBox(slot.method_7677()) && !this.isBag(slot.method_7677())) {
               mc.field_1761.method_2906(player.field_7512.field_7763, slot.field_7874, 0, class_1713.field_7794, player);
            }
         }

      }
   }

   @Unique
   private boolean isShulkerBox(class_1799 stack) {
      if (stack.method_7960()) {
         return false;
      } else {
         class_1792 var3 = stack.method_7909();
         if (var3 instanceof class_1747) {
            class_1747 blockItem = (class_1747)var3;
            return blockItem.method_7711() instanceof class_2480;
         } else {
            return false;
         }
      }
   }

   @Unique
   private boolean isBag(class_1799 stack) {
      return stack.method_7960() ? false : stack.method_7909() instanceof class_5537;
   }
}

