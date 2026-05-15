package kotlin.coroutines.jvm.internal;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.SinceKotlin;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\t\b!\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\u00020\u00032\u00020\u0004B\u0019\u0012\u0010\u0010\u0005\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001¢\u0006\u0004\b\u0006\u0010\u0007J+\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u00012\b\u0010\b\u001a\u0004\u0018\u00010\u00022\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0016¢\u0006\u0004\b\n\u0010\u000bJ!\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u00012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0016¢\u0006\u0004\b\n\u0010\fJ\u0011\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ!\u0010\u0012\u001a\u0004\u0018\u00010\u00022\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0010H$¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\tH\u0014¢\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0016\u001a\u00020\t2\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0010¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001d\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR!\u0010\u0005\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u00018\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006!"},
   d2 = {"Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Ljava/io/Serializable;", "completion", "<init>", "(Lkotlin/coroutines/Continuation;)V", "value", "", "create", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "(Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "Ljava/lang/StackTraceElement;", "getStackTraceElement", "()Ljava/lang/StackTraceElement;", "Lkotlin/Result;", "result", "invokeSuspend", "(Ljava/lang/Object;)Ljava/lang/Object;", "releaseIntercepted", "()V", "resumeWith", "(Ljava/lang/Object;)V", "", "toString", "()Ljava/lang/String;", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "callerFrame", "Lkotlin/coroutines/Continuation;", "getCompletion", "()Lkotlin/coroutines/Continuation;", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.3"
)
public abstract class BaseContinuationImpl implements Continuation, CoroutineStackFrame, Serializable {
   @Nullable
   private final Continuation completion;

   public BaseContinuationImpl(@Nullable Continuation completion) {
      this.completion = completion;
   }

   @Nullable
   public final Continuation getCompletion() {
      return this.completion;
   }

   public final void resumeWith(@NotNull Object result) {
      Object current = null;
      current = this;
      Object param = null;
      param = result;

      while(true) {
         DebugProbesKt.probeCoroutineResumed((Continuation)current);
         BaseContinuationImpl $this$resumeWith_u24lambda_u240 = (BaseContinuationImpl)current;
         int var6 = 0;
         Continuation var10000 = $this$resumeWith_u24lambda_u240.completion;
         Intrinsics.checkNotNull(var10000);
         Continuation completion = var10000;

         Object outcome;
         try {
            outcome = $this$resumeWith_u24lambda_u240.invokeSuspend(param);
            if (outcome == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
               return;
            }

            Result.Companion var16 = Result.Companion;
            outcome = Result.constructor-impl(outcome);
         } catch (Throwable exception) {
            Result.Companion var15 = Result.Companion;
            outcome = Result.constructor-impl(ResultKt.createFailure(exception));
         }

         $this$resumeWith_u24lambda_u240.releaseIntercepted();
         if (!(completion instanceof BaseContinuationImpl)) {
            completion.resumeWith(outcome);
            return;
         }

         current = completion;
         param = outcome;
      }
   }

   @Nullable
   protected abstract Object invokeSuspend(@NotNull Object var1);

   protected void releaseIntercepted() {
   }

   @NotNull
   public Continuation create(@NotNull Continuation completion) {
      Intrinsics.checkNotNullParameter(completion, "completion");
      throw new UnsupportedOperationException("create(Continuation) has not been overridden");
   }

   @NotNull
   public Continuation create(@Nullable Object value, @NotNull Continuation completion) {
      Intrinsics.checkNotNullParameter(completion, "completion");
      throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
   }

   @NotNull
   public String toString() {
      StringBuilder var10000 = (new StringBuilder()).append("Continuation at ");
      StackTraceElement var10001 = this.getStackTraceElement();
      return var10000.append(var10001 != null ? (Serializable)var10001 : (Serializable)this.getClass().getName()).toString();
   }

   @Nullable
   public CoroutineStackFrame getCallerFrame() {
      Continuation var1 = this.completion;
      return var1 instanceof CoroutineStackFrame ? (CoroutineStackFrame)var1 : null;
   }

   @Nullable
   public StackTraceElement getStackTraceElement() {
      return DebugMetadataKt.getStackTraceElement(this);
   }
}
