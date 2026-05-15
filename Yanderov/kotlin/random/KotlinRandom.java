package kotlin.random;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\f\b\u0002\u0018\u0000 &2\u00020\u0001:\u0001&B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0018\u0010\tJ\u000f\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001f\u0010 R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010!\u001a\u0004\b\"\u0010#R\u0016\u0010$\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010%¨\u0006'"},
   d2 = {"Lkotlin/random/KotlinRandom;", "Ljava/util/Random;", "Lkotlin/random/Random;", "impl", "<init>", "(Lkotlin/random/Random;)V", "", "bits", "next", "(I)I", "", "nextBoolean", "()Z", "", "bytes", "", "nextBytes", "([B)V", "", "nextDouble", "()D", "", "nextFloat", "()F", "nextInt", "()I", "bound", "", "nextLong", "()J", "seed", "setSeed", "(J)V", "Lkotlin/random/Random;", "getImpl", "()Lkotlin/random/Random;", "seedInitialized", "Z", "Companion", "kotlin-stdlib"}
)
final class KotlinRandom extends java.util.Random {
   @NotNull
   private static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private final Random impl;
   private boolean seedInitialized;
   private static final long serialVersionUID = 0L;

   public KotlinRandom(@NotNull Random impl) {
      Intrinsics.checkNotNullParameter(impl, "impl");
      super();
      this.impl = impl;
   }

   @NotNull
   public final Random getImpl() {
      return this.impl;
   }

   protected int next(int bits) {
      return this.impl.nextBits(bits);
   }

   public int nextInt() {
      return this.impl.nextInt();
   }

   public int nextInt(int bound) {
      return this.impl.nextInt(bound);
   }

   public boolean nextBoolean() {
      return this.impl.nextBoolean();
   }

   public long nextLong() {
      return this.impl.nextLong();
   }

   public float nextFloat() {
      return this.impl.nextFloat();
   }

   public double nextDouble() {
      return this.impl.nextDouble();
   }

   public void nextBytes(@NotNull byte[] bytes) {
      Intrinsics.checkNotNullParameter(bytes, "bytes");
      this.impl.nextBytes(bytes);
   }

   public void setSeed(long seed) {
      if (!this.seedInitialized) {
         this.seedInitialized = true;
      } else {
         throw new UnsupportedOperationException("Setting seed is not supported.");
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
      d2 = {"Lkotlin/random/KotlinRandom$Companion;", "", "<init>", "()V", "", "serialVersionUID", "J", "kotlin-stdlib"}
   )
   private static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
