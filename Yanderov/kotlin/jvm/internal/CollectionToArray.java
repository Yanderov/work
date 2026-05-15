package kotlin.jvm.internal;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000*\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001a#\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\n\u0010\u0001\u001a\u0006\u0012\u0002\b\u00030\u0000H\u0007¢\u0006\u0004\b\u0004\u0010\u0005\u001a5\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\n\u0010\u0001\u001a\u0006\u0012\u0002\b\u00030\u00002\u0010\u0010\u0007\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0002H\u0007¢\u0006\u0004\b\u0004\u0010\b\u001a\u0080\u0001\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\n\u0010\u0001\u001a\u0006\u0012\u0002\b\u00030\u00002\u0014\u0010\n\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00020\t2\u001a\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\f\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00020\u000b2(\u0010\u000f\u001a$\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002\u0012\u0004\u0012\u00020\f\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00020\u000eH\u0082\b¢\u0006\u0004\b\u0010\u0010\u0011\"\u001c\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013\"\u0014\u0010\u0014\u001a\u00020\f8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0016"},
   d2 = {"", "collection", "", "", "toArray", "(Ljava/util/Collection;)[Ljava/lang/Object;", "collectionToArray", "a", "(Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;", "Lkotlin/Function0;", "empty", "Lkotlin/Function1;", "", "alloc", "Lkotlin/Function2;", "trim", "toArrayImpl", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)[Ljava/lang/Object;", "EMPTY", "[Ljava/lang/Object;", "MAX_SIZE", "I", "kotlin-stdlib"}
)
@JvmName(
   name = "CollectionToArray"
)
@SourceDebugExtension({"SMAP\nCollectionToArray.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CollectionToArray.kt\nkotlin/jvm/internal/CollectionToArray\n*L\n1#1,88:1\n63#1,22:89\n63#1,22:111\n*S KotlinDebug\n*F\n+ 1 CollectionToArray.kt\nkotlin/jvm/internal/CollectionToArray\n*L\n22#1:89,22\n37#1:111,22\n*E\n"})
public final class CollectionToArray {
   @NotNull
   private static final Object[] EMPTY = new Object[0];
   private static final int MAX_SIZE = 2147483645;

   /** @deprecated */
   @Deprecated(
      message = "This function will be made internal in a future release"
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.9"
   )
   @JvmName(
      name = "toArray"
   )
   @NotNull
   public static final Object[] toArray(@NotNull Collection collection) {
      Intrinsics.checkNotNullParameter(collection, "collection");
      int $i$f$toArrayImpl = 0;
      int size$iv = collection.size();
      Object[] var10000;
      if (size$iv == 0) {
         int var3 = 0;
         var10000 = EMPTY;
      } else {
         Iterator iter$iv = collection.iterator();
         if (!iter$iv.hasNext()) {
            int result = 0;
            var10000 = EMPTY;
         } else {
            int var5 = 0;
            Object[] result$iv = new Object[size$iv];
            var5 = 0;

            while(true) {
               result$iv[var5++] = iter$iv.next();
               if (var5 >= result$iv.length) {
                  if (!iter$iv.hasNext()) {
                     var10000 = result$iv;
                     break;
                  }

                  int newSize$iv = var5 * 3 + 1 >>> 1;
                  if (newSize$iv <= var5) {
                     if (var5 >= 2147483645) {
                        throw new OutOfMemoryError();
                     }

                     newSize$iv = 2147483645;
                  }

                  var10000 = Arrays.copyOf(result$iv, newSize$iv);
                  Intrinsics.checkNotNullExpressionValue(var10000, "copyOf(...)");
                  result$iv = var10000;
               } else if (!iter$iv.hasNext()) {
                  int var9 = 0;
                  var10000 = Arrays.copyOf(result$iv, var5);
                  Intrinsics.checkNotNullExpressionValue(var10000, "copyOf(...)");
                  break;
               }
            }
         }
      }

      return var10000;
   }

   /** @deprecated */
   @Deprecated(
      message = "This function will be made internal in a future release"
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.9"
   )
   @JvmName(
      name = "toArray"
   )
   @NotNull
   public static final Object[] toArray(@NotNull Collection collection, @Nullable Object[] a) {
      Intrinsics.checkNotNullParameter(collection, "collection");
      if (a == null) {
         throw new NullPointerException();
      } else {
         int $i$f$toArrayImpl = 0;
         int size$iv = collection.size();
         Object[] var10000;
         if (size$iv == 0) {
            int var4 = 0;
            if (a.length > 0) {
               a[0] = null;
            }

            var10000 = a;
         } else {
            Iterator iter$iv = collection.iterator();
            if (!iter$iv.hasNext()) {
               int var11 = 0;
               if (a.length > 0) {
                  a[0] = null;
               }

               var10000 = a;
            } else {
               int var6 = 0;
               if (size$iv <= a.length) {
                  var10000 = a;
               } else {
                  var10000 = Array.newInstance(a.getClass().getComponentType(), size$iv);
                  Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                  var10000 = var10000;
               }

               Object[] result$iv = var10000;
               int i$iv = 0;

               while(true) {
                  result$iv[i$iv++] = iter$iv.next();
                  if (i$iv >= result$iv.length) {
                     if (!iter$iv.hasNext()) {
                        var10000 = result$iv;
                        break;
                     }

                     var6 = i$iv * 3 + 1 >>> 1;
                     if (var6 <= i$iv) {
                        if (i$iv >= 2147483645) {
                           throw new OutOfMemoryError();
                        }

                        var6 = 2147483645;
                     }

                     Object[] var16 = Arrays.copyOf(result$iv, var6);
                     Intrinsics.checkNotNullExpressionValue(var16, "copyOf(...)");
                     result$iv = var16;
                  } else if (!iter$iv.hasNext()) {
                     int var10 = 0;
                     if (result$iv == a) {
                        a[i$iv] = null;
                        var10000 = a;
                     } else {
                        var10000 = Arrays.copyOf(result$iv, i$iv);
                        Intrinsics.checkNotNullExpressionValue(var10000, "copyOf(...)");
                     }
                     break;
                  }
               }
            }
         }

         return (Object[])var10000;
      }
   }

   private static final Object[] toArrayImpl(Collection collection, Function0 empty, Function1 alloc, Function2 trim) {
      int $i$f$toArrayImpl = 0;
      int size = collection.size();
      if (size == 0) {
         return empty.invoke();
      } else {
         Iterator iter = collection.iterator();
         if (!iter.hasNext()) {
            return empty.invoke();
         } else {
            Object[] result = alloc.invoke(size);
            int i = 0;

            while(true) {
               result[i++] = iter.next();
               if (i >= result.length) {
                  if (!iter.hasNext()) {
                     return result;
                  }

                  int newSize = i * 3 + 1 >>> 1;
                  if (newSize <= i) {
                     if (i >= 2147483645) {
                        throw new OutOfMemoryError();
                     }

                     newSize = 2147483645;
                  }

                  Object[] var10000 = Arrays.copyOf(result, newSize);
                  Intrinsics.checkNotNullExpressionValue(var10000, "copyOf(...)");
                  result = var10000;
               } else if (!iter.hasNext()) {
                  return trim.invoke(result, i);
               }
            }
         }
      }
   }
}
