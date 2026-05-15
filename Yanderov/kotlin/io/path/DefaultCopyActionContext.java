package kotlin.io.path;

import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J#\u0010\t\u001a\u00020\b*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"},
   d2 = {"Lkotlin/io/path/DefaultCopyActionContext;", "Lkotlin/io/path/CopyActionContext;", "<init>", "()V", "Ljava/nio/file/Path;", "target", "", "followLinks", "Lkotlin/io/path/CopyActionResult;", "copyToIgnoringExistingDirectory", "(Ljava/nio/file/Path;Ljava/nio/file/Path;Z)Lkotlin/io/path/CopyActionResult;", "kotlin-stdlib-jdk7"}
)
@ExperimentalPathApi
final class DefaultCopyActionContext implements CopyActionContext {
   @NotNull
   public static final DefaultCopyActionContext INSTANCE = new DefaultCopyActionContext();

   private DefaultCopyActionContext() {
   }

   @NotNull
   public CopyActionResult copyToIgnoringExistingDirectory(@NotNull Path $this$copyToIgnoringExistingDirectory, @NotNull Path target, boolean followLinks) {
      Intrinsics.checkNotNullParameter($this$copyToIgnoringExistingDirectory, "<this>");
      Intrinsics.checkNotNullParameter(target, "target");
      LinkOption[] options = LinkFollowing.INSTANCE.toLinkOptions(followLinks);
      LinkOption[] var10001 = (LinkOption[])Arrays.copyOf(options, options.length);
      if (Files.isDirectory($this$copyToIgnoringExistingDirectory, (LinkOption[])Arrays.copyOf(var10001, var10001.length))) {
         LinkOption[] var6 = new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
         if (Files.isDirectory(target, (LinkOption[])Arrays.copyOf(var6, var6.length))) {
            return CopyActionResult.CONTINUE;
         }
      }

      CopyOption[] var10002 = (CopyOption[])Arrays.copyOf(options, options.length);
      Intrinsics.checkNotNullExpressionValue(Files.copy($this$copyToIgnoringExistingDirectory, target, (CopyOption[])Arrays.copyOf(var10002, var10002.length)), "copy(...)");
      return CopyActionResult.CONTINUE;
   }
}
