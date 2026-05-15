package kotlin.collections;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000B\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u001a¥\u0001\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\r\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0002*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00032b\u0010\f\u001a^\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0015\u0012\u0013\u0018\u00018\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00028\u00020\u0004H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a¹\u0001\u0010\u0013\u001a\u00028\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0002\"\u0016\b\u0003\u0010\u0011*\u0010\u0012\u0006\b\u0000\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0010*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00032\u0006\u0010\u0012\u001a\u00028\u00032b\u0010\f\u001a^\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0015\u0012\u0013\u0018\u00018\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00028\u00020\u0004H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001aK\u0010\u0016\u001a\u00028\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0016\b\u0002\u0010\u0011*\u0010\u0012\u0006\b\u0000\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u00150\u0010*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00032\u0006\u0010\u0012\u001a\u00028\u0002H\u0007¢\u0006\u0004\b\u0016\u0010\u0017\u001aÆ\u0001\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\r\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0002*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000326\u0010\u0019\u001a2\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00028\u00020\u00182K\u0010\f\u001aG\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00118\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00028\u00020\u001aH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u0081\u0001\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\r\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0002*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00032\u0006\u0010\u001d\u001a\u00028\u000226\u0010\f\u001a2\u0012\u0013\u0012\u00118\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00028\u00020\u0018H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001e\u001aÚ\u0001\u0010\u001f\u001a\u00028\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0002\"\u0016\b\u0003\u0010\u0011*\u0010\u0012\u0006\b\u0000\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0010*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00032\u0006\u0010\u0012\u001a\u00028\u000326\u0010\u0019\u001a2\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00028\u00020\u00182K\u0010\f\u001aG\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00118\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00028\u00020\u001aH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u001a\u0095\u0001\u0010\u001f\u001a\u00028\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0002\"\u0016\b\u0003\u0010\u0011*\u0010\u0012\u0006\b\u0000\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0010*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00032\u0006\u0010\u0012\u001a\u00028\u00032\u0006\u0010\u001d\u001a\u00028\u000226\u0010\f\u001a2\u0012\u0013\u0012\u00118\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00028\u00020\u0018H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001f\u0010!\u001a\u0092\u0001\u0010#\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00000\r\"\u0004\b\u0000\u0010\"\"\b\b\u0001\u0010\u0000*\u00028\u0000\"\u0004\b\u0002\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00032K\u0010\f\u001aG\u0012\u0013\u0012\u00118\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00028\u00000\u001aH\u0087\bø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a¦\u0001\u0010%\u001a\u00028\u0003\"\u0004\b\u0000\u0010\"\"\b\b\u0001\u0010\u0000*\u00028\u0000\"\u0004\b\u0002\u0010\u0001\"\u0016\b\u0003\u0010\u0011*\u0010\u0012\u0006\b\u0000\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00000\u0010*\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00032\u0006\u0010\u0012\u001a\u00028\u00032K\u0010\f\u001aG\u0012\u0013\u0012\u00118\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00028\u00000\u001aH\u0087\bø\u0001\u0000¢\u0006\u0004\b%\u0010&\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006'"},
   d2 = {"T", "K", "R", "Lkotlin/collections/Grouping;", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "key", "accumulator", "element", "", "first", "operation", "", "aggregate", "(Lkotlin/collections/Grouping;Lkotlin/jvm/functions/Function4;)Ljava/util/Map;", "", "M", "destination", "aggregateTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function4;)Ljava/util/Map;", "", "eachCountTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;)Ljava/util/Map;", "Lkotlin/Function2;", "initialValueSelector", "Lkotlin/Function3;", "fold", "(Lkotlin/collections/Grouping;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;)Ljava/util/Map;", "initialValue", "(Lkotlin/collections/Grouping;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "foldTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;)Ljava/util/Map;", "(Lkotlin/collections/Grouping;Ljava/util/Map;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "S", "reduce", "(Lkotlin/collections/Grouping;Lkotlin/jvm/functions/Function3;)Ljava/util/Map;", "reduceTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function3;)Ljava/util/Map;", "kotlin-stdlib"},
   xs = "kotlin/collections/GroupingKt"
)
@SourceDebugExtension({"SMAP\nGrouping.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Grouping.kt\nkotlin/collections/GroupingKt__GroupingKt\n*L\n1#1,291:1\n80#1,6:292\n53#1:298\n80#1,6:299\n80#1,6:305\n53#1:311\n80#1,6:312\n80#1,6:318\n53#1:324\n80#1,6:325\n80#1,6:331\n189#1:337\n80#1,6:338\n*S KotlinDebug\n*F\n+ 1 Grouping.kt\nkotlin/collections/GroupingKt__GroupingKt\n*L\n53#1:292,6\n112#1:298\n112#1:299,6\n143#1:305,6\n164#1:311\n164#1:312,6\n189#1:318,6\n211#1:324\n211#1:325,6\n239#1:331,6\n257#1:337\n257#1:338,6\n*E\n"})
class GroupingKt__GroupingKt extends GroupingKt__GroupingJVMKt {
   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Map aggregate(@NotNull Grouping $this$aggregate, @NotNull Function4 operation) {
      Intrinsics.checkNotNullParameter($this$aggregate, "<this>");
      Intrinsics.checkNotNullParameter(operation, "operation");
      int $i$f$aggregate = 0;
      Grouping $this$aggregateTo$iv = $this$aggregate;
      Map destination$iv = (Map)(new LinkedHashMap());
      int $i$f$aggregateTo = 0;
      Iterator var6 = $this$aggregate.sourceIterator();

      while(var6.hasNext()) {
         Object e$iv = var6.next();
         Object key$iv = $this$aggregateTo$iv.keyOf(e$iv);
         Object accumulator$iv = destination$iv.get(key$iv);
         destination$iv.put(key$iv, operation.invoke(key$iv, accumulator$iv, e$iv, accumulator$iv == null && !destination$iv.containsKey(key$iv)));
      }

      return destination$iv;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Map aggregateTo(@NotNull Grouping $this$aggregateTo, @NotNull Map destination, @NotNull Function4 operation) {
      Intrinsics.checkNotNullParameter($this$aggregateTo, "<this>");
      Intrinsics.checkNotNullParameter(destination, "destination");
      Intrinsics.checkNotNullParameter(operation, "operation");
      int $i$f$aggregateTo = 0;
      Iterator var4 = $this$aggregateTo.sourceIterator();

      while(var4.hasNext()) {
         Object e = var4.next();
         Object key = $this$aggregateTo.keyOf(e);
         Object accumulator = destination.get(key);
         destination.put(key, operation.invoke(key, accumulator, e, accumulator == null && !destination.containsKey(key)));
      }

      return destination;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Map fold(@NotNull Grouping $this$fold, @NotNull Function2 initialValueSelector, @NotNull Function3 operation) {
      Intrinsics.checkNotNullParameter($this$fold, "<this>");
      Intrinsics.checkNotNullParameter(initialValueSelector, "initialValueSelector");
      Intrinsics.checkNotNullParameter(operation, "operation");
      int $i$f$fold = 0;
      int $i$f$aggregate = 0;
      Grouping $this$aggregateTo$iv$iv = $this$fold;
      Map destination$iv$iv = (Map)(new LinkedHashMap());
      int $i$f$aggregateTo = 0;
      Iterator var9 = $this$fold.sourceIterator();

      while(var9.hasNext()) {
         Object e$iv$iv = var9.next();
         Object key$iv$iv = $this$aggregateTo$iv$iv.keyOf(e$iv$iv);
         Object accumulator$iv$iv = destination$iv$iv.get(key$iv$iv);
         boolean first = accumulator$iv$iv == null && !destination$iv$iv.containsKey(key$iv$iv);
         int var17 = 0;
         Object var20 = operation.invoke(key$iv$iv, first ? initialValueSelector.invoke(key$iv$iv, e$iv$iv) : accumulator$iv$iv, e$iv$iv);
         destination$iv$iv.put(key$iv$iv, var20);
      }

      return destination$iv$iv;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Map foldTo(@NotNull Grouping $this$foldTo, @NotNull Map destination, @NotNull Function2 initialValueSelector, @NotNull Function3 operation) {
      Intrinsics.checkNotNullParameter($this$foldTo, "<this>");
      Intrinsics.checkNotNullParameter(destination, "destination");
      Intrinsics.checkNotNullParameter(initialValueSelector, "initialValueSelector");
      Intrinsics.checkNotNullParameter(operation, "operation");
      int $i$f$foldTo = 0;
      Grouping $this$aggregateTo$iv = $this$foldTo;
      int $i$f$aggregateTo = 0;
      Iterator var7 = $this$foldTo.sourceIterator();

      while(var7.hasNext()) {
         Object e$iv = var7.next();
         Object key$iv = $this$aggregateTo$iv.keyOf(e$iv);
         Object accumulator$iv = destination.get(key$iv);
         boolean first = accumulator$iv == null && !destination.containsKey(key$iv);
         int var15 = 0;
         Object var18 = operation.invoke(key$iv, first ? initialValueSelector.invoke(key$iv, e$iv) : accumulator$iv, e$iv);
         destination.put(key$iv, var18);
      }

      return destination;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Map fold(@NotNull Grouping $this$fold, Object initialValue, @NotNull Function2 operation) {
      Intrinsics.checkNotNullParameter($this$fold, "<this>");
      Intrinsics.checkNotNullParameter(operation, "operation");
      int $i$f$fold = 0;
      int $i$f$aggregate = 0;
      Grouping $this$aggregateTo$iv$iv = $this$fold;
      Map destination$iv$iv = (Map)(new LinkedHashMap());
      int $i$f$aggregateTo = 0;
      Iterator var9 = $this$fold.sourceIterator();

      while(var9.hasNext()) {
         Object e$iv$iv = var9.next();
         Object key$iv$iv = $this$aggregateTo$iv$iv.keyOf(e$iv$iv);
         Object accumulator$iv$iv = destination$iv$iv.get(key$iv$iv);
         boolean first = accumulator$iv$iv == null && !destination$iv$iv.containsKey(key$iv$iv);
         int var16 = 0;
         Object var19 = operation.invoke(first ? initialValue : accumulator$iv$iv, e$iv$iv);
         destination$iv$iv.put(key$iv$iv, var19);
      }

      return destination$iv$iv;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Map foldTo(@NotNull Grouping $this$foldTo, @NotNull Map destination, Object initialValue, @NotNull Function2 operation) {
      Intrinsics.checkNotNullParameter($this$foldTo, "<this>");
      Intrinsics.checkNotNullParameter(destination, "destination");
      Intrinsics.checkNotNullParameter(operation, "operation");
      int $i$f$foldTo = 0;
      Grouping $this$aggregateTo$iv = $this$foldTo;
      int $i$f$aggregateTo = 0;
      Iterator var7 = $this$foldTo.sourceIterator();

      while(var7.hasNext()) {
         Object e$iv = var7.next();
         Object key$iv = $this$aggregateTo$iv.keyOf(e$iv);
         Object accumulator$iv = destination.get(key$iv);
         boolean first = accumulator$iv == null && !destination.containsKey(key$iv);
         int var14 = 0;
         Object var17 = operation.invoke(first ? initialValue : accumulator$iv, e$iv);
         destination.put(key$iv, var17);
      }

      return destination;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Map reduce(@NotNull Grouping $this$reduce, @NotNull Function3 operation) {
      Intrinsics.checkNotNullParameter($this$reduce, "<this>");
      Intrinsics.checkNotNullParameter(operation, "operation");
      int $i$f$reduce = 0;
      int $i$f$aggregate = 0;
      Grouping $this$aggregateTo$iv$iv = $this$reduce;
      Map destination$iv$iv = (Map)(new LinkedHashMap());
      int $i$f$aggregateTo = 0;
      Iterator var8 = $this$reduce.sourceIterator();

      while(var8.hasNext()) {
         Object e$iv$iv = var8.next();
         Object key$iv$iv = $this$aggregateTo$iv$iv.keyOf(e$iv$iv);
         Object accumulator$iv$iv = destination$iv$iv.get(key$iv$iv);
         boolean first = accumulator$iv$iv == null && !destination$iv$iv.containsKey(key$iv$iv);
         int var16 = 0;
         Object var19 = first ? e$iv$iv : operation.invoke(key$iv$iv, accumulator$iv$iv, e$iv$iv);
         destination$iv$iv.put(key$iv$iv, var19);
      }

      return destination$iv$iv;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Map reduceTo(@NotNull Grouping $this$reduceTo, @NotNull Map destination, @NotNull Function3 operation) {
      Intrinsics.checkNotNullParameter($this$reduceTo, "<this>");
      Intrinsics.checkNotNullParameter(destination, "destination");
      Intrinsics.checkNotNullParameter(operation, "operation");
      int $i$f$reduceTo = 0;
      Grouping $this$aggregateTo$iv = $this$reduceTo;
      int $i$f$aggregateTo = 0;
      Iterator var6 = $this$reduceTo.sourceIterator();

      while(var6.hasNext()) {
         Object e$iv = var6.next();
         Object key$iv = $this$aggregateTo$iv.keyOf(e$iv);
         Object accumulator$iv = destination.get(key$iv);
         boolean first = accumulator$iv == null && !destination.containsKey(key$iv);
         int var14 = 0;
         Object var17 = first ? e$iv : operation.invoke(key$iv, accumulator$iv, e$iv);
         destination.put(key$iv, var17);
      }

      return destination;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Map eachCountTo(@NotNull Grouping $this$eachCountTo, @NotNull Map destination) {
      Intrinsics.checkNotNullParameter($this$eachCountTo, "<this>");
      Intrinsics.checkNotNullParameter(destination, "destination");
      Object initialValue$iv = 0;
      int $i$f$foldTo = 0;
      Grouping $this$aggregateTo$iv$iv = $this$eachCountTo;
      int $i$f$aggregateTo = 0;
      Iterator var7 = $this$eachCountTo.sourceIterator();

      while(var7.hasNext()) {
         Object e$iv$iv = var7.next();
         Object key$iv$iv = $this$aggregateTo$iv$iv.keyOf(e$iv$iv);
         Object accumulator$iv$iv = destination.get(key$iv$iv);
         boolean first$iv = accumulator$iv$iv == null && !destination.containsKey(key$iv$iv);
         int var16 = 0;
         int acc = ((Number)(first$iv ? initialValue$iv : accumulator$iv$iv)).intValue();
         int var18 = 0;
         ++acc;
         destination.put(key$iv$iv, acc);
      }

      return destination;
   }

   public GroupingKt__GroupingKt() {
   }
}
