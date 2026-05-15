package kotlin.sequences;

import java.util.HashSet;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.AbstractIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003B)\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0014¢\u0006\u0004\b\u000b\u0010\fR \u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\rR$\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00028\u00010\u000ej\b\u0012\u0004\u0012\u00028\u0001`\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0012¨\u0006\u0013"},
   d2 = {"Lkotlin/sequences/DistinctIterator;", "T", "K", "Lkotlin/collections/AbstractIterator;", "", "source", "Lkotlin/Function1;", "keySelector", "<init>", "(Ljava/util/Iterator;Lkotlin/jvm/functions/Function1;)V", "", "computeNext", "()V", "Lkotlin/jvm/functions/Function1;", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "observed", "Ljava/util/HashSet;", "Ljava/util/Iterator;", "kotlin-stdlib"}
)
final class DistinctIterator extends AbstractIterator {
   @NotNull
   private final Iterator source;
   @NotNull
   private final Function1 keySelector;
   @NotNull
   private final HashSet observed;

   public DistinctIterator(@NotNull Iterator source, @NotNull Function1 keySelector) {
      Intrinsics.checkNotNullParameter(source, "source");
      Intrinsics.checkNotNullParameter(keySelector, "keySelector");
      super();
      this.source = source;
      this.keySelector = keySelector;
      this.observed = new HashSet();
   }

   protected void computeNext() {
      while(true) {
         if (this.source.hasNext()) {
            Object next = this.source.next();
            Object key = this.keySelector.invoke(next);
            if (!this.observed.add(key)) {
               continue;
            }

            this.setNext(next);
            return;
         }

         this.done();
         return;
      }
   }
}
