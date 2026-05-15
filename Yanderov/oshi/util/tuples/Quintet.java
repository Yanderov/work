package oshi.util.tuples;

import oshi.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class Quintet {
   private final Object a;
   private final Object b;
   private final Object c;
   private final Object d;
   private final Object e;

   public Quintet(Object a, Object b, Object c, Object d, Object e) {
      this.a = a;
      this.b = b;
      this.c = c;
      this.d = d;
      this.e = e;
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

   public final Object getE() {
      return this.e;
   }
}
