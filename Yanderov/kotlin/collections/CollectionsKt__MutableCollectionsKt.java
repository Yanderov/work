package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000T\n\u0000\n\u0002\u0010\u001f\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u001d\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0010\u001a/\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00012\u000e\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a-\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\u0005\u0010\b\u001a-\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0004\b\u0005\u0010\n\u001a%\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0000¢\u0006\u0004\b\f\u0010\r\u001a;\u0010\u0014\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u000f2\u0006\u0010\u0011\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0012\u0010\u0013\u001a;\u0010\u0014\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00152\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u000f2\u0006\u0010\u0011\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0012\u0010\u0016\u001a*\u0010\u0019\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00012\u0006\u0010\u0017\u001a\u00028\u0000H\u0087\n¢\u0006\u0004\b\u0019\u0010\u001a\u001a0\u0010\u0019\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0087\n¢\u0006\u0004\b\u0019\u0010\u001b\u001a0\u0010\u0019\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0087\n¢\u0006\u0004\b\u0019\u0010\u001c\u001a0\u0010\u0019\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0087\n¢\u0006\u0004\b\u0019\u0010\u001d\u001a*\u0010\u001e\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00012\u0006\u0010\u0017\u001a\u00028\u0000H\u0087\n¢\u0006\u0004\b\u001e\u0010\u001a\u001a0\u0010\u001e\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0087\n¢\u0006\u0004\b\u001e\u0010\u001b\u001a0\u0010\u001e\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0087\n¢\u0006\u0004\b\u001e\u0010\u001c\u001a0\u0010\u001e\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0087\n¢\u0006\u0004\b\u001e\u0010\u001d\u001a/\u0010 \u001a\u00020\u0004\"\t\b\u0000\u0010\u0000¢\u0006\u0002\b\u001f*\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00012\u0006\u0010\u0017\u001a\u00028\u0000H\u0087\b¢\u0006\u0004\b \u0010!\u001a(\u0010 \u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00152\u0006\u0010#\u001a\u00020\"H\u0087\b¢\u0006\u0004\b \u0010$\u001a/\u0010%\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00012\u000e\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0002¢\u0006\u0004\b%\u0010\u0006\u001a-\u0010%\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b%\u0010\b\u001a-\u0010%\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0004\b%\u0010\n\u001a5\u0010%\u001a\u00020\u0004\"\t\b\u0000\u0010\u0000¢\u0006\u0002\b\u001f*\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0087\b¢\u0006\u0004\b%\u0010&\u001a1\u0010%\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u000f¢\u0006\u0004\b%\u0010'\u001a1\u0010%\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00152\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u000f¢\u0006\u0004\b%\u0010(\u001a\u001f\u0010)\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0007¢\u0006\u0004\b)\u0010*\u001a!\u0010+\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0007¢\u0006\u0004\b+\u0010*\u001a\u001f\u0010,\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0007¢\u0006\u0004\b,\u0010*\u001a!\u0010-\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0007¢\u0006\u0004\b-\u0010*\u001a/\u0010.\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00012\u000e\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0002¢\u0006\u0004\b.\u0010\u0006\u001a-\u0010.\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b.\u0010\b\u001a-\u0010.\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0004\b.\u0010\n\u001a5\u0010.\u001a\u00020\u0004\"\t\b\u0000\u0010\u0000¢\u0006\u0002\b\u001f*\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0087\b¢\u0006\u0004\b.\u0010&\u001a1\u0010.\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u000f¢\u0006\u0004\b.\u0010'\u001a1\u0010.\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00152\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u000f¢\u0006\u0004\b.\u0010(\u001a\u0017\u00101\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u0001H\u0002¢\u0006\u0004\b/\u00100¨\u00062"},
   d2 = {"T", "", "", "elements", "", "addAll", "(Ljava/util/Collection;[Ljava/lang/Object;)Z", "", "(Ljava/util/Collection;Ljava/lang/Iterable;)Z", "Lkotlin/sequences/Sequence;", "(Ljava/util/Collection;Lkotlin/sequences/Sequence;)Z", "", "convertToListIfNotCollection", "(Ljava/lang/Iterable;)Ljava/util/Collection;", "", "Lkotlin/Function1;", "predicate", "predicateResultToRemove", "filterInPlace$CollectionsKt__MutableCollectionsKt", "(Ljava/lang/Iterable;Lkotlin/jvm/functions/Function1;Z)Z", "filterInPlace", "", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;Z)Z", "element", "", "minusAssign", "(Ljava/util/Collection;Ljava/lang/Object;)V", "(Ljava/util/Collection;[Ljava/lang/Object;)V", "(Ljava/util/Collection;Ljava/lang/Iterable;)V", "(Ljava/util/Collection;Lkotlin/sequences/Sequence;)V", "plusAssign", "Lkotlin/internal/OnlyInputTypes;", "remove", "(Ljava/util/Collection;Ljava/lang/Object;)Z", "", "index", "(Ljava/util/List;I)Ljava/lang/Object;", "removeAll", "(Ljava/util/Collection;Ljava/util/Collection;)Z", "(Ljava/lang/Iterable;Lkotlin/jvm/functions/Function1;)Z", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)Z", "removeFirst", "(Ljava/util/List;)Ljava/lang/Object;", "removeFirstOrNull", "removeLast", "removeLastOrNull", "retainAll", "retainNothing$CollectionsKt__MutableCollectionsKt", "(Ljava/util/Collection;)Z", "retainNothing", "kotlin-stdlib"},
   xs = "kotlin/collections/CollectionsKt"
)
class CollectionsKt__MutableCollectionsKt extends CollectionsKt__MutableCollectionsJVMKt {
   @InlineOnly
   private static final boolean remove(Collection $this$remove, Object element) {
      Intrinsics.checkNotNullParameter($this$remove, "<this>");
      return TypeIntrinsics.asMutableCollection($this$remove).remove(element);
   }

   @InlineOnly
   private static final boolean removeAll(Collection $this$removeAll, Collection elements) {
      Intrinsics.checkNotNullParameter($this$removeAll, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      return TypeIntrinsics.asMutableCollection($this$removeAll).removeAll(elements);
   }

   @InlineOnly
   private static final boolean retainAll(Collection $this$retainAll, Collection elements) {
      Intrinsics.checkNotNullParameter($this$retainAll, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      return TypeIntrinsics.asMutableCollection($this$retainAll).retainAll(elements);
   }

   @InlineOnly
   private static final void plusAssign(Collection $this$plusAssign, Object element) {
      Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
      $this$plusAssign.add(element);
   }

   @InlineOnly
   private static final void plusAssign(Collection $this$plusAssign, Iterable elements) {
      Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      CollectionsKt.addAll($this$plusAssign, elements);
   }

   @InlineOnly
   private static final void plusAssign(Collection $this$plusAssign, Object[] elements) {
      Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      CollectionsKt.addAll($this$plusAssign, elements);
   }

   @InlineOnly
   private static final void plusAssign(Collection $this$plusAssign, Sequence elements) {
      Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      CollectionsKt.addAll($this$plusAssign, elements);
   }

   @InlineOnly
   private static final void minusAssign(Collection $this$minusAssign, Object element) {
      Intrinsics.checkNotNullParameter($this$minusAssign, "<this>");
      $this$minusAssign.remove(element);
   }

   @InlineOnly
   private static final void minusAssign(Collection $this$minusAssign, Iterable elements) {
      Intrinsics.checkNotNullParameter($this$minusAssign, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      CollectionsKt.removeAll($this$minusAssign, elements);
   }

   @InlineOnly
   private static final void minusAssign(Collection $this$minusAssign, Object[] elements) {
      Intrinsics.checkNotNullParameter($this$minusAssign, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      CollectionsKt.removeAll($this$minusAssign, elements);
   }

   @InlineOnly
   private static final void minusAssign(Collection $this$minusAssign, Sequence elements) {
      Intrinsics.checkNotNullParameter($this$minusAssign, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      CollectionsKt.removeAll($this$minusAssign, elements);
   }

   public static final boolean addAll(@NotNull Collection $this$addAll, @NotNull Iterable elements) {
      Intrinsics.checkNotNullParameter($this$addAll, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      if (elements instanceof Collection) {
         return $this$addAll.addAll((Collection)elements);
      } else {
         boolean result = false;

         for(Object item : elements) {
            if ($this$addAll.add(item)) {
               result = true;
            }
         }

         return result;
      }
   }

   public static final boolean addAll(@NotNull Collection $this$addAll, @NotNull Sequence elements) {
      Intrinsics.checkNotNullParameter($this$addAll, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      boolean result = false;

      for(Object item : elements) {
         if ($this$addAll.add(item)) {
            result = true;
         }
      }

      return result;
   }

   public static final boolean addAll(@NotNull Collection $this$addAll, @NotNull Object[] elements) {
      Intrinsics.checkNotNullParameter($this$addAll, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      return $this$addAll.addAll((Collection)ArraysKt.asList(elements));
   }

   @NotNull
   public static final Collection convertToListIfNotCollection(@NotNull Iterable $this$convertToListIfNotCollection) {
      Intrinsics.checkNotNullParameter($this$convertToListIfNotCollection, "<this>");
      return $this$convertToListIfNotCollection instanceof Collection ? (Collection)$this$convertToListIfNotCollection : (Collection)CollectionsKt.toList($this$convertToListIfNotCollection);
   }

   public static final boolean removeAll(@NotNull Collection $this$removeAll, @NotNull Iterable elements) {
      Intrinsics.checkNotNullParameter($this$removeAll, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      return $this$removeAll.removeAll(CollectionsKt.convertToListIfNotCollection(elements));
   }

   public static final boolean removeAll(@NotNull Collection $this$removeAll, @NotNull Sequence elements) {
      Intrinsics.checkNotNullParameter($this$removeAll, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      List list = SequencesKt.toList(elements);
      return !((Collection)list).isEmpty() && $this$removeAll.removeAll((Collection)list);
   }

   public static final boolean removeAll(@NotNull Collection $this$removeAll, @NotNull Object[] elements) {
      Intrinsics.checkNotNullParameter($this$removeAll, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      return elements.length != 0 && $this$removeAll.removeAll((Collection)ArraysKt.asList(elements));
   }

   public static final boolean retainAll(@NotNull Collection $this$retainAll, @NotNull Iterable elements) {
      Intrinsics.checkNotNullParameter($this$retainAll, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      return $this$retainAll.retainAll(CollectionsKt.convertToListIfNotCollection(elements));
   }

   public static final boolean retainAll(@NotNull Collection $this$retainAll, @NotNull Object[] elements) {
      Intrinsics.checkNotNullParameter($this$retainAll, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      return elements.length != 0 ? $this$retainAll.retainAll((Collection)ArraysKt.asList(elements)) : retainNothing$CollectionsKt__MutableCollectionsKt($this$retainAll);
   }

   public static final boolean retainAll(@NotNull Collection $this$retainAll, @NotNull Sequence elements) {
      Intrinsics.checkNotNullParameter($this$retainAll, "<this>");
      Intrinsics.checkNotNullParameter(elements, "elements");
      List list = SequencesKt.toList(elements);
      return !((Collection)list).isEmpty() ? $this$retainAll.retainAll((Collection)list) : retainNothing$CollectionsKt__MutableCollectionsKt($this$retainAll);
   }

   private static final boolean retainNothing$CollectionsKt__MutableCollectionsKt(Collection $this$retainNothing) {
      boolean result = !$this$retainNothing.isEmpty();
      $this$retainNothing.clear();
      return result;
   }

   public static final boolean removeAll(@NotNull Iterable $this$removeAll, @NotNull Function1 predicate) {
      Intrinsics.checkNotNullParameter($this$removeAll, "<this>");
      Intrinsics.checkNotNullParameter(predicate, "predicate");
      return filterInPlace$CollectionsKt__MutableCollectionsKt($this$removeAll, predicate, true);
   }

   public static final boolean retainAll(@NotNull Iterable $this$retainAll, @NotNull Function1 predicate) {
      Intrinsics.checkNotNullParameter($this$retainAll, "<this>");
      Intrinsics.checkNotNullParameter(predicate, "predicate");
      return filterInPlace$CollectionsKt__MutableCollectionsKt($this$retainAll, predicate, false);
   }

   private static final boolean filterInPlace$CollectionsKt__MutableCollectionsKt(Iterable $this$filterInPlace, Function1 predicate, boolean predicateResultToRemove) {
      boolean result = false;
      Iterator var4 = $this$filterInPlace.iterator();
      Iterator $this$filterInPlace_u24lambda_u240 = var4;
      int var6 = 0;

      while($this$filterInPlace_u24lambda_u240.hasNext()) {
         if ((Boolean)predicate.invoke($this$filterInPlace_u24lambda_u240.next()) == predicateResultToRemove) {
            $this$filterInPlace_u24lambda_u240.remove();
            result = true;
         }
      }

      return result;
   }

   /** @deprecated */
   @Deprecated(
      message = "Use removeAt(index) instead.",
      replaceWith = @ReplaceWith(
   expression = "removeAt(index)",
   imports = {}
),
      level = DeprecationLevel.ERROR
   )
   @InlineOnly
   private static final Object remove(List $this$remove, int index) {
      Intrinsics.checkNotNullParameter($this$remove, "<this>");
      return $this$remove.remove(index);
   }

   @SinceKotlin(
      version = "1.4"
   )
   public static final Object removeFirst(@NotNull List $this$removeFirst) {
      Intrinsics.checkNotNullParameter($this$removeFirst, "<this>");
      if ($this$removeFirst.isEmpty()) {
         throw new NoSuchElementException("List is empty.");
      } else {
         return $this$removeFirst.remove(0);
      }
   }

   @SinceKotlin(
      version = "1.4"
   )
   @Nullable
   public static final Object removeFirstOrNull(@NotNull List $this$removeFirstOrNull) {
      Intrinsics.checkNotNullParameter($this$removeFirstOrNull, "<this>");
      return $this$removeFirstOrNull.isEmpty() ? null : $this$removeFirstOrNull.remove(0);
   }

   @SinceKotlin(
      version = "1.4"
   )
   public static final Object removeLast(@NotNull List $this$removeLast) {
      Intrinsics.checkNotNullParameter($this$removeLast, "<this>");
      if ($this$removeLast.isEmpty()) {
         throw new NoSuchElementException("List is empty.");
      } else {
         return $this$removeLast.remove(CollectionsKt.getLastIndex($this$removeLast));
      }
   }

   @SinceKotlin(
      version = "1.4"
   )
   @Nullable
   public static final Object removeLastOrNull(@NotNull List $this$removeLastOrNull) {
      Intrinsics.checkNotNullParameter($this$removeLastOrNull, "<this>");
      return $this$removeLastOrNull.isEmpty() ? null : $this$removeLastOrNull.remove(CollectionsKt.getLastIndex($this$removeLastOrNull));
   }

   public static final boolean removeAll(@NotNull List $this$removeAll, @NotNull Function1 predicate) {
      Intrinsics.checkNotNullParameter($this$removeAll, "<this>");
      Intrinsics.checkNotNullParameter(predicate, "predicate");
      return filterInPlace$CollectionsKt__MutableCollectionsKt($this$removeAll, predicate, true);
   }

   public static final boolean retainAll(@NotNull List $this$retainAll, @NotNull Function1 predicate) {
      Intrinsics.checkNotNullParameter($this$retainAll, "<this>");
      Intrinsics.checkNotNullParameter(predicate, "predicate");
      return filterInPlace$CollectionsKt__MutableCollectionsKt($this$retainAll, predicate, false);
   }

   private static final boolean filterInPlace$CollectionsKt__MutableCollectionsKt(List $this$filterInPlace, Function1 predicate, boolean predicateResultToRemove) {
      if (!($this$filterInPlace instanceof RandomAccess)) {
         Intrinsics.checkNotNull($this$filterInPlace, "null cannot be cast to non-null type kotlin.collections.MutableIterable<T of kotlin.collections.CollectionsKt__MutableCollectionsKt.filterInPlace>");
         return filterInPlace$CollectionsKt__MutableCollectionsKt(TypeIntrinsics.asMutableIterable($this$filterInPlace), predicate, predicateResultToRemove);
      } else {
         int writeIndex = 0;
         int readIndex = 0;
         int var5 = CollectionsKt.getLastIndex($this$filterInPlace);
         if (readIndex <= var5) {
            while(true) {
               Object element = $this$filterInPlace.get(readIndex);
               if ((Boolean)predicate.invoke(element) != predicateResultToRemove) {
                  if (writeIndex != readIndex) {
                     $this$filterInPlace.set(writeIndex, element);
                  }

                  ++writeIndex;
               }

               if (readIndex == var5) {
                  break;
               }

               ++readIndex;
            }
         }

         if (writeIndex >= $this$filterInPlace.size()) {
            return false;
         } else {
            readIndex = CollectionsKt.getLastIndex($this$filterInPlace);
            var5 = writeIndex;
            if (writeIndex <= readIndex) {
               while(true) {
                  $this$filterInPlace.remove(readIndex);
                  if (readIndex == var5) {
                     break;
                  }

                  --readIndex;
               }
            }

            return true;
         }
      }
   }

   public CollectionsKt__MutableCollectionsKt() {
   }
}
