package kotlin.io;

import java.io.InputStream;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000N\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a\u001a\u0010\u0003\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\u0087\b¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0005H\u0087\b¢\u0006\u0004\b\u0003\u0010\u0006\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0007H\u0087\b¢\u0006\u0004\b\u0003\u0010\b\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\tH\u0087\b¢\u0006\u0004\b\u0003\u0010\n\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u000bH\u0087\b¢\u0006\u0004\b\u0003\u0010\f\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\rH\u0087\b¢\u0006\u0004\b\u0003\u0010\u000e\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u000fH\u0087\b¢\u0006\u0004\b\u0003\u0010\u0010\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0011H\u0087\b¢\u0006\u0004\b\u0003\u0010\u0012\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\b\u0003\u0010\u0014\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0015H\u0087\b¢\u0006\u0004\b\u0003\u0010\u0016\u001a\u0010\u0010\u0017\u001a\u00020\u0002H\u0087\b¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u001a\u0010\u0017\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\u0087\b¢\u0006\u0004\b\u0017\u0010\u0004\u001a\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0005H\u0087\b¢\u0006\u0004\b\u0017\u0010\u0006\u001a\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0007H\u0087\b¢\u0006\u0004\b\u0017\u0010\b\u001a\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\tH\u0087\b¢\u0006\u0004\b\u0017\u0010\n\u001a\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u000bH\u0087\b¢\u0006\u0004\b\u0017\u0010\f\u001a\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\rH\u0087\b¢\u0006\u0004\b\u0017\u0010\u000e\u001a\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u000fH\u0087\b¢\u0006\u0004\b\u0017\u0010\u0010\u001a\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0011H\u0087\b¢\u0006\u0004\b\u0017\u0010\u0012\u001a\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\b\u0017\u0010\u0014\u001a\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0015H\u0087\b¢\u0006\u0004\b\u0017\u0010\u0016\u001a\u000f\u0010\u001a\u001a\u0004\u0018\u00010\u0019¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u000f\u0010\u001c\u001a\u00020\u0019H\u0007¢\u0006\u0004\b\u001c\u0010\u001b\u001a\u0011\u0010\u001d\u001a\u0004\u0018\u00010\u0019H\u0007¢\u0006\u0004\b\u001d\u0010\u001b¨\u0006\u001e"},
   d2 = {"", "message", "", "print", "(Ljava/lang/Object;)V", "", "(Z)V", "", "(B)V", "", "(C)V", "", "([C)V", "", "(D)V", "", "(F)V", "", "(I)V", "", "(J)V", "", "(S)V", "println", "()V", "", "readLine", "()Ljava/lang/String;", "readln", "readlnOrNull", "kotlin-stdlib"}
)
@JvmName(
   name = "ConsoleKt"
)
public final class ConsoleKt {
   @InlineOnly
   private static final void print(Object message) {
      System.out.print(message);
   }

   @InlineOnly
   private static final void print(int message) {
      System.out.print(message);
   }

   @InlineOnly
   private static final void print(long message) {
      System.out.print(message);
   }

   @InlineOnly
   private static final void print(byte message) {
      System.out.print(message);
   }

   @InlineOnly
   private static final void print(short message) {
      System.out.print(message);
   }

   @InlineOnly
   private static final void print(char message) {
      System.out.print(message);
   }

   @InlineOnly
   private static final void print(boolean message) {
      System.out.print(message);
   }

   @InlineOnly
   private static final void print(float message) {
      System.out.print(message);
   }

   @InlineOnly
   private static final void print(double message) {
      System.out.print(message);
   }

   @InlineOnly
   private static final void print(char[] message) {
      Intrinsics.checkNotNullParameter(message, "message");
      System.out.print(message);
   }

   @InlineOnly
   private static final void println(Object message) {
      System.out.println(message);
   }

   @InlineOnly
   private static final void println(int message) {
      System.out.println(message);
   }

   @InlineOnly
   private static final void println(long message) {
      System.out.println(message);
   }

   @InlineOnly
   private static final void println(byte message) {
      System.out.println(message);
   }

   @InlineOnly
   private static final void println(short message) {
      System.out.println(message);
   }

   @InlineOnly
   private static final void println(char message) {
      System.out.println(message);
   }

   @InlineOnly
   private static final void println(boolean message) {
      System.out.println(message);
   }

   @InlineOnly
   private static final void println(float message) {
      System.out.println(message);
   }

   @InlineOnly
   private static final void println(double message) {
      System.out.println(message);
   }

   @InlineOnly
   private static final void println(char[] message) {
      Intrinsics.checkNotNullParameter(message, "message");
      System.out.println(message);
   }

   @InlineOnly
   private static final void println() {
      System.out.println();
   }

   @SinceKotlin(
      version = "1.6"
   )
   @NotNull
   public static final String readln() {
      return readlnOrNull();
   }

   @SinceKotlin(
      version = "1.6"
   )
   @Nullable
   public static final String readlnOrNull() {
      return readLine();
   }

   @Nullable
   public static final String readLine() {
      LineReader var10000 = LineReader.INSTANCE;
      InputStream var10001 = System.in;
      Intrinsics.checkNotNullExpressionValue(var10001, "in");
      Charset var10002 = Charset.defaultCharset();
      Intrinsics.checkNotNullExpressionValue(var10002, "defaultCharset(...)");
      return var10000.readLine(var10001, var10002);
   }
}
