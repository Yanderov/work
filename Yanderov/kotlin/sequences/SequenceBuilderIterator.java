package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u00032\b\u0012\u0004\u0012\u00020\u00050\u0004B\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bH\u0096\u0002¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u0010\u0010\u000fJ\u001d\u0010\u0013\u001a\u00020\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00028\u0000H\u0096@¢\u0006\u0004\b\u0016\u0010\u0017J\u001e\u0010\u0019\u001a\u00020\u00052\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096@¢\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001e\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u001e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 R*\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0018\u0010'\u001a\u0004\u0018\u00018\u00008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010(R\u001a\u0010+\u001a\u00060)j\u0002`*8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010,¨\u0006-"},
   d2 = {"Lkotlin/sequences/SequenceBuilderIterator;", "T", "Lkotlin/sequences/SequenceScope;", "", "Lkotlin/coroutines/Continuation;", "", "<init>", "()V", "", "exceptionalState", "()Ljava/lang/Throwable;", "", "hasNext", "()Z", "next", "()Ljava/lang/Object;", "nextNotReady", "Lkotlin/Result;", "result", "resumeWith", "(Ljava/lang/Object;)V", "value", "yield", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "iterator", "yieldAll", "(Ljava/util/Iterator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "context", "nextIterator", "Ljava/util/Iterator;", "nextStep", "Lkotlin/coroutines/Continuation;", "getNextStep", "()Lkotlin/coroutines/Continuation;", "setNextStep", "(Lkotlin/coroutines/Continuation;)V", "nextValue", "Ljava/lang/Object;", "", "Lkotlin/sequences/State;", "state", "I", "kotlin-stdlib"}
)
final class SequenceBuilderIterator extends SequenceScope implements Iterator, Continuation, KMappedMarker {
   private int state;
   @Nullable
   private Object nextValue;
   @Nullable
   private Iterator nextIterator;
   @Nullable
   private Continuation nextStep;

   public SequenceBuilderIterator() {
   }

   @Nullable
   public final Continuation getNextStep() {
      return this.nextStep;
   }

   public final void setNextStep(@Nullable Continuation <set-?>) {
      this.nextStep = <set-?>;
   }

   public boolean hasNext() {
      while(true) {
         switch (this.state) {
            case 1:
               Iterator var10000 = this.nextIterator;
               Intrinsics.checkNotNull(var10000);
               if (var10000.hasNext()) {
                  this.state = 2;
                  return true;
               }

               this.nextIterator = null;
            case 0:
               this.state = 5;
               Continuation var2 = this.nextStep;
               Intrinsics.checkNotNull(var2);
               Continuation step = var2;
               this.nextStep = null;
               Result.Companion var10001 = Result.Companion;
               step.resumeWith(Result.constructor-impl(Unit.INSTANCE));
               break;
            case 2:
            case 3:
               return true;
            case 4:
               return false;
            default:
               throw this.exceptionalState();
         }
      }
   }

   public Object next() {
      switch (this.state) {
         case 0:
         case 1:
            return this.nextNotReady();
         case 2:
            this.state = 1;
            Iterator var10000 = this.nextIterator;
            Intrinsics.checkNotNull(var10000);
            return var10000.next();
         case 3:
            this.state = 0;
            Object result = this.nextValue;
            this.nextValue = null;
            return result;
         default:
            throw this.exceptionalState();
      }
   }

   private final Object nextNotReady() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         return this.next();
      }
   }

   private final Throwable exceptionalState() {
      Throwable var10000;
      switch (this.state) {
         case 4:
            var10000 = (Throwable)(new NoSuchElementException());
            break;
         case 5:
            var10000 = (Throwable)(new IllegalStateException("Iterator has failed."));
            break;
         default:
            var10000 = (Throwable)(new IllegalStateException("Unexpected state of the iterator: " + this.state));
      }

      return var10000;
   }

   @Nullable
   public Object yield(Object value, @NotNull Continuation $completion) {
      this.nextValue = value;
      this.state = 3;
      int var4 = 0;
      this.nextStep = $completion;
      Object var10000 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      if (var10000 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended($completion);
      }

      return var10000 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var10000 : Unit.INSTANCE;
   }

   @Nullable
   public Object yieldAll(@NotNull Iterator iterator, @NotNull Continuation $completion) {
      if (!iterator.hasNext()) {
         return Unit.INSTANCE;
      } else {
         this.nextIterator = iterator;
         this.state = 2;
         int var4 = 0;
         this.nextStep = $completion;
         Object var10000 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         if (var10000 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended($completion);
         }

         return var10000 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var10000 : Unit.INSTANCE;
      }
   }

   public void resumeWith(@NotNull Object result) {
      ResultKt.throwOnFailure(result);
      this.state = 4;
   }

   @NotNull
   public CoroutineContext getContext() {
      return EmptyCoroutineContext.INSTANCE;
   }

   public void remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }
}
