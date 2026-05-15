package kotlin.system;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u001a.\u0010\u0004\u001a\u00020\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a.\u0010\u0006\u001a\u00020\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\u0006\u0010\u0005\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0007"},
   d2 = {"Lkotlin/Function0;", "", "block", "", "measureNanoTime", "(Lkotlin/jvm/functions/Function0;)J", "measureTimeMillis", "kotlin-stdlib"}
)
@JvmName(
   name = "TimingKt"
)
public final class TimingKt {
   public static final long measureTimeMillis(@NotNull Function0 block) {
      Intrinsics.checkNotNullParameter(block, "block");
      int $i$f$measureTimeMillis = 0;
      long start = System.currentTimeMillis();
      block.invoke();
      return System.currentTimeMillis() - start;
   }

   public static final long measureNanoTime(@NotNull Function0 block) {
      Intrinsics.checkNotNullParameter(block, "block");
      int $i$f$measureNanoTime = 0;
      long start = System.nanoTime();
      block.invoke();
      return System.nanoTime() - start;
   }
}
