package kotlin.sequences;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
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
   d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0004\n\u0002\u0010\u000f\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a/\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\u0006\u0012\u0002\b\u00030\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001aC\u0010\t\u001a\u00028\u0000\"\u0010\b\u0000\u0010\u0007*\n\u0012\u0006\b\u0000\u0012\u00028\u00010\u0006\"\u0004\b\u0001\u0010\u0000*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\b\u001a\u00028\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002¢\u0006\u0004\b\t\u0010\n\u001a+\u0010\r\u001a\u0004\u0018\u00018\u0000\"\u000e\b\u0000\u0010\f*\b\u0012\u0004\u0012\u00028\u00000\u000b*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0007¢\u0006\u0004\b\r\u0010\u000e\u001a\u001b\u0010\r\u001a\u0004\u0018\u00010\u000f*\b\u0012\u0004\u0012\u00020\u000f0\u0001H\u0007¢\u0006\u0004\b\r\u0010\u0010\u001a\u001b\u0010\r\u001a\u0004\u0018\u00010\u0011*\b\u0012\u0004\u0012\u00020\u00110\u0001H\u0007¢\u0006\u0004\b\r\u0010\u0012\u001aI\u0010\u0015\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\f\"\u000e\b\u0001\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00010\u000b*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0013H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a=\u0010\u001a\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u00028\u00000\u00012\u001a\u0010\u0019\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00000\u0017j\n\u0012\u0006\b\u0000\u0012\u00028\u0000`\u0018H\u0007¢\u0006\u0004\b\u001a\u0010\u001b\u001a+\u0010\u001c\u001a\u0004\u0018\u00018\u0000\"\u000e\b\u0000\u0010\f*\b\u0012\u0004\u0012\u00028\u00000\u000b*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0007¢\u0006\u0004\b\u001c\u0010\u000e\u001a\u001b\u0010\u001c\u001a\u0004\u0018\u00010\u000f*\b\u0012\u0004\u0012\u00020\u000f0\u0001H\u0007¢\u0006\u0004\b\u001c\u0010\u0010\u001a\u001b\u0010\u001c\u001a\u0004\u0018\u00010\u0011*\b\u0012\u0004\u0012\u00020\u00110\u0001H\u0007¢\u0006\u0004\b\u001c\u0010\u0012\u001aI\u0010\u001d\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\f\"\u000e\b\u0001\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00010\u000b*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0013H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0016\u001a=\u0010\u001e\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u00028\u00000\u00012\u001a\u0010\u0019\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00000\u0017j\n\u0012\u0006\b\u0000\u0012\u00028\u0000`\u0018H\u0007¢\u0006\u0004\b\u001e\u0010\u001b\u001a7\u0010\"\u001a\u00020\u001f\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u001f0\u0013H\u0087\bø\u0001\u0000¢\u0006\u0004\b \u0010!\u001a7\u0010\"\u001a\u00020#\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020#0\u0013H\u0087\bø\u0001\u0000¢\u0006\u0004\b$\u0010%\u001a-\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00000&\"\u000e\b\u0000\u0010\f*\b\u0012\u0004\u0012\u00028\u00000\u000b*\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\u0004\b'\u0010(\u001a?\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00000&\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u00028\u00000\u00012\u001a\u0010\u0019\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00000\u0017j\n\u0012\u0006\b\u0000\u0012\u00028\u0000`\u0018¢\u0006\u0004\b'\u0010)\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006*"},
   d2 = {"R", "Lkotlin/sequences/Sequence;", "Ljava/lang/Class;", "klass", "filterIsInstance", "(Lkotlin/sequences/Sequence;Ljava/lang/Class;)Lkotlin/sequences/Sequence;", "", "C", "destination", "filterIsInstanceTo", "(Lkotlin/sequences/Sequence;Ljava/util/Collection;Ljava/lang/Class;)Ljava/util/Collection;", "", "T", "max", "(Lkotlin/sequences/Sequence;)Ljava/lang/Comparable;", "", "(Lkotlin/sequences/Sequence;)Ljava/lang/Double;", "", "(Lkotlin/sequences/Sequence;)Ljava/lang/Float;", "Lkotlin/Function1;", "selector", "maxBy", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "comparator", "maxWith", "(Lkotlin/sequences/Sequence;Ljava/util/Comparator;)Ljava/lang/Object;", "min", "minBy", "minWith", "Ljava/math/BigDecimal;", "sumOfBigDecimal", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "sumOf", "Ljava/math/BigInteger;", "sumOfBigInteger", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "Ljava/util/SortedSet;", "toSortedSet", "(Lkotlin/sequences/Sequence;)Ljava/util/SortedSet;", "(Lkotlin/sequences/Sequence;Ljava/util/Comparator;)Ljava/util/SortedSet;", "kotlin-stdlib"},
   xs = "kotlin/sequences/SequencesKt"
)
@SourceDebugExtension({"SMAP\n_SequencesJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _SequencesJvm.kt\nkotlin/sequences/SequencesKt___SequencesJvmKt\n+ 2 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,172:1\n1437#2,14:173\n1843#2,14:187\n*S KotlinDebug\n*F\n+ 1 _SequencesJvm.kt\nkotlin/sequences/SequencesKt___SequencesJvmKt\n*L\n89#1:173,14\n126#1:187,14\n*E\n"})
class SequencesKt___SequencesJvmKt extends SequencesKt__SequencesKt {
   @NotNull
   public static final Sequence filterIsInstance(@NotNull Sequence $this$filterIsInstance, @NotNull final Class klass) {
      Intrinsics.checkNotNullParameter($this$filterIsInstance, "<this>");
      Intrinsics.checkNotNullParameter(klass, "klass");
      Sequence var10000 = SequencesKt.filter($this$filterIsInstance, new Function1(klass) {
         public final Boolean invoke(Object it) {
            return klass.isInstance(it);
         }
      });
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type kotlin.sequences.Sequence<R of kotlin.sequences.SequencesKt___SequencesJvmKt.filterIsInstance>");
      return var10000;
   }

   @NotNull
   public static final Collection filterIsInstanceTo(@NotNull Sequence $this$filterIsInstanceTo, @NotNull Collection destination, @NotNull Class klass) {
      Intrinsics.checkNotNullParameter($this$filterIsInstanceTo, "<this>");
      Intrinsics.checkNotNullParameter(destination, "destination");
      Intrinsics.checkNotNullParameter(klass, "klass");

      for(Object element : $this$filterIsInstanceTo) {
         if (klass.isInstance(element)) {
            destination.add(element);
         }
      }

      return destination;
   }

   @NotNull
   public static final SortedSet toSortedSet(@NotNull Sequence $this$toSortedSet) {
      Intrinsics.checkNotNullParameter($this$toSortedSet, "<this>");
      return (SortedSet)SequencesKt.toCollection($this$toSortedSet, (Collection)(new TreeSet()));
   }

   @NotNull
   public static final SortedSet toSortedSet(@NotNull Sequence $this$toSortedSet, @NotNull Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$toSortedSet, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return (SortedSet)SequencesKt.toCollection($this$toSortedSet, (Collection)(new TreeSet(comparator)));
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
   @SinceKotlin(
      version = "1.1"
   )
   public static final Double max(Sequence $this$max) {
      Intrinsics.checkNotNullParameter($this$max, "<this>");
      return SequencesKt.maxOrNull($this$max);
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
   @SinceKotlin(
      version = "1.1"
   )
   public static final Float max(Sequence $this$max) {
      Intrinsics.checkNotNullParameter($this$max, "<this>");
      return SequencesKt.maxOrNull($this$max);
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
   public static final Comparable max(Sequence $this$max) {
      Intrinsics.checkNotNullParameter($this$max, "<this>");
      return SequencesKt.maxOrNull($this$max);
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
   public static final Object maxBy(Sequence $this$maxBy, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$maxBy, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      int $i$f$maxBy = 0;
      int $i$f$maxByOrNull = 0;
      Iterator iterator$iv = $this$maxBy.iterator();
      Object var10000;
      if (!iterator$iv.hasNext()) {
         var10000 = null;
      } else {
         Object maxElem$iv = iterator$iv.next();
         if (!iterator$iv.hasNext()) {
            var10000 = maxElem$iv;
         } else {
            Comparable maxValue$iv = (Comparable)selector.invoke(maxElem$iv);

            do {
               Object e$iv = iterator$iv.next();
               Comparable v$iv = (Comparable)selector.invoke(e$iv);
               if (maxValue$iv.compareTo(v$iv) < 0) {
                  maxElem$iv = e$iv;
                  maxValue$iv = v$iv;
               }
            } while(iterator$iv.hasNext());

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
   public static final Object maxWith(Sequence $this$maxWith, Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$maxWith, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return SequencesKt.maxWithOrNull($this$maxWith, comparator);
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
   @SinceKotlin(
      version = "1.1"
   )
   public static final Double min(Sequence $this$min) {
      Intrinsics.checkNotNullParameter($this$min, "<this>");
      return SequencesKt.minOrNull($this$min);
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
   @SinceKotlin(
      version = "1.1"
   )
   public static final Float min(Sequence $this$min) {
      Intrinsics.checkNotNullParameter($this$min, "<this>");
      return SequencesKt.minOrNull($this$min);
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
   public static final Comparable min(Sequence $this$min) {
      Intrinsics.checkNotNullParameter($this$min, "<this>");
      return SequencesKt.minOrNull($this$min);
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
   public static final Object minBy(Sequence $this$minBy, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$minBy, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      int $i$f$minBy = 0;
      int $i$f$minByOrNull = 0;
      Iterator iterator$iv = $this$minBy.iterator();
      Object var10000;
      if (!iterator$iv.hasNext()) {
         var10000 = null;
      } else {
         Object minElem$iv = iterator$iv.next();
         if (!iterator$iv.hasNext()) {
            var10000 = minElem$iv;
         } else {
            Comparable minValue$iv = (Comparable)selector.invoke(minElem$iv);

            do {
               Object e$iv = iterator$iv.next();
               Comparable v$iv = (Comparable)selector.invoke(e$iv);
               if (minValue$iv.compareTo(v$iv) > 0) {
                  minElem$iv = e$iv;
                  minValue$iv = v$iv;
               }
            } while(iterator$iv.hasNext());

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
   public static final Object minWith(Sequence $this$minWith, Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$minWith, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return SequencesKt.minWithOrNull($this$minWith, comparator);
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @JvmName(
      name = "sumOfBigDecimal"
   )
   @InlineOnly
   private static final BigDecimal sumOfBigDecimal(Sequence $this$sumOf, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$sumOf, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      BigDecimal var10000 = BigDecimal.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var10000, "valueOf(...)");
      BigDecimal sum = var10000;

      for(Object element : $this$sumOf) {
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
   private static final BigInteger sumOfBigInteger(Sequence $this$sumOf, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$sumOf, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      BigInteger var10000 = BigInteger.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var10000, "valueOf(...)");
      BigInteger sum = var10000;

      for(Object element : $this$sumOf) {
         var10000 = sum.add((BigInteger)selector.invoke(element));
         Intrinsics.checkNotNullExpressionValue(var10000, "add(...)");
         sum = var10000;
      }

      return sum;
   }

   public SequencesKt___SequencesJvmKt() {
   }
}
