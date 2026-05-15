package dev.client.yanderov.mixins.client.screen.mainmenu;

import dev.client.yanderov.display.screens.clickgui.MenuScreen;
import dev.client.yanderov.display.screens.mainmenu.MainMenu;
import dev.client.yanderov.events.chat.ChatEvent;
import dev.client.yanderov.utils.client.managers.event.EventManager;
import net.minecraft.class_2558;
import net.minecraft.class_2583;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_751;
import net.minecraft.class_766;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_437.class})
public class ScreenMixin {
   private static final class_751 CUSTOM_PANORAMA_RENDERER = new class_751(class_2960.method_60655("minecraft", "panorama/panorama"));
   private static final class_766 CUSTOM_ROTATING_PANORAMA_RENDERER;

   @Inject(
      at = {@At(
   value = "INVOKE",
   target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;)V",
   remap = false,
   ordinal = 1
)},
      method = {"handleTextClick"},
      cancellable = true
   )
   public void handleCustomClickEvent(class_2583 style, CallbackInfoReturnable cir) {
      class_2558 clickEvent = style.method_10970();
      if (clickEvent != null) {
         EventManager.callEvent(new ChatEvent(clickEvent.method_10844()));
         cir.setReturnValue(true);
         cir.cancel();
      }
   }

   @Inject(
      method = {"renderBackground"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void disableBackgroundBlurAndDimming(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      if (this instanceof MenuScreen || this instanceof MainMenu || this instanceof class_500 || this instanceof class_526 || this instanceof net.minecraft.class_429) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"renderPanoramaBackground"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderCustomPanoramaBackground(class_332 context, float delta, CallbackInfo ci) {
      if (this instanceof MainMenu) {
         ci.cancel();
      } else {
         CUSTOM_ROTATING_PANORAMA_RENDERER.method_3317(context, ((class_437)this).field_22789, ((class_437)this).field_22790, 1.0F, delta);
         ci.cancel();
      }

   }

   static {
      CUSTOM_ROTATING_PANORAMA_RENDERER = new class_766(CUSTOM_PANORAMA_RENDERER);
   }
}

