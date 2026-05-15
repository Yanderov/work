package kotlin.io;

import java.io.Closeable;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001f\u0010\u0004\u001a\u00020\u0003*\u0004\u0018\u00010\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001aJ\u0010\n\u001a\u00028\u0001\"\n\b\u0000\u0010\u0006*\u0004\u0018\u00010\u0000\"\u0004\b\u0001\u0010\u0007*\u00028\u00002\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\bH\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\f"},
   d2 = {"Ljava/io/Closeable;", "", "cause", "", "closeFinally", "(Ljava/io/Closeable;Ljava/lang/Throwable;)V", "T", "R", "Lkotlin/Function1;", "block", "use", "(Ljava/io/Closeable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib"}
)
@JvmName(
   name = "CloseableKt"
)
public final class CloseableKt {
   @InlineOnly
   private static final Object use(Closeable $this$use, Function1 block) {
      Intrinsics.checkNotNullParameter(block, "block");
      Throwable exception = null;
      boolean var8 = false;

      Object var3;
      try {
         var8 = true;
         var3 = block.invoke($this$use);
         var8 = false;
      } catch (Throwable e) {
         exception = e;
         throw e;
      } finally {
         if (var8) {
            InlineMarker.finallyStart(1);
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
               closeFinally($this$use, exception);
            } else if ($this$use != null) {
               if (exception == null) {
                  $this$use.close();
               } else {
                  try {
                     $this$use.close();
                  } catch (Throwable var9) {
                  }
               }
            }

            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
         closeFinally($this$use, exception);
      } else if ($this$use != null) {
         $this$use.close();
      }

      InlineMarker.finallyEnd(1);
      return var3;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @PublishedApi
   public static final void closeFinally(@Nullable Closeable $this$closeFinally, @Nullable Throwable cause) {
      if ($this$closeFinally != null) {
         if (cause == null) {
            $this$closeFinally.close();
         } else {
            try {
               $this$closeFinally.close();
            } catch (Throwable closeException) {
               kotlin.ExceptionsKt.addSuppressed(cause, closeException);
            }
         }
      }

   }
}
