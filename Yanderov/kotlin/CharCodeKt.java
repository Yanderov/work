package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.internal.IntrinsicConstEvaluation;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0007\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0003\u0010\u0004\"\u001f\u0010\u0001\u001a\u00020\u0000*\u00020\u00028Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"},
   d2 = {"", "code", "", "Char", "(I)C", "getCode", "(C)I", "getCode$annotations", "(C)V", "kotlin-stdlib"}
)
public final class CharCodeKt {
   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final char Char(int code) {
      if (code >= 0 && code <= 65535) {
         return (char)code;
      } else {
         throw new IllegalArgumentException("Invalid Char code: " + code);
      }
   }

   private static final int getCode(char $this$code) {
      return $this$code;
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @InlineOnly
   @IntrinsicConstEvaluation
   public static void getCode$annotations(char <this>) {
   }
}
