package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.internal.IntrinsicConstEvaluation;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.ULongRange;
import kotlin.ranges.URangesKt;
import org.jetbrains.annotations.NotNull;

@JvmInline
@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0087@\u0018\u0000 \u007f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u007fB\u0011\b\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\t\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\nH\u0087\n¢\u0006\u0004\b\f\u0010\rJ\u0018\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\n¢\u0006\u0004\b\u0010\u0010\u0011J\u0018\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0000H\u0097\n¢\u0006\u0004\b\u0012\u0010\u0013J\u0018\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0014H\u0087\n¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0018\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0005J\u0018\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\nH\u0087\n¢\u0006\u0004\b\u0019\u0010\u001aJ\u0018\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\n¢\u0006\u0004\b\u001c\u0010\u001dJ\u0018\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b\u001e\u0010\bJ\u0018\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0014H\u0087\n¢\u0006\u0004\b\u001f\u0010 J\u001a\u0010%\u001a\u00020\"2\b\u0010\u0006\u001a\u0004\u0018\u00010!HÖ\u0003¢\u0006\u0004\b#\u0010$J\u0018\u0010'\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\nH\u0087\b¢\u0006\u0004\b&\u0010\u001aJ\u0018\u0010'\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\b¢\u0006\u0004\b(\u0010\u001dJ\u0018\u0010'\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b)\u0010\bJ\u0018\u0010'\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0014H\u0087\b¢\u0006\u0004\b*\u0010 J\u0010\u0010-\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b+\u0010,J\u0013\u0010/\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b.\u0010\u0005J\u0013\u00101\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b0\u0010\u0005J\u0018\u00103\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\nH\u0087\n¢\u0006\u0004\b2\u0010\u001aJ\u0018\u00103\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\n¢\u0006\u0004\b4\u0010\u001dJ\u0018\u00103\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b5\u0010\bJ\u0018\u00103\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0014H\u0087\n¢\u0006\u0004\b6\u0010 J\u0018\u00109\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\nH\u0087\b¢\u0006\u0004\b7\u00108J\u0018\u00109\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\b¢\u0006\u0004\b:\u0010\u0011J\u0018\u00109\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b;\u0010\bJ\u0018\u00109\u001a\u00020\u00142\u0006\u0010\u0006\u001a\u00020\u0014H\u0087\b¢\u0006\u0004\b<\u0010=J\u0018\u0010?\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\b>\u0010\bJ\u0018\u0010A\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\nH\u0087\n¢\u0006\u0004\b@\u0010\u001aJ\u0018\u0010A\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\n¢\u0006\u0004\bB\u0010\u001dJ\u0018\u0010A\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bC\u0010\bJ\u0018\u0010A\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0014H\u0087\n¢\u0006\u0004\bD\u0010 J\u0018\u0010H\u001a\u00020E2\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bF\u0010GJ\u0018\u0010J\u001a\u00020E2\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bI\u0010GJ\u0018\u0010L\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\nH\u0087\n¢\u0006\u0004\bK\u0010\u001aJ\u0018\u0010L\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\n¢\u0006\u0004\bM\u0010\u001dJ\u0018\u0010L\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bN\u0010\bJ\u0018\u0010L\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0014H\u0087\n¢\u0006\u0004\bO\u0010 J\u001b\u0010R\u001a\u00020\u00002\u0006\u0010P\u001a\u00020\u000bH\u0087\fø\u0001\u0000¢\u0006\u0004\bQ\u0010\u001dJ\u001b\u0010T\u001a\u00020\u00002\u0006\u0010P\u001a\u00020\u000bH\u0087\fø\u0001\u0000¢\u0006\u0004\bS\u0010\u001dJ\u0018\u0010V\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\nH\u0087\n¢\u0006\u0004\bU\u0010\u001aJ\u0018\u0010V\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\n¢\u0006\u0004\bW\u0010\u001dJ\u0018\u0010V\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bX\u0010\bJ\u0018\u0010V\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0014H\u0087\n¢\u0006\u0004\bY\u0010 J\u0010\u0010]\u001a\u00020ZH\u0087\b¢\u0006\u0004\b[\u0010\\J\u0010\u0010a\u001a\u00020^H\u0087\b¢\u0006\u0004\b_\u0010`J\u0010\u0010e\u001a\u00020bH\u0087\b¢\u0006\u0004\bc\u0010dJ\u0010\u0010g\u001a\u00020\u000bH\u0087\b¢\u0006\u0004\bf\u0010,J\u0010\u0010i\u001a\u00020\u0002H\u0087\b¢\u0006\u0004\bh\u0010\u0005J\u0010\u0010m\u001a\u00020jH\u0087\b¢\u0006\u0004\bk\u0010lJ\u000f\u0010q\u001a\u00020nH\u0016¢\u0006\u0004\bo\u0010pJ\u0013\u0010s\u001a\u00020\nH\u0087\bø\u0001\u0000¢\u0006\u0004\br\u0010\\J\u0013\u0010u\u001a\u00020\u000fH\u0087\bø\u0001\u0000¢\u0006\u0004\bt\u0010,J\u0013\u0010w\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\bv\u0010\u0005J\u0013\u0010y\u001a\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\bx\u0010lJ\u0018\u0010{\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\bz\u0010\bR\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0081\u0004¢\u0006\f\n\u0004\b\u0003\u0010|\u0012\u0004\b}\u0010~\u0088\u0001\u0003\u0092\u0001\u00020\u0002\u0082\u0002\u0004\n\u0002\b!¨\u0006\u0080\u0001"},
   d2 = {"Lkotlin/ULong;", "", "", "data", "constructor-impl", "(J)J", "other", "and-VKZWuLQ", "(JJ)J", "and", "Lkotlin/UByte;", "", "compareTo-7apg3OU", "(JB)I", "compareTo", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(JI)I", "compareTo-VKZWuLQ", "(JJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(JS)I", "dec-s-VKNKU", "dec", "div-7apg3OU", "(JB)J", "div", "div-WZ4Q5Ns", "(JI)J", "div-VKZWuLQ", "div-xj2QHRw", "(JS)J", "", "", "equals-impl", "(JLjava/lang/Object;)Z", "equals", "floorDiv-7apg3OU", "floorDiv", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode-impl", "(J)I", "hashCode", "inc-s-VKNKU", "inc", "inv-s-VKNKU", "inv", "minus-7apg3OU", "minus", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod-7apg3OU", "(JB)B", "mod", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(JS)S", "or-VKZWuLQ", "or", "plus-7apg3OU", "plus", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "Lkotlin/ranges/ULongRange;", "rangeTo-VKZWuLQ", "(JJ)Lkotlin/ranges/ULongRange;", "rangeTo", "rangeUntil-VKZWuLQ", "rangeUntil", "rem-7apg3OU", "rem", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "bitCount", "shl-s-VKNKU", "shl", "shr-s-VKNKU", "shr", "times-7apg3OU", "times", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "", "toByte-impl", "(J)B", "toByte", "", "toDouble-impl", "(J)D", "toDouble", "", "toFloat-impl", "(J)F", "toFloat", "toInt-impl", "toInt", "toLong-impl", "toLong", "", "toShort-impl", "(J)S", "toShort", "", "toString-impl", "(J)Ljava/lang/String;", "toString", "toUByte-w2LRezQ", "toUByte", "toUInt-pVg5ArA", "toUInt", "toULong-s-VKNKU", "toULong", "toUShort-Mh2AYeg", "toUShort", "xor-VKZWuLQ", "xor", "J", "getData$annotations", "()V", "Companion", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.5"
)
@WasExperimental(
   markerClass = {ExperimentalUnsignedTypes.class}
)
public final class ULong implements Comparable {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private final long data;
   public static final long MIN_VALUE = 0L;
   public static final long MAX_VALUE = -1L;
   public static final int SIZE_BYTES = 8;
   public static final int SIZE_BITS = 64;

   /** @deprecated */
   // $FF: synthetic method
   @PublishedApi
   public static void getData$annotations() {
   }

   @InlineOnly
   private static final int compareTo_7apg3OU/* $FF was: compareTo-7apg3OU*/(long arg0, byte other) {
      return Long.compareUnsigned(arg0, constructor-impl((long)other & 255L));
   }

   @InlineOnly
   private static final int compareTo_xj2QHRw/* $FF was: compareTo-xj2QHRw*/(long arg0, short other) {
      return Long.compareUnsigned(arg0, constructor-impl((long)other & 65535L));
   }

   @InlineOnly
   private static final int compareTo_WZ4Q5Ns/* $FF was: compareTo-WZ4Q5Ns*/(long arg0, int other) {
      return Long.compareUnsigned(arg0, constructor-impl((long)other & 4294967295L));
   }

   @InlineOnly
   private static int compareTo_VKZWuLQ/* $FF was: compareTo-VKZWuLQ*/(long arg0, long other) {
      return UnsignedKt.ulongCompare(arg0, other);
   }

   @InlineOnly
   private int compareTo_VKZWuLQ/* $FF was: compareTo-VKZWuLQ*/(long other) {
      return UnsignedKt.ulongCompare(this.unbox-impl(), other);
   }

   @InlineOnly
   private static final long plus_7apg3OU/* $FF was: plus-7apg3OU*/(long arg0, byte other) {
      return constructor-impl(arg0 + constructor-impl((long)other & 255L));
   }

   @InlineOnly
   private static final long plus_xj2QHRw/* $FF was: plus-xj2QHRw*/(long arg0, short other) {
      return constructor-impl(arg0 + constructor-impl((long)other & 65535L));
   }

   @InlineOnly
   private static final long plus_WZ4Q5Ns/* $FF was: plus-WZ4Q5Ns*/(long arg0, int other) {
      return constructor-impl(arg0 + constructor-impl((long)other & 4294967295L));
   }

   @InlineOnly
   private static final long plus_VKZWuLQ/* $FF was: plus-VKZWuLQ*/(long arg0, long other) {
      return constructor-impl(arg0 + other);
   }

   @InlineOnly
   private static final long minus_7apg3OU/* $FF was: minus-7apg3OU*/(long arg0, byte other) {
      return constructor-impl(arg0 - constructor-impl((long)other & 255L));
   }

   @InlineOnly
   private static final long minus_xj2QHRw/* $FF was: minus-xj2QHRw*/(long arg0, short other) {
      return constructor-impl(arg0 - constructor-impl((long)other & 65535L));
   }

   @InlineOnly
   private static final long minus_WZ4Q5Ns/* $FF was: minus-WZ4Q5Ns*/(long arg0, int other) {
      return constructor-impl(arg0 - constructor-impl((long)other & 4294967295L));
   }

   @InlineOnly
   private static final long minus_VKZWuLQ/* $FF was: minus-VKZWuLQ*/(long arg0, long other) {
      return constructor-impl(arg0 - other);
   }

   @InlineOnly
   private static final long times_7apg3OU/* $FF was: times-7apg3OU*/(long arg0, byte other) {
      return constructor-impl(arg0 * constructor-impl((long)other & 255L));
   }

   @InlineOnly
   private static final long times_xj2QHRw/* $FF was: times-xj2QHRw*/(long arg0, short other) {
      return constructor-impl(arg0 * constructor-impl((long)other & 65535L));
   }

   @InlineOnly
   private static final long times_WZ4Q5Ns/* $FF was: times-WZ4Q5Ns*/(long arg0, int other) {
      return constructor-impl(arg0 * constructor-impl((long)other & 4294967295L));
   }

   @InlineOnly
   private static final long times_VKZWuLQ/* $FF was: times-VKZWuLQ*/(long arg0, long other) {
      return constructor-impl(arg0 * other);
   }

   @InlineOnly
   private static final long div_7apg3OU/* $FF was: div-7apg3OU*/(long arg0, byte other) {
      return Long.divideUnsigned(arg0, constructor-impl((long)other & 255L));
   }

   @InlineOnly
   private static final long div_xj2QHRw/* $FF was: div-xj2QHRw*/(long arg0, short other) {
      return Long.divideUnsigned(arg0, constructor-impl((long)other & 65535L));
   }

   @InlineOnly
   private static final long div_WZ4Q5Ns/* $FF was: div-WZ4Q5Ns*/(long arg0, int other) {
      return Long.divideUnsigned(arg0, constructor-impl((long)other & 4294967295L));
   }

   @InlineOnly
   private static final long div_VKZWuLQ/* $FF was: div-VKZWuLQ*/(long arg0, long other) {
      return UnsignedKt.ulongDivide-eb3DHEI(arg0, other);
   }

   @InlineOnly
   private static final long rem_7apg3OU/* $FF was: rem-7apg3OU*/(long arg0, byte other) {
      return Long.remainderUnsigned(arg0, constructor-impl((long)other & 255L));
   }

   @InlineOnly
   private static final long rem_xj2QHRw/* $FF was: rem-xj2QHRw*/(long arg0, short other) {
      return Long.remainderUnsigned(arg0, constructor-impl((long)other & 65535L));
   }

   @InlineOnly
   private static final long rem_WZ4Q5Ns/* $FF was: rem-WZ4Q5Ns*/(long arg0, int other) {
      return Long.remainderUnsigned(arg0, constructor-impl((long)other & 4294967295L));
   }

   @InlineOnly
   private static final long rem_VKZWuLQ/* $FF was: rem-VKZWuLQ*/(long arg0, long other) {
      return UnsignedKt.ulongRemainder-eb3DHEI(arg0, other);
   }

   @InlineOnly
   private static final long floorDiv_7apg3OU/* $FF was: floorDiv-7apg3OU*/(long arg0, byte other) {
      return Long.divideUnsigned(arg0, constructor-impl((long)other & 255L));
   }

   @InlineOnly
   private static final long floorDiv_xj2QHRw/* $FF was: floorDiv-xj2QHRw*/(long arg0, short other) {
      return Long.divideUnsigned(arg0, constructor-impl((long)other & 65535L));
   }

   @InlineOnly
   private static final long floorDiv_WZ4Q5Ns/* $FF was: floorDiv-WZ4Q5Ns*/(long arg0, int other) {
      return Long.divideUnsigned(arg0, constructor-impl((long)other & 4294967295L));
   }

   @InlineOnly
   private static final long floorDiv_VKZWuLQ/* $FF was: floorDiv-VKZWuLQ*/(long arg0, long other) {
      return Long.divideUnsigned(arg0, other);
   }

   @InlineOnly
   private static final byte mod_7apg3OU/* $FF was: mod-7apg3OU*/(long arg0, byte other) {
      return UByte.constructor-impl((byte)((int)Long.remainderUnsigned(arg0, constructor-impl((long)other & 255L))));
   }

   @InlineOnly
   private static final short mod_xj2QHRw/* $FF was: mod-xj2QHRw*/(long arg0, short other) {
      return UShort.constructor-impl((short)((int)Long.remainderUnsigned(arg0, constructor-impl((long)other & 65535L))));
   }

   @InlineOnly
   private static final int mod_WZ4Q5Ns/* $FF was: mod-WZ4Q5Ns*/(long arg0, int other) {
      return UInt.constructor-impl((int)Long.remainderUnsigned(arg0, constructor-impl((long)other & 4294967295L)));
   }

   @InlineOnly
   private static final long mod_VKZWuLQ/* $FF was: mod-VKZWuLQ*/(long arg0, long other) {
      return Long.remainderUnsigned(arg0, other);
   }

   @InlineOnly
   private static final long inc_s_VKNKU/* $FF was: inc-s-VKNKU*/(long arg0) {
      return constructor-impl(arg0 + 1L);
   }

   @InlineOnly
   private static final long dec_s_VKNKU/* $FF was: dec-s-VKNKU*/(long arg0) {
      return constructor-impl(arg0 + -1L);
   }

   @InlineOnly
   private static final ULongRange rangeTo_VKZWuLQ/* $FF was: rangeTo-VKZWuLQ*/(long arg0, long other) {
      return new ULongRange(arg0, other, (DefaultConstructorMarker)null);
   }

   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final ULongRange rangeUntil_VKZWuLQ/* $FF was: rangeUntil-VKZWuLQ*/(long arg0, long other) {
      return URangesKt.until-eb3DHEI(arg0, other);
   }

   @InlineOnly
   private static final long shl_s_VKNKU/* $FF was: shl-s-VKNKU*/(long arg0, int bitCount) {
      return constructor-impl(arg0 << bitCount);
   }

   @InlineOnly
   private static final long shr_s_VKNKU/* $FF was: shr-s-VKNKU*/(long arg0, int bitCount) {
      return constructor-impl(arg0 >>> bitCount);
   }

   @InlineOnly
   private static final long and_VKZWuLQ/* $FF was: and-VKZWuLQ*/(long arg0, long other) {
      return constructor-impl(arg0 & other);
   }

   @InlineOnly
   private static final long or_VKZWuLQ/* $FF was: or-VKZWuLQ*/(long arg0, long other) {
      return constructor-impl(arg0 | other);
   }

   @InlineOnly
   private static final long xor_VKZWuLQ/* $FF was: xor-VKZWuLQ*/(long arg0, long other) {
      return constructor-impl(arg0 ^ other);
   }

   @InlineOnly
   private static final long inv_s_VKNKU/* $FF was: inv-s-VKNKU*/(long arg0) {
      return constructor-impl(~arg0);
   }

   @InlineOnly
   private static final byte toByte_impl/* $FF was: toByte-impl*/(long arg0) {
      return (byte)((int)arg0);
   }

   @InlineOnly
   private static final short toShort_impl/* $FF was: toShort-impl*/(long arg0) {
      return (short)((int)arg0);
   }

   @InlineOnly
   private static final int toInt_impl/* $FF was: toInt-impl*/(long arg0) {
      return (int)arg0;
   }

   @InlineOnly
   private static final long toLong_impl/* $FF was: toLong-impl*/(long arg0) {
      return arg0;
   }

   @InlineOnly
   private static final byte toUByte_w2LRezQ/* $FF was: toUByte-w2LRezQ*/(long arg0) {
      return UByte.constructor-impl((byte)((int)arg0));
   }

   @InlineOnly
   private static final short toUShort_Mh2AYeg/* $FF was: toUShort-Mh2AYeg*/(long arg0) {
      return UShort.constructor-impl((short)((int)arg0));
   }

   @InlineOnly
   private static final int toUInt_pVg5ArA/* $FF was: toUInt-pVg5ArA*/(long arg0) {
      return UInt.constructor-impl((int)arg0);
   }

   @InlineOnly
   private static final long toULong_s_VKNKU/* $FF was: toULong-s-VKNKU*/(long arg0) {
      return arg0;
   }

   @InlineOnly
   private static final float toFloat_impl/* $FF was: toFloat-impl*/(long arg0) {
      return (float)UnsignedKt.ulongToDouble(arg0);
   }

   @InlineOnly
   private static final double toDouble_impl/* $FF was: toDouble-impl*/(long arg0) {
      return UnsignedKt.ulongToDouble(arg0);
   }

   @NotNull
   public static String toString_impl/* $FF was: toString-impl*/(long arg0) {
      return UnsignedKt.ulongToString(arg0, 10);
   }

   @NotNull
   public String toString() {
      return toString-impl(this.data);
   }

   public static int hashCode_impl/* $FF was: hashCode-impl*/(long arg0) {
      return Long.hashCode(arg0);
   }

   public int hashCode() {
      return hashCode-impl(this.data);
   }

   public static boolean equals_impl/* $FF was: equals-impl*/(long arg0, Object other) {
      if (!(other instanceof ULong)) {
         return false;
      } else {
         long var3 = ((ULong)other).unbox-impl();
         return arg0 == var3;
      }
   }

   public boolean equals(Object other) {
      return equals-impl(this.data, other);
   }

   // $FF: synthetic method
   @IntrinsicConstEvaluation
   @PublishedApi
   private ULong(long data) {
      this.data = data;
   }

   @IntrinsicConstEvaluation
   @PublishedApi
   public static long constructor_impl/* $FF was: constructor-impl*/(long data) {
      return data;
   }

   // $FF: synthetic method
   public static final ULong box_impl/* $FF was: box-impl*/(long v) {
      return new ULong(v);
   }

   // $FF: synthetic method
   public final long unbox_impl/* $FF was: unbox-impl*/() {
      return this.data;
   }

   public static final boolean equals_impl0/* $FF was: equals-impl0*/(long p1, long p2) {
      return p1 == p2;
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006X\u0086Tø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\u00020\u00048\u0006X\u0086Tø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\t\u001a\u00020\b8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\b8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u000b\u0010\n\u0082\u0002\u0004\n\u0002\b!¨\u0006\f"},
      d2 = {"Lkotlin/ULong$Companion;", "", "<init>", "()V", "Lkotlin/ULong;", "MAX_VALUE", "J", "MIN_VALUE", "", "SIZE_BITS", "I", "SIZE_BYTES", "kotlin-stdlib"}
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
