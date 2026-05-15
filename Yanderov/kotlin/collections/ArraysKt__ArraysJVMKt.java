package kotlin.collections;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u00002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\u001a1\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0001¢\u0006\u0004\b\t\u0010\n\u001a#\u0010\r\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\f\u0012\u0006\b\u0001\u0012\u00028\u0000\u0018\u00010\u0001H\u0001¢\u0006\u0004\b\u000b\u0010\f\u001a.\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0001\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\f\u0012\u0006\b\u0001\u0012\u00028\u0000\u0018\u00010\u0001H\u0086\b¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u001c\u0010\u0014\u001a\u00020\u0013*\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0011H\u0087\b¢\u0006\u0004\b\u0014\u0010\u0015\u001a(\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0086\b¢\u0006\u0004\b\u0017\u0010\u0018¨\u0006\u0019"},
   d2 = {"T", "", "reference", "", "size", "arrayOfNulls", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "toIndex", "", "copyOfRangeToIndexCheck", "(II)V", "contentDeepHashCode", "([Ljava/lang/Object;)I", "contentDeepHashCodeImpl", "orEmpty", "([Ljava/lang/Object;)[Ljava/lang/Object;", "", "Ljava/nio/charset/Charset;", "charset", "", "toString", "([BLjava/nio/charset/Charset;)Ljava/lang/String;", "", "toTypedArray", "(Ljava/util/Collection;)[Ljava/lang/Object;", "kotlin-stdlib"},
   xs = "kotlin/collections/ArraysKt"
)
class ArraysKt__ArraysJVMKt {
   // $FF: synthetic method
   public static final Object[] orEmpty(Object[] $this$orEmpty) {
      int $i$f$orEmpty = 0;
      Object[] var10000 = $this$orEmpty;
      if ($this$orEmpty == null) {
         Intrinsics.reifiedOperationMarker(0, "T");
         var10000 = new Object[0];
      }

      return var10000;
   }

   @InlineOnly
   private static final String toString(byte[] $this$toString, Charset charset) {
      Intrinsics.checkNotNullParameter($this$toString, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      return new String($this$toString, charset);
   }

   // $FF: synthetic method
   public static final Object[] toTypedArray(Collection $this$toTypedArray) {
      Intrinsics.checkNotNullParameter($this$toTypedArray, "<this>");
      int $i$f$toTypedArray = 0;
      Collection thisCollection = $this$toTypedArray;
      Intrinsics.reifiedOperationMarker(0, "T?");
      return thisCollection.toArray(new Object[0]);
   }

   @NotNull
   public static final Object[] arrayOfNulls(@NotNull Object[] reference, int size) {
      Intrinsics.checkNotNullParameter(reference, "reference");
      Object var10000 = Array.newInstance(reference.getClass().getComponentType(), size);
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.arrayOfNulls>");
      return var10000;
   }

   @SinceKotlin(
      version = "1.3"
   )
   public static final void copyOfRangeToIndexCheck(int toIndex, int size) {
      if (toIndex > size) {
         throw new IndexOutOfBoundsException("toIndex (" + toIndex + ") is greater than size (" + size + ").");
      }
   }

   @SinceKotlin(
      version = "1.3"
   )
   @PublishedApi
   @JvmName(
      name = "contentDeepHashCode"
   )
   public static final int contentDeepHashCode(@Nullable Object[] $this$contentDeepHashCodeImpl) {
      return Arrays.deepHashCode($this$contentDeepHashCodeImpl);
   }

   public ArraysKt__ArraysJVMKt() {
   }
}
