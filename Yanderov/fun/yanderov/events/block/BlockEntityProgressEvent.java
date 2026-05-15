package fun.Yanderov.events.block;

import fun.Yanderov.utils.client.managers.event.events.Event;
import net.minecraft.class_2586;

public record BlockEntityProgressEvent(class_2586 blockEntity, Type type) implements Event {
   public static enum Type {
      ADD,
      REMOVE;

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{ADD, REMOVE};
      }
   }
}

