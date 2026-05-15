package kotlin.ranges;

import kotlin.Deprecated;
import kotlin.ExperimentalStdlibApi;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.WasExperimental;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0007\u0018\u0000 !2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u00020\u00030\u0004:\u0001!B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0003H\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u0010\u001a\u00020\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0096\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u001d\u0010\u001e\u001a\u00020\u00038VX\u0097\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0006\u001a\u00020\u00038VX\u0096\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001bR\u0017\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b \u0010\u001b\u0082\u0002\u0004\n\u0002\b!¨\u0006\""},
   d2 = {"Lkotlin/ranges/ULongRange;", "Lkotlin/ranges/ULongProgression;", "Lkotlin/ranges/ClosedRange;", "Lkotlin/ULong;", "Lkotlin/ranges/OpenEndRange;", "start", "endInclusive", "<init>", "(JJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "value", "", "contains-VKZWuLQ", "(J)Z", "contains", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "isEmpty", "()Z", "", "toString", "()Ljava/lang/String;", "getEndExclusive-s-VKNKU", "()J", "getEndExclusive-s-VKNKU$annotations", "()V", "endExclusive", "getEndInclusive-s-VKNKU", "getStart-s-VKNKU", "Companion", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.5"
)
@WasExperimental(
   markerClass = {ExperimentalUnsignedTypes.class}
)
public final class ULongRange extends ULongProgression implements ClosedRange, OpenEndRange {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private static final ULongRange EMPTY = new ULongRange(-1L, 0L, (DefaultConstructorMarker)null);

   private ULongRange(long start, long endInclusive) {
      super(start, endInclusive, 1L, (DefaultConstructorMarker)null);
   }

   public long getStart_s_VKNKU/* $FF was: getStart-s-VKNKU*/() {
      return this.getFirst-s-VKNKU();
   }

   public long getEndInclusive_s_VKNKU/* $FF was: getEndInclusive-s-VKNKU*/() {
      return this.getLast-s-VKNKU();
   }

   /** @deprecated */
   public long getEndExclusive_s_VKNKU/* $FF was: getEndExclusive-s-VKNKU*/() {
      if (this.getLast-s-VKNKU() == -1L) {
         throw new IllegalStateException("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.".toString());
      } else {
         long var1 = this.getLast-s-VKNKU();
         byte var3 = 1;
         return ULong.constructor-impl(var1 + ULong.constructor-impl((long)var3 & 4294967295L));
      }
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Can throw an exception when it's impossible to represent the value with ULong type, for example, when the range includes MAX_VALUE. It's recommended to use 'endInclusive' property that doesn't throw."
   )
   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   public static void getEndExclusive_s_VKNKU$annotations/* $FF was: getEndExclusive-s-VKNKU$annotations*/() {
   }

   public boolean contains_VKZWuLQ/* $FF was: contains-VKZWuLQ*/(long value) {
      return Long.compareUnsigned(this.getFirst-s-VKNKU(), value) <= 0 && Long.compareUnsigned(value, this.getLast-s-VKNKU()) <= 0;
   }

   public boolean isEmpty() {
      return Long.compareUnsigned(this.getFirst-s-VKNKU(), this.getLast-s-VKNKU()) > 0;
   }

   public boolean equals(@Nullable Object other) {
      return other instanceof ULongRange && (this.isEmpty() && ((ULongRange)other).isEmpty() || this.getFirst-s-VKNKU() == ((ULongRange)other).getFirst-s-VKNKU() && this.getLast-s-VKNKU() == ((ULongRange)other).getLast-s-VKNKU());
   }

   public int hashCode() {
      return this.isEmpty() ? -1 : 31 * (int)ULong.constructor-impl(this.getFirst-s-VKNKU() ^ ULong.constructor-impl(this.getFirst-s-VKNKU() >>> 32)) + (int)ULong.constructor-impl(this.getLast-s-VKNKU() ^ ULong.constructor-impl(this.getLast-s-VKNKU() >>> 32));
   }

   @NotNull
   public String toString() {
      return ULong.toString-impl(this.getFirst-s-VKNKU()) + ".." + ULong.toString-impl(this.getLast-s-VKNKU());
   }

   // $FF: synthetic method
   public ULongRange(long start, long endInclusive, DefaultConstructorMarker $constructor_marker) {
      this(start, endInclusive);
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"},
      d2 = {"Lkotlin/ranges/ULongRange$Companion;", "", "<init>", "()V", "Lkotlin/ranges/ULongRange;", "EMPTY", "Lkotlin/ranges/ULongRange;", "getEMPTY", "()Lkotlin/ranges/ULongRange;", "kotlin-stdlib"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final ULongRange getEMPTY() {
         return ULongRange.EMPTY;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
