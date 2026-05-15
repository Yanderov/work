package kotlin;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001d\u0010\u0005\u001a\u00020\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\f¸\u0006\u000b"},
   d2 = {"kotlin/coroutines/ContinuationKt.Continuation.1", "Lkotlin/coroutines/Continuation;", "Lkotlin/Result;", "result", "", "resumeWith", "(Ljava/lang/Object;)V", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "context", "kotlin/coroutines/ContinuationKt$Continuation$1", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nContinuation.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Continuation.kt\nkotlin/coroutines/ContinuationKt$Continuation$1\n+ 2 DeepRecursive.kt\nkotlin/DeepRecursiveScopeImpl\n*L\n1#1,71:1\n182#2,6:72\n*E\n"})
public final class DeepRecursiveScopeImpl$crossFunctionCompletion$$inlined$Continuation$1 implements Continuation {
   // $FF: synthetic field
   final CoroutineContext $context;
   // $FF: synthetic field
   final DeepRecursiveScopeImpl this$0;
   // $FF: synthetic field
   final Function3 $currentFunction$inlined;
   // $FF: synthetic field
   final Continuation $cont$inlined;

   public DeepRecursiveScopeImpl$crossFunctionCompletion$$inlined$Continuation$1(CoroutineContext $context, DeepRecursiveScopeImpl var2, Function3 var3, Continuation var4) {
      this.$context = $context;
      this.this$0 = var2;
      this.$currentFunction$inlined = var3;
      this.$cont$inlined = var4;
   }

   public CoroutineContext getContext() {
      return this.$context;
   }

   public void resumeWith(Object result) {
      int var3 = 0;
      DeepRecursiveScopeImpl.access$setFunction$p(this.this$0, this.$currentFunction$inlined);
      DeepRecursiveScopeImpl.access$setCont$p(this.this$0, this.$cont$inlined);
      DeepRecursiveScopeImpl.access$setResult$p(this.this$0, result);
   }
}
