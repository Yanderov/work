package kotlin;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.RestrictsSuspension;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B\t\b\u0004¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0007\u001a\u00028\u00012\u0006\u0010\u0006\u001a\u00028\u0000H¦@¢\u0006\u0004\b\u0007\u0010\bJ4\u0010\u0007\u001a\u00028\u0003\"\u0004\b\u0002\u0010\t\"\u0004\b\u0003\u0010\n*\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u000b2\u0006\u0010\u0006\u001a\u00028\u0002H¦@¢\u0006\u0004\b\u0007\u0010\fJ&\u0010\u000e\u001a\u00020\r*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003H\u0087\u0002¢\u0006\u0004\b\u000e\u0010\u000f\u0082\u0001\u0001\u0010¨\u0006\u0011"},
   d2 = {"Lkotlin/DeepRecursiveScope;", "T", "R", "", "<init>", "()V", "value", "callRecursive", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "U", "S", "Lkotlin/DeepRecursiveFunction;", "(Lkotlin/DeepRecursiveFunction;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "invoke", "(Lkotlin/DeepRecursiveFunction;Ljava/lang/Object;)Ljava/lang/Void;", "Lkotlin/DeepRecursiveScopeImpl;", "kotlin-stdlib"}
)
@RestrictsSuspension
@SinceKotlin(
   version = "1.7"
)
@WasExperimental(
   markerClass = {ExperimentalStdlibApi.class}
)
public abstract class DeepRecursiveScope {
   private DeepRecursiveScope() {
   }

   @Nullable
   public abstract Object callRecursive(Object var1, @NotNull Continuation var2);

   @Nullable
   public abstract Object callRecursive(@NotNull DeepRecursiveFunction var1, Object var2, @NotNull Continuation var3);

   /** @deprecated */
   @Deprecated(
      message = "'invoke' should not be called from DeepRecursiveScope. Use 'callRecursive' to do recursion in the heap instead of the call stack.",
      replaceWith = @ReplaceWith(
   expression = "this.callRecursive(value)",
   imports = {}
),
      level = DeprecationLevel.ERROR
   )
   @NotNull
   public final Void invoke(@NotNull DeepRecursiveFunction $this$invoke, @Nullable Object value) {
      Intrinsics.checkNotNullParameter($this$invoke, "<this>");
      throw new UnsupportedOperationException("Should not be called from DeepRecursiveScope");
   }

   // $FF: synthetic method
   public DeepRecursiveScope(DefaultConstructorMarker $constructor_marker) {
      this();
   }
}
