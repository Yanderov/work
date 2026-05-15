package kotlin.jvm.internal;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0018\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0002¢\u0006\u0004\b\f\u0010\rJ\u0013\u0010\u000e\u001a\u00020\u0003*\u00020\u0002H\u0014¢\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011¨\u0006\u0012"},
   d2 = {"Lkotlin/jvm/internal/BooleanSpreadBuilder;", "Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "", "", "size", "<init>", "(I)V", "", "value", "", "add", "(Z)V", "toArray", "()[Z", "getSize", "([Z)I", "values", "[Z", "kotlin-stdlib"}
)
public final class BooleanSpreadBuilder extends PrimitiveSpreadBuilder {
   @NotNull
   private final boolean[] values;

   public BooleanSpreadBuilder(int size) {
      super(size);
      this.values = new boolean[size];
   }

   protected int getSize(@NotNull boolean[] $this$getSize) {
      Intrinsics.checkNotNullParameter($this$getSize, "<this>");
      return $this$getSize.length;
   }

   public final void add(boolean value) {
      boolean[] var10000 = this.values;
      int var2 = this.getPosition();
      this.setPosition(var2 + 1);
      var10000[var2] = value;
   }

   @NotNull
   public final boolean[] toArray() {
      return (boolean[])this.toArray(this.values, new boolean[this.size()]);
   }
}
