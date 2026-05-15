package kotlin.coroutines.jvm.internal;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\b!\u0018\u00002\u00020\u0001B\u001b\b\u0016\u0012\u0010\u0010\u0004\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0002¢\u0006\u0004\b\u0005\u0010\u0006B#\u0012\u0010\u0010\u0004\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0005\u0010\tJ\u0015\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0014¢\u0006\u0004\b\r\u0010\u000eR\u0016\u0010\b\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u000fR\u0014\u0010\u0012\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R \u0010\n\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u0010\u0013¨\u0006\u0014"},
   d2 = {"Lkotlin/coroutines/jvm/internal/ContinuationImpl;", "Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "Lkotlin/coroutines/Continuation;", "", "completion", "<init>", "(Lkotlin/coroutines/Continuation;)V", "Lkotlin/coroutines/CoroutineContext;", "_context", "(Lkotlin/coroutines/Continuation;Lkotlin/coroutines/CoroutineContext;)V", "intercepted", "()Lkotlin/coroutines/Continuation;", "", "releaseIntercepted", "()V", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "context", "Lkotlin/coroutines/Continuation;", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.3"
)
@SourceDebugExtension({"SMAP\nContinuationImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ContinuationImpl.kt\nkotlin/coroutines/jvm/internal/ContinuationImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,168:1\n1#2:169\n*E\n"})
public abstract class ContinuationImpl extends BaseContinuationImpl {
   @Nullable
   private final CoroutineContext _context;
   @Nullable
   private transient Continuation intercepted;

   public ContinuationImpl(@Nullable Continuation completion, @Nullable CoroutineContext _context) {
      super(completion);
      this._context = _context;
   }

   public ContinuationImpl(@Nullable Continuation completion) {
      this(completion, completion != null ? completion.getContext() : null);
   }

   @NotNull
   public CoroutineContext getContext() {
      CoroutineContext var10000 = this._context;
      Intrinsics.checkNotNull(var10000);
      return var10000;
   }

   @NotNull
   public final Continuation intercepted() {
      Continuation var10000 = this.intercepted;
      if (var10000 == null) {
         label13: {
            ContinuationInterceptor var4 = (ContinuationInterceptor)this.getContext().get(ContinuationInterceptor.Key);
            if (var4 != null) {
               var10000 = var4.interceptContinuation(this);
               if (var10000 != null) {
                  break label13;
               }
            }

            var10000 = this;
         }

         Continuation it = var10000;
         int var3 = 0;
         this.intercepted = it;
         var10000 = it;
      }

      return var10000;
   }

   protected void releaseIntercepted() {
      Continuation intercepted = this.intercepted;
      if (intercepted != null && intercepted != this) {
         CoroutineContext.Element var10000 = this.getContext().get(ContinuationInterceptor.Key);
         Intrinsics.checkNotNull(var10000);
         ((ContinuationInterceptor)var10000).releaseInterceptedContinuation(intercepted);
      }

      this.intercepted = CompletedContinuation.INSTANCE;
   }
}
