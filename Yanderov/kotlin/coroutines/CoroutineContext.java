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
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\bg\u0018\u00002\u00020\u0001:\u0002\u0013\u0014J7\u0010\u0007\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00028\u00002\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00028\u00000\u0004H&¢\u0006\u0004\b\u0007\u0010\bJ*\u0010\f\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\t*\u00020\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\nH¦\u0002¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u000e\u001a\u00020\u00002\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\nH&¢\u0006\u0004\b\u000e\u0010\u000fJ\u0018\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0000H\u0096\u0002¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0015"},
   d2 = {"Lkotlin/coroutines/CoroutineContext;", "", "R", "initial", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext$Element;", "operation", "fold", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "E", "Lkotlin/coroutines/CoroutineContext$Key;", "key", "get", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusKey", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext;", "context", "plus", "(Lkotlin/coroutines/CoroutineContext;)Lkotlin/coroutines/CoroutineContext;", "Element", "Key", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.3"
)
public interface CoroutineContext {
   @Nullable
   Element get(@NotNull Key var1);

   Object fold(Object var1, @NotNull Function2 var2);

   @NotNull
   CoroutineContext plus(@NotNull CoroutineContext var1);

   @NotNull
   CoroutineContext minusKey(@NotNull Key var1);

   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      @NotNull
      public static CoroutineContext plus(@NotNull CoroutineContext $this, @NotNull CoroutineContext context) {
         Intrinsics.checkNotNullParameter(context, "context");
         return context == EmptyCoroutineContext.INSTANCE ? $this : (CoroutineContext)context.fold($this, null.INSTANCE);
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J7\u0010\u0006\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00028\u00002\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00028\u00000\u0004H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J*\u0010\u000b\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\b*\u00020\u00002\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\r\u001a\u00020\u00012\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\tH\u0016¢\u0006\u0004\b\r\u0010\u000eR\u0018\u0010\n\u001a\u0006\u0012\u0002\b\u00030\t8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"},
      d2 = {"Lkotlin/coroutines/CoroutineContext$Element;", "Lkotlin/coroutines/CoroutineContext;", "R", "initial", "Lkotlin/Function2;", "operation", "fold", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "E", "Lkotlin/coroutines/CoroutineContext$Key;", "key", "get", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusKey", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "kotlin-stdlib"}
   )
   public interface Element extends CoroutineContext {
      @NotNull
      Key getKey();

      @Nullable
      Element get(@NotNull Key var1);

      Object fold(Object var1, @NotNull Function2 var2);

      @NotNull
      CoroutineContext minusKey(@NotNull Key var1);

      @Metadata(
         mv = {1, 9, 0},
         k = 3,
         xi = 48
      )
      public static final class DefaultImpls {
         @Nullable
         public static Element get(@NotNull Element $this, @NotNull Key key) {
            Intrinsics.checkNotNullParameter(key, "key");
            Element var10000;
            if (Intrinsics.areEqual((Object)$this.getKey(), (Object)key)) {
               Intrinsics.checkNotNull($this, "null cannot be cast to non-null type E of kotlin.coroutines.CoroutineContext.Element.get");
               var10000 = $this;
            } else {
               var10000 = null;
            }

            return var10000;
         }

         public static Object fold(@NotNull Element $this, Object initial, @NotNull Function2 operation) {
            Intrinsics.checkNotNullParameter(operation, "operation");
            return operation.invoke(initial, $this);
         }

         @NotNull
         public static CoroutineContext minusKey(@NotNull Element $this, @NotNull Key key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return Intrinsics.areEqual((Object)$this.getKey(), (Object)key) ? (CoroutineContext)EmptyCoroutineContext.INSTANCE : (CoroutineContext)$this;
         }

         @NotNull
         public static CoroutineContext plus(@NotNull Element $this, @NotNull CoroutineContext context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return CoroutineContext.DefaultImpls.plus($this, context);
         }
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\bf\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003¨\u0006\u0004"},
      d2 = {"Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlin/coroutines/CoroutineContext$Element;", "E", "", "kotlin-stdlib"}
   )
   public interface Key {
   }
}
