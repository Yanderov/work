package dev.client.yanderov.mixins.player.entity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.client.yanderov.events.player.FireworkEvent;
import dev.client.yanderov.utils.client.managers.event.EventManager;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.features.aura.warp.TurnsConnection;
import net.minecraft.class_1309;
import net.minecraft.class_1671;
import net.minecraft.class_243;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({class_1671.class})
public class FireworkRocketEntityMixin implements QuickImports {
   @Shadow
   private @Nullable class_1309 field_7616;

   @WrapOperation(
      method = {"tick"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/LivingEntity;getRotationVector()Lnet/minecraft/util/math/Vec3d;"
)}
   )
   public class_243 getRotationVectorHook(class_1309 instance, Operation original) {
      return this.field_7616 == mc.field_1724 ? TurnsConnection.INSTANCE.getMoveRotation().toVector() : (class_243)original.call(new Object[]{instance});
   }

   @WrapOperation(
      method = {"tick"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/LivingEntity;getVelocity()Lnet/minecraft/util/math/Vec3d;",
   ordinal = 0
)}
   )
   public class_243 getVelocityHook(class_1309 instance, Operation original) {
      if (this.field_7616 == mc.field_1724) {
         FireworkEvent event = new FireworkEvent((class_243)original.call(new Object[]{instance}));
         EventManager.callEvent(event);
         return event.getVector();
      } else {
         return (class_243)original.call(new Object[]{instance});
      }
   }
}

