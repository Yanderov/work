package kotlin.reflect;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0096\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0014\u0010\u0010R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0015¨\u0006\u0016"},
   d2 = {"Lkotlin/reflect/GenericArrayTypeImpl;", "Ljava/lang/reflect/GenericArrayType;", "Lkotlin/reflect/TypeImpl;", "Ljava/lang/reflect/Type;", "elementType", "<init>", "(Ljava/lang/reflect/Type;)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "getGenericComponentType", "()Ljava/lang/reflect/Type;", "", "getTypeName", "()Ljava/lang/String;", "", "hashCode", "()I", "toString", "Ljava/lang/reflect/Type;", "kotlin-stdlib"}
)
@ExperimentalStdlibApi
final class GenericArrayTypeImpl implements GenericArrayType, TypeImpl {
   @NotNull
   private final Type elementType;

   public GenericArrayTypeImpl(@NotNull Type elementType) {
      Intrinsics.checkNotNullParameter(elementType, "elementType");
      super();
      this.elementType = elementType;
   }

   @NotNull
   public Type getGenericComponentType() {
      return this.elementType;
   }

   @NotNull
   public String getTypeName() {
      return TypesJVMKt.access$typeToString(this.elementType) + "[]";
   }

   public boolean equals(@Nullable Object other) {
      return other instanceof GenericArrayType && Intrinsics.areEqual((Object)this.getGenericComponentType(), (Object)((GenericArrayType)other).getGenericComponentType());
   }

   public int hashCode() {
      return this.getGenericComponentType().hashCode();
   }

   @NotNull
   public String toString() {
      return this.getTypeName();
   }
}
