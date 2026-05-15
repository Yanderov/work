package dev.client.yanderov.events.player;

import dev.client.yanderov.utils.client.managers.event.events.Event;
import net.minecraft.class_243;

public class FireworkEvent implements Event {
   public class_243 vector;

   public FireworkEvent(class_243 vector) {
      this.vector = vector;
   }

   public class_243 getVector() {
      return this.vector;
   }

   public void setVector(class_243 vector) {
      this.vector = vector;
   }
}

