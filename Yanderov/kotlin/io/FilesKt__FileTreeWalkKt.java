package kotlin.io;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0011\u0010\u0006\u001a\u00020\u0003*\u00020\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0011\u0010\b\u001a\u00020\u0003*\u00020\u0000¢\u0006\u0004\b\b\u0010\u0007¨\u0006\t"},
   d2 = {"Ljava/io/File;", "Lkotlin/io/FileWalkDirection;", "direction", "Lkotlin/io/FileTreeWalk;", "walk", "(Ljava/io/File;Lkotlin/io/FileWalkDirection;)Lkotlin/io/FileTreeWalk;", "walkBottomUp", "(Ljava/io/File;)Lkotlin/io/FileTreeWalk;", "walkTopDown", "kotlin-stdlib"},
   xs = "kotlin/io/FilesKt"
)
class FilesKt__FileTreeWalkKt extends FilesKt__FileReadWriteKt {
   @NotNull
   public static final FileTreeWalk walk(@NotNull File $this$walk, @NotNull FileWalkDirection direction) {
      Intrinsics.checkNotNullParameter($this$walk, "<this>");
      Intrinsics.checkNotNullParameter(direction, "direction");
      return new FileTreeWalk($this$walk, direction);
   }

   // $FF: synthetic method
   public static FileTreeWalk walk$default(File var0, FileWalkDirection var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = FileWalkDirection.TOP_DOWN;
      }

      return FilesKt.walk(var0, var1);
   }

   @NotNull
   public static final FileTreeWalk walkTopDown(@NotNull File $this$walkTopDown) {
      Intrinsics.checkNotNullParameter($this$walkTopDown, "<this>");
      return FilesKt.walk($this$walkTopDown, FileWalkDirection.TOP_DOWN);
   }

   @NotNull
   public static final FileTreeWalk walkBottomUp(@NotNull File $this$walkBottomUp) {
      Intrinsics.checkNotNullParameter($this$walkBottomUp, "<this>");
      return FilesKt.walk($this$walkBottomUp, FileWalkDirection.BOTTOM_UP);
   }

   public FilesKt__FileTreeWalkKt() {
   }
}
