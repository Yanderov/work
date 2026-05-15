package kotlin.jvm.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.reflect.KClass;
import kotlin.reflect.KVisibility;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 T2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001TB\u0013\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0096\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0019\u0010\u0013\u001a\u00020\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002H\u0017¢\u0006\u0004\b\u0013\u0010\u000bJ\u000f\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR \u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u001d0\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u001a\u0010!\u001a\u00020\t8VX\u0097\u0004¢\u0006\f\u0012\u0004\b#\u0010$\u001a\u0004\b!\u0010\"R\u001a\u0010%\u001a\u00020\t8VX\u0097\u0004¢\u0006\f\u0012\u0004\b&\u0010$\u001a\u0004\b%\u0010\"R\u001a\u0010'\u001a\u00020\t8VX\u0097\u0004¢\u0006\f\u0012\u0004\b(\u0010$\u001a\u0004\b'\u0010\"R\u001a\u0010)\u001a\u00020\t8VX\u0097\u0004¢\u0006\f\u0012\u0004\b*\u0010$\u001a\u0004\b)\u0010\"R\u001a\u0010+\u001a\u00020\t8VX\u0097\u0004¢\u0006\f\u0012\u0004\b,\u0010$\u001a\u0004\b+\u0010\"R\u001a\u0010-\u001a\u00020\t8VX\u0097\u0004¢\u0006\f\u0012\u0004\b.\u0010$\u001a\u0004\b-\u0010\"R\u001a\u0010/\u001a\u00020\t8VX\u0097\u0004¢\u0006\f\u0012\u0004\b0\u0010$\u001a\u0004\b/\u0010\"R\u001a\u00101\u001a\u00020\t8VX\u0097\u0004¢\u0006\f\u0012\u0004\b2\u0010$\u001a\u0004\b1\u0010\"R\u001a\u00103\u001a\u00020\t8VX\u0097\u0004¢\u0006\f\u0012\u0004\b4\u0010$\u001a\u0004\b3\u0010\"R\u001e\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u00105\u001a\u0004\b6\u00107R\u001e\u0010:\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003080\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b9\u0010\u001fR\u001e\u0010<\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u0010\u001fR\u0016\u0010?\u001a\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0016\u0010A\u001a\u0004\u0018\u00010\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b@\u0010\u0016R(\u0010D\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00010\u00178VX\u0097\u0004¢\u0006\f\u0012\u0004\bC\u0010$\u001a\u0004\bB\u0010\u001aR\u0016\u0010F\u001a\u0004\u0018\u00010\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bE\u0010\u0016R \u0010J\u001a\b\u0012\u0004\u0012\u00020G0\u00178VX\u0097\u0004¢\u0006\f\u0012\u0004\bI\u0010$\u001a\u0004\bH\u0010\u001aR \u0010N\u001a\b\u0012\u0004\u0012\u00020K0\u00178VX\u0097\u0004¢\u0006\f\u0012\u0004\bM\u0010$\u001a\u0004\bL\u0010\u001aR\u001c\u0010S\u001a\u0004\u0018\u00010O8VX\u0097\u0004¢\u0006\f\u0012\u0004\bR\u0010$\u001a\u0004\bP\u0010Q¨\u0006U"},
   d2 = {"Lkotlin/jvm/internal/ClassReference;", "Lkotlin/reflect/KClass;", "", "Lkotlin/jvm/internal/ClassBasedDeclarationContainer;", "Ljava/lang/Class;", "jClass", "<init>", "(Ljava/lang/Class;)V", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "error", "()Ljava/lang/Void;", "", "hashCode", "()I", "value", "isInstance", "", "toString", "()Ljava/lang/String;", "", "", "getAnnotations", "()Ljava/util/List;", "annotations", "", "Lkotlin/reflect/KFunction;", "getConstructors", "()Ljava/util/Collection;", "constructors", "isAbstract", "()Z", "isAbstract$annotations", "()V", "isCompanion", "isCompanion$annotations", "isData", "isData$annotations", "isFinal", "isFinal$annotations", "isFun", "isFun$annotations", "isInner", "isInner$annotations", "isOpen", "isOpen$annotations", "isSealed", "isSealed$annotations", "isValue", "isValue$annotations", "Ljava/lang/Class;", "getJClass", "()Ljava/lang/Class;", "Lkotlin/reflect/KCallable;", "getMembers", "members", "getNestedClasses", "nestedClasses", "getObjectInstance", "()Ljava/lang/Object;", "objectInstance", "getQualifiedName", "qualifiedName", "getSealedSubclasses", "getSealedSubclasses$annotations", "sealedSubclasses", "getSimpleName", "simpleName", "Lkotlin/reflect/KType;", "getSupertypes", "getSupertypes$annotations", "supertypes", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters", "getTypeParameters$annotations", "typeParameters", "Lkotlin/reflect/KVisibility;", "getVisibility", "()Lkotlin/reflect/KVisibility;", "getVisibility$annotations", "visibility", "Companion", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nClassReference.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ClassReference.kt\nkotlin/jvm/internal/ClassReference\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,205:1\n1567#2:206\n1598#2,4:207\n1261#2,4:211\n1246#2,4:217\n462#3:215\n412#3:216\n*S KotlinDebug\n*F\n+ 1 ClassReference.kt\nkotlin/jvm/internal/ClassReference\n*L\n107#1:206\n107#1:207,4\n155#1:211,4\n163#1:217,4\n163#1:215\n163#1:216\n*E\n"})
public final class ClassReference implements KClass, ClassBasedDeclarationContainer {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private final Class jClass;
   @NotNull
   private static final Map FUNCTION_CLASSES;
   @NotNull
   private static final HashMap primitiveFqNames;
   @NotNull
   private static final HashMap primitiveWrapperFqNames;
   @NotNull
   private static final HashMap classFqNames;
   @NotNull
   private static final Map simpleNames;

   public ClassReference(@NotNull Class jClass) {
      Intrinsics.checkNotNullParameter(jClass, "jClass");
      super();
      this.jClass = jClass;
   }

   @NotNull
   public Class getJClass() {
      return this.jClass;
   }

   @Nullable
   public String getSimpleName() {
      return Companion.getClassSimpleName(this.getJClass());
   }

   @Nullable
   public String getQualifiedName() {
      return Companion.getClassQualifiedName(this.getJClass());
   }

   @NotNull
   public Collection getMembers() {
      this.error();
      throw new KotlinNothingValueException();
   }

   @NotNull
   public Collection getConstructors() {
      this.error();
      throw new KotlinNothingValueException();
   }

   @NotNull
   public Collection getNestedClasses() {
      this.error();
      throw new KotlinNothingValueException();
   }

   @NotNull
   public List getAnnotations() {
      this.error();
      throw new KotlinNothingValueException();
   }

   @Nullable
   public Object getObjectInstance() {
      this.error();
      throw new KotlinNothingValueException();
   }

   @SinceKotlin(
      version = "1.1"
   )
   public boolean isInstance(@Nullable Object value) {
      return Companion.isInstance(value, this.getJClass());
   }

   @NotNull
   public List getTypeParameters() {
      this.error();
      throw new KotlinNothingValueException();
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.1"
   )
   public static void getTypeParameters$annotations() {
   }

   @NotNull
   public List getSupertypes() {
      this.error();
      throw new KotlinNothingValueException();
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.1"
   )
   public static void getSupertypes$annotations() {
   }

   @NotNull
   public List getSealedSubclasses() {
      this.error();
      throw new KotlinNothingValueException();
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.3"
   )
   public static void getSealedSubclasses$annotations() {
   }

   @Nullable
   public KVisibility getVisibility() {
      this.error();
      throw new KotlinNothingValueException();
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.1"
   )
   public static void getVisibility$annotations() {
   }

   public boolean isFinal() {
      this.error();
      throw new KotlinNothingValueException();
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.1"
   )
   public static void isFinal$annotations() {
   }

   public boolean isOpen() {
      this.error();
      throw new KotlinNothingValueException();
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.1"
   )
   public static void isOpen$annotations() {
   }

   public boolean isAbstract() {
      this.error();
      throw new KotlinNothingValueException();
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.1"
   )
   public static void isAbstract$annotations() {
   }

   public boolean isSealed() {
      this.error();
      throw new KotlinNothingValueException();
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.1"
   )
   public static void isSealed$annotations() {
   }

   public boolean isData() {
      this.error();
      throw new KotlinNothingValueException();
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.1"
   )
   public static void isData$annotations() {
   }

   public boolean isInner() {
      this.error();
      throw new KotlinNothingValueException();
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.1"
   )
   public static void isInner$annotations() {
   }

   public boolean isCompanion() {
      this.error();
      throw new KotlinNothingValueException();
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.1"
   )
   public static void isCompanion$annotations() {
   }

   public boolean isFun() {
      this.error();
      throw new KotlinNothingValueException();
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.4"
   )
   public static void isFun$annotations() {
   }

   public boolean isValue() {
      this.error();
      throw new KotlinNothingValueException();
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.5"
   )
   public static void isValue$annotations() {
   }

   private final Void error() {
      throw new KotlinReflectionNotSupportedError();
   }

   public boolean equals(@Nullable Object other) {
      return other instanceof ClassReference && Intrinsics.areEqual((Object)JvmClassMappingKt.getJavaObjectType(this), (Object)JvmClassMappingKt.getJavaObjectType((KClass)other));
   }

   public int hashCode() {
      return JvmClassMappingKt.getJavaObjectType(this).hashCode();
   }

   @NotNull
   public String toString() {
      return this.getJClass().toString() + " (Kotlin reflection is not available)";
   }

   static {
      Class[] $this$primitiveFqNames_u24lambda_u241 = new Class[]{Function0.class, Function1.class, Function2.class, Function3.class, Function4.class, Function5.class, Function6.class, Function7.class, Function8.class, Function9.class, Function10.class, Function11.class, Function12.class, Function13.class, Function14.class, Function15.class, Function16.class, Function17.class, Function18.class, Function19.class, Function20.class, Function21.class, Function22.class};
      Iterable $this$mapValues$iv = (Iterable)CollectionsKt.listOf($this$primitiveFqNames_u24lambda_u241);
      int $i$f$mapIndexed = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapValues$iv, 10)));
      int $i$f$mapIndexedTo = 0;
      int index$iv$iv = 0;

      for(Object item$iv$iv : $this$mapValues$iv) {
         int var8 = index$iv$iv++;
         if (var8 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         Class clazz = (Class)item$iv$iv;
         int var11 = 0;
         destination$iv$iv.add(TuplesKt.to(clazz, var8));
      }

      FUNCTION_CLASSES = MapsKt.toMap((Iterable)((List)destination$iv$iv));
      HashMap $this$primitiveFqNames_u24lambda_u241 = new HashMap();
      int $this$mapIndexedTo$iv$iv = 0;
      $this$primitiveFqNames_u24lambda_u241.put("boolean", "kotlin.Boolean");
      $this$primitiveFqNames_u24lambda_u241.put("char", "kotlin.Char");
      $this$primitiveFqNames_u24lambda_u241.put("byte", "kotlin.Byte");
      $this$primitiveFqNames_u24lambda_u241.put("short", "kotlin.Short");
      $this$primitiveFqNames_u24lambda_u241.put("int", "kotlin.Int");
      $this$primitiveFqNames_u24lambda_u241.put("float", "kotlin.Float");
      $this$primitiveFqNames_u24lambda_u241.put("long", "kotlin.Long");
      $this$primitiveFqNames_u24lambda_u241.put("double", "kotlin.Double");
      primitiveFqNames = $this$primitiveFqNames_u24lambda_u241;
      $this$primitiveFqNames_u24lambda_u241 = new HashMap();
      $this$mapIndexedTo$iv$iv = 0;
      $this$primitiveFqNames_u24lambda_u241.put("java.lang.Boolean", "kotlin.Boolean");
      $this$primitiveFqNames_u24lambda_u241.put("java.lang.Character", "kotlin.Char");
      $this$primitiveFqNames_u24lambda_u241.put("java.lang.Byte", "kotlin.Byte");
      $this$primitiveFqNames_u24lambda_u241.put("java.lang.Short", "kotlin.Short");
      $this$primitiveFqNames_u24lambda_u241.put("java.lang.Integer", "kotlin.Int");
      $this$primitiveFqNames_u24lambda_u241.put("java.lang.Float", "kotlin.Float");
      $this$primitiveFqNames_u24lambda_u241.put("java.lang.Long", "kotlin.Long");
      $this$primitiveFqNames_u24lambda_u241.put("java.lang.Double", "kotlin.Double");
      primitiveWrapperFqNames = $this$primitiveFqNames_u24lambda_u241;
      $this$primitiveFqNames_u24lambda_u241 = new HashMap();
      HashMap $this$classFqNames_u24lambda_u244 = $this$primitiveFqNames_u24lambda_u241;
      $this$mapIndexedTo$iv$iv = 0;
      $this$primitiveFqNames_u24lambda_u241.put("java.lang.Object", "kotlin.Any");
      $this$primitiveFqNames_u24lambda_u241.put("java.lang.String", "kotlin.String");
      $this$primitiveFqNames_u24lambda_u241.put("java.lang.CharSequence", "kotlin.CharSequence");
      $this$primitiveFqNames_u24lambda_u241.put("java.lang.Throwable", "kotlin.Throwable");
      $this$primitiveFqNames_u24lambda_u241.put("java.lang.Cloneable", "kotlin.Cloneable");
      $this$primitiveFqNames_u24lambda_u241.put("java.lang.Number", "kotlin.Number");
      $this$primitiveFqNames_u24lambda_u241.put("java.lang.Comparable", "kotlin.Comparable");
      $this$primitiveFqNames_u24lambda_u241.put("java.lang.Enum", "kotlin.Enum");
      $this$primitiveFqNames_u24lambda_u241.put("java.lang.annotation.Annotation", "kotlin.Annotation");
      $this$primitiveFqNames_u24lambda_u241.put("java.lang.Iterable", "kotlin.collections.Iterable");
      $this$primitiveFqNames_u24lambda_u241.put("java.util.Iterator", "kotlin.collections.Iterator");
      $this$primitiveFqNames_u24lambda_u241.put("java.util.Collection", "kotlin.collections.Collection");
      $this$primitiveFqNames_u24lambda_u241.put("java.util.List", "kotlin.collections.List");
      $this$primitiveFqNames_u24lambda_u241.put("java.util.Set", "kotlin.collections.Set");
      $this$primitiveFqNames_u24lambda_u241.put("java.util.ListIterator", "kotlin.collections.ListIterator");
      $this$primitiveFqNames_u24lambda_u241.put("java.util.Map", "kotlin.collections.Map");
      $this$primitiveFqNames_u24lambda_u241.put("java.util.Map$Entry", "kotlin.collections.Map.Entry");
      $this$primitiveFqNames_u24lambda_u241.put("kotlin.jvm.internal.StringCompanionObject", "kotlin.String.Companion");
      $this$primitiveFqNames_u24lambda_u241.put("kotlin.jvm.internal.EnumCompanionObject", "kotlin.Enum.Companion");
      $this$primitiveFqNames_u24lambda_u241.putAll((Map)primitiveFqNames);
      $this$primitiveFqNames_u24lambda_u241.putAll((Map)primitiveWrapperFqNames);
      Collection var10000 = primitiveFqNames.values();
      Intrinsics.checkNotNullExpressionValue(var10000, "<get-values>(...)");
      Iterable $this$associateTo$iv = (Iterable)var10000;
      $i$f$mapIndexedTo = 0;

      for(Object element$iv : $this$associateTo$iv) {
         Map var39 = (Map)$this$classFqNames_u24lambda_u244;
         String kotlinName = (String)element$iv;
         int var44 = 0;
         StringBuilder var47 = (new StringBuilder()).append("kotlin.jvm.internal.");
         Intrinsics.checkNotNull(kotlinName);
         Pair var42 = TuplesKt.to(var47.append(StringsKt.substringAfterLast$default(kotlinName, '.', (String)null, 2, (Object)null)).append("CompanionObject").toString(), kotlinName + ".Companion");
         var39.put(var42.getFirst(), var42.getSecond());
      }

      Map var48 = (Map)$this$classFqNames_u24lambda_u244;

      for(Map.Entry $i$f$mapValuesTo : FUNCTION_CLASSES.entrySet()) {
         Class klass = (Class)$i$f$mapValuesTo.getKey();
         int arity = ((Number)$i$f$mapValuesTo.getValue()).intValue();
         $this$classFqNames_u24lambda_u244.put(klass.getName(), "kotlin.Function" + arity);
      }

      classFqNames = $this$primitiveFqNames_u24lambda_u241;
      Map $this$mapValues$iv = (Map)classFqNames;
      int $i$f$mapValues = 0;
      Map destination$iv$iv = (Map)(new LinkedHashMap(MapsKt.mapCapacity($this$mapValues$iv.size())));
      $i$f$mapIndexedTo = 0;
      Iterable $this$associateByTo$iv$iv$iv = (Iterable)$this$mapValues$iv.entrySet();
      int $i$f$associateByTo = 0;

      for(Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
         Map.Entry it$iv$iv = (Map.Entry)element$iv$iv$iv;
         int var46 = 0;
         Object var49 = it$iv$iv.getKey();
         Map.Entry var12 = (Map.Entry)element$iv$iv$iv;
         Object var16 = var49;
         int var13 = 0;
         String fqName = (String)var12.getValue();
         String var17 = StringsKt.substringAfterLast$default(fqName, '.', (String)null, 2, (Object)null);
         destination$iv$iv.put(var16, var17);
      }

      simpleNames = destination$iv$iv;
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\t\u001a\u0004\u0018\u00010\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0004\b\t\u0010\bJ#\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\u00012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0004\b\f\u0010\rR,\u0010\u0011\u001a\u001a\u0012\u0010\u0012\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u000f0\u0004\u0012\u0004\u0012\u00020\u00100\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R0\u0010\u0015\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0013j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006`\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R0\u0010\u0017\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0013j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006`\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0016R0\u0010\u0018\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0013j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006`\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0016R \u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u0012¨\u0006\u001a"},
      d2 = {"Lkotlin/jvm/internal/ClassReference$Companion;", "", "<init>", "()V", "Ljava/lang/Class;", "jClass", "", "getClassQualifiedName", "(Ljava/lang/Class;)Ljava/lang/String;", "getClassSimpleName", "value", "", "isInstance", "(Ljava/lang/Object;Ljava/lang/Class;)Z", "", "Lkotlin/Function;", "", "FUNCTION_CLASSES", "Ljava/util/Map;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "classFqNames", "Ljava/util/HashMap;", "primitiveFqNames", "primitiveWrapperFqNames", "simpleNames", "kotlin-stdlib"}
   )
   @SourceDebugExtension({"SMAP\nClassReference.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ClassReference.kt\nkotlin/jvm/internal/ClassReference$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,205:1\n1#2:206\n*E\n"})
   public static final class Companion {
      private Companion() {
      }

      @Nullable
      public final String getClassSimpleName(@NotNull Class jClass) {
         Intrinsics.checkNotNullParameter(jClass, "jClass");
         String var10000;
         if (jClass.isAnonymousClass()) {
            var10000 = null;
         } else if (jClass.isLocalClass()) {
            String name = jClass.getSimpleName();
            Method var9 = jClass.getEnclosingMethod();
            if (var9 != null) {
               Method method = var9;
               int var6 = 0;
               Intrinsics.checkNotNull(name);
               var10000 = StringsKt.substringAfter$default(name, method.getName() + '$', (String)null, 2, (Object)null);
               if (var10000 != null) {
                  return var10000;
               }
            }

            Constructor var10 = jClass.getEnclosingConstructor();
            if (var10 != null) {
               Constructor method = var10;
               int var7 = 0;
               Intrinsics.checkNotNull(name);
               var10000 = StringsKt.substringAfter$default(name, method.getName() + '$', (String)null, 2, (Object)null);
            } else {
               Intrinsics.checkNotNull(name);
               var10000 = StringsKt.substringAfter$default(name, '$', (String)null, 2, (Object)null);
            }
         } else if (jClass.isArray()) {
            Class componentType = jClass.getComponentType();
            if (componentType.isPrimitive()) {
               String var3 = (String)ClassReference.simpleNames.get(componentType.getName());
               var10000 = var3 != null ? var3 + "Array" : null;
            } else {
               var10000 = null;
            }

            if (var10000 == null) {
               var10000 = "Array";
            }
         } else {
            var10000 = (String)ClassReference.simpleNames.get(jClass.getName());
            if (var10000 == null) {
               var10000 = jClass.getSimpleName();
            }
         }

         return var10000;
      }

      @Nullable
      public final String getClassQualifiedName(@NotNull Class jClass) {
         Intrinsics.checkNotNullParameter(jClass, "jClass");
         String var10000;
         if (jClass.isAnonymousClass()) {
            var10000 = null;
         } else if (jClass.isLocalClass()) {
            var10000 = null;
         } else if (jClass.isArray()) {
            Class componentType = jClass.getComponentType();
            if (componentType.isPrimitive()) {
               String var3 = (String)ClassReference.classFqNames.get(componentType.getName());
               var10000 = var3 != null ? var3 + "Array" : null;
            } else {
               var10000 = null;
            }

            if (var10000 == null) {
               var10000 = "kotlin.Array";
            }
         } else {
            var10000 = (String)ClassReference.classFqNames.get(jClass.getName());
            if (var10000 == null) {
               var10000 = jClass.getCanonicalName();
            }
         }

         return var10000;
      }

      public final boolean isInstance(@Nullable Object value, @NotNull Class jClass) {
         Intrinsics.checkNotNullParameter(jClass, "jClass");
         Map var10000 = ClassReference.FUNCTION_CLASSES;
         Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.get, V of kotlin.collections.MapsKt__MapsKt.get>");
         Integer var3 = (Integer)var10000.get(jClass);
         if (var3 != null) {
            int arity = ((Number)var3).intValue();
            int var6 = 0;
            return TypeIntrinsics.isFunctionOfArity(value, arity);
         } else {
            Class objectType = jClass.isPrimitive() ? JvmClassMappingKt.getJavaObjectType(JvmClassMappingKt.getKotlinClass(jClass)) : jClass;
            return objectType.isInstance(value);
         }
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
