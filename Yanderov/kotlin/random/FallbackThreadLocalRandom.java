package kotlin.random;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0004*\u0001\b\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0007\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\n¨\u0006\u000b"},
   d2 = {"Lkotlin/random/FallbackThreadLocalRandom;", "Lkotlin/random/AbstractPlatformRandom;", "<init>", "()V", "Ljava/util/Random;", "getImpl", "()Ljava/util/Random;", "impl", "kotlin/random/FallbackThreadLocalRandom.implStorage.1", "implStorage", "Lkotlin/random/FallbackThreadLocalRandom$implStorage$1;", "kotlin-stdlib"}
)
public final class FallbackThreadLocalRandom extends AbstractPlatformRandom {
   @NotNull
   private final <undefinedtype> implStorage = new ThreadLocal() {
      protected java.util.Random initialValue() {
         return new java.util.Random();
      }
   };

   @NotNull
   public java.util.Random getImpl() {
      Object var10000 = this.implStorage.get();
      Intrinsics.checkNotNullExpressionValue(var10000, "get(...)");
      return (java.util.Random)var10000;
   }
}
