package kotlin.io.path;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"},
   d2 = {"Lkotlin/io/path/CopyActionResult;", "", "<init>", "(Ljava/lang/String;I)V", "CONTINUE", "SKIP_SUBTREE", "TERMINATE", "kotlin-stdlib-jdk7"}
)
@ExperimentalPathApi
@SinceKotlin(
   version = "1.8"
)
public enum CopyActionResult {
   CONTINUE,
   SKIP_SUBTREE,
   TERMINATE;

   // $FF: synthetic field
   private static final EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

   @NotNull
   public static EnumEntries getEntries() {
      return $ENTRIES;
   }

   // $FF: synthetic method
   private static final CopyActionResult[] $values() {
      CopyActionResult[] var0 = new CopyActionResult[]{CONTINUE, SKIP_SUBTREE, TERMINATE};
      return var0;
   }
}
