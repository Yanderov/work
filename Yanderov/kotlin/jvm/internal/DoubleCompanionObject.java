package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\u0006J\r\u0010\b\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\u0006J\r\u0010\t\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\u0006J\r\u0010\n\u001a\u00020\u0004¢\u0006\u0004\b\n\u0010\u0006R\u001a\u0010\u000b\u001a\u00020\u00048\u0006X\u0087T¢\u0006\f\n\u0004\b\u000b\u0010\f\u0012\u0004\b\r\u0010\u0003R\u001a\u0010\u000e\u001a\u00020\u00048\u0006X\u0087T¢\u0006\f\n\u0004\b\u000e\u0010\f\u0012\u0004\b\u000f\u0010\u0003R\u001a\u0010\u0010\u001a\u00020\u00048\u0006X\u0087T¢\u0006\f\n\u0004\b\u0010\u0010\f\u0012\u0004\b\u0011\u0010\u0003R\u001a\u0010\u0012\u001a\u00020\u00048\u0006X\u0087T¢\u0006\f\n\u0004\b\u0012\u0010\f\u0012\u0004\b\u0013\u0010\u0003R\u001a\u0010\u0014\u001a\u00020\u00048\u0006X\u0087T¢\u0006\f\n\u0004\b\u0014\u0010\f\u0012\u0004\b\u0015\u0010\u0003R\u001a\u0010\u0017\u001a\u00020\u00168\u0006X\u0087T¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u0012\u0004\b\u0019\u0010\u0003R\u001a\u0010\u001a\u001a\u00020\u00168\u0006X\u0087T¢\u0006\f\n\u0004\b\u001a\u0010\u0018\u0012\u0004\b\u001b\u0010\u0003¨\u0006\u001c"},
   d2 = {"Lkotlin/jvm/internal/DoubleCompanionObject;", "", "<init>", "()V", "", "getMAX_VALUE", "()D", "getMIN_VALUE", "getNEGATIVE_INFINITY", "getNaN", "getPOSITIVE_INFINITY", "MAX_VALUE", "D", "getMAX_VALUE$annotations", "MIN_VALUE", "getMIN_VALUE$annotations", "NEGATIVE_INFINITY", "getNEGATIVE_INFINITY$annotations", "NaN", "getNaN$annotations", "POSITIVE_INFINITY", "getPOSITIVE_INFINITY$annotations", "", "SIZE_BITS", "I", "getSIZE_BITS$annotations", "SIZE_BYTES", "getSIZE_BYTES$annotations", "kotlin-stdlib"}
)
public final class DoubleCompanionObject {
   @NotNull
   public static final DoubleCompanionObject INSTANCE = new DoubleCompanionObject();
   public static final double MIN_VALUE = Double.MIN_VALUE;
   public static final double MAX_VALUE = Double.MAX_VALUE;
   public static final double POSITIVE_INFINITY = Double.POSITIVE_INFINITY;
   public static final double NEGATIVE_INFINITY = Double.NEGATIVE_INFINITY;
   public static final double NaN = Double.NaN;
   public static final int SIZE_BYTES = 8;
   public static final int SIZE_BITS = 64;

   private DoubleCompanionObject() {
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.4"
   )
   public static void getMIN_VALUE$annotations() {
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.4"
   )
   public static void getMAX_VALUE$annotations() {
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.4"
   )
   public static void getPOSITIVE_INFINITY$annotations() {
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.4"
   )
   public static void getNEGATIVE_INFINITY$annotations() {
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.4"
   )
   public static void getNaN$annotations() {
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.4"
   )
   public static void getSIZE_BYTES$annotations() {
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.4"
   )
   public static void getSIZE_BITS$annotations() {
   }

   public final double getMIN_VALUE() {
      return Double.MIN_VALUE;
   }

   public final double getMAX_VALUE() {
      return Double.MAX_VALUE;
   }

   public final double getPOSITIVE_INFINITY() {
      return Double.POSITIVE_INFINITY;
   }

   public final double getNEGATIVE_INFINITY() {
      return Double.NEGATIVE_INFINITY;
   }

   public final double getNaN() {
      return Double.NaN;
   }
}
