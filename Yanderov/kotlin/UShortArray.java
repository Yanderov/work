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
   d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0017\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u000e\n\u0002\u0010(\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\b\u0087@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00013B\u0011\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0001\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\tJ\u0018\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0002H\u0096\u0002¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u0012\u001a\u00020\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013HÖ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0003H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001e\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010!\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u001f\u0010 J\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00020\"H\u0096\u0002¢\u0006\u0004\b#\u0010$J \u0010*\u001a\u00020'2\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u0002H\u0086\u0002¢\u0006\u0004\b(\u0010)J\u0010\u0010.\u001a\u00020+HÖ\u0001¢\u0006\u0004\b,\u0010-R\u0014\u0010\u0004\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u0010\u001dR\u001a\u0010\b\u001a\u00020\u00078\u0000X\u0081\u0004¢\u0006\f\n\u0004\b\b\u00100\u0012\u0004\b1\u00102\u0088\u0001\b\u0092\u0001\u00020\u0007\u0082\u0002\u0004\n\u0002\b!¨\u00064"},
   d2 = {"Lkotlin/UShortArray;", "", "Lkotlin/UShort;", "", "size", "constructor-impl", "(I)[S", "", "storage", "([S)[S", "element", "", "contains-xj2QHRw", "([SS)Z", "contains", "elements", "containsAll-impl", "([SLjava/util/Collection;)Z", "containsAll", "", "other", "equals-impl", "([SLjava/lang/Object;)Z", "equals", "index", "get-Mh2AYeg", "([SI)S", "get", "hashCode-impl", "([S)I", "hashCode", "isEmpty-impl", "([S)Z", "isEmpty", "", "iterator-impl", "([S)Ljava/util/Iterator;", "iterator", "value", "", "set-01HTLdE", "([SIS)V", "set", "", "toString-impl", "([S)Ljava/lang/String;", "toString", "getSize-impl", "[S", "getStorage$annotations", "()V", "Iterator", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.3"
)
@ExperimentalUnsignedTypes
@SourceDebugExtension({"SMAP\nUShortArray.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UShortArray.kt\nkotlin/UShortArray\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,87:1\n1734#2,3:88\n*S KotlinDebug\n*F\n+ 1 UShortArray.kt\nkotlin/UShortArray\n*L\n63#1:88,3\n*E\n"})
public final class UShortArray implements Collection, KMappedMarker {
   @NotNull
   private final short[] storage;

   /** @deprecated */
   // $FF: synthetic method
   @PublishedApi
   public static void getStorage$annotations() {
   }

   @NotNull
   public static short[] constructor_impl/* $FF was: constructor-impl*/(int size) {
      return constructor-impl(new short[size]);
   }

   public static final short get_Mh2AYeg/* $FF was: get-Mh2AYeg*/(short[] arg0, int index) {
      return UShort.constructor-impl(arg0[index]);
   }

   public static final void set_01HTLdE/* $FF was: set-01HTLdE*/(short[] arg0, int index, short value) {
      arg0[index] = value;
   }

   public static int getSize_impl/* $FF was: getSize-impl*/(short[] arg0) {
      return arg0.length;
   }

   public int getSize() {
      return getSize-impl(this.storage);
   }

   @NotNull
   public static java.util.Iterator iterator_impl/* $FF was: iterator-impl*/(short[] arg0) {
      return new Iterator(arg0);
   }

   @NotNull
   public java.util.Iterator iterator() {
      return iterator-impl(this.storage);
   }

   public static boolean contains_xj2QHRw/* $FF was: contains-xj2QHRw*/(short[] arg0, short element) {
      return ArraysKt.contains(arg0, element);
   }

   public boolean contains_xj2QHRw/* $FF was: contains-xj2QHRw*/(short element) {
      return contains-xj2QHRw(this.storage, element);
   }

   public static boolean containsAll_impl/* $FF was: containsAll-impl*/(short[] arg0, @NotNull Collection elements) {
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
            if (!(element$iv instanceof UShort) || !ArraysKt.contains(arg0, ((UShort)element$iv).unbox-impl())) {
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

   public static boolean isEmpty_impl/* $FF was: isEmpty-impl*/(short[] arg0) {
      return arg0.length == 0;
   }

   public boolean isEmpty() {
      return isEmpty-impl(this.storage);
   }

   public static String toString_impl/* $FF was: toString-impl*/(short[] arg0) {
      return "UShortArray(storage=" + Arrays.toString(arg0) + ')';
   }

   public String toString() {
      return toString-impl(this.storage);
   }

   public static int hashCode_impl/* $FF was: hashCode-impl*/(short[] arg0) {
      return Arrays.hashCode(arg0);
   }

   public int hashCode() {
      return hashCode-impl(this.storage);
   }

   public static boolean equals_impl/* $FF was: equals-impl*/(short[] arg0, Object other) {
      if (!(other instanceof UShortArray)) {
         return false;
      } else {
         return Intrinsics.areEqual((Object)arg0, (Object)((UShortArray)other).unbox-impl());
      }
   }

   public boolean equals(Object other) {
      return equals-impl(this.storage, other);
   }

   public boolean add_xj2QHRw/* $FF was: add-xj2QHRw*/(short element) {
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
   private UShortArray(short[] storage) {
      this.storage = storage;
   }

   @PublishedApi
   @NotNull
   public static short[] constructor_impl/* $FF was: constructor-impl*/(@NotNull short[] storage) {
      Intrinsics.checkNotNullParameter(storage, "storage");
      return storage;
   }

   // $FF: synthetic method
   public static final UShortArray box_impl/* $FF was: box-impl*/(short[] v) {
      return new UShortArray(v);
   }

   // $FF: synthetic method
   public final short[] unbox_impl/* $FF was: unbox-impl*/() {
      return this.storage;
   }

   public static final boolean equals_impl0/* $FF was: equals-impl0*/(short[] p1, short[] p2) {
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
      d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\u0010\u0017\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\b\u001a\u00020\u0007H\u0096\u0002¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\f\u001a\u00020\u0002H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\rR\u0016\u0010\u000f\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010\u0082\u0002\u0004\n\u0002\b!¨\u0006\u0011"},
      d2 = {"Lkotlin/UShortArray$Iterator;", "", "Lkotlin/UShort;", "", "array", "<init>", "([S)V", "", "hasNext", "()Z", "next-Mh2AYeg", "()S", "next", "[S", "", "index", "I", "kotlin-stdlib"}
   )
   private static final class Iterator implements java.util.Iterator, KMappedMarker {
      @NotNull
      private final short[] array;
      private int index;

      public Iterator(@NotNull short[] array) {
         Intrinsics.checkNotNullParameter(array, "array");
         super();
         this.array = array;
      }

      public boolean hasNext() {
         return this.index < this.array.length;
      }

      public short next_Mh2AYeg/* $FF was: next-Mh2AYeg*/() {
         if (this.index < this.array.length) {
            short[] var10000 = this.array;
            int var1 = this.index++;
            return UShort.constructor-impl(var10000[var1]);
         } else {
            throw new NoSuchElementException(String.valueOf(this.index));
         }
      }

      public void remove() {
         throw new UnsupportedOperationException("Operation is not supported for read-only collection");
      }
   }
}
