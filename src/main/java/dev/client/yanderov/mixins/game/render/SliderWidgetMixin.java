package dev.client.yanderov.mixins.game.render;

import dev.client.yanderov.features.impl.misc.SelfDestruct;
import dev.client.yanderov.features.impl.render.BetterMinecraft;
import dev.client.yanderov.utils.display.widget.SliderRender;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_339;
import net.minecraft.class_357;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_357.class})
public abstract class SliderWidgetMixin extends class_339 {
   @Shadow
   protected double field_22753;
   @Unique
   private static final SliderRender RENDER = new SliderRender();

   public SliderWidgetMixin(int x, int y, int width, int height, class_2561 message) {
      super(x, y, width, height, message);
   }

   @Inject(
      method = {"renderWidget"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onRenderWidget(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      if (!SelfDestruct.unhooked) {
         if (BetterMinecraft.getInstance().isState() && BetterMinecraft.getInstance().getBetterButton().isValue()) {
            ci.cancel();
            SliderRender var10000 = RENDER;
            SliderRender.renderSlider(context, this.method_46426(), this.method_46427(), this.method_25368(), this.method_25364(), this.field_22753, this.field_22763, this.method_25369() != null ? this.method_25369().getString() : "");
         }

      }
   }
}

