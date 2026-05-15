package fun.Yanderov.utils.math.task;

import fun.Yanderov.features.module.Module;
import java.util.PriorityQueue;

public class TaskProcessor {
   public int tickCounter = 0;
   public PriorityQueue activeTasks = new PriorityQueue((r1, r2) -> Integer.compare(r2.priority, r1.priority));

   public void tick(int deltaTime) {
      this.tickCounter += deltaTime;
   }

   public void addTask(Task task) {
      this.activeTasks.removeIf((r) -> r.provider.equals(task.provider));
      task.expiresIn += this.tickCounter;
      this.activeTasks.add(task);
   }

   public Object fetchActiveTaskValue() {
      while(!this.activeTasks.isEmpty() && this.activeTasks.peek() != null && (((Task)this.activeTasks.peek()).expiresIn <= this.tickCounter || !((Task)this.activeTasks.peek()).provider.isState())) {
         this.activeTasks.poll();
      }

      if (this.activeTasks.isEmpty()) {
         return null;
      } else if (this.activeTasks.peek() != null) {
         return ((Task)this.activeTasks.peek()).value;
      } else {
         return null;
      }
   }

   public static class Task {
      private int expiresIn;
      private final int priority;
      private final Module provider;
      private final Object value;

      public String toString() {
         int var10000 = this.expiresIn;
         return "TaskProcessor.Task(expiresIn=" + var10000 + ", priority=" + this.priority + ", provider=" + String.valueOf(this.provider) + ", value=" + String.valueOf(this.value) + ")";
      }

      public Task(int expiresIn, int priority, Module provider, Object value) {
         this.expiresIn = expiresIn;
         this.priority = priority;
         this.provider = provider;
         this.value = value;
      }
   }
}

