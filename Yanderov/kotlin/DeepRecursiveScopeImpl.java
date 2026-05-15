package kotlin;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00032\b\u0012\u0004\u0012\u00028\u00010\u0004BJ\u00129\u0010\b\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¢\u0006\u0002\b\u0007\u0012\u0006\u0010\t\u001a\u00028\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0018\u0010\f\u001a\u00028\u00012\u0006\u0010\t\u001a\u00028\u0000H\u0096@¢\u0006\u0004\b\f\u0010\rJb\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000429\u0010\u000e\u001a5\b\u0001\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¢\u0006\u0002\b\u00072\u000e\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0015\u001a\u00020\u00142\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010\u0012H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00028\u0001¢\u0006\u0004\b\u0017\u0010\u0018J4\u0010\f\u001a\u00028\u0003\"\u0004\b\u0002\u0010\u0019\"\u0004\b\u0003\u0010\u001a*\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u001b2\u0006\u0010\t\u001a\u00028\u0002H\u0096@¢\u0006\u0004\b\f\u0010\u001cR \u0010\u000f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u001dR\u0014\u0010!\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 RI\u0010\"\u001a5\b\u0001\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¢\u0006\u0002\b\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010#R!\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00128\u0002@\u0002X\u0082\u000eø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0013\u0010$R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010$\u0082\u0002\u0004\n\u0002\b!¨\u0006%"},
   d2 = {"Lkotlin/DeepRecursiveScopeImpl;", "T", "R", "Lkotlin/DeepRecursiveScope;", "Lkotlin/coroutines/Continuation;", "Lkotlin/Function3;", "", "Lkotlin/ExtensionFunctionType;", "block", "value", "<init>", "(Lkotlin/jvm/functions/Function3;Ljava/lang/Object;)V", "callRecursive", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "currentFunction", "cont", "crossFunctionCompletion", "(Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "Lkotlin/Result;", "result", "", "resumeWith", "(Ljava/lang/Object;)V", "runCallLoop", "()Ljava/lang/Object;", "U", "S", "Lkotlin/DeepRecursiveFunction;", "(Lkotlin/DeepRecursiveFunction;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/coroutines/Continuation;", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "context", "function", "Lkotlin/jvm/functions/Function3;", "Ljava/lang/Object;", "kotlin-stdlib"}
)
final class DeepRecursiveScopeImpl extends DeepRecursiveScope implements Continuation {
   @NotNull
   private Function3 function;
   @Nullable
   private Object value;
   @Nullable
   private Continuation cont;
   @NotNull
   private Object result;

   public DeepRecursiveScopeImpl(@NotNull Function3 block, Object value) {
      Intrinsics.checkNotNullParameter(block, "block");
      super((DefaultConstructorMarker)null);
      this.function = block;
      this.value = value;
      Intrinsics.checkNotNull(this, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
      this.cont = this;
      this.result = DeepRecursiveKt.access$getUNDEFINED_RESULT$p();
   }

   @NotNull
   public CoroutineContext getContext() {
      return EmptyCoroutineContext.INSTANCE;
   }

   public void resumeWith(@NotNull Object result) {
      this.cont = null;
      this.result = result;
   }

   @Nullable
   public Object callRecursive(Object value, @NotNull Continuation $completion) {
      int var4 = 0;
      Intrinsics.checkNotNull($completion, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
      this.cont = $completion;
      this.value = value;
      Object var10000 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      if (var10000 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended($completion);
      }

      return var10000;
   }

   @Nullable
   public Object callRecursive(@NotNull DeepRecursiveFunction $this$callRecursive, Object value, @NotNull Continuation $completion) {
      int var5 = 0;
      Function3 var10000 = $this$callRecursive.getBlock$kotlin_stdlib();
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type @[ExtensionFunctionType] kotlin.coroutines.SuspendFunction2<kotlin.DeepRecursiveScope<*, *>, kotlin.Any?, kotlin.Any?>{ kotlin.DeepRecursiveKt.DeepRecursiveFunctionBlock }");
      Function3 function = var10000;
      DeepRecursiveScopeImpl $this$callRecursive_u24lambda_u242_u24lambda_u241 = this;
      int var8 = 0;
      Function3 currentFunction = $this$callRecursive_u24lambda_u242_u24lambda_u241.function;
      if (function != currentFunction) {
         $this$callRecursive_u24lambda_u242_u24lambda_u241.function = function;
         Intrinsics.checkNotNull($completion, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
         $this$callRecursive_u24lambda_u242_u24lambda_u241.cont = $this$callRecursive_u24lambda_u242_u24lambda_u241.crossFunctionCompletion(currentFunction, $completion);
      } else {
         Intrinsics.checkNotNull($completion, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
         $this$callRecursive_u24lambda_u242_u24lambda_u241.cont = $completion;
      }

      $this$callRecursive_u24lambda_u242_u24lambda_u241.value = value;
      Object var10 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      if (var10 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended($completion);
      }

      return var10;
   }

   private final Continuation crossFunctionCompletion(Function3 currentFunction, Continuation cont) {
      CoroutineContext var3 = EmptyCoroutineContext.INSTANCE;
      return new DeepRecursiveScopeImpl$crossFunctionCompletion$$inlined$Continuation$1(var3, this, currentFunction, cont);
   }

   public final Object runCallLoop() {
      while(true) {
         Object result = this.result;
         Continuation var10000 = this.cont;
         if (var10000 == null) {
            ResultKt.throwOnFailure(result);
            return result;
         }

         Continuation cont = var10000;
         if (Result.equals-impl0(DeepRecursiveKt.access$getUNDEFINED_RESULT$p(), result)) {
            Object r;
            try {
               Function3 r = this.function;
               Object var5 = this.value;
               r = !(r instanceof BaseContinuationImpl) ? IntrinsicsKt.wrapWithContinuationImpl(r, this, var5, cont) : ((Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(r, 3)).invoke(this, var5, cont);
            } catch (Throwable e) {
               Result.Companion var10001 = Result.Companion;
               cont.resumeWith(Result.constructor-impl(ResultKt.createFailure(e)));
               continue;
            }

            if (r != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
               Result.Companion var8 = Result.Companion;
               cont.resumeWith(Result.constructor-impl(r));
            }
         } else {
            this.result = DeepRecursiveKt.access$getUNDEFINED_RESULT$p();
            cont.resumeWith(result);
         }
      }
   }

   // $FF: synthetic method
   public static final void access$setFunction$p(DeepRecursiveScopeImpl $this, Function3 <set-?>) {
      $this.function = <set-?>;
   }

   // $FF: synthetic method
   public static final void access$setCont$p(DeepRecursiveScopeImpl $this, Continuation <set-?>) {
      $this.cont = <set-?>;
   }

   // $FF: synthetic method
   public static final void access$setResult$p(DeepRecursiveScopeImpl $this, Object <set-?>) {
      $this.result = <set-?>;
   }
}
