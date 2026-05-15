package dev.client.yanderov.mixins.client.screen.ingame;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.events.render.DrawEvent;
import dev.client.yanderov.features.impl.render.CrossHair;
import dev.client.yanderov.features.impl.render.Hud;
import dev.client.yanderov.utils.client.managers.event.EventManager;
import dev.client.yanderov.utils.display.geometry.Render2D;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.math.calc.Calculate;
import java.util.ConcurrentModificationException;
import net.minecraft.class_1921;
import net.minecraft.class_266;
import net.minecraft.class_310;
import net.minecraft.class_329;
import net.minecraft.class_332;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_329.class})
public abstract class InGameHudMixin implements QuickImports {
   @Unique
   private final Hud hud = Hud.getInstance();
   @Final
   @Shadow
   private class_310 field_2035;

   @Shadow
   protected abstract void method_1760(class_332 var1);

   @Shadow
   protected abstract void method_1741(class_332 var1);

   @Inject(
      method = {"render"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/gui/LayeredDrawer;render(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V",
   shift = Shift.AFTER
)}
   )
   public void onRender(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      blur.setup();
      DrawEvent event = new DrawEvent(context, drawEngine, tickCounter.method_60637(false));
      EventManager.callEvent(event);
      Render2D.onRender(context);
      boolean debugHudVisible = this.field_2035.method_53526().method_53536();
      boolean tabVisible = this.field_2035.field_1690.field_1907.method_1434();
      if (!this.field_2035.field_1690.field_1842 && !debugHudVisible) {
         context.method_51448().method_22903();
         context.method_51448().method_46416(0.0F, 0.0F, 400.0F);
         YanderovIntegration.getInstance().getDraggableRepository().draggable().forEach((draggable) -> {
            if (draggable.canDraw(this.hud, draggable)) {
               draggable.startAnimation();
            } else {
               draggable.stopAnimation();
            }

            float scale = draggable.getScaleAnimation().getOutput().floatValue();
            if (!draggable.isCloseAnimationFinished()) {
               draggable.validPosition();

               try {
                  Calculate.setAlpha(scale, () -> draggable.drawDraggable(context));
               } catch (ConcurrentModificationException var5) {
               }
            }

         });
         context.method_51448().method_22909();
      }

   }

   @Inject(
      method = {"renderCrosshair"},
      at = {@At(
   value = "FIELD",
   target = "Lnet/minecraft/client/gui/hud/InGameHud;CROSSHAIR_TEXTURE:Lnet/minecraft/util/Identifier;"
)},
      cancellable = true
   )
   public void renderCrosshairHook(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      CrossHair crossHair = CrossHair.getInstance();
      if (crossHair.isState()) {
         crossHair.onRenderCrossHair();
         ci.cancel();
      }

   }

   @Inject(
      at = {@At("HEAD")},
      method = {"renderStatusEffectOverlay"},
      cancellable = true
   )
   public void renderStatusEffectOverlayHook(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      if (this.hud.isState() && this.hud.interfaceSettings.isSelected("Potions")) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderScoreboardSidebarHook(class_332 context, class_266 objective, CallbackInfo ci) {
      if (this.hud.isState() && this.hud.interfaceSettings.isSelected("Score Board")) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"renderOverlayMessage"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderOverlayMessage(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      if (this.hud.isState() && this.hud.interfaceSettings.isSelected("HotBar")) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"renderExperienceLevel"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderExperienceLevel(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      if (this.hud.isState() && this.hud.interfaceSettings.isSelected("HotBar")) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"renderMainHud"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderMainHud(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      if (this.hud.isState() && this.hud.interfaceSettings.isSelected("HotBar")) {
         context.method_52706(class_1921::method_62277, class_329.field_45314, 0, 0, 1, 1);
         if (this.field_2035.field_1761.method_2908()) {
            this.method_1760(context);
         }

         this.method_1741(context);
         ci.cancel();
      }

   }
}

