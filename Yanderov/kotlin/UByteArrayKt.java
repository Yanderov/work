package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u0018\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a/\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u0002H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001c\u0010\u000b\u001a\u00020\u00052\n\u0010\b\u001a\u00020\u0005\"\u00020\u0003H\u0087\b¢\u0006\u0004\b\t\u0010\n\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\f"},
   d2 = {"", "size", "Lkotlin/Function1;", "Lkotlin/UByte;", "init", "Lkotlin/UByteArray;", "UByteArray", "(ILkotlin/jvm/functions/Function1;)[B", "elements", "ubyteArrayOf-GBYM_sE", "([B)[B", "ubyteArrayOf", "kotlin-stdlib"}
)
public final class UByteArrayKt {
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final byte[] UByteArray(int size, Function1 init) {
      Intrinsics.checkNotNullParameter(init, "init");
      int var2 = 0;

      byte[] var3;
      for(var3 = new byte[size]; var2 < size; ++var2) {
         var3[var2] = ((UByte)init.invoke(var2)).unbox-impl();
      }

      return UByteArray.constructor-impl(var3);
   }

   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   @InlineOnly
   private static final byte[] ubyteArrayOf_GBYM_sE/* $FF was: ubyteArrayOf-GBYM_sE*/(byte... elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      return elements;
   }
}
