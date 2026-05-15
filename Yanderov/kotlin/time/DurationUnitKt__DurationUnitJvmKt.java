package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000\u001e\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a'\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001a'\u0010\u0005\u001a\u00020\u00072\u0006\u0010\u0001\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\u0005\u0010\b\u001a'\u0010\t\u001a\u00020\u00072\u0006\u0010\u0001\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\t\u0010\b\u001a\u0013\u0010\u000b\u001a\u00020\u0002*\u00020\nH\u0007¢\u0006\u0004\b\u000b\u0010\f\u001a\u0013\u0010\r\u001a\u00020\n*\u00020\u0002H\u0007¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"},
   d2 = {"", "value", "Lkotlin/time/DurationUnit;", "sourceUnit", "targetUnit", "convertDurationUnit", "(DLkotlin/time/DurationUnit;Lkotlin/time/DurationUnit;)D", "", "(JLkotlin/time/DurationUnit;Lkotlin/time/DurationUnit;)J", "convertDurationUnitOverflow", "Ljava/util/concurrent/TimeUnit;", "toDurationUnit", "(Ljava/util/concurrent/TimeUnit;)Lkotlin/time/DurationUnit;", "toTimeUnit", "(Lkotlin/time/DurationUnit;)Ljava/util/concurrent/TimeUnit;", "kotlin-stdlib"},
   xs = "kotlin/time/DurationUnitKt"
)
class DurationUnitKt__DurationUnitJvmKt {
   @SinceKotlin(
      version = "1.8"
   )
   @WasExperimental(
      markerClass = {ExperimentalTime.class}
   )
   @NotNull
   public static final TimeUnit toTimeUnit(@NotNull DurationUnit $this$toTimeUnit) {
      Intrinsics.checkNotNullParameter($this$toTimeUnit, "<this>");
      return $this$toTimeUnit.getTimeUnit$kotlin_stdlib();
   }

   @SinceKotlin(
      version = "1.8"
   )
   @WasExperimental(
      markerClass = {ExperimentalTime.class}
   )
   @NotNull
   public static final DurationUnit toDurationUnit(@NotNull TimeUnit $this$toDurationUnit) {
      Intrinsics.checkNotNullParameter($this$toDurationUnit, "<this>");
      DurationUnit var10000;
      switch (DurationUnitKt__DurationUnitJvmKt.WhenMappings.$EnumSwitchMapping$0[$this$toDurationUnit.ordinal()]) {
         case 1:
            var10000 = DurationUnit.NANOSECONDS;
            break;
         case 2:
            var10000 = DurationUnit.MICROSECONDS;
            break;
         case 3:
            var10000 = DurationUnit.MILLISECONDS;
            break;
         case 4:
            var10000 = DurationUnit.SECONDS;
            break;
         case 5:
            var10000 = DurationUnit.MINUTES;
            break;
         case 6:
            var10000 = DurationUnit.HOURS;
            break;
         case 7:
            var10000 = DurationUnit.DAYS;
            break;
         default:
            throw new NoWhenBranchMatchedException();
      }

      return var10000;
   }

   @SinceKotlin(
      version = "1.3"
   )
   public static final double convertDurationUnit(double value, @NotNull DurationUnit sourceUnit, @NotNull DurationUnit targetUnit) {
      Intrinsics.checkNotNullParameter(sourceUnit, "sourceUnit");
      Intrinsics.checkNotNullParameter(targetUnit, "targetUnit");
      long sourceInTargets = targetUnit.getTimeUnit$kotlin_stdlib().convert(1L, sourceUnit.getTimeUnit$kotlin_stdlib());
      if (sourceInTargets > 0L) {
         return value * (double)sourceInTargets;
      } else {
         long otherInThis = sourceUnit.getTimeUnit$kotlin_stdlib().convert(1L, targetUnit.getTimeUnit$kotlin_stdlib());
         return value / (double)otherInThis;
      }
   }

   @SinceKotlin(
      version = "1.5"
   )
   public static final long convertDurationUnitOverflow(long value, @NotNull DurationUnit sourceUnit, @NotNull DurationUnit targetUnit) {
      Intrinsics.checkNotNullParameter(sourceUnit, "sourceUnit");
      Intrinsics.checkNotNullParameter(targetUnit, "targetUnit");
      return targetUnit.getTimeUnit$kotlin_stdlib().convert(value, sourceUnit.getTimeUnit$kotlin_stdlib());
   }

   @SinceKotlin(
      version = "1.5"
   )
   public static final long convertDurationUnit(long value, @NotNull DurationUnit sourceUnit, @NotNull DurationUnit targetUnit) {
      Intrinsics.checkNotNullParameter(sourceUnit, "sourceUnit");
      Intrinsics.checkNotNullParameter(targetUnit, "targetUnit");
      return targetUnit.getTimeUnit$kotlin_stdlib().convert(value, sourceUnit.getTimeUnit$kotlin_stdlib());
   }

   public DurationUnitKt__DurationUnitJvmKt() {
   }

   // $FF: synthetic class
   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public class WhenMappings {
      // $FF: synthetic field
      public static final int[] $EnumSwitchMapping$0;

      static {
         int[] var0 = new int[TimeUnit.values().length];

         try {
            var0[TimeUnit.NANOSECONDS.ordinal()] = 1;
         } catch (NoSuchFieldError var8) {
         }

         try {
            var0[TimeUnit.MICROSECONDS.ordinal()] = 2;
         } catch (NoSuchFieldError var7) {
         }

         try {
            var0[TimeUnit.MILLISECONDS.ordinal()] = 3;
         } catch (NoSuchFieldError var6) {
         }

         try {
            var0[TimeUnit.SECONDS.ordinal()] = 4;
         } catch (NoSuchFieldError var5) {
         }

         try {
            var0[TimeUnit.MINUTES.ordinal()] = 5;
         } catch (NoSuchFieldError var4) {
         }

         try {
            var0[TimeUnit.HOURS.ordinal()] = 6;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[TimeUnit.DAYS.ordinal()] = 7;
         } catch (NoSuchFieldError var2) {
         }

         $EnumSwitchMapping$0 = var0;
      }
   }
}
