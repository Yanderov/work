package kotlin.collections;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000&\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001ao\u0010\b\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u000e\b\u0002\u0010\u0003*\b\u0012\u0004\u0012\u00028\u00020\u0002*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00042\u001e\u0010\u0007\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0004\u0012\u00028\u00020\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001ap\u0010\r\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000422\u0010\f\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00060\nj\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006`\u000bH\u0087\b¢\u0006\u0004\b\r\u0010\u000e\u001ao\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u000e\b\u0002\u0010\u0003*\b\u0012\u0004\u0012\u00028\u00020\u0002*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00042\u001e\u0010\u0007\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0004\u0012\u00028\u00020\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\t\u001ao\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000422\u0010\f\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00060\nj\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006`\u000bH\u0007¢\u0006\u0004\b\u0010\u0010\u000e\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0011"},
   d2 = {"K", "V", "", "R", "", "Lkotlin/Function1;", "", "selector", "maxBy", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map$Entry;", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "comparator", "maxWith", "(Ljava/util/Map;Ljava/util/Comparator;)Ljava/util/Map$Entry;", "minBy", "minWith", "kotlin-stdlib"},
   xs = "kotlin/collections/MapsKt"
)
class MapsKt___MapsJvmKt extends MapsKt__MapsKt {
   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use maxByOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxByOrNull(selector)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @InlineOnly
   private static final Map.Entry maxBy(Map $this$maxBy, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$maxBy, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      Iterable var2 = (Iterable)$this$maxBy.entrySet();
      Iterator var3 = var2.iterator();
      Object var10000;
      if (!var3.hasNext()) {
         var10000 = null;
      } else {
         Object var4 = var3.next();
         if (!var3.hasNext()) {
            var10000 = var4;
         } else {
            Comparable var5 = (Comparable)selector.invoke(var4);

            do {
               Object var6 = var3.next();
               Comparable var7 = (Comparable)selector.invoke(var6);
               if (var5.compareTo(var7) < 0) {
                  var4 = var6;
                  var5 = var7;
               }
            } while(var3.hasNext());

            var10000 = var4;
         }
      }

      return (Map.Entry)var10000;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use maxWithOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxWithOrNull(comparator)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   @InlineOnly
   private static final Map.Entry maxWith(Map $this$maxWith, Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$maxWith, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return (Map.Entry)CollectionsKt.maxWithOrNull((Iterable)$this$maxWith.entrySet(), comparator);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use minByOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minByOrNull(selector)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   public static final Map.Entry minBy(Map $this$minBy, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$minBy, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      int $i$f$minBy = 0;
      Iterable var3 = (Iterable)$this$minBy.entrySet();
      Iterator var4 = var3.iterator();
      Object var10000;
      if (!var4.hasNext()) {
         var10000 = null;
      } else {
         Object var5 = var4.next();
         if (!var4.hasNext()) {
            var10000 = var5;
         } else {
            Comparable var6 = (Comparable)selector.invoke(var5);

            do {
               Object var7 = var4.next();
               Comparable var8 = (Comparable)selector.invoke(var7);
               if (var6.compareTo(var8) > 0) {
                  var5 = var7;
                  var6 = var8;
               }
            } while(var4.hasNext());

            var10000 = var5;
         }
      }

      return (Map.Entry)var10000;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use minWithOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minWithOrNull(comparator)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.4",
      errorSince = "1.5",
      hiddenSince = "1.6"
   )
   public static final Map.Entry minWith(Map $this$minWith, Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$minWith, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return (Map.Entry)CollectionsKt.minWithOrNull((Iterable)$this$minWith.entrySet(), comparator);
   }

   public MapsKt___MapsJvmKt() {
   }
}
