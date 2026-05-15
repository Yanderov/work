package kotlin.collections.unsigned;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.UnsignedKt;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u001e\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u0019\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\u0007¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0019\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001*\u00020\u0006H\u0007¢\u0006\u0004\b\b\u0010\t\u001a\u0019\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0001*\u00020\nH\u0007¢\u0006\u0004\b\f\u0010\r\u001a\u0019\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0001*\u00020\u000eH\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001a/\u0010\u0018\u001a\u00020\u0013*\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00022\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u0013H\u0007¢\u0006\u0004\b\u0016\u0010\u0017\u001a/\u0010\u0018\u001a\u00020\u0013*\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u0013H\u0007¢\u0006\u0004\b\u0019\u0010\u001a\u001a/\u0010\u0018\u001a\u00020\u0013*\u00020\n2\u0006\u0010\u0012\u001a\u00020\u000b2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u0013H\u0007¢\u0006\u0004\b\u001b\u0010\u001c\u001a/\u0010\u0018\u001a\u00020\u0013*\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u0013H\u0007¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u001c\u0010\"\u001a\u00020\u0002*\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\b \u0010!\u001a\u001c\u0010\"\u001a\u00020\u0007*\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\b#\u0010$\u001a\u001c\u0010\"\u001a\u00020\u000b*\u00020\n2\u0006\u0010\u001f\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\b%\u0010&\u001a\u001c\u0010\"\u001a\u00020\u000f*\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\b'\u0010(\u001a\u0015\u0010+\u001a\u0004\u0018\u00010\u0002*\u00020\u0000H\u0007¢\u0006\u0004\b)\u0010*\u001a\u0015\u0010+\u001a\u0004\u0018\u00010\u0007*\u00020\u0006H\u0007¢\u0006\u0004\b,\u0010-\u001a\u0015\u0010+\u001a\u0004\u0018\u00010\u000b*\u00020\nH\u0007¢\u0006\u0004\b.\u0010/\u001a\u0015\u0010+\u001a\u0004\u0018\u00010\u000f*\u00020\u000eH\u0007¢\u0006\u0004\b0\u00101\u001a=\u00108\u001a\u0004\u0018\u00010\u0002\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u00028\u000002*\u00020\u00002\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00028\u000004H\u0087\bø\u0001\u0000¢\u0006\u0004\b6\u00107\u001a=\u00108\u001a\u0004\u0018\u00010\u0007\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u00028\u000002*\u00020\u00062\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00028\u000004H\u0087\bø\u0001\u0000¢\u0006\u0004\b9\u0010:\u001a=\u00108\u001a\u0004\u0018\u00010\u000b\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u00028\u000002*\u00020\n2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00028\u000004H\u0087\bø\u0001\u0000¢\u0006\u0004\b;\u0010<\u001a=\u00108\u001a\u0004\u0018\u00010\u000f\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u00028\u000002*\u00020\u000e2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00028\u000004H\u0087\bø\u0001\u0000¢\u0006\u0004\b=\u0010>\u001a1\u0010D\u001a\u0004\u0018\u00010\u0002*\u00020\u00002\u001a\u0010A\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00020?j\n\u0012\u0006\b\u0000\u0012\u00020\u0002`@H\u0007¢\u0006\u0004\bB\u0010C\u001a1\u0010D\u001a\u0004\u0018\u00010\u0007*\u00020\u00062\u001a\u0010A\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00070?j\n\u0012\u0006\b\u0000\u0012\u00020\u0007`@H\u0007¢\u0006\u0004\bE\u0010F\u001a1\u0010D\u001a\u0004\u0018\u00010\u000b*\u00020\n2\u001a\u0010A\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u000b0?j\n\u0012\u0006\b\u0000\u0012\u00020\u000b`@H\u0007¢\u0006\u0004\bG\u0010H\u001a1\u0010D\u001a\u0004\u0018\u00010\u000f*\u00020\u000e2\u001a\u0010A\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u000f0?j\n\u0012\u0006\b\u0000\u0012\u00020\u000f`@H\u0007¢\u0006\u0004\bI\u0010J\u001a\u0015\u0010L\u001a\u0004\u0018\u00010\u0002*\u00020\u0000H\u0007¢\u0006\u0004\bK\u0010*\u001a\u0015\u0010L\u001a\u0004\u0018\u00010\u0007*\u00020\u0006H\u0007¢\u0006\u0004\bM\u0010-\u001a\u0015\u0010L\u001a\u0004\u0018\u00010\u000b*\u00020\nH\u0007¢\u0006\u0004\bN\u0010/\u001a\u0015\u0010L\u001a\u0004\u0018\u00010\u000f*\u00020\u000eH\u0007¢\u0006\u0004\bO\u00101\u001a=\u0010Q\u001a\u0004\u0018\u00010\u0002\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u00028\u000002*\u00020\u00002\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00028\u000004H\u0087\bø\u0001\u0000¢\u0006\u0004\bP\u00107\u001a=\u0010Q\u001a\u0004\u0018\u00010\u0007\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u00028\u000002*\u00020\u00062\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00028\u000004H\u0087\bø\u0001\u0000¢\u0006\u0004\bR\u0010:\u001a=\u0010Q\u001a\u0004\u0018\u00010\u000b\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u00028\u000002*\u00020\n2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00028\u000004H\u0087\bø\u0001\u0000¢\u0006\u0004\bS\u0010<\u001a=\u0010Q\u001a\u0004\u0018\u00010\u000f\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u00028\u000002*\u00020\u000e2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00028\u000004H\u0087\bø\u0001\u0000¢\u0006\u0004\bT\u0010>\u001a1\u0010V\u001a\u0004\u0018\u00010\u0002*\u00020\u00002\u001a\u0010A\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00020?j\n\u0012\u0006\b\u0000\u0012\u00020\u0002`@H\u0007¢\u0006\u0004\bU\u0010C\u001a1\u0010V\u001a\u0004\u0018\u00010\u0007*\u00020\u00062\u001a\u0010A\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00070?j\n\u0012\u0006\b\u0000\u0012\u00020\u0007`@H\u0007¢\u0006\u0004\bW\u0010F\u001a1\u0010V\u001a\u0004\u0018\u00010\u000b*\u00020\n2\u001a\u0010A\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u000b0?j\n\u0012\u0006\b\u0000\u0012\u00020\u000b`@H\u0007¢\u0006\u0004\bX\u0010H\u001a1\u0010V\u001a\u0004\u0018\u00010\u000f*\u00020\u000e2\u001a\u0010A\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u000f0?j\n\u0012\u0006\b\u0000\u0012\u00020\u000f`@H\u0007¢\u0006\u0004\bY\u0010J\u001a+\u0010]\u001a\u00020Z*\u00020\u00002\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020Z04H\u0087\bø\u0001\u0000¢\u0006\u0004\b[\u0010\\\u001a+\u0010]\u001a\u00020^*\u00020\u00002\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020^04H\u0087\bø\u0001\u0000¢\u0006\u0004\b_\u0010`\u001a+\u0010]\u001a\u00020Z*\u00020\u00062\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020Z04H\u0087\bø\u0001\u0000¢\u0006\u0004\b[\u0010a\u001a+\u0010]\u001a\u00020^*\u00020\u00062\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020^04H\u0087\bø\u0001\u0000¢\u0006\u0004\b_\u0010b\u001a+\u0010]\u001a\u00020Z*\u00020\n2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020Z04H\u0087\bø\u0001\u0000¢\u0006\u0004\b[\u0010c\u001a+\u0010]\u001a\u00020^*\u00020\n2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020^04H\u0087\bø\u0001\u0000¢\u0006\u0004\b_\u0010d\u001a+\u0010]\u001a\u00020Z*\u00020\u000e2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020Z04H\u0087\bø\u0001\u0000¢\u0006\u0004\b[\u0010e\u001a+\u0010]\u001a\u00020^*\u00020\u000e2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020^04H\u0087\bø\u0001\u0000¢\u0006\u0004\b_\u0010f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006g"},
   d2 = {"Lkotlin/UByteArray;", "", "Lkotlin/UByte;", "asList-GBYM_sE", "([B)Ljava/util/List;", "asList", "Lkotlin/UIntArray;", "Lkotlin/UInt;", "asList--ajY-9A", "([I)Ljava/util/List;", "Lkotlin/ULongArray;", "Lkotlin/ULong;", "asList-QwZRm1k", "([J)Ljava/util/List;", "Lkotlin/UShortArray;", "Lkotlin/UShort;", "asList-rL5Bavg", "([S)Ljava/util/List;", "element", "", "fromIndex", "toIndex", "binarySearch-WpHrYlw", "([BBII)I", "binarySearch", "binarySearch-2fe2U9s", "([IIII)I", "binarySearch-K6DWlUc", "([JJII)I", "binarySearch-EtDCXyQ", "([SSII)I", "index", "elementAt-PpDY95g", "([BI)B", "elementAt", "elementAt-qFRl0hI", "([II)I", "elementAt-r7IrZao", "([JI)J", "elementAt-nggk6HY", "([SI)S", "max-GBYM_sE", "([B)Lkotlin/UByte;", "max", "max--ajY-9A", "([I)Lkotlin/UInt;", "max-QwZRm1k", "([J)Lkotlin/ULong;", "max-rL5Bavg", "([S)Lkotlin/UShort;", "", "R", "Lkotlin/Function1;", "selector", "maxBy-JOV_ifY", "([BLkotlin/jvm/functions/Function1;)Lkotlin/UByte;", "maxBy", "maxBy-jgv0xPQ", "([ILkotlin/jvm/functions/Function1;)Lkotlin/UInt;", "maxBy-MShoTSo", "([JLkotlin/jvm/functions/Function1;)Lkotlin/ULong;", "maxBy-xTcfx_M", "([SLkotlin/jvm/functions/Function1;)Lkotlin/UShort;", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "comparator", "maxWith-XMRcp5o", "([BLjava/util/Comparator;)Lkotlin/UByte;", "maxWith", "maxWith-YmdZ_VM", "([ILjava/util/Comparator;)Lkotlin/UInt;", "maxWith-zrEWJaI", "([JLjava/util/Comparator;)Lkotlin/ULong;", "maxWith-eOHTfZs", "([SLjava/util/Comparator;)Lkotlin/UShort;", "min-GBYM_sE", "min", "min--ajY-9A", "min-QwZRm1k", "min-rL5Bavg", "minBy-JOV_ifY", "minBy", "minBy-jgv0xPQ", "minBy-MShoTSo", "minBy-xTcfx_M", "minWith-XMRcp5o", "minWith", "minWith-YmdZ_VM", "minWith-zrEWJaI", "minWith-eOHTfZs", "Ljava/math/BigDecimal;", "sumOfBigDecimal", "([BLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "sumOf", "Ljava/math/BigInteger;", "sumOfBigInteger", "([BLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([ILkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([ILkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([JLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([JLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([SLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([SLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "kotlin-stdlib"},
   xs = "kotlin/collections/unsigned/UArraysKt",
   pn = ""
)
class UArraysKt___UArraysJvmKt {
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final int elementAt_qFRl0hI/* $FF was: elementAt-qFRl0hI*/(int[] $this$elementAt_u2dqFRl0hI, int index) {
      Intrinsics.checkNotNullParameter($this$elementAt_u2dqFRl0hI, "$this$elementAt");
      return UIntArray.get-pVg5ArA($this$elementAt_u2dqFRl0hI, index);
   }

   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final long elementAt_r7IrZao/* $FF was: elementAt-r7IrZao*/(long[] $this$elementAt_u2dr7IrZao, int index) {
      Intrinsics.checkNotNullParameter($this$elementAt_u2dr7IrZao, "$this$elementAt");
      return ULongArray.get-s-VKNKU($this$elementAt_u2dr7IrZao, index);
   }

   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final byte elementAt_PpDY95g/* $FF was: elementAt-PpDY95g*/(byte[] $this$elementAt_u2dPpDY95g, int index) {
      Intrinsics.checkNotNullParameter($this$elementAt_u2dPpDY95g, "$this$elementAt");
      return UByteArray.get-w2LRezQ($this$elementAt_u2dPpDY95g, index);
   }

   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final short elementAt_nggk6HY/* $FF was: elementAt-nggk6HY*/(short[] $this$elementAt_u2dnggk6HY, int index) {
      Intrinsics.checkNotNullParameter($this$elementAt_u2dnggk6HY, "$this$elementAt");
      return UShortArray.get-Mh2AYeg($this$elementAt_u2dnggk6HY, index);
   }

   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   @NotNull
   public static final List asList__ajY_9A/* $FF was: asList--ajY-9A*/(@NotNull final int[] $this$asList_u2d_u2dajY_u2d9A) {
      Intrinsics.checkNotNullParameter($this$asList_u2d_u2dajY_u2d9A, "$this$asList");
      return (List)(new RandomAccess($this$asList_u2d_u2dajY_u2d9A) {
         public int getSize() {
            return UIntArray.getSize-impl($this$asList_u2d_u2dajY_u2d9A);
         }

         public boolean isEmpty() {
            return UIntArray.isEmpty-impl($this$asList_u2d_u2dajY_u2d9A);
         }

         public boolean contains_WZ4Q5Ns/* $FF was: contains-WZ4Q5Ns*/(int element) {
            return UIntArray.contains-WZ4Q5Ns($this$asList_u2d_u2dajY_u2d9A, element);
         }

         public int get_pVg5ArA/* $FF was: get-pVg5ArA*/(int index) {
            return UIntArray.get-pVg5ArA($this$asList_u2d_u2dajY_u2d9A, index);
         }

         public int indexOf_WZ4Q5Ns/* $FF was: indexOf-WZ4Q5Ns*/(int element) {
            return ArraysKt.indexOf($this$asList_u2d_u2dajY_u2d9A, element);
         }

         public int lastIndexOf_WZ4Q5Ns/* $FF was: lastIndexOf-WZ4Q5Ns*/(int element) {
            return ArraysKt.lastIndexOf($this$asList_u2d_u2dajY_u2d9A, element);
         }
      });
   }

   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   @NotNull
   public static final List asList_QwZRm1k/* $FF was: asList-QwZRm1k*/(@NotNull final long[] $this$asList_u2dQwZRm1k) {
      Intrinsics.checkNotNullParameter($this$asList_u2dQwZRm1k, "$this$asList");
      return (List)(new RandomAccess($this$asList_u2dQwZRm1k) {
         public int getSize() {
            return ULongArray.getSize-impl($this$asList_u2dQwZRm1k);
         }

         public boolean isEmpty() {
            return ULongArray.isEmpty-impl($this$asList_u2dQwZRm1k);
         }

         public boolean contains_VKZWuLQ/* $FF was: contains-VKZWuLQ*/(long element) {
            return ULongArray.contains-VKZWuLQ($this$asList_u2dQwZRm1k, element);
         }

         public long get_s_VKNKU/* $FF was: get-s-VKNKU*/(int index) {
            return ULongArray.get-s-VKNKU($this$asList_u2dQwZRm1k, index);
         }

         public int indexOf_VKZWuLQ/* $FF was: indexOf-VKZWuLQ*/(long element) {
            return ArraysKt.indexOf($this$asList_u2dQwZRm1k, element);
         }

         public int lastIndexOf_VKZWuLQ/* $FF was: lastIndexOf-VKZWuLQ*/(long element) {
            return ArraysKt.lastIndexOf($this$asList_u2dQwZRm1k, element);
         }
      });
   }

   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   @NotNull
   public static final List asList_GBYM_sE/* $FF was: asList-GBYM_sE*/(@NotNull final byte[] $this$asList_u2dGBYM_sE) {
      Intrinsics.checkNotNullParameter($this$asList_u2dGBYM_sE, "$this$asList");
      return (List)(new RandomAccess($this$asList_u2dGBYM_sE) {
         public int getSize() {
            return UByteArray.getSize-impl($this$asList_u2dGBYM_sE);
         }

         public boolean isEmpty() {
            return UByteArray.isEmpty-impl($this$asList_u2dGBYM_sE);
         }

         public boolean contains_7apg3OU/* $FF was: contains-7apg3OU*/(byte element) {
            return UByteArray.contains-7apg3OU($this$asList_u2dGBYM_sE, element);
         }

         public byte get_w2LRezQ/* $FF was: get-w2LRezQ*/(int index) {
            return UByteArray.get-w2LRezQ($this$asList_u2dGBYM_sE, index);
         }

         public int indexOf_7apg3OU/* $FF was: indexOf-7apg3OU*/(byte element) {
            return ArraysKt.indexOf($this$asList_u2dGBYM_sE, element);
         }

         public int lastIndexOf_7apg3OU/* $FF was: lastIndexOf-7apg3OU*/(byte element) {
            return ArraysKt.lastIndexOf($this$asList_u2dGBYM_sE, element);
         }
      });
   }

   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   @NotNull
   public static final List asList_rL5Bavg/* $FF was: asList-rL5Bavg*/(@NotNull final short[] $this$asList_u2drL5Bavg) {
      Intrinsics.checkNotNullParameter($this$asList_u2drL5Bavg, "$this$asList");
      return (List)(new RandomAccess($this$asList_u2drL5Bavg) {
         public int getSize() {
            return UShortArray.getSize-impl($this$asList_u2drL5Bavg);
         }

         public boolean isEmpty() {
            return UShortArray.isEmpty-impl($this$asList_u2drL5Bavg);
         }

         public boolean contains_xj2QHRw/* $FF was: contains-xj2QHRw*/(short element) {
            return UShortArray.contains-xj2QHRw($this$asList_u2drL5Bavg, element);
         }

         public short get_Mh2AYeg/* $FF was: get-Mh2AYeg*/(int index) {
            return UShortArray.get-Mh2AYeg($this$asList_u2drL5Bavg, index);
         }

         public int indexOf_xj2QHRw/* $FF was: indexOf-xj2QHRw*/(short element) {
            return ArraysKt.indexOf($this$asList_u2drL5Bavg, element);
         }

         public int lastIndexOf_xj2QHRw/* $FF was: lastIndexOf-xj2QHRw*/(short element) {
            return ArraysKt.lastIndexOf($this$asList_u2drL5Bavg, element);
         }
      });
   }

   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public static final int binarySearch_2fe2U9s/* $FF was: binarySearch-2fe2U9s*/(@NotNull int[] $this$binarySearch_u2d2fe2U9s, int element, int fromIndex, int toIndex) {
      Intrinsics.checkNotNullParameter($this$binarySearch_u2d2fe2U9s, "$this$binarySearch");
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, UIntArray.getSize-impl($this$binarySearch_u2d2fe2U9s));
      int signedElement = element;
      int low = fromIndex;
      int high = toIndex - 1;

      while(low <= high) {
         int mid = low + high >>> 1;
         int midVal = $this$binarySearch_u2d2fe2U9s[mid];
         int cmp = UnsignedKt.uintCompare(midVal, signedElement);
         if (cmp < 0) {
            low = mid + 1;
         } else {
            if (cmp <= 0) {
               return mid;
            }

            high = mid - 1;
         }
      }

      return -(low + 1);
   }

   // $FF: synthetic method
   public static int binarySearch_2fe2U9s$default/* $FF was: binarySearch-2fe2U9s$default*/(int[] var0, int var1, int var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      if ((var4 & 4) != 0) {
         var3 = UIntArray.getSize-impl(var0);
      }

      return UArraysKt.binarySearch-2fe2U9s(var0, var1, var2, var3);
   }

   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public static final int binarySearch_K6DWlUc/* $FF was: binarySearch-K6DWlUc*/(@NotNull long[] $this$binarySearch_u2dK6DWlUc, long element, int fromIndex, int toIndex) {
      Intrinsics.checkNotNullParameter($this$binarySearch_u2dK6DWlUc, "$this$binarySearch");
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, ULongArray.getSize-impl($this$binarySearch_u2dK6DWlUc));
      long signedElement = element;
      int low = fromIndex;
      int high = toIndex - 1;

      while(low <= high) {
         int mid = low + high >>> 1;
         long midVal = $this$binarySearch_u2dK6DWlUc[mid];
         int cmp = UnsignedKt.ulongCompare(midVal, signedElement);
         if (cmp < 0) {
            low = mid + 1;
         } else {
            if (cmp <= 0) {
               return mid;
            }

            high = mid - 1;
         }
      }

      return -(low + 1);
   }

   // $FF: synthetic method
   public static int binarySearch_K6DWlUc$default/* $FF was: binarySearch-K6DWlUc$default*/(long[] var0, long var1, int var3, int var4, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         var3 = 0;
      }

      if ((var5 & 4) != 0) {
         var4 = ULongArray.getSize-impl(var0);
      }

      return UArraysKt.binarySearch-K6DWlUc(var0, var1, var3, var4);
   }

   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public static final int binarySearch_WpHrYlw/* $FF was: binarySearch-WpHrYlw*/(@NotNull byte[] $this$binarySearch_u2dWpHrYlw, byte element, int fromIndex, int toIndex) {
      Intrinsics.checkNotNullParameter($this$binarySearch_u2dWpHrYlw, "$this$binarySearch");
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, UByteArray.getSize-impl($this$binarySearch_u2dWpHrYlw));
      int signedElement = element & 255;
      int low = fromIndex;
      int high = toIndex - 1;

      while(low <= high) {
         int mid = low + high >>> 1;
         byte midVal = $this$binarySearch_u2dWpHrYlw[mid];
         int cmp = UnsignedKt.uintCompare(midVal, signedElement);
         if (cmp < 0) {
            low = mid + 1;
         } else {
            if (cmp <= 0) {
               return mid;
            }

            high = mid - 1;
         }
      }

      return -(low + 1);
   }

   // $FF: synthetic method
   public static int binarySearch_WpHrYlw$default/* $FF was: binarySearch-WpHrYlw$default*/(byte[] var0, byte var1, int var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      if ((var4 & 4) != 0) {
         var3 = UByteArray.getSize-impl(var0);
      }

      return UArraysKt.binarySearch-WpHrYlw(var0, var1, var2, var3);
   }

   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public static final int binarySearch_EtDCXyQ/* $FF was: binarySearch-EtDCXyQ*/(@NotNull short[] $this$binarySearch_u2dEtDCXyQ, short element, int fromIndex, int toIndex) {
      Intrinsics.checkNotNullParameter($this$binarySearch_u2dEtDCXyQ, "$this$binarySearch");
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, UShortArray.getSize-impl($this$binarySearch_u2dEtDCXyQ));
      int signedElement = element & '\uffff';
      int low = fromIndex;
      int high = toIndex - 1;

      while(low <= high) {
         int mid = low + high >>> 1;
         short midVal = $this$binarySearch_u2dEtDCXyQ[mid];
         int cmp = UnsignedKt.uintCompare(midVal, signedElement);
         if (cmp < 0) {
            low = mid + 1;
         } else {
            if (cmp <= 0) {
               return mid;
            }

            high = mid - 1;
         }
      }

      return -(low + 1);
   }

   // $FF: synthetic method
   public static int binarySearch_EtDCXyQ$default/* $FF was: binarySearch-EtDCXyQ$default*/(short[] var0, short var1, int var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      if ((var4 & 4) != 0) {
         var3 = UShortArray.getSize-impl(var0);
      }

      return UArraysKt.binarySearch-EtDCXyQ(var0, var1, var2, var3);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use maxOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxOrNull()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public static final UInt max__ajY_9A/* $FF was: max--ajY-9A*/(int[] $this$max_u2d_u2dajY_u2d9A) {
      Intrinsics.checkNotNullParameter($this$max_u2d_u2dajY_u2d9A, "$this$max");
      return UArraysKt.maxOrNull--ajY-9A($this$max_u2d_u2dajY_u2d9A);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use maxOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxOrNull()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public static final ULong max_QwZRm1k/* $FF was: max-QwZRm1k*/(long[] $this$max_u2dQwZRm1k) {
      Intrinsics.checkNotNullParameter($this$max_u2dQwZRm1k, "$this$max");
      return UArraysKt.maxOrNull-QwZRm1k($this$max_u2dQwZRm1k);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use maxOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxOrNull()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public static final UByte max_GBYM_sE/* $FF was: max-GBYM_sE*/(byte[] $this$max_u2dGBYM_sE) {
      Intrinsics.checkNotNullParameter($this$max_u2dGBYM_sE, "$this$max");
      return UArraysKt.maxOrNull-GBYM_sE($this$max_u2dGBYM_sE);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use maxOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxOrNull()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public static final UShort max_rL5Bavg/* $FF was: max-rL5Bavg*/(short[] $this$max_u2drL5Bavg) {
      Intrinsics.checkNotNullParameter($this$max_u2drL5Bavg, "$this$max");
      return UArraysKt.maxOrNull-rL5Bavg($this$max_u2drL5Bavg);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use maxByOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxByOrNull(selector)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final UInt maxBy_jgv0xPQ/* $FF was: maxBy-jgv0xPQ*/(int[] $this$maxBy_u2djgv0xPQ, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$maxBy_u2djgv0xPQ, "$this$maxBy");
      Intrinsics.checkNotNullParameter(selector, "selector");
      int[] var2 = $this$maxBy_u2djgv0xPQ;
      UInt var10000;
      if (UIntArray.isEmpty-impl($this$maxBy_u2djgv0xPQ)) {
         var10000 = null;
      } else {
         int var3 = UIntArray.get-pVg5ArA($this$maxBy_u2djgv0xPQ, 0);
         int var5 = ArraysKt.getLastIndex($this$maxBy_u2djgv0xPQ);
         if (var5 == 0) {
            var10000 = UInt.box-impl(var3);
         } else {
            Comparable var4 = (Comparable)selector.invoke(UInt.box-impl(var3));
            int var6 = 1;
            if (var6 <= var5) {
               while(true) {
                  int var7 = UIntArray.get-pVg5ArA(var2, var6);
                  Comparable var8 = (Comparable)selector.invoke(UInt.box-impl(var7));
                  if (var4.compareTo(var8) < 0) {
                     var3 = var7;
                     var4 = var8;
                  }

                  if (var6 == var5) {
                     break;
                  }

                  ++var6;
               }
            }

            var10000 = UInt.box-impl(var3);
         }
      }

      return var10000;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use maxByOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxByOrNull(selector)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final ULong maxBy_MShoTSo/* $FF was: maxBy-MShoTSo*/(long[] $this$maxBy_u2dMShoTSo, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$maxBy_u2dMShoTSo, "$this$maxBy");
      Intrinsics.checkNotNullParameter(selector, "selector");
      long[] var2 = $this$maxBy_u2dMShoTSo;
      ULong var10000;
      if (ULongArray.isEmpty-impl($this$maxBy_u2dMShoTSo)) {
         var10000 = null;
      } else {
         long var3 = ULongArray.get-s-VKNKU($this$maxBy_u2dMShoTSo, 0);
         int var6 = ArraysKt.getLastIndex($this$maxBy_u2dMShoTSo);
         if (var6 == 0) {
            var10000 = ULong.box-impl(var3);
         } else {
            Comparable var5 = (Comparable)selector.invoke(ULong.box-impl(var3));
            int var7 = 1;
            if (var7 <= var6) {
               while(true) {
                  long var8 = ULongArray.get-s-VKNKU(var2, var7);
                  Comparable var10 = (Comparable)selector.invoke(ULong.box-impl(var8));
                  if (var5.compareTo(var10) < 0) {
                     var3 = var8;
                     var5 = var10;
                  }

                  if (var7 == var6) {
                     break;
                  }

                  ++var7;
               }
            }

            var10000 = ULong.box-impl(var3);
         }
      }

      return var10000;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use maxByOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxByOrNull(selector)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final UByte maxBy_JOV_ifY/* $FF was: maxBy-JOV_ifY*/(byte[] $this$maxBy_u2dJOV_ifY, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$maxBy_u2dJOV_ifY, "$this$maxBy");
      Intrinsics.checkNotNullParameter(selector, "selector");
      byte[] var2 = $this$maxBy_u2dJOV_ifY;
      UByte var10000;
      if (UByteArray.isEmpty-impl($this$maxBy_u2dJOV_ifY)) {
         var10000 = null;
      } else {
         byte var3 = UByteArray.get-w2LRezQ($this$maxBy_u2dJOV_ifY, 0);
         int var5 = ArraysKt.getLastIndex($this$maxBy_u2dJOV_ifY);
         if (var5 == 0) {
            var10000 = UByte.box-impl(var3);
         } else {
            Comparable var4 = (Comparable)selector.invoke(UByte.box-impl(var3));
            int var6 = 1;
            if (var6 <= var5) {
               while(true) {
                  byte var7 = UByteArray.get-w2LRezQ(var2, var6);
                  Comparable var8 = (Comparable)selector.invoke(UByte.box-impl(var7));
                  if (var4.compareTo(var8) < 0) {
                     var3 = var7;
                     var4 = var8;
                  }

                  if (var6 == var5) {
                     break;
                  }

                  ++var6;
               }
            }

            var10000 = UByte.box-impl(var3);
         }
      }

      return var10000;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use maxByOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxByOrNull(selector)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final UShort maxBy_xTcfx_M/* $FF was: maxBy-xTcfx_M*/(short[] $this$maxBy_u2dxTcfx_M, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$maxBy_u2dxTcfx_M, "$this$maxBy");
      Intrinsics.checkNotNullParameter(selector, "selector");
      short[] var2 = $this$maxBy_u2dxTcfx_M;
      UShort var10000;
      if (UShortArray.isEmpty-impl($this$maxBy_u2dxTcfx_M)) {
         var10000 = null;
      } else {
         short var3 = UShortArray.get-Mh2AYeg($this$maxBy_u2dxTcfx_M, 0);
         int var5 = ArraysKt.getLastIndex($this$maxBy_u2dxTcfx_M);
         if (var5 == 0) {
            var10000 = UShort.box-impl(var3);
         } else {
            Comparable var4 = (Comparable)selector.invoke(UShort.box-impl(var3));
            int var6 = 1;
            if (var6 <= var5) {
               while(true) {
                  short var7 = UShortArray.get-Mh2AYeg(var2, var6);
                  Comparable var8 = (Comparable)selector.invoke(UShort.box-impl(var7));
                  if (var4.compareTo(var8) < 0) {
                     var3 = var7;
                     var4 = var8;
                  }

                  if (var6 == var5) {
                     break;
                  }

                  ++var6;
               }
            }

            var10000 = UShort.box-impl(var3);
         }
      }

      return var10000;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use maxWithOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxWithOrNull(comparator)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public static final UInt maxWith_YmdZ_VM/* $FF was: maxWith-YmdZ_VM*/(int[] $this$maxWith_u2dYmdZ_VM, Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$maxWith_u2dYmdZ_VM, "$this$maxWith");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return UArraysKt.maxWithOrNull-YmdZ_VM($this$maxWith_u2dYmdZ_VM, comparator);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use maxWithOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxWithOrNull(comparator)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public static final ULong maxWith_zrEWJaI/* $FF was: maxWith-zrEWJaI*/(long[] $this$maxWith_u2dzrEWJaI, Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$maxWith_u2dzrEWJaI, "$this$maxWith");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return UArraysKt.maxWithOrNull-zrEWJaI($this$maxWith_u2dzrEWJaI, comparator);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use maxWithOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxWithOrNull(comparator)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public static final UByte maxWith_XMRcp5o/* $FF was: maxWith-XMRcp5o*/(byte[] $this$maxWith_u2dXMRcp5o, Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$maxWith_u2dXMRcp5o, "$this$maxWith");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return UArraysKt.maxWithOrNull-XMRcp5o($this$maxWith_u2dXMRcp5o, comparator);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use maxWithOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxWithOrNull(comparator)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public static final UShort maxWith_eOHTfZs/* $FF was: maxWith-eOHTfZs*/(short[] $this$maxWith_u2deOHTfZs, Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$maxWith_u2deOHTfZs, "$this$maxWith");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return UArraysKt.maxWithOrNull-eOHTfZs($this$maxWith_u2deOHTfZs, comparator);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use minOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minOrNull()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public static final UInt min__ajY_9A/* $FF was: min--ajY-9A*/(int[] $this$min_u2d_u2dajY_u2d9A) {
      Intrinsics.checkNotNullParameter($this$min_u2d_u2dajY_u2d9A, "$this$min");
      return UArraysKt.minOrNull--ajY-9A($this$min_u2d_u2dajY_u2d9A);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use minOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minOrNull()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public static final ULong min_QwZRm1k/* $FF was: min-QwZRm1k*/(long[] $this$min_u2dQwZRm1k) {
      Intrinsics.checkNotNullParameter($this$min_u2dQwZRm1k, "$this$min");
      return UArraysKt.minOrNull-QwZRm1k($this$min_u2dQwZRm1k);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use minOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minOrNull()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public static final UByte min_GBYM_sE/* $FF was: min-GBYM_sE*/(byte[] $this$min_u2dGBYM_sE) {
      Intrinsics.checkNotNullParameter($this$min_u2dGBYM_sE, "$this$min");
      return UArraysKt.minOrNull-GBYM_sE($this$min_u2dGBYM_sE);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use minOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minOrNull()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public static final UShort min_rL5Bavg/* $FF was: min-rL5Bavg*/(short[] $this$min_u2drL5Bavg) {
      Intrinsics.checkNotNullParameter($this$min_u2drL5Bavg, "$this$min");
      return UArraysKt.minOrNull-rL5Bavg($this$min_u2drL5Bavg);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use minByOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minByOrNull(selector)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final UInt minBy_jgv0xPQ/* $FF was: minBy-jgv0xPQ*/(int[] $this$minBy_u2djgv0xPQ, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$minBy_u2djgv0xPQ, "$this$minBy");
      Intrinsics.checkNotNullParameter(selector, "selector");
      int[] var2 = $this$minBy_u2djgv0xPQ;
      UInt var10000;
      if (UIntArray.isEmpty-impl($this$minBy_u2djgv0xPQ)) {
         var10000 = null;
      } else {
         int var3 = UIntArray.get-pVg5ArA($this$minBy_u2djgv0xPQ, 0);
         int var5 = ArraysKt.getLastIndex($this$minBy_u2djgv0xPQ);
         if (var5 == 0) {
            var10000 = UInt.box-impl(var3);
         } else {
            Comparable var4 = (Comparable)selector.invoke(UInt.box-impl(var3));
            int var6 = 1;
            if (var6 <= var5) {
               while(true) {
                  int var7 = UIntArray.get-pVg5ArA(var2, var6);
                  Comparable var8 = (Comparable)selector.invoke(UInt.box-impl(var7));
                  if (var4.compareTo(var8) > 0) {
                     var3 = var7;
                     var4 = var8;
                  }

                  if (var6 == var5) {
                     break;
                  }

                  ++var6;
               }
            }

            var10000 = UInt.box-impl(var3);
         }
      }

      return var10000;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use minByOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minByOrNull(selector)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final ULong minBy_MShoTSo/* $FF was: minBy-MShoTSo*/(long[] $this$minBy_u2dMShoTSo, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$minBy_u2dMShoTSo, "$this$minBy");
      Intrinsics.checkNotNullParameter(selector, "selector");
      long[] var2 = $this$minBy_u2dMShoTSo;
      ULong var10000;
      if (ULongArray.isEmpty-impl($this$minBy_u2dMShoTSo)) {
         var10000 = null;
      } else {
         long var3 = ULongArray.get-s-VKNKU($this$minBy_u2dMShoTSo, 0);
         int var6 = ArraysKt.getLastIndex($this$minBy_u2dMShoTSo);
         if (var6 == 0) {
            var10000 = ULong.box-impl(var3);
         } else {
            Comparable var5 = (Comparable)selector.invoke(ULong.box-impl(var3));
            int var7 = 1;
            if (var7 <= var6) {
               while(true) {
                  long var8 = ULongArray.get-s-VKNKU(var2, var7);
                  Comparable var10 = (Comparable)selector.invoke(ULong.box-impl(var8));
                  if (var5.compareTo(var10) > 0) {
                     var3 = var8;
                     var5 = var10;
                  }

                  if (var7 == var6) {
                     break;
                  }

                  ++var7;
               }
            }

            var10000 = ULong.box-impl(var3);
         }
      }

      return var10000;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use minByOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minByOrNull(selector)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final UByte minBy_JOV_ifY/* $FF was: minBy-JOV_ifY*/(byte[] $this$minBy_u2dJOV_ifY, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$minBy_u2dJOV_ifY, "$this$minBy");
      Intrinsics.checkNotNullParameter(selector, "selector");
      byte[] var2 = $this$minBy_u2dJOV_ifY;
      UByte var10000;
      if (UByteArray.isEmpty-impl($this$minBy_u2dJOV_ifY)) {
         var10000 = null;
      } else {
         byte var3 = UByteArray.get-w2LRezQ($this$minBy_u2dJOV_ifY, 0);
         int var5 = ArraysKt.getLastIndex($this$minBy_u2dJOV_ifY);
         if (var5 == 0) {
            var10000 = UByte.box-impl(var3);
         } else {
            Comparable var4 = (Comparable)selector.invoke(UByte.box-impl(var3));
            int var6 = 1;
            if (var6 <= var5) {
               while(true) {
                  byte var7 = UByteArray.get-w2LRezQ(var2, var6);
                  Comparable var8 = (Comparable)selector.invoke(UByte.box-impl(var7));
                  if (var4.compareTo(var8) > 0) {
                     var3 = var7;
                     var4 = var8;
                  }

                  if (var6 == var5) {
                     break;
                  }

                  ++var6;
               }
            }

            var10000 = UByte.box-impl(var3);
         }
      }

      return var10000;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use minByOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minByOrNull(selector)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final UShort minBy_xTcfx_M/* $FF was: minBy-xTcfx_M*/(short[] $this$minBy_u2dxTcfx_M, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$minBy_u2dxTcfx_M, "$this$minBy");
      Intrinsics.checkNotNullParameter(selector, "selector");
      short[] var2 = $this$minBy_u2dxTcfx_M;
      UShort var10000;
      if (UShortArray.isEmpty-impl($this$minBy_u2dxTcfx_M)) {
         var10000 = null;
      } else {
         short var3 = UShortArray.get-Mh2AYeg($this$minBy_u2dxTcfx_M, 0);
         int var5 = ArraysKt.getLastIndex($this$minBy_u2dxTcfx_M);
         if (var5 == 0) {
            var10000 = UShort.box-impl(var3);
         } else {
            Comparable var4 = (Comparable)selector.invoke(UShort.box-impl(var3));
            int var6 = 1;
            if (var6 <= var5) {
               while(true) {
                  short var7 = UShortArray.get-Mh2AYeg(var2, var6);
                  Comparable var8 = (Comparable)selector.invoke(UShort.box-impl(var7));
                  if (var4.compareTo(var8) > 0) {
                     var3 = var7;
                     var4 = var8;
                  }

                  if (var6 == var5) {
                     break;
                  }

                  ++var6;
               }
            }

            var10000 = UShort.box-impl(var3);
         }
      }

      return var10000;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use minWithOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minWithOrNull(comparator)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public static final UInt minWith_YmdZ_VM/* $FF was: minWith-YmdZ_VM*/(int[] $this$minWith_u2dYmdZ_VM, Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$minWith_u2dYmdZ_VM, "$this$minWith");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return UArraysKt.minWithOrNull-YmdZ_VM($this$minWith_u2dYmdZ_VM, comparator);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use minWithOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minWithOrNull(comparator)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public static final ULong minWith_zrEWJaI/* $FF was: minWith-zrEWJaI*/(long[] $this$minWith_u2dzrEWJaI, Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$minWith_u2dzrEWJaI, "$this$minWith");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return UArraysKt.minWithOrNull-zrEWJaI($this$minWith_u2dzrEWJaI, comparator);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use minWithOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minWithOrNull(comparator)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public static final UByte minWith_XMRcp5o/* $FF was: minWith-XMRcp5o*/(byte[] $this$minWith_u2dXMRcp5o, Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$minWith_u2dXMRcp5o, "$this$minWith");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return UArraysKt.minWithOrNull-XMRcp5o($this$minWith_u2dXMRcp5o, comparator);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use minWithOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minWithOrNull(comparator)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public static final UShort minWith_eOHTfZs/* $FF was: minWith-eOHTfZs*/(short[] $this$minWith_u2deOHTfZs, Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$minWith_u2deOHTfZs, "$this$minWith");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return UArraysKt.minWithOrNull-eOHTfZs($this$minWith_u2deOHTfZs, comparator);
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @JvmName(
      name = "sumOfBigDecimal"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final BigDecimal sumOfBigDecimal(int[] $this$sumOf_u2djgv0xPQ, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$sumOf_u2djgv0xPQ, "$this$sumOf");
      Intrinsics.checkNotNullParameter(selector, "selector");
      BigDecimal var10000 = BigDecimal.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var10000, "valueOf(...)");
      BigDecimal sum = var10000;
      int var3 = 0;

      for(int var4 = UIntArray.getSize-impl($this$sumOf_u2djgv0xPQ); var3 < var4; ++var3) {
         int element = UIntArray.get-pVg5ArA($this$sumOf_u2djgv0xPQ, var3);
         var10000 = sum.add((BigDecimal)selector.invoke(UInt.box-impl(element)));
         Intrinsics.checkNotNullExpressionValue(var10000, "add(...)");
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @JvmName(
      name = "sumOfBigDecimal"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final BigDecimal sumOfBigDecimal(long[] $this$sumOf_u2dMShoTSo, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$sumOf_u2dMShoTSo, "$this$sumOf");
      Intrinsics.checkNotNullParameter(selector, "selector");
      BigDecimal var10000 = BigDecimal.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var10000, "valueOf(...)");
      BigDecimal sum = var10000;
      int var3 = 0;

      for(int var4 = ULongArray.getSize-impl($this$sumOf_u2dMShoTSo); var3 < var4; ++var3) {
         long element = ULongArray.get-s-VKNKU($this$sumOf_u2dMShoTSo, var3);
         var10000 = sum.add((BigDecimal)selector.invoke(ULong.box-impl(element)));
         Intrinsics.checkNotNullExpressionValue(var10000, "add(...)");
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @JvmName(
      name = "sumOfBigDecimal"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final BigDecimal sumOfBigDecimal(byte[] $this$sumOf_u2dJOV_ifY, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$sumOf_u2dJOV_ifY, "$this$sumOf");
      Intrinsics.checkNotNullParameter(selector, "selector");
      BigDecimal var10000 = BigDecimal.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var10000, "valueOf(...)");
      BigDecimal sum = var10000;
      int var3 = 0;

      for(int var4 = UByteArray.getSize-impl($this$sumOf_u2dJOV_ifY); var3 < var4; ++var3) {
         byte element = UByteArray.get-w2LRezQ($this$sumOf_u2dJOV_ifY, var3);
         var10000 = sum.add((BigDecimal)selector.invoke(UByte.box-impl(element)));
         Intrinsics.checkNotNullExpressionValue(var10000, "add(...)");
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @JvmName(
      name = "sumOfBigDecimal"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final BigDecimal sumOfBigDecimal(short[] $this$sumOf_u2dxTcfx_M, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$sumOf_u2dxTcfx_M, "$this$sumOf");
      Intrinsics.checkNotNullParameter(selector, "selector");
      BigDecimal var10000 = BigDecimal.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var10000, "valueOf(...)");
      BigDecimal sum = var10000;
      int var3 = 0;

      for(int var4 = UShortArray.getSize-impl($this$sumOf_u2dxTcfx_M); var3 < var4; ++var3) {
         short element = UShortArray.get-Mh2AYeg($this$sumOf_u2dxTcfx_M, var3);
         var10000 = sum.add((BigDecimal)selector.invoke(UShort.box-impl(element)));
         Intrinsics.checkNotNullExpressionValue(var10000, "add(...)");
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @JvmName(
      name = "sumOfBigInteger"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final BigInteger sumOfBigInteger(int[] $this$sumOf_u2djgv0xPQ, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$sumOf_u2djgv0xPQ, "$this$sumOf");
      Intrinsics.checkNotNullParameter(selector, "selector");
      BigInteger var10000 = BigInteger.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var10000, "valueOf(...)");
      BigInteger sum = var10000;
      int var3 = 0;

      for(int var4 = UIntArray.getSize-impl($this$sumOf_u2djgv0xPQ); var3 < var4; ++var3) {
         int element = UIntArray.get-pVg5ArA($this$sumOf_u2djgv0xPQ, var3);
         var10000 = sum.add((BigInteger)selector.invoke(UInt.box-impl(element)));
         Intrinsics.checkNotNullExpressionValue(var10000, "add(...)");
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @JvmName(
      name = "sumOfBigInteger"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final BigInteger sumOfBigInteger(long[] $this$sumOf_u2dMShoTSo, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$sumOf_u2dMShoTSo, "$this$sumOf");
      Intrinsics.checkNotNullParameter(selector, "selector");
      BigInteger var10000 = BigInteger.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var10000, "valueOf(...)");
      BigInteger sum = var10000;
      int var3 = 0;

      for(int var4 = ULongArray.getSize-impl($this$sumOf_u2dMShoTSo); var3 < var4; ++var3) {
         long element = ULongArray.get-s-VKNKU($this$sumOf_u2dMShoTSo, var3);
         var10000 = sum.add((BigInteger)selector.invoke(ULong.box-impl(element)));
         Intrinsics.checkNotNullExpressionValue(var10000, "add(...)");
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @JvmName(
      name = "sumOfBigInteger"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final BigInteger sumOfBigInteger(byte[] $this$sumOf_u2dJOV_ifY, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$sumOf_u2dJOV_ifY, "$this$sumOf");
      Intrinsics.checkNotNullParameter(selector, "selector");
      BigInteger var10000 = BigInteger.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var10000, "valueOf(...)");
      BigInteger sum = var10000;
      int var3 = 0;

      for(int var4 = UByteArray.getSize-impl($this$sumOf_u2dJOV_ifY); var3 < var4; ++var3) {
         byte element = UByteArray.get-w2LRezQ($this$sumOf_u2dJOV_ifY, var3);
         var10000 = sum.add((BigInteger)selector.invoke(UByte.box-impl(element)));
         Intrinsics.checkNotNullExpressionValue(var10000, "add(...)");
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @JvmName(
      name = "sumOfBigInteger"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final BigInteger sumOfBigInteger(short[] $this$sumOf_u2dxTcfx_M, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$sumOf_u2dxTcfx_M, "$this$sumOf");
      Intrinsics.checkNotNullParameter(selector, "selector");
      BigInteger var10000 = BigInteger.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var10000, "valueOf(...)");
      BigInteger sum = var10000;
      int var3 = 0;

      for(int var4 = UShortArray.getSize-impl($this$sumOf_u2dxTcfx_M); var3 < var4; ++var3) {
         short element = UShortArray.get-Mh2AYeg($this$sumOf_u2dxTcfx_M, var3);
         var10000 = sum.add((BigInteger)selector.invoke(UShort.box-impl(element)));
         Intrinsics.checkNotNullExpressionValue(var10000, "add(...)");
         sum = var10000;
      }

      return sum;
   }

   public UArraysKt___UArraysJvmKt() {
   }
}
