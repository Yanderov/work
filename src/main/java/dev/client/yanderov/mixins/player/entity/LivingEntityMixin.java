package dev.client.yanderov.mixins.player.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.client.yanderov.events.block.PushEvent;
import dev.client.yanderov.events.item.SwingDurationEvent;
import dev.client.yanderov.events.player.EntityDeathEvent;
import dev.client.yanderov.events.player.JumpEvent;
import dev.client.yanderov.utils.client.managers.event.EventManager;
import dev.client.yanderov.utils.features.aura.warp.Turns;
import dev.client.yanderov.utils.features.aura.warp.TurnsConnection;
import dev.client.yanderov.utils.features.aura.warp.TurnsConstructor;
import net.minecraft.class_1282;
import net.minecraft.class_1292;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_6880;
import net.minecraft.class_746;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1309.class})
public abstract class LivingEntityMixin {
   @Shadow
   public float field_6283;
   @Unique
   private final class_310 client = class_310.method_1551();

   @Shadow
   public abstract boolean method_6059(class_6880 var1);

   @Shadow
   public abstract @Nullable class_1293 method_6112(class_6880 var1);

   @Shadow
   public abstract boolean method_20232();

   @Inject(
      method = {"isPushable"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void isPushable(CallbackInfoReturnable infoReturnable) {
      PushEvent event = new PushEvent(PushEvent.Type.COLLISION);
      EventManager.callEvent(event);
      if (event.isCancelled()) {
         infoReturnable.setReturnValue(false);
      }

   }

   @Redirect(
      method = {"calcGlidingVelocity"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/LivingEntity;getPitch()F"
)
   )
   private float hookModifyFallFlyingPitch(class_1309 instance) {
      if (this != class_310.method_1551().field_1724) {
         return instance.method_36455();
      } else {
         TurnsConnection rotationManager = TurnsConnection.INSTANCE;
         Turns rotation = rotationManager.getRotation();
         TurnsConstructor configurable = rotationManager.getCurrentRotationPlan();
         return rotation != null && configurable != null && configurable.isMoveCorrection() && !configurable.isChangeLook() ? rotation.getPitch() : instance.method_36455();
      }
   }

   @Redirect(
      method = {"calcGlidingVelocity"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/LivingEntity;getRotationVector()Lnet/minecraft/util/math/Vec3d;"
)
   )
   private class_243 hookModifyFallFlyingRotationVector(class_1309 original) {
      if (this != class_310.method_1551().field_1724) {
         return original.method_5720();
      } else {
         TurnsConnection rotationManager = TurnsConnection.INSTANCE;
         Turns rotation = rotationManager.getRotation();
         TurnsConstructor configurable = rotationManager.getCurrentRotationPlan();
         return rotation != null && configurable != null && configurable.isMoveCorrection() && !configurable.isChangeLook() ? rotation.toVector() : original.method_5720();
      }
   }

   @Inject(
      method = {"jump"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void jump(CallbackInfo info) {
      if (this instanceof class_746 player) {
         JumpEvent event = new JumpEvent(player);
         EventManager.callEvent(event);
         if (event.isCancelled()) {
            info.cancel();
         }
      }

   }

   @ModifyExpressionValue(
      method = {"jump"},
      at = {@At(
   value = "NEW",
   target = "(DDD)Lnet/minecraft/util/math/Vec3d;"
)}
   )
   private class_243 hookFixRotation(class_243 original) {
      if (this != this.client.field_1724) {
         return original;
      } else {
         float yaw = TurnsConnection.INSTANCE.getMoveRotation().getYaw() * ((float)Math.PI / 180F);
         return new class_243((double)(-class_3532.method_15374(yaw) * 0.2F), (double)0.0F, (double)(class_3532.method_15362(yaw) * 0.2F));
      }
   }

   @ModifyExpressionValue(
      method = {"calcGlidingVelocity"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/LivingEntity;getPitch()F"
)}
   )
   private float hookModifyFallFlyingPitch(float original) {
      return this != this.client.field_1724 ? original : TurnsConnection.INSTANCE.getMoveRotation().getPitch();
   }

   @ModifyExpressionValue(
      method = {"calcGlidingVelocity"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/LivingEntity;getRotationVector()Lnet/minecraft/util/math/Vec3d;"
)}
   )
   private class_243 hookModifyFallFlyingRotationVector(class_243 original) {
      return this != this.client.field_1724 ? original : TurnsConnection.INSTANCE.getMoveRotation().toVector();
   }

   @Inject(
      method = {"getHandSwingDuration"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void swingProgressHook(CallbackInfoReturnable cir) {
      if (this == this.client.field_1724) {
         SwingDurationEvent event = new SwingDurationEvent();
         EventManager.callEvent(event);
         if (event.isCancelled()) {
            float animation = event.getAnimation();
            if (class_1292.method_5576(this.client.field_1724)) {
               animation *= (float)(6 - (1 + class_1292.method_5575(this.client.field_1724)));
            } else {
               animation *= (float)(this.method_6059(class_1294.field_5901) ? 6 + (1 + this.method_6112(class_1294.field_5901).method_5578()) * 2 : 6);
            }

            cir.setReturnValue((int)animation);
         }

      }
   }

   @Inject(
      method = {"onDeath"},
      at = {@At("HEAD")}
   )
   private void onDeath(class_1282 source, CallbackInfo ci) {
      class_1309 entity = (class_1309)this;
      EntityDeathEvent event = new EntityDeathEvent(entity, source);
      EventManager.callEvent(event);
   }

   @Inject(
      method = {"handleStatus"},
      at = {@At("HEAD")}
   )
   private void handleStatus(byte status, CallbackInfo ci) {
      if (status == 3) {
         class_1309 entity = (class_1309)this;
         EntityDeathEvent event = new EntityDeathEvent(entity, (class_1282)null);
         EventManager.callEvent(event);
      }

   }
}

