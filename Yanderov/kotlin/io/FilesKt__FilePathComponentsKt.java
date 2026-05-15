package kotlin.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\u001a\u0013\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u0002¢\u0006\u0004\b\u0002\u0010\u0003\u001a#\u0010\b\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u0013\u0010\u000b\u001a\u00020\n*\u00020\u0005H\u0000¢\u0006\u0004\b\u000b\u0010\f\"\u0015\u0010\u000e\u001a\u00020\r*\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\"\u0018\u0010\u0012\u001a\u00020\u0005*\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\"\u0018\u0010\u0015\u001a\u00020\u0000*\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"},
   d2 = {"", "", "getRootLength$FilesKt__FilePathComponentsKt", "(Ljava/lang/String;)I", "getRootLength", "Ljava/io/File;", "beginIndex", "endIndex", "subPath", "(Ljava/io/File;II)Ljava/io/File;", "Lkotlin/io/FilePathComponents;", "toComponents", "(Ljava/io/File;)Lkotlin/io/FilePathComponents;", "", "isRooted", "(Ljava/io/File;)Z", "getRoot", "(Ljava/io/File;)Ljava/io/File;", "root", "getRootName", "(Ljava/io/File;)Ljava/lang/String;", "rootName", "kotlin-stdlib"},
   xs = "kotlin/io/FilesKt"
)
@SourceDebugExtension({"SMAP\nFilePathComponents.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FilePathComponents.kt\nkotlin/io/FilesKt__FilePathComponentsKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,148:1\n1557#2:149\n1628#2,3:150\n*S KotlinDebug\n*F\n+ 1 FilePathComponents.kt\nkotlin/io/FilesKt__FilePathComponentsKt\n*L\n133#1:149\n133#1:150,3\n*E\n"})
class FilesKt__FilePathComponentsKt {
   private static final int getRootLength$FilesKt__FilePathComponentsKt(String $this$getRootLength) {
      int first = StringsKt.indexOf$default((CharSequence)$this$getRootLength, File.separatorChar, 0, false, 4, (Object)null);
      if (first == 0) {
         if ($this$getRootLength.length() > 1 && $this$getRootLength.charAt(1) == File.separatorChar) {
            first = StringsKt.indexOf$default((CharSequence)$this$getRootLength, File.separatorChar, 2, false, 4, (Object)null);
            if (first >= 0) {
               first = StringsKt.indexOf$default((CharSequence)$this$getRootLength, File.separatorChar, first + 1, false, 4, (Object)null);
               if (first >= 0) {
                  return first + 1;
               }

               return $this$getRootLength.length();
            }
         }

         return 1;
      } else if (first > 0 && $this$getRootLength.charAt(first - 1) == ':') {
         ++first;
         return first;
      } else {
         return first == -1 && StringsKt.endsWith$default((CharSequence)$this$getRootLength, ':', false, 2, (Object)null) ? $this$getRootLength.length() : 0;
      }
   }

   @NotNull
   public static final String getRootName(@NotNull File $this$rootName) {
      Intrinsics.checkNotNullParameter($this$rootName, "<this>");
      String var10000 = $this$rootName.getPath();
      Intrinsics.checkNotNullExpressionValue(var10000, "getPath(...)");
      String var1 = var10000;
      byte var2 = 0;
      var10000 = $this$rootName.getPath();
      Intrinsics.checkNotNullExpressionValue(var10000, "getPath(...)");
      int var3 = getRootLength$FilesKt__FilePathComponentsKt(var10000);
      var10000 = var1.substring(var2, var3);
      Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
      return var10000;
   }

   @NotNull
   public static final File getRoot(@NotNull File $this$root) {
      Intrinsics.checkNotNullParameter($this$root, "<this>");
      return new File(FilesKt.getRootName($this$root));
   }

   public static final boolean isRooted(@NotNull File $this$isRooted) {
      Intrinsics.checkNotNullParameter($this$isRooted, "<this>");
      String var10000 = $this$isRooted.getPath();
      Intrinsics.checkNotNullExpressionValue(var10000, "getPath(...)");
      return getRootLength$FilesKt__FilePathComponentsKt(var10000) > 0;
   }

   @NotNull
   public static final FilePathComponents toComponents(@NotNull File $this$toComponents) {
      Intrinsics.checkNotNullParameter($this$toComponents, "<this>");
      String path = $this$toComponents.getPath();
      Intrinsics.checkNotNull(path);
      int rootLength = getRootLength$FilesKt__FilePathComponentsKt(path);
      byte var5 = 0;
      String var10000 = path.substring(var5, rootLength);
      Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
      String rootName = var10000;
      var10000 = path.substring(rootLength);
      Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
      String subPath = var10000;
      List var19;
      if (((CharSequence)subPath).length() == 0) {
         var19 = CollectionsKt.emptyList();
      } else {
         CharSequence var20 = (CharSequence)subPath;
         char[] var6 = new char[]{File.separatorChar};
         Iterable $this$map$iv = (Iterable)StringsKt.split$default(var20, var6, false, 0, 6, (Object)null);
         int $i$f$map = 0;
         Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
         int $i$f$mapTo = 0;

         for(Object item$iv$iv : $this$map$iv) {
            String p0 = (String)item$iv$iv;
            int var14 = 0;
            destination$iv$iv.add(new File(p0));
         }

         var19 = (List)destination$iv$iv;
      }

      List list = var19;
      return new FilePathComponents(new File(rootName), list);
   }

   @NotNull
   public static final File subPath(@NotNull File $this$subPath, int beginIndex, int endIndex) {
      Intrinsics.checkNotNullParameter($this$subPath, "<this>");
      return FilesKt.toComponents($this$subPath).subPath(beginIndex, endIndex);
   }

   public FilesKt__FilePathComponentsKt() {
   }
}
