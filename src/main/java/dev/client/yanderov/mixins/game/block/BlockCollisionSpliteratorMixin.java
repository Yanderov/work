package dev.client.yanderov.mixins.game.block;

import dev.client.yanderov.events.block.BlockCollisionEvent;
import dev.client.yanderov.utils.client.managers.event.EventManager;
import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_5329;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({class_5329.class})
public abstract class BlockCollisionSpliteratorMixin {
   @Redirect(
      method = {"computeNext"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/world/BlockView;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;"
)
   )
   private class_2680 computeNext(class_1922 instance, class_2338 blockPos) {
      BlockCollisionEvent event = new BlockCollisionEvent(blockPos, instance.method_8320(blockPos));
      EventManager.callEvent(event);
      return event.getState();
   }
}

