package kotlin;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u001c\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a4\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u00028\u00002\u0006\u0010\u0002\u001a\u00028\u0001H\u0086\u0004¢\u0006\u0004\b\u0004\u0010\u0005\u001a)\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\"\u0004\b\u0000\u0010\u0006*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\b\u0010\t\u001a/\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\"\u0004\b\u0000\u0010\u0006*\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00000\n¢\u0006\u0004\b\b\u0010\u000b¨\u0006\f"},
   d2 = {"A", "B", "that", "Lkotlin/Pair;", "to", "(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;", "T", "", "toList", "(Lkotlin/Pair;)Ljava/util/List;", "Lkotlin/Triple;", "(Lkotlin/Triple;)Ljava/util/List;", "kotlin-stdlib"}
)
@JvmName(
   name = "TuplesKt"
)
public final class TuplesKt {
   @NotNull
   public static final Pair to(Object $this$to, Object that) {
      return new Pair($this$to, that);
   }

   @NotNull
   public static final List toList(@NotNull Pair $this$toList) {
      Intrinsics.checkNotNullParameter($this$toList, "<this>");
      Object[] var1 = new Object[]{$this$toList.getFirst(), $this$toList.getSecond()};
      return CollectionsKt.listOf(var1);
   }

   @NotNull
   public static final List toList(@NotNull Triple $this$toList) {
      Intrinsics.checkNotNullParameter($this$toList, "<this>");
      Object[] var1 = new Object[]{$this$toList.getFirst(), $this$toList.getSecond(), $this$toList.getThird()};
      return CollectionsKt.listOf(var1);
   }
}
