package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b1\b\u0007\u0018\u0000 e*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002:\u0001eB\u0011\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\t\b\u0016¢\u0006\u0004\b\u0005\u0010\u0007B\u0017\b\u0016\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0004\b\u0005\u0010\nJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\r\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\r\u0010\u0011J%\u0010\u0012\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0012\u001a\u00020\f2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016¢\u0006\u0004\b\u0012\u0010\u0014J\u0015\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00028\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00028\u0000¢\u0006\u0004\b\u0017\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0018\u0010\u0007J\u0018\u0010\u0019\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\u0019\u0010\u000eJ%\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u001e\u0010\u0006J\u0017\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010\"\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\"\u0010\u0006J$\u0010%\u001a\u00020\f2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0#H\u0082\b¢\u0006\u0004\b%\u0010&J\r\u0010'\u001a\u00028\u0000¢\u0006\u0004\b'\u0010(J\u000f\u0010)\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b)\u0010(J\u0018\u0010*\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00020\u0003H\u0096\u0002¢\u0006\u0004\b*\u0010+J\u0017\u0010,\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003H\u0002¢\u0006\u0004\b,\u0010 J\u0017\u0010-\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00028\u0000H\u0016¢\u0006\u0004\b-\u0010.J\u0018\u0010/\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u0003H\u0083\b¢\u0006\u0004\b/\u0010+J\u0018\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003H\u0083\b¢\u0006\u0004\b\u001a\u0010 JO\u00109\u001a\u00020\u00102>\u00106\u001a:\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(3\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010504¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u001000H\u0000¢\u0006\u0004\b7\u00108J\u000f\u0010:\u001a\u00020\fH\u0016¢\u0006\u0004\b:\u0010;J\r\u0010<\u001a\u00028\u0000¢\u0006\u0004\b<\u0010(J\u0017\u0010=\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00028\u0000H\u0016¢\u0006\u0004\b=\u0010.J\u000f\u0010>\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b>\u0010(J\u0017\u0010?\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003H\u0002¢\u0006\u0004\b?\u0010 J\u001f\u0010B\u001a\u00020\u00102\u0006\u0010@\u001a\u00020\u00032\u0006\u0010A\u001a\u00020\u0003H\u0002¢\u0006\u0004\bB\u0010CJ\u0017\u0010D\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003H\u0002¢\u0006\u0004\bD\u0010 J\u000f\u0010E\u001a\u00020\u0010H\u0002¢\u0006\u0004\bE\u0010\u0007J\u0017\u0010F\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00028\u0000H\u0016¢\u0006\u0004\bF\u0010\u000eJ\u001d\u0010G\u001a\u00020\f2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016¢\u0006\u0004\bG\u0010\u0014J\u0017\u0010H\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00020\u0003H\u0016¢\u0006\u0004\bH\u0010+J\r\u0010I\u001a\u00028\u0000¢\u0006\u0004\bI\u0010(J\u000f\u0010J\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\bJ\u0010(J\r\u0010K\u001a\u00028\u0000¢\u0006\u0004\bK\u0010(J\u000f\u0010L\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\bL\u0010(J\u001f\u0010O\u001a\u00020\u00102\u0006\u0010M\u001a\u00020\u00032\u0006\u0010N\u001a\u00020\u0003H\u0014¢\u0006\u0004\bO\u0010CJ\u001f\u0010P\u001a\u00020\u00102\u0006\u0010M\u001a\u00020\u00032\u0006\u0010N\u001a\u00020\u0003H\u0002¢\u0006\u0004\bP\u0010CJ\u001f\u0010Q\u001a\u00020\u00102\u0006\u0010M\u001a\u00020\u00032\u0006\u0010N\u001a\u00020\u0003H\u0002¢\u0006\u0004\bQ\u0010CJ\u001d\u0010R\u001a\u00020\f2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016¢\u0006\u0004\bR\u0010\u0014J \u0010S\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\bS\u0010TJ\u001f\u0010V\u001a\u00020\u00102\u0006\u0010M\u001a\u00020\u00032\u0006\u0010N\u001a\u00020\u0003H\u0000¢\u0006\u0004\bU\u0010CJ\u0017\u0010Y\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010504H\u0000¢\u0006\u0004\bW\u0010XJ)\u0010Y\u001a\b\u0012\u0004\u0012\u00028\u000104\"\u0004\b\u0001\u0010Z2\f\u0010[\u001a\b\u0012\u0004\u0012\u00028\u000104H\u0000¢\u0006\u0004\bW\u0010\\J\u0017\u0010]\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010504H\u0016¢\u0006\u0004\b]\u0010XJ)\u0010]\u001a\b\u0012\u0004\u0012\u00028\u000104\"\u0004\b\u0001\u0010Z2\f\u0010[\u001a\b\u0012\u0004\u0012\u00028\u000104H\u0016¢\u0006\u0004\b]\u0010\\R\u001e\u0010^\u001a\n\u0012\u0006\u0012\u0004\u0018\u000105048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b^\u0010_R\u0016\u00103\u001a\u00020\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u0010`R$\u0010b\u001a\u00020\u00032\u0006\u0010a\u001a\u00020\u00038\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\bb\u0010`\u001a\u0004\bc\u0010d¨\u0006f"},
   d2 = {"Lkotlin/collections/ArrayDeque;", "E", "Lkotlin/collections/AbstractMutableList;", "", "initialCapacity", "<init>", "(I)V", "()V", "", "elements", "(Ljava/util/Collection;)V", "element", "", "add", "(Ljava/lang/Object;)Z", "index", "", "(ILjava/lang/Object;)V", "addAll", "(ILjava/util/Collection;)Z", "(Ljava/util/Collection;)Z", "addFirst", "(Ljava/lang/Object;)V", "addLast", "clear", "contains", "internalIndex", "copyCollectionElements", "(ILjava/util/Collection;)V", "newCapacity", "copyElements", "decremented", "(I)I", "minCapacity", "ensureCapacity", "Lkotlin/Function1;", "predicate", "filterInPlace", "(Lkotlin/jvm/functions/Function1;)Z", "first", "()Ljava/lang/Object;", "firstOrNull", "get", "(I)Ljava/lang/Object;", "incremented", "indexOf", "(Ljava/lang/Object;)I", "internalGet", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "head", "", "", "structure", "internalStructure$kotlin_stdlib", "(Lkotlin/jvm/functions/Function2;)V", "internalStructure", "isEmpty", "()Z", "last", "lastIndexOf", "lastOrNull", "negativeMod", "internalFromIndex", "internalToIndex", "nullifyNonEmpty", "(II)V", "positiveMod", "registerModification", "remove", "removeAll", "removeAt", "removeFirst", "removeFirstOrNull", "removeLast", "removeLastOrNull", "fromIndex", "toIndex", "removeRange", "removeRangeShiftPreceding", "removeRangeShiftSucceeding", "retainAll", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "testRemoveRange$kotlin_stdlib", "testRemoveRange", "testToArray$kotlin_stdlib", "()[Ljava/lang/Object;", "testToArray", "T", "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "toArray", "elementData", "[Ljava/lang/Object;", "I", "<set-?>", "size", "getSize", "()I", "Companion", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.4"
)
@SourceDebugExtension({"SMAP\nArrayDeque.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ArrayDeque.kt\nkotlin/collections/ArrayDeque\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,660:1\n476#1,53:663\n476#1,53:716\n37#2,2:661\n*S KotlinDebug\n*F\n+ 1 ArrayDeque.kt\nkotlin/collections/ArrayDeque\n*L\n471#1:663,53\n473#1:716,53\n46#1:661,2\n*E\n"})
public final class ArrayDeque extends AbstractMutableList {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private int head;
   @NotNull
   private Object[] elementData;
   private int size;
   @NotNull
   private static final Object[] emptyElementData = new Object[0];
   private static final int defaultMinCapacity = 10;

   public int getSize() {
      return this.size;
   }

   public ArrayDeque(int initialCapacity) {
      Object[] var10001;
      if (initialCapacity == 0) {
         var10001 = emptyElementData;
      } else {
         if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
         }

         var10001 = new Object[initialCapacity];
      }

      this.elementData = var10001;
   }

   public ArrayDeque() {
      this.elementData = emptyElementData;
   }

   public ArrayDeque(@NotNull Collection elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      super();
      int $i$f$toTypedArray = 0;
      this.elementData = elements.toArray(new Object[0]);
      this.size = this.elementData.length;
      if (this.elementData.length == 0) {
         this.elementData = emptyElementData;
      }

   }

   private final void ensureCapacity(int minCapacity) {
      if (minCapacity < 0) {
         throw new IllegalStateException("Deque is too big.");
      } else if (minCapacity > this.elementData.length) {
         if (this.elementData == emptyElementData) {
            this.elementData = new Object[RangesKt.coerceAtLeast(minCapacity, 10)];
         } else {
            int newCapacity = AbstractList.Companion.newCapacity$kotlin_stdlib(this.elementData.length, minCapacity);
            this.copyElements(newCapacity);
         }
      }
   }

   private final void copyElements(int newCapacity) {
      Object[] newElements = new Object[newCapacity];
      ArraysKt.copyInto(this.elementData, newElements, 0, this.head, this.elementData.length);
      ArraysKt.copyInto(this.elementData, newElements, this.elementData.length - this.head, 0, this.head);
      this.head = 0;
      this.elementData = newElements;
   }

   @InlineOnly
   private final Object internalGet(int internalIndex) {
      return this.elementData[internalIndex];
   }

   private final int positiveMod(int index) {
      return index >= this.elementData.length ? index - this.elementData.length : index;
   }

   private final int negativeMod(int index) {
      return index < 0 ? index + this.elementData.length : index;
   }

   @InlineOnly
   private final int internalIndex(int index) {
      return this.positiveMod(this.head + index);
   }

   private final int incremented(int index) {
      return index == ArraysKt.getLastIndex(this.elementData) ? 0 : index + 1;
   }

   private final int decremented(int index) {
      return index == 0 ? ArraysKt.getLastIndex(this.elementData) : index - 1;
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public final Object first() {
      if (this.isEmpty()) {
         throw new NoSuchElementException("ArrayDeque is empty.");
      } else {
         return this.elementData[this.head];
      }
   }

   @Nullable
   public final Object firstOrNull() {
      return this.isEmpty() ? null : this.elementData[this.head];
   }

   public final Object last() {
      if (this.isEmpty()) {
         throw new NoSuchElementException("ArrayDeque is empty.");
      } else {
         return this.elementData[this.positiveMod(this.head + CollectionsKt.getLastIndex(this))];
      }
   }

   @Nullable
   public final Object lastOrNull() {
      return this.isEmpty() ? null : this.elementData[this.positiveMod(this.head + CollectionsKt.getLastIndex(this))];
   }

   public final void addFirst(Object element) {
      this.registerModification();
      this.ensureCapacity(this.size() + 1);
      this.head = this.decremented(this.head);
      this.elementData[this.head] = element;
      this.size = this.size() + 1;
   }

   public final void addLast(Object element) {
      this.registerModification();
      this.ensureCapacity(this.size() + 1);
      this.elementData[this.positiveMod(this.head + this.size())] = element;
      this.size = this.size() + 1;
   }

   public final Object removeFirst() {
      if (this.isEmpty()) {
         throw new NoSuchElementException("ArrayDeque is empty.");
      } else {
         this.registerModification();
         Object element = this.elementData[this.head];
         this.elementData[this.head] = null;
         this.head = this.incremented(this.head);
         this.size = this.size() - 1;
         return element;
      }
   }

   @Nullable
   public final Object removeFirstOrNull() {
      return this.isEmpty() ? null : this.removeFirst();
   }

   public final Object removeLast() {
      if (this.isEmpty()) {
         throw new NoSuchElementException("ArrayDeque is empty.");
      } else {
         this.registerModification();
         int internalLastIndex = this.positiveMod(this.head + CollectionsKt.getLastIndex(this));
         Object element = this.elementData[internalLastIndex];
         this.elementData[internalLastIndex] = null;
         this.size = this.size() - 1;
         return element;
      }
   }

   @Nullable
   public final Object removeLastOrNull() {
      return this.isEmpty() ? null : this.removeLast();
   }

   public boolean add(Object element) {
      this.addLast(element);
      return true;
   }

   public void add(int index, Object element) {
      AbstractList.Companion.checkPositionIndex$kotlin_stdlib(index, this.size());
      if (index == this.size()) {
         this.addLast(element);
      } else if (index == 0) {
         this.addFirst(element);
      } else {
         this.registerModification();
         this.ensureCapacity(this.size() + 1);
         int internalIndex = this.positiveMod(this.head + index);
         if (index < this.size() + 1 >> 1) {
            int decrementedInternalIndex = this.decremented(internalIndex);
            int decrementedHead = this.decremented(this.head);
            if (decrementedInternalIndex >= this.head) {
               this.elementData[decrementedHead] = this.elementData[this.head];
               ArraysKt.copyInto(this.elementData, this.elementData, this.head, this.head + 1, decrementedInternalIndex + 1);
            } else {
               ArraysKt.copyInto(this.elementData, this.elementData, this.head - 1, this.head, this.elementData.length);
               this.elementData[this.elementData.length - 1] = this.elementData[0];
               ArraysKt.copyInto(this.elementData, this.elementData, 0, 1, decrementedInternalIndex + 1);
            }

            this.elementData[decrementedInternalIndex] = element;
            this.head = decrementedHead;
         } else {
            int tail = this.positiveMod(this.head + this.size());
            if (internalIndex < tail) {
               ArraysKt.copyInto(this.elementData, this.elementData, internalIndex + 1, internalIndex, tail);
            } else {
               ArraysKt.copyInto(this.elementData, this.elementData, 1, 0, tail);
               this.elementData[0] = this.elementData[this.elementData.length - 1];
               ArraysKt.copyInto(this.elementData, this.elementData, internalIndex + 1, internalIndex, this.elementData.length - 1);
            }

            this.elementData[internalIndex] = element;
         }

         this.size = this.size() + 1;
      }
   }

   private final void copyCollectionElements(int internalIndex, Collection elements) {
      Iterator iterator = elements.iterator();
      int index = internalIndex;

      for(int var5 = this.elementData.length; index < var5 && iterator.hasNext(); ++index) {
         this.elementData[index] = iterator.next();
      }

      index = 0;

      for(int var7 = this.head; index < var7 && iterator.hasNext(); ++index) {
         this.elementData[index] = iterator.next();
      }

      this.size = this.size() + elements.size();
   }

   public boolean addAll(@NotNull Collection elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      if (elements.isEmpty()) {
         return false;
      } else {
         this.registerModification();
         this.ensureCapacity(this.size() + elements.size());
         this.copyCollectionElements(this.positiveMod(this.head + this.size()), elements);
         return true;
      }
   }

   public boolean addAll(int index, @NotNull Collection elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      AbstractList.Companion.checkPositionIndex$kotlin_stdlib(index, this.size());
      if (elements.isEmpty()) {
         return false;
      } else if (index == this.size()) {
         return this.addAll(elements);
      } else {
         this.registerModification();
         this.ensureCapacity(this.size() + elements.size());
         int tail = this.positiveMod(this.head + this.size());
         int internalIndex = this.positiveMod(this.head + index);
         int elementsSize = elements.size();
         if (index < this.size() + 1 >> 1) {
            int shiftedHead = this.head - elementsSize;
            if (internalIndex >= this.head) {
               if (shiftedHead >= 0) {
                  ArraysKt.copyInto(this.elementData, this.elementData, shiftedHead, this.head, internalIndex);
               } else {
                  shiftedHead += this.elementData.length;
                  int elementsToShift = internalIndex - this.head;
                  int shiftToBack = this.elementData.length - shiftedHead;
                  if (shiftToBack >= elementsToShift) {
                     ArraysKt.copyInto(this.elementData, this.elementData, shiftedHead, this.head, internalIndex);
                  } else {
                     ArraysKt.copyInto(this.elementData, this.elementData, shiftedHead, this.head, this.head + shiftToBack);
                     ArraysKt.copyInto(this.elementData, this.elementData, 0, this.head + shiftToBack, internalIndex);
                  }
               }
            } else {
               ArraysKt.copyInto(this.elementData, this.elementData, shiftedHead, this.head, this.elementData.length);
               if (elementsSize >= internalIndex) {
                  ArraysKt.copyInto(this.elementData, this.elementData, this.elementData.length - elementsSize, 0, internalIndex);
               } else {
                  ArraysKt.copyInto(this.elementData, this.elementData, this.elementData.length - elementsSize, 0, elementsSize);
                  ArraysKt.copyInto(this.elementData, this.elementData, 0, elementsSize, internalIndex);
               }
            }

            this.head = shiftedHead;
            this.copyCollectionElements(this.negativeMod(internalIndex - elementsSize), elements);
         } else {
            int shiftedInternalIndex = internalIndex + elementsSize;
            if (internalIndex < tail) {
               if (tail + elementsSize <= this.elementData.length) {
                  ArraysKt.copyInto(this.elementData, this.elementData, shiftedInternalIndex, internalIndex, tail);
               } else if (shiftedInternalIndex >= this.elementData.length) {
                  ArraysKt.copyInto(this.elementData, this.elementData, shiftedInternalIndex - this.elementData.length, internalIndex, tail);
               } else {
                  int shiftToFront = tail + elementsSize - this.elementData.length;
                  ArraysKt.copyInto(this.elementData, this.elementData, 0, tail - shiftToFront, tail);
                  ArraysKt.copyInto(this.elementData, this.elementData, shiftedInternalIndex, internalIndex, tail - shiftToFront);
               }
            } else {
               ArraysKt.copyInto(this.elementData, this.elementData, elementsSize, 0, tail);
               if (shiftedInternalIndex >= this.elementData.length) {
                  ArraysKt.copyInto(this.elementData, this.elementData, shiftedInternalIndex - this.elementData.length, internalIndex, this.elementData.length);
               } else {
                  ArraysKt.copyInto(this.elementData, this.elementData, 0, this.elementData.length - elementsSize, this.elementData.length);
                  ArraysKt.copyInto(this.elementData, this.elementData, shiftedInternalIndex, internalIndex, this.elementData.length - elementsSize);
               }
            }

            this.copyCollectionElements(internalIndex, elements);
         }

         return true;
      }
   }

   public Object get(int index) {
      AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this.size());
      return this.elementData[this.positiveMod(this.head + index)];
   }

   public Object set(int index, Object element) {
      AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this.size());
      int internalIndex = this.positiveMod(this.head + index);
      Object oldElement = this.elementData[internalIndex];
      this.elementData[internalIndex] = element;
      return oldElement;
   }

   public boolean contains(Object element) {
      return this.indexOf(element) != -1;
   }

   public int indexOf(Object element) {
      int tail = this.positiveMod(this.head + this.size());
      if (this.head < tail) {
         for(int index = this.head; index < tail; ++index) {
            if (Intrinsics.areEqual(element, this.elementData[index])) {
               return index - this.head;
            }
         }
      } else if (this.head >= tail) {
         int index = this.head;

         for(int var4 = this.elementData.length; index < var4; ++index) {
            if (Intrinsics.areEqual(element, this.elementData[index])) {
               return index - this.head;
            }
         }

         for(int index = 0; index < tail; ++index) {
            if (Intrinsics.areEqual(element, this.elementData[index])) {
               return index + this.elementData.length - this.head;
            }
         }
      }

      return -1;
   }

   public int lastIndexOf(Object element) {
      int tail = this.positiveMod(this.head + this.size());
      if (this.head < tail) {
         int index = tail - 1;
         int var4 = this.head;
         if (var4 <= index) {
            while(true) {
               if (Intrinsics.areEqual(element, this.elementData[index])) {
                  return index - this.head;
               }

               if (index == var4) {
                  break;
               }

               --index;
            }
         }
      } else if (this.head > tail) {
         for(int index = tail - 1; -1 < index; --index) {
            if (Intrinsics.areEqual(element, this.elementData[index])) {
               return index + this.elementData.length - this.head;
            }
         }

         int index = ArraysKt.getLastIndex(this.elementData);
         int var7 = this.head;
         if (var7 <= index) {
            while(true) {
               if (Intrinsics.areEqual(element, this.elementData[index])) {
                  return index - this.head;
               }

               if (index == var7) {
                  break;
               }

               --index;
            }
         }
      }

      return -1;
   }

   public boolean remove(Object element) {
      int index = this.indexOf(element);
      if (index == -1) {
         return false;
      } else {
         this.remove(index);
         return true;
      }
   }

   public Object removeAt(int index) {
      AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this.size());
      if (index == CollectionsKt.getLastIndex(this)) {
         return this.removeLast();
      } else if (index == 0) {
         return this.removeFirst();
      } else {
         this.registerModification();
         int internalIndex = this.positiveMod(this.head + index);
         Object element = this.elementData[internalIndex];
         if (index < this.size() >> 1) {
            if (internalIndex >= this.head) {
               ArraysKt.copyInto(this.elementData, this.elementData, this.head + 1, this.head, internalIndex);
            } else {
               ArraysKt.copyInto(this.elementData, this.elementData, 1, 0, internalIndex);
               this.elementData[0] = this.elementData[this.elementData.length - 1];
               ArraysKt.copyInto(this.elementData, this.elementData, this.head + 1, this.head, this.elementData.length - 1);
            }

            this.elementData[this.head] = null;
            this.head = this.incremented(this.head);
         } else {
            int internalLastIndex = this.positiveMod(this.head + CollectionsKt.getLastIndex(this));
            if (internalIndex <= internalLastIndex) {
               ArraysKt.copyInto(this.elementData, this.elementData, internalIndex, internalIndex + 1, internalLastIndex + 1);
            } else {
               ArraysKt.copyInto(this.elementData, this.elementData, internalIndex, internalIndex + 1, this.elementData.length);
               this.elementData[this.elementData.length - 1] = this.elementData[0];
               ArraysKt.copyInto(this.elementData, this.elementData, 0, 1, internalLastIndex + 1);
            }

            this.elementData[internalLastIndex] = null;
         }

         this.size = this.size() - 1;
         return element;
      }
   }

   public boolean removeAll(@NotNull Collection elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      ArrayDeque this_$iv = this;
      int $i$f$filterInPlace = 0;
      boolean var10000;
      if (!this.isEmpty() && this.elementData.length != 0) {
         int tail$iv = this.positiveMod(this.head + this.size());
         int newTail$iv = this.head;
         boolean modified$iv = false;
         if (this.head < tail$iv) {
            for(int index$iv = this.head; index$iv < tail$iv; ++index$iv) {
               Object element$iv = this_$iv.elementData[index$iv];
               int var10 = 0;
               if (!elements.contains(element$iv)) {
                  this_$iv.elementData[newTail$iv++] = element$iv;
               } else {
                  modified$iv = true;
               }
            }

            ArraysKt.fill(this_$iv.elementData, (Object)null, newTail$iv, tail$iv);
         } else {
            int index$iv = this.head;

            for(int element$iv = this.elementData.length; index$iv < element$iv; ++index$iv) {
               Object element$iv = this_$iv.elementData[index$iv];
               this_$iv.elementData[index$iv] = null;
               int var11 = 0;
               if (!elements.contains(element$iv)) {
                  this_$iv.elementData[newTail$iv++] = element$iv;
               } else {
                  modified$iv = true;
               }
            }

            newTail$iv = this_$iv.positiveMod(newTail$iv);

            for(int index$iv = 0; index$iv < tail$iv; ++index$iv) {
               Object element$iv = this_$iv.elementData[index$iv];
               this_$iv.elementData[index$iv] = null;
               int var16 = 0;
               if (!elements.contains(element$iv)) {
                  this_$iv.elementData[newTail$iv] = element$iv;
                  newTail$iv = this_$iv.incremented(newTail$iv);
               } else {
                  modified$iv = true;
               }
            }
         }

         if (modified$iv) {
            this_$iv.registerModification();
            this_$iv.size = this_$iv.negativeMod(newTail$iv - this_$iv.head);
         }

         var10000 = modified$iv;
      } else {
         var10000 = false;
      }

      return var10000;
   }

   public boolean retainAll(@NotNull Collection elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      ArrayDeque this_$iv = this;
      int $i$f$filterInPlace = 0;
      boolean var10000;
      if (!this.isEmpty() && this.elementData.length != 0) {
         int tail$iv = this.positiveMod(this.head + this.size());
         int newTail$iv = this.head;
         boolean modified$iv = false;
         if (this.head < tail$iv) {
            for(int index$iv = this.head; index$iv < tail$iv; ++index$iv) {
               Object element$iv = this_$iv.elementData[index$iv];
               int var10 = 0;
               if (elements.contains(element$iv)) {
                  this_$iv.elementData[newTail$iv++] = element$iv;
               } else {
                  modified$iv = true;
               }
            }

            ArraysKt.fill(this_$iv.elementData, (Object)null, newTail$iv, tail$iv);
         } else {
            int index$iv = this.head;

            for(int element$iv = this.elementData.length; index$iv < element$iv; ++index$iv) {
               Object element$iv = this_$iv.elementData[index$iv];
               this_$iv.elementData[index$iv] = null;
               int var11 = 0;
               if (elements.contains(element$iv)) {
                  this_$iv.elementData[newTail$iv++] = element$iv;
               } else {
                  modified$iv = true;
               }
            }

            newTail$iv = this_$iv.positiveMod(newTail$iv);

            for(int index$iv = 0; index$iv < tail$iv; ++index$iv) {
               Object element$iv = this_$iv.elementData[index$iv];
               this_$iv.elementData[index$iv] = null;
               int var16 = 0;
               if (elements.contains(element$iv)) {
                  this_$iv.elementData[newTail$iv] = element$iv;
                  newTail$iv = this_$iv.incremented(newTail$iv);
               } else {
                  modified$iv = true;
               }
            }
         }

         if (modified$iv) {
            this_$iv.registerModification();
            this_$iv.size = this_$iv.negativeMod(newTail$iv - this_$iv.head);
         }

         var10000 = modified$iv;
      } else {
         var10000 = false;
      }

      return var10000;
   }

   private final boolean filterInPlace(Function1 predicate) {
      int $i$f$filterInPlace = 0;
      if (!this.isEmpty() && this.elementData.length != 0) {
         int tail = this.positiveMod(this.head + this.size());
         int newTail = this.head;
         boolean modified = false;
         if (this.head < tail) {
            for(int index = this.head; index < tail; ++index) {
               Object element = this.elementData[index];
               if ((Boolean)predicate.invoke(element)) {
                  this.elementData[newTail++] = element;
               } else {
                  modified = true;
               }
            }

            ArraysKt.fill(this.elementData, (Object)null, newTail, tail);
         } else {
            int index = this.head;

            for(int element = this.elementData.length; index < element; ++index) {
               Object element = this.elementData[index];
               this.elementData[index] = null;
               if ((Boolean)predicate.invoke(element)) {
                  this.elementData[newTail++] = element;
               } else {
                  modified = true;
               }
            }

            newTail = this.positiveMod(newTail);

            for(int index = 0; index < tail; ++index) {
               Object element = this.elementData[index];
               this.elementData[index] = null;
               if ((Boolean)predicate.invoke(element)) {
                  this.elementData[newTail] = element;
                  newTail = this.incremented(newTail);
               } else {
                  modified = true;
               }
            }
         }

         if (modified) {
            this.registerModification();
            this.size = this.negativeMod(newTail - this.head);
         }

         return modified;
      } else {
         return false;
      }
   }

   public void clear() {
      if (!((Collection)this).isEmpty()) {
         this.registerModification();
         int tail = this.positiveMod(this.head + this.size());
         this.nullifyNonEmpty(this.head, tail);
      }

      this.head = 0;
      this.size = 0;
   }

   @NotNull
   public Object[] toArray(@NotNull Object[] array) {
      Intrinsics.checkNotNullParameter(array, "array");
      Object[] dest = array.length >= this.size() ? array : ArraysKt.arrayOfNulls(array, this.size());
      int tail = this.positiveMod(this.head + this.size());
      if (this.head < tail) {
         ArraysKt.copyInto$default(this.elementData, dest, 0, this.head, tail, 2, (Object)null);
      } else if (!((Collection)this).isEmpty()) {
         ArraysKt.copyInto(this.elementData, dest, 0, this.head, this.elementData.length);
         ArraysKt.copyInto(this.elementData, dest, this.elementData.length - this.head, 0, tail);
      }

      return CollectionsKt.terminateCollectionToArray(this.size(), dest);
   }

   @NotNull
   public Object[] toArray() {
      return this.toArray(new Object[this.size()]);
   }

   protected void removeRange(int fromIndex, int toIndex) {
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, this.size());
      int length = toIndex - fromIndex;
      if (length != 0) {
         if (length == this.size()) {
            this.clear();
         } else if (length == 1) {
            this.remove(fromIndex);
         } else {
            this.registerModification();
            if (fromIndex < this.size() - toIndex) {
               this.removeRangeShiftPreceding(fromIndex, toIndex);
               int newHead = this.positiveMod(this.head + length);
               this.nullifyNonEmpty(this.head, newHead);
               this.head = newHead;
            } else {
               this.removeRangeShiftSucceeding(fromIndex, toIndex);
               int tail = this.positiveMod(this.head + this.size());
               this.nullifyNonEmpty(this.negativeMod(tail - length), tail);
            }

            this.size = this.size() - length;
         }
      }
   }

   private final void removeRangeShiftPreceding(int fromIndex, int toIndex) {
      int copyFromIndex = this.positiveMod(this.head + (fromIndex - 1));
      int copyToIndex = this.positiveMod(this.head + (toIndex - 1));

      int segmentLength;
      for(int copyCount = fromIndex; copyCount > 0; copyCount -= segmentLength) {
         int var7 = copyFromIndex + 1;
         int var8 = copyToIndex + 1;
         segmentLength = Math.min(copyCount, Math.min(var7, var8));
         ArraysKt.copyInto(this.elementData, this.elementData, copyToIndex - segmentLength + 1, copyFromIndex - segmentLength + 1, copyFromIndex + 1);
         copyFromIndex = this.negativeMod(copyFromIndex - segmentLength);
         copyToIndex = this.negativeMod(copyToIndex - segmentLength);
      }

   }

   private final void removeRangeShiftSucceeding(int fromIndex, int toIndex) {
      int copyFromIndex = this.positiveMod(this.head + toIndex);
      int copyToIndex = this.positiveMod(this.head + fromIndex);

      int segmentLength;
      for(int copyCount = this.size() - toIndex; copyCount > 0; copyCount -= segmentLength) {
         int var7 = this.elementData.length - copyFromIndex;
         int var8 = this.elementData.length - copyToIndex;
         segmentLength = Math.min(copyCount, Math.min(var7, var8));
         ArraysKt.copyInto(this.elementData, this.elementData, copyToIndex, copyFromIndex, copyFromIndex + segmentLength);
         copyFromIndex = this.positiveMod(copyFromIndex + segmentLength);
         copyToIndex = this.positiveMod(copyToIndex + segmentLength);
      }

   }

   private final void nullifyNonEmpty(int internalFromIndex, int internalToIndex) {
      if (internalFromIndex < internalToIndex) {
         ArraysKt.fill(this.elementData, (Object)null, internalFromIndex, internalToIndex);
      } else {
         ArraysKt.fill(this.elementData, (Object)null, internalFromIndex, this.elementData.length);
         ArraysKt.fill(this.elementData, (Object)null, 0, internalToIndex);
      }

   }

   private final void registerModification() {
      ++this.modCount;
   }

   @NotNull
   public final Object[] testToArray$kotlin_stdlib(@NotNull Object[] array) {
      Intrinsics.checkNotNullParameter(array, "array");
      return this.toArray(array);
   }

   @NotNull
   public final Object[] testToArray$kotlin_stdlib() {
      return this.toArray();
   }

   public final void testRemoveRange$kotlin_stdlib(int fromIndex, int toIndex) {
      this.removeRange(fromIndex, toIndex);
   }

   public final void internalStructure$kotlin_stdlib(@NotNull Function2 structure) {
      Intrinsics.checkNotNullParameter(structure, "structure");
      int tail = this.positiveMod(this.head + this.size());
      int head = !this.isEmpty() && this.head >= tail ? this.head - this.elementData.length : this.head;
      structure.invoke(head, this.toArray());
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u001c\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t¨\u0006\n"},
      d2 = {"Lkotlin/collections/ArrayDeque$Companion;", "", "<init>", "()V", "", "defaultMinCapacity", "I", "", "emptyElementData", "[Ljava/lang/Object;", "kotlin-stdlib"}
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
