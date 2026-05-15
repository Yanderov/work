package kotlin.io;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin._Assertions;
import kotlin.collections.AbstractIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0010\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003$%&B\u001b\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\u008b\u0001\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b\u00128\u0010\u0013\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0006\u0010\u0016J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u0017H\u0096\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u0015\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u001bJ!\u0010\n\u001a\u00020\u00002\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u001dJ'\u0010\u0013\u001a\u00020\u00002\u0018\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0004\b\u0013\u0010\u001eJ!\u0010\f\u001a\u00020\u00002\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\b¢\u0006\u0004\b\f\u0010\u001dR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001fR\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010 R\"\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010!RF\u0010\u0013\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\"R\"\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010!R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010#¨\u0006'"},
   d2 = {"Lkotlin/io/FileTreeWalk;", "Lkotlin/sequences/Sequence;", "Ljava/io/File;", "start", "Lkotlin/io/FileWalkDirection;", "direction", "<init>", "(Ljava/io/File;Lkotlin/io/FileWalkDirection;)V", "Lkotlin/Function1;", "", "onEnter", "", "onLeave", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "f", "Ljava/io/IOException;", "e", "onFail", "", "maxDepth", "(Ljava/io/File;Lkotlin/io/FileWalkDirection;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;I)V", "", "iterator", "()Ljava/util/Iterator;", "depth", "(I)Lkotlin/io/FileTreeWalk;", "function", "(Lkotlin/jvm/functions/Function1;)Lkotlin/io/FileTreeWalk;", "(Lkotlin/jvm/functions/Function2;)Lkotlin/io/FileTreeWalk;", "Lkotlin/io/FileWalkDirection;", "I", "Lkotlin/jvm/functions/Function1;", "Lkotlin/jvm/functions/Function2;", "Ljava/io/File;", "DirectoryState", "FileTreeWalkIterator", "WalkState", "kotlin-stdlib"}
)
public final class FileTreeWalk implements Sequence {
   @NotNull
   private final File start;
   @NotNull
   private final FileWalkDirection direction;
   @Nullable
   private final Function1 onEnter;
   @Nullable
   private final Function1 onLeave;
   @Nullable
   private final Function2 onFail;
   private final int maxDepth;

   private FileTreeWalk(File start, FileWalkDirection direction, Function1 onEnter, Function1 onLeave, Function2 onFail, int maxDepth) {
      this.start = start;
      this.direction = direction;
      this.onEnter = onEnter;
      this.onLeave = onLeave;
      this.onFail = onFail;
      this.maxDepth = maxDepth;
   }

   // $FF: synthetic method
   FileTreeWalk(File var1, FileWalkDirection var2, Function1 var3, Function1 var4, Function2 var5, int var6, int var7, DefaultConstructorMarker var8) {
      if ((var7 & 2) != 0) {
         var2 = FileWalkDirection.TOP_DOWN;
      }

      if ((var7 & 32) != 0) {
         var6 = Integer.MAX_VALUE;
      }

      this(var1, var2, var3, var4, var5, var6);
   }

   public FileTreeWalk(@NotNull File start, @NotNull FileWalkDirection direction) {
      Intrinsics.checkNotNullParameter(start, "start");
      Intrinsics.checkNotNullParameter(direction, "direction");
      this(start, direction, (Function1)null, (Function1)null, (Function2)null, 0, 32, (DefaultConstructorMarker)null);
   }

   // $FF: synthetic method
   public FileTreeWalk(File var1, FileWalkDirection var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 2) != 0) {
         var2 = FileWalkDirection.TOP_DOWN;
      }

      this(var1, var2);
   }

   @NotNull
   public Iterator iterator() {
      return new FileTreeWalkIterator(this);
   }

   @NotNull
   public final FileTreeWalk onEnter(@NotNull Function1 function) {
      Intrinsics.checkNotNullParameter(function, "function");
      return new FileTreeWalk(this.start, this.direction, function, this.onLeave, this.onFail, this.maxDepth);
   }

   @NotNull
   public final FileTreeWalk onLeave(@NotNull Function1 function) {
      Intrinsics.checkNotNullParameter(function, "function");
      return new FileTreeWalk(this.start, this.direction, this.onEnter, function, this.onFail, this.maxDepth);
   }

   @NotNull
   public final FileTreeWalk onFail(@NotNull Function2 function) {
      Intrinsics.checkNotNullParameter(function, "function");
      return new FileTreeWalk(this.start, this.direction, this.onEnter, this.onLeave, function, this.maxDepth);
   }

   @NotNull
   public final FileTreeWalk maxDepth(int depth) {
      if (depth <= 0) {
         throw new IllegalArgumentException("depth must be positive, but was " + depth + '.');
      } else {
         return new FileTreeWalk(this.start, this.direction, this.onEnter, this.onLeave, this.onFail, depth);
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\"\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u0006\u001a\u0004\u0018\u00010\u0002H&¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\u0007¨\u0006\n"},
      d2 = {"Lkotlin/io/FileTreeWalk$WalkState;", "", "Ljava/io/File;", "root", "<init>", "(Ljava/io/File;)V", "step", "()Ljava/io/File;", "Ljava/io/File;", "getRoot", "kotlin-stdlib"}
   )
   private abstract static class WalkState {
      @NotNull
      private final File root;

      public WalkState(@NotNull File root) {
         Intrinsics.checkNotNullParameter(root, "root");
         super();
         this.root = root;
      }

      @NotNull
      public final File getRoot() {
         return this.root;
      }

      @Nullable
      public abstract File step();
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\"\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"},
      d2 = {"Lkotlin/io/FileTreeWalk$DirectoryState;", "Lkotlin/io/FileTreeWalk$WalkState;", "Ljava/io/File;", "rootDir", "<init>", "(Ljava/io/File;)V", "kotlin-stdlib"}
   )
   @SourceDebugExtension({"SMAP\nFileTreeWalk.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileTreeWalk.kt\nkotlin/io/FileTreeWalk$DirectoryState\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,273:1\n1#2:274\n*E\n"})
   private abstract static class DirectoryState extends WalkState {
      public DirectoryState(@NotNull File rootDir) {
         Intrinsics.checkNotNullParameter(rootDir, "rootDir");
         super(rootDir);
         if (_Assertions.ENABLED) {
            boolean var2 = rootDir.isDirectory();
            if (_Assertions.ENABLED && !var2) {
               int var3 = 0;
               String var4 = "rootDir must be verified to be directory beforehand.";
               throw new AssertionError(var4);
            }
         }

      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\u0012\u0013\u0014B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0014¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0082\u0010¢\u0006\u0004\b\f\u0010\rR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011¨\u0006\u0015"},
      d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;", "Lkotlin/collections/AbstractIterator;", "Ljava/io/File;", "<init>", "(Lkotlin/io/FileTreeWalk;)V", "", "computeNext", "()V", "root", "Lkotlin/io/FileTreeWalk$DirectoryState;", "directoryState", "(Ljava/io/File;)Lkotlin/io/FileTreeWalk$DirectoryState;", "gotoNext", "()Ljava/io/File;", "Ljava/util/ArrayDeque;", "Lkotlin/io/FileTreeWalk$WalkState;", "state", "Ljava/util/ArrayDeque;", "BottomUpDirectoryState", "SingleFileState", "TopDownDirectoryState", "kotlin-stdlib"}
   )
   private final class FileTreeWalkIterator extends AbstractIterator {
      @NotNull
      private final ArrayDeque state = new ArrayDeque();

      public FileTreeWalkIterator(FileTreeWalk this$0) {
         if (FileTreeWalk.this.start.isDirectory()) {
            this.state.push(this.directoryState(FileTreeWalk.this.start));
         } else if (FileTreeWalk.this.start.isFile()) {
            this.state.push(new SingleFileState(this, FileTreeWalk.this.start));
         } else {
            this.done();
         }

      }

      protected void computeNext() {
         File nextFile = this.gotoNext();
         if (nextFile != null) {
            this.setNext(nextFile);
         } else {
            this.done();
         }

      }

      private final DirectoryState directoryState(File root) {
         DirectoryState var10000;
         switch (FileTreeWalk.FileTreeWalkIterator.WhenMappings.$EnumSwitchMapping$0[FileTreeWalk.this.direction.ordinal()]) {
            case 1:
               var10000 = new TopDownDirectoryState(this, root);
               break;
            case 2:
               var10000 = new BottomUpDirectoryState(this, root);
               break;
            default:
               throw new NoWhenBranchMatchedException();
         }

         return var10000;
      }

      private final File gotoNext() {
         while(true) {
            WalkState var10000 = (WalkState)this.state.peek();
            if (var10000 == null) {
               return null;
            }

            WalkState topState = var10000;
            File file = topState.step();
            if (file == null) {
               this.state.pop();
               this = this;
            } else {
               if (Intrinsics.areEqual((Object)file, (Object)topState.getRoot()) || !file.isDirectory() || this.state.size() >= FileTreeWalk.this.maxDepth) {
                  return file;
               }

               this.state.push(this.directoryState(file));
               this = this;
            }
         }
      }

      @Metadata(
         mv = {1, 9, 0},
         k = 1,
         xi = 48,
         d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u0006\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0016\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\rR\u001e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010\n¨\u0006\u0012"},
         d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$BottomUpDirectoryState;", "Lkotlin/io/FileTreeWalk$DirectoryState;", "Ljava/io/File;", "rootDir", "<init>", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "step", "()Ljava/io/File;", "", "failed", "Z", "", "fileIndex", "I", "", "fileList", "[Ljava/io/File;", "rootVisited", "kotlin-stdlib"}
      )
      private final class BottomUpDirectoryState extends DirectoryState {
         private boolean rootVisited;
         @Nullable
         private File[] fileList;
         private int fileIndex;
         private boolean failed;

         public BottomUpDirectoryState(@NotNull FileTreeWalkIterator this$0, File rootDir) {
            Intrinsics.checkNotNullParameter(rootDir, "rootDir");
            super(rootDir);
         }

         @Nullable
         public File step() {
            if (!this.failed && this.fileList == null) {
               Function1 var10000 = FileTreeWalk.this.onEnter;
               if (var10000 != null ? !(Boolean)var10000.invoke(this.getRoot()) : false) {
                  return null;
               }

               this.fileList = this.getRoot().listFiles();
               if (this.fileList == null) {
                  Function2 var2 = FileTreeWalk.this.onFail;
                  if (var2 != null) {
                     var2.invoke(this.getRoot(), new AccessDeniedException(this.getRoot(), (File)null, "Cannot list files in a directory", 2, (DefaultConstructorMarker)null));
                  }

                  this.failed = true;
               }
            }

            if (this.fileList != null) {
               int var3 = this.fileIndex;
               File[] var10001 = this.fileList;
               Intrinsics.checkNotNull(var10001);
               if (var3 < var10001.length) {
                  File[] var5 = this.fileList;
                  Intrinsics.checkNotNull(var5);
                  int var1 = this.fileIndex++;
                  return var5[var1];
               }
            }

            if (!this.rootVisited) {
               this.rootVisited = true;
               return this.getRoot();
            } else {
               Function1 var4 = FileTreeWalk.this.onLeave;
               if (var4 != null) {
                  var4.invoke(this.getRoot());
               }

               return null;
            }
         }
      }

      @Metadata(
         mv = {1, 9, 0},
         k = 1,
         xi = 48,
         d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u0006\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\nR\u001e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\rR\u0016\u0010\u000f\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0011"},
         d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$TopDownDirectoryState;", "Lkotlin/io/FileTreeWalk$DirectoryState;", "Ljava/io/File;", "rootDir", "<init>", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "step", "()Ljava/io/File;", "", "fileIndex", "I", "", "fileList", "[Ljava/io/File;", "", "rootVisited", "Z", "kotlin-stdlib"}
      )
      private final class TopDownDirectoryState extends DirectoryState {
         private boolean rootVisited;
         @Nullable
         private File[] fileList;
         private int fileIndex;

         public TopDownDirectoryState(@NotNull FileTreeWalkIterator this$0, File rootDir) {
            Intrinsics.checkNotNullParameter(rootDir, "rootDir");
            super(rootDir);
         }

         @Nullable
         public File step() {
            if (!this.rootVisited) {
               Function1 var7 = FileTreeWalk.this.onEnter;
               if (var7 != null ? !(Boolean)var7.invoke(this.getRoot()) : false) {
                  return null;
               } else {
                  this.rootVisited = true;
                  return this.getRoot();
               }
            } else {
               if (this.fileList != null) {
                  int var10000 = this.fileIndex;
                  File[] var10001 = this.fileList;
                  Intrinsics.checkNotNull(var10001);
                  if (var10000 >= var10001.length) {
                     Function1 var6 = FileTreeWalk.this.onLeave;
                     if (var6 != null) {
                        var6.invoke(this.getRoot());
                     }

                     return null;
                  }
               }

               if (this.fileList == null) {
                  label62: {
                     this.fileList = this.getRoot().listFiles();
                     if (this.fileList == null) {
                        Function2 var2 = FileTreeWalk.this.onFail;
                        if (var2 != null) {
                           var2.invoke(this.getRoot(), new AccessDeniedException(this.getRoot(), (File)null, "Cannot list files in a directory", 2, (DefaultConstructorMarker)null));
                        }
                     }

                     if (this.fileList != null) {
                        File[] var3 = this.fileList;
                        Intrinsics.checkNotNull(var3);
                        if (var3.length != 0) {
                           break label62;
                        }
                     }

                     Function1 var4 = FileTreeWalk.this.onLeave;
                     if (var4 != null) {
                        var4.invoke(this.getRoot());
                     }

                     return null;
                  }
               }

               File[] var5 = this.fileList;
               Intrinsics.checkNotNull(var5);
               int var1 = this.fileIndex++;
               return var5[var1];
            }
         }
      }

      @Metadata(
         mv = {1, 9, 0},
         k = 1,
         xi = 48,
         d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u0006\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\n¨\u0006\u000b"},
         d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$SingleFileState;", "Lkotlin/io/FileTreeWalk$WalkState;", "Ljava/io/File;", "rootFile", "<init>", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "step", "()Ljava/io/File;", "", "visited", "Z", "kotlin-stdlib"}
      )
      @SourceDebugExtension({"SMAP\nFileTreeWalk.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileTreeWalk.kt\nkotlin/io/FileTreeWalk$FileTreeWalkIterator$SingleFileState\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,273:1\n1#2:274\n*E\n"})
      private final class SingleFileState extends WalkState {
         private boolean visited;

         public SingleFileState(@NotNull FileTreeWalkIterator this$0, File rootFile) {
            Intrinsics.checkNotNullParameter(rootFile, "rootFile");
            super(rootFile);
            if (_Assertions.ENABLED) {
               boolean var3 = rootFile.isFile();
               if (_Assertions.ENABLED && !var3) {
                  int var4 = 0;
                  String var5 = "rootFile must be verified to be file beforehand.";
                  throw new AssertionError(var5);
               }
            }

         }

         @Nullable
         public File step() {
            if (this.visited) {
               return null;
            } else {
               this.visited = true;
               return this.getRoot();
            }
         }
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

         static {
            int[] var0 = new int[FileWalkDirection.values().length];

            try {
               var0[FileWalkDirection.TOP_DOWN.ordinal()] = 1;
            } catch (NoSuchFieldError var3) {
            }

            try {
               var0[FileWalkDirection.BOTTOM_UP.ordinal()] = 2;
            } catch (NoSuchFieldError var2) {
            }

            $EnumSwitchMapping$0 = var0;
         }
      }
   }
}
