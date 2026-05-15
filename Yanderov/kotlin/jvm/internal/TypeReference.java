package kotlin.jvm.internal;

import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.SinceKotlin;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u001b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 22\u00020\u0001:\u00012B'\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nB1\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\t\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0015\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0096\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u0013\u0010\u0011\u001a\u00020\u0010*\u00020\u0005H\u0002¢\u0006\u0004\b\u0011\u0010\u001bR\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0006\u0010 \u001a\u0004\b!\u0010\u001eR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\"\u001a\u0004\b#\u0010$R \u0010\r\u001a\u00020\f8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b\r\u0010%\u0012\u0004\b'\u0010(\u001a\u0004\b&\u0010\u0018R\u0014\u0010\b\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010)R\"\u0010\u000b\u001a\u0004\u0018\u00010\u00018\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b\u000b\u0010*\u0012\u0004\b-\u0010(\u001a\u0004\b+\u0010,R\u001c\u00101\u001a\u00020\u0010*\u0006\u0012\u0002\b\u00030.8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b/\u00100¨\u00063"},
   d2 = {"Lkotlin/jvm/internal/TypeReference;", "Lkotlin/reflect/KType;", "Lkotlin/reflect/KClassifier;", "classifier", "", "Lkotlin/reflect/KTypeProjection;", "arguments", "", "isMarkedNullable", "<init>", "(Lkotlin/reflect/KClassifier;Ljava/util/List;Z)V", "platformTypeUpperBound", "", "flags", "(Lkotlin/reflect/KClassifier;Ljava/util/List;Lkotlin/reflect/KType;I)V", "convertPrimitiveToWrapper", "", "asString", "(Z)Ljava/lang/String;", "", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "toString", "()Ljava/lang/String;", "(Lkotlin/reflect/KTypeProjection;)Ljava/lang/String;", "", "getAnnotations", "()Ljava/util/List;", "annotations", "Ljava/util/List;", "getArguments", "Lkotlin/reflect/KClassifier;", "getClassifier", "()Lkotlin/reflect/KClassifier;", "I", "getFlags$kotlin_stdlib", "getFlags$kotlin_stdlib$annotations", "()V", "()Z", "Lkotlin/reflect/KType;", "getPlatformTypeUpperBound$kotlin_stdlib", "()Lkotlin/reflect/KType;", "getPlatformTypeUpperBound$kotlin_stdlib$annotations", "Ljava/lang/Class;", "getArrayClassName", "(Ljava/lang/Class;)Ljava/lang/String;", "arrayClassName", "Companion", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.4"
)
public final class TypeReference implements KType {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private final KClassifier classifier;
   @NotNull
   private final List arguments;
   @Nullable
   private final KType platformTypeUpperBound;
   private final int flags;
   public static final int IS_MARKED_NULLABLE = 1;
   public static final int IS_MUTABLE_COLLECTION_TYPE = 2;
   public static final int IS_NOTHING_TYPE = 4;

   @SinceKotlin(
      version = "1.6"
   )
   public TypeReference(@NotNull KClassifier classifier, @NotNull List arguments, @Nullable KType platformTypeUpperBound, int flags) {
      Intrinsics.checkNotNullParameter(classifier, "classifier");
      Intrinsics.checkNotNullParameter(arguments, "arguments");
      super();
      this.classifier = classifier;
      this.arguments = arguments;
      this.platformTypeUpperBound = platformTypeUpperBound;
      this.flags = flags;
   }

   @NotNull
   public KClassifier getClassifier() {
      return this.classifier;
   }

   @NotNull
   public List getArguments() {
      return this.arguments;
   }

   @Nullable
   public final KType getPlatformTypeUpperBound$kotlin_stdlib() {
      return this.platformTypeUpperBound;
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.6"
   )
   public static void getPlatformTypeUpperBound$kotlin_stdlib$annotations() {
   }

   public final int getFlags$kotlin_stdlib() {
      return this.flags;
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.6"
   )
   public static void getFlags$kotlin_stdlib$annotations() {
   }

   public TypeReference(@NotNull KClassifier classifier, @NotNull List arguments, boolean isMarkedNullable) {
      Intrinsics.checkNotNullParameter(classifier, "classifier");
      Intrinsics.checkNotNullParameter(arguments, "arguments");
      this(classifier, arguments, (KType)null, isMarkedNullable ? 1 : 0);
   }

   @NotNull
   public List getAnnotations() {
      return CollectionsKt.emptyList();
   }

   public boolean isMarkedNullable() {
      return (this.flags & 1) != 0;
   }

   public boolean equals(@Nullable Object other) {
      return other instanceof TypeReference && Intrinsics.areEqual((Object)this.getClassifier(), (Object)((TypeReference)other).getClassifier()) && Intrinsics.areEqual((Object)this.getArguments(), (Object)((TypeReference)other).getArguments()) && Intrinsics.areEqual((Object)this.platformTypeUpperBound, (Object)((TypeReference)other).platformTypeUpperBound) && this.flags == ((TypeReference)other).flags;
   }

   public int hashCode() {
      return (this.getClassifier().hashCode() * 31 + this.getArguments().hashCode()) * 31 + Integer.hashCode(this.flags);
   }

   @NotNull
   public String toString() {
      return this.asString(false) + " (Kotlin reflection is not available)";
   }

   private final String asString(boolean convertPrimitiveToWrapper) {
      KClassifier var4 = this.getClassifier();
      Class javaClass = (var4 instanceof KClass ? (KClass)var4 : null) != null ? JvmClassMappingKt.getJavaClass(var4 instanceof KClass ? (KClass)var4 : null) : null;
      String var10000;
      if (javaClass == null) {
         var10000 = this.getClassifier().toString();
      } else if ((this.flags & 4) != 0) {
         var10000 = "kotlin.Nothing";
      } else if (javaClass.isArray()) {
         var10000 = this.getArrayClassName(javaClass);
      } else if (convertPrimitiveToWrapper && javaClass.isPrimitive()) {
         KClassifier var10 = this.getClassifier();
         Intrinsics.checkNotNull(var10, "null cannot be cast to non-null type kotlin.reflect.KClass<*>");
         var10000 = JvmClassMappingKt.getJavaObjectType((KClass)var10).getName();
      } else {
         var10000 = javaClass.getName();
      }

      String klass = var10000;
      String args = this.getArguments().isEmpty() ? "" : CollectionsKt.joinToString$default((Iterable)this.getArguments(), (CharSequence)", ", (CharSequence)"<", (CharSequence)">", 0, (CharSequence)null, new Function1(this) {
         public final CharSequence invoke(KTypeProjection it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (CharSequence)TypeReference.this.asString(it);
         }
      }, 24, (Object)null);
      String nullable = this.isMarkedNullable() ? "?" : "";
      String result = klass + args + nullable;
      KType upper = this.platformTypeUpperBound;
      if (upper instanceof TypeReference) {
         String renderedUpper = ((TypeReference)upper).asString(true);
         var10000 = Intrinsics.areEqual((Object)renderedUpper, (Object)result) ? result : (Intrinsics.areEqual((Object)renderedUpper, (Object)(result + '?')) ? result + '!' : '(' + result + ".." + renderedUpper + ')');
      } else {
         var10000 = result;
      }

      return var10000;
   }

   private final String getArrayClassName(Class $this$arrayClassName) {
      return Intrinsics.areEqual((Object)$this$arrayClassName, (Object)boolean[].class) ? "kotlin.BooleanArray" : (Intrinsics.areEqual((Object)$this$arrayClassName, (Object)char[].class) ? "kotlin.CharArray" : (Intrinsics.areEqual((Object)$this$arrayClassName, (Object)byte[].class) ? "kotlin.ByteArray" : (Intrinsics.areEqual((Object)$this$arrayClassName, (Object)short[].class) ? "kotlin.ShortArray" : (Intrinsics.areEqual((Object)$this$arrayClassName, (Object)int[].class) ? "kotlin.IntArray" : (Intrinsics.areEqual((Object)$this$arrayClassName, (Object)float[].class) ? "kotlin.FloatArray" : (Intrinsics.areEqual((Object)$this$arrayClassName, (Object)long[].class) ? "kotlin.LongArray" : (Intrinsics.areEqual((Object)$this$arrayClassName, (Object)double[].class) ? "kotlin.DoubleArray" : "kotlin.Array")))))));
   }

   private final String asString(KTypeProjection $this$asString) {
      if ($this$asString.getVariance() == null) {
         return "*";
      } else {
         String var4;
         label24: {
            KType var3 = $this$asString.getType();
            TypeReference var10000 = var3 instanceof TypeReference ? (TypeReference)var3 : null;
            if ((var3 instanceof TypeReference ? (TypeReference)var3 : null) != null) {
               var4 = var10000.asString(true);
               if (var4 != null) {
                  break label24;
               }
            }

            var4 = String.valueOf($this$asString.getType());
         }

         String typeString = var4;
         switch (TypeReference.WhenMappings.$EnumSwitchMapping$0[$this$asString.getVariance().ordinal()]) {
            case 1:
               var4 = typeString;
               break;
            case 2:
               var4 = "in " + typeString;
               break;
            case 3:
               var4 = "out " + typeString;
               break;
            default:
               throw new NoWhenBranchMatchedException();
         }

         return var4;
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0000X\u0080T¢\u0006\u0006\n\u0004\b\b\u0010\u0006¨\u0006\t"},
      d2 = {"Lkotlin/jvm/internal/TypeReference$Companion;", "", "<init>", "()V", "", "IS_MARKED_NULLABLE", "I", "IS_MUTABLE_COLLECTION_TYPE", "IS_NOTHING_TYPE", "kotlin-stdlib"}
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
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
            var0[KVariance.INVARIANT.ordinal()] = 1;
         } catch (NoSuchFieldError var4) {
         }

         try {
            var0[KVariance.IN.ordinal()] = 2;
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
