package kotlin.text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.ResultKt;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.WasExperimental;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u000b\u0018\u0000 =2\u00060\u0001j\u0002`\u0002:\u0002=>B\u0011\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0019\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\tB\u001f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\n¢\u0006\u0004\b\u0005\u0010\fB\u0011\b\u0001\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0005\u0010\u000fJ\u0015\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0013\u0010\u0014J!\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0018\u0010\u0019J%\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00170\u001a2\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u001b\u0010\u001cJ!\u0010\u001e\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u0015H\u0007¢\u0006\u0004\b\u001e\u0010\u0019J\u0017\u0010\u001f\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u001f\u0010 J\u0018\u0010!\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0086\u0004¢\u0006\u0004\b!\u0010\u0014J\u001f\u0010\"\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u0015H\u0007¢\u0006\u0004\b\"\u0010#J)\u0010&\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00102\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00100$¢\u0006\u0004\b&\u0010'J\u001d\u0010&\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010(\u001a\u00020\u0003¢\u0006\u0004\b&\u0010)J\u001d\u0010*\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010(\u001a\u00020\u0003¢\u0006\u0004\b*\u0010)J%\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00030,2\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010+\u001a\u00020\u0015¢\u0006\u0004\b-\u0010.J'\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00030\u001a2\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010+\u001a\u00020\u0015H\u0007¢\u0006\u0004\b/\u0010\u001cJ\r\u00100\u001a\u00020\r¢\u0006\u0004\b0\u00101J\u000f\u00102\u001a\u00020\u0003H\u0016¢\u0006\u0004\b2\u00103J\u000f\u00105\u001a\u000204H\u0002¢\u0006\u0004\b5\u00106R\u001e\u00107\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b7\u00108R\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u00109R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\n8F¢\u0006\u0006\u001a\u0004\b:\u0010;R\u0011\u0010\u0004\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b<\u00103¨\u0006?"},
   d2 = {"Lkotlin/text/Regex;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "", "pattern", "<init>", "(Ljava/lang/String;)V", "Lkotlin/text/RegexOption;", "option", "(Ljava/lang/String;Lkotlin/text/RegexOption;)V", "", "options", "(Ljava/lang/String;Ljava/util/Set;)V", "Ljava/util/regex/Pattern;", "nativePattern", "(Ljava/util/regex/Pattern;)V", "", "input", "", "containsMatchIn", "(Ljava/lang/CharSequence;)Z", "", "startIndex", "Lkotlin/text/MatchResult;", "find", "(Ljava/lang/CharSequence;I)Lkotlin/text/MatchResult;", "Lkotlin/sequences/Sequence;", "findAll", "(Ljava/lang/CharSequence;I)Lkotlin/sequences/Sequence;", "index", "matchAt", "matchEntire", "(Ljava/lang/CharSequence;)Lkotlin/text/MatchResult;", "matches", "matchesAt", "(Ljava/lang/CharSequence;I)Z", "Lkotlin/Function1;", "transform", "replace", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/String;", "replacement", "(Ljava/lang/CharSequence;Ljava/lang/String;)Ljava/lang/String;", "replaceFirst", "limit", "", "split", "(Ljava/lang/CharSequence;I)Ljava/util/List;", "splitToSequence", "toPattern", "()Ljava/util/regex/Pattern;", "toString", "()Ljava/lang/String;", "", "writeReplace", "()Ljava/lang/Object;", "_options", "Ljava/util/Set;", "Ljava/util/regex/Pattern;", "getOptions", "()Ljava/util/Set;", "getPattern", "Companion", "Serialized", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nRegex.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Regex.kt\nkotlin/text/Regex\n+ 2 Regex.kt\nkotlin/text/RegexKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,397:1\n22#2,3:398\n1#3:401\n*S KotlinDebug\n*F\n+ 1 Regex.kt\nkotlin/text/Regex\n*L\n103#1:398,3\n*E\n"})
public final class Regex implements Serializable {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private final Pattern nativePattern;
   @Nullable
   private Set _options;

   @PublishedApi
   public Regex(@NotNull Pattern nativePattern) {
      Intrinsics.checkNotNullParameter(nativePattern, "nativePattern");
      super();
      this.nativePattern = nativePattern;
   }

   public Regex(@NotNull String pattern) {
      Intrinsics.checkNotNullParameter(pattern, "pattern");
      Pattern var10001 = Pattern.compile(pattern);
      Intrinsics.checkNotNullExpressionValue(var10001, "compile(...)");
      this(var10001);
   }

   public Regex(@NotNull String pattern, @NotNull RegexOption option) {
      Intrinsics.checkNotNullParameter(pattern, "pattern");
      Intrinsics.checkNotNullParameter(option, "option");
      Pattern var10001 = Pattern.compile(pattern, Companion.ensureUnicodeCase(option.getValue()));
      Intrinsics.checkNotNullExpressionValue(var10001, "compile(...)");
      this(var10001);
   }

   public Regex(@NotNull String pattern, @NotNull Set options) {
      Intrinsics.checkNotNullParameter(pattern, "pattern");
      Intrinsics.checkNotNullParameter(options, "options");
      Pattern var10001 = Pattern.compile(pattern, Companion.ensureUnicodeCase(RegexKt.access$toInt((Iterable)options)));
      Intrinsics.checkNotNullExpressionValue(var10001, "compile(...)");
      this(var10001);
   }

   @NotNull
   public final String getPattern() {
      String var10000 = this.nativePattern.pattern();
      Intrinsics.checkNotNullExpressionValue(var10000, "pattern(...)");
      return var10000;
   }

   @NotNull
   public final Set getOptions() {
      Set var10000 = this._options;
      if (var10000 == null) {
         int value$iv = this.nativePattern.flags();
         int $i$f$fromInt = 0;
         EnumSet $this$fromInt_u24lambda_u241$iv = EnumSet.allOf(RegexOption.class);
         int var5 = 0;
         Intrinsics.checkNotNull($this$fromInt_u24lambda_u241$iv);
         CollectionsKt.retainAll((Iterable)$this$fromInt_u24lambda_u241$iv, new Regex$special$$inlined$fromInt$1(value$iv));
         var10000 = Collections.unmodifiableSet((Set)$this$fromInt_u24lambda_u241$iv);
         Intrinsics.checkNotNullExpressionValue(var10000, "unmodifiableSet(...)");
         Set it = var10000;
         int var7 = 0;
         this._options = it;
         var10000 = it;
      }

      return var10000;
   }

   public final boolean matches(@NotNull CharSequence input) {
      Intrinsics.checkNotNullParameter(input, "input");
      return this.nativePattern.matcher(input).matches();
   }

   public final boolean containsMatchIn(@NotNull CharSequence input) {
      Intrinsics.checkNotNullParameter(input, "input");
      return this.nativePattern.matcher(input).find();
   }

   @Nullable
   public final MatchResult find(@NotNull CharSequence input, int startIndex) {
      Intrinsics.checkNotNullParameter(input, "input");
      Matcher var10000 = this.nativePattern.matcher(input);
      Intrinsics.checkNotNullExpressionValue(var10000, "matcher(...)");
      return RegexKt.access$findNext(var10000, startIndex, input);
   }

   // $FF: synthetic method
   public static MatchResult find$default(Regex var0, CharSequence var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = 0;
      }

      return var0.find(var1, var2);
   }

   @NotNull
   public final Sequence findAll(@NotNull final CharSequence input, final int startIndex) {
      Intrinsics.checkNotNullParameter(input, "input");
      if (startIndex >= 0 && startIndex <= input.length()) {
         return SequencesKt.generateSequence(new Function0(this, input, startIndex) {
            public final MatchResult invoke() {
               return Regex.this.find(input, startIndex);
            }
         }, null.INSTANCE);
      } else {
         throw new IndexOutOfBoundsException("Start index out of bounds: " + startIndex + ", input length: " + input.length());
      }
   }

   // $FF: synthetic method
   public static Sequence findAll$default(Regex var0, CharSequence var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = 0;
      }

      return var0.findAll(var1, var2);
   }

   @Nullable
   public final MatchResult matchEntire(@NotNull CharSequence input) {
      Intrinsics.checkNotNullParameter(input, "input");
      Matcher var10000 = this.nativePattern.matcher(input);
      Intrinsics.checkNotNullExpressionValue(var10000, "matcher(...)");
      return RegexKt.access$matchEntire(var10000, input);
   }

   @SinceKotlin(
      version = "1.7"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @Nullable
   public final MatchResult matchAt(@NotNull CharSequence input, int index) {
      Intrinsics.checkNotNullParameter(input, "input");
      Matcher $this$matchAt_u24lambda_u241 = this.nativePattern.matcher(input).useAnchoringBounds(false).useTransparentBounds(true).region(index, input.length());
      int var5 = 0;
      MatcherMatchResult var10000;
      if ($this$matchAt_u24lambda_u241.lookingAt()) {
         Intrinsics.checkNotNull($this$matchAt_u24lambda_u241);
         var10000 = new MatcherMatchResult($this$matchAt_u24lambda_u241, input);
      } else {
         var10000 = null;
      }

      return var10000;
   }

   @SinceKotlin(
      version = "1.7"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   public final boolean matchesAt(@NotNull CharSequence input, int index) {
      Intrinsics.checkNotNullParameter(input, "input");
      return this.nativePattern.matcher(input).useAnchoringBounds(false).useTransparentBounds(true).region(index, input.length()).lookingAt();
   }

   @NotNull
   public final String replace(@NotNull CharSequence input, @NotNull String replacement) {
      Intrinsics.checkNotNullParameter(input, "input");
      Intrinsics.checkNotNullParameter(replacement, "replacement");
      String var10000 = this.nativePattern.matcher(input).replaceAll(replacement);
      Intrinsics.checkNotNullExpressionValue(var10000, "replaceAll(...)");
      return var10000;
   }

   @NotNull
   public final String replace(@NotNull CharSequence input, @NotNull Function1 transform) {
      Intrinsics.checkNotNullParameter(input, "input");
      Intrinsics.checkNotNullParameter(transform, "transform");
      MatchResult var10000 = find$default(this, input, 0, 2, (Object)null);
      if (var10000 == null) {
         return input.toString();
      } else {
         MatchResult match = var10000;
         int lastStart = 0;
         int length = input.length();
         StringBuilder sb = new StringBuilder(length);

         do {
            sb.append(input, lastStart, match.getRange().getStart());
            sb.append((CharSequence)transform.invoke(match));
            lastStart = match.getRange().getEndInclusive() + 1;
            match = match.next();
         } while(lastStart < length && match != null);

         if (lastStart < length) {
            sb.append(input, lastStart, length);
         }

         String var8 = sb.toString();
         Intrinsics.checkNotNullExpressionValue(var8, "toString(...)");
         return var8;
      }
   }

   @NotNull
   public final String replaceFirst(@NotNull CharSequence input, @NotNull String replacement) {
      Intrinsics.checkNotNullParameter(input, "input");
      Intrinsics.checkNotNullParameter(replacement, "replacement");
      String var10000 = this.nativePattern.matcher(input).replaceFirst(replacement);
      Intrinsics.checkNotNullExpressionValue(var10000, "replaceFirst(...)");
      return var10000;
   }

   @NotNull
   public final List split(@NotNull CharSequence input, int limit) {
      Intrinsics.checkNotNullParameter(input, "input");
      StringsKt.requireNonNegativeLimit(limit);
      Matcher matcher = this.nativePattern.matcher(input);
      if (limit != 1 && matcher.find()) {
         ArrayList result = new ArrayList(limit > 0 ? RangesKt.coerceAtMost(limit, 10) : 10);
         int lastStart = 0;
         int lastSplit = limit - 1;

         do {
            result.add(input.subSequence(lastStart, matcher.start()).toString());
            lastStart = matcher.end();
         } while((lastSplit < 0 || result.size() != lastSplit) && matcher.find());

         result.add(input.subSequence(lastStart, input.length()).toString());
         return (List)result;
      } else {
         return CollectionsKt.listOf(input.toString());
      }
   }

   // $FF: synthetic method
   public static List split$default(Regex var0, CharSequence var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = 0;
      }

      return var0.split(var1, var2);
   }

   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @NotNull
   public final Sequence splitToSequence(@NotNull final CharSequence input, final int limit) {
      Intrinsics.checkNotNullParameter(input, "input");
      StringsKt.requireNonNegativeLimit(limit);
      return SequencesKt.sequence(new Function2(this, input, limit, (Continuation)null) {
         Object L$1;
         int I$0;
         int label;
         // $FF: synthetic field
         private Object L$0;

         public final Object invokeSuspend(Object $result) {
            Object var6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            SequenceScope $this$sequence;
            Matcher matcher;
            int splitCount;
            switch (this.label) {
               case 0:
                  ResultKt.throwOnFailure($result);
                  $this$sequence = (SequenceScope)this.L$0;
                  matcher = Regex.this.nativePattern.matcher(input);
                  if (limit == 1 || !matcher.find()) {
                     String var10 = input.toString();
                     Continuation var13 = this;
                     this.label = 1;
                     if ($this$sequence.yield(var10, var13) == var6) {
                        return var6;
                     }

                     return Unit.INSTANCE;
                  }

                  int nextStart = 0;
                  splitCount = 0;
                  String var10001 = input.subSequence(nextStart, matcher.start()).toString();
                  Continuation var10002 = this;
                  this.L$0 = $this$sequence;
                  this.L$1 = matcher;
                  this.I$0 = splitCount;
                  this.label = 2;
                  if ($this$sequence.yield(var10001, var10002) == var6) {
                     return var6;
                  }
                  break;
               case 1:
                  ResultKt.throwOnFailure($result);
                  return Unit.INSTANCE;
               case 2:
                  splitCount = this.I$0;
                  matcher = (Matcher)this.L$1;
                  $this$sequence = (SequenceScope)this.L$0;
                  ResultKt.throwOnFailure($result);
                  break;
               case 3:
                  ResultKt.throwOnFailure($result);
                  return Unit.INSTANCE;
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            String var8;
            Continuation var11;
            do {
               int nextStart = matcher.end();
               ++splitCount;
               if (splitCount == limit - 1 || !matcher.find()) {
                  var8 = input.subSequence(nextStart, input.length()).toString();
                  var11 = this;
                  this.L$0 = null;
                  this.L$1 = null;
                  this.label = 3;
                  if ($this$sequence.yield(var8, var11) == var6) {
                     return var6;
                  }

                  return Unit.INSTANCE;
               }

               var8 = input.subSequence(nextStart, matcher.start()).toString();
               var11 = this;
               this.L$0 = $this$sequence;
               this.L$1 = matcher;
               this.I$0 = splitCount;
               this.label = 2;
            } while($this$sequence.yield(var8, var11) != var6);

            return var6;
         }

         public final Continuation create(Object value, Continuation $completion) {
            Function2 var3 = new <anonymous constructor>(Regex.this, input, limit, $completion);
            var3.L$0 = value;
            return (Continuation)var3;
         }

         public final Object invoke(SequenceScope p1, Continuation p2) {
            return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
         }
      });
   }

   // $FF: synthetic method
   public static Sequence splitToSequence$default(Regex var0, CharSequence var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = 0;
      }

      return var0.splitToSequence(var1, var2);
   }

   @NotNull
   public String toString() {
      String var10000 = this.nativePattern.toString();
      Intrinsics.checkNotNullExpressionValue(var10000, "toString(...)");
      return var10000;
   }

   @NotNull
   public final Pattern toPattern() {
      return this.nativePattern;
   }

   private final Object writeReplace() {
      String var10002 = this.nativePattern.pattern();
      Intrinsics.checkNotNullExpressionValue(var10002, "pattern(...)");
      return new Serialized(var10002, this.nativePattern.flags());
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\n\b\u0002\u0018\u0000 \u00122\u00060\u0001j\u0002`\u0002:\u0001\u0012B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0013"},
      d2 = {"Lkotlin/text/Regex$Serialized;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "", "pattern", "", "flags", "<init>", "(Ljava/lang/String;I)V", "", "readResolve", "()Ljava/lang/Object;", "I", "getFlags", "()I", "Ljava/lang/String;", "getPattern", "()Ljava/lang/String;", "Companion", "kotlin-stdlib"}
   )
   private static final class Serialized implements Serializable {
      @NotNull
      public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
      @NotNull
      private final String pattern;
      private final int flags;
      private static final long serialVersionUID = 0L;

      public Serialized(@NotNull String pattern, int flags) {
         Intrinsics.checkNotNullParameter(pattern, "pattern");
         super();
         this.pattern = pattern;
         this.flags = flags;
      }

      @NotNull
      public final String getPattern() {
         return this.pattern;
      }

      public final int getFlags() {
         return this.flags;
      }

      private final Object readResolve() {
         Pattern var10002 = Pattern.compile(this.pattern, this.flags);
         Intrinsics.checkNotNullExpressionValue(var10002, "compile(...)");
         return new Regex(var10002);
      }

      @Metadata(
         mv = {1, 9, 0},
         k = 1,
         xi = 48,
         d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
         d2 = {"Lkotlin/text/Regex$Serialized$Companion;", "", "<init>", "()V", "", "serialVersionUID", "J", "kotlin-stdlib"}
      )
      public static final class Companion {
         private Companion() {
         }

         // $FF: synthetic method
         public Companion(DefaultConstructorMarker $constructor_marker) {
            this();
         }
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\f\u0010\u000bJ\u0015\u0010\u000e\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"},
      d2 = {"Lkotlin/text/Regex$Companion;", "", "<init>", "()V", "", "flags", "ensureUnicodeCase", "(I)I", "", "literal", "escape", "(Ljava/lang/String;)Ljava/lang/String;", "escapeReplacement", "Lkotlin/text/Regex;", "fromLiteral", "(Ljava/lang/String;)Lkotlin/text/Regex;", "kotlin-stdlib"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final Regex fromLiteral(@NotNull String literal) {
         Intrinsics.checkNotNullParameter(literal, "literal");
         return new Regex(literal, RegexOption.LITERAL);
      }

      @NotNull
      public final String escape(@NotNull String literal) {
         Intrinsics.checkNotNullParameter(literal, "literal");
         String var10000 = Pattern.quote(literal);
         Intrinsics.checkNotNullExpressionValue(var10000, "quote(...)");
         return var10000;
      }

      @NotNull
      public final String escapeReplacement(@NotNull String literal) {
         Intrinsics.checkNotNullParameter(literal, "literal");
         String var10000 = Matcher.quoteReplacement(literal);
         Intrinsics.checkNotNullExpressionValue(var10000, "quoteReplacement(...)");
         return var10000;
      }

      private final int ensureUnicodeCase(int flags) {
         return (flags & 2) != 0 ? flags | 64 : flags;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
