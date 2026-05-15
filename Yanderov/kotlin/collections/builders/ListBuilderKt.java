package kotlin.collections.builders;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u00004\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a#\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a-\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u0004\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0007\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\b\u0010\t\u001a'\u0010\f\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\n\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\f\u0010\r\u001a/\u0010\u0010\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a;\u0010\u0017\u001a\u00020\u0016\"\u0004\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001a/\u0010\u0019\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0019\u0010\u001a\u001a?\u0010\u001e\u001a\u00020\u001d\"\u0004\b\u0000\u0010\u0006*\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00032\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bH\u0002¢\u0006\u0004\b\u001e\u0010\u001f¨\u0006 "},
   d2 = {"E", "", "size", "", "arrayOfUninitializedElements", "(I)[Ljava/lang/Object;", "T", "newSize", "copyOfUninitializedElements", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "index", "", "resetAt", "([Ljava/lang/Object;I)V", "fromIndex", "toIndex", "resetRange", "([Ljava/lang/Object;II)V", "offset", "length", "", "other", "", "subarrayContentEquals", "([Ljava/lang/Object;IILjava/util/List;)Z", "subarrayContentHashCode", "([Ljava/lang/Object;II)I", "", "thisCollection", "", "subarrayContentToString", "([Ljava/lang/Object;IILjava/util/Collection;)Ljava/lang/String;", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nListBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ListBuilder.kt\nkotlin/collections/builders/ListBuilderKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,718:1\n1#2:719\n*E\n"})
public final class ListBuilderKt {
   @NotNull
   public static final Object[] arrayOfUninitializedElements(int size) {
      boolean var1 = size >= 0;
      if (!var1) {
         int var2 = 0;
         String var3 = "capacity must be non-negative.";
         throw new IllegalArgumentException(var3.toString());
      } else {
         return new Object[size];
      }
   }

   private static final String subarrayContentToString(Object[] $this$subarrayContentToString, int offset, int length, Collection thisCollection) {
      StringBuilder sb = new StringBuilder(2 + length * 3);
      sb.append("[");

      for(int i = 0; i < length; ++i) {
         if (i > 0) {
            sb.append(", ");
         }

         Object nextElement = $this$subarrayContentToString[offset + i];
         if (nextElement == thisCollection) {
            sb.append("(this Collection)");
         } else {
            sb.append(nextElement);
         }
      }

      sb.append("]");
      String var10000 = sb.toString();
      Intrinsics.checkNotNullExpressionValue(var10000, "toString(...)");
      return var10000;
   }

   private static final int subarrayContentHashCode(Object[] $this$subarrayContentHashCode, int offset, int length) {
      int result = 1;

      for(int i = 0; i < length; ++i) {
         Object nextElement = $this$subarrayContentHashCode[offset + i];
         result = result * 31 + (nextElement != null ? nextElement.hashCode() : 0);
      }

      return result;
   }

   private static final boolean subarrayContentEquals(Object[] $this$subarrayContentEquals, int offset, int length, List other) {
      if (length != other.size()) {
         return false;
      } else {
         for(int i = 0; i < length; ++i) {
            if (!Intrinsics.areEqual($this$subarrayContentEquals[offset + i], other.get(i))) {
               return false;
            }
         }

         return true;
      }
   }

   @NotNull
   public static final Object[] copyOfUninitializedElements(@NotNull Object[] $this$copyOfUninitializedElements, int newSize) {
      Intrinsics.checkNotNullParameter($this$copyOfUninitializedElements, "<this>");
      Object[] var10000 = Arrays.copyOf($this$copyOfUninitializedElements, newSize);
      Intrinsics.checkNotNullExpressionValue(var10000, "copyOf(...)");
      return var10000;
   }

   public static final void resetAt(@NotNull Object[] $this$resetAt, int index) {
      Intrinsics.checkNotNullParameter($this$resetAt, "<this>");
      $this$resetAt[index] = null;
   }

   public static final void resetRange(@NotNull Object[] $this$resetRange, int fromIndex, int toIndex) {
      Intrinsics.checkNotNullParameter($this$resetRange, "<this>");

      for(int index = fromIndex; index < toIndex; ++index) {
         resetAt($this$resetRange, index);
      }

   }

   // $FF: synthetic method
   public static final int access$subarrayContentHashCode(Object[] $receiver, int offset, int length) {
      return subarrayContentHashCode($receiver, offset, length);
   }

   // $FF: synthetic method
   public static final String access$subarrayContentToString(Object[] $receiver, int offset, int length, Collection thisCollection) {
      return subarrayContentToString($receiver, offset, length, thisCollection);
   }

   // $FF: synthetic method
   public static final boolean access$subarrayContentEquals(Object[] $receiver, int offset, int length, List other) {
      return subarrayContentEquals($receiver, offset, length, other);
   }
}
