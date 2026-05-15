package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u0000 \u00052\u00020\u0001:\u0003\u0005\u0006\u0007J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\b"},
   d2 = {"Lkotlin/time/TimeSource;", "", "Lkotlin/time/TimeMark;", "markNow", "()Lkotlin/time/TimeMark;", "Companion", "Monotonic", "WithComparableMarks", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.9"
)
@WasExperimental(
   markerClass = {ExperimentalTime.class}
)
public interface TimeSource {
   @NotNull
   Companion Companion = TimeSource.Companion.$$INSTANCE;

   @NotNull
   TimeMark markNow();

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0007\u001a\u00020\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\n\u0082\u0002\u0004\n\u0002\b!¨\u0006\f"},
      d2 = {"Lkotlin/time/TimeSource$Monotonic;", "Lkotlin/time/TimeSource$WithComparableMarks;", "<init>", "()V", "Lkotlin/time/TimeSource$Monotonic$ValueTimeMark;", "markNow-z9LOYto", "()J", "markNow", "", "toString", "()Ljava/lang/String;", "ValueTimeMark", "kotlin-stdlib"}
   )
   public static final class Monotonic implements WithComparableMarks {
      @NotNull
      public static final Monotonic INSTANCE = new Monotonic();

      private Monotonic() {
      }

      public long markNow_z9LOYto/* $FF was: markNow-z9LOYto*/() {
         return MonotonicTimeSource.INSTANCE.markNow-z9LOYto();
      }

      @NotNull
      public String toString() {
         return MonotonicTimeSource.INSTANCE.toString();
      }

      @JvmInline
      @Metadata(
         mv = {1, 9, 0},
         k = 1,
         xi = 48,
         d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0087@\u0018\u00002\u00020\u0001B\u0015\b\u0000\u0012\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b\t\u0010\nJ\u0012\u0010\u000e\u001a\u00020\fH\u0016ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u0006J\u001a\u0010\u0013\u001a\u00020\u00102\b\u0010\u0007\u001a\u0004\u0018\u00010\u000fHÖ\u0003¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0016\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0018\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0017\u0010\u0015J\u0010\u0010\u001b\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u001b\u0010\u001e\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u0001H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u0018\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\fH\u0096\u0002¢\u0006\u0004\b \u0010!J\u0018\u0010\u001e\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b\"\u0010!J\u0018\u0010$\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\fH\u0096\u0002¢\u0006\u0004\b#\u0010!J\u0010\u0010(\u001a\u00020%HÖ\u0001¢\u0006\u0004\b&\u0010'R\u0018\u0010\u0004\u001a\u00060\u0002j\u0002`\u00038\u0000X\u0080\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010)\u0088\u0001\u0004\u0092\u0001\u00060\u0002j\u0002`\u0003\u0082\u0002\u0004\n\u0002\b!¨\u0006*"},
         d2 = {"Lkotlin/time/TimeSource$Monotonic$ValueTimeMark;", "Lkotlin/time/ComparableTimeMark;", "", "Lkotlin/time/ValueTimeMarkReading;", "reading", "constructor-impl", "(J)J", "other", "", "compareTo-6eNON_k", "(JJ)I", "compareTo", "Lkotlin/time/Duration;", "elapsedNow-UwyO8pc", "elapsedNow", "", "", "equals-impl", "(JLjava/lang/Object;)Z", "equals", "hasNotPassedNow-impl", "(J)Z", "hasNotPassedNow", "hasPassedNow-impl", "hasPassedNow", "hashCode-impl", "(J)I", "hashCode", "minus-UwyO8pc", "(JLkotlin/time/ComparableTimeMark;)J", "minus", "duration", "minus-LRDsOJo", "(JJ)J", "minus-6eNON_k", "plus-LRDsOJo", "plus", "", "toString-impl", "(J)Ljava/lang/String;", "toString", "J", "kotlin-stdlib"}
      )
      @SinceKotlin(
         version = "1.9"
      )
      @WasExperimental(
         markerClass = {ExperimentalTime.class}
      )
      public static final class ValueTimeMark implements ComparableTimeMark {
         private final long reading;

         public static long elapsedNow_UwyO8pc/* $FF was: elapsedNow-UwyO8pc*/(long arg0) {
            return MonotonicTimeSource.INSTANCE.elapsedFrom-6eNON_k(arg0);
         }

         public long elapsedNow_UwyO8pc/* $FF was: elapsedNow-UwyO8pc*/() {
            return elapsedNow-UwyO8pc(this.reading);
         }

         public static long plus_LRDsOJo/* $FF was: plus-LRDsOJo*/(long arg0, long duration) {
            return MonotonicTimeSource.INSTANCE.adjustReading-6QKq23U(arg0, duration);
         }

         public long plus_LRDsOJo/* $FF was: plus-LRDsOJo*/(long duration) {
            return plus-LRDsOJo(this.reading, duration);
         }

         public static long minus_LRDsOJo/* $FF was: minus-LRDsOJo*/(long arg0, long duration) {
            return MonotonicTimeSource.INSTANCE.adjustReading-6QKq23U(arg0, Duration.unaryMinus-UwyO8pc(duration));
         }

         public long minus_LRDsOJo/* $FF was: minus-LRDsOJo*/(long duration) {
            return minus-LRDsOJo(this.reading, duration);
         }

         public static boolean hasPassedNow_impl/* $FF was: hasPassedNow-impl*/(long arg0) {
            return !Duration.isNegative-impl(elapsedNow-UwyO8pc(arg0));
         }

         public boolean hasPassedNow() {
            return hasPassedNow-impl(this.reading);
         }

         public static boolean hasNotPassedNow_impl/* $FF was: hasNotPassedNow-impl*/(long arg0) {
            return Duration.isNegative-impl(elapsedNow-UwyO8pc(arg0));
         }

         public boolean hasNotPassedNow() {
            return hasNotPassedNow-impl(this.reading);
         }

         public static long minus_UwyO8pc/* $FF was: minus-UwyO8pc*/(long arg0, @NotNull ComparableTimeMark other) {
            Intrinsics.checkNotNullParameter(other, "other");
            if (!(other instanceof ValueTimeMark)) {
               throw new IllegalArgumentException("Subtracting or comparing time marks from different time sources is not possible: " + toString-impl(arg0) + " and " + other);
            } else {
               return minus-6eNON_k(arg0, ((ValueTimeMark)other).unbox-impl());
            }
         }

         public long minus_UwyO8pc/* $FF was: minus-UwyO8pc*/(@NotNull ComparableTimeMark other) {
            Intrinsics.checkNotNullParameter(other, "other");
            return minus-UwyO8pc(this.reading, other);
         }

         public static final long minus_6eNON_k/* $FF was: minus-6eNON_k*/(long arg0, long other) {
            return MonotonicTimeSource.INSTANCE.differenceBetween-fRLX17w(arg0, other);
         }

         public static final int compareTo_6eNON_k/* $FF was: compareTo-6eNON_k*/(long arg0, long other) {
            return Duration.compareTo-LRDsOJo(minus-6eNON_k(arg0, other), Duration.Companion.getZERO-UwyO8pc());
         }

         public static int compareTo_impl/* $FF was: compareTo-impl*/(long arg0, @NotNull ComparableTimeMark other) {
            Intrinsics.checkNotNullParameter(other, "other");
            return box-impl(arg0).compareTo(other);
         }

         public int compareTo(@NotNull ComparableTimeMark other) {
            return ComparableTimeMark.DefaultImpls.compareTo(this, other);
         }

         public static String toString_impl/* $FF was: toString-impl*/(long arg0) {
            return "ValueTimeMark(reading=" + arg0 + ')';
         }

         public String toString() {
            return toString-impl(this.reading);
         }

         public static int hashCode_impl/* $FF was: hashCode-impl*/(long arg0) {
            return Long.hashCode(arg0);
         }

         public int hashCode() {
            return hashCode-impl(this.reading);
         }

         public static boolean equals_impl/* $FF was: equals-impl*/(long arg0, Object other) {
            if (!(other instanceof ValueTimeMark)) {
               return false;
            } else {
               long var3 = ((ValueTimeMark)other).unbox-impl();
               return arg0 == var3;
            }
         }

         public boolean equals(Object other) {
            return equals-impl(this.reading, other);
         }

         // $FF: synthetic method
         private ValueTimeMark(long reading) {
            this.reading = reading;
         }

         public static long constructor_impl/* $FF was: constructor-impl*/(long reading) {
            return reading;
         }

         // $FF: synthetic method
         public static final ValueTimeMark box_impl/* $FF was: box-impl*/(long v) {
            return new ValueTimeMark(v);
         }

         // $FF: synthetic method
         public final long unbox_impl/* $FF was: unbox-impl*/() {
            return this.reading;
         }

         public static final boolean equals_impl0/* $FF was: equals-impl0*/(long p1, long p2) {
            return p1 == p2;
         }
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"},
      d2 = {"Lkotlin/time/TimeSource$Companion;", "", "<init>", "()V", "kotlin-stdlib"}
   )
   public static final class Companion {
      // $FF: synthetic field
      static final Companion $$INSTANCE = new Companion();

      private Companion() {
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"},
      d2 = {"Lkotlin/time/TimeSource$WithComparableMarks;", "Lkotlin/time/TimeSource;", "Lkotlin/time/ComparableTimeMark;", "markNow", "()Lkotlin/time/ComparableTimeMark;", "kotlin-stdlib"}
   )
   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalTime.class}
   )
   public interface WithComparableMarks extends TimeSource {
      @NotNull
      ComparableTimeMark markNow();
   }
}
