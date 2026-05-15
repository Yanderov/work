package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u001a\u0010\t\u001a\u00020\b8\u0006X\u0087T¢\u0006\f\n\u0004\b\t\u0010\n\u0012\u0004\b\u000b\u0010\u0003R\u001a\u0010\f\u001a\u00020\b8\u0006X\u0087T¢\u0006\f\n\u0004\b\f\u0010\n\u0012\u0004\b\r\u0010\u0003¨\u0006\u000e"},
   d2 = {"Lkotlin/jvm/internal/ByteCompanionObject;", "", "<init>", "()V", "", "MAX_VALUE", "B", "MIN_VALUE", "", "SIZE_BITS", "I", "getSIZE_BITS$annotations", "SIZE_BYTES", "getSIZE_BYTES$annotations", "kotlin-stdlib"}
)
public final class ByteCompanionObject {
   @NotNull
   public static final ByteCompanionObject INSTANCE = new ByteCompanionObject();
   public static final byte MIN_VALUE = -128;
   public static final byte MAX_VALUE = 127;
   public static final int SIZE_BYTES = 1;
   public static final int SIZE_BITS = 8;

   private ByteCompanionObject() {
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.3"
   )
   public static void getSIZE_BYTES$annotations() {
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.3"
   )
   public static void getSIZE_BITS$annotations() {
   }
}
