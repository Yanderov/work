package kotlin.text;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0002\b\u0004\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\t\u001a$\u0010\u0004\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\b¢\u0006\u0004\b\u0004\u0010\u0005\u001a$\u0010\u0004\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\b¢\u0006\u0004\b\u0004\u0010\u0007\u001a&\u0010\t\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\bH\u0087\b¢\u0006\u0004\b\t\u0010\n\u001a$\u0010\t\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\b¢\u0006\u0004\b\t\u0010\u0005\u001a$\u0010\t\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u000bH\u0087\b¢\u0006\u0004\b\t\u0010\f\u001a$\u0010\t\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\rH\u0087\b¢\u0006\u0004\b\t\u0010\u000e\u001a$\u0010\t\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u000fH\u0087\b¢\u0006\u0004\b\t\u0010\u0010\u001a$\u0010\t\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u0011H\u0087\b¢\u0006\u0004\b\t\u0010\u0012\u001a$\u0010\t\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\b¢\u0006\u0004\b\t\u0010\u0007\u001a,\u0010\t\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u000e\u0010\u0003\u001a\n\u0018\u00010\u0000j\u0004\u0018\u0001`\u0001H\u0087\b¢\u0006\u0004\b\t\u0010\u0013\u001a4\u0010\u0017\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000fH\u0087\b¢\u0006\u0004\b\u0017\u0010\u0018\u001a4\u0010\u0017\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000fH\u0087\b¢\u0006\u0004\b\u0017\u0010\u001a\u001a\u001b\u0010\u001d\u001a\u00060\u001bj\u0002`\u001c*\u00060\u001bj\u0002`\u001cH\u0007¢\u0006\u0004\b\u001d\u0010\u001e\u001a$\u0010\u001d\u001a\u00060\u001bj\u0002`\u001c*\u00060\u001bj\u0002`\u001c2\u0006\u0010\u0003\u001a\u00020\u001fH\u0087\b¢\u0006\u0004\b\u001d\u0010 \u001a&\u0010\u001d\u001a\u00060\u001bj\u0002`\u001c*\u00060\u001bj\u0002`\u001c2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0019H\u0087\b¢\u0006\u0004\b\u001d\u0010!\u001a\u001b\u0010\u001d\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u0001H\u0007¢\u0006\u0004\b\u001d\u0010\"\u001a&\u0010\u001d\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\bH\u0087\b¢\u0006\u0004\b\u001d\u0010\n\u001a&\u0010\u001d\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010#H\u0087\b¢\u0006\u0004\b\u001d\u0010$\u001a$\u0010\u001d\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020%H\u0087\b¢\u0006\u0004\b\u001d\u0010&\u001a$\u0010\u001d\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\b¢\u0006\u0004\b\u001d\u0010\u0005\u001a$\u0010\u001d\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u001fH\u0087\b¢\u0006\u0004\b\u001d\u0010'\u001a$\u0010\u001d\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u0014H\u0087\b¢\u0006\u0004\b\u001d\u0010(\u001a&\u0010\u001d\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0019H\u0087\b¢\u0006\u0004\b\u001d\u0010)\u001a$\u0010\u001d\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u000bH\u0087\b¢\u0006\u0004\b\u001d\u0010\f\u001a$\u0010\u001d\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\rH\u0087\b¢\u0006\u0004\b\u001d\u0010\u000e\u001a$\u0010\u001d\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u000fH\u0087\b¢\u0006\u0004\b\u001d\u0010\u0010\u001a$\u0010\u001d\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u0011H\u0087\b¢\u0006\u0004\b\u001d\u0010\u0012\u001a$\u0010\u001d\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\b¢\u0006\u0004\b\u001d\u0010\u0007\u001a&\u0010\u001d\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010*H\u0087\b¢\u0006\u0004\b\u001d\u0010+\u001a,\u0010\u001d\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u000e\u0010\u0003\u001a\n\u0018\u00010\u0000j\u0004\u0018\u0001`\u0001H\u0087\b¢\u0006\u0004\b\u001d\u0010\u0013\u001a\u001b\u0010,\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u0001H\u0007¢\u0006\u0004\b,\u0010\"\u001a$\u0010.\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010-\u001a\u00020\u000fH\u0087\b¢\u0006\u0004\b.\u0010\u0010\u001a,\u0010/\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000fH\u0087\b¢\u0006\u0004\b/\u00100\u001a,\u00101\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010-\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\b¢\u0006\u0004\b1\u00102\u001a,\u00101\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010-\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\b¢\u0006\u0004\b1\u00103\u001a<\u00104\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010-\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000fH\u0087\b¢\u0006\u0004\b4\u00105\u001a<\u00104\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010-\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000fH\u0087\b¢\u0006\u0004\b4\u00106\u001a(\u00108\u001a\u000207*\u00060\u0000j\u0002`\u00012\u0006\u0010-\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u001fH\u0087\n¢\u0006\u0004\b8\u00109\u001a4\u0010:\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020*H\u0087\b¢\u0006\u0004\b:\u0010;\u001a>\u0010>\u001a\u000207*\u00060\u0000j\u0002`\u00012\u0006\u0010<\u001a\u00020\u00142\b\b\u0002\u0010=\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u000f2\b\b\u0002\u0010\u0016\u001a\u00020\u000fH\u0087\b¢\u0006\u0004\b>\u0010?¨\u0006@"},
   d2 = {"Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "", "value", "append", "(Ljava/lang/StringBuilder;B)Ljava/lang/StringBuilder;", "", "(Ljava/lang/StringBuilder;S)Ljava/lang/StringBuilder;", "Ljava/lang/StringBuffer;", "appendLine", "(Ljava/lang/StringBuilder;Ljava/lang/StringBuffer;)Ljava/lang/StringBuilder;", "", "(Ljava/lang/StringBuilder;D)Ljava/lang/StringBuilder;", "", "(Ljava/lang/StringBuilder;F)Ljava/lang/StringBuilder;", "", "(Ljava/lang/StringBuilder;I)Ljava/lang/StringBuilder;", "", "(Ljava/lang/StringBuilder;J)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;Ljava/lang/StringBuilder;)Ljava/lang/StringBuilder;", "", "startIndex", "endIndex", "appendRange", "(Ljava/lang/StringBuilder;[CII)Ljava/lang/StringBuilder;", "", "(Ljava/lang/StringBuilder;Ljava/lang/CharSequence;II)Ljava/lang/StringBuilder;", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "appendln", "(Ljava/lang/Appendable;)Ljava/lang/Appendable;", "", "(Ljava/lang/Appendable;C)Ljava/lang/Appendable;", "(Ljava/lang/Appendable;Ljava/lang/CharSequence;)Ljava/lang/Appendable;", "(Ljava/lang/StringBuilder;)Ljava/lang/StringBuilder;", "", "(Ljava/lang/StringBuilder;Ljava/lang/Object;)Ljava/lang/StringBuilder;", "", "(Ljava/lang/StringBuilder;Z)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;C)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;[C)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;Ljava/lang/CharSequence;)Ljava/lang/StringBuilder;", "", "(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;", "clear", "index", "deleteAt", "deleteRange", "(Ljava/lang/StringBuilder;II)Ljava/lang/StringBuilder;", "insert", "(Ljava/lang/StringBuilder;IB)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;IS)Ljava/lang/StringBuilder;", "insertRange", "(Ljava/lang/StringBuilder;I[CII)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;ILjava/lang/CharSequence;II)Ljava/lang/StringBuilder;", "", "set", "(Ljava/lang/StringBuilder;IC)V", "setRange", "(Ljava/lang/StringBuilder;IILjava/lang/String;)Ljava/lang/StringBuilder;", "destination", "destinationOffset", "toCharArray", "(Ljava/lang/StringBuilder;[CIII)V", "kotlin-stdlib"},
   xs = "kotlin/text/StringsKt"
)
@SourceDebugExtension({"SMAP\nStringBuilderJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StringBuilderJVM.kt\nkotlin/text/StringsKt__StringBuilderJVMKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,421:1\n1#2:422\n*E\n"})
class StringsKt__StringBuilderJVMKt extends StringsKt__RegexExtensionsKt {
   @SinceKotlin(
      version = "1.9"
   )
   @InlineOnly
   private static final StringBuilder append(StringBuilder $this$append, byte value) {
      Intrinsics.checkNotNullParameter($this$append, "<this>");
      StringBuilder var10000 = $this$append.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.9"
   )
   @InlineOnly
   private static final StringBuilder append(StringBuilder $this$append, short value) {
      Intrinsics.checkNotNullParameter($this$append, "<this>");
      StringBuilder var10000 = $this$append.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.9"
   )
   @InlineOnly
   private static final StringBuilder insert(StringBuilder $this$insert, int index, byte value) {
      Intrinsics.checkNotNullParameter($this$insert, "<this>");
      StringBuilder var10000 = $this$insert.insert(index, value);
      Intrinsics.checkNotNullExpressionValue(var10000, "insert(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.9"
   )
   @InlineOnly
   private static final StringBuilder insert(StringBuilder $this$insert, int index, short value) {
      Intrinsics.checkNotNullParameter($this$insert, "<this>");
      StringBuilder var10000 = $this$insert.insert(index, value);
      Intrinsics.checkNotNullExpressionValue(var10000, "insert(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.3"
   )
   @NotNull
   public static final StringBuilder clear(@NotNull StringBuilder $this$clear) {
      Intrinsics.checkNotNullParameter($this$clear, "<this>");
      int var3 = 0;
      $this$clear.setLength(0);
      return $this$clear;
   }

   @InlineOnly
   private static final void set(StringBuilder $this$set, int index, char value) {
      Intrinsics.checkNotNullParameter($this$set, "<this>");
      $this$set.setCharAt(index, value);
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final StringBuilder setRange(StringBuilder $this$setRange, int startIndex, int endIndex, String value) {
      Intrinsics.checkNotNullParameter($this$setRange, "<this>");
      Intrinsics.checkNotNullParameter(value, "value");
      StringBuilder var10000 = $this$setRange.replace(startIndex, endIndex, value);
      Intrinsics.checkNotNullExpressionValue(var10000, "replace(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final StringBuilder deleteAt(StringBuilder $this$deleteAt, int index) {
      Intrinsics.checkNotNullParameter($this$deleteAt, "<this>");
      StringBuilder var10000 = $this$deleteAt.deleteCharAt(index);
      Intrinsics.checkNotNullExpressionValue(var10000, "deleteCharAt(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final StringBuilder deleteRange(StringBuilder $this$deleteRange, int startIndex, int endIndex) {
      Intrinsics.checkNotNullParameter($this$deleteRange, "<this>");
      StringBuilder var10000 = $this$deleteRange.delete(startIndex, endIndex);
      Intrinsics.checkNotNullExpressionValue(var10000, "delete(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final void toCharArray(StringBuilder $this$toCharArray, char[] destination, int destinationOffset, int startIndex, int endIndex) {
      Intrinsics.checkNotNullParameter($this$toCharArray, "<this>");
      Intrinsics.checkNotNullParameter(destination, "destination");
      $this$toCharArray.getChars(startIndex, endIndex, destination, destinationOffset);
   }

   // $FF: synthetic method
   static void toCharArray$default(StringBuilder $this$toCharArray_u24default, char[] destination, int destinationOffset, int startIndex, int endIndex, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         destinationOffset = 0;
      }

      if ((var5 & 4) != 0) {
         startIndex = 0;
      }

      if ((var5 & 8) != 0) {
         endIndex = $this$toCharArray_u24default.length();
      }

      Intrinsics.checkNotNullParameter($this$toCharArray_u24default, "<this>");
      Intrinsics.checkNotNullParameter(destination, "destination");
      $this$toCharArray_u24default.getChars(startIndex, endIndex, destination, destinationOffset);
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final StringBuilder appendRange(StringBuilder $this$appendRange, char[] value, int startIndex, int endIndex) {
      Intrinsics.checkNotNullParameter($this$appendRange, "<this>");
      Intrinsics.checkNotNullParameter(value, "value");
      StringBuilder var10000 = $this$appendRange.append(value, startIndex, endIndex - startIndex);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final StringBuilder appendRange(StringBuilder $this$appendRange, CharSequence value, int startIndex, int endIndex) {
      Intrinsics.checkNotNullParameter($this$appendRange, "<this>");
      Intrinsics.checkNotNullParameter(value, "value");
      StringBuilder var10000 = $this$appendRange.append(value, startIndex, endIndex);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final StringBuilder insertRange(StringBuilder $this$insertRange, int index, char[] value, int startIndex, int endIndex) {
      Intrinsics.checkNotNullParameter($this$insertRange, "<this>");
      Intrinsics.checkNotNullParameter(value, "value");
      StringBuilder var10000 = $this$insertRange.insert(index, value, startIndex, endIndex - startIndex);
      Intrinsics.checkNotNullExpressionValue(var10000, "insert(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final StringBuilder insertRange(StringBuilder $this$insertRange, int index, CharSequence value, int startIndex, int endIndex) {
      Intrinsics.checkNotNullParameter($this$insertRange, "<this>");
      Intrinsics.checkNotNullParameter(value, "value");
      StringBuilder var10000 = $this$insertRange.insert(index, value, startIndex, endIndex);
      Intrinsics.checkNotNullExpressionValue(var10000, "insert(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final StringBuilder appendLine(StringBuilder $this$appendLine, StringBuffer value) {
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
   private static final StringBuilder appendLine(StringBuilder $this$appendLine, StringBuilder value) {
      Intrinsics.checkNotNullParameter($this$appendLine, "<this>");
      StringBuilder var10000 = $this$appendLine.append((CharSequence)value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      var10000 = var10000.append('\n');
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final StringBuilder appendLine(StringBuilder $this$appendLine, int value) {
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
   private static final StringBuilder appendLine(StringBuilder $this$appendLine, short value) {
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
   private static final StringBuilder appendLine(StringBuilder $this$appendLine, byte value) {
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
   private static final StringBuilder appendLine(StringBuilder $this$appendLine, long value) {
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
   private static final StringBuilder appendLine(StringBuilder $this$appendLine, float value) {
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
   private static final StringBuilder appendLine(StringBuilder $this$appendLine, double value) {
      Intrinsics.checkNotNullParameter($this$appendLine, "<this>");
      StringBuilder var10000 = $this$appendLine.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      var10000 = var10000.append('\n');
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return var10000;
   }

   /** @deprecated */
   @Deprecated(
      message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.",
      replaceWith = @ReplaceWith(
   expression = "appendLine()",
   imports = {}
),
      level = DeprecationLevel.WARNING
   )
   @NotNull
   public static final Appendable appendln(@NotNull Appendable $this$appendln) {
      Intrinsics.checkNotNullParameter($this$appendln, "<this>");
      Appendable var10000 = $this$appendln.append((CharSequence)SystemProperties.LINE_SEPARATOR);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return var10000;
   }

   /** @deprecated */
   @Deprecated(
      message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.",
      replaceWith = @ReplaceWith(
   expression = "appendLine(value)",
   imports = {}
),
      level = DeprecationLevel.WARNING
   )
   @InlineOnly
   private static final Appendable appendln(Appendable $this$appendln, CharSequence value) {
      Intrinsics.checkNotNullParameter($this$appendln, "<this>");
      Appendable var10000 = $this$appendln.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return StringsKt.appendln(var10000);
   }

   /** @deprecated */
   @Deprecated(
      message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.",
      replaceWith = @ReplaceWith(
   expression = "appendLine(value)",
   imports = {}
),
      level = DeprecationLevel.WARNING
   )
   @InlineOnly
   private static final Appendable appendln(Appendable $this$appendln, char value) {
      Intrinsics.checkNotNullParameter($this$appendln, "<this>");
      Appendable var10000 = $this$appendln.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return StringsKt.appendln(var10000);
   }

   /** @deprecated */
   @Deprecated(
      message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.",
      replaceWith = @ReplaceWith(
   expression = "appendLine()",
   imports = {}
),
      level = DeprecationLevel.WARNING
   )
   @NotNull
   public static final StringBuilder appendln(@NotNull StringBuilder $this$appendln) {
      Intrinsics.checkNotNullParameter($this$appendln, "<this>");
      StringBuilder var10000 = $this$appendln.append(SystemProperties.LINE_SEPARATOR);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return var10000;
   }

   /** @deprecated */
   @Deprecated(
      message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.",
      replaceWith = @ReplaceWith(
   expression = "appendLine(value)",
   imports = {}
),
      level = DeprecationLevel.WARNING
   )
   @InlineOnly
   private static final StringBuilder appendln(StringBuilder $this$appendln, StringBuffer value) {
      Intrinsics.checkNotNullParameter($this$appendln, "<this>");
      StringBuilder var10000 = $this$appendln.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return StringsKt.appendln(var10000);
   }

   /** @deprecated */
   @Deprecated(
      message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.",
      replaceWith = @ReplaceWith(
   expression = "appendLine(value)",
   imports = {}
),
      level = DeprecationLevel.WARNING
   )
   @InlineOnly
   private static final StringBuilder appendln(StringBuilder $this$appendln, CharSequence value) {
      Intrinsics.checkNotNullParameter($this$appendln, "<this>");
      StringBuilder var10000 = $this$appendln.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return StringsKt.appendln(var10000);
   }

   /** @deprecated */
   @Deprecated(
      message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.",
      replaceWith = @ReplaceWith(
   expression = "appendLine(value)",
   imports = {}
),
      level = DeprecationLevel.WARNING
   )
   @InlineOnly
   private static final StringBuilder appendln(StringBuilder $this$appendln, String value) {
      Intrinsics.checkNotNullParameter($this$appendln, "<this>");
      StringBuilder var10000 = $this$appendln.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return StringsKt.appendln(var10000);
   }

   /** @deprecated */
   @Deprecated(
      message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.",
      replaceWith = @ReplaceWith(
   expression = "appendLine(value)",
   imports = {}
),
      level = DeprecationLevel.WARNING
   )
   @InlineOnly
   private static final StringBuilder appendln(StringBuilder $this$appendln, Object value) {
      Intrinsics.checkNotNullParameter($this$appendln, "<this>");
      StringBuilder var10000 = $this$appendln.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return StringsKt.appendln(var10000);
   }

   /** @deprecated */
   @Deprecated(
      message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.",
      replaceWith = @ReplaceWith(
   expression = "appendLine(value)",
   imports = {}
),
      level = DeprecationLevel.WARNING
   )
   @InlineOnly
   private static final StringBuilder appendln(StringBuilder $this$appendln, StringBuilder value) {
      Intrinsics.checkNotNullParameter($this$appendln, "<this>");
      StringBuilder var10000 = $this$appendln.append((CharSequence)value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return StringsKt.appendln(var10000);
   }

   /** @deprecated */
   @Deprecated(
      message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.",
      replaceWith = @ReplaceWith(
   expression = "appendLine(value)",
   imports = {}
),
      level = DeprecationLevel.WARNING
   )
   @InlineOnly
   private static final StringBuilder appendln(StringBuilder $this$appendln, char[] value) {
      Intrinsics.checkNotNullParameter($this$appendln, "<this>");
      Intrinsics.checkNotNullParameter(value, "value");
      StringBuilder var10000 = $this$appendln.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return StringsKt.appendln(var10000);
   }

   /** @deprecated */
   @Deprecated(
      message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.",
      replaceWith = @ReplaceWith(
   expression = "appendLine(value)",
   imports = {}
),
      level = DeprecationLevel.WARNING
   )
   @InlineOnly
   private static final StringBuilder appendln(StringBuilder $this$appendln, char value) {
      Intrinsics.checkNotNullParameter($this$appendln, "<this>");
      StringBuilder var10000 = $this$appendln.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return StringsKt.appendln(var10000);
   }

   /** @deprecated */
   @Deprecated(
      message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.",
      replaceWith = @ReplaceWith(
   expression = "appendLine(value)",
   imports = {}
),
      level = DeprecationLevel.WARNING
   )
   @InlineOnly
   private static final StringBuilder appendln(StringBuilder $this$appendln, boolean value) {
      Intrinsics.checkNotNullParameter($this$appendln, "<this>");
      StringBuilder var10000 = $this$appendln.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return StringsKt.appendln(var10000);
   }

   /** @deprecated */
   @Deprecated(
      message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.",
      replaceWith = @ReplaceWith(
   expression = "appendLine(value)",
   imports = {}
),
      level = DeprecationLevel.WARNING
   )
   @InlineOnly
   private static final StringBuilder appendln(StringBuilder $this$appendln, int value) {
      Intrinsics.checkNotNullParameter($this$appendln, "<this>");
      StringBuilder var10000 = $this$appendln.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return StringsKt.appendln(var10000);
   }

   /** @deprecated */
   @Deprecated(
      message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.",
      replaceWith = @ReplaceWith(
   expression = "appendLine(value)",
   imports = {}
),
      level = DeprecationLevel.WARNING
   )
   @InlineOnly
   private static final StringBuilder appendln(StringBuilder $this$appendln, short value) {
      Intrinsics.checkNotNullParameter($this$appendln, "<this>");
      StringBuilder var10000 = $this$appendln.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return StringsKt.appendln(var10000);
   }

   /** @deprecated */
   @Deprecated(
      message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.",
      replaceWith = @ReplaceWith(
   expression = "appendLine(value)",
   imports = {}
),
      level = DeprecationLevel.WARNING
   )
   @InlineOnly
   private static final StringBuilder appendln(StringBuilder $this$appendln, byte value) {
      Intrinsics.checkNotNullParameter($this$appendln, "<this>");
      StringBuilder var10000 = $this$appendln.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return StringsKt.appendln(var10000);
   }

   /** @deprecated */
   @Deprecated(
      message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.",
      replaceWith = @ReplaceWith(
   expression = "appendLine(value)",
   imports = {}
),
      level = DeprecationLevel.WARNING
   )
   @InlineOnly
   private static final StringBuilder appendln(StringBuilder $this$appendln, long value) {
      Intrinsics.checkNotNullParameter($this$appendln, "<this>");
      StringBuilder var10000 = $this$appendln.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return StringsKt.appendln(var10000);
   }

   /** @deprecated */
   @Deprecated(
      message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.",
      replaceWith = @ReplaceWith(
   expression = "appendLine(value)",
   imports = {}
),
      level = DeprecationLevel.WARNING
   )
   @InlineOnly
   private static final StringBuilder appendln(StringBuilder $this$appendln, float value) {
      Intrinsics.checkNotNullParameter($this$appendln, "<this>");
      StringBuilder var10000 = $this$appendln.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return StringsKt.appendln(var10000);
   }

   /** @deprecated */
   @Deprecated(
      message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.",
      replaceWith = @ReplaceWith(
   expression = "appendLine(value)",
   imports = {}
),
      level = DeprecationLevel.WARNING
   )
   @InlineOnly
   private static final StringBuilder appendln(StringBuilder $this$appendln, double value) {
      Intrinsics.checkNotNullParameter($this$appendln, "<this>");
      StringBuilder var10000 = $this$appendln.append(value);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      return StringsKt.appendln(var10000);
   }

   public StringsKt__StringBuilderJVMKt() {
   }
}
