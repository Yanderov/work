package kotlin.sequences;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.RestrictsSuspension;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u0002B\t\b\u0000¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00028\u0000H¦@¢\u0006\u0004\b\u0007\u0010\bJ\u001e\u0010\u000b\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0086@¢\u0006\u0004\b\u000b\u0010\fJ\u001e\u0010\u000b\u001a\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\rH¦@¢\u0006\u0004\b\u000b\u0010\u000fJ\u001e\u0010\u000b\u001a\u00020\u00062\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0086@¢\u0006\u0004\b\u000b\u0010\u0012¨\u0006\u0013"},
   d2 = {"Lkotlin/sequences/SequenceScope;", "T", "", "<init>", "()V", "value", "", "yield", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "elements", "yieldAll", "(Ljava/lang/Iterable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "iterator", "(Ljava/util/Iterator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/sequences/Sequence;", "sequence", "(Lkotlin/sequences/Sequence;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlin-stdlib"}
)
@RestrictsSuspension
@SinceKotlin(
   version = "1.3"
)
public abstract class SequenceScope {
   @Nullable
   public abstract Object yield(Object var1, @NotNull Continuation var2);

   @Nullable
   public abstract Object yieldAll(@NotNull Iterator var1, @NotNull Continuation var2);

   @Nullable
   public final Object yieldAll(@NotNull Iterable elements, @NotNull Continuation $completion) {
      if (elements instanceof Collection && ((Collection)elements).isEmpty()) {
         return Unit.INSTANCE;
      } else {
         Object var10000 = this.yieldAll(elements.iterator(), $completion);
         return var10000 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var10000 : Unit.INSTANCE;
      }
   }

   @Nullable
   public final Object yieldAll(@NotNull Sequence sequence, @NotNull Continuation $completion) {
      Object var10000 = this.yieldAll(sequence.iterator(), $completion);
      return var10000 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var10000 : Unit.INSTANCE;
   }
}
