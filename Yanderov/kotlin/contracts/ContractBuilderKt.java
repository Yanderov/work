package kotlin.contracts;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.ContractsDsl;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a,\u0010\u0005\u001a\u00020\u00022\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000¢\u0006\u0002\b\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0007"},
   d2 = {"Lkotlin/Function1;", "Lkotlin/contracts/ContractBuilder;", "", "Lkotlin/ExtensionFunctionType;", "builder", "contract", "(Lkotlin/jvm/functions/Function1;)V", "kotlin-stdlib"}
)
public final class ContractBuilderKt {
   @ContractsDsl
   @ExperimentalContracts
   @InlineOnly
   @SinceKotlin(
      version = "1.3"
   )
   private static final void contract(Function1 builder) {
      Intrinsics.checkNotNullParameter(builder, "builder");
   }
}
