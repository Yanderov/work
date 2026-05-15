package kotlin.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000\u0098\u0001\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001f\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0019\u0010\u000b\u001a\u00020\n*\u00020\u00072\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\f\u001a#\u0010\u0011\u001a\u00020\n*\u00020\u00072\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012\u001a(\u0010\u0015\u001a\u00020\u0014*\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0015\u0010\u0016\u001a(\u0010\u0018\u001a\u00020\u0017*\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0018\u0010\u0019\u001aI\u0010 \u001a\u00020\n*\u00020\u000726\u0010\u001f\u001a2\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\n0\u001a¢\u0006\u0004\b \u0010!\u001aQ\u0010 \u001a\u00020\n*\u00020\u00072\u0006\u0010\"\u001a\u00020\u000026\u0010\u001f\u001a2\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\n0\u001a¢\u0006\u0004\b \u0010#\u001a>\u0010&\u001a\u00020\n*\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u000f2!\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\n0$¢\u0006\u0004\b&\u0010'\u001a\u0014\u0010)\u001a\u00020(*\u00020\u0007H\u0087\b¢\u0006\u0004\b)\u0010*\u001a\u001b\u0010,\u001a\n +*\u0004\u0018\u00010\u00020\u0002*\u00020\u000fH\u0000¢\u0006\u0004\b,\u0010-\u001a\u0014\u0010/\u001a\u00020.*\u00020\u0007H\u0087\b¢\u0006\u0004\b/\u00100\u001a\u001e\u00102\u001a\u000201*\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u000fH\u0087\b¢\u0006\u0004\b2\u00103\u001a\u0011\u00104\u001a\u00020\b*\u00020\u0007¢\u0006\u0004\b4\u00105\u001a!\u00107\u001a\b\u0012\u0004\u0012\u00020\r06*\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b7\u00108\u001a\u001b\u00109\u001a\u00020\r*\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b9\u0010:\u001a\u001e\u0010<\u001a\u00020;*\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u000fH\u0087\b¢\u0006\u0004\b<\u0010=\u001aN\u0010A\u001a\u00028\u0000\"\u0004\b\u0000\u0010>*\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\u0018\u0010@\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0?\u0012\u0004\u0012\u00028\u00000$H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0004\bA\u0010B\u001a\u0019\u0010C\u001a\u00020\n*\u00020\u00072\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\bC\u0010\f\u001a#\u0010D\u001a\u00020\n*\u00020\u00072\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\bD\u0010\u0012\u001a#\u0010F\u001a\u00020\n*\u00020E2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0000¢\u0006\u0004\bF\u0010G\u001a\u001e\u0010I\u001a\u00020H*\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u000fH\u0087\b¢\u0006\u0004\bI\u0010J\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006K"},
   d2 = {"", "chunkSize", "Ljava/nio/charset/CharsetEncoder;", "encoder", "Ljava/nio/ByteBuffer;", "byteBufferForEncoding", "(ILjava/nio/charset/CharsetEncoder;)Ljava/nio/ByteBuffer;", "Ljava/io/File;", "", "array", "", "appendBytes", "(Ljava/io/File;[B)V", "", "text", "Ljava/nio/charset/Charset;", "charset", "appendText", "(Ljava/io/File;Ljava/lang/String;Ljava/nio/charset/Charset;)V", "bufferSize", "Ljava/io/BufferedReader;", "bufferedReader", "(Ljava/io/File;Ljava/nio/charset/Charset;I)Ljava/io/BufferedReader;", "Ljava/io/BufferedWriter;", "bufferedWriter", "(Ljava/io/File;Ljava/nio/charset/Charset;I)Ljava/io/BufferedWriter;", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "buffer", "bytesRead", "action", "forEachBlock", "(Ljava/io/File;Lkotlin/jvm/functions/Function2;)V", "blockSize", "(Ljava/io/File;ILkotlin/jvm/functions/Function2;)V", "Lkotlin/Function1;", "line", "forEachLine", "(Ljava/io/File;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)V", "Ljava/io/FileInputStream;", "inputStream", "(Ljava/io/File;)Ljava/io/FileInputStream;", "kotlin.jvm.PlatformType", "newReplaceEncoder", "(Ljava/nio/charset/Charset;)Ljava/nio/charset/CharsetEncoder;", "Ljava/io/FileOutputStream;", "outputStream", "(Ljava/io/File;)Ljava/io/FileOutputStream;", "Ljava/io/PrintWriter;", "printWriter", "(Ljava/io/File;Ljava/nio/charset/Charset;)Ljava/io/PrintWriter;", "readBytes", "(Ljava/io/File;)[B", "", "readLines", "(Ljava/io/File;Ljava/nio/charset/Charset;)Ljava/util/List;", "readText", "(Ljava/io/File;Ljava/nio/charset/Charset;)Ljava/lang/String;", "Ljava/io/InputStreamReader;", "reader", "(Ljava/io/File;Ljava/nio/charset/Charset;)Ljava/io/InputStreamReader;", "T", "Lkotlin/sequences/Sequence;", "block", "useLines", "(Ljava/io/File;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writeBytes", "writeText", "Ljava/io/OutputStream;", "writeTextImpl", "(Ljava/io/OutputStream;Ljava/lang/String;Ljava/nio/charset/Charset;)V", "Ljava/io/OutputStreamWriter;", "writer", "(Ljava/io/File;Ljava/nio/charset/Charset;)Ljava/io/OutputStreamWriter;", "kotlin-stdlib"},
   xs = "kotlin/io/FilesKt"
)
@SourceDebugExtension({"SMAP\nFileReadWrite.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileReadWrite.kt\nkotlin/io/FilesKt__FileReadWriteKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,290:1\n1#2:291\n*E\n"})
class FilesKt__FileReadWriteKt extends FilesKt__FilePathComponentsKt {
   @InlineOnly
   private static final InputStreamReader reader(File $this$reader, Charset charset) {
      Intrinsics.checkNotNullParameter($this$reader, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      return new InputStreamReader((InputStream)(new FileInputStream($this$reader)), charset);
   }

   // $FF: synthetic method
   static InputStreamReader reader$default(File $this$reader_u24default, Charset charset, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         charset = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter($this$reader_u24default, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      return new InputStreamReader((InputStream)(new FileInputStream($this$reader_u24default)), charset);
   }

   @InlineOnly
   private static final BufferedReader bufferedReader(File $this$bufferedReader, Charset charset, int bufferSize) {
      Intrinsics.checkNotNullParameter($this$bufferedReader, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Reader var3 = (Reader)(new InputStreamReader((InputStream)(new FileInputStream($this$bufferedReader)), charset));
      return var3 instanceof BufferedReader ? (BufferedReader)var3 : new BufferedReader(var3, bufferSize);
   }

   // $FF: synthetic method
   static BufferedReader bufferedReader$default(File $this$bufferedReader_u24default, Charset charset, int bufferSize, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         charset = Charsets.UTF_8;
      }

      if ((var3 & 2) != 0) {
         bufferSize = 8192;
      }

      Intrinsics.checkNotNullParameter($this$bufferedReader_u24default, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Reader var5 = (Reader)(new InputStreamReader((InputStream)(new FileInputStream($this$bufferedReader_u24default)), charset));
      return var5 instanceof BufferedReader ? (BufferedReader)var5 : new BufferedReader(var5, bufferSize);
   }

   @InlineOnly
   private static final OutputStreamWriter writer(File $this$writer, Charset charset) {
      Intrinsics.checkNotNullParameter($this$writer, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      return new OutputStreamWriter((OutputStream)(new FileOutputStream($this$writer)), charset);
   }

   // $FF: synthetic method
   static OutputStreamWriter writer$default(File $this$writer_u24default, Charset charset, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         charset = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter($this$writer_u24default, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      return new OutputStreamWriter((OutputStream)(new FileOutputStream($this$writer_u24default)), charset);
   }

   @InlineOnly
   private static final BufferedWriter bufferedWriter(File $this$bufferedWriter, Charset charset, int bufferSize) {
      Intrinsics.checkNotNullParameter($this$bufferedWriter, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Writer var3 = (Writer)(new OutputStreamWriter((OutputStream)(new FileOutputStream($this$bufferedWriter)), charset));
      return var3 instanceof BufferedWriter ? (BufferedWriter)var3 : new BufferedWriter(var3, bufferSize);
   }

   // $FF: synthetic method
   static BufferedWriter bufferedWriter$default(File $this$bufferedWriter_u24default, Charset charset, int bufferSize, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         charset = Charsets.UTF_8;
      }

      if ((var3 & 2) != 0) {
         bufferSize = 8192;
      }

      Intrinsics.checkNotNullParameter($this$bufferedWriter_u24default, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Writer var5 = (Writer)(new OutputStreamWriter((OutputStream)(new FileOutputStream($this$bufferedWriter_u24default)), charset));
      return var5 instanceof BufferedWriter ? (BufferedWriter)var5 : new BufferedWriter(var5, bufferSize);
   }

   @InlineOnly
   private static final PrintWriter printWriter(File $this$printWriter, Charset charset) {
      Intrinsics.checkNotNullParameter($this$printWriter, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      short var3 = 8192;
      Writer var4 = (Writer)(new OutputStreamWriter((OutputStream)(new FileOutputStream($this$printWriter)), charset));
      return new PrintWriter((Writer)(var4 instanceof BufferedWriter ? (BufferedWriter)var4 : new BufferedWriter(var4, var3)));
   }

   // $FF: synthetic method
   static PrintWriter printWriter$default(File $this$printWriter_u24default, Charset charset, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         charset = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter($this$printWriter_u24default, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      short var5 = 8192;
      Writer var4 = (Writer)(new OutputStreamWriter((OutputStream)(new FileOutputStream($this$printWriter_u24default)), charset));
      return new PrintWriter((Writer)(var4 instanceof BufferedWriter ? (BufferedWriter)var4 : new BufferedWriter(var4, var5)));
   }

   @NotNull
   public static final byte[] readBytes(@NotNull File $this$readBytes) {
      Intrinsics.checkNotNullParameter($this$readBytes, "<this>");
      Closeable var1 = (Closeable)(new FileInputStream($this$readBytes));
      Throwable var2 = null;

      byte[] var18;
      try {
         FileInputStream input = (FileInputStream)var1;
         int var4 = 0;
         int offset = 0;
         long length = $this$readBytes.length();
         int var10 = 0;
         if (length > 2147483647L) {
            throw new OutOfMemoryError("File " + $this$readBytes + " is too big (" + length + " bytes) to fit in memory.");
         }

         int remaining = (int)length;

         int read;
         for(result = new byte[remaining]; remaining > 0; offset += read) {
            read = input.read(result, offset, remaining);
            if (read < 0) {
               break;
            }

            remaining -= read;
         }

         byte[] var10000;
         if (remaining > 0) {
            var10000 = Arrays.copyOf(result, offset);
            Intrinsics.checkNotNullExpressionValue(var10000, "copyOf(...)");
         } else {
            read = input.read();
            if (read == -1) {
               var10000 = result;
            } else {
               ExposingBufferByteArrayOutputStream extra = new ExposingBufferByteArrayOutputStream(8193);
               extra.write(read);
               ByteStreamsKt.copyTo$default((InputStream)input, (OutputStream)extra, 0, 2, (Object)null);
               var10 = result.length + extra.size();
               if (var10 < 0) {
                  throw new OutOfMemoryError("File " + $this$readBytes + " is too big to fit in memory.");
               }

               var10000 = extra.getBuffer();
               byte[] var10001 = Arrays.copyOf(result, var10);
               Intrinsics.checkNotNullExpressionValue(var10001, "copyOf(...)");
               var10000 = ArraysKt.copyInto(var10000, var10001, result.length, 0, extra.size());
            }
         }

         var18 = var10000;
      } catch (Throwable var16) {
         var2 = var16;
         throw var16;
      } finally {
         CloseableKt.closeFinally(var1, var2);
      }

      return var18;
   }

   public static final void writeBytes(@NotNull File $this$writeBytes, @NotNull byte[] array) {
      Intrinsics.checkNotNullParameter($this$writeBytes, "<this>");
      Intrinsics.checkNotNullParameter(array, "array");
      Closeable var2 = (Closeable)(new FileOutputStream($this$writeBytes));
      Throwable var3 = null;

      try {
         FileOutputStream it = (FileOutputStream)var2;
         int var5 = 0;
         it.write(array);
         Unit var10 = Unit.INSTANCE;
      } catch (Throwable var8) {
         var3 = var8;
         throw var8;
      } finally {
         CloseableKt.closeFinally(var2, var3);
      }

   }

   public static final void appendBytes(@NotNull File $this$appendBytes, @NotNull byte[] array) {
      Intrinsics.checkNotNullParameter($this$appendBytes, "<this>");
      Intrinsics.checkNotNullParameter(array, "array");
      Closeable var2 = (Closeable)(new FileOutputStream($this$appendBytes, true));
      Throwable var3 = null;

      try {
         FileOutputStream it = (FileOutputStream)var2;
         int var5 = 0;
         it.write(array);
         Unit var10 = Unit.INSTANCE;
      } catch (Throwable var8) {
         var3 = var8;
         throw var8;
      } finally {
         CloseableKt.closeFinally(var2, var3);
      }

   }

   @NotNull
   public static final String readText(@NotNull File $this$readText, @NotNull Charset charset) {
      Intrinsics.checkNotNullParameter($this$readText, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Closeable var2 = (Closeable)(new InputStreamReader((InputStream)(new FileInputStream($this$readText)), charset));
      Throwable var3 = null;

      String var10;
      try {
         InputStreamReader it = (InputStreamReader)var2;
         int var5 = 0;
         var10 = TextStreamsKt.readText((Reader)it);
      } catch (Throwable var8) {
         var3 = var8;
         throw var8;
      } finally {
         CloseableKt.closeFinally(var2, var3);
      }

      return var10;
   }

   // $FF: synthetic method
   public static String readText$default(File var0, Charset var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      return FilesKt.readText(var0, var1);
   }

   public static final void writeText(@NotNull File $this$writeText, @NotNull String text, @NotNull Charset charset) {
      Intrinsics.checkNotNullParameter($this$writeText, "<this>");
      Intrinsics.checkNotNullParameter(text, "text");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Closeable var3 = (Closeable)(new FileOutputStream($this$writeText));
      Throwable var4 = null;

      try {
         FileOutputStream it = (FileOutputStream)var3;
         int var6 = 0;
         FilesKt.writeTextImpl((OutputStream)it, text, charset);
         Unit var11 = Unit.INSTANCE;
      } catch (Throwable var9) {
         var4 = var9;
         throw var9;
      } finally {
         CloseableKt.closeFinally(var3, var4);
      }

   }

   // $FF: synthetic method
   public static void writeText$default(File var0, String var1, Charset var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = Charsets.UTF_8;
      }

      FilesKt.writeText(var0, var1, var2);
   }

   public static final void appendText(@NotNull File $this$appendText, @NotNull String text, @NotNull Charset charset) {
      Intrinsics.checkNotNullParameter($this$appendText, "<this>");
      Intrinsics.checkNotNullParameter(text, "text");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Closeable var3 = (Closeable)(new FileOutputStream($this$appendText, true));
      Throwable var4 = null;

      try {
         FileOutputStream it = (FileOutputStream)var3;
         int var6 = 0;
         FilesKt.writeTextImpl((OutputStream)it, text, charset);
         Unit var11 = Unit.INSTANCE;
      } catch (Throwable var9) {
         var4 = var9;
         throw var9;
      } finally {
         CloseableKt.closeFinally(var3, var4);
      }

   }

   // $FF: synthetic method
   public static void appendText$default(File var0, String var1, Charset var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = Charsets.UTF_8;
      }

      FilesKt.appendText(var0, var1, var2);
   }

   public static final void writeTextImpl(@NotNull OutputStream $this$writeTextImpl, @NotNull String text, @NotNull Charset charset) {
      Intrinsics.checkNotNullParameter($this$writeTextImpl, "<this>");
      Intrinsics.checkNotNullParameter(text, "text");
      Intrinsics.checkNotNullParameter(charset, "charset");
      int chunkSize = 8192;
      if (text.length() < 2 * chunkSize) {
         byte[] var10001 = text.getBytes(charset);
         Intrinsics.checkNotNullExpressionValue(var10001, "getBytes(...)");
         $this$writeTextImpl.write(var10001);
      } else {
         CharsetEncoder encoder = FilesKt.newReplaceEncoder(charset);
         CharBuffer charBuffer = CharBuffer.allocate(chunkSize);
         Intrinsics.checkNotNull(encoder);
         ByteBuffer byteBuffer = FilesKt.byteBufferForEncoding(chunkSize, encoder);
         int startIndex = 0;

         int endIndex;
         for(int leftover = 0; startIndex < text.length(); startIndex = endIndex) {
            int copyLength = Math.min(chunkSize - leftover, text.length() - startIndex);
            endIndex = startIndex + copyLength;
            char[] var10000 = charBuffer.array();
            Intrinsics.checkNotNullExpressionValue(var10000, "array(...)");
            char[] var12 = var10000;
            text.getChars(startIndex, endIndex, var12, leftover);
            charBuffer.limit(copyLength + leftover);
            CoderResult it = encoder.encode(charBuffer, byteBuffer, endIndex == text.length());
            int var13 = 0;
            boolean var14 = it.isUnderflow();
            if (!var14) {
               String var15 = "Check failed.";
               throw new IllegalStateException(var15.toString());
            }

            $this$writeTextImpl.write(byteBuffer.array(), 0, byteBuffer.position());
            if (charBuffer.position() != charBuffer.limit()) {
               charBuffer.put(0, charBuffer.get());
               leftover = 1;
            } else {
               leftover = 0;
            }

            charBuffer.clear();
            byteBuffer.clear();
         }

      }
   }

   public static final CharsetEncoder newReplaceEncoder(@NotNull Charset $this$newReplaceEncoder) {
      Intrinsics.checkNotNullParameter($this$newReplaceEncoder, "<this>");
      return $this$newReplaceEncoder.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
   }

   @NotNull
   public static final ByteBuffer byteBufferForEncoding(int chunkSize, @NotNull CharsetEncoder encoder) {
      Intrinsics.checkNotNullParameter(encoder, "encoder");
      int maxBytesPerChar = (int)((float)Math.ceil((double)encoder.maxBytesPerChar()));
      ByteBuffer var10000 = ByteBuffer.allocate(chunkSize * maxBytesPerChar);
      Intrinsics.checkNotNullExpressionValue(var10000, "allocate(...)");
      return var10000;
   }

   public static final void forEachBlock(@NotNull File $this$forEachBlock, @NotNull Function2 action) {
      Intrinsics.checkNotNullParameter($this$forEachBlock, "<this>");
      Intrinsics.checkNotNullParameter(action, "action");
      FilesKt.forEachBlock($this$forEachBlock, 4096, action);
   }

   public static final void forEachBlock(@NotNull File $this$forEachBlock, int blockSize, @NotNull Function2 action) {
      Intrinsics.checkNotNullParameter($this$forEachBlock, "<this>");
      Intrinsics.checkNotNullParameter(action, "action");
      byte[] arr = new byte[RangesKt.coerceAtLeast(blockSize, 512)];
      Closeable var4 = (Closeable)(new FileInputStream($this$forEachBlock));
      Throwable var5 = null;

      try {
         FileInputStream input = (FileInputStream)var4;
         int var7 = 0;

         while(true) {
            int size = input.read(arr);
            if (size <= 0) {
               Unit var13 = Unit.INSTANCE;
               return;
            }

            action.invoke(arr, size);
         }
      } catch (Throwable var11) {
         var5 = var11;
         throw var11;
      } finally {
         CloseableKt.closeFinally(var4, var5);
      }
   }

   public static final void forEachLine(@NotNull File $this$forEachLine, @NotNull Charset charset, @NotNull Function1 action) {
      Intrinsics.checkNotNullParameter($this$forEachLine, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Intrinsics.checkNotNullParameter(action, "action");
      TextStreamsKt.forEachLine((Reader)(new BufferedReader((Reader)(new InputStreamReader((InputStream)(new FileInputStream($this$forEachLine)), charset)))), action);
   }

   // $FF: synthetic method
   public static void forEachLine$default(File var0, Charset var1, Function1 var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      FilesKt.forEachLine(var0, var1, var2);
   }

   @InlineOnly
   private static final FileInputStream inputStream(File $this$inputStream) {
      Intrinsics.checkNotNullParameter($this$inputStream, "<this>");
      return new FileInputStream($this$inputStream);
   }

   @InlineOnly
   private static final FileOutputStream outputStream(File $this$outputStream) {
      Intrinsics.checkNotNullParameter($this$outputStream, "<this>");
      return new FileOutputStream($this$outputStream);
   }

   @NotNull
   public static final List readLines(@NotNull File $this$readLines, @NotNull Charset charset) {
      Intrinsics.checkNotNullParameter($this$readLines, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      final ArrayList result = new ArrayList();
      FilesKt.forEachLine($this$readLines, charset, new Function1(result) {
         public final void invoke(String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            result.add(it);
         }
      });
      return (List)result;
   }

   // $FF: synthetic method
   public static List readLines$default(File var0, Charset var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      return FilesKt.readLines(var0, var1);
   }

   public static final Object useLines(@NotNull File $this$useLines, @NotNull Charset charset, @NotNull Function1 block) {
      Intrinsics.checkNotNullParameter($this$useLines, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Intrinsics.checkNotNullParameter(block, "block");
      int $i$f$useLines = 0;
      short var5 = 8192;
      Reader var6 = (Reader)(new InputStreamReader((InputStream)(new FileInputStream($this$useLines)), charset));
      Closeable var4 = (Closeable)(var6 instanceof BufferedReader ? (BufferedReader)var6 : new BufferedReader(var6, var5));
      Throwable var15 = null;
      boolean var11 = false;

      try {
         var11 = true;
         BufferedReader var16 = (BufferedReader)var4;
         int var7 = false;
         var17 = block.invoke(TextStreamsKt.lineSequence(var16));
         var11 = false;
      } catch (Throwable var13) {
         var15 = var13;
         throw var13;
      } finally {
         if (var11) {
            InlineMarker.finallyStart(1);
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
               CloseableKt.closeFinally(var4, var15);
            } else if (var15 == null) {
               var4.close();
            } else {
               try {
                  var4.close();
               } catch (Throwable var12) {
               }
            }

            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
         CloseableKt.closeFinally(var4, var15);
      } else {
         var4.close();
      }

      InlineMarker.finallyEnd(1);
      return var17;
   }

   // $FF: synthetic method
   public static Object useLines$default(File $this$useLines_u24default, Charset charset, Function1 block, int $i$f$useLines, Object var4) {
      if (($i$f$useLines & 1) != 0) {
         charset = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter($this$useLines_u24default, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Intrinsics.checkNotNullParameter(block, "block");
      $i$f$useLines = 0;
      short var5 = 8192;
      Reader var6 = (Reader)(new InputStreamReader((InputStream)(new FileInputStream($this$useLines_u24default)), charset));
      Closeable var16 = (Closeable)(var6 instanceof BufferedReader ? (BufferedReader)var6 : new BufferedReader(var6, var5));
      Throwable var17 = null;
      boolean var11 = false;

      try {
         var11 = true;
         BufferedReader var18 = (BufferedReader)var16;
         int var7 = false;
         var19 = block.invoke(TextStreamsKt.lineSequence(var18));
         var11 = false;
      } catch (Throwable var13) {
         var17 = var13;
         throw var13;
      } finally {
         if (var11) {
            InlineMarker.finallyStart(1);
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
               CloseableKt.closeFinally(var16, var17);
            } else if (var17 == null) {
               var16.close();
            } else {
               try {
                  var16.close();
               } catch (Throwable var12) {
               }
            }

            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
         CloseableKt.closeFinally(var16, var17);
      } else {
         var16.close();
      }

      InlineMarker.finallyEnd(1);
      return var19;
   }

   public FilesKt__FileReadWriteKt() {
   }
}
