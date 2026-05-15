package kotlin.ranges;

import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u0016\u0018\u0000 \u001d2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001dB!\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0011H\u0096\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0017\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u000eR\u0017\u0010\u001a\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u0018\u001a\u0004\b\u001b\u0010\u000eR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u001c\u0010\u000e¨\u0006\u001e"},
   d2 = {"Lkotlin/ranges/IntProgression;", "", "", "start", "endInclusive", "step", "<init>", "(III)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "isEmpty", "()Z", "Lkotlin/collections/IntIterator;", "iterator", "()Lkotlin/collections/IntIterator;", "", "toString", "()Ljava/lang/String;", "first", "I", "getFirst", "last", "getLast", "getStep", "Companion", "kotlin-stdlib"}
)
public class IntProgression implements Iterable, KMappedMarker {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private final int first;
   private final int last;
   private final int step;

   public IntProgression(int start, int endInclusive, int step) {
      if (step == 0) {
         throw new IllegalArgumentException("Step must be non-zero.");
      } else if (step == Integer.MIN_VALUE) {
         throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
      } else {
         this.first = start;
         this.last = ProgressionUtilKt.getProgressionLastElement(start, endInclusive, step);
         this.step = step;
      }
   }

   public final int getFirst() {
      return this.first;
   }

   public final int getLast() {
      return this.last;
   }

   public final int getStep() {
      return this.step;
   }

   @NotNull
   public IntIterator iterator() {
      return new IntProgressionIterator(this.first, this.last, this.step);
   }

   public boolean isEmpty() {
      return this.step > 0 ? this.first > this.last : this.first < this.last;
   }

   public boolean equals(@Nullable Object other) {
      return other instanceof IntProgression && (this.isEmpty() && ((IntProgression)other).isEmpty() || this.first == ((IntProgression)other).first && this.last == ((IntProgression)other).last && this.step == ((IntProgression)other).step);
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
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"},
      d2 = {"Lkotlin/ranges/IntProgression$Companion;", "", "<init>", "()V", "", "rangeStart", "rangeEnd", "step", "Lkotlin/ranges/IntProgression;", "fromClosedRange", "(III)Lkotlin/ranges/IntProgression;", "kotlin-stdlib"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final IntProgression fromClosedRange(int rangeStart, int rangeEnd, int step) {
         return new IntProgression(rangeStart, rangeEnd, step);
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
