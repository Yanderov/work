package kotlin.time;

import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.comparisons.ComparisonsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JvmInline
@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b@\b\u0087@\u0018\u0000 §\u00012\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002§\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\"\u0010\n\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0002ø\u0001\u0000¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u000f\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0000H\u0096\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\fH\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0015J\u0018\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u001c\u001a\u00020\u00192\b\u0010\u000b\u001a\u0004\u0018\u00010\u0018HÖ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001f\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\r\u0010\"\u001a\u00020\u0019¢\u0006\u0004\b \u0010!J\u000f\u0010$\u001a\u00020\u0019H\u0002¢\u0006\u0004\b#\u0010!J\u000f\u0010&\u001a\u00020\u0019H\u0002¢\u0006\u0004\b%\u0010!J\r\u0010(\u001a\u00020\u0019¢\u0006\u0004\b'\u0010!J\r\u0010*\u001a\u00020\u0019¢\u0006\u0004\b)\u0010!J\r\u0010,\u001a\u00020\u0019¢\u0006\u0004\b+\u0010!J\u0018\u0010/\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b-\u0010.J\u0018\u00101\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b0\u0010.J\u001b\u00103\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b2\u0010\u0013J\u001b\u00103\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\fH\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b2\u0010\u0015J\u009d\u0001\u0010@\u001a\u00028\u0000\"\u0004\b\u0000\u001042u\u0010=\u001aq\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(8\u0012\u0013\u0012\u00110\f¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(9\u0012\u0013\u0012\u00110\f¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(:\u0012\u0013\u0012\u00110\f¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(;\u0012\u0013\u0012\u00110\f¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(<\u0012\u0004\u0012\u00028\u000005H\u0086\bø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b>\u0010?J\u0088\u0001\u0010@\u001a\u00028\u0000\"\u0004\b\u0000\u001042`\u0010=\u001a\\\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(9\u0012\u0013\u0012\u00110\f¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(:\u0012\u0013\u0012\u00110\f¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(;\u0012\u0013\u0012\u00110\f¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(<\u0012\u0004\u0012\u00028\u00000AH\u0086\bø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b>\u0010BJs\u0010@\u001a\u00028\u0000\"\u0004\b\u0000\u001042K\u0010=\u001aG\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(:\u0012\u0013\u0012\u00110\f¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(;\u0012\u0013\u0012\u00110\f¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(<\u0012\u0004\u0012\u00028\u00000CH\u0086\bø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b>\u0010DJ^\u0010@\u001a\u00028\u0000\"\u0004\b\u0000\u0010426\u0010=\u001a2\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(;\u0012\u0013\u0012\u00110\f¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(<\u0012\u0004\u0012\u00028\u00000EH\u0086\bø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b>\u0010FJ\u0015\u0010K\u001a\u00020\u00102\u0006\u0010H\u001a\u00020G¢\u0006\u0004\bI\u0010JJ\u0015\u0010N\u001a\u00020\f2\u0006\u0010H\u001a\u00020G¢\u0006\u0004\bL\u0010MJ\r\u0010R\u001a\u00020O¢\u0006\u0004\bP\u0010QJ\u0015\u0010U\u001a\u00020\u00022\u0006\u0010H\u001a\u00020G¢\u0006\u0004\bS\u0010TJ\u000f\u0010W\u001a\u00020\u0002H\u0007¢\u0006\u0004\bV\u0010\u0005J\u000f\u0010Y\u001a\u00020\u0002H\u0007¢\u0006\u0004\bX\u0010\u0005J\u000f\u0010[\u001a\u00020OH\u0016¢\u0006\u0004\bZ\u0010QJ\u001f\u0010[\u001a\u00020O2\u0006\u0010H\u001a\u00020G2\b\b\u0002\u0010\\\u001a\u00020\f¢\u0006\u0004\bZ\u0010]J\u001a\u0010_\u001a\u00020\u00002\u0006\u0010H\u001a\u00020GH\u0000ø\u0001\u0000¢\u0006\u0004\b^\u0010TJ\u0013\u0010a\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b`\u0010\u0005J?\u0010k\u001a\u00020h*\u00060bj\u0002`c2\u0006\u0010d\u001a\u00020\f2\u0006\u0010e\u001a\u00020\f2\u0006\u0010f\u001a\u00020\f2\u0006\u0010H\u001a\u00020O2\u0006\u0010g\u001a\u00020\u0019H\u0002¢\u0006\u0004\bi\u0010jR\u0014\u0010m\u001a\u00020\u00008Fø\u0001\u0000¢\u0006\u0006\u001a\u0004\bl\u0010\u0005R\u001a\u0010q\u001a\u00020\f8@X\u0081\u0004¢\u0006\f\u0012\u0004\bo\u0010p\u001a\u0004\bn\u0010\u001eR\u001a\u0010u\u001a\u00020\u00108FX\u0087\u0004¢\u0006\f\u0012\u0004\bt\u0010p\u001a\u0004\br\u0010sR\u001a\u0010x\u001a\u00020\u00108FX\u0087\u0004¢\u0006\f\u0012\u0004\bw\u0010p\u001a\u0004\bv\u0010sR\u001a\u0010{\u001a\u00020\u00108FX\u0087\u0004¢\u0006\f\u0012\u0004\bz\u0010p\u001a\u0004\by\u0010sR\u001a\u0010~\u001a\u00020\u00108FX\u0087\u0004¢\u0006\f\u0012\u0004\b}\u0010p\u001a\u0004\b|\u0010sR\u001c\u0010\u0081\u0001\u001a\u00020\u00108FX\u0087\u0004¢\u0006\r\u0012\u0005\b\u0080\u0001\u0010p\u001a\u0004\b\u007f\u0010sR\u001d\u0010\u0084\u0001\u001a\u00020\u00108FX\u0087\u0004¢\u0006\u000e\u0012\u0005\b\u0083\u0001\u0010p\u001a\u0005\b\u0082\u0001\u0010sR\u001d\u0010\u0087\u0001\u001a\u00020\u00108FX\u0087\u0004¢\u0006\u000e\u0012\u0005\b\u0086\u0001\u0010p\u001a\u0005\b\u0085\u0001\u0010sR\u0013\u0010\u0089\u0001\u001a\u00020\u00028F¢\u0006\u0007\u001a\u0005\b\u0088\u0001\u0010\u0005R\u0013\u0010\u008b\u0001\u001a\u00020\u00028F¢\u0006\u0007\u001a\u0005\b\u008a\u0001\u0010\u0005R\u0013\u0010\u008d\u0001\u001a\u00020\u00028F¢\u0006\u0007\u001a\u0005\b\u008c\u0001\u0010\u0005R\u0013\u0010\u008f\u0001\u001a\u00020\u00028F¢\u0006\u0007\u001a\u0005\b\u008e\u0001\u0010\u0005R\u0013\u0010\u0091\u0001\u001a\u00020\u00028F¢\u0006\u0007\u001a\u0005\b\u0090\u0001\u0010\u0005R\u0013\u0010\u0093\u0001\u001a\u00020\u00028F¢\u0006\u0007\u001a\u0005\b\u0092\u0001\u0010\u0005R\u0013\u0010\u0095\u0001\u001a\u00020\u00028F¢\u0006\u0007\u001a\u0005\b\u0094\u0001\u0010\u0005R\u001d\u0010\u0098\u0001\u001a\u00020\f8@X\u0081\u0004¢\u0006\u000e\u0012\u0005\b\u0097\u0001\u0010p\u001a\u0005\b\u0096\u0001\u0010\u001eR\u001d\u0010\u009b\u0001\u001a\u00020\f8@X\u0081\u0004¢\u0006\u000e\u0012\u0005\b\u009a\u0001\u0010p\u001a\u0005\b\u0099\u0001\u0010\u001eR\u0015\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0003\u0010\u009c\u0001R\u001d\u0010\u009f\u0001\u001a\u00020\f8@X\u0081\u0004¢\u0006\u000e\u0012\u0005\b\u009e\u0001\u0010p\u001a\u0005\b\u009d\u0001\u0010\u001eR\u0017\u0010¢\u0001\u001a\u00020G8BX\u0082\u0004¢\u0006\b\u001a\u0006\b \u0001\u0010¡\u0001R\u0017\u0010¤\u0001\u001a\u00020\f8Â\u0002X\u0082\u0004¢\u0006\u0007\u001a\u0005\b£\u0001\u0010\u001eR\u0016\u0010¦\u0001\u001a\u00020\u00028BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b¥\u0001\u0010\u0005\u0088\u0001\u0003\u0092\u0001\u00020\u0002\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u009920\u0001¨\u0006¨\u0001"},
   d2 = {"Lkotlin/time/Duration;", "", "", "rawValue", "constructor-impl", "(J)J", "thisMillis", "otherNanos", "addValuesMixedRanges-UwyO8pc", "(JJJ)J", "addValuesMixedRanges", "other", "", "compareTo-LRDsOJo", "(JJ)I", "compareTo", "", "scale", "div-UwyO8pc", "(JD)J", "div", "(JI)J", "div-LRDsOJo", "(JJ)D", "", "", "equals-impl", "(JLjava/lang/Object;)Z", "equals", "hashCode-impl", "(J)I", "hashCode", "isFinite-impl", "(J)Z", "isFinite", "isInMillis-impl", "isInMillis", "isInNanos-impl", "isInNanos", "isInfinite-impl", "isInfinite", "isNegative-impl", "isNegative", "isPositive-impl", "isPositive", "minus-LRDsOJo", "(JJ)J", "minus", "plus-LRDsOJo", "plus", "times-UwyO8pc", "times", "T", "Lkotlin/Function5;", "Lkotlin/ParameterName;", "name", "days", "hours", "minutes", "seconds", "nanoseconds", "action", "toComponents-impl", "(JLkotlin/jvm/functions/Function5;)Ljava/lang/Object;", "toComponents", "Lkotlin/Function4;", "(JLkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "Lkotlin/Function3;", "(JLkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "Lkotlin/Function2;", "(JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "Lkotlin/time/DurationUnit;", "unit", "toDouble-impl", "(JLkotlin/time/DurationUnit;)D", "toDouble", "toInt-impl", "(JLkotlin/time/DurationUnit;)I", "toInt", "", "toIsoString-impl", "(J)Ljava/lang/String;", "toIsoString", "toLong-impl", "(JLkotlin/time/DurationUnit;)J", "toLong", "toLongMilliseconds-impl", "toLongMilliseconds", "toLongNanoseconds-impl", "toLongNanoseconds", "toString-impl", "toString", "decimals", "(JLkotlin/time/DurationUnit;I)Ljava/lang/String;", "truncateTo-UwyO8pc$kotlin_stdlib", "truncateTo", "unaryMinus-UwyO8pc", "unaryMinus", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "whole", "fractional", "fractionalSize", "isoZeroes", "", "appendFractional-impl", "(JLjava/lang/StringBuilder;IIILjava/lang/String;Z)V", "appendFractional", "getAbsoluteValue-UwyO8pc", "absoluteValue", "getHoursComponent-impl", "getHoursComponent$annotations", "()V", "hoursComponent", "getInDays-impl", "(J)D", "getInDays$annotations", "inDays", "getInHours-impl", "getInHours$annotations", "inHours", "getInMicroseconds-impl", "getInMicroseconds$annotations", "inMicroseconds", "getInMilliseconds-impl", "getInMilliseconds$annotations", "inMilliseconds", "getInMinutes-impl", "getInMinutes$annotations", "inMinutes", "getInNanoseconds-impl", "getInNanoseconds$annotations", "inNanoseconds", "getInSeconds-impl", "getInSeconds$annotations", "inSeconds", "getInWholeDays-impl", "inWholeDays", "getInWholeHours-impl", "inWholeHours", "getInWholeMicroseconds-impl", "inWholeMicroseconds", "getInWholeMilliseconds-impl", "inWholeMilliseconds", "getInWholeMinutes-impl", "inWholeMinutes", "getInWholeNanoseconds-impl", "inWholeNanoseconds", "getInWholeSeconds-impl", "inWholeSeconds", "getMinutesComponent-impl", "getMinutesComponent$annotations", "minutesComponent", "getNanosecondsComponent-impl", "getNanosecondsComponent$annotations", "nanosecondsComponent", "J", "getSecondsComponent-impl", "getSecondsComponent$annotations", "secondsComponent", "getStorageUnit-impl", "(J)Lkotlin/time/DurationUnit;", "storageUnit", "getUnitDiscriminator-impl", "unitDiscriminator", "getValue-impl", "value", "Companion", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.6"
)
@WasExperimental(
   markerClass = {ExperimentalTime.class}
)
@SourceDebugExtension({"SMAP\nDuration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Duration.kt\nkotlin/time/Duration\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1494:1\n38#1:1495\n38#1:1496\n38#1:1497\n38#1:1498\n38#1:1499\n683#1,2:1500\n700#1,2:1509\n170#2,6:1502\n1#3:1508\n*S KotlinDebug\n*F\n+ 1 Duration.kt\nkotlin/time/Duration\n*L\n39#1:1495\n40#1:1496\n458#1:1497\n478#1:1498\n662#1:1499\n979#1:1500,2\n1070#1:1509,2\n1021#1:1502,6\n*E\n"})
public final class Duration implements Comparable {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private final long rawValue;
   private static final long ZERO = constructor-impl(0L);
   private static final long INFINITE = DurationKt.access$durationOfMillis(4611686018427387903L);
   private static final long NEG_INFINITE = DurationKt.access$durationOfMillis(-4611686018427387903L);

   private static final long getValue_impl/* $FF was: getValue-impl*/(long arg0) {
      return arg0 >> 1;
   }

   private static final int getUnitDiscriminator_impl/* $FF was: getUnitDiscriminator-impl*/(long arg0) {
      int var2 = 0;
      return (int)arg0 & 1;
   }

   private static final boolean isInNanos_impl/* $FF was: isInNanos-impl*/(long arg0) {
      int var2 = 0;
      return ((int)arg0 & 1) == 0;
   }

   private static final boolean isInMillis_impl/* $FF was: isInMillis-impl*/(long arg0) {
      int var2 = 0;
      return ((int)arg0 & 1) == 1;
   }

   private static final DurationUnit getStorageUnit_impl/* $FF was: getStorageUnit-impl*/(long arg0) {
      return isInNanos-impl(arg0) ? DurationUnit.NANOSECONDS : DurationUnit.MILLISECONDS;
   }

   public static final long unaryMinus_UwyO8pc/* $FF was: unaryMinus-UwyO8pc*/(long arg0) {
      long var10000 = -getValue-impl(arg0);
      int var2 = 0;
      return DurationKt.access$durationOf(var10000, (int)arg0 & 1);
   }

   public static final long plus_LRDsOJo/* $FF was: plus-LRDsOJo*/(long arg0, long other) {
      if (isInfinite-impl(arg0)) {
         if (!isFinite-impl(other) && (arg0 ^ other) < 0L) {
            throw new IllegalArgumentException("Summing infinite durations of different signs yields an undefined result.");
         } else {
            return arg0;
         }
      } else if (isInfinite-impl(other)) {
         return other;
      } else {
         int var4 = 0;
         int var10000 = (int)arg0 & 1;
         var4 = 0;
         long var8;
         if (var10000 == ((int)other & 1)) {
            long result = getValue-impl(arg0) + getValue-impl(other);
            var8 = isInNanos-impl(arg0) ? DurationKt.access$durationOfNanosNormalized(result) : DurationKt.access$durationOfMillisNormalized(result);
         } else {
            var8 = isInMillis-impl(arg0) ? addValuesMixedRanges-UwyO8pc(arg0, getValue-impl(arg0), getValue-impl(other)) : addValuesMixedRanges-UwyO8pc(arg0, getValue-impl(other), getValue-impl(arg0));
         }

         return var8;
      }
   }

   private static final long addValuesMixedRanges_UwyO8pc/* $FF was: addValuesMixedRanges-UwyO8pc*/(long arg0, long thisMillis, long otherNanos) {
      long otherMillis = DurationKt.access$nanosToMillis(otherNanos);
      long resultMillis = thisMillis + otherMillis;
      long var10000;
      if (-4611686018426L <= resultMillis ? resultMillis < 4611686018427L : false) {
         long otherNanoRemainder = otherNanos - DurationKt.access$millisToNanos(otherMillis);
         var10000 = DurationKt.access$durationOfNanos(DurationKt.access$millisToNanos(resultMillis) + otherNanoRemainder);
      } else {
         var10000 = DurationKt.access$durationOfMillis(RangesKt.coerceIn(resultMillis, -4611686018427387903L, 4611686018427387903L));
      }

      return var10000;
   }

   public static final long minus_LRDsOJo/* $FF was: minus-LRDsOJo*/(long arg0, long other) {
      return plus-LRDsOJo(arg0, unaryMinus-UwyO8pc(other));
   }

   public static final long times_UwyO8pc/* $FF was: times-UwyO8pc*/(long arg0, int scale) {
      if (isInfinite-impl(arg0)) {
         if (scale == 0) {
            throw new IllegalArgumentException("Multiplying infinite duration by zero yields an undefined result.");
         } else {
            return scale > 0 ? arg0 : unaryMinus-UwyO8pc(arg0);
         }
      } else if (scale == 0) {
         return ZERO;
      } else {
         long value = getValue-impl(arg0);
         long result = value * (long)scale;
         long var10000;
         if (isInNanos-impl(arg0)) {
            if (-2147483647L <= value ? value < 2147483648L : false) {
               var10000 = DurationKt.access$durationOfNanos(result);
            } else if (result / (long)scale == value) {
               var10000 = DurationKt.access$durationOfNanosNormalized(result);
            } else {
               long millis = DurationKt.access$nanosToMillis(value);
               long remNanos = value - DurationKt.access$millisToNanos(millis);
               long resultMillis = millis * (long)scale;
               long totalMillis = resultMillis + DurationKt.access$nanosToMillis(remNanos * (long)scale);
               var10000 = resultMillis / (long)scale == millis && (totalMillis ^ resultMillis) >= 0L ? DurationKt.access$durationOfMillis(RangesKt.coerceIn(totalMillis, new LongRange(-4611686018427387903L, 4611686018427387903L))) : (MathKt.getSign(value) * MathKt.getSign(scale) > 0 ? INFINITE : NEG_INFINITE);
            }
         } else {
            var10000 = result / (long)scale == value ? DurationKt.access$durationOfMillis(RangesKt.coerceIn(result, new LongRange(-4611686018427387903L, 4611686018427387903L))) : (MathKt.getSign(value) * MathKt.getSign(scale) > 0 ? INFINITE : NEG_INFINITE);
         }

         return var10000;
      }
   }

   public static final long times_UwyO8pc/* $FF was: times-UwyO8pc*/(long arg0, double scale) {
      int intScale = MathKt.roundToInt(scale);
      if ((double)intScale == scale) {
         return times-UwyO8pc(arg0, intScale);
      } else {
         DurationUnit unit = getStorageUnit-impl(arg0);
         double result = toDouble-impl(arg0, unit) * scale;
         return DurationKt.toDuration(result, unit);
      }
   }

   public static final long div_UwyO8pc/* $FF was: div-UwyO8pc*/(long arg0, int scale) {
      if (scale == 0) {
         long var10000;
         if (isPositive-impl(arg0)) {
            var10000 = INFINITE;
         } else {
            if (!isNegative-impl(arg0)) {
               throw new IllegalArgumentException("Dividing zero duration by zero yields an undefined result.");
            }

            var10000 = NEG_INFINITE;
         }

         return var10000;
      } else if (isInNanos-impl(arg0)) {
         return DurationKt.access$durationOfNanos(getValue-impl(arg0) / (long)scale);
      } else if (isInfinite-impl(arg0)) {
         return times-UwyO8pc(arg0, MathKt.getSign(scale));
      } else {
         long result = getValue-impl(arg0) / (long)scale;
         if (-4611686018426L <= result ? result < 4611686018427L : false) {
            long rem = DurationKt.access$millisToNanos(getValue-impl(arg0) - result * (long)scale) / (long)scale;
            return DurationKt.access$durationOfNanos(DurationKt.access$millisToNanos(result) + rem);
         } else {
            return DurationKt.access$durationOfMillis(result);
         }
      }
   }

   public static final long div_UwyO8pc/* $FF was: div-UwyO8pc*/(long arg0, double scale) {
      int intScale = MathKt.roundToInt(scale);
      if ((double)intScale == scale && intScale != 0) {
         return div-UwyO8pc(arg0, intScale);
      } else {
         DurationUnit unit = getStorageUnit-impl(arg0);
         double result = toDouble-impl(arg0, unit) / scale;
         return DurationKt.toDuration(result, unit);
      }
   }

   public static final double div_LRDsOJo/* $FF was: div-LRDsOJo*/(long arg0, long other) {
      DurationUnit coarserUnit = (DurationUnit)ComparisonsKt.maxOf((Comparable)getStorageUnit-impl(arg0), (Comparable)getStorageUnit-impl(other));
      return toDouble-impl(arg0, coarserUnit) / toDouble-impl(other, coarserUnit);
   }

   public static final long truncateTo_UwyO8pc$kotlin_stdlib/* $FF was: truncateTo-UwyO8pc$kotlin_stdlib*/(long arg0, @NotNull DurationUnit unit) {
      Intrinsics.checkNotNullParameter(unit, "unit");
      DurationUnit storageUnit = getStorageUnit-impl(arg0);
      if (unit.compareTo(storageUnit) > 0 && !isInfinite-impl(arg0)) {
         long scale = DurationUnitKt.convertDurationUnit(1L, unit, storageUnit);
         long result = getValue-impl(arg0) - getValue-impl(arg0) % scale;
         return DurationKt.toDuration(result, storageUnit);
      } else {
         return arg0;
      }
   }

   public static final boolean isNegative_impl/* $FF was: isNegative-impl*/(long arg0) {
      return arg0 < 0L;
   }

   public static final boolean isPositive_impl/* $FF was: isPositive-impl*/(long arg0) {
      return arg0 > 0L;
   }

   public static final boolean isInfinite_impl/* $FF was: isInfinite-impl*/(long arg0) {
      return arg0 == INFINITE || arg0 == NEG_INFINITE;
   }

   public static final boolean isFinite_impl/* $FF was: isFinite-impl*/(long arg0) {
      return !isInfinite-impl(arg0);
   }

   public static final long getAbsoluteValue_UwyO8pc/* $FF was: getAbsoluteValue-UwyO8pc*/(long arg0) {
      return isNegative-impl(arg0) ? unaryMinus-UwyO8pc(arg0) : arg0;
   }

   public static int compareTo_LRDsOJo/* $FF was: compareTo-LRDsOJo*/(long arg0, long other) {
      long compareBits = arg0 ^ other;
      if (compareBits >= 0L && ((int)compareBits & 1) != 0) {
         int var7 = 0;
         int var10000 = (int)arg0 & 1;
         var7 = 0;
         int r = var10000 - ((int)other & 1);
         return isNegative-impl(arg0) ? -r : r;
      } else {
         return Intrinsics.compare(arg0, other);
      }
   }

   public int compareTo_LRDsOJo/* $FF was: compareTo-LRDsOJo*/(long other) {
      return compareTo-LRDsOJo(this.rawValue, other);
   }

   public static final Object toComponents_impl/* $FF was: toComponents-impl*/(long arg0, @NotNull Function5 action) {
      Intrinsics.checkNotNullParameter(action, "action");
      int var3 = 0;
      return action.invoke(getInWholeDays-impl(arg0), getHoursComponent-impl(arg0), getMinutesComponent-impl(arg0), getSecondsComponent-impl(arg0), getNanosecondsComponent-impl(arg0));
   }

   public static final Object toComponents_impl/* $FF was: toComponents-impl*/(long arg0, @NotNull Function4 action) {
      Intrinsics.checkNotNullParameter(action, "action");
      int var3 = 0;
      return action.invoke(getInWholeHours-impl(arg0), getMinutesComponent-impl(arg0), getSecondsComponent-impl(arg0), getNanosecondsComponent-impl(arg0));
   }

   public static final Object toComponents_impl/* $FF was: toComponents-impl*/(long arg0, @NotNull Function3 action) {
      Intrinsics.checkNotNullParameter(action, "action");
      int var3 = 0;
      return action.invoke(getInWholeMinutes-impl(arg0), getSecondsComponent-impl(arg0), getNanosecondsComponent-impl(arg0));
   }

   public static final Object toComponents_impl/* $FF was: toComponents-impl*/(long arg0, @NotNull Function2 action) {
      Intrinsics.checkNotNullParameter(action, "action");
      int var3 = 0;
      return action.invoke(getInWholeSeconds-impl(arg0), getNanosecondsComponent-impl(arg0));
   }

   public static final int getHoursComponent_impl/* $FF was: getHoursComponent-impl*/(long arg0) {
      return isInfinite-impl(arg0) ? 0 : (int)(getInWholeHours-impl(arg0) % (long)24);
   }

   /** @deprecated */
   // $FF: synthetic method
   @PublishedApi
   public static void getHoursComponent$annotations() {
   }

   public static final int getMinutesComponent_impl/* $FF was: getMinutesComponent-impl*/(long arg0) {
      return isInfinite-impl(arg0) ? 0 : (int)(getInWholeMinutes-impl(arg0) % (long)60);
   }

   /** @deprecated */
   // $FF: synthetic method
   @PublishedApi
   public static void getMinutesComponent$annotations() {
   }

   public static final int getSecondsComponent_impl/* $FF was: getSecondsComponent-impl*/(long arg0) {
      return isInfinite-impl(arg0) ? 0 : (int)(getInWholeSeconds-impl(arg0) % (long)60);
   }

   /** @deprecated */
   // $FF: synthetic method
   @PublishedApi
   public static void getSecondsComponent$annotations() {
   }

   public static final int getNanosecondsComponent_impl/* $FF was: getNanosecondsComponent-impl*/(long arg0) {
      return isInfinite-impl(arg0) ? 0 : (isInMillis-impl(arg0) ? (int)DurationKt.access$millisToNanos(getValue-impl(arg0) % (long)1000) : (int)(getValue-impl(arg0) % (long)1000000000));
   }

   /** @deprecated */
   // $FF: synthetic method
   @PublishedApi
   public static void getNanosecondsComponent$annotations() {
   }

   public static final double toDouble_impl/* $FF was: toDouble-impl*/(long arg0, @NotNull DurationUnit unit) {
      Intrinsics.checkNotNullParameter(unit, "unit");
      return arg0 == INFINITE ? Double.POSITIVE_INFINITY : (arg0 == NEG_INFINITE ? Double.NEGATIVE_INFINITY : DurationUnitKt.convertDurationUnit((double)getValue-impl(arg0), getStorageUnit-impl(arg0), unit));
   }

   public static final long toLong_impl/* $FF was: toLong-impl*/(long arg0, @NotNull DurationUnit unit) {
      Intrinsics.checkNotNullParameter(unit, "unit");
      return arg0 == INFINITE ? Long.MAX_VALUE : (arg0 == NEG_INFINITE ? Long.MIN_VALUE : DurationUnitKt.convertDurationUnit(getValue-impl(arg0), getStorageUnit-impl(arg0), unit));
   }

   public static final int toInt_impl/* $FF was: toInt-impl*/(long arg0, @NotNull DurationUnit unit) {
      Intrinsics.checkNotNullParameter(unit, "unit");
      return (int)RangesKt.coerceIn(toLong-impl(arg0, unit), -2147483648L, 2147483647L);
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final double getInDays_impl/* $FF was: getInDays-impl*/(long arg0) {
      return toDouble-impl(arg0, DurationUnit.DAYS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use inWholeDays property instead or convert toDouble(DAYS) if a double value is required.",
      replaceWith = @ReplaceWith(
   expression = "toDouble(DurationUnit.DAYS)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @ExperimentalTime
   public static void getInDays$annotations() {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final double getInHours_impl/* $FF was: getInHours-impl*/(long arg0) {
      return toDouble-impl(arg0, DurationUnit.HOURS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use inWholeHours property instead or convert toDouble(HOURS) if a double value is required.",
      replaceWith = @ReplaceWith(
   expression = "toDouble(DurationUnit.HOURS)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @ExperimentalTime
   public static void getInHours$annotations() {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final double getInMinutes_impl/* $FF was: getInMinutes-impl*/(long arg0) {
      return toDouble-impl(arg0, DurationUnit.MINUTES);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use inWholeMinutes property instead or convert toDouble(MINUTES) if a double value is required.",
      replaceWith = @ReplaceWith(
   expression = "toDouble(DurationUnit.MINUTES)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @ExperimentalTime
   public static void getInMinutes$annotations() {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final double getInSeconds_impl/* $FF was: getInSeconds-impl*/(long arg0) {
      return toDouble-impl(arg0, DurationUnit.SECONDS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use inWholeSeconds property instead or convert toDouble(SECONDS) if a double value is required.",
      replaceWith = @ReplaceWith(
   expression = "toDouble(DurationUnit.SECONDS)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @ExperimentalTime
   public static void getInSeconds$annotations() {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final double getInMilliseconds_impl/* $FF was: getInMilliseconds-impl*/(long arg0) {
      return toDouble-impl(arg0, DurationUnit.MILLISECONDS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use inWholeMilliseconds property instead or convert toDouble(MILLISECONDS) if a double value is required.",
      replaceWith = @ReplaceWith(
   expression = "toDouble(DurationUnit.MILLISECONDS)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @ExperimentalTime
   public static void getInMilliseconds$annotations() {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final double getInMicroseconds_impl/* $FF was: getInMicroseconds-impl*/(long arg0) {
      return toDouble-impl(arg0, DurationUnit.MICROSECONDS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use inWholeMicroseconds property instead or convert toDouble(MICROSECONDS) if a double value is required.",
      replaceWith = @ReplaceWith(
   expression = "toDouble(DurationUnit.MICROSECONDS)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @ExperimentalTime
   public static void getInMicroseconds$annotations() {
   }

   /** @deprecated */
   // $FF: synthetic method
   public static final double getInNanoseconds_impl/* $FF was: getInNanoseconds-impl*/(long arg0) {
      return toDouble-impl(arg0, DurationUnit.NANOSECONDS);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use inWholeNanoseconds property instead or convert toDouble(NANOSECONDS) if a double value is required.",
      replaceWith = @ReplaceWith(
   expression = "toDouble(DurationUnit.NANOSECONDS)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @ExperimentalTime
   public static void getInNanoseconds$annotations() {
   }

   public static final long getInWholeDays_impl/* $FF was: getInWholeDays-impl*/(long arg0) {
      return toLong-impl(arg0, DurationUnit.DAYS);
   }

   public static final long getInWholeHours_impl/* $FF was: getInWholeHours-impl*/(long arg0) {
      return toLong-impl(arg0, DurationUnit.HOURS);
   }

   public static final long getInWholeMinutes_impl/* $FF was: getInWholeMinutes-impl*/(long arg0) {
      return toLong-impl(arg0, DurationUnit.MINUTES);
   }

   public static final long getInWholeSeconds_impl/* $FF was: getInWholeSeconds-impl*/(long arg0) {
      return toLong-impl(arg0, DurationUnit.SECONDS);
   }

   public static final long getInWholeMilliseconds_impl/* $FF was: getInWholeMilliseconds-impl*/(long arg0) {
      return isInMillis-impl(arg0) && isFinite-impl(arg0) ? getValue-impl(arg0) : toLong-impl(arg0, DurationUnit.MILLISECONDS);
   }

   public static final long getInWholeMicroseconds_impl/* $FF was: getInWholeMicroseconds-impl*/(long arg0) {
      return toLong-impl(arg0, DurationUnit.MICROSECONDS);
   }

   public static final long getInWholeNanoseconds_impl/* $FF was: getInWholeNanoseconds-impl*/(long arg0) {
      long value = getValue-impl(arg0);
      return isInNanos-impl(arg0) ? value : (value > 9223372036854L ? Long.MAX_VALUE : (value < -9223372036854L ? Long.MIN_VALUE : DurationKt.access$millisToNanos(value)));
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use inWholeNanoseconds property instead.",
      replaceWith = @ReplaceWith(
   expression = "this.inWholeNanoseconds",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @ExperimentalTime
   public static final long toLongNanoseconds_impl/* $FF was: toLongNanoseconds-impl*/(long arg0) {
      return getInWholeNanoseconds-impl(arg0);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use inWholeMilliseconds property instead.",
      replaceWith = @ReplaceWith(
   expression = "this.inWholeMilliseconds",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5",
      errorSince = "1.8",
      hiddenSince = "1.9"
   )
   @ExperimentalTime
   public static final long toLongMilliseconds_impl/* $FF was: toLongMilliseconds-impl*/(long arg0) {
      return getInWholeMilliseconds-impl(arg0);
   }

   @NotNull
   public static String toString_impl/* $FF was: toString-impl*/(long arg0) {
      String var10000;
      if (arg0 == 0L) {
         var10000 = "0s";
      } else if (arg0 == INFINITE) {
         var10000 = "Infinity";
      } else if (arg0 == NEG_INFINITE) {
         var10000 = "-Infinity";
      } else {
         boolean isNegative = isNegative-impl(arg0);
         StringBuilder var5 = new StringBuilder();
         int var7 = 0;
         if (isNegative) {
            var5.append('-');
         }

         long arg0$iv = getAbsoluteValue-UwyO8pc(arg0);
         int var10 = 0;
         long var23 = getInWholeDays-impl(arg0$iv);
         int var10001 = getHoursComponent-impl(arg0$iv);
         int var10002 = getMinutesComponent-impl(arg0$iv);
         int var10003 = getSecondsComponent-impl(arg0$iv);
         int nanoseconds = getNanosecondsComponent-impl(arg0$iv);
         int seconds = var10003;
         int minutes = var10002;
         int hours = var10001;
         long days = var23;
         int var17 = 0;
         boolean hasDays = days != 0L;
         boolean hasHours = hours != 0;
         boolean hasMinutes = minutes != 0;
         boolean hasSeconds = seconds != 0 || nanoseconds != 0;
         int components = 0;
         if (hasDays) {
            var5.append(days).append('d');
            ++components;
         }

         if (hasHours || hasDays && (hasMinutes || hasSeconds)) {
            if (components++ > 0) {
               var5.append(' ');
            }

            var5.append(hours).append('h');
         }

         if (hasMinutes || hasSeconds && (hasHours || hasDays)) {
            if (components++ > 0) {
               var5.append(' ');
            }

            var5.append(minutes).append('m');
         }

         if (hasSeconds) {
            if (components++ > 0) {
               var5.append(' ');
            }

            if (seconds == 0 && !hasDays && !hasHours && !hasMinutes) {
               if (nanoseconds >= 1000000) {
                  appendFractional-impl(arg0, var5, nanoseconds / 1000000, nanoseconds % 1000000, 6, "ms", false);
               } else if (nanoseconds >= 1000) {
                  appendFractional-impl(arg0, var5, nanoseconds / 1000, nanoseconds % 1000, 3, "us", false);
               } else {
                  var5.append(nanoseconds).append("ns");
               }
            } else {
               appendFractional-impl(arg0, var5, seconds, nanoseconds, 9, "s", false);
            }
         }

         if (isNegative && components > 1) {
            var5.insert(1, '(').append(')');
         }

         var10000 = var5.toString();
         Intrinsics.checkNotNullExpressionValue(var10000, "toString(...)");
      }

      return var10000;
   }

   @NotNull
   public String toString() {
      return toString-impl(this.rawValue);
   }

   private static final void appendFractional_impl/* $FF was: appendFractional-impl*/(long arg0, StringBuilder $this$appendFractional, int whole, int fractional, int fractionalSize, String unit, boolean isoZeroes) {
      $this$appendFractional.append(whole);
      if (fractional != 0) {
         String fracString;
         int var10000;
         label31: {
            $this$appendFractional.append('.');
            fracString = StringsKt.padStart(String.valueOf(fractional), fractionalSize, '0');
            CharSequence $this$indexOfLast$iv = (CharSequence)fracString;
            int $i$f$indexOfLast = 0;
            int var12 = $this$indexOfLast$iv.length() + -1;
            if (0 <= var12) {
               do {
                  int index$iv = var12--;
                  char it = $this$indexOfLast$iv.charAt(index$iv);
                  int var15 = 0;
                  if (it != '0') {
                     var10000 = index$iv;
                     break label31;
                  }
               } while(0 <= var12);
            }

            var10000 = -1;
         }

         int nonZeroDigits = var10000 + 1;
         if (!isoZeroes && nonZeroDigits < 3) {
            Intrinsics.checkNotNullExpressionValue($this$appendFractional.append((CharSequence)fracString, 0, nonZeroDigits), "append(...)");
         } else {
            Intrinsics.checkNotNullExpressionValue($this$appendFractional.append((CharSequence)fracString, 0, (nonZeroDigits + 2) / 3 * 3), "append(...)");
         }
      }

      $this$appendFractional.append(unit);
   }

   @NotNull
   public static final String toString_impl/* $FF was: toString-impl*/(long arg0, @NotNull DurationUnit unit, int decimals) {
      Intrinsics.checkNotNullParameter(unit, "unit");
      boolean var4 = decimals >= 0;
      if (!var4) {
         int var5 = 0;
         String var7 = "decimals must be not negative, but was " + decimals;
         throw new IllegalArgumentException(var7.toString());
      } else {
         double number = toDouble-impl(arg0, unit);
         return Double.isInfinite(number) ? String.valueOf(number) : DurationJvmKt.formatToExactDecimals(number, RangesKt.coerceAtMost(decimals, 12)) + DurationUnitKt.shortName(unit);
      }
   }

   // $FF: synthetic method
   public static String toString_impl$default/* $FF was: toString-impl$default*/(long var0, DurationUnit var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var3 = 0;
      }

      return toString-impl(var0, var2, var3);
   }

   @NotNull
   public static final String toIsoString_impl/* $FF was: toIsoString-impl*/(long arg0) {
      StringBuilder var2 = new StringBuilder();
      int var4 = 0;
      if (isNegative-impl(arg0)) {
         var2.append('-');
      }

      var2.append("PT");
      long arg0$iv = getAbsoluteValue-UwyO8pc(arg0);
      int var7 = 0;
      long var10000 = getInWholeHours-impl(arg0$iv);
      int var10001 = getMinutesComponent-impl(arg0$iv);
      int var10002 = getSecondsComponent-impl(arg0$iv);
      int nanoseconds = getNanosecondsComponent-impl(arg0$iv);
      int seconds = var10002;
      int minutes = var10001;
      long hours = var10000;
      int var13 = 0;
      long hours = hours;
      if (isInfinite-impl(arg0)) {
         hours = 9999999999999L;
      }

      boolean hasHours = hours != 0L;
      boolean hasSeconds = seconds != 0 || nanoseconds != 0;
      boolean hasMinutes = minutes != 0 || hasSeconds && hasHours;
      if (hasHours) {
         var2.append(hours).append('H');
      }

      if (hasMinutes) {
         var2.append(minutes).append('M');
      }

      if (hasSeconds || !hasHours && !hasMinutes) {
         appendFractional-impl(arg0, var2, seconds, nanoseconds, 9, "S", true);
      }

      String var19 = var2.toString();
      Intrinsics.checkNotNullExpressionValue(var19, "toString(...)");
      return var19;
   }

   public static int hashCode_impl/* $FF was: hashCode-impl*/(long arg0) {
      return Long.hashCode(arg0);
   }

   public int hashCode() {
      return hashCode-impl(this.rawValue);
   }

   public static boolean equals_impl/* $FF was: equals-impl*/(long arg0, Object other) {
      if (!(other instanceof Duration)) {
         return false;
      } else {
         long var3 = ((Duration)other).unbox-impl();
         return arg0 == var3;
      }
   }

   public boolean equals(Object other) {
      return equals-impl(this.rawValue, other);
   }

   // $FF: synthetic method
   private Duration(long rawValue) {
      this.rawValue = rawValue;
   }

   public static long constructor_impl/* $FF was: constructor-impl*/(long rawValue) {
      if (DurationJvmKt.getDurationAssertionsEnabled()) {
         if (isInNanos-impl(rawValue)) {
            long var4 = getValue-impl(rawValue);
            if (!(-4611686018426999999L <= var4 ? var4 < 4611686018427000000L : false)) {
               throw new AssertionError(getValue-impl(rawValue) + " ns is out of nanoseconds range");
            }
         } else {
            long var6 = getValue-impl(rawValue);
            if (!(-4611686018427387903L <= var6 ? var6 < 4611686018427387904L : false)) {
               throw new AssertionError(getValue-impl(rawValue) + " ms is out of milliseconds range");
            }

            var6 = getValue-impl(rawValue);
            if (-4611686018426L <= var6 ? var6 < 4611686018427L : false) {
               throw new AssertionError(getValue-impl(rawValue) + " ms is denormalized");
            }
         }
      }

      return rawValue;
   }

   // $FF: synthetic method
   public static final Duration box_impl/* $FF was: box-impl*/(long v) {
      return new Duration(v);
   }

   // $FF: synthetic method
   public final long unbox_impl/* $FF was: unbox-impl*/() {
      return this.rawValue;
   }

   public static final boolean equals_impl0/* $FF was: equals-impl0*/(long p1, long p2) {
      return p1 == p2;
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b&\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\f\u0010\u0010J\u001a\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0011H\u0007ø\u0001\u0000¢\u0006\u0004\b\f\u0010\u0012J\u001a\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\rJ\u001a\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0010J\u001a\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0011H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0012J\u001a\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\rJ\u001a\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0010J\u001a\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0011H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0012J\u001a\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\rJ\u001a\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0010J\u001a\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0011H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0012J\u001a\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\rJ\u001a\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u0010J\u001a\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0011H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u0012J\u001a\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\rJ\u001a\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u0010J\u001a\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0011H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u0012J\u0018\u0010 \u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u001dø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u0018\u0010\"\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u001dø\u0001\u0000¢\u0006\u0004\b!\u0010\u001fJ\u001a\u0010%\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0005\u001a\u00020\u001dø\u0001\u0000¢\u0006\u0004\b#\u0010$J\u001a\u0010'\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0005\u001a\u00020\u001dø\u0001\u0000¢\u0006\u0004\b&\u0010$J\u001a\u0010)\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b(\u0010\rJ\u001a\u0010)\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b(\u0010\u0010J\u001a\u0010)\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0011H\u0007ø\u0001\u0000¢\u0006\u0004\b(\u0010\u0012R\u001a\u0010*\u001a\u00020\u000b8\u0006ø\u0001\u0000¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u001d\u0010.\u001a\u00020\u000b8\u0000X\u0080\u0004ø\u0001\u0000¢\u0006\f\n\u0004\b.\u0010+\u001a\u0004\b/\u0010-R\u001a\u00100\u001a\u00020\u000b8\u0006ø\u0001\u0000¢\u0006\f\n\u0004\b0\u0010+\u001a\u0004\b1\u0010-R\"\u0010\u000e\u001a\u00020\u000b*\u00020\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b3\u00104\u001a\u0004\b2\u0010\rR\"\u0010\u000e\u001a\u00020\u000b*\u00020\u000f8Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b3\u00105\u001a\u0004\b2\u0010\u0010R\"\u0010\u000e\u001a\u00020\u000b*\u00020\u00118Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b3\u00106\u001a\u0004\b2\u0010\u0012R\"\u0010\u0014\u001a\u00020\u000b*\u00020\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b8\u00104\u001a\u0004\b7\u0010\rR\"\u0010\u0014\u001a\u00020\u000b*\u00020\u000f8Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b8\u00105\u001a\u0004\b7\u0010\u0010R\"\u0010\u0014\u001a\u00020\u000b*\u00020\u00118Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b8\u00106\u001a\u0004\b7\u0010\u0012R\"\u0010\u0016\u001a\u00020\u000b*\u00020\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b:\u00104\u001a\u0004\b9\u0010\rR\"\u0010\u0016\u001a\u00020\u000b*\u00020\u000f8Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b:\u00105\u001a\u0004\b9\u0010\u0010R\"\u0010\u0016\u001a\u00020\u000b*\u00020\u00118Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b:\u00106\u001a\u0004\b9\u0010\u0012R\"\u0010\u0018\u001a\u00020\u000b*\u00020\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b<\u00104\u001a\u0004\b;\u0010\rR\"\u0010\u0018\u001a\u00020\u000b*\u00020\u000f8Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b<\u00105\u001a\u0004\b;\u0010\u0010R\"\u0010\u0018\u001a\u00020\u000b*\u00020\u00118Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b<\u00106\u001a\u0004\b;\u0010\u0012R\"\u0010\u001a\u001a\u00020\u000b*\u00020\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b>\u00104\u001a\u0004\b=\u0010\rR\"\u0010\u001a\u001a\u00020\u000b*\u00020\u000f8Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b>\u00105\u001a\u0004\b=\u0010\u0010R\"\u0010\u001a\u001a\u00020\u000b*\u00020\u00118Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b>\u00106\u001a\u0004\b=\u0010\u0012R\"\u0010\u001c\u001a\u00020\u000b*\u00020\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b@\u00104\u001a\u0004\b?\u0010\rR\"\u0010\u001c\u001a\u00020\u000b*\u00020\u000f8Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b@\u00105\u001a\u0004\b?\u0010\u0010R\"\u0010\u001c\u001a\u00020\u000b*\u00020\u00118Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b@\u00106\u001a\u0004\b?\u0010\u0012R\"\u0010)\u001a\u00020\u000b*\u00020\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\bB\u00104\u001a\u0004\bA\u0010\rR\"\u0010)\u001a\u00020\u000b*\u00020\u000f8Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\bB\u00105\u001a\u0004\bA\u0010\u0010R\"\u0010)\u001a\u00020\u000b*\u00020\u00118Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\bB\u00106\u001a\u0004\bA\u0010\u0012\u0082\u0002\u0004\n\u0002\b!¨\u0006C"},
      d2 = {"Lkotlin/time/Duration$Companion;", "", "<init>", "()V", "", "value", "Lkotlin/time/DurationUnit;", "sourceUnit", "targetUnit", "convert", "(DLkotlin/time/DurationUnit;Lkotlin/time/DurationUnit;)D", "Lkotlin/time/Duration;", "days-UwyO8pc", "(D)J", "days", "", "(I)J", "", "(J)J", "hours-UwyO8pc", "hours", "microseconds-UwyO8pc", "microseconds", "milliseconds-UwyO8pc", "milliseconds", "minutes-UwyO8pc", "minutes", "nanoseconds-UwyO8pc", "nanoseconds", "", "parse-UwyO8pc", "(Ljava/lang/String;)J", "parse", "parseIsoString-UwyO8pc", "parseIsoString", "parseIsoStringOrNull-FghU774", "(Ljava/lang/String;)Lkotlin/time/Duration;", "parseIsoStringOrNull", "parseOrNull-FghU774", "parseOrNull", "seconds-UwyO8pc", "seconds", "INFINITE", "J", "getINFINITE-UwyO8pc", "()J", "NEG_INFINITE", "getNEG_INFINITE-UwyO8pc$kotlin_stdlib", "ZERO", "getZERO-UwyO8pc", "getDays-UwyO8pc", "getDays-UwyO8pc$annotations", "(D)V", "(I)V", "(J)V", "getHours-UwyO8pc", "getHours-UwyO8pc$annotations", "getMicroseconds-UwyO8pc", "getMicroseconds-UwyO8pc$annotations", "getMilliseconds-UwyO8pc", "getMilliseconds-UwyO8pc$annotations", "getMinutes-UwyO8pc", "getMinutes-UwyO8pc$annotations", "getNanoseconds-UwyO8pc", "getNanoseconds-UwyO8pc$annotations", "getSeconds-UwyO8pc", "getSeconds-UwyO8pc$annotations", "kotlin-stdlib"}
   )
   public static final class Companion {
      private Companion() {
      }

      public final long getZERO_UwyO8pc/* $FF was: getZERO-UwyO8pc*/() {
         return Duration.ZERO;
      }

      public final long getINFINITE_UwyO8pc/* $FF was: getINFINITE-UwyO8pc*/() {
         return Duration.INFINITE;
      }

      public final long getNEG_INFINITE_UwyO8pc$kotlin_stdlib/* $FF was: getNEG_INFINITE-UwyO8pc$kotlin_stdlib*/() {
         return Duration.NEG_INFINITE;
      }

      @ExperimentalTime
      public final double convert(double value, @NotNull DurationUnit sourceUnit, @NotNull DurationUnit targetUnit) {
         Intrinsics.checkNotNullParameter(sourceUnit, "sourceUnit");
         Intrinsics.checkNotNullParameter(targetUnit, "targetUnit");
         return DurationUnitKt.convertDurationUnit(value, sourceUnit, targetUnit);
      }

      private final long getNanoseconds_UwyO8pc/* $FF was: getNanoseconds-UwyO8pc*/(int $this$nanoseconds) {
         return DurationKt.toDuration($this$nanoseconds, DurationUnit.NANOSECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @InlineOnly
      public static void getNanoseconds_UwyO8pc$annotations/* $FF was: getNanoseconds-UwyO8pc$annotations*/(int <this>) {
      }

      private final long getNanoseconds_UwyO8pc/* $FF was: getNanoseconds-UwyO8pc*/(long $this$nanoseconds) {
         return DurationKt.toDuration($this$nanoseconds, DurationUnit.NANOSECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @InlineOnly
      public static void getNanoseconds_UwyO8pc$annotations/* $FF was: getNanoseconds-UwyO8pc$annotations*/(long <this>) {
      }

      private final long getNanoseconds_UwyO8pc/* $FF was: getNanoseconds-UwyO8pc*/(double $this$nanoseconds) {
         return DurationKt.toDuration($this$nanoseconds, DurationUnit.NANOSECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @InlineOnly
      public static void getNanoseconds_UwyO8pc$annotations/* $FF was: getNanoseconds-UwyO8pc$annotations*/(double <this>) {
      }

      private final long getMicroseconds_UwyO8pc/* $FF was: getMicroseconds-UwyO8pc*/(int $this$microseconds) {
         return DurationKt.toDuration($this$microseconds, DurationUnit.MICROSECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @InlineOnly
      public static void getMicroseconds_UwyO8pc$annotations/* $FF was: getMicroseconds-UwyO8pc$annotations*/(int <this>) {
      }

      private final long getMicroseconds_UwyO8pc/* $FF was: getMicroseconds-UwyO8pc*/(long $this$microseconds) {
         return DurationKt.toDuration($this$microseconds, DurationUnit.MICROSECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @InlineOnly
      public static void getMicroseconds_UwyO8pc$annotations/* $FF was: getMicroseconds-UwyO8pc$annotations*/(long <this>) {
      }

      private final long getMicroseconds_UwyO8pc/* $FF was: getMicroseconds-UwyO8pc*/(double $this$microseconds) {
         return DurationKt.toDuration($this$microseconds, DurationUnit.MICROSECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @InlineOnly
      public static void getMicroseconds_UwyO8pc$annotations/* $FF was: getMicroseconds-UwyO8pc$annotations*/(double <this>) {
      }

      private final long getMilliseconds_UwyO8pc/* $FF was: getMilliseconds-UwyO8pc*/(int $this$milliseconds) {
         return DurationKt.toDuration($this$milliseconds, DurationUnit.MILLISECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @InlineOnly
      public static void getMilliseconds_UwyO8pc$annotations/* $FF was: getMilliseconds-UwyO8pc$annotations*/(int <this>) {
      }

      private final long getMilliseconds_UwyO8pc/* $FF was: getMilliseconds-UwyO8pc*/(long $this$milliseconds) {
         return DurationKt.toDuration($this$milliseconds, DurationUnit.MILLISECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @InlineOnly
      public static void getMilliseconds_UwyO8pc$annotations/* $FF was: getMilliseconds-UwyO8pc$annotations*/(long <this>) {
      }

      private final long getMilliseconds_UwyO8pc/* $FF was: getMilliseconds-UwyO8pc*/(double $this$milliseconds) {
         return DurationKt.toDuration($this$milliseconds, DurationUnit.MILLISECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @InlineOnly
      public static void getMilliseconds_UwyO8pc$annotations/* $FF was: getMilliseconds-UwyO8pc$annotations*/(double <this>) {
      }

      private final long getSeconds_UwyO8pc/* $FF was: getSeconds-UwyO8pc*/(int $this$seconds) {
         return DurationKt.toDuration($this$seconds, DurationUnit.SECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @InlineOnly
      public static void getSeconds_UwyO8pc$annotations/* $FF was: getSeconds-UwyO8pc$annotations*/(int <this>) {
      }

      private final long getSeconds_UwyO8pc/* $FF was: getSeconds-UwyO8pc*/(long $this$seconds) {
         return DurationKt.toDuration($this$seconds, DurationUnit.SECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @InlineOnly
      public static void getSeconds_UwyO8pc$annotations/* $FF was: getSeconds-UwyO8pc$annotations*/(long <this>) {
      }

      private final long getSeconds_UwyO8pc/* $FF was: getSeconds-UwyO8pc*/(double $this$seconds) {
         return DurationKt.toDuration($this$seconds, DurationUnit.SECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @InlineOnly
      public static void getSeconds_UwyO8pc$annotations/* $FF was: getSeconds-UwyO8pc$annotations*/(double <this>) {
      }

      private final long getMinutes_UwyO8pc/* $FF was: getMinutes-UwyO8pc*/(int $this$minutes) {
         return DurationKt.toDuration($this$minutes, DurationUnit.MINUTES);
      }

      /** @deprecated */
      // $FF: synthetic method
      @InlineOnly
      public static void getMinutes_UwyO8pc$annotations/* $FF was: getMinutes-UwyO8pc$annotations*/(int <this>) {
      }

      private final long getMinutes_UwyO8pc/* $FF was: getMinutes-UwyO8pc*/(long $this$minutes) {
         return DurationKt.toDuration($this$minutes, DurationUnit.MINUTES);
      }

      /** @deprecated */
      // $FF: synthetic method
      @InlineOnly
      public static void getMinutes_UwyO8pc$annotations/* $FF was: getMinutes-UwyO8pc$annotations*/(long <this>) {
      }

      private final long getMinutes_UwyO8pc/* $FF was: getMinutes-UwyO8pc*/(double $this$minutes) {
         return DurationKt.toDuration($this$minutes, DurationUnit.MINUTES);
      }

      /** @deprecated */
      // $FF: synthetic method
      @InlineOnly
      public static void getMinutes_UwyO8pc$annotations/* $FF was: getMinutes-UwyO8pc$annotations*/(double <this>) {
      }

      private final long getHours_UwyO8pc/* $FF was: getHours-UwyO8pc*/(int $this$hours) {
         return DurationKt.toDuration($this$hours, DurationUnit.HOURS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @InlineOnly
      public static void getHours_UwyO8pc$annotations/* $FF was: getHours-UwyO8pc$annotations*/(int <this>) {
      }

      private final long getHours_UwyO8pc/* $FF was: getHours-UwyO8pc*/(long $this$hours) {
         return DurationKt.toDuration($this$hours, DurationUnit.HOURS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @InlineOnly
      public static void getHours_UwyO8pc$annotations/* $FF was: getHours-UwyO8pc$annotations*/(long <this>) {
      }

      private final long getHours_UwyO8pc/* $FF was: getHours-UwyO8pc*/(double $this$hours) {
         return DurationKt.toDuration($this$hours, DurationUnit.HOURS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @InlineOnly
      public static void getHours_UwyO8pc$annotations/* $FF was: getHours-UwyO8pc$annotations*/(double <this>) {
      }

      private final long getDays_UwyO8pc/* $FF was: getDays-UwyO8pc*/(int $this$days) {
         return DurationKt.toDuration($this$days, DurationUnit.DAYS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @InlineOnly
      public static void getDays_UwyO8pc$annotations/* $FF was: getDays-UwyO8pc$annotations*/(int <this>) {
      }

      private final long getDays_UwyO8pc/* $FF was: getDays-UwyO8pc*/(long $this$days) {
         return DurationKt.toDuration($this$days, DurationUnit.DAYS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @InlineOnly
      public static void getDays_UwyO8pc$annotations/* $FF was: getDays-UwyO8pc$annotations*/(long <this>) {
      }

      private final long getDays_UwyO8pc/* $FF was: getDays-UwyO8pc*/(double $this$days) {
         return DurationKt.toDuration($this$days, DurationUnit.DAYS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @InlineOnly
      public static void getDays_UwyO8pc$annotations/* $FF was: getDays-UwyO8pc$annotations*/(double <this>) {
      }

      /** @deprecated */
      // $FF: synthetic method
      @Deprecated(
         message = "Use 'Int.nanoseconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.nanoseconds",
   imports = {"kotlin.time.Duration.Companion.nanoseconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6",
         errorSince = "1.8",
         hiddenSince = "1.9"
      )
      @SinceKotlin(
         version = "1.5"
      )
      @ExperimentalTime
      public final long nanoseconds_UwyO8pc/* $FF was: nanoseconds-UwyO8pc*/(int value) {
         return DurationKt.toDuration(value, DurationUnit.NANOSECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @Deprecated(
         message = "Use 'Long.nanoseconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.nanoseconds",
   imports = {"kotlin.time.Duration.Companion.nanoseconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6",
         errorSince = "1.8",
         hiddenSince = "1.9"
      )
      @SinceKotlin(
         version = "1.5"
      )
      @ExperimentalTime
      public final long nanoseconds_UwyO8pc/* $FF was: nanoseconds-UwyO8pc*/(long value) {
         return DurationKt.toDuration(value, DurationUnit.NANOSECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @Deprecated(
         message = "Use 'Double.nanoseconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.nanoseconds",
   imports = {"kotlin.time.Duration.Companion.nanoseconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6",
         errorSince = "1.8",
         hiddenSince = "1.9"
      )
      @SinceKotlin(
         version = "1.5"
      )
      @ExperimentalTime
      public final long nanoseconds_UwyO8pc/* $FF was: nanoseconds-UwyO8pc*/(double value) {
         return DurationKt.toDuration(value, DurationUnit.NANOSECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @Deprecated(
         message = "Use 'Int.microseconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.microseconds",
   imports = {"kotlin.time.Duration.Companion.microseconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6",
         errorSince = "1.8",
         hiddenSince = "1.9"
      )
      @SinceKotlin(
         version = "1.5"
      )
      @ExperimentalTime
      public final long microseconds_UwyO8pc/* $FF was: microseconds-UwyO8pc*/(int value) {
         return DurationKt.toDuration(value, DurationUnit.MICROSECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @Deprecated(
         message = "Use 'Long.microseconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.microseconds",
   imports = {"kotlin.time.Duration.Companion.microseconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6",
         errorSince = "1.8",
         hiddenSince = "1.9"
      )
      @SinceKotlin(
         version = "1.5"
      )
      @ExperimentalTime
      public final long microseconds_UwyO8pc/* $FF was: microseconds-UwyO8pc*/(long value) {
         return DurationKt.toDuration(value, DurationUnit.MICROSECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @Deprecated(
         message = "Use 'Double.microseconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.microseconds",
   imports = {"kotlin.time.Duration.Companion.microseconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6",
         errorSince = "1.8",
         hiddenSince = "1.9"
      )
      @SinceKotlin(
         version = "1.5"
      )
      @ExperimentalTime
      public final long microseconds_UwyO8pc/* $FF was: microseconds-UwyO8pc*/(double value) {
         return DurationKt.toDuration(value, DurationUnit.MICROSECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @Deprecated(
         message = "Use 'Int.milliseconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.milliseconds",
   imports = {"kotlin.time.Duration.Companion.milliseconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6",
         errorSince = "1.8",
         hiddenSince = "1.9"
      )
      @SinceKotlin(
         version = "1.5"
      )
      @ExperimentalTime
      public final long milliseconds_UwyO8pc/* $FF was: milliseconds-UwyO8pc*/(int value) {
         return DurationKt.toDuration(value, DurationUnit.MILLISECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @Deprecated(
         message = "Use 'Long.milliseconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.milliseconds",
   imports = {"kotlin.time.Duration.Companion.milliseconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6",
         errorSince = "1.8",
         hiddenSince = "1.9"
      )
      @SinceKotlin(
         version = "1.5"
      )
      @ExperimentalTime
      public final long milliseconds_UwyO8pc/* $FF was: milliseconds-UwyO8pc*/(long value) {
         return DurationKt.toDuration(value, DurationUnit.MILLISECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @Deprecated(
         message = "Use 'Double.milliseconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.milliseconds",
   imports = {"kotlin.time.Duration.Companion.milliseconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6",
         errorSince = "1.8",
         hiddenSince = "1.9"
      )
      @SinceKotlin(
         version = "1.5"
      )
      @ExperimentalTime
      public final long milliseconds_UwyO8pc/* $FF was: milliseconds-UwyO8pc*/(double value) {
         return DurationKt.toDuration(value, DurationUnit.MILLISECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @Deprecated(
         message = "Use 'Int.seconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.seconds",
   imports = {"kotlin.time.Duration.Companion.seconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6",
         errorSince = "1.8",
         hiddenSince = "1.9"
      )
      @SinceKotlin(
         version = "1.5"
      )
      @ExperimentalTime
      public final long seconds_UwyO8pc/* $FF was: seconds-UwyO8pc*/(int value) {
         return DurationKt.toDuration(value, DurationUnit.SECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @Deprecated(
         message = "Use 'Long.seconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.seconds",
   imports = {"kotlin.time.Duration.Companion.seconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6",
         errorSince = "1.8",
         hiddenSince = "1.9"
      )
      @SinceKotlin(
         version = "1.5"
      )
      @ExperimentalTime
      public final long seconds_UwyO8pc/* $FF was: seconds-UwyO8pc*/(long value) {
         return DurationKt.toDuration(value, DurationUnit.SECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @Deprecated(
         message = "Use 'Double.seconds' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.seconds",
   imports = {"kotlin.time.Duration.Companion.seconds"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6",
         errorSince = "1.8",
         hiddenSince = "1.9"
      )
      @SinceKotlin(
         version = "1.5"
      )
      @ExperimentalTime
      public final long seconds_UwyO8pc/* $FF was: seconds-UwyO8pc*/(double value) {
         return DurationKt.toDuration(value, DurationUnit.SECONDS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @Deprecated(
         message = "Use 'Int.minutes' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.minutes",
   imports = {"kotlin.time.Duration.Companion.minutes"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6",
         errorSince = "1.8",
         hiddenSince = "1.9"
      )
      @SinceKotlin(
         version = "1.5"
      )
      @ExperimentalTime
      public final long minutes_UwyO8pc/* $FF was: minutes-UwyO8pc*/(int value) {
         return DurationKt.toDuration(value, DurationUnit.MINUTES);
      }

      /** @deprecated */
      // $FF: synthetic method
      @Deprecated(
         message = "Use 'Long.minutes' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.minutes",
   imports = {"kotlin.time.Duration.Companion.minutes"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6",
         errorSince = "1.8",
         hiddenSince = "1.9"
      )
      @SinceKotlin(
         version = "1.5"
      )
      @ExperimentalTime
      public final long minutes_UwyO8pc/* $FF was: minutes-UwyO8pc*/(long value) {
         return DurationKt.toDuration(value, DurationUnit.MINUTES);
      }

      /** @deprecated */
      // $FF: synthetic method
      @Deprecated(
         message = "Use 'Double.minutes' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.minutes",
   imports = {"kotlin.time.Duration.Companion.minutes"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6",
         errorSince = "1.8",
         hiddenSince = "1.9"
      )
      @SinceKotlin(
         version = "1.5"
      )
      @ExperimentalTime
      public final long minutes_UwyO8pc/* $FF was: minutes-UwyO8pc*/(double value) {
         return DurationKt.toDuration(value, DurationUnit.MINUTES);
      }

      /** @deprecated */
      // $FF: synthetic method
      @Deprecated(
         message = "Use 'Int.hours' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.hours",
   imports = {"kotlin.time.Duration.Companion.hours"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6",
         errorSince = "1.8",
         hiddenSince = "1.9"
      )
      @SinceKotlin(
         version = "1.5"
      )
      @ExperimentalTime
      public final long hours_UwyO8pc/* $FF was: hours-UwyO8pc*/(int value) {
         return DurationKt.toDuration(value, DurationUnit.HOURS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @Deprecated(
         message = "Use 'Long.hours' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.hours",
   imports = {"kotlin.time.Duration.Companion.hours"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6",
         errorSince = "1.8",
         hiddenSince = "1.9"
      )
      @SinceKotlin(
         version = "1.5"
      )
      @ExperimentalTime
      public final long hours_UwyO8pc/* $FF was: hours-UwyO8pc*/(long value) {
         return DurationKt.toDuration(value, DurationUnit.HOURS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @Deprecated(
         message = "Use 'Double.hours' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.hours",
   imports = {"kotlin.time.Duration.Companion.hours"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6",
         errorSince = "1.8",
         hiddenSince = "1.9"
      )
      @SinceKotlin(
         version = "1.5"
      )
      @ExperimentalTime
      public final long hours_UwyO8pc/* $FF was: hours-UwyO8pc*/(double value) {
         return DurationKt.toDuration(value, DurationUnit.HOURS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @Deprecated(
         message = "Use 'Int.days' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.days",
   imports = {"kotlin.time.Duration.Companion.days"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6",
         errorSince = "1.8",
         hiddenSince = "1.9"
      )
      @SinceKotlin(
         version = "1.5"
      )
      @ExperimentalTime
      public final long days_UwyO8pc/* $FF was: days-UwyO8pc*/(int value) {
         return DurationKt.toDuration(value, DurationUnit.DAYS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @Deprecated(
         message = "Use 'Long.days' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.days",
   imports = {"kotlin.time.Duration.Companion.days"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6",
         errorSince = "1.8",
         hiddenSince = "1.9"
      )
      @SinceKotlin(
         version = "1.5"
      )
      @ExperimentalTime
      public final long days_UwyO8pc/* $FF was: days-UwyO8pc*/(long value) {
         return DurationKt.toDuration(value, DurationUnit.DAYS);
      }

      /** @deprecated */
      // $FF: synthetic method
      @Deprecated(
         message = "Use 'Double.days' extension property from Duration.Companion instead.",
         replaceWith = @ReplaceWith(
   expression = "value.days",
   imports = {"kotlin.time.Duration.Companion.days"}
)
      )
      @DeprecatedSinceKotlin(
         warningSince = "1.6",
         errorSince = "1.8",
         hiddenSince = "1.9"
      )
      @SinceKotlin(
         version = "1.5"
      )
      @ExperimentalTime
      public final long days_UwyO8pc/* $FF was: days-UwyO8pc*/(double value) {
         return DurationKt.toDuration(value, DurationUnit.DAYS);
      }

      public final long parse_UwyO8pc/* $FF was: parse-UwyO8pc*/(@NotNull String value) {
         Intrinsics.checkNotNullParameter(value, "value");

         try {
            long var2 = DurationKt.access$parseDuration(value, false);
            return var2;
         } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid duration string format: '" + value + "'.", (Throwable)e);
         }
      }

      public final long parseIsoString_UwyO8pc/* $FF was: parseIsoString-UwyO8pc*/(@NotNull String value) {
         Intrinsics.checkNotNullParameter(value, "value");

         try {
            long var2 = DurationKt.access$parseDuration(value, true);
            return var2;
         } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid ISO duration string format: '" + value + "'.", (Throwable)e);
         }
      }

      @Nullable
      public final Duration parseOrNull_FghU774/* $FF was: parseOrNull-FghU774*/(@NotNull String value) {
         Intrinsics.checkNotNullParameter(value, "value");

         Duration var2;
         try {
            var2 = Duration.box-impl(DurationKt.access$parseDuration(value, false));
         } catch (IllegalArgumentException var4) {
            var2 = null;
         }

         return var2;
      }

      @Nullable
      public final Duration parseIsoStringOrNull_FghU774/* $FF was: parseIsoStringOrNull-FghU774*/(@NotNull String value) {
         Intrinsics.checkNotNullParameter(value, "value");

         Duration var2;
         try {
            var2 = Duration.box-impl(DurationKt.access$parseDuration(value, true));
         } catch (IllegalArgumentException var4) {
            var2 = null;
         }

         return var2;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
