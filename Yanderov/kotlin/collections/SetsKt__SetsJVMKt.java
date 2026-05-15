package kotlin.collections;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.collections.builders.SetBuilder;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000@\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a)\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u0004\b\u0000\u0010\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001aF\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0007\u001a\u00020\u00062\u001d\u0010\u000b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0081\bø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a>\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u0004\b\u0000\u0010\u00002\u001d\u0010\u000b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0081\bø\u0001\u0000¢\u0006\u0004\b\f\u0010\u000e\u001a\u001b\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000H\u0001¢\u0006\u0004\b\u000f\u0010\u0010\u001a#\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0007\u001a\u00020\u0006H\u0001¢\u0006\u0004\b\u000f\u0010\u0011\u001a!\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u0004\b\u0000\u0010\u00122\u0006\u0010\u0013\u001a\u00028\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a-\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018\"\u0004\b\u0000\u0010\u00122\u0012\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0016\"\u00028\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001aI\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018\"\u0004\b\u0000\u0010\u00122\u001a\u0010\u001d\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00000\u001bj\n\u0012\u0006\b\u0000\u0012\u00028\u0000`\u001c2\u0012\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0016\"\u00028\u0000¢\u0006\u0004\b\u0019\u0010\u001e\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001f"},
   d2 = {"E", "", "builder", "", "build", "(Ljava/util/Set;)Ljava/util/Set;", "", "capacity", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "builderAction", "buildSetInternal", "(ILkotlin/jvm/functions/Function1;)Ljava/util/Set;", "(Lkotlin/jvm/functions/Function1;)Ljava/util/Set;", "createSetBuilder", "()Ljava/util/Set;", "(I)Ljava/util/Set;", "T", "element", "setOf", "(Ljava/lang/Object;)Ljava/util/Set;", "", "elements", "Ljava/util/TreeSet;", "sortedSetOf", "([Ljava/lang/Object;)Ljava/util/TreeSet;", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "comparator", "(Ljava/util/Comparator;[Ljava/lang/Object;)Ljava/util/TreeSet;", "kotlin-stdlib"},
   xs = "kotlin/collections/SetsKt"
)
class SetsKt__SetsJVMKt {
   @NotNull
   public static final Set setOf(Object element) {
      Set var10000 = Collections.singleton(element);
      Intrinsics.checkNotNullExpressionValue(var10000, "singleton(...)");
      return var10000;
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final Set buildSetInternal(Function1 builderAction) {
      Intrinsics.checkNotNullParameter(builderAction, "builderAction");
      Set var1 = SetsKt.createSetBuilder();
      builderAction.invoke(var1);
      return SetsKt.build(var1);
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final Set buildSetInternal(int capacity, Function1 builderAction) {
      Intrinsics.checkNotNullParameter(builderAction, "builderAction");
      Set var2 = SetsKt.createSetBuilder(capacity);
      builderAction.invoke(var2);
      return SetsKt.build(var2);
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   @NotNull
   public static final Set createSetBuilder() {
      return new SetBuilder();
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   @NotNull
   public static final Set createSetBuilder(int capacity) {
      return new SetBuilder(capacity);
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   @NotNull
   public static final Set build(@NotNull Set builder) {
      Intrinsics.checkNotNullParameter(builder, "builder");
      return ((SetBuilder)builder).build();
   }

   @NotNull
   public static final TreeSet sortedSetOf(@NotNull Object... elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      return (TreeSet)ArraysKt.toCollection(elements, (Collection)(new TreeSet()));
   }

   @NotNull
   public static final TreeSet sortedSetOf(@NotNull Comparator comparator, @NotNull Object... elements) {
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      Intrinsics.checkNotNullParameter(elements, "elements");
      return (TreeSet)ArraysKt.toCollection(elements, (Collection)(new TreeSet(comparator)));
   }

   public SetsKt__SetsJVMKt() {
   }
}
