package fun.Yanderov.mixins.client.screen.ingame;

import fun.Yanderov.features.impl.misc.SelfDestruct;
import fun.Yanderov.utils.features.aura.warp.TurnsConnection;
import net.minecraft.class_1297;
import net.minecraft.class_340;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({class_340.class})
public abstract class DebugHudMixin {
   @Redirect(
      method = {"getLeftText"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/Entity;getYaw()F"
)
   )
   private float redirectYaw(class_1297 entity) {
      return SelfDestruct.unhooked ? entity.method_36454() : TurnsConnection.INSTANCE.getRotation().getYaw();
   }

   @Redirect(
      method = {"getLeftText"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/Entity;getPitch()F"
)
   )
   private float redirectPitch(class_1297 entity) {
      return SelfDestruct.unhooked ? entity.method_36455() : TurnsConnection.INSTANCE.getRotation().getPitch();
   }
}

