package oshi.util.tuples;

import oshi.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class Pair {
   private final Object a;
   private final Object b;

   public Pair(Object a, Object b) {
      this.a = a;
      this.b = b;
   }

   public final Object getA() {
      return this.a;
   }

   public final Object getB() {
      return this.b;
   }
}
