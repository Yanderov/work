package kotlin;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import kotlin.internal.HidesMembers;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000:\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0005\u001a\u001b\u0010\u0003\u001a\u00020\u0002*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0014\u0010\u0005\u001a\u00020\u0002*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001c\u0010\u0005\u001a\u00020\u0002*\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007H\u0087\b¢\u0006\u0004\b\u0005\u0010\t\u001a\u001c\u0010\u0005\u001a\u00020\u0002*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\nH\u0087\b¢\u0006\u0004\b\u0005\u0010\f\u001a\u0013\u0010\u000e\u001a\u00020\r*\u00020\u0000H\u0007¢\u0006\u0004\b\u000e\u0010\u000f\"!\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010*\u00020\u00008F¢\u0006\f\u0012\u0004\b\u0014\u0010\u0006\u001a\u0004\b\u0012\u0010\u0013\"$\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00000\u0016*\u00020\u00008FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0019\u0010\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001b"},
   d2 = {"", "exception", "", "addSuppressed", "(Ljava/lang/Throwable;Ljava/lang/Throwable;)V", "printStackTrace", "(Ljava/lang/Throwable;)V", "Ljava/io/PrintStream;", "stream", "(Ljava/lang/Throwable;Ljava/io/PrintStream;)V", "Ljava/io/PrintWriter;", "writer", "(Ljava/lang/Throwable;Ljava/io/PrintWriter;)V", "", "stackTraceToString", "(Ljava/lang/Throwable;)Ljava/lang/String;", "", "Ljava/lang/StackTraceElement;", "getStackTrace", "(Ljava/lang/Throwable;)[Ljava/lang/StackTraceElement;", "getStackTrace$annotations", "stackTrace", "", "getSuppressedExceptions", "(Ljava/lang/Throwable;)Ljava/util/List;", "getSuppressedExceptions$annotations", "suppressedExceptions", "kotlin-stdlib"},
   xs = "kotlin/ExceptionsKt"
)
class ExceptionsKt__ExceptionsKt {
   @InlineOnly
   private static final void printStackTrace(Throwable $this$printStackTrace) {
      Intrinsics.checkNotNullParameter($this$printStackTrace, "<this>");
      $this$printStackTrace.printStackTrace();
   }

   @InlineOnly
   private static final void printStackTrace(Throwable $this$printStackTrace, PrintWriter writer) {
      Intrinsics.checkNotNullParameter($this$printStackTrace, "<this>");
      Intrinsics.checkNotNullParameter(writer, "writer");
      $this$printStackTrace.printStackTrace(writer);
   }

   @InlineOnly
   private static final void printStackTrace(Throwable $this$printStackTrace, PrintStream stream) {
      Intrinsics.checkNotNullParameter($this$printStackTrace, "<this>");
      Intrinsics.checkNotNullParameter(stream, "stream");
      $this$printStackTrace.printStackTrace(stream);
   }

   @NotNull
   public static final StackTraceElement[] getStackTrace(@NotNull Throwable $this$stackTrace) {
      Intrinsics.checkNotNullParameter($this$stackTrace, "<this>");
      StackTraceElement[] var10000 = $this$stackTrace.getStackTrace();
      Intrinsics.checkNotNull(var10000);
      return var10000;
   }

   /** @deprecated */
   // $FF: synthetic method
   public static void getStackTrace$annotations(Throwable <this>) {
   }

   @SinceKotlin(
      version = "1.4"
   )
   @NotNull
   public static final String stackTraceToString(@NotNull Throwable $this$stackTraceToString) {
      Intrinsics.checkNotNullParameter($this$stackTraceToString, "<this>");
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter((Writer)sw);
      $this$stackTraceToString.printStackTrace(pw);
      pw.flush();
      String var10000 = sw.toString();
      Intrinsics.checkNotNullExpressionValue(var10000, "toString(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @HidesMembers
   public static final void addSuppressed(@NotNull Throwable $this$addSuppressed, @NotNull Throwable exception) {
      Intrinsics.checkNotNullParameter($this$addSuppressed, "<this>");
      Intrinsics.checkNotNullParameter(exception, "exception");
      if ($this$addSuppressed != exception) {
         PlatformImplementationsKt.IMPLEMENTATIONS.addSuppressed($this$addSuppressed, exception);
      }

   }

   @NotNull
   public static final List getSuppressedExceptions(@NotNull Throwable $this$suppressedExceptions) {
      Intrinsics.checkNotNullParameter($this$suppressedExceptions, "<this>");
      return PlatformImplementationsKt.IMPLEMENTATIONS.getSuppressed($this$suppressedExceptions);
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.4"
   )
   public static void getSuppressedExceptions$annotations(Throwable <this>) {
   }

   public ExceptionsKt__ExceptionsKt() {
   }
}
