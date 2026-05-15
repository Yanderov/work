package kotlin.jdk7;

import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a'\u0010\u0005\u001a\u00060\u0003j\u0002`\u00042\u000e\b\u0004\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001f\u0010\t\u001a\u00020\u0001*\u0004\u0018\u00010\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0001¢\u0006\u0004\b\t\u0010\n\u001aJ\u0010\u000f\u001a\u00028\u0001\"\n\b\u0000\u0010\u000b*\u0004\u0018\u00010\u0003\"\u0004\b\u0001\u0010\f*\u00028\u00002\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\rH\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\u000f\u0010\u0010*\u001a\b\u0007\u0010\u0005\"\u00020\u00032\u00020\u0003B\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0014"},
   d2 = {"Lkotlin/Function0;", "", "closeAction", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "AutoCloseable", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/AutoCloseable;", "", "cause", "closeFinally", "(Ljava/lang/AutoCloseable;Ljava/lang/Throwable;)V", "T", "R", "Lkotlin/Function1;", "block", "use", "(Ljava/lang/AutoCloseable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Lkotlin/SinceKotlin;", "version", "2.0", "kotlin-stdlib-jdk7"},
   pn = ""
)
@JvmName(
   name = "AutoCloseableKt"
)
public final class AutoCloseableKt {
   @SinceKotlin(
      version = "2.0"
   )
   @InlineOnly
   private static final AutoCloseable AutoCloseable(final Function0 closeAction) {
      Intrinsics.checkNotNullParameter(closeAction, "closeAction");
      return new AutoCloseable(closeAction) {
         public final void close() {
            closeAction.invoke();
         }
      };
   }

   @SinceKotlin(
      version = "1.2"
   )
   @InlineOnly
   private static final Object use(AutoCloseable $this$use, Function1 block) {
      Intrinsics.checkNotNullParameter(block, "block");
      Throwable exception = null;

      Object var3;
      try {
         var3 = block.invoke($this$use);
      } catch (Throwable e) {
         exception = e;
         throw e;
      } finally {
         InlineMarker.finallyStart(1);
         closeFinally($this$use, exception);
         InlineMarker.finallyEnd(1);
      }

      return var3;
   }

   @SinceKotlin(
      version = "1.2"
   )
   @PublishedApi
   public static final void closeFinally(@Nullable AutoCloseable $this$closeFinally, @Nullable Throwable cause) {
      if ($this$closeFinally != null) {
         if (cause == null) {
            $this$closeFinally.close();
         } else {
            try {
               $this$closeFinally.close();
            } catch (Throwable closeException) {
               ExceptionsKt.addSuppressed(cause, closeException);
            }
         }
      }

   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "2.0"
   )
   public static void AutoCloseable$annotations() {
   }
}
