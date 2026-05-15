package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
   d2 = {"Lkotlin/text/ScreenFloatValueRegEx;", "", "<init>", "()V", "Lkotlin/text/Regex;", "value", "Lkotlin/text/Regex;", "kotlin-stdlib"}
)
final class ScreenFloatValueRegEx {
   @NotNull
   public static final ScreenFloatValueRegEx INSTANCE = new ScreenFloatValueRegEx();
   @JvmField
   @NotNull
   public static final Regex value;

   private ScreenFloatValueRegEx() {
   }

   static {
      ScreenFloatValueRegEx var0 = INSTANCE;
      int var2 = 0;
      String Digits = "(\\p{Digit}+)";
      String HexDigits = "(\\p{XDigit}+)";
      String Exp = "[eE][+-]?" + Digits;
      String HexString = "(0[xX]" + HexDigits + "(\\.)?)|(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ')';
      String Number = '(' + Digits + "(\\.)?(" + Digits + "?)(" + Exp + ")?)|(\\.(" + Digits + ")(" + Exp + ")?)|((" + HexString + ")[pP][+-]?" + Digits + ')';
      String fpRegex = "[\\x00-\\x20]*[+-]?(NaN|Infinity|((" + Number + ")[fFdD]?))[\\x00-\\x20]*";
      value = new Regex(fpRegex);
   }
}
