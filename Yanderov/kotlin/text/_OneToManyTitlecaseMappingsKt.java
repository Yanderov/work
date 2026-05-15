package kotlin.text;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\f\n\u0002\u0010\f\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0013\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0000¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"},
   d2 = {"", "", "titlecaseImpl", "(C)Ljava/lang/String;", "kotlin-stdlib"}
)
public final class _OneToManyTitlecaseMappingsKt {
   @NotNull
   public static final String titlecaseImpl(char $this$titlecaseImpl) {
      String var10000 = String.valueOf($this$titlecaseImpl);
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type java.lang.String");
      var10000 = var10000.toUpperCase(Locale.ROOT);
      Intrinsics.checkNotNullExpressionValue(var10000, "toUpperCase(...)");
      String uppercase = var10000;
      if (uppercase.length() > 1) {
         if ($this$titlecaseImpl == 329) {
            var10000 = uppercase;
         } else {
            char var2 = uppercase.charAt(0);
            byte var4 = 1;
            Intrinsics.checkNotNull(uppercase, "null cannot be cast to non-null type java.lang.String");
            var10000 = uppercase.substring(var4);
            Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
            String var3 = var10000;
            Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type java.lang.String");
            var10000 = var3.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(var10000, "toLowerCase(...)");
            var3 = var10000;
            var10000 = var2 + var3;
         }

         return var10000;
      } else {
         return String.valueOf(Character.toTitleCase($this$titlecaseImpl));
      }
   }
}
