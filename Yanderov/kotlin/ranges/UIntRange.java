package kotlin.ranges;

import kotlin.Deprecated;
import kotlin.ExperimentalStdlibApi;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.WasExperimental;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\n\b\u0007\u0018\u0000  2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u00020\u00030\u0004:\u0001 B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0003H\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u0010\u001a\u00020\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0096\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u001d\u0010\u001d\u001a\u00020\u00038VX\u0097\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001a\u0010\u0014R\u0017\u0010\u0006\u001a\u00020\u00038VX\u0096\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0014R\u0017\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0014\u0082\u0002\u0004\n\u0002\b!¨\u0006!"},
   d2 = {"Lkotlin/ranges/UIntRange;", "Lkotlin/ranges/UIntProgression;", "Lkotlin/ranges/ClosedRange;", "Lkotlin/UInt;", "Lkotlin/ranges/OpenEndRange;", "start", "endInclusive", "<init>", "(IILkotlin/jvm/internal/DefaultConstructorMarker;)V", "value", "", "contains-WZ4Q5Ns", "(I)Z", "contains", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "isEmpty", "()Z", "", "toString", "()Ljava/lang/String;", "getEndExclusive-pVg5ArA", "getEndExclusive-pVg5ArA$annotations", "()V", "endExclusive", "getEndInclusive-pVg5ArA", "getStart-pVg5ArA", "Companion", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.5"
)
@WasExperimental(
   markerClass = {ExperimentalUnsignedTypes.class}
)
public final class UIntRange extends UIntProgression implements ClosedRange, OpenEndRange {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private static final UIntRange EMPTY = new UIntRange(-1, 0, (DefaultConstructorMarker)null);

   private UIntRange(int start, int endInclusive) {
      super(start, endInclusive, 1, (DefaultConstructorMarker)null);
   }

   public int getStart_pVg5ArA/* $FF was: getStart-pVg5ArA*/() {
      return this.getFirst-pVg5ArA();
   }

   public int getEndInclusive_pVg5ArA/* $FF was: getEndInclusive-pVg5ArA*/() {
      return this.getLast-pVg5ArA();
   }

   /** @deprecated */
   public int getEndExclusive_pVg5ArA/* $FF was: getEndExclusive-pVg5ArA*/() {
      if (this.getLast-pVg5ArA() == -1) {
         throw new IllegalStateException("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.".toString());
      } else {
         return UInt.constructor-impl(this.getLast-pVg5ArA() + 1);
      }
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Can throw an exception when it's impossible to represent the value with UInt type, for example, when the range includes MAX_VALUE. It's recommended to use 'endInclusive' property that doesn't throw."
   )
   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   public static void getEndExclusive_pVg5ArA$annotations/* $FF was: getEndExclusive-pVg5ArA$annotations*/() {
   }

   public boolean contains_WZ4Q5Ns/* $FF was: contains-WZ4Q5Ns*/(int value) {
      return Integer.compareUnsigned(this.getFirst-pVg5ArA(), value) <= 0 && Integer.compareUnsigned(value, this.getLast-pVg5ArA()) <= 0;
   }

   public boolean isEmpty() {
      return Integer.compareUnsigned(this.getFirst-pVg5ArA(), this.getLast-pVg5ArA()) > 0;
   }

   public boolean equals(@Nullable Object other) {
      return other instanceof UIntRange && (this.isEmpty() && ((UIntRange)other).isEmpty() || this.getFirst-pVg5ArA() == ((UIntRange)other).getFirst-pVg5ArA() && this.getLast-pVg5ArA() == ((UIntRange)other).getLast-pVg5ArA());
   }

   public int hashCode() {
      return this.isEmpty() ? -1 : 31 * this.getFirst-pVg5ArA() + this.getLast-pVg5ArA();
   }

   @NotNull
   public String toString() {
      return UInt.toString-impl(this.getFirst-pVg5ArA()) + ".." + UInt.toString-impl(this.getLast-pVg5ArA());
   }

   // $FF: synthetic method
   public UIntRange(int start, int endInclusive, DefaultConstructorMarker $constructor_marker) {
      this(start, endInclusive);
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"},
      d2 = {"Lkotlin/ranges/UIntRange$Companion;", "", "<init>", "()V", "Lkotlin/ranges/UIntRange;", "EMPTY", "Lkotlin/ranges/UIntRange;", "getEMPTY", "()Lkotlin/ranges/UIntRange;", "kotlin-stdlib"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final UIntRange getEMPTY() {
         return UIntRange.EMPTY;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
