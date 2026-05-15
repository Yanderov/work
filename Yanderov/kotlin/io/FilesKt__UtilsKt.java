package kotlin.io;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000>\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\u001a1\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0001\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0004\b\u0005\u0010\u0006\u001a1\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\u0001\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0004\b\u0007\u0010\u0006\u001a?\u0010\u000f\u001a\u00020\t*\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\t2\u001a\b\u0002\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0004\b\u000f\u0010\u0010\u001a-\u0010\u0013\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u0011\u0010\u0015\u001a\u00020\t*\u00020\u0003¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u0019\u0010\u0018\u001a\u00020\t*\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0003¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u0019\u0010\u0018\u001a\u00020\t*\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0000¢\u0006\u0004\b\u0018\u0010\u001a\u001a\u0011\u0010\u001b\u001a\u00020\u0003*\u00020\u0003¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u001f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00030\u001d*\b\u0012\u0004\u0012\u00020\u00030\u001dH\u0002¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u0013\u0010\u001b\u001a\u00020 *\u00020 H\u0002¢\u0006\u0004\b\u001e\u0010!\u001a\u0019\u0010#\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\"\u001a\u00020\u0003¢\u0006\u0004\b#\u0010$\u001a\u001b\u0010%\u001a\u0004\u0018\u00010\u0003*\u00020\u00032\u0006\u0010\"\u001a\u00020\u0003¢\u0006\u0004\b%\u0010$\u001a\u0019\u0010&\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\"\u001a\u00020\u0003¢\u0006\u0004\b&\u0010$\u001a\u0019\u0010(\u001a\u00020\u0003*\u00020\u00032\u0006\u0010'\u001a\u00020\u0003¢\u0006\u0004\b(\u0010$\u001a\u0019\u0010(\u001a\u00020\u0003*\u00020\u00032\u0006\u0010'\u001a\u00020\u0000¢\u0006\u0004\b(\u0010)\u001a\u0019\u0010*\u001a\u00020\u0003*\u00020\u00032\u0006\u0010'\u001a\u00020\u0003¢\u0006\u0004\b*\u0010$\u001a\u0019\u0010*\u001a\u00020\u0003*\u00020\u00032\u0006\u0010'\u001a\u00020\u0000¢\u0006\u0004\b*\u0010)\u001a\u0019\u0010+\u001a\u00020\t*\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0003¢\u0006\u0004\b+\u0010\u0019\u001a\u0019\u0010+\u001a\u00020\t*\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0000¢\u0006\u0004\b+\u0010\u001a\u001a\u0019\u0010,\u001a\u00020\u0000*\u00020\u00032\u0006\u0010\"\u001a\u00020\u0003¢\u0006\u0004\b,\u0010-\u001a\u001d\u0010/\u001a\u0004\u0018\u00010\u0000*\u00020\u00032\u0006\u0010\"\u001a\u00020\u0003H\u0002¢\u0006\u0004\b.\u0010-\"\u0015\u00102\u001a\u00020\u0000*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b0\u00101\"\u0015\u00104\u001a\u00020\u0000*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b3\u00101\"\u0015\u00106\u001a\u00020\u0000*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b5\u00101¨\u00067"},
   d2 = {"", "prefix", "suffix", "Ljava/io/File;", "directory", "createTempDir", "(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;)Ljava/io/File;", "createTempFile", "target", "", "overwrite", "Lkotlin/Function2;", "Ljava/io/IOException;", "Lkotlin/io/OnErrorAction;", "onError", "copyRecursively", "(Ljava/io/File;Ljava/io/File;ZLkotlin/jvm/functions/Function2;)Z", "", "bufferSize", "copyTo", "(Ljava/io/File;Ljava/io/File;ZI)Ljava/io/File;", "deleteRecursively", "(Ljava/io/File;)Z", "other", "endsWith", "(Ljava/io/File;Ljava/io/File;)Z", "(Ljava/io/File;Ljava/lang/String;)Z", "normalize", "(Ljava/io/File;)Ljava/io/File;", "", "normalize$FilesKt__UtilsKt", "(Ljava/util/List;)Ljava/util/List;", "Lkotlin/io/FilePathComponents;", "(Lkotlin/io/FilePathComponents;)Lkotlin/io/FilePathComponents;", "base", "relativeTo", "(Ljava/io/File;Ljava/io/File;)Ljava/io/File;", "relativeToOrNull", "relativeToOrSelf", "relative", "resolve", "(Ljava/io/File;Ljava/lang/String;)Ljava/io/File;", "resolveSibling", "startsWith", "toRelativeString", "(Ljava/io/File;Ljava/io/File;)Ljava/lang/String;", "toRelativeStringOrNull$FilesKt__UtilsKt", "toRelativeStringOrNull", "getExtension", "(Ljava/io/File;)Ljava/lang/String;", "extension", "getInvariantSeparatorsPath", "invariantSeparatorsPath", "getNameWithoutExtension", "nameWithoutExtension", "kotlin-stdlib"},
   xs = "kotlin/io/FilesKt"
)
@SourceDebugExtension({"SMAP\nUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Utils.kt\nkotlin/io/FilesKt__UtilsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,473:1\n1#2:474\n1288#3,3:475\n*S KotlinDebug\n*F\n+ 1 Utils.kt\nkotlin/io/FilesKt__UtilsKt\n*L\n347#1:475,3\n*E\n"})
class FilesKt__UtilsKt extends FilesKt__FileTreeWalkKt {
   /** @deprecated */
   @Deprecated(
      message = "Avoid creating temporary directories in the default temp location with this function due to too wide permissions on the newly created directory. Use kotlin.io.path.createTempDirectory instead."
   )
   @NotNull
   public static final File createTempDir(@NotNull String prefix, @Nullable String suffix, @Nullable File directory) {
      Intrinsics.checkNotNullParameter(prefix, "prefix");
      File dir = File.createTempFile(prefix, suffix, directory);
      dir.delete();
      if (dir.mkdir()) {
         Intrinsics.checkNotNull(dir);
         return dir;
      } else {
         throw new IOException("Unable to create temporary directory " + dir + '.');
      }
   }

   /** @deprecated */
   // $FF: synthetic method
   public static File createTempDir$default(String var0, String var1, File var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var0 = "tmp";
      }

      if ((var3 & 2) != 0) {
         var1 = null;
      }

      if ((var3 & 4) != 0) {
         var2 = null;
      }

      return FilesKt.createTempDir(var0, var1, var2);
   }

   /** @deprecated */
   @Deprecated(
      message = "Avoid creating temporary files in the default temp location with this function due to too wide permissions on the newly created file. Use kotlin.io.path.createTempFile instead or resort to java.io.File.createTempFile."
   )
   @NotNull
   public static final File createTempFile(@NotNull String prefix, @Nullable String suffix, @Nullable File directory) {
      Intrinsics.checkNotNullParameter(prefix, "prefix");
      File var10000 = File.createTempFile(prefix, suffix, directory);
      Intrinsics.checkNotNullExpressionValue(var10000, "createTempFile(...)");
      return var10000;
   }

   /** @deprecated */
   // $FF: synthetic method
   public static File createTempFile$default(String var0, String var1, File var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var0 = "tmp";
      }

      if ((var3 & 2) != 0) {
         var1 = null;
      }

      if ((var3 & 4) != 0) {
         var2 = null;
      }

      return FilesKt.createTempFile(var0, var1, var2);
   }

   @NotNull
   public static final String getExtension(@NotNull File $this$extension) {
      Intrinsics.checkNotNullParameter($this$extension, "<this>");
      String var10000 = $this$extension.getName();
      Intrinsics.checkNotNullExpressionValue(var10000, "getName(...)");
      return StringsKt.substringAfterLast(var10000, '.', "");
   }

   @NotNull
   public static final String getInvariantSeparatorsPath(@NotNull File $this$invariantSeparatorsPath) {
      Intrinsics.checkNotNullParameter($this$invariantSeparatorsPath, "<this>");
      String var1;
      if (File.separatorChar != '/') {
         var1 = $this$invariantSeparatorsPath.getPath();
         Intrinsics.checkNotNullExpressionValue(var1, "getPath(...)");
         var1 = StringsKt.replace$default(var1, File.separatorChar, '/', false, 4, (Object)null);
      } else {
         var1 = $this$invariantSeparatorsPath.getPath();
         Intrinsics.checkNotNullExpressionValue(var1, "getPath(...)");
      }

      return var1;
   }

   @NotNull
   public static final String getNameWithoutExtension(@NotNull File $this$nameWithoutExtension) {
      Intrinsics.checkNotNullParameter($this$nameWithoutExtension, "<this>");
      String var10000 = $this$nameWithoutExtension.getName();
      Intrinsics.checkNotNullExpressionValue(var10000, "getName(...)");
      return StringsKt.substringBeforeLast$default(var10000, ".", (String)null, 2, (Object)null);
   }

   @NotNull
   public static final String toRelativeString(@NotNull File $this$toRelativeString, @NotNull File base) {
      Intrinsics.checkNotNullParameter($this$toRelativeString, "<this>");
      Intrinsics.checkNotNullParameter(base, "base");
      return toRelativeStringOrNull$FilesKt__UtilsKt($this$toRelativeString, base);
   }

   @NotNull
   public static final File relativeTo(@NotNull File $this$relativeTo, @NotNull File base) {
      Intrinsics.checkNotNullParameter($this$relativeTo, "<this>");
      Intrinsics.checkNotNullParameter(base, "base");
      return new File(FilesKt.toRelativeString($this$relativeTo, base));
   }

   @NotNull
   public static final File relativeToOrSelf(@NotNull File $this$relativeToOrSelf, @NotNull File base) {
      Intrinsics.checkNotNullParameter($this$relativeToOrSelf, "<this>");
      Intrinsics.checkNotNullParameter(base, "base");
      String var10000 = toRelativeStringOrNull$FilesKt__UtilsKt($this$relativeToOrSelf, base);
      File var5;
      if (var10000 != null) {
         String p0 = var10000;
         int var4 = 0;
         var5 = new File(p0);
      } else {
         var5 = $this$relativeToOrSelf;
      }

      return var5;
   }

   @Nullable
   public static final File relativeToOrNull(@NotNull File $this$relativeToOrNull, @NotNull File base) {
      Intrinsics.checkNotNullParameter($this$relativeToOrNull, "<this>");
      Intrinsics.checkNotNullParameter(base, "base");
      String var10000 = toRelativeStringOrNull$FilesKt__UtilsKt($this$relativeToOrNull, base);
      File var5;
      if (var10000 != null) {
         String p0 = var10000;
         int var4 = 0;
         var5 = new File(p0);
      } else {
         var5 = null;
      }

      return var5;
   }

   private static final String toRelativeStringOrNull$FilesKt__UtilsKt(File $this$toRelativeStringOrNull, File base) {
      FilePathComponents thisComponents = normalize$FilesKt__UtilsKt(FilesKt.toComponents($this$toRelativeStringOrNull));
      FilePathComponents baseComponents = normalize$FilesKt__UtilsKt(FilesKt.toComponents(base));
      if (!Intrinsics.areEqual((Object)thisComponents.getRoot(), (Object)baseComponents.getRoot())) {
         return null;
      } else {
         int baseCount = baseComponents.getSize();
         int thisCount = thisComponents.getSize();
         int var9 = 0;
         int i = 0;

         for(int maxSameCount = Math.min(thisCount, baseCount); i < maxSameCount && Intrinsics.areEqual(thisComponents.getSegments().get(i), baseComponents.getSegments().get(i)); ++i) {
         }

         int sameCount = i;
         StringBuilder res = new StringBuilder();
         int i = baseCount - 1;
         if (i <= i) {
            while(true) {
               if (Intrinsics.areEqual((Object)((File)baseComponents.getSegments().get(i)).getName(), (Object)"..")) {
                  return null;
               }

               res.append("..");
               if (i != sameCount) {
                  res.append(File.separatorChar);
               }

               if (i == sameCount) {
                  break;
               }

               --i;
            }
         }

         if (sameCount < thisCount) {
            if (sameCount < baseCount) {
               res.append(File.separatorChar);
            }

            Iterable var10000 = (Iterable)CollectionsKt.drop((Iterable)thisComponents.getSegments(), sameCount);
            Appendable var10001 = (Appendable)res;
            String var10002 = File.separator;
            Intrinsics.checkNotNullExpressionValue(var10002, "separator");
            CollectionsKt.joinTo$default(var10000, var10001, (CharSequence)var10002, (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 124, (Object)null);
         }

         return res.toString();
      }
   }

   @NotNull
   public static final File copyTo(@NotNull File $this$copyTo, @NotNull File target, boolean overwrite, int bufferSize) {
      Intrinsics.checkNotNullParameter($this$copyTo, "<this>");
      Intrinsics.checkNotNullParameter(target, "target");
      if (!$this$copyTo.exists()) {
         throw new NoSuchFileException($this$copyTo, (File)null, "The source file doesn't exist.", 2, (DefaultConstructorMarker)null);
      } else {
         if (target.exists()) {
            if (!overwrite) {
               throw new FileAlreadyExistsException($this$copyTo, target, "The destination file already exists.");
            }

            if (!target.delete()) {
               throw new FileAlreadyExistsException($this$copyTo, target, "Tried to overwrite the destination, but failed to delete it.");
            }
         }

         if ($this$copyTo.isDirectory()) {
            if (!target.mkdirs()) {
               throw new FileSystemException($this$copyTo, target, "Failed to create target directory.");
            }
         } else {
            File var10000 = target.getParentFile();
            if (var10000 != null) {
               var10000.mkdirs();
            }

            Closeable var4 = (Closeable)(new FileInputStream($this$copyTo));
            Throwable var5 = null;

            try {
               FileInputStream input = (FileInputStream)var4;
               int var8 = 0;
               Closeable var9 = (Closeable)(new FileOutputStream(target));
               Throwable var10 = null;

               try {
                  FileOutputStream output = (FileOutputStream)var9;
                  int var13 = 0;
                  long var26 = ByteStreamsKt.copyTo((InputStream)input, (OutputStream)output, bufferSize);
               } catch (Throwable var22) {
                  var10 = var22;
                  throw var22;
               } finally {
                  CloseableKt.closeFinally(var9, var10);
               }
            } catch (Throwable var24) {
               var5 = var24;
               throw var24;
            } finally {
               CloseableKt.closeFinally(var4, var5);
            }
         }

         return target;
      }
   }

   // $FF: synthetic method
   public static File copyTo$default(File var0, File var1, boolean var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = false;
      }

      if ((var4 & 4) != 0) {
         var3 = 8192;
      }

      return FilesKt.copyTo(var0, var1, var2, var3);
   }

   public static final boolean copyRecursively(@NotNull File $this$copyRecursively, @NotNull File target, boolean overwrite, @NotNull final Function2 onError) {
      Intrinsics.checkNotNullParameter($this$copyRecursively, "<this>");
      Intrinsics.checkNotNullParameter(target, "target");
      Intrinsics.checkNotNullParameter(onError, "onError");
      if (!$this$copyRecursively.exists()) {
         return onError.invoke($this$copyRecursively, new NoSuchFileException($this$copyRecursively, (File)null, "The source file doesn't exist.", 2, (DefaultConstructorMarker)null)) != OnErrorAction.TERMINATE;
      } else {
         try {
            for(File src : FilesKt.walkTopDown($this$copyRecursively).onFail(new Function2(onError) {
               public final void invoke(File f, IOException e) {
                  Intrinsics.checkNotNullParameter(f, "f");
                  Intrinsics.checkNotNullParameter(e, "e");
                  if (onError.invoke(f, e) == OnErrorAction.TERMINATE) {
                     throw new TerminateException(f);
                  }
               }
            })) {
               if (!src.exists()) {
                  if (onError.invoke(src, new NoSuchFileException(src, (File)null, "The source file doesn't exist.", 2, (DefaultConstructorMarker)null)) == OnErrorAction.TERMINATE) {
                     return false;
                  }
               } else {
                  String relPath = FilesKt.toRelativeString(src, $this$copyRecursively);
                  File dstFile = new File(target, relPath);
                  if (dstFile.exists() && (!src.isDirectory() || !dstFile.isDirectory())) {
                     boolean stillExists = !overwrite ? true : (dstFile.isDirectory() ? !FilesKt.deleteRecursively(dstFile) : !dstFile.delete());
                     if (stillExists) {
                        if (onError.invoke(dstFile, new FileAlreadyExistsException(src, dstFile, "The destination file already exists.")) == OnErrorAction.TERMINATE) {
                           return false;
                        }
                        continue;
                     }
                  }

                  if (src.isDirectory()) {
                     dstFile.mkdirs();
                  } else if (FilesKt.copyTo$default(src, dstFile, overwrite, 0, 4, (Object)null).length() != src.length() && onError.invoke(src, new IOException("Source file wasn't copied completely, length of destination file differs.")) == OnErrorAction.TERMINATE) {
                     return false;
                  }
               }
            }

            return true;
         } catch (TerminateException var9) {
            return false;
         }
      }
   }

   // $FF: synthetic method
   public static boolean copyRecursively$default(File var0, File var1, boolean var2, Function2 var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = false;
      }

      if ((var4 & 4) != 0) {
         var3 = null.INSTANCE;
      }

      return FilesKt.copyRecursively(var0, var1, var2, var3);
   }

   public static final boolean deleteRecursively(@NotNull File $this$deleteRecursively) {
      Intrinsics.checkNotNullParameter($this$deleteRecursively, "<this>");
      Sequence $this$fold$iv = FilesKt.walkBottomUp($this$deleteRecursively);
      boolean initial$iv = true;
      int $i$f$fold = 0;
      boolean accumulator$iv = initial$iv;

      for(Object element$iv : $this$fold$iv) {
         File it = (File)element$iv;
         int var9 = 0;
         accumulator$iv = (it.delete() || !it.exists()) && accumulator$iv;
      }

      return accumulator$iv;
   }

   public static final boolean startsWith(@NotNull File $this$startsWith, @NotNull File other) {
      Intrinsics.checkNotNullParameter($this$startsWith, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      FilePathComponents components = FilesKt.toComponents($this$startsWith);
      FilePathComponents otherComponents = FilesKt.toComponents(other);
      if (!Intrinsics.areEqual((Object)components.getRoot(), (Object)otherComponents.getRoot())) {
         return false;
      } else {
         return components.getSize() < otherComponents.getSize() ? false : components.getSegments().subList(0, otherComponents.getSize()).equals(otherComponents.getSegments());
      }
   }

   public static final boolean startsWith(@NotNull File $this$startsWith, @NotNull String other) {
      Intrinsics.checkNotNullParameter($this$startsWith, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      return FilesKt.startsWith($this$startsWith, new File(other));
   }

   public static final boolean endsWith(@NotNull File $this$endsWith, @NotNull File other) {
      Intrinsics.checkNotNullParameter($this$endsWith, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      FilePathComponents components = FilesKt.toComponents($this$endsWith);
      FilePathComponents otherComponents = FilesKt.toComponents(other);
      if (otherComponents.isRooted()) {
         return Intrinsics.areEqual((Object)$this$endsWith, (Object)other);
      } else {
         int shift = components.getSize() - otherComponents.getSize();
         return shift < 0 ? false : components.getSegments().subList(shift, components.getSize()).equals(otherComponents.getSegments());
      }
   }

   public static final boolean endsWith(@NotNull File $this$endsWith, @NotNull String other) {
      Intrinsics.checkNotNullParameter($this$endsWith, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      return FilesKt.endsWith($this$endsWith, new File(other));
   }

   @NotNull
   public static final File normalize(@NotNull File $this$normalize) {
      Intrinsics.checkNotNullParameter($this$normalize, "<this>");
      FilePathComponents $this$normalize_u24lambda_u245 = FilesKt.toComponents($this$normalize);
      int var3 = 0;
      File var10000 = $this$normalize_u24lambda_u245.getRoot();
      Iterable var10001 = (Iterable)normalize$FilesKt__UtilsKt($this$normalize_u24lambda_u245.getSegments());
      String var10002 = File.separator;
      Intrinsics.checkNotNullExpressionValue(var10002, "separator");
      return FilesKt.resolve(var10000, CollectionsKt.joinToString$default(var10001, (CharSequence)var10002, (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null));
   }

   private static final FilePathComponents normalize$FilesKt__UtilsKt(FilePathComponents $this$normalize) {
      return new FilePathComponents($this$normalize.getRoot(), normalize$FilesKt__UtilsKt($this$normalize.getSegments()));
   }

   private static final List normalize$FilesKt__UtilsKt(List $this$normalize) {
      List list = (List)(new ArrayList($this$normalize.size()));

      for(File file : $this$normalize) {
         String var4 = file.getName();
         if (!Intrinsics.areEqual((Object)var4, (Object)".")) {
            if (Intrinsics.areEqual((Object)var4, (Object)"..")) {
               if (!list.isEmpty() && !Intrinsics.areEqual((Object)((File)CollectionsKt.last(list)).getName(), (Object)"..")) {
                  list.remove(list.size() - 1);
               } else {
                  list.add(file);
               }
            } else {
               list.add(file);
            }
         }
      }

      return list;
   }

   @NotNull
   public static final File resolve(@NotNull File $this$resolve, @NotNull File relative) {
      Intrinsics.checkNotNullParameter($this$resolve, "<this>");
      Intrinsics.checkNotNullParameter(relative, "relative");
      if (FilesKt.isRooted(relative)) {
         return relative;
      } else {
         String var10000 = $this$resolve.toString();
         Intrinsics.checkNotNullExpressionValue(var10000, "toString(...)");
         String baseName = var10000;
         return ((CharSequence)baseName).length() != 0 && !StringsKt.endsWith$default((CharSequence)baseName, File.separatorChar, false, 2, (Object)null) ? new File(baseName + File.separatorChar + relative) : new File(baseName + relative);
      }
   }

   @NotNull
   public static final File resolve(@NotNull File $this$resolve, @NotNull String relative) {
      Intrinsics.checkNotNullParameter($this$resolve, "<this>");
      Intrinsics.checkNotNullParameter(relative, "relative");
      return FilesKt.resolve($this$resolve, new File(relative));
   }

   @NotNull
   public static final File resolveSibling(@NotNull File $this$resolveSibling, @NotNull File relative) {
      Intrinsics.checkNotNullParameter($this$resolveSibling, "<this>");
      Intrinsics.checkNotNullParameter(relative, "relative");
      FilePathComponents components = FilesKt.toComponents($this$resolveSibling);
      File parentSubPath = components.getSize() == 0 ? new File("..") : components.subPath(0, components.getSize() - 1);
      return FilesKt.resolve(FilesKt.resolve(components.getRoot(), parentSubPath), relative);
   }

   @NotNull
   public static final File resolveSibling(@NotNull File $this$resolveSibling, @NotNull String relative) {
      Intrinsics.checkNotNullParameter($this$resolveSibling, "<this>");
      Intrinsics.checkNotNullParameter(relative, "relative");
      return FilesKt.resolveSibling($this$resolveSibling, new File(relative));
   }

   public FilesKt__UtilsKt() {
   }
}
