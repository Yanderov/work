package kotlin.jvm.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\b\u001a\u00020\u0007H\u0096\u0002¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\n\u0010\u000bR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011¨\u0006\u0012"},
   d2 = {"Lkotlin/jvm/internal/ArrayIterator;", "T", "", "", "array", "<init>", "([Ljava/lang/Object;)V", "", "hasNext", "()Z", "next", "()Ljava/lang/Object;", "[Ljava/lang/Object;", "getArray", "()[Ljava/lang/Object;", "", "index", "I", "kotlin-stdlib"}
)
final class ArrayIterator implements Iterator, KMappedMarker {
   @NotNull
   private final Object[] array;
   private int index;

   public ArrayIterator(@NotNull Object[] array) {
      Intrinsics.checkNotNullParameter(array, "array");
      super();
      this.array = array;
   }

   @NotNull
   public final Object[] getArray() {
      return this.array;
   }

   public boolean hasNext() {
      return this.index < this.array.length;
   }

   public Object next() {
      try {
         Object[] var10000 = this.array;
         int var1 = this.index++;
         Object var4 = var10000[var1];
         return var4;
      } catch (ArrayIndexOutOfBoundsException e) {
         --this.index;
         throw new NoSuchElementException(e.getMessage());
      }
   }

   public void remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }
}
