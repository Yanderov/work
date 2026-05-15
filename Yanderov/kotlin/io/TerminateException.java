package kotlin.io;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"},
   d2 = {"Lkotlin/io/TerminateException;", "Lkotlin/io/FileSystemException;", "Ljava/io/File;", "file", "<init>", "(Ljava/io/File;)V", "kotlin-stdlib"}
)
final class TerminateException extends FileSystemException {
   public TerminateException(@NotNull File file) {
      Intrinsics.checkNotNullParameter(file, "file");
      super(file, (File)null, (String)null, 6, (DefaultConstructorMarker)null);
   }
}
