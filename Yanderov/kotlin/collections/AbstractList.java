package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0010*\n\u0002\b\u000e\b'\u0018\u0000 \"*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003:\u0004\"#$%B\t\b\u0004¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0096\u0002¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\r\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\u000bH¦\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0096\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0017\u0010\u0013J\u0015\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u001d\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0019\u0010\u001bJ%\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u001e\u0010\u001fR\u0014\u0010!\u001a\u00020\u000b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0010¨\u0006&"},
   d2 = {"Lkotlin/collections/AbstractList;", "E", "Lkotlin/collections/AbstractCollection;", "", "<init>", "()V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "index", "get", "(I)Ljava/lang/Object;", "hashCode", "()I", "element", "indexOf", "(Ljava/lang/Object;)I", "", "iterator", "()Ljava/util/Iterator;", "lastIndexOf", "", "listIterator", "()Ljava/util/ListIterator;", "(I)Ljava/util/ListIterator;", "fromIndex", "toIndex", "subList", "(II)Ljava/util/List;", "getSize", "size", "Companion", "IteratorImpl", "ListIteratorImpl", "SubList", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.1"
)
@SourceDebugExtension({"SMAP\nAbstractList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractList.kt\nkotlin/collections/AbstractList\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,170:1\n360#2,7:171\n388#2,7:178\n*S KotlinDebug\n*F\n+ 1 AbstractList.kt\nkotlin/collections/AbstractList\n*L\n27#1:171,7\n29#1:178,7\n*E\n"})
public abstract class AbstractList extends AbstractCollection implements List, KMappedMarker {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final int maxArraySize = 2147483639;

   protected AbstractList() {
   }

   public abstract int getSize();

   public abstract Object get(int var1);

   @NotNull
   public Iterator iterator() {
      return new IteratorImpl(this);
   }

   public int indexOf(Object element) {
      List $this$indexOfFirst$iv = this;
      int $i$f$indexOfFirst = 0;
      int index$iv = 0;
      Iterator var5 = $this$indexOfFirst$iv.iterator();

      int var10000;
      while(true) {
         if (!var5.hasNext()) {
            var10000 = -1;
            break;
         }

         Object item$iv = var5.next();
         int var8 = 0;
         if (Intrinsics.areEqual(item$iv, element)) {
            var10000 = index$iv;
            break;
         }

         ++index$iv;
      }

      return var10000;
   }

   public int lastIndexOf(Object element) {
      List $this$indexOfLast$iv = this;
      int $i$f$indexOfLast = 0;
      ListIterator iterator$iv = $this$indexOfLast$iv.listIterator($this$indexOfLast$iv.size());

      int var10000;
      while(true) {
         if (iterator$iv.hasPrevious()) {
            Object it = iterator$iv.previous();
            int var6 = 0;
            if (!Intrinsics.areEqual(it, element)) {
               continue;
            }

            var10000 = iterator$iv.nextIndex();
            break;
         }

         var10000 = -1;
         break;
      }

      return var10000;
   }

   @NotNull
   public ListIterator listIterator() {
      return new ListIteratorImpl(this, 0);
   }

   @NotNull
   public ListIterator listIterator(int index) {
      return new ListIteratorImpl(this, index);
   }

   @NotNull
   public List subList(int fromIndex, int toIndex) {
      return new SubList(this, fromIndex, toIndex);
   }

   public boolean equals(@Nullable Object other) {
      if (other == this) {
         return true;
      } else {
         return !(other instanceof List) ? false : Companion.orderedEquals$kotlin_stdlib(this, (Collection)other);
      }
   }

   public int hashCode() {
      return Companion.orderedHashCode$kotlin_stdlib(this);
   }

   public void add(int index, Object element) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public boolean addAll(int index, Collection elements) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public Object remove(int index) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public Object set(int index, Object element) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\b\u0002\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u00028\u00010\u00022\u00060\u0003j\u0002`\u0004B%\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\f\u001a\u00028\u00012\u0006\u0010\u000b\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0004\b\f\u0010\rR\u0016\u0010\u000e\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u000fR\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0010R\u0014\u0010\u0013\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0014"},
      d2 = {"Lkotlin/collections/AbstractList$SubList;", "E", "Lkotlin/collections/AbstractList;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "list", "", "fromIndex", "toIndex", "<init>", "(Lkotlin/collections/AbstractList;II)V", "index", "get", "(I)Ljava/lang/Object;", "_size", "I", "Lkotlin/collections/AbstractList;", "getSize", "()I", "size", "kotlin-stdlib"}
   )
   private static final class SubList extends AbstractList implements RandomAccess {
      @NotNull
      private final AbstractList list;
      private final int fromIndex;
      private int _size;

      public SubList(@NotNull AbstractList list, int fromIndex, int toIndex) {
         Intrinsics.checkNotNullParameter(list, "list");
         super();
         this.list = list;
         this.fromIndex = fromIndex;
         AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(this.fromIndex, toIndex, this.list.size());
         this._size = toIndex - this.fromIndex;
      }

      public Object get(int index) {
         AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this._size);
         return this.list.get(this.fromIndex + index);
      }

      public int getSize() {
         return this._size;
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\b\u0092\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0005\u001a\u00020\u0004H\u0096\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\u0007\u0010\bR\"\u0010\n\u001a\u00020\t8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"},
      d2 = {"Lkotlin/collections/AbstractList$IteratorImpl;", "", "<init>", "(Lkotlin/collections/AbstractList;)V", "", "hasNext", "()Z", "next", "()Ljava/lang/Object;", "", "index", "I", "getIndex", "()I", "setIndex", "(I)V", "kotlin-stdlib"}
   )
   private class IteratorImpl implements Iterator, KMappedMarker {
      private int index;

      public IteratorImpl(AbstractList this$0) {
      }

      protected final int getIndex() {
         return this.index;
      }

      protected final void setIndex(int <set-?>) {
         this.index = <set-?>;
      }

      public boolean hasNext() {
         return this.index < AbstractList.this.size();
      }

      public Object next() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            AbstractList var10000 = AbstractList.this;
            int var1 = this.index++;
            return var10000.get(var1);
         }
      }

      public void remove() {
         throw new UnsupportedOperationException("Operation is not supported for read-only collection");
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010*\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\b\u0092\u0004\u0018\u00002\f0\u0001R\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u000f\u0010\f¨\u0006\u0010"},
      d2 = {"Lkotlin/collections/AbstractList$ListIteratorImpl;", "Lkotlin/collections/AbstractList$IteratorImpl;", "Lkotlin/collections/AbstractList;", "", "", "index", "<init>", "(Lkotlin/collections/AbstractList;I)V", "", "hasPrevious", "()Z", "nextIndex", "()I", "previous", "()Ljava/lang/Object;", "previousIndex", "kotlin-stdlib"}
   )
   private class ListIteratorImpl extends IteratorImpl implements ListIterator, KMappedMarker {
      public ListIteratorImpl(AbstractList this$0, int index) {
         super(this$0);
         AbstractList.Companion.checkPositionIndex$kotlin_stdlib(index, AbstractList.this.size());
         this.setIndex(index);
      }

      public boolean hasPrevious() {
         return this.getIndex() > 0;
      }

      public int nextIndex() {
         return this.getIndex();
      }

      public Object previous() {
         if (!this.hasPrevious()) {
            throw new NoSuchElementException();
         } else {
            AbstractList var10000 = AbstractList.this;
            this.setIndex(this.getIndex() + -1);
            return var10000.get(this.getIndex());
         }
      }

      public int previousIndex() {
         return this.getIndex() - 1;
      }

      public void add(Object element) {
         throw new UnsupportedOperationException("Operation is not supported for read-only collection");
      }

      public void set(Object element) {
         throw new UnsupportedOperationException("Operation is not supported for read-only collection");
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000f\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0011\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0010\u0010\u000eJ'\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0014\u0010\nJ\u001f\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0018\u0010\u0019J'\u0010!\u001a\u00020\u001e2\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u001b2\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\u001bH\u0000¢\u0006\u0004\b\u001f\u0010 J\u001b\u0010$\u001a\u00020\u00042\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u001bH\u0000¢\u0006\u0004\b\"\u0010#R\u0014\u0010%\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b%\u0010&¨\u0006'"},
      d2 = {"Lkotlin/collections/AbstractList$Companion;", "", "<init>", "()V", "", "startIndex", "endIndex", "size", "", "checkBoundsIndexes$kotlin_stdlib", "(III)V", "checkBoundsIndexes", "index", "checkElementIndex$kotlin_stdlib", "(II)V", "checkElementIndex", "checkPositionIndex$kotlin_stdlib", "checkPositionIndex", "fromIndex", "toIndex", "checkRangeIndexes$kotlin_stdlib", "checkRangeIndexes", "oldCapacity", "minCapacity", "newCapacity$kotlin_stdlib", "(II)I", "newCapacity", "", "c", "other", "", "orderedEquals$kotlin_stdlib", "(Ljava/util/Collection;Ljava/util/Collection;)Z", "orderedEquals", "orderedHashCode$kotlin_stdlib", "(Ljava/util/Collection;)I", "orderedHashCode", "maxArraySize", "I", "kotlin-stdlib"}
   )
   public static final class Companion {
      private Companion() {
      }

      public final void checkElementIndex$kotlin_stdlib(int index, int size) {
         if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
         }
      }

      public final void checkPositionIndex$kotlin_stdlib(int index, int size) {
         if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
         }
      }

      public final void checkRangeIndexes$kotlin_stdlib(int fromIndex, int toIndex, int size) {
         if (fromIndex >= 0 && toIndex <= size) {
            if (fromIndex > toIndex) {
               throw new IllegalArgumentException("fromIndex: " + fromIndex + " > toIndex: " + toIndex);
            }
         } else {
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + ", toIndex: " + toIndex + ", size: " + size);
         }
      }

      public final void checkBoundsIndexes$kotlin_stdlib(int startIndex, int endIndex, int size) {
         if (startIndex >= 0 && endIndex <= size) {
            if (startIndex > endIndex) {
               throw new IllegalArgumentException("startIndex: " + startIndex + " > endIndex: " + endIndex);
            }
         } else {
            throw new IndexOutOfBoundsException("startIndex: " + startIndex + ", endIndex: " + endIndex + ", size: " + size);
         }
      }

      public final int newCapacity$kotlin_stdlib(int oldCapacity, int minCapacity) {
         int newCapacity = oldCapacity + (oldCapacity >> 1);
         if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
         }

         if (newCapacity - 2147483639 > 0) {
            newCapacity = minCapacity > 2147483639 ? Integer.MAX_VALUE : 2147483639;
         }

         return newCapacity;
      }

      public final int orderedHashCode$kotlin_stdlib(@NotNull Collection c) {
         Intrinsics.checkNotNullParameter(c, "c");
         int hashCode = 1;

         for(Object e : c) {
            hashCode = 31 * hashCode + (e != null ? e.hashCode() : 0);
         }

         return hashCode;
      }

      public final boolean orderedEquals$kotlin_stdlib(@NotNull Collection c, @NotNull Collection other) {
         Intrinsics.checkNotNullParameter(c, "c");
         Intrinsics.checkNotNullParameter(other, "other");
         if (c.size() != other.size()) {
            return false;
         } else {
            Iterator otherIterator = other.iterator();

            for(Object elem : c) {
               Object elemOther = otherIterator.next();
               if (!Intrinsics.areEqual(elem, elemOther)) {
                  return false;
               }
            }

            return true;
         }
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
