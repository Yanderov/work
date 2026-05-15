package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010*\n\u0002\b\b\b\u0012\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\t\u001a\u00028\u00002\u0006\u0010\b\u001a\u00020\u0007H\u0096\u0002¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0096\u0002¢\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u000f\u0010\u0011R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0012R\u0014\u0010\u0015\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"},
   d2 = {"Lkotlin/collections/ReversedListReadOnly;", "T", "Lkotlin/collections/AbstractList;", "", "delegate", "<init>", "(Ljava/util/List;)V", "", "index", "get", "(I)Ljava/lang/Object;", "", "iterator", "()Ljava/util/Iterator;", "", "listIterator", "()Ljava/util/ListIterator;", "(I)Ljava/util/ListIterator;", "Ljava/util/List;", "getSize", "()I", "size", "kotlin-stdlib"}
)
class ReversedListReadOnly extends AbstractList {
   @NotNull
   private final List delegate;

   public ReversedListReadOnly(@NotNull List delegate) {
      Intrinsics.checkNotNullParameter(delegate, "delegate");
      super();
      this.delegate = delegate;
   }

   public int getSize() {
      return this.delegate.size();
   }

   public Object get(int index) {
      return this.delegate.get(CollectionsKt__ReversedViewsKt.access$reverseElementIndex(this, index));
   }

   @NotNull
   public Iterator iterator() {
      return (Iterator)this.listIterator(0);
   }

   @NotNull
   public ListIterator listIterator() {
      return this.listIterator(0);
   }

   @NotNull
   public ListIterator listIterator(int index) {
      return new ListIterator(this, index) {
         private final ListIterator delegateIterator;

         {
            this.delegateIterator = $receiver.delegate.listIterator(CollectionsKt__ReversedViewsKt.access$reversePositionIndex($receiver, $index));
         }

         public final ListIterator getDelegateIterator() {
            return this.delegateIterator;
         }

         public boolean hasNext() {
            return this.delegateIterator.hasPrevious();
         }

         public boolean hasPrevious() {
            return this.delegateIterator.hasNext();
         }

         public Object next() {
            return this.delegateIterator.previous();
         }

         public int nextIndex() {
            return CollectionsKt__ReversedViewsKt.access$reverseIteratorIndex(ReversedListReadOnly.this, this.delegateIterator.previousIndex());
         }

         public Object previous() {
            return this.delegateIterator.next();
         }

         public int previousIndex() {
            return CollectionsKt__ReversedViewsKt.access$reverseIteratorIndex(ReversedListReadOnly.this, this.delegateIterator.nextIndex());
         }

         public void add(Object element) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
         }

         public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
         }

         public void set(Object element) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
         }
      };
   }
}
