package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0086\u0002¢\u0006\u0004\b\n\u0010\bJ\u000f\u0010\r\u001a\u00020\fH\u0014¢\u0006\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0011"},
   d2 = {"Lkotlin/time/TestTimeSource;", "Lkotlin/time/AbstractLongTimeSource;", "<init>", "()V", "Lkotlin/time/Duration;", "duration", "", "overflow-LRDsOJo", "(J)V", "overflow", "plusAssign-LRDsOJo", "plusAssign", "", "read", "()J", "reading", "J", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.9"
)
@WasExperimental(
   markerClass = {ExperimentalTime.class}
)
@SourceDebugExtension({"SMAP\nTimeSources.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TimeSources.kt\nkotlin/time/TestTimeSource\n+ 2 longSaturatedMath.kt\nkotlin/time/LongSaturatedMathKt\n*L\n1#1,199:1\n80#2:200\n80#2:201\n*S KotlinDebug\n*F\n+ 1 TimeSources.kt\nkotlin/time/TestTimeSource\n*L\n173#1:200\n180#1:201\n*E\n"})
public final class TestTimeSource extends AbstractLongTimeSource {
   private long reading;

   public TestTimeSource() {
      super(DurationUnit.NANOSECONDS);
      this.markNow();
   }

   protected long read() {
      return this.reading;
   }

   public final void plusAssign_LRDsOJo/* $FF was: plusAssign-LRDsOJo*/(long duration) {
      long longDelta = Duration.toLong-impl(duration, this.getUnit());
      int $i$f$isSaturated = 0;
      if ((longDelta - 1L | 1L) != Long.MAX_VALUE) {
         long newReading = this.reading + longDelta;
         if ((this.reading ^ longDelta) >= 0L && (this.reading ^ newReading) < 0L) {
            this.overflow-LRDsOJo(duration);
         }

         this.reading = newReading;
      } else {
         long half = Duration.div-UwyO8pc(duration, 2);
         long $this$isSaturated$iv = Duration.toLong-impl(half, this.getUnit());
         int $i$f$isSaturated = 0;
         if (($this$isSaturated$iv - 1L | 1L) != Long.MAX_VALUE) {
            $this$isSaturated$iv = this.reading;

            try {
               this.plusAssign-LRDsOJo(half);
               this.plusAssign-LRDsOJo(Duration.minus-LRDsOJo(duration, half));
            } catch (IllegalStateException e) {
               this.reading = $this$isSaturated$iv;
               throw e;
            }
         } else {
            this.overflow-LRDsOJo(duration);
         }
      }

   }

   private final void overflow_LRDsOJo/* $FF was: overflow-LRDsOJo*/(long duration) {
      throw new IllegalStateException("TestTimeSource will overflow if its reading " + this.reading + DurationUnitKt.shortName(this.getUnit()) + " is advanced by " + Duration.toString-impl(duration) + '.');
   }
}
