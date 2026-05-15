package kotlin.collections.builders;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u000b\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010&\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010#\n\u0002\u0010'\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 \u0092\u0001*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00032\u00060\u0004j\u0002`\u0005:\f\u0092\u0001\u0093\u0001\u0094\u0001\u0095\u0001\u0096\u0001\u0097\u0001B\t\b\u0016¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u0006\u0010\nBG\b\u0002\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\b\u0012\u0006\u0010\u0012\u001a\u00020\b¢\u0006\u0004\b\u0006\u0010\u0013J\u0017\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00028\u0000H\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00010\u000bH\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u0019\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001a¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001f\u001a\u00020\u001dH\u0000¢\u0006\u0004\b\u001e\u0010\u0007J\u000f\u0010 \u001a\u00020\u001dH\u0016¢\u0006\u0004\b \u0010\u0007J\u000f\u0010!\u001a\u00020\u001dH\u0002¢\u0006\u0004\b!\u0010\u0007J\u001b\u0010'\u001a\u00020$2\n\u0010#\u001a\u0006\u0012\u0002\b\u00030\"H\u0000¢\u0006\u0004\b%\u0010&J#\u0010,\u001a\u00020$2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010(H\u0000¢\u0006\u0004\b*\u0010+J\u0017\u0010-\u001a\u00020$2\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0004\b-\u0010.J\u0017\u00100\u001a\u00020$2\u0006\u0010/\u001a\u00028\u0001H\u0016¢\u0006\u0004\b0\u0010.J\u001f\u00102\u001a\u00020$2\u000e\u00101\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u001aH\u0002¢\u0006\u0004\b2\u00103J\u0017\u00105\u001a\u00020\u001d2\u0006\u00104\u001a\u00020\bH\u0002¢\u0006\u0004\b5\u0010\nJ\u0017\u00107\u001a\u00020\u001d2\u0006\u00106\u001a\u00020\bH\u0002¢\u0006\u0004\b7\u0010\nJ\u001b\u0010;\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000108H\u0000¢\u0006\u0004\b9\u0010:J\u001a\u0010=\u001a\u00020$2\b\u00101\u001a\u0004\u0018\u00010<H\u0096\u0002¢\u0006\u0004\b=\u0010.J\u0017\u0010>\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00028\u0000H\u0002¢\u0006\u0004\b>\u0010\u0016J\u0017\u0010?\u001a\u00020\b2\u0006\u0010/\u001a\u00028\u0001H\u0002¢\u0006\u0004\b?\u0010\u0016J\u001a\u0010@\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0014\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b@\u0010AJ\u0017\u0010B\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00028\u0000H\u0002¢\u0006\u0004\bB\u0010\u0016J\u000f\u0010C\u001a\u00020\bH\u0016¢\u0006\u0004\bC\u0010DJ\u000f\u0010E\u001a\u00020$H\u0016¢\u0006\u0004\bE\u0010FJ\u001b\u0010J\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010GH\u0000¢\u0006\u0004\bH\u0010IJ!\u0010K\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0014\u001a\u00028\u00002\u0006\u0010/\u001a\u00028\u0001H\u0016¢\u0006\u0004\bK\u0010LJ%\u0010N\u001a\u00020\u001d2\u0014\u0010M\u001a\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001aH\u0016¢\u0006\u0004\bN\u0010OJ)\u0010P\u001a\u00020$2\u0018\u0010M\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010(0\"H\u0002¢\u0006\u0004\bP\u0010&J#\u0010Q\u001a\u00020$2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010(H\u0002¢\u0006\u0004\bQ\u0010+J\u0017\u0010S\u001a\u00020$2\u0006\u0010R\u001a\u00020\bH\u0002¢\u0006\u0004\bS\u0010TJ\u000f\u0010U\u001a\u00020\u001dH\u0002¢\u0006\u0004\bU\u0010\u0007J\u0017\u0010W\u001a\u00020\u001d2\u0006\u0010V\u001a\u00020\bH\u0002¢\u0006\u0004\bW\u0010\nJ\u0019\u0010X\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0004\bX\u0010AJ#\u0010Z\u001a\u00020$2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010(H\u0000¢\u0006\u0004\bY\u0010+J\u0017\u0010\\\u001a\u00020\u001d2\u0006\u0010[\u001a\u00020\bH\u0002¢\u0006\u0004\b\\\u0010\nJ\u0017\u0010^\u001a\u00020\u001d2\u0006\u0010]\u001a\u00020\bH\u0002¢\u0006\u0004\b^\u0010\nJ\u0017\u0010`\u001a\u00020$2\u0006\u0010\u0014\u001a\u00028\u0000H\u0000¢\u0006\u0004\b_\u0010.J\u0017\u0010c\u001a\u00020$2\u0006\u0010a\u001a\u00028\u0001H\u0000¢\u0006\u0004\bb\u0010.J\u0017\u0010e\u001a\u00020$2\u0006\u0010d\u001a\u00020\bH\u0002¢\u0006\u0004\be\u0010TJ\u000f\u0010g\u001a\u00020fH\u0016¢\u0006\u0004\bg\u0010hJ\u001b\u0010l\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010iH\u0000¢\u0006\u0004\bj\u0010kJ\u000f\u0010m\u001a\u00020<H\u0002¢\u0006\u0004\bm\u0010nR\u0014\u0010p\u001a\u00020\b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bo\u0010DR&\u0010u\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010r0q8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bs\u0010tR$\u0010w\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010v8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bw\u0010xR\u0016\u0010\u0010\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010yR\u0016\u0010z\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bz\u0010{R\u0014\u0010}\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b|\u0010DR&\u0010\u007f\u001a\u00020$2\u0006\u0010~\u001a\u00020$8\u0000@BX\u0080\u000e¢\u0006\u000e\n\u0005\b\u007f\u0010\u0080\u0001\u001a\u0005\b\u0081\u0001\u0010FR\u001c\u0010\u0083\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000q8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0082\u0001\u0010tR\u001d\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\f\u0010\u0084\u0001R\"\u0010\u0086\u0001\u001a\u000b\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0085\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0086\u0001\u0010\u0087\u0001R\u0016\u0010\u0012\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010{R\u0016\u0010\u0011\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010{R\u0018\u0010\u0088\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0088\u0001\u0010{R\u0016\u0010\u000f\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010yR'\u0010\u0089\u0001\u001a\u00020\b2\u0006\u0010~\u001a\u00020\b8\u0016@RX\u0096\u000e¢\u0006\u000e\n\u0005\b\u0089\u0001\u0010{\u001a\u0005\b\u008a\u0001\u0010DR\u001e\u0010\u008e\u0001\u001a\t\u0012\u0004\u0012\u00028\u00010\u008b\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u008c\u0001\u0010\u008d\u0001R\u001f\u0010\r\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\r\u0010\u0084\u0001R\"\u0010\u0090\u0001\u001a\u000b\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u008f\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0090\u0001\u0010\u0091\u0001¨\u0006\u0098\u0001"},
   d2 = {"Lkotlin/collections/builders/MapBuilder;", "K", "V", "", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "<init>", "()V", "", "initialCapacity", "(I)V", "", "keysArray", "valuesArray", "", "presenceArray", "hashArray", "maxProbeDistance", "length", "([Ljava/lang/Object;[Ljava/lang/Object;[I[III)V", "key", "addKey$kotlin_stdlib", "(Ljava/lang/Object;)I", "addKey", "allocateValuesArray", "()[Ljava/lang/Object;", "", "build", "()Ljava/util/Map;", "", "checkIsMutable$kotlin_stdlib", "checkIsMutable", "clear", "compact", "", "m", "", "containsAllEntries$kotlin_stdlib", "(Ljava/util/Collection;)Z", "containsAllEntries", "", "entry", "containsEntry$kotlin_stdlib", "(Ljava/util/Map$Entry;)Z", "containsEntry", "containsKey", "(Ljava/lang/Object;)Z", "value", "containsValue", "other", "contentEquals", "(Ljava/util/Map;)Z", "minCapacity", "ensureCapacity", "n", "ensureExtraCapacity", "Lkotlin/collections/builders/MapBuilder$EntriesItr;", "entriesIterator$kotlin_stdlib", "()Lkotlin/collections/builders/MapBuilder$EntriesItr;", "entriesIterator", "", "equals", "findKey", "findValue", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "hash", "hashCode", "()I", "isEmpty", "()Z", "Lkotlin/collections/builders/MapBuilder$KeysItr;", "keysIterator$kotlin_stdlib", "()Lkotlin/collections/builders/MapBuilder$KeysItr;", "keysIterator", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "from", "putAll", "(Ljava/util/Map;)V", "putAllEntries", "putEntry", "i", "putRehash", "(I)Z", "registerModification", "newHashSize", "rehash", "remove", "removeEntry$kotlin_stdlib", "removeEntry", "index", "removeEntryAt", "removedHash", "removeHashAt", "removeKey$kotlin_stdlib", "removeKey", "element", "removeValue$kotlin_stdlib", "removeValue", "extraCapacity", "shouldCompact", "", "toString", "()Ljava/lang/String;", "Lkotlin/collections/builders/MapBuilder$ValuesItr;", "valuesIterator$kotlin_stdlib", "()Lkotlin/collections/builders/MapBuilder$ValuesItr;", "valuesIterator", "writeReplace", "()Ljava/lang/Object;", "getCapacity$kotlin_stdlib", "capacity", "", "", "getEntries", "()Ljava/util/Set;", "entries", "Lkotlin/collections/builders/MapBuilderEntries;", "entriesView", "Lkotlin/collections/builders/MapBuilderEntries;", "[I", "hashShift", "I", "getHashSize", "hashSize", "<set-?>", "isReadOnly", "Z", "isReadOnly$kotlin_stdlib", "getKeys", "keys", "[Ljava/lang/Object;", "Lkotlin/collections/builders/MapBuilderKeys;", "keysView", "Lkotlin/collections/builders/MapBuilderKeys;", "modCount", "size", "getSize", "", "getValues", "()Ljava/util/Collection;", "values", "Lkotlin/collections/builders/MapBuilderValues;", "valuesView", "Lkotlin/collections/builders/MapBuilderValues;", "Companion", "EntriesItr", "EntryRef", "Itr", "KeysItr", "ValuesItr", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nMapBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MapBuilder.kt\nkotlin/collections/builders/MapBuilder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,728:1\n1#2:729\n*E\n"})
public final class MapBuilder implements Map, Serializable, KMutableMap {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private Object[] keysArray;
   @Nullable
   private Object[] valuesArray;
   @NotNull
   private int[] presenceArray;
   @NotNull
   private int[] hashArray;
   private int maxProbeDistance;
   private int length;
   private int hashShift;
   private int modCount;
   private int size;
   @Nullable
   private MapBuilderKeys keysView;
   @Nullable
   private MapBuilderValues valuesView;
   @Nullable
   private MapBuilderEntries entriesView;
   private boolean isReadOnly;
   private static final int MAGIC = -1640531527;
   private static final int INITIAL_CAPACITY = 8;
   private static final int INITIAL_MAX_PROBE_DISTANCE = 2;
   private static final int TOMBSTONE = -1;
   @NotNull
   private static final MapBuilder Empty;

   private MapBuilder(Object[] keysArray, Object[] valuesArray, int[] presenceArray, int[] hashArray, int maxProbeDistance, int length) {
      this.keysArray = keysArray;
      this.valuesArray = valuesArray;
      this.presenceArray = presenceArray;
      this.hashArray = hashArray;
      this.maxProbeDistance = maxProbeDistance;
      this.length = length;
      this.hashShift = Companion.computeShift(this.getHashSize());
   }

   public int getSize() {
      return this.size;
   }

   public final boolean isReadOnly$kotlin_stdlib() {
      return this.isReadOnly;
   }

   public MapBuilder() {
      this(8);
   }

   public MapBuilder(int initialCapacity) {
      this(ListBuilderKt.arrayOfUninitializedElements(initialCapacity), (Object[])null, new int[initialCapacity], new int[Companion.computeHashSize(initialCapacity)], 2, 0);
   }

   @NotNull
   public final Map build() {
      this.checkIsMutable$kotlin_stdlib();
      this.isReadOnly = true;
      Map var10000;
      if (this.size() > 0) {
         var10000 = this;
      } else {
         var10000 = Empty;
         Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.builders.MapBuilder, V of kotlin.collections.builders.MapBuilder>");
         var10000 = var10000;
      }

      return var10000;
   }

   private final Object writeReplace() {
      if (this.isReadOnly) {
         return new SerializedMap(this);
      } else {
         throw new NotSerializableException("The map cannot be serialized while it is being built.");
      }
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public boolean containsKey(Object key) {
      return this.findKey(key) >= 0;
   }

   public boolean containsValue(Object value) {
      return this.findValue(value) >= 0;
   }

   @Nullable
   public Object get(Object key) {
      int index = this.findKey(key);
      if (index < 0) {
         return null;
      } else {
         Object[] var10000 = this.valuesArray;
         Intrinsics.checkNotNull(var10000);
         return var10000[index];
      }
   }

   @Nullable
   public Object put(Object key, Object value) {
      this.checkIsMutable$kotlin_stdlib();
      int index = this.addKey$kotlin_stdlib(key);
      Object[] valuesArray = this.allocateValuesArray();
      if (index < 0) {
         Object oldValue = valuesArray[-index - 1];
         valuesArray[-index - 1] = value;
         return oldValue;
      } else {
         valuesArray[index] = value;
         return null;
      }
   }

   public void putAll(@NotNull Map from) {
      Intrinsics.checkNotNullParameter(from, "from");
      this.checkIsMutable$kotlin_stdlib();
      this.putAllEntries((Collection)from.entrySet());
   }

   @Nullable
   public Object remove(Object key) {
      this.checkIsMutable$kotlin_stdlib();
      int index = this.findKey(key);
      if (index < 0) {
         return null;
      } else {
         Object[] var10000 = this.valuesArray;
         Intrinsics.checkNotNull(var10000);
         Object oldValue = var10000[index];
         this.removeEntryAt(index);
         return oldValue;
      }
   }

   public void clear() {
      this.checkIsMutable$kotlin_stdlib();
      int i = 0;
      int var2 = this.length - 1;
      if (i <= var2) {
         while(true) {
            int hash = this.presenceArray[i];
            if (hash >= 0) {
               this.hashArray[hash] = 0;
               this.presenceArray[i] = -1;
            }

            if (i == var2) {
               break;
            }

            ++i;
         }
      }

      ListBuilderKt.resetRange(this.keysArray, 0, this.length);
      Object[] var10000 = this.valuesArray;
      if (var10000 != null) {
         ListBuilderKt.resetRange(var10000, 0, this.length);
      }

      this.size = 0;
      this.length = 0;
      this.registerModification();
   }

   @NotNull
   public Set getKeys() {
      MapBuilderKeys cur = this.keysView;
      Set var10000;
      if (cur == null) {
         MapBuilderKeys var2 = new MapBuilderKeys(this);
         this.keysView = var2;
         var10000 = var2;
      } else {
         var10000 = cur;
      }

      return var10000;
   }

   @NotNull
   public Collection getValues() {
      MapBuilderValues cur = this.valuesView;
      Collection var10000;
      if (cur == null) {
         MapBuilderValues var2 = new MapBuilderValues(this);
         this.valuesView = var2;
         var10000 = var2;
      } else {
         var10000 = cur;
      }

      return var10000;
   }

   @NotNull
   public Set getEntries() {
      MapBuilderEntries cur = this.entriesView;
      if (cur == null) {
         MapBuilderEntries var2 = new MapBuilderEntries(this);
         this.entriesView = var2;
         return var2;
      } else {
         return cur;
      }
   }

   public boolean equals(@Nullable Object other) {
      return other == this || other instanceof Map && this.contentEquals((Map)other);
   }

   public int hashCode() {
      int result = 0;

      for(EntriesItr it = this.entriesIterator$kotlin_stdlib(); it.hasNext(); result += it.nextHashCode$kotlin_stdlib()) {
      }

      return result;
   }

   @NotNull
   public String toString() {
      StringBuilder sb = new StringBuilder(2 + this.size() * 3);
      sb.append("{");
      int i = 0;

      for(EntriesItr it = this.entriesIterator$kotlin_stdlib(); it.hasNext(); ++i) {
         if (i > 0) {
            sb.append(", ");
         }

         it.nextAppendString(sb);
      }

      sb.append("}");
      String var10000 = sb.toString();
      Intrinsics.checkNotNullExpressionValue(var10000, "toString(...)");
      return var10000;
   }

   public final int getCapacity$kotlin_stdlib() {
      return this.keysArray.length;
   }

   private final int getHashSize() {
      return this.hashArray.length;
   }

   private final void registerModification() {
      ++this.modCount;
   }

   public final void checkIsMutable$kotlin_stdlib() {
      if (this.isReadOnly) {
         throw new UnsupportedOperationException();
      }
   }

   private final void ensureExtraCapacity(int n) {
      if (this.shouldCompact(n)) {
         this.rehash(this.getHashSize());
      } else {
         this.ensureCapacity(this.length + n);
      }

   }

   private final boolean shouldCompact(int extraCapacity) {
      int spareCapacity = this.getCapacity$kotlin_stdlib() - this.length;
      int gaps = this.length - this.size();
      return spareCapacity < extraCapacity && gaps + spareCapacity >= extraCapacity && gaps >= this.getCapacity$kotlin_stdlib() / 4;
   }

   private final void ensureCapacity(int minCapacity) {
      if (minCapacity < 0) {
         throw new OutOfMemoryError();
      } else {
         if (minCapacity > this.getCapacity$kotlin_stdlib()) {
            int newSize = AbstractList.Companion.newCapacity$kotlin_stdlib(this.getCapacity$kotlin_stdlib(), minCapacity);
            this.keysArray = ListBuilderKt.copyOfUninitializedElements(this.keysArray, newSize);
            int[] var10001 = (int[])this.valuesArray;
            this.valuesArray = var10001 != null ? ListBuilderKt.copyOfUninitializedElements(var10001, newSize) : null;
            var10001 = Arrays.copyOf(this.presenceArray, newSize);
            Intrinsics.checkNotNullExpressionValue(var10001, "copyOf(...)");
            this.presenceArray = var10001;
            int newHashSize = Companion.computeHashSize(newSize);
            if (newHashSize > this.getHashSize()) {
               this.rehash(newHashSize);
            }
         }

      }
   }

   private final Object[] allocateValuesArray() {
      Object[] curValuesArray = this.valuesArray;
      if (curValuesArray != null) {
         return curValuesArray;
      } else {
         Object[] newValuesArray = ListBuilderKt.arrayOfUninitializedElements(this.getCapacity$kotlin_stdlib());
         this.valuesArray = newValuesArray;
         return newValuesArray;
      }
   }

   private final int hash(Object key) {
      return (key != null ? key.hashCode() : 0) * -1640531527 >>> this.hashShift;
   }

   private final void compact() {
      int i = 0;
      int j = 0;

      Object[] valuesArray;
      for(valuesArray = this.valuesArray; i < this.length; ++i) {
         if (this.presenceArray[i] >= 0) {
            this.keysArray[j] = this.keysArray[i];
            if (valuesArray != null) {
               valuesArray[j] = valuesArray[i];
            }

            ++j;
         }
      }

      ListBuilderKt.resetRange(this.keysArray, j, this.length);
      if (valuesArray != null) {
         ListBuilderKt.resetRange(valuesArray, j, this.length);
      }

      this.length = j;
   }

   private final void rehash(int newHashSize) {
      this.registerModification();
      if (this.length > this.size()) {
         this.compact();
      }

      if (newHashSize != this.getHashSize()) {
         this.hashArray = new int[newHashSize];
         this.hashShift = Companion.computeShift(newHashSize);
      } else {
         ArraysKt.fill(this.hashArray, 0, 0, this.getHashSize());
      }

      int i = 0;

      while(i < this.length) {
         if (!this.putRehash(i++)) {
            throw new IllegalStateException("This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?");
         }
      }

   }

   private final boolean putRehash(int i) {
      int hash = this.hash(this.keysArray[i]);
      int probesLeft = this.maxProbeDistance;

      while(true) {
         int index = this.hashArray[hash];
         if (index == 0) {
            this.hashArray[hash] = i + 1;
            this.presenceArray[i] = hash;
            return true;
         }

         --probesLeft;
         if (probesLeft < 0) {
            return false;
         }

         if (hash-- == 0) {
            hash = this.getHashSize() - 1;
         }
      }
   }

   private final int findKey(Object key) {
      int hash = this.hash(key);
      int probesLeft = this.maxProbeDistance;

      while(true) {
         int index = this.hashArray[hash];
         if (index == 0) {
            return -1;
         }

         if (index > 0 && Intrinsics.areEqual(this.keysArray[index - 1], key)) {
            return index - 1;
         }

         --probesLeft;
         if (probesLeft < 0) {
            return -1;
         }

         if (hash-- == 0) {
            hash = this.getHashSize() - 1;
         }
      }
   }

   private final int findValue(Object value) {
      int i = this.length;

      while(true) {
         --i;
         if (i < 0) {
            return -1;
         }

         if (this.presenceArray[i] >= 0) {
            Object[] var10000 = this.valuesArray;
            Intrinsics.checkNotNull(var10000);
            if (Intrinsics.areEqual(var10000[i], value)) {
               return i;
            }
         }
      }
   }

   public final int addKey$kotlin_stdlib(Object key) {
      this.checkIsMutable$kotlin_stdlib();

      while(true) {
         int hash = this.hash(key);
         int tentativeMaxProbeDistance = RangesKt.coerceAtMost(this.maxProbeDistance * 2, this.getHashSize() / 2);
         int probeDistance = 0;

         while(true) {
            int index = this.hashArray[hash];
            if (index <= 0) {
               if (this.length < this.getCapacity$kotlin_stdlib()) {
                  int putIndex = this.length++;
                  this.keysArray[putIndex] = key;
                  this.presenceArray[putIndex] = hash;
                  this.hashArray[hash] = putIndex + 1;
                  putIndex = this.size();
                  this.size = putIndex + 1;
                  this.registerModification();
                  if (probeDistance > this.maxProbeDistance) {
                     this.maxProbeDistance = probeDistance;
                  }

                  return putIndex;
               }

               this.ensureExtraCapacity(1);
               break;
            }

            if (Intrinsics.areEqual(this.keysArray[index - 1], key)) {
               return -index;
            }

            ++probeDistance;
            if (probeDistance > tentativeMaxProbeDistance) {
               this.rehash(this.getHashSize() * 2);
               break;
            }

            if (hash-- == 0) {
               hash = this.getHashSize() - 1;
            }
         }
      }
   }

   public final boolean removeKey$kotlin_stdlib(Object key) {
      this.checkIsMutable$kotlin_stdlib();
      int index = this.findKey(key);
      if (index < 0) {
         return false;
      } else {
         this.removeEntryAt(index);
         return true;
      }
   }

   private final void removeEntryAt(int index) {
      ListBuilderKt.resetAt(this.keysArray, index);
      Object[] var10000 = this.valuesArray;
      if (var10000 != null) {
         ListBuilderKt.resetAt(var10000, index);
      }

      this.removeHashAt(this.presenceArray[index]);
      this.presenceArray[index] = -1;
      int var2 = this.size();
      this.size = var2 + -1;
      this.registerModification();
   }

   private final void removeHashAt(int removedHash) {
      int hash = removedHash;
      int hole = removedHash;
      int probeDistance = 0;
      int patchAttemptsLeft = RangesKt.coerceAtMost(this.maxProbeDistance * 2, this.getHashSize() / 2);

      do {
         if (hash-- == 0) {
            hash = this.getHashSize() - 1;
         }

         ++probeDistance;
         if (probeDistance > this.maxProbeDistance) {
            this.hashArray[hole] = 0;
            return;
         }

         int index = this.hashArray[hash];
         if (index == 0) {
            this.hashArray[hole] = 0;
            return;
         }

         if (index < 0) {
            this.hashArray[hole] = -1;
            hole = hash;
            probeDistance = 0;
         } else {
            int otherHash = this.hash(this.keysArray[index - 1]);
            if ((otherHash - hash & this.getHashSize() - 1) >= probeDistance) {
               this.hashArray[hole] = index;
               this.presenceArray[index - 1] = hole;
               hole = hash;
               probeDistance = 0;
            }
         }

         --patchAttemptsLeft;
      } while(patchAttemptsLeft >= 0);

      this.hashArray[hole] = -1;
   }

   public final boolean containsEntry$kotlin_stdlib(@NotNull Map.Entry entry) {
      Intrinsics.checkNotNullParameter(entry, "entry");
      int index = this.findKey(entry.getKey());
      if (index < 0) {
         return false;
      } else {
         Object[] var10000 = this.valuesArray;
         Intrinsics.checkNotNull(var10000);
         return Intrinsics.areEqual(var10000[index], entry.getValue());
      }
   }

   private final boolean contentEquals(Map other) {
      return this.size() == other.size() && this.containsAllEntries$kotlin_stdlib((Collection)other.entrySet());
   }

   public final boolean containsAllEntries$kotlin_stdlib(@NotNull Collection m) {
      Intrinsics.checkNotNullParameter(m, "m");
      Iterator it = m.iterator();

      while(true) {
         if (it.hasNext()) {
            Object entry = it.next();

            try {
               if (entry != null && this.containsEntry$kotlin_stdlib((Map.Entry)entry)) {
                  continue;
               }

               return false;
            } catch (ClassCastException var5) {
               return false;
            }
         }

         return true;
      }
   }

   private final boolean putEntry(Map.Entry entry) {
      int index = this.addKey$kotlin_stdlib(entry.getKey());
      Object[] valuesArray = this.allocateValuesArray();
      if (index >= 0) {
         valuesArray[index] = entry.getValue();
         return true;
      } else {
         Object oldValue = valuesArray[-index - 1];
         if (!Intrinsics.areEqual(entry.getValue(), oldValue)) {
            valuesArray[-index - 1] = entry.getValue();
            return true;
         } else {
            return false;
         }
      }
   }

   private final boolean putAllEntries(Collection from) {
      if (from.isEmpty()) {
         return false;
      } else {
         this.ensureExtraCapacity(from.size());
         Iterator it = from.iterator();
         boolean updated = false;

         while(it.hasNext()) {
            if (this.putEntry((Map.Entry)it.next())) {
               updated = true;
            }
         }

         return updated;
      }
   }

   public final boolean removeEntry$kotlin_stdlib(@NotNull Map.Entry entry) {
      Intrinsics.checkNotNullParameter(entry, "entry");
      this.checkIsMutable$kotlin_stdlib();
      int index = this.findKey(entry.getKey());
      if (index < 0) {
         return false;
      } else {
         Object[] var10000 = this.valuesArray;
         Intrinsics.checkNotNull(var10000);
         if (!Intrinsics.areEqual(var10000[index], entry.getValue())) {
            return false;
         } else {
            this.removeEntryAt(index);
            return true;
         }
      }
   }

   public final boolean removeValue$kotlin_stdlib(Object element) {
      this.checkIsMutable$kotlin_stdlib();
      int index = this.findValue(element);
      if (index < 0) {
         return false;
      } else {
         this.removeEntryAt(index);
         return true;
      }
   }

   @NotNull
   public final KeysItr keysIterator$kotlin_stdlib() {
      return new KeysItr(this);
   }

   @NotNull
   public final ValuesItr valuesIterator$kotlin_stdlib() {
      return new ValuesItr(this);
   }

   @NotNull
   public final EntriesItr entriesIterator$kotlin_stdlib() {
      return new EntriesItr(this);
   }

   static {
      MapBuilder it = new MapBuilder(0);
      int var2 = 0;
      it.isReadOnly = true;
      Empty = it;
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\n\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\t\u0010\u0007R&\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\n8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0012\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0013\u0010\u0011R\u0014\u0010\u0014\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0014\u0010\u0011¨\u0006\u0015"},
      d2 = {"Lkotlin/collections/builders/MapBuilder$Companion;", "", "<init>", "()V", "", "capacity", "computeHashSize", "(I)I", "hashSize", "computeShift", "Lkotlin/collections/builders/MapBuilder;", "", "Empty", "Lkotlin/collections/builders/MapBuilder;", "getEmpty$kotlin_stdlib", "()Lkotlin/collections/builders/MapBuilder;", "INITIAL_CAPACITY", "I", "INITIAL_MAX_PROBE_DISTANCE", "MAGIC", "TOMBSTONE", "kotlin-stdlib"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final MapBuilder getEmpty$kotlin_stdlib() {
         return MapBuilder.Empty;
      }

      private final int computeHashSize(int capacity) {
         return Integer.highestOneBit(RangesKt.coerceAtLeast(capacity, 1) * 3);
      }

      private final int computeShift(int hashSize) {
         return Integer.numberOfLeadingZeros(hashSize) + 1;
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
      d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000e\b\u0010\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u00020\u0003B\u001b\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\u000b\u001a\u00020\bH\u0000¢\u0006\u0004\b\t\u0010\nJ\r\u0010\r\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\bH\u0000¢\u0006\u0004\b\u000f\u0010\nJ\r\u0010\u0011\u001a\u00020\b¢\u0006\u0004\b\u0011\u0010\nR\u0016\u0010\u0013\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\"\u0010\u0015\u001a\u00020\u00128\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0014\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\"\u0010\u001a\u001a\u00020\u00128\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0014\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R&\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f¨\u0006 "},
      d2 = {"Lkotlin/collections/builders/MapBuilder$Itr;", "K", "V", "", "Lkotlin/collections/builders/MapBuilder;", "map", "<init>", "(Lkotlin/collections/builders/MapBuilder;)V", "", "checkForComodification$kotlin_stdlib", "()V", "checkForComodification", "", "hasNext", "()Z", "initNext$kotlin_stdlib", "initNext", "remove", "", "expectedModCount", "I", "index", "getIndex$kotlin_stdlib", "()I", "setIndex$kotlin_stdlib", "(I)V", "lastIndex", "getLastIndex$kotlin_stdlib", "setLastIndex$kotlin_stdlib", "Lkotlin/collections/builders/MapBuilder;", "getMap$kotlin_stdlib", "()Lkotlin/collections/builders/MapBuilder;", "kotlin-stdlib"}
   )
   @SourceDebugExtension({"SMAP\nMapBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MapBuilder.kt\nkotlin/collections/builders/MapBuilder$Itr\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,728:1\n1#2:729\n*E\n"})
   public static class Itr {
      @NotNull
      private final MapBuilder map;
      private int index;
      private int lastIndex;
      private int expectedModCount;

      public Itr(@NotNull MapBuilder map) {
         Intrinsics.checkNotNullParameter(map, "map");
         super();
         this.map = map;
         this.lastIndex = -1;
         this.expectedModCount = this.map.modCount;
         this.initNext$kotlin_stdlib();
      }

      @NotNull
      public final MapBuilder getMap$kotlin_stdlib() {
         return this.map;
      }

      public final int getIndex$kotlin_stdlib() {
         return this.index;
      }

      public final void setIndex$kotlin_stdlib(int <set-?>) {
         this.index = <set-?>;
      }

      public final int getLastIndex$kotlin_stdlib() {
         return this.lastIndex;
      }

      public final void setLastIndex$kotlin_stdlib(int <set-?>) {
         this.lastIndex = <set-?>;
      }

      public final void initNext$kotlin_stdlib() {
         while(this.index < this.map.length && this.map.presenceArray[this.index] < 0) {
            int var1 = this.index++;
         }

      }

      public final boolean hasNext() {
         return this.index < this.map.length;
      }

      public final void remove() {
         this.checkForComodification$kotlin_stdlib();
         boolean var1 = this.lastIndex != -1;
         if (!var1) {
            int var2 = 0;
            String var3 = "Call next() before removing element from the iterator.";
            throw new IllegalStateException(var3.toString());
         } else {
            this.map.checkIsMutable$kotlin_stdlib();
            this.map.removeEntryAt(this.lastIndex);
            this.lastIndex = -1;
            this.expectedModCount = this.map.modCount;
         }
      }

      public final void checkForComodification$kotlin_stdlib() {
         if (this.map.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
         }
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010)\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00032\b\u0012\u0004\u0012\u00028\u00020\u0004B\u001b\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00028\u0002H\u0096\u0002¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"},
      d2 = {"Lkotlin/collections/builders/MapBuilder$KeysItr;", "K", "V", "Lkotlin/collections/builders/MapBuilder$Itr;", "", "Lkotlin/collections/builders/MapBuilder;", "map", "<init>", "(Lkotlin/collections/builders/MapBuilder;)V", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}
   )
   public static final class KeysItr extends Itr implements Iterator, KMutableIterator {
      public KeysItr(@NotNull MapBuilder map) {
         Intrinsics.checkNotNullParameter(map, "map");
         super(map);
      }

      public Object next() {
         this.checkForComodification$kotlin_stdlib();
         if (this.getIndex$kotlin_stdlib() >= this.getMap$kotlin_stdlib().length) {
            throw new NoSuchElementException();
         } else {
            int var1 = this.getIndex$kotlin_stdlib();
            this.setIndex$kotlin_stdlib(var1 + 1);
            this.setLastIndex$kotlin_stdlib(var1);
            Object result = this.getMap$kotlin_stdlib().keysArray[this.getLastIndex$kotlin_stdlib()];
            this.initNext$kotlin_stdlib();
            return result;
         }
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010)\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00032\b\u0012\u0004\u0012\u00028\u00030\u0004B\u001b\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00028\u0003H\u0096\u0002¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"},
      d2 = {"Lkotlin/collections/builders/MapBuilder$ValuesItr;", "K", "V", "Lkotlin/collections/builders/MapBuilder$Itr;", "", "Lkotlin/collections/builders/MapBuilder;", "map", "<init>", "(Lkotlin/collections/builders/MapBuilder;)V", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}
   )
   public static final class ValuesItr extends Itr implements Iterator, KMutableIterator {
      public ValuesItr(@NotNull MapBuilder map) {
         Intrinsics.checkNotNullParameter(map, "map");
         super(map);
      }

      public Object next() {
         this.checkForComodification$kotlin_stdlib();
         if (this.getIndex$kotlin_stdlib() >= this.getMap$kotlin_stdlib().length) {
            throw new NoSuchElementException();
         } else {
            int var1 = this.getIndex$kotlin_stdlib();
            this.setIndex$kotlin_stdlib(var1 + 1);
            this.setLastIndex$kotlin_stdlib(var1);
            Object[] var10000 = this.getMap$kotlin_stdlib().valuesArray;
            Intrinsics.checkNotNull(var10000);
            Object result = var10000[this.getLastIndex$kotlin_stdlib()];
            this.initNext$kotlin_stdlib();
            return result;
         }
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010)\n\u0002\u0010'\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00032\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00050\u0004B\u001b\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0006¢\u0006\u0004\b\b\u0010\tJ\u001c\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\nH\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\u0011\u001a\u00020\u00102\n\u0010\u000f\u001a\u00060\rj\u0002`\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0016\u001a\u00020\u0013H\u0000¢\u0006\u0004\b\u0014\u0010\u0015¨\u0006\u0017"},
      d2 = {"Lkotlin/collections/builders/MapBuilder$EntriesItr;", "K", "V", "Lkotlin/collections/builders/MapBuilder$Itr;", "", "", "Lkotlin/collections/builders/MapBuilder;", "map", "<init>", "(Lkotlin/collections/builders/MapBuilder;)V", "Lkotlin/collections/builders/MapBuilder$EntryRef;", "next", "()Lkotlin/collections/builders/MapBuilder$EntryRef;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "sb", "", "nextAppendString", "(Ljava/lang/StringBuilder;)V", "", "nextHashCode$kotlin_stdlib", "()I", "nextHashCode", "kotlin-stdlib"}
   )
   public static final class EntriesItr extends Itr implements Iterator, KMutableIterator {
      public EntriesItr(@NotNull MapBuilder map) {
         Intrinsics.checkNotNullParameter(map, "map");
         super(map);
      }

      @NotNull
      public EntryRef next() {
         this.checkForComodification$kotlin_stdlib();
         if (this.getIndex$kotlin_stdlib() >= this.getMap$kotlin_stdlib().length) {
            throw new NoSuchElementException();
         } else {
            int var1 = this.getIndex$kotlin_stdlib();
            this.setIndex$kotlin_stdlib(var1 + 1);
            this.setLastIndex$kotlin_stdlib(var1);
            EntryRef result = new EntryRef(this.getMap$kotlin_stdlib(), this.getLastIndex$kotlin_stdlib());
            this.initNext$kotlin_stdlib();
            return result;
         }
      }

      public final int nextHashCode$kotlin_stdlib() {
         if (this.getIndex$kotlin_stdlib() >= this.getMap$kotlin_stdlib().length) {
            throw new NoSuchElementException();
         } else {
            int result = this.getIndex$kotlin_stdlib();
            this.setIndex$kotlin_stdlib(result + 1);
            this.setLastIndex$kotlin_stdlib(result);
            Object var10000 = this.getMap$kotlin_stdlib().keysArray[this.getLastIndex$kotlin_stdlib()];
            int var3 = var10000 != null ? var10000.hashCode() : 0;
            Object var10001 = this.getMap$kotlin_stdlib().valuesArray;
            Intrinsics.checkNotNull(var10001);
            var10001 = ((Object[])var10001)[this.getLastIndex$kotlin_stdlib()];
            result = var3 ^ (var10001 != null ? var10001.hashCode() : 0);
            this.initNext$kotlin_stdlib();
            return result;
         }
      }

      public final void nextAppendString(@NotNull StringBuilder sb) {
         Intrinsics.checkNotNullParameter(sb, "sb");
         if (this.getIndex$kotlin_stdlib() >= this.getMap$kotlin_stdlib().length) {
            throw new NoSuchElementException();
         } else {
            int var2 = this.getIndex$kotlin_stdlib();
            this.setIndex$kotlin_stdlib(var2 + 1);
            this.setLastIndex$kotlin_stdlib(var2);
            Object key = this.getMap$kotlin_stdlib().keysArray[this.getLastIndex$kotlin_stdlib()];
            if (key == this.getMap$kotlin_stdlib()) {
               sb.append("(this Map)");
            } else {
               sb.append(key);
            }

            sb.append('=');
            Object[] var10000 = this.getMap$kotlin_stdlib().valuesArray;
            Intrinsics.checkNotNull(var10000);
            Object value = var10000[this.getLastIndex$kotlin_stdlib()];
            if (value == this.getMap$kotlin_stdlib()) {
               sb.append("(this Map)");
            } else {
               sb.append(value);
            }

            this.initNext$kotlin_stdlib();
         }
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010'\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\n\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0003B#\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001a\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0096\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00028\u00032\u0006\u0010\u0011\u001a\u00028\u0003H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0017R\u0014\u0010\u001a\u001a\u00028\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R \u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001bR\u0014\u0010\u001d\u001a\u00028\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0019¨\u0006\u001e"},
      d2 = {"Lkotlin/collections/builders/MapBuilder$EntryRef;", "K", "V", "", "Lkotlin/collections/builders/MapBuilder;", "map", "", "index", "<init>", "(Lkotlin/collections/builders/MapBuilder;I)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "newValue", "setValue", "(Ljava/lang/Object;)Ljava/lang/Object;", "", "toString", "()Ljava/lang/String;", "I", "getKey", "()Ljava/lang/Object;", "key", "Lkotlin/collections/builders/MapBuilder;", "getValue", "value", "kotlin-stdlib"}
   )
   public static final class EntryRef implements Map.Entry, KMutableMap.Entry {
      @NotNull
      private final MapBuilder map;
      private final int index;

      public EntryRef(@NotNull MapBuilder map, int index) {
         Intrinsics.checkNotNullParameter(map, "map");
         super();
         this.map = map;
         this.index = index;
      }

      public Object getKey() {
         return this.map.keysArray[this.index];
      }

      public Object getValue() {
         Object[] var10000 = this.map.valuesArray;
         Intrinsics.checkNotNull(var10000);
         return var10000[this.index];
      }

      public Object setValue(Object newValue) {
         this.map.checkIsMutable$kotlin_stdlib();
         Object[] valuesArray = this.map.allocateValuesArray();
         Object oldValue = valuesArray[this.index];
         valuesArray[this.index] = newValue;
         return oldValue;
      }

      public boolean equals(@Nullable Object other) {
         return other instanceof Map.Entry && Intrinsics.areEqual(((Map.Entry)other).getKey(), this.getKey()) && Intrinsics.areEqual(((Map.Entry)other).getValue(), this.getValue());
      }

      public int hashCode() {
         Object var10000 = this.getKey();
         int var1 = var10000 != null ? var10000.hashCode() : 0;
         Object var10001 = this.getValue();
         return var1 ^ (var10001 != null ? var10001.hashCode() : 0);
      }

      @NotNull
      public String toString() {
         return "" + this.getKey() + '=' + this.getValue();
      }
   }
}
