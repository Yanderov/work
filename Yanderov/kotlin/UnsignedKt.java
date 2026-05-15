package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u00006\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0014\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0001¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u0000H\u0001¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0018\u0010\t\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\bH\u0081\b¢\u0006\u0004\b\t\u0010\n\u001a\u0018\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\bH\u0081\b¢\u0006\u0004\b\u000b\u0010\f\u001a\u001f\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0001¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u001f\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\u0012\u0010\u0011\u001a\u001f\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\u0014\u0010\u0011\u001a\u0017\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\rH\u0001¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0018\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\rH\u0081\b¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u0018\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0001\u001a\u00020\rH\u0081\b¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u0018\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0001\u001a\u00020\rH\u0081\b¢\u0006\u0004\b\u001e\u0010\u001f\u001a \u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0001\u001a\u00020\r2\u0006\u0010 \u001a\u00020\rH\u0081\b¢\u0006\u0004\b\u001e\u0010!\u001a\u0018\u0010\"\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\rH\u0081\b¢\u0006\u0004\b\"\u0010\u001c\u001a\u001f\u0010#\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u001a2\u0006\u0010\u000f\u001a\u00020\u001aH\u0001¢\u0006\u0004\b#\u0010$\u001a\u001f\u0010'\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0001¢\u0006\u0004\b%\u0010&\u001a\u001f\u0010)\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0001¢\u0006\u0004\b(\u0010&\u001a\u0017\u0010*\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u001aH\u0001¢\u0006\u0004\b*\u0010+\u001a\u0018\u0010,\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u001aH\u0081\b¢\u0006\u0004\b,\u0010-\u001a\u0018\u0010.\u001a\u00020\u001d2\u0006\u0010\u0001\u001a\u00020\u001aH\u0081\b¢\u0006\u0004\b.\u0010/\u001a\u001f\u0010.\u001a\u00020\u001d2\u0006\u0010\u0001\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\rH\u0000¢\u0006\u0004\b.\u00100¨\u00061"},
   d2 = {"", "value", "Lkotlin/UInt;", "doubleToUInt", "(D)I", "Lkotlin/ULong;", "doubleToULong", "(D)J", "", "floatToUInt", "(F)I", "floatToULong", "(F)J", "", "v1", "v2", "uintCompare", "(II)I", "uintDivide-J1ME1BU", "uintDivide", "uintRemainder-J1ME1BU", "uintRemainder", "uintToDouble", "(I)D", "uintToFloat", "(I)F", "", "uintToLong", "(I)J", "", "uintToString", "(I)Ljava/lang/String;", "base", "(II)Ljava/lang/String;", "uintToULong", "ulongCompare", "(JJ)I", "ulongDivide-eb3DHEI", "(JJ)J", "ulongDivide", "ulongRemainder-eb3DHEI", "ulongRemainder", "ulongToDouble", "(J)D", "ulongToFloat", "(J)F", "ulongToString", "(J)Ljava/lang/String;", "(JI)Ljava/lang/String;", "kotlin-stdlib"}
)
@JvmName(
   name = "UnsignedKt"
)
public final class UnsignedKt {
   @PublishedApi
   public static final int uintRemainder_J1ME1BU/* $FF was: uintRemainder-J1ME1BU*/(int v1, int v2) {
      return UInt.constructor-impl((int)(((long)v1 & 4294967295L) % ((long)v2 & 4294967295L)));
   }

   @PublishedApi
   public static final int uintDivide_J1ME1BU/* $FF was: uintDivide-J1ME1BU*/(int v1, int v2) {
      return UInt.constructor-impl((int)(((long)v1 & 4294967295L) / ((long)v2 & 4294967295L)));
   }

   @PublishedApi
   public static final long ulongDivide_eb3DHEI/* $FF was: ulongDivide-eb3DHEI*/(long v1, long v2) {
      if (v2 < 0L) {
         return Long.compareUnsigned(v1, v2) < 0 ? ULong.constructor-impl(0L) : ULong.constructor-impl(1L);
      } else if (v1 >= 0L) {
         return ULong.constructor-impl(v1 / v2);
      } else {
         long quotient = (v1 >>> 1) / v2 << 1;
         long rem = v1 - quotient * v2;
         return ULong.constructor-impl(quotient + (long)(Long.compareUnsigned(ULong.constructor-impl(rem), ULong.constructor-impl(v2)) >= 0 ? 1 : 0));
      }
   }

   @PublishedApi
   public static final long ulongRemainder_eb3DHEI/* $FF was: ulongRemainder-eb3DHEI*/(long v1, long v2) {
      if (v2 < 0L) {
         return Long.compareUnsigned(v1, v2) < 0 ? v1 : ULong.constructor-impl(v1 - v2);
      } else if (v1 >= 0L) {
         return ULong.constructor-impl(v1 % v2);
      } else {
         long quotient = (v1 >>> 1) / v2 << 1;
         long rem = v1 - quotient * v2;
         return ULong.constructor-impl(rem - (Long.compareUnsigned(ULong.constructor-impl(rem), ULong.constructor-impl(v2)) >= 0 ? v2 : 0L));
      }
   }

   @PublishedApi
   public static final int uintCompare(int v1, int v2) {
      return Intrinsics.compare(v1 ^ Integer.MIN_VALUE, v2 ^ Integer.MIN_VALUE);
   }

   @PublishedApi
   public static final int ulongCompare(long v1, long v2) {
      return Intrinsics.compare(v1 ^ Long.MIN_VALUE, v2 ^ Long.MIN_VALUE);
   }

   @PublishedApi
   @InlineOnly
   private static final long uintToULong(int value) {
      return ULong.constructor-impl((long)value & 4294967295L);
   }

   @PublishedApi
   @InlineOnly
   private static final long uintToLong(int value) {
      return (long)value & 4294967295L;
   }

   @PublishedApi
   @InlineOnly
   private static final float uintToFloat(int value) {
      return (float)uintToDouble(value);
   }

   @PublishedApi
   @InlineOnly
   private static final int floatToUInt(float value) {
      return doubleToUInt((double)value);
   }

   @PublishedApi
   public static final double uintToDouble(int value) {
      return (double)(value & Integer.MAX_VALUE) + (double)(value >>> 31 << 30) * (double)2;
   }

   @PublishedApi
   public static final int doubleToUInt(double value) {
      return Double.isNaN(value) ? 0 : (value <= uintToDouble(0) ? 0 : (value >= uintToDouble(-1) ? -1 : (value <= (double)Integer.MAX_VALUE ? UInt.constructor-impl((int)value) : UInt.constructor-impl(UInt.constructor-impl((int)(value - (double)Integer.MAX_VALUE)) + UInt.constructor-impl(Integer.MAX_VALUE)))));
   }

   @PublishedApi
   @InlineOnly
   private static final float ulongToFloat(long value) {
      return (float)ulongToDouble(value);
   }

   @PublishedApi
   @InlineOnly
   private static final long floatToULong(float value) {
      return doubleToULong((double)value);
   }

   @PublishedApi
   public static final double ulongToDouble(long value) {
      return (double)(value >>> 11) * (double)2048 + (double)(value & 2047L);
   }

   @PublishedApi
   public static final long doubleToULong(double value) {
      return Double.isNaN(value) ? 0L : (value <= ulongToDouble(0L) ? 0L : (value >= ulongToDouble(-1L) ? -1L : (value < (double)Long.MAX_VALUE ? ULong.constructor-impl((long)value) : ULong.constructor-impl(ULong.constructor-impl((long)(value - (double)Long.MAX_VALUE)) + Long.MIN_VALUE))));
   }

   @InlineOnly
   private static final String uintToString(int value) {
      return String.valueOf((long)value & 4294967295L);
   }

   @InlineOnly
   private static final String uintToString(int value, int base) {
      return ulongToString((long)value & 4294967295L, base);
   }

   @InlineOnly
   private static final String ulongToString(long value) {
      return ulongToString(value, 10);
   }

   @NotNull
   public static final String ulongToString(long value, int base) {
      if (value >= 0L) {
         String var8 = Long.toString(value, CharsKt.checkRadix(base));
         Intrinsics.checkNotNullExpressionValue(var8, "toString(...)");
         return var8;
      } else {
         long quotient = (value >>> 1) / (long)base << 1;
         long rem = value - quotient * (long)base;
         if (rem >= (long)base) {
            rem -= (long)base;
            ++quotient;
         }

         StringBuilder var10000 = new StringBuilder();
         String var10001 = Long.toString(quotient, CharsKt.checkRadix(base));
         Intrinsics.checkNotNullExpressionValue(var10001, "toString(...)");
         var10000 = var10000.append(var10001);
         var10001 = Long.toString(rem, CharsKt.checkRadix(base));
         Intrinsics.checkNotNullExpressionValue(var10001, "toString(...)");
         return var10000.append(var10001).toString();
      }
   }
}
