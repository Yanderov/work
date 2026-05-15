package kotlin.io.path;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001By\u0012\u001a\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u001a\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u001a\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u001c\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003¢\u0006\u0004\b\u000b\u0010\fJ!\u0010\u000f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0012\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0015\u0010\u0013J\u001f\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0016\u0010\u0010R*\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u0017R(\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0017R(\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0017R(\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0017¨\u0006\u0018"},
   d2 = {"Lkotlin/io/path/FileVisitorImpl;", "Ljava/nio/file/SimpleFileVisitor;", "Ljava/nio/file/Path;", "Lkotlin/Function2;", "Ljava/nio/file/attribute/BasicFileAttributes;", "Ljava/nio/file/FileVisitResult;", "onPreVisitDirectory", "onVisitFile", "Ljava/io/IOException;", "onVisitFileFailed", "onPostVisitDirectory", "<init>", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;)V", "dir", "exc", "postVisitDirectory", "(Ljava/nio/file/Path;Ljava/io/IOException;)Ljava/nio/file/FileVisitResult;", "attrs", "preVisitDirectory", "(Ljava/nio/file/Path;Ljava/nio/file/attribute/BasicFileAttributes;)Ljava/nio/file/FileVisitResult;", "file", "visitFile", "visitFileFailed", "Lkotlin/jvm/functions/Function2;", "kotlin-stdlib-jdk7"}
)
final class FileVisitorImpl extends SimpleFileVisitor {
   @Nullable
   private final Function2 onPreVisitDirectory;
   @Nullable
   private final Function2 onVisitFile;
   @Nullable
   private final Function2 onVisitFileFailed;
   @Nullable
   private final Function2 onPostVisitDirectory;

   public FileVisitorImpl(@Nullable Function2 onPreVisitDirectory, @Nullable Function2 onVisitFile, @Nullable Function2 onVisitFileFailed, @Nullable Function2 onPostVisitDirectory) {
      this.onPreVisitDirectory = onPreVisitDirectory;
      this.onVisitFile = onVisitFile;
      this.onVisitFileFailed = onVisitFileFailed;
      this.onPostVisitDirectory = onPostVisitDirectory;
   }

   @NotNull
   public FileVisitResult preVisitDirectory(@NotNull Path dir, @NotNull BasicFileAttributes attrs) {
      Intrinsics.checkNotNullParameter(dir, "dir");
      Intrinsics.checkNotNullParameter(attrs, "attrs");
      Function2 var10000 = this.onPreVisitDirectory;
      FileVisitResult var3;
      if (var10000 != null) {
         var3 = (FileVisitResult)var10000.invoke(dir, attrs);
         if (var3 != null) {
            return var3;
         }
      }

      var3 = super.preVisitDirectory(dir, attrs);
      Intrinsics.checkNotNullExpressionValue(var3, "preVisitDirectory(...)");
      return var3;
   }

   @NotNull
   public FileVisitResult visitFile(@NotNull Path file, @NotNull BasicFileAttributes attrs) {
      Intrinsics.checkNotNullParameter(file, "file");
      Intrinsics.checkNotNullParameter(attrs, "attrs");
      Function2 var10000 = this.onVisitFile;
      FileVisitResult var3;
      if (var10000 != null) {
         var3 = (FileVisitResult)var10000.invoke(file, attrs);
         if (var3 != null) {
            return var3;
         }
      }

      var3 = super.visitFile(file, attrs);
      Intrinsics.checkNotNullExpressionValue(var3, "visitFile(...)");
      return var3;
   }

   @NotNull
   public FileVisitResult visitFileFailed(@NotNull Path file, @NotNull IOException exc) {
      Intrinsics.checkNotNullParameter(file, "file");
      Intrinsics.checkNotNullParameter(exc, "exc");
      Function2 var10000 = this.onVisitFileFailed;
      FileVisitResult var3;
      if (var10000 != null) {
         var3 = (FileVisitResult)var10000.invoke(file, exc);
         if (var3 != null) {
            return var3;
         }
      }

      var3 = super.visitFileFailed(file, exc);
      Intrinsics.checkNotNullExpressionValue(var3, "visitFileFailed(...)");
      return var3;
   }

   @NotNull
   public FileVisitResult postVisitDirectory(@NotNull Path dir, @Nullable IOException exc) {
      Intrinsics.checkNotNullParameter(dir, "dir");
      Function2 var10000 = this.onPostVisitDirectory;
      FileVisitResult var3;
      if (var10000 != null) {
         var3 = (FileVisitResult)var10000.invoke(dir, exc);
         if (var3 != null) {
            return var3;
         }
      }

      var3 = super.postVisitDirectory(dir, exc);
      Intrinsics.checkNotNullExpressionValue(var3, "postVisitDirectory(...)");
      return var3;
   }
}
