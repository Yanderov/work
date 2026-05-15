package dev.client.yanderov.main.listener;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.features.impl.misc.MirorAiTrainerListener;
import dev.client.yanderov.main.listener.impl.EventListener;
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
      Arrays.stream(listeners).forEach((listener) -> YanderovIntegration.getInstance().getEventManager().register(listener));
   }

   public List getListeners() {
      return this.listeners;
   }
}

