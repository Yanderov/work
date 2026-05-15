package kotlin.collections;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0011\u001a'\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0003¢\u0006\u0004\b\u0005\u0010\u0006\u001a'\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0003¢\u0006\u0004\b\t\u0010\n\u001a'\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0003¢\u0006\u0004\b\f\u0010\r\u001a'\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0003¢\u0006\u0004\b\u000f\u0010\u0010\u001a'\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0003¢\u0006\u0004\b\u0012\u0010\u0013\u001a'\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0001\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0003¢\u0006\u0004\b\u0015\u0010\u0016\u001a'\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0001\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0003¢\u0006\u0004\b\u0017\u0010\u0018\u001a'\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0001\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0003¢\u0006\u0004\b\u0019\u0010\u001a\u001a'\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\u001d\u0010\u0013\u001a'\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0001\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\u001f\u0010\u0016\u001a'\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0001\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u0002H\u0001¢\u0006\u0004\b \u0010\u0018\u001a'\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0001\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u0002H\u0001¢\u0006\u0004\b!\u0010\u001a¨\u0006\""},
   d2 = {"Lkotlin/UByteArray;", "array", "", "left", "right", "partition-4UcCI2c", "([BII)I", "partition", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "", "quickSort-4UcCI2c", "([BII)V", "quickSort", "quickSort-oBK06Vg", "([III)V", "quickSort--nroSd4", "([JII)V", "quickSort-Aa5vz7o", "([SII)V", "fromIndex", "toIndex", "sortArray-4UcCI2c", "sortArray", "sortArray-oBK06Vg", "sortArray--nroSd4", "sortArray-Aa5vz7o", "kotlin-stdlib"}
)
public final class UArraySortingKt {
   @ExperimentalUnsignedTypes
   private static final int partition_4UcCI2c/* $FF was: partition-4UcCI2c*/(byte[] array, int left, int right) {
      int i = left;
      int j = right;
      byte pivot = UByteArray.get-w2LRezQ(array, (left + right) / 2);

      while(i <= j) {
         while(Intrinsics.compare(UByteArray.get-w2LRezQ(array, i) & 255, pivot & 255) < 0) {
            ++i;
         }

         while(Intrinsics.compare(UByteArray.get-w2LRezQ(array, j) & 255, pivot & 255) > 0) {
            --j;
         }

         if (i <= j) {
            byte tmp = UByteArray.get-w2LRezQ(array, i);
            UByteArray.set-VurrAj0(array, i, UByteArray.get-w2LRezQ(array, j));
            UByteArray.set-VurrAj0(array, j, tmp);
            ++i;
            --j;
         }
      }

      return i;
   }

   @ExperimentalUnsignedTypes
   private static final void quickSort_4UcCI2c/* $FF was: quickSort-4UcCI2c*/(byte[] array, int left, int right) {
      int index = partition-4UcCI2c(array, left, right);
      if (left < index - 1) {
         quickSort-4UcCI2c(array, left, index - 1);
      }

      if (index < right) {
         quickSort-4UcCI2c(array, index, right);
      }

   }

   @ExperimentalUnsignedTypes
   private static final int partition_Aa5vz7o/* $FF was: partition-Aa5vz7o*/(short[] array, int left, int right) {
      int i = left;
      int j = right;
      short pivot = UShortArray.get-Mh2AYeg(array, (left + right) / 2);

      while(i <= j) {
         while(Intrinsics.compare(UShortArray.get-Mh2AYeg(array, i) & '\uffff', pivot & '\uffff') < 0) {
            ++i;
         }

         while(Intrinsics.compare(UShortArray.get-Mh2AYeg(array, j) & '\uffff', pivot & '\uffff') > 0) {
            --j;
         }

         if (i <= j) {
            short tmp = UShortArray.get-Mh2AYeg(array, i);
            UShortArray.set-01HTLdE(array, i, UShortArray.get-Mh2AYeg(array, j));
            UShortArray.set-01HTLdE(array, j, tmp);
            ++i;
            --j;
         }
      }

      return i;
   }

   @ExperimentalUnsignedTypes
   private static final void quickSort_Aa5vz7o/* $FF was: quickSort-Aa5vz7o*/(short[] array, int left, int right) {
      int index = partition-Aa5vz7o(array, left, right);
      if (left < index - 1) {
         quickSort-Aa5vz7o(array, left, index - 1);
      }

      if (index < right) {
         quickSort-Aa5vz7o(array, index, right);
      }

   }

   @ExperimentalUnsignedTypes
   private static final int partition_oBK06Vg/* $FF was: partition-oBK06Vg*/(int[] array, int left, int right) {
      int i = left;
      int j = right;
      int pivot = UIntArray.get-pVg5ArA(array, (left + right) / 2);

      while(i <= j) {
         while(Integer.compareUnsigned(UIntArray.get-pVg5ArA(array, i), pivot) < 0) {
            ++i;
         }

         while(Integer.compareUnsigned(UIntArray.get-pVg5ArA(array, j), pivot) > 0) {
            --j;
         }

         if (i <= j) {
            int tmp = UIntArray.get-pVg5ArA(array, i);
            UIntArray.set-VXSXFK8(array, i, UIntArray.get-pVg5ArA(array, j));
            UIntArray.set-VXSXFK8(array, j, tmp);
            ++i;
            --j;
         }
      }

      return i;
   }

   @ExperimentalUnsignedTypes
   private static final void quickSort_oBK06Vg/* $FF was: quickSort-oBK06Vg*/(int[] array, int left, int right) {
      int index = partition-oBK06Vg(array, left, right);
      if (left < index - 1) {
         quickSort-oBK06Vg(array, left, index - 1);
      }

      if (index < right) {
         quickSort-oBK06Vg(array, index, right);
      }

   }

   @ExperimentalUnsignedTypes
   private static final int partition__nroSd4/* $FF was: partition--nroSd4*/(long[] array, int left, int right) {
      int i = left;
      int j = right;
      long pivot = ULongArray.get-s-VKNKU(array, (left + right) / 2);

      while(i <= j) {
         while(Long.compareUnsigned(ULongArray.get-s-VKNKU(array, i), pivot) < 0) {
            ++i;
         }

         while(Long.compareUnsigned(ULongArray.get-s-VKNKU(array, j), pivot) > 0) {
            --j;
         }

         if (i <= j) {
            long tmp = ULongArray.get-s-VKNKU(array, i);
            ULongArray.set-k8EXiF4(array, i, ULongArray.get-s-VKNKU(array, j));
            ULongArray.set-k8EXiF4(array, j, tmp);
            ++i;
            --j;
         }
      }

      return i;
   }

   @ExperimentalUnsignedTypes
   private static final void quickSort__nroSd4/* $FF was: quickSort--nroSd4*/(long[] array, int left, int right) {
      int index = partition--nroSd4(array, left, right);
      if (left < index - 1) {
         quickSort--nroSd4(array, left, index - 1);
      }

      if (index < right) {
         quickSort--nroSd4(array, index, right);
      }

   }

   @ExperimentalUnsignedTypes
   public static final void sortArray_4UcCI2c/* $FF was: sortArray-4UcCI2c*/(@NotNull byte[] array, int fromIndex, int toIndex) {
      Intrinsics.checkNotNullParameter(array, "array");
      quickSort-4UcCI2c(array, fromIndex, toIndex - 1);
   }

   @ExperimentalUnsignedTypes
   public static final void sortArray_Aa5vz7o/* $FF was: sortArray-Aa5vz7o*/(@NotNull short[] array, int fromIndex, int toIndex) {
      Intrinsics.checkNotNullParameter(array, "array");
      quickSort-Aa5vz7o(array, fromIndex, toIndex - 1);
   }

   @ExperimentalUnsignedTypes
   public static final void sortArray_oBK06Vg/* $FF was: sortArray-oBK06Vg*/(@NotNull int[] array, int fromIndex, int toIndex) {
      Intrinsics.checkNotNullParameter(array, "array");
      quickSort-oBK06Vg(array, fromIndex, toIndex - 1);
   }

   @ExperimentalUnsignedTypes
   public static final void sortArray__nroSd4/* $FF was: sortArray--nroSd4*/(@NotNull long[] array, int fromIndex, int toIndex) {
      Intrinsics.checkNotNullParameter(array, "array");
      quickSort--nroSd4(array, fromIndex, toIndex - 1);
   }
}
