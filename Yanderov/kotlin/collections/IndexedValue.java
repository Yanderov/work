package kotlin.collections;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00028\u0000HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ*\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00028\u0000HÆ\u0001¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u0012\u0010\tJ\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0017\u0010\tR\u0017\u0010\u0005\u001a\u00028\u00008\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u0019\u0010\u000b¨\u0006\u001a"},
   d2 = {"Lkotlin/collections/IndexedValue;", "T", "", "", "index", "value", "<init>", "(ILjava/lang/Object;)V", "component1", "()I", "component2", "()Ljava/lang/Object;", "copy", "(ILjava/lang/Object;)Lkotlin/collections/IndexedValue;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "I", "getIndex", "Ljava/lang/Object;", "getValue", "kotlin-stdlib"}
)
public final class IndexedValue {
   private final int index;
   private final Object value;

   public IndexedValue(int index, Object value) {
      this.index = index;
      this.value = value;
   }

   public final int getIndex() {
      return this.index;
   }

   public final Object getValue() {
      return this.value;
   }

   public final int component1() {
      return this.index;
   }

   public final Object component2() {
      return this.value;
   }

   @NotNull
   public final IndexedValue copy(int index, Object value) {
      return new IndexedValue(index, value);
   }

   // $FF: synthetic method
   public static IndexedValue copy$default(IndexedValue var0, int var1, Object var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.index;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.value;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "IndexedValue(index=" + this.index + ", value=" + this.value + ')';
   }

   public int hashCode() {
      int result = Integer.hashCode(this.index);
      result = result * 31 + (this.value == null ? 0 : this.value.hashCode());
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof IndexedValue)) {
         return false;
      } else {
         IndexedValue var2 = (IndexedValue)other;
         if (this.index != var2.index) {
            return false;
         } else {
            return Intrinsics.areEqual(this.value, var2.value);
         }
      }
   }
}
