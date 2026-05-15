package dev.client.yanderov.events.block;

import dev.client.yanderov.utils.client.managers.event.events.callables.EventCancellable;

public class PushEvent extends EventCancellable {
   private Type type;

   public Type getType() {
      return this.type;
   }

   public PushEvent(Type type) {
      this.type = type;
   }

   public static enum Type {
      COLLISION,
      BLOCK,
      WATER;

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{COLLISION, BLOCK, WATER};
      }
   }
}

