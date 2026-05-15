package dev.client.yanderov.events.item;

import dev.client.yanderov.utils.client.managers.event.events.callables.EventCancellable;

public class SwingDurationEvent extends EventCancellable {
   private float animation;

   public float getAnimation() {
      return this.animation;
   }

   public void setAnimation(float animation) {
      this.animation = animation;
   }
}

