package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0010\f\n\u0002\b\u0007\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u0002H\u0086\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0002H&¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"},
   d2 = {"Lkotlin/collections/CharIterator;", "", "", "<init>", "()V", "next", "()Ljava/lang/Character;", "nextChar", "()C", "kotlin-stdlib"}
)
public abstract class CharIterator implements Iterator, KMappedMarker {
   @NotNull
   public final Character next() {
      return this.nextChar();
   }

   public abstract char nextChar();

   public void remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }
}
