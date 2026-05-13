package dev.client.mixins.hooks;

import dev.client.WildClient;
import dev.client.event.classes.ClickEvent;
import dev.client.modules.Module;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({Keyboard.class})
public class KeyboardMixin {
   @Inject(
      method = {"onKey"},
      at = {@At("RETURN")}
   )
   private void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
      WildClient.INSTANCE.getEventManager().hookEvent(new ClickEvent(key, action));
      if (action == 1) {
         if (MinecraftClient.getInstance().currentScreen == null) {
            for(Module module : WildClient.INSTANCE.getModuleManager().getModules()) {
               if (module.getBind() == key && module.getBind() > 0 && module.getBind() < 1300) {
                  module.setEnabled(!module.isEnabled());
               }
            }

         }
      }
   }
}
