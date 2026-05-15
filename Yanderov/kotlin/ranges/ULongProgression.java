package kotlin.ranges;

import java.util.Iterator;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.WasExperimental;
import kotlin.internal.UProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0017\u0018\u0000  2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001 B!\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0096\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0013H\u0086\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u00028\u0006ø\u0001\u0000¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u00028\u0006ø\u0001\u0000¢\u0006\f\n\u0004\b\u001d\u0010\u001a\u001a\u0004\b\u001e\u0010\u001cR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001a\u001a\u0004\b\u001f\u0010\u001c\u0082\u0002\u0004\n\u0002\b!¨\u0006!"},
   d2 = {"Lkotlin/ranges/ULongProgression;", "", "Lkotlin/ULong;", "start", "endInclusive", "", "step", "<init>", "(JJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "isEmpty", "()Z", "", "iterator", "()Ljava/util/Iterator;", "", "toString", "()Ljava/lang/String;", "first", "J", "getFirst-s-VKNKU", "()J", "last", "getLast-s-VKNKU", "getStep", "Companion", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.5"
)
@WasExperimental(
   markerClass = {ExperimentalUnsignedTypes.class}
)
public class ULongProgression implements Iterable, KMappedMarker {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private final long first;
   private final long last;
   private final long step;

   private ULongProgression(long start, long endInclusive, long step) {
      if (step == 0L) {
         throw new IllegalArgumentException("Step must be non-zero.");
      } else if (step == Long.MIN_VALUE) {
         throw new IllegalArgumentException("Step must be greater than Long.MIN_VALUE to avoid overflow on negation.");
      } else {
         this.first = start;
         this.last = UProgressionUtilKt.getProgressionLastElement-7ftBX0g(start, endInclusive, step);
         this.step = step;
      }
   }

   public final long getFirst_s_VKNKU/* $FF was: getFirst-s-VKNKU*/() {
      return this.first;
   }

   public final long getLast_s_VKNKU/* $FF was: getLast-s-VKNKU*/() {
      return this.last;
   }

   public final long getStep() {
      return this.step;
   }

   @NotNull
   public final Iterator iterator() {
      return new ULongProgressionIterator(this.first, this.last, this.step, (DefaultConstructorMarker)null);
   }

   public boolean isEmpty() {
      return this.step > 0L ? Long.compareUnsigned(this.first, this.last) > 0 : Long.compareUnsigned(this.first, this.last) < 0;
   }

   public boolean equals(@Nullable Object other) {
      return other instanceof ULongProgression && (this.isEmpty() && ((ULongProgression)other).isEmpty() || this.first == ((ULongProgression)other).first && this.last == ((ULongProgression)other).last && this.step == ((ULongProgression)other).step);
   }

   public int hashCode() {
      return this.isEmpty() ? -1 : 31 * (31 * (int)ULong.constructor-impl(this.first ^ ULong.constructor-impl(this.first >>> 32)) + (int)ULong.constructor-impl(this.last ^ ULong.constructor-impl(this.last >>> 32))) + (int)(this.step ^ this.step >>> 32);
   }

   @NotNull
   public String toString() {
      return this.step > 0L ? ULong.toString-impl(this.first) + ".." + ULong.toString-impl(this.last) + " step " + this.step : ULong.toString-impl(this.first) + " downTo " + ULong.toString-impl(this.last) + " step " + -this.step;
   }

   // $FF: synthetic method
   public ULongProgression(long start, long endInclusive, long step, DefaultConstructorMarker $constructor_marker) {
      this(start, endInclusive, step);
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\f\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000b¨\u0006\r"},
      d2 = {"Lkotlin/ranges/ULongProgression$Companion;", "", "<init>", "()V", "Lkotlin/ULong;", "rangeStart", "rangeEnd", "", "step", "Lkotlin/ranges/ULongProgression;", "fromClosedRange-7ftBX0g", "(JJJ)Lkotlin/ranges/ULongProgression;", "fromClosedRange", "kotlin-stdlib"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final ULongProgression fromClosedRange_7ftBX0g/* $FF was: fromClosedRange-7ftBX0g*/(long rangeStart, long rangeEnd, long step) {
         return new ULongProgression(rangeStart, rangeEnd, step, (DefaultConstructorMarker)null);
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
