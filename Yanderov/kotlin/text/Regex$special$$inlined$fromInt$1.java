package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(
   mv = {1, 9, 0},
   k = 3,
   xi = 48,
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0010\t\u001a\u00020\u0005\"\u0014\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u000e\u0010\u0004\u001a\n \u0003*\u0004\u0018\u00018\u00008\u0000H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"},
   d2 = {"Lkotlin/text/FlagEnum;", "", "T", "kotlin.jvm.PlatformType", "it", "", "invoke", "(Ljava/lang/Enum;)Ljava/lang/Boolean;", "kotlin/text/RegexKt$fromInt$1$1", "<anonymous>"}
)
public final class Regex$special$$inlined$fromInt$1 extends Lambda implements Function1 {
   // $FF: synthetic field
   final int $value;

   public Regex$special$$inlined$fromInt$1(int $value) {
      super(1);
      this.$value = $value;
   }

   public final Boolean invoke(Enum it) {
      return (this.$value & ((FlagEnum)it).getMask()) == ((FlagEnum)it).getValue();
   }
}
