package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.internal.IntrinsicConstEvaluation;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.UIntRange;
import kotlin.ranges.URangesKt;
import org.jetbrains.annotations.NotNull;

@JvmInline
@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0087@\u0018\u0000 |2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001|B\u0011\b\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\t\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\r\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\nH\u0087\n¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\r\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0000H\u0097\n¢\u0006\u0004\b\u000e\u0010\bJ\u0018\u0010\r\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\n¢\u0006\u0004\b\u0010\u0010\u0011J\u0018\u0010\r\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0012H\u0087\n¢\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0016\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0005J\u0018\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\nH\u0087\n¢\u0006\u0004\b\u0017\u0010\fJ\u0018\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b\u0019\u0010\bJ\u0018\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\n¢\u0006\u0004\b\u001a\u0010\u001bJ\u0018\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0012H\u0087\n¢\u0006\u0004\b\u001c\u0010\u0014J\u001a\u0010!\u001a\u00020\u001e2\b\u0010\u0006\u001a\u0004\u0018\u00010\u001dHÖ\u0003¢\u0006\u0004\b\u001f\u0010 J\u0018\u0010#\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\nH\u0087\b¢\u0006\u0004\b\"\u0010\fJ\u0018\u0010#\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b$\u0010\bJ\u0018\u0010#\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\b¢\u0006\u0004\b%\u0010\u001bJ\u0018\u0010#\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0012H\u0087\b¢\u0006\u0004\b&\u0010\u0014J\u0010\u0010(\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b'\u0010\u0005J\u0013\u0010*\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b)\u0010\u0005J\u0013\u0010,\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b+\u0010\u0005J\u0018\u0010.\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\nH\u0087\n¢\u0006\u0004\b-\u0010\fJ\u0018\u0010.\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b/\u0010\bJ\u0018\u0010.\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\n¢\u0006\u0004\b0\u0010\u001bJ\u0018\u0010.\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0012H\u0087\n¢\u0006\u0004\b1\u0010\u0014J\u0018\u00104\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\nH\u0087\b¢\u0006\u0004\b2\u00103J\u0018\u00104\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b5\u0010\bJ\u0018\u00104\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\b¢\u0006\u0004\b6\u0010\u001bJ\u0018\u00104\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u0012H\u0087\b¢\u0006\u0004\b7\u00108J\u0018\u0010:\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\b9\u0010\bJ\u0018\u0010<\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\nH\u0087\n¢\u0006\u0004\b;\u0010\fJ\u0018\u0010<\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b=\u0010\bJ\u0018\u0010<\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\n¢\u0006\u0004\b>\u0010\u001bJ\u0018\u0010<\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0012H\u0087\n¢\u0006\u0004\b?\u0010\u0014J\u0018\u0010C\u001a\u00020@2\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bA\u0010BJ\u0018\u0010E\u001a\u00020@2\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bD\u0010BJ\u0018\u0010G\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\nH\u0087\n¢\u0006\u0004\bF\u0010\fJ\u0018\u0010G\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bH\u0010\bJ\u0018\u0010G\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\n¢\u0006\u0004\bI\u0010\u001bJ\u0018\u0010G\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0012H\u0087\n¢\u0006\u0004\bJ\u0010\u0014J\u001b\u0010M\u001a\u00020\u00002\u0006\u0010K\u001a\u00020\u0002H\u0087\fø\u0001\u0000¢\u0006\u0004\bL\u0010\bJ\u001b\u0010O\u001a\u00020\u00002\u0006\u0010K\u001a\u00020\u0002H\u0087\fø\u0001\u0000¢\u0006\u0004\bN\u0010\bJ\u0018\u0010Q\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\nH\u0087\n¢\u0006\u0004\bP\u0010\fJ\u0018\u0010Q\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bR\u0010\bJ\u0018\u0010Q\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\n¢\u0006\u0004\bS\u0010\u001bJ\u0018\u0010Q\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0012H\u0087\n¢\u0006\u0004\bT\u0010\u0014J\u0010\u0010X\u001a\u00020UH\u0087\b¢\u0006\u0004\bV\u0010WJ\u0010\u0010\\\u001a\u00020YH\u0087\b¢\u0006\u0004\bZ\u0010[J\u0010\u0010`\u001a\u00020]H\u0087\b¢\u0006\u0004\b^\u0010_J\u0010\u0010b\u001a\u00020\u0002H\u0087\b¢\u0006\u0004\ba\u0010\u0005J\u0010\u0010f\u001a\u00020cH\u0087\b¢\u0006\u0004\bd\u0010eJ\u0010\u0010j\u001a\u00020gH\u0087\b¢\u0006\u0004\bh\u0010iJ\u000f\u0010n\u001a\u00020kH\u0016¢\u0006\u0004\bl\u0010mJ\u0013\u0010p\u001a\u00020\nH\u0087\bø\u0001\u0000¢\u0006\u0004\bo\u0010WJ\u0013\u0010r\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\bq\u0010\u0005J\u0013\u0010t\u001a\u00020\u000fH\u0087\bø\u0001\u0000¢\u0006\u0004\bs\u0010eJ\u0013\u0010v\u001a\u00020\u0012H\u0087\bø\u0001\u0000¢\u0006\u0004\bu\u0010iJ\u0018\u0010x\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\bw\u0010\bR\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0081\u0004¢\u0006\f\n\u0004\b\u0003\u0010y\u0012\u0004\bz\u0010{\u0088\u0001\u0003\u0092\u0001\u00020\u0002\u0082\u0002\u0004\n\u0002\b!¨\u0006}"},
   d2 = {"Lkotlin/UInt;", "", "", "data", "constructor-impl", "(I)I", "other", "and-WZ4Q5Ns", "(II)I", "and", "Lkotlin/UByte;", "compareTo-7apg3OU", "(IB)I", "compareTo", "compareTo-WZ4Q5Ns", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(IJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(IS)I", "dec-pVg5ArA", "dec", "div-7apg3OU", "div", "div-WZ4Q5Ns", "div-VKZWuLQ", "(IJ)J", "div-xj2QHRw", "", "", "equals-impl", "(ILjava/lang/Object;)Z", "equals", "floorDiv-7apg3OU", "floorDiv", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode-impl", "hashCode", "inc-pVg5ArA", "inc", "inv-pVg5ArA", "inv", "minus-7apg3OU", "minus", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod-7apg3OU", "(IB)B", "mod", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(IS)S", "or-WZ4Q5Ns", "or", "plus-7apg3OU", "plus", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "Lkotlin/ranges/UIntRange;", "rangeTo-WZ4Q5Ns", "(II)Lkotlin/ranges/UIntRange;", "rangeTo", "rangeUntil-WZ4Q5Ns", "rangeUntil", "rem-7apg3OU", "rem", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "bitCount", "shl-pVg5ArA", "shl", "shr-pVg5ArA", "shr", "times-7apg3OU", "times", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "", "toByte-impl", "(I)B", "toByte", "", "toDouble-impl", "(I)D", "toDouble", "", "toFloat-impl", "(I)F", "toFloat", "toInt-impl", "toInt", "", "toLong-impl", "(I)J", "toLong", "", "toShort-impl", "(I)S", "toShort", "", "toString-impl", "(I)Ljava/lang/String;", "toString", "toUByte-w2LRezQ", "toUByte", "toUInt-pVg5ArA", "toUInt", "toULong-s-VKNKU", "toULong", "toUShort-Mh2AYeg", "toUShort", "xor-WZ4Q5Ns", "xor", "I", "getData$annotations", "()V", "Companion", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.5"
)
@WasExperimental(
   markerClass = {ExperimentalUnsignedTypes.class}
)
public final class UInt implements Comparable {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private final int data;
   public static final int MIN_VALUE = 0;
   public static final int MAX_VALUE = -1;
   public static final int SIZE_BYTES = 4;
   public static final int SIZE_BITS = 32;

   /** @deprecated */
   // $FF: synthetic method
   @PublishedApi
   public static void getData$annotations() {
   }

   @InlineOnly
   private static final int compareTo_7apg3OU/* $FF was: compareTo-7apg3OU*/(int arg0, byte other) {
      return Integer.compareUnsigned(arg0, constructor-impl(other & 255));
   }

   @InlineOnly
   private static final int compareTo_xj2QHRw/* $FF was: compareTo-xj2QHRw*/(int arg0, short other) {
      return Integer.compareUnsigned(arg0, constructor-impl(other & '\uffff'));
   }

   @InlineOnly
   private static int compareTo_WZ4Q5Ns/* $FF was: compareTo-WZ4Q5Ns*/(int arg0, int other) {
      return UnsignedKt.uintCompare(arg0, other);
   }

   @InlineOnly
   private int compareTo_WZ4Q5Ns/* $FF was: compareTo-WZ4Q5Ns*/(int other) {
      return UnsignedKt.uintCompare(this.unbox-impl(), other);
   }

   @InlineOnly
   private static final int compareTo_VKZWuLQ/* $FF was: compareTo-VKZWuLQ*/(int arg0, long other) {
      return Long.compareUnsigned(ULong.constructor-impl((long)arg0 & 4294967295L), other);
   }

   @InlineOnly
   private static final int plus_7apg3OU/* $FF was: plus-7apg3OU*/(int arg0, byte other) {
      return constructor-impl(arg0 + constructor-impl(other & 255));
   }

   @InlineOnly
   private static final int plus_xj2QHRw/* $FF was: plus-xj2QHRw*/(int arg0, short other) {
      return constructor-impl(arg0 + constructor-impl(other & '\uffff'));
   }

   @InlineOnly
   private static final int plus_WZ4Q5Ns/* $FF was: plus-WZ4Q5Ns*/(int arg0, int other) {
      return constructor-impl(arg0 + other);
   }

   @InlineOnly
   private static final long plus_VKZWuLQ/* $FF was: plus-VKZWuLQ*/(int arg0, long other) {
      return ULong.constructor-impl(ULong.constructor-impl((long)arg0 & 4294967295L) + other);
   }

   @InlineOnly
   private static final int minus_7apg3OU/* $FF was: minus-7apg3OU*/(int arg0, byte other) {
      return constructor-impl(arg0 - constructor-impl(other & 255));
   }

   @InlineOnly
   private static final int minus_xj2QHRw/* $FF was: minus-xj2QHRw*/(int arg0, short other) {
      return constructor-impl(arg0 - constructor-impl(other & '\uffff'));
   }

   @InlineOnly
   private static final int minus_WZ4Q5Ns/* $FF was: minus-WZ4Q5Ns*/(int arg0, int other) {
      return constructor-impl(arg0 - other);
   }

   @InlineOnly
   private static final long minus_VKZWuLQ/* $FF was: minus-VKZWuLQ*/(int arg0, long other) {
      return ULong.constructor-impl(ULong.constructor-impl((long)arg0 & 4294967295L) - other);
   }

   @InlineOnly
   private static final int times_7apg3OU/* $FF was: times-7apg3OU*/(int arg0, byte other) {
      return constructor-impl(arg0 * constructor-impl(other & 255));
   }

   @InlineOnly
   private static final int times_xj2QHRw/* $FF was: times-xj2QHRw*/(int arg0, short other) {
      return constructor-impl(arg0 * constructor-impl(other & '\uffff'));
   }

   @InlineOnly
   private static final int times_WZ4Q5Ns/* $FF was: times-WZ4Q5Ns*/(int arg0, int other) {
      return constructor-impl(arg0 * other);
   }

   @InlineOnly
   private static final long times_VKZWuLQ/* $FF was: times-VKZWuLQ*/(int arg0, long other) {
      return ULong.constructor-impl(ULong.constructor-impl((long)arg0 & 4294967295L) * other);
   }

   @InlineOnly
   private static final int div_7apg3OU/* $FF was: div-7apg3OU*/(int arg0, byte other) {
      return Integer.divideUnsigned(arg0, constructor-impl(other & 255));
   }

   @InlineOnly
   private static final int div_xj2QHRw/* $FF was: div-xj2QHRw*/(int arg0, short other) {
      return Integer.divideUnsigned(arg0, constructor-impl(other & '\uffff'));
   }

   @InlineOnly
   private static final int div_WZ4Q5Ns/* $FF was: div-WZ4Q5Ns*/(int arg0, int other) {
      return UnsignedKt.uintDivide-J1ME1BU(arg0, other);
   }

   @InlineOnly
   private static final long div_VKZWuLQ/* $FF was: div-VKZWuLQ*/(int arg0, long other) {
      return Long.divideUnsigned(ULong.constructor-impl((long)arg0 & 4294967295L), other);
   }

   @InlineOnly
   private static final int rem_7apg3OU/* $FF was: rem-7apg3OU*/(int arg0, byte other) {
      return Integer.remainderUnsigned(arg0, constructor-impl(other & 255));
   }

   @InlineOnly
   private static final int rem_xj2QHRw/* $FF was: rem-xj2QHRw*/(int arg0, short other) {
      return Integer.remainderUnsigned(arg0, constructor-impl(other & '\uffff'));
   }

   @InlineOnly
   private static final int rem_WZ4Q5Ns/* $FF was: rem-WZ4Q5Ns*/(int arg0, int other) {
      return UnsignedKt.uintRemainder-J1ME1BU(arg0, other);
   }

   @InlineOnly
   private static final long rem_VKZWuLQ/* $FF was: rem-VKZWuLQ*/(int arg0, long other) {
      return Long.remainderUnsigned(ULong.constructor-impl((long)arg0 & 4294967295L), other);
   }

   @InlineOnly
   private static final int floorDiv_7apg3OU/* $FF was: floorDiv-7apg3OU*/(int arg0, byte other) {
      return Integer.divideUnsigned(arg0, constructor-impl(other & 255));
   }

   @InlineOnly
   private static final int floorDiv_xj2QHRw/* $FF was: floorDiv-xj2QHRw*/(int arg0, short other) {
      return Integer.divideUnsigned(arg0, constructor-impl(other & '\uffff'));
   }

   @InlineOnly
   private static final int floorDiv_WZ4Q5Ns/* $FF was: floorDiv-WZ4Q5Ns*/(int arg0, int other) {
      return Integer.divideUnsigned(arg0, other);
   }

   @InlineOnly
   private static final long floorDiv_VKZWuLQ/* $FF was: floorDiv-VKZWuLQ*/(int arg0, long other) {
      return Long.divideUnsigned(ULong.constructor-impl((long)arg0 & 4294967295L), other);
   }

   @InlineOnly
   private static final byte mod_7apg3OU/* $FF was: mod-7apg3OU*/(int arg0, byte other) {
      return UByte.constructor-impl((byte)Integer.remainderUnsigned(arg0, constructor-impl(other & 255)));
   }

   @InlineOnly
   private static final short mod_xj2QHRw/* $FF was: mod-xj2QHRw*/(int arg0, short other) {
      return UShort.constructor-impl((short)Integer.remainderUnsigned(arg0, constructor-impl(other & '\uffff')));
   }

   @InlineOnly
   private static final int mod_WZ4Q5Ns/* $FF was: mod-WZ4Q5Ns*/(int arg0, int other) {
      return Integer.remainderUnsigned(arg0, other);
   }

   @InlineOnly
   private static final long mod_VKZWuLQ/* $FF was: mod-VKZWuLQ*/(int arg0, long other) {
      return Long.remainderUnsigned(ULong.constructor-impl((long)arg0 & 4294967295L), other);
   }

   @InlineOnly
   private static final int inc_pVg5ArA/* $FF was: inc-pVg5ArA*/(int arg0) {
      return constructor-impl(arg0 + 1);
   }

   @InlineOnly
   private static final int dec_pVg5ArA/* $FF was: dec-pVg5ArA*/(int arg0) {
      return constructor-impl(arg0 + -1);
   }

   @InlineOnly
   private static final UIntRange rangeTo_WZ4Q5Ns/* $FF was: rangeTo-WZ4Q5Ns*/(int arg0, int other) {
      return new UIntRange(arg0, other, (DefaultConstructorMarker)null);
   }

   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final UIntRange rangeUntil_WZ4Q5Ns/* $FF was: rangeUntil-WZ4Q5Ns*/(int arg0, int other) {
      return URangesKt.until-J1ME1BU(arg0, other);
   }

   @InlineOnly
   private static final int shl_pVg5ArA/* $FF was: shl-pVg5ArA*/(int arg0, int bitCount) {
      return constructor-impl(arg0 << bitCount);
   }

   @InlineOnly
   private static final int shr_pVg5ArA/* $FF was: shr-pVg5ArA*/(int arg0, int bitCount) {
      return constructor-impl(arg0 >>> bitCount);
   }

   @InlineOnly
   private static final int and_WZ4Q5Ns/* $FF was: and-WZ4Q5Ns*/(int arg0, int other) {
      return constructor-impl(arg0 & other);
   }

   @InlineOnly
   private static final int or_WZ4Q5Ns/* $FF was: or-WZ4Q5Ns*/(int arg0, int other) {
      return constructor-impl(arg0 | other);
   }

   @InlineOnly
   private static final int xor_WZ4Q5Ns/* $FF was: xor-WZ4Q5Ns*/(int arg0, int other) {
      return constructor-impl(arg0 ^ other);
   }

   @InlineOnly
   private static final int inv_pVg5ArA/* $FF was: inv-pVg5ArA*/(int arg0) {
      return constructor-impl(~arg0);
   }

   @InlineOnly
   private static final byte toByte_impl/* $FF was: toByte-impl*/(int arg0) {
      return (byte)arg0;
   }

   @InlineOnly
   private static final short toShort_impl/* $FF was: toShort-impl*/(int arg0) {
      return (short)arg0;
   }

   @InlineOnly
   private static final int toInt_impl/* $FF was: toInt-impl*/(int arg0) {
      return arg0;
   }

   @InlineOnly
   private static final long toLong_impl/* $FF was: toLong-impl*/(int arg0) {
      return (long)arg0 & 4294967295L;
   }

   @InlineOnly
   private static final byte toUByte_w2LRezQ/* $FF was: toUByte-w2LRezQ*/(int arg0) {
      return UByte.constructor-impl((byte)arg0);
   }

   @InlineOnly
   private static final short toUShort_Mh2AYeg/* $FF was: toUShort-Mh2AYeg*/(int arg0) {
      return UShort.constructor-impl((short)arg0);
   }

   @InlineOnly
   private static final int toUInt_pVg5ArA/* $FF was: toUInt-pVg5ArA*/(int arg0) {
      return arg0;
   }

   @InlineOnly
   private static final long toULong_s_VKNKU/* $FF was: toULong-s-VKNKU*/(int arg0) {
      return ULong.constructor-impl((long)arg0 & 4294967295L);
   }

   @InlineOnly
   private static final float toFloat_impl/* $FF was: toFloat-impl*/(int arg0) {
      return (float)UnsignedKt.uintToDouble(arg0);
   }

   @InlineOnly
   private static final double toDouble_impl/* $FF was: toDouble-impl*/(int arg0) {
      return UnsignedKt.uintToDouble(arg0);
   }

   @NotNull
   public static String toString_impl/* $FF was: toString-impl*/(int arg0) {
      return String.valueOf((long)arg0 & 4294967295L);
   }

   @NotNull
   public String toString() {
      return toString-impl(this.data);
   }

   public static int hashCode_impl/* $FF was: hashCode-impl*/(int arg0) {
      return Integer.hashCode(arg0);
   }

   public int hashCode() {
      return hashCode-impl(this.data);
   }

   public static boolean equals_impl/* $FF was: equals-impl*/(int arg0, Object other) {
      if (!(other instanceof UInt)) {
         return false;
      } else {
         int var2 = ((UInt)other).unbox-impl();
         return arg0 == var2;
      }
   }

   public boolean equals(Object other) {
      return equals-impl(this.data, other);
   }

   // $FF: synthetic method
   @IntrinsicConstEvaluation
   @PublishedApi
   private UInt(int data) {
      this.data = data;
   }

   @IntrinsicConstEvaluation
   @PublishedApi
   public static int constructor_impl/* $FF was: constructor-impl*/(int data) {
      return data;
   }

   // $FF: synthetic method
   public static final UInt box_impl/* $FF was: box-impl*/(int v) {
      return new UInt(v);
   }

   // $FF: synthetic method
   public final int unbox_impl/* $FF was: unbox-impl*/() {
      return this.data;
   }

   public static final boolean equals_impl0/* $FF was: equals-impl0*/(int p1, int p2) {
      return p1 == p2;
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006X\u0086Tø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\u00020\u00048\u0006X\u0086Tø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\t\u001a\u00020\b8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\t\u0010\u0006R\u0014\u0010\n\u001a\u00020\b8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\n\u0010\u0006\u0082\u0002\u0004\n\u0002\b!¨\u0006\u000b"},
      d2 = {"Lkotlin/UInt$Companion;", "", "<init>", "()V", "Lkotlin/UInt;", "MAX_VALUE", "I", "MIN_VALUE", "", "SIZE_BITS", "SIZE_BYTES", "kotlin-stdlib"}
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
