package kotlin.io.path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a'\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0002¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0013\u0010\n\u001a\u00020\t*\u00020\bH\u0002¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"},
   d2 = {"Ljava/nio/file/Path;", "path", "", "Ljava/nio/file/LinkOption;", "linkOptions", "", "keyOf", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/lang/Object;", "Lkotlin/io/path/PathNode;", "", "createsCycle", "(Lkotlin/io/path/PathNode;)Z", "kotlin-stdlib-jdk7"}
)
public final class PathTreeWalkKt {
   private static final Object keyOf(Path path, LinkOption[] linkOptions) {
      Object var2;
      try {
         LinkOption[] var3 = (LinkOption[])Arrays.copyOf(linkOptions, linkOptions.length);
         BasicFileAttributes var10000 = Files.readAttributes(path, BasicFileAttributes.class, (LinkOption[])Arrays.copyOf(var3, var3.length));
         Intrinsics.checkNotNullExpressionValue(var10000, "readAttributes(...)");
         var2 = var10000.fileKey();
      } catch (Throwable var4) {
         var2 = null;
      }

      return var2;
   }

   private static final boolean createsCycle(PathNode $this$createsCycle) {
      for(PathNode ancestor = $this$createsCycle.getParent(); ancestor != null; ancestor = ancestor.getParent()) {
         if (ancestor.getKey() != null && $this$createsCycle.getKey() != null) {
            if (Intrinsics.areEqual(ancestor.getKey(), $this$createsCycle.getKey())) {
               return true;
            }
         } else {
            try {
               if (Files.isSameFile(ancestor.getPath(), $this$createsCycle.getPath())) {
                  return true;
               }
            } catch (IOException var3) {
            } catch (SecurityException var4) {
            }
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final boolean access$createsCycle(PathNode $receiver) {
      return createsCycle($receiver);
   }

   // $FF: synthetic method
   public static final Object access$keyOf(Path path, LinkOption[] linkOptions) {
      return keyOf(path, linkOptions);
   }
}
