package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000\u001a\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0003\u0010\u0004\u001a)\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"},
   d2 = {"", "value", "", "assert", "(Z)V", "Lkotlin/Function0;", "", "lazyMessage", "(ZLkotlin/jvm/functions/Function0;)V", "kotlin-stdlib"},
   xs = "kotlin/PreconditionsKt"
)
@SourceDebugExtension({"SMAP\nAssertionsJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AssertionsJVM.kt\nkotlin/PreconditionsKt__AssertionsJVMKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,39:1\n1#2:40\n*E\n"})
class PreconditionsKt__AssertionsJVMKt {
   @InlineOnly
   private static final void assert(boolean value) {
      if (_Assertions.ENABLED && !value) {
         int var1 = 0;
         String var2 = "Assertion failed";
         throw new AssertionError(var2);
      }
   }

   @InlineOnly
   private static final void assert(boolean value, Function0 lazyMessage) {
      Intrinsics.checkNotNullParameter(lazyMessage, "lazyMessage");
      if (_Assertions.ENABLED && !value) {
         Object message = lazyMessage.invoke();
         throw new AssertionError(message);
      }
   }

   public PreconditionsKt__AssertionsJVMKt() {
   }
}
