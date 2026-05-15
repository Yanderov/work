package fun.Yanderov.utils.client.managers.event.events;

public interface Cancellable {
   boolean isCancelled();

   void cancel();
}

