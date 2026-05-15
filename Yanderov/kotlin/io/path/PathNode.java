package kotlin.io.path;

import java.nio.file.Path;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010(\n\u0002\b\u0010\b\u0002\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0006\u0010\u0007R*\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00018\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00008\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"},
   d2 = {"Lkotlin/io/path/PathNode;", "", "Ljava/nio/file/Path;", "path", "key", "parent", "<init>", "(Ljava/nio/file/Path;Ljava/lang/Object;Lkotlin/io/path/PathNode;)V", "", "contentIterator", "Ljava/util/Iterator;", "getContentIterator", "()Ljava/util/Iterator;", "setContentIterator", "(Ljava/util/Iterator;)V", "Ljava/lang/Object;", "getKey", "()Ljava/lang/Object;", "Lkotlin/io/path/PathNode;", "getParent", "()Lkotlin/io/path/PathNode;", "Ljava/nio/file/Path;", "getPath", "()Ljava/nio/file/Path;", "kotlin-stdlib-jdk7"}
)
final class PathNode {
   @NotNull
   private final Path path;
   @Nullable
   private final Object key;
   @Nullable
   private final PathNode parent;
   @Nullable
   private Iterator contentIterator;

   public PathNode(@NotNull Path path, @Nullable Object key, @Nullable PathNode parent) {
      Intrinsics.checkNotNullParameter(path, "path");
      super();
      this.path = path;
      this.key = key;
      this.parent = parent;
   }

   @NotNull
   public final Path getPath() {
      return this.path;
   }

   @Nullable
   public final Object getKey() {
      return this.key;
   }

   @Nullable
   public final PathNode getParent() {
      return this.parent;
   }

   @Nullable
   public final Iterator getContentIterator() {
      return this.contentIterator;
   }

   public final void setContentIterator(@Nullable Iterator <set-?>) {
      this.contentIterator = <set-?>;
   }
}
