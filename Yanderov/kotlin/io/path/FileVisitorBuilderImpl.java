package kotlin.io.path;

import java.nio.file.FileVisitor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\t\u0010\u0003J!\u0010\u000e\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJI\u0010\u0016\u001a\u00020\b28\u0010\u000b\u001a4\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\r\u0012\u0004\b\b(\u0012\u0012\u0015\u0012\u0013\u0018\u00010\u0013¢\u0006\f\b\u0011\u0012\b\b\r\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\u0010H\u0016¢\u0006\u0004\b\u0016\u0010\u0017JG\u0010\u001a\u001a\u00020\b26\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\r\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0011\u0012\b\b\r\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00150\u0010H\u0016¢\u0006\u0004\b\u001a\u0010\u0017JG\u0010\u001c\u001a\u00020\b26\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\r\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0011\u0012\b\b\r\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00150\u0010H\u0016¢\u0006\u0004\b\u001c\u0010\u0017JG\u0010\u001d\u001a\u00020\b26\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\r\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0011\u0012\b\b\r\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\u0010H\u0016¢\u0006\u0004\b\u001d\u0010\u0017R\u0016\u0010\u001f\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 R,\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0013\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010!R*\u0010\u001a\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010!R*\u0010\u001c\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010!R*\u0010\u001d\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010!¨\u0006\""},
   d2 = {"Lkotlin/io/path/FileVisitorBuilderImpl;", "Lkotlin/io/path/FileVisitorBuilder;", "<init>", "()V", "Ljava/nio/file/FileVisitor;", "Ljava/nio/file/Path;", "build", "()Ljava/nio/file/FileVisitor;", "", "checkIsNotBuilt", "", "function", "", "name", "checkNotDefined", "(Ljava/lang/Object;Ljava/lang/String;)V", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "directory", "Ljava/io/IOException;", "exception", "Ljava/nio/file/FileVisitResult;", "onPostVisitDirectory", "(Lkotlin/jvm/functions/Function2;)V", "Ljava/nio/file/attribute/BasicFileAttributes;", "attributes", "onPreVisitDirectory", "file", "onVisitFile", "onVisitFileFailed", "", "isBuilt", "Z", "Lkotlin/jvm/functions/Function2;", "kotlin-stdlib-jdk7"}
)
@ExperimentalPathApi
public final class FileVisitorBuilderImpl implements FileVisitorBuilder {
   @Nullable
   private Function2 onPreVisitDirectory;
   @Nullable
   private Function2 onVisitFile;
   @Nullable
   private Function2 onVisitFileFailed;
   @Nullable
   private Function2 onPostVisitDirectory;
   private boolean isBuilt;

   public void onPreVisitDirectory(@NotNull Function2 function) {
      Intrinsics.checkNotNullParameter(function, "function");
      this.checkIsNotBuilt();
      this.checkNotDefined(this.onPreVisitDirectory, "onPreVisitDirectory");
      this.onPreVisitDirectory = function;
   }

   public void onVisitFile(@NotNull Function2 function) {
      Intrinsics.checkNotNullParameter(function, "function");
      this.checkIsNotBuilt();
      this.checkNotDefined(this.onVisitFile, "onVisitFile");
      this.onVisitFile = function;
   }

   public void onVisitFileFailed(@NotNull Function2 function) {
      Intrinsics.checkNotNullParameter(function, "function");
      this.checkIsNotBuilt();
      this.checkNotDefined(this.onVisitFileFailed, "onVisitFileFailed");
      this.onVisitFileFailed = function;
   }

   public void onPostVisitDirectory(@NotNull Function2 function) {
      Intrinsics.checkNotNullParameter(function, "function");
      this.checkIsNotBuilt();
      this.checkNotDefined(this.onPostVisitDirectory, "onPostVisitDirectory");
      this.onPostVisitDirectory = function;
   }

   @NotNull
   public final FileVisitor build() {
      this.checkIsNotBuilt();
      this.isBuilt = true;
      return (FileVisitor)(new FileVisitorImpl(this.onPreVisitDirectory, this.onVisitFile, this.onVisitFileFailed, this.onPostVisitDirectory));
   }

   private final void checkIsNotBuilt() {
      if (this.isBuilt) {
         throw new IllegalStateException("This builder was already built");
      }
   }

   private final void checkNotDefined(Object function, String name) {
      if (function != null) {
         throw new IllegalStateException(name + " was already defined");
      }
   }
}
