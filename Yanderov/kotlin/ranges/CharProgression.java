package kotlin.ranges;

import kotlin.Metadata;
import kotlin.collections.CharIterator;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\b\u0016\u0018\u0000  2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001 B!\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0096\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0012H\u0096\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u0019\u001a\u0004\b\u001d\u0010\u001bR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b\u001f\u0010\u000f¨\u0006!"},
   d2 = {"Lkotlin/ranges/CharProgression;", "", "", "start", "endInclusive", "", "step", "<init>", "(CCI)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "isEmpty", "()Z", "Lkotlin/collections/CharIterator;", "iterator", "()Lkotlin/collections/CharIterator;", "", "toString", "()Ljava/lang/String;", "first", "C", "getFirst", "()C", "last", "getLast", "I", "getStep", "Companion", "kotlin-stdlib"}
)
public class CharProgression implements Iterable, KMappedMarker {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private final char first;
   private final char last;
   private final int step;

   public CharProgression(char start, char endInclusive, int step) {
      if (step == 0) {
         throw new IllegalArgumentException("Step must be non-zero.");
      } else if (step == Integer.MIN_VALUE) {
         throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
      } else {
         this.first = start;
         this.last = (char)ProgressionUtilKt.getProgressionLastElement(start, endInclusive, step);
         this.step = step;
      }
   }

   public final char getFirst() {
      return this.first;
   }

   public final char getLast() {
      return this.last;
   }

   public final int getStep() {
      return this.step;
   }

   @NotNull
   public CharIterator iterator() {
      return new CharProgressionIterator(this.first, this.last, this.step);
   }

   public boolean isEmpty() {
      return this.step > 0 ? Intrinsics.compare(this.first, this.last) > 0 : Intrinsics.compare(this.first, this.last) < 0;
   }

   public boolean equals(@Nullable Object other) {
      return other instanceof CharProgression && (this.isEmpty() && ((CharProgression)other).isEmpty() || this.first == ((CharProgression)other).first && this.last == ((CharProgression)other).last && this.step == ((CharProgression)other).step);
   }

   public int hashCode() {
      return this.isEmpty() ? -1 : 31 * (31 * this.first + this.last) + this.step;
   }

   @NotNull
   public String toString() {
      return this.step > 0 ? this.first + ".." + this.last + " step " + this.step : this.first + " downTo " + this.last + " step " + -this.step;
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"},
      d2 = {"Lkotlin/ranges/CharProgression$Companion;", "", "<init>", "()V", "", "rangeStart", "rangeEnd", "", "step", "Lkotlin/ranges/CharProgression;", "fromClosedRange", "(CCI)Lkotlin/ranges/CharProgression;", "kotlin-stdlib"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final CharProgression fromClosedRange(char rangeStart, char rangeEnd, int step) {
         return new CharProgression(rangeStart, rangeEnd, step);
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
