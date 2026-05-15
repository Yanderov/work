package dev.client.yanderov.mixins.player.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.client.yanderov.events.block.PushEvent;
import dev.client.yanderov.events.player.KeepSprintEvent;
import dev.client.yanderov.events.player.PlayerTravelEvent;
import dev.client.yanderov.events.player.SwimmingEvent;
import dev.client.yanderov.utils.client.managers.event.EventManager;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.features.aura.warp.TurnsConnection;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1657.class})
public abstract class PlayerEntityMixin implements QuickImports {
   @Inject(
      method = {"isPushedByFluids"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void isPushedByFluids(CallbackInfoReturnable cir) {
      PushEvent event = new PushEvent(PushEvent.Type.WATER);
      EventManager.callEvent(event);
      if (event.isCancelled()) {
         cir.setReturnValue(false);
      }

   }

   @Inject(
      method = {"attack"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/player/PlayerEntity;setSprinting(Z)V",
   shift = Shift.AFTER
)}
   )
   public void attackHook(CallbackInfo callbackInfo) {
      EventManager.callEvent(new KeepSprintEvent());
   }

   @ModifyExpressionValue(
      method = {"attack"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/player/PlayerEntity;getYaw()F"
)}
   )
   private float hookFixRotation(float original) {
      return TurnsConnection.INSTANCE.getMoveRotation().getYaw();
   }

   @ModifyExpressionValue(
      method = {"travel"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/player/PlayerEntity;getRotationVector()Lnet/minecraft/util/math/Vec3d;"
)}
   )
   public class_243 travelHook(class_243 vec3d) {
      SwimmingEvent event = new SwimmingEvent(vec3d);
      EventManager.callEvent(event);
      return event.getVector();
   }

   @Inject(
      method = {"travel"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onTravelPre(class_243 movementInput, CallbackInfo ci) {
      if (mc.field_1724 != null) {
         PlayerTravelEvent event = new PlayerTravelEvent(movementInput, true);
         EventManager.callEvent(event);
         if (event.isCancelled()) {
            ci.cancel();
         }

      }
   }

   @Inject(
      method = {"travel"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void onTravelPost(class_243 movementInput, CallbackInfo ci) {
      if (mc.field_1724 != null) {
         PlayerTravelEvent event = new PlayerTravelEvent(movementInput, false);
         EventManager.callEvent(event);
         if (event.isCancelled()) {
         }

      }
   }
}

