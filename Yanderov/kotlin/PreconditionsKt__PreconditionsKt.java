package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000\"\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u0001\n\u0002\b\u0005\u001a#\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001¢\u0006\u0004\b\u0003\u0010\u0004\u001a4\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\bø\u0001\u0000\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001¢\u0006\u0004\b\u0003\u0010\b\u001a1\u0010\n\u001a\u00028\u0000\"\b\b\u0000\u0010\t*\u00020\u00062\b\u0010\u0001\u001a\u0004\u0018\u00018\u0000H\u0087\b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0004\b\n\u0010\u000b\u001aB\u0010\n\u001a\u00028\u0000\"\b\b\u0000\u0010\t*\u00020\u00062\b\u0010\u0001\u001a\u0004\u0018\u00018\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0004\b\n\u0010\f\u001a\u0018\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u0006H\u0087\b¢\u0006\u0004\b\u000f\u0010\u0010\u001a#\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001¢\u0006\u0004\b\u0011\u0010\u0004\u001a4\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\bø\u0001\u0000\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001¢\u0006\u0004\b\u0011\u0010\b\u001a1\u0010\u0012\u001a\u00028\u0000\"\b\b\u0000\u0010\t*\u00020\u00062\b\u0010\u0001\u001a\u0004\u0018\u00018\u0000H\u0087\b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0004\b\u0012\u0010\u000b\u001aB\u0010\u0012\u001a\u00028\u0000\"\b\b\u0000\u0010\t*\u00020\u00062\b\u0010\u0001\u001a\u0004\u0018\u00018\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0004\b\u0012\u0010\f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0013"},
   d2 = {"", "value", "", "check", "(Z)V", "Lkotlin/Function0;", "", "lazyMessage", "(ZLkotlin/jvm/functions/Function0;)V", "T", "checkNotNull", "(Ljava/lang/Object;)Ljava/lang/Object;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "message", "", "error", "(Ljava/lang/Object;)Ljava/lang/Void;", "require", "requireNotNull", "kotlin-stdlib"},
   xs = "kotlin/PreconditionsKt"
)
@SourceDebugExtension({"SMAP\nPreconditions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Preconditions.kt\nkotlin/PreconditionsKt__PreconditionsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,144:1\n1#2:145\n*E\n"})
class PreconditionsKt__PreconditionsKt extends PreconditionsKt__AssertionsJVMKt {
   @InlineOnly
   private static final void require(boolean value) {
      if (!value) {
         int var1 = 0;
         String var2 = "Failed requirement.";
         throw new IllegalArgumentException(var2.toString());
      }
   }

   @InlineOnly
   private static final void require(boolean value, Function0 lazyMessage) {
      Intrinsics.checkNotNullParameter(lazyMessage, "lazyMessage");
      if (!value) {
         Object message = lazyMessage.invoke();
         throw new IllegalArgumentException(message.toString());
      }
   }

   @InlineOnly
   private static final Object requireNotNull(Object value) {
      if (value == null) {
         int var1 = 0;
         String var2 = "Required value was null.";
         throw new IllegalArgumentException(var2.toString());
      } else {
         return value;
      }
   }

   @InlineOnly
   private static final Object requireNotNull(Object value, Function0 lazyMessage) {
      Intrinsics.checkNotNullParameter(lazyMessage, "lazyMessage");
      if (value == null) {
         Object message = lazyMessage.invoke();
         throw new IllegalArgumentException(message.toString());
      } else {
         return value;
      }
   }

   @InlineOnly
   private static final void check(boolean value) {
      if (!value) {
         int var1 = 0;
         String var2 = "Check failed.";
         throw new IllegalStateException(var2.toString());
      }
   }

   @InlineOnly
   private static final void check(boolean value, Function0 lazyMessage) {
      Intrinsics.checkNotNullParameter(lazyMessage, "lazyMessage");
      if (!value) {
         Object message = lazyMessage.invoke();
         throw new IllegalStateException(message.toString());
      }
   }

   @InlineOnly
   private static final Object checkNotNull(Object value) {
      if (value == null) {
         int var1 = 0;
         String var2 = "Required value was null.";
         throw new IllegalStateException(var2.toString());
      } else {
         return value;
      }
   }

   @InlineOnly
   private static final Object checkNotNull(Object value, Function0 lazyMessage) {
      Intrinsics.checkNotNullParameter(lazyMessage, "lazyMessage");
      if (value == null) {
         Object message = lazyMessage.invoke();
         throw new IllegalStateException(message.toString());
      } else {
         return value;
      }
   }

   @InlineOnly
   private static final Void error(Object message) {
      Intrinsics.checkNotNullParameter(message, "message");
      throw new IllegalStateException(message.toString());
   }

   public PreconditionsKt__PreconditionsKt() {
   }
}
