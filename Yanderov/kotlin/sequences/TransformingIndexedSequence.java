package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\u0005\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u00028\u00010\u0003B/\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\nH\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\rR&\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u000e¨\u0006\u000f"},
   d2 = {"Lkotlin/sequences/TransformingIndexedSequence;", "T", "R", "Lkotlin/sequences/Sequence;", "sequence", "Lkotlin/Function2;", "", "transformer", "<init>", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function2;)V", "", "iterator", "()Ljava/util/Iterator;", "Lkotlin/sequences/Sequence;", "Lkotlin/jvm/functions/Function2;", "kotlin-stdlib"}
)
public final class TransformingIndexedSequence implements Sequence {
   @NotNull
   private final Sequence sequence;
   @NotNull
   private final Function2 transformer;

   public TransformingIndexedSequence(@NotNull Sequence sequence, @NotNull Function2 transformer) {
      Intrinsics.checkNotNullParameter(sequence, "sequence");
      Intrinsics.checkNotNullParameter(transformer, "transformer");
      super();
      this.sequence = sequence;
      this.transformer = transformer;
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

         public Object next() {
            Function2 var10000 = TransformingIndexedSequence.this.transformer;
            int var1 = this.index++;
            if (var1 < 0) {
               CollectionsKt.throwIndexOverflow();
            }

            return var10000.invoke(var1, this.iterator.next());
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
