package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\u0005\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\b\u0012\u0004\u0012\u00028\u00020\u0004B=\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00020\u000bH\u0096\u0002¢\u0006\u0004\b\f\u0010\rR\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u000eR\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u000eR&\u0010\b\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u000f¨\u0006\u0010"},
   d2 = {"Lkotlin/sequences/MergingSequence;", "T1", "T2", "V", "Lkotlin/sequences/Sequence;", "sequence1", "sequence2", "Lkotlin/Function2;", "transform", "<init>", "(Lkotlin/sequences/Sequence;Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function2;)V", "", "iterator", "()Ljava/util/Iterator;", "Lkotlin/sequences/Sequence;", "Lkotlin/jvm/functions/Function2;", "kotlin-stdlib"}
)
public final class MergingSequence implements Sequence {
   @NotNull
   private final Sequence sequence1;
   @NotNull
   private final Sequence sequence2;
   @NotNull
   private final Function2 transform;

   public MergingSequence(@NotNull Sequence sequence1, @NotNull Sequence sequence2, @NotNull Function2 transform) {
      Intrinsics.checkNotNullParameter(sequence1, "sequence1");
      Intrinsics.checkNotNullParameter(sequence2, "sequence2");
      Intrinsics.checkNotNullParameter(transform, "transform");
      super();
      this.sequence1 = sequence1;
      this.sequence2 = sequence2;
      this.transform = transform;
   }

   @NotNull
   public Iterator iterator() {
      return new Iterator(this) {
         private final Iterator iterator1;
         private final Iterator iterator2;

         {
            this.iterator1 = $receiver.sequence1.iterator();
            this.iterator2 = $receiver.sequence2.iterator();
         }

         public final Iterator getIterator1() {
            return this.iterator1;
         }

         public final Iterator getIterator2() {
            return this.iterator2;
         }

         public Object next() {
            return MergingSequence.this.transform.invoke(this.iterator1.next(), this.iterator2.next());
         }

         public boolean hasNext() {
            return this.iterator1.hasNext() && this.iterator2.hasNext();
         }

         public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
         }
      };
   }
}
