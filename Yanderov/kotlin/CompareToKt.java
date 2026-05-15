package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a(\u0010\u0004\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u0087\f¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"},
   d2 = {"T", "", "other", "", "compareTo", "(Ljava/lang/Comparable;Ljava/lang/Object;)I", "kotlin-stdlib"}
)
public final class CompareToKt {
   @InlineOnly
   @SinceKotlin(
      version = "1.6"
   )
   private static final int compareTo(Comparable $this$compareTo, Object other) {
      Intrinsics.checkNotNullParameter($this$compareTo, "<this>");
      return $this$compareTo.compareTo(other);
   }
}
