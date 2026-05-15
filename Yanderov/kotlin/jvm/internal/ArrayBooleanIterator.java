package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.BooleanIterator;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0018\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\nR\u0016\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u000e"},
   d2 = {"Lkotlin/jvm/internal/ArrayBooleanIterator;", "Lkotlin/collections/BooleanIterator;", "", "array", "<init>", "([Z)V", "", "hasNext", "()Z", "nextBoolean", "[Z", "", "index", "I", "kotlin-stdlib"}
)
final class ArrayBooleanIterator extends BooleanIterator {
   @NotNull
   private final boolean[] array;
   private int index;

   public ArrayBooleanIterator(@NotNull boolean[] array) {
      Intrinsics.checkNotNullParameter(array, "array");
      super();
      this.array = array;
   }

   public boolean hasNext() {
      return this.index < this.array.length;
   }

   public boolean nextBoolean() {
      try {
         boolean[] var10000 = this.array;
         int var1 = this.index++;
         var1 = var10000[var1];
         return (boolean)var1;
      } catch (ArrayIndexOutOfBoundsException e) {
         --this.index;
         throw new NoSuchElementException(e.getMessage());
      }
   }
}
