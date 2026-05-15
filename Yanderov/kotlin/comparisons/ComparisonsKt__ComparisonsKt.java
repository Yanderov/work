package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aE\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005\"\u0004\b\u0000\u0010\u00002\u001a\b\u0004\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a[\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005\"\u0004\b\u0000\u0010\u000026\u0010\t\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00020\u00010\b\"\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00020\u0001¢\u0006\u0004\b\u0006\u0010\n\u001aa\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u000b2\u001a\u0010\f\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00010\u0004j\n\u0012\u0006\b\u0000\u0012\u00028\u0001`\u00052\u0014\b\u0004\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\r\u001aE\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005\"\u0004\b\u0000\u0010\u00002\u001a\b\u0004\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u0007\u001aa\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u000b2\u001a\u0010\f\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00010\u0004j\n\u0012\u0006\b\u0000\u0012\u00028\u0001`\u00052\u0014\b\u0004\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\r\u001a/\u0010\u0012\u001a\u00020\u0011\"\f\b\u0000\u0010\u0000*\u0006\u0012\u0002\b\u00030\u00022\b\u0010\u000f\u001a\u0004\u0018\u00018\u00002\b\u0010\u0010\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001aC\u0010\u0014\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00028\u00002\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a[\u0010\u0014\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00028\u000026\u0010\t\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00020\u00010\b\"\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00020\u0001¢\u0006\u0004\b\u0014\u0010\u0016\u001a_\u0010\u0014\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u000b2\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00028\u00002\u001a\u0010\f\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00010\u0004j\n\u0012\u0006\b\u0000\u0012\u00028\u0001`\u00052\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0017\u001aG\u0010\u0019\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00028\u00002 \u0010\t\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00020\u00010\bH\u0002¢\u0006\u0004\b\u0018\u0010\u0016\u001a-\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005\"\u000e\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0004\b\u001a\u0010\u001b\u001a4\u0010\u001c\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0004j\n\u0012\u0006\u0012\u0004\u0018\u00018\u0000`\u0005\"\u000e\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0087\b¢\u0006\u0004\b\u001c\u0010\u001b\u001aG\u0010\u001c\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0004j\n\u0012\u0006\u0012\u0004\u0018\u00018\u0000`\u0005\"\b\b\u0000\u0010\u0000*\u00020\u001d2\u001a\u0010\f\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00000\u0004j\n\u0012\u0006\b\u0000\u0012\u00028\u0000`\u0005¢\u0006\u0004\b\u001c\u0010\u001e\u001a4\u0010\u001f\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0004j\n\u0012\u0006\u0012\u0004\u0018\u00018\u0000`\u0005\"\u000e\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0087\b¢\u0006\u0004\b\u001f\u0010\u001b\u001aG\u0010\u001f\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0004j\n\u0012\u0006\u0012\u0004\u0018\u00018\u0000`\u0005\"\b\b\u0000\u0010\u0000*\u00020\u001d2\u001a\u0010\f\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00000\u0004j\n\u0012\u0006\b\u0000\u0012\u00028\u0000`\u0005¢\u0006\u0004\b\u001f\u0010\u001e\u001a-\u0010 \u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005\"\u000e\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0004\b \u0010\u001b\u001a7\u0010!\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005\"\u0004\b\u0000\u0010\u0000*\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005¢\u0006\u0004\b!\u0010\u001e\u001aV\u0010\"\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005\"\u0004\b\u0000\u0010\u0000*\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u00052\u001a\u0010\f\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00000\u0004j\n\u0012\u0006\b\u0000\u0012\u00028\u0000`\u0005H\u0086\u0004¢\u0006\u0004\b\"\u0010#\u001aY\u0010$\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005\"\u0004\b\u0000\u0010\u0000*\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u00052\u001a\b\u0004\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b$\u0010\r\u001au\u0010$\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u000b*\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u00052\u001a\u0010\f\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00010\u0004j\n\u0012\u0006\b\u0000\u0012\u00028\u0001`\u00052\u0014\b\u0004\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b$\u0010%\u001aY\u0010&\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005\"\u0004\b\u0000\u0010\u0000*\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u00052\u001a\b\u0004\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b&\u0010\r\u001au\u0010&\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u000b*\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u00052\u001a\u0010\f\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00010\u0004j\n\u0012\u0006\b\u0000\u0012\u00028\u0001`\u00052\u0014\b\u0004\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b&\u0010%\u001aw\u0010+\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005\"\u0004\b\u0000\u0010\u0000*\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u000528\b\u0004\u0010*\u001a2\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110'H\u0087\bø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001aV\u0010-\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005\"\u0004\b\u0000\u0010\u0000*\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u00052\u001a\u0010\f\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00000\u0004j\n\u0012\u0006\b\u0000\u0012\u00028\u0000`\u0005H\u0086\u0004¢\u0006\u0004\b-\u0010#\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006."},
   d2 = {"T", "Lkotlin/Function1;", "", "selector", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "compareBy", "(Lkotlin/jvm/functions/Function1;)Ljava/util/Comparator;", "", "selectors", "([Lkotlin/jvm/functions/Function1;)Ljava/util/Comparator;", "K", "comparator", "(Ljava/util/Comparator;Lkotlin/jvm/functions/Function1;)Ljava/util/Comparator;", "compareByDescending", "a", "b", "", "compareValues", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)I", "compareValuesBy", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)I", "(Ljava/lang/Object;Ljava/lang/Object;[Lkotlin/jvm/functions/Function1;)I", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;Lkotlin/jvm/functions/Function1;)I", "compareValuesByImpl$ComparisonsKt__ComparisonsKt", "compareValuesByImpl", "naturalOrder", "()Ljava/util/Comparator;", "nullsFirst", "", "(Ljava/util/Comparator;)Ljava/util/Comparator;", "nullsLast", "reverseOrder", "reversed", "then", "(Ljava/util/Comparator;Ljava/util/Comparator;)Ljava/util/Comparator;", "thenBy", "(Ljava/util/Comparator;Ljava/util/Comparator;Lkotlin/jvm/functions/Function1;)Ljava/util/Comparator;", "thenByDescending", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "comparison", "thenComparator", "(Ljava/util/Comparator;Lkotlin/jvm/functions/Function2;)Ljava/util/Comparator;", "thenDescending", "kotlin-stdlib"},
   xs = "kotlin/comparisons/ComparisonsKt"
)
class ComparisonsKt__ComparisonsKt {
   public static final int compareValuesBy(Object a, Object b, @NotNull Function1... selectors) {
      Intrinsics.checkNotNullParameter(selectors, "selectors");
      boolean var3 = selectors.length > 0;
      if (!var3) {
         String var4 = "Failed requirement.";
         throw new IllegalArgumentException(var4.toString());
      } else {
         return compareValuesByImpl$ComparisonsKt__ComparisonsKt(a, b, selectors);
      }
   }

   private static final int compareValuesByImpl$ComparisonsKt__ComparisonsKt(Object a, Object b, Function1[] selectors) {
      int var3 = 0;

      for(int var4 = selectors.length; var3 < var4; ++var3) {
         Function1 fn = selectors[var3];
         Comparable v1 = (Comparable)fn.invoke(a);
         Comparable v2 = (Comparable)fn.invoke(b);
         int diff = ComparisonsKt.compareValues(v1, v2);
         if (diff != 0) {
            return diff;
         }
      }

      return 0;
   }

   @InlineOnly
   private static final int compareValuesBy(Object a, Object b, Function1 selector) {
      Intrinsics.checkNotNullParameter(selector, "selector");
      return ComparisonsKt.compareValues((Comparable)selector.invoke(a), (Comparable)selector.invoke(b));
   }

   @InlineOnly
   private static final int compareValuesBy(Object a, Object b, Comparator comparator, Function1 selector) {
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      Intrinsics.checkNotNullParameter(selector, "selector");
      return comparator.compare(selector.invoke(a), selector.invoke(b));
   }

   public static final int compareValues(@Nullable Comparable a, @Nullable Comparable b) {
      if (a == b) {
         return 0;
      } else if (a == null) {
         return -1;
      } else {
         return b == null ? 1 : a.compareTo(b);
      }
   }

   @NotNull
   public static final Comparator compareBy(@NotNull Function1... selectors) {
      Intrinsics.checkNotNullParameter(selectors, "selectors");
      boolean var1 = selectors.length > 0;
      if (!var1) {
         String var2 = "Failed requirement.";
         throw new IllegalArgumentException(var2.toString());
      } else {
         return ComparisonsKt__ComparisonsKt::compareBy$lambda$0$ComparisonsKt__ComparisonsKt;
      }
   }

   @InlineOnly
   private static final Comparator compareBy(final Function1 selector) {
      Intrinsics.checkNotNullParameter(selector, "selector");
      return new Comparator(selector) {
         public final int compare(Object a, Object b) {
            Function1 var3 = selector;
            return ComparisonsKt.compareValues((Comparable)var3.invoke(a), (Comparable)var3.invoke(b));
         }
      };
   }

   @InlineOnly
   private static final Comparator compareBy(final Comparator comparator, final Function1 selector) {
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      Intrinsics.checkNotNullParameter(selector, "selector");
      return new Comparator(comparator, selector) {
         public final int compare(Object a, Object b) {
            Comparator var3 = comparator;
            Function1 var4 = selector;
            return var3.compare(var4.invoke(a), var4.invoke(b));
         }
      };
   }

   @InlineOnly
   private static final Comparator compareByDescending(final Function1 selector) {
      Intrinsics.checkNotNullParameter(selector, "selector");
      return new Comparator(selector) {
         public final int compare(Object a, Object b) {
            Function1 var3 = selector;
            return ComparisonsKt.compareValues((Comparable)var3.invoke(b), (Comparable)var3.invoke(a));
         }
      };
   }

   @InlineOnly
   private static final Comparator compareByDescending(final Comparator comparator, final Function1 selector) {
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      Intrinsics.checkNotNullParameter(selector, "selector");
      return new Comparator(comparator, selector) {
         public final int compare(Object a, Object b) {
            Comparator var3 = comparator;
            Function1 var4 = selector;
            return var3.compare(var4.invoke(b), var4.invoke(a));
         }
      };
   }

   @InlineOnly
   private static final Comparator thenBy(final Comparator $this$thenBy, final Function1 selector) {
      Intrinsics.checkNotNullParameter($this$thenBy, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      return new Comparator($this$thenBy, selector) {
         public final int compare(Object a, Object b) {
            int previousCompare = $this$thenBy.compare(a, b);
            int var10000;
            if (previousCompare != 0) {
               var10000 = previousCompare;
            } else {
               Function1 var4 = selector;
               var10000 = ComparisonsKt.compareValues((Comparable)var4.invoke(a), (Comparable)var4.invoke(b));
            }

            return var10000;
         }
      };
   }

   @InlineOnly
   private static final Comparator thenBy(final Comparator $this$thenBy, final Comparator comparator, final Function1 selector) {
      Intrinsics.checkNotNullParameter($this$thenBy, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      Intrinsics.checkNotNullParameter(selector, "selector");
      return new Comparator($this$thenBy, comparator, selector) {
         public final int compare(Object a, Object b) {
            int previousCompare = $this$thenBy.compare(a, b);
            int var10000;
            if (previousCompare != 0) {
               var10000 = previousCompare;
            } else {
               Comparator var4 = comparator;
               Function1 var5 = selector;
               var10000 = var4.compare(var5.invoke(a), var5.invoke(b));
            }

            return var10000;
         }
      };
   }

   @InlineOnly
   private static final Comparator thenByDescending(final Comparator $this$thenByDescending, final Function1 selector) {
      Intrinsics.checkNotNullParameter($this$thenByDescending, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      return new Comparator($this$thenByDescending, selector) {
         public final int compare(Object a, Object b) {
            int previousCompare = $this$thenByDescending.compare(a, b);
            int var10000;
            if (previousCompare != 0) {
               var10000 = previousCompare;
            } else {
               Function1 var4 = selector;
               var10000 = ComparisonsKt.compareValues((Comparable)var4.invoke(b), (Comparable)var4.invoke(a));
            }

            return var10000;
         }
      };
   }

   @InlineOnly
   private static final Comparator thenByDescending(final Comparator $this$thenByDescending, final Comparator comparator, final Function1 selector) {
      Intrinsics.checkNotNullParameter($this$thenByDescending, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      Intrinsics.checkNotNullParameter(selector, "selector");
      return new Comparator($this$thenByDescending, comparator, selector) {
         public final int compare(Object a, Object b) {
            int previousCompare = $this$thenByDescending.compare(a, b);
            int var10000;
            if (previousCompare != 0) {
               var10000 = previousCompare;
            } else {
               Comparator var4 = comparator;
               Function1 var5 = selector;
               var10000 = var4.compare(var5.invoke(b), var5.invoke(a));
            }

            return var10000;
         }
      };
   }

   @InlineOnly
   private static final Comparator thenComparator(final Comparator $this$thenComparator, final Function2 comparison) {
      Intrinsics.checkNotNullParameter($this$thenComparator, "<this>");
      Intrinsics.checkNotNullParameter(comparison, "comparison");
      return new Comparator($this$thenComparator, comparison) {
         public final int compare(Object a, Object b) {
            int previousCompare = $this$thenComparator.compare(a, b);
            return previousCompare != 0 ? previousCompare : ((Number)comparison.invoke(a, b)).intValue();
         }
      };
   }

   @NotNull
   public static final Comparator then(@NotNull Comparator $this$then, @NotNull Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$then, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return ComparisonsKt__ComparisonsKt::then$lambda$1$ComparisonsKt__ComparisonsKt;
   }

   @NotNull
   public static final Comparator thenDescending(@NotNull Comparator $this$thenDescending, @NotNull Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$thenDescending, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return ComparisonsKt__ComparisonsKt::thenDescending$lambda$2$ComparisonsKt__ComparisonsKt;
   }

   @NotNull
   public static final Comparator nullsFirst(@NotNull Comparator comparator) {
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return ComparisonsKt__ComparisonsKt::nullsFirst$lambda$3$ComparisonsKt__ComparisonsKt;
   }

   @InlineOnly
   private static final Comparator nullsFirst() {
      return ComparisonsKt.nullsFirst(ComparisonsKt.naturalOrder());
   }

   @NotNull
   public static final Comparator nullsLast(@NotNull Comparator comparator) {
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return ComparisonsKt__ComparisonsKt::nullsLast$lambda$4$ComparisonsKt__ComparisonsKt;
   }

   @InlineOnly
   private static final Comparator nullsLast() {
      return ComparisonsKt.nullsLast(ComparisonsKt.naturalOrder());
   }

   @NotNull
   public static final Comparator naturalOrder() {
      NaturalOrderComparator var10000 = NaturalOrderComparator.INSTANCE;
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type java.util.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.naturalOrder>{ kotlin.TypeAliasesKt.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.naturalOrder> }");
      return var10000;
   }

   @NotNull
   public static final Comparator reverseOrder() {
      ReverseOrderComparator var10000 = ReverseOrderComparator.INSTANCE;
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type java.util.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reverseOrder>{ kotlin.TypeAliasesKt.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reverseOrder> }");
      return var10000;
   }

   @NotNull
   public static final Comparator reversed(@NotNull Comparator $this$reversed) {
      Intrinsics.checkNotNullParameter($this$reversed, "<this>");
      Comparator var10000;
      if ($this$reversed instanceof ReversedComparator) {
         var10000 = ((ReversedComparator)$this$reversed).getComparator();
      } else if (Intrinsics.areEqual((Object)$this$reversed, (Object)NaturalOrderComparator.INSTANCE)) {
         var10000 = ReverseOrderComparator.INSTANCE;
         Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type java.util.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reversed>{ kotlin.TypeAliasesKt.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reversed> }");
         var10000 = var10000;
      } else if (Intrinsics.areEqual((Object)$this$reversed, (Object)ReverseOrderComparator.INSTANCE)) {
         var10000 = NaturalOrderComparator.INSTANCE;
         Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type java.util.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reversed>{ kotlin.TypeAliasesKt.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reversed> }");
         var10000 = var10000;
      } else {
         var10000 = new ReversedComparator($this$reversed);
      }

      return var10000;
   }

   private static final int compareBy$lambda$0$ComparisonsKt__ComparisonsKt(Function1[] $selectors, Object a, Object b) {
      Intrinsics.checkNotNullParameter($selectors, "$selectors");
      return compareValuesByImpl$ComparisonsKt__ComparisonsKt(a, b, $selectors);
   }

   private static final int then$lambda$1$ComparisonsKt__ComparisonsKt(Comparator $this_then, Comparator $comparator, Object a, Object b) {
      Intrinsics.checkNotNullParameter($this_then, "$this_then");
      Intrinsics.checkNotNullParameter($comparator, "$comparator");
      int previousCompare = $this_then.compare(a, b);
      return previousCompare != 0 ? previousCompare : $comparator.compare(a, b);
   }

   private static final int thenDescending$lambda$2$ComparisonsKt__ComparisonsKt(Comparator $this_thenDescending, Comparator $comparator, Object a, Object b) {
      Intrinsics.checkNotNullParameter($this_thenDescending, "$this_thenDescending");
      Intrinsics.checkNotNullParameter($comparator, "$comparator");
      int previousCompare = $this_thenDescending.compare(a, b);
      return previousCompare != 0 ? previousCompare : $comparator.compare(b, a);
   }

   private static final int nullsFirst$lambda$3$ComparisonsKt__ComparisonsKt(Comparator $comparator, Object a, Object b) {
      Intrinsics.checkNotNullParameter($comparator, "$comparator");
      return a == b ? 0 : (a == null ? -1 : (b == null ? 1 : $comparator.compare(a, b)));
   }

   private static final int nullsLast$lambda$4$ComparisonsKt__ComparisonsKt(Comparator $comparator, Object a, Object b) {
      Intrinsics.checkNotNullParameter($comparator, "$comparator");
      return a == b ? 0 : (a == null ? 1 : (b == null ? -1 : $comparator.compare(a, b)));
   }

   public ComparisonsKt__ComparisonsKt() {
   }
}
