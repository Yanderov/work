package kotlin.time.jdk8;

import java.time.Duration;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlin.time.ExperimentalTime;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0017\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0014\u0010\u0005\u001a\u00020\u0000*\u00020\u0001H\u0087\b¢\u0006\u0004\b\u0005\u0010\u0006\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0007"},
   d2 = {"Lkotlin/time/Duration;", "Ljava/time/Duration;", "toJavaDuration-LRDsOJo", "(J)Ljava/time/Duration;", "toJavaDuration", "toKotlinDuration", "(Ljava/time/Duration;)J", "kotlin-stdlib-jdk8"},
   pn = ""
)
@JvmName(
   name = "DurationConversionsJDK8Kt"
)
@SourceDebugExtension({"SMAP\nDurationConversions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DurationConversions.kt\nkotlin/time/jdk8/DurationConversionsJDK8Kt\n+ 2 Duration.kt\nkotlin/time/Duration\n*L\n1#1,35:1\n731#2,2:36\n*S KotlinDebug\n*F\n+ 1 DurationConversions.kt\nkotlin/time/jdk8/DurationConversionsJDK8Kt\n*L\n35#1:36,2\n*E\n"})
public final class DurationConversionsJDK8Kt {
   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalTime.class}
   )
   @InlineOnly
   private static final long toKotlinDuration(Duration $this$toKotlinDuration) {
      Intrinsics.checkNotNullParameter($this$toKotlinDuration, "<this>");
      return kotlin.time.Duration.plus-LRDsOJo(DurationKt.toDuration($this$toKotlinDuration.getSeconds(), DurationUnit.SECONDS), DurationKt.toDuration($this$toKotlinDuration.getNano(), DurationUnit.NANOSECONDS));
   }

   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalTime.class}
   )
   @InlineOnly
   private static final Duration toJavaDuration_LRDsOJo/* $FF was: toJavaDuration-LRDsOJo*/(long $this$toJavaDuration_u2dLRDsOJo) {
      int var2 = 0;
      long var10000 = kotlin.time.Duration.getInWholeSeconds-impl($this$toJavaDuration_u2dLRDsOJo);
      int nanoseconds = kotlin.time.Duration.getNanosecondsComponent-impl($this$toJavaDuration_u2dLRDsOJo);
      long seconds = var10000;
      int var6 = 0;
      Duration var7 = Duration.ofSeconds(seconds, (long)nanoseconds);
      Intrinsics.checkNotNullExpressionValue(var7, "toComponents-impl(...)");
      return var7;
   }
}
