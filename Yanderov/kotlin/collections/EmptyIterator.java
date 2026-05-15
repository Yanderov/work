package kotlin.collections;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010*\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0006\u001a\u00020\u0005H\u0096\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\b\u0010\u0007J\u0010\u0010\t\u001a\u00020\u0002H\u0096\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000e\u0010\nJ\u000f\u0010\u000f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000f\u0010\r¨\u0006\u0010"},
   d2 = {"Lkotlin/collections/EmptyIterator;", "", "", "<init>", "()V", "", "hasNext", "()Z", "hasPrevious", "next", "()Ljava/lang/Void;", "", "nextIndex", "()I", "previous", "previousIndex", "kotlin-stdlib"}
)
public final class EmptyIterator implements ListIterator, KMappedMarker {
   @NotNull
   public static final EmptyIterator INSTANCE = new EmptyIterator();

   private EmptyIterator() {
   }

   public boolean hasNext() {
      return false;
   }

   public boolean hasPrevious() {
      return false;
   }

   public int nextIndex() {
      return 0;
   }

   public int previousIndex() {
      return -1;
   }

   @NotNull
   public Void next() {
      throw new NoSuchElementException();
   }

   @NotNull
   public Void previous() {
      throw new NoSuchElementException();
   }

   public void add(Void element) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public void remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public void set(Void element) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }
}
