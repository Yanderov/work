package kotlin.coroutines.jvm.internal;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bÂ\u0002\u0018\u00002\u00020\u0001:\u0001\u000fB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\n\u0010\u000bR\u0018\u0010\f\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\r¨\u0006\u0010"},
   d2 = {"Lkotlin/coroutines/jvm/internal/ModuleNameRetriever;", "", "<init>", "()V", "Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "continuation", "Lkotlin/coroutines/jvm/internal/ModuleNameRetriever$Cache;", "buildCache", "(Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;)Lkotlin/coroutines/jvm/internal/ModuleNameRetriever$Cache;", "", "getModuleName", "(Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;)Ljava/lang/String;", "cache", "Lkotlin/coroutines/jvm/internal/ModuleNameRetriever$Cache;", "notOnJava9", "Cache", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nDebugMetadata.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DebugMetadata.kt\nkotlin/coroutines/jvm/internal/ModuleNameRetriever\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,134:1\n1#2:135\n*E\n"})
final class ModuleNameRetriever {
   @NotNull
   public static final ModuleNameRetriever INSTANCE = new ModuleNameRetriever();
   @NotNull
   private static final Cache notOnJava9 = new Cache((Method)null, (Method)null, (Method)null);
   @Nullable
   private static Cache cache;

   private ModuleNameRetriever() {
   }

   @Nullable
   public final String getModuleName(@NotNull BaseContinuationImpl continuation) {
      Intrinsics.checkNotNullParameter(continuation, "continuation");
      Cache var10000 = ModuleNameRetriever.cache;
      if (var10000 == null) {
         var10000 = this.buildCache(continuation);
      }

      Cache cache = var10000;
      if (cache == notOnJava9) {
         return null;
      } else {
         Method var6 = cache.getModuleMethod;
         Object var7 = var6 != null ? var6.invoke(continuation.getClass()) : null;
         if (var7 == null) {
            return null;
         } else {
            Object module = var7;
            Method var8 = cache.getDescriptorMethod;
            Object var9 = var8 != null ? var8.invoke(module) : null;
            if (var9 == null) {
               return null;
            } else {
               Object descriptor = var9;
               Method var10 = cache.nameMethod;
               Object var5 = var10 != null ? var10.invoke(descriptor) : null;
               return var5 instanceof String ? (String)var5 : null;
            }
         }
      }
   }

   private final Cache buildCache(BaseContinuationImpl continuation) {
      try {
         Method getModuleMethod = Class.class.getDeclaredMethod("getModule");
         Class methodClass = continuation.getClass().getClassLoader().loadClass("java.lang.Module");
         Method getDescriptorMethod = methodClass.getDeclaredMethod("getDescriptor");
         Class moduleDescriptorClass = continuation.getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor");
         Method nameMethod = moduleDescriptorClass.getDeclaredMethod("name");
         Cache it = new Cache(getModuleMethod, getDescriptorMethod, nameMethod);
         int var9 = 0;
         ModuleNameRetriever var13 = INSTANCE;
         cache = it;
         return it;
      } catch (Exception var10) {
         Cache methodClass = notOnJava9;
         int moduleDescriptorClass = 0;
         ModuleNameRetriever var10000 = INSTANCE;
         cache = methodClass;
         return methodClass;
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\bR\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\bR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\b¨\u0006\t"},
      d2 = {"Lkotlin/coroutines/jvm/internal/ModuleNameRetriever$Cache;", "", "Ljava/lang/reflect/Method;", "getModuleMethod", "getDescriptorMethod", "nameMethod", "<init>", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "Ljava/lang/reflect/Method;", "kotlin-stdlib"}
   )
   private static final class Cache {
      @JvmField
      @Nullable
      public final Method getModuleMethod;
      @JvmField
      @Nullable
      public final Method getDescriptorMethod;
      @JvmField
      @Nullable
      public final Method nameMethod;

      public Cache(@Nullable Method getModuleMethod, @Nullable Method getDescriptorMethod, @Nullable Method nameMethod) {
         this.getModuleMethod = getModuleMethod;
         this.getDescriptorMethod = getDescriptorMethod;
         this.nameMethod = nameMethod;
      }
   }
}
