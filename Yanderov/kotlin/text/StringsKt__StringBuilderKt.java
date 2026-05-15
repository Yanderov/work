package kotlin.text;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000R\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0003\u001aE\u0010\t\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u001b\u0010\u0007\u001a\u0017\u0012\b\u0012\u00060\u0003j\u0002`\u0004\u0012\u0004\u0012\u00020\u00050\u0002¢\u0006\u0002\b\u0006H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0004\b\t\u0010\n\u001a=\u0010\t\u001a\u00020\b2\u001b\u0010\u0007\u001a\u0017\u0012\b\u0012\u00060\u0003j\u0002`\u0004\u0012\u0004\u0012\u00020\u00050\u0002¢\u0006\u0002\b\u0006H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\t\u0010\u000b\u001a&\u0010\u000e\u001a\u00060\u0003j\u0002`\u0004*\u00060\u0003j\u0002`\u00042\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0087\b¢\u0006\u0004\b\u000e\u0010\u000f\u001a1\u0010\u000e\u001a\u00060\u0003j\u0002`\u0004*\u00060\u0003j\u0002`\u00042\u0016\u0010\u0011\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\f0\u0010\"\u0004\u0018\u00010\f¢\u0006\u0004\b\u000e\u0010\u0012\u001a1\u0010\u000e\u001a\u00060\u0003j\u0002`\u0004*\u00060\u0003j\u0002`\u00042\u0016\u0010\u0011\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\b0\u0010\"\u0004\u0018\u00010\b¢\u0006\u0004\b\u000e\u0010\u0013\u001a4\u0010\u000e\u001a\u00060\u0003j\u0002`\u0004*\u00060\u0003j\u0002`\u00042\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b\u000e\u0010\u0018\u001a\u001c\u0010\u0019\u001a\u00060\u0003j\u0002`\u0004*\u00060\u0003j\u0002`\u0004H\u0087\b¢\u0006\u0004\b\u0019\u0010\u001a\u001a&\u0010\u0019\u001a\u00060\u0003j\u0002`\u0004*\u00060\u0003j\u0002`\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\fH\u0087\b¢\u0006\u0004\b\u0019\u0010\u000f\u001a$\u0010\u0019\u001a\u00060\u0003j\u0002`\u0004*\u00060\u0003j\u0002`\u00042\u0006\u0010\u0011\u001a\u00020\u001bH\u0087\b¢\u0006\u0004\b\u0019\u0010\u001c\u001a$\u0010\u0019\u001a\u00060\u0003j\u0002`\u0004*\u00060\u0003j\u0002`\u00042\u0006\u0010\u0011\u001a\u00020\u001dH\u0087\b¢\u0006\u0004\b\u0019\u0010\u001e\u001a$\u0010\u0019\u001a\u00060\u0003j\u0002`\u0004*\u00060\u0003j\u0002`\u00042\u0006\u0010\u0011\u001a\u00020\u0014H\u0087\b¢\u0006\u0004\b\u0019\u0010\u001f\u001a&\u0010\u0019\u001a\u00060\u0003j\u0002`\u0004*\u00060\u0003j\u0002`\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010 H\u0087\b¢\u0006\u0004\b\u0019\u0010!\u001a&\u0010\u0019\u001a\u00060\u0003j\u0002`\u0004*\u00060\u0003j\u0002`\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\bH\u0087\b¢\u0006\u0004\b\u0019\u0010\"\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006#"},
   d2 = {"", "capacity", "Lkotlin/Function1;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "", "Lkotlin/ExtensionFunctionType;", "builderAction", "", "buildString", "(ILkotlin/jvm/functions/Function1;)Ljava/lang/String;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/String;", "", "obj", "append", "(Ljava/lang/StringBuilder;Ljava/lang/Object;)Ljava/lang/StringBuilder;", "", "value", "(Ljava/lang/StringBuilder;[Ljava/lang/Object;)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;[Ljava/lang/String;)Ljava/lang/StringBuilder;", "", "str", "offset", "len", "(Ljava/lang/StringBuilder;[CII)Ljava/lang/StringBuilder;", "appendLine", "(Ljava/lang/StringBuilder;)Ljava/lang/StringBuilder;", "", "(Ljava/lang/StringBuilder;Z)Ljava/lang/StringBuilder;", "", "(Ljava/lang/StringBuilder;C)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;[C)Ljava/lang/StringBuilder;", "", "(Ljava/lang/StringBuilder;Ljava/lang/CharSequence;)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;", "kotlin-stdlib"},
   xs = "kotlin/text/StringsKt"
)
class StringsKt__StringBuilderKt extends StringsKt__StringBuilderJVMKt {
   /** @deprecated */
   @Deprecated(
      message = "Use append(value: Any?) instead",
      replaceWith = @ReplaceWith(
   expression = "append(value = obj)",
   imports = {}
),
      level = DeprecationLevel.WARNING
   )
   @InlineOnly
   private static final StringBuilder append(StringBuilder $this$append, Object obj) {
      Intrinsics.checkNotNullParameter($this$append, "<this>");
      StringBuilder var10000 = $this$append.append(obj);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return var10000;
   }

   @InlineOnly
   private static final String buildString(Function1 builderAction) {
      Intrinsics.checkNotNullParameter(builderAction, "builderAction");
      StringBuilder var1 = new StringBuilder();
      builderAction.invoke(var1);
      String var10000 = var1.toString();
      Intrinsics.checkNotNullExpressionValue(var10000, "toString(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final String buildString(int capacity, Function1 builderAction) {
      Intrinsics.checkNotNullParameter(builderAction, "builderAction");
      StringBuilder var2 = new StringBuilder(capacity);
      builderAction.invoke(var2);
      String var10000 = var2.toString();
      Intrinsics.checkNotNullExpressionValue(var10000, "toString(...)");
      return var10000;
   }

   @NotNull
   public static final StringBuilder append(@NotNull StringBuilder $this$append, @NotNull String... value) {
      Intrinsics.checkNotNullParameter($this$append, "<this>");
      Intrinsics.checkNotNullParameter(value, "value");
      int var2 = 0;

      for(int var3 = value.length; var2 < var3; ++var2) {
         String item = value[var2];
         $this$append.append(item);
      }

      return $this$append;
   }

   @NotNull
   public static final StringBuilder append(@NotNull StringBuilder $this$append, @NotNull Object... value) {
      Intrinsics.checkNotNullParameter($this$append, "<this>");
      Intrinsics.checkNotNullParameter(value, "value");
      int var2 = 0;

      for(int var3 = value.length; var2 < var3; ++var2) {
         Object item = value[var2];
         $this$append.append(item);
      }

      return $this$append;
   }

   /** @deprecated */
   @Deprecated(
      message = "Use appendRange instead.",
      replaceWith = @ReplaceWith(
   expression = "this.appendRange(str, offset, offset + len)",
   imports = {}
),
      level = DeprecationLevel.ERROR
   )
   @InlineOnly
   private static final StringBuilder append(StringBuilder $this$append, char[] str, int offset, int len) {
      Intrinsics.checkNotNullParameter($this$append, "<this>");
      Intrinsics.checkNotNullParameter(str, "str");
      throw new NotImplementedError((String)null, 1, (DefaultConstructorMarker)null);
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final StringBuilder appendLine(StringBuilder $this$appendLine) {
      Intrinsics.checkNotNullParameter($this$appendLine, "<this>");
      StringBuilder var10000 = $this$appendLine.append('\n');
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final StringBuilder appendLine(StringBuilder $this$appendLine, CharSequence value) {
      Intrinsics.checkNotNullParameter($this$appendLine, "<this>");
      StringBuilder var10000 = $this$appendLine.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      var10000 = var10000.append('\n');
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final StringBuilder appendLine(StringBuilder $this$appendLine, String value) {
      Intrinsics.checkNotNullParameter($this$appendLine, "<this>");
      StringBuilder var10000 = $this$appendLine.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      var10000 = var10000.append('\n');
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final StringBuilder appendLine(StringBuilder $this$appendLine, Object value) {
      Intrinsics.checkNotNullParameter($this$appendLine, "<this>");
      StringBuilder var10000 = $this$appendLine.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      var10000 = var10000.append('\n');
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final StringBuilder appendLine(StringBuilder $this$appendLine, char[] value) {
      Intrinsics.checkNotNullParameter($this$appendLine, "<this>");
      Intrinsics.checkNotNullParameter(value, "value");
      StringBuilder var10000 = $this$appendLine.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      var10000 = var10000.append('\n');
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final StringBuilder appendLine(StringBuilder $this$appendLine, char value) {
      Intrinsics.checkNotNullParameter($this$appendLine, "<this>");
      StringBuilder var10000 = $this$appendLine.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      var10000 = var10000.append('\n');
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final StringBuilder appendLine(StringBuilder $this$appendLine, boolean value) {
      Intrinsics.checkNotNullParameter($this$appendLine, "<this>");
      StringBuilder var10000 = $this$appendLine.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      var10000 = var10000.append('\n');
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return var10000;
   }

   public StringsKt__StringBuilderKt() {
   }
}
