package kotlin.coroutines;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b'\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u001e\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"},
   d2 = {"Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlin/coroutines/CoroutineContext$Element;", "Lkotlin/coroutines/CoroutineContext$Key;", "key", "<init>", "(Lkotlin/coroutines/CoroutineContext$Key;)V", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.3"
)
public abstract class AbstractCoroutineContextElement implements CoroutineContext.Element {
   @NotNull
   private final CoroutineContext.Key key;

   public AbstractCoroutineContextElement(@NotNull CoroutineContext.Key key) {
      Intrinsics.checkNotNullParameter(key, "key");
      super();
      this.key = key;
   }

   @NotNull
   public CoroutineContext.Key getKey() {
      return this.key;
   }

   @Nullable
   public CoroutineContext.Element get(@NotNull CoroutineContext.Key key) {
      return CoroutineContext.Element.DefaultImpls.get(this, key);
   }

   public Object fold(Object initial, @NotNull Function2 operation) {
      return CoroutineContext.Element.DefaultImpls.fold(this, initial, operation);
   }

   @NotNull
   public CoroutineContext minusKey(@NotNull CoroutineContext.Key key) {
      return CoroutineContext.Element.DefaultImpls.minusKey(this, key);
   }

   @NotNull
   public CoroutineContext plus(@NotNull CoroutineContext context) {
      return CoroutineContext.Element.DefaultImpls.plus(this, context);
   }
}
