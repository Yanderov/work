package kotlin.experimental;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u0010\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0007\u001a\u001c\u0010\u0002\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u001c\u0010\u0002\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u0004H\u0087\f¢\u0006\u0004\b\u0002\u0010\u0005\u001a\u0014\u0010\u0006\u001a\u00020\u0000*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0014\u0010\u0006\u001a\u00020\u0004*\u00020\u0004H\u0087\b¢\u0006\u0004\b\u0006\u0010\b\u001a\u001c\u0010\t\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\b\t\u0010\u0003\u001a\u001c\u0010\t\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u0004H\u0087\f¢\u0006\u0004\b\t\u0010\u0005\u001a\u001c\u0010\n\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\b\n\u0010\u0003\u001a\u001c\u0010\n\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u0004H\u0087\f¢\u0006\u0004\b\n\u0010\u0005¨\u0006\u000b"},
   d2 = {"", "other", "and", "(BB)B", "", "(SS)S", "inv", "(B)B", "(S)S", "or", "xor", "kotlin-stdlib"}
)
public final class BitwiseOperationsKt {
   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final byte and(byte $this$and, byte other) {
      return (byte)($this$and & other);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final byte or(byte $this$or, byte other) {
      return (byte)($this$or | other);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final byte xor(byte $this$xor, byte other) {
      return (byte)($this$xor ^ other);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final byte inv(byte $this$inv) {
      return (byte)(~$this$inv);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final short and(short $this$and, short other) {
      return (short)($this$and & other);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final short or(short $this$or, short other) {
      return (short)($this$or | other);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final short xor(short $this$xor, short other) {
      return (short)($this$xor ^ other);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final short inv(short $this$inv) {
      return (short)(~$this$inv);
   }
}
