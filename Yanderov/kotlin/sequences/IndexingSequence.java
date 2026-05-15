package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0002B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001c\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0007H\u0096\u0002¢\u0006\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\n¨\u0006\u000b"},
   d2 = {"Lkotlin/sequences/IndexingSequence;", "T", "Lkotlin/sequences/Sequence;", "Lkotlin/collections/IndexedValue;", "sequence", "<init>", "(Lkotlin/sequences/Sequence;)V", "", "iterator", "()Ljava/util/Iterator;", "Lkotlin/sequences/Sequence;", "kotlin-stdlib"}
)
public final class IndexingSequence implements Sequence {
   @NotNull
   private final Sequence sequence;

   public IndexingSequence(@NotNull Sequence sequence) {
      Intrinsics.checkNotNullParameter(sequence, "sequence");
      super();
      this.sequence = sequence;
   }

   @NotNull
   public Iterator iterator() {
      return new Iterator(this) {
         private final Iterator iterator;
         private int index;

         {
            this.iterator = $receiver.sequence.iterator();
         }

         public final Iterator getIterator() {
            return this.iterator;
         }

         public final int getIndex() {
            return this.index;
         }

         public final void setIndex(int <set-?>) {
            this.index = <set-?>;
         }

         public IndexedValue next() {
            IndexedValue var10000 = new IndexedValue;
            int var1 = this.index++;
            if (var1 < 0) {
               CollectionsKt.throwIndexOverflow();
            }

            var10000.<init>(var1, this.iterator.next());
            return var10000;
         }

         public boolean hasNext() {
            return this.iterator.hasNext();
         }

         public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
         }
      };
   }
}
