package dev.client.yanderov.mixins.player.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.authlib.GameProfile;
import dev.client.yanderov.events.block.PushEvent;
import dev.client.yanderov.events.container.CloseScreenEvent;
import dev.client.yanderov.events.item.UsingItemEvent;
import dev.client.yanderov.events.player.MotionEvent;
import dev.client.yanderov.events.player.MoveEvent;
import dev.client.yanderov.events.player.PlayerTravelEvent;
import dev.client.yanderov.events.player.PostMotionEvent;
import dev.client.yanderov.events.player.PostTickEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.impl.movement.AutoSprint;
import dev.client.yanderov.features.impl.movement.NoSlow;
import dev.client.yanderov.utils.client.managers.event.EventManager;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.features.aura.warp.TurnsConnection;
import dev.client.yanderov.utils.interactions.inv.InventoryFlowManager;
import dev.client.yanderov.utils.interactions.simulate.Simulations;
import net.minecraft.class_1313;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_638;
import net.minecraft.class_742;
import net.minecraft.class_744;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_746.class})
public abstract class ClientPlayerEntityMixin extends class_742 {
   @Final
   @Shadow
   protected class_310 field_3937;
   @Shadow
   public class_744 field_3913;
   private double prevX = (double)0.0F;
   private double prevZ = (double)0.0F;
   private float prevBodyYaw = 0.0F;
   private boolean initialized = false;

   @Shadow
   public abstract float method_5695(float var1);

   @Shadow
   public abstract float method_5705(float var1);

   @Shadow
   protected abstract void method_3148(float var1, float var2);

   public ClientPlayerEntityMixin(class_638 world, GameProfile profile) {
      super(world, profile);
   }

   @Inject(
      method = {"tick"},
      at = {@At("HEAD")}
   )
   public void tick(CallbackInfo info) {
      if (this.field_3937.field_1724 != null && this.field_3937.field_1687 != null) {
         EventManager.callEvent(new TickEvent());
      }

   }

   @Inject(
      method = {"tick"},
      at = {@At("HEAD")}
   )
   public void onTick(CallbackInfo ci) {
      if (!this.initialized && QuickImports.mc.field_1724 != null) {
         this.prevX = QuickImports.mc.field_1724.method_23317();
         this.prevZ = QuickImports.mc.field_1724.method_23321();
         this.prevBodyYaw = QuickImports.mc.field_1724.method_43078();
         this.initialized = true;
      }

   }

   @Inject(
      method = {"tick"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;tick()V",
   shift = Shift.AFTER
)}
   )
   public void postTick(CallbackInfo callbackInfo) {
      if (this.field_3937.field_1724 != null && this.field_3937.field_1687 != null) {
         EventManager.callEvent(new PostTickEvent());
      }

   }

   @Inject(
      method = {"tickMovement"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/input/Input;tick()V",
   shift = Shift.AFTER
)}
   )
   private void onInputTick(CallbackInfo ci) {
      if (QuickImports.mc.field_1724 != null) {
         PlayerTravelEvent event = new PlayerTravelEvent(class_243.field_1353, false);
         EventManager.callEvent(event);
      }
   }

   @ModifyExpressionValue(
      method = {"sendMovementPackets", "tick"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/ClientPlayerEntity;getYaw()F"
)}
   )
   private float hookSilentRotationYaw(float original) {
      if (QuickImports.mc.field_1724 != null) {
         float currentYaw = TurnsConnection.INSTANCE.getRotation().getYaw();
         float newBodyYaw = Simulations.calculateBodyYaw(currentYaw, this.prevBodyYaw, this.prevX, this.prevZ, QuickImports.mc.field_1724.method_23317(), QuickImports.mc.field_1724.method_23321(), QuickImports.mc.field_1724.field_6251);
         this.prevBodyYaw = newBodyYaw;
         this.prevX = QuickImports.mc.field_1724.method_23317();
         this.prevZ = QuickImports.mc.field_1724.method_23321();
         QuickImports.mc.field_1724.method_5636(newBodyYaw);
         return currentYaw;
      } else {
         return original;
      }
   }

   @ModifyExpressionValue(
      method = {"sendMovementPackets", "tick"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/ClientPlayerEntity;getPitch()F"
)}
   )
   private float hookSilentRotationPitch(float original) {
      return TurnsConnection.INSTANCE.getRotation().getPitch();
   }

   @Inject(
      method = {"closeHandledScreen"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void closeHandledScreenHook(CallbackInfo info) {
      CloseScreenEvent event = new CloseScreenEvent(this.field_3937.field_1755);
      EventManager.callEvent(event);
      if (event.isCancelled()) {
         info.cancel();
      }

   }

   @ModifyExpressionValue(
      method = {"tickMovement"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"
)}
   )
   private boolean usingItemHook(boolean original) {
      if (original) {
         UsingItemEvent event = new UsingItemEvent((byte)1);
         EventManager.callEvent(event);
         if (event.isCancelled()) {
            return false;
         }

         AutoSprint.getInstance();
         AutoSprint.tickStop = 1;
      }

      return original;
   }

   @Inject(
      method = {"sendMovementPackets"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void preMotion(CallbackInfo ci) {
      MotionEvent event = new MotionEvent(this.method_23317(), this.method_23318(), this.method_23321(), this.method_5705(1.0F), this.method_5695(1.0F), this.method_24828());
      EventManager.callEvent(event);
      if (event.isCancelled()) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"move"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V"
)},
      cancellable = true
   )
   public void onMoveHook(class_1313 movementType, class_243 movement, CallbackInfo ci) {
      MoveEvent event = new MoveEvent(movement);
      EventManager.callEvent(event);
      double d = this.method_23317();
      double e = this.method_23321();
      super.method_5784(movementType, event.getMovement());
      this.method_3148((float)(this.method_23317() - d), (float)(this.method_23321() - e));
      ci.cancel();
   }

   @Inject(
      method = {"sendMovementPackets"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void postMotion(CallbackInfo ci) {
      PostMotionEvent postMotionEvent = new PostMotionEvent();
      EventManager.callEvent(postMotionEvent);
      InventoryFlowManager.postMotion();
      if (postMotionEvent.isCancelled()) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"pushOutOfBlocks"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void pushOutOfBlocks(double x, double z, CallbackInfo ci) {
      PushEvent event = new PushEvent(PushEvent.Type.BLOCK);
      EventManager.callEvent(event);
      if (event.isCancelled()) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"shouldStopSprinting"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"
)},
      cancellable = true
   )
   public void shouldStopSprintingHook(CallbackInfoReturnable cir) {
      if (AutoSprint.getInstance().isState() && NoSlow.getInstance().isState()) {
         cir.setReturnValue(false);
      }

   }

   @Inject(
      method = {"canStartSprinting"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"
)},
      cancellable = true
   )
   public void canStartSprintingHook(CallbackInfoReturnable cir) {
      if (AutoSprint.getInstance().isState() && NoSlow.getInstance().isState()) {
         cir.setReturnValue(false);
      }

   }
}

