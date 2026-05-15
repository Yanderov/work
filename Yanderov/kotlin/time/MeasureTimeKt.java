package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a.\u0010\u0004\u001a\u00020\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a:\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\"\u0004\b\u0000\u0010\u00062\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\b\u0010\t\u001a2\u0010\u0004\u001a\u00020\u0003*\u00020\n2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\u0004\u0010\u000b\u001a2\u0010\u0004\u001a\u00020\u0003*\u00020\f2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\u0004\u0010\r\u001a>\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\"\u0004\b\u0000\u0010\u0006*\u00020\n2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\b\u0010\u000e\u001a>\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\"\u0004\b\u0000\u0010\u0006*\u00020\f2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\b\u0010\u000f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0010"},
   d2 = {"Lkotlin/Function0;", "", "block", "Lkotlin/time/Duration;", "measureTime", "(Lkotlin/jvm/functions/Function0;)J", "T", "Lkotlin/time/TimedValue;", "measureTimedValue", "(Lkotlin/jvm/functions/Function0;)Lkotlin/time/TimedValue;", "Lkotlin/time/TimeSource;", "(Lkotlin/time/TimeSource;Lkotlin/jvm/functions/Function0;)J", "Lkotlin/time/TimeSource$Monotonic;", "(Lkotlin/time/TimeSource$Monotonic;Lkotlin/jvm/functions/Function0;)J", "(Lkotlin/time/TimeSource;Lkotlin/jvm/functions/Function0;)Lkotlin/time/TimedValue;", "(Lkotlin/time/TimeSource$Monotonic;Lkotlin/jvm/functions/Function0;)Lkotlin/time/TimedValue;", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nmeasureTime.kt\nKotlin\n*S Kotlin\n*F\n+ 1 measureTime.kt\nkotlin/time/MeasureTimeKt\n*L\n1#1,121:1\n50#1,7:122\n113#1,7:129\n*S KotlinDebug\n*F\n+ 1 measureTime.kt\nkotlin/time/MeasureTimeKt\n*L\n21#1:122,7\n83#1:129,7\n*E\n"})
public final class MeasureTimeKt {
   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalTime.class}
   )
   public static final long measureTime(@NotNull Function0 block) {
      Intrinsics.checkNotNullParameter(block, "block");
      int $i$f$measureTime = 0;
      TimeSource.Monotonic $this$measureTime$iv = TimeSource.Monotonic.INSTANCE;
      int $i$f$measureTime = 0;
      long mark$iv = $this$measureTime$iv.markNow-z9LOYto();
      block.invoke();
      return TimeSource.Monotonic.ValueTimeMark.elapsedNow-UwyO8pc(mark$iv);
   }

   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalTime.class}
   )
   public static final long measureTime(@NotNull TimeSource $this$measureTime, @NotNull Function0 block) {
      Intrinsics.checkNotNullParameter($this$measureTime, "<this>");
      Intrinsics.checkNotNullParameter(block, "block");
      int $i$f$measureTime = 0;
      TimeMark mark = $this$measureTime.markNow();
      block.invoke();
      return mark.elapsedNow-UwyO8pc();
   }

   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalTime.class}
   )
   public static final long measureTime(@NotNull TimeSource.Monotonic $this$measureTime, @NotNull Function0 block) {
      Intrinsics.checkNotNullParameter($this$measureTime, "<this>");
      Intrinsics.checkNotNullParameter(block, "block");
      int $i$f$measureTime = 0;
      long mark = $this$measureTime.markNow-z9LOYto();
      block.invoke();
      return TimeSource.Monotonic.ValueTimeMark.elapsedNow-UwyO8pc(mark);
   }

   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalTime.class}
   )
   @NotNull
   public static final TimedValue measureTimedValue(@NotNull Function0 block) {
      Intrinsics.checkNotNullParameter(block, "block");
      int $i$f$measureTimedValue = 0;
      TimeSource.Monotonic $this$measureTimedValue$iv = TimeSource.Monotonic.INSTANCE;
      int $i$f$measureTimedValue = 0;
      long mark$iv = $this$measureTimedValue$iv.markNow-z9LOYto();
      Object result$iv = block.invoke();
      return new TimedValue(result$iv, TimeSource.Monotonic.ValueTimeMark.elapsedNow-UwyO8pc(mark$iv), (DefaultConstructorMarker)null);
   }

   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalTime.class}
   )
   @NotNull
   public static final TimedValue measureTimedValue(@NotNull TimeSource $this$measureTimedValue, @NotNull Function0 block) {
      Intrinsics.checkNotNullParameter($this$measureTimedValue, "<this>");
      Intrinsics.checkNotNullParameter(block, "block");
      int $i$f$measureTimedValue = 0;
      TimeMark mark = $this$measureTimedValue.markNow();
      Object result = block.invoke();
      return new TimedValue(result, mark.elapsedNow-UwyO8pc(), (DefaultConstructorMarker)null);
   }

   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalTime.class}
   )
   @NotNull
   public static final TimedValue measureTimedValue(@NotNull TimeSource.Monotonic $this$measureTimedValue, @NotNull Function0 block) {
      Intrinsics.checkNotNullParameter($this$measureTimedValue, "<this>");
      Intrinsics.checkNotNullParameter(block, "block");
      int $i$f$measureTimedValue = 0;
      long mark = $this$measureTimedValue.markNow-z9LOYto();
      Object result = block.invoke();
      return new TimedValue(result, TimeSource.Monotonic.ValueTimeMark.elapsedNow-UwyO8pc(mark), (DefaultConstructorMarker)null);
   }
}
