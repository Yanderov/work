package kotlin.io.path;

import java.nio.file.Path;
import java.nio.file.Paths;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bR\u001c\u0010\n\u001a\n \t*\u0004\u0018\u00010\u00040\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\n \t*\u0004\u0018\u00010\u00040\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\u000b¨\u0006\r"},
   d2 = {"Lkotlin/io/path/PathRelativizer;", "", "<init>", "()V", "Ljava/nio/file/Path;", "path", "base", "tryRelativeTo", "(Ljava/nio/file/Path;Ljava/nio/file/Path;)Ljava/nio/file/Path;", "kotlin.jvm.PlatformType", "emptyPath", "Ljava/nio/file/Path;", "parentPath", "kotlin-stdlib-jdk7"}
)
final class PathRelativizer {
   @NotNull
   public static final PathRelativizer INSTANCE = new PathRelativizer();
   private static final Path emptyPath = Paths.get("");
   private static final Path parentPath = Paths.get("..");

   private PathRelativizer() {
   }

   @NotNull
   public final Path tryRelativeTo(@NotNull Path path, @NotNull Path base) {
      Intrinsics.checkNotNullParameter(path, "path");
      Intrinsics.checkNotNullParameter(base, "base");
      Path bn = base.normalize();
      Path pn = path.normalize();
      Path rn = bn.relativize(pn);
      int i = 0;

      for(int var7 = Math.min(bn.getNameCount(), pn.getNameCount()); i < var7 && Intrinsics.areEqual((Object)bn.getName(i), (Object)parentPath); ++i) {
         if (!Intrinsics.areEqual((Object)pn.getName(i), (Object)parentPath)) {
            throw new IllegalArgumentException("Unable to compute relative path");
         }
      }

      Path var10000;
      if (!Intrinsics.areEqual((Object)pn, (Object)bn) && Intrinsics.areEqual((Object)bn, (Object)emptyPath)) {
         var10000 = pn;
      } else {
         String rnString = rn.toString();
         String var10001 = rn.getFileSystem().getSeparator();
         Intrinsics.checkNotNullExpressionValue(var10001, "getSeparator(...)");
         var10000 = StringsKt.endsWith$default(rnString, var10001, false, 2, (Object)null) ? rn.getFileSystem().getPath(StringsKt.dropLast(rnString, rn.getFileSystem().getSeparator().length())) : rn;
      }

      Path r = var10000;
      Intrinsics.checkNotNull(r);
      return r;
   }
}
