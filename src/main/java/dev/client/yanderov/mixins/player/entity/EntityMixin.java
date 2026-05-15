package dev.client.yanderov.mixins.player.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.client.yanderov.events.player.BoundingBoxControlEvent;
import dev.client.yanderov.events.player.PlayerVelocityStrafeEvent;
import dev.client.yanderov.utils.client.managers.event.EventManager;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.features.aura.warp.TurnsConnection;
import net.minecraft.class_1297;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1297.class})
public abstract class EntityMixin implements QuickImports {
   @Shadow
   private class_238 field_6005;
   @Shadow
   public float field_6031;
   @Unique
   private final class_310 client = class_310.method_1551();

   @Redirect(
      method = {"updateVelocity"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/Entity;movementInputToVelocity(Lnet/minecraft/util/math/Vec3d;FF)Lnet/minecraft/util/math/Vec3d;"
)
   )
   public class_243 hookVelocity(class_243 movementInput, float speed, float yaw) {
      if (this == mc.field_1724) {
         PlayerVelocityStrafeEvent event = new PlayerVelocityStrafeEvent(movementInput, speed, yaw, class_1297.method_18795(movementInput, speed, yaw));
         EventManager.callEvent(event);
         return event.getVelocity();
      } else {
         return class_1297.method_18795(movementInput, speed, yaw);
      }
   }

   @Inject(
      method = {"getBoundingBox"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public final void getBoundingBox(CallbackInfoReturnable cir) {
      BoundingBoxControlEvent event = new BoundingBoxControlEvent(this.field_6005, (class_1297)this);
      EventManager.callEvent(event);
      cir.setReturnValue(event.getBox());
   }

   @ModifyVariable(
      method = {"getRotationVector(FF)Lnet/minecraft/util/math/Vec3d;"},
      at = @At("HEAD"),
      ordinal = 0,
      argsOnly = true
   )
   private float modifyPitch(float pitch) {
      return this instanceof class_746 && TurnsConnection.INSTANCE.getCurrentAngle() != null ? TurnsConnection.INSTANCE.getCurrentAngle().getPitch() : pitch;
   }

   @ModifyExpressionValue(
      method = {"move"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/Entity;isControlledByPlayer()Z"
)}
   )
   public boolean isControlledByPlayerHook(boolean original) {
      return this == mc.field_1724 ? false : original;
   }
}

