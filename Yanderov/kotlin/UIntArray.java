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
   d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u000e\n\u0002\u0010(\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\b\u0087@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00013B\u0011\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0001\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\tJ\u0018\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0002H\u0096\u0002¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u0012\u001a\u00020\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013HÖ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0003H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001e\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010!\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u001f\u0010 J\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00020\"H\u0096\u0002¢\u0006\u0004\b#\u0010$J \u0010*\u001a\u00020'2\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u0002H\u0086\u0002¢\u0006\u0004\b(\u0010)J\u0010\u0010.\u001a\u00020+HÖ\u0001¢\u0006\u0004\b,\u0010-R\u0014\u0010\u0004\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u0010\u001dR\u001a\u0010\b\u001a\u00020\u00078\u0000X\u0081\u0004¢\u0006\f\n\u0004\b\b\u00100\u0012\u0004\b1\u00102\u0088\u0001\b\u0092\u0001\u00020\u0007\u0082\u0002\u0004\n\u0002\b!¨\u00064"},
   d2 = {"Lkotlin/UIntArray;", "", "Lkotlin/UInt;", "", "size", "constructor-impl", "(I)[I", "", "storage", "([I)[I", "element", "", "contains-WZ4Q5Ns", "([II)Z", "contains", "elements", "containsAll-impl", "([ILjava/util/Collection;)Z", "containsAll", "", "other", "equals-impl", "([ILjava/lang/Object;)Z", "equals", "index", "get-pVg5ArA", "([II)I", "get", "hashCode-impl", "([I)I", "hashCode", "isEmpty-impl", "([I)Z", "isEmpty", "", "iterator-impl", "([I)Ljava/util/Iterator;", "iterator", "value", "", "set-VXSXFK8", "([III)V", "set", "", "toString-impl", "([I)Ljava/lang/String;", "toString", "getSize-impl", "[I", "getStorage$annotations", "()V", "Iterator", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.3"
)
@ExperimentalUnsignedTypes
@SourceDebugExtension({"SMAP\nUIntArray.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UIntArray.kt\nkotlin/UIntArray\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,87:1\n1734#2,3:88\n*S KotlinDebug\n*F\n+ 1 UIntArray.kt\nkotlin/UIntArray\n*L\n63#1:88,3\n*E\n"})
public final class UIntArray implements Collection, KMappedMarker {
   @NotNull
   private final int[] storage;

   /** @deprecated */
   // $FF: synthetic method
   @PublishedApi
   public static void getStorage$annotations() {
   }

   @NotNull
   public static int[] constructor_impl/* $FF was: constructor-impl*/(int size) {
      return constructor-impl(new int[size]);
   }

   public static final int get_pVg5ArA/* $FF was: get-pVg5ArA*/(int[] arg0, int index) {
      return UInt.constructor-impl(arg0[index]);
   }

   public static final void set_VXSXFK8/* $FF was: set-VXSXFK8*/(int[] arg0, int index, int value) {
      arg0[index] = value;
   }

   public static int getSize_impl/* $FF was: getSize-impl*/(int[] arg0) {
      return arg0.length;
   }

   public int getSize() {
      return getSize-impl(this.storage);
   }

   @NotNull
   public static java.util.Iterator iterator_impl/* $FF was: iterator-impl*/(int[] arg0) {
      return new Iterator(arg0);
   }

   @NotNull
   public java.util.Iterator iterator() {
      return iterator-impl(this.storage);
   }

   public static boolean contains_WZ4Q5Ns/* $FF was: contains-WZ4Q5Ns*/(int[] arg0, int element) {
      return ArraysKt.contains(arg0, element);
   }

   public boolean contains_WZ4Q5Ns/* $FF was: contains-WZ4Q5Ns*/(int element) {
      return contains-WZ4Q5Ns(this.storage, element);
   }

   public static boolean containsAll_impl/* $FF was: containsAll-impl*/(int[] arg0, @NotNull Collection elements) {
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
            if (!(element$iv instanceof UInt) || !ArraysKt.contains(arg0, ((UInt)element$iv).unbox-impl())) {
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

   public static boolean isEmpty_impl/* $FF was: isEmpty-impl*/(int[] arg0) {
      return arg0.length == 0;
   }

   public boolean isEmpty() {
      return isEmpty-impl(this.storage);
   }

   public static String toString_impl/* $FF was: toString-impl*/(int[] arg0) {
      return "UIntArray(storage=" + Arrays.toString(arg0) + ')';
   }

   public String toString() {
      return toString-impl(this.storage);
   }

   public static int hashCode_impl/* $FF was: hashCode-impl*/(int[] arg0) {
      return Arrays.hashCode(arg0);
   }

   public int hashCode() {
      return hashCode-impl(this.storage);
   }

   public static boolean equals_impl/* $FF was: equals-impl*/(int[] arg0, Object other) {
      if (!(other instanceof UIntArray)) {
         return false;
      } else {
         return Intrinsics.areEqual((Object)arg0, (Object)((UIntArray)other).unbox-impl());
      }
   }

   public boolean equals(Object other) {
      return equals-impl(this.storage, other);
   }

   public boolean add_WZ4Q5Ns/* $FF was: add-WZ4Q5Ns*/(int element) {
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
   private UIntArray(int[] storage) {
      this.storage = storage;
   }

   @PublishedApi
   @NotNull
   public static int[] constructor_impl/* $FF was: constructor-impl*/(@NotNull int[] storage) {
      Intrinsics.checkNotNullParameter(storage, "storage");
      return storage;
   }

   // $FF: synthetic method
   public static final UIntArray box_impl/* $FF was: box-impl*/(int[] v) {
      return new UIntArray(v);
   }

   // $FF: synthetic method
   public final int[] unbox_impl/* $FF was: unbox-impl*/() {
      return this.storage;
   }

   public static final boolean equals_impl0/* $FF was: equals-impl0*/(int[] p1, int[] p2) {
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
      d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\b\u001a\u00020\u0007H\u0096\u0002¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\f\u001a\u00020\u0002H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\rR\u0016\u0010\u000f\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010\u0082\u0002\u0004\n\u0002\b!¨\u0006\u0011"},
      d2 = {"Lkotlin/UIntArray$Iterator;", "", "Lkotlin/UInt;", "", "array", "<init>", "([I)V", "", "hasNext", "()Z", "next-pVg5ArA", "()I", "next", "[I", "", "index", "I", "kotlin-stdlib"}
   )
   private static final class Iterator implements java.util.Iterator, KMappedMarker {
      @NotNull
      private final int[] array;
      private int index;

      public Iterator(@NotNull int[] array) {
         Intrinsics.checkNotNullParameter(array, "array");
         super();
         this.array = array;
      }

      public boolean hasNext() {
         return this.index < this.array.length;
      }

      public int next_pVg5ArA/* $FF was: next-pVg5ArA*/() {
         if (this.index < this.array.length) {
            int[] var10000 = this.array;
            int var1 = this.index++;
            return UInt.constructor-impl(var10000[var1]);
         } else {
            throw new NoSuchElementException(String.valueOf(this.index));
         }
      }

      public void remove() {
         throw new UnsupportedOperationException("Operation is not supported for read-only collection");
      }
   }
}
