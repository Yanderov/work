package kotlin.io.path;

import java.nio.file.FileSystemException;
import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0004\u0010\t¨\u0006\n"},
   d2 = {"Lkotlin/io/path/IllegalFileNameException;", "Ljava/nio/file/FileSystemException;", "Ljava/nio/file/Path;", "file", "<init>", "(Ljava/nio/file/Path;)V", "other", "", "message", "(Ljava/nio/file/Path;Ljava/nio/file/Path;Ljava/lang/String;)V", "kotlin-stdlib-jdk7"}
)
public final class IllegalFileNameException extends FileSystemException {
   public IllegalFileNameException(@NotNull Path file, @Nullable Path other, @Nullable String message) {
      Intrinsics.checkNotNullParameter(file, "file");
      super(file.toString(), other != null ? other.toString() : null, message);
   }

   public IllegalFileNameException(@NotNull Path file) {
      Intrinsics.checkNotNullParameter(file, "file");
      this(file, (Path)null, (String)null);
   }
}
