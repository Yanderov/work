package dev.client.mixins.hooks;

import dev.client.WildClient;
import dev.client.event.classes.BoundingBoxControlEvent;
import dev.client.event.classes.FireworkEvent;
import dev.client.event.classes.MoveCorrectionEvent;
import dev.client.event.classes.MoveOrEvent;
import dev.client.event.classes.PostMoveEvent;
import dev.client.modules.impl.player.NoPush;
import dev.client.modules.impl.render.CustomModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Environment(EnvType.CLIENT)
@Mixin({Entity.class})
public abstract class EntityMixin {
   @Shadow
   private Box boundingBox;
   @Shadow
   public double prevX;
   @Shadow
   public double prevZ;
   private Vec3d winnerclient$originalMovement;

   @Shadow
   public abstract Vec3d getPos();

   @Shadow
   public abstract Box getBoundingBox();

   @Shadow
   public abstract double getX();

   @Shadow
   public abstract double getZ();

   @Inject(
      method = {"movementInputToVelocity"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void movementInputToVelocity(Vec3d movementInput, float speed, float yaw, CallbackInfoReturnable<Vec3d> cir) {
      float yaw2 = MinecraftClient.getInstance() != null && MinecraftClient.getInstance().player != null ? MinecraftClient.getInstance().player.getYaw() : 0.0F;
      if (yaw == yaw2) {
         double d = movementInput.lengthSquared();
         if (d < 1.0E-7) {
            cir.setReturnValue(Vec3d.ZERO);
            cir.cancel();
         } else {
            MoveCorrectionEvent moveCorrectionEvent = new MoveCorrectionEvent(yaw, 0.0F);
            WildClient.INSTANCE.getEventManager().hookEvent(moveCorrectionEvent);
            yaw = moveCorrectionEvent.getYaw();
            Vec3d vec3d = (d > 1.0D ? movementInput.normalize() : movementInput).multiply((double)speed);
            float f = MathHelper.sin(yaw * ((float)Math.PI / 180F));
            float g = MathHelper.cos(yaw * ((float)Math.PI / 180F));
            cir.setReturnValue(new Vec3d(vec3d.x * (double)g - vec3d.z * (double)f, vec3d.y, vec3d.z * (double)g + vec3d.x * (double)f));
            cir.cancel();
         }
      }

   }

   @Inject(
      method = {"getBoundingBox"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public final void getBoundingBox(CallbackInfoReturnable<Box> cir) {
      BoundingBoxControlEvent event = new BoundingBoxControlEvent(this.boundingBox, (Entity)(Object)this);
      WildClient.INSTANCE.getEventManager().hookEvent(event);
      cir.setReturnValue(event.getBox());
   }

   @Inject(
      method = {"pushAwayFrom"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void pushAwayFrom(Entity entity, CallbackInfo ci) {
      CustomModel customModel = (CustomModel)WildClient.INSTANCE.getModuleManager().getByClass(CustomModel.class);
      if (customModel.isEnabled() && customModel.getCustomModel() == entity) {
         ci.cancel();
      }

   }

   @ModifyArgs(
      method = {"pushAwayFrom"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"
)
   )
   public void pushAwayFromHook(Args args) {
      NoPush noPush = (NoPush)WildClient.INSTANCE.getModuleManager().getByClass(NoPush.class);
      if ((Object)this == MinecraftClient.getInstance().player && noPush.isEnabled() && noPush.options.getValueByName("Players")) {
         args.set(0, 0.0D);
         args.set(1, 0.0D);
         args.set(2, 0.0D);
      }

   }

   @Inject(
      method = {"getRotationVector()Lnet/minecraft/util/math/Vec3d;"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void movementInputToVelocity(CallbackInfoReturnable<Vec3d> cir) {
      Entity entity = (Entity)(Object)this;
      if (entity == MinecraftClient.getInstance().player) {
         FireworkEvent fireworkEvent = new FireworkEvent((double)entity.getYaw(), (double)entity.getPitch());
         WildClient.INSTANCE.getEventManager().hookEvent(fireworkEvent);
         cir.setReturnValue(entity.getRotationVector((float)fireworkEvent.getPitch(), (float)fireworkEvent.getYaw()));
      }

   }

   @Inject(
      method = {"move"},
      at = {@At("HEAD")}
   )
   private void captureMovement(MovementType type, Vec3d movement, CallbackInfo ci) {
      if ((Object)this instanceof ClientPlayerEntity) {
         this.winnerclient$originalMovement = movement;
      }
   }

   @ModifyVariable(
      method = {"move"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/Entity;adjustMovementForCollisions(Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/Vec3d;",
   shift = Shift.AFTER
),
      ordinal = 0
   )
   private Vec3d hookMoveEvent(Vec3d adjustedMovement, MovementType type, Vec3d originalMovement) {
      if (!((Object)this instanceof ClientPlayerEntity)) {
         return adjustedMovement;
      } else {
         Vec3d from = this.getPos();
         Vec3d predictedPosition = from.add(adjustedMovement);
         Vec3d intended = this.winnerclient$originalMovement != null ? this.winnerclient$originalMovement : originalMovement;
         boolean collidedVertically = intended.y != adjustedMovement.y;
         boolean collidedHorizontally = !isNearlyEqual(intended.x, adjustedMovement.x) || !isNearlyEqual(intended.z, adjustedMovement.z);
         boolean onGround = collidedVertically && intended.y < 0.0D;
         MoveOrEvent event = new MoveOrEvent(from, predictedPosition, originalMovement, onGround, collidedHorizontally, collidedVertically, this.getBoundingBox());
         WildClient.INSTANCE.getEventManager().hookEvent(event);
         return new Vec3d(event.getMotion().x, event.getMotion().y, event.getMotion().z);
      }
   }

   @Inject(
      method = {"move"},
      at = {@At("TAIL")}
   )
   private void hookPostMoveEvent(MovementType type, Vec3d movement, CallbackInfo ci) {
      if ((Object)this instanceof ClientPlayerEntity) {
         double deltaX = this.getX() - this.prevX;
         double deltaZ = this.getZ() - this.prevZ;
         double dist = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
         WildClient.INSTANCE.getEventManager().hookEvent(new PostMoveEvent(dist));
      }
   }

   private static boolean isNearlyEqual(double a, double b) {
      return Math.abs(a - b) < 1.0E-5;
   }
}
