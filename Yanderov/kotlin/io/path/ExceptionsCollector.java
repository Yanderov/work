package kotlin.io.path;

import java.nio.file.FileSystemException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u000f\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\n\u001a\u00020\t2\n\u0010\b\u001a\u00060\u0006j\u0002`\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\u000e\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0010\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0010\u0010\u000fR!\u0010\u0012\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0016R$\u0010\u0017\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u000fR$\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00028\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u001d\u0010\u0016\u001a\u0004\b\u001e\u0010\u001f¨\u0006 "},
   d2 = {"Lkotlin/io/path/ExceptionsCollector;", "", "", "limit", "<init>", "(I)V", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "", "collect", "(Ljava/lang/Exception;)V", "Ljava/nio/file/Path;", "name", "enterEntry", "(Ljava/nio/file/Path;)V", "exitEntry", "", "collectedExceptions", "Ljava/util/List;", "getCollectedExceptions", "()Ljava/util/List;", "I", "path", "Ljava/nio/file/Path;", "getPath", "()Ljava/nio/file/Path;", "setPath", "<set-?>", "totalExceptions", "getTotalExceptions", "()I", "kotlin-stdlib-jdk7"}
)
final class ExceptionsCollector {
   private final int limit;
   private int totalExceptions;
   @NotNull
   private final List collectedExceptions;
   @Nullable
   private Path path;

   public ExceptionsCollector(int limit) {
      this.limit = limit;
      this.collectedExceptions = (List)(new ArrayList());
   }

   // $FF: synthetic method
   public ExceptionsCollector(int var1, int var2, DefaultConstructorMarker var3) {
      if ((var2 & 1) != 0) {
         var1 = 64;
      }

      this(var1);
   }

   public final int getTotalExceptions() {
      return this.totalExceptions;
   }

   @NotNull
   public final List getCollectedExceptions() {
      return this.collectedExceptions;
   }

   @Nullable
   public final Path getPath() {
      return this.path;
   }

   public final void setPath(@Nullable Path <set-?>) {
      this.path = <set-?>;
   }

   public final void enterEntry(@NotNull Path name) {
      Intrinsics.checkNotNullParameter(name, "name");
      Path var10001 = this.path;
      this.path = var10001 != null ? var10001.resolve(name) : null;
   }

   public final void exitEntry(@NotNull Path name) {
      Intrinsics.checkNotNullParameter(name, "name");
      Path var10001 = this.path;
      if (!Intrinsics.areEqual((Object)name, (Object)(var10001 != null ? var10001.getFileName() : null))) {
         String var2 = "Failed requirement.";
         throw new IllegalArgumentException(var2.toString());
      } else {
         var10001 = this.path;
         this.path = var10001 != null ? var10001.getParent() : null;
      }
   }

   public final void collect(@NotNull Exception exception) {
      Intrinsics.checkNotNullParameter(exception, "exception");
      ++this.totalExceptions;
      boolean shouldCollect = this.collectedExceptions.size() < this.limit;
      if (shouldCollect) {
         Exception var4;
         if (this.path != null) {
            Throwable var10000 = (new FileSystemException(String.valueOf(this.path))).initCause((Throwable)exception);
            Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type java.nio.file.FileSystemException");
            var4 = (Exception)((FileSystemException)var10000);
         } else {
            var4 = exception;
         }

         Exception restoredException = var4;
         this.collectedExceptions.add(restoredException);
      }

   }

   public ExceptionsCollector() {
      this(0, 1, (DefaultConstructorMarker)null);
   }
}
