package dev.client.yanderov.mixins.client.screen.ingame;

import dev.client.yanderov.features.impl.render.NoRender;
import net.minecraft.class_1058;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4603;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_4603.class})
public class InGameOverlayRendererMixin {
   @Inject(
      method = {"renderFireOverlay"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void renderFireOverlayHook(class_4587 matrices, class_4597 vertexConsumers, CallbackInfo ci) {
      NoRender noRender = NoRender.getInstance();
      if (noRender.isState() && noRender.modeSetting.isSelected("Fire")) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"renderInWallOverlay"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void renderInWallOverlayHook(class_1058 sprite, class_4587 matrices, class_4597 vertexConsumers, CallbackInfo ci) {
      NoRender noRender = NoRender.getInstance();
      if (noRender.isState() && noRender.modeSetting.isSelected("Block Overlay")) {
         ci.cancel();
      }

   }
}

