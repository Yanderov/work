package kotlin.collections.builders;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010'\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010&\n\u0002\b\u0003\n\u0002\u0010)\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022 \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003B\u001d\b\u0000\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ#\u0010\u000b\u001a\u00020\n2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004H\u0016¢\u0006\u0004\b\u000b\u0010\fJ)\u0010\u000f\u001a\u00020\n2\u0018\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J)\u0010\u0014\u001a\u00020\n2\u0018\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040\rH\u0016¢\u0006\u0004\b\u0014\u0010\u0010J#\u0010\u0016\u001a\u00020\n2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\fJ\u000f\u0010\u0017\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\"\u0010\u001a\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040\u0019H\u0096\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ#\u0010\u001c\u001a\u00020\n2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004H\u0016¢\u0006\u0004\b\u001c\u0010\fJ)\u0010\u001d\u001a\u00020\n2\u0018\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040\rH\u0016¢\u0006\u0004\b\u001d\u0010\u0010J)\u0010\u001e\u001a\u00020\n2\u0018\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040\rH\u0016¢\u0006\u0004\b\u001e\u0010\u0010R#\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001f\u001a\u0004\b \u0010!R\u0014\u0010%\u001a\u00020\"8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$¨\u0006&"},
   d2 = {"Lkotlin/collections/builders/MapBuilderEntries;", "K", "V", "Lkotlin/collections/builders/AbstractMapBuilderEntrySet;", "", "Lkotlin/collections/builders/MapBuilder;", "backing", "<init>", "(Lkotlin/collections/builders/MapBuilder;)V", "element", "", "add", "(Ljava/util/Map$Entry;)Z", "", "elements", "addAll", "(Ljava/util/Collection;)Z", "", "clear", "()V", "containsAll", "", "containsEntry", "isEmpty", "()Z", "", "iterator", "()Ljava/util/Iterator;", "remove", "removeAll", "retainAll", "Lkotlin/collections/builders/MapBuilder;", "getBacking", "()Lkotlin/collections/builders/MapBuilder;", "", "getSize", "()I", "size", "kotlin-stdlib"}
)
public final class MapBuilderEntries extends AbstractMapBuilderEntrySet {
   @NotNull
   private final MapBuilder backing;

   public MapBuilderEntries(@NotNull MapBuilder backing) {
      Intrinsics.checkNotNullParameter(backing, "backing");
      super();
      this.backing = backing;
   }

   @NotNull
   public final MapBuilder getBacking() {
      return this.backing;
   }

   public int getSize() {
      return this.backing.size();
   }

   public boolean isEmpty() {
      return this.backing.isEmpty();
   }

   public boolean containsEntry(@NotNull Map.Entry element) {
      Intrinsics.checkNotNullParameter(element, "element");
      return this.backing.containsEntry$kotlin_stdlib(element);
   }

   public void clear() {
      this.backing.clear();
   }

   public boolean add(@NotNull Map.Entry element) {
      Intrinsics.checkNotNullParameter(element, "element");
      throw new UnsupportedOperationException();
   }

   public boolean addAll(@NotNull Collection elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      throw new UnsupportedOperationException();
   }

   public boolean remove(@NotNull Map.Entry element) {
      Intrinsics.checkNotNullParameter(element, "element");
      return this.backing.removeEntry$kotlin_stdlib(element);
   }

   @NotNull
   public Iterator iterator() {
      return this.backing.entriesIterator$kotlin_stdlib();
   }

   public boolean containsAll(@NotNull Collection elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      return this.backing.containsAllEntries$kotlin_stdlib(elements);
   }

   public boolean removeAll(@NotNull Collection elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      this.backing.checkIsMutable$kotlin_stdlib();
      return super.removeAll(elements);
   }

   public boolean retainAll(@NotNull Collection elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      this.backing.checkIsMutable$kotlin_stdlib();
      return super.retainAll(elements);
   }
}
