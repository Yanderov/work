package dev.client.yanderov.events.player;

import dev.client.yanderov.utils.client.managers.event.events.Event;

public class RotationUpdateEvent implements Event {
   byte type;

   public byte getType() {
      return this.type;
   }

   public RotationUpdateEvent(byte type) {
      this.type = type;
   }
}

