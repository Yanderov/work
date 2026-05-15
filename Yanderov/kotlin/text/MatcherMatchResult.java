package kotlin.text;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.collections.CollectionsKt;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0011\u0010\b\u001a\u0004\u0018\u00010\u0001H\u0016¢\u0006\u0004\b\b\u0010\tR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u00118\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0016R\u0014\u0010\u001a\u001a\u00020\u00178BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001bR\u0014\u0010\u001f\u001a\u00020\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\"\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006#"},
   d2 = {"Lkotlin/text/MatcherMatchResult;", "Lkotlin/text/MatchResult;", "Ljava/util/regex/Matcher;", "matcher", "", "input", "<init>", "(Ljava/util/regex/Matcher;Ljava/lang/CharSequence;)V", "next", "()Lkotlin/text/MatchResult;", "", "", "getGroupValues", "()Ljava/util/List;", "groupValues", "groupValues_", "Ljava/util/List;", "Lkotlin/text/MatchGroupCollection;", "groups", "Lkotlin/text/MatchGroupCollection;", "getGroups", "()Lkotlin/text/MatchGroupCollection;", "Ljava/lang/CharSequence;", "Ljava/util/regex/MatchResult;", "getMatchResult", "()Ljava/util/regex/MatchResult;", "matchResult", "Ljava/util/regex/Matcher;", "Lkotlin/ranges/IntRange;", "getRange", "()Lkotlin/ranges/IntRange;", "range", "getValue", "()Ljava/lang/String;", "value", "kotlin-stdlib"}
)
final class MatcherMatchResult implements MatchResult {
   @NotNull
   private final Matcher matcher;
   @NotNull
   private final CharSequence input;
   @NotNull
   private final MatchGroupCollection groups;
   @Nullable
   private List groupValues_;

   public MatcherMatchResult(@NotNull Matcher matcher, @NotNull CharSequence input) {
      Intrinsics.checkNotNullParameter(matcher, "matcher");
      Intrinsics.checkNotNullParameter(input, "input");
      super();
      this.matcher = matcher;
      this.input = input;
      this.groups = new MatchNamedGroupCollection(this) {
         public int getSize() {
            return MatcherMatchResult.this.getMatchResult().groupCount() + 1;
         }

         public boolean isEmpty() {
            return false;
         }

         public Iterator iterator() {
            return SequencesKt.map(CollectionsKt.asSequence(CollectionsKt.getIndices(this)), new Function1(this) {
               public final MatchGroup invoke(int it) {
                  return get(it);
               }
            }).iterator();
         }

         public MatchGroup get(int index) {
            IntRange range = RegexKt.access$range(MatcherMatchResult.this.getMatchResult(), index);
            MatchGroup var10000;
            if (range.getStart() >= 0) {
               String var10002 = MatcherMatchResult.this.getMatchResult().group(index);
               Intrinsics.checkNotNullExpressionValue(var10002, "group(...)");
               var10000 = new MatchGroup(var10002, range);
            } else {
               var10000 = null;
            }

            return var10000;
         }

         public MatchGroup get(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            return PlatformImplementationsKt.IMPLEMENTATIONS.getMatchResultNamedGroup(MatcherMatchResult.this.getMatchResult(), name);
         }
      };
   }

   private final java.util.regex.MatchResult getMatchResult() {
      return (java.util.regex.MatchResult)this.matcher;
   }

   @NotNull
   public IntRange getRange() {
      return RegexKt.access$range(this.getMatchResult());
   }

   @NotNull
   public String getValue() {
      String var10000 = this.getMatchResult().group();
      Intrinsics.checkNotNullExpressionValue(var10000, "group(...)");
      return var10000;
   }

   @NotNull
   public MatchGroupCollection getGroups() {
      return this.groups;
   }

   @NotNull
   public List getGroupValues() {
      if (this.groupValues_ == null) {
         this.groupValues_ = new AbstractList(this) {
            public int getSize() {
               return MatcherMatchResult.this.getMatchResult().groupCount() + 1;
            }

            public String get(int index) {
               String var10000 = MatcherMatchResult.this.getMatchResult().group(index);
               if (var10000 == null) {
                  var10000 = "";
               }

               return var10000;
            }
         };
      }

      List var10000 = this.groupValues_;
      Intrinsics.checkNotNull(var10000);
      return var10000;
   }

   @Nullable
   public MatchResult next() {
      int nextIndex = this.getMatchResult().end() + (this.getMatchResult().end() == this.getMatchResult().start() ? 1 : 0);
      MatchResult var2;
      if (nextIndex <= this.input.length()) {
         Matcher var10000 = this.matcher.pattern().matcher(this.input);
         Intrinsics.checkNotNullExpressionValue(var10000, "matcher(...)");
         var2 = RegexKt.access$findNext(var10000, nextIndex, this.input);
      } else {
         var2 = null;
      }

      return var2;
   }

   @NotNull
   public MatchResult.Destructured getDestructured() {
      return MatchResult.DefaultImpls.getDestructured(this);
   }
}
