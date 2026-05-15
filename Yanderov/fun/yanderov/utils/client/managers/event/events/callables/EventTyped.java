package fun.Yanderov.utils.client.managers.event.events.callables;

import fun.Yanderov.utils.client.managers.event.events.Event;
import fun.Yanderov.utils.client.managers.event.events.Typed;

public abstract class EventTyped implements Event, Typed {
   private final byte type;

   protected EventTyped(byte eventType) {
      this.type = eventType;
   }

   public byte getType() {
      return this.type;
   }
}

