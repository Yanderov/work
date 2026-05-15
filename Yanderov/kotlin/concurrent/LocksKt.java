package kotlin.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a8\u0010\u0004\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a8\u0010\u0007\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u00062\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\u0007\u0010\b\u001a8\u0010\t\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\t\u0010\u0005\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\n"},
   d2 = {"T", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "Lkotlin/Function0;", "action", "read", "(Ljava/util/concurrent/locks/ReentrantReadWriteLock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Ljava/util/concurrent/locks/Lock;", "withLock", "(Ljava/util/concurrent/locks/Lock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "write", "kotlin-stdlib"}
)
@JvmName(
   name = "LocksKt"
)
@SourceDebugExtension({"SMAP\nLocks.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Locks.kt\nkotlin/concurrent/LocksKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,74:1\n1#2:75\n*E\n"})
public final class LocksKt {
   @InlineOnly
   private static final Object withLock(Lock $this$withLock, Function0 action) {
      Intrinsics.checkNotNullParameter($this$withLock, "<this>");
      Intrinsics.checkNotNullParameter(action, "action");
      $this$withLock.lock();

      Object var2;
      try {
         var2 = action.invoke();
      } finally {
         InlineMarker.finallyStart(1);
         $this$withLock.unlock();
         InlineMarker.finallyEnd(1);
      }

      return var2;
   }

   @InlineOnly
   private static final Object read(ReentrantReadWriteLock $this$read, Function0 action) {
      Intrinsics.checkNotNullParameter($this$read, "<this>");
      Intrinsics.checkNotNullParameter(action, "action");
      ReentrantReadWriteLock.ReadLock rl = $this$read.readLock();
      rl.lock();

      Object var3;
      try {
         var3 = action.invoke();
      } finally {
         InlineMarker.finallyStart(1);
         rl.unlock();
         InlineMarker.finallyEnd(1);
      }

      return var3;
   }

   @InlineOnly
   private static final Object write(ReentrantReadWriteLock $this$write, Function0 action) {
      Intrinsics.checkNotNullParameter($this$write, "<this>");
      Intrinsics.checkNotNullParameter(action, "action");
      ReentrantReadWriteLock.ReadLock rl = $this$write.readLock();
      int readCount = $this$write.getWriteHoldCount() == 0 ? $this$write.getReadHoldCount() : 0;

      for(int var4 = 0; var4 < readCount; ++var4) {
         int var6 = 0;
         rl.unlock();
      }

      ReentrantReadWriteLock.WriteLock wl = $this$write.writeLock();
      wl.lock();

      Object it;
      try {
         it = action.invoke();
      } finally {
         InlineMarker.finallyStart(1);

         for(int var12 = 0; var12 < readCount; ++var12) {
            int var8 = 0;
            rl.lock();
         }

         wl.unlock();
         InlineMarker.finallyEnd(1);
      }

      return it;
   }
}
