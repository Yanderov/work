package kotlin.collections;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000 \n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a.\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\b\u0003\u0010\u0004\u001a6\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u000e\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0005H\u0086\u0002¢\u0006\u0004\b\u0003\u0010\u0007\u001a4\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0086\u0002¢\u0006\u0004\b\u0003\u0010\t\u001a4\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0086\u0002¢\u0006\u0004\b\u0003\u0010\u000b\u001a.\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u0087\b¢\u0006\u0004\b\f\u0010\u0004\u001a.\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\b\r\u0010\u0004\u001a6\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u000e\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0005H\u0086\u0002¢\u0006\u0004\b\r\u0010\u0007\u001a4\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0086\u0002¢\u0006\u0004\b\r\u0010\t\u001a4\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0086\u0002¢\u0006\u0004\b\r\u0010\u000b\u001a.\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u0087\b¢\u0006\u0004\b\u000e\u0010\u0004¨\u0006\u000f"},
   d2 = {"T", "", "element", "minus", "(Ljava/util/Set;Ljava/lang/Object;)Ljava/util/Set;", "", "elements", "(Ljava/util/Set;[Ljava/lang/Object;)Ljava/util/Set;", "", "(Ljava/util/Set;Ljava/lang/Iterable;)Ljava/util/Set;", "Lkotlin/sequences/Sequence;", "(Ljava/util/Set;Lkotlin/sequences/Sequence;)Ljava/util/Set;", "minusElement", "plus", "plusElement", "kotlin-stdlib"},
   xs = "kotlin/collections/SetsKt"
)
@SourceDebugExtension({"SMAP\n_Sets.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _Sets.kt\nkotlin/collections/SetsKt___SetsKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,140:1\n865#2,2:141\n855#2,2:143\n1#3:145\n*S KotlinDebug\n*F\n+ 1 _Sets.kt\nkotlin/collections/SetsKt___SetsKt\n*L\n29#1:141,2\n53#1:143,2\n*E\n"})
class SetsKt___SetsKt extends SetsKt__SetsKt {
   @NotNull
   public static final Set minus(@NotNull Set $this$minus, Object element) {
      Intrinsics.checkNotNullParameter($this$minus, "<this>");
      LinkedHashSet result = new LinkedHashSet(MapsKt.mapCapacity($this$minus.size()));
      boolean removed = false;
      Iterable $this$filterTo$iv = (Iterable)$this$minus;
      int $i$f$filterTo = 0;

      for(Object element$iv : $this$filterTo$iv) {
         int var9 = 0;
         boolean var10000;
         if (!removed && Intrinsics.areEqual(element$iv, element)) {
            removed = true;
            var10000 = false;
         } else {
            var10000 = true;
         }

         if (var10000) {
            ((Collection)result).add(element$iv);
         }
      }

      return (Set)((Collection)result);
   }

   @NotNull
   public static final Set minus(@NotNull Set $this$minus, @NotNull Object[] elements) {
      Intrinsics.checkNotNullParameter($this$minus, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      LinkedHashSet result = new LinkedHashSet((Collection)$this$minus);
      CollectionsKt.removeAll((Collection)result, elements);
      return (Set)result;
   }

   @NotNull
   public static final Set minus(@NotNull Set $this$minus, @NotNull Iterable elements) {
      Intrinsics.checkNotNullParameter($this$minus, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      Collection other = CollectionsKt.convertToListIfNotCollection(elements);
      if (other.isEmpty()) {
         return CollectionsKt.toSet((Iterable)$this$minus);
      } else if (other instanceof Set) {
         Iterable result = (Iterable)$this$minus;
         Collection destination$iv = (Collection)(new LinkedHashSet());
         int $i$f$filterNotTo = 0;

         for(Object element$iv : result) {
            int var9 = 0;
            if (!other.contains(element$iv)) {
               destination$iv.add(element$iv);
            }
         }

         return (Set)destination$iv;
      } else {
         LinkedHashSet result = new LinkedHashSet((Collection)$this$minus);
         result.removeAll(other);
         return (Set)result;
      }
   }

   @NotNull
   public static final Set minus(@NotNull Set $this$minus, @NotNull Sequence elements) {
      Intrinsics.checkNotNullParameter($this$minus, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      LinkedHashSet result = new LinkedHashSet((Collection)$this$minus);
      CollectionsKt.removeAll((Collection)result, elements);
      return (Set)result;
   }

   @InlineOnly
   private static final Set minusElement(Set $this$minusElement, Object element) {
      Intrinsics.checkNotNullParameter($this$minusElement, "<this>");
      return SetsKt.minus($this$minusElement, element);
   }

   @NotNull
   public static final Set plus(@NotNull Set $this$plus, Object element) {
      Intrinsics.checkNotNullParameter($this$plus, "<this>");
      LinkedHashSet result = new LinkedHashSet(MapsKt.mapCapacity($this$plus.size() + 1));
      result.addAll((Collection)$this$plus);
      result.add(element);
      return (Set)result;
   }

   @NotNull
   public static final Set plus(@NotNull Set $this$plus, @NotNull Object[] elements) {
      Intrinsics.checkNotNullParameter($this$plus, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      LinkedHashSet result = new LinkedHashSet(MapsKt.mapCapacity($this$plus.size() + elements.length));
      result.addAll((Collection)$this$plus);
      CollectionsKt.addAll((Collection)result, elements);
      return (Set)result;
   }

   @NotNull
   public static final Set plus(@NotNull Set $this$plus, @NotNull Iterable elements) {
      Intrinsics.checkNotNullParameter($this$plus, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      Integer var10000 = CollectionsKt.collectionSizeOrNull(elements);
      int var7;
      if (var10000 != null) {
         Integer var3 = var10000;
         int it = ((Number)var3).intValue();
         int var5 = 0;
         var7 = $this$plus.size() + it;
      } else {
         var7 = $this$plus.size() * 2;
      }

      int var6 = MapsKt.mapCapacity(var7);
      LinkedHashSet result = new LinkedHashSet(var6);
      result.addAll((Collection)$this$plus);
      CollectionsKt.addAll((Collection)result, elements);
      return (Set)result;
   }

   @NotNull
   public static final Set plus(@NotNull Set $this$plus, @NotNull Sequence elements) {
      Intrinsics.checkNotNullParameter($this$plus, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      LinkedHashSet result = new LinkedHashSet(MapsKt.mapCapacity($this$plus.size() * 2));
      result.addAll((Collection)$this$plus);
      CollectionsKt.addAll((Collection)result, elements);
      return (Set)result;
   }

   @InlineOnly
   private static final Set plusElement(Set $this$plusElement, Object element) {
      Intrinsics.checkNotNullParameter($this$plusElement, "<this>");
      return SetsKt.plus($this$plusElement, element);
   }

   public SetsKt___SetsKt() {
   }
}
