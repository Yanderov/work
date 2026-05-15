package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b'\u0018\u0000 \u000e*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003:\u0001\u000eB\t\b\u0004¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0096\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\r¨\u0006\u000f"},
   d2 = {"Lkotlin/collections/AbstractSet;", "E", "Lkotlin/collections/AbstractCollection;", "", "<init>", "()V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Companion", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.1"
)
public abstract class AbstractSet extends AbstractCollection implements Set, KMappedMarker {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);

   protected AbstractSet() {
   }

   public boolean equals(@Nullable Object other) {
      if (other == this) {
         return true;
      } else {
         return !(other instanceof Set) ? false : Companion.setEquals$kotlin_stdlib(this, (Set)other);
      }
   }

   public int hashCode() {
      return Companion.unorderedHashCode$kotlin_stdlib(this);
   }

   public Iterator iterator() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0010\b\n\u0002\b\u0004\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\n\u001a\u00020\u00072\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00042\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0000¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\u000f\u001a\u00020\f2\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0000¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u0010"},
      d2 = {"Lkotlin/collections/AbstractSet$Companion;", "", "<init>", "()V", "", "c", "other", "", "setEquals$kotlin_stdlib", "(Ljava/util/Set;Ljava/util/Set;)Z", "setEquals", "", "", "unorderedHashCode$kotlin_stdlib", "(Ljava/util/Collection;)I", "unorderedHashCode", "kotlin-stdlib"}
   )
   public static final class Companion {
      private Companion() {
      }

      public final int unorderedHashCode$kotlin_stdlib(@NotNull Collection c) {
         Intrinsics.checkNotNullParameter(c, "c");
         int hashCode = 0;

         for(Object element : c) {
            hashCode += element != null ? element.hashCode() : 0;
         }

         return hashCode;
      }

      public final boolean setEquals$kotlin_stdlib(@NotNull Set c, @NotNull Set other) {
         Intrinsics.checkNotNullParameter(c, "c");
         Intrinsics.checkNotNullParameter(other, "other");
         return c.size() != other.size() ? false : ((Collection)c).containsAll((Collection)other);
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
