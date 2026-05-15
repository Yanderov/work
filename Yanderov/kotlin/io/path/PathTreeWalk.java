package kotlin.io.path;

import java.nio.file.FileSystemLoopException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\tH\u0002¢\u0006\u0004\b\f\u0010\u000bJ\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\tH\u0096\u0002¢\u0006\u0004\b\r\u0010\u000bJD\u0010\u0017\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\u0018\u0010\u0016\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u0014\u0012\u0004\u0012\u00020\u00150\u0013H\u0082H¢\u0006\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001c\u001a\u00020\u00198BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001e\u001a\u00020\u00198BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001bR\u0014\u0010\u001f\u001a\u00020\u00198BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001bR\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020 0\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u001c\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010$R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010%¨\u0006&"},
   d2 = {"Lkotlin/io/path/PathTreeWalk;", "Lkotlin/sequences/Sequence;", "Ljava/nio/file/Path;", "start", "", "Lkotlin/io/path/PathWalkOption;", "options", "<init>", "(Ljava/nio/file/Path;[Lkotlin/io/path/PathWalkOption;)V", "", "bfsIterator", "()Ljava/util/Iterator;", "dfsIterator", "iterator", "Lkotlin/sequences/SequenceScope;", "Lkotlin/io/path/PathNode;", "node", "Lkotlin/io/path/DirectoryEntriesReader;", "entriesReader", "Lkotlin/Function1;", "", "", "entriesAction", "yieldIfNeeded", "(Lkotlin/sequences/SequenceScope;Lkotlin/io/path/PathNode;Lkotlin/io/path/DirectoryEntriesReader;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "getFollowLinks", "()Z", "followLinks", "getIncludeDirectories", "includeDirectories", "isBFS", "Ljava/nio/file/LinkOption;", "getLinkOptions", "()[Ljava/nio/file/LinkOption;", "linkOptions", "[Lkotlin/io/path/PathWalkOption;", "Ljava/nio/file/Path;", "kotlin-stdlib-jdk7"}
)
@ExperimentalPathApi
public final class PathTreeWalk implements Sequence {
   @NotNull
   private final Path start;
   @NotNull
   private final PathWalkOption[] options;

   public PathTreeWalk(@NotNull Path start, @NotNull PathWalkOption[] options) {
      Intrinsics.checkNotNullParameter(start, "start");
      Intrinsics.checkNotNullParameter(options, "options");
      super();
      this.start = start;
      this.options = options;
   }

   private final boolean getFollowLinks() {
      return ArraysKt.contains(this.options, PathWalkOption.FOLLOW_LINKS);
   }

   private final LinkOption[] getLinkOptions() {
      return LinkFollowing.INSTANCE.toLinkOptions(this.getFollowLinks());
   }

   private final boolean getIncludeDirectories() {
      return ArraysKt.contains(this.options, PathWalkOption.INCLUDE_DIRECTORIES);
   }

   private final boolean isBFS() {
      return ArraysKt.contains(this.options, PathWalkOption.BREADTH_FIRST);
   }

   @NotNull
   public Iterator iterator() {
      return this.isBFS() ? this.bfsIterator() : this.dfsIterator();
   }

   private final Object yieldIfNeeded(SequenceScope $this$yieldIfNeeded, PathNode node, DirectoryEntriesReader entriesReader, Function1 entriesAction, Continuation $completion) {
      int $i$f$yieldIfNeeded = 0;
      Path path = node.getPath();
      if (node.getParent() != null) {
         PathsKt.checkFileName(path);
      }

      LinkOption[] var9 = access$getLinkOptions(this);
      var9 = (LinkOption[])Arrays.copyOf(var9, var9.length);
      if (Files.isDirectory(path, (LinkOption[])Arrays.copyOf(var9, var9.length))) {
         if (PathTreeWalkKt.access$createsCycle(node)) {
            throw new FileSystemLoopException(path.toString());
         }

         if (access$getIncludeDirectories(this)) {
            InlineMarker.mark(0);
            $this$yieldIfNeeded.yield(path, $completion);
            InlineMarker.mark(1);
         }

         var9 = access$getLinkOptions(this);
         var9 = (LinkOption[])Arrays.copyOf(var9, var9.length);
         if (Files.isDirectory(path, (LinkOption[])Arrays.copyOf(var9, var9.length))) {
            entriesAction.invoke(entriesReader.readEntries(node));
         }
      } else {
         var9 = new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
         if (Files.exists(path, (LinkOption[])Arrays.copyOf(var9, var9.length))) {
            InlineMarker.mark(0);
            $this$yieldIfNeeded.yield(path, $completion);
            InlineMarker.mark(1);
            return Unit.INSTANCE;
         }
      }

      return Unit.INSTANCE;
   }

   private final Iterator dfsIterator() {
      return SequencesKt.iterator(new Function2(this, (Continuation)null) {
         Object L$1;
         Object L$2;
         Object L$3;
         Object L$4;
         Object L$5;
         int label;
         // $FF: synthetic field
         private Object L$0;

         public final Object invokeSuspend(Object $result) {
            Object var17 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            SequenceScope $this$iterator;
            ArrayDeque stack;
            DirectoryEntriesReader entriesReader;
            switch (this.label) {
               case 0:
                  ResultKt.throwOnFailure($result);
                  $this$iterator = (SequenceScope)this.L$0;
                  stack = new ArrayDeque();
                  entriesReader = new DirectoryEntriesReader(PathTreeWalk.this.getFollowLinks());
                  PathNode startNode = new PathNode(PathTreeWalk.this.start, PathTreeWalkKt.access$keyOf(PathTreeWalk.this.start, PathTreeWalk.this.getLinkOptions()), (PathNode)null);
                  PathTreeWalk this_$ivx = PathTreeWalk.this;
                  int $i$f$yieldIfNeeded = 0;
                  Path path$iv = startNode.getPath();
                  if (startNode.getParent() != null) {
                     PathsKt.checkFileName(path$iv);
                  }

                  LinkOption[] var31 = this_$ivx.getLinkOptions();
                  var31 = (LinkOption[])Arrays.copyOf(var31, var31.length);
                  if (Files.isDirectory(path$iv, (LinkOption[])Arrays.copyOf(var31, var31.length))) {
                     if (PathTreeWalkKt.access$createsCycle(startNode)) {
                        throw new FileSystemLoopException(path$iv.toString());
                     }

                     if (this_$ivx.getIncludeDirectories()) {
                        Continuation var10002 = this;
                        this.L$0 = $this$iterator;
                        this.L$1 = stack;
                        this.L$2 = entriesReader;
                        this.L$3 = startNode;
                        this.L$4 = this_$ivx;
                        this.L$5 = path$iv;
                        this.label = 1;
                        if ($this$iterator.yield(path$iv, var10002) == var17) {
                           return var17;
                        }
                     }

                     var31 = this_$ivx.getLinkOptions();
                     var31 = (LinkOption[])Arrays.copyOf(var31, var31.length);
                     if (Files.isDirectory(path$iv, (LinkOption[])Arrays.copyOf(var31, var31.length))) {
                        List entries = entriesReader.readEntries(startNode);
                        int var40 = 0;
                        startNode.setContentIterator(entries.iterator());
                        stack.addLast(startNode);
                     }
                  } else {
                     var31 = new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
                     if (Files.exists(path$iv, (LinkOption[])Arrays.copyOf(var31, var31.length))) {
                        Continuation var49 = this;
                        this.L$0 = $this$iterator;
                        this.L$1 = stack;
                        this.L$2 = entriesReader;
                        this.label = 2;
                        if ($this$iterator.yield(path$iv, var49) == var17) {
                           return var17;
                        }
                     }
                  }
                  break;
               case 1:
                  int $i$f$yieldIfNeeded = 0;
                  Path path$iv = (Path)this.L$5;
                  PathTreeWalk this_$iv = (PathTreeWalk)this.L$4;
                  PathNode startNode = (PathNode)this.L$3;
                  entriesReader = (DirectoryEntriesReader)this.L$2;
                  stack = (ArrayDeque)this.L$1;
                  $this$iterator = (SequenceScope)this.L$0;
                  ResultKt.throwOnFailure($result);
                  LinkOption[] var29 = this_$iv.getLinkOptions();
                  var29 = (LinkOption[])Arrays.copyOf(var29, var29.length);
                  if (Files.isDirectory(path$iv, (LinkOption[])Arrays.copyOf(var29, var29.length))) {
                     List var37 = entriesReader.readEntries(startNode);
                     boolean var13 = false;
                     startNode.setContentIterator(var37.iterator());
                     stack.addLast(startNode);
                  }
                  break;
               case 2:
                  int $i$f$yieldIfNeeded = 0;
                  entriesReader = (DirectoryEntriesReader)this.L$2;
                  stack = (ArrayDeque)this.L$1;
                  $this$iterator = (SequenceScope)this.L$0;
                  ResultKt.throwOnFailure($result);
                  break;
               case 3:
                  int $i$f$yieldIfNeeded = 0;
                  Path path$iv = (Path)this.L$5;
                  PathTreeWalk this_$iv = (PathTreeWalk)this.L$4;
                  PathNode pathNode = (PathNode)this.L$3;
                  entriesReader = (DirectoryEntriesReader)this.L$2;
                  stack = (ArrayDeque)this.L$1;
                  $this$iterator = (SequenceScope)this.L$0;
                  ResultKt.throwOnFailure($result);
                  LinkOption[] var14 = this_$iv.getLinkOptions();
                  var14 = (LinkOption[])Arrays.copyOf(var14, var14.length);
                  if (Files.isDirectory(path$iv, (LinkOption[])Arrays.copyOf(var14, var14.length))) {
                     List entries = entriesReader.readEntries(pathNode);
                     int var16 = 0;
                     pathNode.setContentIterator(entries.iterator());
                     stack.addLast(pathNode);
                  }
                  break;
               case 4:
                  int $i$f$yieldIfNeeded = 0;
                  entriesReader = (DirectoryEntriesReader)this.L$2;
                  stack = (ArrayDeque)this.L$1;
                  $this$iterator = (SequenceScope)this.L$0;
                  ResultKt.throwOnFailure($result);
                  break;
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            while(!((Collection)stack).isEmpty()) {
               PathNode topNode = (PathNode)stack.last();
               Iterator var10000 = topNode.getContentIterator();
               Intrinsics.checkNotNull(var10000);
               Iterator topIterator = var10000;
               if (topIterator.hasNext()) {
                  PathNode pathNode = (PathNode)topIterator.next();
                  PathTreeWalk this_$ivxx = PathTreeWalk.this;
                  int $i$f$yieldIfNeeded = 0;
                  Path path$iv = pathNode.getPath();
                  if (pathNode.getParent() != null) {
                     PathsKt.checkFileName(path$iv);
                  }

                  LinkOption[] var42 = this_$ivxx.getLinkOptions();
                  var42 = (LinkOption[])Arrays.copyOf(var42, var42.length);
                  if (Files.isDirectory(path$iv, (LinkOption[])Arrays.copyOf(var42, var42.length))) {
                     if (PathTreeWalkKt.access$createsCycle(pathNode)) {
                        throw new FileSystemLoopException(path$iv.toString());
                     }

                     if (this_$ivxx.getIncludeDirectories()) {
                        Continuation var50 = this;
                        this.L$0 = $this$iterator;
                        this.L$1 = stack;
                        this.L$2 = entriesReader;
                        this.L$3 = pathNode;
                        this.L$4 = this_$ivxx;
                        this.L$5 = path$iv;
                        this.label = 3;
                        if ($this$iterator.yield(path$iv, var50) == var17) {
                           return var17;
                        }
                     }

                     var42 = this_$ivxx.getLinkOptions();
                     var42 = (LinkOption[])Arrays.copyOf(var42, var42.length);
                     if (Files.isDirectory(path$iv, (LinkOption[])Arrays.copyOf(var42, var42.length))) {
                        List var47 = entriesReader.readEntries(pathNode);
                        boolean var48 = false;
                        pathNode.setContentIterator(var47.iterator());
                        stack.addLast(pathNode);
                     }
                  } else {
                     var42 = new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
                     if (Files.exists(path$iv, (LinkOption[])Arrays.copyOf(var42, var42.length))) {
                        Continuation var51 = this;
                        this.L$0 = $this$iterator;
                        this.L$1 = stack;
                        this.L$2 = entriesReader;
                        this.L$3 = null;
                        this.L$4 = null;
                        this.L$5 = null;
                        this.label = 4;
                        if ($this$iterator.yield(path$iv, var51) == var17) {
                           return var17;
                        }
                     }
                  }
               } else {
                  stack.removeLast();
               }
            }

            return Unit.INSTANCE;
         }

         public final Continuation create(Object value, Continuation $completion) {
            Function2 var3 = new <anonymous constructor>(PathTreeWalk.this, $completion);
            var3.L$0 = value;
            return (Continuation)var3;
         }

         public final Object invoke(SequenceScope p1, Continuation p2) {
            return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
         }
      });
   }

   private final Iterator bfsIterator() {
      return SequencesKt.iterator(new Function2(this, (Continuation)null) {
         Object L$1;
         Object L$2;
         Object L$3;
         Object L$4;
         Object L$5;
         int label;
         // $FF: synthetic field
         private Object L$0;

         public final Object invokeSuspend(Object $result) {
            Object var14 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            SequenceScope $this$iterator;
            ArrayDeque queue;
            DirectoryEntriesReader entriesReader;
            switch (this.label) {
               case 0:
                  ResultKt.throwOnFailure($result);
                  $this$iterator = (SequenceScope)this.L$0;
                  queue = new ArrayDeque();
                  entriesReader = new DirectoryEntriesReader(PathTreeWalk.this.getFollowLinks());
                  queue.addLast(new PathNode(PathTreeWalk.this.start, PathTreeWalkKt.access$keyOf(PathTreeWalk.this.start, PathTreeWalk.this.getLinkOptions()), (PathNode)null));
                  break;
               case 1:
                  int $i$f$yieldIfNeeded = 0;
                  Path path$iv = (Path)this.L$5;
                  PathTreeWalk this_$iv = (PathTreeWalk)this.L$4;
                  PathNode pathNode = (PathNode)this.L$3;
                  entriesReader = (DirectoryEntriesReader)this.L$2;
                  queue = (ArrayDeque)this.L$1;
                  $this$iterator = (SequenceScope)this.L$0;
                  ResultKt.throwOnFailure($result);
                  LinkOption[] var11 = this_$iv.getLinkOptions();
                  var11 = (LinkOption[])Arrays.copyOf(var11, var11.length);
                  if (Files.isDirectory(path$iv, (LinkOption[])Arrays.copyOf(var11, var11.length))) {
                     List entries = entriesReader.readEntries(pathNode);
                     int var13 = 0;
                     queue.addAll((Collection)entries);
                  }
                  break;
               case 2:
                  int $i$f$yieldIfNeeded = 0;
                  entriesReader = (DirectoryEntriesReader)this.L$2;
                  queue = (ArrayDeque)this.L$1;
                  $this$iterator = (SequenceScope)this.L$0;
                  ResultKt.throwOnFailure($result);
                  break;
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            while(!((Collection)queue).isEmpty()) {
               PathNode pathNode = (PathNode)queue.removeFirst();
               PathTreeWalk this_$ivx = PathTreeWalk.this;
               int $i$f$yieldIfNeeded = 0;
               Path path$iv = pathNode.getPath();
               if (pathNode.getParent() != null) {
                  PathsKt.checkFileName(path$iv);
               }

               LinkOption[] var21 = this_$ivx.getLinkOptions();
               var21 = (LinkOption[])Arrays.copyOf(var21, var21.length);
               if (Files.isDirectory(path$iv, (LinkOption[])Arrays.copyOf(var21, var21.length))) {
                  if (PathTreeWalkKt.access$createsCycle(pathNode)) {
                     throw new FileSystemLoopException(path$iv.toString());
                  }

                  if (this_$ivx.getIncludeDirectories()) {
                     Continuation var10002 = this;
                     this.L$0 = $this$iterator;
                     this.L$1 = queue;
                     this.L$2 = entriesReader;
                     this.L$3 = pathNode;
                     this.L$4 = this_$ivx;
                     this.L$5 = path$iv;
                     this.label = 1;
                     if ($this$iterator.yield(path$iv, var10002) == var14) {
                        return var14;
                     }
                  }

                  var21 = this_$ivx.getLinkOptions();
                  var21 = (LinkOption[])Arrays.copyOf(var21, var21.length);
                  if (Files.isDirectory(path$iv, (LinkOption[])Arrays.copyOf(var21, var21.length))) {
                     List var26 = entriesReader.readEntries(pathNode);
                     boolean var27 = false;
                     queue.addAll((Collection)var26);
                  }
               } else {
                  var21 = new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
                  if (Files.exists(path$iv, (LinkOption[])Arrays.copyOf(var21, var21.length))) {
                     Continuation var28 = this;
                     this.L$0 = $this$iterator;
                     this.L$1 = queue;
                     this.L$2 = entriesReader;
                     this.L$3 = null;
                     this.L$4 = null;
                     this.L$5 = null;
                     this.label = 2;
                     if ($this$iterator.yield(path$iv, var28) == var14) {
                        return var14;
                     }
                  }
               }
            }

            return Unit.INSTANCE;
         }

         public final Continuation create(Object value, Continuation $completion) {
            Function2 var3 = new <anonymous constructor>(PathTreeWalk.this, $completion);
            var3.L$0 = value;
            return (Continuation)var3;
         }

         public final Object invoke(SequenceScope p1, Continuation p2) {
            return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
         }
      });
   }
}
