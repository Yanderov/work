package kotlin.text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000D\n\u0002\u0010\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001c\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003*\u00020\u0000H\u0007¢\u0006\u0004\b\u0006\u0010\u0007\u001a=\u0010\f\u001a\u0004\u0018\u00010\u0003\"\u000e\b\u0000\u0010\t*\b\u0012\u0004\u0012\u00028\u00000\b*\u00020\u00002\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00028\u00000\nH\u0087\bø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a1\u0010\u0011\u001a\u0004\u0018\u00010\u0003*\u00020\u00002\u001a\u0010\u0010\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00030\u000ej\n\u0012\u0006\b\u0000\u0012\u00020\u0003`\u000fH\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u0003*\u00020\u0000H\u0007¢\u0006\u0004\b\u0013\u0010\u0007\u001a=\u0010\u0014\u001a\u0004\u0018\u00010\u0003\"\u000e\b\u0000\u0010\t*\b\u0012\u0004\u0012\u00028\u00000\b*\u00020\u00002\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00028\u00000\nH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\r\u001a1\u0010\u0015\u001a\u0004\u0018\u00010\u0003*\u00020\u00002\u001a\u0010\u0010\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00030\u000ej\n\u0012\u0006\b\u0000\u0012\u00020\u0003`\u000fH\u0007¢\u0006\u0004\b\u0015\u0010\u0012\u001a+\u0010\u0019\u001a\u00020\u0016*\u00020\u00002\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00160\nH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a+\u0010\u0019\u001a\u00020\u001a*\u00020\u00002\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001a0\nH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u001d*\u00020\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006 "},
   d2 = {"", "", "index", "", "elementAt", "(Ljava/lang/CharSequence;I)C", "max", "(Ljava/lang/CharSequence;)Ljava/lang/Character;", "", "R", "Lkotlin/Function1;", "selector", "maxBy", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/Character;", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "comparator", "maxWith", "(Ljava/lang/CharSequence;Ljava/util/Comparator;)Ljava/lang/Character;", "min", "minBy", "minWith", "Ljava/math/BigDecimal;", "sumOfBigDecimal", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "sumOf", "Ljava/math/BigInteger;", "sumOfBigInteger", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "Ljava/util/SortedSet;", "toSortedSet", "(Ljava/lang/CharSequence;)Ljava/util/SortedSet;", "kotlin-stdlib"},
   xs = "kotlin/text/StringsKt"
)
@SourceDebugExtension({"SMAP\n_StringsJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _StringsJvm.kt\nkotlin/text/StringsKt___StringsJvmKt\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,108:1\n1244#2,14:109\n1526#2,14:123\n*S KotlinDebug\n*F\n+ 1 _StringsJvm.kt\nkotlin/text/StringsKt___StringsJvmKt\n*L\n45#1:109,14\n66#1:123,14\n*E\n"})
class StringsKt___StringsJvmKt extends StringsKt__StringsKt {
   @InlineOnly
   private static final char elementAt(CharSequence $this$elementAt, int index) {
      Intrinsics.checkNotNullParameter($this$elementAt, "<this>");
      return $this$elementAt.charAt(index);
   }

   @NotNull
   public static final SortedSet toSortedSet(@NotNull CharSequence $this$toSortedSet) {
      Intrinsics.checkNotNullParameter($this$toSortedSet, "<this>");
      return (SortedSet)StringsKt.toCollection($this$toSortedSet, (Collection)(new TreeSet()));
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use maxOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxOrNull()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   public static final Character max(CharSequence $this$max) {
      Intrinsics.checkNotNullParameter($this$max, "<this>");
      return StringsKt.maxOrNull($this$max);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use maxByOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxByOrNull(selector)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   public static final Character maxBy(CharSequence $this$maxBy, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$maxBy, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      int $i$f$maxBy = 0;
      CharSequence $this$maxByOrNull$iv = $this$maxBy;
      int $i$f$maxByOrNull = 0;
      Character var10000;
      if ($this$maxBy.length() == 0) {
         var10000 = null;
      } else {
         char maxElem$iv = $this$maxBy.charAt(0);
         int lastIndex$iv = StringsKt.getLastIndex($this$maxBy);
         if (lastIndex$iv == 0) {
            var10000 = maxElem$iv;
         } else {
            Comparable maxValue$iv = (Comparable)selector.invoke(maxElem$iv);
            int i$iv = 1;
            if (i$iv <= lastIndex$iv) {
               while(true) {
                  char e$iv = $this$maxByOrNull$iv.charAt(i$iv);
                  Comparable v$iv = (Comparable)selector.invoke(e$iv);
                  if (maxValue$iv.compareTo(v$iv) < 0) {
                     maxElem$iv = e$iv;
                     maxValue$iv = v$iv;
                  }

                  if (i$iv == lastIndex$iv) {
                     break;
                  }

                  ++i$iv;
               }
            }

            var10000 = maxElem$iv;
         }
      }

      return var10000;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use maxWithOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxWithOrNull(comparator)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   public static final Character maxWith(CharSequence $this$maxWith, Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$maxWith, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return StringsKt.maxWithOrNull($this$maxWith, comparator);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use minOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minOrNull()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   public static final Character min(CharSequence $this$min) {
      Intrinsics.checkNotNullParameter($this$min, "<this>");
      return StringsKt.minOrNull($this$min);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use minByOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minByOrNull(selector)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   public static final Character minBy(CharSequence $this$minBy, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$minBy, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      int $i$f$minBy = 0;
      CharSequence $this$minByOrNull$iv = $this$minBy;
      int $i$f$minByOrNull = 0;
      Character var10000;
      if ($this$minBy.length() == 0) {
         var10000 = null;
      } else {
         char minElem$iv = $this$minBy.charAt(0);
         int lastIndex$iv = StringsKt.getLastIndex($this$minBy);
         if (lastIndex$iv == 0) {
            var10000 = minElem$iv;
         } else {
            Comparable minValue$iv = (Comparable)selector.invoke(minElem$iv);
            int i$iv = 1;
            if (i$iv <= lastIndex$iv) {
               while(true) {
                  char e$iv = $this$minByOrNull$iv.charAt(i$iv);
                  Comparable v$iv = (Comparable)selector.invoke(e$iv);
                  if (minValue$iv.compareTo(v$iv) > 0) {
                     minElem$iv = e$iv;
                     minValue$iv = v$iv;
                  }

                  if (i$iv == lastIndex$iv) {
                     break;
                  }

                  ++i$iv;
               }
            }

            var10000 = minElem$iv;
         }
      }

      return var10000;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use minWithOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minWithOrNull(comparator)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   public static final Character minWith(CharSequence $this$minWith, Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$minWith, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return StringsKt.minWithOrNull($this$minWith, comparator);
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @JvmName(
      name = "sumOfBigDecimal"
   )
   @InlineOnly
   private static final BigDecimal sumOfBigDecimal(CharSequence $this$sumOf, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$sumOf, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      BigDecimal var10000 = BigDecimal.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var10000, "valueOf(...)");
      BigDecimal sum = var10000;

      for(int var3 = 0; var3 < $this$sumOf.length(); ++var3) {
         char element = $this$sumOf.charAt(var3);
         var10000 = sum.add((BigDecimal)selector.invoke(element));
         Intrinsics.checkNotNullExpressionValue(var10000, "add(...)");
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @JvmName(
      name = "sumOfBigInteger"
   )
   @InlineOnly
   private static final BigInteger sumOfBigInteger(CharSequence $this$sumOf, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$sumOf, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      BigInteger var10000 = BigInteger.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var10000, "valueOf(...)");
      BigInteger sum = var10000;

      for(int var3 = 0; var3 < $this$sumOf.length(); ++var3) {
         char element = $this$sumOf.charAt(var3);
         var10000 = sum.add((BigInteger)selector.invoke(element));
         Intrinsics.checkNotNullExpressionValue(var10000, "add(...)");
         sum = var10000;
      }

      return sum;
   }

   public StringsKt___StringsJvmKt() {
   }
}
