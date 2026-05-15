package kotlin.time;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u00000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\b\u0010\t\"\u001a\u0010\u000b\u001a\u00020\n8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\" \u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00100\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012¨\u0006\u0013"},
   d2 = {"", "decimals", "Ljava/text/DecimalFormat;", "createFormatForDecimals", "(I)Ljava/text/DecimalFormat;", "", "value", "", "formatToExactDecimals", "(DI)Ljava/lang/String;", "", "durationAssertionsEnabled", "Z", "getDurationAssertionsEnabled", "()Z", "", "Ljava/lang/ThreadLocal;", "precisionFormats", "[Ljava/lang/ThreadLocal;", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nDurationJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DurationJvm.kt\nkotlin/time/DurationJvmKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,28:1\n1#2:29\n*E\n"})
public final class DurationJvmKt {
   private static final boolean durationAssertionsEnabled = Duration.class.desiredAssertionStatus();
   @NotNull
   private static final ThreadLocal[] precisionFormats;

   public static final boolean getDurationAssertionsEnabled() {
      return durationAssertionsEnabled;
   }

   private static final DecimalFormat createFormatForDecimals(int decimals) {
      DecimalFormat var1 = new DecimalFormat("0");
      int var3 = 0;
      if (decimals > 0) {
         var1.setMinimumFractionDigits(decimals);
      }

      var1.setRoundingMode(RoundingMode.HALF_UP);
      return var1;
   }

   @NotNull
   public static final String formatToExactDecimals(double value, int decimals) {
      DecimalFormat var9;
      if (decimals < precisionFormats.length) {
         ThreadLocal var4 = precisionFormats[decimals];
         Object var5 = var4.get();
         Object var10000;
         if (var5 == null) {
            int var6 = 0;
            DecimalFormat var8 = createFormatForDecimals(decimals);
            var4.set(var8);
            var10000 = var8;
         } else {
            Intrinsics.checkNotNull(var5);
            var10000 = var5;
         }

         var9 = (DecimalFormat)var10000;
      } else {
         var9 = createFormatForDecimals(decimals);
      }

      DecimalFormat format = var9;
      String var10 = format.format(value);
      Intrinsics.checkNotNullExpressionValue(var10, "format(...)");
      return var10;
   }

   static {
      int var0 = 0;

      ThreadLocal[] var1;
      for(var1 = new ThreadLocal[4]; var0 < 4; ++var0) {
         var1[var0] = new ThreadLocal();
      }

      precisionFormats = var1;
   }
}
