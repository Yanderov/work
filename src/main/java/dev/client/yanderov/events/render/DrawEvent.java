package dev.client.yanderov.events.render;

import dev.client.yanderov.utils.client.managers.event.events.Event;
import dev.client.yanderov.utils.display.draw.DrawEngine;
import net.minecraft.class_332;

public class DrawEvent implements Event {
   private class_332 drawContext;
   private DrawEngine drawEngine;
   private float partialTicks;

   public class_332 getDrawContext() {
      return this.drawContext;
   }

   public DrawEngine getDrawEngine() {
      return this.drawEngine;
   }

   public float getPartialTicks() {
      return this.partialTicks;
   }

   public DrawEvent(class_332 drawContext, DrawEngine drawEngine, float partialTicks) {
      this.drawContext = drawContext;
      this.drawEngine = drawEngine;
      this.partialTicks = partialTicks;
   }
}

