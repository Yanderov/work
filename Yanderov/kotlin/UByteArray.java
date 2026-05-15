package kotlin;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@JvmInline
@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u000e\n\u0002\u0010(\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\b\u0087@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00013B\u0011\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0001\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\tJ\u0018\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0002H\u0096\u0002¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u0012\u001a\u00020\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013HÖ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0003H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001e\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010!\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u001f\u0010 J\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00020\"H\u0096\u0002¢\u0006\u0004\b#\u0010$J \u0010*\u001a\u00020'2\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u0002H\u0086\u0002¢\u0006\u0004\b(\u0010)J\u0010\u0010.\u001a\u00020+HÖ\u0001¢\u0006\u0004\b,\u0010-R\u0014\u0010\u0004\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u0010\u001dR\u001a\u0010\b\u001a\u00020\u00078\u0000X\u0081\u0004¢\u0006\f\n\u0004\b\b\u00100\u0012\u0004\b1\u00102\u0088\u0001\b\u0092\u0001\u00020\u0007\u0082\u0002\u0004\n\u0002\b!¨\u00064"},
   d2 = {"Lkotlin/UByteArray;", "", "Lkotlin/UByte;", "", "size", "constructor-impl", "(I)[B", "", "storage", "([B)[B", "element", "", "contains-7apg3OU", "([BB)Z", "contains", "elements", "containsAll-impl", "([BLjava/util/Collection;)Z", "containsAll", "", "other", "equals-impl", "([BLjava/lang/Object;)Z", "equals", "index", "get-w2LRezQ", "([BI)B", "get", "hashCode-impl", "([B)I", "hashCode", "isEmpty-impl", "([B)Z", "isEmpty", "", "iterator-impl", "([B)Ljava/util/Iterator;", "iterator", "value", "", "set-VurrAj0", "([BIB)V", "set", "", "toString-impl", "([B)Ljava/lang/String;", "toString", "getSize-impl", "[B", "getStorage$annotations", "()V", "Iterator", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.3"
)
@ExperimentalUnsignedTypes
@SourceDebugExtension({"SMAP\nUByteArray.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UByteArray.kt\nkotlin/UByteArray\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,87:1\n1734#2,3:88\n*S KotlinDebug\n*F\n+ 1 UByteArray.kt\nkotlin/UByteArray\n*L\n63#1:88,3\n*E\n"})
public final class UByteArray implements Collection, KMappedMarker {
   @NotNull
   private final byte[] storage;

   /** @deprecated */
   // $FF: synthetic method
   @PublishedApi
   public static void getStorage$annotations() {
   }

   @NotNull
   public static byte[] constructor_impl/* $FF was: constructor-impl*/(int size) {
      return constructor-impl(new byte[size]);
   }

   public static final byte get_w2LRezQ/* $FF was: get-w2LRezQ*/(byte[] arg0, int index) {
      return UByte.constructor-impl(arg0[index]);
   }

   public static final void set_VurrAj0/* $FF was: set-VurrAj0*/(byte[] arg0, int index, byte value) {
      arg0[index] = value;
   }

   public static int getSize_impl/* $FF was: getSize-impl*/(byte[] arg0) {
      return arg0.length;
   }

   public int getSize() {
      return getSize-impl(this.storage);
   }

   @NotNull
   public static java.util.Iterator iterator_impl/* $FF was: iterator-impl*/(byte[] arg0) {
      return new Iterator(arg0);
   }

   @NotNull
   public java.util.Iterator iterator() {
      return iterator-impl(this.storage);
   }

   public static boolean contains_7apg3OU/* $FF was: contains-7apg3OU*/(byte[] arg0, byte element) {
      return ArraysKt.contains(arg0, element);
   }

   public boolean contains_7apg3OU/* $FF was: contains-7apg3OU*/(byte element) {
      return contains-7apg3OU(this.storage, element);
   }

   public static boolean containsAll_impl/* $FF was: containsAll-impl*/(byte[] arg0, @NotNull Collection elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      Iterable $this$all$iv = (Iterable)elements;
      int $i$f$all = 0;
      boolean var10000;
      if (((Collection)$this$all$iv).isEmpty()) {
         var10000 = true;
      } else {
         java.util.Iterator var4 = $this$all$iv.iterator();

         while(true) {
            if (!var4.hasNext()) {
               var10000 = true;
               break;
            }

            Object element$iv = var4.next();
            int var7 = 0;
            if (!(element$iv instanceof UByte) || !ArraysKt.contains(arg0, ((UByte)element$iv).unbox-impl())) {
               var10000 = false;
               break;
            }
         }
      }

      return var10000;
   }

   public boolean containsAll(@NotNull Collection elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      return containsAll-impl(this.storage, elements);
   }

   public static boolean isEmpty_impl/* $FF was: isEmpty-impl*/(byte[] arg0) {
      return arg0.length == 0;
   }

   public boolean isEmpty() {
      return isEmpty-impl(this.storage);
   }

   public static String toString_impl/* $FF was: toString-impl*/(byte[] arg0) {
      return "UByteArray(storage=" + Arrays.toString(arg0) + ')';
   }

   public String toString() {
      return toString-impl(this.storage);
   }

   public static int hashCode_impl/* $FF was: hashCode-impl*/(byte[] arg0) {
      return Arrays.hashCode(arg0);
   }

   public int hashCode() {
      return hashCode-impl(this.storage);
   }

   public static boolean equals_impl/* $FF was: equals-impl*/(byte[] arg0, Object other) {
      if (!(other instanceof UByteArray)) {
         return false;
      } else {
         return Intrinsics.areEqual((Object)arg0, (Object)((UByteArray)other).unbox-impl());
      }
   }

   public boolean equals(Object other) {
      return equals-impl(this.storage, other);
   }

   public boolean add_7apg3OU/* $FF was: add-7apg3OU*/(byte element) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public boolean addAll(Collection elements) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public void clear() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public boolean remove(Object element) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public boolean removeAll(Collection elements) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public boolean retainAll(Collection elements) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   // $FF: synthetic method
   @PublishedApi
   private UByteArray(byte[] storage) {
      this.storage = storage;
   }

   @PublishedApi
   @NotNull
   public static byte[] constructor_impl/* $FF was: constructor-impl*/(@NotNull byte[] storage) {
      Intrinsics.checkNotNullParameter(storage, "storage");
      return storage;
   }

   // $FF: synthetic method
   public static final UByteArray box_impl/* $FF was: box-impl*/(byte[] v) {
      return new UByteArray(v);
   }

   // $FF: synthetic method
   public final byte[] unbox_impl/* $FF was: unbox-impl*/() {
      return this.storage;
   }

   public static final boolean equals_impl0/* $FF was: equals-impl0*/(byte[] p1, byte[] p2) {
      return Intrinsics.areEqual((Object)p1, (Object)p2);
   }

   public Object[] toArray(Object[] array) {
      Intrinsics.checkNotNullParameter(array, "array");
      return CollectionToArray.toArray(this, array);
   }

   public Object[] toArray() {
      return CollectionToArray.toArray(this);
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\b\u001a\u00020\u0007H\u0096\u0002¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\f\u001a\u00020\u0002H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\rR\u0016\u0010\u000f\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010\u0082\u0002\u0004\n\u0002\b!¨\u0006\u0011"},
      d2 = {"Lkotlin/UByteArray$Iterator;", "", "Lkotlin/UByte;", "", "array", "<init>", "([B)V", "", "hasNext", "()Z", "next-w2LRezQ", "()B", "next", "[B", "", "index", "I", "kotlin-stdlib"}
   )
   private static final class Iterator implements java.util.Iterator, KMappedMarker {
      @NotNull
      private final byte[] array;
      private int index;

      public Iterator(@NotNull byte[] array) {
         Intrinsics.checkNotNullParameter(array, "array");
         super();
         this.array = array;
      }

      public boolean hasNext() {
         return this.index < this.array.length;
      }

      public byte next_w2LRezQ/* $FF was: next-w2LRezQ*/() {
         if (this.index < this.array.length) {
            byte[] var10000 = this.array;
            int var1 = this.index++;
            return UByte.constructor-impl(var10000[var1]);
         } else {
            throw new NoSuchElementException(String.valueOf(this.index));
         }
      }

      public void remove() {
         throw new UnsupportedOperationException("Operation is not supported for read-only collection");
      }
   }
}
