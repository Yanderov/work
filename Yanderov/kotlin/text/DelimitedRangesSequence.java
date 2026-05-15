package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B[\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012:\u0010\u000e\u001a6\u0012\u0004\u0012\u00020\u0003\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\f0\b¢\u0006\u0002\b\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u0011H\u0096\u0002¢\u0006\u0004\b\u0012\u0010\u0013RH\u0010\u000e\u001a6\u0012\u0004\u0012\u00020\u0003\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\f0\b¢\u0006\u0002\b\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u0014R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0015R\u0014\u0010\u0007\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0016R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0016¨\u0006\u0017"},
   d2 = {"Lkotlin/text/DelimitedRangesSequence;", "Lkotlin/sequences/Sequence;", "Lkotlin/ranges/IntRange;", "", "input", "", "startIndex", "limit", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "currentIndex", "Lkotlin/Pair;", "Lkotlin/ExtensionFunctionType;", "getNextMatch", "<init>", "(Ljava/lang/CharSequence;IILkotlin/jvm/functions/Function2;)V", "", "iterator", "()Ljava/util/Iterator;", "Lkotlin/jvm/functions/Function2;", "Ljava/lang/CharSequence;", "I", "kotlin-stdlib"}
)
final class DelimitedRangesSequence implements Sequence {
   @NotNull
   private final CharSequence input;
   private final int startIndex;
   private final int limit;
   @NotNull
   private final Function2 getNextMatch;

   public DelimitedRangesSequence(@NotNull CharSequence input, int startIndex, int limit, @NotNull Function2 getNextMatch) {
      Intrinsics.checkNotNullParameter(input, "input");
      Intrinsics.checkNotNullParameter(getNextMatch, "getNextMatch");
      super();
      this.input = input;
      this.startIndex = startIndex;
      this.limit = limit;
      this.getNextMatch = getNextMatch;
   }

   @NotNull
   public Iterator iterator() {
      return new Iterator(this) {
         private int nextState = -1;
         private int currentStartIndex;
         private int nextSearchIndex;
         private IntRange nextItem;
         private int counter;

         {
            this.currentStartIndex = RangesKt.coerceIn($receiver.startIndex, 0, $receiver.input.length());
            this.nextSearchIndex = this.currentStartIndex;
         }

         public final int getNextState() {
            return this.nextState;
         }

         public final void setNextState(int <set-?>) {
            this.nextState = <set-?>;
         }

         public final int getCurrentStartIndex() {
            return this.currentStartIndex;
         }

         public final void setCurrentStartIndex(int <set-?>) {
            this.currentStartIndex = <set-?>;
         }

         public final int getNextSearchIndex() {
            return this.nextSearchIndex;
         }

         public final void setNextSearchIndex(int <set-?>) {
            this.nextSearchIndex = <set-?>;
         }

         public final IntRange getNextItem() {
            return this.nextItem;
         }

         public final void setNextItem(IntRange <set-?>) {
            this.nextItem = <set-?>;
         }

         public final int getCounter() {
            return this.counter;
         }

         public final void setCounter(int <set-?>) {
            this.counter = <set-?>;
         }

         private final void calcNext() {
            if (this.nextSearchIndex < 0) {
               this.nextState = 0;
               this.nextItem = null;
            } else {
               label27: {
                  label26: {
                     if (DelimitedRangesSequence.this.limit > 0) {
                        ++this.counter;
                        if (this.counter >= DelimitedRangesSequence.this.limit) {
                           break label26;
                        }
                     }

                     if (this.nextSearchIndex <= DelimitedRangesSequence.this.input.length()) {
                        Pair match = (Pair)DelimitedRangesSequence.this.getNextMatch.invoke(DelimitedRangesSequence.this.input, this.nextSearchIndex);
                        if (match == null) {
                           this.nextItem = new IntRange(this.currentStartIndex, StringsKt.getLastIndex(DelimitedRangesSequence.this.input));
                           this.nextSearchIndex = -1;
                        } else {
                           int index = ((Number)match.component1()).intValue();
                           int length = ((Number)match.component2()).intValue();
                           this.nextItem = RangesKt.until(this.currentStartIndex, index);
                           this.currentStartIndex = index + length;
                           this.nextSearchIndex = this.currentStartIndex + (length == 0 ? 1 : 0);
                        }
                        break label27;
                     }
                  }

                  this.nextItem = new IntRange(this.currentStartIndex, StringsKt.getLastIndex(DelimitedRangesSequence.this.input));
                  this.nextSearchIndex = -1;
               }

               this.nextState = 1;
            }

         }

         public IntRange next() {
            if (this.nextState == -1) {
               this.calcNext();
            }

            if (this.nextState == 0) {
               throw new NoSuchElementException();
            } else {
               IntRange var10000 = this.nextItem;
               Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type kotlin.ranges.IntRange");
               IntRange result = var10000;
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
