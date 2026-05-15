package fun.Yanderov.mixins.player.item;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import fun.Yanderov.events.item.HandAnimationEvent;
import fun.Yanderov.events.item.HandOffsetEvent;
import fun.Yanderov.events.render.ItemRendererEvent;
import fun.Yanderov.utils.client.managers.event.EventManager;
import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_742;
import net.minecraft.class_759;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_759.class})
public abstract class HeldItemRendererMixin {
   @Inject(
      method = {"renderFirstPersonItem"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/util/math/MatrixStack;push()V",
   shift = Shift.AFTER
)}
   )
   private void renderFirstPersonItemHook(class_742 player, float tickDelta, float pitch, class_1268 hand, float swingProgress, class_1799 stack, float equipProgress, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
      HandOffsetEvent event = new HandOffsetEvent(matrices, stack, hand);
      EventManager.callEvent(event);
   }

   @WrapOperation(
      method = {"renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;Lnet/minecraft/client/network/ClientPlayerEntity;I)V"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderFirstPersonItem(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/util/Hand;FLnet/minecraft/item/ItemStack;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"
)}
   )
   private void itemRenderHook(class_759 instance, class_742 player, float tickDelta, float pitch, class_1268 hand, float swingProgress, class_1799 item, float equipProgress, class_4587 matrices, class_4597 vertexConsumers, int light, Operation original) {
      ItemRendererEvent event = new ItemRendererEvent(player, item, hand);
      EventManager.callEvent(event);
      original.call(new Object[]{instance, event.getPlayer(), tickDelta, pitch, event.getHand(), swingProgress, event.getStack(), equipProgress, matrices, vertexConsumers, light});
   }

   @WrapOperation(
      method = {"renderFirstPersonItem"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/item/HeldItemRenderer;swingArm(FFLnet/minecraft/client/util/math/MatrixStack;ILnet/minecraft/util/Arm;)V",
   ordinal = 2
)}
   )
   private void handAnimationHook(class_759 instance, float swingProgress, float equipProgress, class_4587 matrices, int armX, class_1306 arm, Operation original, @Local(ordinal = 0,argsOnly = true) class_742 player, @Local(ordinal = 0,argsOnly = true) class_1268 hand) {
      HandAnimationEvent event = new HandAnimationEvent(matrices, hand, swingProgress);
      EventManager.callEvent(event);
      if (!event.isCancelled()) {
         original.call(new Object[]{instance, swingProgress, equipProgress, matrices, armX, arm});
      }

   }
}

