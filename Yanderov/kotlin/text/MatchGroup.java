package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ$\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0015\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u0017\u0010\u000bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\t¨\u0006\u001a"},
   d2 = {"Lkotlin/text/MatchGroup;", "", "", "value", "Lkotlin/ranges/IntRange;", "range", "<init>", "(Ljava/lang/String;Lkotlin/ranges/IntRange;)V", "component1", "()Ljava/lang/String;", "component2", "()Lkotlin/ranges/IntRange;", "copy", "(Ljava/lang/String;Lkotlin/ranges/IntRange;)Lkotlin/text/MatchGroup;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Lkotlin/ranges/IntRange;", "getRange", "Ljava/lang/String;", "getValue", "kotlin-stdlib"}
)
public final class MatchGroup {
   @NotNull
   private final String value;
   @NotNull
   private final IntRange range;

   public MatchGroup(@NotNull String value, @NotNull IntRange range) {
      Intrinsics.checkNotNullParameter(value, "value");
      Intrinsics.checkNotNullParameter(range, "range");
      super();
      this.value = value;
      this.range = range;
   }

   @NotNull
   public final String getValue() {
      return this.value;
   }

   @NotNull
   public final IntRange getRange() {
      return this.range;
   }

   @NotNull
   public final String component1() {
      return this.value;
   }

   @NotNull
   public final IntRange component2() {
      return this.range;
   }

   @NotNull
   public final MatchGroup copy(@NotNull String value, @NotNull IntRange range) {
      Intrinsics.checkNotNullParameter(value, "value");
      Intrinsics.checkNotNullParameter(range, "range");
      return new MatchGroup(value, range);
   }

   // $FF: synthetic method
   public static MatchGroup copy$default(MatchGroup var0, String var1, IntRange var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.value;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.range;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "MatchGroup(value=" + this.value + ", range=" + this.range + ')';
   }

   public int hashCode() {
      int result = this.value.hashCode();
      result = result * 31 + this.range.hashCode();
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof MatchGroup)) {
         return false;
      } else {
         MatchGroup var2 = (MatchGroup)other;
         if (!Intrinsics.areEqual((Object)this.value, (Object)var2.value)) {
            return false;
         } else {
            return Intrinsics.areEqual((Object)this.range, (Object)var2.range);
         }
      }
   }
}
