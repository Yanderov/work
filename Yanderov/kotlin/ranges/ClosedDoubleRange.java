package kotlin.ranges;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\t\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0002H\u0096\u0002¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\r\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0096\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001cR\u0014\u0010\u0004\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010\u0003\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u001f¨\u0006!"},
   d2 = {"Lkotlin/ranges/ClosedDoubleRange;", "Lkotlin/ranges/ClosedFloatingPointRange;", "", "start", "endInclusive", "<init>", "(DD)V", "value", "", "contains", "(D)Z", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "isEmpty", "()Z", "a", "b", "lessThanOrEquals", "(DD)Z", "", "toString", "()Ljava/lang/String;", "_endInclusive", "D", "_start", "getEndInclusive", "()Ljava/lang/Double;", "getStart", "kotlin-stdlib"}
)
final class ClosedDoubleRange implements ClosedFloatingPointRange {
   private final double _start;
   private final double _endInclusive;

   public ClosedDoubleRange(double start, double endInclusive) {
      this._start = start;
      this._endInclusive = endInclusive;
   }

   @NotNull
   public Double getStart() {
      return this._start;
   }

   @NotNull
   public Double getEndInclusive() {
      return this._endInclusive;
   }

   public boolean lessThanOrEquals(double a, double b) {
      return a <= b;
   }

   public boolean contains(double value) {
      return value >= this._start && value <= this._endInclusive;
   }

   public boolean isEmpty() {
      return !(this._start <= this._endInclusive);
   }

   public boolean equals(@Nullable Object other) {
      return other instanceof ClosedDoubleRange && (this.isEmpty() && ((ClosedDoubleRange)other).isEmpty() || this._start == ((ClosedDoubleRange)other)._start && this._endInclusive == ((ClosedDoubleRange)other)._endInclusive);
   }

   public int hashCode() {
      return this.isEmpty() ? -1 : 31 * Double.hashCode(this._start) + Double.hashCode(this._endInclusive);
   }

   @NotNull
   public String toString() {
      return this._start + ".." + this._endInclusive;
   }
}
