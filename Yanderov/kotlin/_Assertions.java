package kotlin;

import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u0012\u0004\b\u0007\u0010\u0003¨\u0006\b"},
   d2 = {"Lkotlin/_Assertions;", "", "<init>", "()V", "", "ENABLED", "Z", "getENABLED$annotations", "kotlin-stdlib"}
)
@PublishedApi
public final class _Assertions {
   @NotNull
   public static final _Assertions INSTANCE = new _Assertions();
   @JvmField
   public static final boolean ENABLED;

   private _Assertions() {
   }

   /** @deprecated */
   // $FF: synthetic method
   @PublishedApi
   public static void getENABLED$annotations() {
   }

   static {
      ENABLED = INSTANCE.getClass().desiredAssertionStatus();
   }
}
