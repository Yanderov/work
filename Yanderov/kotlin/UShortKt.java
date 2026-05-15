package kotlin;

import kotlin.internal.InlineOnly;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000 \n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\u001a\u0014\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0014\u0010\u0002\u001a\u00020\u0001*\u00020\u0004H\u0087\b¢\u0006\u0004\b\u0002\u0010\u0005\u001a\u0014\u0010\u0002\u001a\u00020\u0001*\u00020\u0006H\u0087\b¢\u0006\u0004\b\u0002\u0010\u0007\u001a\u0014\u0010\u0002\u001a\u00020\u0001*\u00020\bH\u0087\b¢\u0006\u0004\b\u0002\u0010\t¨\u0006\n"},
   d2 = {"", "Lkotlin/UShort;", "toUShort", "(B)S", "", "(I)S", "", "(J)S", "", "(S)S", "kotlin-stdlib"}
)
public final class UShortKt {
   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @InlineOnly
   private static final short toUShort(byte $this$toUShort) {
      return UShort.constructor-impl((short)$this$toUShort);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @InlineOnly
   private static final short toUShort(short $this$toUShort) {
      return UShort.constructor-impl($this$toUShort);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @InlineOnly
   private static final short toUShort(int $this$toUShort) {
      return UShort.constructor-impl((short)$this$toUShort);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @InlineOnly
   private static final short toUShort(long $this$toUShort) {
      return UShort.constructor-impl((short)((int)$this$toUShort));
   }
}
