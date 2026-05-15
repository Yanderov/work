package kotlin.sequences;

import java.util.Iterator;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.WasExperimental;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0019\u0010\u0005\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0007¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0019\u0010\u0005\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00020\u0000H\u0007¢\u0006\u0004\b\u0006\u0010\u0004\u001a\u0019\u0010\u0005\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u0000H\u0007¢\u0006\u0004\b\b\u0010\t\u001a\u0019\u0010\u0005\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\n0\u0000H\u0007¢\u0006\u0004\b\u000b\u0010\u0004¨\u0006\f"},
   d2 = {"Lkotlin/sequences/Sequence;", "Lkotlin/UByte;", "Lkotlin/UInt;", "sumOfUByte", "(Lkotlin/sequences/Sequence;)I", "sum", "sumOfUInt", "Lkotlin/ULong;", "sumOfULong", "(Lkotlin/sequences/Sequence;)J", "Lkotlin/UShort;", "sumOfUShort", "kotlin-stdlib"},
   xs = "kotlin/sequences/USequencesKt"
)
class USequencesKt___USequencesKt {
   @JvmName(
      name = "sumOfUInt"
   )
   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final int sumOfUInt(@NotNull Sequence $this$sum) {
      Intrinsics.checkNotNullParameter($this$sum, "<this>");
      int sum = 0;

      int element;
      for(Iterator var2 = $this$sum.iterator(); var2.hasNext(); sum = UInt.constructor-impl(sum + element)) {
         element = ((UInt)var2.next()).unbox-impl();
      }

      return sum;
   }

   @JvmName(
      name = "sumOfULong"
   )
   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final long sumOfULong(@NotNull Sequence $this$sum) {
      Intrinsics.checkNotNullParameter($this$sum, "<this>");
      long sum = 0L;

      long element;
      for(Iterator var3 = $this$sum.iterator(); var3.hasNext(); sum = ULong.constructor-impl(sum + element)) {
         element = ((ULong)var3.next()).unbox-impl();
      }

      return sum;
   }

   @JvmName(
      name = "sumOfUByte"
   )
   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final int sumOfUByte(@NotNull Sequence $this$sum) {
      Intrinsics.checkNotNullParameter($this$sum, "<this>");
      int sum = 0;

      byte element;
      for(Iterator var2 = $this$sum.iterator(); var2.hasNext(); sum = UInt.constructor-impl(sum + UInt.constructor-impl(element & 255))) {
         element = ((UByte)var2.next()).unbox-impl();
      }

      return sum;
   }

   @JvmName(
      name = "sumOfUShort"
   )
   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final int sumOfUShort(@NotNull Sequence $this$sum) {
      Intrinsics.checkNotNullParameter($this$sum, "<this>");
      int sum = 0;

      short element;
      for(Iterator var2 = $this$sum.iterator(); var2.hasNext(); sum = UInt.constructor-impl(sum + UInt.constructor-impl(element & '\uffff'))) {
         element = ((UShort)var2.next()).unbox-impl();
      }

      return sum;
   }

   public USequencesKt___USequencesKt() {
   }
}
