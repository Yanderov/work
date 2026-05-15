package kotlin.collections.builders;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.collections.AbstractMutableList;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableList;
import kotlin.jvm.internal.markers.KMutableListIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0010)\n\u0002\b\u0003\n\u0002\u0010+\n\u0002\b\u0015\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0000\u0018\u0000 b*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\u00060\u0003j\u0002`\u00042\b\u0012\u0004\u0012\u00028\u00000\u00052\u00060\u0006j\u0002`\u0007:\u0003cbdB\u0011\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u000e\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u000e\u0010\u0012J%\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u001d\u0010\u0015\u001a\u00020\r2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0017J-\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00132\u0006\u0010\u0019\u001a\u00020\bH\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\f\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u001c\u0010\u0012J\u0013\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001d¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\u0011H\u0002¢\u0006\u0004\b \u0010!J\u000f\u0010\"\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\"\u0010!J\u001b\u0010$\u001a\u00020\r2\n\u0010#\u001a\u0006\u0012\u0002\b\u00030\u001dH\u0002¢\u0006\u0004\b$\u0010%J\u0017\u0010'\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\bH\u0002¢\u0006\u0004\b'\u0010\u000bJ\u0017\u0010(\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\bH\u0002¢\u0006\u0004\b(\u0010\u000bJ\u001a\u0010*\u001a\u00020\r2\b\u0010#\u001a\u0004\u0018\u00010)H\u0096\u0002¢\u0006\u0004\b*\u0010\u000fJ\u0018\u0010+\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\bH\u0096\u0002¢\u0006\u0004\b+\u0010,J\u000f\u0010-\u001a\u00020\bH\u0016¢\u0006\u0004\b-\u0010.J\u0017\u0010/\u001a\u00020\b2\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b/\u00100J\u001f\u00101\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0002¢\u0006\u0004\b1\u00102J\u000f\u00103\u001a\u00020\rH\u0016¢\u0006\u0004\b3\u00104J\u0016\u00106\u001a\b\u0012\u0004\u0012\u00028\u000005H\u0096\u0002¢\u0006\u0004\b6\u00107J\u0017\u00108\u001a\u00020\b2\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b8\u00100J\u0015\u0010:\u001a\b\u0012\u0004\u0012\u00028\u000009H\u0016¢\u0006\u0004\b:\u0010;J\u001d\u0010:\u001a\b\u0012\u0004\u0012\u00028\u0000092\u0006\u0010\u0010\u001a\u00020\bH\u0016¢\u0006\u0004\b:\u0010<J\u000f\u0010=\u001a\u00020\u0011H\u0002¢\u0006\u0004\b=\u0010!J\u0017\u0010>\u001a\u00020\r2\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b>\u0010\u000fJ\u001d\u0010?\u001a\u00020\r2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0016¢\u0006\u0004\b?\u0010\u0017J\u0017\u0010@\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\bH\u0016¢\u0006\u0004\b@\u0010,J\u0017\u0010A\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\bH\u0002¢\u0006\u0004\bA\u0010,J\u001f\u0010D\u001a\u00020\u00112\u0006\u0010B\u001a\u00020\b2\u0006\u0010C\u001a\u00020\bH\u0002¢\u0006\u0004\bD\u00102J\u001d\u0010E\u001a\u00020\r2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0016¢\u0006\u0004\bE\u0010\u0017J5\u0010G\u001a\u00020\b2\u0006\u0010B\u001a\u00020\b2\u0006\u0010C\u001a\u00020\b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00132\u0006\u0010F\u001a\u00020\rH\u0002¢\u0006\u0004\bG\u0010HJ \u0010I\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\f\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\bI\u0010JJ%\u0010M\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010K\u001a\u00020\b2\u0006\u0010L\u001a\u00020\bH\u0016¢\u0006\u0004\bM\u0010NJ\u0017\u0010P\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010)0OH\u0016¢\u0006\u0004\bP\u0010QJ)\u0010P\u001a\b\u0012\u0004\u0012\u00028\u00010O\"\u0004\b\u0001\u0010R2\f\u0010S\u001a\b\u0012\u0004\u0012\u00028\u00010OH\u0016¢\u0006\u0004\bP\u0010TJ\u000f\u0010V\u001a\u00020UH\u0016¢\u0006\u0004\bV\u0010WJ\u000f\u0010X\u001a\u00020)H\u0002¢\u0006\u0004\bX\u0010YR\u001c\u0010Z\u001a\b\u0012\u0004\u0012\u00028\u00000O8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bZ\u0010[R\u0016\u0010\\\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\\\u0010]R\u0016\u0010^\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b^\u0010_R\u0014\u0010a\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b`\u0010.¨\u0006e"},
   d2 = {"Lkotlin/collections/builders/ListBuilder;", "E", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "Lkotlin/collections/AbstractMutableList;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "", "initialCapacity", "<init>", "(I)V", "element", "", "add", "(Ljava/lang/Object;)Z", "index", "", "(ILjava/lang/Object;)V", "", "elements", "addAll", "(ILjava/util/Collection;)Z", "(Ljava/util/Collection;)Z", "i", "n", "addAllInternal", "(ILjava/util/Collection;I)V", "addAtInternal", "", "build", "()Ljava/util/List;", "checkIsMutable", "()V", "clear", "other", "contentEquals", "(Ljava/util/List;)Z", "minCapacity", "ensureCapacityInternal", "ensureExtraCapacity", "", "equals", "get", "(I)Ljava/lang/Object;", "hashCode", "()I", "indexOf", "(Ljava/lang/Object;)I", "insertAtInternal", "(II)V", "isEmpty", "()Z", "", "iterator", "()Ljava/util/Iterator;", "lastIndexOf", "", "listIterator", "()Ljava/util/ListIterator;", "(I)Ljava/util/ListIterator;", "registerModification", "remove", "removeAll", "removeAt", "removeAtInternal", "rangeOffset", "rangeLength", "removeRangeInternal", "retainAll", "retain", "retainOrRemoveAllInternal", "(IILjava/util/Collection;Z)I", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "fromIndex", "toIndex", "subList", "(II)Ljava/util/List;", "", "toArray", "()[Ljava/lang/Object;", "T", "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "", "toString", "()Ljava/lang/String;", "writeReplace", "()Ljava/lang/Object;", "backing", "[Ljava/lang/Object;", "isReadOnly", "Z", "length", "I", "getSize", "size", "Companion", "BuilderSubList", "Itr", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nListBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ListBuilder.kt\nkotlin/collections/builders/ListBuilder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,718:1\n1#2:719\n*E\n"})
public final class ListBuilder extends AbstractMutableList implements List, RandomAccess, Serializable, KMutableList {
   @NotNull
   private static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private Object[] backing;
   private int length;
   private boolean isReadOnly;
   @NotNull
   private static final ListBuilder Empty;

   public ListBuilder(int initialCapacity) {
      this.backing = ListBuilderKt.arrayOfUninitializedElements(initialCapacity);
   }

   // $FF: synthetic method
   public ListBuilder(int var1, int var2, DefaultConstructorMarker var3) {
      if ((var2 & 1) != 0) {
         var1 = 10;
      }

      this(var1);
   }

   @NotNull
   public final List build() {
      this.checkIsMutable();
      this.isReadOnly = true;
      return this.length > 0 ? (List)this : (List)Empty;
   }

   private final Object writeReplace() {
      if (this.isReadOnly) {
         return new SerializedCollection((Collection)this, 0);
      } else {
         throw new NotSerializableException("The list cannot be serialized while it is being built.");
      }
   }

   public int getSize() {
      return this.length;
   }

   public boolean isEmpty() {
      return this.length == 0;
   }

   public Object get(int index) {
      AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this.length);
      return this.backing[index];
   }

   public Object set(int index, Object element) {
      this.checkIsMutable();
      AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this.length);
      Object old = this.backing[index];
      this.backing[index] = element;
      return old;
   }

   public int indexOf(Object element) {
      for(int i = 0; i < this.length; ++i) {
         if (Intrinsics.areEqual(this.backing[i], element)) {
            return i;
         }
      }

      return -1;
   }

   public int lastIndexOf(Object element) {
      for(int i = this.length - 1; i >= 0; --i) {
         if (Intrinsics.areEqual(this.backing[i], element)) {
            return i;
         }
      }

      return -1;
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
      AbstractList.Companion.checkPositionIndex$kotlin_stdlib(index, this.length);
      return new Itr(this, index);
   }

   public boolean add(Object element) {
      this.checkIsMutable();
      this.addAtInternal(this.length, element);
      return true;
   }

   public void add(int index, Object element) {
      this.checkIsMutable();
      AbstractList.Companion.checkPositionIndex$kotlin_stdlib(index, this.length);
      this.addAtInternal(index, element);
   }

   public boolean addAll(@NotNull Collection elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      this.checkIsMutable();
      int n = elements.size();
      this.addAllInternal(this.length, elements, n);
      return n > 0;
   }

   public boolean addAll(int index, @NotNull Collection elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      this.checkIsMutable();
      AbstractList.Companion.checkPositionIndex$kotlin_stdlib(index, this.length);
      int n = elements.size();
      this.addAllInternal(index, elements, n);
      return n > 0;
   }

   public void clear() {
      this.checkIsMutable();
      this.removeRangeInternal(0, this.length);
   }

   public Object removeAt(int index) {
      this.checkIsMutable();
      AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this.length);
      return this.removeAtInternal(index);
   }

   public boolean remove(Object element) {
      this.checkIsMutable();
      int i = this.indexOf(element);
      if (i >= 0) {
         this.remove(i);
      }

      return i >= 0;
   }

   public boolean removeAll(@NotNull Collection elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      this.checkIsMutable();
      return this.retainOrRemoveAllInternal(0, this.length, elements, false) > 0;
   }

   public boolean retainAll(@NotNull Collection elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      this.checkIsMutable();
      return this.retainOrRemoveAllInternal(0, this.length, elements, true) > 0;
   }

   @NotNull
   public List subList(int fromIndex, int toIndex) {
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, this.length);
      return new BuilderSubList(this.backing, fromIndex, toIndex - fromIndex, (BuilderSubList)null, this);
   }

   @NotNull
   public Object[] toArray(@NotNull Object[] array) {
      Intrinsics.checkNotNullParameter(array, "array");
      if (array.length < this.length) {
         Object[] var10000 = Arrays.copyOfRange(this.backing, 0, this.length, array.getClass());
         Intrinsics.checkNotNullExpressionValue(var10000, "copyOfRange(...)");
         return var10000;
      } else {
         ArraysKt.copyInto(this.backing, array, 0, 0, this.length);
         return CollectionsKt.terminateCollectionToArray(this.length, array);
      }
   }

   @NotNull
   public Object[] toArray() {
      Object[] var1 = this.backing;
      byte var2 = 0;
      int var3 = this.length;
      return ArraysKt.copyOfRange(var1, var2, var3);
   }

   public boolean equals(@Nullable Object other) {
      return other == this || other instanceof List && this.contentEquals((List)other);
   }

   public int hashCode() {
      return ListBuilderKt.access$subarrayContentHashCode(this.backing, 0, this.length);
   }

   @NotNull
   public String toString() {
      return ListBuilderKt.access$subarrayContentToString(this.backing, 0, this.length, (Collection)this);
   }

   private final void registerModification() {
      ++this.modCount;
   }

   private final void checkIsMutable() {
      if (this.isReadOnly) {
         throw new UnsupportedOperationException();
      }
   }

   private final void ensureExtraCapacity(int n) {
      this.ensureCapacityInternal(this.length + n);
   }

   private final void ensureCapacityInternal(int minCapacity) {
      if (minCapacity < 0) {
         throw new OutOfMemoryError();
      } else {
         if (minCapacity > this.backing.length) {
            int newSize = AbstractList.Companion.newCapacity$kotlin_stdlib(this.backing.length, minCapacity);
            this.backing = ListBuilderKt.copyOfUninitializedElements(this.backing, newSize);
         }

      }
   }

   private final boolean contentEquals(List other) {
      return ListBuilderKt.access$subarrayContentEquals(this.backing, 0, this.length, other);
   }

   private final void insertAtInternal(int i, int n) {
      this.ensureExtraCapacity(n);
      Object[] var3 = this.backing;
      Object[] var4 = this.backing;
      int var5 = this.length;
      int var6 = i + n;
      ArraysKt.copyInto(var3, var4, var6, i, var5);
      this.length += n;
   }

   private final void addAtInternal(int i, Object element) {
      this.registerModification();
      this.insertAtInternal(i, 1);
      this.backing[i] = element;
   }

   private final void addAllInternal(int i, Collection elements, int n) {
      this.registerModification();
      this.insertAtInternal(i, n);
      int j = 0;

      for(Iterator it = elements.iterator(); j < n; ++j) {
         this.backing[i + j] = it.next();
      }

   }

   private final Object removeAtInternal(int i) {
      this.registerModification();
      Object old = this.backing[i];
      Object[] var3 = this.backing;
      Object[] var4 = this.backing;
      int var5 = i + 1;
      int var6 = this.length;
      ArraysKt.copyInto(var3, var4, i, var5, var6);
      ListBuilderKt.resetAt(this.backing, this.length - 1);
      int var7 = this.length;
      this.length = var7 + -1;
      return old;
   }

   private final void removeRangeInternal(int rangeOffset, int rangeLength) {
      if (rangeLength > 0) {
         this.registerModification();
      }

      Object[] var3 = this.backing;
      Object[] var4 = this.backing;
      int var5 = rangeOffset + rangeLength;
      int var6 = this.length;
      ArraysKt.copyInto(var3, var4, rangeOffset, var5, var6);
      ListBuilderKt.resetRange(this.backing, this.length - rangeLength, this.length);
      this.length -= rangeLength;
   }

   private final int retainOrRemoveAllInternal(int rangeOffset, int rangeLength, Collection elements, boolean retain) {
      int i = 0;
      int j = 0;

      while(i < rangeLength) {
         if (elements.contains(this.backing[rangeOffset + i]) == retain) {
            this.backing[rangeOffset + j++] = this.backing[rangeOffset + i++];
         } else {
            ++i;
         }
      }

      int removed = rangeLength - j;
      Object[] var8 = this.backing;
      Object[] var9 = this.backing;
      int var10 = rangeOffset + rangeLength;
      int var11 = this.length;
      int var12 = rangeOffset + j;
      ArraysKt.copyInto(var8, var9, var12, var10, var11);
      ListBuilderKt.resetRange(this.backing, this.length - removed, this.length);
      if (removed > 0) {
         this.registerModification();
      }

      this.length -= removed;
      return removed;
   }

   public ListBuilder() {
      this(0, 1, (DefaultConstructorMarker)null);
   }

   static {
      ListBuilder it = new ListBuilder(0);
      int var2 = 0;
      it.isReadOnly = true;
      Empty = it;
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007¨\u0006\b"},
      d2 = {"Lkotlin/collections/builders/ListBuilder$Companion;", "", "<init>", "()V", "Lkotlin/collections/builders/ListBuilder;", "", "Empty", "Lkotlin/collections/builders/ListBuilder;", "kotlin-stdlib"}
   )
   private static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010+\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u00028\u00010\u0002B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fH\u0096\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0011J\u0010\u0010\u0013\u001a\u00028\u0001H\u0096\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u0017\u0010\u0014J\u000f\u0010\u0018\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0018\u0010\u0016J\u000f\u0010\u0019\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0019\u0010\u000eJ\u0017\u0010\u001a\u001a\u00020\n2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u001a\u0010\fR\u0016\u0010\u001b\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0016\u0010\u0006\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0006\u0010\u001cR\u0016\u0010\u001d\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001cR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u001e¨\u0006\u001f"},
      d2 = {"Lkotlin/collections/builders/ListBuilder$Itr;", "E", "", "Lkotlin/collections/builders/ListBuilder;", "list", "", "index", "<init>", "(Lkotlin/collections/builders/ListBuilder;I)V", "element", "", "add", "(Ljava/lang/Object;)V", "checkForComodification", "()V", "", "hasNext", "()Z", "hasPrevious", "next", "()Ljava/lang/Object;", "nextIndex", "()I", "previous", "previousIndex", "remove", "set", "expectedModCount", "I", "lastIndex", "Lkotlin/collections/builders/ListBuilder;", "kotlin-stdlib"}
   )
   @SourceDebugExtension({"SMAP\nListBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ListBuilder.kt\nkotlin/collections/builders/ListBuilder$Itr\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,718:1\n1#2:719\n*E\n"})
   private static final class Itr implements ListIterator, KMutableListIterator {
      @NotNull
      private final ListBuilder list;
      private int index;
      private int lastIndex;
      private int expectedModCount;

      public Itr(@NotNull ListBuilder list, int index) {
         Intrinsics.checkNotNullParameter(list, "list");
         super();
         this.list = list;
         this.index = index;
         this.lastIndex = -1;
         this.expectedModCount = this.list.modCount;
      }

      public boolean hasPrevious() {
         return this.index > 0;
      }

      public boolean hasNext() {
         return this.index < this.list.length;
      }

      public int previousIndex() {
         return this.index - 1;
      }

      public int nextIndex() {
         return this.index;
      }

      public Object previous() {
         this.checkForComodification();
         if (this.index <= 0) {
            throw new NoSuchElementException();
         } else {
            this.index += -1;
            this.lastIndex = this.index;
            return this.list.backing[this.lastIndex];
         }
      }

      public Object next() {
         this.checkForComodification();
         if (this.index >= this.list.length) {
            throw new NoSuchElementException();
         } else {
            int var1 = this.index++;
            this.lastIndex = var1;
            return this.list.backing[this.lastIndex];
         }
      }

      public void set(Object element) {
         this.checkForComodification();
         boolean var2 = this.lastIndex != -1;
         if (!var2) {
            int var3 = 0;
            String var4 = "Call next() or previous() before replacing element from the iterator.";
            throw new IllegalStateException(var4.toString());
         } else {
            this.list.set(this.lastIndex, element);
         }
      }

      public void add(Object element) {
         this.checkForComodification();
         ListBuilder var10000 = this.list;
         int var2 = this.index++;
         var10000.add(var2, element);
         this.lastIndex = -1;
         this.expectedModCount = this.list.modCount;
      }

      public void remove() {
         this.checkForComodification();
         boolean var1 = this.lastIndex != -1;
         if (!var1) {
            int var2 = 0;
            String var3 = "Call next() or previous() before removing element from the iterator.";
            throw new IllegalStateException(var3.toString());
         } else {
            this.list.remove(this.lastIndex);
            this.index = this.lastIndex;
            this.lastIndex = -1;
            this.expectedModCount = this.list.modCount;
         }
      }

      private final void checkForComodification() {
         if (this.list.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
         }
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\r\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0010)\n\u0002\b\u0003\n\u0002\u0010+\n\u0002\b\u001b\n\u0002\u0010\u000e\n\u0002\b\r\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u00028\u00010\u00022\u00060\u0003j\u0002`\u00042\b\u0012\u0004\u0012\u00028\u00010\u00052\u00060\u0006j\u0002`\u0007:\u0001aBC\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0000\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0014\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u0014\u0010\u0018J%\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\n2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00010\u0019H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u001d\u0010\u001b\u001a\u00020\u00132\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00010\u0019H\u0016¢\u0006\u0004\b\u001b\u0010\u001dJ-\u0010 \u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\n2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00010\u00192\u0006\u0010\u001f\u001a\u00020\nH\u0002¢\u0006\u0004\b \u0010!J\u001f\u0010\"\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00028\u0001H\u0002¢\u0006\u0004\b\"\u0010\u0018J\u000f\u0010#\u001a\u00020\u0017H\u0002¢\u0006\u0004\b#\u0010$J\u000f\u0010%\u001a\u00020\u0017H\u0002¢\u0006\u0004\b%\u0010$J\u000f\u0010&\u001a\u00020\u0017H\u0016¢\u0006\u0004\b&\u0010$J\u001b\u0010)\u001a\u00020\u00132\n\u0010(\u001a\u0006\u0012\u0002\b\u00030'H\u0002¢\u0006\u0004\b)\u0010*J\u001a\u0010,\u001a\u00020\u00132\b\u0010(\u001a\u0004\u0018\u00010+H\u0096\u0002¢\u0006\u0004\b,\u0010\u0015J\u0018\u0010-\u001a\u00028\u00012\u0006\u0010\u0016\u001a\u00020\nH\u0096\u0002¢\u0006\u0004\b-\u0010.J\u000f\u0010/\u001a\u00020\nH\u0016¢\u0006\u0004\b/\u00100J\u0017\u00101\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00028\u0001H\u0016¢\u0006\u0004\b1\u00102J\u000f\u00103\u001a\u00020\u0013H\u0016¢\u0006\u0004\b3\u00104J\u0016\u00106\u001a\b\u0012\u0004\u0012\u00028\u000105H\u0096\u0002¢\u0006\u0004\b6\u00107J\u0017\u00108\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00028\u0001H\u0016¢\u0006\u0004\b8\u00102J\u0015\u0010:\u001a\b\u0012\u0004\u0012\u00028\u000109H\u0016¢\u0006\u0004\b:\u0010;J\u001d\u0010:\u001a\b\u0012\u0004\u0012\u00028\u0001092\u0006\u0010\u0016\u001a\u00020\nH\u0016¢\u0006\u0004\b:\u0010<J\u000f\u0010=\u001a\u00020\u0017H\u0002¢\u0006\u0004\b=\u0010$J\u0017\u0010>\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00028\u0001H\u0016¢\u0006\u0004\b>\u0010\u0015J\u001d\u0010?\u001a\u00020\u00132\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00010\u0019H\u0016¢\u0006\u0004\b?\u0010\u001dJ\u0017\u0010@\u001a\u00028\u00012\u0006\u0010\u0016\u001a\u00020\nH\u0016¢\u0006\u0004\b@\u0010.J\u0017\u0010A\u001a\u00028\u00012\u0006\u0010\u001e\u001a\u00020\nH\u0002¢\u0006\u0004\bA\u0010.J\u001f\u0010D\u001a\u00020\u00172\u0006\u0010B\u001a\u00020\n2\u0006\u0010C\u001a\u00020\nH\u0002¢\u0006\u0004\bD\u0010EJ\u001d\u0010F\u001a\u00020\u00132\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00010\u0019H\u0016¢\u0006\u0004\bF\u0010\u001dJ5\u0010H\u001a\u00020\n2\u0006\u0010B\u001a\u00020\n2\u0006\u0010C\u001a\u00020\n2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00010\u00192\u0006\u0010G\u001a\u00020\u0013H\u0002¢\u0006\u0004\bH\u0010IJ \u0010J\u001a\u00028\u00012\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00028\u0001H\u0096\u0002¢\u0006\u0004\bJ\u0010KJ%\u0010N\u001a\b\u0012\u0004\u0012\u00028\u00010\u00022\u0006\u0010L\u001a\u00020\n2\u0006\u0010M\u001a\u00020\nH\u0016¢\u0006\u0004\bN\u0010OJ\u0017\u0010P\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010+0\bH\u0016¢\u0006\u0004\bP\u0010QJ)\u0010P\u001a\b\u0012\u0004\u0012\u00028\u00020\b\"\u0004\b\u0002\u0010R2\f\u0010S\u001a\b\u0012\u0004\u0012\u00028\u00020\bH\u0016¢\u0006\u0004\bP\u0010TJ\u000f\u0010V\u001a\u00020UH\u0016¢\u0006\u0004\bV\u0010WJ\u000f\u0010X\u001a\u00020+H\u0002¢\u0006\u0004\bX\u0010YR\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010ZR\u0014\u0010[\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b[\u00104R\u0016\u0010\f\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\\R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\\R\u001c\u0010\r\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010]R\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010^R\u0014\u0010`\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b_\u00100¨\u0006b"},
      d2 = {"Lkotlin/collections/builders/ListBuilder$BuilderSubList;", "E", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "Lkotlin/collections/AbstractMutableList;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "", "backing", "", "offset", "length", "parent", "Lkotlin/collections/builders/ListBuilder;", "root", "<init>", "([Ljava/lang/Object;IILkotlin/collections/builders/ListBuilder$BuilderSubList;Lkotlin/collections/builders/ListBuilder;)V", "element", "", "add", "(Ljava/lang/Object;)Z", "index", "", "(ILjava/lang/Object;)V", "", "elements", "addAll", "(ILjava/util/Collection;)Z", "(Ljava/util/Collection;)Z", "i", "n", "addAllInternal", "(ILjava/util/Collection;I)V", "addAtInternal", "checkForComodification", "()V", "checkIsMutable", "clear", "", "other", "contentEquals", "(Ljava/util/List;)Z", "", "equals", "get", "(I)Ljava/lang/Object;", "hashCode", "()I", "indexOf", "(Ljava/lang/Object;)I", "isEmpty", "()Z", "", "iterator", "()Ljava/util/Iterator;", "lastIndexOf", "", "listIterator", "()Ljava/util/ListIterator;", "(I)Ljava/util/ListIterator;", "registerModification", "remove", "removeAll", "removeAt", "removeAtInternal", "rangeOffset", "rangeLength", "removeRangeInternal", "(II)V", "retainAll", "retain", "retainOrRemoveAllInternal", "(IILjava/util/Collection;Z)I", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "fromIndex", "toIndex", "subList", "(II)Ljava/util/List;", "toArray", "()[Ljava/lang/Object;", "T", "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "", "toString", "()Ljava/lang/String;", "writeReplace", "()Ljava/lang/Object;", "[Ljava/lang/Object;", "isReadOnly", "I", "Lkotlin/collections/builders/ListBuilder$BuilderSubList;", "Lkotlin/collections/builders/ListBuilder;", "getSize", "size", "Itr", "kotlin-stdlib"}
   )
   public static final class BuilderSubList extends AbstractMutableList implements List, RandomAccess, Serializable, KMutableList {
      @NotNull
      private Object[] backing;
      private final int offset;
      private int length;
      @Nullable
      private final BuilderSubList parent;
      @NotNull
      private final ListBuilder root;

      public BuilderSubList(@NotNull Object[] backing, int offset, int length, @Nullable BuilderSubList parent, @NotNull ListBuilder root) {
         Intrinsics.checkNotNullParameter(backing, "backing");
         Intrinsics.checkNotNullParameter(root, "root");
         super();
         this.backing = backing;
         this.offset = offset;
         this.length = length;
         this.parent = parent;
         this.root = root;
         this.modCount = this.root.modCount;
      }

      private final Object writeReplace() {
         if (this.isReadOnly()) {
            return new SerializedCollection((Collection)this, 0);
         } else {
            throw new NotSerializableException("The list cannot be serialized while it is being built.");
         }
      }

      public int getSize() {
         this.checkForComodification();
         return this.length;
      }

      public boolean isEmpty() {
         this.checkForComodification();
         return this.length == 0;
      }

      public Object get(int index) {
         this.checkForComodification();
         AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this.length);
         return this.backing[this.offset + index];
      }

      public Object set(int index, Object element) {
         this.checkIsMutable();
         this.checkForComodification();
         AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this.length);
         Object old = this.backing[this.offset + index];
         this.backing[this.offset + index] = element;
         return old;
      }

      public int indexOf(Object element) {
         this.checkForComodification();

         for(int i = 0; i < this.length; ++i) {
            if (Intrinsics.areEqual(this.backing[this.offset + i], element)) {
               return i;
            }
         }

         return -1;
      }

      public int lastIndexOf(Object element) {
         this.checkForComodification();

         for(int i = this.length - 1; i >= 0; --i) {
            if (Intrinsics.areEqual(this.backing[this.offset + i], element)) {
               return i;
            }
         }

         return -1;
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
         this.checkForComodification();
         AbstractList.Companion.checkPositionIndex$kotlin_stdlib(index, this.length);
         return new Itr(this, index);
      }

      public boolean add(Object element) {
         this.checkIsMutable();
         this.checkForComodification();
         this.addAtInternal(this.offset + this.length, element);
         return true;
      }

      public void add(int index, Object element) {
         this.checkIsMutable();
         this.checkForComodification();
         AbstractList.Companion.checkPositionIndex$kotlin_stdlib(index, this.length);
         this.addAtInternal(this.offset + index, element);
      }

      public boolean addAll(@NotNull Collection elements) {
         Intrinsics.checkNotNullParameter(elements, "elements");
         this.checkIsMutable();
         this.checkForComodification();
         int n = elements.size();
         this.addAllInternal(this.offset + this.length, elements, n);
         return n > 0;
      }

      public boolean addAll(int index, @NotNull Collection elements) {
         Intrinsics.checkNotNullParameter(elements, "elements");
         this.checkIsMutable();
         this.checkForComodification();
         AbstractList.Companion.checkPositionIndex$kotlin_stdlib(index, this.length);
         int n = elements.size();
         this.addAllInternal(this.offset + index, elements, n);
         return n > 0;
      }

      public void clear() {
         this.checkIsMutable();
         this.checkForComodification();
         this.removeRangeInternal(this.offset, this.length);
      }

      public Object removeAt(int index) {
         this.checkIsMutable();
         this.checkForComodification();
         AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this.length);
         return this.removeAtInternal(this.offset + index);
      }

      public boolean remove(Object element) {
         this.checkIsMutable();
         this.checkForComodification();
         int i = this.indexOf(element);
         if (i >= 0) {
            this.remove(i);
         }

         return i >= 0;
      }

      public boolean removeAll(@NotNull Collection elements) {
         Intrinsics.checkNotNullParameter(elements, "elements");
         this.checkIsMutable();
         this.checkForComodification();
         return this.retainOrRemoveAllInternal(this.offset, this.length, elements, false) > 0;
      }

      public boolean retainAll(@NotNull Collection elements) {
         Intrinsics.checkNotNullParameter(elements, "elements");
         this.checkIsMutable();
         this.checkForComodification();
         return this.retainOrRemoveAllInternal(this.offset, this.length, elements, true) > 0;
      }

      @NotNull
      public List subList(int fromIndex, int toIndex) {
         AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, this.length);
         return new BuilderSubList(this.backing, this.offset + fromIndex, toIndex - fromIndex, this, this.root);
      }

      @NotNull
      public Object[] toArray(@NotNull Object[] array) {
         Intrinsics.checkNotNullParameter(array, "array");
         this.checkForComodification();
         if (array.length < this.length) {
            Object[] var10000 = Arrays.copyOfRange(this.backing, this.offset, this.offset + this.length, array.getClass());
            Intrinsics.checkNotNullExpressionValue(var10000, "copyOfRange(...)");
            return var10000;
         } else {
            ArraysKt.copyInto(this.backing, array, 0, this.offset, this.offset + this.length);
            return CollectionsKt.terminateCollectionToArray(this.length, array);
         }
      }

      @NotNull
      public Object[] toArray() {
         this.checkForComodification();
         Object[] var1 = this.backing;
         int var2 = this.offset;
         int var3 = this.offset + this.length;
         return ArraysKt.copyOfRange(var1, var2, var3);
      }

      public boolean equals(@Nullable Object other) {
         this.checkForComodification();
         return other == this || other instanceof List && this.contentEquals((List)other);
      }

      public int hashCode() {
         this.checkForComodification();
         return ListBuilderKt.access$subarrayContentHashCode(this.backing, this.offset, this.length);
      }

      @NotNull
      public String toString() {
         this.checkForComodification();
         return ListBuilderKt.access$subarrayContentToString(this.backing, this.offset, this.length, (Collection)this);
      }

      private final void registerModification() {
         ++this.modCount;
      }

      private final void checkForComodification() {
         if (this.root.modCount != this.modCount) {
            throw new ConcurrentModificationException();
         }
      }

      private final void checkIsMutable() {
         if (this.isReadOnly()) {
            throw new UnsupportedOperationException();
         }
      }

      private final boolean isReadOnly() {
         return this.root.isReadOnly;
      }

      private final boolean contentEquals(List other) {
         return ListBuilderKt.access$subarrayContentEquals(this.backing, this.offset, this.length, other);
      }

      private final void addAtInternal(int i, Object element) {
         this.registerModification();
         if (this.parent != null) {
            this.parent.addAtInternal(i, element);
         } else {
            this.root.addAtInternal(i, element);
         }

         this.backing = this.root.backing;
         int var3 = this.length++;
      }

      private final void addAllInternal(int i, Collection elements, int n) {
         this.registerModification();
         if (this.parent != null) {
            this.parent.addAllInternal(i, elements, n);
         } else {
            this.root.addAllInternal(i, elements, n);
         }

         this.backing = this.root.backing;
         this.length += n;
      }

      private final Object removeAtInternal(int i) {
         this.registerModification();
         Object old = this.parent != null ? this.parent.removeAtInternal(i) : this.root.removeAtInternal(i);
         int var3 = this.length;
         this.length = var3 + -1;
         return old;
      }

      private final void removeRangeInternal(int rangeOffset, int rangeLength) {
         if (rangeLength > 0) {
            this.registerModification();
         }

         if (this.parent != null) {
            this.parent.removeRangeInternal(rangeOffset, rangeLength);
         } else {
            this.root.removeRangeInternal(rangeOffset, rangeLength);
         }

         this.length -= rangeLength;
      }

      private final int retainOrRemoveAllInternal(int rangeOffset, int rangeLength, Collection elements, boolean retain) {
         int removed = this.parent != null ? this.parent.retainOrRemoveAllInternal(rangeOffset, rangeLength, elements, retain) : this.root.retainOrRemoveAllInternal(rangeOffset, rangeLength, elements, retain);
         if (removed > 0) {
            this.registerModification();
         }

         this.length -= removed;
         return removed;
      }

      @Metadata(
         mv = {1, 9, 0},
         k = 1,
         xi = 48,
         d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010+\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0002\u0018\u0000*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u00028\u00020\u0002B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00028\u0002H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fH\u0096\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0011J\u0010\u0010\u0013\u001a\u00028\u0002H\u0096\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00028\u0002H\u0016¢\u0006\u0004\b\u0017\u0010\u0014J\u000f\u0010\u0018\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0018\u0010\u0016J\u000f\u0010\u0019\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0019\u0010\u000eJ\u0017\u0010\u001a\u001a\u00020\n2\u0006\u0010\t\u001a\u00028\u0002H\u0016¢\u0006\u0004\b\u001a\u0010\fR\u0016\u0010\u001b\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0016\u0010\u0006\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0006\u0010\u001cR\u0016\u0010\u001d\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001cR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u001e¨\u0006\u001f"},
         d2 = {"Lkotlin/collections/builders/ListBuilder$BuilderSubList$Itr;", "E", "", "Lkotlin/collections/builders/ListBuilder$BuilderSubList;", "list", "", "index", "<init>", "(Lkotlin/collections/builders/ListBuilder$BuilderSubList;I)V", "element", "", "add", "(Ljava/lang/Object;)V", "checkForComodification", "()V", "", "hasNext", "()Z", "hasPrevious", "next", "()Ljava/lang/Object;", "nextIndex", "()I", "previous", "previousIndex", "remove", "set", "expectedModCount", "I", "lastIndex", "Lkotlin/collections/builders/ListBuilder$BuilderSubList;", "kotlin-stdlib"}
      )
      @SourceDebugExtension({"SMAP\nListBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ListBuilder.kt\nkotlin/collections/builders/ListBuilder$BuilderSubList$Itr\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,718:1\n1#2:719\n*E\n"})
      private static final class Itr implements ListIterator, KMutableListIterator {
         @NotNull
         private final BuilderSubList list;
         private int index;
         private int lastIndex;
         private int expectedModCount;

         public Itr(@NotNull BuilderSubList list, int index) {
            Intrinsics.checkNotNullParameter(list, "list");
            super();
            this.list = list;
            this.index = index;
            this.lastIndex = -1;
            this.expectedModCount = this.list.modCount;
         }

         public boolean hasPrevious() {
            return this.index > 0;
         }

         public boolean hasNext() {
            return this.index < this.list.length;
         }

         public int previousIndex() {
            return this.index - 1;
         }

         public int nextIndex() {
            return this.index;
         }

         public Object previous() {
            this.checkForComodification();
            if (this.index <= 0) {
               throw new NoSuchElementException();
            } else {
               this.index += -1;
               this.lastIndex = this.index;
               return this.list.backing[this.list.offset + this.lastIndex];
            }
         }

         public Object next() {
            this.checkForComodification();
            if (this.index >= this.list.length) {
               throw new NoSuchElementException();
            } else {
               int var1 = this.index++;
               this.lastIndex = var1;
               return this.list.backing[this.list.offset + this.lastIndex];
            }
         }

         public void set(Object element) {
            this.checkForComodification();
            boolean var2 = this.lastIndex != -1;
            if (!var2) {
               int var3 = 0;
               String var4 = "Call next() or previous() before replacing element from the iterator.";
               throw new IllegalStateException(var4.toString());
            } else {
               this.list.set(this.lastIndex, element);
            }
         }

         public void add(Object element) {
            this.checkForComodification();
            BuilderSubList var10000 = this.list;
            int var2 = this.index++;
            var10000.add(var2, element);
            this.lastIndex = -1;
            this.expectedModCount = this.list.modCount;
         }

         public void remove() {
            this.checkForComodification();
            boolean var1 = this.lastIndex != -1;
            if (!var1) {
               int var2 = 0;
               String var3 = "Call next() or previous() before removing element from the iterator.";
               throw new IllegalStateException(var3.toString());
            } else {
               this.list.remove(this.lastIndex);
               this.index = this.lastIndex;
               this.lastIndex = -1;
               this.expectedModCount = this.list.modCount;
            }
         }

         private final void checkForComodification() {
            if (this.list.root.modCount != this.expectedModCount) {
               throw new ConcurrentModificationException();
            }
         }
      }
   }
}
