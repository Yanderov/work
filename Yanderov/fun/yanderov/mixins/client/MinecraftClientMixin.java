package fun.Yanderov.mixins.client;

import fun.Yanderov.Yanderov;
import fun.Yanderov.events.container.SetScreenEvent;
import fun.Yanderov.events.player.HotBarUpdateEvent;
import fun.Yanderov.features.impl.combat.NoInteract;
import fun.Yanderov.features.impl.misc.SelfDestruct;
import fun.Yanderov.utils.client.logs.Logger;
import fun.Yanderov.utils.client.managers.event.EventManager;
import fun.Yanderov.utils.client.managers.file.exception.FileProcessingException;
import fun.Yanderov.utils.client.window.WindowStyle;
import fun.Yanderov.utils.client.window.WindowTitleAnimation;
import fun.Yanderov.utils.display.font.Fonts;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_310;
import net.minecraft.class_437;
import net.minecraft.class_542;
import net.minecraft.class_634;
import net.minecraft.class_636;
import net.minecraft.class_746;
import net.minecraft.class_757;
import net.minecraft.class_1269.class_9861;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({class_310.class})
public abstract class MinecraftClientMixin implements QuickImports {
   @Shadow
   public @Nullable class_636 field_1761;
   @Shadow
   public @Nullable class_746 field_1724;
   @Shadow
   @Final
   public class_757 field_1773;
   @Shadow
   public @Nullable class_437 field_1755;
   private final WindowTitleAnimation titleUtil = WindowTitleAnimation.getInstance();

   @Shadow
   public abstract @Nullable class_634 method_1562();

   @Inject(
      at = {@At("TAIL")},
      method = {"<init>"}
   )
   private void onInit(class_542 args, CallbackInfo ci) {
      if (!SelfDestruct.unhooked) {
         Fonts.init();
         class_310.method_1551().method_22683().method_24286(this.titleUtil.getCurrentTitle());
      }
   }

   @Inject(
      at = {@At("HEAD")},
      method = {"stop"}
   )
   private void stop(CallbackInfo ci) {
      Logger.info("Stopping for MinecraftClient");
      if (Yanderov.getInstance().isInitialized()) {
         try {
            Yanderov.getInstance().getFileController().saveFiles();
         } catch (FileProcessingException e) {
            String var10000 = e.getMessage();
            Logger.error("Error occurred while saving files: " + var10000 + " " + String.valueOf(e.getCause()));
         } finally {
            Yanderov.getInstance().getFileController().stopAutoSave();
         }
      }

   }

   @Inject(
      method = {"doItemUse"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/util/Hand;values()[Lnet/minecraft/util/Hand;"
)},
      cancellable = true
   )
   public void doItemUseHook(CallbackInfo ci) {
      if (NoInteract.getInstance().isState()) {
         for(class_1268 hand : class_1268.values()) {
            if (!this.field_1724.method_5998(hand).method_7960()) {
               class_1269 result = this.field_1761.method_2919(this.field_1724, hand);
               if (result.method_23665()) {
                  if (result instanceof class_1269.class_9860) {
                     class_1269.class_9860 success = (class_1269.class_9860)result;
                     if (success.comp_2909().equals(class_9861.field_52427)) {
                        this.field_1773.field_4012.method_3215(hand);
                        this.field_1724.method_6104(hand);
                     }
                  }

                  ci.cancel();
               }
            }
         }
      }

   }

   @Inject(
      method = {"setScreen"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void setScreenHook(class_437 screen, CallbackInfo ci) {
      if (!SelfDestruct.unhooked) {
         SetScreenEvent event = new SetScreenEvent(screen);
         EventManager.callEvent(event);
         Yanderov.getInstance().getDraggableRepository().draggable().forEach((drag) -> drag.setScreen(event));
         class_437 eventScreen = event.getScreen();
         if (screen != eventScreen) {
            mc.method_1507(eventScreen);
            ci.cancel();
         }

      }
   }

   @Inject(
      method = {"onResolutionChanged"},
      at = {@At("TAIL")}
   )
   private void applyDarkMode(CallbackInfo ci) {
      if (!SelfDestruct.unhooked) {
         String os = System.getProperty("os.name").toLowerCase();
         if (!os.contains("linux")) {
            class_310 client = class_310.method_1551();
            WindowStyle.setDarkMode(client.method_22683().method_4490());
         }
      }
   }

   @Inject(
      method = {"tick"},
      at = {@At("HEAD")}
   )
   private void onTick(CallbackInfo ci) {
      if (!SelfDestruct.unhooked) {
         this.titleUtil.updateTitle();
         class_310.method_1551().method_22683().method_24286(this.titleUtil.getCurrentTitle());
      }
   }

   @Inject(
      method = {"updateWindowTitle"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onUpdateWindowTitle(CallbackInfo ci) {
      if (!SelfDestruct.unhooked) {
         class_310.method_1551().method_22683().method_24286(this.titleUtil.getCurrentTitle());
         ci.cancel();
      }
   }

   @Inject(
      method = {"handleInputEvents"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/ClientPlayerEntity;getInventory()Lnet/minecraft/entity/player/PlayerInventory;"
)},
      cancellable = true
   )
   public void handleInputEventsHook(CallbackInfo ci) {
      HotBarUpdateEvent event = new HotBarUpdateEvent();
      EventManager.callEvent(event);
      if (event.isCancelled()) {
         ci.cancel();
      }

   }
}

