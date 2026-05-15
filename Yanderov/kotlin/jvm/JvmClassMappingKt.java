package kotlin.jvm;

import java.lang.annotation.Annotation;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000,\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\u001a!\u0010\u0004\u001a\u00020\u0003\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u0006\u0012\u0002\b\u00030\u0002¢\u0006\u0004\b\u0004\u0010\u0005\"'\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0007\"\b\b\u0000\u0010\u0001*\u00020\u0006*\u00028\u00008F¢\u0006\u0006\u001a\u0004\b\b\u0010\t\";\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\r\"\u000e\b\u0000\u0010\f*\b\u0012\u0004\u0012\u00028\u00000\u000b*\b\u0012\u0004\u0012\u00028\u00000\u000b8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f\"-\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\r\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00078G¢\u0006\f\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014\"&\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\r\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00028\u00008Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0018\";\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\r\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00078Ç\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u001b\u0010\u0016\u001a\u0004\b\u001a\u0010\u0014\"+\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\r\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00078F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0014\"-\u0010\u001f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\r\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00078F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0014\"+\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\r8G¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006#"},
   d2 = {"", "T", "", "", "isArrayOf", "([Ljava/lang/Object;)Z", "", "Lkotlin/reflect/KClass;", "getAnnotationClass", "(Ljava/lang/annotation/Annotation;)Lkotlin/reflect/KClass;", "annotationClass", "", "E", "Ljava/lang/Class;", "getDeclaringJavaClass", "(Ljava/lang/Enum;)Ljava/lang/Class;", "getDeclaringJavaClass$annotations", "(Ljava/lang/Enum;)V", "declaringJavaClass", "getJavaClass", "(Lkotlin/reflect/KClass;)Ljava/lang/Class;", "getJavaClass$annotations", "(Lkotlin/reflect/KClass;)V", "java", "(Ljava/lang/Object;)Ljava/lang/Class;", "javaClass", "getRuntimeClassOfKClassInstance", "getRuntimeClassOfKClassInstance$annotations", "getJavaObjectType", "javaObjectType", "getJavaPrimitiveType", "javaPrimitiveType", "getKotlinClass", "(Ljava/lang/Class;)Lkotlin/reflect/KClass;", "kotlin", "kotlin-stdlib"}
)
@JvmName(
   name = "JvmClassMappingKt"
)
public final class JvmClassMappingKt {
   @JvmName(
      name = "getJavaClass"
   )
   @NotNull
   public static final Class getJavaClass(@NotNull KClass $this$java) {
      Intrinsics.checkNotNullParameter($this$java, "<this>");
      Class var10000 = ((ClassBasedDeclarationContainer)$this$java).getJClass();
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
      return var10000;
   }

   /** @deprecated */
   // $FF: synthetic method
   public static void getJavaClass$annotations(KClass <this>) {
   }

   @Nullable
   public static final Class getJavaPrimitiveType(@NotNull KClass $this$javaPrimitiveType) {
      Intrinsics.checkNotNullParameter($this$javaPrimitiveType, "<this>");
      Class thisJClass = ((ClassBasedDeclarationContainer)$this$javaPrimitiveType).getJClass();
      if (thisJClass.isPrimitive()) {
         Intrinsics.checkNotNull(thisJClass, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-javaPrimitiveType>>");
         return thisJClass;
      } else {
         Class var10000;
         if (var2 != null) {
            switch (var2) {
               case "java.lang.Integer":
                  var10000 = Integer.TYPE;
                  return var10000;
               case "java.lang.Float":
                  var10000 = Float.TYPE;
                  return var10000;
               case "java.lang.Short":
                  var10000 = Short.TYPE;
                  return var10000;
               case "java.lang.Character":
                  var10000 = Character.TYPE;
                  return var10000;
               case "java.lang.Boolean":
                  var10000 = Boolean.TYPE;
                  return var10000;
               case "java.lang.Byte":
                  var10000 = Byte.TYPE;
                  return var10000;
               case "java.lang.Long":
                  var10000 = Long.TYPE;
                  return var10000;
               case "java.lang.Void":
                  var10000 = Void.TYPE;
                  return var10000;
               case "java.lang.Double":
                  var10000 = Double.TYPE;
                  return var10000;
            }
         }

         var10000 = null;
         return var10000;
      }
   }

   @NotNull
   public static final Class getJavaObjectType(@NotNull KClass $this$javaObjectType) {
      Intrinsics.checkNotNullParameter($this$javaObjectType, "<this>");
      Class thisJClass = ((ClassBasedDeclarationContainer)$this$javaObjectType).getJClass();
      if (!thisJClass.isPrimitive()) {
         Intrinsics.checkNotNull(thisJClass, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-javaObjectType>>");
         return thisJClass;
      } else {
         Class var10000;
         label52: {
            if (var2 != null) {
               switch (var2) {
                  case "double":
                     var10000 = Double.class;
                     break label52;
                  case "int":
                     var10000 = Integer.class;
                     break label52;
                  case "byte":
                     var10000 = Byte.class;
                     break label52;
                  case "char":
                     var10000 = Character.class;
                     break label52;
                  case "long":
                     var10000 = Long.class;
                     break label52;
                  case "void":
                     var10000 = Void.class;
                     break label52;
                  case "boolean":
                     var10000 = Boolean.class;
                     break label52;
                  case "float":
                     var10000 = Float.class;
                     break label52;
                  case "short":
                     var10000 = Short.class;
                     break label52;
               }
            }

            var10000 = thisJClass;
         }

         Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-javaObjectType>>");
         return var10000;
      }
   }

   @JvmName(
      name = "getKotlinClass"
   )
   @NotNull
   public static final KClass getKotlinClass(@NotNull Class $this$kotlin) {
      Intrinsics.checkNotNullParameter($this$kotlin, "<this>");
      return Reflection.getOrCreateKotlinClass($this$kotlin);
   }

   @NotNull
   public static final Class getJavaClass(@NotNull Object $this$javaClass) {
      Intrinsics.checkNotNullParameter($this$javaClass, "<this>");
      int $i$f$getJavaClass = 0;
      Class var10000 = $this$javaClass.getClass();
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-javaClass>>");
      return var10000;
   }

   /** @deprecated */
   @JvmName(
      name = "getRuntimeClassOfKClassInstance"
   )
   @NotNull
   public static final Class getRuntimeClassOfKClassInstance(@NotNull KClass $this$javaClass) {
      Intrinsics.checkNotNullParameter($this$javaClass, "<this>");
      int $i$f$getRuntimeClassOfKClassInstance = 0;
      Class var10000 = ((Object)$this$javaClass).getClass();
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type java.lang.Class<kotlin.reflect.KClass<T of kotlin.jvm.JvmClassMappingKt.<get-javaClass>>>");
      return var10000;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use 'java' property to get Java class corresponding to this Kotlin class or cast this instance to Any if you really want to get the runtime Java class of this implementation of KClass.",
      replaceWith = @ReplaceWith(
   expression = "(this as Any).javaClass",
   imports = {}
),
      level = DeprecationLevel.ERROR
   )
   public static void getRuntimeClassOfKClassInstance$annotations(KClass <this>) {
   }

   // $FF: synthetic method
   public static final boolean isArrayOf(Object[] $this$isArrayOf) {
      Intrinsics.checkNotNullParameter($this$isArrayOf, "<this>");
      Intrinsics.reifiedOperationMarker(4, "T");
      return ((Class)Object.class).isAssignableFrom(((Class)$this$isArrayOf.getClass()).getComponentType());
   }

   @NotNull
   public static final KClass getAnnotationClass(@NotNull Annotation $this$annotationClass) {
      Intrinsics.checkNotNullParameter($this$annotationClass, "<this>");
      Class var10000 = $this$annotationClass.annotationType();
      Intrinsics.checkNotNullExpressionValue(var10000, "annotationType(...)");
      KClass var1 = getKotlinClass(var10000);
      Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type kotlin.reflect.KClass<out T of kotlin.jvm.JvmClassMappingKt.<get-annotationClass>>");
      return var1;
   }

   private static final Class getDeclaringJavaClass(Enum $this$declaringJavaClass) {
      Intrinsics.checkNotNullParameter($this$declaringJavaClass, "<this>");
      Class var10000 = $this$declaringJavaClass.getDeclaringClass();
      Intrinsics.checkNotNullExpressionValue(var10000, "getDeclaringClass(...)");
      return var10000;
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.7"
   )
   @InlineOnly
   public static void getDeclaringJavaClass$annotations(Enum <this>) {
   }
}
