package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\bg\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002J\u0018\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0000H\u0096\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\t\u001a\u00020\b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0007H¦\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0004H&¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0000H¦\u0002ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u0018\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\rH\u0096\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0018\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\rH¦\u0002¢\u0006\u0004\b\u0014\u0010\u0013\u0082\u0002\u0004\n\u0002\b!¨\u0006\u0016"},
   d2 = {"Lkotlin/time/ComparableTimeMark;", "Lkotlin/time/TimeMark;", "", "other", "", "compareTo", "(Lkotlin/time/ComparableTimeMark;)I", "", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "Lkotlin/time/Duration;", "minus-UwyO8pc", "(Lkotlin/time/ComparableTimeMark;)J", "minus", "duration", "minus-LRDsOJo", "(J)Lkotlin/time/ComparableTimeMark;", "plus-LRDsOJo", "plus", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.9"
)
@WasExperimental(
   markerClass = {ExperimentalTime.class}
)
public interface ComparableTimeMark extends TimeMark, Comparable {
   @NotNull
   ComparableTimeMark plus_LRDsOJo/* $FF was: plus-LRDsOJo*/(long var1);

   @NotNull
   ComparableTimeMark minus_LRDsOJo/* $FF was: minus-LRDsOJo*/(long var1);

   long minus_UwyO8pc/* $FF was: minus-UwyO8pc*/(@NotNull ComparableTimeMark var1);

   int compareTo(@NotNull ComparableTimeMark var1);

   boolean equals(@Nullable Object var1);

   int hashCode();

   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      @NotNull
      public static ComparableTimeMark minus_LRDsOJo/* $FF was: minus-LRDsOJo*/(@NotNull ComparableTimeMark $this, long duration) {
         return $this.plus-LRDsOJo(Duration.unaryMinus-UwyO8pc(duration));
      }

      public static int compareTo(@NotNull ComparableTimeMark $this, @NotNull ComparableTimeMark other) {
         Intrinsics.checkNotNullParameter(other, "other");
         return Duration.compareTo-LRDsOJo($this.minus-UwyO8pc(other), Duration.Companion.getZERO-UwyO8pc());
      }

      public static boolean hasPassedNow(@NotNull ComparableTimeMark $this) {
         return TimeMark.DefaultImpls.hasPassedNow($this);
      }

      public static boolean hasNotPassedNow(@NotNull ComparableTimeMark $this) {
         return TimeMark.DefaultImpls.hasNotPassedNow($this);
      }
   }
}
