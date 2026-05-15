package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010(\n\u0002\b\u0006\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\t\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\fH\u0096\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\t\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u000f\u0010\u000bR\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0010R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0011¨\u0006\u0012"},
   d2 = {"Lkotlin/sequences/TakeSequence;", "T", "Lkotlin/sequences/Sequence;", "Lkotlin/sequences/DropTakeSequence;", "sequence", "", "count", "<init>", "(Lkotlin/sequences/Sequence;I)V", "n", "drop", "(I)Lkotlin/sequences/Sequence;", "", "iterator", "()Ljava/util/Iterator;", "take", "I", "Lkotlin/sequences/Sequence;", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nSequences.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Sequences.kt\nkotlin/sequences/TakeSequence\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,698:1\n1#2:699\n*E\n"})
public final class TakeSequence implements Sequence, DropTakeSequence {
   @NotNull
   private final Sequence sequence;
   private final int count;

   public TakeSequence(@NotNull Sequence sequence, int count) {
      Intrinsics.checkNotNullParameter(sequence, "sequence");
      super();
      this.sequence = sequence;
      this.count = count;
      boolean var3 = this.count >= 0;
      if (!var3) {
         int var4 = 0;
         String var5 = "count must be non-negative, but was " + this.count + '.';
         throw new IllegalArgumentException(var5.toString());
      }
   }

   @NotNull
   public Sequence drop(int n) {
      return n >= this.count ? SequencesKt.emptySequence() : (Sequence)(new SubSequence(this.sequence, n, this.count));
   }

   @NotNull
   public Sequence take(int n) {
      return n >= this.count ? (Sequence)this : (Sequence)(new TakeSequence(this.sequence, n));
   }

   @NotNull
   public Iterator iterator() {
      return new Iterator(this) {
         private int left;
         private final Iterator iterator;

         {
            this.left = $receiver.count;
            this.iterator = $receiver.sequence.iterator();
         }

         public final int getLeft() {
            return this.left;
         }

         public final void setLeft(int <set-?>) {
            this.left = <set-?>;
         }

         public final Iterator getIterator() {
            return this.iterator;
         }

         public Object next() {
            if (this.left == 0) {
               throw new NoSuchElementException();
            } else {
               int var1 = this.left;
               this.left = var1 + -1;
               return this.iterator.next();
            }
         }

         public boolean hasNext() {
            return this.left > 0 && this.iterator.hasNext();
         }

         public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
         }
      };
   }
}
