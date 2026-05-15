package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\u0005\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B)\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0096\u0002¢\u0006\u0004\b\n\u0010\u000bR \u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\fR\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\r¨\u0006\u000e"},
   d2 = {"Lkotlin/sequences/TakeWhileSequence;", "T", "Lkotlin/sequences/Sequence;", "sequence", "Lkotlin/Function1;", "", "predicate", "<init>", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)V", "", "iterator", "()Ljava/util/Iterator;", "Lkotlin/jvm/functions/Function1;", "Lkotlin/sequences/Sequence;", "kotlin-stdlib"}
)
public final class TakeWhileSequence implements Sequence {
   @NotNull
   private final Sequence sequence;
   @NotNull
   private final Function1 predicate;

   public TakeWhileSequence(@NotNull Sequence sequence, @NotNull Function1 predicate) {
      Intrinsics.checkNotNullParameter(sequence, "sequence");
      Intrinsics.checkNotNullParameter(predicate, "predicate");
      super();
      this.sequence = sequence;
      this.predicate = predicate;
   }

   @NotNull
   public Iterator iterator() {
      return new Iterator(this) {
         private final Iterator iterator;
         private int nextState;
         private Object nextItem;

         {
            this.iterator = $receiver.sequence.iterator();
            this.nextState = -1;
         }

         public final Iterator getIterator() {
            return this.iterator;
         }

         public final int getNextState() {
            return this.nextState;
         }

         public final void setNextState(int <set-?>) {
            this.nextState = <set-?>;
         }

         public final Object getNextItem() {
            return this.nextItem;
         }

         public final void setNextItem(Object <set-?>) {
            this.nextItem = <set-?>;
         }

         private final void calcNext() {
            if (this.iterator.hasNext()) {
               Object item = this.iterator.next();
               if ((Boolean)TakeWhileSequence.this.predicate.invoke(item)) {
                  this.nextState = 1;
                  this.nextItem = item;
                  return;
               }
            }

            this.nextState = 0;
         }

         public Object next() {
            if (this.nextState == -1) {
               this.calcNext();
            }

            if (this.nextState == 0) {
               throw new NoSuchElementException();
            } else {
               Object result = this.nextItem;
               this.nextItem = null;
               this.nextState = -1;
               return result;
            }
         }

         public boolean hasNext() {
            if (this.nextState == -1) {
               this.calcNext();
            }

            return this.nextState == 1;
         }

         public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
         }
      };
   }
}
