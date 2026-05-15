package kotlin.jvm.optionals;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u00002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001f\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0003\u001a+\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000*\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0002H\u0007¢\u0006\u0004\b\u0004\u0010\u0005\u001a.\u0010\u0007\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0001*\f\u0012\b\b\u0001\u0012\u0004\b\u00028\u00000\u00022\u0006\u0010\u0006\u001a\u00028\u0000H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u001aE\u0010\n\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0001*\f\u0012\b\b\u0001\u0012\u0004\b\u00028\u00000\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0087\bø\u0001\u0001ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0004\b\n\u0010\u000b\u001a%\u0010\f\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0007¢\u0006\u0004\b\f\u0010\r\u001a=\u0010\u0011\u001a\u00028\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\u0010\b\u0001\u0010\u000f*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u000e*\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0010\u001a\u00028\u0001H\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001a+\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013\"\b\b\u0000\u0010\u0001*\u00020\u0000*\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0002H\u0007¢\u0006\u0004\b\u0014\u0010\u0015\u001a+\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016\"\b\b\u0000\u0010\u0001*\u00020\u0000*\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0002H\u0007¢\u0006\u0004\b\u0017\u0010\u0018\u0082\u0002\u000b\n\u0002\b9\n\u0005\b\u009920\u0001¨\u0006\u0019"},
   d2 = {"", "T", "Ljava/util/Optional;", "Lkotlin/sequences/Sequence;", "asSequence", "(Ljava/util/Optional;)Lkotlin/sequences/Sequence;", "defaultValue", "getOrDefault", "(Ljava/util/Optional;Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlin/Function0;", "getOrElse", "(Ljava/util/Optional;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getOrNull", "(Ljava/util/Optional;)Ljava/lang/Object;", "", "C", "destination", "toCollection", "(Ljava/util/Optional;Ljava/util/Collection;)Ljava/util/Collection;", "", "toList", "(Ljava/util/Optional;)Ljava/util/List;", "", "toSet", "(Ljava/util/Optional;)Ljava/util/Set;", "kotlin-stdlib-jdk8"}
)
public final class OptionalsKt {
   @SinceKotlin(
      version = "1.8"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @Nullable
   public static final Object getOrNull(@NotNull Optional $this$getOrNull) {
      Intrinsics.checkNotNullParameter($this$getOrNull, "<this>");
      return $this$getOrNull.orElse((Object)null);
   }

   @SinceKotlin(
      version = "1.8"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   public static final Object getOrDefault(@NotNull Optional $this$getOrDefault, Object defaultValue) {
      Intrinsics.checkNotNullParameter($this$getOrDefault, "<this>");
      return $this$getOrDefault.isPresent() ? $this$getOrDefault.get() : defaultValue;
   }

   @SinceKotlin(
      version = "1.8"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   public static final Object getOrElse(@NotNull Optional $this$getOrElse, @NotNull Function0 defaultValue) {
      Intrinsics.checkNotNullParameter($this$getOrElse, "<this>");
      Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
      int $i$f$getOrElse = 0;
      return $this$getOrElse.isPresent() ? $this$getOrElse.get() : defaultValue.invoke();
   }

   @SinceKotlin(
      version = "1.8"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @NotNull
   public static final Collection toCollection(@NotNull Optional $this$toCollection, @NotNull Collection destination) {
      Intrinsics.checkNotNullParameter($this$toCollection, "<this>");
      Intrinsics.checkNotNullParameter(destination, "destination");
      if ($this$toCollection.isPresent()) {
         Object var10001 = $this$toCollection.get();
         Intrinsics.checkNotNullExpressionValue(var10001, "get(...)");
         destination.add(var10001);
      }

      return destination;
   }

   @SinceKotlin(
      version = "1.8"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @NotNull
   public static final List toList(@NotNull Optional $this$toList) {
      Intrinsics.checkNotNullParameter($this$toList, "<this>");
      return $this$toList.isPresent() ? CollectionsKt.listOf($this$toList.get()) : CollectionsKt.emptyList();
   }

   @SinceKotlin(
      version = "1.8"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @NotNull
   public static final Set toSet(@NotNull Optional $this$toSet) {
      Intrinsics.checkNotNullParameter($this$toSet, "<this>");
      return $this$toSet.isPresent() ? SetsKt.setOf($this$toSet.get()) : SetsKt.emptySet();
   }

   @SinceKotlin(
      version = "1.8"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @NotNull
   public static final Sequence asSequence(@NotNull Optional $this$asSequence) {
      Intrinsics.checkNotNullParameter($this$asSequence, "<this>");
      Sequence var10000;
      if ($this$asSequence.isPresent()) {
         Object[] var1 = new Object[]{$this$asSequence.get()};
         var10000 = SequencesKt.sequenceOf(var1);
      } else {
         var10000 = SequencesKt.emptySequence();
      }

      return var10000;
   }
}
