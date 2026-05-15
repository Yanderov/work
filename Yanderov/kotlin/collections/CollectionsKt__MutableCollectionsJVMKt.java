package kotlin.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u00006\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a(\u0010\u0004\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u0087\b¢\u0006\u0004\b\u0004\u0010\u0005\u001a \u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0087\b¢\u0006\u0004\b\u0006\u0010\u0007\u001a(\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\t\u001a\u00020\bH\u0087\b¢\u0006\u0004\b\u0006\u0010\n\u001a'\u0010\f\u001a\u00020\u0003\"\u000e\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u000b*\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\u0004\b\f\u0010\u0007\u001a=\u0010\f\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000e0\rH\u0087\bø\u0001\u0000¢\u0006\u0004\b\f\u0010\u0010\u001a<\u0010\f\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u001a\u0010\u0013\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00000\u0011j\n\u0012\u0006\b\u0000\u0012\u00028\u0000`\u0012H\u0087\b¢\u0006\u0004\b\f\u0010\u0014\u001a9\u0010\u0015\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u001a\u0010\u0013\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00000\u0011j\n\u0012\u0006\b\u0000\u0012\u00028\u0000`\u0012¢\u0006\u0004\b\u0015\u0010\u0014\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0016"},
   d2 = {"T", "", "value", "", "fill", "(Ljava/util/List;Ljava/lang/Object;)V", "shuffle", "(Ljava/util/List;)V", "Ljava/util/Random;", "random", "(Ljava/util/List;Ljava/util/Random;)V", "", "sort", "Lkotlin/Function2;", "", "comparison", "(Ljava/util/List;Lkotlin/jvm/functions/Function2;)V", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "comparator", "(Ljava/util/List;Ljava/util/Comparator;)V", "sortWith", "kotlin-stdlib"},
   xs = "kotlin/collections/CollectionsKt"
)
class CollectionsKt__MutableCollectionsJVMKt extends CollectionsKt__IteratorsKt {
   /** @deprecated */
   @Deprecated(
      message = "Use sortWith(comparator) instead.",
      replaceWith = @ReplaceWith(
   expression = "this.sortWith(comparator)",
   imports = {}
),
      level = DeprecationLevel.ERROR
   )
   @InlineOnly
   private static final void sort(List $this$sort, Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$sort, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      throw new NotImplementedError((String)null, 1, (DefaultConstructorMarker)null);
   }

   /** @deprecated */
   @Deprecated(
      message = "Use sortWith(Comparator(comparison)) instead.",
      replaceWith = @ReplaceWith(
   expression = "this.sortWith(Comparator(comparison))",
   imports = {}
),
      level = DeprecationLevel.ERROR
   )
   @InlineOnly
   private static final void sort(List $this$sort, Function2 comparison) {
      Intrinsics.checkNotNullParameter($this$sort, "<this>");
      Intrinsics.checkNotNullParameter(comparison, "comparison");
      throw new NotImplementedError((String)null, 1, (DefaultConstructorMarker)null);
   }

   public static final void sort(@NotNull List $this$sort) {
      Intrinsics.checkNotNullParameter($this$sort, "<this>");
      if ($this$sort.size() > 1) {
         Collections.sort($this$sort);
      }

   }

   public static final void sortWith(@NotNull List $this$sortWith, @NotNull Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$sortWith, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      if ($this$sortWith.size() > 1) {
         Collections.sort($this$sortWith, comparator);
      }

   }

   @InlineOnly
   @SinceKotlin(
      version = "1.2"
   )
   private static final void fill(List $this$fill, Object value) {
      Intrinsics.checkNotNullParameter($this$fill, "<this>");
      Collections.fill($this$fill, value);
   }

   @InlineOnly
   @SinceKotlin(
      version = "1.2"
   )
   private static final void shuffle(List $this$shuffle) {
      Intrinsics.checkNotNullParameter($this$shuffle, "<this>");
      Collections.shuffle($this$shuffle);
   }

   @InlineOnly
   @SinceKotlin(
      version = "1.2"
   )
   private static final void shuffle(List $this$shuffle, Random random) {
      Intrinsics.checkNotNullParameter($this$shuffle, "<this>");
      Intrinsics.checkNotNullParameter(random, "random");
      Collections.shuffle($this$shuffle, random);
   }

   public CollectionsKt__MutableCollectionsJVMKt() {
   }
}
