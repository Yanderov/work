package kotlin.ranges;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\bg\u0018\u0000*\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u00028\u00000\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003J\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\f\u001a\u00020\u00052\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00028\u0000H&¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"},
   d2 = {"Lkotlin/ranges/ClosedFloatingPointRange;", "", "T", "Lkotlin/ranges/ClosedRange;", "value", "", "contains", "(Ljava/lang/Comparable;)Z", "isEmpty", "()Z", "a", "b", "lessThanOrEquals", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Z", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.1"
)
public interface ClosedFloatingPointRange extends ClosedRange {
   boolean contains(@NotNull Comparable var1);

   boolean isEmpty();

   boolean lessThanOrEquals(@NotNull Comparable var1, @NotNull Comparable var2);

   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      public static boolean contains(@NotNull ClosedFloatingPointRange $this, @NotNull Comparable value) {
         Intrinsics.checkNotNullParameter(value, "value");
         return $this.lessThanOrEquals($this.getStart(), value) && $this.lessThanOrEquals(value, $this.getEndInclusive());
      }

      public static boolean isEmpty(@NotNull ClosedFloatingPointRange $this) {
         return !$this.lessThanOrEquals($this.getStart(), $this.getEndInclusive());
      }
   }
}
