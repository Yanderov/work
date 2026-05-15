package dev.client.yanderov.mixins.player.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import dev.client.yanderov.Yanderov;
import dev.client.yanderov.common.repository.friend.FriendUtils;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.geometry.Render3D;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.math.calc.Calculate;
import dev.client.yanderov.utils.math.projection.Projection;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1531;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_5498;
import net.minecraft.class_898;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_898.class})
public class EntityRenderDispatcherMixin implements QuickImports {
   @ModifyExpressionValue(
      method = {"render(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/EntityRenderer;)V"},
      at = {@At(
   value = "FIELD",
   target = "Lnet/minecraft/client/render/entity/state/EntityRenderState;invisible:Z"
)}
   )
   private boolean renderHitboxHook(boolean original, @Local(ordinal = 0,argsOnly = true) class_1297 entity) {
      return entity instanceof class_1531;
   }

   @Inject(
      method = {"renderHitbox"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void renderHitboxHook(class_4587 matrices, class_4588 vertices, class_1297 entity, float tickDelta, float red, float green, float blue, CallbackInfo ci) {
      if (!YanderovIntegration.getInstance().getBoxESPRepository().entities.containsKey(entity.method_5864())) {
         renderBox(entity);
      }

      ci.cancel();
   }

   @Unique
   private static void renderBox(class_1297 entity) {
      if (entity != mc.field_1724 || !mc.field_1690.method_31044().equals(class_5498.field_26664)) {
         int color = FriendUtils.isFriend(entity) ? ColorAssist.getFriendColor() : ColorAssist.getClientColor();
         class_243 offset = Calculate.interpolate(entity).method_1020(entity.method_19538());
         class_238 box = entity.method_5829().method_997(offset);
         if (Projection.canSee(box)) {
            if (entity instanceof class_1309) {
               class_1309 living = (class_1309)entity;
               float width = entity.method_17681();
               class_243 eyeMin = entity.method_33571().method_1019(offset).method_1031((double)(-width / 2.0F), (double)0.0F, (double)(-width / 2.0F));
               Render3D.drawBox(box, ColorAssist.multRed(color, (float)(1 + living.field_6235)), 2.0F, true, true, true);
               Render3D.drawLine(eyeMin, eyeMin.method_1031((double)width, (double)0.0F, (double)0.0F), ColorAssist.RED, 2.0F, true);
               Render3D.drawLine(eyeMin.method_1031((double)width, (double)0.0F, (double)0.0F), eyeMin.method_1031((double)width, (double)0.0F, (double)width), ColorAssist.RED, 2.0F, true);
               Render3D.drawLine(eyeMin, eyeMin.method_1031((double)0.0F, (double)0.0F, (double)width), ColorAssist.RED, 2.0F, true);
               Render3D.drawLine(eyeMin.method_1031((double)0.0F, (double)0.0F, (double)width), eyeMin.method_1031((double)width, (double)0.0F, (double)width), ColorAssist.RED, 2.0F, true);
            } else {
               Render3D.drawBox(box, color, 2.0F, true, true, true);
            }
         }
      }

   }
}

