package fun.Yanderov.events.chat;

import fun.Yanderov.utils.client.managers.event.events.callables.EventCancellable;

public class TabCompleteEvent extends EventCancellable {
   public final String prefix;
   public String[] completions;

   public TabCompleteEvent(String prefix) {
      this.prefix = prefix;
      this.completions = null;
   }
}

