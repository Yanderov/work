package fun.Yanderov.mixins.game.block;

import fun.Yanderov.events.block.BlockEntityProgressEvent;
import fun.Yanderov.utils.client.managers.event.EventManager;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_2586.class})
public class BlockEntityMixin {
   @Inject(
      method = {"<init>"},
      at = {@At("RETURN")}
   )
   public void initHook(class_2591 type, class_2338 pos, class_2680 state, CallbackInfo ci) {
      class_2586 blockEntity = (class_2586)this;
      if (blockEntity != null) {
         EventManager.callEvent(new BlockEntityProgressEvent(blockEntity, BlockEntityProgressEvent.Type.ADD));
      }

   }

   @Inject(
      method = {"markRemoved"},
      at = {@At("HEAD")}
   )
   private void markRemovedHook(CallbackInfo ci) {
      class_2586 blockEntity = (class_2586)this;
      if (blockEntity != null) {
         EventManager.callEvent(new BlockEntityProgressEvent(blockEntity, BlockEntityProgressEvent.Type.REMOVE));
      }

   }
}

