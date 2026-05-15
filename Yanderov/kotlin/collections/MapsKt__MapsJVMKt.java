package kotlin.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.collections.builders.MapBuilder;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000d\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\n\u001a;\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002H\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001aX\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\b\u001a\u00020\u00072#\u0010\f\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000bH\u0081\bø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001aP\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012#\u0010\f\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000bH\u0081\bø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000f\u001a'\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001H\u0001¢\u0006\u0004\b\u0010\u0010\u0011\u001a/\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\b\u001a\u00020\u0007H\u0001¢\u0006\u0004\b\u0010\u0010\u0012\u001a\u0017\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007H\u0001¢\u0006\u0004\b\u0014\u0010\u0015\u001a9\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0016¢\u0006\u0004\b\u0018\u0010\u0019\u001ac\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001e\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u000e\u0010\u001b\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u001a2*\u0010\u001d\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00160\u001c\"\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0016H\u0007¢\u0006\u0004\b\u001f\u0010 \u001a[\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001e\"\u000e\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000!\"\u0004\b\u0001\u0010\u00012*\u0010\u001d\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00160\u001c\"\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0016¢\u0006\u0004\b\u001f\u0010\"\u001aE\u0010'\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010#2\u0006\u0010$\u001a\u00028\u00002\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00010%H\u0086\bø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a \u0010+\u001a\u00020**\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020)0\u0004H\u0087\b¢\u0006\u0004\b+\u0010,\u001a9\u0010-\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004H\u0000¢\u0006\u0004\b-\u0010\u0006\u001a8\u0010.\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004H\u0081\b¢\u0006\u0004\b.\u0010\u0006\u001aA\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001e\"\u000e\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000!\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004¢\u0006\u0004\b/\u00100\u001aG\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001e\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00042\u000e\u0010\u001b\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u001a¢\u0006\u0004\b/\u00101\"\u0014\u00102\u001a\u00020\u00078\u0002X\u0082T¢\u0006\u0006\n\u0004\b2\u00103\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u00064"},
   d2 = {"K", "V", "", "builder", "", "build", "(Ljava/util/Map;)Ljava/util/Map;", "", "capacity", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "builderAction", "buildMapInternal", "(ILkotlin/jvm/functions/Function1;)Ljava/util/Map;", "(Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "createMapBuilder", "()Ljava/util/Map;", "(I)Ljava/util/Map;", "expectedSize", "mapCapacity", "(I)I", "Lkotlin/Pair;", "pair", "mapOf", "(Lkotlin/Pair;)Ljava/util/Map;", "Ljava/util/Comparator;", "comparator", "", "pairs", "Ljava/util/SortedMap;", "sortedMapOf", "(Ljava/util/Comparator;[Lkotlin/Pair;)Ljava/util/SortedMap;", "", "([Lkotlin/Pair;)Ljava/util/SortedMap;", "Ljava/util/concurrent/ConcurrentMap;", "key", "Lkotlin/Function0;", "defaultValue", "getOrPut", "(Ljava/util/concurrent/ConcurrentMap;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "", "Ljava/util/Properties;", "toProperties", "(Ljava/util/Map;)Ljava/util/Properties;", "toSingletonMap", "toSingletonMapOrSelf", "toSortedMap", "(Ljava/util/Map;)Ljava/util/SortedMap;", "(Ljava/util/Map;Ljava/util/Comparator;)Ljava/util/SortedMap;", "INT_MAX_POWER_OF_TWO", "I", "kotlin-stdlib"},
   xs = "kotlin/collections/MapsKt"
)
@SourceDebugExtension({"SMAP\nMapsJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MapsJVM.kt\nkotlin/collections/MapsKt__MapsJVMKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,157:1\n1#2:158\n*E\n"})
class MapsKt__MapsJVMKt extends MapsKt__MapWithDefaultKt {
   private static final int INT_MAX_POWER_OF_TWO = 1073741824;

   @NotNull
   public static final Map mapOf(@NotNull Pair pair) {
      Intrinsics.checkNotNullParameter(pair, "pair");
      Map var10000 = Collections.singletonMap(pair.getFirst(), pair.getSecond());
      Intrinsics.checkNotNullExpressionValue(var10000, "singletonMap(...)");
      return var10000;
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final Map buildMapInternal(Function1 builderAction) {
      Intrinsics.checkNotNullParameter(builderAction, "builderAction");
      Map var1 = MapsKt.createMapBuilder();
      builderAction.invoke(var1);
      return MapsKt.build(var1);
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final Map buildMapInternal(int capacity, Function1 builderAction) {
      Intrinsics.checkNotNullParameter(builderAction, "builderAction");
      Map var2 = MapsKt.createMapBuilder(capacity);
      builderAction.invoke(var2);
      return MapsKt.build(var2);
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   @NotNull
   public static final Map createMapBuilder() {
      return new MapBuilder();
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   @NotNull
   public static final Map createMapBuilder(int capacity) {
      return new MapBuilder(capacity);
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   @NotNull
   public static final Map build(@NotNull Map builder) {
      Intrinsics.checkNotNullParameter(builder, "builder");
      return ((MapBuilder)builder).build();
   }

   public static final Object getOrPut(@NotNull ConcurrentMap $this$getOrPut, Object key, @NotNull Function0 defaultValue) {
      Intrinsics.checkNotNullParameter($this$getOrPut, "<this>");
      Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
      int $i$f$getOrPut = 0;
      Object var10000 = $this$getOrPut.get(key);
      if (var10000 == null) {
         Object var5_1 = defaultValue.invoke();
         int var6 = 0;
         var10000 = $this$getOrPut.putIfAbsent(key, var5_1);
         if (var10000 == null) {
            var10000 = var5_1;
         }
      }

      return var10000;
   }

   @NotNull
   public static final SortedMap toSortedMap(@NotNull Map $this$toSortedMap) {
      Intrinsics.checkNotNullParameter($this$toSortedMap, "<this>");
      return (SortedMap)(new TreeMap($this$toSortedMap));
   }

   @NotNull
   public static final SortedMap toSortedMap(@NotNull Map $this$toSortedMap, @NotNull Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$toSortedMap, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      TreeMap $this$toSortedMap_u24lambda_u241 = new TreeMap(comparator);
      int var4 = 0;
      $this$toSortedMap_u24lambda_u241.putAll($this$toSortedMap);
      return (SortedMap)$this$toSortedMap_u24lambda_u241;
   }

   @NotNull
   public static final SortedMap sortedMapOf(@NotNull Pair... pairs) {
      Intrinsics.checkNotNullParameter(pairs, "pairs");
      TreeMap $this$sortedMapOf_u24lambda_u242 = new TreeMap();
      int var3 = 0;
      MapsKt.putAll((Map)$this$sortedMapOf_u24lambda_u242, pairs);
      return (SortedMap)$this$sortedMapOf_u24lambda_u242;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @NotNull
   public static final SortedMap sortedMapOf(@NotNull Comparator comparator, @NotNull Pair... pairs) {
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      Intrinsics.checkNotNullParameter(pairs, "pairs");
      TreeMap $this$sortedMapOf_u24lambda_u243 = new TreeMap(comparator);
      int var4 = 0;
      MapsKt.putAll((Map)$this$sortedMapOf_u24lambda_u243, pairs);
      return (SortedMap)$this$sortedMapOf_u24lambda_u243;
   }

   @InlineOnly
   private static final Properties toProperties(Map $this$toProperties) {
      Intrinsics.checkNotNullParameter($this$toProperties, "<this>");
      Properties $this$toProperties_u24lambda_u244 = new Properties();
      int var3 = 0;
      $this$toProperties_u24lambda_u244.putAll($this$toProperties);
      return $this$toProperties_u24lambda_u244;
   }

   @InlineOnly
   private static final Map toSingletonMapOrSelf(Map $this$toSingletonMapOrSelf) {
      Intrinsics.checkNotNullParameter($this$toSingletonMapOrSelf, "<this>");
      return MapsKt.toSingletonMap($this$toSingletonMapOrSelf);
   }

   @NotNull
   public static final Map toSingletonMap(@NotNull Map $this$toSingletonMap) {
      Intrinsics.checkNotNullParameter($this$toSingletonMap, "<this>");
      Object var1 = $this$toSingletonMap.entrySet().iterator().next();
      Map.Entry $this$toSingletonMap_u24lambda_u245 = (Map.Entry)var1;
      int var3 = 0;
      Map var10000 = Collections.singletonMap($this$toSingletonMap_u24lambda_u245.getKey(), $this$toSingletonMap_u24lambda_u245.getValue());
      Intrinsics.checkNotNullExpressionValue(var10000, "with(...)");
      return var10000;
   }

   @PublishedApi
   public static final int mapCapacity(int expectedSize) {
      return expectedSize < 0 ? expectedSize : (expectedSize < 3 ? expectedSize + 1 : (expectedSize < 1073741824 ? (int)((float)expectedSize / 0.75F + 1.0F) : Integer.MAX_VALUE));
   }

   public MapsKt__MapsJVMKt() {
   }
}
