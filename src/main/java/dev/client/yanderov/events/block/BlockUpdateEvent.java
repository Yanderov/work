package dev.client.yanderov.events.block;

import dev.client.yanderov.utils.client.managers.event.events.Event;
import net.minecraft.class_2338;
import net.minecraft.class_2680;

public record BlockUpdateEvent(class_2680 state, class_2338 pos, Type type) implements Event {
   public static enum Type {
      LOAD,
      UNLOAD,
      UPDATE;

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{LOAD, UNLOAD, UPDATE};
      }
   }
}

