package kotlin.time;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.collections.IntIterator;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000>\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b&\u001a\u001f\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0017\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\b\u0010\t\u001a\u0017\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u000b\u0010\t\u001a\u0017\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\r\u0010\t\u001a\u0017\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u000f\u0010\t\u001a\u0017\u0010\u0010\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0010\u0010\t\u001a\u0017\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0011\u0010\t\u001a\u001f\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014H\u0002¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0017\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0018\u0010\u0019\u001a0\u0010\u001e\u001a\u00020\u0002*\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u00022\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00140\u001bH\u0082\b¢\u0006\u0004\b\u001e\u0010\u001f\u001a0\u0010 \u001a\u00020\u0012*\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u00022\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00140\u001bH\u0082\b¢\u0006\u0004\b \u0010!\u001a\u001c\u0010&\u001a\u00020\u0004*\u00020\"2\u0006\u0010#\u001a\u00020\u0004H\u0087\n¢\u0006\u0004\b$\u0010%\u001a\u001c\u0010&\u001a\u00020\u0004*\u00020\u00022\u0006\u0010#\u001a\u00020\u0004H\u0087\n¢\u0006\u0004\b'\u0010(\u001a\u001b\u0010+\u001a\u00020\u0004*\u00020\"2\u0006\u0010*\u001a\u00020)H\u0007¢\u0006\u0004\b+\u0010,\u001a\u001b\u0010+\u001a\u00020\u0004*\u00020\u00022\u0006\u0010*\u001a\u00020)H\u0007¢\u0006\u0004\b+\u0010-\u001a\u001b\u0010+\u001a\u00020\u0004*\u00020\u00002\u0006\u0010*\u001a\u00020)H\u0007¢\u0006\u0004\b+\u0010.\"\u0014\u0010/\u001a\u00020\u00008\u0000X\u0080T¢\u0006\u0006\n\u0004\b/\u00100\"\u0014\u00101\u001a\u00020\u00008\u0000X\u0080T¢\u0006\u0006\n\u0004\b1\u00100\"\u0014\u00102\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b2\u00100\"\u0014\u00103\u001a\u00020\u00028\u0000X\u0080T¢\u0006\u0006\n\u0004\b3\u00104\"\u001e\u00109\u001a\u00020\u0004*\u00020\"8FX\u0087\u0004¢\u0006\f\u0012\u0004\b7\u00108\u001a\u0004\b5\u00106\"\u001e\u00109\u001a\u00020\u0004*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b7\u0010;\u001a\u0004\b5\u0010:\"\u001e\u00109\u001a\u00020\u0004*\u00020\u00008FX\u0087\u0004¢\u0006\f\u0012\u0004\b7\u0010<\u001a\u0004\b5\u0010\t\"\u001e\u0010?\u001a\u00020\u0004*\u00020\"8FX\u0087\u0004¢\u0006\f\u0012\u0004\b>\u00108\u001a\u0004\b=\u00106\"\u001e\u0010?\u001a\u00020\u0004*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b>\u0010;\u001a\u0004\b=\u0010:\"\u001e\u0010?\u001a\u00020\u0004*\u00020\u00008FX\u0087\u0004¢\u0006\f\u0012\u0004\b>\u0010<\u001a\u0004\b=\u0010\t\"\u001e\u0010B\u001a\u00020\u0004*\u00020\"8FX\u0087\u0004¢\u0006\f\u0012\u0004\bA\u00108\u001a\u0004\b@\u00106\"\u001e\u0010B\u001a\u00020\u0004*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\bA\u0010;\u001a\u0004\b@\u0010:\"\u001e\u0010B\u001a\u00020\u0004*\u00020\u00008FX\u0087\u0004¢\u0006\f\u0012\u0004\bA\u0010<\u001a\u0004\b@\u0010\t\"\u001e\u0010E\u001a\u00020\u0004*\u00020\"8FX\u0087\u0004¢\u0006\f\u0012\u0004\bD\u00108\u001a\u0004\bC\u00106\"\u001e\u0010E\u001a\u00020\u0004*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\bD\u0010;\u001a\u0004\bC\u0010:\"\u001e\u0010E\u001a\u00020\u0004*\u00020\u00008FX\u0087\u0004¢\u0006\f\u0012\u0004\bD\u0010<\u001a\u0004\bC\u0010\t\"\u001e\u0010H\u001a\u00020\u0004*\u00020\"8FX\u0087\u0004¢\u0006\f\u0012\u0004\bG\u00108\u001a\u0004\bF\u00106\"\u001e\u0010H\u001a\u00020\u0004*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\bG\u0010;\u001a\u0004\bF\u0010:\"\u001e\u0010H\u001a\u00020\u0004*\u00020\u00008FX\u0087\u0004¢\u0006\f\u0012\u0004\bG\u0010<\u001a\u0004\bF\u0010\t\"\u001e\u0010K\u001a\u00020\u0004*\u00020\"8FX\u0087\u0004¢\u0006\f\u0012\u0004\bJ\u00108\u001a\u0004\bI\u00106\"\u001e\u0010K\u001a\u00020\u0004*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\bJ\u0010;\u001a\u0004\bI\u0010:\"\u001e\u0010K\u001a\u00020\u0004*\u00020\u00008FX\u0087\u0004¢\u0006\f\u0012\u0004\bJ\u0010<\u001a\u0004\bI\u0010\t\"\u001e\u0010N\u001a\u00020\u0004*\u00020\"8FX\u0087\u0004¢\u0006\f\u0012\u0004\bM\u00108\u001a\u0004\bL\u00106\"\u001e\u0010N\u001a\u00020\u0004*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\bM\u0010;\u001a\u0004\bL\u0010:\"\u001e\u0010N\u001a\u00020\u0004*\u00020\u00008FX\u0087\u0004¢\u0006\f\u0012\u0004\bM\u0010<\u001a\u0004\bL\u0010\t¨\u0006O"},
   d2 = {"", "normalValue", "", "unitDiscriminator", "Lkotlin/time/Duration;", "durationOf", "(JI)J", "normalMillis", "durationOfMillis", "(J)J", "millis", "durationOfMillisNormalized", "normalNanos", "durationOfNanos", "nanos", "durationOfNanosNormalized", "millisToNanos", "nanosToMillis", "", "value", "", "strictIso", "parseDuration", "(Ljava/lang/String;Z)J", "parseOverLongIsoComponent", "(Ljava/lang/String;)J", "startIndex", "Lkotlin/Function1;", "", "predicate", "skipWhile", "(Ljava/lang/String;ILkotlin/jvm/functions/Function1;)I", "substringWhile", "(Ljava/lang/String;ILkotlin/jvm/functions/Function1;)Ljava/lang/String;", "", "duration", "times-kIfJnKk", "(DJ)J", "times", "times-mvk6XK0", "(IJ)J", "Lkotlin/time/DurationUnit;", "unit", "toDuration", "(DLkotlin/time/DurationUnit;)J", "(ILkotlin/time/DurationUnit;)J", "(JLkotlin/time/DurationUnit;)J", "MAX_MILLIS", "J", "MAX_NANOS", "MAX_NANOS_IN_MILLIS", "NANOS_IN_MILLIS", "I", "getDays", "(D)J", "getDays$annotations", "(D)V", "days", "(I)J", "(I)V", "(J)V", "getHours", "getHours$annotations", "hours", "getMicroseconds", "getMicroseconds$annotations", "microseconds", "getMilliseconds", "getMilliseconds$annotations", "milliseconds", "getMinutes", "getMinutes$annotations", "minutes", "getNanoseconds", "getNanoseconds$annotations", "nanoseconds", "getSeconds", "getSeconds$annotations", "seconds", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nDuration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Duration.kt\nkotlin/time/DurationKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1494:1\n1447#1,6:1496\n1450#1,3:1502\n1447#1,6:1505\n1447#1,6:1511\n1450#1,3:1520\n1#2:1495\n1734#3,3:1517\n*S KotlinDebug\n*F\n+ 1 Duration.kt\nkotlin/time/DurationKt\n*L\n1371#1:1496,6\n1405#1:1502,3\n1408#1:1505,6\n1411#1:1511,6\n1447#1:1520,3\n1436#1:1517,3\n*E\n"})
public final class DurationKt {
   public static final int NANOS_IN_MILLIS = 1000000;
   public static final long MAX_NANOS = 4611686018426999999L;
   public static final long MAX_MILLIS = 4611686018427387903L;
   private static final long MAX_NANOS_IN_MILLIS = 4611686018426L;

   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalTime.class}
   )
   public static final long toDuration(int $this$toDuration, @NotNull DurationUnit unit) {
      Intrinsics.checkNotNullParameter(unit, "unit");
      return unit.compareTo(DurationUnit.SECONDS) <= 0 ? durationOfNanos(DurationUnitKt.convertDurationUnitOverflow((long)$this$toDuration, unit, DurationUnit.NANOSECONDS)) : toDuration((long)$this$toDuration, unit);
   }

   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalTime.class}
   )
   public static final long toDuration(long $this$toDuration, @NotNull DurationUnit unit) {
      Intrinsics.checkNotNullParameter(unit, "unit");
      long maxNsInUnit = DurationUnitKt.convertDurationUnitOverflow(4611686018426999999L, DurationUnit.NANOSECONDS, unit);
      if (-maxNsInUnit <= $this$toDuration ? $this$toDuration <= maxNsInUnit : false) {
         return durationOfNanos(DurationUnitKt.convertDurationUnitOverflow($this$toDuration, unit, DurationUnit.NANOSECONDS));
      } else {
         long millis = DurationUnitKt.convertDurationUnit($this$toDuration, unit, DurationUnit.MILLISECONDS);
         return durationOfMillis(RangesKt.coerceIn(millis, -4611686018427387903L, 4611686018427387903L));
      }
   }

   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalTime.class}
   )
   public static final long toDuration(double $this$toDuration, @NotNull DurationUnit unit) {
      Intrinsics.checkNotNullParameter(unit, "unit");
      double valueInNs = DurationUnitKt.convertDurationUnit($this$toDuration, unit, DurationUnit.NANOSECONDS);
      boolean var5 = !Double.isNaN(valueInNs);
      if (!var5) {
         int var6 = 0;
         String var10 = "Duration value cannot be NaN.";
         throw new IllegalArgumentException(var10.toString());
      } else {
         long nanos = MathKt.roundToLong(valueInNs);
         long var10000;
         if (-4611686018426999999L <= nanos ? nanos < 4611686018427000000L : false) {
            var10000 = durationOfNanos(nanos);
         } else {
            long millis = MathKt.roundToLong(DurationUnitKt.convertDurationUnit($this$toDuration, unit, DurationUnit.MILLISECONDS));
            var10000 = durationOfMillisNormalized(millis);
         }

         return var10000;
      }
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final long getNanoseconds(int $this$nanoseconds) {
      return toDuration($this$nanoseconds, DurationUnit.NANOSECONDS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Int.nanoseconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.nanoseconds",
   imports = {"kotlin.time.Duration.Companion.nanoseconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalTime
   public static void getNanoseconds$annotations(int <this>) {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final long getNanoseconds(long $this$nanoseconds) {
      return toDuration($this$nanoseconds, DurationUnit.NANOSECONDS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Long.nanoseconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.nanoseconds",
   imports = {"kotlin.time.Duration.Companion.nanoseconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalTime
   public static void getNanoseconds$annotations(long <this>) {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final long getNanoseconds(double $this$nanoseconds) {
      return toDuration($this$nanoseconds, DurationUnit.NANOSECONDS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Double.nanoseconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.nanoseconds",
   imports = {"kotlin.time.Duration.Companion.nanoseconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalTime
   public static void getNanoseconds$annotations(double <this>) {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final long getMicroseconds(int $this$microseconds) {
      return toDuration($this$microseconds, DurationUnit.MICROSECONDS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Int.microseconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.microseconds",
   imports = {"kotlin.time.Duration.Companion.microseconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalTime
   public static void getMicroseconds$annotations(int <this>) {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final long getMicroseconds(long $this$microseconds) {
      return toDuration($this$microseconds, DurationUnit.MICROSECONDS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Long.microseconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.microseconds",
   imports = {"kotlin.time.Duration.Companion.microseconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalTime
   public static void getMicroseconds$annotations(long <this>) {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final long getMicroseconds(double $this$microseconds) {
      return toDuration($this$microseconds, DurationUnit.MICROSECONDS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Double.microseconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.microseconds",
   imports = {"kotlin.time.Duration.Companion.microseconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalTime
   public static void getMicroseconds$annotations(double <this>) {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final long getMilliseconds(int $this$milliseconds) {
      return toDuration($this$milliseconds, DurationUnit.MILLISECONDS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Int.milliseconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.milliseconds",
   imports = {"kotlin.time.Duration.Companion.milliseconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalTime
   public static void getMilliseconds$annotations(int <this>) {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final long getMilliseconds(long $this$milliseconds) {
      return toDuration($this$milliseconds, DurationUnit.MILLISECONDS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Long.milliseconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.milliseconds",
   imports = {"kotlin.time.Duration.Companion.milliseconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalTime
   public static void getMilliseconds$annotations(long <this>) {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final long getMilliseconds(double $this$milliseconds) {
      return toDuration($this$milliseconds, DurationUnit.MILLISECONDS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Double.milliseconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.milliseconds",
   imports = {"kotlin.time.Duration.Companion.milliseconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalTime
   public static void getMilliseconds$annotations(double <this>) {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final long getSeconds(int $this$seconds) {
      return toDuration($this$seconds, DurationUnit.SECONDS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Int.seconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.seconds",
   imports = {"kotlin.time.Duration.Companion.seconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalTime
   public static void getSeconds$annotations(int <this>) {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final long getSeconds(long $this$seconds) {
      return toDuration($this$seconds, DurationUnit.SECONDS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Long.seconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.seconds",
   imports = {"kotlin.time.Duration.Companion.seconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalTime
   public static void getSeconds$annotations(long <this>) {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final long getSeconds(double $this$seconds) {
      return toDuration($this$seconds, DurationUnit.SECONDS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Double.seconds' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.seconds",
   imports = {"kotlin.time.Duration.Companion.seconds"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalTime
   public static void getSeconds$annotations(double <this>) {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final long getMinutes(int $this$minutes) {
      return toDuration($this$minutes, DurationUnit.MINUTES);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Int.minutes' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minutes",
   imports = {"kotlin.time.Duration.Companion.minutes"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalTime
   public static void getMinutes$annotations(int <this>) {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final long getMinutes(long $this$minutes) {
      return toDuration($this$minutes, DurationUnit.MINUTES);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Long.minutes' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minutes",
   imports = {"kotlin.time.Duration.Companion.minutes"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalTime
   public static void getMinutes$annotations(long <this>) {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final long getMinutes(double $this$minutes) {
      return toDuration($this$minutes, DurationUnit.MINUTES);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Double.minutes' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minutes",
   imports = {"kotlin.time.Duration.Companion.minutes"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalTime
   public static void getMinutes$annotations(double <this>) {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final long getHours(int $this$hours) {
      return toDuration($this$hours, DurationUnit.HOURS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Int.hours' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.hours",
   imports = {"kotlin.time.Duration.Companion.hours"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalTime
   public static void getHours$annotations(int <this>) {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final long getHours(long $this$hours) {
      return toDuration($this$hours, DurationUnit.HOURS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Long.hours' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.hours",
   imports = {"kotlin.time.Duration.Companion.hours"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalTime
   public static void getHours$annotations(long <this>) {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final long getHours(double $this$hours) {
      return toDuration($this$hours, DurationUnit.HOURS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Double.hours' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.hours",
   imports = {"kotlin.time.Duration.Companion.hours"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalTime
   public static void getHours$annotations(double <this>) {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final long getDays(int $this$days) {
      return toDuration($this$days, DurationUnit.DAYS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Int.days' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.days",
   imports = {"kotlin.time.Duration.Companion.days"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalTime
   public static void getDays$annotations(int <this>) {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final long getDays(long $this$days) {
      return toDuration($this$days, DurationUnit.DAYS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Long.days' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.days",
   imports = {"kotlin.time.Duration.Companion.days"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalTime
   public static void getDays$annotations(long <this>) {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final long getDays(double $this$days) {
      return toDuration($this$days, DurationUnit.DAYS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use 'Double.days' extension property from Duration.Companion instead.",
      replaceWith = @ReplaceWith(
   expression = "this.days",
   imports = {"kotlin.time.Duration.Companion.days"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalTime
   public static void getDays$annotations(double <this>) {
   }

   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalTime.class}
   )
   @InlineOnly
   private static final long times_mvk6XK0/* $FF was: times-mvk6XK0*/(int $this$times_u2dmvk6XK0, long duration) {
      return Duration.times-UwyO8pc(duration, $this$times_u2dmvk6XK0);
   }

   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalTime.class}
   )
   @InlineOnly
   private static final long times_kIfJnKk/* $FF was: times-kIfJnKk*/(double $this$times_u2dkIfJnKk, long duration) {
      return Duration.times-UwyO8pc(duration, $this$times_u2dkIfJnKk);
   }

   private static final long parseDuration(String value, boolean strictIso) {
      int length = value.length();
      if (length == 0) {
         throw new IllegalArgumentException("The string is empty");
      } else {
         int index = 0;
         long result = Duration.Companion.getZERO-UwyO8pc();
         String infinityString = "Infinity";
         boolean hasSign = (boolean)value.charAt(index);
         if (hasSign == 43 ? true : hasSign == 45) {
            ++index;
         }

         hasSign = index > 0;
         boolean isNegative = hasSign && StringsKt.startsWith$default((CharSequence)value, '-', false, 2, (Object)null);
         if (length <= index) {
            throw new IllegalArgumentException("No components");
         } else {
            if (value.charAt(index) != 'P') {
               if (strictIso) {
                  throw new IllegalArgumentException();
               }

               if (StringsKt.regionMatches(value, index, infinityString, 0, Math.max(length - index, infinityString.length()), true)) {
                  result = Duration.Companion.getINFINITE-UwyO8pc();
               } else {
                  DurationUnit prevUnit = null;
                  boolean afterFirst = false;
                  boolean allowSpaces = !hasSign;
                  if (hasSign && value.charAt(index) == '(' && StringsKt.last((CharSequence)value) == ')') {
                     allowSpaces = true;
                     ++index;
                     --length;
                     if (index == length) {
                        throw new IllegalArgumentException("No components");
                     }
                  }

                  while(index < length) {
                     if (afterFirst && allowSpaces) {
                        String $this$skipWhile$iv = value;
                        int $i$f$skipWhile = 0;

                        int i$iv;
                        for(i$iv = index; i$iv < $this$skipWhile$iv.length(); ++i$iv) {
                           char it = $this$skipWhile$iv.charAt(i$iv);
                           int var45 = 0;
                           if (it != ' ') {
                              break;
                           }
                        }

                        index = i$iv;
                     }

                     afterFirst = true;
                     int $i$f$substringWhile = 0;
                     String $this$skipWhile$iv$iv = value;
                     int $i$f$skipWhile = 0;

                     int i$iv$iv;
                     for(i$iv$iv = index; i$iv$iv < $this$skipWhile$iv$iv.length(); ++i$iv$iv) {
                        char it = $this$skipWhile$iv$iv.charAt(i$iv$iv);
                        int var56 = 0;
                        if (!('0' <= it ? it < ':' : false) && it != '.') {
                           break;
                        }
                     }

                     Intrinsics.checkNotNull(value, "null cannot be cast to non-null type java.lang.String");
                     String var59 = value.substring(index, i$iv$iv);
                     Intrinsics.checkNotNullExpressionValue(var59, "substring(...)");
                     String component = var59;
                     if (((CharSequence)component).length() == 0) {
                        throw new IllegalArgumentException();
                     }

                     index += component.length();
                     int $i$f$substringWhile = 0;
                     String $this$skipWhile$iv$iv = value;
                     i$iv$iv = 0;

                     int i$iv$iv;
                     for(i$iv$iv = index; i$iv$iv < $this$skipWhile$iv$iv.length(); ++i$iv$iv) {
                        char it = $this$skipWhile$iv$iv.charAt(i$iv$iv);
                        int var21 = 0;
                        if (!('a' <= it ? it < '{' : false)) {
                           break;
                        }
                     }

                     Intrinsics.checkNotNull(value, "null cannot be cast to non-null type java.lang.String");
                     var59 = value.substring(index, i$iv$iv);
                     Intrinsics.checkNotNullExpressionValue(var59, "substring(...)");
                     String unitName = var59;
                     index += unitName.length();
                     DurationUnit unit = DurationUnitKt.durationUnitByShortName(unitName);
                     if (prevUnit != null && prevUnit.compareTo(unit) <= 0) {
                        throw new IllegalArgumentException("Unexpected order of duration components");
                     }

                     prevUnit = unit;
                     $i$f$substringWhile = StringsKt.indexOf$default((CharSequence)component, '.', 0, false, 6, (Object)null);
                     if ($i$f$substringWhile > 0) {
                        i$iv$iv = 0;
                        Intrinsics.checkNotNull(component, "null cannot be cast to non-null type java.lang.String");
                        var59 = component.substring(i$iv$iv, $i$f$substringWhile);
                        Intrinsics.checkNotNullExpressionValue(var59, "substring(...)");
                        $this$skipWhile$iv$iv = var59;
                        result = Duration.plus-LRDsOJo(result, toDuration(Long.parseLong($this$skipWhile$iv$iv), unit));
                        Intrinsics.checkNotNull(component, "null cannot be cast to non-null type java.lang.String");
                        String var62 = component.substring($i$f$substringWhile);
                        Intrinsics.checkNotNullExpressionValue(var62, "substring(...)");
                        result = Duration.plus-LRDsOJo(result, toDuration(Double.parseDouble(var62), unit));
                        if (index < length) {
                           throw new IllegalArgumentException("Fractional component must be last");
                        }
                     } else {
                        result = Duration.plus-LRDsOJo(result, toDuration(Long.parseLong(component), unit));
                     }
                  }
               }
            } else {
               ++index;
               if (index == length) {
                  throw new IllegalArgumentException();
               }

               String nonDigitSymbols = "+-.";
               boolean isTimeComponent = false;
               DurationUnit prevUnit = null;

               while(index < length) {
                  if (value.charAt(index) == 'T') {
                     if (isTimeComponent) {
                        throw new IllegalArgumentException();
                     }

                     ++index;
                     if (index == length) {
                        throw new IllegalArgumentException();
                     }

                     isTimeComponent = true;
                  } else {
                     int $i$f$substringWhile = 0;
                     String $this$skipWhile$iv$iv = value;
                     int $i$f$skipWhile = 0;

                     int i$iv$iv;
                     for(i$iv$iv = index; i$iv$iv < $this$skipWhile$iv$iv.length(); ++i$iv$iv) {
                        char it = $this$skipWhile$iv$iv.charAt(i$iv$iv);
                        int var20 = 0;
                        if (!('0' <= it ? it < ':' : false) && !StringsKt.contains$default((CharSequence)nonDigitSymbols, it, false, 2, (Object)null)) {
                           break;
                        }
                     }

                     Intrinsics.checkNotNull(value, "null cannot be cast to non-null type java.lang.String");
                     String var10000 = value.substring(index, i$iv$iv);
                     Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
                     String component = var10000;
                     if (((CharSequence)component).length() == 0) {
                        throw new IllegalArgumentException();
                     }

                     index += component.length();
                     CharSequence unit = (CharSequence)value;
                     if (!(0 <= index ? index < unit.length() : false)) {
                        int whole = 0;
                        throw new IllegalArgumentException("Missing unit for value " + component);
                     }

                     char $this$substringWhile$iv = unit.charAt(index);
                     ++index;
                     DurationUnit i$iv = DurationUnitKt.durationUnitByIsoChar($this$substringWhile$iv, isTimeComponent);
                     if (prevUnit != null && prevUnit.compareTo(i$iv) <= 0) {
                        throw new IllegalArgumentException("Unexpected order of duration components");
                     }

                     prevUnit = i$iv;
                     int dotIndex = StringsKt.indexOf$default((CharSequence)component, '.', 0, false, 6, (Object)null);
                     if (i$iv == DurationUnit.SECONDS && dotIndex > 0) {
                        i$iv$iv = 0;
                        Intrinsics.checkNotNull(component, "null cannot be cast to non-null type java.lang.String");
                        var10000 = component.substring(i$iv$iv, dotIndex);
                        Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
                        $this$skipWhile$iv$iv = var10000;
                        result = Duration.plus-LRDsOJo(result, toDuration(parseOverLongIsoComponent($this$skipWhile$iv$iv), i$iv));
                        Intrinsics.checkNotNull(component, "null cannot be cast to non-null type java.lang.String");
                        String var10001 = component.substring(dotIndex);
                        Intrinsics.checkNotNullExpressionValue(var10001, "substring(...)");
                        result = Duration.plus-LRDsOJo(result, toDuration(Double.parseDouble(var10001), i$iv));
                     } else {
                        result = Duration.plus-LRDsOJo(result, toDuration(parseOverLongIsoComponent(component), i$iv));
                     }
                  }
               }
            }

            return isNegative ? Duration.unaryMinus-UwyO8pc(result) : result;
         }
      }
   }

   private static final long parseOverLongIsoComponent(String value) {
      int length = value.length();
      int startIndex = 0;
      if (length > 0 && StringsKt.contains$default((CharSequence)"+-", value.charAt(0), false, 2, (Object)null)) {
         ++startIndex;
      }

      if (length - startIndex > 16) {
         Iterable $this$all$iv = new IntRange(startIndex, StringsKt.getLastIndex((CharSequence)value));
         int $i$f$all = 0;
         boolean var10000;
         if ($this$all$iv instanceof Collection && ((Collection)$this$all$iv).isEmpty()) {
            var10000 = true;
         } else {
            label62: {
               Iterator var5 = $this$all$iv.iterator();

               while(var5.hasNext()) {
                  int element$iv = ((IntIterator)var5).nextInt();
                  int var8 = 0;
                  char var9 = value.charAt(element$iv);
                  if (!('0' <= var9 ? var9 < ':' : false)) {
                     var10000 = false;
                     break label62;
                  }
               }

               var10000 = true;
            }
         }

         if (var10000) {
            return value.charAt(0) == '-' ? Long.MIN_VALUE : Long.MAX_VALUE;
         }
      }

      return StringsKt.startsWith$default(value, "+", false, 2, (Object)null) ? Long.parseLong(StringsKt.drop(value, 1)) : Long.parseLong(value);
   }

   private static final String substringWhile(String $this$substringWhile, int startIndex, Function1 predicate) {
      int $i$f$substringWhile = 0;
      String $this$skipWhile$iv = $this$substringWhile;
      int $i$f$skipWhile = 0;

      int i$iv;
      for(i$iv = startIndex; i$iv < $this$skipWhile$iv.length() && (Boolean)predicate.invoke($this$skipWhile$iv.charAt(i$iv)); ++i$iv) {
      }

      Intrinsics.checkNotNull($this$substringWhile, "null cannot be cast to non-null type java.lang.String");
      String var10000 = $this$substringWhile.substring(startIndex, i$iv);
      Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
      return var10000;
   }

   private static final int skipWhile(String $this$skipWhile, int startIndex, Function1 predicate) {
      int $i$f$skipWhile = 0;

      int i;
      for(i = startIndex; i < $this$skipWhile.length() && (Boolean)predicate.invoke($this$skipWhile.charAt(i)); ++i) {
      }

      return i;
   }

   private static final long nanosToMillis(long nanos) {
      return nanos / (long)1000000;
   }

   private static final long millisToNanos(long millis) {
      return millis * (long)1000000;
   }

   private static final long durationOfNanos(long normalNanos) {
      return Duration.constructor-impl(normalNanos << 1);
   }

   private static final long durationOfMillis(long normalMillis) {
      return Duration.constructor-impl((normalMillis << 1) + 1L);
   }

   private static final long durationOf(long normalValue, int unitDiscriminator) {
      return Duration.constructor-impl((normalValue << 1) + (long)unitDiscriminator);
   }

   private static final long durationOfNanosNormalized(long nanos) {
      return (-4611686018426999999L <= nanos ? nanos < 4611686018427000000L : false) ? durationOfNanos(nanos) : durationOfMillis(nanosToMillis(nanos));
   }

   private static final long durationOfMillisNormalized(long millis) {
      return (-4611686018426L <= millis ? millis < 4611686018427L : false) ? durationOfNanos(millisToNanos(millis)) : durationOfMillis(RangesKt.coerceIn(millis, -4611686018427387903L, 4611686018427387903L));
   }

   // $FF: synthetic method
   public static final long access$parseDuration(String value, boolean strictIso) {
      return parseDuration(value, strictIso);
   }

   // $FF: synthetic method
   public static final long access$durationOf(long normalValue, int unitDiscriminator) {
      return durationOf(normalValue, unitDiscriminator);
   }

   // $FF: synthetic method
   public static final long access$durationOfNanosNormalized(long nanos) {
      return durationOfNanosNormalized(nanos);
   }

   // $FF: synthetic method
   public static final long access$durationOfMillisNormalized(long millis) {
      return durationOfMillisNormalized(millis);
   }

   // $FF: synthetic method
   public static final long access$nanosToMillis(long nanos) {
      return nanosToMillis(nanos);
   }

   // $FF: synthetic method
   public static final long access$millisToNanos(long millis) {
      return millisToNanos(millis);
   }

   // $FF: synthetic method
   public static final long access$durationOfNanos(long normalNanos) {
      return durationOfNanos(normalNanos);
   }

   // $FF: synthetic method
   public static final long access$durationOfMillis(long normalMillis) {
      return durationOfMillis(normalMillis);
   }
}
