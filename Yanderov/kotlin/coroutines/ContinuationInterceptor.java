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
   d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bg\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012J*\u0010\u0005\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0002¢\u0006\u0004\b\u0005\u0010\u0006J)\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\bH&¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\r\u001a\u00020\f2\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u0010\u001a\u00020\u000f2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\bH\u0016¢\u0006\u0004\b\u0010\u0010\u0011¨\u0006\u0013"},
   d2 = {"Lkotlin/coroutines/ContinuationInterceptor;", "Lkotlin/coroutines/CoroutineContext$Element;", "E", "Lkotlin/coroutines/CoroutineContext$Key;", "key", "get", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "T", "Lkotlin/coroutines/Continuation;", "continuation", "interceptContinuation", "(Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "Lkotlin/coroutines/CoroutineContext;", "minusKey", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext;", "", "releaseInterceptedContinuation", "(Lkotlin/coroutines/Continuation;)V", "Key", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.3"
)
public interface ContinuationInterceptor extends CoroutineContext.Element {
   @NotNull
   Key Key = ContinuationInterceptor.Key.$$INSTANCE;

   @NotNull
   Continuation interceptContinuation(@NotNull Continuation var1);

   void releaseInterceptedContinuation(@NotNull Continuation var1);

   @Nullable
   CoroutineContext.Element get(@NotNull CoroutineContext.Key var1);

   @NotNull
   CoroutineContext minusKey(@NotNull CoroutineContext.Key var1);

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"},
      d2 = {"Lkotlin/coroutines/ContinuationInterceptor$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlin/coroutines/ContinuationInterceptor;", "<init>", "()V", "kotlin-stdlib"}
   )
   public static final class Key implements CoroutineContext.Key {
      // $FF: synthetic field
      static final Key $$INSTANCE = new Key();

      private Key() {
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      public static void releaseInterceptedContinuation(@NotNull ContinuationInterceptor $this, @NotNull Continuation continuation) {
         Intrinsics.checkNotNullParameter(continuation, "continuation");
      }

      @Nullable
      public static CoroutineContext.Element get(@NotNull ContinuationInterceptor $this, @NotNull CoroutineContext.Key key) {
         Intrinsics.checkNotNullParameter(key, "key");
         if (key instanceof AbstractCoroutineContextKey) {
            CoroutineContext.Element var3;
            if (((AbstractCoroutineContextKey)key).isSubKey$kotlin_stdlib($this.getKey())) {
               CoroutineContext.Element var2 = ((AbstractCoroutineContextKey)key).tryCast$kotlin_stdlib($this);
               var3 = var2 instanceof CoroutineContext.Element ? var2 : null;
            } else {
               var3 = null;
            }

            return var3;
         } else {
            CoroutineContext.Element var10000;
            if (ContinuationInterceptor.Key == key) {
               Intrinsics.checkNotNull($this, "null cannot be cast to non-null type E of kotlin.coroutines.ContinuationInterceptor.get");
               var10000 = $this;
            } else {
               var10000 = null;
            }

            return var10000;
         }
      }

      @NotNull
      public static CoroutineContext minusKey(@NotNull ContinuationInterceptor $this, @NotNull CoroutineContext.Key key) {
         Intrinsics.checkNotNullParameter(key, "key");
         if (!(key instanceof AbstractCoroutineContextKey)) {
            return ContinuationInterceptor.Key == key ? (CoroutineContext)EmptyCoroutineContext.INSTANCE : (CoroutineContext)$this;
         } else {
            return ((AbstractCoroutineContextKey)key).isSubKey$kotlin_stdlib($this.getKey()) && ((AbstractCoroutineContextKey)key).tryCast$kotlin_stdlib($this) != null ? (CoroutineContext)EmptyCoroutineContext.INSTANCE : (CoroutineContext)$this;
         }
      }

      public static Object fold(@NotNull ContinuationInterceptor $this, Object initial, @NotNull Function2 operation) {
         Intrinsics.checkNotNullParameter(operation, "operation");
         return CoroutineContext.Element.DefaultImpls.fold($this, initial, operation);
      }

      @NotNull
      public static CoroutineContext plus(@NotNull ContinuationInterceptor $this, @NotNull CoroutineContext context) {
         Intrinsics.checkNotNullParameter(context, "context");
         return CoroutineContext.Element.DefaultImpls.plus($this, context);
      }
   }
}
