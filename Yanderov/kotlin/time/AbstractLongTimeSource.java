package kotlin.time;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b'\u0018\u00002\u00020\u0001:\u0001\u0014B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0006H$¢\u0006\u0004\b\f\u0010\bR\u001a\u0010\u0003\u001a\u00020\u00028\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u0003\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0013\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\b¨\u0006\u0015"},
   d2 = {"Lkotlin/time/AbstractLongTimeSource;", "Lkotlin/time/TimeSource$WithComparableMarks;", "Lkotlin/time/DurationUnit;", "unit", "<init>", "(Lkotlin/time/DurationUnit;)V", "", "adjustedRead", "()J", "Lkotlin/time/ComparableTimeMark;", "markNow", "()Lkotlin/time/ComparableTimeMark;", "read", "Lkotlin/time/DurationUnit;", "getUnit", "()Lkotlin/time/DurationUnit;", "zero$delegate", "Lkotlin/Lazy;", "getZero", "zero", "LongTimeMark", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.9"
)
@WasExperimental(
   markerClass = {ExperimentalTime.class}
)
public abstract class AbstractLongTimeSource implements TimeSource.WithComparableMarks {
   @NotNull
   private final DurationUnit unit;
   @NotNull
   private final Lazy zero$delegate;

   public AbstractLongTimeSource(@NotNull DurationUnit unit) {
      Intrinsics.checkNotNullParameter(unit, "unit");
      super();
      this.unit = unit;
      this.zero$delegate = LazyKt.lazy(new Function0(this) {
         public final Long invoke() {
            return AbstractLongTimeSource.this.read();
         }
      });
   }

   @NotNull
   protected final DurationUnit getUnit() {
      return this.unit;
   }

   protected abstract long read();

   private final long getZero() {
      Lazy var1 = this.zero$delegate;
      return ((Number)var1.getValue()).longValue();
   }

   private final long adjustedRead() {
      return this.read() - this.getZero();
   }

   @NotNull
   public ComparableTimeMark markNow() {
      return new LongTimeMark(this.adjustedRead(), this, Duration.Companion.getZERO-UwyO8pc(), (DefaultConstructorMarker)null);
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\f\u001a\u00020\u0006H\u0016ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0096\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u001b\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0001H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0018\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004ø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0007\u0010\u001fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001fR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010 \u0082\u0002\u0004\n\u0002\b!¨\u0006!"},
      d2 = {"Lkotlin/time/AbstractLongTimeSource$LongTimeMark;", "Lkotlin/time/ComparableTimeMark;", "", "startedAt", "Lkotlin/time/AbstractLongTimeSource;", "timeSource", "Lkotlin/time/Duration;", "offset", "<init>", "(JLkotlin/time/AbstractLongTimeSource;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "elapsedNow-UwyO8pc", "()J", "elapsedNow", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "minus-UwyO8pc", "(Lkotlin/time/ComparableTimeMark;)J", "minus", "duration", "plus-LRDsOJo", "(J)Lkotlin/time/ComparableTimeMark;", "plus", "", "toString", "()Ljava/lang/String;", "J", "Lkotlin/time/AbstractLongTimeSource;", "kotlin-stdlib"}
   )
   @SourceDebugExtension({"SMAP\nTimeSources.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TimeSources.kt\nkotlin/time/AbstractLongTimeSource$LongTimeMark\n+ 2 longSaturatedMath.kt\nkotlin/time/LongSaturatedMathKt\n*L\n1#1,199:1\n80#2:200\n*S KotlinDebug\n*F\n+ 1 TimeSources.kt\nkotlin/time/AbstractLongTimeSource$LongTimeMark\n*L\n67#1:200\n*E\n"})
   private static final class LongTimeMark implements ComparableTimeMark {
      private final long startedAt;
      @NotNull
      private final AbstractLongTimeSource timeSource;
      private final long offset;

      private LongTimeMark(long startedAt, AbstractLongTimeSource timeSource, long offset) {
         Intrinsics.checkNotNullParameter(timeSource, "timeSource");
         super();
         this.startedAt = startedAt;
         this.timeSource = timeSource;
         this.offset = offset;
      }

      public long elapsedNow_UwyO8pc/* $FF was: elapsedNow-UwyO8pc*/() {
         return Duration.minus-LRDsOJo(LongSaturatedMathKt.saturatingOriginsDiff(this.timeSource.adjustedRead(), this.startedAt, this.timeSource.getUnit()), this.offset);
      }

      @NotNull
      public ComparableTimeMark plus_LRDsOJo/* $FF was: plus-LRDsOJo*/(long duration) {
         DurationUnit unit = this.timeSource.getUnit();
         if (Duration.isInfinite-impl(duration)) {
            long newValue = LongSaturatedMathKt.saturatingAdd-NuflL3o(this.startedAt, unit, duration);
            return new LongTimeMark(newValue, this.timeSource, Duration.Companion.getZERO-UwyO8pc(), (DefaultConstructorMarker)null);
         } else {
            long durationInUnit = Duration.truncateTo-UwyO8pc$kotlin_stdlib(duration, unit);
            long rest = Duration.plus-LRDsOJo(Duration.minus-LRDsOJo(duration, durationInUnit), this.offset);
            long sum = LongSaturatedMathKt.saturatingAdd-NuflL3o(this.startedAt, unit, durationInUnit);
            long restInUnit = Duration.truncateTo-UwyO8pc$kotlin_stdlib(rest, unit);
            sum = LongSaturatedMathKt.saturatingAdd-NuflL3o(sum, unit, restInUnit);
            long restUnderUnit = Duration.minus-LRDsOJo(rest, restInUnit);
            long restUnderUnitNs = Duration.getInWholeNanoseconds-impl(restUnderUnit);
            if (sum != 0L && restUnderUnitNs != 0L && (sum ^ restUnderUnitNs) < 0L) {
               long correction = DurationKt.toDuration(MathKt.getSign(restUnderUnitNs), unit);
               sum = LongSaturatedMathKt.saturatingAdd-NuflL3o(sum, unit, correction);
               restUnderUnit = Duration.minus-LRDsOJo(restUnderUnit, correction);
            }

            int $i$f$isSaturated = 0;
            long newOffset = (sum - 1L | 1L) == Long.MAX_VALUE ? Duration.Companion.getZERO-UwyO8pc() : restUnderUnit;
            return new LongTimeMark(sum, this.timeSource, newOffset, (DefaultConstructorMarker)null);
         }
      }

      public long minus_UwyO8pc/* $FF was: minus-UwyO8pc*/(@NotNull ComparableTimeMark other) {
         Intrinsics.checkNotNullParameter(other, "other");
         if (other instanceof LongTimeMark && Intrinsics.areEqual((Object)this.timeSource, (Object)((LongTimeMark)other).timeSource)) {
            long startedAtDiff = LongSaturatedMathKt.saturatingOriginsDiff(this.startedAt, ((LongTimeMark)other).startedAt, this.timeSource.getUnit());
            return Duration.plus-LRDsOJo(startedAtDiff, Duration.minus-LRDsOJo(this.offset, ((LongTimeMark)other).offset));
         } else {
            throw new IllegalArgumentException("Subtracting or comparing time marks from different time sources is not possible: " + this + " and " + other);
         }
      }

      public boolean equals(@Nullable Object other) {
         return other instanceof LongTimeMark && Intrinsics.areEqual((Object)this.timeSource, (Object)((LongTimeMark)other).timeSource) && Duration.equals-impl0(this.minus-UwyO8pc((ComparableTimeMark)other), Duration.Companion.getZERO-UwyO8pc());
      }

      public int hashCode() {
         return Duration.hashCode-impl(this.offset) * 37 + Long.hashCode(this.startedAt);
      }

      @NotNull
      public String toString() {
         return "LongTimeMark(" + this.startedAt + DurationUnitKt.shortName(this.timeSource.getUnit()) + " + " + Duration.toString-impl(this.offset) + ", " + this.timeSource + ')';
      }

      @NotNull
      public ComparableTimeMark minus_LRDsOJo/* $FF was: minus-LRDsOJo*/(long duration) {
         return ComparableTimeMark.DefaultImpls.minus-LRDsOJo(this, duration);
      }

      public int compareTo(@NotNull ComparableTimeMark other) {
         return ComparableTimeMark.DefaultImpls.compareTo(this, other);
      }

      public boolean hasPassedNow() {
         return ComparableTimeMark.DefaultImpls.hasPassedNow(this);
      }

      public boolean hasNotPassedNow() {
         return ComparableTimeMark.DefaultImpls.hasNotPassedNow(this);
      }

      // $FF: synthetic method
      public LongTimeMark(long startedAt, AbstractLongTimeSource timeSource, long offset, DefaultConstructorMarker $constructor_marker) {
         this(startedAt, timeSource, offset);
      }
   }
}
