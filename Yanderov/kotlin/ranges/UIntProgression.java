package kotlin.ranges;

import java.util.Iterator;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UInt;
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
   d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u0017\u0018\u0000 \u001e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001eB!\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0096\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012H\u0086\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u00028\u0006ø\u0001\u0000¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u000fR\u001a\u0010\u001b\u001a\u00020\u00028\u0006ø\u0001\u0000¢\u0006\f\n\u0004\b\u001b\u0010\u0019\u001a\u0004\b\u001c\u0010\u000fR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0019\u001a\u0004\b\u001d\u0010\u000f\u0082\u0002\u0004\n\u0002\b!¨\u0006\u001f"},
   d2 = {"Lkotlin/ranges/UIntProgression;", "", "Lkotlin/UInt;", "start", "endInclusive", "", "step", "<init>", "(IIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "isEmpty", "()Z", "", "iterator", "()Ljava/util/Iterator;", "", "toString", "()Ljava/lang/String;", "first", "I", "getFirst-pVg5ArA", "last", "getLast-pVg5ArA", "getStep", "Companion", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.5"
)
@WasExperimental(
   markerClass = {ExperimentalUnsignedTypes.class}
)
public class UIntProgression implements Iterable, KMappedMarker {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private final int first;
   private final int last;
   private final int step;

   private UIntProgression(int start, int endInclusive, int step) {
      if (step == 0) {
         throw new IllegalArgumentException("Step must be non-zero.");
      } else if (step == Integer.MIN_VALUE) {
         throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
      } else {
         this.first = start;
         this.last = UProgressionUtilKt.getProgressionLastElement-Nkh28Cs(start, endInclusive, step);
         this.step = step;
      }
   }

   public final int getFirst_pVg5ArA/* $FF was: getFirst-pVg5ArA*/() {
      return this.first;
   }

   public final int getLast_pVg5ArA/* $FF was: getLast-pVg5ArA*/() {
      return this.last;
   }

   public final int getStep() {
      return this.step;
   }

   @NotNull
   public final Iterator iterator() {
      return new UIntProgressionIterator(this.first, this.last, this.step, (DefaultConstructorMarker)null);
   }

   public boolean isEmpty() {
      return this.step > 0 ? Integer.compareUnsigned(this.first, this.last) > 0 : Integer.compareUnsigned(this.first, this.last) < 0;
   }

   public boolean equals(@Nullable Object other) {
      return other instanceof UIntProgression && (this.isEmpty() && ((UIntProgression)other).isEmpty() || this.first == ((UIntProgression)other).first && this.last == ((UIntProgression)other).last && this.step == ((UIntProgression)other).step);
   }

   public int hashCode() {
      return this.isEmpty() ? -1 : 31 * (31 * this.first + this.last) + this.step;
   }

   @NotNull
   public String toString() {
      return this.step > 0 ? UInt.toString-impl(this.first) + ".." + UInt.toString-impl(this.last) + " step " + this.step : UInt.toString-impl(this.first) + " downTo " + UInt.toString-impl(this.last) + " step " + -this.step;
   }

   // $FF: synthetic method
   public UIntProgression(int start, int endInclusive, int step, DefaultConstructorMarker $constructor_marker) {
      this(start, endInclusive, step);
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\f\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000b¨\u0006\r"},
      d2 = {"Lkotlin/ranges/UIntProgression$Companion;", "", "<init>", "()V", "Lkotlin/UInt;", "rangeStart", "rangeEnd", "", "step", "Lkotlin/ranges/UIntProgression;", "fromClosedRange-Nkh28Cs", "(III)Lkotlin/ranges/UIntProgression;", "fromClosedRange", "kotlin-stdlib"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final UIntProgression fromClosedRange_Nkh28Cs/* $FF was: fromClosedRange-Nkh28Cs*/(int rangeStart, int rangeEnd, int step) {
         return new UIntProgression(rangeStart, rangeEnd, step, (DefaultConstructorMarker)null);
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
