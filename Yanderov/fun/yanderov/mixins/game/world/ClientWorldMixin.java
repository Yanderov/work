package fun.Yanderov.mixins.game.world;

import fun.Yanderov.events.player.EntitySpawnEvent;
import fun.Yanderov.events.render.WorldLoadEvent;
import fun.Yanderov.utils.client.managers.event.EventManager;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import net.minecraft.class_1297;
import net.minecraft.class_638;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_638.class})
public class ClientWorldMixin implements QuickImports {
   @Inject(
      method = {"<init>"},
      at = {@At("RETURN")}
   )
   public void initHook(CallbackInfo info) {
      EventManager.callEvent(new WorldLoadEvent());
   }

   @Inject(
      method = {"addEntity"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void addEntityHook(class_1297 entity, CallbackInfo ci) {
      if (!PlayerInteractionHelper.nullCheck()) {
         EntitySpawnEvent event = new EntitySpawnEvent(entity);
         EventManager.callEvent(event);
         if (event.isCancelled()) {
            ci.cancel();
         }

      }
   }
}

