package kotlin.random;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import kotlin.ranges.LongRange;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000@\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u0003\u0010\u0006\u001a\u001f\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u001f\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\rH\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u001f\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u000f\u0010\u0011\u001a\u001f\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\u000f\u0010\u0012\u001a\u0017\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u001b\u0010\u0018\u001a\u00020\u0000*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0016H\u0007¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u001b\u0010\u001b\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u001aH\u0007¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u001b\u0010\u001e\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u001e\u0010\u001f¨\u0006 "},
   d2 = {"", "seed", "Lkotlin/random/Random;", "Random", "(I)Lkotlin/random/Random;", "", "(J)Lkotlin/random/Random;", "", "from", "until", "", "boundsErrorMessage", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/String;", "", "", "checkRangeBounds", "(DD)V", "(II)V", "(JJ)V", "value", "fastLog2", "(I)I", "Lkotlin/ranges/IntRange;", "range", "nextInt", "(Lkotlin/random/Random;Lkotlin/ranges/IntRange;)I", "Lkotlin/ranges/LongRange;", "nextLong", "(Lkotlin/random/Random;Lkotlin/ranges/LongRange;)J", "bitCount", "takeUpperBits", "(II)I", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nRandom.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Random.kt\nkotlin/random/RandomKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,383:1\n1#2:384\n*E\n"})
public final class RandomKt {
   @SinceKotlin(
      version = "1.3"
   )
   @NotNull
   public static final Random Random(int seed) {
      return new XorWowRandom(seed, seed >> 31);
   }

   @SinceKotlin(
      version = "1.3"
   )
   @NotNull
   public static final Random Random(long seed) {
      return new XorWowRandom((int)seed, (int)(seed >> 32));
   }

   @SinceKotlin(
      version = "1.3"
   )
   public static final int nextInt(@NotNull Random $this$nextInt, @NotNull IntRange range) {
      Intrinsics.checkNotNullParameter($this$nextInt, "<this>");
      Intrinsics.checkNotNullParameter(range, "range");
      if (range.isEmpty()) {
         throw new IllegalArgumentException("Cannot get random in empty range: " + range);
      } else {
         return range.getLast() < Integer.MAX_VALUE ? $this$nextInt.nextInt(range.getFirst(), range.getLast() + 1) : (range.getFirst() > Integer.MIN_VALUE ? $this$nextInt.nextInt(range.getFirst() - 1, range.getLast()) + 1 : $this$nextInt.nextInt());
      }
   }

   @SinceKotlin(
      version = "1.3"
   )
   public static final long nextLong(@NotNull Random $this$nextLong, @NotNull LongRange range) {
      Intrinsics.checkNotNullParameter($this$nextLong, "<this>");
      Intrinsics.checkNotNullParameter(range, "range");
      if (range.isEmpty()) {
         throw new IllegalArgumentException("Cannot get random in empty range: " + range);
      } else {
         return range.getLast() < Long.MAX_VALUE ? $this$nextLong.nextLong(range.getFirst(), range.getLast() + 1L) : (range.getFirst() > Long.MIN_VALUE ? $this$nextLong.nextLong(range.getFirst() - 1L, range.getLast()) + 1L : $this$nextLong.nextLong());
      }
   }

   public static final int fastLog2(int value) {
      return 31 - Integer.numberOfLeadingZeros(value);
   }

   public static final int takeUpperBits(int $this$takeUpperBits, int bitCount) {
      return $this$takeUpperBits >>> 32 - bitCount & -bitCount >> 31;
   }

   public static final void checkRangeBounds(int from, int until) {
      boolean var2 = until > from;
      if (!var2) {
         int var3 = 0;
         String var4 = boundsErrorMessage(from, until);
         throw new IllegalArgumentException(var4.toString());
      }
   }

   public static final void checkRangeBounds(long from, long until) {
      boolean var4 = until > from;
      if (!var4) {
         int var5 = 0;
         String var6 = boundsErrorMessage(from, until);
         throw new IllegalArgumentException(var6.toString());
      }
   }

   public static final void checkRangeBounds(double from, double until) {
      boolean var4 = until > from;
      if (!var4) {
         int var5 = 0;
         String var6 = boundsErrorMessage(from, until);
         throw new IllegalArgumentException(var6.toString());
      }
   }

   @NotNull
   public static final String boundsErrorMessage(@NotNull Object from, @NotNull Object until) {
      Intrinsics.checkNotNullParameter(from, "from");
      Intrinsics.checkNotNullParameter(until, "until");
      return "Random range is empty: [" + from + ", " + until + ").";
   }
}
