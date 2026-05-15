package kotlin.text;

import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u001c\b\u0086\u0081\u0002\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0006\u001a\u0004\b\u0007\u0010\bj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001d¨\u0006\u001e"},
   d2 = {"Lkotlin/text/CharDirectionality;", "", "", "value", "<init>", "(Ljava/lang/String;II)V", "I", "getValue", "()I", "Companion", "UNDEFINED", "LEFT_TO_RIGHT", "RIGHT_TO_LEFT", "RIGHT_TO_LEFT_ARABIC", "EUROPEAN_NUMBER", "EUROPEAN_NUMBER_SEPARATOR", "EUROPEAN_NUMBER_TERMINATOR", "ARABIC_NUMBER", "COMMON_NUMBER_SEPARATOR", "NONSPACING_MARK", "BOUNDARY_NEUTRAL", "PARAGRAPH_SEPARATOR", "SEGMENT_SEPARATOR", "WHITESPACE", "OTHER_NEUTRALS", "LEFT_TO_RIGHT_EMBEDDING", "LEFT_TO_RIGHT_OVERRIDE", "RIGHT_TO_LEFT_EMBEDDING", "RIGHT_TO_LEFT_OVERRIDE", "POP_DIRECTIONAL_FORMAT", "kotlin-stdlib"}
)
public enum CharDirectionality {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private final int value;
   @NotNull
   private static final Lazy directionalityMap$delegate = LazyKt.lazy(null.INSTANCE);
   UNDEFINED(-1),
   LEFT_TO_RIGHT(0),
   RIGHT_TO_LEFT(1),
   RIGHT_TO_LEFT_ARABIC(2),
   EUROPEAN_NUMBER(3),
   EUROPEAN_NUMBER_SEPARATOR(4),
   EUROPEAN_NUMBER_TERMINATOR(5),
   ARABIC_NUMBER(6),
   COMMON_NUMBER_SEPARATOR(7),
   NONSPACING_MARK(8),
   BOUNDARY_NEUTRAL(9),
   PARAGRAPH_SEPARATOR(10),
   SEGMENT_SEPARATOR(11),
   WHITESPACE(12),
   OTHER_NEUTRALS(13),
   LEFT_TO_RIGHT_EMBEDDING(14),
   LEFT_TO_RIGHT_OVERRIDE(15),
   RIGHT_TO_LEFT_EMBEDDING(16),
   RIGHT_TO_LEFT_OVERRIDE(17),
   POP_DIRECTIONAL_FORMAT(18);

   // $FF: synthetic field
   private static final EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

   private CharDirectionality(int value) {
      this.value = value;
   }

   public final int getValue() {
      return this.value;
   }

   @NotNull
   public static EnumEntries getEntries() {
      return $ENTRIES;
   }

   // $FF: synthetic method
   private static final CharDirectionality[] $values() {
      CharDirectionality[] var0 = new CharDirectionality[]{UNDEFINED, LEFT_TO_RIGHT, RIGHT_TO_LEFT, RIGHT_TO_LEFT_ARABIC, EUROPEAN_NUMBER, EUROPEAN_NUMBER_SEPARATOR, EUROPEAN_NUMBER_TERMINATOR, ARABIC_NUMBER, COMMON_NUMBER_SEPARATOR, NONSPACING_MARK, BOUNDARY_NEUTRAL, PARAGRAPH_SEPARATOR, SEGMENT_SEPARATOR, WHITESPACE, OTHER_NEUTRALS, LEFT_TO_RIGHT_EMBEDDING, LEFT_TO_RIGHT_OVERRIDE, RIGHT_TO_LEFT_EMBEDDING, RIGHT_TO_LEFT_OVERRIDE, POP_DIRECTIONAL_FORMAT};
      return var0;
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bR'\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u000f"},
      d2 = {"Lkotlin/text/CharDirectionality$Companion;", "", "<init>", "()V", "", "directionality", "Lkotlin/text/CharDirectionality;", "valueOf", "(I)Lkotlin/text/CharDirectionality;", "", "directionalityMap$delegate", "Lkotlin/Lazy;", "getDirectionalityMap", "()Ljava/util/Map;", "directionalityMap", "kotlin-stdlib"}
   )
   public static final class Companion {
      private Companion() {
      }

      private final Map getDirectionalityMap() {
         Lazy var1 = CharDirectionality.directionalityMap$delegate;
         return (Map)var1.getValue();
      }

      @NotNull
      public final CharDirectionality valueOf(int directionality) {
         return (CharDirectionality)this.getDirectionalityMap().get(directionality);
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
