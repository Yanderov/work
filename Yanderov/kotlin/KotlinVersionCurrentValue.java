package kotlin;

import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
   d2 = {"Lkotlin/KotlinVersionCurrentValue;", "", "<init>", "()V", "Lkotlin/KotlinVersion;", "get", "()Lkotlin/KotlinVersion;", "kotlin-stdlib"}
)
final class KotlinVersionCurrentValue {
   @NotNull
   public static final KotlinVersionCurrentValue INSTANCE = new KotlinVersionCurrentValue();

   private KotlinVersionCurrentValue() {
   }

   @JvmStatic
   @NotNull
   public static final KotlinVersion get() {
      return new KotlinVersion(2, 0, 0);
   }
}
