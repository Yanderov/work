package kotlin.text;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u001a7\u0010\u0006\u001a\u00028\u0000\"\f\b\u0000\u0010\u0002*\u00060\u0000j\u0002`\u0001*\u00028\u00002\u0016\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00040\u0003\"\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007\u001a;\u0010\f\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0002*\u00060\u0000j\u0002`\u00012\u0006\u0010\b\u001a\u00028\u00002\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0004\u0018\u00010\tH\u0000¢\u0006\u0004\b\f\u0010\r\u001a\u001c\u0010\u000e\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u0001H\u0087\b¢\u0006\u0004\b\u000e\u0010\u000f\u001a$\u0010\u000e\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0005\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b\u000e\u0010\u0011\u001a&\u0010\u000e\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0087\b¢\u0006\u0004\b\u000e\u0010\u0012\u001a9\u0010\u0016\u001a\u00028\u0000\"\f\b\u0000\u0010\u0002*\u00060\u0000j\u0002`\u0001*\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0013H\u0007¢\u0006\u0004\b\u0016\u0010\u0017¨\u0006\u0018"},
   d2 = {"Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "T", "", "", "value", "append", "(Ljava/lang/Appendable;[Ljava/lang/CharSequence;)Ljava/lang/Appendable;", "element", "Lkotlin/Function1;", "transform", "", "appendElement", "(Ljava/lang/Appendable;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "appendLine", "(Ljava/lang/Appendable;)Ljava/lang/Appendable;", "", "(Ljava/lang/Appendable;C)Ljava/lang/Appendable;", "(Ljava/lang/Appendable;Ljava/lang/CharSequence;)Ljava/lang/Appendable;", "", "startIndex", "endIndex", "appendRange", "(Ljava/lang/Appendable;Ljava/lang/CharSequence;II)Ljava/lang/Appendable;", "kotlin-stdlib"},
   xs = "kotlin/text/StringsKt"
)
class StringsKt__AppendableKt {
   @SinceKotlin(
      version = "1.4"
   )
   @NotNull
   public static final Appendable appendRange(@NotNull Appendable $this$appendRange, @NotNull CharSequence value, int startIndex, int endIndex) {
      Intrinsics.checkNotNullParameter($this$appendRange, "<this>");
      Intrinsics.checkNotNullParameter(value, "value");
      Appendable var10000 = $this$appendRange.append(value, startIndex, endIndex);
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type T of kotlin.text.StringsKt__AppendableKt.appendRange");
      return var10000;
   }

   @NotNull
   public static final Appendable append(@NotNull Appendable $this$append, @NotNull CharSequence... value) {
      Intrinsics.checkNotNullParameter($this$append, "<this>");
      Intrinsics.checkNotNullParameter(value, "value");
      int var2 = 0;

      for(int var3 = value.length; var2 < var3; ++var2) {
         CharSequence item = value[var2];
         $this$append.append(item);
      }

      return $this$append;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final Appendable appendLine(Appendable $this$appendLine) {
      Intrinsics.checkNotNullParameter($this$appendLine, "<this>");
      Appendable var10000 = $this$appendLine.append('\n');
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final Appendable appendLine(Appendable $this$appendLine, CharSequence value) {
      Intrinsics.checkNotNullParameter($this$appendLine, "<this>");
      Appendable var10000 = $this$appendLine.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      var10000 = var10000.append('\n');
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final Appendable appendLine(Appendable $this$appendLine, char value) {
      Intrinsics.checkNotNullParameter($this$appendLine, "<this>");
      Appendable var10000 = $this$appendLine.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      var10000 = var10000.append('\n');
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return var10000;
   }

   public static final void appendElement(@NotNull Appendable $this$appendElement, Object element, @Nullable Function1 transform) {
      Intrinsics.checkNotNullParameter($this$appendElement, "<this>");
      if (transform != null) {
         $this$appendElement.append((CharSequence)transform.invoke(element));
      } else if (element == null ? true : element instanceof CharSequence) {
         $this$appendElement.append((CharSequence)element);
      } else if (element instanceof Character) {
         $this$appendElement.append((Character)element);
      } else {
         $this$appendElement.append((CharSequence)String.valueOf(element));
      }

   }

   public StringsKt__AppendableKt() {
   }
}
