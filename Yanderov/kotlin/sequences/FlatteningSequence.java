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
   d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\b\b\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\b\u0012\u0004\u0012\u00028\u00020\u0004:\u0001\u000fBC\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\b0\u0006¢\u0006\u0004\b\n\u0010\u000bJ\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00020\bH\u0096\u0002¢\u0006\u0004\b\t\u0010\fR&\u0010\t\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\b0\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\rR\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u000eR \u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\r¨\u0006\u0010"},
   d2 = {"Lkotlin/sequences/FlatteningSequence;", "T", "R", "E", "Lkotlin/sequences/Sequence;", "sequence", "Lkotlin/Function1;", "transformer", "", "iterator", "<init>", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "()Ljava/util/Iterator;", "Lkotlin/jvm/functions/Function1;", "Lkotlin/sequences/Sequence;", "State", "kotlin-stdlib"}
)
public final class FlatteningSequence implements Sequence {
   @NotNull
   private final Sequence sequence;
   @NotNull
   private final Function1 transformer;
   @NotNull
   private final Function1 iterator;

   public FlatteningSequence(@NotNull Sequence sequence, @NotNull Function1 transformer, @NotNull Function1 iterator) {
      Intrinsics.checkNotNullParameter(sequence, "sequence");
      Intrinsics.checkNotNullParameter(transformer, "transformer");
      Intrinsics.checkNotNullParameter(iterator, "iterator");
      super();
      this.sequence = sequence;
      this.transformer = transformer;
      this.iterator = iterator;
   }

   @NotNull
   public Iterator iterator() {
      return new Iterator(this) {
         private final Iterator iterator;
         private Iterator itemIterator;
         private int state;

         {
            this.iterator = $receiver.sequence.iterator();
         }

         public final Iterator getIterator() {
            return this.iterator;
         }

         public final Iterator getItemIterator() {
            return this.itemIterator;
         }

         public final void setItemIterator(Iterator <set-?>) {
            this.itemIterator = <set-?>;
         }

         public final int getState() {
            return this.state;
         }

         public final void setState(int <set-?>) {
            this.state = <set-?>;
         }

         public Object next() {
            if (this.state == 2) {
               throw new NoSuchElementException();
            } else if (this.state == 0 && !this.ensureItemIterator()) {
               throw new NoSuchElementException();
            } else {
               this.state = 0;
               Iterator var10000 = this.itemIterator;
               Intrinsics.checkNotNull(var10000);
               return var10000.next();
            }
         }

         public boolean hasNext() {
            if (this.state == 1) {
               return true;
            } else {
               return this.state == 2 ? false : this.ensureItemIterator();
            }
         }

         private final boolean ensureItemIterator() {
            Iterator itemIterator = this.itemIterator;
            if (itemIterator != null && itemIterator.hasNext()) {
               this.state = 1;
               return true;
            } else {
               while(this.iterator.hasNext()) {
                  Object element = this.iterator.next();
                  Iterator nextItemIterator = (Iterator)FlatteningSequence.this.iterator.invoke(FlatteningSequence.this.transformer.invoke(element));
                  if (nextItemIterator.hasNext()) {
                     this.itemIterator = nextItemIterator;
                     this.state = 1;
                     return true;
                  }
               }

               this.state = 2;
               this.itemIterator = null;
               return false;
            }
         }

         public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
         }
      };
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\b\u0010\u0006¨\u0006\t"},
      d2 = {"Lkotlin/sequences/FlatteningSequence$State;", "", "<init>", "()V", "", "DONE", "I", "READY", "UNDEFINED", "kotlin-stdlib"}
   )
   private static final class State {
      @NotNull
      public static final State INSTANCE = new State();
      public static final int UNDEFINED = 0;
      public static final int READY = 1;
      public static final int DONE = 2;
   }
}
