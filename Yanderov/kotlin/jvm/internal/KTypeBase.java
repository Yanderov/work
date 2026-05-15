package kotlin.jvm.internal;

import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.reflect.KType;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0006"},
   d2 = {"Lkotlin/jvm/internal/KTypeBase;", "Lkotlin/reflect/KType;", "Ljava/lang/reflect/Type;", "getJavaType", "()Ljava/lang/reflect/Type;", "javaType", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.4"
)
public interface KTypeBase extends KType {
   @Nullable
   Type getJavaType();
}
