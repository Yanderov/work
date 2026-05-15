package dev.client.yanderov.mixins.player.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import dev.client.yanderov.events.render.EntityColorEvent;
import dev.client.yanderov.features.impl.combat.Aura;
import dev.client.yanderov.utils.client.managers.event.EventManager;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.features.aura.warp.TurnsConnection;
import net.minecraft.class_10042;
import net.minecraft.class_1309;
import net.minecraft.class_1921;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_465;
import net.minecraft.class_583;
import net.minecraft.class_922;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({class_922.class})
public abstract class LivingEntityRendererMixin implements QuickImports {
   @Shadow
   protected abstract @Nullable class_1921 method_24302(class_10042 var1, boolean var2, boolean var3, boolean var4);

   @Redirect(
      method = {"render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;getRenderLayer(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;ZZZ)Lnet/minecraft/client/render/RenderLayer;"
)
   )
   private class_1921 renderHook(class_922 instance, class_10042 state, boolean showBody, boolean translucent, boolean showOutline) {
      if (!translucent && state.field_53329 == 0.6F) {
         EntityColorEvent event = new EntityColorEvent(-1);
         EventManager.callEvent(event);
         if (event.isCancelled()) {
            translucent = true;
         }
      }

      return this.method_24302(state, showBody, translucent, showOutline);
   }

   @Redirect(
      method = {"render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/entity/model/EntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V"
)
   )
   private void renderModelHook(class_583 instance, class_4587 matrixStack, class_4588 vertexConsumer, int i, int j, int l, @Local(ordinal = 0,argsOnly = true) class_10042 renderState) {
      EntityColorEvent event = new EntityColorEvent(l);
      if (renderState.field_53461) {
         EventManager.callEvent(event);
      }

      instance.method_62100(matrixStack, vertexConsumer, i, j, event.getColor());
   }

   @ModifyExpressionValue(
      method = {"updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/util/math/MathHelper;lerpAngleDegrees(FFF)F"
)}
   )
   private float lerpAngleDegreesHook(float original, @Local(ordinal = 0,argsOnly = true) class_1309 entity, @Local(ordinal = 0,argsOnly = true) float delta) {
      TurnsConnection controller = TurnsConnection.INSTANCE;
      Aura aura = Aura.getInstance();
      if (entity.equals(mc.field_1724) && controller.getPreviousRotation().getYaw() != mc.field_1724.method_36454() && controller.getFakeRotation().getYaw() != mc.field_1724.method_36454() && !(mc.field_1755 instanceof class_465)) {
         boolean isLony = Aura.fakeRotate;
         float prevYaw = isLony ? controller.getFakeRotation().getYaw() : controller.getPreviousRotation().getYaw();
         float currentYaw = isLony ? controller.getFakeRotation().getYaw() : controller.getRotation().getYaw();
         if (Aura.getInstance().getTarget() == null) {
            prevYaw = controller.getPreviousRotation().getYaw();
            currentYaw = controller.getRotation().getYaw();
         }

         return class_3532.method_16439(delta, prevYaw, currentYaw);
      } else {
         return original;
      }
   }

   @ModifyExpressionValue(
      method = {"updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/LivingEntity;getLerpedPitch(F)F"
)}
   )
   private float getLerpedPitchHook(float original, @Local(ordinal = 0,argsOnly = true) class_1309 entity, @Local(ordinal = 0,argsOnly = true) float delta) {
      TurnsConnection controller = TurnsConnection.INSTANCE;
      Aura aura = Aura.getInstance();
      if (entity.equals(mc.field_1724) && controller.getPreviousRotation().getPitch() != mc.field_1724.method_36455() && controller.getFakeRotation().getPitch() != mc.field_1724.method_36455() && !(mc.field_1755 instanceof class_465)) {
         boolean isLony = Aura.fakeRotate;
         float prevPitch = isLony ? controller.getFakeRotation().getPitch() : controller.getPreviousRotation().getPitch();
         float currentPitch = isLony ? controller.getFakeRotation().getPitch() : controller.getRotation().getPitch();
         if (Aura.getInstance().getTarget() == null) {
            prevPitch = controller.getPreviousRotation().getPitch();
            currentPitch = controller.getRotation().getPitch();
         }

         return class_3532.method_16439(delta, prevPitch, currentPitch);
      } else {
         return original;
      }
   }
}

