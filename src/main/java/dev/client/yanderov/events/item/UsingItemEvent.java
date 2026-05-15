package dev.client.yanderov.events.item;

import dev.client.yanderov.utils.client.managers.event.events.callables.EventCancellable;

public class UsingItemEvent extends EventCancellable {
   byte type;

   public byte getType() {
      return this.type;
   }

   public void setType(byte type) {
      this.type = type;
   }

   public UsingItemEvent(byte type) {
      this.type = type;
   }
}

