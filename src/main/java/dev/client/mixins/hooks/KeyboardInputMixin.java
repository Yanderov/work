package dev.client.mixins.hooks;

import dev.client.WildClient;
import dev.client.event.classes.InputEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.input.Input;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.client.option.GameOptions;
import net.minecraft.util.PlayerInput;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({KeyboardInput.class})
public class KeyboardInputMixin extends Input {
   @Shadow
   @Final
   private GameOptions settings;

   @Unique
   private float abobaGetMovementMultiplier(boolean positive, boolean negative) {
      if (positive == negative) {
         return 0.0F;
      } else {
         return positive ? 1.0F : -1.0F;
      }
   }

   @Inject(
      method = {"tick"},
      at = {@At(
   value = "FIELD",
   target = "Lnet/minecraft/client/input/KeyboardInput;playerInput:Lnet/minecraft/util/PlayerInput;",
   ordinal = 0,
   shift = Shift.AFTER
)},
      cancellable = true
   )
   public void injectInputEvent(CallbackInfo ci) {
      InputEvent event = new InputEvent(this.abobaGetMovementMultiplier(this.playerInput.forward(), this.playerInput.backward()), this.abobaGetMovementMultiplier(this.playerInput.left(), this.playerInput.right()), this.settings.jumpKey.isPressed(), this.settings.sneakKey.isPressed());
      WildClient.INSTANCE.getEventManager().hookEvent(event);
      this.movementForward = event.getForward();
      this.movementSideways = event.getStrafe();
      this.playerInput = new PlayerInput(this.movementForward > 0.0F, this.movementForward < 0.0F, this.movementSideways > 0.0F, this.movementSideways < 0.0F, event.isJump(), event.isSneak(), this.settings.sprintKey.isPressed());
      ci.cancel();
   }
}
