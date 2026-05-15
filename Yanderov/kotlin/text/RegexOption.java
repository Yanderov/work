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
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0010\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u001b\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0005\u001a\u00020\u00038\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\b\u001a\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u00038\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\b\u001a\u0004\b\u000b\u0010\nj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0013"},
   d2 = {"Lkotlin/text/RegexOption;", "", "Lkotlin/text/FlagEnum;", "", "value", "mask", "<init>", "(Ljava/lang/String;III)V", "I", "getMask", "()I", "getValue", "IGNORE_CASE", "MULTILINE", "LITERAL", "UNIX_LINES", "COMMENTS", "DOT_MATCHES_ALL", "CANON_EQ", "kotlin-stdlib"}
)
public enum RegexOption implements FlagEnum {
   private final int value;
   private final int mask;
   IGNORE_CASE(2, 0, 2, (DefaultConstructorMarker)null),
   MULTILINE(8, 0, 2, (DefaultConstructorMarker)null),
   LITERAL(16, 0, 2, (DefaultConstructorMarker)null),
   UNIX_LINES(1, 0, 2, (DefaultConstructorMarker)null),
   COMMENTS(4, 0, 2, (DefaultConstructorMarker)null),
   DOT_MATCHES_ALL(32, 0, 2, (DefaultConstructorMarker)null),
   CANON_EQ(128, 0, 2, (DefaultConstructorMarker)null);

   // $FF: synthetic field
   private static final EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

   private RegexOption(int value, int mask) {
      this.value = value;
      this.mask = mask;
   }

   // $FF: synthetic method
   RegexOption(int var3, int var4, int var5, DefaultConstructorMarker var6) {
      if ((var5 & 2) != 0) {
         var4 = var3;
      }

      this(var3, var4);
   }

   public int getValue() {
      return this.value;
   }

   public int getMask() {
      return this.mask;
   }

   @NotNull
   public static EnumEntries getEntries() {
      return $ENTRIES;
   }

   // $FF: synthetic method
   private static final RegexOption[] $values() {
      RegexOption[] var0 = new RegexOption[]{IGNORE_CASE, MULTILINE, LITERAL, UNIX_LINES, COMMENTS, DOT_MATCHES_ALL, CANON_EQ};
      return var0;
   }
}
