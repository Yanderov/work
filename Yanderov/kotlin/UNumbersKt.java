package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b)\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u0005H\u0087\b¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\bH\u0087\b¢\u0006\u0004\b\t\u0010\n\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u000bH\u0087\b¢\u0006\u0004\b\f\u0010\r\u001a\u0014\u0010\u000f\u001a\u00020\u0001*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u000e\u0010\u0003\u001a\u0014\u0010\u000f\u001a\u00020\u0001*\u00020\u0005H\u0087\b¢\u0006\u0004\b\u0010\u0010\u0007\u001a\u0014\u0010\u000f\u001a\u00020\u0001*\u00020\bH\u0087\b¢\u0006\u0004\b\u0011\u0010\n\u001a\u0014\u0010\u000f\u001a\u00020\u0001*\u00020\u000bH\u0087\b¢\u0006\u0004\b\u0012\u0010\r\u001a\u0014\u0010\u0014\u001a\u00020\u0001*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0013\u0010\u0003\u001a\u0014\u0010\u0014\u001a\u00020\u0001*\u00020\u0005H\u0087\b¢\u0006\u0004\b\u0015\u0010\u0007\u001a\u0014\u0010\u0014\u001a\u00020\u0001*\u00020\bH\u0087\b¢\u0006\u0004\b\u0016\u0010\n\u001a\u0014\u0010\u0014\u001a\u00020\u0001*\u00020\u000bH\u0087\b¢\u0006\u0004\b\u0017\u0010\r\u001a\u001c\u0010\u001b\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u001c\u0010\u001b\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001c\u0010\u001b\u001a\u00020\b*\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u001c\u0010\u001b\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b \u0010!\u001a\u001c\u0010#\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b\"\u0010\u001a\u001a\u001c\u0010#\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b$\u0010\u001d\u001a\u001c\u0010#\u001a\u00020\b*\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b%\u0010\u001f\u001a\u001c\u0010#\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b&\u0010!\u001a\u0014\u0010)\u001a\u00020\u0000*\u00020\u0000H\u0087\b¢\u0006\u0004\b'\u0010(\u001a\u0014\u0010)\u001a\u00020\u0005*\u00020\u0005H\u0087\b¢\u0006\u0004\b*\u0010\u0007\u001a\u0014\u0010)\u001a\u00020\b*\u00020\bH\u0087\b¢\u0006\u0004\b+\u0010,\u001a\u0014\u0010)\u001a\u00020\u000b*\u00020\u000bH\u0087\b¢\u0006\u0004\b-\u0010.\u001a\u0014\u00100\u001a\u00020\u0000*\u00020\u0000H\u0087\b¢\u0006\u0004\b/\u0010(\u001a\u0014\u00100\u001a\u00020\u0005*\u00020\u0005H\u0087\b¢\u0006\u0004\b1\u0010\u0007\u001a\u0014\u00100\u001a\u00020\b*\u00020\bH\u0087\b¢\u0006\u0004\b2\u0010,\u001a\u0014\u00100\u001a\u00020\u000b*\u00020\u000bH\u0087\b¢\u0006\u0004\b3\u0010.¨\u00064"},
   d2 = {"Lkotlin/UByte;", "", "countLeadingZeroBits-7apg3OU", "(B)I", "countLeadingZeroBits", "Lkotlin/UInt;", "countLeadingZeroBits-WZ4Q5Ns", "(I)I", "Lkotlin/ULong;", "countLeadingZeroBits-VKZWuLQ", "(J)I", "Lkotlin/UShort;", "countLeadingZeroBits-xj2QHRw", "(S)I", "countOneBits-7apg3OU", "countOneBits", "countOneBits-WZ4Q5Ns", "countOneBits-VKZWuLQ", "countOneBits-xj2QHRw", "countTrailingZeroBits-7apg3OU", "countTrailingZeroBits", "countTrailingZeroBits-WZ4Q5Ns", "countTrailingZeroBits-VKZWuLQ", "countTrailingZeroBits-xj2QHRw", "bitCount", "rotateLeft-LxnNnR4", "(BI)B", "rotateLeft", "rotateLeft-V7xB4Y4", "(II)I", "rotateLeft-JSWoG40", "(JI)J", "rotateLeft-olVBNx4", "(SI)S", "rotateRight-LxnNnR4", "rotateRight", "rotateRight-V7xB4Y4", "rotateRight-JSWoG40", "rotateRight-olVBNx4", "takeHighestOneBit-7apg3OU", "(B)B", "takeHighestOneBit", "takeHighestOneBit-WZ4Q5Ns", "takeHighestOneBit-VKZWuLQ", "(J)J", "takeHighestOneBit-xj2QHRw", "(S)S", "takeLowestOneBit-7apg3OU", "takeLowestOneBit", "takeLowestOneBit-WZ4Q5Ns", "takeLowestOneBit-VKZWuLQ", "takeLowestOneBit-xj2QHRw", "kotlin-stdlib"}
)
@JvmName(
   name = "UNumbersKt"
)
public final class UNumbersKt {
   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final int countOneBits_WZ4Q5Ns/* $FF was: countOneBits-WZ4Q5Ns*/(int $this$countOneBits_u2dWZ4Q5Ns) {
      return Integer.bitCount($this$countOneBits_u2dWZ4Q5Ns);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final int countLeadingZeroBits_WZ4Q5Ns/* $FF was: countLeadingZeroBits-WZ4Q5Ns*/(int $this$countLeadingZeroBits_u2dWZ4Q5Ns) {
      return Integer.numberOfLeadingZeros($this$countLeadingZeroBits_u2dWZ4Q5Ns);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final int countTrailingZeroBits_WZ4Q5Ns/* $FF was: countTrailingZeroBits-WZ4Q5Ns*/(int $this$countTrailingZeroBits_u2dWZ4Q5Ns) {
      return Integer.numberOfTrailingZeros($this$countTrailingZeroBits_u2dWZ4Q5Ns);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final int takeHighestOneBit_WZ4Q5Ns/* $FF was: takeHighestOneBit-WZ4Q5Ns*/(int $this$takeHighestOneBit_u2dWZ4Q5Ns) {
      return UInt.constructor-impl(Integer.highestOneBit($this$takeHighestOneBit_u2dWZ4Q5Ns));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final int takeLowestOneBit_WZ4Q5Ns/* $FF was: takeLowestOneBit-WZ4Q5Ns*/(int $this$takeLowestOneBit_u2dWZ4Q5Ns) {
      return UInt.constructor-impl(Integer.lowestOneBit($this$takeLowestOneBit_u2dWZ4Q5Ns));
   }

   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class}
   )
   @InlineOnly
   private static final int rotateLeft_V7xB4Y4/* $FF was: rotateLeft-V7xB4Y4*/(int $this$rotateLeft_u2dV7xB4Y4, int bitCount) {
      return UInt.constructor-impl(Integer.rotateLeft($this$rotateLeft_u2dV7xB4Y4, bitCount));
   }

   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class}
   )
   @InlineOnly
   private static final int rotateRight_V7xB4Y4/* $FF was: rotateRight-V7xB4Y4*/(int $this$rotateRight_u2dV7xB4Y4, int bitCount) {
      return UInt.constructor-impl(Integer.rotateRight($this$rotateRight_u2dV7xB4Y4, bitCount));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final int countOneBits_VKZWuLQ/* $FF was: countOneBits-VKZWuLQ*/(long $this$countOneBits_u2dVKZWuLQ) {
      return Long.bitCount($this$countOneBits_u2dVKZWuLQ);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final int countLeadingZeroBits_VKZWuLQ/* $FF was: countLeadingZeroBits-VKZWuLQ*/(long $this$countLeadingZeroBits_u2dVKZWuLQ) {
      return Long.numberOfLeadingZeros($this$countLeadingZeroBits_u2dVKZWuLQ);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final int countTrailingZeroBits_VKZWuLQ/* $FF was: countTrailingZeroBits-VKZWuLQ*/(long $this$countTrailingZeroBits_u2dVKZWuLQ) {
      return Long.numberOfTrailingZeros($this$countTrailingZeroBits_u2dVKZWuLQ);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final long takeHighestOneBit_VKZWuLQ/* $FF was: takeHighestOneBit-VKZWuLQ*/(long $this$takeHighestOneBit_u2dVKZWuLQ) {
      return ULong.constructor-impl(Long.highestOneBit($this$takeHighestOneBit_u2dVKZWuLQ));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final long takeLowestOneBit_VKZWuLQ/* $FF was: takeLowestOneBit-VKZWuLQ*/(long $this$takeLowestOneBit_u2dVKZWuLQ) {
      return ULong.constructor-impl(Long.lowestOneBit($this$takeLowestOneBit_u2dVKZWuLQ));
   }

   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class}
   )
   @InlineOnly
   private static final long rotateLeft_JSWoG40/* $FF was: rotateLeft-JSWoG40*/(long $this$rotateLeft_u2dJSWoG40, int bitCount) {
      return ULong.constructor-impl(Long.rotateLeft($this$rotateLeft_u2dJSWoG40, bitCount));
   }

   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class}
   )
   @InlineOnly
   private static final long rotateRight_JSWoG40/* $FF was: rotateRight-JSWoG40*/(long $this$rotateRight_u2dJSWoG40, int bitCount) {
      return ULong.constructor-impl(Long.rotateRight($this$rotateRight_u2dJSWoG40, bitCount));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final int countOneBits_7apg3OU/* $FF was: countOneBits-7apg3OU*/(byte $this$countOneBits_u2d7apg3OU) {
      return Integer.bitCount(UInt.constructor-impl($this$countOneBits_u2d7apg3OU & 255));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final int countLeadingZeroBits_7apg3OU/* $FF was: countLeadingZeroBits-7apg3OU*/(byte $this$countLeadingZeroBits_u2d7apg3OU) {
      return Integer.numberOfLeadingZeros($this$countLeadingZeroBits_u2d7apg3OU & 255) - 24;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final int countTrailingZeroBits_7apg3OU/* $FF was: countTrailingZeroBits-7apg3OU*/(byte $this$countTrailingZeroBits_u2d7apg3OU) {
      return Integer.numberOfTrailingZeros($this$countTrailingZeroBits_u2d7apg3OU | 256);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final byte takeHighestOneBit_7apg3OU/* $FF was: takeHighestOneBit-7apg3OU*/(byte $this$takeHighestOneBit_u2d7apg3OU) {
      return UByte.constructor-impl((byte)Integer.highestOneBit($this$takeHighestOneBit_u2d7apg3OU & 255));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final byte takeLowestOneBit_7apg3OU/* $FF was: takeLowestOneBit-7apg3OU*/(byte $this$takeLowestOneBit_u2d7apg3OU) {
      return UByte.constructor-impl((byte)Integer.lowestOneBit($this$takeLowestOneBit_u2d7apg3OU & 255));
   }

   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class}
   )
   @InlineOnly
   private static final byte rotateLeft_LxnNnR4/* $FF was: rotateLeft-LxnNnR4*/(byte $this$rotateLeft_u2dLxnNnR4, int bitCount) {
      return UByte.constructor-impl(NumbersKt.rotateLeft($this$rotateLeft_u2dLxnNnR4, bitCount));
   }

   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class}
   )
   @InlineOnly
   private static final byte rotateRight_LxnNnR4/* $FF was: rotateRight-LxnNnR4*/(byte $this$rotateRight_u2dLxnNnR4, int bitCount) {
      return UByte.constructor-impl(NumbersKt.rotateRight($this$rotateRight_u2dLxnNnR4, bitCount));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final int countOneBits_xj2QHRw/* $FF was: countOneBits-xj2QHRw*/(short $this$countOneBits_u2dxj2QHRw) {
      return Integer.bitCount(UInt.constructor-impl($this$countOneBits_u2dxj2QHRw & '\uffff'));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final int countLeadingZeroBits_xj2QHRw/* $FF was: countLeadingZeroBits-xj2QHRw*/(short $this$countLeadingZeroBits_u2dxj2QHRw) {
      return Integer.numberOfLeadingZeros($this$countLeadingZeroBits_u2dxj2QHRw & '\uffff') - 16;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final int countTrailingZeroBits_xj2QHRw/* $FF was: countTrailingZeroBits-xj2QHRw*/(short $this$countTrailingZeroBits_u2dxj2QHRw) {
      return Integer.numberOfTrailingZeros($this$countTrailingZeroBits_u2dxj2QHRw | 65536);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final short takeHighestOneBit_xj2QHRw/* $FF was: takeHighestOneBit-xj2QHRw*/(short $this$takeHighestOneBit_u2dxj2QHRw) {
      return UShort.constructor-impl((short)Integer.highestOneBit($this$takeHighestOneBit_u2dxj2QHRw & '\uffff'));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final short takeLowestOneBit_xj2QHRw/* $FF was: takeLowestOneBit-xj2QHRw*/(short $this$takeLowestOneBit_u2dxj2QHRw) {
      return UShort.constructor-impl((short)Integer.lowestOneBit($this$takeLowestOneBit_u2dxj2QHRw & '\uffff'));
   }

   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class}
   )
   @InlineOnly
   private static final short rotateLeft_olVBNx4/* $FF was: rotateLeft-olVBNx4*/(short $this$rotateLeft_u2dolVBNx4, int bitCount) {
      return UShort.constructor-impl(NumbersKt.rotateLeft($this$rotateLeft_u2dolVBNx4, bitCount));
   }

   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class}
   )
   @InlineOnly
   private static final short rotateRight_olVBNx4/* $FF was: rotateRight-olVBNx4*/(short $this$rotateRight_u2dolVBNx4, int bitCount) {
      return UShort.constructor-impl(NumbersKt.rotateRight($this$rotateRight_u2dolVBNx4, bitCount));
   }
}
