package kotlin.text;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001c\n\u0002\b\u0003\u001a4\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\u0014\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\u0082\b¢\u0006\u0004\b\u0006\u0010\u0007\u001a%\u0010\r\u001a\u0004\u0018\u00010\f*\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\r\u0010\u000e\u001a\u001d\u0010\u000f\u001a\u0004\u0018\u00010\f*\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u0013\u0010\u0013\u001a\u00020\u0012*\u00020\u0011H\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u001b\u0010\u0013\u001a\u00020\u0012*\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0013\u0010\u0016\u001a\u0019\u0010\u0018\u001a\u00020\u0003*\b\u0012\u0004\u0012\u00020\u00000\u0017H\u0002¢\u0006\u0004\b\u0018\u0010\u0019¨\u0006\u001a"},
   d2 = {"Lkotlin/text/FlagEnum;", "", "T", "", "value", "", "fromInt", "(I)Ljava/util/Set;", "Ljava/util/regex/Matcher;", "from", "", "input", "Lkotlin/text/MatchResult;", "findNext", "(Ljava/util/regex/Matcher;ILjava/lang/CharSequence;)Lkotlin/text/MatchResult;", "matchEntire", "(Ljava/util/regex/Matcher;Ljava/lang/CharSequence;)Lkotlin/text/MatchResult;", "Ljava/util/regex/MatchResult;", "Lkotlin/ranges/IntRange;", "range", "(Ljava/util/regex/MatchResult;)Lkotlin/ranges/IntRange;", "groupIndex", "(Ljava/util/regex/MatchResult;I)Lkotlin/ranges/IntRange;", "", "toInt", "(Ljava/lang/Iterable;)I", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nRegex.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Regex.kt\nkotlin/text/RegexKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,397:1\n1797#2,3:398\n*S KotlinDebug\n*F\n+ 1 Regex.kt\nkotlin/text/RegexKt\n*L\n19#1:398,3\n*E\n"})
public final class RegexKt {
   private static final int toInt(Iterable $this$toInt) {
      int initial$iv = 0;
      int $i$f$fold = 0;
      int accumulator$iv = initial$iv;

      for(Object element$iv : $this$toInt) {
         FlagEnum option = (FlagEnum)element$iv;
         int var9 = 0;
         accumulator$iv |= option.getValue();
      }

      return accumulator$iv;
   }

   // $FF: synthetic method
   private static final Set fromInt(final int value) {
      int $i$f$fromInt = 0;
      Intrinsics.reifiedOperationMarker(4, "T");
      EnumSet var2 = EnumSet.allOf(Enum.class);
      EnumSet $this$fromInt_u24lambda_u241 = var2;
      int var4 = 0;
      Intrinsics.checkNotNull($this$fromInt_u24lambda_u241);
      Iterable var10000 = (Iterable)$this$fromInt_u24lambda_u241;
      Intrinsics.needClassReification();
      CollectionsKt.retainAll(var10000, new Function1(value) {
         public final Boolean invoke(Enum it) {
            return (value & ((FlagEnum)it).getMask()) == ((FlagEnum)it).getValue();
         }
      });
      Set var5 = Collections.unmodifiableSet((Set)var2);
      Intrinsics.checkNotNullExpressionValue(var5, "unmodifiableSet(...)");
      return var5;
   }

   private static final MatchResult findNext(Matcher $this$findNext, int from, CharSequence input) {
      return !$this$findNext.find(from) ? null : (MatchResult)(new MatcherMatchResult($this$findNext, input));
   }

   private static final MatchResult matchEntire(Matcher $this$matchEntire, CharSequence input) {
      return !$this$matchEntire.matches() ? null : (MatchResult)(new MatcherMatchResult($this$matchEntire, input));
   }

   private static final IntRange range(java.util.regex.MatchResult $this$range) {
      return RangesKt.until($this$range.start(), $this$range.end());
   }

   private static final IntRange range(java.util.regex.MatchResult $this$range, int groupIndex) {
      return RangesKt.until($this$range.start(groupIndex), $this$range.end(groupIndex));
   }

   // $FF: synthetic method
   public static final int access$toInt(Iterable $receiver) {
      return toInt($receiver);
   }

   // $FF: synthetic method
   public static final MatchResult access$findNext(Matcher $receiver, int from, CharSequence input) {
      return findNext($receiver, from, input);
   }

   // $FF: synthetic method
   public static final MatchResult access$matchEntire(Matcher $receiver, CharSequence input) {
      return matchEntire($receiver, input);
   }

   // $FF: synthetic method
   public static final IntRange access$range(java.util.regex.MatchResult $receiver) {
      return range($receiver);
   }

   // $FF: synthetic method
   public static final IntRange access$range(java.util.regex.MatchResult $receiver, int groupIndex) {
      return range($receiver, groupIndex);
   }
}
