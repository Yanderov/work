package kotlin.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0012\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\u00060\u0003j\u0002`\u0004B\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bB\u001f\u0012\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t\u0012\u0006\u0010\f\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\rJ\u0015\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00028\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0012\u001a\u00020\u0005¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0016\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00020\u0005H\u0096\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u0019\u0010\u001aJ\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bH\u0096\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u0015\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u0005¢\u0006\u0004\b\u001f\u0010\bJ\u0017\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tH\u0014¢\u0006\u0004\b \u0010!J)\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0001\u0010\u00012\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00010\tH\u0014¢\u0006\u0004\b \u0010#J\u001c\u0010$\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u0005H\u0082\b¢\u0006\u0004\b$\u0010%R\u001c\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010&R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010'R$\u0010)\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u00058\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b)\u0010'\u001a\u0004\b*\u0010+R\u0016\u0010,\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010'¨\u0006-"},
   d2 = {"Lkotlin/collections/RingBuffer;", "T", "Lkotlin/collections/AbstractList;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "", "capacity", "<init>", "(I)V", "", "", "buffer", "filledSize", "([Ljava/lang/Object;I)V", "element", "", "add", "(Ljava/lang/Object;)V", "maxCapacity", "expanded", "(I)Lkotlin/collections/RingBuffer;", "index", "get", "(I)Ljava/lang/Object;", "", "isFull", "()Z", "", "iterator", "()Ljava/util/Iterator;", "n", "removeFirst", "toArray", "()[Ljava/lang/Object;", "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "forward", "(II)I", "[Ljava/lang/Object;", "I", "<set-?>", "size", "getSize", "()I", "startIndex", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nSlidingWindow.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SlidingWindow.kt\nkotlin/collections/RingBuffer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,206:1\n204#1:208\n204#1:209\n204#1:210\n1#2:207\n*S KotlinDebug\n*F\n+ 1 SlidingWindow.kt\nkotlin/collections/RingBuffer\n*L\n106#1:208\n175#1:209\n188#1:210\n*E\n"})
final class RingBuffer extends AbstractList implements RandomAccess {
   @NotNull
   private final Object[] buffer;
   private final int capacity;
   private int startIndex;
   private int size;

   public RingBuffer(@NotNull Object[] buffer, int filledSize) {
      Intrinsics.checkNotNullParameter(buffer, "buffer");
      super();
      this.buffer = buffer;
      boolean var3 = filledSize >= 0;
      if (!var3) {
         int var7 = 0;
         String var8 = "ring buffer filled size should not be negative but it is " + filledSize;
         throw new IllegalArgumentException(var8.toString());
      } else {
         var3 = filledSize <= this.buffer.length;
         if (!var3) {
            int var4 = 0;
            String var6 = "ring buffer filled size: " + filledSize + " cannot be larger than the buffer size: " + this.buffer.length;
            throw new IllegalArgumentException(var6.toString());
         } else {
            this.capacity = this.buffer.length;
            this.size = filledSize;
         }
      }
   }

   public RingBuffer(int capacity) {
      this(new Object[capacity], 0);
   }

   public int getSize() {
      return this.size;
   }

   public Object get(int index) {
      AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this.size());
      int $this$forward$iv = this.startIndex;
      int $i$f$forward = 0;
      return this.buffer[($this$forward$iv + index) % access$getCapacity$p(this)];
   }

   public final boolean isFull() {
      return this.size() == this.capacity;
   }

   @NotNull
   public Iterator iterator() {
      return new AbstractIterator(this) {
         private int count;
         private int index;

         {
            this.count = $receiver.size();
            this.index = $receiver.startIndex;
         }

         protected void computeNext() {
            if (this.count == 0) {
               this.done();
            } else {
               this.setNext(RingBuffer.this.buffer[this.index]);
               RingBuffer this_$iv = RingBuffer.this;
               int $this$forward$iv = this.index;
               int n$iv = 1;
               int $i$f$forward = 0;
               this.index = ($this$forward$iv + n$iv) % this_$iv.capacity;
               int var5 = this.count;
               this.count = var5 + -1;
            }

         }
      };
   }

   @NotNull
   public Object[] toArray(@NotNull Object[] array) {
      Intrinsics.checkNotNullParameter(array, "array");
      Object[] var10000;
      if (array.length < this.size()) {
         var10000 = Arrays.copyOf(array, this.size());
         Intrinsics.checkNotNullExpressionValue(var10000, "copyOf(...)");
      } else {
         var10000 = array;
      }

      Object[] result = var10000;
      int size = this.size();
      int widx = 0;

      for(int idx = this.startIndex; widx < size && idx < this.capacity; ++idx) {
         result[widx] = this.buffer[idx];
         ++widx;
      }

      for(int var6 = 0; widx < size; ++var6) {
         result[widx] = this.buffer[var6];
         ++widx;
      }

      return CollectionsKt.terminateCollectionToArray(size, result);
   }

   @NotNull
   public Object[] toArray() {
      return this.toArray(new Object[this.size()]);
   }

   @NotNull
   public final RingBuffer expanded(int maxCapacity) {
      int newCapacity = RangesKt.coerceAtMost(this.capacity + (this.capacity >> 1) + 1, maxCapacity);
      Object[] var10000;
      if (this.startIndex == 0) {
         var10000 = Arrays.copyOf(this.buffer, newCapacity);
         Intrinsics.checkNotNullExpressionValue(var10000, "copyOf(...)");
      } else {
         var10000 = this.toArray(new Object[newCapacity]);
      }

      Object[] newBuffer = var10000;
      return new RingBuffer(newBuffer, this.size());
   }

   public final void add(Object element) {
      if (this.isFull()) {
         throw new IllegalStateException("ring buffer is full");
      } else {
         Object[] var10000 = this.buffer;
         int $this$forward$iv = this.startIndex;
         int n$iv = this.size();
         int $i$f$forward = 0;
         var10000[($this$forward$iv + n$iv) % access$getCapacity$p(this)] = element;
         int this_$iv = this.size();
         this.size = this_$iv + 1;
      }
   }

   public final void removeFirst(int n) {
      int start = n >= 0;
      if (!start) {
         int var11 = 0;
         String var12 = "n shouldn't be negative but it is " + n;
         throw new IllegalArgumentException(var12.toString());
      } else {
         start = n <= this.size();
         if (!start) {
            int var9 = 0;
            String end = "n shouldn't be greater than the buffer size: n = " + n + ", size = " + this.size();
            throw new IllegalArgumentException(end.toString());
         } else {
            if (n > 0) {
               start = this.startIndex;
               int $i$f$forward = 0;
               int end = (start + n) % access$getCapacity$p(this);
               if (start > end) {
                  ArraysKt.fill(this.buffer, (Object)null, start, this.capacity);
                  ArraysKt.fill(this.buffer, (Object)null, 0, end);
               } else {
                  ArraysKt.fill(this.buffer, (Object)null, start, end);
               }

               this.startIndex = end;
               this.size = this.size() - n;
            }

         }
      }
   }

   private final int forward(int $this$forward, int n) {
      int $i$f$forward = 0;
      return ($this$forward + n) % access$getCapacity$p(this);
   }
}
