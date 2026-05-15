package fun.Yanderov.events.player;

import fun.Yanderov.utils.client.managers.event.events.callables.EventCancellable;
import net.minecraft.class_1657;

public class JumpEvent extends EventCancellable {
   private class_1657 player;

   public class_1657 getPlayer() {
      return this.player;
   }

   public JumpEvent(class_1657 player) {
      this.player = player;
   }
}

