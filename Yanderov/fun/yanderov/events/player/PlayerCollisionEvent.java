package fun.Yanderov.events.player;

import fun.Yanderov.utils.client.managers.event.events.callables.EventCancellable;
import net.minecraft.class_2248;

public class PlayerCollisionEvent extends EventCancellable {
   private class_2248 block;

   public void setBlock(class_2248 block) {
      this.block = block;
   }

   public class_2248 getBlock() {
      return this.block;
   }

   public PlayerCollisionEvent(class_2248 block) {
      this.block = block;
   }
}

