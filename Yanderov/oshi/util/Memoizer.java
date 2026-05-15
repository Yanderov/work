package oshi.util;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import oshi.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class Memoizer {
   private static final Supplier DEFAULT_EXPIRATION_NANOS;

   private Memoizer() {
   }

   private static long queryExpirationConfig() {
      return TimeUnit.MILLISECONDS.toNanos((long)GlobalConfig.get("oshi.util.memoizer.expiration", 300));
   }

   public static long defaultExpiration() {
      return (Long)DEFAULT_EXPIRATION_NANOS.get();
   }

   public static Supplier memoize(final Supplier original, final long ttlNanos) {
      return new Supplier() {
         private final Supplier delegate = original;
         private volatile Object value;
         private volatile long expirationNanos;

         public Object get() {
            long nanos = this.expirationNanos;
            long now = System.nanoTime();
            if (nanos == 0L || ttlNanos >= 0L && now - nanos >= 0L) {
               synchronized(this) {
                  if (nanos == this.expirationNanos) {
                     T t = (T)this.delegate.get();
                     this.value = t;
                     nanos = now + ttlNanos;
                     this.expirationNanos = nanos == 0L ? 1L : nanos;
                     return t;
                  }
               }
            }

            return this.value;
         }
      };
   }

   public static Supplier memoize(Supplier original) {
      return memoize(original, -1L);
   }

   static {
      DEFAULT_EXPIRATION_NANOS = memoize(Memoizer::queryExpirationConfig, TimeUnit.MINUTES.toNanos(1L));
   }
}
