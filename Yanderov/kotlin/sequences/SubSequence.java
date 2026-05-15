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
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010(\n\u0002\b\t\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003B%\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\n\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0096\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\n\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0010\u0010\fR\u0014\u0010\u0013\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0007\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0014R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0015R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0014¨\u0006\u0016"},
   d2 = {"Lkotlin/sequences/SubSequence;", "T", "Lkotlin/sequences/Sequence;", "Lkotlin/sequences/DropTakeSequence;", "sequence", "", "startIndex", "endIndex", "<init>", "(Lkotlin/sequences/Sequence;II)V", "n", "drop", "(I)Lkotlin/sequences/Sequence;", "", "iterator", "()Ljava/util/Iterator;", "take", "getCount", "()I", "count", "I", "Lkotlin/sequences/Sequence;", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nSequences.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Sequences.kt\nkotlin/sequences/SubSequence\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,698:1\n1#2:699\n*E\n"})
public final class SubSequence implements Sequence, DropTakeSequence {
   @NotNull
   private final Sequence sequence;
   private final int startIndex;
   private final int endIndex;

   public SubSequence(@NotNull Sequence sequence, int startIndex, int endIndex) {
      Intrinsics.checkNotNullParameter(sequence, "sequence");
      super();
      this.sequence = sequence;
      this.startIndex = startIndex;
      this.endIndex = endIndex;
      boolean var4 = this.startIndex >= 0;
      if (!var4) {
         int var11 = 0;
         String var12 = "startIndex should be non-negative, but is " + this.startIndex;
         throw new IllegalArgumentException(var12.toString());
      } else {
         var4 = this.endIndex >= 0;
         if (!var4) {
            int var9 = 0;
            String var10 = "endIndex should be non-negative, but is " + this.endIndex;
            throw new IllegalArgumentException(var10.toString());
         } else {
            var4 = this.endIndex >= this.startIndex;
            if (!var4) {
               int var5 = 0;
               String var8 = "endIndex should be not less than startIndex, but was " + this.endIndex + " < " + this.startIndex;
               throw new IllegalArgumentException(var8.toString());
            }
         }
      }
   }

   private final int getCount() {
      return this.endIndex - this.startIndex;
   }

   @NotNull
   public Sequence drop(int n) {
      return n >= this.getCount() ? SequencesKt.emptySequence() : (Sequence)(new SubSequence(this.sequence, this.startIndex + n, this.endIndex));
   }

   @NotNull
   public Sequence take(int n) {
      return n >= this.getCount() ? (Sequence)this : (Sequence)(new SubSequence(this.sequence, this.startIndex, this.startIndex + n));
   }

   @NotNull
   public Iterator iterator() {
      return new Iterator(this) {
         private final Iterator iterator;
         private int position;

         {
            this.iterator = $receiver.sequence.iterator();
         }

         public final Iterator getIterator() {
            return this.iterator;
         }

         public final int getPosition() {
            return this.position;
         }

         public final void setPosition(int <set-?>) {
            this.position = <set-?>;
         }

         private final void drop() {
            while(this.position < SubSequence.this.startIndex && this.iterator.hasNext()) {
               this.iterator.next();
               int var1 = this.position++;
            }

         }

         public boolean hasNext() {
            this.drop();
            return this.position < SubSequence.this.endIndex && this.iterator.hasNext();
         }

         public Object next() {
            this.drop();
            if (this.position >= SubSequence.this.endIndex) {
               throw new NoSuchElementException();
            } else {
               int var1 = this.position++;
               return this.iterator.next();
            }
         }

         public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
         }
      };
   }
}
