package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u001f\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\r\u001a\u00020\u00052\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0096\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00140\u0003¢\u0006\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0017\u001a\u0004\b\u0006\u0010\u0010R\u0014\u0010\u001b\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001f\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001c\u001a\u0004\b\u001d\u0010\u0016¨\u0006\u001e"},
   d2 = {"Lkotlin/collections/ArrayAsCollection;", "T", "", "", "values", "", "isVarargs", "<init>", "([Ljava/lang/Object;Z)V", "element", "contains", "(Ljava/lang/Object;)Z", "elements", "containsAll", "(Ljava/util/Collection;)Z", "isEmpty", "()Z", "", "iterator", "()Ljava/util/Iterator;", "", "toArray", "()[Ljava/lang/Object;", "Z", "", "getSize", "()I", "size", "[Ljava/lang/Object;", "getValues", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nCollections.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Collections.kt\nkotlin/collections/ArrayAsCollection\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,526:1\n1734#2,3:527\n*S KotlinDebug\n*F\n+ 1 Collections.kt\nkotlin/collections/ArrayAsCollection\n*L\n62#1:527,3\n*E\n"})
final class ArrayAsCollection implements Collection, KMappedMarker {
   @NotNull
   private final Object[] values;
   private final boolean isVarargs;

   public ArrayAsCollection(@NotNull Object[] values, boolean isVarargs) {
      Intrinsics.checkNotNullParameter(values, "values");
      super();
      this.values = values;
      this.isVarargs = isVarargs;
   }

   @NotNull
   public final Object[] getValues() {
      return this.values;
   }

   public final boolean isVarargs() {
      return this.isVarargs;
   }

   public int getSize() {
      return this.values.length;
   }

   public boolean isEmpty() {
      return this.values.length == 0;
   }

   public boolean contains(Object element) {
      return ArraysKt.contains(this.values, element);
   }

   public boolean containsAll(@NotNull Collection elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      Iterable $this$all$iv = (Iterable)elements;
      int $i$f$all = 0;
      boolean var10000;
      if (((Collection)$this$all$iv).isEmpty()) {
         var10000 = true;
      } else {
         Iterator var4 = $this$all$iv.iterator();

         while(true) {
            if (!var4.hasNext()) {
               var10000 = true;
               break;
            }

            Object element$iv = var4.next();
            int var7 = 0;
            if (!this.contains(element$iv)) {
               var10000 = false;
               break;
            }
         }
      }

      return var10000;
   }

   @NotNull
   public Iterator iterator() {
      return ArrayIteratorKt.iterator(this.values);
   }

   @NotNull
   public final Object[] toArray() {
      return CollectionsKt.copyToArrayOfAny(this.values, this.isVarargs);
   }

   public boolean add(Object element) {
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

   public Object[] toArray(Object[] array) {
      Intrinsics.checkNotNullParameter(array, "array");
      return CollectionToArray.toArray(this, array);
   }
}
