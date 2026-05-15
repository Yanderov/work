package kotlin.io;

import java.io.BufferedReader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007H\u0096\u0002¢\u0006\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\n¨\u0006\u000b"},
   d2 = {"Lkotlin/io/LinesSequence;", "Lkotlin/sequences/Sequence;", "", "Ljava/io/BufferedReader;", "reader", "<init>", "(Ljava/io/BufferedReader;)V", "", "iterator", "()Ljava/util/Iterator;", "Ljava/io/BufferedReader;", "kotlin-stdlib"}
)
final class LinesSequence implements Sequence {
   @NotNull
   private final BufferedReader reader;

   public LinesSequence(@NotNull BufferedReader reader) {
      Intrinsics.checkNotNullParameter(reader, "reader");
      super();
      this.reader = reader;
   }

   @NotNull
   public Iterator iterator() {
      return new Iterator(this) {
         private String nextValue;
         private boolean done;

         public boolean hasNext() {
            if (this.nextValue == null && !this.done) {
               this.nextValue = LinesSequence.this.reader.readLine();
               if (this.nextValue == null) {
                  this.done = true;
               }
            }

            return this.nextValue != null;
         }

         public String next() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               String answer = this.nextValue;
               this.nextValue = null;
               Intrinsics.checkNotNull(answer);
               return answer;
            }
         }

         public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
         }
      };
   }
}
