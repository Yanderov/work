package fun.Yanderov.mixins.game.world;

import fun.Yanderov.events.block.BlockUpdateEvent;
import fun.Yanderov.utils.client.managers.event.EventManager;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1937.class})
public abstract class WorldMixin implements QuickImports {
   @Inject(
      method = {"onBlockChanged"},
      at = {@At("RETURN")}
   )
   private void onBlockChangedHook(class_2338 pos, class_2680 oldBlock, class_2680 newBlock, CallbackInfo ci) {
      if (mc.field_1687 == this) {
         EventManager.callEvent(new BlockUpdateEvent(newBlock, pos.method_10062(), BlockUpdateEvent.Type.UPDATE));
      }
   }
}

