package dev.client.mixins.hooks;

import dev.client.WildClient;
import dev.client.event.classes.PlaceBlockEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin({BlockItem.class})
public class BlockItemMixin {
   @Inject(
      method = {"place(Lnet/minecraft/item/ItemPlacementContext;Lnet/minecraft/block/BlockState;)Z"},
      at = {@At("RETURN")}
   )
   private void onPlace(ItemPlacementContext context, BlockState state, CallbackInfoReturnable<Boolean> cir) {
      if (context.getWorld().isClient) {
         PlaceBlockEvent placeBlockEvent = new PlaceBlockEvent(context.getBlockPos(), state.getBlock());
         WildClient.INSTANCE.getEventManager().hookEvent(placeBlockEvent);
      }

   }
}
