package kotlin.text;

import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a,\u0010\u0006\u001a\u00020\u00052\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000¢\u0006\u0002\b\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0013\u0010\n\u001a\u00020\t*\u00020\bH\u0002¢\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\f"},
   d2 = {"Lkotlin/Function1;", "Lkotlin/text/HexFormat$Builder;", "", "Lkotlin/ExtensionFunctionType;", "builderAction", "Lkotlin/text/HexFormat;", "HexFormat", "(Lkotlin/jvm/functions/Function1;)Lkotlin/text/HexFormat;", "", "", "isCaseSensitive", "(Ljava/lang/String;)Z", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nHexFormat.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HexFormat.kt\nkotlin/text/HexFormatKt\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,441:1\n1088#2,2:442\n*S KotlinDebug\n*F\n+ 1 HexFormat.kt\nkotlin/text/HexFormatKt\n*L\n440#1:442,2\n*E\n"})
public final class HexFormatKt {
   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   @InlineOnly
   private static final HexFormat HexFormat(Function1 builderAction) {
      Intrinsics.checkNotNullParameter(builderAction, "builderAction");
      HexFormat.Builder var1 = new HexFormat.Builder();
      builderAction.invoke(var1);
      return var1.build();
   }

   private static final boolean isCaseSensitive(String $this$isCaseSensitive) {
      CharSequence $this$any$iv = (CharSequence)$this$isCaseSensitive;
      int $i$f$any = 0;
      int var3 = 0;

      boolean var10000;
      while(true) {
         if (var3 >= $this$any$iv.length()) {
            var10000 = false;
            break;
         }

         char element$iv = $this$any$iv.charAt(var3);
         int var6 = 0;
         if (Intrinsics.compare(element$iv, 128) >= 0 || Character.isLetter(element$iv)) {
            var10000 = true;
            break;
         }

         ++var3;
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final boolean access$isCaseSensitive(String $receiver) {
      return isCaseSensitive($receiver);
   }
}
