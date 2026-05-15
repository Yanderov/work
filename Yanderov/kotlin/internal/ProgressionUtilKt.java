package kotlin.internal;

import kotlin.Metadata;
import kotlin.PublishedApi;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u0010\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\t\u001a'\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001a'\u0010\u0004\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0004\u0010\u0007\u001a'\u0010\u000b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0001¢\u0006\u0004\b\u000b\u0010\u0005\u001a'\u0010\u000b\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0001¢\u0006\u0004\b\u000b\u0010\u0007\u001a\u001f\u0010\f\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\f\u0010\r\u001a\u001f\u0010\f\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\f\u0010\u000e¨\u0006\u000f"},
   d2 = {"", "a", "b", "c", "differenceModulo", "(III)I", "", "(JJJ)J", "start", "end", "step", "getProgressionLastElement", "mod", "(II)I", "(JJ)J", "kotlin-stdlib"}
)
public final class ProgressionUtilKt {
   private static final int mod(int a, int b) {
      int mod = a % b;
      return mod >= 0 ? mod : mod + b;
   }

   private static final long mod(long a, long b) {
      long mod = a % b;
      return mod >= 0L ? mod : mod + b;
   }

   private static final int differenceModulo(int a, int b, int c) {
      return mod(mod(a, c) - mod(b, c), c);
   }

   private static final long differenceModulo(long a, long b, long c) {
      return mod(mod(a, c) - mod(b, c), c);
   }

   @PublishedApi
   public static final int getProgressionLastElement(int start, int end, int step) {
      int var10000;
      if (step > 0) {
         var10000 = start >= end ? end : end - differenceModulo(end, start, step);
      } else {
         if (step >= 0) {
            throw new IllegalArgumentException("Step is zero.");
         }

         var10000 = start <= end ? end : end + differenceModulo(start, end, -step);
      }

      return var10000;
   }

   @PublishedApi
   public static final long getProgressionLastElement(long start, long end, long step) {
      long var10000;
      if (step > 0L) {
         var10000 = start >= end ? end : end - differenceModulo(end, start, step);
      } else {
         if (step >= 0L) {
            throw new IllegalArgumentException("Step is zero.");
         }

         var10000 = start <= end ? end : end + differenceModulo(start, end, -step);
      }

      return var10000;
   }
}
