package kotlin.collections.builders;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.AbstractMutableSet;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableSet;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010)\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\b\b\u0000\u0018\u0000 +*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u00032\u00060\u0004j\u0002`\u0005:\u0001+B\t\b\u0016¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u0006\u0010\nB\u001b\b\u0000\u0012\u0010\u0010\f\u001a\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u000b¢\u0006\u0004\b\u0006\u0010\rJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0014\u001a\u00020\u000f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0013\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001a\u0010\u0007J\u0018\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\u001b\u0010\u0011J\u000f\u0010\u001c\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH\u0096\u0002¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010!\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00028\u0000H\u0016¢\u0006\u0004\b!\u0010\u0011J\u001d\u0010\"\u001a\u00020\u000f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012H\u0016¢\u0006\u0004\b\"\u0010\u0015J\u001d\u0010#\u001a\u00020\u000f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012H\u0016¢\u0006\u0004\b#\u0010\u0015J\u000f\u0010%\u001a\u00020$H\u0002¢\u0006\u0004\b%\u0010&R\u001e\u0010\f\u001a\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010'R\u0014\u0010*\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)¨\u0006,"},
   d2 = {"Lkotlin/collections/builders/SetBuilder;", "E", "", "Lkotlin/collections/AbstractMutableSet;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "<init>", "()V", "", "initialCapacity", "(I)V", "Lkotlin/collections/builders/MapBuilder;", "backing", "(Lkotlin/collections/builders/MapBuilder;)V", "element", "", "add", "(Ljava/lang/Object;)Z", "", "elements", "addAll", "(Ljava/util/Collection;)Z", "", "build", "()Ljava/util/Set;", "", "clear", "contains", "isEmpty", "()Z", "", "iterator", "()Ljava/util/Iterator;", "remove", "removeAll", "retainAll", "", "writeReplace", "()Ljava/lang/Object;", "Lkotlin/collections/builders/MapBuilder;", "getSize", "()I", "size", "Companion", "kotlin-stdlib"}
)
public final class SetBuilder extends AbstractMutableSet implements Set, Serializable, KMutableSet {
   @NotNull
   private static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private final MapBuilder backing;
   @NotNull
   private static final SetBuilder Empty;

   public SetBuilder(@NotNull MapBuilder backing) {
      Intrinsics.checkNotNullParameter(backing, "backing");
      super();
      this.backing = backing;
   }

   public SetBuilder() {
      this(new MapBuilder());
   }

   public SetBuilder(int initialCapacity) {
      this(new MapBuilder(initialCapacity));
   }

   @NotNull
   public final Set build() {
      this.backing.build();
      return this.size() > 0 ? (Set)this : (Set)Empty;
   }

   private final Object writeReplace() {
      if (this.backing.isReadOnly$kotlin_stdlib()) {
         return new SerializedCollection((Collection)this, 1);
      } else {
         throw new NotSerializableException("The set cannot be serialized while it is being built.");
      }
   }

   public int getSize() {
      return this.backing.size();
   }

   public boolean isEmpty() {
      return this.backing.isEmpty();
   }

   public boolean contains(Object element) {
      return this.backing.containsKey(element);
   }

   public void clear() {
      this.backing.clear();
   }

   public boolean add(Object element) {
      return this.backing.addKey$kotlin_stdlib(element) >= 0;
   }

   public boolean remove(Object element) {
      return this.backing.removeKey$kotlin_stdlib(element);
   }

   @NotNull
   public Iterator iterator() {
      return this.backing.keysIterator$kotlin_stdlib();
   }

   public boolean addAll(@NotNull Collection elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      this.backing.checkIsMutable$kotlin_stdlib();
      return super.addAll(elements);
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

   static {
      Empty = new SetBuilder(MapBuilder.Companion.getEmpty$kotlin_stdlib());
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007¨\u0006\b"},
      d2 = {"Lkotlin/collections/builders/SetBuilder$Companion;", "", "<init>", "()V", "Lkotlin/collections/builders/SetBuilder;", "", "Empty", "Lkotlin/collections/builders/SetBuilder;", "kotlin-stdlib"}
   )
   private static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
