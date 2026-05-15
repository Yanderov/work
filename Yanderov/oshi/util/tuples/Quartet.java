package oshi.util.tuples;

import oshi.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class Quartet {
   private final Object a;
   private final Object b;
   private final Object c;
   private final Object d;

   public Quartet(Object a, Object b, Object c, Object d) {
      this.a = a;
      this.b = b;
      this.c = c;
      this.d = d;
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

   public final Object getD() {
      return this.d;
   }
}
