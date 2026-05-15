package dev.client.yanderov.events.player;

import dev.client.yanderov.utils.client.managers.event.events.Event;

public class DeathScreenEvent implements Event {
   private int ticksSinceDeath;

   public int getTicksSinceDeath() {
      return this.ticksSinceDeath;
   }

   public DeathScreenEvent(int ticksSinceDeath) {
      this.ticksSinceDeath = ticksSinceDeath;
   }
}

