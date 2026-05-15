package kotlin.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fJ'\u0010\u0011\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u000e*\u00020\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u0013\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0013H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\r0\u0013¢\u0006\u0004\b\u0019\u0010\u0015J\u000f\u0010\u001a\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\u001f\u0010\u001eJ\u000f\u0010!\u001a\u00020 H\u0016¢\u0006\u0004\b!\u0010\"J\u000f\u0010#\u001a\u00020\u001cH\u0016¢\u0006\u0004\b#\u0010\u001eR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010$¨\u0006%"},
   d2 = {"Lkotlin/reflect/TypeVariableImpl;", "Ljava/lang/reflect/TypeVariable;", "Ljava/lang/reflect/GenericDeclaration;", "Lkotlin/reflect/TypeImpl;", "Lkotlin/reflect/KTypeParameter;", "typeParameter", "<init>", "(Lkotlin/reflect/KTypeParameter;)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "T", "Ljava/lang/Class;", "annotationClass", "getAnnotation", "(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;", "", "getAnnotations", "()[Ljava/lang/annotation/Annotation;", "Ljava/lang/reflect/Type;", "getBounds", "()[Ljava/lang/reflect/Type;", "getDeclaredAnnotations", "getGenericDeclaration", "()Ljava/lang/reflect/GenericDeclaration;", "", "getName", "()Ljava/lang/String;", "getTypeName", "", "hashCode", "()I", "toString", "Lkotlin/reflect/KTypeParameter;", "kotlin-stdlib"}
)
@ExperimentalStdlibApi
@SourceDebugExtension({"SMAP\nTypesJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TypesJVM.kt\nkotlin/reflect/TypeVariableImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,230:1\n1557#2:231\n1628#2,3:232\n37#3,2:235\n*S KotlinDebug\n*F\n+ 1 TypesJVM.kt\nkotlin/reflect/TypeVariableImpl\n*L\n116#1:231\n116#1:232,3\n116#1:235,2\n*E\n"})
final class TypeVariableImpl implements TypeVariable, TypeImpl {
   @NotNull
   private final KTypeParameter typeParameter;

   public TypeVariableImpl(@NotNull KTypeParameter typeParameter) {
      Intrinsics.checkNotNullParameter(typeParameter, "typeParameter");
      super();
      this.typeParameter = typeParameter;
   }

   @NotNull
   public String getName() {
      return this.typeParameter.getName();
   }

   @NotNull
   public GenericDeclaration getGenericDeclaration() {
      String var1 = "getGenericDeclaration() is not yet supported for type variables created from KType: " + this.typeParameter;
      throw new NotImplementedError("An operation is not implemented: " + var1);
   }

   @NotNull
   public Type[] getBounds() {
      Iterable $this$map$iv = (Iterable)this.typeParameter.getUpperBounds();
      int $i$f$map = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
      int $i$f$mapTo = 0;

      for(Object item$iv$iv : $this$map$iv) {
         KType it = (KType)item$iv$iv;
         int var9 = 0;
         destination$iv$iv.add(TypesJVMKt.access$computeJavaType(it, true));
      }

      Collection $this$toTypedArray$iv = (Collection)((List)destination$iv$iv);
      $i$f$map = 0;
      return (Type[])$this$toTypedArray$iv.toArray(new Type[0]);
   }

   @NotNull
   public String getTypeName() {
      return this.getName();
   }

   public boolean equals(@Nullable Object other) {
      return other instanceof TypeVariable && Intrinsics.areEqual((Object)this.getName(), (Object)((TypeVariable)other).getName()) && Intrinsics.areEqual((Object)this.getGenericDeclaration(), (Object)((TypeVariable)other).getGenericDeclaration());
   }

   public int hashCode() {
      return this.getName().hashCode() ^ this.getGenericDeclaration().hashCode();
   }

   @NotNull
   public String toString() {
      return this.getTypeName();
   }

   @Nullable
   public final Annotation getAnnotation(@NotNull Class annotationClass) {
      Intrinsics.checkNotNullParameter(annotationClass, "annotationClass");
      return null;
   }

   @NotNull
   public final Annotation[] getAnnotations() {
      return new Annotation[0];
   }

   @NotNull
   public final Annotation[] getDeclaredAnnotations() {
      return new Annotation[0];
   }
}
