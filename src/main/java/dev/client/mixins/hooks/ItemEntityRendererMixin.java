package dev.client.mixins.hooks;

import dev.client.WildClient;
import dev.client.modules.impl.render.ItemPhysics;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.client.render.entity.state.ItemEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({ItemEntityRenderer.class})
public class ItemEntityRendererMixin {
   @ModifyVariable(
      method = {"render(Lnet/minecraft/client/render/entity/state/ItemEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/util/math/MatrixStack;multiply(Lorg/joml/Quaternionf;)V",
   shift = Shift.AFTER
),
      ordinal = 0
   )
   private float modifyRotation(float originalRotation, ItemEntityRenderState state) {
      return WildClient.INSTANCE.getModuleManager().getByClass(ItemPhysics.class).isEnabled() ? 0.0F : originalRotation;
   }

   @Inject(
      method = {"render(Lnet/minecraft/client/render/entity/state/ItemEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/entity/ItemEntityRenderer;renderStack(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/state/ItemStackEntityRenderState;Lnet/minecraft/util/math/random/Random;)V",
   shift = Shift.BEFORE
)}
   )
   private void applyCustomRotation(ItemEntityRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
      if (WildClient.INSTANCE.getModuleManager().getByClass(ItemPhysics.class).isEnabled()) {
         float rotation = ItemEntity.getRotation(state.age, state.uniqueOffset);
         matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(85.0F));
         matrices.multiply(RotationAxis.POSITIVE_Z.rotation(rotation * 0.3F));
      }

   }
}
