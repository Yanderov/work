package fun.Yanderov.mixins.player.entity;

import fun.Yanderov.utils.features.aura.warp.TurnsConnection;
import net.minecraft.class_1268;
import net.minecraft.class_2886;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_2886.class})
public class PlayerInteractItemC2SPacketMixin {
   @Mutable
   @Shadow
   @Final
   private float field_51930;
   @Mutable
   @Shadow
   @Final
   private float field_51931;

   @Inject(
      method = {"<init>(Lnet/minecraft/util/Hand;IFF)V"},
      at = {@At("RETURN")}
   )
   private void modifyRotation(class_1268 hand, int sequence, float yaw, float pitch, CallbackInfo ci) {
      this.field_51930 = TurnsConnection.INSTANCE.getRotation().getYaw();
      this.field_51931 = TurnsConnection.INSTANCE.getRotation().getPitch();
   }
}

