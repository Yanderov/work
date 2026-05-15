package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\b\u0010\u0006R\u001a\u0010\t\u001a\u00020\u00048\u0006X\u0087T¢\u0006\f\n\u0004\b\t\u0010\u0006\u0012\u0004\b\n\u0010\u0003R\u0014\u0010\u000b\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u000b\u0010\u0006R\u0014\u0010\f\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\r\u0010\u0006R\u001a\u0010\u000e\u001a\u00020\u00048\u0006X\u0087T¢\u0006\f\n\u0004\b\u000e\u0010\u0006\u0012\u0004\b\u000f\u0010\u0003R\u001a\u0010\u0011\u001a\u00020\u00108\u0006X\u0087T¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u0012\u0004\b\u0013\u0010\u0003R\u001a\u0010\u0014\u001a\u00020\u00108\u0006X\u0087T¢\u0006\f\n\u0004\b\u0014\u0010\u0012\u0012\u0004\b\u0015\u0010\u0003¨\u0006\u0016"},
   d2 = {"Lkotlin/jvm/internal/CharCompanionObject;", "", "<init>", "()V", "", "MAX_HIGH_SURROGATE", "C", "MAX_LOW_SURROGATE", "MAX_SURROGATE", "MAX_VALUE", "getMAX_VALUE$annotations", "MIN_HIGH_SURROGATE", "MIN_LOW_SURROGATE", "MIN_SURROGATE", "MIN_VALUE", "getMIN_VALUE$annotations", "", "SIZE_BITS", "I", "getSIZE_BITS$annotations", "SIZE_BYTES", "getSIZE_BYTES$annotations", "kotlin-stdlib"}
)
public final class CharCompanionObject {
   @NotNull
   public static final CharCompanionObject INSTANCE = new CharCompanionObject();
   public static final char MIN_VALUE = '\u0000';
   public static final char MAX_VALUE = '\uffff';
   public static final char MIN_HIGH_SURROGATE = '\ud800';
   public static final char MAX_HIGH_SURROGATE = '\udbff';
   public static final char MIN_LOW_SURROGATE = '\udc00';
   public static final char MAX_LOW_SURROGATE = '\udfff';
   public static final char MIN_SURROGATE = '\ud800';
   public static final char MAX_SURROGATE = '\udfff';
   public static final int SIZE_BYTES = 2;
   public static final int SIZE_BITS = 16;

   private CharCompanionObject() {
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.3"
   )
   public static void getMIN_VALUE$annotations() {
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.3"
   )
   public static void getMAX_VALUE$annotations() {
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
