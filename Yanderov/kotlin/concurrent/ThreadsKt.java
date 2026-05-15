package kotlin.concurrent;

import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000:\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aQ\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\r\u0010\u000e\u001a5\u0010\u0013\u001a\u00028\u0000\"\b\b\u0000\u0010\u0010*\u00020\u000f*\b\u0012\u0004\u0012\u00028\u00000\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0015"},
   d2 = {"", "start", "isDaemon", "Ljava/lang/ClassLoader;", "contextClassLoader", "", "name", "", "priority", "Lkotlin/Function0;", "", "block", "Ljava/lang/Thread;", "thread", "(ZZLjava/lang/ClassLoader;Ljava/lang/String;ILkotlin/jvm/functions/Function0;)Ljava/lang/Thread;", "", "T", "Ljava/lang/ThreadLocal;", "default", "getOrSet", "(Ljava/lang/ThreadLocal;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlin-stdlib"}
)
@JvmName(
   name = "ThreadsKt"
)
@SourceDebugExtension({"SMAP\nThread.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Thread.kt\nkotlin/concurrent/ThreadsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,61:1\n1#2:62\n*E\n"})
public final class ThreadsKt {
   @NotNull
   public static final Thread thread(boolean start, boolean isDaemon, @Nullable ClassLoader contextClassLoader, @Nullable String name, int priority, @NotNull final Function0 block) {
      Intrinsics.checkNotNullParameter(block, "block");
      <undefinedtype> thread = new Thread(block) {
         public void run() {
            block.invoke();
         }
      };
      if (isDaemon) {
         thread.setDaemon(true);
      }

      if (priority > 0) {
         thread.setPriority(priority);
      }

      if (name != null) {
         thread.setName(name);
      }

      if (contextClassLoader != null) {
         thread.setContextClassLoader(contextClassLoader);
      }

      if (start) {
         thread.start();
      }

      return thread;
   }

   // $FF: synthetic method
   public static Thread thread$default(boolean var0, boolean var1, ClassLoader var2, String var3, int var4, Function0 var5, int var6, Object var7) {
      if ((var6 & 1) != 0) {
         var0 = true;
      }

      if ((var6 & 2) != 0) {
         var1 = false;
      }

      if ((var6 & 4) != 0) {
         var2 = null;
      }

      if ((var6 & 8) != 0) {
         var3 = null;
      }

      if ((var6 & 16) != 0) {
         var4 = -1;
      }

      return thread(var0, var1, var2, var3, var4, var5);
   }

   @InlineOnly
   private static final Object getOrSet(ThreadLocal $this$getOrSet, Function0 default) {
      Intrinsics.checkNotNullParameter($this$getOrSet, "<this>");
      Intrinsics.checkNotNullParameter(default, "default");
      Object var2 = $this$getOrSet.get();
      Object var10000;
      if (var2 == null) {
         Object p0 = default.invoke();
         int var5 = 0;
         $this$getOrSet.set(p0);
         var10000 = p0;
      } else {
         var10000 = var2;
      }

      return var10000;
   }
}
