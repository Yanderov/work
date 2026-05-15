package fun.Yanderov.utils.client;

import fun.Yanderov.Yanderov;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.utils.client.managers.api.draggable.AbstractDraggable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class Instance {
   private static final ConcurrentMap instanceModules = new ConcurrentHashMap();
   private static final ConcurrentMap instanceDraggables = new ConcurrentHashMap();

   public static Module get(Class clazz) {
      return (Module)clazz.cast(instanceModules.computeIfAbsent(clazz, (instance) -> Yanderov.getInstance().getModuleProvider().get(instance)));
   }

   public static Module get(String module) {
      return Yanderov.getInstance().getModuleProvider().get(module);
   }

   public static AbstractDraggable getDraggable(Class clazz) {
      return (AbstractDraggable)clazz.cast(instanceDraggables.computeIfAbsent(clazz, (instance) -> Yanderov.getInstance().getDraggableRepository().get(instance)));
   }

   public static AbstractDraggable getDraggable(String draggable) {
      return Yanderov.getInstance().getDraggableRepository().get(draggable);
   }

   private Instance() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}

