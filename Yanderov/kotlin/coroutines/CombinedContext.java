package kotlin.coroutines;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\b\u0001\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0001+B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\r\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0011\u001a\u00020\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0096\u0002¢\u0006\u0004\b\u0011\u0010\u0012J7\u0010\u0017\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00132\u0006\u0010\u0014\u001a\u00028\u00002\u0018\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00028\u00000\u0015H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J*\u0010\u001c\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0019*\u00020\u00052\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001aH\u0096\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001f\u001a\u00020\u001eH\u0016¢\u0006\u0004\b\u001f\u0010 J\u001b\u0010!\u001a\u00020\u00012\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001aH\u0016¢\u0006\u0004\b!\u0010\"J\u000f\u0010#\u001a\u00020\u001eH\u0002¢\u0006\u0004\b#\u0010 J\u000f\u0010%\u001a\u00020$H\u0016¢\u0006\u0004\b%\u0010&J\u000f\u0010'\u001a\u00020\u000fH\u0002¢\u0006\u0004\b'\u0010(R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010)R\u0014\u0010\u0004\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010*¨\u0006,"},
   d2 = {"Lkotlin/coroutines/CombinedContext;", "Lkotlin/coroutines/CoroutineContext;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "left", "Lkotlin/coroutines/CoroutineContext$Element;", "element", "<init>", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext$Element;)V", "", "contains", "(Lkotlin/coroutines/CoroutineContext$Element;)Z", "context", "containsAll", "(Lkotlin/coroutines/CombinedContext;)Z", "", "other", "equals", "(Ljava/lang/Object;)Z", "R", "initial", "Lkotlin/Function2;", "operation", "fold", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "E", "Lkotlin/coroutines/CoroutineContext$Key;", "key", "get", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "", "hashCode", "()I", "minusKey", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext;", "size", "", "toString", "()Ljava/lang/String;", "writeReplace", "()Ljava/lang/Object;", "Lkotlin/coroutines/CoroutineContext$Element;", "Lkotlin/coroutines/CoroutineContext;", "Serialized", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.3"
)
@SourceDebugExtension({"SMAP\nCoroutineContextImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoroutineContextImpl.kt\nkotlin/coroutines/CombinedContext\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,196:1\n1#2:197\n*E\n"})
public final class CombinedContext implements CoroutineContext, Serializable {
   @NotNull
   private final CoroutineContext left;
   @NotNull
   private final CoroutineContext.Element element;

   public CombinedContext(@NotNull CoroutineContext left, @NotNull CoroutineContext.Element element) {
      Intrinsics.checkNotNullParameter(left, "left");
      Intrinsics.checkNotNullParameter(element, "element");
      super();
      this.left = left;
      this.element = element;
   }

   @Nullable
   public CoroutineContext.Element get(@NotNull CoroutineContext.Key key) {
      Intrinsics.checkNotNullParameter(key, "key");
      CombinedContext cur = this;

      while(true) {
         CoroutineContext.Element it = cur.element.get(key);
         if (it != null) {
            int var6 = 0;
            return it;
         }

         CoroutineContext next = cur.left;
         if (!(next instanceof CombinedContext)) {
            return next.get(key);
         }

         cur = (CombinedContext)next;
      }
   }

   public Object fold(Object initial, @NotNull Function2 operation) {
      Intrinsics.checkNotNullParameter(operation, "operation");
      return operation.invoke(this.left.fold(initial, operation), this.element);
   }

   @NotNull
   public CoroutineContext minusKey(@NotNull CoroutineContext.Key key) {
      Intrinsics.checkNotNullParameter(key, "key");
      if (this.element.get(key) != null) {
         int var5 = 0;
         return this.left;
      } else {
         CoroutineContext newLeft = this.left.minusKey(key);
         return newLeft == this.left ? (CoroutineContext)this : (newLeft == EmptyCoroutineContext.INSTANCE ? (CoroutineContext)this.element : (CoroutineContext)(new CombinedContext(newLeft, this.element)));
      }
   }

   private final int size() {
      CombinedContext cur = this;
      int size = 2;

      while(true) {
         CoroutineContext var3 = cur.left;
         CombinedContext var10000 = var3 instanceof CombinedContext ? (CombinedContext)var3 : null;
         if ((var3 instanceof CombinedContext ? (CombinedContext)var3 : null) == null) {
            return size;
         }

         cur = var10000;
         ++size;
      }
   }

   private final boolean contains(CoroutineContext.Element element) {
      return Intrinsics.areEqual((Object)this.get(element.getKey()), (Object)element);
   }

   private final boolean containsAll(CombinedContext context) {
      CoroutineContext next;
      for(CombinedContext cur = context; this.contains(cur.element); cur = (CombinedContext)next) {
         next = cur.left;
         if (!(next instanceof CombinedContext)) {
            Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlin.coroutines.CoroutineContext.Element");
            return this.contains((CoroutineContext.Element)next);
         }
      }

      return false;
   }

   public boolean equals(@Nullable Object other) {
      return this == other || other instanceof CombinedContext && ((CombinedContext)other).size() == this.size() && ((CombinedContext)other).containsAll(this);
   }

   public int hashCode() {
      return this.left.hashCode() + this.element.hashCode();
   }

   @NotNull
   public String toString() {
      return '[' + (String)this.fold("", null.INSTANCE) + ']';
   }

   private final Object writeReplace() {
      int n = this.size();
      final CoroutineContext[] elements = new CoroutineContext[n];
      final Ref.IntRef index = new Ref.IntRef();
      this.fold(Unit.INSTANCE, new Function2(elements, index) {
         public final void invoke(Unit var1, CoroutineContext.Element element) {
            Intrinsics.checkNotNullParameter(var1, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(element, "element");
            CoroutineContext[] var10000 = elements;
            int var3 = index.element++;
            var10000[var3] = element;
         }
      });
      boolean var4 = index.element == n;
      if (!var4) {
         String var5 = "Check failed.";
         throw new IllegalStateException(var5.toString());
      } else {
         return new Serialized(elements);
      }
   }

   @NotNull
   public CoroutineContext plus(@NotNull CoroutineContext context) {
      return CoroutineContext.DefaultImpls.plus(this, context);
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0002\u0018\u0000 \u000e2\u00060\u0001j\u0002`\u0002:\u0001\u000eB\u0015\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\t\u0010\nR\u001d\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u000f"},
      d2 = {"Lkotlin/coroutines/CombinedContext$Serialized;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "", "Lkotlin/coroutines/CoroutineContext;", "elements", "<init>", "([Lkotlin/coroutines/CoroutineContext;)V", "", "readResolve", "()Ljava/lang/Object;", "[Lkotlin/coroutines/CoroutineContext;", "getElements", "()[Lkotlin/coroutines/CoroutineContext;", "Companion", "kotlin-stdlib"}
   )
   @SourceDebugExtension({"SMAP\nCoroutineContextImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoroutineContextImpl.kt\nkotlin/coroutines/CombinedContext$Serialized\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,196:1\n12757#2,3:197\n*S KotlinDebug\n*F\n+ 1 CoroutineContextImpl.kt\nkotlin/coroutines/CombinedContext$Serialized\n*L\n193#1:197,3\n*E\n"})
   private static final class Serialized implements Serializable {
      @NotNull
      public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
      @NotNull
      private final CoroutineContext[] elements;
      private static final long serialVersionUID = 0L;

      public Serialized(@NotNull CoroutineContext[] elements) {
         Intrinsics.checkNotNullParameter(elements, "elements");
         super();
         this.elements = elements;
      }

      @NotNull
      public final CoroutineContext[] getElements() {
         return this.elements;
      }

      private final Object readResolve() {
         CoroutineContext[] $this$fold$iv = this.elements;
         Object initial$iv = EmptyCoroutineContext.INSTANCE;
         int $i$f$fold = 0;
         Object accumulator$iv = initial$iv;
         int var5 = 0;

         for(int var6 = $this$fold$iv.length; var5 < var6; ++var5) {
            Object element$iv = $this$fold$iv[var5];
            CoroutineContext p0 = (CoroutineContext)accumulator$iv;
            int var10 = 0;
            accumulator$iv = p0.plus((CoroutineContext)element$iv);
         }

         return accumulator$iv;
      }

      @Metadata(
         mv = {1, 9, 0},
         k = 1,
         xi = 48,
         d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
         d2 = {"Lkotlin/coroutines/CombinedContext$Serialized$Companion;", "", "<init>", "()V", "", "serialVersionUID", "J", "kotlin-stdlib"}
      )
      public static final class Companion {
         private Companion() {
         }

         // $FF: synthetic method
         public Companion(DefaultConstructorMarker $constructor_marker) {
            this();
         }
      }
   }
}
