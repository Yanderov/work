package dev.client.yanderov.events.render;

import dev.client.yanderov.utils.client.managers.event.events.Event;
import net.minecraft.class_4587;

public class WorldRenderEvent implements Event {
   private class_4587 stack;
   private float partialTicks;

   public WorldRenderEvent(class_4587 stack, float partialTicks) {
      this.stack = stack;
      this.partialTicks = partialTicks;
   }

   public class_4587 getStack() {
      return this.stack;
   }

   public float getPartialTicks() {
      return this.partialTicks;
   }
}

