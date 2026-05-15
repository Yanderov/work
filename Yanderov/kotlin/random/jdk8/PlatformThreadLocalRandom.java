package kotlin.random.jdk8;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.AbstractPlatformRandom;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\bH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000fR\u0014\u0010\u0013\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0014"},
   d2 = {"Lkotlin/random/jdk8/PlatformThreadLocalRandom;", "Lkotlin/random/AbstractPlatformRandom;", "<init>", "()V", "", "until", "nextDouble", "(D)D", "", "from", "nextInt", "(II)I", "", "nextLong", "(J)J", "(JJ)J", "Ljava/util/Random;", "getImpl", "()Ljava/util/Random;", "impl", "kotlin-stdlib-jdk8"}
)
public final class PlatformThreadLocalRandom extends AbstractPlatformRandom {
   @NotNull
   public Random getImpl() {
      ThreadLocalRandom var10000 = ThreadLocalRandom.current();
      Intrinsics.checkNotNullExpressionValue(var10000, "current(...)");
      return (Random)var10000;
   }

   public int nextInt(int from, int until) {
      return ThreadLocalRandom.current().nextInt(from, until);
   }

   public long nextLong(long until) {
      return ThreadLocalRandom.current().nextLong(until);
   }

   public long nextLong(long from, long until) {
      return ThreadLocalRandom.current().nextLong(from, until);
   }

   public double nextDouble(double until) {
      return ThreadLocalRandom.current().nextDouble(until);
   }
}
