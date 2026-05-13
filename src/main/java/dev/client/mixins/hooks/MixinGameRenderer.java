package dev.client.mixins.hooks;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.client.WildClient;
import dev.client.event.classes.ShaderEvent;
import dev.client.mixins.other.IMixinGameRenderer;
import dev.client.modules.impl.util.NoRender;
import dev.client.util.render.RenderUtil3D;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.SimpleFramebuffer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL43;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({GameRenderer.class})
public class MixinGameRenderer implements IMixinGameRenderer {
   @Shadow
   private boolean renderingPanorama;
   @Shadow
   private float lastFovMultiplier;
   @Shadow
   private float fovMultiplier;
   @Unique
   private SimpleFramebuffer wild$beforeHandFbo = null;
   @Unique
   private SimpleFramebuffer wild$withHandFbo = null;

   public float getFov2(Camera camera, float tickDelta, boolean changingFov) {
      if (this.renderingPanorama) {
         return 90.0F;
      } else {
         float f = 70.0F;
         if (changingFov) {
            f = (float)(Integer)MinecraftClient.getInstance().options.getFov().getValue();
            f *= MathHelper.lerp(tickDelta, this.lastFovMultiplier, this.fovMultiplier);
         }

         Entity entity = camera.getFocusedEntity();
         if (entity instanceof LivingEntity livingEntity) {
            if (livingEntity.isDead()) {
               float g = Math.min((float)livingEntity.deathTime + tickDelta, 20.0F);
               f /= (1.0F - 500.0F / (g + 500.0F)) * 2.0F + 1.0F;
            }
         }

         CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
         if (cameraSubmersionType == CameraSubmersionType.LAVA || cameraSubmersionType == CameraSubmersionType.WATER) {
            float g = ((Double)MinecraftClient.getInstance().options.getFovEffectScale().getValue()).floatValue();
            f *= MathHelper.lerp(g, 1.0F, 0.85714287F);
         }

         return f;
      }
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
   public void hookWorldRender(RenderTickCounter tickCounter, CallbackInfo ci, @Local(ordinal = 2) Matrix4f matrix4f) {
      Camera camera = MinecraftClient.getInstance().getEntityRenderDispatcher().camera;
      MatrixStack matrixStack = new MatrixStack();
      matrixStack.multiplyPositionMatrix(matrix4f);
      matrixStack.translate(MinecraftClient.getInstance().getEntityRenderDispatcher().camera.getPos().negate());
      RenderUtil3D.getInstance().savedCamPos = camera.getPos();
      WildClient.INSTANCE.getEventManager().getWorldRenderEvent().update(tickCounter, matrixStack);
      WildClient.INSTANCE.getEventManager().hookEvent(WildClient.INSTANCE.getEventManager().getWorldRenderEvent());
      RenderUtil3D.getInstance().lastWorldSpaceMatrix = matrixStack.peek();
      RenderUtil3D.getInstance().onRender3D();
   }

   @Inject(
      at = {@At(
   value = "FIELD",
   target = "Lnet/minecraft/client/render/GameRenderer;renderHand:Z",
   opcode = 180,
   ordinal = 0
)},
      method = {"renderWorld"}
   )
   void render3dHook(RenderTickCounter renderTickCounter, CallbackInfo ci) {
      Camera camera = MinecraftClient.getInstance().gameRenderer.getCamera();
      MatrixStack matrixStack = new MatrixStack();
      RenderSystem.getModelViewStack().pushMatrix().mul(matrixStack.peek().getPositionMatrix());
      matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camera.getPitch()));
      matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(camera.getYaw() + 180.0F));
      WildClient.INSTANCE.getEventManager().getRender3DEvent().update(renderTickCounter, matrixStack);
      WildClient.INSTANCE.getEventManager().hookEvent(WildClient.INSTANCE.getEventManager().getRender3DEvent());
      RenderSystem.getModelViewStack().popMatrix();
   }

   @Redirect(
      method = {"renderWorld"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/util/math/MathHelper;lerp(FFF)F"
)
   )
   private float renderWorldHook(float delta, float first, float second) {
      NoRender noRender = (NoRender)WildClient.INSTANCE.getModuleManager().getByClass(NoRender.class);
      return noRender.isEnabled() && noRender.options.getValueByName("Nausea") ? 0.0F : MathHelper.lerp(delta, first, second);
   }

   @Inject(
      method = {"tiltViewWhenHurt"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void tiltViewWhenHurtHook(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
      NoRender noRender = (NoRender)WildClient.INSTANCE.getModuleManager().getByClass(NoRender.class);
      if (noRender.isEnabled() && noRender.options.getValueByName("NoHurtCum")) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"renderHand"},
      at = {@At("HEAD")}
   )
   private void morbid$beforeHand(Camera camera, float tickDelta, Matrix4f matrix4f, CallbackInfo ci) {
      Framebuffer main = MinecraftClient.getInstance().getFramebuffer();
      if (main.getColorAttachment() != 0) {
         this.wild$beforeHandFbo = this.morbid$ensureFbo(this.wild$beforeHandFbo, main);
         this.morbid$copyGL(main, this.wild$beforeHandFbo);
      }
   }

   @Inject(
      method = {"renderHand"},
      at = {@At("TAIL")}
   )
   private void morbid$afterHand(Camera camera, float tickDelta, Matrix4f matrix4f, CallbackInfo ci) {
      Framebuffer main = MinecraftClient.getInstance().getFramebuffer();
      if (main.getColorAttachment() != 0 && this.wild$beforeHandFbo != null) {
         this.wild$withHandFbo = this.morbid$ensureFbo(this.wild$withHandFbo, main);
         this.morbid$copyGL(main, this.wild$withHandFbo);
         WildClient.INSTANCE.getEventManager().hookEvent(new ShaderEvent(this.wild$beforeHandFbo, this.wild$withHandFbo));
      }
   }

   @Unique
   private SimpleFramebuffer morbid$ensureFbo(SimpleFramebuffer fbo, Framebuffer ref) {
      if (fbo == null) {
         fbo = new SimpleFramebuffer(ref.textureWidth, ref.textureHeight, false);
      } else if (fbo.textureWidth != ref.textureWidth || fbo.textureHeight != ref.textureHeight) {
         fbo.resize(ref.textureWidth, ref.textureHeight);
      }

      return fbo;
   }

   @Unique
   private void morbid$copyGL(Framebuffer src, SimpleFramebuffer dst) {
      int srcColor = src.getColorAttachment();
      int dstColor = dst.getColorAttachment();
      if (srcColor != 0 && dstColor != 0) {
         GL43.glCopyImageSubData(srcColor, 3553, 0, 0, 0, 0, dstColor, 3553, 0, 0, 0, 0, src.textureWidth, src.textureHeight, 1);
      }
   }
}
