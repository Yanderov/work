package kotlin.coroutines.intrinsics;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.ResultKt;
import kotlin.SinceKotlin;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.RestrictedContinuationImpl;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aH\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001\"\u0004\b\u0000\u0010\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u00012\u001c\b\u0004\u0010\u0005\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\u0083\b¢\u0006\u0004\b\u0007\u0010\b\u001a)\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0002¢\u0006\u0004\b\n\u0010\u000b\u001aC\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001\"\u0004\b\u0000\u0010\u0000*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0007¢\u0006\u0004\b\r\u0010\u000e\u001a\\\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u0000*#\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0010¢\u0006\u0002\b\u00112\u0006\u0010\u0012\u001a\u00028\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001H\u0007¢\u0006\u0004\b\r\u0010\u0013\u001a%\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0007¢\u0006\u0004\b\u0014\u0010\u000b\u001a@\u0010\u0015\u001a\u0004\u0018\u00010\u0004\"\u0004\b\u0000\u0010\u0000*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0087\b¢\u0006\u0004\b\u0015\u0010\u0016\u001aY\u0010\u0015\u001a\u0004\u0018\u00010\u0004\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u0000*#\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0010¢\u0006\u0002\b\u00112\u0006\u0010\u0012\u001a\u00028\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001H\u0087\b¢\u0006\u0004\b\u0015\u0010\u0017\u001am\u0010\u0015\u001a\u0004\u0018\u00010\u0004\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u0018\"\u0004\b\u0002\u0010\u0000*)\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0019¢\u0006\u0002\b\u00112\u0006\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00028\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00020\u0001H\u0081\b¢\u0006\u0004\b\u0015\u0010\u001b\u001a?\u0010\u001c\u001a\u0004\u0018\u00010\u0004\"\u0004\b\u0000\u0010\u0000*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0001¢\u0006\u0004\b\u001c\u0010\u0016\u001aX\u0010\u001c\u001a\u0004\u0018\u00010\u0004\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u0000*#\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0010¢\u0006\u0002\b\u00112\u0006\u0010\u0012\u001a\u00028\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001H\u0001¢\u0006\u0004\b\u001c\u0010\u0017\u001al\u0010\u001c\u001a\u0004\u0018\u00010\u0004\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u0018\"\u0004\b\u0002\u0010\u0000*)\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0019¢\u0006\u0002\b\u00112\u0006\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00028\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00020\u0001H\u0001¢\u0006\u0004\b\u001c\u0010\u001b¨\u0006\u001d"},
   d2 = {"T", "Lkotlin/coroutines/Continuation;", "completion", "Lkotlin/Function1;", "", "block", "", "createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt", "(Lkotlin/coroutines/Continuation;Lkotlin/jvm/functions/Function1;)Lkotlin/coroutines/Continuation;", "createCoroutineFromSuspendFunction", "createSimpleCoroutineForSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt", "(Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "createSimpleCoroutineForSuspendFunction", "createCoroutineUnintercepted", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "R", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "receiver", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "intercepted", "startCoroutineUninterceptedOrReturn", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "P", "Lkotlin/Function3;", "param", "(Lkotlin/jvm/functions/Function3;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "wrapWithContinuationImpl", "kotlin-stdlib"},
   xs = "kotlin/coroutines/intrinsics/IntrinsicsKt"
)
@SourceDebugExtension({"SMAP\nIntrinsicsJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IntrinsicsJvm.kt\nkotlin/coroutines/intrinsics/IntrinsicsKt__IntrinsicsJvmKt\n*L\n1#1,269:1\n204#1,4:270\n225#1:274\n204#1,4:275\n225#1:279\n*S KotlinDebug\n*F\n+ 1 IntrinsicsJvm.kt\nkotlin/coroutines/intrinsics/IntrinsicsKt__IntrinsicsJvmKt\n*L\n130#1:270,4\n130#1:274\n165#1:275,4\n165#1:279\n*E\n"})
class IntrinsicsKt__IntrinsicsJvmKt {
   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final Object startCoroutineUninterceptedOrReturn(Function1 $this$startCoroutineUninterceptedOrReturn, Continuation completion) {
      Intrinsics.checkNotNullParameter($this$startCoroutineUninterceptedOrReturn, "<this>");
      Intrinsics.checkNotNullParameter(completion, "completion");
      return !($this$startCoroutineUninterceptedOrReturn instanceof BaseContinuationImpl) ? IntrinsicsKt.wrapWithContinuationImpl($this$startCoroutineUninterceptedOrReturn, completion) : ((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity($this$startCoroutineUninterceptedOrReturn, 1)).invoke(completion);
   }

   @PublishedApi
   @Nullable
   public static final Object wrapWithContinuationImpl(@NotNull Function1 $this$wrapWithContinuationImpl, @NotNull Continuation completion) {
      Intrinsics.checkNotNullParameter($this$wrapWithContinuationImpl, "<this>");
      Intrinsics.checkNotNullParameter(completion, "completion");
      Continuation newCompletion = createSimpleCoroutineForSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt(DebugProbesKt.probeCoroutineCreated(completion));
      return ((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity($this$wrapWithContinuationImpl, 1)).invoke(newCompletion);
   }

   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final Object startCoroutineUninterceptedOrReturn(Function2 $this$startCoroutineUninterceptedOrReturn, Object receiver, Continuation completion) {
      Intrinsics.checkNotNullParameter($this$startCoroutineUninterceptedOrReturn, "<this>");
      Intrinsics.checkNotNullParameter(completion, "completion");
      return !($this$startCoroutineUninterceptedOrReturn instanceof BaseContinuationImpl) ? IntrinsicsKt.wrapWithContinuationImpl($this$startCoroutineUninterceptedOrReturn, receiver, completion) : ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity($this$startCoroutineUninterceptedOrReturn, 2)).invoke(receiver, completion);
   }

   @PublishedApi
   @Nullable
   public static final Object wrapWithContinuationImpl(@NotNull Function2 $this$wrapWithContinuationImpl, Object receiver, @NotNull Continuation completion) {
      Intrinsics.checkNotNullParameter($this$wrapWithContinuationImpl, "<this>");
      Intrinsics.checkNotNullParameter(completion, "completion");
      Continuation newCompletion = createSimpleCoroutineForSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt(DebugProbesKt.probeCoroutineCreated(completion));
      return ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity($this$wrapWithContinuationImpl, 2)).invoke(receiver, newCompletion);
   }

   @InlineOnly
   private static final Object startCoroutineUninterceptedOrReturn(Function3 $this$startCoroutineUninterceptedOrReturn, Object receiver, Object param, Continuation completion) {
      Intrinsics.checkNotNullParameter($this$startCoroutineUninterceptedOrReturn, "<this>");
      Intrinsics.checkNotNullParameter(completion, "completion");
      return !($this$startCoroutineUninterceptedOrReturn instanceof BaseContinuationImpl) ? IntrinsicsKt.wrapWithContinuationImpl($this$startCoroutineUninterceptedOrReturn, receiver, param, completion) : ((Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity($this$startCoroutineUninterceptedOrReturn, 3)).invoke(receiver, param, completion);
   }

   @PublishedApi
   @Nullable
   public static final Object wrapWithContinuationImpl(@NotNull Function3 $this$wrapWithContinuationImpl, Object receiver, Object param, @NotNull Continuation completion) {
      Intrinsics.checkNotNullParameter($this$wrapWithContinuationImpl, "<this>");
      Intrinsics.checkNotNullParameter(completion, "completion");
      Continuation newCompletion = createSimpleCoroutineForSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt(DebugProbesKt.probeCoroutineCreated(completion));
      return ((Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity($this$wrapWithContinuationImpl, 3)).invoke(receiver, param, newCompletion);
   }

   @SinceKotlin(
      version = "1.3"
   )
   @NotNull
   public static final Continuation createCoroutineUnintercepted(@NotNull Function1 $this$createCoroutineUnintercepted, @NotNull Continuation completion) {
      Intrinsics.checkNotNullParameter($this$createCoroutineUnintercepted, "<this>");
      Intrinsics.checkNotNullParameter(completion, "completion");
      Continuation probeCompletion = DebugProbesKt.probeCoroutineCreated(completion);
      Continuation var10000;
      if ($this$createCoroutineUnintercepted instanceof BaseContinuationImpl) {
         var10000 = ((BaseContinuationImpl)$this$createCoroutineUnintercepted).create(probeCompletion);
      } else {
         int $i$f$createCoroutineFromSuspendFunction = 0;
         CoroutineContext context$iv = probeCompletion.getContext();
         var10000 = context$iv == EmptyCoroutineContext.INSTANCE ? (Continuation)(new IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$1(probeCompletion, $this$createCoroutineUnintercepted)) : (Continuation)(new IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$2(probeCompletion, context$iv, $this$createCoroutineUnintercepted));
      }

      return var10000;
   }

   @SinceKotlin(
      version = "1.3"
   )
   @NotNull
   public static final Continuation createCoroutineUnintercepted(@NotNull Function2 $this$createCoroutineUnintercepted, Object receiver, @NotNull Continuation completion) {
      Intrinsics.checkNotNullParameter($this$createCoroutineUnintercepted, "<this>");
      Intrinsics.checkNotNullParameter(completion, "completion");
      Continuation probeCompletion = DebugProbesKt.probeCoroutineCreated(completion);
      Continuation var10000;
      if ($this$createCoroutineUnintercepted instanceof BaseContinuationImpl) {
         var10000 = ((BaseContinuationImpl)$this$createCoroutineUnintercepted).create(receiver, probeCompletion);
      } else {
         int $i$f$createCoroutineFromSuspendFunction = 0;
         CoroutineContext context$iv = probeCompletion.getContext();
         var10000 = context$iv == EmptyCoroutineContext.INSTANCE ? (Continuation)(new IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$3(probeCompletion, $this$createCoroutineUnintercepted, receiver)) : (Continuation)(new IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$4(probeCompletion, context$iv, $this$createCoroutineUnintercepted, receiver));
      }

      return var10000;
   }

   @SinceKotlin(
      version = "1.3"
   )
   @NotNull
   public static final Continuation intercepted(@NotNull Continuation $this$intercepted) {
      Intrinsics.checkNotNullParameter($this$intercepted, "<this>");
      ContinuationImpl var10000 = $this$intercepted instanceof ContinuationImpl ? (ContinuationImpl)$this$intercepted : null;
      Continuation var1;
      if (($this$intercepted instanceof ContinuationImpl ? (ContinuationImpl)$this$intercepted : null) != null) {
         var1 = var10000.intercepted();
         if (var1 != null) {
            return var1;
         }
      }

      var1 = $this$intercepted;
      return var1;
   }

   @SinceKotlin(
      version = "1.3"
   )
   private static final Continuation createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt(Continuation completion, final Function1 block) {
      int $i$f$createCoroutineFromSuspendFunction = 0;
      CoroutineContext context = completion.getContext();
      return context == EmptyCoroutineContext.INSTANCE ? (Continuation)(new RestrictedContinuationImpl(completion, block) {
         private int label;

         public {
            Intrinsics.checkNotNull($completion, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
         }

         protected Object invokeSuspend(Object result) {
            Object var10000;
            switch (this.label) {
               case 0:
                  this.label = 1;
                  ResultKt.throwOnFailure(result);
                  var10000 = block.invoke(this);
                  break;
               case 1:
                  this.label = 2;
                  ResultKt.throwOnFailure(result);
                  var10000 = result;
                  break;
               default:
                  throw new IllegalStateException("This coroutine had already completed".toString());
            }

            return var10000;
         }
      }) : (Continuation)(new ContinuationImpl(completion, context, block) {
         private int label;

         public {
            Intrinsics.checkNotNull($completion, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
         }

         protected Object invokeSuspend(Object result) {
            Object var10000;
            switch (this.label) {
               case 0:
                  this.label = 1;
                  ResultKt.throwOnFailure(result);
                  var10000 = block.invoke(this);
                  break;
               case 1:
                  this.label = 2;
                  ResultKt.throwOnFailure(result);
                  var10000 = result;
                  break;
               default:
                  throw new IllegalStateException("This coroutine had already completed".toString());
            }

            return var10000;
         }
      });
   }

   private static final Continuation createSimpleCoroutineForSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt(Continuation completion) {
      CoroutineContext context = completion.getContext();
      return context == EmptyCoroutineContext.INSTANCE ? (Continuation)(new RestrictedContinuationImpl(completion) {
         {
            Intrinsics.checkNotNull($completion, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
         }

         protected Object invokeSuspend(Object result) {
            ResultKt.throwOnFailure(result);
            return result;
         }
      }) : (Continuation)(new ContinuationImpl(completion, context) {
         {
            Intrinsics.checkNotNull($completion, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
         }

         protected Object invokeSuspend(Object result) {
            ResultKt.throwOnFailure(result);
            return result;
         }
      });
   }

   public IntrinsicsKt__IntrinsicsJvmKt() {
   }
}
