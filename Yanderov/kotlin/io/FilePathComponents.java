package kotlin.io;

import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\r\b\u0080\b\u0018\u00002\u00020\u0001B\u001f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ*\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004HÆ\u0001¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0012¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u001a\u001a\u00020\u0019HÖ\u0001¢\u0006\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010\tR\u0011\u0010!\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b \u0010\u001bR\u001d\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\"\u001a\u0004\b#\u0010\u000bR\u0011\u0010%\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b$\u0010\u0014¨\u0006&"},
   d2 = {"Lkotlin/io/FilePathComponents;", "", "Ljava/io/File;", "root", "", "segments", "<init>", "(Ljava/io/File;Ljava/util/List;)V", "component1", "()Ljava/io/File;", "component2", "()Ljava/util/List;", "copy", "(Ljava/io/File;Ljava/util/List;)Lkotlin/io/FilePathComponents;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "beginIndex", "endIndex", "subPath", "(II)Ljava/io/File;", "", "toString", "()Ljava/lang/String;", "isRooted", "()Z", "Ljava/io/File;", "getRoot", "getRootName", "rootName", "Ljava/util/List;", "getSegments", "getSize", "size", "kotlin-stdlib"}
)
public final class FilePathComponents {
   @NotNull
   private final File root;
   @NotNull
   private final List segments;

   public FilePathComponents(@NotNull File root, @NotNull List segments) {
      Intrinsics.checkNotNullParameter(root, "root");
      Intrinsics.checkNotNullParameter(segments, "segments");
      super();
      this.root = root;
      this.segments = segments;
   }

   @NotNull
   public final File getRoot() {
      return this.root;
   }

   @NotNull
   public final List getSegments() {
      return this.segments;
   }

   @NotNull
   public final String getRootName() {
      String var10000 = this.root.getPath();
      Intrinsics.checkNotNullExpressionValue(var10000, "getPath(...)");
      return var10000;
   }

   public final boolean isRooted() {
      String var10000 = this.root.getPath();
      Intrinsics.checkNotNullExpressionValue(var10000, "getPath(...)");
      return ((CharSequence)var10000).length() > 0;
   }

   public final int getSize() {
      return this.segments.size();
   }

   @NotNull
   public final File subPath(int beginIndex, int endIndex) {
      if (beginIndex >= 0 && beginIndex <= endIndex && endIndex <= this.getSize()) {
         Iterable var10002 = (Iterable)this.segments.subList(beginIndex, endIndex);
         String var10003 = File.separator;
         Intrinsics.checkNotNullExpressionValue(var10003, "separator");
         return new File(CollectionsKt.joinToString$default(var10002, (CharSequence)var10003, (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null));
      } else {
         throw new IllegalArgumentException();
      }
   }

   @NotNull
   public final File component1() {
      return this.root;
   }

   @NotNull
   public final List component2() {
      return this.segments;
   }

   @NotNull
   public final FilePathComponents copy(@NotNull File root, @NotNull List segments) {
      Intrinsics.checkNotNullParameter(root, "root");
      Intrinsics.checkNotNullParameter(segments, "segments");
      return new FilePathComponents(root, segments);
   }

   // $FF: synthetic method
   public static FilePathComponents copy$default(FilePathComponents var0, File var1, List var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.root;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.segments;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "FilePathComponents(root=" + this.root + ", segments=" + this.segments + ')';
   }

   public int hashCode() {
      int result = this.root.hashCode();
      result = result * 31 + this.segments.hashCode();
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof FilePathComponents)) {
         return false;
      } else {
         FilePathComponents var2 = (FilePathComponents)other;
         if (!Intrinsics.areEqual((Object)this.root, (Object)var2.root)) {
            return false;
         } else {
            return Intrinsics.areEqual((Object)this.segments, (Object)var2.segments);
         }
      }
   }
}
