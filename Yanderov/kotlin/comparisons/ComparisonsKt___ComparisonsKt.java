package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000\u0018\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0004\u001aI\u0010\u0007\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u00002\u0006\u0010\u0002\u001a\u00028\u00002\u0006\u0010\u0003\u001a\u00028\u00002\u001a\u0010\u0006\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00000\u0004j\n\u0012\u0006\b\u0000\u0012\u00028\u0000`\u0005H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001aA\u0010\u0007\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u00002\u0006\u0010\u0002\u001a\u00028\u00002\u001a\u0010\u0006\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00000\u0004j\n\u0012\u0006\b\u0000\u0012\u00028\u0000`\u0005H\u0007¢\u0006\u0004\b\u0007\u0010\t\u001aM\u0010\u0007\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u00002\u0012\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\n\"\u00028\u00002\u001a\u0010\u0006\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00000\u0004j\n\u0012\u0006\b\u0000\u0012\u00028\u0000`\u0005H\u0007¢\u0006\u0004\b\u0007\u0010\f\u001aI\u0010\r\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u00002\u0006\u0010\u0002\u001a\u00028\u00002\u0006\u0010\u0003\u001a\u00028\u00002\u001a\u0010\u0006\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00000\u0004j\n\u0012\u0006\b\u0000\u0012\u00028\u0000`\u0005H\u0007¢\u0006\u0004\b\r\u0010\b\u001aA\u0010\r\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u00002\u0006\u0010\u0002\u001a\u00028\u00002\u001a\u0010\u0006\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00000\u0004j\n\u0012\u0006\b\u0000\u0012\u00028\u0000`\u0005H\u0007¢\u0006\u0004\b\r\u0010\t\u001aM\u0010\r\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u00002\u0012\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\n\"\u00028\u00002\u001a\u0010\u0006\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00000\u0004j\n\u0012\u0006\b\u0000\u0012\u00028\u0000`\u0005H\u0007¢\u0006\u0004\b\r\u0010\f¨\u0006\u000e"},
   d2 = {"T", "a", "b", "c", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "comparator", "maxOf", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;)Ljava/lang/Object;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;)Ljava/lang/Object;", "", "other", "(Ljava/lang/Object;[Ljava/lang/Object;Ljava/util/Comparator;)Ljava/lang/Object;", "minOf", "kotlin-stdlib"},
   xs = "kotlin/comparisons/ComparisonsKt"
)
class ComparisonsKt___ComparisonsKt extends ComparisonsKt___ComparisonsJvmKt {
   @SinceKotlin(
      version = "1.1"
   )
   public static final Object maxOf(Object a, Object b, Object c, @NotNull Comparator comparator) {
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return ComparisonsKt.maxOf(a, ComparisonsKt.maxOf(b, c, comparator), comparator);
   }

   @SinceKotlin(
      version = "1.1"
   )
   public static final Object maxOf(Object a, Object b, @NotNull Comparator comparator) {
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return comparator.compare(a, b) >= 0 ? a : b;
   }

   @SinceKotlin(
      version = "1.4"
   )
   public static final Object maxOf(Object a, @NotNull Object[] other, @NotNull Comparator comparator) {
      Intrinsics.checkNotNullParameter(other, "other");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      Object max = a;
      int var4 = 0;

      for(int var5 = other.length; var4 < var5; ++var4) {
         Object e = other[var4];
         if (comparator.compare(max, e) < 0) {
            max = e;
         }
      }

      return max;
   }

   @SinceKotlin(
      version = "1.1"
   )
   public static final Object minOf(Object a, Object b, Object c, @NotNull Comparator comparator) {
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return ComparisonsKt.minOf(a, ComparisonsKt.minOf(b, c, comparator), comparator);
   }

   @SinceKotlin(
      version = "1.1"
   )
   public static final Object minOf(Object a, Object b, @NotNull Comparator comparator) {
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return comparator.compare(a, b) <= 0 ? a : b;
   }

   @SinceKotlin(
      version = "1.4"
   )
   public static final Object minOf(Object a, @NotNull Object[] other, @NotNull Comparator comparator) {
      Intrinsics.checkNotNullParameter(other, "other");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      Object min = a;
      int var4 = 0;

      for(int var5 = other.length; var4 < var5; ++var4) {
         Object e = other[var4];
         if (comparator.compare(min, e) > 0) {
            min = e;
         }
      }

      return min;
   }

   public ComparisonsKt___ComparisonsKt() {
   }
}
