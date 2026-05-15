package fun.Yanderov.utils.display.scissor;

import java.util.ArrayDeque;
import java.util.Queue;

public class Pool {
   private final Queue items = new ArrayDeque();
   private final Producer producer;

   public Pool(Producer producer) {
      this.producer = producer;
   }

   public synchronized Object get() {
      return !this.items.isEmpty() ? this.items.poll() : this.producer.create();
   }

   public synchronized void free(Object obj) {
      this.items.offer(obj);
   }
}

