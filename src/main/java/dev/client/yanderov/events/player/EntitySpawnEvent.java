package dev.client.yanderov.events.player;

import dev.client.yanderov.utils.client.managers.event.events.callables.EventCancellable;
import net.minecraft.class_1297;

public class EntitySpawnEvent extends EventCancellable {
   private class_1297 entity;

   public EntitySpawnEvent(class_1297 entity) {
      this.entity = entity;
   }

   public class_1297 getEntity() {
      return this.entity;
   }

   public void setEntity(class_1297 entity) {
      this.entity = entity;
   }
}

