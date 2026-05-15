package kotlin.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.BuilderInference;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000J\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0007\u001aU\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u001f\b\u0001\u0010\u0007\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0004\b\t\u0010\n\u001aM\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u00002\u001f\b\u0001\u0010\u0007\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\t\u0010\u000b\u001a\u0019\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\f¢\u0006\u0004\b\r\u0010\u000e\u001a&\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u000fj\b\u0012\u0004\u0012\u00028\u0000`\u0010\"\u0004\b\u0000\u0010\fH\u0087\b¢\u0006\u0004\b\u0011\u0010\u0012\u001a7\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u000fj\b\u0012\u0004\u0012\u00028\u0000`\u0010\"\u0004\b\u0000\u0010\f2\u0012\u0010\u0014\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0013\"\u00028\u0000¢\u0006\u0004\b\u0011\u0010\u0015\u001a&\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0016j\b\u0012\u0004\u0012\u00028\u0000`\u0017\"\u0004\b\u0000\u0010\fH\u0087\b¢\u0006\u0004\b\u0018\u0010\u0019\u001a7\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0016j\b\u0012\u0004\u0012\u00028\u0000`\u0017\"\u0004\b\u0000\u0010\f2\u0012\u0010\u0014\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0013\"\u00028\u0000¢\u0006\u0004\b\u0018\u0010\u001a\u001a\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\fH\u0087\b¢\u0006\u0004\b\u001b\u0010\u000e\u001a-\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\f2\u0012\u0010\u0014\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0013\"\u00028\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u001c\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\fH\u0087\b¢\u0006\u0004\b\u001d\u0010\u000e\u001a-\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\f2\u0012\u0010\u0014\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0013\"\u00028\u0000¢\u0006\u0004\b\u001d\u0010\u001c\u001a)\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\b\b\u0000\u0010\f*\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00018\u0000H\u0007¢\u0006\u0004\b \u0010!\u001a7\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\b\b\u0000\u0010\f*\u00020\u001e2\u0016\u0010\u0014\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00018\u00000\u0013\"\u0004\u0018\u00018\u0000H\u0007¢\u0006\u0004\b \u0010\u001c\u001a%\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u00028\u00000\bH\u0000¢\u0006\u0004\b\"\u0010#\u001a(\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\f*\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\bH\u0087\b¢\u0006\u0004\b$\u0010#\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006%"},
   d2 = {"E", "", "capacity", "Lkotlin/Function1;", "", "", "Lkotlin/ExtensionFunctionType;", "builderAction", "", "buildSet", "(ILkotlin/jvm/functions/Function1;)Ljava/util/Set;", "(Lkotlin/jvm/functions/Function1;)Ljava/util/Set;", "T", "emptySet", "()Ljava/util/Set;", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "hashSetOf", "()Ljava/util/HashSet;", "", "elements", "([Ljava/lang/Object;)Ljava/util/HashSet;", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "linkedSetOf", "()Ljava/util/LinkedHashSet;", "([Ljava/lang/Object;)Ljava/util/LinkedHashSet;", "mutableSetOf", "([Ljava/lang/Object;)Ljava/util/Set;", "setOf", "", "element", "setOfNotNull", "(Ljava/lang/Object;)Ljava/util/Set;", "optimizeReadOnlySet", "(Ljava/util/Set;)Ljava/util/Set;", "orEmpty", "kotlin-stdlib"},
   xs = "kotlin/collections/SetsKt"
)
class SetsKt__SetsKt extends SetsKt__SetsJVMKt {
   @NotNull
   public static final Set emptySet() {
      return EmptySet.INSTANCE;
   }

   @NotNull
   public static final Set setOf(@NotNull Object... elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      return ArraysKt.toSet(elements);
   }

   @InlineOnly
   private static final Set setOf() {
      return SetsKt.emptySet();
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final Set mutableSetOf() {
      return (Set)(new LinkedHashSet());
   }

   @NotNull
   public static final Set mutableSetOf(@NotNull Object... elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      return (Set)ArraysKt.toCollection(elements, (Collection)(new LinkedHashSet(MapsKt.mapCapacity(elements.length))));
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final HashSet hashSetOf() {
      return new HashSet();
   }

   @NotNull
   public static final HashSet hashSetOf(@NotNull Object... elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      return (HashSet)ArraysKt.toCollection(elements, (Collection)(new HashSet(MapsKt.mapCapacity(elements.length))));
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final LinkedHashSet linkedSetOf() {
      return new LinkedHashSet();
   }

   @NotNull
   public static final LinkedHashSet linkedSetOf(@NotNull Object... elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      return (LinkedHashSet)ArraysKt.toCollection(elements, (Collection)(new LinkedHashSet(MapsKt.mapCapacity(elements.length))));
   }

   @SinceKotlin(
      version = "1.4"
   )
   @NotNull
   public static final Set setOfNotNull(@Nullable Object element) {
      return element != null ? SetsKt.setOf(element) : SetsKt.emptySet();
   }

   @SinceKotlin(
      version = "1.4"
   )
   @NotNull
   public static final Set setOfNotNull(@NotNull Object... elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      return (Set)ArraysKt.filterNotNullTo(elements, (Collection)(new LinkedHashSet()));
   }

   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final Set buildSet(@BuilderInference Function1 builderAction) {
      Intrinsics.checkNotNullParameter(builderAction, "builderAction");
      Set var1 = SetsKt.createSetBuilder();
      builderAction.invoke(var1);
      return SetsKt.build(var1);
   }

   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final Set buildSet(int capacity, @BuilderInference Function1 builderAction) {
      Intrinsics.checkNotNullParameter(builderAction, "builderAction");
      Set var2 = SetsKt.createSetBuilder(capacity);
      builderAction.invoke(var2);
      return SetsKt.build(var2);
   }

   @InlineOnly
   private static final Set orEmpty(Set $this$orEmpty) {
      Set var10000 = $this$orEmpty;
      if ($this$orEmpty == null) {
         var10000 = SetsKt.emptySet();
      }

      return var10000;
   }

   @NotNull
   public static final Set optimizeReadOnlySet(@NotNull Set $this$optimizeReadOnlySet) {
      Intrinsics.checkNotNullParameter($this$optimizeReadOnlySet, "<this>");
      Set var10000;
      switch ($this$optimizeReadOnlySet.size()) {
         case 0:
            var10000 = SetsKt.emptySet();
            break;
         case 1:
            var10000 = SetsKt.setOf($this$optimizeReadOnlySet.iterator().next());
            break;
         default:
            var10000 = $this$optimizeReadOnlySet;
      }

      return var10000;
   }

   public SetsKt__SetsKt() {
   }
}
