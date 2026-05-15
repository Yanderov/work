package kotlin.ranges;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0012\u0018\u0000*\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u00028\u00000\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0005\u001a\u00028\u00008\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0004\u001a\u00028\u00008\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0013\u001a\u0004\b\u0016\u0010\u0015¨\u0006\u0017"},
   d2 = {"Lkotlin/ranges/ComparableRange;", "", "T", "Lkotlin/ranges/ClosedRange;", "start", "endInclusive", "<init>", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Ljava/lang/Comparable;", "getEndInclusive", "()Ljava/lang/Comparable;", "getStart", "kotlin-stdlib"}
)
class ComparableRange implements ClosedRange {
   @NotNull
   private final Comparable start;
   @NotNull
   private final Comparable endInclusive;

   public ComparableRange(@NotNull Comparable start, @NotNull Comparable endInclusive) {
      Intrinsics.checkNotNullParameter(start, "start");
      Intrinsics.checkNotNullParameter(endInclusive, "endInclusive");
      super();
      this.start = start;
      this.endInclusive = endInclusive;
   }

   @NotNull
   public Comparable getStart() {
      return this.start;
   }

   @NotNull
   public Comparable getEndInclusive() {
      return this.endInclusive;
   }

   public boolean equals(@Nullable Object other) {
      return other instanceof ComparableRange && (this.isEmpty() && ((ComparableRange)other).isEmpty() || Intrinsics.areEqual((Object)this.getStart(), (Object)((ComparableRange)other).getStart()) && Intrinsics.areEqual((Object)this.getEndInclusive(), (Object)((ComparableRange)other).getEndInclusive()));
   }

   public int hashCode() {
      return this.isEmpty() ? -1 : 31 * this.getStart().hashCode() + this.getEndInclusive().hashCode();
   }

   @NotNull
   public String toString() {
      return this.getStart() + ".." + this.getEndInclusive();
   }

   public boolean contains(@NotNull Comparable value) {
      return ClosedRange.DefaultImpls.contains(this, value);
   }

   public boolean isEmpty() {
      return ClosedRange.DefaultImpls.isEmpty(this);
   }
}
