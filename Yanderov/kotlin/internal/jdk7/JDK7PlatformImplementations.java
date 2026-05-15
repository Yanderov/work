package kotlin.internal.jdk7;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.internal.PlatformImplementations;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0010\u0018\u00002\u00020\u0001:\u0001\u0012B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\b\u0010\tJ\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\n2\u0006\u0010\u0006\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0010\u0010\u0011¨\u0006\u0013"},
   d2 = {"Lkotlin/internal/jdk7/JDK7PlatformImplementations;", "Lkotlin/internal/PlatformImplementations;", "<init>", "()V", "", "cause", "exception", "", "addSuppressed", "(Ljava/lang/Throwable;Ljava/lang/Throwable;)V", "", "getSuppressed", "(Ljava/lang/Throwable;)Ljava/util/List;", "", "version", "", "sdkIsNullOrAtLeast", "(I)Z", "ReflectSdkVersion", "kotlin-stdlib-jdk7"}
)
public class JDK7PlatformImplementations extends PlatformImplementations {
   private final boolean sdkIsNullOrAtLeast(int version) {
      return JDK7PlatformImplementations.ReflectSdkVersion.sdkVersion == null || JDK7PlatformImplementations.ReflectSdkVersion.sdkVersion >= version;
   }

   public void addSuppressed(@NotNull Throwable cause, @NotNull Throwable exception) {
      Intrinsics.checkNotNullParameter(cause, "cause");
      Intrinsics.checkNotNullParameter(exception, "exception");
      if (this.sdkIsNullOrAtLeast(19)) {
         cause.addSuppressed(exception);
      } else {
         super.addSuppressed(cause, exception);
      }

   }

   @NotNull
   public List getSuppressed(@NotNull Throwable exception) {
      Intrinsics.checkNotNullParameter(exception, "exception");
      List var2;
      if (this.sdkIsNullOrAtLeast(19)) {
         Throwable[] var10000 = exception.getSuppressed();
         Intrinsics.checkNotNullExpressionValue(var10000, "getSuppressed(...)");
         var2 = ArraysKt.asList(var10000);
      } else {
         var2 = super.getSuppressed(exception);
      }

      return var2;
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
      d2 = {"Lkotlin/internal/jdk7/JDK7PlatformImplementations$ReflectSdkVersion;", "", "<init>", "()V", "", "sdkVersion", "Ljava/lang/Integer;", "kotlin-stdlib-jdk7"}
   )
   @SourceDebugExtension({"SMAP\nJDK7PlatformImplementations.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JDK7PlatformImplementations.kt\nkotlin/internal/jdk7/JDK7PlatformImplementations$ReflectSdkVersion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,38:1\n1#2:39\n*E\n"})
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
