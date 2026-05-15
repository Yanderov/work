package kotlin.reflect;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a-\u0010\u0004\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0000H\u0007¢\u0006\u0004\b\u0004\u0010\u0005\u001a/\u0010\u0006\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0000H\u0007¢\u0006\u0004\b\u0006\u0010\u0005¨\u0006\u0007"},
   d2 = {"", "T", "Lkotlin/reflect/KClass;", "value", "cast", "(Lkotlin/reflect/KClass;Ljava/lang/Object;)Ljava/lang/Object;", "safeCast", "kotlin-stdlib"}
)
@JvmName(
   name = "KClasses"
)
@SourceDebugExtension({"SMAP\nKClasses.kt\nKotlin\n*S Kotlin\n*F\n+ 1 KClasses.kt\nkotlin/reflect/KClasses\n+ 2 KClassesImpl.kt\nkotlin/reflect/KClassesImplKt\n*L\n1#1,46:1\n9#2:47\n*S KotlinDebug\n*F\n+ 1 KClasses.kt\nkotlin/reflect/KClasses\n*L\n25#1:47\n*E\n"})
public final class KClasses {
   @SinceKotlin(
      version = "1.4"
   )
   @LowPriorityInOverloadResolution
   @NotNull
   public static final Object cast(@NotNull KClass $this$cast, @Nullable Object value) {
      Intrinsics.checkNotNullParameter($this$cast, "<this>");
      if (!$this$cast.isInstance(value)) {
         StringBuilder var10002 = (new StringBuilder()).append("Value cannot be cast to ");
         int $i$f$getQualifiedOrSimpleName = 0;
         throw new ClassCastException(var10002.append($this$cast.getQualifiedName()).toString());
      } else {
         Intrinsics.checkNotNull(value, "null cannot be cast to non-null type T of kotlin.reflect.KClasses.cast");
         return value;
      }
   }

   @SinceKotlin(
      version = "1.4"
   )
   @LowPriorityInOverloadResolution
   @Nullable
   public static final Object safeCast(@NotNull KClass $this$safeCast, @Nullable Object value) {
      Intrinsics.checkNotNullParameter($this$safeCast, "<this>");
      Object var10000;
      if ($this$safeCast.isInstance(value)) {
         Intrinsics.checkNotNull(value, "null cannot be cast to non-null type T of kotlin.reflect.KClasses.safeCast");
         var10000 = value;
      } else {
         var10000 = null;
      }

      return var10000;
   }
}
