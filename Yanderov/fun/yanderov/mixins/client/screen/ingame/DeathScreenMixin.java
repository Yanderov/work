package fun.Yanderov.mixins.client.screen.ingame;

import fun.Yanderov.events.player.DeathScreenEvent;
import fun.Yanderov.utils.client.managers.event.EventManager;
import net.minecraft.class_332;
import net.minecraft.class_418;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_418.class})
public class DeathScreenMixin {
   @Shadow
   private int field_2451;

   @Inject(
      method = {"render"},
      at = {@At("HEAD")}
   )
   public void render(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      EventManager.callEvent(new DeathScreenEvent(this.field_2451));
   }
}

