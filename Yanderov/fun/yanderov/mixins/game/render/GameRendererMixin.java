package fun.Yanderov.mixins.game.render;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.systems.RenderSystem;
import fun.Yanderov.events.render.AspectRatioEvent;
import fun.Yanderov.events.render.FovEvent;
import fun.Yanderov.events.render.WorldRenderEvent;
import fun.Yanderov.features.impl.misc.FreeCam;
import fun.Yanderov.features.impl.player.NoEntityTrace;
import fun.Yanderov.features.impl.render.NoRender;
import fun.Yanderov.utils.client.managers.event.EventManager;
import fun.Yanderov.utils.display.geometry.Render3D;
import fun.Yanderov.utils.features.aura.utils.MathAngle;
import fun.Yanderov.utils.features.aura.utils.RaycastAngle;
import fun.Yanderov.utils.features.aura.warp.TurnsConnection;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import net.minecraft.class_10209;
import net.minecraft.class_1297;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_757;
import net.minecraft.class_9779;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_757.class})
public abstract class GameRendererMixin {
   @Final
   @Shadow
   private class_310 field_4015;
   @Shadow
   private float field_4005;
   @Shadow
   private float field_3988;
   @Shadow
   private float field_4004;

   @Shadow
   public abstract float method_32796();

   @Inject(
      method = {"updateCrosshairTarget"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/GameRenderer;findCrosshairTarget(Lnet/minecraft/entity/Entity;DDF)Lnet/minecraft/util/hit/HitResult;"
)},
      cancellable = true
   )
   private void onUpdateTargetedEntity(float tickDelta, CallbackInfo ci) {
      if (!PlayerInteractionHelper.nullCheck()) {
         FreeCam freeCam = FreeCam.getInstance();
         if (freeCam != null && freeCam.isState()) {
            class_10209.method_64146().method_15407();
            this.field_4015.field_1765 = RaycastAngle.raycast(freeCam.pos, (double)4.5F, MathAngle.cameraAngle(), false);
            ci.cancel();
         }

      }
   }

   @Inject(
      method = {"findCrosshairTarget"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void findCrosshairTargetHook(class_1297 camera, double blockInteractionRange, double entityInteractionRange, float tickDelta, CallbackInfoReturnable cir) {
      NoEntityTrace noEntityTrace = NoEntityTrace.getInstance();
      if (noEntityTrace != null && noEntityTrace.shouldIgnoreEntityTrace()) {
         double d = Math.max(blockInteractionRange, entityInteractionRange);
         camera.method_5836(tickDelta);
         class_239 hitResult = camera.method_5745(d, tickDelta, false);
         cir.setReturnValue(hitResult);
      }

   }

   @Redirect(
      method = {"findCrosshairTarget"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/Entity;raycast(DFZ)Lnet/minecraft/util/hit/HitResult;"
)
   )
   private class_239 hookRaycast(class_1297 instance, double maxDistance, float tickDelta, boolean includeFluids) {
      return (class_239)(instance != this.field_4015.field_1724 ? instance.method_5745(maxDistance, tickDelta, includeFluids) : RaycastAngle.raycast(maxDistance, TurnsConnection.INSTANCE.getRotation(), includeFluids));
   }

   @Redirect(
      method = {"findCrosshairTarget"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/Entity;getRotationVec(F)Lnet/minecraft/util/math/Vec3d;"
)
   )
   private class_243 hookRotationVector(class_1297 instance, float tickDelta) {
      return TurnsConnection.INSTANCE.getRotation().toVector();
   }

   @Inject(
      method = {"getBasicProjectionMatrix"},
      at = {@At("TAIL")},
      cancellable = true
   )
   public void getBasicProjectionMatrixHook(float fovDegrees, CallbackInfoReturnable cir) {
      AspectRatioEvent aspectRatioEvent = new AspectRatioEvent();
      EventManager.callEvent(aspectRatioEvent);
      if (aspectRatioEvent.isCancelled()) {
         Matrix4f matrix4f = new Matrix4f();
         if (this.field_4005 != 1.0F) {
            matrix4f.translate(this.field_3988, -this.field_4004, 0.0F);
            matrix4f.scale(this.field_4005, this.field_4005, 1.0F);
         }

         matrix4f.perspective(fovDegrees * ((float)Math.PI / 180F), aspectRatioEvent.getRatio(), 0.05F, this.method_32796());
         cir.setReturnValue(matrix4f);
      }

   }

   @ModifyExpressionValue(
      method = {"getFov"},
      at = {@At(
   value = "INVOKE",
   target = "Ljava/lang/Integer;intValue()I",
   remap = false
)}
   )
   private int hookGetFov(int original) {
      FovEvent event = new FovEvent();
      EventManager.callEvent(event);
      return event.isCancelled() ? event.getFov() : original;
   }

   @Inject(
      method = {"renderWorld"},
      at = {@At(
   value = "FIELD",
   target = "Lnet/minecraft/client/render/GameRenderer;renderHand:Z",
   opcode = 180,
   ordinal = 0
)}
   )
   public void hookWorldRender(class_9779 tickCounter, CallbackInfo ci, @Local(ordinal = 2) Matrix4f matrix4f) {
      class_4587 matrixStack = new class_4587();
      matrixStack.method_34425(matrix4f);
      matrixStack.method_61958(this.field_4015.method_1561().field_4686.method_19326().method_22882());
      Render3D.setLastProjMat(RenderSystem.getProjectionMatrix());
      Render3D.setLastWorldSpaceMatrix(matrixStack.method_23760());
      WorldRenderEvent event = new WorldRenderEvent(matrixStack, tickCounter.method_60637(false));
      EventManager.callEvent(event);
      Render3D.onWorldRender(event);
   }

   @Inject(
      method = {"tiltViewWhenHurt"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onTiltViewWhenHurt(class_4587 matrices, float tickDelta, CallbackInfo ci) {
      NoRender noRender = NoRender.getInstance();
      if (noRender != null && noRender.isState() && noRender.modeSetting.isSelected("Damage")) {
         ci.cancel();
      }

   }
}

