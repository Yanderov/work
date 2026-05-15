package dev.client.yanderov.utils.client;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.utils.client.managers.api.draggable.AbstractDraggable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class Instance {
   private static final ConcurrentMap instanceModules = new ConcurrentHashMap();
   private static final ConcurrentMap instanceDraggables = new ConcurrentHashMap();

   public static Module get(Class clazz) {
      return (Module)clazz.cast(instanceModules.computeIfAbsent(clazz, (instance) -> YanderovIntegration.getInstance().getModuleProvider().get(instance)));
   }

   public static Module get(String module) {
      return YanderovIntegration.getInstance().getModuleProvider().get(module);
   }

   public static AbstractDraggable getDraggable(Class clazz) {
      return (AbstractDraggable)clazz.cast(instanceDraggables.computeIfAbsent(clazz, (instance) -> YanderovIntegration.getInstance().getDraggableRepository().get(instance)));
   }

   public static AbstractDraggable getDraggable(String draggable) {
      return YanderovIntegration.getInstance().getDraggableRepository().get(draggable);
   }

   private Instance() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}

