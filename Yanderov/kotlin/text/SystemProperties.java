package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
   d2 = {"Lkotlin/text/SystemProperties;", "", "<init>", "()V", "", "LINE_SEPARATOR", "Ljava/lang/String;", "kotlin-stdlib"}
)
final class SystemProperties {
   @NotNull
   public static final SystemProperties INSTANCE = new SystemProperties();
   @JvmField
   @NotNull
   public static final String LINE_SEPARATOR;

   private SystemProperties() {
   }

   static {
      String var10000 = System.getProperty("line.separator");
      Intrinsics.checkNotNull(var10000);
      LINE_SEPARATOR = var10000;
   }
}
