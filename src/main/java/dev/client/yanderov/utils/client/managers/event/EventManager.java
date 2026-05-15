package dev.client.yanderov.utils.client.managers.event;

import dev.client.yanderov.common.logger.implement.ConsoleLogger;
import dev.client.yanderov.common.logger.implement.MinecraftLogger;
import dev.client.yanderov.features.module.exception.ModuleException;
import dev.client.yanderov.utils.client.managers.event.events.Event;
import dev.client.yanderov.utils.client.managers.event.events.EventStoppable;
import dev.client.yanderov.utils.client.managers.event.types.Priority;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_124;
import net.minecraft.class_2561;

public final class EventManager {
   private static final Map REGISTRY_MAP = new HashMap();

   public void register(Object object) {
      for(Method method : object.getClass().getDeclaredMethods()) {
         if (!isMethodBad(method)) {
            this.register(method, object);
         }
      }

   }

   public void register(Object object, Class eventClass) {
      for(Method method : object.getClass().getDeclaredMethods()) {
         if (!isMethodBad(method, eventClass)) {
            this.register(method, object);
         }
      }

   }

   public void unregister(Object object) {
      for(List dataList : REGISTRY_MAP.values()) {
         for(MethodData data : dataList) {
            if (data.source().equals(object)) {
               dataList.remove(data);
            }
         }
      }

      cleanMap(true);
   }

   public void unregister(Object object, Class eventClass) {
      if (REGISTRY_MAP.containsKey(eventClass)) {
         for(MethodData data : (List)REGISTRY_MAP.get(eventClass)) {
            if (data.source().equals(object)) {
               ((List)REGISTRY_MAP.get(eventClass)).remove(data);
            }
         }

         cleanMap(true);
      }

   }

   private void register(Method method, Object object) {
      Class<? extends Event> indexClass = method.getParameterTypes()[0];
      final MethodData data = new MethodData(object, method, ((EventHandler)method.getAnnotation(EventHandler.class)).value());
      if (!data.target().isAccessible()) {
         data.target().setAccessible(true);
      }

      if (REGISTRY_MAP.containsKey(indexClass)) {
         if (!((List)REGISTRY_MAP.get(indexClass)).contains(data)) {
            ((List)REGISTRY_MAP.get(indexClass)).add(data);
            sortListValue(indexClass);
         }
      } else {
         REGISTRY_MAP.put(indexClass, new CopyOnWriteArrayList() {
            private static final long serialVersionUID = 666L;

            {
               this.add(data);
            }
         });
      }

   }

   public void removeEntry(Class indexClass) {
      Iterator<Map.Entry<Class<? extends Event>, List<MethodData>>> mapIterator = REGISTRY_MAP.entrySet().iterator();

      while(mapIterator.hasNext()) {
         if (((Class)((Map.Entry)mapIterator.next()).getKey()).equals(indexClass)) {
            mapIterator.remove();
            break;
         }
      }

   }

   public static void cleanMap(boolean onlyEmptyEntries) {
      Iterator<Map.Entry<Class<? extends Event>, List<MethodData>>> mapIterator = REGISTRY_MAP.entrySet().iterator();

      while(mapIterator.hasNext()) {
         if (!onlyEmptyEntries || ((List)((Map.Entry)mapIterator.next()).getValue()).isEmpty()) {
            mapIterator.remove();
         }
      }

   }

   private static void sortListValue(Class indexClass) {
      List<MethodData> sortedList = new CopyOnWriteArrayList();

      for(byte priority : Priority.VALUE_ARRAY) {
         for(MethodData data : (List)REGISTRY_MAP.get(indexClass)) {
            if (data.priority() == priority) {
               sortedList.add(data);
            }
         }
      }

      REGISTRY_MAP.put(indexClass, sortedList);
   }

   private static boolean isMethodBad(Method method) {
      return method.getParameterTypes().length != 1 || !method.isAnnotationPresent(EventHandler.class);
   }

   private static boolean isMethodBad(Method method, Class eventClass) {
      return isMethodBad(method) || !method.getParameterTypes()[0].equals(eventClass);
   }

   public static Event callEvent(Event event) {
      List<MethodData> dataList = (List)REGISTRY_MAP.get(event.getClass());
      if (dataList != null) {
         if (event instanceof EventStoppable) {
            EventStoppable stoppable = (EventStoppable)event;

            for(MethodData data : dataList) {
               invoke(data, event);
               if (stoppable.isStopped()) {
                  break;
               }
            }
         } else {
            for(MethodData data : dataList) {
               try {
                  invoke(data, event);
               } catch (Exception e) {
                  e.printStackTrace();
               }
            }
         }
      }

      return event;
   }

   private static void invoke(MethodData data, Event argument) {
      try {
         data.target().invoke(data.source(), argument);
      } catch (IllegalAccessException e) {
         ConsoleLogger consoleLogger = new ConsoleLogger();
         String errorMessage = "Illegal access to method. ";
         errorMessage = errorMessage + "Method: " + data.target().getName() + ", ";
         errorMessage = errorMessage + "Argument: " + argument.toString() + ", ";
         errorMessage = errorMessage + "Log: " + String.valueOf(e.fillInStackTrace());
         consoleLogger.log(errorMessage);
      } catch (IllegalArgumentException e) {
         ConsoleLogger consoleLogger = new ConsoleLogger();
         String errorMessage = "Illegal arguments passed to method. ";
         errorMessage = errorMessage + "Method: " + data.target().getName() + ", ";
         errorMessage = errorMessage + "Argument: " + argument.toString() + ", ";
         errorMessage = errorMessage + "Log: " + String.valueOf(e.getCause());
         consoleLogger.log(errorMessage);
      } catch (InvocationTargetException e) {
         Throwable cause = e.getCause();
         ConsoleLogger consoleLogger = new ConsoleLogger();
         MinecraftLogger minecraftLogger = new MinecraftLogger();
         if (cause instanceof ModuleException moduleException) {
            class_2561[] var10001 = new class_2561[1];
            String var10004 = moduleException.getModuleName();
            var10001[0] = class_2561.method_43470("[" + var10004 + "] " + String.valueOf(class_124.field_1061) + moduleException.getMessage());
            minecraftLogger.minecraftLog(var10001);
         } else {
            String errorMessage = "Exception occurred within invoked method. ";
            errorMessage = errorMessage + "Method: " + data.target().getName() + ", ";
            errorMessage = errorMessage + "Argument: " + argument.toString() + ", ";
            errorMessage = errorMessage + "Log: " + String.valueOf(e.getCause());
            consoleLogger.log(errorMessage);
         }
      }

   }

   private static record MethodData(Object source, Method target, byte priority) {
   }
}

