package kotlin;

import kotlin.internal.InlineOnly;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0004\u001a\u0018\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0006"},
   d2 = {"Lkotlin/UShort;", "code", "", "Char-xj2QHRw", "(S)C", "Char", "kotlin-stdlib"}
)
public final class CharCodeJVMKt {
   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final char Char_xj2QHRw/* $FF was: Char-xj2QHRw*/(short code) {
      return (char)(code & '\uffff');
   }
}
