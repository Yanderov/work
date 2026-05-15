package kotlin.io;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\r\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\u0017\u0010\u0003J\u000f\u0010\u0018\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\u0018\u0010\u0003J\u0017\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001e\u001a\u00020\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010*\u001a\u00020)8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b*\u0010+R\u0016\u0010,\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\u0018\u00100\u001a\u00060.j\u0002`/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u00101¨\u00062"},
   d2 = {"Lkotlin/io/LineReader;", "", "<init>", "()V", "", "compactBytes", "()I", "", "endOfInput", "decode", "(Z)I", "nBytes", "nChars", "decodeEndOfInput", "(II)I", "Ljava/io/InputStream;", "inputStream", "Ljava/nio/charset/Charset;", "charset", "", "readLine", "(Ljava/io/InputStream;Ljava/nio/charset/Charset;)Ljava/lang/String;", "", "resetAll", "trimStringBuilder", "updateCharset", "(Ljava/nio/charset/Charset;)V", "BUFFER_SIZE", "I", "Ljava/nio/ByteBuffer;", "byteBuf", "Ljava/nio/ByteBuffer;", "", "bytes", "[B", "Ljava/nio/CharBuffer;", "charBuf", "Ljava/nio/CharBuffer;", "", "chars", "[C", "Ljava/nio/charset/CharsetDecoder;", "decoder", "Ljava/nio/charset/CharsetDecoder;", "directEOL", "Z", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "sb", "Ljava/lang/StringBuilder;", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nConsole.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Console.kt\nkotlin/io/LineReader\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,299:1\n1#2:300\n*E\n"})
public final class LineReader {
   @NotNull
   public static final LineReader INSTANCE = new LineReader();
   private static final int BUFFER_SIZE = 32;
   private static CharsetDecoder decoder;
   private static boolean directEOL;
   @NotNull
   private static final byte[] bytes = new byte[32];
   @NotNull
   private static final char[] chars = new char[32];
   @NotNull
   private static final ByteBuffer byteBuf;
   @NotNull
   private static final CharBuffer charBuf;
   @NotNull
   private static final StringBuilder sb;

   private LineReader() {
   }

   @Nullable
   public final synchronized String readLine(@NotNull InputStream inputStream, @NotNull Charset charset) {
      label74: {
         Intrinsics.checkNotNullParameter(inputStream, "inputStream");
         Intrinsics.checkNotNullParameter(charset, "charset");
         if (decoder != null) {
            CharsetDecoder var10000 = decoder;
            if (var10000 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("decoder");
               var10000 = null;
            }

            if (Intrinsics.areEqual((Object)var10000.charset(), (Object)charset)) {
               break label74;
            }
         }

         this.updateCharset(charset);
      }

      int nBytes = 0;
      int nChars = 0;

      while(true) {
         int readByte = inputStream.read();
         if (readByte == -1) {
            if (((CharSequence)sb).length() == 0 && nBytes == 0 && nChars == 0) {
               return null;
            }

            nChars = this.decodeEndOfInput(nBytes, nChars);
            break;
         }

         bytes[nBytes++] = (byte)readByte;
         if (readByte == 10 || nBytes == 32 || !directEOL) {
            byteBuf.limit(nBytes);
            charBuf.position(nChars);
            nChars = this.decode(false);
            if (nChars > 0 && chars[nChars - 1] == '\n') {
               byteBuf.position(0);
               break;
            }

            nBytes = this.compactBytes();
         }
      }

      if (nChars > 0 && chars[nChars - 1] == '\n') {
         --nChars;
         if (nChars > 0 && chars[nChars - 1] == '\r') {
            --nChars;
         }
      }

      if (((CharSequence)sb).length() == 0) {
         return new String(chars, 0, nChars);
      } else {
         sb.append(chars, 0, nChars);
         String var7 = sb.toString();
         Intrinsics.checkNotNullExpressionValue(var7, "toString(...)");
         String result = var7;
         if (sb.length() > 32) {
            this.trimStringBuilder();
         }

         sb.setLength(0);
         return result;
      }
   }

   private final int decode(boolean endOfInput) {
      while(true) {
         CharsetDecoder var10000 = decoder;
         if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
            var10000 = null;
         }

         CoderResult var4 = var10000.decode(byteBuf, charBuf, endOfInput);
         Intrinsics.checkNotNullExpressionValue(var4, "decode(...)");
         CoderResult coderResult = var4;
         if (coderResult.isError()) {
            this.resetAll();
            coderResult.throwException();
         }

         int nChars = charBuf.position();
         if (!coderResult.isOverflow()) {
            return nChars;
         }

         sb.append(chars, 0, nChars - 1);
         charBuf.position(0);
         charBuf.limit(32);
         charBuf.put(chars[nChars - 1]);
      }
   }

   private final int compactBytes() {
      ByteBuffer $this$compactBytes_u24lambda_u241 = byteBuf;
      int var3 = 0;
      $this$compactBytes_u24lambda_u241.compact();
      int var4 = $this$compactBytes_u24lambda_u241.position();
      int var6 = 0;
      $this$compactBytes_u24lambda_u241.position(0);
      return var4;
   }

   private final int decodeEndOfInput(int nBytes, int nChars) {
      byteBuf.limit(nBytes);
      charBuf.position(nChars);
      int var3 = this.decode(true);
      int var5 = 0;
      CharsetDecoder var10000 = decoder;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("decoder");
         var10000 = null;
      }

      var10000.reset();
      byteBuf.position(0);
      return var3;
   }

   private final void updateCharset(Charset charset) {
      CharsetDecoder var10000 = charset.newDecoder();
      Intrinsics.checkNotNullExpressionValue(var10000, "newDecoder(...)");
      decoder = var10000;
      byteBuf.clear();
      charBuf.clear();
      byteBuf.put((byte)10);
      byteBuf.flip();
      var10000 = decoder;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("decoder");
         var10000 = null;
      }

      var10000.decode(byteBuf, charBuf, false);
      directEOL = charBuf.position() == 1 && charBuf.get(0) == '\n';
      this.resetAll();
   }

   private final void resetAll() {
      CharsetDecoder var10000 = decoder;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("decoder");
         var10000 = null;
      }

      var10000.reset();
      byteBuf.position(0);
      sb.setLength(0);
   }

   private final void trimStringBuilder() {
      sb.setLength(32);
      sb.trimToSize();
   }

   static {
      ByteBuffer var10000 = ByteBuffer.wrap(bytes);
      Intrinsics.checkNotNullExpressionValue(var10000, "wrap(...)");
      byteBuf = var10000;
      CharBuffer var0 = CharBuffer.wrap(chars);
      Intrinsics.checkNotNullExpressionValue(var0, "wrap(...)");
      charBuf = var0;
      sb = new StringBuilder();
   }
}
