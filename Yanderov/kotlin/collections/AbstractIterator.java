package kotlin.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0003\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H$¢\u0006\u0004\b\u0006\u0010\u0004J\u000f\u0010\u0007\u001a\u00020\u0005H\u0004¢\u0006\u0004\b\u0007\u0010\u0004J\u0010\u0010\t\u001a\u00020\bH\u0096\u0002¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\u00052\u0006\u0010\r\u001a\u00028\u0000H\u0004¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0010\u0010\nR\u0018\u0010\u0011\u001a\u0004\u0018\u00018\u00008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0014\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0016"},
   d2 = {"Lkotlin/collections/AbstractIterator;", "T", "", "<init>", "()V", "", "computeNext", "done", "", "hasNext", "()Z", "next", "()Ljava/lang/Object;", "value", "setNext", "(Ljava/lang/Object;)V", "tryToComputeNext", "nextValue", "Ljava/lang/Object;", "", "state", "I", "kotlin-stdlib"}
)
public abstract class AbstractIterator implements Iterator, KMappedMarker {
   private int state;
   @Nullable
   private Object nextValue;

   public boolean hasNext() {
      boolean var10000;
      switch (this.state) {
         case 0:
            var10000 = this.tryToComputeNext();
            break;
         case 1:
            var10000 = true;
            break;
         case 2:
            var10000 = false;
            break;
         default:
            throw new IllegalArgumentException("hasNext called when the iterator is in the FAILED state.");
      }

      return var10000;
   }

   public Object next() {
      if (this.state == 1) {
         this.state = 0;
         return this.nextValue;
      } else if (this.state != 2 && this.tryToComputeNext()) {
         this.state = 0;
         return this.nextValue;
      } else {
         throw new NoSuchElementException();
      }
   }

   private final boolean tryToComputeNext() {
      this.state = 3;
      this.computeNext();
      return this.state == 1;
   }

   protected abstract void computeNext();

   protected final void setNext(Object value) {
      this.nextValue = value;
      this.state = 1;
   }

   protected final void done() {
      this.state = 2;
   }

   public void remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }
}
