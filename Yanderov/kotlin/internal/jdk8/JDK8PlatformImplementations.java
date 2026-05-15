package kotlin.internal.jdk8;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.internal.jdk7.JDK7PlatformImplementations;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import kotlin.random.jdk8.PlatformThreadLocalRandom;
import kotlin.ranges.IntRange;
import kotlin.text.MatchGroup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0010\u0018\u00002\u00020\u0001:\u0001\u0013B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J!\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0014"},
   d2 = {"Lkotlin/internal/jdk8/JDK8PlatformImplementations;", "Lkotlin/internal/jdk7/JDK7PlatformImplementations;", "<init>", "()V", "Lkotlin/random/Random;", "defaultPlatformRandom", "()Lkotlin/random/Random;", "Ljava/util/regex/MatchResult;", "matchResult", "", "name", "Lkotlin/text/MatchGroup;", "getMatchResultNamedGroup", "(Ljava/util/regex/MatchResult;Ljava/lang/String;)Lkotlin/text/MatchGroup;", "", "version", "", "sdkIsNullOrAtLeast", "(I)Z", "ReflectSdkVersion", "kotlin-stdlib-jdk8"}
)
public class JDK8PlatformImplementations extends JDK7PlatformImplementations {
   private final boolean sdkIsNullOrAtLeast(int version) {
      return JDK8PlatformImplementations.ReflectSdkVersion.sdkVersion == null || JDK8PlatformImplementations.ReflectSdkVersion.sdkVersion >= version;
   }

   @Nullable
   public MatchGroup getMatchResultNamedGroup(@NotNull MatchResult matchResult, @NotNull String name) {
      Intrinsics.checkNotNullParameter(matchResult, "matchResult");
      Intrinsics.checkNotNullParameter(name, "name");
      Matcher var10000 = matchResult instanceof Matcher ? (Matcher)matchResult : null;
      if ((matchResult instanceof Matcher ? (Matcher)matchResult : null) == null) {
         throw new UnsupportedOperationException("Retrieving groups by name is not supported on this platform.");
      } else {
         Matcher matcher = var10000;
         IntRange range = new IntRange(matcher.start(name), matcher.end(name) - 1);
         MatchGroup var5;
         if (range.getStart() >= 0) {
            String var10002 = matcher.group(name);
            Intrinsics.checkNotNullExpressionValue(var10002, "group(...)");
            var5 = new MatchGroup(var10002, range);
         } else {
            var5 = null;
         }

         return var5;
      }
   }

   @NotNull
   public Random defaultPlatformRandom() {
      return this.sdkIsNullOrAtLeast(34) ? (Random)(new PlatformThreadLocalRandom()) : super.defaultPlatformRandom();
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
      d2 = {"Lkotlin/internal/jdk8/JDK8PlatformImplementations$ReflectSdkVersion;", "", "<init>", "()V", "", "sdkVersion", "Ljava/lang/Integer;", "kotlin-stdlib-jdk8"}
   )
   @SourceDebugExtension({"SMAP\nJDK8PlatformImplementations.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JDK8PlatformImplementations.kt\nkotlin/internal/jdk8/JDK8PlatformImplementations$ReflectSdkVersion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,61:1\n1#2:62\n*E\n"})
   private static final class ReflectSdkVersion {
      @NotNull
      public static final ReflectSdkVersion INSTANCE = new ReflectSdkVersion();
      @JvmField
      @Nullable
      public static final Integer sdkVersion;

      static {
         Integer var1;
         try {
            Object var5 = Class.forName("android.os.Build$VERSION").getField("SDK_INT").get((Object)null);
            var1 = var5 instanceof Integer ? (Integer)var5 : null;
         } catch (Throwable var4) {
            var1 = null;
         }

         Integer var10000;
         if (var1 != null) {
            int it = ((Number)var1).intValue();
            int var3 = 0;
            var10000 = it > 0 ? var1 : null;
         } else {
            var10000 = null;
         }

         sdkVersion = var10000;
      }
   }
}
