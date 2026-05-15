package kotlin.random;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\b'\u0018\u0000 &2\u00020\u0001:\u0001&B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ+\u0010\r\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\r\u0010\u0011J\u0017\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\r\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0018J\u001f\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u001aJ\u000f\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u001e\u0010\u0007J\u001f\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u001e\u0010 J\u000f\u0010\"\u001a\u00020!H\u0016¢\u0006\u0004\b\"\u0010#J\u0017\u0010\"\u001a\u00020!2\u0006\u0010\u0017\u001a\u00020!H\u0016¢\u0006\u0004\b\"\u0010$J\u001f\u0010\"\u001a\u00020!2\u0006\u0010\u0019\u001a\u00020!2\u0006\u0010\u0017\u001a\u00020!H\u0016¢\u0006\u0004\b\"\u0010%¨\u0006'"},
   d2 = {"Lkotlin/random/Random;", "", "<init>", "()V", "", "bitCount", "nextBits", "(I)I", "", "nextBoolean", "()Z", "", "array", "nextBytes", "([B)[B", "fromIndex", "toIndex", "([BII)[B", "size", "(I)[B", "", "nextDouble", "()D", "until", "(D)D", "from", "(DD)D", "", "nextFloat", "()F", "nextInt", "()I", "(II)I", "", "nextLong", "()J", "(J)J", "(JJ)J", "Default", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.3"
)
@SourceDebugExtension({"SMAP\nRandom.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Random.kt\nkotlin/random/Random\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,383:1\n1#2:384\n*E\n"})
public abstract class Random {
   @NotNull
   public static final Default Default = new Default((DefaultConstructorMarker)null);
   @NotNull
   private static final Random defaultRandom;

   public abstract int nextBits(int var1);

   public int nextInt() {
      return this.nextBits(32);
   }

   public int nextInt(int until) {
      return this.nextInt(0, until);
   }

   public int nextInt(int from, int until) {
      RandomKt.checkRangeBounds(from, until);
      int n = until - from;
      if (n <= 0 && n != Integer.MIN_VALUE) {
         int rnd;
         do {
            rnd = this.nextInt();
         } while(!(from <= rnd ? rnd < until : false));

         return rnd;
      } else {
         int var10000;
         if ((n & -n) == n) {
            int bitCount = RandomKt.fastLog2(n);
            var10000 = this.nextBits(bitCount);
         } else {
            int v = 0;

            int bits;
            do {
               bits = this.nextInt() >>> 1;
               v = bits % n;
            } while(bits - v + (n - 1) < 0);

            var10000 = v;
         }

         int rnd = var10000;
         return from + rnd;
      }
   }

   public long nextLong() {
      return ((long)this.nextInt() << 32) + (long)this.nextInt();
   }

   public long nextLong(long until) {
      return this.nextLong(0L, until);
   }

   public long nextLong(long from, long until) {
      RandomKt.checkRangeBounds(from, until);
      long n = until - from;
      if (n <= 0L) {
         long rnd;
         do {
            rnd = this.nextLong();
         } while(!(from <= rnd ? rnd < until : false));

         return rnd;
      } else {
         long rnd = 0L;
         if ((n & -n) == n) {
            int nLow = (int)n;
            int nHigh = (int)(n >>> 32);
            long var10000;
            if (nLow != 0) {
               int bitCount = RandomKt.fastLog2(nLow);
               var10000 = (long)this.nextBits(bitCount) & 4294967295L;
            } else if (nHigh == 1) {
               var10000 = (long)this.nextInt() & 4294967295L;
            } else {
               int bitCount = RandomKt.fastLog2(nHigh);
               var10000 = ((long)this.nextBits(bitCount) << 32) + ((long)this.nextInt() & 4294967295L);
            }

            rnd = var10000;
         } else {
            long v = 0L;

            long bits;
            do {
               bits = this.nextLong() >>> 1;
               v = bits % n;
            } while(bits - v + (n - 1L) < 0L);

            rnd = v;
         }

         return from + rnd;
      }
   }

   public boolean nextBoolean() {
      return this.nextBits(1) != 0;
   }

   public double nextDouble() {
      return PlatformRandomKt.doubleFromParts(this.nextBits(26), this.nextBits(27));
   }

   public double nextDouble(double until) {
      return this.nextDouble((double)0.0F, until);
   }

   public double nextDouble(double from, double until) {
      RandomKt.checkRangeBounds(from, until);
      double size = until - from;
      double var10000;
      if (Double.isInfinite(size) && !Double.isInfinite(from) && !Double.isNaN(from) && !Double.isInfinite(until) && !Double.isNaN(until)) {
         double r1 = this.nextDouble() * (until / (double)2 - from / (double)2);
         var10000 = from + r1 + r1;
      } else {
         var10000 = from + this.nextDouble() * size;
      }

      double r = var10000;
      return r >= until ? Math.nextAfter(until, Double.NEGATIVE_INFINITY) : r;
   }

   public float nextFloat() {
      return (float)this.nextBits(24) / 1.6777216E7F;
   }

   @NotNull
   public byte[] nextBytes(@NotNull byte[] array, int fromIndex, int toIndex) {
      Intrinsics.checkNotNullParameter(array, "array");
      int steps = (0 <= fromIndex ? fromIndex <= array.length : false) && (0 <= toIndex ? toIndex <= array.length : false);
      if (!steps) {
         int var15 = 0;
         String var16 = "fromIndex (" + fromIndex + ") or toIndex (" + toIndex + ") are out of range: 0.." + array.length + '.';
         throw new IllegalArgumentException(var16.toString());
      } else {
         steps = fromIndex <= toIndex;
         if (!steps) {
            int var13 = 0;
            String var14 = "fromIndex (" + fromIndex + ") must be not greater than toIndex (" + toIndex + ").";
            throw new IllegalArgumentException(var14.toString());
         } else {
            steps = (toIndex - fromIndex) / 4;
            int position = 0;
            position = fromIndex;

            for(int var6 = 0; var6 < steps; ++var6) {
               int var8 = 0;
               int v = this.nextInt();
               array[position] = (byte)v;
               array[position + 1] = (byte)(v >>> 8);
               array[position + 2] = (byte)(v >>> 16);
               array[position + 3] = (byte)(v >>> 24);
               position += 4;
            }

            int remainder = toIndex - position;
            int vr = this.nextBits(remainder * 8);

            for(int i = 0; i < remainder; ++i) {
               array[position + i] = (byte)(vr >>> i * 8);
            }

            return array;
         }
      }
   }

   // $FF: synthetic method
   public static byte[] nextBytes$default(Random var0, byte[] var1, int var2, int var3, int var4, Object var5) {
      if (var5 != null) {
         throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: nextBytes");
      } else {
         if ((var4 & 2) != 0) {
            var2 = 0;
         }

         if ((var4 & 4) != 0) {
            var3 = var1.length;
         }

         return var0.nextBytes(var1, var2, var3);
      }
   }

   @NotNull
   public byte[] nextBytes(@NotNull byte[] array) {
      Intrinsics.checkNotNullParameter(array, "array");
      return this.nextBytes(array, 0, array.length);
   }

   @NotNull
   public byte[] nextBytes(int size) {
      return this.nextBytes(new byte[size]);
   }

   static {
      defaultRandom = PlatformImplementationsKt.IMPLEMENTATIONS.defaultPlatformRandom();
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0001-B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J'\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000f\u0010\u0013J\u0017\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000f\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u001aJ\u001f\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\u001dH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\u0006H\u0016¢\u0006\u0004\b \u0010!J\u0017\u0010 \u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0006H\u0016¢\u0006\u0004\b \u0010\tJ\u001f\u0010 \u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0006H\u0016¢\u0006\u0004\b \u0010\"J\u000f\u0010$\u001a\u00020#H\u0016¢\u0006\u0004\b$\u0010%J\u0017\u0010$\u001a\u00020#2\u0006\u0010\u0019\u001a\u00020#H\u0016¢\u0006\u0004\b$\u0010&J\u001f\u0010$\u001a\u00020#2\u0006\u0010\u001b\u001a\u00020#2\u0006\u0010\u0019\u001a\u00020#H\u0016¢\u0006\u0004\b$\u0010'J\u000f\u0010)\u001a\u00020(H\u0002¢\u0006\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010,¨\u0006."},
      d2 = {"Lkotlin/random/Random$Default;", "Lkotlin/random/Random;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "<init>", "()V", "", "bitCount", "nextBits", "(I)I", "", "nextBoolean", "()Z", "", "array", "nextBytes", "([B)[B", "fromIndex", "toIndex", "([BII)[B", "size", "(I)[B", "", "nextDouble", "()D", "until", "(D)D", "from", "(DD)D", "", "nextFloat", "()F", "nextInt", "()I", "(II)I", "", "nextLong", "()J", "(J)J", "(JJ)J", "", "writeReplace", "()Ljava/lang/Object;", "defaultRandom", "Lkotlin/random/Random;", "Serialized", "kotlin-stdlib"}
   )
   public static final class Default extends Random implements Serializable {
      private Default() {
      }

      private final Object writeReplace() {
         return Random.Default.Serialized.INSTANCE;
      }

      public int nextBits(int bitCount) {
         return Random.defaultRandom.nextBits(bitCount);
      }

      public int nextInt() {
         return Random.defaultRandom.nextInt();
      }

      public int nextInt(int until) {
         return Random.defaultRandom.nextInt(until);
      }

      public int nextInt(int from, int until) {
         return Random.defaultRandom.nextInt(from, until);
      }

      public long nextLong() {
         return Random.defaultRandom.nextLong();
      }

      public long nextLong(long until) {
         return Random.defaultRandom.nextLong(until);
      }

      public long nextLong(long from, long until) {
         return Random.defaultRandom.nextLong(from, until);
      }

      public boolean nextBoolean() {
         return Random.defaultRandom.nextBoolean();
      }

      public double nextDouble() {
         return Random.defaultRandom.nextDouble();
      }

      public double nextDouble(double until) {
         return Random.defaultRandom.nextDouble(until);
      }

      public double nextDouble(double from, double until) {
         return Random.defaultRandom.nextDouble(from, until);
      }

      public float nextFloat() {
         return Random.defaultRandom.nextFloat();
      }

      @NotNull
      public byte[] nextBytes(@NotNull byte[] array) {
         Intrinsics.checkNotNullParameter(array, "array");
         return Random.defaultRandom.nextBytes(array);
      }

      @NotNull
      public byte[] nextBytes(int size) {
         return Random.defaultRandom.nextBytes(size);
      }

      @NotNull
      public byte[] nextBytes(@NotNull byte[] array, int fromIndex, int toIndex) {
         Intrinsics.checkNotNullParameter(array, "array");
         return Random.defaultRandom.nextBytes(array, fromIndex, toIndex);
      }

      // $FF: synthetic method
      public Default(DefaultConstructorMarker $constructor_marker) {
         this();
      }

      @Metadata(
         mv = {1, 9, 0},
         k = 1,
         xi = 48,
         d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\t\u0010\n¨\u0006\u000b"},
         d2 = {"Lkotlin/random/Random$Default$Serialized;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "<init>", "()V", "", "readResolve", "()Ljava/lang/Object;", "", "serialVersionUID", "J", "kotlin-stdlib"}
      )
      private static final class Serialized implements Serializable {
         @NotNull
         public static final Serialized INSTANCE = new Serialized();
         private static final long serialVersionUID = 0L;

         private final Object readResolve() {
            return Random.Default;
         }
      }
   }
}
