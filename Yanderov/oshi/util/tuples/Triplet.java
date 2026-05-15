package oshi.util.tuples;

import oshi.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class Triplet {
   private final Object a;
   private final Object b;
   private final Object c;

   public Triplet(Object a, Object b, Object c) {
      this.a = a;
      this.b = b;
      this.c = c;
   }

   public final Object getA() {
      return this.a;
   }

   public final Object getB() {
      return this.b;
   }

   public final Object getC() {
      return this.c;
   }
}
