package kotlin.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.collections.builders.ListBuilder;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000X\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a)\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u0004\b\u0000\u0010\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001aF\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0007\u001a\u00020\u00062\u001d\u0010\u000b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0081\bø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a>\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u0004\b\u0000\u0010\u00002\u001d\u0010\u000b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0081\bø\u0001\u0000¢\u0006\u0004\b\f\u0010\u000e\u001a\u0018\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0081\b¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0018\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006H\u0081\b¢\u0006\u0004\b\u0013\u0010\u0011\u001a$\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u00162\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0081\b¢\u0006\u0004\b\u0018\u0010\u0019\u001a6\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016\"\u0004\b\u0000\u0010\u001a2\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u00142\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0081\b¢\u0006\u0004\b\u0018\u0010\u001c\u001a\u001b\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000H\u0001¢\u0006\u0004\b\u001d\u0010\u001e\u001a#\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0007\u001a\u00020\u0006H\u0001¢\u0006\u0004\b\u001d\u0010\u001f\u001a!\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u0004\b\u0000\u0010\u001a2\u0006\u0010 \u001a\u00028\u0000¢\u0006\u0004\b!\u0010\"\u001a1\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016\"\u0004\b\u0000\u0010\u001a2\u0006\u0010#\u001a\u00020\u00062\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0000¢\u0006\u0004\b$\u0010%\u001a3\u0010(\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00170\u0016\"\u0004\b\u0000\u0010\u001a*\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00162\u0006\u0010'\u001a\u00020&H\u0000¢\u0006\u0004\b(\u0010)\u001a%\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u0004\b\u0000\u0010\u001a*\b\u0012\u0004\u0012\u00028\u00000*H\u0007¢\u0006\u0004\b+\u0010,\u001a-\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u0004\b\u0000\u0010\u001a*\b\u0012\u0004\u0012\u00028\u00000*2\u0006\u0010.\u001a\u00020-H\u0007¢\u0006\u0004\b+\u0010/\u001a&\u00101\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u0004\b\u0000\u0010\u001a*\b\u0012\u0004\u0012\u00028\u000000H\u0087\b¢\u0006\u0004\b1\u00102\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u00063"},
   d2 = {"E", "", "builder", "", "build", "(Ljava/util/List;)Ljava/util/List;", "", "capacity", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "builderAction", "buildListInternal", "(ILkotlin/jvm/functions/Function1;)Ljava/util/List;", "(Lkotlin/jvm/functions/Function1;)Ljava/util/List;", "count", "checkCountOverflow", "(I)I", "index", "checkIndexOverflow", "", "collection", "", "", "collectionToArray", "(Ljava/util/Collection;)[Ljava/lang/Object;", "T", "array", "(Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;", "createListBuilder", "()Ljava/util/List;", "(I)Ljava/util/List;", "element", "listOf", "(Ljava/lang/Object;)Ljava/util/List;", "collectionSize", "terminateCollectionToArray", "(I[Ljava/lang/Object;)[Ljava/lang/Object;", "", "isVarargs", "copyToArrayOfAny", "([Ljava/lang/Object;Z)[Ljava/lang/Object;", "", "shuffled", "(Ljava/lang/Iterable;)Ljava/util/List;", "Ljava/util/Random;", "random", "(Ljava/lang/Iterable;Ljava/util/Random;)Ljava/util/List;", "Ljava/util/Enumeration;", "toList", "(Ljava/util/Enumeration;)Ljava/util/List;", "kotlin-stdlib"},
   xs = "kotlin/collections/CollectionsKt"
)
@SourceDebugExtension({"SMAP\nCollectionsJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CollectionsJVM.kt\nkotlin/collections/CollectionsKt__CollectionsJVMKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,131:1\n1#2:132\n*E\n"})
class CollectionsKt__CollectionsJVMKt {
   @NotNull
   public static final List listOf(Object element) {
      List var10000 = Collections.singletonList(element);
      Intrinsics.checkNotNullExpressionValue(var10000, "singletonList(...)");
      return var10000;
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final List buildListInternal(Function1 builderAction) {
      Intrinsics.checkNotNullParameter(builderAction, "builderAction");
      List var1 = CollectionsKt.createListBuilder();
      builderAction.invoke(var1);
      return CollectionsKt.build(var1);
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final List buildListInternal(int capacity, Function1 builderAction) {
      Intrinsics.checkNotNullParameter(builderAction, "builderAction");
      List var2 = CollectionsKt.createListBuilder(capacity);
      builderAction.invoke(var2);
      return CollectionsKt.build(var2);
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   @NotNull
   public static final List createListBuilder() {
      return new ListBuilder(0, 1, (DefaultConstructorMarker)null);
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   @NotNull
   public static final List createListBuilder(int capacity) {
      return new ListBuilder(capacity);
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   @NotNull
   public static final List build(@NotNull List builder) {
      Intrinsics.checkNotNullParameter(builder, "builder");
      return ((ListBuilder)builder).build();
   }

   @InlineOnly
   private static final List toList(Enumeration $this$toList) {
      Intrinsics.checkNotNullParameter($this$toList, "<this>");
      ArrayList var10000 = Collections.list($this$toList);
      Intrinsics.checkNotNullExpressionValue(var10000, "list(...)");
      return (List)var10000;
   }

   @SinceKotlin(
      version = "1.2"
   )
   @NotNull
   public static final List shuffled(@NotNull Iterable $this$shuffled) {
      Intrinsics.checkNotNullParameter($this$shuffled, "<this>");
      List $this$shuffled_u24lambda_u240 = CollectionsKt.toMutableList($this$shuffled);
      int var3 = 0;
      Collections.shuffle($this$shuffled_u24lambda_u240);
      return $this$shuffled_u24lambda_u240;
   }

   @SinceKotlin(
      version = "1.2"
   )
   @NotNull
   public static final List shuffled(@NotNull Iterable $this$shuffled, @NotNull Random random) {
      Intrinsics.checkNotNullParameter($this$shuffled, "<this>");
      Intrinsics.checkNotNullParameter(random, "random");
      List $this$shuffled_u24lambda_u241 = CollectionsKt.toMutableList($this$shuffled);
      int var4 = 0;
      Collections.shuffle($this$shuffled_u24lambda_u241, random);
      return $this$shuffled_u24lambda_u241;
   }

   @InlineOnly
   private static final Object[] collectionToArray(Collection collection) {
      Intrinsics.checkNotNullParameter(collection, "collection");
      return CollectionToArray.toArray(collection);
   }

   @InlineOnly
   private static final Object[] collectionToArray(Collection collection, Object[] array) {
      Intrinsics.checkNotNullParameter(collection, "collection");
      Intrinsics.checkNotNullParameter(array, "array");
      return CollectionToArray.toArray(collection, array);
   }

   @NotNull
   public static final Object[] terminateCollectionToArray(int collectionSize, @NotNull Object[] array) {
      Intrinsics.checkNotNullParameter(array, "array");
      if (collectionSize < array.length) {
         array[collectionSize] = null;
      }

      return array;
   }

   @NotNull
   public static final Object[] copyToArrayOfAny(@NotNull Object[] $this$copyToArrayOfAny, boolean isVarargs) {
      Intrinsics.checkNotNullParameter($this$copyToArrayOfAny, "<this>");
      Object[] var10000;
      if (isVarargs && Intrinsics.areEqual((Object)$this$copyToArrayOfAny.getClass(), (Object)Object[].class)) {
         var10000 = $this$copyToArrayOfAny;
      } else {
         var10000 = Arrays.copyOf($this$copyToArrayOfAny, $this$copyToArrayOfAny.length, Object[].class);
         Intrinsics.checkNotNullExpressionValue(var10000, "copyOf(...)");
      }

      return var10000;
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final int checkIndexOverflow(int index) {
      if (index < 0) {
         if (!PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            throw new ArithmeticException("Index overflow has happened.");
         }

         CollectionsKt.throwIndexOverflow();
      }

      return index;
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final int checkCountOverflow(int count) {
      if (count < 0) {
         if (!PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            throw new ArithmeticException("Count overflow has happened.");
         }

         CollectionsKt.throwCountOverflow();
      }

      return count;
   }

   public CollectionsKt__CollectionsJVMKt() {
   }
}
