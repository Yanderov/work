package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0005\u001a\u00020\u0002H&ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\bJ\u0018\u0010\r\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0002H\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\u000f\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0002H\u0096\u0002¢\u0006\u0004\b\u000e\u0010\f\u0082\u0002\u0004\n\u0002\b!¨\u0006\u0010"},
   d2 = {"Lkotlin/time/TimeMark;", "", "Lkotlin/time/Duration;", "elapsedNow-UwyO8pc", "()J", "elapsedNow", "", "hasNotPassedNow", "()Z", "hasPassedNow", "duration", "minus-LRDsOJo", "(J)Lkotlin/time/TimeMark;", "minus", "plus-LRDsOJo", "plus", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.9"
)
@WasExperimental(
   markerClass = {ExperimentalTime.class}
)
public interface TimeMark {
   long elapsedNow_UwyO8pc/* $FF was: elapsedNow-UwyO8pc*/();

   @NotNull
   TimeMark plus_LRDsOJo/* $FF was: plus-LRDsOJo*/(long var1);

   @NotNull
   TimeMark minus_LRDsOJo/* $FF was: minus-LRDsOJo*/(long var1);

   boolean hasPassedNow();

   boolean hasNotPassedNow();

   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      @NotNull
      public static TimeMark plus_LRDsOJo/* $FF was: plus-LRDsOJo*/(@NotNull TimeMark $this, long duration) {
         return new AdjustedTimeMark($this, duration, (DefaultConstructorMarker)null);
      }

      @NotNull
      public static TimeMark minus_LRDsOJo/* $FF was: minus-LRDsOJo*/(@NotNull TimeMark $this, long duration) {
         return $this.plus-LRDsOJo(Duration.unaryMinus-UwyO8pc(duration));
      }

      public static boolean hasPassedNow(@NotNull TimeMark $this) {
         return !Duration.isNegative-impl($this.elapsedNow-UwyO8pc());
      }

      public static boolean hasNotPassedNow(@NotNull TimeMark $this) {
         return Duration.isNegative-impl($this.elapsedNow-UwyO8pc());
      }
   }
}
