package kotlin.text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000`\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\t\u001a4\u0010\u0007\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00028\u00000\u0003H\u0082\b¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0014\u0010\t\u001a\u00020\b*\u00020\u0001H\u0087\b¢\u0006\u0004\b\t\u0010\n\u001a\u001c\u0010\t\u001a\u00020\b*\u00020\u00012\u0006\u0010\f\u001a\u00020\u000bH\u0087\b¢\u0006\u0004\b\t\u0010\r\u001a\u0015\u0010\u000e\u001a\u0004\u0018\u00010\b*\u00020\u0001H\u0007¢\u0006\u0004\b\u000e\u0010\n\u001a\u001d\u0010\u000e\u001a\u0004\u0018\u00010\b*\u00020\u00012\u0006\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u000e\u0010\r\u001a\u0014\u0010\u0010\u001a\u00020\u000f*\u00020\u0001H\u0087\b¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u001c\u0010\u0010\u001a\u00020\u000f*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0012H\u0087\b¢\u0006\u0004\b\u0010\u0010\u0014\u001a\u0015\u0010\u0015\u001a\u0004\u0018\u00010\u000f*\u00020\u0001H\u0007¢\u0006\u0004\b\u0015\u0010\u0011\u001a\u001d\u0010\u0015\u001a\u0004\u0018\u00010\u000f*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0012H\u0007¢\u0006\u0004\b\u0015\u0010\u0014\u001a\u0016\u0010\u0017\u001a\u00020\u0016*\u0004\u0018\u00010\u0001H\u0087\b¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u0014\u0010\u001a\u001a\u00020\u0019*\u00020\u0001H\u0087\b¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u001c\u0010\u001a\u001a\u00020\u0019*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0012H\u0087\b¢\u0006\u0004\b\u001a\u0010\u001c\u001a\u0014\u0010\u001e\u001a\u00020\u001d*\u00020\u0001H\u0087\b¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u0015\u0010 \u001a\u0004\u0018\u00010\u001d*\u00020\u0001H\u0007¢\u0006\u0004\b \u0010!\u001a\u0014\u0010#\u001a\u00020\"*\u00020\u0001H\u0087\b¢\u0006\u0004\b#\u0010$\u001a\u0015\u0010%\u001a\u0004\u0018\u00010\"*\u00020\u0001H\u0007¢\u0006\u0004\b%\u0010&\u001a\u0014\u0010'\u001a\u00020\u0012*\u00020\u0001H\u0087\b¢\u0006\u0004\b'\u0010(\u001a\u001c\u0010'\u001a\u00020\u0012*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0012H\u0087\b¢\u0006\u0004\b'\u0010)\u001a\u0014\u0010+\u001a\u00020**\u00020\u0001H\u0087\b¢\u0006\u0004\b+\u0010,\u001a\u001c\u0010+\u001a\u00020**\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0012H\u0087\b¢\u0006\u0004\b+\u0010-\u001a\u0014\u0010/\u001a\u00020.*\u00020\u0001H\u0087\b¢\u0006\u0004\b/\u00100\u001a\u001c\u0010/\u001a\u00020.*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0012H\u0087\b¢\u0006\u0004\b/\u00101\u001a\u001c\u00102\u001a\u00020\u0001*\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u0012H\u0087\b¢\u0006\u0004\b2\u00103\u001a\u001c\u00102\u001a\u00020\u0001*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0087\b¢\u0006\u0004\b2\u00104\u001a\u001c\u00102\u001a\u00020\u0001*\u00020*2\u0006\u0010\u0013\u001a\u00020\u0012H\u0087\b¢\u0006\u0004\b2\u00105\u001a\u001c\u00102\u001a\u00020\u0001*\u00020.2\u0006\u0010\u0013\u001a\u00020\u0012H\u0087\b¢\u0006\u0004\b2\u00106¨\u00067"},
   d2 = {"T", "", "str", "Lkotlin/Function1;", "parse", "screenFloatValue$StringsKt__StringNumberConversionsJVMKt", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "screenFloatValue", "Ljava/math/BigDecimal;", "toBigDecimal", "(Ljava/lang/String;)Ljava/math/BigDecimal;", "Ljava/math/MathContext;", "mathContext", "(Ljava/lang/String;Ljava/math/MathContext;)Ljava/math/BigDecimal;", "toBigDecimalOrNull", "Ljava/math/BigInteger;", "toBigInteger", "(Ljava/lang/String;)Ljava/math/BigInteger;", "", "radix", "(Ljava/lang/String;I)Ljava/math/BigInteger;", "toBigIntegerOrNull", "", "toBoolean", "(Ljava/lang/String;)Z", "", "toByte", "(Ljava/lang/String;)B", "(Ljava/lang/String;I)B", "", "toDouble", "(Ljava/lang/String;)D", "toDoubleOrNull", "(Ljava/lang/String;)Ljava/lang/Double;", "", "toFloat", "(Ljava/lang/String;)F", "toFloatOrNull", "(Ljava/lang/String;)Ljava/lang/Float;", "toInt", "(Ljava/lang/String;)I", "(Ljava/lang/String;I)I", "", "toLong", "(Ljava/lang/String;)J", "(Ljava/lang/String;I)J", "", "toShort", "(Ljava/lang/String;)S", "(Ljava/lang/String;I)S", "toString", "(BI)Ljava/lang/String;", "(II)Ljava/lang/String;", "(JI)Ljava/lang/String;", "(SI)Ljava/lang/String;", "kotlin-stdlib"},
   xs = "kotlin/text/StringsKt"
)
@SourceDebugExtension({"SMAP\nStringNumberConversionsJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StringNumberConversionsJVM.kt\nkotlin/text/StringsKt__StringNumberConversionsJVMKt\n*L\n1#1,274:1\n265#1,7:275\n265#1,7:282\n265#1,7:289\n265#1,7:296\n*S KotlinDebug\n*F\n+ 1 StringNumberConversionsJVM.kt\nkotlin/text/StringsKt__StringNumberConversionsJVMKt\n*L\n142#1:275,7\n149#1:282,7\n229#1:289,7\n240#1:296,7\n*E\n"})
class StringsKt__StringNumberConversionsJVMKt extends StringsKt__StringBuilderKt {
   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final String toString(byte $this$toString, int radix) {
      String var10000 = Integer.toString($this$toString, CharsKt.checkRadix(radix));
      Intrinsics.checkNotNullExpressionValue(var10000, "toString(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final String toString(short $this$toString, int radix) {
      String var10000 = Integer.toString($this$toString, CharsKt.checkRadix(radix));
      Intrinsics.checkNotNullExpressionValue(var10000, "toString(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final String toString(int $this$toString, int radix) {
      String var10000 = Integer.toString($this$toString, CharsKt.checkRadix(radix));
      Intrinsics.checkNotNullExpressionValue(var10000, "toString(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final String toString(long $this$toString, int radix) {
      String var10000 = Long.toString($this$toString, CharsKt.checkRadix(radix));
      Intrinsics.checkNotNullExpressionValue(var10000, "toString(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final boolean toBoolean(String $this$toBoolean) {
      return Boolean.parseBoolean($this$toBoolean);
   }

   @InlineOnly
   private static final byte toByte(String $this$toByte) {
      Intrinsics.checkNotNullParameter($this$toByte, "<this>");
      return Byte.parseByte($this$toByte);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final byte toByte(String $this$toByte, int radix) {
      Intrinsics.checkNotNullParameter($this$toByte, "<this>");
      return Byte.parseByte($this$toByte, CharsKt.checkRadix(radix));
   }

   @InlineOnly
   private static final short toShort(String $this$toShort) {
      Intrinsics.checkNotNullParameter($this$toShort, "<this>");
      return Short.parseShort($this$toShort);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final short toShort(String $this$toShort, int radix) {
      Intrinsics.checkNotNullParameter($this$toShort, "<this>");
      return Short.parseShort($this$toShort, CharsKt.checkRadix(radix));
   }

   @InlineOnly
   private static final int toInt(String $this$toInt) {
      Intrinsics.checkNotNullParameter($this$toInt, "<this>");
      return Integer.parseInt($this$toInt);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final int toInt(String $this$toInt, int radix) {
      Intrinsics.checkNotNullParameter($this$toInt, "<this>");
      return Integer.parseInt($this$toInt, CharsKt.checkRadix(radix));
   }

   @InlineOnly
   private static final long toLong(String $this$toLong) {
      Intrinsics.checkNotNullParameter($this$toLong, "<this>");
      return Long.parseLong($this$toLong);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final long toLong(String $this$toLong, int radix) {
      Intrinsics.checkNotNullParameter($this$toLong, "<this>");
      return Long.parseLong($this$toLong, CharsKt.checkRadix(radix));
   }

   @InlineOnly
   private static final float toFloat(String $this$toFloat) {
      Intrinsics.checkNotNullParameter($this$toFloat, "<this>");
      return Float.parseFloat($this$toFloat);
   }

   @InlineOnly
   private static final double toDouble(String $this$toDouble) {
      Intrinsics.checkNotNullParameter($this$toDouble, "<this>");
      return Double.parseDouble($this$toDouble);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @Nullable
   public static final Float toFloatOrNull(@NotNull String $this$toFloatOrNull) {
      Intrinsics.checkNotNullParameter($this$toFloatOrNull, "<this>");
      int $i$f$screenFloatValue = 0;

      Float p0;
      try {
         Float var10000;
         if (ScreenFloatValueRegEx.value.matches((CharSequence)$this$toFloatOrNull)) {
            int var3 = 0;
            var10000 = Float.parseFloat($this$toFloatOrNull);
         } else {
            var10000 = null;
         }

         p0 = var10000;
      } catch (NumberFormatException var4) {
         p0 = null;
      }

      return p0;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @Nullable
   public static final Double toDoubleOrNull(@NotNull String $this$toDoubleOrNull) {
      Intrinsics.checkNotNullParameter($this$toDoubleOrNull, "<this>");
      int $i$f$screenFloatValue = 0;

      Double p0;
      try {
         Double var10000;
         if (ScreenFloatValueRegEx.value.matches((CharSequence)$this$toDoubleOrNull)) {
            int var3 = 0;
            var10000 = Double.parseDouble($this$toDoubleOrNull);
         } else {
            var10000 = null;
         }

         p0 = var10000;
      } catch (NumberFormatException var4) {
         p0 = null;
      }

      return p0;
   }

   @SinceKotlin(
      version = "1.2"
   )
   @InlineOnly
   private static final BigInteger toBigInteger(String $this$toBigInteger) {
      Intrinsics.checkNotNullParameter($this$toBigInteger, "<this>");
      return new BigInteger($this$toBigInteger);
   }

   @SinceKotlin(
      version = "1.2"
   )
   @InlineOnly
   private static final BigInteger toBigInteger(String $this$toBigInteger, int radix) {
      Intrinsics.checkNotNullParameter($this$toBigInteger, "<this>");
      return new BigInteger($this$toBigInteger, CharsKt.checkRadix(radix));
   }

   @SinceKotlin(
      version = "1.2"
   )
   @Nullable
   public static final BigInteger toBigIntegerOrNull(@NotNull String $this$toBigIntegerOrNull) {
      Intrinsics.checkNotNullParameter($this$toBigIntegerOrNull, "<this>");
      return StringsKt.toBigIntegerOrNull($this$toBigIntegerOrNull, 10);
   }

   @SinceKotlin(
      version = "1.2"
   )
   @Nullable
   public static final BigInteger toBigIntegerOrNull(@NotNull String $this$toBigIntegerOrNull, int radix) {
      Intrinsics.checkNotNullParameter($this$toBigIntegerOrNull, "<this>");
      CharsKt.checkRadix(radix);
      int length = $this$toBigIntegerOrNull.length();
      switch (length) {
         case 0:
            return null;
         case 1:
            if (CharsKt.digitOf($this$toBigIntegerOrNull.charAt(0), radix) < 0) {
               return null;
            }
            break;
         default:
            int start = $this$toBigIntegerOrNull.charAt(0) == '-' ? 1 : 0;

            for(int index = start; index < length; ++index) {
               if (CharsKt.digitOf($this$toBigIntegerOrNull.charAt(index), radix) < 0) {
                  return null;
               }
            }
      }

      return new BigInteger($this$toBigIntegerOrNull, CharsKt.checkRadix(radix));
   }

   @SinceKotlin(
      version = "1.2"
   )
   @InlineOnly
   private static final BigDecimal toBigDecimal(String $this$toBigDecimal) {
      Intrinsics.checkNotNullParameter($this$toBigDecimal, "<this>");
      return new BigDecimal($this$toBigDecimal);
   }

   @SinceKotlin(
      version = "1.2"
   )
   @InlineOnly
   private static final BigDecimal toBigDecimal(String $this$toBigDecimal, MathContext mathContext) {
      Intrinsics.checkNotNullParameter($this$toBigDecimal, "<this>");
      Intrinsics.checkNotNullParameter(mathContext, "mathContext");
      return new BigDecimal($this$toBigDecimal, mathContext);
   }

   @SinceKotlin(
      version = "1.2"
   )
   @Nullable
   public static final BigDecimal toBigDecimalOrNull(@NotNull String $this$toBigDecimalOrNull) {
      Intrinsics.checkNotNullParameter($this$toBigDecimalOrNull, "<this>");
      int $i$f$screenFloatValue = 0;

      BigDecimal it;
      try {
         BigDecimal var10000;
         if (ScreenFloatValueRegEx.value.matches((CharSequence)$this$toBigDecimalOrNull)) {
            int var3 = 0;
            var10000 = new BigDecimal($this$toBigDecimalOrNull);
         } else {
            var10000 = null;
         }

         it = var10000;
      } catch (NumberFormatException var4) {
         it = null;
      }

      return it;
   }

   @SinceKotlin(
      version = "1.2"
   )
   @Nullable
   public static final BigDecimal toBigDecimalOrNull(@NotNull String $this$toBigDecimalOrNull, @NotNull MathContext mathContext) {
      Intrinsics.checkNotNullParameter($this$toBigDecimalOrNull, "<this>");
      Intrinsics.checkNotNullParameter(mathContext, "mathContext");
      int $i$f$screenFloatValue = 0;

      BigDecimal var5;
      try {
         BigDecimal var10000;
         if (ScreenFloatValueRegEx.value.matches((CharSequence)$this$toBigDecimalOrNull)) {
            int var4 = 0;
            var10000 = new BigDecimal($this$toBigDecimalOrNull, mathContext);
         } else {
            var10000 = null;
         }

         var5 = var10000;
      } catch (NumberFormatException var6) {
         var5 = null;
      }

      return var5;
   }

   private static final Object screenFloatValue$StringsKt__StringNumberConversionsJVMKt(String str, Function1 parse) {
      int $i$f$screenFloatValue = 0;

      Object var3;
      try {
         var3 = ScreenFloatValueRegEx.value.matches((CharSequence)str) ? parse.invoke(str) : null;
      } catch (NumberFormatException var5) {
         var3 = null;
      }

      return var3;
   }

   public StringsKt__StringNumberConversionsJVMKt() {
   }
}
