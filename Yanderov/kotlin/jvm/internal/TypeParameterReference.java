package kotlin.jvm.internal;

import java.util.List;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.collections.CollectionsKt;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVariance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0014\b\u0007\u0018\u0000 (2\u00020\u0001:\u0001(B)\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\r\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0096\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0016\u001a\u00020\u00152\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u001e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001cR\u001a\u0010\t\u001a\u00020\b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\t\u0010\u001d\u001a\u0004\b\t\u0010\u001eR\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u001f\u001a\u0004\b \u0010\u0019R \u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00128VX\u0096\u0004¢\u0006\f\u0012\u0004\b#\u0010$\u001a\u0004\b!\u0010\"R\u001a\u0010\u0007\u001a\u00020\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0007\u0010%\u001a\u0004\b&\u0010'¨\u0006)"},
   d2 = {"Lkotlin/jvm/internal/TypeParameterReference;", "Lkotlin/reflect/KTypeParameter;", "", "container", "", "name", "Lkotlin/reflect/KVariance;", "variance", "", "isReified", "<init>", "(Ljava/lang/Object;Ljava/lang/String;Lkotlin/reflect/KVariance;Z)V", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "Lkotlin/reflect/KType;", "upperBounds", "", "setUpperBounds", "(Ljava/util/List;)V", "toString", "()Ljava/lang/String;", "bounds", "Ljava/util/List;", "Ljava/lang/Object;", "Z", "()Z", "Ljava/lang/String;", "getName", "getUpperBounds", "()Ljava/util/List;", "getUpperBounds$annotations", "()V", "Lkotlin/reflect/KVariance;", "getVariance", "()Lkotlin/reflect/KVariance;", "Companion", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.4"
)
@SourceDebugExtension({"SMAP\nTypeParameterReference.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TypeParameterReference.kt\nkotlin/jvm/internal/TypeParameterReference\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,58:1\n1#2:59\n*E\n"})
public final class TypeParameterReference implements KTypeParameter {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @Nullable
   private final Object container;
   @NotNull
   private final String name;
   @NotNull
   private final KVariance variance;
   private final boolean isReified;
   @Nullable
   private volatile List bounds;

   public TypeParameterReference(@Nullable Object container, @NotNull String name, @NotNull KVariance variance, boolean isReified) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(variance, "variance");
      super();
      this.container = container;
      this.name = name;
      this.variance = variance;
      this.isReified = isReified;
   }

   @NotNull
   public String getName() {
      return this.name;
   }

   @NotNull
   public KVariance getVariance() {
      return this.variance;
   }

   public boolean isReified() {
      return this.isReified;
   }

   @NotNull
   public List getUpperBounds() {
      List var10000 = this.bounds;
      if (var10000 == null) {
         List it = CollectionsKt.listOf(Reflection.nullableTypeOf(Object.class));
         int var3 = 0;
         this.bounds = it;
         var10000 = it;
      }

      return var10000;
   }

   /** @deprecated */
   // $FF: synthetic method
   public static void getUpperBounds$annotations() {
   }

   public final void setUpperBounds(@NotNull List upperBounds) {
      Intrinsics.checkNotNullParameter(upperBounds, "upperBounds");
      if (this.bounds != null) {
         throw new IllegalStateException(("Upper bounds of type parameter '" + this + "' have already been initialized.").toString());
      } else {
         this.bounds = upperBounds;
      }
   }

   public boolean equals(@Nullable Object other) {
      return other instanceof TypeParameterReference && Intrinsics.areEqual(this.container, ((TypeParameterReference)other).container) && Intrinsics.areEqual((Object)this.getName(), (Object)((TypeParameterReference)other).getName());
   }

   public int hashCode() {
      Object var10000 = this.container;
      return (var10000 != null ? var10000.hashCode() : 0) * 31 + this.getName().hashCode();
   }

   @NotNull
   public String toString() {
      return Companion.toString(this);
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"},
      d2 = {"Lkotlin/jvm/internal/TypeParameterReference$Companion;", "", "<init>", "()V", "Lkotlin/reflect/KTypeParameter;", "typeParameter", "", "toString", "(Lkotlin/reflect/KTypeParameter;)Ljava/lang/String;", "kotlin-stdlib"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final String toString(@NotNull KTypeParameter typeParameter) {
         Intrinsics.checkNotNullParameter(typeParameter, "typeParameter");
         StringBuilder var2 = new StringBuilder();
         int var4 = 0;
         switch (TypeParameterReference.Companion.WhenMappings.$EnumSwitchMapping$0[typeParameter.getVariance().ordinal()]) {
            case 1:
            default:
               break;
            case 2:
               var2.append("in ");
               break;
            case 3:
               var2.append("out ");
         }

         var2.append(typeParameter.getName());
         String var10000 = var2.toString();
         Intrinsics.checkNotNullExpressionValue(var10000, "toString(...)");
         return var10000;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
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
}
