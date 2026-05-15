package kotlin.coroutines.jvm.internal;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\u001a)\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0001¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001b\u0010\u0007\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0001¢\u0006\u0004\b\u0007\u0010\b\u001a\u001b\u0010\t\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0001¢\u0006\u0004\b\t\u0010\b¨\u0006\n"},
   d2 = {"T", "Lkotlin/coroutines/Continuation;", "completion", "probeCoroutineCreated", "(Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "frame", "", "probeCoroutineResumed", "(Lkotlin/coroutines/Continuation;)V", "probeCoroutineSuspended", "kotlin-stdlib"}
)
public final class DebugProbesKt {
   @SinceKotlin(
      version = "1.3"
   )
   @NotNull
   public static final Continuation probeCoroutineCreated(@NotNull Continuation completion) {
      Intrinsics.checkNotNullParameter(completion, "completion");
      return completion;
   }

   @SinceKotlin(
      version = "1.3"
   )
   public static final void probeCoroutineResumed(@NotNull Continuation frame) {
      Intrinsics.checkNotNullParameter(frame, "frame");
   }

   @SinceKotlin(
      version = "1.3"
   )
   public static final void probeCoroutineSuspended(@NotNull Continuation frame) {
      Intrinsics.checkNotNullParameter(frame, "frame");
   }
}
