package kotlin.text.jdk8;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchGroup;
import kotlin.text.MatchGroupCollection;
import kotlin.text.MatchNamedGroupCollection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"},
   d2 = {"Lkotlin/text/MatchGroupCollection;", "", "name", "Lkotlin/text/MatchGroup;", "get", "(Lkotlin/text/MatchGroupCollection;Ljava/lang/String;)Lkotlin/text/MatchGroup;", "kotlin-stdlib"},
   pn = ""
)
@JvmName(
   name = "RegexExtensionsJDK8Kt"
)
public final class RegexExtensionsJDK8Kt {
   @SinceKotlin(
      version = "1.2"
   )
   @Nullable
   public static final MatchGroup get(@NotNull MatchGroupCollection $this$get, @NotNull String name) {
      Intrinsics.checkNotNullParameter($this$get, "<this>");
      Intrinsics.checkNotNullParameter(name, "name");
      MatchNamedGroupCollection var10000 = $this$get instanceof MatchNamedGroupCollection ? (MatchNamedGroupCollection)$this$get : null;
      if (($this$get instanceof MatchNamedGroupCollection ? (MatchNamedGroupCollection)$this$get : null) == null) {
         throw new UnsupportedOperationException("Retrieving groups by name is not supported on this platform.");
      } else {
         MatchNamedGroupCollection namedGroups = var10000;
         return namedGroups.get(name);
      }
   }
}
