package dev.client.yanderov.mixins.player.entity;

import dev.client.yanderov.features.impl.render.Esp;
import net.minecraft.class_10017;
import net.minecraft.class_2561;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_897;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_897.class})
public abstract class EntityRendererMixin {
   @Inject(
      method = {"renderLabelIfPresent"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderLabelIfPresent(class_10017 state, class_2561 text, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
      if (Esp.getInstance().isState() && this.canRemove((int)(state.field_53329 * 100.0F), Esp.getInstance())) {
         ci.cancel();
      }

   }

   @Unique
   private boolean canRemove(int width, Esp esp) {
      boolean var10000;
      switch (width) {
         case 60 -> var10000 = esp.entityType.isSelected("Player");
         case 98 -> var10000 = esp.entityType.isSelected("TNT");
         default -> var10000 = false;
      }

      return var10000;
   }
}

