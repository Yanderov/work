package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.BuilderInference;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.comparisons.ComparisonsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000\u008c\u0001\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000f\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001aJ\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u00012!\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00028\u00000\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001aJ\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u00012!\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00028\u00000\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\b\f\u0010\n\u001a&\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00028\u00000\rj\b\u0012\u0004\u0012\u00028\u0000`\u000e\"\u0004\b\u0000\u0010\u0000H\u0087\b¢\u0006\u0004\b\u000f\u0010\u0010\u001a7\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00028\u00000\rj\b\u0012\u0004\u0012\u00028\u0000`\u000e\"\u0004\b\u0000\u0010\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0011\"\u00028\u0000¢\u0006\u0004\b\u000f\u0010\u0013\u001aU\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u00142\u0006\u0010\u0015\u001a\u00020\u00012\u001f\b\u0001\u0010\u0018\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\u0004\u0012\u00020\u00160\u0003¢\u0006\u0002\b\u0017H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0004\b\u0019\u0010\n\u001aM\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u00142\u001f\b\u0001\u0010\u0018\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\u0004\u0012\u00020\u00160\u0003¢\u0006\u0002\b\u0017H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\u0019\u0010\u001a\u001a#\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u00112\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u001bH\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a5\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011\"\u0004\b\u0000\u0010\u00002\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u001b2\f\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0000¢\u0006\u0004\b\u001e\u0010!\u001a\u0019\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u0000¢\u0006\u0004\b\"\u0010#\u001a\u001c\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u0000H\u0087\b¢\u0006\u0004\b$\u0010#\u001a-\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0011\"\u00028\u0000¢\u0006\u0004\b$\u0010%\u001a'\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\b\b\u0000\u0010\u0000*\u00020\u001d2\b\u0010&\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b'\u0010(\u001a5\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\b\b\u0000\u0010\u0000*\u00020\u001d2\u0016\u0010\u0012\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00018\u00000\u0011\"\u0004\u0018\u00018\u0000¢\u0006\u0004\b'\u0010%\u001a\u001c\u0010)\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\"\u0004\b\u0000\u0010\u0000H\u0087\b¢\u0006\u0004\b)\u0010#\u001a-\u0010)\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\"\u0004\b\u0000\u0010\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0011\"\u00028\u0000¢\u0006\u0004\b)\u0010%\u001a'\u0010.\u001a\u00020\u00162\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010*\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\u0001H\u0002¢\u0006\u0004\b,\u0010-\u001a\u000f\u0010/\u001a\u00020\u0016H\u0001¢\u0006\u0004\b/\u00100\u001a\u000f\u00101\u001a\u00020\u0016H\u0001¢\u0006\u0004\b1\u00100\u001a'\u00102\u001a\b\u0012\u0004\u0012\u00028\u00000\u001b\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0011H\u0000¢\u0006\u0004\b2\u00103\u001aU\u00107\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\b2\u0006\u0010&\u001a\u00028\u00002\u001a\u00106\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u000004j\n\u0012\u0006\b\u0000\u0012\u00028\u0000`52\b\b\u0002\u0010*\u001a\u00020\u00012\b\b\u0002\u0010+\u001a\u00020\u0001¢\u0006\u0004\b7\u00108\u001aE\u00107\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\b2\b\b\u0002\u0010*\u001a\u00020\u00012\b\b\u0002\u0010+\u001a\u00020\u00012\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b7\u0010:\u001aG\u00107\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000;*\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\b2\b\u0010&\u001a\u0004\u0018\u00018\u00002\b\b\u0002\u0010*\u001a\u00020\u00012\b\b\u0002\u0010+\u001a\u00020\u0001¢\u0006\u0004\b7\u0010<\u001ai\u0010@\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0000\"\u000e\b\u0001\u0010=*\b\u0012\u0004\u0012\u00028\u00010;*\b\u0012\u0004\u0012\u00028\u00000\b2\b\u0010>\u001a\u0004\u0018\u00018\u00012\b\b\u0002\u0010*\u001a\u00020\u00012\b\b\u0002\u0010+\u001a\u00020\u00012\u0016\b\u0004\u0010?\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u0003H\u0086\bø\u0001\u0000¢\u0006\u0004\b@\u0010A\u001a3\u0010D\u001a\u00020C\"\t\b\u0000\u0010\u0000¢\u0006\u0002\bB*\b\u0012\u0004\u0012\u00028\u00000\u001b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bH\u0087\b¢\u0006\u0004\bD\u0010E\u001aJ\u0010J\u001a\u00028\u0001\"\u0010\b\u0000\u0010F*\u0006\u0012\u0002\b\u00030\u001b*\u00028\u0001\"\u0004\b\u0001\u0010G*\u00028\u00002\f\u0010I\u001a\b\u0012\u0004\u0012\u00028\u00010HH\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0004\bJ\u0010K\u001a \u0010L\u001a\u00020C\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u001bH\u0087\b¢\u0006\u0004\bL\u0010M\u001a3\u0010N\u001a\u00020C\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u001bH\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000¢\u0006\u0004\bN\u0010M\u001a%\u0010O\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\bH\u0000¢\u0006\u0004\bO\u0010P\u001a(\u0010Q\u001a\b\u0012\u0004\u0012\u00028\u00000\u001b\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u001bH\u0087\b¢\u0006\u0004\bQ\u0010R\u001a(\u0010Q\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\bH\u0087\b¢\u0006\u0004\bQ\u0010P\u001a-\u0010V\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000S2\u0006\u0010U\u001a\u00020TH\u0007¢\u0006\u0004\bV\u0010W\"\u0019\u0010[\u001a\u00020X*\u0006\u0012\u0002\b\u00030\u001b8F¢\u0006\u0006\u001a\u0004\bY\u0010Z\"!\u0010^\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\b8F¢\u0006\u0006\u001a\u0004\b\\\u0010]\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006_"},
   d2 = {"T", "", "size", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "index", "init", "", "List", "(ILkotlin/jvm/functions/Function1;)Ljava/util/List;", "", "MutableList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "arrayListOf", "()Ljava/util/ArrayList;", "", "elements", "([Ljava/lang/Object;)Ljava/util/ArrayList;", "E", "capacity", "", "Lkotlin/ExtensionFunctionType;", "builderAction", "buildList", "(Lkotlin/jvm/functions/Function1;)Ljava/util/List;", "", "collection", "", "collectionToArrayCommonImpl", "(Ljava/util/Collection;)[Ljava/lang/Object;", "array", "(Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;", "emptyList", "()Ljava/util/List;", "listOf", "([Ljava/lang/Object;)Ljava/util/List;", "element", "listOfNotNull", "(Ljava/lang/Object;)Ljava/util/List;", "mutableListOf", "fromIndex", "toIndex", "rangeCheck$CollectionsKt__CollectionsKt", "(III)V", "rangeCheck", "throwCountOverflow", "()V", "throwIndexOverflow", "asCollection", "([Ljava/lang/Object;)Ljava/util/Collection;", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "comparator", "binarySearch", "(Ljava/util/List;Ljava/lang/Object;Ljava/util/Comparator;II)I", "comparison", "(Ljava/util/List;IILkotlin/jvm/functions/Function1;)I", "", "(Ljava/util/List;Ljava/lang/Comparable;II)I", "K", "key", "selector", "binarySearchBy", "(Ljava/util/List;Ljava/lang/Comparable;IILkotlin/jvm/functions/Function1;)I", "Lkotlin/internal/OnlyInputTypes;", "", "containsAll", "(Ljava/util/Collection;Ljava/util/Collection;)Z", "C", "R", "Lkotlin/Function0;", "defaultValue", "ifEmpty", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNotEmpty", "(Ljava/util/Collection;)Z", "isNullOrEmpty", "optimizeReadOnlyList", "(Ljava/util/List;)Ljava/util/List;", "orEmpty", "(Ljava/util/Collection;)Ljava/util/Collection;", "", "Lkotlin/random/Random;", "random", "shuffled", "(Ljava/lang/Iterable;Lkotlin/random/Random;)Ljava/util/List;", "Lkotlin/ranges/IntRange;", "getIndices", "(Ljava/util/Collection;)Lkotlin/ranges/IntRange;", "indices", "getLastIndex", "(Ljava/util/List;)I", "lastIndex", "kotlin-stdlib"},
   xs = "kotlin/collections/CollectionsKt"
)
@SourceDebugExtension({"SMAP\nCollections.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Collections.kt\nkotlin/collections/CollectionsKt__CollectionsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,526:1\n1#2:527\n*E\n"})
class CollectionsKt__CollectionsKt extends CollectionsKt__CollectionsJVMKt {
   @NotNull
   public static final Collection asCollection(@NotNull Object[] $this$asCollection) {
      Intrinsics.checkNotNullParameter($this$asCollection, "<this>");
      return new ArrayAsCollection($this$asCollection, false);
   }

   @NotNull
   public static final List emptyList() {
      return EmptyList.INSTANCE;
   }

   @NotNull
   public static final List listOf(@NotNull Object... elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      return elements.length > 0 ? ArraysKt.asList(elements) : CollectionsKt.emptyList();
   }

   @InlineOnly
   private static final List listOf() {
      return CollectionsKt.emptyList();
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final List mutableListOf() {
      return (List)(new ArrayList());
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final ArrayList arrayListOf() {
      return new ArrayList();
   }

   @NotNull
   public static final List mutableListOf(@NotNull Object... elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      return elements.length == 0 ? (List)(new ArrayList()) : (List)(new ArrayList(new ArrayAsCollection(elements, true)));
   }

   @NotNull
   public static final ArrayList arrayListOf(@NotNull Object... elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      return elements.length == 0 ? new ArrayList() : new ArrayList(new ArrayAsCollection(elements, true));
   }

   @NotNull
   public static final List listOfNotNull(@Nullable Object element) {
      return element != null ? CollectionsKt.listOf(element) : CollectionsKt.emptyList();
   }

   @NotNull
   public static final List listOfNotNull(@NotNull Object... elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      return ArraysKt.filterNotNull(elements);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final List List(int size, Function1 init) {
      Intrinsics.checkNotNullParameter(init, "init");
      ArrayList var2 = new ArrayList(size);

      for(int var3 = 0; var3 < size; ++var3) {
         var2.add(init.invoke(var3));
      }

      return (List)var2;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final List MutableList(int size, Function1 init) {
      Intrinsics.checkNotNullParameter(init, "init");
      ArrayList list = new ArrayList(size);

      for(int index = 0; index < size; ++index) {
         int var5 = 0;
         list.add(init.invoke(index));
      }

      return (List)list;
   }

   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final List buildList(@BuilderInference Function1 builderAction) {
      Intrinsics.checkNotNullParameter(builderAction, "builderAction");
      List var1 = CollectionsKt.createListBuilder();
      builderAction.invoke(var1);
      return CollectionsKt.build(var1);
   }

   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final List buildList(int capacity, @BuilderInference Function1 builderAction) {
      Intrinsics.checkNotNullParameter(builderAction, "builderAction");
      List var2 = CollectionsKt.createListBuilder(capacity);
      builderAction.invoke(var2);
      return CollectionsKt.build(var2);
   }

   @NotNull
   public static final IntRange getIndices(@NotNull Collection $this$indices) {
      Intrinsics.checkNotNullParameter($this$indices, "<this>");
      return new IntRange(0, $this$indices.size() - 1);
   }

   public static final int getLastIndex(@NotNull List $this$lastIndex) {
      Intrinsics.checkNotNullParameter($this$lastIndex, "<this>");
      return $this$lastIndex.size() - 1;
   }

   @InlineOnly
   private static final boolean isNotEmpty(Collection $this$isNotEmpty) {
      Intrinsics.checkNotNullParameter($this$isNotEmpty, "<this>");
      return !$this$isNotEmpty.isEmpty();
   }

   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final boolean isNullOrEmpty(Collection $this$isNullOrEmpty) {
      return $this$isNullOrEmpty == null || $this$isNullOrEmpty.isEmpty();
   }

   @InlineOnly
   private static final Collection orEmpty(Collection $this$orEmpty) {
      Collection var10000 = $this$orEmpty;
      if ($this$orEmpty == null) {
         var10000 = (Collection)CollectionsKt.emptyList();
      }

      return var10000;
   }

   @InlineOnly
   private static final List orEmpty(List $this$orEmpty) {
      List var10000 = $this$orEmpty;
      if ($this$orEmpty == null) {
         var10000 = CollectionsKt.emptyList();
      }

      return var10000;
   }

   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final Object ifEmpty(Collection $this$ifEmpty, Function0 defaultValue) {
      Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
      return $this$ifEmpty.isEmpty() ? defaultValue.invoke() : $this$ifEmpty;
   }

   @InlineOnly
   private static final boolean containsAll(Collection $this$containsAll, Collection elements) {
      Intrinsics.checkNotNullParameter($this$containsAll, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      return $this$containsAll.containsAll(elements);
   }

   @SinceKotlin(
      version = "1.3"
   )
   @NotNull
   public static final List shuffled(@NotNull Iterable $this$shuffled, @NotNull Random random) {
      Intrinsics.checkNotNullParameter($this$shuffled, "<this>");
      Intrinsics.checkNotNullParameter(random, "random");
      List $this$shuffled_u24lambda_u245 = CollectionsKt.toMutableList($this$shuffled);
      int var4 = 0;
      CollectionsKt.shuffle($this$shuffled_u24lambda_u245, random);
      return $this$shuffled_u24lambda_u245;
   }

   @NotNull
   public static final List optimizeReadOnlyList(@NotNull List $this$optimizeReadOnlyList) {
      Intrinsics.checkNotNullParameter($this$optimizeReadOnlyList, "<this>");
      List var10000;
      switch ($this$optimizeReadOnlyList.size()) {
         case 0:
            var10000 = CollectionsKt.emptyList();
            break;
         case 1:
            var10000 = CollectionsKt.listOf($this$optimizeReadOnlyList.get(0));
            break;
         default:
            var10000 = $this$optimizeReadOnlyList;
      }

      return var10000;
   }

   public static final int binarySearch(@NotNull List $this$binarySearch, @Nullable Comparable element, int fromIndex, int toIndex) {
      Intrinsics.checkNotNullParameter($this$binarySearch, "<this>");
      rangeCheck$CollectionsKt__CollectionsKt($this$binarySearch.size(), fromIndex, toIndex);
      int low = fromIndex;
      int high = toIndex - 1;

      while(low <= high) {
         int mid = low + high >>> 1;
         Comparable midVal = (Comparable)$this$binarySearch.get(mid);
         int cmp = ComparisonsKt.compareValues(midVal, element);
         if (cmp < 0) {
            low = mid + 1;
         } else {
            if (cmp <= 0) {
               return mid;
            }

            high = mid - 1;
         }
      }

      return -(low + 1);
   }

   // $FF: synthetic method
   public static int binarySearch$default(List var0, Comparable var1, int var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      if ((var4 & 4) != 0) {
         var3 = var0.size();
      }

      return CollectionsKt.binarySearch(var0, var1, var2, var3);
   }

   public static final int binarySearch(@NotNull List $this$binarySearch, Object element, @NotNull Comparator comparator, int fromIndex, int toIndex) {
      Intrinsics.checkNotNullParameter($this$binarySearch, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      rangeCheck$CollectionsKt__CollectionsKt($this$binarySearch.size(), fromIndex, toIndex);
      int low = fromIndex;
      int high = toIndex - 1;

      while(low <= high) {
         int mid = low + high >>> 1;
         Object midVal = $this$binarySearch.get(mid);
         int cmp = comparator.compare(midVal, element);
         if (cmp < 0) {
            low = mid + 1;
         } else {
            if (cmp <= 0) {
               return mid;
            }

            high = mid - 1;
         }
      }

      return -(low + 1);
   }

   // $FF: synthetic method
   public static int binarySearch$default(List var0, Object var1, Comparator var2, int var3, int var4, int var5, Object var6) {
      if ((var5 & 4) != 0) {
         var3 = 0;
      }

      if ((var5 & 8) != 0) {
         var4 = var0.size();
      }

      return CollectionsKt.binarySearch(var0, var1, var2, var3, var4);
   }

   public static final int binarySearchBy(@NotNull List $this$binarySearchBy, @Nullable Comparable key, int fromIndex, int toIndex, @NotNull Function1 selector) {
      Intrinsics.checkNotNullParameter($this$binarySearchBy, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      int $i$f$binarySearchBy = 0;
      return CollectionsKt.binarySearch($this$binarySearchBy, fromIndex, toIndex, new Function1(selector, key) {
         public final Integer invoke(Object it) {
            return ComparisonsKt.compareValues((Comparable)selector.invoke(it), key);
         }
      });
   }

   // $FF: synthetic method
   public static int binarySearchBy$default(List $this$binarySearchBy_u24default, final Comparable key, int fromIndex, int toIndex, final Function1 selector, int $i$f$binarySearchBy, Object var6) {
      if (($i$f$binarySearchBy & 2) != 0) {
         fromIndex = 0;
      }

      if (($i$f$binarySearchBy & 4) != 0) {
         toIndex = $this$binarySearchBy_u24default.size();
      }

      Intrinsics.checkNotNullParameter($this$binarySearchBy_u24default, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      $i$f$binarySearchBy = 0;
      return CollectionsKt.binarySearch($this$binarySearchBy_u24default, fromIndex, toIndex, new Function1(selector, key) {
         public final Integer invoke(Object it) {
            return ComparisonsKt.compareValues((Comparable)selector.invoke(it), key);
         }
      });
   }

   public static final int binarySearch(@NotNull List $this$binarySearch, int fromIndex, int toIndex, @NotNull Function1 comparison) {
      Intrinsics.checkNotNullParameter($this$binarySearch, "<this>");
      Intrinsics.checkNotNullParameter(comparison, "comparison");
      rangeCheck$CollectionsKt__CollectionsKt($this$binarySearch.size(), fromIndex, toIndex);
      int low = fromIndex;
      int high = toIndex - 1;

      while(low <= high) {
         int mid = low + high >>> 1;
         Object midVal = $this$binarySearch.get(mid);
         int cmp = ((Number)comparison.invoke(midVal)).intValue();
         if (cmp < 0) {
            low = mid + 1;
         } else {
            if (cmp <= 0) {
               return mid;
            }

            high = mid - 1;
         }
      }

      return -(low + 1);
   }

   // $FF: synthetic method
   public static int binarySearch$default(List var0, int var1, int var2, Function1 var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = 0;
      }

      if ((var4 & 2) != 0) {
         var2 = var0.size();
      }

      return CollectionsKt.binarySearch(var0, var1, var2, var3);
   }

   private static final void rangeCheck$CollectionsKt__CollectionsKt(int size, int fromIndex, int toIndex) {
      if (fromIndex > toIndex) {
         throw new IllegalArgumentException("fromIndex (" + fromIndex + ") is greater than toIndex (" + toIndex + ").");
      } else if (fromIndex < 0) {
         throw new IndexOutOfBoundsException("fromIndex (" + fromIndex + ") is less than zero.");
      } else if (toIndex > size) {
         throw new IndexOutOfBoundsException("toIndex (" + toIndex + ") is greater than size (" + size + ").");
      }
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   public static final void throwIndexOverflow() {
      throw new ArithmeticException("Index overflow has happened.");
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   public static final void throwCountOverflow() {
      throw new ArithmeticException("Count overflow has happened.");
   }

   @NotNull
   public static final Object[] collectionToArrayCommonImpl(@NotNull Collection collection) {
      Intrinsics.checkNotNullParameter(collection, "collection");
      if (collection.isEmpty()) {
         return new Object[0];
      } else {
         Object[] destination = new Object[collection.size()];
         Iterator iterator = collection.iterator();

         for(int index = 0; iterator.hasNext(); destination[index++] = iterator.next()) {
         }

         return destination;
      }
   }

   @NotNull
   public static final Object[] collectionToArrayCommonImpl(@NotNull Collection collection, @NotNull Object[] array) {
      Intrinsics.checkNotNullParameter(collection, "collection");
      Intrinsics.checkNotNullParameter(array, "array");
      if (collection.isEmpty()) {
         return CollectionsKt.terminateCollectionToArray(0, array);
      } else {
         Object[] destination = array.length < collection.size() ? ArraysKt.arrayOfNulls(array, collection.size()) : array;
         Iterator iterator = collection.iterator();

         for(int index = 0; iterator.hasNext(); destination[index++] = iterator.next()) {
         }

         return CollectionsKt.terminateCollectionToArray(collection.size(), destination);
      }
   }

   public CollectionsKt__CollectionsKt() {
   }
}
