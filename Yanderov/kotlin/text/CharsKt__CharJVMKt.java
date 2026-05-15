package kotlin.text;

import java.util.Locale;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u00008\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0017\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0001¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u001f\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0014\u0010\t\u001a\u00020\b*\u00020\u0004H\u0087\b¢\u0006\u0004\b\t\u0010\n\u001a\u0014\u0010\u000b\u001a\u00020\b*\u00020\u0004H\u0087\b¢\u0006\u0004\b\u000b\u0010\n\u001a\u0014\u0010\f\u001a\u00020\b*\u00020\u0004H\u0087\b¢\u0006\u0004\b\f\u0010\n\u001a\u0014\u0010\r\u001a\u00020\b*\u00020\u0004H\u0087\b¢\u0006\u0004\b\r\u0010\n\u001a\u0014\u0010\u000e\u001a\u00020\b*\u00020\u0004H\u0087\b¢\u0006\u0004\b\u000e\u0010\n\u001a\u0014\u0010\u000f\u001a\u00020\b*\u00020\u0004H\u0087\b¢\u0006\u0004\b\u000f\u0010\n\u001a\u0014\u0010\u0010\u001a\u00020\b*\u00020\u0004H\u0087\b¢\u0006\u0004\b\u0010\u0010\n\u001a\u0014\u0010\u0011\u001a\u00020\b*\u00020\u0004H\u0087\b¢\u0006\u0004\b\u0011\u0010\n\u001a\u0014\u0010\u0012\u001a\u00020\b*\u00020\u0004H\u0087\b¢\u0006\u0004\b\u0012\u0010\n\u001a\u0014\u0010\u0013\u001a\u00020\b*\u00020\u0004H\u0087\b¢\u0006\u0004\b\u0013\u0010\n\u001a\u0014\u0010\u0014\u001a\u00020\b*\u00020\u0004H\u0087\b¢\u0006\u0004\b\u0014\u0010\n\u001a\u0014\u0010\u0015\u001a\u00020\b*\u00020\u0004H\u0087\b¢\u0006\u0004\b\u0015\u0010\n\u001a\u0014\u0010\u0016\u001a\u00020\b*\u00020\u0004H\u0087\b¢\u0006\u0004\b\u0016\u0010\n\u001a\u0011\u0010\u0017\u001a\u00020\b*\u00020\u0004¢\u0006\u0004\b\u0017\u0010\n\u001a\u0014\u0010\u0019\u001a\u00020\u0018*\u00020\u0004H\u0087\b¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u001b\u0010\u0019\u001a\u00020\u0018*\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001bH\u0007¢\u0006\u0004\b\u0019\u0010\u001d\u001a\u0014\u0010\u001e\u001a\u00020\u0004*\u00020\u0004H\u0087\b¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u001b\u0010 \u001a\u00020\u0018*\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001bH\u0007¢\u0006\u0004\b \u0010\u001d\u001a\u0014\u0010!\u001a\u00020\u0004*\u00020\u0004H\u0087\b¢\u0006\u0004\b!\u0010\u001f\u001a\u0014\u0010\"\u001a\u00020\u0004*\u00020\u0004H\u0087\b¢\u0006\u0004\b\"\u0010\u001f\u001a\u0014\u0010#\u001a\u00020\u0004*\u00020\u0004H\u0087\b¢\u0006\u0004\b#\u0010\u001f\u001a\u0014\u0010$\u001a\u00020\u0004*\u00020\u0004H\u0087\b¢\u0006\u0004\b$\u0010\u001f\u001a\u0014\u0010%\u001a\u00020\u0018*\u00020\u0004H\u0087\b¢\u0006\u0004\b%\u0010\u001a\u001a\u001b\u0010%\u001a\u00020\u0018*\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001bH\u0007¢\u0006\u0004\b%\u0010\u001d\u001a\u0014\u0010&\u001a\u00020\u0004*\u00020\u0004H\u0087\b¢\u0006\u0004\b&\u0010\u001f\"\u0015\u0010*\u001a\u00020'*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b(\u0010)\"\u0015\u0010.\u001a\u00020+*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b,\u0010-¨\u0006/"},
   d2 = {"", "radix", "checkRadix", "(I)I", "", "char", "digitOf", "(CI)I", "", "isDefined", "(C)Z", "isDigit", "isHighSurrogate", "isISOControl", "isIdentifierIgnorable", "isJavaIdentifierPart", "isJavaIdentifierStart", "isLetter", "isLetterOrDigit", "isLowSurrogate", "isLowerCase", "isTitleCase", "isUpperCase", "isWhitespace", "", "lowercase", "(C)Ljava/lang/String;", "Ljava/util/Locale;", "locale", "(CLjava/util/Locale;)Ljava/lang/String;", "lowercaseChar", "(C)C", "titlecase", "titlecaseChar", "toLowerCase", "toTitleCase", "toUpperCase", "uppercase", "uppercaseChar", "Lkotlin/text/CharCategory;", "getCategory", "(C)Lkotlin/text/CharCategory;", "category", "Lkotlin/text/CharDirectionality;", "getDirectionality", "(C)Lkotlin/text/CharDirectionality;", "directionality", "kotlin-stdlib"},
   xs = "kotlin/text/CharsKt"
)
class CharsKt__CharJVMKt {
   @NotNull
   public static final CharCategory getCategory(char $this$category) {
      return CharCategory.Companion.valueOf(Character.getType($this$category));
   }

   @InlineOnly
   private static final boolean isDefined(char $this$isDefined) {
      return Character.isDefined($this$isDefined);
   }

   @InlineOnly
   private static final boolean isLetter(char $this$isLetter) {
      return Character.isLetter($this$isLetter);
   }

   @InlineOnly
   private static final boolean isLetterOrDigit(char $this$isLetterOrDigit) {
      return Character.isLetterOrDigit($this$isLetterOrDigit);
   }

   @InlineOnly
   private static final boolean isDigit(char $this$isDigit) {
      return Character.isDigit($this$isDigit);
   }

   @InlineOnly
   private static final boolean isIdentifierIgnorable(char $this$isIdentifierIgnorable) {
      return Character.isIdentifierIgnorable($this$isIdentifierIgnorable);
   }

   @InlineOnly
   private static final boolean isISOControl(char $this$isISOControl) {
      return Character.isISOControl($this$isISOControl);
   }

   @InlineOnly
   private static final boolean isJavaIdentifierPart(char $this$isJavaIdentifierPart) {
      return Character.isJavaIdentifierPart($this$isJavaIdentifierPart);
   }

   @InlineOnly
   private static final boolean isJavaIdentifierStart(char $this$isJavaIdentifierStart) {
      return Character.isJavaIdentifierStart($this$isJavaIdentifierStart);
   }

   public static final boolean isWhitespace(char $this$isWhitespace) {
      return Character.isWhitespace($this$isWhitespace) || Character.isSpaceChar($this$isWhitespace);
   }

   @InlineOnly
   private static final boolean isUpperCase(char $this$isUpperCase) {
      return Character.isUpperCase($this$isUpperCase);
   }

   @InlineOnly
   private static final boolean isLowerCase(char $this$isLowerCase) {
      return Character.isLowerCase($this$isLowerCase);
   }

   /** @deprecated */
   @Deprecated(
      message = "Use uppercaseChar() instead.",
      replaceWith = @ReplaceWith(
   expression = "uppercaseChar()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   @InlineOnly
   private static final char toUpperCase(char $this$toUpperCase) {
      return Character.toUpperCase($this$toUpperCase);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final char uppercaseChar(char $this$uppercaseChar) {
      return Character.toUpperCase($this$uppercaseChar);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final String uppercase(char $this$uppercase) {
      String var10000 = String.valueOf($this$uppercase);
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type java.lang.String");
      var10000 = var10000.toUpperCase(Locale.ROOT);
      Intrinsics.checkNotNullExpressionValue(var10000, "toUpperCase(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @NotNull
   public static final String uppercase(char $this$uppercase, @NotNull Locale locale) {
      Intrinsics.checkNotNullParameter(locale, "locale");
      String var10000 = String.valueOf($this$uppercase);
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type java.lang.String");
      var10000 = var10000.toUpperCase(locale);
      Intrinsics.checkNotNullExpressionValue(var10000, "toUpperCase(...)");
      return var10000;
   }

   /** @deprecated */
   @Deprecated(
      message = "Use lowercaseChar() instead.",
      replaceWith = @ReplaceWith(
   expression = "lowercaseChar()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   @InlineOnly
   private static final char toLowerCase(char $this$toLowerCase) {
      return Character.toLowerCase($this$toLowerCase);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final char lowercaseChar(char $this$lowercaseChar) {
      return Character.toLowerCase($this$lowercaseChar);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final String lowercase(char $this$lowercase) {
      String var10000 = String.valueOf($this$lowercase);
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type java.lang.String");
      var10000 = var10000.toLowerCase(Locale.ROOT);
      Intrinsics.checkNotNullExpressionValue(var10000, "toLowerCase(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @NotNull
   public static final String lowercase(char $this$lowercase, @NotNull Locale locale) {
      Intrinsics.checkNotNullParameter(locale, "locale");
      String var10000 = String.valueOf($this$lowercase);
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type java.lang.String");
      var10000 = var10000.toLowerCase(locale);
      Intrinsics.checkNotNullExpressionValue(var10000, "toLowerCase(...)");
      return var10000;
   }

   @InlineOnly
   private static final boolean isTitleCase(char $this$isTitleCase) {
      return Character.isTitleCase($this$isTitleCase);
   }

   /** @deprecated */
   @Deprecated(
      message = "Use titlecaseChar() instead.",
      replaceWith = @ReplaceWith(
   expression = "titlecaseChar()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   @InlineOnly
   private static final char toTitleCase(char $this$toTitleCase) {
      return Character.toTitleCase($this$toTitleCase);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final char titlecaseChar(char $this$titlecaseChar) {
      return Character.toTitleCase($this$titlecaseChar);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @NotNull
   public static final String titlecase(char $this$titlecase, @NotNull Locale locale) {
      Intrinsics.checkNotNullParameter(locale, "locale");
      String localizedUppercase = CharsKt.uppercase($this$titlecase, locale);
      if (localizedUppercase.length() > 1) {
         String var10000;
         if ($this$titlecase == 329) {
            var10000 = localizedUppercase;
         } else {
            char var3 = localizedUppercase.charAt(0);
            byte var5 = 1;
            Intrinsics.checkNotNull(localizedUppercase, "null cannot be cast to non-null type java.lang.String");
            var10000 = localizedUppercase.substring(var5);
            Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
            String var4 = var10000;
            Intrinsics.checkNotNull(var4, "null cannot be cast to non-null type java.lang.String");
            var10000 = var4.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(var10000, "toLowerCase(...)");
            var4 = var10000;
            var10000 = var3 + var4;
         }

         return var10000;
      } else {
         String var10001 = String.valueOf($this$titlecase);
         Intrinsics.checkNotNull(var10001, "null cannot be cast to non-null type java.lang.String");
         var10001 = var10001.toUpperCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var10001, "toUpperCase(...)");
         return !Intrinsics.areEqual((Object)localizedUppercase, (Object)var10001) ? localizedUppercase : String.valueOf(Character.toTitleCase($this$titlecase));
      }
   }

   @NotNull
   public static final CharDirectionality getDirectionality(char $this$directionality) {
      return CharDirectionality.Companion.valueOf(Character.getDirectionality($this$directionality));
   }

   @InlineOnly
   private static final boolean isHighSurrogate(char $this$isHighSurrogate) {
      return Character.isHighSurrogate($this$isHighSurrogate);
   }

   @InlineOnly
   private static final boolean isLowSurrogate(char $this$isLowSurrogate) {
      return Character.isLowSurrogate($this$isLowSurrogate);
   }

   public static final int digitOf(char char, int radix) {
      return Character.digit(char, radix);
   }

   @PublishedApi
   public static final int checkRadix(int radix) {
      if (!(2 <= radix ? radix < 37 : false)) {
         throw new IllegalArgumentException("radix " + radix + " was not in valid range " + new IntRange(2, 36));
      } else {
         return radix;
      }
   }

   public CharsKt__CharJVMKt() {
   }
}
