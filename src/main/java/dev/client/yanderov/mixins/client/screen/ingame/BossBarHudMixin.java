package dev.client.yanderov.mixins.client.screen.ingame;

import dev.client.yanderov.features.impl.render.Hud;
import net.minecraft.class_337;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_337.class})
public class BossBarHudMixin {
   @Inject(
      method = {"render"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void render(CallbackInfo ci) {
      if (Hud.getInstance().isState() && Hud.getInstance().interfaceSettings.isSelected("Boss Bars")) {
         ci.cancel();
      }

   }
}

