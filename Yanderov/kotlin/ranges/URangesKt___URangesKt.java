package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.ExperimentalStdlibApi;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.random.URandomKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000b\u001a\u001b\u0010\u0004\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u001b\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001b\u0010\u0004\u001a\u00020\b*\u00020\b2\u0006\u0010\u0001\u001a\u00020\bH\u0007¢\u0006\u0004\b\t\u0010\n\u001a\u001b\u0010\u0004\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0001\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\f\u0010\r\u001a\u001b\u0010\u0010\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u000f\u0010\u0003\u001a\u001b\u0010\u0010\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u0011\u0010\u0007\u001a\u001b\u0010\u0010\u001a\u00020\b*\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0007¢\u0006\u0004\b\u0012\u0010\n\u001a\u001b\u0010\u0010\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u0013\u0010\r\u001a#\u0010\u0016\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0014\u0010\u0015\u001a#\u0010\u0016\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u0017\u0010\u0018\u001a!\u0010\u0016\u001a\u00020\u0005*\u00020\u00052\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u0019H\u0007¢\u0006\u0004\b\u001b\u0010\u001c\u001a#\u0010\u0016\u001a\u00020\b*\u00020\b2\u0006\u0010\u0001\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0007¢\u0006\u0004\b\u001d\u0010\u001e\u001a!\u0010\u0016\u001a\u00020\b*\u00020\b2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u0019H\u0007¢\u0006\u0004\b\u001f\u0010 \u001a#\u0010\u0016\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0001\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000bH\u0007¢\u0006\u0004\b!\u0010\"\u001a\u001c\u0010(\u001a\u00020%*\u00020#2\u0006\u0010$\u001a\u00020\u0000H\u0087\u0002¢\u0006\u0004\b&\u0010'\u001a\u001e\u0010(\u001a\u00020%*\u00020#2\b\u0010)\u001a\u0004\u0018\u00010\u0005H\u0087\n¢\u0006\u0004\b*\u0010+\u001a\u001c\u0010(\u001a\u00020%*\u00020#2\u0006\u0010$\u001a\u00020\bH\u0087\u0002¢\u0006\u0004\b,\u0010-\u001a\u001c\u0010(\u001a\u00020%*\u00020#2\u0006\u0010$\u001a\u00020\u000bH\u0087\u0002¢\u0006\u0004\b.\u0010/\u001a\u001c\u0010(\u001a\u00020%*\u0002002\u0006\u0010$\u001a\u00020\u0000H\u0087\u0002¢\u0006\u0004\b1\u00102\u001a\u001c\u0010(\u001a\u00020%*\u0002002\u0006\u0010$\u001a\u00020\u0005H\u0087\u0002¢\u0006\u0004\b3\u00104\u001a\u001e\u0010(\u001a\u00020%*\u0002002\b\u0010)\u001a\u0004\u0018\u00010\bH\u0087\n¢\u0006\u0004\b5\u00106\u001a\u001c\u0010(\u001a\u00020%*\u0002002\u0006\u0010$\u001a\u00020\u000bH\u0087\u0002¢\u0006\u0004\b7\u00108\u001a\u001c\u0010=\u001a\u00020:*\u00020\u00002\u0006\u00109\u001a\u00020\u0000H\u0087\u0004¢\u0006\u0004\b;\u0010<\u001a\u001c\u0010=\u001a\u00020:*\u00020\u00052\u0006\u00109\u001a\u00020\u0005H\u0087\u0004¢\u0006\u0004\b>\u0010?\u001a\u001c\u0010=\u001a\u00020@*\u00020\b2\u0006\u00109\u001a\u00020\bH\u0087\u0004¢\u0006\u0004\bA\u0010B\u001a\u001c\u0010=\u001a\u00020:*\u00020\u000b2\u0006\u00109\u001a\u00020\u000bH\u0087\u0004¢\u0006\u0004\bC\u0010D\u001a\u0013\u0010E\u001a\u00020\u0005*\u00020:H\u0007¢\u0006\u0004\bE\u0010F\u001a\u0013\u0010E\u001a\u00020\b*\u00020@H\u0007¢\u0006\u0004\bE\u0010G\u001a\u0015\u0010H\u001a\u0004\u0018\u00010\u0005*\u00020:H\u0007¢\u0006\u0004\bH\u0010I\u001a\u0015\u0010H\u001a\u0004\u0018\u00010\b*\u00020@H\u0007¢\u0006\u0004\bH\u0010J\u001a\u0013\u0010K\u001a\u00020\u0005*\u00020:H\u0007¢\u0006\u0004\bK\u0010F\u001a\u0013\u0010K\u001a\u00020\b*\u00020@H\u0007¢\u0006\u0004\bK\u0010G\u001a\u0015\u0010L\u001a\u0004\u0018\u00010\u0005*\u00020:H\u0007¢\u0006\u0004\bL\u0010I\u001a\u0015\u0010L\u001a\u0004\u0018\u00010\b*\u00020@H\u0007¢\u0006\u0004\bL\u0010J\u001a\u0014\u0010M\u001a\u00020\u0005*\u00020#H\u0087\b¢\u0006\u0004\bM\u0010N\u001a\u001b\u0010M\u001a\u00020\u0005*\u00020#2\u0006\u0010M\u001a\u00020OH\u0007¢\u0006\u0004\bM\u0010P\u001a\u0014\u0010M\u001a\u00020\b*\u000200H\u0087\b¢\u0006\u0004\bM\u0010Q\u001a\u001b\u0010M\u001a\u00020\b*\u0002002\u0006\u0010M\u001a\u00020OH\u0007¢\u0006\u0004\bM\u0010R\u001a\u0016\u0010S\u001a\u0004\u0018\u00010\u0005*\u00020#H\u0087\b¢\u0006\u0004\bS\u0010T\u001a\u001d\u0010S\u001a\u0004\u0018\u00010\u0005*\u00020#2\u0006\u0010M\u001a\u00020OH\u0007¢\u0006\u0004\bS\u0010U\u001a\u0016\u0010S\u001a\u0004\u0018\u00010\b*\u000200H\u0087\b¢\u0006\u0004\bS\u0010V\u001a\u001d\u0010S\u001a\u0004\u0018\u00010\b*\u0002002\u0006\u0010M\u001a\u00020OH\u0007¢\u0006\u0004\bS\u0010W\u001a\u0013\u0010X\u001a\u00020:*\u00020:H\u0007¢\u0006\u0004\bX\u0010Y\u001a\u0013\u0010X\u001a\u00020@*\u00020@H\u0007¢\u0006\u0004\bX\u0010Z\u001a\u001c\u0010\\\u001a\u00020:*\u00020:2\u0006\u0010\\\u001a\u00020[H\u0087\u0004¢\u0006\u0004\b\\\u0010]\u001a\u001c\u0010\\\u001a\u00020@*\u00020@2\u0006\u0010\\\u001a\u00020^H\u0087\u0004¢\u0006\u0004\b\\\u0010_\u001a\u001c\u0010b\u001a\u00020#*\u00020\u00002\u0006\u00109\u001a\u00020\u0000H\u0087\u0004¢\u0006\u0004\b`\u0010a\u001a\u001c\u0010b\u001a\u00020#*\u00020\u00052\u0006\u00109\u001a\u00020\u0005H\u0087\u0004¢\u0006\u0004\bc\u0010d\u001a\u001c\u0010b\u001a\u000200*\u00020\b2\u0006\u00109\u001a\u00020\bH\u0087\u0004¢\u0006\u0004\be\u0010f\u001a\u001c\u0010b\u001a\u00020#*\u00020\u000b2\u0006\u00109\u001a\u00020\u000bH\u0087\u0004¢\u0006\u0004\bg\u0010h¨\u0006i"},
   d2 = {"Lkotlin/UByte;", "minimumValue", "coerceAtLeast-Kr8caGY", "(BB)B", "coerceAtLeast", "Lkotlin/UInt;", "coerceAtLeast-J1ME1BU", "(II)I", "Lkotlin/ULong;", "coerceAtLeast-eb3DHEI", "(JJ)J", "Lkotlin/UShort;", "coerceAtLeast-5PvTz6A", "(SS)S", "maximumValue", "coerceAtMost-Kr8caGY", "coerceAtMost", "coerceAtMost-J1ME1BU", "coerceAtMost-eb3DHEI", "coerceAtMost-5PvTz6A", "coerceIn-b33U2AM", "(BBB)B", "coerceIn", "coerceIn-WZ9TVnA", "(III)I", "Lkotlin/ranges/ClosedRange;", "range", "coerceIn-wuiCnnA", "(ILkotlin/ranges/ClosedRange;)I", "coerceIn-sambcqE", "(JJJ)J", "coerceIn-JPwROB0", "(JLkotlin/ranges/ClosedRange;)J", "coerceIn-VKSA0NQ", "(SSS)S", "Lkotlin/ranges/UIntRange;", "value", "", "contains-68kG9v0", "(Lkotlin/ranges/UIntRange;B)Z", "contains", "element", "contains-biwQdVI", "(Lkotlin/ranges/UIntRange;Lkotlin/UInt;)Z", "contains-fz5IDCE", "(Lkotlin/ranges/UIntRange;J)Z", "contains-ZsK3CEQ", "(Lkotlin/ranges/UIntRange;S)Z", "Lkotlin/ranges/ULongRange;", "contains-ULb-yJY", "(Lkotlin/ranges/ULongRange;B)Z", "contains-Gab390E", "(Lkotlin/ranges/ULongRange;I)Z", "contains-GYNo2lE", "(Lkotlin/ranges/ULongRange;Lkotlin/ULong;)Z", "contains-uhHAxoY", "(Lkotlin/ranges/ULongRange;S)Z", "to", "Lkotlin/ranges/UIntProgression;", "downTo-Kr8caGY", "(BB)Lkotlin/ranges/UIntProgression;", "downTo", "downTo-J1ME1BU", "(II)Lkotlin/ranges/UIntProgression;", "Lkotlin/ranges/ULongProgression;", "downTo-eb3DHEI", "(JJ)Lkotlin/ranges/ULongProgression;", "downTo-5PvTz6A", "(SS)Lkotlin/ranges/UIntProgression;", "first", "(Lkotlin/ranges/UIntProgression;)I", "(Lkotlin/ranges/ULongProgression;)J", "firstOrNull", "(Lkotlin/ranges/UIntProgression;)Lkotlin/UInt;", "(Lkotlin/ranges/ULongProgression;)Lkotlin/ULong;", "last", "lastOrNull", "random", "(Lkotlin/ranges/UIntRange;)I", "Lkotlin/random/Random;", "(Lkotlin/ranges/UIntRange;Lkotlin/random/Random;)I", "(Lkotlin/ranges/ULongRange;)J", "(Lkotlin/ranges/ULongRange;Lkotlin/random/Random;)J", "randomOrNull", "(Lkotlin/ranges/UIntRange;)Lkotlin/UInt;", "(Lkotlin/ranges/UIntRange;Lkotlin/random/Random;)Lkotlin/UInt;", "(Lkotlin/ranges/ULongRange;)Lkotlin/ULong;", "(Lkotlin/ranges/ULongRange;Lkotlin/random/Random;)Lkotlin/ULong;", "reversed", "(Lkotlin/ranges/UIntProgression;)Lkotlin/ranges/UIntProgression;", "(Lkotlin/ranges/ULongProgression;)Lkotlin/ranges/ULongProgression;", "", "step", "(Lkotlin/ranges/UIntProgression;I)Lkotlin/ranges/UIntProgression;", "", "(Lkotlin/ranges/ULongProgression;J)Lkotlin/ranges/ULongProgression;", "until-Kr8caGY", "(BB)Lkotlin/ranges/UIntRange;", "until", "until-J1ME1BU", "(II)Lkotlin/ranges/UIntRange;", "until-eb3DHEI", "(JJ)Lkotlin/ranges/ULongRange;", "until-5PvTz6A", "(SS)Lkotlin/ranges/UIntRange;", "kotlin-stdlib"},
   xs = "kotlin/ranges/URangesKt"
)
class URangesKt___URangesKt {
   @SinceKotlin(
      version = "1.7"
   )
   public static final int first(@NotNull UIntProgression $this$first) {
      Intrinsics.checkNotNullParameter($this$first, "<this>");
      if ($this$first.isEmpty()) {
         throw new NoSuchElementException("Progression " + $this$first + " is empty.");
      } else {
         return $this$first.getFirst-pVg5ArA();
      }
   }

   @SinceKotlin(
      version = "1.7"
   )
   public static final long first(@NotNull ULongProgression $this$first) {
      Intrinsics.checkNotNullParameter($this$first, "<this>");
      if ($this$first.isEmpty()) {
         throw new NoSuchElementException("Progression " + $this$first + " is empty.");
      } else {
         return $this$first.getFirst-s-VKNKU();
      }
   }

   @SinceKotlin(
      version = "1.7"
   )
   @Nullable
   public static final UInt firstOrNull(@NotNull UIntProgression $this$firstOrNull) {
      Intrinsics.checkNotNullParameter($this$firstOrNull, "<this>");
      return $this$firstOrNull.isEmpty() ? null : UInt.box-impl($this$firstOrNull.getFirst-pVg5ArA());
   }

   @SinceKotlin(
      version = "1.7"
   )
   @Nullable
   public static final ULong firstOrNull(@NotNull ULongProgression $this$firstOrNull) {
      Intrinsics.checkNotNullParameter($this$firstOrNull, "<this>");
      return $this$firstOrNull.isEmpty() ? null : ULong.box-impl($this$firstOrNull.getFirst-s-VKNKU());
   }

   @SinceKotlin(
      version = "1.7"
   )
   public static final int last(@NotNull UIntProgression $this$last) {
      Intrinsics.checkNotNullParameter($this$last, "<this>");
      if ($this$last.isEmpty()) {
         throw new NoSuchElementException("Progression " + $this$last + " is empty.");
      } else {
         return $this$last.getLast-pVg5ArA();
      }
   }

   @SinceKotlin(
      version = "1.7"
   )
   public static final long last(@NotNull ULongProgression $this$last) {
      Intrinsics.checkNotNullParameter($this$last, "<this>");
      if ($this$last.isEmpty()) {
         throw new NoSuchElementException("Progression " + $this$last + " is empty.");
      } else {
         return $this$last.getLast-s-VKNKU();
      }
   }

   @SinceKotlin(
      version = "1.7"
   )
   @Nullable
   public static final UInt lastOrNull(@NotNull UIntProgression $this$lastOrNull) {
      Intrinsics.checkNotNullParameter($this$lastOrNull, "<this>");
      return $this$lastOrNull.isEmpty() ? null : UInt.box-impl($this$lastOrNull.getLast-pVg5ArA());
   }

   @SinceKotlin(
      version = "1.7"
   )
   @Nullable
   public static final ULong lastOrNull(@NotNull ULongProgression $this$lastOrNull) {
      Intrinsics.checkNotNullParameter($this$lastOrNull, "<this>");
      return $this$lastOrNull.isEmpty() ? null : ULong.box-impl($this$lastOrNull.getLast-s-VKNKU());
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @InlineOnly
   private static final int random(UIntRange $this$random) {
      Intrinsics.checkNotNullParameter($this$random, "<this>");
      return URangesKt.random($this$random, Random.Default);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @InlineOnly
   private static final long random(ULongRange $this$random) {
      Intrinsics.checkNotNullParameter($this$random, "<this>");
      return URangesKt.random($this$random, Random.Default);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final int random(@NotNull UIntRange $this$random, @NotNull Random random) {
      Intrinsics.checkNotNullParameter($this$random, "<this>");
      Intrinsics.checkNotNullParameter(random, "random");

      try {
         return URandomKt.nextUInt(random, $this$random);
      } catch (IllegalArgumentException e) {
         throw new NoSuchElementException(e.getMessage());
      }
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final long random(@NotNull ULongRange $this$random, @NotNull Random random) {
      Intrinsics.checkNotNullParameter($this$random, "<this>");
      Intrinsics.checkNotNullParameter(random, "random");

      try {
         return URandomKt.nextULong(random, $this$random);
      } catch (IllegalArgumentException e) {
         throw new NoSuchElementException(e.getMessage());
      }
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class}
   )
   @InlineOnly
   private static final UInt randomOrNull(UIntRange $this$randomOrNull) {
      Intrinsics.checkNotNullParameter($this$randomOrNull, "<this>");
      return URangesKt.randomOrNull($this$randomOrNull, Random.Default);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class}
   )
   @InlineOnly
   private static final ULong randomOrNull(ULongRange $this$randomOrNull) {
      Intrinsics.checkNotNullParameter($this$randomOrNull, "<this>");
      return URangesKt.randomOrNull($this$randomOrNull, Random.Default);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class}
   )
   @Nullable
   public static final UInt randomOrNull(@NotNull UIntRange $this$randomOrNull, @NotNull Random random) {
      Intrinsics.checkNotNullParameter($this$randomOrNull, "<this>");
      Intrinsics.checkNotNullParameter(random, "random");
      return $this$randomOrNull.isEmpty() ? null : UInt.box-impl(URandomKt.nextUInt(random, $this$randomOrNull));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class}
   )
   @Nullable
   public static final ULong randomOrNull(@NotNull ULongRange $this$randomOrNull, @NotNull Random random) {
      Intrinsics.checkNotNullParameter($this$randomOrNull, "<this>");
      Intrinsics.checkNotNullParameter(random, "random");
      return $this$randomOrNull.isEmpty() ? null : ULong.box-impl(URandomKt.nextULong(random, $this$randomOrNull));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @InlineOnly
   private static final boolean contains_biwQdVI/* $FF was: contains-biwQdVI*/(UIntRange $this$contains_u2dbiwQdVI, UInt element) {
      Intrinsics.checkNotNullParameter($this$contains_u2dbiwQdVI, "$this$contains");
      return element != null && $this$contains_u2dbiwQdVI.contains-WZ4Q5Ns(element.unbox-impl());
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @InlineOnly
   private static final boolean contains_GYNo2lE/* $FF was: contains-GYNo2lE*/(ULongRange $this$contains_u2dGYNo2lE, ULong element) {
      Intrinsics.checkNotNullParameter($this$contains_u2dGYNo2lE, "$this$contains");
      return element != null && $this$contains_u2dGYNo2lE.contains-VKZWuLQ(element.unbox-impl());
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final boolean contains_68kG9v0/* $FF was: contains-68kG9v0*/(@NotNull UIntRange $this$contains_u2d68kG9v0, byte value) {
      Intrinsics.checkNotNullParameter($this$contains_u2d68kG9v0, "$this$contains");
      return $this$contains_u2d68kG9v0.contains-WZ4Q5Ns(UInt.constructor-impl(value & 255));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final boolean contains_ULb_yJY/* $FF was: contains-ULb-yJY*/(@NotNull ULongRange $this$contains_u2dULb_u2dyJY, byte value) {
      Intrinsics.checkNotNullParameter($this$contains_u2dULb_u2dyJY, "$this$contains");
      return $this$contains_u2dULb_u2dyJY.contains-VKZWuLQ(ULong.constructor-impl((long)value & 255L));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final boolean contains_Gab390E/* $FF was: contains-Gab390E*/(@NotNull ULongRange $this$contains_u2dGab390E, int value) {
      Intrinsics.checkNotNullParameter($this$contains_u2dGab390E, "$this$contains");
      return $this$contains_u2dGab390E.contains-VKZWuLQ(ULong.constructor-impl((long)value & 4294967295L));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final boolean contains_fz5IDCE/* $FF was: contains-fz5IDCE*/(@NotNull UIntRange $this$contains_u2dfz5IDCE, long value) {
      Intrinsics.checkNotNullParameter($this$contains_u2dfz5IDCE, "$this$contains");
      return ULong.constructor-impl(value >>> 32) == 0L && $this$contains_u2dfz5IDCE.contains-WZ4Q5Ns(UInt.constructor-impl((int)value));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final boolean contains_ZsK3CEQ/* $FF was: contains-ZsK3CEQ*/(@NotNull UIntRange $this$contains_u2dZsK3CEQ, short value) {
      Intrinsics.checkNotNullParameter($this$contains_u2dZsK3CEQ, "$this$contains");
      return $this$contains_u2dZsK3CEQ.contains-WZ4Q5Ns(UInt.constructor-impl(value & '\uffff'));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final boolean contains_uhHAxoY/* $FF was: contains-uhHAxoY*/(@NotNull ULongRange $this$contains_u2duhHAxoY, short value) {
      Intrinsics.checkNotNullParameter($this$contains_u2duhHAxoY, "$this$contains");
      return $this$contains_u2duhHAxoY.contains-VKZWuLQ(ULong.constructor-impl((long)value & 65535L));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @NotNull
   public static final UIntProgression downTo_Kr8caGY/* $FF was: downTo-Kr8caGY*/(byte $this$downTo_u2dKr8caGY, byte to) {
      return UIntProgression.Companion.fromClosedRange-Nkh28Cs(UInt.constructor-impl($this$downTo_u2dKr8caGY & 255), UInt.constructor-impl(to & 255), -1);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @NotNull
   public static final UIntProgression downTo_J1ME1BU/* $FF was: downTo-J1ME1BU*/(int $this$downTo_u2dJ1ME1BU, int to) {
      return UIntProgression.Companion.fromClosedRange-Nkh28Cs($this$downTo_u2dJ1ME1BU, to, -1);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @NotNull
   public static final ULongProgression downTo_eb3DHEI/* $FF was: downTo-eb3DHEI*/(long $this$downTo_u2deb3DHEI, long to) {
      return ULongProgression.Companion.fromClosedRange-7ftBX0g($this$downTo_u2deb3DHEI, to, -1L);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @NotNull
   public static final UIntProgression downTo_5PvTz6A/* $FF was: downTo-5PvTz6A*/(short $this$downTo_u2d5PvTz6A, short to) {
      return UIntProgression.Companion.fromClosedRange-Nkh28Cs(UInt.constructor-impl($this$downTo_u2d5PvTz6A & '\uffff'), UInt.constructor-impl(to & '\uffff'), -1);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @NotNull
   public static final UIntProgression reversed(@NotNull UIntProgression $this$reversed) {
      Intrinsics.checkNotNullParameter($this$reversed, "<this>");
      return UIntProgression.Companion.fromClosedRange-Nkh28Cs($this$reversed.getLast-pVg5ArA(), $this$reversed.getFirst-pVg5ArA(), -$this$reversed.getStep());
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @NotNull
   public static final ULongProgression reversed(@NotNull ULongProgression $this$reversed) {
      Intrinsics.checkNotNullParameter($this$reversed, "<this>");
      return ULongProgression.Companion.fromClosedRange-7ftBX0g($this$reversed.getLast-s-VKNKU(), $this$reversed.getFirst-s-VKNKU(), -$this$reversed.getStep());
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @NotNull
   public static final UIntProgression step(@NotNull UIntProgression $this$step, int step) {
      Intrinsics.checkNotNullParameter($this$step, "<this>");
      RangesKt.checkStepIsPositive(step > 0, (Number)step);
      return UIntProgression.Companion.fromClosedRange-Nkh28Cs($this$step.getFirst-pVg5ArA(), $this$step.getLast-pVg5ArA(), $this$step.getStep() > 0 ? step : -step);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @NotNull
   public static final ULongProgression step(@NotNull ULongProgression $this$step, long step) {
      Intrinsics.checkNotNullParameter($this$step, "<this>");
      RangesKt.checkStepIsPositive(step > 0L, (Number)step);
      return ULongProgression.Companion.fromClosedRange-7ftBX0g($this$step.getFirst-s-VKNKU(), $this$step.getLast-s-VKNKU(), $this$step.getStep() > 0L ? step : -step);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @NotNull
   public static final UIntRange until_Kr8caGY/* $FF was: until-Kr8caGY*/(byte $this$until_u2dKr8caGY, byte to) {
      int var2 = 0;
      if (Intrinsics.compare(to & 255, var2 & 255) <= 0) {
         return UIntRange.Companion.getEMPTY();
      } else {
         var2 = UInt.constructor-impl($this$until_u2dKr8caGY & 255);
         int var3 = 1;
         var3 = UInt.constructor-impl(UInt.constructor-impl(to & 255) - var3);
         return new UIntRange(var2, var3, (DefaultConstructorMarker)null);
      }
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @NotNull
   public static final UIntRange until_J1ME1BU/* $FF was: until-J1ME1BU*/(int $this$until_u2dJ1ME1BU, int to) {
      return Integer.compareUnsigned(to, 0) <= 0 ? UIntRange.Companion.getEMPTY() : new UIntRange($this$until_u2dJ1ME1BU, UInt.constructor-impl(to - 1), (DefaultConstructorMarker)null);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @NotNull
   public static final ULongRange until_eb3DHEI/* $FF was: until-eb3DHEI*/(long $this$until_u2deb3DHEI, long to) {
      if (Long.compareUnsigned(to, 0L) <= 0) {
         return ULongRange.Companion.getEMPTY();
      } else {
         byte var4 = 1;
         long var6 = ULong.constructor-impl(to - ULong.constructor-impl((long)var4 & 4294967295L));
         return new ULongRange($this$until_u2deb3DHEI, var6, (DefaultConstructorMarker)null);
      }
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @NotNull
   public static final UIntRange until_5PvTz6A/* $FF was: until-5PvTz6A*/(short $this$until_u2d5PvTz6A, short to) {
      int var2 = 0;
      if (Intrinsics.compare(to & '\uffff', var2 & '\uffff') <= 0) {
         return UIntRange.Companion.getEMPTY();
      } else {
         var2 = UInt.constructor-impl($this$until_u2d5PvTz6A & '\uffff');
         int var3 = 1;
         var3 = UInt.constructor-impl(UInt.constructor-impl(to & '\uffff') - var3);
         return new UIntRange(var2, var3, (DefaultConstructorMarker)null);
      }
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final int coerceAtLeast_J1ME1BU/* $FF was: coerceAtLeast-J1ME1BU*/(int $this$coerceAtLeast_u2dJ1ME1BU, int minimumValue) {
      return Integer.compareUnsigned($this$coerceAtLeast_u2dJ1ME1BU, minimumValue) < 0 ? minimumValue : $this$coerceAtLeast_u2dJ1ME1BU;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final long coerceAtLeast_eb3DHEI/* $FF was: coerceAtLeast-eb3DHEI*/(long $this$coerceAtLeast_u2deb3DHEI, long minimumValue) {
      return Long.compareUnsigned($this$coerceAtLeast_u2deb3DHEI, minimumValue) < 0 ? minimumValue : $this$coerceAtLeast_u2deb3DHEI;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final byte coerceAtLeast_Kr8caGY/* $FF was: coerceAtLeast-Kr8caGY*/(byte $this$coerceAtLeast_u2dKr8caGY, byte minimumValue) {
      return Intrinsics.compare($this$coerceAtLeast_u2dKr8caGY & 255, minimumValue & 255) < 0 ? minimumValue : $this$coerceAtLeast_u2dKr8caGY;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final short coerceAtLeast_5PvTz6A/* $FF was: coerceAtLeast-5PvTz6A*/(short $this$coerceAtLeast_u2d5PvTz6A, short minimumValue) {
      return Intrinsics.compare($this$coerceAtLeast_u2d5PvTz6A & '\uffff', minimumValue & '\uffff') < 0 ? minimumValue : $this$coerceAtLeast_u2d5PvTz6A;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final int coerceAtMost_J1ME1BU/* $FF was: coerceAtMost-J1ME1BU*/(int $this$coerceAtMost_u2dJ1ME1BU, int maximumValue) {
      return Integer.compareUnsigned($this$coerceAtMost_u2dJ1ME1BU, maximumValue) > 0 ? maximumValue : $this$coerceAtMost_u2dJ1ME1BU;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final long coerceAtMost_eb3DHEI/* $FF was: coerceAtMost-eb3DHEI*/(long $this$coerceAtMost_u2deb3DHEI, long maximumValue) {
      return Long.compareUnsigned($this$coerceAtMost_u2deb3DHEI, maximumValue) > 0 ? maximumValue : $this$coerceAtMost_u2deb3DHEI;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final byte coerceAtMost_Kr8caGY/* $FF was: coerceAtMost-Kr8caGY*/(byte $this$coerceAtMost_u2dKr8caGY, byte maximumValue) {
      return Intrinsics.compare($this$coerceAtMost_u2dKr8caGY & 255, maximumValue & 255) > 0 ? maximumValue : $this$coerceAtMost_u2dKr8caGY;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final short coerceAtMost_5PvTz6A/* $FF was: coerceAtMost-5PvTz6A*/(short $this$coerceAtMost_u2d5PvTz6A, short maximumValue) {
      return Intrinsics.compare($this$coerceAtMost_u2d5PvTz6A & '\uffff', maximumValue & '\uffff') > 0 ? maximumValue : $this$coerceAtMost_u2d5PvTz6A;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final int coerceIn_WZ9TVnA/* $FF was: coerceIn-WZ9TVnA*/(int $this$coerceIn_u2dWZ9TVnA, int minimumValue, int maximumValue) {
      if (Integer.compareUnsigned(minimumValue, maximumValue) > 0) {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UInt.toString-impl(maximumValue) + " is less than minimum " + UInt.toString-impl(minimumValue) + '.');
      } else if (Integer.compareUnsigned($this$coerceIn_u2dWZ9TVnA, minimumValue) < 0) {
         return minimumValue;
      } else {
         return Integer.compareUnsigned($this$coerceIn_u2dWZ9TVnA, maximumValue) > 0 ? maximumValue : $this$coerceIn_u2dWZ9TVnA;
      }
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final long coerceIn_sambcqE/* $FF was: coerceIn-sambcqE*/(long $this$coerceIn_u2dsambcqE, long minimumValue, long maximumValue) {
      if (Long.compareUnsigned(minimumValue, maximumValue) > 0) {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + ULong.toString-impl(maximumValue) + " is less than minimum " + ULong.toString-impl(minimumValue) + '.');
      } else if (Long.compareUnsigned($this$coerceIn_u2dsambcqE, minimumValue) < 0) {
         return minimumValue;
      } else {
         return Long.compareUnsigned($this$coerceIn_u2dsambcqE, maximumValue) > 0 ? maximumValue : $this$coerceIn_u2dsambcqE;
      }
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final byte coerceIn_b33U2AM/* $FF was: coerceIn-b33U2AM*/(byte $this$coerceIn_u2db33U2AM, byte minimumValue, byte maximumValue) {
      if (Intrinsics.compare(minimumValue & 255, maximumValue & 255) > 0) {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UByte.toString-impl(maximumValue) + " is less than minimum " + UByte.toString-impl(minimumValue) + '.');
      } else if (Intrinsics.compare($this$coerceIn_u2db33U2AM & 255, minimumValue & 255) < 0) {
         return minimumValue;
      } else {
         return Intrinsics.compare($this$coerceIn_u2db33U2AM & 255, maximumValue & 255) > 0 ? maximumValue : $this$coerceIn_u2db33U2AM;
      }
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final short coerceIn_VKSA0NQ/* $FF was: coerceIn-VKSA0NQ*/(short $this$coerceIn_u2dVKSA0NQ, short minimumValue, short maximumValue) {
      if (Intrinsics.compare(minimumValue & '\uffff', maximumValue & '\uffff') > 0) {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UShort.toString-impl(maximumValue) + " is less than minimum " + UShort.toString-impl(minimumValue) + '.');
      } else if (Intrinsics.compare($this$coerceIn_u2dVKSA0NQ & '\uffff', minimumValue & '\uffff') < 0) {
         return minimumValue;
      } else {
         return Intrinsics.compare($this$coerceIn_u2dVKSA0NQ & '\uffff', maximumValue & '\uffff') > 0 ? maximumValue : $this$coerceIn_u2dVKSA0NQ;
      }
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final int coerceIn_wuiCnnA/* $FF was: coerceIn-wuiCnnA*/(int $this$coerceIn_u2dwuiCnnA, @NotNull ClosedRange range) {
      Intrinsics.checkNotNullParameter(range, "range");
      if (range instanceof ClosedFloatingPointRange) {
         return ((UInt)RangesKt.coerceIn(UInt.box-impl($this$coerceIn_u2dwuiCnnA), (ClosedFloatingPointRange)range)).unbox-impl();
      } else if (range.isEmpty()) {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: " + range + '.');
      } else {
         return Integer.compareUnsigned($this$coerceIn_u2dwuiCnnA, ((UInt)range.getStart()).unbox-impl()) < 0 ? ((UInt)range.getStart()).unbox-impl() : (Integer.compareUnsigned($this$coerceIn_u2dwuiCnnA, ((UInt)range.getEndInclusive()).unbox-impl()) > 0 ? ((UInt)range.getEndInclusive()).unbox-impl() : $this$coerceIn_u2dwuiCnnA);
      }
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final long coerceIn_JPwROB0/* $FF was: coerceIn-JPwROB0*/(long $this$coerceIn_u2dJPwROB0, @NotNull ClosedRange range) {
      Intrinsics.checkNotNullParameter(range, "range");
      if (range instanceof ClosedFloatingPointRange) {
         return ((ULong)RangesKt.coerceIn(ULong.box-impl($this$coerceIn_u2dJPwROB0), (ClosedFloatingPointRange)range)).unbox-impl();
      } else if (range.isEmpty()) {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: " + range + '.');
      } else {
         return Long.compareUnsigned($this$coerceIn_u2dJPwROB0, ((ULong)range.getStart()).unbox-impl()) < 0 ? ((ULong)range.getStart()).unbox-impl() : (Long.compareUnsigned($this$coerceIn_u2dJPwROB0, ((ULong)range.getEndInclusive()).unbox-impl()) > 0 ? ((ULong)range.getEndInclusive()).unbox-impl() : $this$coerceIn_u2dJPwROB0);
      }
   }

   public URangesKt___URangesKt() {
   }
}
