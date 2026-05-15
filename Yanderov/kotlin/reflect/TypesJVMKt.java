package kotlin.reflect;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.SinceKotlin;
import kotlin.collections.CollectionsKt;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.KTypeBase;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\u001a)\u0010\u0006\u001a\u00020\u00052\n\u0010\u0001\u001a\u0006\u0012\u0002\b\u00030\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0003¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\n\u0010\u000b\u001a\u001d\u0010\u000f\u001a\u00020\u0005*\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\rH\u0003¢\u0006\u0004\b\u000f\u0010\u0010\"\u001e\u0010\u0015\u001a\u00020\u0005*\u00020\f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012\"\u001e\u0010\u0015\u001a\u00020\u0005*\u00020\u00038BX\u0083\u0004¢\u0006\f\u0012\u0004\b\u0013\u0010\u0017\u001a\u0004\b\u0011\u0010\u0016¨\u0006\u0018"},
   d2 = {"Ljava/lang/Class;", "jClass", "", "Lkotlin/reflect/KTypeProjection;", "arguments", "Ljava/lang/reflect/Type;", "createPossiblyInnerType", "(Ljava/lang/Class;Ljava/util/List;)Ljava/lang/reflect/Type;", "type", "", "typeToString", "(Ljava/lang/reflect/Type;)Ljava/lang/String;", "Lkotlin/reflect/KType;", "", "forceWrapper", "computeJavaType", "(Lkotlin/reflect/KType;Z)Ljava/lang/reflect/Type;", "getJavaType", "(Lkotlin/reflect/KType;)Ljava/lang/reflect/Type;", "getJavaType$annotations", "(Lkotlin/reflect/KType;)V", "javaType", "(Lkotlin/reflect/KTypeProjection;)Ljava/lang/reflect/Type;", "(Lkotlin/reflect/KTypeProjection;)V", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nTypesJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TypesJVM.kt\nkotlin/reflect/TypesJVMKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,230:1\n1#2:231\n1557#3:232\n1628#3,3:233\n1557#3:236\n1628#3,3:237\n1557#3:240\n1628#3,3:241\n*S KotlinDebug\n*F\n+ 1 TypesJVM.kt\nkotlin/reflect/TypesJVMKt\n*L\n69#1:232\n69#1:233,3\n71#1:236\n71#1:237,3\n77#1:240\n77#1:241,3\n*E\n"})
public final class TypesJVMKt {
   @NotNull
   public static final Type getJavaType(@NotNull KType $this$javaType) {
      Intrinsics.checkNotNullParameter($this$javaType, "<this>");
      if ($this$javaType instanceof KTypeBase) {
         Type it = ((KTypeBase)$this$javaType).getJavaType();
         if (it != null) {
            int var4 = 0;
            return it;
         }
      }

      return computeJavaType$default($this$javaType, false, 1, (Object)null);
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.4"
   )
   @ExperimentalStdlibApi
   @LowPriorityInOverloadResolution
   public static void getJavaType$annotations(KType <this>) {
   }

   @ExperimentalStdlibApi
   private static final Type computeJavaType(KType $this$computeJavaType, boolean forceWrapper) {
      KClassifier classifier = $this$computeJavaType.getClassifier();
      if (classifier instanceof KTypeParameter) {
         return new TypeVariableImpl((KTypeParameter)classifier);
      } else if (classifier instanceof KClass) {
         Class jClass = forceWrapper ? JvmClassMappingKt.getJavaObjectType((KClass)classifier) : JvmClassMappingKt.getJavaClass((KClass)classifier);
         List arguments = $this$computeJavaType.getArguments();
         if (arguments.isEmpty()) {
            return (Type)jClass;
         } else if (jClass.isArray()) {
            if (jClass.getComponentType().isPrimitive()) {
               return (Type)jClass;
            } else {
               KTypeProjection var10000 = (KTypeProjection)CollectionsKt.singleOrNull(arguments);
               if (var10000 == null) {
                  throw new IllegalArgumentException("kotlin.Array must have exactly one type argument: " + $this$computeJavaType);
               } else {
                  KTypeProjection var5 = var10000;
                  KVariance variance = var5.component1();
                  KType elementType = var5.component2();
                  Type var9;
                  switch (variance == null ? -1 : TypesJVMKt.WhenMappings.$EnumSwitchMapping$0[variance.ordinal()]) {
                     case -1:
                     case 1:
                        var9 = (Type)jClass;
                        break;
                     case 0:
                     default:
                        throw new NoWhenBranchMatchedException();
                     case 2:
                     case 3:
                        Intrinsics.checkNotNull(elementType);
                        Type javaElementType = computeJavaType$default(elementType, false, 1, (Object)null);
                        var9 = javaElementType instanceof Class ? (Type)jClass : (Type)(new GenericArrayTypeImpl(javaElementType));
                  }

                  return var9;
               }
            }
         } else {
            return createPossiblyInnerType(jClass, arguments);
         }
      } else {
         throw new UnsupportedOperationException("Unsupported type classifier: " + $this$computeJavaType);
      }
   }

   // $FF: synthetic method
   static Type computeJavaType$default(KType var0, boolean var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = false;
      }

      return computeJavaType(var0, var1);
   }

   @ExperimentalStdlibApi
   private static final Type createPossiblyInnerType(Class jClass, List arguments) {
      Class var3 = jClass.getDeclaringClass();
      if (var3 == null) {
         Iterable $this$map$iv = (Iterable)arguments;
         Type var42 = null;
         int $i$f$map = 0;
         Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
         int $i$f$mapTo = 0;

         for(Object item$iv$iv : $this$map$iv) {
            KTypeProjection p0 = (KTypeProjection)item$iv$iv;
            int var40 = 0;
            destination$iv$iv.add(getJavaType(p0));
         }

         List var44 = (List)destination$iv$iv;
         return new ParameterizedTypeImpl(jClass, var42, var44);
      } else {
         Class ownerClass = var3;
         if (Modifier.isStatic(jClass.getModifiers())) {
            Type var45 = (Type)var3;
            Iterable var26 = (Iterable)arguments;
            Type var41 = var45;
            int $i$f$map = 0;
            Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault(var26, 10)));
            int $i$f$mapTo = 0;

            for(Object item$iv$iv : var26) {
               KTypeProjection p0 = (KTypeProjection)item$iv$iv;
               int var38 = 0;
               destination$iv$iv.add(getJavaType(p0));
            }

            List var43 = (List)destination$iv$iv;
            return new ParameterizedTypeImpl(jClass, var41, var43);
         } else {
            int n = jClass.getTypeParameters().length;
            Type var10001 = createPossiblyInnerType(ownerClass, arguments.subList(n, arguments.size()));
            Iterable $this$map$iv = (Iterable)arguments.subList(0, n);
            Type var14 = var10001;
            int $i$f$map = 0;
            Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
            int $i$f$mapTo = 0;

            for(Object item$iv$iv : $this$map$iv) {
               KTypeProjection p0 = (KTypeProjection)item$iv$iv;
               int var12 = 0;
               destination$iv$iv.add(getJavaType(p0));
            }

            List var15 = (List)destination$iv$iv;
            return new ParameterizedTypeImpl(jClass, var14, var15);
         }
      }
   }

   private static final Type getJavaType(KTypeProjection $this$javaType) {
      KVariance var10000 = $this$javaType.getVariance();
      if (var10000 == null) {
         return WildcardTypeImpl.Companion.getSTAR();
      } else {
         KVariance variance = var10000;
         KType var3 = $this$javaType.getType();
         Intrinsics.checkNotNull(var3);
         KType type = var3;
         Type var4;
         switch (TypesJVMKt.WhenMappings.$EnumSwitchMapping$0[variance.ordinal()]) {
            case 1:
               var4 = new WildcardTypeImpl((Type)null, computeJavaType(type, true));
               break;
            case 2:
               var4 = computeJavaType(type, true);
               break;
            case 3:
               var4 = new WildcardTypeImpl(computeJavaType(type, true), (Type)null);
               break;
            default:
               throw new NoWhenBranchMatchedException();
         }

         return var4;
      }
   }

   /** @deprecated */
   // $FF: synthetic method
   @ExperimentalStdlibApi
   private static void getJavaType$annotations(KTypeProjection <this>) {
   }

   private static final String typeToString(Type type) {
      String var3;
      if (type instanceof Class) {
         if (((Class)type).isArray()) {
            Sequence unwrap = SequencesKt.generateSequence(type, null.INSTANCE);
            var3 = ((Class)SequencesKt.last(unwrap)).getName() + StringsKt.repeat((CharSequence)"[]", SequencesKt.count(unwrap));
         } else {
            var3 = ((Class)type).getName();
         }

         String var1 = var3;
         Intrinsics.checkNotNull(var1);
         var3 = var1;
      } else {
         var3 = type.toString();
      }

      return var3;
   }

   // $FF: synthetic method
   public static final Type access$computeJavaType(KType $receiver, boolean forceWrapper) {
      return computeJavaType($receiver, forceWrapper);
   }

   // $FF: synthetic method
   public static final String access$typeToString(Type type) {
      return typeToString(type);
   }

   // $FF: synthetic class
   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public class WhenMappings {
      // $FF: synthetic field
      public static final int[] $EnumSwitchMapping$0;

      static {
         int[] var0 = new int[KVariance.values().length];

         try {
            var0[KVariance.IN.ordinal()] = 1;
         } catch (NoSuchFieldError var4) {
         }

         try {
            var0[KVariance.INVARIANT.ordinal()] = 2;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[KVariance.OUT.ordinal()] = 3;
         } catch (NoSuchFieldError var2) {
         }

         $EnumSwitchMapping$0 = var0;
      }
   }
}
