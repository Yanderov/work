package kotlin.io.path;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystemException;
import java.nio.file.FileSystemLoopException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.SecureDirectoryStream;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.SpreadBuilder;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a&\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0082\b¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001f\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\n\u0010\u000b\u001a)\u0010\u0011\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u000f\u0010\u0010\u001a&\u0010\u0015\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u00122\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0082\b¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u0013\u0010\u0016\u001a\u00020\u0003*\u00020\bH\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u001b\u0010\u001a\u001a\u00020\u0003*\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0018\u0010\u0019\u001a~\u0010(\u001a\u00020\b*\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2Q\b\u0002\u0010$\u001aK\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001b\u0012\u0017\u0012\u00150 j\u0002`!¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020#0\u001c2\u0006\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020%H\u0007¢\u0006\u0004\b(\u0010)\u001a»\u0001\u0010(\u001a\u00020\b*\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2Q\b\u0002\u0010$\u001aK\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001b\u0012\u0017\u0012\u00150 j\u0002`!¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020#0\u001c2\u0006\u0010&\u001a\u00020%2C\b\u0002\u0010-\u001a=\u0012\u0004\u0012\u00020*\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020+0\u001c¢\u0006\u0002\b,H\u0007¢\u0006\u0004\b(\u0010.\u001a\u0013\u0010/\u001a\u00020\u0003*\u00020\bH\u0007¢\u0006\u0004\b/\u0010\u0017\u001a\u001d\u00103\u001a\f\u0012\b\u0012\u00060 j\u0002`!00*\u00020\bH\u0002¢\u0006\u0004\b1\u00102\u001a)\u00107\u001a\u00020\u0003*\b\u0012\u0004\u0012\u00020\b042\u0006\u0010\u001e\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b5\u00106\u001a3\u0010:\u001a\u00020\u0003*\b\u0012\u0004\u0012\u00020\b042\u0006\u0010\u001e\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b8\u00109\u001a5\u0010A\u001a\u00020%*\b\u0012\u0004\u0012\u00020\b042\u0006\u0010;\u001a\u00020\b2\u0012\u0010>\u001a\n\u0012\u0006\b\u0001\u0012\u00020=0<\"\u00020=H\u0002¢\u0006\u0004\b?\u0010@\u001a\u0013\u0010E\u001a\u00020B*\u00020+H\u0003¢\u0006\u0004\bC\u0010D\u001a\u0013\u0010E\u001a\u00020B*\u00020#H\u0003¢\u0006\u0004\bC\u0010F¨\u0006G"},
   d2 = {"Lkotlin/io/path/ExceptionsCollector;", "collector", "Lkotlin/Function0;", "", "function", "collectIfThrows$PathsKt__PathRecursiveFunctionsKt", "(Lkotlin/io/path/ExceptionsCollector;Lkotlin/jvm/functions/Function0;)V", "collectIfThrows", "Ljava/nio/file/Path;", "path", "insecureEnterDirectory$PathsKt__PathRecursiveFunctionsKt", "(Ljava/nio/file/Path;Lkotlin/io/path/ExceptionsCollector;)V", "insecureEnterDirectory", "entry", "parent", "insecureHandleEntry$PathsKt__PathRecursiveFunctionsKt", "(Ljava/nio/file/Path;Ljava/nio/file/Path;Lkotlin/io/path/ExceptionsCollector;)V", "insecureHandleEntry", "R", "tryIgnoreNoSuchFileException$PathsKt__PathRecursiveFunctionsKt", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "tryIgnoreNoSuchFileException", "checkFileName", "(Ljava/nio/file/Path;)V", "checkNotSameAs$PathsKt__PathRecursiveFunctionsKt", "(Ljava/nio/file/Path;Ljava/nio/file/Path;)V", "checkNotSameAs", "target", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "source", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "Lkotlin/io/path/OnErrorResult;", "onError", "", "followLinks", "overwrite", "copyToRecursively", "(Ljava/nio/file/Path;Ljava/nio/file/Path;Lkotlin/jvm/functions/Function3;ZZ)Ljava/nio/file/Path;", "Lkotlin/io/path/CopyActionContext;", "Lkotlin/io/path/CopyActionResult;", "Lkotlin/ExtensionFunctionType;", "copyAction", "(Ljava/nio/file/Path;Ljava/nio/file/Path;Lkotlin/jvm/functions/Function3;ZLkotlin/jvm/functions/Function3;)Ljava/nio/file/Path;", "deleteRecursively", "", "deleteRecursivelyImpl$PathsKt__PathRecursiveFunctionsKt", "(Ljava/nio/file/Path;)Ljava/util/List;", "deleteRecursivelyImpl", "Ljava/nio/file/SecureDirectoryStream;", "enterDirectory$PathsKt__PathRecursiveFunctionsKt", "(Ljava/nio/file/SecureDirectoryStream;Ljava/nio/file/Path;Lkotlin/io/path/ExceptionsCollector;)V", "enterDirectory", "handleEntry$PathsKt__PathRecursiveFunctionsKt", "(Ljava/nio/file/SecureDirectoryStream;Ljava/nio/file/Path;Ljava/nio/file/Path;Lkotlin/io/path/ExceptionsCollector;)V", "handleEntry", "entryName", "", "Ljava/nio/file/LinkOption;", "options", "isDirectory$PathsKt__PathRecursiveFunctionsKt", "(Ljava/nio/file/SecureDirectoryStream;Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Z", "isDirectory", "Ljava/nio/file/FileVisitResult;", "toFileVisitResult$PathsKt__PathRecursiveFunctionsKt", "(Lkotlin/io/path/CopyActionResult;)Ljava/nio/file/FileVisitResult;", "toFileVisitResult", "(Lkotlin/io/path/OnErrorResult;)Ljava/nio/file/FileVisitResult;", "kotlin-stdlib-jdk7"},
   xs = "kotlin/io/path/PathsKt"
)
@SourceDebugExtension({"SMAP\nPathRecursiveFunctions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathRecursiveFunctions.kt\nkotlin/io/path/PathsKt__PathRecursiveFunctionsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,530:1\n376#1,2:534\n384#1:536\n384#1:537\n378#1,4:538\n376#1,2:542\n384#1:544\n378#1,4:545\n384#1:549\n376#1,6:550\n376#1,2:556\n384#1:558\n378#1,4:559\n1#2:531\n1863#3,2:532\n*S KotlinDebug\n*F\n+ 1 PathRecursiveFunctions.kt\nkotlin/io/path/PathsKt__PathRecursiveFunctionsKt\n*L\n392#1:534,2\n407#1:536\n410#1:537\n392#1:538,4\n418#1:542,2\n419#1:544\n418#1:545,4\n430#1:549\n438#1:550,6\n461#1:556,2\n462#1:558\n461#1:559,4\n314#1:532,2\n*E\n"})
class PathsKt__PathRecursiveFunctionsKt extends PathsKt__PathReadWriteKt {
   @ExperimentalPathApi
   @SinceKotlin(
      version = "1.8"
   )
   @NotNull
   public static final Path copyToRecursively(@NotNull Path $this$copyToRecursively, @NotNull Path target, @NotNull Function3 onError, final boolean followLinks, boolean overwrite) {
      Intrinsics.checkNotNullParameter($this$copyToRecursively, "<this>");
      Intrinsics.checkNotNullParameter(target, "target");
      Intrinsics.checkNotNullParameter(onError, "onError");
      return overwrite ? PathsKt.copyToRecursively($this$copyToRecursively, target, onError, followLinks, new Function3(followLinks) {
         public final CopyActionResult invoke(CopyActionContext $this$copyToRecursively, Path src, Path dst) {
            Intrinsics.checkNotNullParameter($this$copyToRecursively, "$this$copyToRecursively");
            Intrinsics.checkNotNullParameter(src, "src");
            Intrinsics.checkNotNullParameter(dst, "dst");
            LinkOption[] options = LinkFollowing.INSTANCE.toLinkOptions(followLinks);
            LinkOption[] var7 = new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
            boolean dstIsDirectory = Files.isDirectory(dst, (LinkOption[])Arrays.copyOf(var7, var7.length));
            LinkOption[] var10001 = (LinkOption[])Arrays.copyOf(options, options.length);
            boolean srcIsDirectory = Files.isDirectory(src, (LinkOption[])Arrays.copyOf(var10001, var10001.length));
            if (!srcIsDirectory || !dstIsDirectory) {
               if (dstIsDirectory) {
                  PathsKt.deleteRecursively(dst);
               }

               SpreadBuilder var8 = new SpreadBuilder(2);
               var8.addSpread(options);
               var8.add(StandardCopyOption.REPLACE_EXISTING);
               CopyOption[] var9 = (CopyOption[])var8.toArray(new CopyOption[var8.size()]);
               Intrinsics.checkNotNullExpressionValue(Files.copy(src, dst, (CopyOption[])Arrays.copyOf(var9, var9.length)), "copy(...)");
            }

            return CopyActionResult.CONTINUE;
         }
      }) : PathsKt.copyToRecursively$default($this$copyToRecursively, target, onError, followLinks, (Function3)null, 8, (Object)null);
   }

   // $FF: synthetic method
   public static Path copyToRecursively$default(Path var0, Path var1, Function3 var2, boolean var3, boolean var4, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         var2 = null.INSTANCE;
      }

      return PathsKt.copyToRecursively(var0, var1, var2, var3, var4);
   }

   @ExperimentalPathApi
   @SinceKotlin(
      version = "1.8"
   )
   @NotNull
   public static final Path copyToRecursively(@NotNull final Path $this$copyToRecursively, @NotNull final Path target, @NotNull final Function3 onError, boolean followLinks, @NotNull final Function3 copyAction) {
      Intrinsics.checkNotNullParameter($this$copyToRecursively, "<this>");
      Intrinsics.checkNotNullParameter(target, "target");
      Intrinsics.checkNotNullParameter(onError, "onError");
      Intrinsics.checkNotNullParameter(copyAction, "copyAction");
      LinkOption[] var6 = LinkFollowing.INSTANCE.toLinkOptions(followLinks);
      var6 = (LinkOption[])Arrays.copyOf(var6, var6.length);
      if (!Files.exists($this$copyToRecursively, (LinkOption[])Arrays.copyOf(var6, var6.length))) {
         throw new NoSuchFileException($this$copyToRecursively.toString(), target.toString(), "The source file doesn't exist.");
      } else {
         LinkOption[] var10001 = new LinkOption[0];
         if (Files.exists($this$copyToRecursively, (LinkOption[])Arrays.copyOf(var10001, var10001.length)) && (followLinks || !Files.isSymbolicLink($this$copyToRecursively))) {
            var10001 = new LinkOption[0];
            boolean targetExistsAndNotSymlink = Files.exists(target, (LinkOption[])Arrays.copyOf(var10001, var10001.length)) && !Files.isSymbolicLink(target);
            if (!targetExistsAndNotSymlink || !Files.isSameFile($this$copyToRecursively, target)) {
               boolean var13;
               if (!Intrinsics.areEqual((Object)$this$copyToRecursively.getFileSystem(), (Object)target.getFileSystem())) {
                  var13 = false;
               } else if (targetExistsAndNotSymlink) {
                  var13 = target.toRealPath().startsWith($this$copyToRecursively.toRealPath());
               } else {
                  Path var10000 = target.getParent();
                  if (var10000 == null) {
                     var13 = false;
                  } else {
                     Path it = var10000;
                     int var8 = 0;
                     var10001 = new LinkOption[0];
                     var13 = Files.exists(it, (LinkOption[])Arrays.copyOf(var10001, var10001.length)) && it.toRealPath().startsWith($this$copyToRecursively.toRealPath());
                  }
               }

               boolean isSubdirectory = var13;
               if (isSubdirectory) {
                  throw new FileSystemException($this$copyToRecursively.toString(), target.toString(), "Recursively copying a directory into its subdirectory is prohibited.");
               }
            }
         }

         final Path normalizedTarget = target.normalize();
         final ArrayList stack = new ArrayList();
         PathsKt.visitFileTree$default($this$copyToRecursively, 0, followLinks, new Function1(stack, copyAction, $this$copyToRecursively, target, normalizedTarget, onError) {
            public final void invoke(FileVisitorBuilder $this$visitFileTree) {
               Intrinsics.checkNotNullParameter($this$visitFileTree, "$this$visitFileTree");
               $this$visitFileTree.onPreVisitDirectory(new Function2(stack, copyAction, $this$copyToRecursively, target, normalizedTarget, onError) {
                  public final FileVisitResult invoke(Path directory, BasicFileAttributes attributes) {
                     Intrinsics.checkNotNullParameter(directory, "directory");
                     Intrinsics.checkNotNullParameter(attributes, "attributes");
                     FileVisitResult it = PathsKt__PathRecursiveFunctionsKt.copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(stack, copyAction, $this$copyToRecursively, target, normalizedTarget, onError, directory, attributes);
                     ArrayList var4 = stack;
                     int var6 = 0;
                     if (it == FileVisitResult.CONTINUE) {
                        var4.add(directory);
                     }

                     return it;
                  }
               });
               $this$visitFileTree.onVisitFile(new Function2(stack, copyAction, $this$copyToRecursively, target, normalizedTarget, onError) {
                  // $FF: synthetic field
                  final ArrayList $stack;
                  // $FF: synthetic field
                  final Function3 $copyAction;
                  // $FF: synthetic field
                  final Path $this_copyToRecursively;
                  // $FF: synthetic field
                  final Path $target;
                  // $FF: synthetic field
                  final Path $normalizedTarget;
                  // $FF: synthetic field
                  final Function3 $onError;

                  {
                     this.$stack = $stack;
                     this.$copyAction = $copyAction;
                     this.$this_copyToRecursively = $receiver;
                     this.$target = $target;
                     this.$normalizedTarget = $normalizedTarget;
                     this.$onError = $onError;
                  }

                  public final FileVisitResult invoke(Path p0, BasicFileAttributes p1) {
                     Intrinsics.checkNotNullParameter(p0, "p0");
                     Intrinsics.checkNotNullParameter(p1, "p1");
                     return PathsKt__PathRecursiveFunctionsKt.copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(this.$stack, this.$copyAction, this.$this_copyToRecursively, this.$target, this.$normalizedTarget, this.$onError, p0, p1);
                  }
               });
               $this$visitFileTree.onVisitFileFailed(new Function2(onError, $this$copyToRecursively, target, normalizedTarget) {
                  // $FF: synthetic field
                  final Function3 $onError;
                  // $FF: synthetic field
                  final Path $this_copyToRecursively;
                  // $FF: synthetic field
                  final Path $target;
                  // $FF: synthetic field
                  final Path $normalizedTarget;

                  {
                     this.$onError = $onError;
                     this.$this_copyToRecursively = $receiver;
                     this.$target = $target;
                     this.$normalizedTarget = $normalizedTarget;
                  }

                  public final FileVisitResult invoke(Path p0, Exception p1) {
                     Intrinsics.checkNotNullParameter(p0, "p0");
                     Intrinsics.checkNotNullParameter(p1, "p1");
                     return PathsKt__PathRecursiveFunctionsKt.copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt(this.$onError, this.$this_copyToRecursively, this.$target, this.$normalizedTarget, p0, p1);
                  }
               });
               $this$visitFileTree.onPostVisitDirectory(new Function2(stack, onError, $this$copyToRecursively, target, normalizedTarget) {
                  public final FileVisitResult invoke(Path directory, IOException exception) {
                     Intrinsics.checkNotNullParameter(directory, "directory");
                     CollectionsKt.removeLast((List)stack);
                     return exception == null ? FileVisitResult.CONTINUE : PathsKt__PathRecursiveFunctionsKt.copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt(onError, $this$copyToRecursively, target, normalizedTarget, directory, (Exception)exception);
                  }
               });
            }
         }, 1, (Object)null);
         return target;
      }
   }

   // $FF: synthetic method
   public static Path copyToRecursively$default(Path var0, Path var1, Function3 var2, final boolean var3, Function3 var4, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         var2 = null.INSTANCE;
      }

      if ((var5 & 8) != 0) {
         var4 = new Function3(var3) {
            public final CopyActionResult invoke(CopyActionContext $this$null, Path src, Path dst) {
               Intrinsics.checkNotNullParameter($this$null, "$this$null");
               Intrinsics.checkNotNullParameter(src, "src");
               Intrinsics.checkNotNullParameter(dst, "dst");
               return $this$null.copyToIgnoringExistingDirectory(src, dst, var3);
            }
         };
      }

      return PathsKt.copyToRecursively(var0, var1, var2, var3, var4);
   }

   @ExperimentalPathApi
   private static final FileVisitResult toFileVisitResult$PathsKt__PathRecursiveFunctionsKt(CopyActionResult $this$toFileVisitResult) {
      FileVisitResult var10000;
      switch (PathsKt__PathRecursiveFunctionsKt.WhenMappings.$EnumSwitchMapping$0[$this$toFileVisitResult.ordinal()]) {
         case 1:
            var10000 = FileVisitResult.CONTINUE;
            break;
         case 2:
            var10000 = FileVisitResult.TERMINATE;
            break;
         case 3:
            var10000 = FileVisitResult.SKIP_SUBTREE;
            break;
         default:
            throw new NoWhenBranchMatchedException();
      }

      return var10000;
   }

   @ExperimentalPathApi
   private static final FileVisitResult toFileVisitResult$PathsKt__PathRecursiveFunctionsKt(OnErrorResult $this$toFileVisitResult) {
      FileVisitResult var10000;
      switch (PathsKt__PathRecursiveFunctionsKt.WhenMappings.$EnumSwitchMapping$1[$this$toFileVisitResult.ordinal()]) {
         case 1:
            var10000 = FileVisitResult.TERMINATE;
            break;
         case 2:
            var10000 = FileVisitResult.SKIP_SUBTREE;
            break;
         default:
            throw new NoWhenBranchMatchedException();
      }

      return var10000;
   }

   @ExperimentalPathApi
   @SinceKotlin(
      version = "1.8"
   )
   public static final void deleteRecursively(@NotNull Path $this$deleteRecursively) {
      Intrinsics.checkNotNullParameter($this$deleteRecursively, "<this>");
      List suppressedExceptions = deleteRecursivelyImpl$PathsKt__PathRecursiveFunctionsKt($this$deleteRecursively);
      if (!((Collection)suppressedExceptions).isEmpty()) {
         FileSystemException var2 = new FileSystemException("Failed to delete one or more files. See suppressed exceptions for details.");
         FileSystemException $this$deleteRecursively_u24lambda_u242 = var2;
         int var4 = 0;
         Iterable $this$forEach$iv = (Iterable)suppressedExceptions;
         int $i$f$forEach = 0;

         for(Object element$iv : $this$forEach$iv) {
            Exception it = (Exception)element$iv;
            int var10 = 0;
            ExceptionsKt.addSuppressed((Throwable)$this$deleteRecursively_u24lambda_u242, (Throwable)it);
         }

         throw (Throwable)var2;
      }
   }

   private static final List deleteRecursivelyImpl$PathsKt__PathRecursiveFunctionsKt(Path $this$deleteRecursivelyImpl) {
      ExceptionsCollector collector = new ExceptionsCollector(0, 1, (DefaultConstructorMarker)null);
      boolean useInsecure = false;
      useInsecure = true;
      Path var10000 = $this$deleteRecursivelyImpl.getParent();
      if (var10000 != null) {
         Path parent = var10000;
         int var4 = 0;

         DirectoryStream directoryStream;
         try {
            directoryStream = Files.newDirectoryStream(parent);
         } catch (Throwable var15) {
            directoryStream = null;
         }

         if (directoryStream != null) {
            Closeable directoryStream = (Closeable)directoryStream;
            Throwable _ = null;

            try {
               DirectoryStream stream = (DirectoryStream)directoryStream;
               int var9 = 0;
               if (stream instanceof SecureDirectoryStream) {
                  useInsecure = false;
                  collector.setPath(parent);
                  SecureDirectoryStream var19 = (SecureDirectoryStream)stream;
                  Path var10001 = $this$deleteRecursivelyImpl.getFileName();
                  Intrinsics.checkNotNullExpressionValue(var10001, "getFileName(...)");
                  handleEntry$PathsKt__PathRecursiveFunctionsKt(var19, var10001, (Path)null, collector);
               }

               Unit var18 = Unit.INSTANCE;
            } catch (Throwable var13) {
               _ = var13;
               throw var13;
            } finally {
               CloseableKt.closeFinally(directoryStream, _);
            }
         }
      }

      if (useInsecure) {
         insecureHandleEntry$PathsKt__PathRecursiveFunctionsKt($this$deleteRecursivelyImpl, (Path)null, collector);
      }

      return collector.getCollectedExceptions();
   }

   private static final void collectIfThrows$PathsKt__PathRecursiveFunctionsKt(ExceptionsCollector collector, Function0 function) {
      int $i$f$collectIfThrows = 0;

      try {
         function.invoke();
      } catch (Exception exception) {
         collector.collect(exception);
      }

   }

   private static final Object tryIgnoreNoSuchFileException$PathsKt__PathRecursiveFunctionsKt(Function0 function) {
      int $i$f$tryIgnoreNoSuchFileException = 0;

      Object var2;
      try {
         var2 = function.invoke();
      } catch (NoSuchFileException var4) {
         var2 = null;
      }

      return var2;
   }

   private static final void handleEntry$PathsKt__PathRecursiveFunctionsKt(SecureDirectoryStream $this$handleEntry, Path name, Path parent, ExceptionsCollector collector) {
      collector.enterEntry(name);
      int $i$f$collectIfThrows = 0;

      try {
         int var5 = 0;
         if (parent != null) {
            Path var10000 = collector.getPath();
            Intrinsics.checkNotNull(var10000);
            Path entry = var10000;
            PathsKt.checkFileName(entry);
            checkNotSameAs$PathsKt__PathRecursiveFunctionsKt(entry, parent);
         }

         LinkOption[] preEnterTotalExceptions = new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
         if (isDirectory$PathsKt__PathRecursiveFunctionsKt($this$handleEntry, name, preEnterTotalExceptions)) {
            int preEnterTotalExceptions = collector.getTotalExceptions();
            enterDirectory$PathsKt__PathRecursiveFunctionsKt($this$handleEntry, name, collector);
            if (preEnterTotalExceptions == collector.getTotalExceptions()) {
               int $i$f$tryIgnoreNoSuchFileException = 0;

               try {
                  int var8 = 0;
                  $this$handleEntry.deleteDirectory(name);
                  Unit var21 = Unit.INSTANCE;
               } catch (NoSuchFileException var13) {
                  Unit var9 = null;
               }
            }
         } else {
            int $i$f$tryIgnoreNoSuchFileException = 0;

            try {
               int var18 = 0;
               $this$handleEntry.deleteFile(name);
               Unit var20 = Unit.INSTANCE;
            } catch (NoSuchFileException var12) {
               Object var19 = null;
            }
         }
      } catch (Exception exception$iv) {
         collector.collect(exception$iv);
      }

      collector.exitEntry(name);
   }

   private static final void enterDirectory$PathsKt__PathRecursiveFunctionsKt(SecureDirectoryStream $this$enterDirectory, Path name, ExceptionsCollector collector) {
      int $i$f$collectIfThrows = 0;

      try {
         int var4 = 0;
         int $i$f$tryIgnoreNoSuchFileException = 0;

         SecureDirectoryStream var8;
         try {
            int var6 = 0;
            LinkOption[] var7 = new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
            var8 = $this$enterDirectory.newDirectoryStream(name, var7);
         } catch (NoSuchFileException var17) {
            var8 = null;
         }

         if (var8 != null) {
            Closeable var21 = (Closeable)var8;
            Throwable var22 = null;

            try {
               SecureDirectoryStream directoryStream = (SecureDirectoryStream)var21;
               int var25 = 0;

               for(Path entry : directoryStream) {
                  Path var10001 = entry.getFileName();
                  Intrinsics.checkNotNullExpressionValue(var10001, "getFileName(...)");
                  handleEntry$PathsKt__PathRecursiveFunctionsKt(directoryStream, var10001, collector.getPath(), collector);
               }

               Unit var24 = Unit.INSTANCE;
            } catch (Throwable var18) {
               var22 = var18;
               throw var18;
            } finally {
               CloseableKt.closeFinally(var21, var22);
            }
         }
      } catch (Exception exception$iv) {
         collector.collect(exception$iv);
      }

   }

   private static final boolean isDirectory$PathsKt__PathRecursiveFunctionsKt(SecureDirectoryStream $this$isDirectory, Path entryName, LinkOption... options) {
      int $i$f$tryIgnoreNoSuchFileException = 0;

      Boolean var5;
      try {
         int var4 = 0;
         var5 = ((BasicFileAttributeView)$this$isDirectory.getFileAttributeView(entryName, BasicFileAttributeView.class, (LinkOption[])Arrays.copyOf(options, options.length))).readAttributes().isDirectory();
      } catch (NoSuchFileException var7) {
         var5 = null;
      }

      return var5 != null ? var5 : false;
   }

   private static final void insecureHandleEntry$PathsKt__PathRecursiveFunctionsKt(Path entry, Path parent, ExceptionsCollector collector) {
      int $i$f$collectIfThrows = 0;

      try {
         int var4 = 0;
         if (parent != null) {
            PathsKt.checkFileName(entry);
            checkNotSameAs$PathsKt__PathRecursiveFunctionsKt(entry, parent);
         }

         LinkOption[] var6 = new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
         if (Files.isDirectory(entry, (LinkOption[])Arrays.copyOf(var6, var6.length))) {
            int preEnterTotalExceptions = collector.getTotalExceptions();
            insecureEnterDirectory$PathsKt__PathRecursiveFunctionsKt(entry, collector);
            if (preEnterTotalExceptions == collector.getTotalExceptions()) {
               Files.deleteIfExists(entry);
            }
         } else {
            Files.deleteIfExists(entry);
         }
      } catch (Exception exception$iv) {
         collector.collect(exception$iv);
      }

   }

   private static final void insecureEnterDirectory$PathsKt__PathRecursiveFunctionsKt(Path path, ExceptionsCollector collector) {
      int $i$f$collectIfThrows = 0;

      try {
         int var3 = 0;
         int $i$f$tryIgnoreNoSuchFileException = 0;

         DirectoryStream directoryStream;
         try {
            int var5 = 0;
            directoryStream = Files.newDirectoryStream(path);
         } catch (NoSuchFileException var16) {
            directoryStream = null;
         }

         if (directoryStream != null) {
            Closeable var20 = (Closeable)directoryStream;
            Throwable _$iv = null;

            try {
               directoryStream = (DirectoryStream)var20;
               int var8 = 0;

               for(Path entry : directoryStream) {
                  Intrinsics.checkNotNull(entry);
                  insecureHandleEntry$PathsKt__PathRecursiveFunctionsKt(entry, path, collector);
               }

               Unit var23 = Unit.INSTANCE;
            } catch (Throwable var17) {
               _$iv = var17;
               throw var17;
            } finally {
               CloseableKt.closeFinally(var20, _$iv);
            }
         }
      } catch (Exception exception$iv) {
         collector.collect(exception$iv);
      }

   }

   public static final void checkFileName(@NotNull Path $this$checkFileName) {
      Intrinsics.checkNotNullParameter($this$checkFileName, "<this>");
      switch (fileName) {
         case ".":
            throw new IllegalFileNameException($this$checkFileName);
         case "..":
            throw new IllegalFileNameException($this$checkFileName);
         case "./":
            throw new IllegalFileNameException($this$checkFileName);
         case ".\\":
            throw new IllegalFileNameException($this$checkFileName);
         case "../":
            throw new IllegalFileNameException($this$checkFileName);
         case "..\\":
            throw new IllegalFileNameException($this$checkFileName);
      }

   }

   private static final void checkNotSameAs$PathsKt__PathRecursiveFunctionsKt(Path $this$checkNotSameAs, Path parent) {
      if (!Files.isSymbolicLink($this$checkNotSameAs) && Files.isSameFile($this$checkNotSameAs, parent)) {
         throw new FileSystemLoopException($this$checkNotSameAs.toString());
      }
   }

   private static final Path copyToRecursively$destination$PathsKt__PathRecursiveFunctionsKt(Path $this_copyToRecursively, Path $target, Path normalizedTarget, Path source) {
      Path relativePath = PathsKt.relativeTo(source, $this_copyToRecursively);
      Path destination = $target.resolve(relativePath.toString());
      if (!destination.normalize().startsWith(normalizedTarget)) {
         throw new IllegalFileNameException(source, destination, "Copying files to outside the specified target directory is prohibited. The directory being recursively copied might contain an entry with an illegal name.");
      } else {
         Intrinsics.checkNotNull(destination);
         return destination;
      }
   }

   private static final FileVisitResult copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt(Function3 $onError, Path $this_copyToRecursively, Path $target, Path normalizedTarget, Path source, Exception exception) {
      return toFileVisitResult$PathsKt__PathRecursiveFunctionsKt((OnErrorResult)$onError.invoke(source, copyToRecursively$destination$PathsKt__PathRecursiveFunctionsKt($this_copyToRecursively, $target, normalizedTarget, source), exception));
   }

   private static final FileVisitResult copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(ArrayList stack, Function3 $copyAction, Path $this_copyToRecursively, Path $target, Path normalizedTarget, Function3 $onError, Path source, BasicFileAttributes attributes) {
      FileVisitResult var8;
      try {
         if (!((Collection)stack).isEmpty()) {
            PathsKt.checkFileName(source);
            checkNotSameAs$PathsKt__PathRecursiveFunctionsKt(source, (Path)CollectionsKt.last((List)stack));
         }

         var8 = toFileVisitResult$PathsKt__PathRecursiveFunctionsKt((CopyActionResult)$copyAction.invoke(DefaultCopyActionContext.INSTANCE, source, copyToRecursively$destination$PathsKt__PathRecursiveFunctionsKt($this_copyToRecursively, $target, normalizedTarget, source)));
      } catch (Exception exception) {
         var8 = copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt($onError, $this_copyToRecursively, $target, normalizedTarget, source, exception);
      }

      return var8;
   }

   public PathsKt__PathRecursiveFunctionsKt() {
   }

   // $FF: synthetic class
   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public class WhenMappings {
      // $FF: synthetic field
      public static final int[] $EnumSwitchMapping$0;
      // $FF: synthetic field
      public static final int[] $EnumSwitchMapping$1;

      static {
         int[] var0 = new int[CopyActionResult.values().length];

         try {
            var0[CopyActionResult.CONTINUE.ordinal()] = 1;
         } catch (NoSuchFieldError var6) {
         }

         try {
            var0[CopyActionResult.TERMINATE.ordinal()] = 2;
         } catch (NoSuchFieldError var5) {
         }

         try {
            var0[CopyActionResult.SKIP_SUBTREE.ordinal()] = 3;
         } catch (NoSuchFieldError var4) {
         }

         $EnumSwitchMapping$0 = var0;
         var0 = new int[OnErrorResult.values().length];

         try {
            var0[OnErrorResult.TERMINATE.ordinal()] = 1;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[OnErrorResult.SKIP_SUBTREE.ordinal()] = 2;
         } catch (NoSuchFieldError var2) {
         }

         $EnumSwitchMapping$1 = var0;
      }
   }
}
