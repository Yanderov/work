package kotlin.jvm.internal;

import kotlin.Metadata;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\b\n\u0002\u0010\u0001\n\u0002\b\u0003\u001a\u000f\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0001\u0010\u0002¨\u0006\u0003"},
   d2 = {"", "notSupportedError", "()Ljava/lang/Void;", "kotlin-stdlib"}
)
public final class LocalVariableReferencesKt {
   private static final Void notSupportedError() {
      throw new UnsupportedOperationException("Not supported for local property reference.");
   }

   // $FF: synthetic method
   public static final Void access$notSupportedError() {
      return notSupportedError();
   }
}
