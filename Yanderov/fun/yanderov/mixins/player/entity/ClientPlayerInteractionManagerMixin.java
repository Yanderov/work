package fun.Yanderov.mixins.player.entity;

import fun.Yanderov.events.block.BlockBreakingEvent;
import fun.Yanderov.events.block.BreakBlockEvent;
import fun.Yanderov.events.item.ClickSlotEvent;
import fun.Yanderov.events.item.UsingItemEvent;
import fun.Yanderov.events.player.AttackEvent;
import fun.Yanderov.utils.client.managers.event.EventManager;
import fun.Yanderov.utils.interactions.item.ItemToolkit;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_636;
import net.minecraft.class_1269.class_9861;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_636.class})
public class ClientPlayerInteractionManagerMixin {
   @Inject(
      method = {"attackEntity"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void attackEntityHook(class_1657 player, class_1297 target, CallbackInfo info) {
      AttackEvent event = new AttackEvent(target);
      EventManager.callEvent(event);
      if (event.isCancelled()) {
         info.cancel();
      }

   }

   @Inject(
      method = {"interactItem"},
      at = {@At("RETURN")}
   )
   public void interactItemHook(class_1657 player, class_1268 hand, CallbackInfoReturnable cir) {
      Object var5 = cir.getReturnValue();
      if (var5 instanceof class_1269.class_9860 success) {
         if (!success.comp_2909().equals(class_9861.field_52427)) {
            UsingItemEvent event = new UsingItemEvent((byte)0);
            EventManager.callEvent(event);
         }
      }

   }

   @Inject(
      method = {"stopUsingItem"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void stopUsingItemHook(CallbackInfo ci) {
      UsingItemEvent event = new UsingItemEvent((byte)2);
      EventManager.callEvent(event);
      if (ItemToolkit.INSTANCE.isUseItem()) {
         ItemToolkit.INSTANCE.setUseItem(false);
         ci.cancel();
      }

   }

   @Inject(
      method = {"interactItem"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void gameModeHook(class_1657 player, class_1268 hand, CallbackInfoReturnable cir) {
      UsingItemEvent event = new UsingItemEvent((byte)-1);
      EventManager.callEvent(event);
      if (event.isCancelled()) {
         cir.setReturnValue(class_1269.field_5811);
      }

   }

   @Inject(
      method = {"clickSlot"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void clickSlotHook(int syncId, int slotId, int button, class_1713 actionType, class_1657 player, CallbackInfo info) {
      ClickSlotEvent event = new ClickSlotEvent(syncId, slotId, button, actionType);
      EventManager.callEvent(event);
      if (event.isCancelled()) {
         info.cancel();
      }

   }

   @Inject(
      method = {"updateBlockBreakingProgress"},
      at = {@At("HEAD")}
   )
   private void injectBlockBreaking(class_2338 pos, class_2350 direction, CallbackInfoReturnable cir) {
      EventManager.callEvent(new BlockBreakingEvent(pos, direction));
   }

   @Inject(
      method = {"breakBlock"},
      at = {@At("RETURN")}
   )
   private void injectBreakBlock(class_2338 pos, CallbackInfoReturnable cir) {
      EventManager.callEvent(new BreakBlockEvent(pos));
   }
}

