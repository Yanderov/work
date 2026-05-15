package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a7\u0010\u0005\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00030\u0002H\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a&\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0087\n¢\u0006\u0004\b\u0007\u0010\b\u001a)\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t0\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\u0004\b\n\u0010\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000b"},
   d2 = {"T", "", "Lkotlin/Function1;", "", "operation", "forEach", "(Ljava/util/Iterator;Lkotlin/jvm/functions/Function1;)V", "iterator", "(Ljava/util/Iterator;)Ljava/util/Iterator;", "Lkotlin/collections/IndexedValue;", "withIndex", "kotlin-stdlib"},
   xs = "kotlin/collections/CollectionsKt"
)
class CollectionsKt__IteratorsKt extends CollectionsKt__IteratorsJVMKt {
   @InlineOnly
   private static final Iterator iterator(Iterator $this$iterator) {
      Intrinsics.checkNotNullParameter($this$iterator, "<this>");
      return $this$iterator;
   }

   @NotNull
   public static final Iterator withIndex(@NotNull Iterator $this$withIndex) {
      Intrinsics.checkNotNullParameter($this$withIndex, "<this>");
      return new IndexingIterator($this$withIndex);
   }

   public static final void forEach(@NotNull Iterator $this$forEach, @NotNull Function1 operation) {
      Intrinsics.checkNotNullParameter($this$forEach, "<this>");
      Intrinsics.checkNotNullParameter(operation, "operation");
      int $i$f$forEach = 0;
      Iterator var3 = $this$forEach;

      while(var3.hasNext()) {
         Object element = var3.next();
         operation.invoke(element);
      }

   }

   public CollectionsKt__IteratorsKt() {
   }
}
