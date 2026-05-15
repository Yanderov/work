package kotlin.io.encoding;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0017\u0018\u0000 >2\u00020\u0001:\u0001>B\u0019\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0000¢\u0006\u0004\b\n\u0010\u000bJ'\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0000¢\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0018\u0010\u0019J'\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0000¢\u0006\u0004\b\u001b\u0010\u0019J)\u0010\u001d\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e¢\u0006\u0004\b\u001d\u0010\u001eJ)\u0010\u001d\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e¢\u0006\u0004\b\u001d\u0010\u0012J7\u0010 \u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0002¢\u0006\u0004\b \u0010!J;\u0010\"\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\b\b\u0002\u0010\u0015\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e¢\u0006\u0004\b\"\u0010!J;\u0010\"\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u00072\b\b\u0002\u0010\u0015\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e¢\u0006\u0004\b\"\u0010#J'\u0010$\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0002¢\u0006\u0004\b$\u0010%J)\u0010&\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e¢\u0006\u0004\b&\u0010'J;\u0010(\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\b\b\u0002\u0010\u0015\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e¢\u0006\u0004\b(\u0010!J7\u0010*\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0000¢\u0006\u0004\b)\u0010!J\u0017\u0010+\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000eH\u0002¢\u0006\u0004\b+\u0010,J?\u00100\u001a\u00028\u0000\"\f\b\u0000\u0010/*\u00060-j\u0002`.2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00028\u00002\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e¢\u0006\u0004\b0\u00101J)\u00102\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e¢\u0006\u0004\b2\u0010\u001eJ'\u00104\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0000¢\u0006\u0004\b3\u0010\u001eJ/\u00107\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u00072\u0006\u00105\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u00106\u001a\u00020\u000eH\u0002¢\u0006\u0004\b7\u00108J'\u00109\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0002¢\u0006\u0004\b9\u0010%R\u001a\u0010\u0004\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0004\u0010:\u001a\u0004\b;\u0010<R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010:\u001a\u0004\b=\u0010<¨\u0006?"},
   d2 = {"Lkotlin/io/encoding/Base64;", "", "", "isUrlSafe", "isMimeScheme", "<init>", "(ZZ)V", "", "source", "", "bytesToStringImpl$kotlin_stdlib", "([B)Ljava/lang/String;", "bytesToStringImpl", "", "", "startIndex", "endIndex", "charsToBytesImpl$kotlin_stdlib", "(Ljava/lang/CharSequence;II)[B", "charsToBytesImpl", "destinationSize", "destinationOffset", "capacityNeeded", "", "checkDestinationBounds", "(III)V", "sourceSize", "checkSourceBounds$kotlin_stdlib", "checkSourceBounds", "decode", "([BII)[B", "destination", "decodeImpl", "([B[BIII)I", "decodeIntoByteArray", "(Ljava/lang/CharSequence;[BIII)I", "decodeSize", "([BII)I", "encode", "([BII)Ljava/lang/String;", "encodeIntoByteArray", "encodeIntoByteArrayImpl$kotlin_stdlib", "encodeIntoByteArrayImpl", "encodeSize", "(I)I", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "A", "encodeToAppendable", "([BLjava/lang/Appendable;II)Ljava/lang/Appendable;", "encodeToByteArray", "encodeToByteArrayImpl$kotlin_stdlib", "encodeToByteArrayImpl", "padIndex", "byteStart", "handlePaddingSymbol", "([BIII)I", "skipIllegalSymbolsIfMime", "Z", "isMimeScheme$kotlin_stdlib", "()Z", "isUrlSafe$kotlin_stdlib", "Default", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.8"
)
@ExperimentalEncodingApi
public class Base64 {
   @NotNull
   public static final Default Default = new Default((DefaultConstructorMarker)null);
   private final boolean isUrlSafe;
   private final boolean isMimeScheme;
   private static final int bitsPerByte = 8;
   private static final int bitsPerSymbol = 6;
   public static final int bytesPerGroup = 3;
   public static final int symbolsPerGroup = 4;
   public static final byte padSymbol = 61;
   public static final int mimeLineLength = 76;
   private static final int mimeGroupsPerLine = 19;
   @NotNull
   private static final byte[] mimeLineSeparatorSymbols;
   @NotNull
   private static final Base64 UrlSafe;
   @NotNull
   private static final Base64 Mime;

   private Base64(boolean isUrlSafe, boolean isMimeScheme) {
      this.isUrlSafe = isUrlSafe;
      this.isMimeScheme = isMimeScheme;
      boolean var3 = !this.isUrlSafe || !this.isMimeScheme;
      if (!var3) {
         String var4 = "Failed requirement.";
         throw new IllegalArgumentException(var4.toString());
      }
   }

   public final boolean isUrlSafe$kotlin_stdlib() {
      return this.isUrlSafe;
   }

   public final boolean isMimeScheme$kotlin_stdlib() {
      return this.isMimeScheme;
   }

   @NotNull
   public final byte[] encodeToByteArray(@NotNull byte[] source, int startIndex, int endIndex) {
      Intrinsics.checkNotNullParameter(source, "source");
      return this.encodeToByteArrayImpl$kotlin_stdlib(source, startIndex, endIndex);
   }

   // $FF: synthetic method
   public static byte[] encodeToByteArray$default(Base64 var0, byte[] var1, int var2, int var3, int var4, Object var5) {
      if (var5 != null) {
         throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: encodeToByteArray");
      } else {
         if ((var4 & 2) != 0) {
            var2 = 0;
         }

         if ((var4 & 4) != 0) {
            var3 = var1.length;
         }

         return var0.encodeToByteArray(var1, var2, var3);
      }
   }

   public final int encodeIntoByteArray(@NotNull byte[] source, @NotNull byte[] destination, int destinationOffset, int startIndex, int endIndex) {
      Intrinsics.checkNotNullParameter(source, "source");
      Intrinsics.checkNotNullParameter(destination, "destination");
      return this.encodeIntoByteArrayImpl$kotlin_stdlib(source, destination, destinationOffset, startIndex, endIndex);
   }

   // $FF: synthetic method
   public static int encodeIntoByteArray$default(Base64 var0, byte[] var1, byte[] var2, int var3, int var4, int var5, int var6, Object var7) {
      if (var7 != null) {
         throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: encodeIntoByteArray");
      } else {
         if ((var6 & 4) != 0) {
            var3 = 0;
         }

         if ((var6 & 8) != 0) {
            var4 = 0;
         }

         if ((var6 & 16) != 0) {
            var5 = var1.length;
         }

         return var0.encodeIntoByteArray(var1, var2, var3, var4, var5);
      }
   }

   @NotNull
   public final String encode(@NotNull byte[] source, int startIndex, int endIndex) {
      Intrinsics.checkNotNullParameter(source, "source");
      byte[] var4 = this.encodeToByteArrayImpl$kotlin_stdlib(source, startIndex, endIndex);
      return new String(var4, Charsets.ISO_8859_1);
   }

   // $FF: synthetic method
   public static String encode$default(Base64 var0, byte[] var1, int var2, int var3, int var4, Object var5) {
      if (var5 != null) {
         throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: encode");
      } else {
         if ((var4 & 2) != 0) {
            var2 = 0;
         }

         if ((var4 & 4) != 0) {
            var3 = var1.length;
         }

         return var0.encode(var1, var2, var3);
      }
   }

   @NotNull
   public final Appendable encodeToAppendable(@NotNull byte[] source, @NotNull Appendable destination, int startIndex, int endIndex) {
      Intrinsics.checkNotNullParameter(source, "source");
      Intrinsics.checkNotNullParameter(destination, "destination");
      byte[] var6 = this.encodeToByteArrayImpl$kotlin_stdlib(source, startIndex, endIndex);
      String stringResult = new String(var6, Charsets.ISO_8859_1);
      destination.append((CharSequence)stringResult);
      return destination;
   }

   // $FF: synthetic method
   public static Appendable encodeToAppendable$default(Base64 var0, byte[] var1, Appendable var2, int var3, int var4, int var5, Object var6) {
      if (var6 != null) {
         throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: encodeToAppendable");
      } else {
         if ((var5 & 4) != 0) {
            var3 = 0;
         }

         if ((var5 & 8) != 0) {
            var4 = var1.length;
         }

         return var0.encodeToAppendable(var1, var2, var3, var4);
      }
   }

   @NotNull
   public final byte[] decode(@NotNull byte[] source, int startIndex, int endIndex) {
      Intrinsics.checkNotNullParameter(source, "source");
      this.checkSourceBounds$kotlin_stdlib(source.length, startIndex, endIndex);
      int decodeSize = this.decodeSize(source, startIndex, endIndex);
      byte[] destination = new byte[decodeSize];
      int bytesWritten = this.decodeImpl(source, destination, 0, startIndex, endIndex);
      boolean var7 = bytesWritten == destination.length;
      if (!var7) {
         String var8 = "Check failed.";
         throw new IllegalStateException(var8.toString());
      } else {
         return destination;
      }
   }

   // $FF: synthetic method
   public static byte[] decode$default(Base64 var0, byte[] var1, int var2, int var3, int var4, Object var5) {
      if (var5 != null) {
         throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decode");
      } else {
         if ((var4 & 2) != 0) {
            var2 = 0;
         }

         if ((var4 & 4) != 0) {
            var3 = var1.length;
         }

         return var0.decode(var1, var2, var3);
      }
   }

   public final int decodeIntoByteArray(@NotNull byte[] source, @NotNull byte[] destination, int destinationOffset, int startIndex, int endIndex) {
      Intrinsics.checkNotNullParameter(source, "source");
      Intrinsics.checkNotNullParameter(destination, "destination");
      this.checkSourceBounds$kotlin_stdlib(source.length, startIndex, endIndex);
      this.checkDestinationBounds(destination.length, destinationOffset, this.decodeSize(source, startIndex, endIndex));
      return this.decodeImpl(source, destination, destinationOffset, startIndex, endIndex);
   }

   // $FF: synthetic method
   public static int decodeIntoByteArray$default(Base64 var0, byte[] var1, byte[] var2, int var3, int var4, int var5, int var6, Object var7) {
      if (var7 != null) {
         throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decodeIntoByteArray");
      } else {
         if ((var6 & 4) != 0) {
            var3 = 0;
         }

         if ((var6 & 8) != 0) {
            var4 = 0;
         }

         if ((var6 & 16) != 0) {
            var5 = var1.length;
         }

         return var0.decodeIntoByteArray(var1, var2, var3, var4, var5);
      }
   }

   @NotNull
   public final byte[] decode(@NotNull CharSequence source, int startIndex, int endIndex) {
      Intrinsics.checkNotNullParameter(source, "source");
      byte[] var8;
      if (source instanceof String) {
         this.checkSourceBounds$kotlin_stdlib(source.length(), startIndex, endIndex);
         String var10000 = ((String)source).substring(startIndex, endIndex);
         Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
         String var6 = var10000;
         Charset var7 = Charsets.ISO_8859_1;
         Intrinsics.checkNotNull(var6, "null cannot be cast to non-null type java.lang.String");
         var8 = var6.getBytes(var7);
         Intrinsics.checkNotNullExpressionValue(var8, "getBytes(...)");
      } else {
         var8 = this.charsToBytesImpl$kotlin_stdlib(source, startIndex, endIndex);
      }

      byte[] byteSource = var8;
      return decode$default(this, (byte[])byteSource, 0, 0, 6, (Object)null);
   }

   // $FF: synthetic method
   public static byte[] decode$default(Base64 var0, CharSequence var1, int var2, int var3, int var4, Object var5) {
      if (var5 != null) {
         throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decode");
      } else {
         if ((var4 & 2) != 0) {
            var2 = 0;
         }

         if ((var4 & 4) != 0) {
            var3 = var1.length();
         }

         return var0.decode(var1, var2, var3);
      }
   }

   public final int decodeIntoByteArray(@NotNull CharSequence source, @NotNull byte[] destination, int destinationOffset, int startIndex, int endIndex) {
      Intrinsics.checkNotNullParameter(source, "source");
      Intrinsics.checkNotNullParameter(destination, "destination");
      byte[] var10;
      if (source instanceof String) {
         this.checkSourceBounds$kotlin_stdlib(source.length(), startIndex, endIndex);
         String var10000 = ((String)source).substring(startIndex, endIndex);
         Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
         String var8 = var10000;
         Charset var9 = Charsets.ISO_8859_1;
         Intrinsics.checkNotNull(var8, "null cannot be cast to non-null type java.lang.String");
         var10 = var8.getBytes(var9);
         Intrinsics.checkNotNullExpressionValue(var10, "getBytes(...)");
      } else {
         var10 = this.charsToBytesImpl$kotlin_stdlib(source, startIndex, endIndex);
      }

      byte[] byteSource = var10;
      return decodeIntoByteArray$default(this, (byte[])byteSource, destination, destinationOffset, 0, 0, 24, (Object)null);
   }

   // $FF: synthetic method
   public static int decodeIntoByteArray$default(Base64 var0, CharSequence var1, byte[] var2, int var3, int var4, int var5, int var6, Object var7) {
      if (var7 != null) {
         throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decodeIntoByteArray");
      } else {
         if ((var6 & 4) != 0) {
            var3 = 0;
         }

         if ((var6 & 8) != 0) {
            var4 = 0;
         }

         if ((var6 & 16) != 0) {
            var5 = var1.length();
         }

         return var0.decodeIntoByteArray(var1, var2, var3, var4, var5);
      }
   }

   @NotNull
   public final byte[] encodeToByteArrayImpl$kotlin_stdlib(@NotNull byte[] source, int startIndex, int endIndex) {
      Intrinsics.checkNotNullParameter(source, "source");
      this.checkSourceBounds$kotlin_stdlib(source.length, startIndex, endIndex);
      int encodeSize = this.encodeSize(endIndex - startIndex);
      byte[] destination = new byte[encodeSize];
      this.encodeIntoByteArrayImpl$kotlin_stdlib(source, destination, 0, startIndex, endIndex);
      return destination;
   }

   public final int encodeIntoByteArrayImpl$kotlin_stdlib(@NotNull byte[] source, @NotNull byte[] destination, int destinationOffset, int startIndex, int endIndex) {
      Intrinsics.checkNotNullParameter(source, "source");
      Intrinsics.checkNotNullParameter(destination, "destination");
      this.checkSourceBounds$kotlin_stdlib(source.length, startIndex, endIndex);
      this.checkDestinationBounds(destination.length, destinationOffset, this.encodeSize(endIndex - startIndex));
      byte[] encodeMap = this.isUrlSafe ? Base64Kt.access$getBase64UrlEncodeMap$p() : Base64Kt.access$getBase64EncodeMap$p();
      int sourceIndex = startIndex;
      int destinationIndex = destinationOffset;
      int groupsPerLine = this.isMimeScheme ? 19 : Integer.MAX_VALUE;

      while(sourceIndex + 2 < endIndex) {
         int groups = Math.min((endIndex - sourceIndex) / 3, groupsPerLine);

         for(int i = 0; i < groups; ++i) {
            int byte1 = source[sourceIndex++] & 255;
            int byte2 = source[sourceIndex++] & 255;
            int byte3 = source[sourceIndex++] & 255;
            int bits = byte1 << 16 | byte2 << 8 | byte3;
            destination[destinationIndex++] = encodeMap[bits >>> 18];
            destination[destinationIndex++] = encodeMap[bits >>> 12 & 63];
            destination[destinationIndex++] = encodeMap[bits >>> 6 & 63];
            destination[destinationIndex++] = encodeMap[bits & 63];
         }

         if (groups == groupsPerLine && sourceIndex != endIndex) {
            destination[destinationIndex++] = mimeLineSeparatorSymbols[0];
            destination[destinationIndex++] = mimeLineSeparatorSymbols[1];
         }
      }

      switch (endIndex - sourceIndex) {
         case 1:
            int byte1 = source[sourceIndex++] & 255;
            int bits = byte1 << 4;
            destination[destinationIndex++] = encodeMap[bits >>> 6];
            destination[destinationIndex++] = encodeMap[bits & 63];
            destination[destinationIndex++] = 61;
            destination[destinationIndex++] = 61;
            break;
         case 2:
            int byte1 = source[sourceIndex++] & 255;
            int byte2 = source[sourceIndex++] & 255;
            int bits = byte1 << 10 | byte2 << 2;
            destination[destinationIndex++] = encodeMap[bits >>> 12];
            destination[destinationIndex++] = encodeMap[bits >>> 6 & 63];
            destination[destinationIndex++] = encodeMap[bits & 63];
            destination[destinationIndex++] = 61;
      }

      boolean var29 = sourceIndex == endIndex;
      if (!var29) {
         String var32 = "Check failed.";
         throw new IllegalStateException(var32.toString());
      } else {
         return destinationIndex - destinationOffset;
      }
   }

   private final int encodeSize(int sourceSize) {
      int groups = (sourceSize + 3 - 1) / 3;
      int lineSeparators = this.isMimeScheme ? (groups - 1) / 19 : 0;
      int size = groups * 4 + lineSeparators * 2;
      if (size < 0) {
         throw new IllegalArgumentException("Input is too big");
      } else {
         return size;
      }
   }

   private final int decodeImpl(byte[] source, byte[] destination, int destinationOffset, int startIndex, int endIndex) {
      int[] decodeMap = this.isUrlSafe ? Base64Kt.access$getBase64UrlDecodeMap$p() : Base64Kt.access$getBase64DecodeMap$p();
      int payload = 0;
      int byteStart = -8;
      int sourceIndex = startIndex;
      int destinationIndex = destinationOffset;

      while(sourceIndex < endIndex) {
         if (byteStart == -8 && sourceIndex + 3 < endIndex) {
            int symbol1 = decodeMap[source[sourceIndex++] & 255];
            int symbol2 = decodeMap[source[sourceIndex++] & 255];
            int symbol3 = decodeMap[source[sourceIndex++] & 255];
            int symbol4 = decodeMap[source[sourceIndex++] & 255];
            int bits = symbol1 << 18 | symbol2 << 12 | symbol3 << 6 | symbol4;
            if (bits >= 0) {
               destination[destinationIndex++] = (byte)(bits >> 16);
               destination[destinationIndex++] = (byte)(bits >> 8);
               destination[destinationIndex++] = (byte)bits;
               continue;
            }

            sourceIndex -= 4;
         }

         int symbol = source[sourceIndex] & 255;
         int symbolBits = decodeMap[symbol];
         if (symbolBits < 0) {
            if (symbolBits == -2) {
               sourceIndex = this.handlePaddingSymbol(source, sourceIndex, endIndex, byteStart);
               break;
            }

            if (!this.isMimeScheme) {
               StringBuilder var31 = (new StringBuilder()).append("Invalid symbol '").append((char)symbol).append("'(");
               String var10003 = Integer.toString(symbol, CharsKt.checkRadix(8));
               Intrinsics.checkNotNullExpressionValue(var10003, "toString(...)");
               throw new IllegalArgumentException(var31.append(var10003).append(") at index ").append(sourceIndex).toString());
            }

            ++sourceIndex;
         } else {
            ++sourceIndex;
            payload = payload << 6 | symbolBits;
            byteStart += 6;
            if (byteStart >= 0) {
               destination[destinationIndex++] = (byte)(payload >>> byteStart);
               payload &= (1 << byteStart) - 1;
               byteStart -= 8;
            }
         }
      }

      if (byteStart == -2) {
         throw new IllegalArgumentException("The last unit of input does not have enough bits");
      } else {
         sourceIndex = this.skipIllegalSymbolsIfMime(source, sourceIndex, endIndex);
         if (sourceIndex < endIndex) {
            int symbol = source[sourceIndex] & 255;
            StringBuilder var32 = (new StringBuilder()).append("Symbol '").append((char)symbol).append("'(");
            String var33 = Integer.toString(symbol, CharsKt.checkRadix(8));
            Intrinsics.checkNotNullExpressionValue(var33, "toString(...)");
            throw new IllegalArgumentException(var32.append(var33).append(") at index ").append(sourceIndex - 1).append(" is prohibited after the pad character").toString());
         } else {
            return destinationIndex - destinationOffset;
         }
      }
   }

   private final int decodeSize(byte[] source, int startIndex, int endIndex) {
      int symbols = endIndex - startIndex;
      if (symbols == 0) {
         return 0;
      } else if (symbols == 1) {
         throw new IllegalArgumentException("Input should have at list 2 symbols for Base64 decoding, startIndex: " + startIndex + ", endIndex: " + endIndex);
      } else {
         if (this.isMimeScheme) {
            for(int index = startIndex; index < endIndex; ++index) {
               int symbol = source[index] & 255;
               int symbolBits = Base64Kt.access$getBase64DecodeMap$p()[symbol];
               if (symbolBits < 0) {
                  if (symbolBits == -2) {
                     symbols -= endIndex - index;
                     break;
                  }

                  --symbols;
               }
            }
         } else if (source[endIndex - 1] == 61) {
            --symbols;
            if (source[endIndex - 2] == 61) {
               --symbols;
            }
         }

         return (int)((long)symbols * (long)6 / (long)8);
      }
   }

   @NotNull
   public final byte[] charsToBytesImpl$kotlin_stdlib(@NotNull CharSequence source, int startIndex, int endIndex) {
      Intrinsics.checkNotNullParameter(source, "source");
      this.checkSourceBounds$kotlin_stdlib(source.length(), startIndex, endIndex);
      byte[] byteArray = new byte[endIndex - startIndex];
      int length = 0;

      for(int index = startIndex; index < endIndex; ++index) {
         int symbol = source.charAt(index);
         if (symbol <= 255) {
            byteArray[length++] = (byte)symbol;
         } else {
            byteArray[length++] = 63;
         }
      }

      return byteArray;
   }

   @NotNull
   public final String bytesToStringImpl$kotlin_stdlib(@NotNull byte[] source) {
      Intrinsics.checkNotNullParameter(source, "source");
      StringBuilder stringBuilder = new StringBuilder(source.length);
      int var3 = 0;

      for(int var4 = source.length; var3 < var4; ++var3) {
         byte var5 = source[var3];
         stringBuilder.append((char)var5);
      }

      String var10000 = stringBuilder.toString();
      Intrinsics.checkNotNullExpressionValue(var10000, "toString(...)");
      return var10000;
   }

   private final int handlePaddingSymbol(byte[] source, int padIndex, int endIndex, int byteStart) {
      int var10000;
      switch (byteStart) {
         case -8:
            throw new IllegalArgumentException("Redundant pad character at index " + padIndex);
         case -7:
         case -5:
         case -3:
         default:
            throw new IllegalStateException("Unreachable".toString());
         case -6:
            var10000 = padIndex + 1;
            break;
         case -4:
            int secondPadIndex = this.skipIllegalSymbolsIfMime(source, padIndex + 1, endIndex);
            if (secondPadIndex == endIndex || source[secondPadIndex] != 61) {
               throw new IllegalArgumentException("Missing one pad character at index " + secondPadIndex);
            }

            var10000 = secondPadIndex + 1;
            break;
         case -2:
            var10000 = padIndex + 1;
      }

      return var10000;
   }

   private final int skipIllegalSymbolsIfMime(byte[] source, int startIndex, int endIndex) {
      if (!this.isMimeScheme) {
         return startIndex;
      } else {
         int sourceIndex;
         for(sourceIndex = startIndex; sourceIndex < endIndex; ++sourceIndex) {
            int symbol = source[sourceIndex] & 255;
            if (Base64Kt.access$getBase64DecodeMap$p()[symbol] != -1) {
               return sourceIndex;
            }
         }

         return sourceIndex;
      }
   }

   public final void checkSourceBounds$kotlin_stdlib(int sourceSize, int startIndex, int endIndex) {
      AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, sourceSize);
   }

   private final void checkDestinationBounds(int destinationSize, int destinationOffset, int capacityNeeded) {
      if (destinationOffset >= 0 && destinationOffset <= destinationSize) {
         int destinationEndIndex = destinationOffset + capacityNeeded;
         if (destinationEndIndex < 0 || destinationEndIndex > destinationSize) {
            throw new IndexOutOfBoundsException("The destination array does not have enough capacity, destination offset: " + destinationOffset + ", destination size: " + destinationSize + ", capacity needed: " + capacityNeeded);
         }
      } else {
         throw new IndexOutOfBoundsException("destination offset: " + destinationOffset + ", destination size: " + destinationSize);
      }
   }

   // $FF: synthetic method
   public Base64(boolean isUrlSafe, boolean isMimeScheme, DefaultConstructorMarker $constructor_marker) {
      this(isUrlSafe, isMimeScheme);
   }

   static {
      byte[] var0 = new byte[]{13, 10};
      mimeLineSeparatorSymbols = var0;
      UrlSafe = new Base64(true, false);
      Mime = new Base64(false, true);
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\u00020\u00018\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\u00020\u00018\u0006¢\u0006\f\n\u0004\b\b\u0010\u0005\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\n8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\r\u0010\fR\u0014\u0010\u000e\u001a\u00020\n8\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u000e\u0010\fR\u0014\u0010\u000f\u001a\u00020\n8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000f\u0010\fR\u0014\u0010\u0010\u001a\u00020\n8\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0010\u0010\fR\u001a\u0010\u0012\u001a\u00020\u00118\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\n8\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0019\u0010\f¨\u0006\u001a"},
      d2 = {"Lkotlin/io/encoding/Base64$Default;", "Lkotlin/io/encoding/Base64;", "<init>", "()V", "Mime", "Lkotlin/io/encoding/Base64;", "getMime", "()Lkotlin/io/encoding/Base64;", "UrlSafe", "getUrlSafe", "", "bitsPerByte", "I", "bitsPerSymbol", "bytesPerGroup", "mimeGroupsPerLine", "mimeLineLength", "", "mimeLineSeparatorSymbols", "[B", "getMimeLineSeparatorSymbols$kotlin_stdlib", "()[B", "", "padSymbol", "B", "symbolsPerGroup", "kotlin-stdlib"}
   )
   public static final class Default extends Base64 {
      private Default() {
         super(false, false, (DefaultConstructorMarker)null);
      }

      @NotNull
      public final byte[] getMimeLineSeparatorSymbols$kotlin_stdlib() {
         return Base64.mimeLineSeparatorSymbols;
      }

      @NotNull
      public final Base64 getUrlSafe() {
         return Base64.UrlSafe;
      }

      @NotNull
      public final Base64 getMime() {
         return Base64.Mime;
      }

      // $FF: synthetic method
      public Default(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
