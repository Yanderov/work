package kotlin.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.collections.unsigned.UArraysKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a5\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\f\u0012\u0006\b\u0001\u0012\u00028\u0000\u0018\u00010\u00012\u0010\u0010\u0002\u001a\f\u0012\u0006\b\u0001\u0012\u00028\u0000\u0018\u00010\u0001H\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a#\u0010\n\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0000*\f\u0012\u0006\b\u0001\u0012\u00028\u0000\u0018\u00010\u0001H\u0001¢\u0006\u0004\b\b\u0010\t\u001a?\u0010\u0013\u001a\u00020\u0010\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00012\n\u0010\r\u001a\u00060\u000bj\u0002`\f2\u0010\u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0012\u001a-\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014\"\u0004\b\u0000\u0010\u0000*\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00010\u0001¢\u0006\u0004\b\u0015\u0010\u0016\u001aJ\u0010\u001b\u001a\u00028\u0001\"\u0010\b\u0000\u0010\u0017*\u0006\u0012\u0002\b\u00030\u0001*\u00028\u0001\"\u0004\b\u0001\u0010\u0018*\u00028\u00002\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00010\u0019H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a+\u0010\u001d\u001a\u00020\u0003*\b\u0012\u0002\b\u0003\u0018\u00010\u0001H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001aI\u0010 \u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00140\u001f\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0018*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001f0\u0001¢\u0006\u0004\b \u0010!\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\""},
   d2 = {"T", "", "other", "", "contentDeepEquals", "([Ljava/lang/Object;[Ljava/lang/Object;)Z", "contentDeepEqualsImpl", "", "contentDeepToString", "([Ljava/lang/Object;)Ljava/lang/String;", "contentDeepToStringImpl", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "result", "", "processed", "", "contentDeepToStringInternal$ArraysKt__ArraysKt", "([Ljava/lang/Object;Ljava/lang/StringBuilder;Ljava/util/List;)V", "contentDeepToStringInternal", "", "flatten", "([[Ljava/lang/Object;)Ljava/util/List;", "C", "R", "Lkotlin/Function0;", "defaultValue", "ifEmpty", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNullOrEmpty", "([Ljava/lang/Object;)Z", "Lkotlin/Pair;", "unzip", "([Lkotlin/Pair;)Lkotlin/Pair;", "kotlin-stdlib"},
   xs = "kotlin/collections/ArraysKt"
)
@SourceDebugExtension({"SMAP\nArrays.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Arrays.kt\nkotlin/collections/ArraysKt__ArraysKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,165:1\n1#2:166\n*E\n"})
class ArraysKt__ArraysKt extends ArraysKt__ArraysJVMKt {
   @NotNull
   public static final List flatten(@NotNull Object[][] $this$flatten) {
      Intrinsics.checkNotNullParameter($this$flatten, "<this>");
      Object[] var2 = $this$flatten;
      int var3 = 0;
      int var4 = 0;

      for(int var5 = var2.length; var4 < var5; ++var4) {
         Object var6 = var2[var4];
         Object[] it = var6;
         int var8 = 0;
         int var10 = it.length;
         var3 += var10;
      }

      ArrayList result = new ArrayList(var3);
      int var12 = 0;

      for(int var13 = ((Object[])$this$flatten).length; var12 < var13; ++var12) {
         Object[] element = $this$flatten[var12];
         CollectionsKt.addAll((Collection)result, element);
      }

      return (List)result;
   }

   @NotNull
   public static final Pair unzip(@NotNull Pair[] $this$unzip) {
      Intrinsics.checkNotNullParameter($this$unzip, "<this>");
      ArrayList listT = new ArrayList($this$unzip.length);
      ArrayList listR = new ArrayList($this$unzip.length);
      int var3 = 0;

      for(int var4 = $this$unzip.length; var3 < var4; ++var3) {
         Pair pair = $this$unzip[var3];
         listT.add(pair.getFirst());
         listR.add(pair.getSecond());
      }

      return TuplesKt.to(listT, listR);
   }

   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final boolean isNullOrEmpty(Object[] $this$isNullOrEmpty) {
      return $this$isNullOrEmpty == null || $this$isNullOrEmpty.length == 0;
   }

   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final Object ifEmpty(Object[] $this$ifEmpty, Function0 defaultValue) {
      Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
      return $this$ifEmpty.length == 0 ? defaultValue.invoke() : $this$ifEmpty;
   }

   @SinceKotlin(
      version = "1.3"
   )
   @PublishedApi
   @JvmName(
      name = "contentDeepEquals"
   )
   public static final boolean contentDeepEquals(@Nullable Object[] $this$contentDeepEqualsImpl, @Nullable Object[] other) {
      if ($this$contentDeepEqualsImpl == other) {
         return true;
      } else if ($this$contentDeepEqualsImpl != null && other != null && $this$contentDeepEqualsImpl.length == other.length) {
         int i = 0;

         for(int var3 = $this$contentDeepEqualsImpl.length; i < var3; ++i) {
            Object v1 = $this$contentDeepEqualsImpl[i];
            Object v2 = other[i];
            if (v1 != v2) {
               if (v1 == null || v2 == null) {
                  return false;
               }

               if (v1 instanceof Object[] && v2 instanceof Object[]) {
                  Object[] var6 = v1;
                  Object[] var7 = v2;
                  if (!ArraysKt.contentDeepEquals(var6, var7)) {
                     return false;
                  }
               } else if (v1 instanceof byte[] && v2 instanceof byte[]) {
                  if (!Arrays.equals((byte[])v1, (byte[])v2)) {
                     return false;
                  }
               } else if (v1 instanceof short[] && v2 instanceof short[]) {
                  if (!Arrays.equals((short[])v1, (short[])v2)) {
                     return false;
                  }
               } else if (v1 instanceof int[] && v2 instanceof int[]) {
                  if (!Arrays.equals((int[])v1, (int[])v2)) {
                     return false;
                  }
               } else if (v1 instanceof long[] && v2 instanceof long[]) {
                  if (!Arrays.equals((long[])v1, (long[])v2)) {
                     return false;
                  }
               } else if (v1 instanceof float[] && v2 instanceof float[]) {
                  if (!Arrays.equals((float[])v1, (float[])v2)) {
                     return false;
                  }
               } else if (v1 instanceof double[] && v2 instanceof double[]) {
                  if (!Arrays.equals((double[])v1, (double[])v2)) {
                     return false;
                  }
               } else if (v1 instanceof char[] && v2 instanceof char[]) {
                  if (!Arrays.equals((char[])v1, (char[])v2)) {
                     return false;
                  }
               } else if (v1 instanceof boolean[] && v2 instanceof boolean[]) {
                  if (!Arrays.equals((boolean[])v1, (boolean[])v2)) {
                     return false;
                  }
               } else if (v1 instanceof UByteArray && v2 instanceof UByteArray) {
                  if (!UArraysKt.contentEquals-kV0jMPg(((UByteArray)v1).unbox-impl(), ((UByteArray)v2).unbox-impl())) {
                     return false;
                  }
               } else if (v1 instanceof UShortArray && v2 instanceof UShortArray) {
                  if (!UArraysKt.contentEquals-FGO6Aew(((UShortArray)v1).unbox-impl(), ((UShortArray)v2).unbox-impl())) {
                     return false;
                  }
               } else if (v1 instanceof UIntArray && v2 instanceof UIntArray) {
                  if (!UArraysKt.contentEquals-KJPZfPQ(((UIntArray)v1).unbox-impl(), ((UIntArray)v2).unbox-impl())) {
                     return false;
                  }
               } else if (v1 instanceof ULongArray && v2 instanceof ULongArray) {
                  if (!UArraysKt.contentEquals-lec5QzE(((ULongArray)v1).unbox-impl(), ((ULongArray)v2).unbox-impl())) {
                     return false;
                  }
               } else if (!Intrinsics.areEqual(v1, v2)) {
                  return false;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   @SinceKotlin(
      version = "1.3"
   )
   @PublishedApi
   @JvmName(
      name = "contentDeepToString"
   )
   @NotNull
   public static final String contentDeepToString(@Nullable Object[] $this$contentDeepToStringImpl) {
      if ($this$contentDeepToStringImpl == null) {
         return "null";
      } else {
         int length = RangesKt.coerceAtMost($this$contentDeepToStringImpl.length, 429496729) * 5 + 2;
         StringBuilder $this$contentDeepToStringImpl_u24lambda_u243 = new StringBuilder(length);
         int var4 = 0;
         contentDeepToStringInternal$ArraysKt__ArraysKt($this$contentDeepToStringImpl, $this$contentDeepToStringImpl_u24lambda_u243, (List)(new ArrayList()));
         String var10000 = $this$contentDeepToStringImpl_u24lambda_u243.toString();
         Intrinsics.checkNotNullExpressionValue(var10000, "toString(...)");
         return var10000;
      }
   }

   private static final void contentDeepToStringInternal$ArraysKt__ArraysKt(Object[] $this$contentDeepToStringInternal, StringBuilder result, List processed) {
      if (processed.contains($this$contentDeepToStringInternal)) {
         result.append("[...]");
      } else {
         processed.add($this$contentDeepToStringInternal);
         result.append('[');
         int i = 0;

         for(int var4 = $this$contentDeepToStringInternal.length; i < var4; ++i) {
            if (i != 0) {
               result.append(", ");
            }

            Object element = $this$contentDeepToStringInternal[i];
            if (element == null) {
               result.append("null");
            } else if (element instanceof Object[]) {
               contentDeepToStringInternal$ArraysKt__ArraysKt(element, result, processed);
            } else if (element instanceof byte[]) {
               String var10001 = Arrays.toString((byte[])element);
               Intrinsics.checkNotNullExpressionValue(var10001, "toString(...)");
               result.append(var10001);
            } else if (element instanceof short[]) {
               String var7 = Arrays.toString((short[])element);
               Intrinsics.checkNotNullExpressionValue(var7, "toString(...)");
               result.append(var7);
            } else if (element instanceof int[]) {
               String var8 = Arrays.toString((int[])element);
               Intrinsics.checkNotNullExpressionValue(var8, "toString(...)");
               result.append(var8);
            } else if (element instanceof long[]) {
               String var9 = Arrays.toString((long[])element);
               Intrinsics.checkNotNullExpressionValue(var9, "toString(...)");
               result.append(var9);
            } else if (element instanceof float[]) {
               String var10 = Arrays.toString((float[])element);
               Intrinsics.checkNotNullExpressionValue(var10, "toString(...)");
               result.append(var10);
            } else if (element instanceof double[]) {
               String var11 = Arrays.toString((double[])element);
               Intrinsics.checkNotNullExpressionValue(var11, "toString(...)");
               result.append(var11);
            } else if (element instanceof char[]) {
               String var12 = Arrays.toString((char[])element);
               Intrinsics.checkNotNullExpressionValue(var12, "toString(...)");
               result.append(var12);
            } else if (element instanceof boolean[]) {
               String var13 = Arrays.toString((boolean[])element);
               Intrinsics.checkNotNullExpressionValue(var13, "toString(...)");
               result.append(var13);
            } else if (element instanceof UByteArray) {
               result.append(UArraysKt.contentToString-2csIQuQ((UByteArray)element != null ? ((UByteArray)element).unbox-impl() : null));
            } else if (element instanceof UShortArray) {
               result.append(UArraysKt.contentToString-d-6D3K8((UShortArray)element != null ? ((UShortArray)element).unbox-impl() : null));
            } else if (element instanceof UIntArray) {
               result.append(UArraysKt.contentToString-XUkPCBk((UIntArray)element != null ? ((UIntArray)element).unbox-impl() : null));
            } else if (element instanceof ULongArray) {
               result.append(UArraysKt.contentToString-uLth9ew((ULongArray)element != null ? ((ULongArray)element).unbox-impl() : null));
            } else {
               result.append(element.toString());
            }
         }

         result.append(']');
         processed.remove(CollectionsKt.getLastIndex(processed));
      }
   }

   public ArraysKt__ArraysKt() {
   }
}
