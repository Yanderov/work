package kotlin.io.path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001c\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b\u0004\u0010\u0005\u001a,\u0010\u000b\u001a\u00020\u0000*\u00020\u00002\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\n\u001a\u00020\tH\u0087\b¢\u0006\u0004\b\u000b\u0010\f\u001a,\u0010\u000b\u001a\u00020\u0000*\u00020\u00002\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\r2\b\b\u0002\u0010\n\u001a\u00020\tH\u0087\b¢\u0006\u0004\b\u000b\u0010\u000e\u001a%\u0010\u0010\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001a<\u0010\u0018\u001a\u00020\u0017*\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u0013\u001a\u00020\u00122\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015H\u0087\b¢\u0006\u0004\b\u0018\u0010\u0019\u001a<\u0010\u001b\u001a\u00020\u001a*\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u0013\u001a\u00020\u00122\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015H\u0087\b¢\u0006\u0004\b\u001b\u0010\u001c\u001aD\u0010#\u001a\u00020\u0003*\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\t2!\u0010\"\u001a\u001d\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u00030\u001dH\u0087\bø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a(\u0010&\u001a\u00020%*\u00020\u00002\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015H\u0087\b¢\u0006\u0004\b&\u0010'\u001a(\u0010)\u001a\u00020(*\u00020\u00002\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015H\u0087\b¢\u0006\u0004\b)\u0010*\u001a\u0014\u0010+\u001a\u00020\u0001*\u00020\u0000H\u0087\b¢\u0006\u0004\b+\u0010,\u001a$\u0010.\u001a\b\u0012\u0004\u0012\u00020\u001e0-*\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\tH\u0087\b¢\u0006\u0004\b.\u0010/\u001a\u001d\u00100\u001a\u00020\u001e*\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b0\u00101\u001a2\u00103\u001a\u000202*\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\t2\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015H\u0087\b¢\u0006\u0004\b3\u00104\u001aN\u00107\u001a\u00028\u0000\"\u0004\b\u0000\u00105*\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\t2\u0018\u00106\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\r\u0012\u0004\u0012\u00028\u00000\u001dH\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0004\b7\u00108\u001a0\u00109\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015H\u0087\b¢\u0006\u0004\b9\u0010:\u001a@\u0010;\u001a\u00020\u0000*\u00020\u00002\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\n\u001a\u00020\t2\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015H\u0087\b¢\u0006\u0004\b;\u0010<\u001a@\u0010;\u001a\u00020\u0000*\u00020\u00002\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\r2\b\b\u0002\u0010\n\u001a\u00020\t2\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015H\u0087\b¢\u0006\u0004\b;\u0010=\u001a9\u0010>\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015H\u0007¢\u0006\u0004\b>\u0010?\u001a2\u0010A\u001a\u00020@*\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\t2\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015H\u0087\b¢\u0006\u0004\bA\u0010B\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006C"},
   d2 = {"Ljava/nio/file/Path;", "", "array", "", "appendBytes", "(Ljava/nio/file/Path;[B)V", "", "", "lines", "Ljava/nio/charset/Charset;", "charset", "appendLines", "(Ljava/nio/file/Path;Ljava/lang/Iterable;Ljava/nio/charset/Charset;)Ljava/nio/file/Path;", "Lkotlin/sequences/Sequence;", "(Ljava/nio/file/Path;Lkotlin/sequences/Sequence;Ljava/nio/charset/Charset;)Ljava/nio/file/Path;", "text", "appendText", "(Ljava/nio/file/Path;Ljava/lang/CharSequence;Ljava/nio/charset/Charset;)V", "", "bufferSize", "", "Ljava/nio/file/OpenOption;", "options", "Ljava/io/BufferedReader;", "bufferedReader", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;I[Ljava/nio/file/OpenOption;)Ljava/io/BufferedReader;", "Ljava/io/BufferedWriter;", "bufferedWriter", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;I[Ljava/nio/file/OpenOption;)Ljava/io/BufferedWriter;", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "line", "action", "forEachLine", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)V", "Ljava/io/InputStream;", "inputStream", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Ljava/io/InputStream;", "Ljava/io/OutputStream;", "outputStream", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Ljava/io/OutputStream;", "readBytes", "(Ljava/nio/file/Path;)[B", "", "readLines", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;)Ljava/util/List;", "readText", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;)Ljava/lang/String;", "Ljava/io/InputStreamReader;", "reader", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/io/InputStreamReader;", "T", "block", "useLines", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writeBytes", "(Ljava/nio/file/Path;[B[Ljava/nio/file/OpenOption;)V", "writeLines", "(Ljava/nio/file/Path;Ljava/lang/Iterable;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/nio/file/Path;", "(Ljava/nio/file/Path;Lkotlin/sequences/Sequence;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/nio/file/Path;", "writeText", "(Ljava/nio/file/Path;Ljava/lang/CharSequence;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)V", "Ljava/io/OutputStreamWriter;", "writer", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/io/OutputStreamWriter;", "kotlin-stdlib-jdk7"},
   xs = "kotlin/io/path/PathsKt"
)
@SourceDebugExtension({"SMAP\nPathReadWrite.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathReadWrite.kt\nkotlin/io/path/PathsKt__PathReadWriteKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ReadWrite.kt\nkotlin/io/TextStreamsKt\n+ 4 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,346:1\n1#2:347\n1#2:352\n54#3,4:348\n1317#4,2:353\n*S KotlinDebug\n*F\n+ 1 PathReadWrite.kt\nkotlin/io/path/PathsKt__PathReadWriteKt\n*L\n219#1:352\n219#1:348,4\n219#1:353,2\n*E\n"})
class PathsKt__PathReadWriteKt {
   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final InputStreamReader reader(Path $this$reader, Charset charset, OpenOption... options) throws IOException {
      Intrinsics.checkNotNullParameter($this$reader, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Intrinsics.checkNotNullParameter(options, "options");
      return new InputStreamReader(Files.newInputStream($this$reader, (OpenOption[])Arrays.copyOf(options, options.length)), charset);
   }

   // $FF: synthetic method
   static InputStreamReader reader$default(Path $this$reader_u24default, Charset charset, OpenOption[] options, int var3, Object var4) throws IOException {
      if ((var3 & 1) != 0) {
         charset = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter($this$reader_u24default, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Intrinsics.checkNotNullParameter(options, "options");
      return new InputStreamReader(Files.newInputStream($this$reader_u24default, (OpenOption[])Arrays.copyOf(options, options.length)), charset);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final BufferedReader bufferedReader(Path $this$bufferedReader, Charset charset, int bufferSize, OpenOption... options) throws IOException {
      Intrinsics.checkNotNullParameter($this$bufferedReader, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Intrinsics.checkNotNullParameter(options, "options");
      return new BufferedReader((Reader)(new InputStreamReader(Files.newInputStream($this$bufferedReader, (OpenOption[])Arrays.copyOf(options, options.length)), charset)), bufferSize);
   }

   // $FF: synthetic method
   static BufferedReader bufferedReader$default(Path $this$bufferedReader_u24default, Charset charset, int bufferSize, OpenOption[] options, int var4, Object var5) throws IOException {
      if ((var4 & 1) != 0) {
         charset = Charsets.UTF_8;
      }

      if ((var4 & 2) != 0) {
         bufferSize = 8192;
      }

      Intrinsics.checkNotNullParameter($this$bufferedReader_u24default, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Intrinsics.checkNotNullParameter(options, "options");
      return new BufferedReader((Reader)(new InputStreamReader(Files.newInputStream($this$bufferedReader_u24default, (OpenOption[])Arrays.copyOf(options, options.length)), charset)), bufferSize);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final OutputStreamWriter writer(Path $this$writer, Charset charset, OpenOption... options) throws IOException {
      Intrinsics.checkNotNullParameter($this$writer, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Intrinsics.checkNotNullParameter(options, "options");
      return new OutputStreamWriter(Files.newOutputStream($this$writer, (OpenOption[])Arrays.copyOf(options, options.length)), charset);
   }

   // $FF: synthetic method
   static OutputStreamWriter writer$default(Path $this$writer_u24default, Charset charset, OpenOption[] options, int var3, Object var4) throws IOException {
      if ((var3 & 1) != 0) {
         charset = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter($this$writer_u24default, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Intrinsics.checkNotNullParameter(options, "options");
      return new OutputStreamWriter(Files.newOutputStream($this$writer_u24default, (OpenOption[])Arrays.copyOf(options, options.length)), charset);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final BufferedWriter bufferedWriter(Path $this$bufferedWriter, Charset charset, int bufferSize, OpenOption... options) throws IOException {
      Intrinsics.checkNotNullParameter($this$bufferedWriter, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Intrinsics.checkNotNullParameter(options, "options");
      return new BufferedWriter((Writer)(new OutputStreamWriter(Files.newOutputStream($this$bufferedWriter, (OpenOption[])Arrays.copyOf(options, options.length)), charset)), bufferSize);
   }

   // $FF: synthetic method
   static BufferedWriter bufferedWriter$default(Path $this$bufferedWriter_u24default, Charset charset, int bufferSize, OpenOption[] options, int var4, Object var5) throws IOException {
      if ((var4 & 1) != 0) {
         charset = Charsets.UTF_8;
      }

      if ((var4 & 2) != 0) {
         bufferSize = 8192;
      }

      Intrinsics.checkNotNullParameter($this$bufferedWriter_u24default, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Intrinsics.checkNotNullParameter(options, "options");
      return new BufferedWriter((Writer)(new OutputStreamWriter(Files.newOutputStream($this$bufferedWriter_u24default, (OpenOption[])Arrays.copyOf(options, options.length)), charset)), bufferSize);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final byte[] readBytes(Path $this$readBytes) throws IOException {
      Intrinsics.checkNotNullParameter($this$readBytes, "<this>");
      byte[] var10000 = Files.readAllBytes($this$readBytes);
      Intrinsics.checkNotNullExpressionValue(var10000, "readAllBytes(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final void writeBytes(Path $this$writeBytes, byte[] array, OpenOption... options) throws IOException {
      Intrinsics.checkNotNullParameter($this$writeBytes, "<this>");
      Intrinsics.checkNotNullParameter(array, "array");
      Intrinsics.checkNotNullParameter(options, "options");
      Files.write($this$writeBytes, array, (OpenOption[])Arrays.copyOf(options, options.length));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final void appendBytes(Path $this$appendBytes, byte[] array) throws IOException {
      Intrinsics.checkNotNullParameter($this$appendBytes, "<this>");
      Intrinsics.checkNotNullParameter(array, "array");
      OpenOption[] var2 = new OpenOption[]{StandardOpenOption.APPEND};
      Files.write($this$appendBytes, array, var2);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @NotNull
   public static final String readText(@NotNull Path $this$readText, @NotNull Charset charset) throws IOException {
      Intrinsics.checkNotNullParameter($this$readText, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      OpenOption[] var3 = new OpenOption[0];
      Closeable var2 = (Closeable)(new InputStreamReader(Files.newInputStream($this$readText, (OpenOption[])Arrays.copyOf(var3, var3.length)), charset));
      Throwable var10 = null;

      String var11;
      try {
         InputStreamReader it = (InputStreamReader)var2;
         int var5 = 0;
         var11 = TextStreamsKt.readText((Reader)it);
      } catch (Throwable var8) {
         var10 = var8;
         throw var8;
      } finally {
         CloseableKt.closeFinally(var2, var10);
      }

      return var11;
   }

   // $FF: synthetic method
   public static String readText$default(Path var0, Charset var1, int var2, Object var3) throws IOException {
      if ((var2 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      return PathsKt.readText(var0, var1);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   public static final void writeText(@NotNull Path $this$writeText, @NotNull CharSequence text, @NotNull Charset charset, @NotNull OpenOption... options) throws IOException {
      Intrinsics.checkNotNullParameter($this$writeText, "<this>");
      Intrinsics.checkNotNullParameter(text, "text");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Intrinsics.checkNotNullParameter(options, "options");
      Closeable var4 = (Closeable)Files.newOutputStream($this$writeText, (OpenOption[])Arrays.copyOf(options, options.length));
      Throwable var5 = null;

      try {
         OutputStream out = (OutputStream)var4;
         int var7 = 0;
         if (text instanceof String) {
            Intrinsics.checkNotNull(out);
            FilesKt.writeTextImpl(out, (String)text, charset);
         } else {
            CharsetEncoder encoder = FilesKt.newReplaceEncoder(charset);
            CharBuffer charBuffer = text instanceof CharBuffer ? ((CharBuffer)text).asReadOnlyBuffer() : CharBuffer.wrap(text);
            int var10000 = Math.min(text.length(), 8192);
            Intrinsics.checkNotNull(encoder);
            ByteBuffer byteBuffer = FilesKt.byteBufferForEncoding(var10000, encoder);

            while(charBuffer.hasRemaining()) {
               CoderResult it = encoder.encode(charBuffer, byteBuffer, true);
               int var13 = 0;
               if (it.isError()) {
                  String var14 = "Check failed.";
                  throw new IllegalStateException(var14.toString());
               }

               out.write(byteBuffer.array(), 0, byteBuffer.position());
               byteBuffer.clear();
            }
         }

         Unit var19 = Unit.INSTANCE;
      } catch (Throwable var17) {
         var5 = var17;
         throw var17;
      } finally {
         CloseableKt.closeFinally(var4, var5);
      }

   }

   // $FF: synthetic method
   public static void writeText$default(Path var0, CharSequence var1, Charset var2, OpenOption[] var3, int var4, Object var5) throws IOException {
      if ((var4 & 2) != 0) {
         var2 = Charsets.UTF_8;
      }

      PathsKt.writeText(var0, var1, var2, var3);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   public static final void appendText(@NotNull Path $this$appendText, @NotNull CharSequence text, @NotNull Charset charset) throws IOException {
      Intrinsics.checkNotNullParameter($this$appendText, "<this>");
      Intrinsics.checkNotNullParameter(text, "text");
      Intrinsics.checkNotNullParameter(charset, "charset");
      OpenOption[] var3 = new OpenOption[]{StandardOpenOption.APPEND};
      PathsKt.writeText($this$appendText, text, charset, var3);
   }

   // $FF: synthetic method
   public static void appendText$default(Path var0, CharSequence var1, Charset var2, int var3, Object var4) throws IOException {
      if ((var3 & 2) != 0) {
         var2 = Charsets.UTF_8;
      }

      PathsKt.appendText(var0, var1, var2);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final void forEachLine(Path $this$forEachLine, Charset charset, Function1 action) throws IOException {
      Intrinsics.checkNotNullParameter($this$forEachLine, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Intrinsics.checkNotNullParameter(action, "action");
      BufferedReader var10000 = Files.newBufferedReader($this$forEachLine, charset);
      Intrinsics.checkNotNullExpressionValue(var10000, "newBufferedReader(...)");
      Reader $this$useLines$iv = (Reader)var10000;
      int $i$f$useLines = 0;
      Closeable var5 = (Closeable)((BufferedReader)$this$useLines$iv);
      Throwable var6 = null;
      boolean var18 = false;

      try {
         var18 = true;
         BufferedReader it$iv = (BufferedReader)var5;
         int var8 = false;
         Sequence it = TextStreamsKt.lineSequence(it$iv);
         int var10 = false;
         int $i$f$forEach = false;

         for(Object element$iv : it) {
            action.invoke(element$iv);
         }

         Unit var22 = Unit.INSTANCE;
         var18 = false;
      } catch (Throwable var20) {
         var6 = var20;
         throw var20;
      } finally {
         if (var18) {
            InlineMarker.finallyStart(1);
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
               CloseableKt.closeFinally(var5, var6);
            } else if (var6 == null) {
               var5.close();
            } else {
               try {
                  var5.close();
               } catch (Throwable var19) {
               }
            }

            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
         CloseableKt.closeFinally(var5, var6);
      } else {
         var5.close();
      }

      InlineMarker.finallyEnd(1);
   }

   // $FF: synthetic method
   static void forEachLine$default(Path $this$forEachLine_u24default, Charset charset, Function1 action, int $this$useLines$iv, Object $i$f$useLines) throws IOException {
      if (($this$useLines$iv & 1) != 0) {
         charset = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter($this$forEachLine_u24default, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Intrinsics.checkNotNullParameter(action, "action");
      BufferedReader var10000 = Files.newBufferedReader($this$forEachLine_u24default, charset);
      Intrinsics.checkNotNullExpressionValue(var10000, "newBufferedReader(...)");
      Reader $this$useLines$iv = (Reader)var10000;
      int $i$f$useLines = 0;
      Closeable var5 = (Closeable)((BufferedReader)$this$useLines$iv);
      Throwable var6 = null;
      boolean var18 = false;

      try {
         var18 = true;
         BufferedReader it$iv = (BufferedReader)var5;
         int var8 = false;
         Sequence it = TextStreamsKt.lineSequence(it$iv);
         int var10 = false;
         int $i$f$forEach = false;

         for(Object element$iv : it) {
            action.invoke(element$iv);
         }

         Unit var24 = Unit.INSTANCE;
         var18 = false;
      } catch (Throwable var20) {
         var6 = var20;
         throw var20;
      } finally {
         if (var18) {
            InlineMarker.finallyStart(1);
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
               CloseableKt.closeFinally(var5, var6);
            } else if (var6 == null) {
               var5.close();
            } else {
               try {
                  var5.close();
               } catch (Throwable var19) {
               }
            }

            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
         CloseableKt.closeFinally(var5, var6);
      } else {
         var5.close();
      }

      InlineMarker.finallyEnd(1);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final InputStream inputStream(Path $this$inputStream, OpenOption... options) throws IOException {
      Intrinsics.checkNotNullParameter($this$inputStream, "<this>");
      Intrinsics.checkNotNullParameter(options, "options");
      InputStream var10000 = Files.newInputStream($this$inputStream, (OpenOption[])Arrays.copyOf(options, options.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "newInputStream(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final OutputStream outputStream(Path $this$outputStream, OpenOption... options) throws IOException {
      Intrinsics.checkNotNullParameter($this$outputStream, "<this>");
      Intrinsics.checkNotNullParameter(options, "options");
      OutputStream var10000 = Files.newOutputStream($this$outputStream, (OpenOption[])Arrays.copyOf(options, options.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "newOutputStream(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final List readLines(Path $this$readLines, Charset charset) throws IOException {
      Intrinsics.checkNotNullParameter($this$readLines, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      List var10000 = Files.readAllLines($this$readLines, charset);
      Intrinsics.checkNotNullExpressionValue(var10000, "readAllLines(...)");
      return var10000;
   }

   // $FF: synthetic method
   static List readLines$default(Path $this$readLines_u24default, Charset charset, int var2, Object var3) throws IOException {
      if ((var2 & 1) != 0) {
         charset = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter($this$readLines_u24default, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      List var10000 = Files.readAllLines($this$readLines_u24default, charset);
      Intrinsics.checkNotNullExpressionValue(var10000, "readAllLines(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Object useLines(Path $this$useLines, Charset charset, Function1 block) throws IOException {
      Intrinsics.checkNotNullParameter($this$useLines, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Intrinsics.checkNotNullParameter(block, "block");
      Closeable var3 = (Closeable)Files.newBufferedReader($this$useLines, charset);
      Throwable var4 = null;
      boolean var10 = false;

      Object var14;
      try {
         var10 = true;
         BufferedReader it = (BufferedReader)var3;
         int var6 = false;
         Intrinsics.checkNotNull(it);
         var14 = block.invoke(TextStreamsKt.lineSequence(it));
         var10 = false;
      } catch (Throwable var12) {
         var4 = var12;
         throw var12;
      } finally {
         if (var10) {
            InlineMarker.finallyStart(1);
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
               CloseableKt.closeFinally(var3, var4);
            } else if (var3 != null) {
               if (var4 == null) {
                  var3.close();
               } else {
                  try {
                     var3.close();
                  } catch (Throwable var11) {
                  }
               }
            }

            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
         CloseableKt.closeFinally(var3, var4);
      } else if (var3 != null) {
         var3.close();
      }

      InlineMarker.finallyEnd(1);
      return var14;
   }

   // $FF: synthetic method
   static Object useLines$default(Path $this$useLines_u24default, Charset charset, Function1 block, int var3, Object var4) throws IOException {
      if ((var3 & 1) != 0) {
         charset = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter($this$useLines_u24default, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Intrinsics.checkNotNullParameter(block, "block");
      Closeable var14 = (Closeable)Files.newBufferedReader($this$useLines_u24default, charset);
      Throwable var15 = null;
      boolean var10 = false;

      Object var16;
      try {
         var10 = true;
         BufferedReader it = (BufferedReader)var14;
         int var6 = false;
         Intrinsics.checkNotNull(it);
         var16 = block.invoke(TextStreamsKt.lineSequence(it));
         var10 = false;
      } catch (Throwable var12) {
         var15 = var12;
         throw var12;
      } finally {
         if (var10) {
            InlineMarker.finallyStart(1);
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
               CloseableKt.closeFinally(var14, var15);
            } else if (var14 != null) {
               if (var15 == null) {
                  var14.close();
               } else {
                  try {
                     var14.close();
                  } catch (Throwable var11) {
                  }
               }
            }

            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
         CloseableKt.closeFinally(var14, var15);
      } else if (var14 != null) {
         var14.close();
      }

      InlineMarker.finallyEnd(1);
      return var16;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path writeLines(Path $this$writeLines, Iterable lines, Charset charset, OpenOption... options) throws IOException {
      Intrinsics.checkNotNullParameter($this$writeLines, "<this>");
      Intrinsics.checkNotNullParameter(lines, "lines");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Intrinsics.checkNotNullParameter(options, "options");
      Path var10000 = Files.write($this$writeLines, lines, charset, (OpenOption[])Arrays.copyOf(options, options.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "write(...)");
      return var10000;
   }

   // $FF: synthetic method
   static Path writeLines$default(Path $this$writeLines_u24default, Iterable lines, Charset charset, OpenOption[] options, int var4, Object var5) throws IOException {
      if ((var4 & 2) != 0) {
         charset = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter($this$writeLines_u24default, "<this>");
      Intrinsics.checkNotNullParameter(lines, "lines");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Intrinsics.checkNotNullParameter(options, "options");
      Path var10000 = Files.write($this$writeLines_u24default, lines, charset, (OpenOption[])Arrays.copyOf(options, options.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "write(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path writeLines(Path $this$writeLines, Sequence lines, Charset charset, OpenOption... options) throws IOException {
      Intrinsics.checkNotNullParameter($this$writeLines, "<this>");
      Intrinsics.checkNotNullParameter(lines, "lines");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Intrinsics.checkNotNullParameter(options, "options");
      Path var10000 = Files.write($this$writeLines, SequencesKt.asIterable(lines), charset, (OpenOption[])Arrays.copyOf(options, options.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "write(...)");
      return var10000;
   }

   // $FF: synthetic method
   static Path writeLines$default(Path $this$writeLines_u24default, Sequence lines, Charset charset, OpenOption[] options, int var4, Object var5) throws IOException {
      if ((var4 & 2) != 0) {
         charset = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter($this$writeLines_u24default, "<this>");
      Intrinsics.checkNotNullParameter(lines, "lines");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Intrinsics.checkNotNullParameter(options, "options");
      Path var10000 = Files.write($this$writeLines_u24default, SequencesKt.asIterable(lines), charset, (OpenOption[])Arrays.copyOf(options, options.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "write(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path appendLines(Path $this$appendLines, Iterable lines, Charset charset) throws IOException {
      Intrinsics.checkNotNullParameter($this$appendLines, "<this>");
      Intrinsics.checkNotNullParameter(lines, "lines");
      Intrinsics.checkNotNullParameter(charset, "charset");
      OpenOption[] var3 = new OpenOption[]{StandardOpenOption.APPEND};
      Path var10000 = Files.write($this$appendLines, lines, charset, var3);
      Intrinsics.checkNotNullExpressionValue(var10000, "write(...)");
      return var10000;
   }

   // $FF: synthetic method
   static Path appendLines$default(Path $this$appendLines_u24default, Iterable lines, Charset charset, int var3, Object var4) throws IOException {
      if ((var3 & 2) != 0) {
         charset = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter($this$appendLines_u24default, "<this>");
      Intrinsics.checkNotNullParameter(lines, "lines");
      Intrinsics.checkNotNullParameter(charset, "charset");
      OpenOption[] var5 = new OpenOption[]{StandardOpenOption.APPEND};
      Path var10000 = Files.write($this$appendLines_u24default, lines, charset, var5);
      Intrinsics.checkNotNullExpressionValue(var10000, "write(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path appendLines(Path $this$appendLines, Sequence lines, Charset charset) throws IOException {
      Intrinsics.checkNotNullParameter($this$appendLines, "<this>");
      Intrinsics.checkNotNullParameter(lines, "lines");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Iterable var10001 = SequencesKt.asIterable(lines);
      OpenOption[] var3 = new OpenOption[]{StandardOpenOption.APPEND};
      Path var10000 = Files.write($this$appendLines, var10001, charset, var3);
      Intrinsics.checkNotNullExpressionValue(var10000, "write(...)");
      return var10000;
   }

   // $FF: synthetic method
   static Path appendLines$default(Path $this$appendLines_u24default, Sequence lines, Charset charset, int var3, Object var4) throws IOException {
      if ((var3 & 2) != 0) {
         charset = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter($this$appendLines_u24default, "<this>");
      Intrinsics.checkNotNullParameter(lines, "lines");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Iterable var10001 = SequencesKt.asIterable(lines);
      OpenOption[] var5 = new OpenOption[]{StandardOpenOption.APPEND};
      Path var10000 = Files.write($this$appendLines_u24default, var10001, charset, var5);
      Intrinsics.checkNotNullExpressionValue(var10000, "write(...)");
      return var10000;
   }

   public PathsKt__PathReadWriteKt() {
   }
}
