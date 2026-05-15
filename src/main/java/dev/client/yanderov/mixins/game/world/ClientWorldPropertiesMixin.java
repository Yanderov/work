package dev.client.yanderov.mixins.game.world;

import dev.client.yanderov.features.impl.render.WorldTweaks;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import net.minecraft.class_638;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_638.class_5271.class})
public class ClientWorldPropertiesMixin implements QuickImports {
   @Shadow
   private long field_24439;

   @Inject(
      method = {"setTimeOfDay"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void setTimeOfDayHook(long timeOfDay, CallbackInfo ci) {
      WorldTweaks tweaks = WorldTweaks.getInstance();
      if (tweaks.state && tweaks.modeSetting.isSelected("Time")) {
         this.field_24439 = (long)tweaks.timeSetting.getInt() * 1000L;
         ci.cancel();
      }

   }
}

