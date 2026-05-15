package dev.client.yanderov.mixins.game.render;

import dev.client.yanderov.features.impl.misc.SelfDestruct;
import dev.client.yanderov.features.impl.render.BetterMinecraft;
import dev.client.yanderov.utils.display.widget.PressableWidgetRender;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_339;
import net.minecraft.class_4264;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_4264.class})
public abstract class PressableWidgetMixin extends class_339 {
   @Unique
   private static final PressableWidgetRender RENDER = new PressableWidgetRender();

   public PressableWidgetMixin(int x, int y, int width, int height, class_2561 message) {
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
            PressableWidgetRender var10000 = RENDER;
            PressableWidgetRender.render(context, this.method_46426(), this.method_46427(), this.method_25368(), this.method_25364(), this.field_22763, this.method_25369() != null ? this.method_25369().getString() : "");
         }

      }
   }
}

