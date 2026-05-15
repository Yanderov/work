package kotlin.reflect;

import java.lang.reflect.Type;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bc\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"},
   d2 = {"Lkotlin/reflect/TypeImpl;", "Ljava/lang/reflect/Type;", "", "getTypeName", "()Ljava/lang/String;", "kotlin-stdlib"}
)
@ExperimentalStdlibApi
interface TypeImpl extends Type {
   @NotNull
   String getTypeName();
}
