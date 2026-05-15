package kotlin.contracts;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.ContractsDsl;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H§\u0004¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
   d2 = {"Lkotlin/contracts/SimpleEffect;", "Lkotlin/contracts/Effect;", "", "booleanExpression", "Lkotlin/contracts/ConditionalEffect;", "implies", "(Z)Lkotlin/contracts/ConditionalEffect;", "kotlin-stdlib"}
)
@ContractsDsl
@ExperimentalContracts
@SinceKotlin(
   version = "1.3"
)
public interface SimpleEffect extends Effect {
   @ContractsDsl
   @ExperimentalContracts
   @NotNull
   ConditionalEffect implies(boolean var1);
}
