package kotlin.internal;

import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.MatchResult;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.FallbackThreadLocalRandom;
import kotlin.random.Random;
import kotlin.text.MatchGroup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\b\u0010\u0018\u00002\u00020\u0001:\u0001\u0017B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ!\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u00142\u0006\u0010\u0006\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0015\u0010\u0016¨\u0006\u0018"},
   d2 = {"Lkotlin/internal/PlatformImplementations;", "", "<init>", "()V", "", "cause", "exception", "", "addSuppressed", "(Ljava/lang/Throwable;Ljava/lang/Throwable;)V", "Lkotlin/random/Random;", "defaultPlatformRandom", "()Lkotlin/random/Random;", "Ljava/util/regex/MatchResult;", "matchResult", "", "name", "Lkotlin/text/MatchGroup;", "getMatchResultNamedGroup", "(Ljava/util/regex/MatchResult;Ljava/lang/String;)Lkotlin/text/MatchGroup;", "", "getSuppressed", "(Ljava/lang/Throwable;)Ljava/util/List;", "ReflectThrowable", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nPlatformImplementations.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PlatformImplementations.kt\nkotlin/internal/PlatformImplementations\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,79:1\n1#2:80\n*E\n"})
public class PlatformImplementations {
   public void addSuppressed(@NotNull Throwable cause, @NotNull Throwable exception) {
      Intrinsics.checkNotNullParameter(cause, "cause");
      Intrinsics.checkNotNullParameter(exception, "exception");
      Method var10000 = PlatformImplementations.ReflectThrowable.addSuppressed;
      if (var10000 != null) {
         Object[] var3 = new Object[]{exception};
         var10000.invoke(cause, var3);
      }

   }

   @NotNull
   public List getSuppressed(@NotNull Throwable exception) {
      Intrinsics.checkNotNullParameter(exception, "exception");
      Method var10000 = PlatformImplementations.ReflectThrowable.getSuppressed;
      List var6;
      if (var10000 != null) {
         Object var5 = var10000.invoke(exception);
         if (var5 != null) {
            Object it = var5;
            int var4 = 0;
            var6 = ArraysKt.asList((Throwable[])it);
            if (var6 != null) {
               return var6;
            }
         }
      }

      var6 = CollectionsKt.emptyList();
      return var6;
   }

   @Nullable
   public MatchGroup getMatchResultNamedGroup(@NotNull MatchResult matchResult, @NotNull String name) {
      Intrinsics.checkNotNullParameter(matchResult, "matchResult");
      Intrinsics.checkNotNullParameter(name, "name");
      throw new UnsupportedOperationException("Retrieving groups by name is not supported on this platform.");
   }

   @NotNull
   public Random defaultPlatformRandom() {
      return new FallbackThreadLocalRandom();
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0006¨\u0006\b"},
      d2 = {"Lkotlin/internal/PlatformImplementations$ReflectThrowable;", "", "<init>", "()V", "Ljava/lang/reflect/Method;", "addSuppressed", "Ljava/lang/reflect/Method;", "getSuppressed", "kotlin-stdlib"}
   )
   @SourceDebugExtension({"SMAP\nPlatformImplementations.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PlatformImplementations.kt\nkotlin/internal/PlatformImplementations$ReflectThrowable\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,79:1\n1#2:80\n*E\n"})
   private static final class ReflectThrowable {
      @NotNull
      public static final ReflectThrowable INSTANCE = new ReflectThrowable();
      @JvmField
      @Nullable
      public static final Method addSuppressed;
      @JvmField
      @Nullable
      public static final Method getSuppressed;

      static {
         Class throwableClass = Throwable.class;
         Method[] throwableMethods = throwableClass.getMethods();
         Intrinsics.checkNotNull(throwableMethods);
         Method[] var2 = throwableMethods;
         int var3 = 0;
         int var4 = throwableMethods.length;

         Method var14;
         while(true) {
            if (var3 >= var4) {
               var14 = null;
               break;
            }

            Method it;
            label36: {
               it = var2[var3];
               int var7 = 0;
               if (Intrinsics.areEqual((Object)it.getName(), (Object)"addSuppressed")) {
                  Class[] var10000 = it.getParameterTypes();
                  Intrinsics.checkNotNullExpressionValue(var10000, "getParameterTypes(...)");
                  if (Intrinsics.areEqual((Object)ArraysKt.singleOrNull(var10000), (Object)throwableClass)) {
                     var13 = true;
                     break label36;
                  }
               }

               var13 = false;
            }

            if (var13) {
               var14 = it;
               break;
            }

            ++var3;
         }

         addSuppressed = var14;
         var2 = throwableMethods;
         var3 = 0;
         var4 = throwableMethods.length;

         while(true) {
            if (var3 >= var4) {
               var14 = null;
               break;
            }

            Method it = var2[var3];
            int var12 = 0;
            if (Intrinsics.areEqual((Object)it.getName(), (Object)"getSuppressed")) {
               var14 = it;
               break;
            }

            ++var3;
         }

         getSuppressed = var14;
      }
   }
}
