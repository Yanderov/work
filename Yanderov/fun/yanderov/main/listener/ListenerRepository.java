package fun.Yanderov.main.listener;

import fun.Yanderov.Yanderov;
import fun.Yanderov.features.impl.misc.MirorAiTrainerListener;
import fun.Yanderov.main.listener.impl.EventListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListenerRepository {
   private final List listeners = new ArrayList();

   public void setup() {
      this.registerListeners(new EventListener(), new MirorAiTrainerListener());
   }

   public void registerListeners(Listener... listeners) {
      this.listeners.addAll(List.of(listeners));
      Arrays.stream(listeners).forEach((listener) -> Yanderov.getInstance().getEventManager().register(listener));
   }

   public List getListeners() {
      return this.listeners;
   }
}

