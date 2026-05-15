package kotlin.text;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000b\n\u0002\b(\b\u0086\u0081\u0002\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0013B\u0019\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0086\u0002¢\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1¨\u00062"},
   d2 = {"Lkotlin/text/CharCategory;", "", "", "value", "", "code", "<init>", "(Ljava/lang/String;IILjava/lang/String;)V", "", "char", "", "contains", "(C)Z", "Ljava/lang/String;", "getCode", "()Ljava/lang/String;", "I", "getValue", "()I", "Companion", "UNASSIGNED", "UPPERCASE_LETTER", "LOWERCASE_LETTER", "TITLECASE_LETTER", "MODIFIER_LETTER", "OTHER_LETTER", "NON_SPACING_MARK", "ENCLOSING_MARK", "COMBINING_SPACING_MARK", "DECIMAL_DIGIT_NUMBER", "LETTER_NUMBER", "OTHER_NUMBER", "SPACE_SEPARATOR", "LINE_SEPARATOR", "PARAGRAPH_SEPARATOR", "CONTROL", "FORMAT", "PRIVATE_USE", "SURROGATE", "DASH_PUNCTUATION", "START_PUNCTUATION", "END_PUNCTUATION", "CONNECTOR_PUNCTUATION", "OTHER_PUNCTUATION", "MATH_SYMBOL", "CURRENCY_SYMBOL", "MODIFIER_SYMBOL", "OTHER_SYMBOL", "INITIAL_QUOTE_PUNCTUATION", "FINAL_QUOTE_PUNCTUATION", "kotlin-stdlib"}
)
public enum CharCategory {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private final int value;
   @NotNull
   private final String code;
   UNASSIGNED(0, "Cn"),
   UPPERCASE_LETTER(1, "Lu"),
   LOWERCASE_LETTER(2, "Ll"),
   TITLECASE_LETTER(3, "Lt"),
   MODIFIER_LETTER(4, "Lm"),
   OTHER_LETTER(5, "Lo"),
   NON_SPACING_MARK(6, "Mn"),
   ENCLOSING_MARK(7, "Me"),
   COMBINING_SPACING_MARK(8, "Mc"),
   DECIMAL_DIGIT_NUMBER(9, "Nd"),
   LETTER_NUMBER(10, "Nl"),
   OTHER_NUMBER(11, "No"),
   SPACE_SEPARATOR(12, "Zs"),
   LINE_SEPARATOR(13, "Zl"),
   PARAGRAPH_SEPARATOR(14, "Zp"),
   CONTROL(15, "Cc"),
   FORMAT(16, "Cf"),
   PRIVATE_USE(18, "Co"),
   SURROGATE(19, "Cs"),
   DASH_PUNCTUATION(20, "Pd"),
   START_PUNCTUATION(21, "Ps"),
   END_PUNCTUATION(22, "Pe"),
   CONNECTOR_PUNCTUATION(23, "Pc"),
   OTHER_PUNCTUATION(24, "Po"),
   MATH_SYMBOL(25, "Sm"),
   CURRENCY_SYMBOL(26, "Sc"),
   MODIFIER_SYMBOL(27, "Sk"),
   OTHER_SYMBOL(28, "So"),
   INITIAL_QUOTE_PUNCTUATION(29, "Pi"),
   FINAL_QUOTE_PUNCTUATION(30, "Pf");

   // $FF: synthetic field
   private static final EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

   private CharCategory(int value, String code) {
      this.value = value;
      this.code = code;
   }

   public final int getValue() {
      return this.value;
   }

   @NotNull
   public final String getCode() {
      return this.code;
   }

   public final boolean contains(char char) {
      return Character.getType(char) == this.value;
   }

   @NotNull
   public static EnumEntries getEntries() {
      return $ENTRIES;
   }

   // $FF: synthetic method
   private static final CharCategory[] $values() {
      CharCategory[] var0 = new CharCategory[]{UNASSIGNED, UPPERCASE_LETTER, LOWERCASE_LETTER, TITLECASE_LETTER, MODIFIER_LETTER, OTHER_LETTER, NON_SPACING_MARK, ENCLOSING_MARK, COMBINING_SPACING_MARK, DECIMAL_DIGIT_NUMBER, LETTER_NUMBER, OTHER_NUMBER, SPACE_SEPARATOR, LINE_SEPARATOR, PARAGRAPH_SEPARATOR, CONTROL, FORMAT, PRIVATE_USE, SURROGATE, DASH_PUNCTUATION, START_PUNCTUATION, END_PUNCTUATION, CONNECTOR_PUNCTUATION, OTHER_PUNCTUATION, MATH_SYMBOL, CURRENCY_SYMBOL, MODIFIER_SYMBOL, OTHER_SYMBOL, INITIAL_QUOTE_PUNCTUATION, FINAL_QUOTE_PUNCTUATION};
      return var0;
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"},
      d2 = {"Lkotlin/text/CharCategory$Companion;", "", "<init>", "()V", "", "category", "Lkotlin/text/CharCategory;", "valueOf", "(I)Lkotlin/text/CharCategory;", "kotlin-stdlib"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final CharCategory valueOf(int category) {
         CharCategory var10000;
         if (0 <= category ? category < 17 : false) {
            var10000 = (CharCategory)CharCategory.getEntries().get(category);
         } else {
            if (!(18 <= category ? category < 31 : false)) {
               throw new IllegalArgumentException("Category #" + category + " is not defined.");
            }

            var10000 = (CharCategory)CharCategory.getEntries().get(category - 1);
         }

         return var10000;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
