package kotlin;

import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000,\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a4\u0010\u0004\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u0006\u0010\u0003\u001a\u00028\u0000H\u0087\u0002¢\u0006\u0004\b\u0004\u0010\u0005\"\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t*r\b\u0002\u0010\u000e\"5\b\u0001\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\f\u0012\u0006\u0012\u0004\u0018\u00010\u00070\n¢\u0006\u0002\b\r25\b\u0001\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\f\u0012\u0006\u0012\u0004\u0018\u00010\u00070\n¢\u0006\u0002\b\r¨\u0006\u000f"},
   d2 = {"T", "R", "Lkotlin/DeepRecursiveFunction;", "value", "invoke", "(Lkotlin/DeepRecursiveFunction;Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlin/Result;", "", "UNDEFINED_RESULT", "Ljava/lang/Object;", "Lkotlin/Function3;", "Lkotlin/DeepRecursiveScope;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "DeepRecursiveFunctionBlock", "kotlin-stdlib"}
)
public final class DeepRecursiveKt {
   @NotNull
   private static final Object UNDEFINED_RESULT;

   @SinceKotlin(
      version = "1.7"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   public static final Object invoke(@NotNull DeepRecursiveFunction $this$invoke, Object value) {
      Intrinsics.checkNotNullParameter($this$invoke, "<this>");
      return (new DeepRecursiveScopeImpl($this$invoke.getBlock$kotlin_stdlib(), value)).runCallLoop();
   }

   // $FF: synthetic method
   public static final Object access$getUNDEFINED_RESULT$p() {
      return UNDEFINED_RESULT;
   }

   static {
      Result.Companion var10000 = Result.Companion;
      UNDEFINED_RESULT = Result.constructor-impl(IntrinsicsKt.getCOROUTINE_SUSPENDED());
   }
}
