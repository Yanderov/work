package kotlin.text;

import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b/\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\t\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u000b\u0010\u0006R\u0014\u0010\f\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\r\u0010\u0006R\u0014\u0010\u000e\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u000f\u0010\u0006R\u0014\u0010\u0010\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0011\u0010\u0006R\u0014\u0010\u0012\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0013\u0010\u0006R\u0014\u0010\u0014\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0014\u0010\u0006R\u0014\u0010\u0015\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0015\u0010\u0006R\u001a\u0010\u0016\u001a\u00020\u00048\u0006X\u0087T¢\u0006\f\n\u0004\b\u0016\u0010\u0006\u0012\u0004\b\u0017\u0010\u0003R\u001a\u0010\u0018\u001a\u00020\u00048\u0006X\u0087T¢\u0006\f\n\u0004\b\u0018\u0010\u0006\u0012\u0004\b\u0019\u0010\u0003R\u0014\u0010\u001a\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001a\u0010\u0006R\u0014\u0010\u001b\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001b\u0010\u0006R\u0014\u0010\u001c\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001c\u0010\u0006R\u0014\u0010\u001d\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001d\u0010\u0006R\u0014\u0010\u001e\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001e\u0010\u0006R\u0014\u0010\u001f\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001f\u0010\u0006R\u0014\u0010 \u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b \u0010\u0006R\u0014\u0010!\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b!\u0010\u0006R\u0014\u0010\"\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\"\u0010\u0006R\u0014\u0010#\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b#\u0010\u0006R\u0014\u0010$\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b$\u0010\u0006R\u0014\u0010%\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b%\u0010\u0006R\u0014\u0010&\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b&\u0010\u0006R\u0014\u0010'\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b'\u0010\u0006R\u0014\u0010(\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b(\u0010\u0006R\u0014\u0010)\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b)\u0010\u0006R\u0014\u0010*\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b*\u0010\u0006R\u001a\u0010+\u001a\u00020\u00048\u0006X\u0087T¢\u0006\f\n\u0004\b+\u0010\u0006\u0012\u0004\b,\u0010\u0003R\u001a\u0010-\u001a\u00020\u00048\u0006X\u0087T¢\u0006\f\n\u0004\b-\u0010\u0006\u0012\u0004\b.\u0010\u0003R\u0014\u0010/\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b/\u0010\u0006R\u0014\u00100\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b0\u0010\u0006R\u0014\u00101\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b1\u0010\u0006R\u0014\u00102\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b2\u0010\u0006¨\u00063"},
   d2 = {"Lkotlin/text/Typography;", "", "<init>", "()V", "", "almostEqual", "C", "amp", "bullet", "cent", "copyright", "dagger", "degree", "dollar", "doubleDagger", "doublePrime", "ellipsis", "euro", "greater", "greaterOrEqual", "half", "leftDoubleQuote", "leftGuillemet", "getLeftGuillemet$annotations", "leftGuillemete", "getLeftGuillemete$annotations", "leftSingleQuote", "less", "lessOrEqual", "lowDoubleQuote", "lowSingleQuote", "mdash", "middleDot", "nbsp", "ndash", "notEqual", "paragraph", "plusMinus", "pound", "prime", "quote", "registered", "rightDoubleQuote", "rightGuillemet", "getRightGuillemet$annotations", "rightGuillemete", "getRightGuillemete$annotations", "rightSingleQuote", "section", "times", "tm", "kotlin-stdlib"}
)
public final class Typography {
   @NotNull
   public static final Typography INSTANCE = new Typography();
   public static final char quote = '"';
   public static final char dollar = '$';
   public static final char amp = '&';
   public static final char less = '<';
   public static final char greater = '>';
   public static final char nbsp = ' ';
   public static final char times = '×';
   public static final char cent = '¢';
   public static final char pound = '£';
   public static final char section = '§';
   public static final char copyright = '©';
   public static final char leftGuillemet = '«';
   public static final char rightGuillemet = '»';
   public static final char registered = '®';
   public static final char degree = '°';
   public static final char plusMinus = '±';
   public static final char paragraph = '¶';
   public static final char middleDot = '·';
   public static final char half = '½';
   public static final char ndash = '–';
   public static final char mdash = '—';
   public static final char leftSingleQuote = '‘';
   public static final char rightSingleQuote = '’';
   public static final char lowSingleQuote = '‚';
   public static final char leftDoubleQuote = '“';
   public static final char rightDoubleQuote = '”';
   public static final char lowDoubleQuote = '„';
   public static final char dagger = '†';
   public static final char doubleDagger = '‡';
   public static final char bullet = '•';
   public static final char ellipsis = '…';
   public static final char prime = '′';
   public static final char doublePrime = '″';
   public static final char euro = '€';
   public static final char tm = '™';
   public static final char almostEqual = '≈';
   public static final char notEqual = '≠';
   public static final char lessOrEqual = '≤';
   public static final char greaterOrEqual = '≥';
   /** @deprecated */
   public static final char leftGuillemete = '«';
   /** @deprecated */
   public static final char rightGuillemete = '»';

   private Typography() {
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.6"
   )
   public static void getLeftGuillemet$annotations() {
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.6"
   )
   public static void getRightGuillemet$annotations() {
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "This constant has a typo in the name. Use leftGuillemet instead.",
      replaceWith = @ReplaceWith(
   expression = "Typography.leftGuillemet",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.6"
   )
   public static void getLeftGuillemete$annotations() {
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "This constant has a typo in the name. Use rightGuillemet instead.",
      replaceWith = @ReplaceWith(
   expression = "Typography.rightGuillemet",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.6"
   )
   public static void getRightGuillemete$annotations() {
   }
}
