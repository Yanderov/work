package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.internal.IntrinsicConstEvaluation;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.URangesKt;
import org.jetbrains.annotations.NotNull;

@JvmInline
@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010\u0005\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0087@\u0018\u0000 w2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001wB\u0011\b\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\t\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0000H\u0097\n¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u000eH\u0087\n¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0011H\u0087\n¢\u0006\u0004\b\u0012\u0010\u0013J\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0014H\u0087\n¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0018\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0005J\u0018\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b\u0019\u0010\fJ\u0018\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u000eH\u0087\n¢\u0006\u0004\b\u001b\u0010\u0010J\u0018\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0011H\u0087\n¢\u0006\u0004\b\u001c\u0010\u001dJ\u0018\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0014H\u0087\n¢\u0006\u0004\b\u001e\u0010\u0016J\u001a\u0010#\u001a\u00020 2\b\u0010\u0006\u001a\u0004\u0018\u00010\u001fHÖ\u0003¢\u0006\u0004\b!\u0010\"J\u0018\u0010%\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b$\u0010\fJ\u0018\u0010%\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u000eH\u0087\b¢\u0006\u0004\b&\u0010\u0010J\u0018\u0010%\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0011H\u0087\b¢\u0006\u0004\b'\u0010\u001dJ\u0018\u0010%\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0014H\u0087\b¢\u0006\u0004\b(\u0010\u0016J\u0010\u0010+\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b)\u0010*J\u0013\u0010-\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b,\u0010\u0005J\u0013\u0010/\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b.\u0010\u0005J\u0018\u00101\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b0\u0010\fJ\u0018\u00101\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u000eH\u0087\n¢\u0006\u0004\b2\u0010\u0010J\u0018\u00101\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0011H\u0087\n¢\u0006\u0004\b3\u0010\u001dJ\u0018\u00101\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0014H\u0087\n¢\u0006\u0004\b4\u0010\u0016J\u0018\u00106\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b5\u0010\bJ\u0018\u00106\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u000eH\u0087\b¢\u0006\u0004\b7\u0010\u0010J\u0018\u00106\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0011H\u0087\b¢\u0006\u0004\b8\u0010\u001dJ\u0018\u00106\u001a\u00020\u00142\u0006\u0010\u0006\u001a\u00020\u0014H\u0087\b¢\u0006\u0004\b9\u0010:J\u0018\u0010<\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\b;\u0010\bJ\u0018\u0010>\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b=\u0010\fJ\u0018\u0010>\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u000eH\u0087\n¢\u0006\u0004\b?\u0010\u0010J\u0018\u0010>\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0011H\u0087\n¢\u0006\u0004\b@\u0010\u001dJ\u0018\u0010>\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0014H\u0087\n¢\u0006\u0004\bA\u0010\u0016J\u0018\u0010E\u001a\u00020B2\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bC\u0010DJ\u0018\u0010G\u001a\u00020B2\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bF\u0010DJ\u0018\u0010I\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bH\u0010\fJ\u0018\u0010I\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u000eH\u0087\n¢\u0006\u0004\bJ\u0010\u0010J\u0018\u0010I\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0011H\u0087\n¢\u0006\u0004\bK\u0010\u001dJ\u0018\u0010I\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0014H\u0087\n¢\u0006\u0004\bL\u0010\u0016J\u0018\u0010N\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bM\u0010\fJ\u0018\u0010N\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u000eH\u0087\n¢\u0006\u0004\bO\u0010\u0010J\u0018\u0010N\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0011H\u0087\n¢\u0006\u0004\bP\u0010\u001dJ\u0018\u0010N\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0014H\u0087\n¢\u0006\u0004\bQ\u0010\u0016J\u0010\u0010S\u001a\u00020\u0002H\u0087\b¢\u0006\u0004\bR\u0010\u0005J\u0010\u0010W\u001a\u00020TH\u0087\b¢\u0006\u0004\bU\u0010VJ\u0010\u0010[\u001a\u00020XH\u0087\b¢\u0006\u0004\bY\u0010ZJ\u0010\u0010]\u001a\u00020\nH\u0087\b¢\u0006\u0004\b\\\u0010*J\u0010\u0010a\u001a\u00020^H\u0087\b¢\u0006\u0004\b_\u0010`J\u0010\u0010e\u001a\u00020bH\u0087\b¢\u0006\u0004\bc\u0010dJ\u000f\u0010i\u001a\u00020fH\u0016¢\u0006\u0004\bg\u0010hJ\u0013\u0010k\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\bj\u0010\u0005J\u0013\u0010m\u001a\u00020\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\bl\u0010*J\u0013\u0010o\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\bn\u0010`J\u0013\u0010q\u001a\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\bp\u0010dJ\u0018\u0010s\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\br\u0010\bR\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0081\u0004¢\u0006\f\n\u0004\b\u0003\u0010t\u0012\u0004\bu\u0010v\u0088\u0001\u0003\u0092\u0001\u00020\u0002\u0082\u0002\u0004\n\u0002\b!¨\u0006x"},
   d2 = {"Lkotlin/UByte;", "", "", "data", "constructor-impl", "(B)B", "other", "and-7apg3OU", "(BB)B", "and", "", "compareTo-7apg3OU", "(BB)I", "compareTo", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "dec-w2LRezQ", "dec", "div-7apg3OU", "div", "div-WZ4Q5Ns", "div-VKZWuLQ", "(BJ)J", "div-xj2QHRw", "", "", "equals-impl", "(BLjava/lang/Object;)Z", "equals", "floorDiv-7apg3OU", "floorDiv", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode-impl", "(B)I", "hashCode", "inc-w2LRezQ", "inc", "inv-w2LRezQ", "inv", "minus-7apg3OU", "minus", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod-7apg3OU", "mod", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(BS)S", "or-7apg3OU", "or", "plus-7apg3OU", "plus", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rangeTo", "rangeUntil-7apg3OU", "rangeUntil", "rem-7apg3OU", "rem", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times-7apg3OU", "times", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte-impl", "toByte", "", "toDouble-impl", "(B)D", "toDouble", "", "toFloat-impl", "(B)F", "toFloat", "toInt-impl", "toInt", "", "toLong-impl", "(B)J", "toLong", "", "toShort-impl", "(B)S", "toShort", "", "toString-impl", "(B)Ljava/lang/String;", "toString", "toUByte-w2LRezQ", "toUByte", "toUInt-pVg5ArA", "toUInt", "toULong-s-VKNKU", "toULong", "toUShort-Mh2AYeg", "toUShort", "xor-7apg3OU", "xor", "B", "getData$annotations", "()V", "Companion", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.5"
)
@WasExperimental(
   markerClass = {ExperimentalUnsignedTypes.class}
)
public final class UByte implements Comparable {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private final byte data;
   public static final byte MIN_VALUE = 0;
   public static final byte MAX_VALUE = -1;
   public static final int SIZE_BYTES = 1;
   public static final int SIZE_BITS = 8;

   /** @deprecated */
   // $FF: synthetic method
   @PublishedApi
   public static void getData$annotations() {
   }

   @InlineOnly
   private static int compareTo_7apg3OU/* $FF was: compareTo-7apg3OU*/(byte arg0, byte other) {
      return Intrinsics.compare(arg0 & 255, other & 255);
   }

   @InlineOnly
   private int compareTo_7apg3OU/* $FF was: compareTo-7apg3OU*/(byte other) {
      return Intrinsics.compare(this.unbox-impl() & 255, other & 255);
   }

   @InlineOnly
   private static final int compareTo_xj2QHRw/* $FF was: compareTo-xj2QHRw*/(byte arg0, short other) {
      return Intrinsics.compare(arg0 & 255, other & '\uffff');
   }

   @InlineOnly
   private static final int compareTo_WZ4Q5Ns/* $FF was: compareTo-WZ4Q5Ns*/(byte arg0, int other) {
      return Integer.compareUnsigned(UInt.constructor-impl(arg0 & 255), other);
   }

   @InlineOnly
   private static final int compareTo_VKZWuLQ/* $FF was: compareTo-VKZWuLQ*/(byte arg0, long other) {
      return Long.compareUnsigned(ULong.constructor-impl((long)arg0 & 255L), other);
   }

   @InlineOnly
   private static final int plus_7apg3OU/* $FF was: plus-7apg3OU*/(byte arg0, byte other) {
      return UInt.constructor-impl(UInt.constructor-impl(arg0 & 255) + UInt.constructor-impl(other & 255));
   }

   @InlineOnly
   private static final int plus_xj2QHRw/* $FF was: plus-xj2QHRw*/(byte arg0, short other) {
      return UInt.constructor-impl(UInt.constructor-impl(arg0 & 255) + UInt.constructor-impl(other & '\uffff'));
   }

   @InlineOnly
   private static final int plus_WZ4Q5Ns/* $FF was: plus-WZ4Q5Ns*/(byte arg0, int other) {
      return UInt.constructor-impl(UInt.constructor-impl(arg0 & 255) + other);
   }

   @InlineOnly
   private static final long plus_VKZWuLQ/* $FF was: plus-VKZWuLQ*/(byte arg0, long other) {
      return ULong.constructor-impl(ULong.constructor-impl((long)arg0 & 255L) + other);
   }

   @InlineOnly
   private static final int minus_7apg3OU/* $FF was: minus-7apg3OU*/(byte arg0, byte other) {
      return UInt.constructor-impl(UInt.constructor-impl(arg0 & 255) - UInt.constructor-impl(other & 255));
   }

   @InlineOnly
   private static final int minus_xj2QHRw/* $FF was: minus-xj2QHRw*/(byte arg0, short other) {
      return UInt.constructor-impl(UInt.constructor-impl(arg0 & 255) - UInt.constructor-impl(other & '\uffff'));
   }

   @InlineOnly
   private static final int minus_WZ4Q5Ns/* $FF was: minus-WZ4Q5Ns*/(byte arg0, int other) {
      return UInt.constructor-impl(UInt.constructor-impl(arg0 & 255) - other);
   }

   @InlineOnly
   private static final long minus_VKZWuLQ/* $FF was: minus-VKZWuLQ*/(byte arg0, long other) {
      return ULong.constructor-impl(ULong.constructor-impl((long)arg0 & 255L) - other);
   }

   @InlineOnly
   private static final int times_7apg3OU/* $FF was: times-7apg3OU*/(byte arg0, byte other) {
      return UInt.constructor-impl(UInt.constructor-impl(arg0 & 255) * UInt.constructor-impl(other & 255));
   }

   @InlineOnly
   private static final int times_xj2QHRw/* $FF was: times-xj2QHRw*/(byte arg0, short other) {
      return UInt.constructor-impl(UInt.constructor-impl(arg0 & 255) * UInt.constructor-impl(other & '\uffff'));
   }

   @InlineOnly
   private static final int times_WZ4Q5Ns/* $FF was: times-WZ4Q5Ns*/(byte arg0, int other) {
      return UInt.constructor-impl(UInt.constructor-impl(arg0 & 255) * other);
   }

   @InlineOnly
   private static final long times_VKZWuLQ/* $FF was: times-VKZWuLQ*/(byte arg0, long other) {
      return ULong.constructor-impl(ULong.constructor-impl((long)arg0 & 255L) * other);
   }

   @InlineOnly
   private static final int div_7apg3OU/* $FF was: div-7apg3OU*/(byte arg0, byte other) {
      return Integer.divideUnsigned(UInt.constructor-impl(arg0 & 255), UInt.constructor-impl(other & 255));
   }

   @InlineOnly
   private static final int div_xj2QHRw/* $FF was: div-xj2QHRw*/(byte arg0, short other) {
      return Integer.divideUnsigned(UInt.constructor-impl(arg0 & 255), UInt.constructor-impl(other & '\uffff'));
   }

   @InlineOnly
   private static final int div_WZ4Q5Ns/* $FF was: div-WZ4Q5Ns*/(byte arg0, int other) {
      return Integer.divideUnsigned(UInt.constructor-impl(arg0 & 255), other);
   }

   @InlineOnly
   private static final long div_VKZWuLQ/* $FF was: div-VKZWuLQ*/(byte arg0, long other) {
      return Long.divideUnsigned(ULong.constructor-impl((long)arg0 & 255L), other);
   }

   @InlineOnly
   private static final int rem_7apg3OU/* $FF was: rem-7apg3OU*/(byte arg0, byte other) {
      return Integer.remainderUnsigned(UInt.constructor-impl(arg0 & 255), UInt.constructor-impl(other & 255));
   }

   @InlineOnly
   private static final int rem_xj2QHRw/* $FF was: rem-xj2QHRw*/(byte arg0, short other) {
      return Integer.remainderUnsigned(UInt.constructor-impl(arg0 & 255), UInt.constructor-impl(other & '\uffff'));
   }

   @InlineOnly
   private static final int rem_WZ4Q5Ns/* $FF was: rem-WZ4Q5Ns*/(byte arg0, int other) {
      return Integer.remainderUnsigned(UInt.constructor-impl(arg0 & 255), other);
   }

   @InlineOnly
   private static final long rem_VKZWuLQ/* $FF was: rem-VKZWuLQ*/(byte arg0, long other) {
      return Long.remainderUnsigned(ULong.constructor-impl((long)arg0 & 255L), other);
   }

   @InlineOnly
   private static final int floorDiv_7apg3OU/* $FF was: floorDiv-7apg3OU*/(byte arg0, byte other) {
      return Integer.divideUnsigned(UInt.constructor-impl(arg0 & 255), UInt.constructor-impl(other & 255));
   }

   @InlineOnly
   private static final int floorDiv_xj2QHRw/* $FF was: floorDiv-xj2QHRw*/(byte arg0, short other) {
      return Integer.divideUnsigned(UInt.constructor-impl(arg0 & 255), UInt.constructor-impl(other & '\uffff'));
   }

   @InlineOnly
   private static final int floorDiv_WZ4Q5Ns/* $FF was: floorDiv-WZ4Q5Ns*/(byte arg0, int other) {
      return Integer.divideUnsigned(UInt.constructor-impl(arg0 & 255), other);
   }

   @InlineOnly
   private static final long floorDiv_VKZWuLQ/* $FF was: floorDiv-VKZWuLQ*/(byte arg0, long other) {
      return Long.divideUnsigned(ULong.constructor-impl((long)arg0 & 255L), other);
   }

   @InlineOnly
   private static final byte mod_7apg3OU/* $FF was: mod-7apg3OU*/(byte arg0, byte other) {
      return constructor-impl((byte)Integer.remainderUnsigned(UInt.constructor-impl(arg0 & 255), UInt.constructor-impl(other & 255)));
   }

   @InlineOnly
   private static final short mod_xj2QHRw/* $FF was: mod-xj2QHRw*/(byte arg0, short other) {
      return UShort.constructor-impl((short)Integer.remainderUnsigned(UInt.constructor-impl(arg0 & 255), UInt.constructor-impl(other & '\uffff')));
   }

   @InlineOnly
   private static final int mod_WZ4Q5Ns/* $FF was: mod-WZ4Q5Ns*/(byte arg0, int other) {
      return Integer.remainderUnsigned(UInt.constructor-impl(arg0 & 255), other);
   }

   @InlineOnly
   private static final long mod_VKZWuLQ/* $FF was: mod-VKZWuLQ*/(byte arg0, long other) {
      return Long.remainderUnsigned(ULong.constructor-impl((long)arg0 & 255L), other);
   }

   @InlineOnly
   private static final byte inc_w2LRezQ/* $FF was: inc-w2LRezQ*/(byte arg0) {
      return constructor-impl((byte)(arg0 + 1));
   }

   @InlineOnly
   private static final byte dec_w2LRezQ/* $FF was: dec-w2LRezQ*/(byte arg0) {
      return constructor-impl((byte)(arg0 + -1));
   }

   @InlineOnly
   private static final UIntRange rangeTo_7apg3OU/* $FF was: rangeTo-7apg3OU*/(byte arg0, byte other) {
      return new UIntRange(UInt.constructor-impl(arg0 & 255), UInt.constructor-impl(other & 255), (DefaultConstructorMarker)null);
   }

   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final UIntRange rangeUntil_7apg3OU/* $FF was: rangeUntil-7apg3OU*/(byte arg0, byte other) {
      return URangesKt.until-J1ME1BU(UInt.constructor-impl(arg0 & 255), UInt.constructor-impl(other & 255));
   }

   @InlineOnly
   private static final byte and_7apg3OU/* $FF was: and-7apg3OU*/(byte arg0, byte other) {
      return constructor-impl((byte)(arg0 & other));
   }

   @InlineOnly
   private static final byte or_7apg3OU/* $FF was: or-7apg3OU*/(byte arg0, byte other) {
      return constructor-impl((byte)(arg0 | other));
   }

   @InlineOnly
   private static final byte xor_7apg3OU/* $FF was: xor-7apg3OU*/(byte arg0, byte other) {
      return constructor-impl((byte)(arg0 ^ other));
   }

   @InlineOnly
   private static final byte inv_w2LRezQ/* $FF was: inv-w2LRezQ*/(byte arg0) {
      return constructor-impl((byte)(~arg0));
   }

   @InlineOnly
   private static final byte toByte_impl/* $FF was: toByte-impl*/(byte arg0) {
      return arg0;
   }

   @InlineOnly
   private static final short toShort_impl/* $FF was: toShort-impl*/(byte arg0) {
      return (short)((short)arg0 & 255);
   }

   @InlineOnly
   private static final int toInt_impl/* $FF was: toInt-impl*/(byte arg0) {
      return arg0 & 255;
   }

   @InlineOnly
   private static final long toLong_impl/* $FF was: toLong-impl*/(byte arg0) {
      return (long)arg0 & 255L;
   }

   @InlineOnly
   private static final byte toUByte_w2LRezQ/* $FF was: toUByte-w2LRezQ*/(byte arg0) {
      return arg0;
   }

   @InlineOnly
   private static final short toUShort_Mh2AYeg/* $FF was: toUShort-Mh2AYeg*/(byte arg0) {
      return UShort.constructor-impl((short)((short)arg0 & 255));
   }

   @InlineOnly
   private static final int toUInt_pVg5ArA/* $FF was: toUInt-pVg5ArA*/(byte arg0) {
      return UInt.constructor-impl(arg0 & 255);
   }

   @InlineOnly
   private static final long toULong_s_VKNKU/* $FF was: toULong-s-VKNKU*/(byte arg0) {
      return ULong.constructor-impl((long)arg0 & 255L);
   }

   @InlineOnly
   private static final float toFloat_impl/* $FF was: toFloat-impl*/(byte arg0) {
      return (float)UnsignedKt.uintToDouble(arg0 & 255);
   }

   @InlineOnly
   private static final double toDouble_impl/* $FF was: toDouble-impl*/(byte arg0) {
      return UnsignedKt.uintToDouble(arg0 & 255);
   }

   @NotNull
   public static String toString_impl/* $FF was: toString-impl*/(byte arg0) {
      return String.valueOf(arg0 & 255);
   }

   @NotNull
   public String toString() {
      return toString-impl(this.data);
   }

   public static int hashCode_impl/* $FF was: hashCode-impl*/(byte arg0) {
      return Byte.hashCode(arg0);
   }

   public int hashCode() {
      return hashCode-impl(this.data);
   }

   public static boolean equals_impl/* $FF was: equals-impl*/(byte arg0, Object other) {
      if (!(other instanceof UByte)) {
         return false;
      } else {
         byte var2 = ((UByte)other).unbox-impl();
         return arg0 == var2;
      }
   }

   public boolean equals(Object other) {
      return equals-impl(this.data, other);
   }

   // $FF: synthetic method
   @IntrinsicConstEvaluation
   @PublishedApi
   private UByte(byte data) {
      this.data = data;
   }

   @IntrinsicConstEvaluation
   @PublishedApi
   public static byte constructor_impl/* $FF was: constructor-impl*/(byte data) {
      return data;
   }

   // $FF: synthetic method
   public static final UByte box_impl/* $FF was: box-impl*/(byte v) {
      return new UByte(v);
   }

   // $FF: synthetic method
   public final byte unbox_impl/* $FF was: unbox-impl*/() {
      return this.data;
   }

   public static final boolean equals_impl0/* $FF was: equals-impl0*/(byte p1, byte p2) {
      return p1 == p2;
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006X\u0086Tø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\u00020\u00048\u0006X\u0086Tø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\t\u001a\u00020\b8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\b8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u000b\u0010\n\u0082\u0002\u0004\n\u0002\b!¨\u0006\f"},
      d2 = {"Lkotlin/UByte$Companion;", "", "<init>", "()V", "Lkotlin/UByte;", "MAX_VALUE", "B", "MIN_VALUE", "", "SIZE_BITS", "I", "SIZE_BYTES", "kotlin-stdlib"}
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
