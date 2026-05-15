package kotlin;

import kotlin.internal.InlineOnly;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\f\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u0016\u0010\u0002\u001a\u00020\u0001*\u0004\u0018\u00010\u0000H\u0087\b¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"},
   d2 = {"", "", "hashCode", "(Ljava/lang/Object;)I", "kotlin-stdlib"}
)
public final class HashCodeKt {
   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final int hashCode(Object $this$hashCode) {
      return $this$hashCode != null ? $this$hashCode.hashCode() : 0;
   }
}
