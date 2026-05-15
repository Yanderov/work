package fun.Yanderov.events.player;

import fun.Yanderov.utils.client.managers.event.events.Event;

public class DeathScreenEvent implements Event {
   private int ticksSinceDeath;

   public int getTicksSinceDeath() {
      return this.ticksSinceDeath;
   }

   public DeathScreenEvent(int ticksSinceDeath) {
      this.ticksSinceDeath = ticksSinceDeath;
   }
}

