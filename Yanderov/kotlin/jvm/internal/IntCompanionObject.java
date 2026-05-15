package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u001a\u0010\b\u001a\u00020\u00048\u0006X\u0087T¢\u0006\f\n\u0004\b\b\u0010\u0006\u0012\u0004\b\t\u0010\u0003R\u001a\u0010\n\u001a\u00020\u00048\u0006X\u0087T¢\u0006\f\n\u0004\b\n\u0010\u0006\u0012\u0004\b\u000b\u0010\u0003¨\u0006\f"},
   d2 = {"Lkotlin/jvm/internal/IntCompanionObject;", "", "<init>", "()V", "", "MAX_VALUE", "I", "MIN_VALUE", "SIZE_BITS", "getSIZE_BITS$annotations", "SIZE_BYTES", "getSIZE_BYTES$annotations", "kotlin-stdlib"}
)
public final class IntCompanionObject {
   @NotNull
   public static final IntCompanionObject INSTANCE = new IntCompanionObject();
   public static final int MIN_VALUE = Integer.MIN_VALUE;
   public static final int MAX_VALUE = Integer.MAX_VALUE;
   public static final int SIZE_BYTES = 4;
   public static final int SIZE_BITS = 32;

   private IntCompanionObject() {
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
