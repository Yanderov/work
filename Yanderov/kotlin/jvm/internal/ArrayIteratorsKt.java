package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.collections.BooleanIterator;
import kotlin.collections.ByteIterator;
import kotlin.collections.CharIterator;
import kotlin.collections.DoubleIterator;
import kotlin.collections.FloatIterator;
import kotlin.collections.IntIterator;
import kotlin.collections.LongIterator;
import kotlin.collections.ShortIterator;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000V\n\u0002\u0010\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0017\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0015\u0010\u0003\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u0005¢\u0006\u0004\b\u0003\u0010\u0007\u001a\u0015\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\b¢\u0006\u0004\b\u0003\u0010\n\u001a\u0015\u0010\u0003\u001a\u00020\f2\u0006\u0010\u0001\u001a\u00020\u000b¢\u0006\u0004\b\u0003\u0010\r\u001a\u0015\u0010\u0003\u001a\u00020\u000f2\u0006\u0010\u0001\u001a\u00020\u000e¢\u0006\u0004\b\u0003\u0010\u0010\u001a\u0015\u0010\u0003\u001a\u00020\u00122\u0006\u0010\u0001\u001a\u00020\u0011¢\u0006\u0004\b\u0003\u0010\u0013\u001a\u0015\u0010\u0003\u001a\u00020\u00152\u0006\u0010\u0001\u001a\u00020\u0014¢\u0006\u0004\b\u0003\u0010\u0016\u001a\u0015\u0010\u0003\u001a\u00020\u00182\u0006\u0010\u0001\u001a\u00020\u0017¢\u0006\u0004\b\u0003\u0010\u0019¨\u0006\u001a"},
   d2 = {"", "array", "Lkotlin/collections/BooleanIterator;", "iterator", "([Z)Lkotlin/collections/BooleanIterator;", "", "Lkotlin/collections/ByteIterator;", "([B)Lkotlin/collections/ByteIterator;", "", "Lkotlin/collections/CharIterator;", "([C)Lkotlin/collections/CharIterator;", "", "Lkotlin/collections/DoubleIterator;", "([D)Lkotlin/collections/DoubleIterator;", "", "Lkotlin/collections/FloatIterator;", "([F)Lkotlin/collections/FloatIterator;", "", "Lkotlin/collections/IntIterator;", "([I)Lkotlin/collections/IntIterator;", "", "Lkotlin/collections/LongIterator;", "([J)Lkotlin/collections/LongIterator;", "", "Lkotlin/collections/ShortIterator;", "([S)Lkotlin/collections/ShortIterator;", "kotlin-stdlib"}
)
public final class ArrayIteratorsKt {
   @NotNull
   public static final ByteIterator iterator(@NotNull byte[] array) {
      Intrinsics.checkNotNullParameter(array, "array");
      return new ArrayByteIterator(array);
   }

   @NotNull
   public static final CharIterator iterator(@NotNull char[] array) {
      Intrinsics.checkNotNullParameter(array, "array");
      return new ArrayCharIterator(array);
   }

   @NotNull
   public static final ShortIterator iterator(@NotNull short[] array) {
      Intrinsics.checkNotNullParameter(array, "array");
      return new ArrayShortIterator(array);
   }

   @NotNull
   public static final IntIterator iterator(@NotNull int[] array) {
      Intrinsics.checkNotNullParameter(array, "array");
      return new ArrayIntIterator(array);
   }

   @NotNull
   public static final LongIterator iterator(@NotNull long[] array) {
      Intrinsics.checkNotNullParameter(array, "array");
      return new ArrayLongIterator(array);
   }

   @NotNull
   public static final FloatIterator iterator(@NotNull float[] array) {
      Intrinsics.checkNotNullParameter(array, "array");
      return new ArrayFloatIterator(array);
   }

   @NotNull
   public static final DoubleIterator iterator(@NotNull double[] array) {
      Intrinsics.checkNotNullParameter(array, "array");
      return new ArrayDoubleIterator(array);
   }

   @NotNull
   public static final BooleanIterator iterator(@NotNull boolean[] array) {
      Intrinsics.checkNotNullParameter(array, "array");
      return new ArrayBooleanIterator(array);
   }
}
