package kotlin.collections;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\t\u001a#\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\u0004\b\u0002\u0010\u0003\u001a%\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0007¢\u0006\u0004\b\u0005\u0010\u0003\u001a\u001f\u0010\n\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\b\u0010\t\u001a\u001f\u0010\f\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\t\u001a\u001f\u0010\u000e\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\r\u0010\t¨\u0006\u000f"},
   d2 = {"T", "", "asReversed", "(Ljava/util/List;)Ljava/util/List;", "", "asReversedMutable", "", "index", "reverseElementIndex$CollectionsKt__ReversedViewsKt", "(Ljava/util/List;I)I", "reverseElementIndex", "reverseIteratorIndex$CollectionsKt__ReversedViewsKt", "reverseIteratorIndex", "reversePositionIndex$CollectionsKt__ReversedViewsKt", "reversePositionIndex", "kotlin-stdlib"},
   xs = "kotlin/collections/CollectionsKt"
)
class CollectionsKt__ReversedViewsKt extends CollectionsKt__MutableCollectionsKt {
   private static final int reverseElementIndex$CollectionsKt__ReversedViewsKt(List $this$reverseElementIndex, int index) {
      if (0 <= index ? index <= CollectionsKt.getLastIndex($this$reverseElementIndex) : false) {
         return CollectionsKt.getLastIndex($this$reverseElementIndex) - index;
      } else {
         throw new IndexOutOfBoundsException("Element index " + index + " must be in range [" + new IntRange(0, CollectionsKt.getLastIndex($this$reverseElementIndex)) + "].");
      }
   }

   private static final int reversePositionIndex$CollectionsKt__ReversedViewsKt(List $this$reversePositionIndex, int index) {
      if (0 <= index ? index <= $this$reversePositionIndex.size() : false) {
         return $this$reversePositionIndex.size() - index;
      } else {
         throw new IndexOutOfBoundsException("Position index " + index + " must be in range [" + new IntRange(0, $this$reversePositionIndex.size()) + "].");
      }
   }

   private static final int reverseIteratorIndex$CollectionsKt__ReversedViewsKt(List $this$reverseIteratorIndex, int index) {
      return CollectionsKt.getLastIndex($this$reverseIteratorIndex) - index;
   }

   @NotNull
   public static final List asReversed(@NotNull List $this$asReversed) {
      Intrinsics.checkNotNullParameter($this$asReversed, "<this>");
      return new ReversedListReadOnly($this$asReversed);
   }

   @JvmName(
      name = "asReversedMutable"
   )
   @NotNull
   public static final List asReversedMutable(@NotNull List $this$asReversed) {
      Intrinsics.checkNotNullParameter($this$asReversed, "<this>");
      return new ReversedList($this$asReversed);
   }

   // $FF: synthetic method
   public static final int access$reverseElementIndex(List $receiver, int index) {
      return reverseElementIndex$CollectionsKt__ReversedViewsKt($receiver, index);
   }

   // $FF: synthetic method
   public static final int access$reversePositionIndex(List $receiver, int index) {
      return reversePositionIndex$CollectionsKt__ReversedViewsKt($receiver, index);
   }

   // $FF: synthetic method
   public static final int access$reverseIteratorIndex(List $receiver, int index) {
      return reverseIteratorIndex$CollectionsKt__ReversedViewsKt($receiver, index);
   }

   public CollectionsKt__ReversedViewsKt() {
   }
}
