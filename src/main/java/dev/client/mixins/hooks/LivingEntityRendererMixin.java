package dev.client.mixins.hooks;

import com.llamalad7.mixinextras.sugar.Local;
import dev.client.WildClient;
import dev.client.event.classes.EntityColorEvent;
import dev.client.modules.impl.render.PlayerEsp;
import dev.client.modules.impl.render.CustomModel;
import dev.client.util.color.ColorUtil;
import dev.client.util.render.esp.ChamsRenderer;
import dev.client.util.render.esp.FlatEspLayer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin({LivingEntityRenderer.class})
public abstract class LivingEntityRendererMixin {
   @Shadow
   protected EntityModel model;

   @Shadow
   protected abstract @Nullable RenderLayer getRenderLayer(LivingEntityRenderState livingEntityRenderState, boolean enabled, boolean enabled2, boolean enabled3);

   @Redirect(
      method = {"render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;getRenderLayer(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;ZZZ)Lnet/minecraft/client/render/RenderLayer;"
)
   )
   private RenderLayer renderHook(LivingEntityRenderer instance, LivingEntityRenderState state, boolean showBody, boolean translucent, boolean showOutline) {
      if (!translucent && state.width == 0.6F) {
         EntityColorEvent entityColorEvent = new EntityColorEvent(-1);
         WildClient.INSTANCE.getEventManager().hookEvent(entityColorEvent);
         if (entityColorEvent.isCancelled()) {
            translucent = true;
            entityColorEvent.resetCancel();
         }
      }

      return this.getRenderLayer(state, showBody, translucent, showOutline);
   }

   @Redirect(
      method = {"render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/render/entity/model/EntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V"
)
   )
   private void redirectModelRender(EntityModel<?> instance, MatrixStack matrixStack, VertexConsumer vertexConsumer, int i, int j, int l, @Local(argsOnly = true) LivingEntityRenderState renderState, @Local(argsOnly = true) VertexConsumerProvider vertexConsumers) {
      EntityColorEvent entityColorEvent = new EntityColorEvent(l);
      if (renderState.invisibleToPlayer) {
         WildClient.INSTANCE.getEventManager().hookEvent(entityColorEvent);
      }

      PlayerEsp playerEsp = WildClient.INSTANCE.getModuleManager().getByClass(PlayerEsp.class);
      CustomModel customModel = WildClient.INSTANCE.getModuleManager().getByClass(CustomModel.class);
      int themeColor = WildClient.INSTANCE.getThemeManager().getTheme().color().getRGB();
      
      boolean isPlayer = renderState instanceof PlayerEntityRenderState;
      boolean isFirstPerson = MinecraftClient.getInstance().options.getPerspective().isFirstPerson();
      
      if (isPlayer && customModel.isEnabled() && ((PlayerEntityRenderState)renderState).name.equals(MinecraftClient.getInstance().player.getName().getString())) {
          if (isFirstPerson) {
              return;
          }
      }

      if (playerEsp.chamsMode.is("Glass") && playerEsp.options.getValueByName("Chams") && playerEsp.isEnabled() && renderState instanceof PlayerEntityRenderState playerState) {
         ;
      } else {
         instance.render(matrixStack, vertexConsumer, i, j, entityColorEvent.getColor());
      }

      if (playerEsp.isEnabled() && playerEsp.options.getValueByName("Chams") && renderState instanceof PlayerEntityRenderState playerState) {
         if (playerEsp.chamsMode.is("Glass") && playerEsp.isEnabled() && playerEsp.options.getValueByName("Chams")) {
            VertexConsumer depthVc = vertexConsumers.getBuffer(FlatEspLayer.FLAT_ESP_DEPTH);
            instance.render(matrixStack, depthVc, i, j, -1);
            VertexConsumer colorVc = vertexConsumers.getBuffer(FlatEspLayer.FLAT_ESP);
            instance.render(matrixStack, colorVc, 15728880, j, ColorUtil.setAlpha(0.4D, WildClient.INSTANCE.getThemeManager().getTheme().color()).getRGB());
         }

         if (playerEsp.chamsMode.is("Flat") && playerEsp.isEnabled() && playerEsp.options.getValueByName("Chams")) {
            VertexConsumer espVc = vertexConsumers.getBuffer(FlatEspLayer.FLAT_ESP);
            instance.render(matrixStack, espVc, 15728880, j, themeColor);
         }

         if (playerEsp.isEnabled() && playerEsp.chamsMode.is("New") && playerEsp.options.getValueByName("Chams")) {
            int[] chams = this.adapt(WildClient.INSTANCE.getThemeManager().getTheme().color().getRGB());
            ChamsRenderer.enqueue(instance, matrixStack, chams);
         }
      }
   }

   @Unique
   public int[] adapt(int rgb) {
      int r = rgb >> 16 & 255;
      int g = rgb >> 8 & 255;
      int b = rgb & 255;
      float brightness = ((float)r * 0.299F + (float)g * 0.587F + (float)b * 0.114F) / 255.0F;
      int overAlpha = Math.max(80, (int)(224.0F * (1.0F - brightness * 0.75F)));
      int throughAlpha = Math.max(37, (int)(112.0F * (1.0F - brightness * 0.65F)));
      return new int[]{overAlpha << 24 | r << 16 | g << 8 | b, throughAlpha << 24 | r << 16 | g << 8 | b};
   }
}
