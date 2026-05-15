package kotlin.ranges;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\bf\u0018\u0000*\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u00028\u00000\u00012\u00020\u0003J\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\b\u0010\tR\u0014\u0010\f\u001a\u00028\u00008&X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u000e\u001a\u00028\u00008&X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000b¨\u0006\u000f"},
   d2 = {"Lkotlin/ranges/ClosedRange;", "", "T", "", "value", "", "contains", "(Ljava/lang/Comparable;)Z", "isEmpty", "()Z", "getEndInclusive", "()Ljava/lang/Comparable;", "endInclusive", "getStart", "start", "kotlin-stdlib"}
)
public interface ClosedRange {
   @NotNull
   Comparable getStart();

   @NotNull
   Comparable getEndInclusive();

   boolean contains(@NotNull Comparable var1);

   boolean isEmpty();

   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      public static boolean contains(@NotNull ClosedRange $this, @NotNull Comparable value) {
         Intrinsics.checkNotNullParameter(value, "value");
         return value.compareTo($this.getStart()) >= 0 && value.compareTo($this.getEndInclusive()) <= 0;
      }

      public static boolean isEmpty(@NotNull ClosedRange $this) {
         return $this.getStart().compareTo($this.getEndInclusive()) > 0;
      }
   }
}
