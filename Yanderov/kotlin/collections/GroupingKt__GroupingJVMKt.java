package kotlin.collections;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0002\b\u0004\u001a7\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u00040\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006\u001aa\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\t\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\b*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u001e\u0010\f\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000b\u0012\u0004\u0012\u00028\u00020\nH\u0081\bø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000f"},
   d2 = {"T", "K", "Lkotlin/collections/Grouping;", "", "", "eachCount", "(Lkotlin/collections/Grouping;)Ljava/util/Map;", "V", "R", "", "Lkotlin/Function1;", "", "f", "mapValuesInPlace", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "kotlin-stdlib"},
   xs = "kotlin/collections/GroupingKt"
)
@SourceDebugExtension({"SMAP\nGroupingJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GroupingJVM.kt\nkotlin/collections/GroupingKt__GroupingJVMKt\n+ 2 Grouping.kt\nkotlin/collections/GroupingKt__GroupingKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,52:1\n143#2:53\n80#2,4:54\n85#2:59\n1#3:58\n1863#4,2:60\n*S KotlinDebug\n*F\n+ 1 GroupingJVM.kt\nkotlin/collections/GroupingKt__GroupingJVMKt\n*L\n22#1:53\n22#1:54,4\n22#1:59\n48#1:60,2\n*E\n"})
class GroupingKt__GroupingJVMKt {
   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Map eachCount(@NotNull Grouping $this$eachCount) {
      Intrinsics.checkNotNullParameter($this$eachCount, "<this>");
      Map destination$iv = (Map)(new LinkedHashMap());
      int $i$f$foldTo = 0;
      Grouping $this$aggregateTo$iv$iv = $this$eachCount;
      int $i$f$aggregateTo = 0;
      Iterator var6 = $this$eachCount.sourceIterator();

      while(var6.hasNext()) {
         Object e$iv$iv = var6.next();
         Object key$iv$iv = $this$aggregateTo$iv$iv.keyOf(e$iv$iv);
         Object accumulator$iv$iv = destination$iv.get(key$iv$iv);
         boolean first$iv = accumulator$iv$iv == null && !destination$iv.containsKey(key$iv$iv);
         int var16 = 0;
         Object var10001;
         if (first$iv) {
            int var19 = 0;
            var10001 = new Ref.IntRef();
         } else {
            var10001 = accumulator$iv$iv;
         }

         Ref.IntRef acc = (Ref.IntRef)var10001;
         int var20 = 0;
         int var23 = 0;
         ++acc.element;
         destination$iv.put(key$iv$iv, acc);
      }

      Map $this$foldTo$iv = destination$iv;

      for(Map.Entry var27 : (Iterable)destination$iv.entrySet()) {
         Intrinsics.checkNotNull(var27, "null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K of kotlin.collections.GroupingKt__GroupingJVMKt.mapValuesInPlace$lambda$4, R of kotlin.collections.GroupingKt__GroupingJVMKt.mapValuesInPlace$lambda$4>");
         Map.Entry var24 = TypeIntrinsics.asMutableMapEntry(var27);
         int var28 = 0;
         var24.setValue(((Ref.IntRef)var27.getValue()).element);
      }

      return TypeIntrinsics.asMutableMap($this$foldTo$iv);
   }

   @PublishedApi
   @InlineOnly
   private static final Map mapValuesInPlace(Map $this$mapValuesInPlace, Function1 f) {
      Intrinsics.checkNotNullParameter($this$mapValuesInPlace, "<this>");
      Intrinsics.checkNotNullParameter(f, "f");
      Iterable $this$forEach$iv = (Iterable)$this$mapValuesInPlace.entrySet();
      int $i$f$forEach = 0;

      for(Object element$iv : $this$forEach$iv) {
         Map.Entry it = (Map.Entry)element$iv;
         int var7 = 0;
         Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K of kotlin.collections.GroupingKt__GroupingJVMKt.mapValuesInPlace$lambda$4, R of kotlin.collections.GroupingKt__GroupingJVMKt.mapValuesInPlace$lambda$4>");
         TypeIntrinsics.asMutableMapEntry(it).setValue(f.invoke(it));
      }

      return TypeIntrinsics.asMutableMap($this$mapValuesInPlace);
   }

   public GroupingKt__GroupingJVMKt() {
   }
}
