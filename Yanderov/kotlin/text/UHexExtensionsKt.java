package kotlin.text;

import kotlin.ExperimentalStdlibApi;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000:\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\r\u001a\u001e\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001e\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b\u0007\u0010\b\u001a\u001e\u0010\n\u001a\u00020\t*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b\n\u0010\u000b\u001a\u001e\u0010\r\u001a\u00020\f*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b\r\u0010\u000e\u001a\u001e\u0010\u0010\u001a\u00020\u000f*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u001e\u0010\u0014\u001a\u00020\u0000*\u00020\u00032\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b\u0012\u0010\u0013\u001a2\u0010\u0014\u001a\u00020\u0000*\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00152\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u001e\u0010\u0014\u001a\u00020\u0000*\u00020\u00062\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u001e\u0010\u0014\u001a\u00020\u0000*\u00020\t2\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001e\u0010\u0014\u001a\u00020\u0000*\u00020\f2\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u001e\u0010\u0014\u001a\u00020\u0000*\u00020\u000f2\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b \u0010!¨\u0006\""},
   d2 = {"", "Lkotlin/text/HexFormat;", "format", "Lkotlin/UByte;", "hexToUByte", "(Ljava/lang/String;Lkotlin/text/HexFormat;)B", "Lkotlin/UByteArray;", "hexToUByteArray", "(Ljava/lang/String;Lkotlin/text/HexFormat;)[B", "Lkotlin/UInt;", "hexToUInt", "(Ljava/lang/String;Lkotlin/text/HexFormat;)I", "Lkotlin/ULong;", "hexToULong", "(Ljava/lang/String;Lkotlin/text/HexFormat;)J", "Lkotlin/UShort;", "hexToUShort", "(Ljava/lang/String;Lkotlin/text/HexFormat;)S", "toHexString-ZQbaR00", "(BLkotlin/text/HexFormat;)Ljava/lang/String;", "toHexString", "", "startIndex", "endIndex", "toHexString-lZCiFrA", "([BIILkotlin/text/HexFormat;)Ljava/lang/String;", "toHexString-zHuV2wU", "([BLkotlin/text/HexFormat;)Ljava/lang/String;", "toHexString-8M7LxHw", "(ILkotlin/text/HexFormat;)Ljava/lang/String;", "toHexString-8UJCm-I", "(JLkotlin/text/HexFormat;)Ljava/lang/String;", "toHexString-r3ox_E0", "(SLkotlin/text/HexFormat;)Ljava/lang/String;", "kotlin-stdlib"}
)
public final class UHexExtensionsKt {
   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final String toHexString_zHuV2wU/* $FF was: toHexString-zHuV2wU*/(byte[] $this$toHexString_u2dzHuV2wU, HexFormat format) {
      Intrinsics.checkNotNullParameter($this$toHexString_u2dzHuV2wU, "$this$toHexString");
      Intrinsics.checkNotNullParameter(format, "format");
      return HexExtensionsKt.toHexString($this$toHexString_u2dzHuV2wU, format);
   }

   // $FF: synthetic method
   static String toHexString_zHuV2wU$default/* $FF was: toHexString-zHuV2wU$default*/(byte[] $this$toHexString_u2dzHuV2wU_u24default, HexFormat format, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         format = HexFormat.Companion.getDefault();
      }

      Intrinsics.checkNotNullParameter($this$toHexString_u2dzHuV2wU_u24default, "$this$toHexString");
      Intrinsics.checkNotNullParameter(format, "format");
      return HexExtensionsKt.toHexString($this$toHexString_u2dzHuV2wU_u24default, format);
   }

   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final String toHexString_lZCiFrA/* $FF was: toHexString-lZCiFrA*/(byte[] $this$toHexString_u2dlZCiFrA, int startIndex, int endIndex, HexFormat format) {
      Intrinsics.checkNotNullParameter($this$toHexString_u2dlZCiFrA, "$this$toHexString");
      Intrinsics.checkNotNullParameter(format, "format");
      return HexExtensionsKt.toHexString($this$toHexString_u2dlZCiFrA, startIndex, endIndex, format);
   }

   // $FF: synthetic method
   static String toHexString_lZCiFrA$default/* $FF was: toHexString-lZCiFrA$default*/(byte[] $this$toHexString_u2dlZCiFrA_u24default, int startIndex, int endIndex, HexFormat format, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         startIndex = 0;
      }

      if ((var4 & 2) != 0) {
         endIndex = UByteArray.getSize-impl($this$toHexString_u2dlZCiFrA_u24default);
      }

      if ((var4 & 4) != 0) {
         format = HexFormat.Companion.getDefault();
      }

      Intrinsics.checkNotNullParameter($this$toHexString_u2dlZCiFrA_u24default, "$this$toHexString");
      Intrinsics.checkNotNullParameter(format, "format");
      return HexExtensionsKt.toHexString($this$toHexString_u2dlZCiFrA_u24default, startIndex, endIndex, format);
   }

   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final byte[] hexToUByteArray(String $this$hexToUByteArray, HexFormat format) {
      Intrinsics.checkNotNullParameter($this$hexToUByteArray, "<this>");
      Intrinsics.checkNotNullParameter(format, "format");
      return UByteArray.constructor-impl(HexExtensionsKt.hexToByteArray($this$hexToUByteArray, format));
   }

   // $FF: synthetic method
   static byte[] hexToUByteArray$default(String $this$hexToUByteArray_u24default, HexFormat format, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         format = HexFormat.Companion.getDefault();
      }

      Intrinsics.checkNotNullParameter($this$hexToUByteArray_u24default, "<this>");
      Intrinsics.checkNotNullParameter(format, "format");
      return UByteArray.constructor-impl(HexExtensionsKt.hexToByteArray($this$hexToUByteArray_u24default, format));
   }

   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   @InlineOnly
   private static final String toHexString_ZQbaR00/* $FF was: toHexString-ZQbaR00*/(byte $this$toHexString_u2dZQbaR00, HexFormat format) {
      Intrinsics.checkNotNullParameter(format, "format");
      return HexExtensionsKt.toHexString($this$toHexString_u2dZQbaR00, format);
   }

   // $FF: synthetic method
   static String toHexString_ZQbaR00$default/* $FF was: toHexString-ZQbaR00$default*/(byte $this$toHexString_u2dZQbaR00_u24default, HexFormat format, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         format = HexFormat.Companion.getDefault();
      }

      Intrinsics.checkNotNullParameter(format, "format");
      return HexExtensionsKt.toHexString($this$toHexString_u2dZQbaR00_u24default, format);
   }

   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   @InlineOnly
   private static final byte hexToUByte(String $this$hexToUByte, HexFormat format) {
      Intrinsics.checkNotNullParameter($this$hexToUByte, "<this>");
      Intrinsics.checkNotNullParameter(format, "format");
      return UByte.constructor-impl(HexExtensionsKt.hexToByte($this$hexToUByte, format));
   }

   // $FF: synthetic method
   static byte hexToUByte$default(String $this$hexToUByte_u24default, HexFormat format, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         format = HexFormat.Companion.getDefault();
      }

      Intrinsics.checkNotNullParameter($this$hexToUByte_u24default, "<this>");
      Intrinsics.checkNotNullParameter(format, "format");
      return UByte.constructor-impl(HexExtensionsKt.hexToByte($this$hexToUByte_u24default, format));
   }

   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   @InlineOnly
   private static final String toHexString_r3ox_E0/* $FF was: toHexString-r3ox_E0*/(short $this$toHexString_u2dr3ox_E0, HexFormat format) {
      Intrinsics.checkNotNullParameter(format, "format");
      return HexExtensionsKt.toHexString($this$toHexString_u2dr3ox_E0, format);
   }

   // $FF: synthetic method
   static String toHexString_r3ox_E0$default/* $FF was: toHexString-r3ox_E0$default*/(short $this$toHexString_u2dr3ox_E0_u24default, HexFormat format, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         format = HexFormat.Companion.getDefault();
      }

      Intrinsics.checkNotNullParameter(format, "format");
      return HexExtensionsKt.toHexString($this$toHexString_u2dr3ox_E0_u24default, format);
   }

   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   @InlineOnly
   private static final short hexToUShort(String $this$hexToUShort, HexFormat format) {
      Intrinsics.checkNotNullParameter($this$hexToUShort, "<this>");
      Intrinsics.checkNotNullParameter(format, "format");
      return UShort.constructor-impl(HexExtensionsKt.hexToShort($this$hexToUShort, format));
   }

   // $FF: synthetic method
   static short hexToUShort$default(String $this$hexToUShort_u24default, HexFormat format, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         format = HexFormat.Companion.getDefault();
      }

      Intrinsics.checkNotNullParameter($this$hexToUShort_u24default, "<this>");
      Intrinsics.checkNotNullParameter(format, "format");
      return UShort.constructor-impl(HexExtensionsKt.hexToShort($this$hexToUShort_u24default, format));
   }

   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   @InlineOnly
   private static final String toHexString_8M7LxHw/* $FF was: toHexString-8M7LxHw*/(int $this$toHexString_u2d8M7LxHw, HexFormat format) {
      Intrinsics.checkNotNullParameter(format, "format");
      return HexExtensionsKt.toHexString($this$toHexString_u2d8M7LxHw, format);
   }

   // $FF: synthetic method
   static String toHexString_8M7LxHw$default/* $FF was: toHexString-8M7LxHw$default*/(int $this$toHexString_u2d8M7LxHw_u24default, HexFormat format, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         format = HexFormat.Companion.getDefault();
      }

      Intrinsics.checkNotNullParameter(format, "format");
      return HexExtensionsKt.toHexString($this$toHexString_u2d8M7LxHw_u24default, format);
   }

   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   @InlineOnly
   private static final int hexToUInt(String $this$hexToUInt, HexFormat format) {
      Intrinsics.checkNotNullParameter($this$hexToUInt, "<this>");
      Intrinsics.checkNotNullParameter(format, "format");
      return UInt.constructor-impl(HexExtensionsKt.hexToInt($this$hexToUInt, format));
   }

   // $FF: synthetic method
   static int hexToUInt$default(String $this$hexToUInt_u24default, HexFormat format, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         format = HexFormat.Companion.getDefault();
      }

      Intrinsics.checkNotNullParameter($this$hexToUInt_u24default, "<this>");
      Intrinsics.checkNotNullParameter(format, "format");
      return UInt.constructor-impl(HexExtensionsKt.hexToInt($this$hexToUInt_u24default, format));
   }

   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   @InlineOnly
   private static final String toHexString_8UJCm_I/* $FF was: toHexString-8UJCm-I*/(long $this$toHexString_u2d8UJCm_u2dI, HexFormat format) {
      Intrinsics.checkNotNullParameter(format, "format");
      return HexExtensionsKt.toHexString($this$toHexString_u2d8UJCm_u2dI, format);
   }

   // $FF: synthetic method
   static String toHexString_8UJCm_I$default/* $FF was: toHexString-8UJCm-I$default*/(long $this$toHexString_u2d8UJCm_u2dI_u24default, HexFormat format, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         format = HexFormat.Companion.getDefault();
      }

      Intrinsics.checkNotNullParameter(format, "format");
      return HexExtensionsKt.toHexString($this$toHexString_u2d8UJCm_u2dI_u24default, format);
   }

   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   @InlineOnly
   private static final long hexToULong(String $this$hexToULong, HexFormat format) {
      Intrinsics.checkNotNullParameter($this$hexToULong, "<this>");
      Intrinsics.checkNotNullParameter(format, "format");
      return ULong.constructor-impl(HexExtensionsKt.hexToLong($this$hexToULong, format));
   }

   // $FF: synthetic method
   static long hexToULong$default(String $this$hexToULong_u24default, HexFormat format, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         format = HexFormat.Companion.getDefault();
      }

      Intrinsics.checkNotNullParameter($this$hexToULong_u24default, "<this>");
      Intrinsics.checkNotNullParameter(format, "format");
      return ULong.constructor-impl(HexExtensionsKt.hexToLong($this$hexToULong_u24default, format));
   }
}
