package fun.Yanderov.utils.client.managers.event.events.callables;

import fun.Yanderov.utils.client.managers.event.events.Cancellable;
import fun.Yanderov.utils.client.managers.event.events.Event;

public abstract class EventCancellable implements Event, Cancellable {
   private boolean cancelled;

   protected EventCancellable() {
   }

   public boolean isCancelled() {
      return this.cancelled;
   }

   public void cancel() {
      this.cancelled = true;
   }

   public void setCancelled(boolean cancelled) {
      this.cancelled = cancelled;
   }
}

