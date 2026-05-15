package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\b\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u00028\u00010\u0003B)\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ5\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00020\u0003\"\u0004\b\u0002\u0010\t2\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\n0\u0005H\u0000¢\u0006\u0004\b\f\u0010\rJ\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\nH\u0096\u0002¢\u0006\u0004\b\u000b\u0010\u000fR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0010R \u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0011¨\u0006\u0012"},
   d2 = {"Lkotlin/sequences/TransformingSequence;", "T", "R", "Lkotlin/sequences/Sequence;", "sequence", "Lkotlin/Function1;", "transformer", "<init>", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)V", "E", "", "iterator", "flatten$kotlin_stdlib", "(Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;", "flatten", "()Ljava/util/Iterator;", "Lkotlin/sequences/Sequence;", "Lkotlin/jvm/functions/Function1;", "kotlin-stdlib"}
)
public final class TransformingSequence implements Sequence {
   @NotNull
   private final Sequence sequence;
   @NotNull
   private final Function1 transformer;

   public TransformingSequence(@NotNull Sequence sequence, @NotNull Function1 transformer) {
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

         {
            this.iterator = $receiver.sequence.iterator();
         }

         public final Iterator getIterator() {
            return this.iterator;
         }

         public Object next() {
            return TransformingSequence.this.transformer.invoke(this.iterator.next());
         }

         public boolean hasNext() {
            return this.iterator.hasNext();
         }

         public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
         }
      };
   }

   @NotNull
   public final Sequence flatten$kotlin_stdlib(@NotNull Function1 iterator) {
      Intrinsics.checkNotNullParameter(iterator, "iterator");
      return new FlatteningSequence(this.sequence, this.transformer, iterator);
   }
}
