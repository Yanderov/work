package dev.client.yanderov.utils.client.managers.event.events;

public interface Cancellable {
   boolean isCancelled();

   void cancel();
}

