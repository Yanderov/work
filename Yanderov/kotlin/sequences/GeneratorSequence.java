package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\u0005\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B-\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0004\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0006¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fR\u001c\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\rR\"\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u000e¨\u0006\u000f"},
   d2 = {"Lkotlin/sequences/GeneratorSequence;", "", "T", "Lkotlin/sequences/Sequence;", "Lkotlin/Function0;", "getInitialValue", "Lkotlin/Function1;", "getNextValue", "<init>", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "", "iterator", "()Ljava/util/Iterator;", "Lkotlin/jvm/functions/Function0;", "Lkotlin/jvm/functions/Function1;", "kotlin-stdlib"}
)
final class GeneratorSequence implements Sequence {
   @NotNull
   private final Function0 getInitialValue;
   @NotNull
   private final Function1 getNextValue;

   public GeneratorSequence(@NotNull Function0 getInitialValue, @NotNull Function1 getNextValue) {
      Intrinsics.checkNotNullParameter(getInitialValue, "getInitialValue");
      Intrinsics.checkNotNullParameter(getNextValue, "getNextValue");
      super();
      this.getInitialValue = getInitialValue;
      this.getNextValue = getNextValue;
   }

   @NotNull
   public Iterator iterator() {
      return new Iterator(this) {
         private Object nextItem;
         private int nextState = -2;

         public final Object getNextItem() {
            return this.nextItem;
         }

         public final void setNextItem(Object <set-?>) {
            this.nextItem = <set-?>;
         }

         public final int getNextState() {
            return this.nextState;
         }

         public final void setNextState(int <set-?>) {
            this.nextState = <set-?>;
         }

         private final void calcNext() {
            Object var10001;
            if (this.nextState == -2) {
               var10001 = GeneratorSequence.this.getInitialValue.invoke();
            } else {
               Function1 var1 = GeneratorSequence.this.getNextValue;
               Object var10002 = this.nextItem;
               Intrinsics.checkNotNull(var10002);
               var10001 = var1.invoke(var10002);
            }

            this.nextItem = var10001;
            this.nextState = this.nextItem == null ? 0 : 1;
         }

         public Object next() {
            if (this.nextState < 0) {
               this.calcNext();
            }

            if (this.nextState == 0) {
               throw new NoSuchElementException();
            } else {
               Object var10000 = this.nextItem;
               Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type T of kotlin.sequences.GeneratorSequence");
               Object result = var10000;
               this.nextState = -1;
               return result;
            }
         }

         public boolean hasNext() {
            if (this.nextState < 0) {
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
