package kotlin.internal;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.ULong;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\u001a'\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001a'\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0001\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\b\u0010\t\u001a'\u0010\u000f\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\fH\u0001¢\u0006\u0004\b\u000e\u0010\u0005\u001a'\u0010\u000f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001¢\u0006\u0004\b\u0011\u0010\t¨\u0006\u0012"},
   d2 = {"Lkotlin/UInt;", "a", "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "differenceModulo", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "start", "end", "", "step", "getProgressionLastElement-Nkh28Cs", "getProgressionLastElement", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"}
)
public final class UProgressionUtilKt {
   private static final int differenceModulo_WZ9TVnA/* $FF was: differenceModulo-WZ9TVnA*/(int a, int b, int c) {
      int ac = Integer.remainderUnsigned(a, c);
      int bc = Integer.remainderUnsigned(b, c);
      return Integer.compareUnsigned(ac, bc) >= 0 ? UInt.constructor-impl(ac - bc) : UInt.constructor-impl(UInt.constructor-impl(ac - bc) + c);
   }

   private static final long differenceModulo_sambcqE/* $FF was: differenceModulo-sambcqE*/(long a, long b, long c) {
      long ac = Long.remainderUnsigned(a, c);
      long bc = Long.remainderUnsigned(b, c);
      return Long.compareUnsigned(ac, bc) >= 0 ? ULong.constructor-impl(ac - bc) : ULong.constructor-impl(ULong.constructor-impl(ac - bc) + c);
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   public static final int getProgressionLastElement_Nkh28Cs/* $FF was: getProgressionLastElement-Nkh28Cs*/(int start, int end, int step) {
      int var10000;
      if (step > 0) {
         var10000 = Integer.compareUnsigned(start, end) >= 0 ? end : UInt.constructor-impl(end - differenceModulo-WZ9TVnA(end, start, UInt.constructor-impl(step)));
      } else {
         if (step >= 0) {
            throw new IllegalArgumentException("Step is zero.");
         }

         var10000 = Integer.compareUnsigned(start, end) <= 0 ? end : UInt.constructor-impl(end + differenceModulo-WZ9TVnA(start, end, UInt.constructor-impl(-step)));
      }

      return var10000;
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   public static final long getProgressionLastElement_7ftBX0g/* $FF was: getProgressionLastElement-7ftBX0g*/(long start, long end, long step) {
      long var10000;
      if (step > 0L) {
         var10000 = Long.compareUnsigned(start, end) >= 0 ? end : ULong.constructor-impl(end - differenceModulo-sambcqE(end, start, ULong.constructor-impl(step)));
      } else {
         if (step >= 0L) {
            throw new IllegalArgumentException("Step is zero.");
         }

         var10000 = Long.compareUnsigned(start, end) <= 0 ? end : ULong.constructor-impl(end + differenceModulo-sambcqE(start, end, ULong.constructor-impl(-step)));
      }

      return var10000;
   }
}
