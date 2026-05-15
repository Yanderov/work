package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.WasExperimental;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000B\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0019\u0010\u0005\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0007¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0019\u0010\u0005\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00020\u0000H\u0007¢\u0006\u0004\b\u0006\u0010\u0004\u001a\u0019\u0010\u0005\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u0000H\u0007¢\u0006\u0004\b\b\u0010\t\u001a\u0019\u0010\u0005\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\n0\u0000H\u0007¢\u0006\u0004\b\u000b\u0010\u0004\u001a\u0019\u0010\u000e\u001a\u00020\r*\b\u0012\u0004\u0012\u00020\u00010\fH\u0007¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0019\u0010\u0011\u001a\u00020\u0010*\b\u0012\u0004\u0012\u00020\u00020\fH\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0019\u0010\u0014\u001a\u00020\u0013*\b\u0012\u0004\u0012\u00020\u00070\fH\u0007¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0019\u0010\u0017\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020\n0\fH\u0007¢\u0006\u0004\b\u0017\u0010\u0018¨\u0006\u0019"},
   d2 = {"", "Lkotlin/UByte;", "Lkotlin/UInt;", "sumOfUByte", "(Ljava/lang/Iterable;)I", "sum", "sumOfUInt", "Lkotlin/ULong;", "sumOfULong", "(Ljava/lang/Iterable;)J", "Lkotlin/UShort;", "sumOfUShort", "", "Lkotlin/UByteArray;", "toUByteArray", "(Ljava/util/Collection;)[B", "Lkotlin/UIntArray;", "toUIntArray", "(Ljava/util/Collection;)[I", "Lkotlin/ULongArray;", "toULongArray", "(Ljava/util/Collection;)[J", "Lkotlin/UShortArray;", "toUShortArray", "(Ljava/util/Collection;)[S", "kotlin-stdlib"},
   xs = "kotlin/collections/UCollectionsKt"
)
class UCollectionsKt___UCollectionsKt {
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   @NotNull
   public static final byte[] toUByteArray(@NotNull Collection $this$toUByteArray) {
      Intrinsics.checkNotNullParameter($this$toUByteArray, "<this>");
      byte[] result = UByteArray.constructor-impl($this$toUByteArray.size());
      int index = 0;
      Iterator var3 = $this$toUByteArray.iterator();

      while(var3.hasNext()) {
         byte element = ((UByte)var3.next()).unbox-impl();
         UByteArray.set-VurrAj0(result, index++, element);
      }

      return result;
   }

   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   @NotNull
   public static final int[] toUIntArray(@NotNull Collection $this$toUIntArray) {
      Intrinsics.checkNotNullParameter($this$toUIntArray, "<this>");
      int[] result = UIntArray.constructor-impl($this$toUIntArray.size());
      int index = 0;
      Iterator var3 = $this$toUIntArray.iterator();

      while(var3.hasNext()) {
         int element = ((UInt)var3.next()).unbox-impl();
         UIntArray.set-VXSXFK8(result, index++, element);
      }

      return result;
   }

   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   @NotNull
   public static final long[] toULongArray(@NotNull Collection $this$toULongArray) {
      Intrinsics.checkNotNullParameter($this$toULongArray, "<this>");
      long[] result = ULongArray.constructor-impl($this$toULongArray.size());
      int index = 0;
      Iterator var3 = $this$toULongArray.iterator();

      while(var3.hasNext()) {
         long element = ((ULong)var3.next()).unbox-impl();
         ULongArray.set-k8EXiF4(result, index++, element);
      }

      return result;
   }

   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   @NotNull
   public static final short[] toUShortArray(@NotNull Collection $this$toUShortArray) {
      Intrinsics.checkNotNullParameter($this$toUShortArray, "<this>");
      short[] result = UShortArray.constructor-impl($this$toUShortArray.size());
      int index = 0;
      Iterator var3 = $this$toUShortArray.iterator();

      while(var3.hasNext()) {
         short element = ((UShort)var3.next()).unbox-impl();
         UShortArray.set-01HTLdE(result, index++, element);
      }

      return result;
   }

   @JvmName(
      name = "sumOfUInt"
   )
   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   public static final int sumOfUInt(@NotNull Iterable $this$sum) {
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
   public static final long sumOfULong(@NotNull Iterable $this$sum) {
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
   public static final int sumOfUByte(@NotNull Iterable $this$sum) {
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
   public static final int sumOfUShort(@NotNull Iterable $this$sum) {
      Intrinsics.checkNotNullParameter($this$sum, "<this>");
      int sum = 0;

      short element;
      for(Iterator var2 = $this$sum.iterator(); var2.hasNext(); sum = UInt.constructor-impl(sum + UInt.constructor-impl(element & '\uffff'))) {
         element = ((UShort)var2.next()).unbox-impl();
      }

      return sum;
   }

   public UCollectionsKt___UCollectionsKt() {
   }
}
