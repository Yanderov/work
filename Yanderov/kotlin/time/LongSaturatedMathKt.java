package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u001e\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a'\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0017\u0010\b\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\b\u0010\t\u001a'\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\f\u0010\r\u001a'\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u000f\u0010\r\u001a'\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a'\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0017\u0010\u0014\u001a'\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0004\b\u001a\u0010\u0014\u001a\u0014\u0010\u001c\u001a\u00020\u001b*\u00020\u0000H\u0080\b¢\u0006\u0004\b\u001c\u0010\u001d¨\u0006\u001e"},
   d2 = {"", "value", "Lkotlin/time/Duration;", "duration", "durationInUnit", "checkInfiniteSumDefined-PjuGub4", "(JJJ)J", "checkInfiniteSumDefined", "infinityOfSign", "(J)J", "Lkotlin/time/DurationUnit;", "unit", "saturatingAdd-NuflL3o", "(JLkotlin/time/DurationUnit;J)J", "saturatingAdd", "saturatingAddInHalves-NuflL3o", "saturatingAddInHalves", "valueNs", "origin", "saturatingDiff", "(JJLkotlin/time/DurationUnit;)J", "value1", "value2", "saturatingFiniteDiff", "origin1", "origin2", "saturatingOriginsDiff", "", "isSaturated", "(J)Z", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nlongSaturatedMath.kt\nKotlin\n*S Kotlin\n*F\n+ 1 longSaturatedMath.kt\nkotlin/time/LongSaturatedMathKt\n*L\n1#1,81:1\n80#1:82\n80#1:83\n80#1:84\n80#1:85\n80#1:86\n80#1:87\n*S KotlinDebug\n*F\n+ 1 longSaturatedMath.kt\nkotlin/time/LongSaturatedMathKt\n*L\n14#1:82\n17#1:83\n36#1:84\n46#1:85\n53#1:86\n57#1:87\n*E\n"})
public final class LongSaturatedMathKt {
   public static final long saturatingAdd_NuflL3o/* $FF was: saturatingAdd-NuflL3o*/(long value, @NotNull DurationUnit unit, long duration) {
      Intrinsics.checkNotNullParameter(unit, "unit");
      long durationInUnit = Duration.toLong-impl(duration, unit);
      int $i$f$isSaturated = 0;
      if ((value - 1L | 1L) == Long.MAX_VALUE) {
         return checkInfiniteSumDefined-PjuGub4(value, duration, durationInUnit);
      } else {
         $i$f$isSaturated = 0;
         if ((durationInUnit - 1L | 1L) == Long.MAX_VALUE) {
            return saturatingAddInHalves-NuflL3o(value, unit, duration);
         } else {
            long result = value + durationInUnit;
            if (((value ^ result) & (durationInUnit ^ result)) < 0L) {
               return value < 0L ? Long.MIN_VALUE : Long.MAX_VALUE;
            } else {
               return result;
            }
         }
      }
   }

   private static final long checkInfiniteSumDefined_PjuGub4/* $FF was: checkInfiniteSumDefined-PjuGub4*/(long value, long duration, long durationInUnit) {
      if (Duration.isInfinite-impl(duration) && (value ^ durationInUnit) < 0L) {
         throw new IllegalArgumentException("Summing infinities of different signs");
      } else {
         return value;
      }
   }

   private static final long saturatingAddInHalves_NuflL3o/* $FF was: saturatingAddInHalves-NuflL3o*/(long value, DurationUnit unit, long duration) {
      long half = Duration.div-UwyO8pc(duration, 2);
      long halfInUnit = Duration.toLong-impl(half, unit);
      int $i$f$isSaturated = 0;
      return (halfInUnit - 1L | 1L) == Long.MAX_VALUE ? halfInUnit : saturatingAdd-NuflL3o(saturatingAdd-NuflL3o(value, unit, half), unit, Duration.minus-LRDsOJo(duration, half));
   }

   private static final long infinityOfSign(long value) {
      return value < 0L ? Duration.Companion.getNEG_INFINITE-UwyO8pc$kotlin_stdlib() : Duration.Companion.getINFINITE-UwyO8pc();
   }

   public static final long saturatingDiff(long valueNs, long origin, @NotNull DurationUnit unit) {
      Intrinsics.checkNotNullParameter(unit, "unit");
      int $i$f$isSaturated = 0;
      return (origin - 1L | 1L) == Long.MAX_VALUE ? Duration.unaryMinus-UwyO8pc(infinityOfSign(origin)) : saturatingFiniteDiff(valueNs, origin, unit);
   }

   public static final long saturatingOriginsDiff(long origin1, long origin2, @NotNull DurationUnit unit) {
      Intrinsics.checkNotNullParameter(unit, "unit");
      int $i$f$isSaturated = 0;
      if ((origin2 - 1L | 1L) == Long.MAX_VALUE) {
         return origin1 == origin2 ? Duration.Companion.getZERO-UwyO8pc() : Duration.unaryMinus-UwyO8pc(infinityOfSign(origin2));
      } else {
         $i$f$isSaturated = 0;
         return (origin1 - 1L | 1L) == Long.MAX_VALUE ? infinityOfSign(origin1) : saturatingFiniteDiff(origin1, origin2, unit);
      }
   }

   private static final long saturatingFiniteDiff(long value1, long value2, DurationUnit unit) {
      long result = value1 - value2;
      if (((result ^ value1) & ~(result ^ value2)) < 0L) {
         if (unit.compareTo(DurationUnit.MILLISECONDS) < 0) {
            long unitsInMilli = DurationUnitKt.convertDurationUnit(1L, DurationUnit.MILLISECONDS, unit);
            long resultMs = value1 / unitsInMilli - value2 / unitsInMilli;
            long resultUnit = value1 % unitsInMilli - value2 % unitsInMilli;
            Duration.Companion var10000 = Duration.Companion;
            return Duration.plus-LRDsOJo(DurationKt.toDuration(resultMs, DurationUnit.MILLISECONDS), DurationKt.toDuration(resultUnit, unit));
         } else {
            return Duration.unaryMinus-UwyO8pc(infinityOfSign(result));
         }
      } else {
         return DurationKt.toDuration(result, unit);
      }
   }

   public static final boolean isSaturated(long $this$isSaturated) {
      int $i$f$isSaturated = 0;
      return ($this$isSaturated - 1L | 1L) == Long.MAX_VALUE;
   }
}
