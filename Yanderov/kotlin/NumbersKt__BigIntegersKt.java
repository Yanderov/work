package kotlin;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\u001a\u001c\u0010\u0002\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0014\u0010\u0004\u001a\u00020\u0000*\u00020\u0000H\u0087\n¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0006\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b\u0006\u0010\u0003\u001a\u0014\u0010\u0007\u001a\u00020\u0000*\u00020\u0000H\u0087\n¢\u0006\u0004\b\u0007\u0010\u0005\u001a\u0014\u0010\b\u001a\u00020\u0000*\u00020\u0000H\u0087\b¢\u0006\u0004\b\b\u0010\u0005\u001a\u001c\u0010\t\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b\t\u0010\u0003\u001a\u001c\u0010\n\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\b\n\u0010\u0003\u001a\u001c\u0010\u000b\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b\u000b\u0010\u0003\u001a\u001c\u0010\f\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b\f\u0010\u0003\u001a\u001c\u0010\u000f\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\rH\u0087\f¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u001c\u0010\u0011\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\rH\u0087\f¢\u0006\u0004\b\u0011\u0010\u0010\u001a\u001c\u0010\u0012\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b\u0012\u0010\u0003\u001a\u0014\u0010\u0014\u001a\u00020\u0013*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0014\u0010\u0015\u001a(\u0010\u0014\u001a\u00020\u0013*\u00020\u00002\b\b\u0002\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0087\b¢\u0006\u0004\b\u0014\u0010\u0019\u001a\u0014\u0010\u001a\u001a\u00020\u0000*\u00020\rH\u0087\b¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u0014\u0010\u001a\u001a\u00020\u0000*\u00020\u001cH\u0087\b¢\u0006\u0004\b\u001a\u0010\u001d\u001a\u0014\u0010\u001e\u001a\u00020\u0000*\u00020\u0000H\u0087\n¢\u0006\u0004\b\u001e\u0010\u0005\u001a\u001c\u0010\u001f\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\b\u001f\u0010\u0003¨\u0006 "},
   d2 = {"Ljava/math/BigInteger;", "other", "and", "(Ljava/math/BigInteger;Ljava/math/BigInteger;)Ljava/math/BigInteger;", "dec", "(Ljava/math/BigInteger;)Ljava/math/BigInteger;", "div", "inc", "inv", "minus", "or", "plus", "rem", "", "n", "shl", "(Ljava/math/BigInteger;I)Ljava/math/BigInteger;", "shr", "times", "Ljava/math/BigDecimal;", "toBigDecimal", "(Ljava/math/BigInteger;)Ljava/math/BigDecimal;", "scale", "Ljava/math/MathContext;", "mathContext", "(Ljava/math/BigInteger;ILjava/math/MathContext;)Ljava/math/BigDecimal;", "toBigInteger", "(I)Ljava/math/BigInteger;", "", "(J)Ljava/math/BigInteger;", "unaryMinus", "xor", "kotlin-stdlib"},
   xs = "kotlin/NumbersKt"
)
class NumbersKt__BigIntegersKt extends NumbersKt__BigDecimalsKt {
   @InlineOnly
   private static final BigInteger plus(BigInteger $this$plus, BigInteger other) {
      Intrinsics.checkNotNullParameter($this$plus, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      BigInteger var10000 = $this$plus.add(other);
      Intrinsics.checkNotNullExpressionValue(var10000, "add(...)");
      return var10000;
   }

   @InlineOnly
   private static final BigInteger minus(BigInteger $this$minus, BigInteger other) {
      Intrinsics.checkNotNullParameter($this$minus, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      BigInteger var10000 = $this$minus.subtract(other);
      Intrinsics.checkNotNullExpressionValue(var10000, "subtract(...)");
      return var10000;
   }

   @InlineOnly
   private static final BigInteger times(BigInteger $this$times, BigInteger other) {
      Intrinsics.checkNotNullParameter($this$times, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      BigInteger var10000 = $this$times.multiply(other);
      Intrinsics.checkNotNullExpressionValue(var10000, "multiply(...)");
      return var10000;
   }

   @InlineOnly
   private static final BigInteger div(BigInteger $this$div, BigInteger other) {
      Intrinsics.checkNotNullParameter($this$div, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      BigInteger var10000 = $this$div.divide(other);
      Intrinsics.checkNotNullExpressionValue(var10000, "divide(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final BigInteger rem(BigInteger $this$rem, BigInteger other) {
      Intrinsics.checkNotNullParameter($this$rem, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      BigInteger var10000 = $this$rem.remainder(other);
      Intrinsics.checkNotNullExpressionValue(var10000, "remainder(...)");
      return var10000;
   }

   @InlineOnly
   private static final BigInteger unaryMinus(BigInteger $this$unaryMinus) {
      Intrinsics.checkNotNullParameter($this$unaryMinus, "<this>");
      BigInteger var10000 = $this$unaryMinus.negate();
      Intrinsics.checkNotNullExpressionValue(var10000, "negate(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.2"
   )
   @InlineOnly
   private static final BigInteger inc(BigInteger $this$inc) {
      Intrinsics.checkNotNullParameter($this$inc, "<this>");
      BigInteger var10000 = $this$inc.add(BigInteger.ONE);
      Intrinsics.checkNotNullExpressionValue(var10000, "add(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.2"
   )
   @InlineOnly
   private static final BigInteger dec(BigInteger $this$dec) {
      Intrinsics.checkNotNullParameter($this$dec, "<this>");
      BigInteger var10000 = $this$dec.subtract(BigInteger.ONE);
      Intrinsics.checkNotNullExpressionValue(var10000, "subtract(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.2"
   )
   @InlineOnly
   private static final BigInteger inv(BigInteger $this$inv) {
      Intrinsics.checkNotNullParameter($this$inv, "<this>");
      BigInteger var10000 = $this$inv.not();
      Intrinsics.checkNotNullExpressionValue(var10000, "not(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.2"
   )
   @InlineOnly
   private static final BigInteger and(BigInteger $this$and, BigInteger other) {
      Intrinsics.checkNotNullParameter($this$and, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      BigInteger var10000 = $this$and.and(other);
      Intrinsics.checkNotNullExpressionValue(var10000, "and(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.2"
   )
   @InlineOnly
   private static final BigInteger or(BigInteger $this$or, BigInteger other) {
      Intrinsics.checkNotNullParameter($this$or, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      BigInteger var10000 = $this$or.or(other);
      Intrinsics.checkNotNullExpressionValue(var10000, "or(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.2"
   )
   @InlineOnly
   private static final BigInteger xor(BigInteger $this$xor, BigInteger other) {
      Intrinsics.checkNotNullParameter($this$xor, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      BigInteger var10000 = $this$xor.xor(other);
      Intrinsics.checkNotNullExpressionValue(var10000, "xor(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.2"
   )
   @InlineOnly
   private static final BigInteger shl(BigInteger $this$shl, int n) {
      Intrinsics.checkNotNullParameter($this$shl, "<this>");
      BigInteger var10000 = $this$shl.shiftLeft(n);
      Intrinsics.checkNotNullExpressionValue(var10000, "shiftLeft(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.2"
   )
   @InlineOnly
   private static final BigInteger shr(BigInteger $this$shr, int n) {
      Intrinsics.checkNotNullParameter($this$shr, "<this>");
      BigInteger var10000 = $this$shr.shiftRight(n);
      Intrinsics.checkNotNullExpressionValue(var10000, "shiftRight(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.2"
   )
   @InlineOnly
   private static final BigInteger toBigInteger(int $this$toBigInteger) {
      BigInteger var10000 = BigInteger.valueOf((long)$this$toBigInteger);
      Intrinsics.checkNotNullExpressionValue(var10000, "valueOf(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.2"
   )
   @InlineOnly
   private static final BigInteger toBigInteger(long $this$toBigInteger) {
      BigInteger var10000 = BigInteger.valueOf($this$toBigInteger);
      Intrinsics.checkNotNullExpressionValue(var10000, "valueOf(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.2"
   )
   @InlineOnly
   private static final BigDecimal toBigDecimal(BigInteger $this$toBigDecimal) {
      Intrinsics.checkNotNullParameter($this$toBigDecimal, "<this>");
      return new BigDecimal($this$toBigDecimal);
   }

   @SinceKotlin(
      version = "1.2"
   )
   @InlineOnly
   private static final BigDecimal toBigDecimal(BigInteger $this$toBigDecimal, int scale, MathContext mathContext) {
      Intrinsics.checkNotNullParameter($this$toBigDecimal, "<this>");
      Intrinsics.checkNotNullParameter(mathContext, "mathContext");
      return new BigDecimal($this$toBigDecimal, scale, mathContext);
   }

   // $FF: synthetic method
   static BigDecimal toBigDecimal$default(BigInteger $this$toBigDecimal_u24default, int scale, MathContext mathContext, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         scale = 0;
      }

      if ((var3 & 2) != 0) {
         MathContext var10000 = MathContext.UNLIMITED;
         Intrinsics.checkNotNullExpressionValue(var10000, "UNLIMITED");
         mathContext = var10000;
      }

      Intrinsics.checkNotNullParameter($this$toBigDecimal_u24default, "<this>");
      Intrinsics.checkNotNullParameter(mathContext, "mathContext");
      return new BigDecimal($this$toBigDecimal_u24default, scale, mathContext);
   }

   public NumbersKt__BigIntegersKt() {
   }
}
