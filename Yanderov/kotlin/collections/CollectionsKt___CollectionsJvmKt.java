package kotlin.collections;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
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
   d1 = {"\u0000h\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u001f\n\u0002\b\u0004\n\u0002\u0010\u000f\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a/\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u0000*\u0006\u0012\u0002\b\u00030\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001aC\u0010\n\u001a\u00028\u0000\"\u0010\b\u0000\u0010\b*\n\u0012\u0006\b\u0000\u0012\u00028\u00010\u0007\"\u0004\b\u0001\u0010\u0000*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\t\u001a\u00028\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002¢\u0006\u0004\b\n\u0010\u000b\u001a+\u0010\u000e\u001a\u0004\u0018\u00018\u0000\"\u000e\b\u0000\u0010\r*\b\u0012\u0004\u0012\u00028\u00000\f*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0007¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u001b\u0010\u000e\u001a\u0004\u0018\u00010\u0010*\b\u0012\u0004\u0012\u00020\u00100\u0001H\u0007¢\u0006\u0004\b\u000e\u0010\u0011\u001a\u001b\u0010\u000e\u001a\u0004\u0018\u00010\u0012*\b\u0012\u0004\u0012\u00020\u00120\u0001H\u0007¢\u0006\u0004\b\u000e\u0010\u0013\u001aI\u0010\u0016\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\r\"\u000e\b\u0001\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00010\f*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a=\u0010\u001b\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u00028\u00000\u00012\u001a\u0010\u001a\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00000\u0018j\n\u0012\u0006\b\u0000\u0012\u00028\u0000`\u0019H\u0007¢\u0006\u0004\b\u001b\u0010\u001c\u001a+\u0010\u001d\u001a\u0004\u0018\u00018\u0000\"\u000e\b\u0000\u0010\r*\b\u0012\u0004\u0012\u00028\u00000\f*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0007¢\u0006\u0004\b\u001d\u0010\u000f\u001a\u001b\u0010\u001d\u001a\u0004\u0018\u00010\u0010*\b\u0012\u0004\u0012\u00020\u00100\u0001H\u0007¢\u0006\u0004\b\u001d\u0010\u0011\u001a\u001b\u0010\u001d\u001a\u0004\u0018\u00010\u0012*\b\u0012\u0004\u0012\u00020\u00120\u0001H\u0007¢\u0006\u0004\b\u001d\u0010\u0013\u001aI\u0010\u001e\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\r\"\u000e\b\u0001\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00010\f*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0017\u001a=\u0010\u001f\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u00028\u00000\u00012\u001a\u0010\u001a\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00000\u0018j\n\u0012\u0006\b\u0000\u0012\u00028\u0000`\u0019H\u0007¢\u0006\u0004\b\u001f\u0010\u001c\u001a\u001d\u0010\"\u001a\u00020!\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u00028\u00000 ¢\u0006\u0004\b\"\u0010#\u001a7\u0010'\u001a\u00020$\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020$0\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001a7\u0010'\u001a\u00020(\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020(0\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a-\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000+\"\u000e\b\u0000\u0010\r*\b\u0012\u0004\u0012\u00028\u00000\f*\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\u0004\b,\u0010-\u001a?\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000+\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u00028\u00000\u00012\u001a\u0010\u001a\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00000\u0018j\n\u0012\u0006\b\u0000\u0012\u00028\u0000`\u0019¢\u0006\u0004\b,\u0010.\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006/"},
   d2 = {"R", "", "Ljava/lang/Class;", "klass", "", "filterIsInstance", "(Ljava/lang/Iterable;Ljava/lang/Class;)Ljava/util/List;", "", "C", "destination", "filterIsInstanceTo", "(Ljava/lang/Iterable;Ljava/util/Collection;Ljava/lang/Class;)Ljava/util/Collection;", "", "T", "max", "(Ljava/lang/Iterable;)Ljava/lang/Comparable;", "", "(Ljava/lang/Iterable;)Ljava/lang/Double;", "", "(Ljava/lang/Iterable;)Ljava/lang/Float;", "Lkotlin/Function1;", "selector", "maxBy", "(Ljava/lang/Iterable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "comparator", "maxWith", "(Ljava/lang/Iterable;Ljava/util/Comparator;)Ljava/lang/Object;", "min", "minBy", "minWith", "", "", "reverse", "(Ljava/util/List;)V", "Ljava/math/BigDecimal;", "sumOfBigDecimal", "(Ljava/lang/Iterable;Lkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "sumOf", "Ljava/math/BigInteger;", "sumOfBigInteger", "(Ljava/lang/Iterable;Lkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "Ljava/util/SortedSet;", "toSortedSet", "(Ljava/lang/Iterable;)Ljava/util/SortedSet;", "(Ljava/lang/Iterable;Ljava/util/Comparator;)Ljava/util/SortedSet;", "kotlin-stdlib"},
   xs = "kotlin/collections/CollectionsKt"
)
@SourceDebugExtension({"SMAP\n_CollectionsJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _CollectionsJvm.kt\nkotlin/collections/CollectionsKt___CollectionsJvmKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,168:1\n1971#2,14:169\n2341#2,14:183\n*S KotlinDebug\n*F\n+ 1 _CollectionsJvm.kt\nkotlin/collections/CollectionsKt___CollectionsJvmKt\n*L\n89#1:169,14\n126#1:183,14\n*E\n"})
class CollectionsKt___CollectionsJvmKt extends CollectionsKt__ReversedViewsKt {
   @NotNull
   public static final List filterIsInstance(@NotNull Iterable $this$filterIsInstance, @NotNull Class klass) {
      Intrinsics.checkNotNullParameter($this$filterIsInstance, "<this>");
      Intrinsics.checkNotNullParameter(klass, "klass");
      return (List)CollectionsKt.filterIsInstanceTo($this$filterIsInstance, (Collection)(new ArrayList()), klass);
   }

   @NotNull
   public static final Collection filterIsInstanceTo(@NotNull Iterable $this$filterIsInstanceTo, @NotNull Collection destination, @NotNull Class klass) {
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

   public static final void reverse(@NotNull List $this$reverse) {
      Intrinsics.checkNotNullParameter($this$reverse, "<this>");
      Collections.reverse($this$reverse);
   }

   @NotNull
   public static final SortedSet toSortedSet(@NotNull Iterable $this$toSortedSet) {
      Intrinsics.checkNotNullParameter($this$toSortedSet, "<this>");
      return (SortedSet)CollectionsKt.toCollection($this$toSortedSet, (Collection)(new TreeSet()));
   }

   @NotNull
   public static final SortedSet toSortedSet(@NotNull Iterable $this$toSortedSet, @NotNull Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$toSortedSet, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return (SortedSet)CollectionsKt.toCollection($this$toSortedSet, (Collection)(new TreeSet(comparator)));
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
   public static final Double max(Iterable $this$max) {
      Intrinsics.checkNotNullParameter($this$max, "<this>");
      return CollectionsKt.maxOrNull($this$max);
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
   public static final Float max(Iterable $this$max) {
      Intrinsics.checkNotNullParameter($this$max, "<this>");
      return CollectionsKt.maxOrNull($this$max);
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
   public static final Comparable max(Iterable $this$max) {
      Intrinsics.checkNotNullParameter($this$max, "<this>");
      return CollectionsKt.maxOrNull($this$max);
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
   public static final Object maxBy(Iterable $this$maxBy, Function1 selector) {
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
   public static final Object maxWith(Iterable $this$maxWith, Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$maxWith, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return CollectionsKt.maxWithOrNull($this$maxWith, comparator);
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
   public static final Double min(Iterable $this$min) {
      Intrinsics.checkNotNullParameter($this$min, "<this>");
      return CollectionsKt.minOrNull($this$min);
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
   public static final Float min(Iterable $this$min) {
      Intrinsics.checkNotNullParameter($this$min, "<this>");
      return CollectionsKt.minOrNull($this$min);
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
   public static final Comparable min(Iterable $this$min) {
      Intrinsics.checkNotNullParameter($this$min, "<this>");
      return CollectionsKt.minOrNull($this$min);
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
   public static final Object minBy(Iterable $this$minBy, Function1 selector) {
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
   public static final Object minWith(Iterable $this$minWith, Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$minWith, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return CollectionsKt.minWithOrNull($this$minWith, comparator);
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @JvmName(
      name = "sumOfBigDecimal"
   )
   @InlineOnly
   private static final BigDecimal sumOfBigDecimal(Iterable $this$sumOf, Function1 selector) {
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
   private static final BigInteger sumOfBigInteger(Iterable $this$sumOf, Function1 selector) {
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

   public CollectionsKt___CollectionsJvmKt() {
   }
}
