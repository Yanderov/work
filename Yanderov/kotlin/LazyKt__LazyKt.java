package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000\u001a\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a!\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a6\u0010\t\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0087\n¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"},
   d2 = {"T", "value", "Lkotlin/Lazy;", "lazyOf", "(Ljava/lang/Object;)Lkotlin/Lazy;", "", "thisRef", "Lkotlin/reflect/KProperty;", "property", "getValue", "(Lkotlin/Lazy;Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "kotlin-stdlib"},
   xs = "kotlin/LazyKt"
)
class LazyKt__LazyKt extends LazyKt__LazyJVMKt {
   @NotNull
   public static final Lazy lazyOf(Object value) {
      return new InitializedLazyImpl(value);
   }

   @InlineOnly
   private static final Object getValue(Lazy $this$getValue, Object thisRef, KProperty property) {
      Intrinsics.checkNotNullParameter($this$getValue, "<this>");
      Intrinsics.checkNotNullParameter(property, "property");
      return $this$getValue.getValue();
   }

   public LazyKt__LazyKt() {
   }
}
