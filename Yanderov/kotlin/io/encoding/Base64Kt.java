package kotlin.io.encoding;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u001e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0006\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0001¢\u0006\u0004\b\u0003\u0010\u0004\"\u001a\u0010\u0006\u001a\u00020\u00058\u0002X\u0083\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u0012\u0004\b\b\u0010\t\"\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f\"\u001a\u0010\r\u001a\u00020\u00058\u0002X\u0083\u0004¢\u0006\f\n\u0004\b\r\u0010\u0007\u0012\u0004\b\u000e\u0010\t\"\u0014\u0010\u000f\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\f¨\u0006\u0010"},
   d2 = {"", "symbol", "", "isInMimeAlphabet", "(I)Z", "", "base64DecodeMap", "[I", "getBase64DecodeMap$annotations", "()V", "", "base64EncodeMap", "[B", "base64UrlDecodeMap", "getBase64UrlDecodeMap$annotations", "base64UrlEncodeMap", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nBase64.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Base64.kt\nkotlin/io/encoding/Base64Kt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,643:1\n13421#2,3:644\n13421#2,3:647\n*S KotlinDebug\n*F\n+ 1 Base64.kt\nkotlin/io/encoding/Base64Kt\n*L\n580#1:644,3\n597#1:647,3\n*E\n"})
public final class Base64Kt {
   @NotNull
   private static final byte[] base64EncodeMap;
   @NotNull
   private static final int[] base64DecodeMap;
   @NotNull
   private static final byte[] base64UrlEncodeMap;
   @NotNull
   private static final int[] base64UrlDecodeMap;

   /** @deprecated */
   // $FF: synthetic method
   @ExperimentalEncodingApi
   private static void getBase64DecodeMap$annotations() {
   }

   /** @deprecated */
   // $FF: synthetic method
   @ExperimentalEncodingApi
   private static void getBase64UrlDecodeMap$annotations() {
   }

   @SinceKotlin(
      version = "1.8"
   )
   @ExperimentalEncodingApi
   public static final boolean isInMimeAlphabet(int symbol) {
      return (0 <= symbol ? symbol < base64DecodeMap.length : false) && base64DecodeMap[symbol] != -1;
   }

   // $FF: synthetic method
   public static final byte[] access$getBase64UrlEncodeMap$p() {
      return base64UrlEncodeMap;
   }

   // $FF: synthetic method
   public static final byte[] access$getBase64EncodeMap$p() {
      return base64EncodeMap;
   }

   // $FF: synthetic method
   public static final int[] access$getBase64UrlDecodeMap$p() {
      return base64UrlDecodeMap;
   }

   // $FF: synthetic method
   public static final int[] access$getBase64DecodeMap$p() {
      return base64DecodeMap;
   }

   static {
      byte[] var0 = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
      base64EncodeMap = var0;
      int[] var12 = new int[256];
      int[] $this$base64DecodeMap_u24lambda_u241 = var12;
      int var2 = 0;
      ArraysKt.fill$default(var12, -1, 0, 0, 6, (Object)null);
      var12[61] = -2;
      byte[] $this$forEachIndexed$iv = base64EncodeMap;
      int $i$f$forEachIndexed = 0;
      int index$iv = 0;
      int var6 = 0;

      for(int var7 = $this$forEachIndexed$iv.length; var6 < var7; ++var6) {
         byte item$iv = $this$forEachIndexed$iv[var6];
         int index = index$iv++;
         int var11 = 0;
         $this$base64DecodeMap_u24lambda_u241[item$iv] = index;
      }

      base64DecodeMap = var12;
      byte[] var13 = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
      base64UrlEncodeMap = var13;
      int[] var14 = new int[256];
      $this$base64DecodeMap_u24lambda_u241 = var14;
      var2 = 0;
      ArraysKt.fill$default(var14, -1, 0, 0, 6, (Object)null);
      var14[61] = -2;
      $this$forEachIndexed$iv = base64UrlEncodeMap;
      $i$f$forEachIndexed = 0;
      index$iv = 0;
      var6 = 0;

      for(int var21 = $this$forEachIndexed$iv.length; var6 < var21; ++var6) {
         byte item$iv = $this$forEachIndexed$iv[var6];
         int index = index$iv++;
         int var24 = 0;
         $this$base64DecodeMap_u24lambda_u241[item$iv] = index;
      }

      base64UrlDecodeMap = var14;
   }
}
