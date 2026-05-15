package kotlin.math;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\b\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\u0006¨\u0006\f"},
   d2 = {"Lkotlin/math/Constants;", "", "<init>", "()V", "", "LN2", "D", "epsilon", "taylor_2_bound", "taylor_n_bound", "upper_taylor_2_bound", "upper_taylor_n_bound", "kotlin-stdlib"}
)
final class Constants {
   @NotNull
   public static final Constants INSTANCE = new Constants();
   @JvmField
   public static final double LN2 = Math.log((double)2.0F);
   @JvmField
   public static final double epsilon = Math.ulp((double)1.0F);
   @JvmField
   public static final double taylor_2_bound;
   @JvmField
   public static final double taylor_n_bound;
   @JvmField
   public static final double upper_taylor_2_bound;
   @JvmField
   public static final double upper_taylor_n_bound;

   private Constants() {
   }

   static {
      taylor_2_bound = Math.sqrt(epsilon);
      taylor_n_bound = Math.sqrt(taylor_2_bound);
      upper_taylor_2_bound = (double)1 / taylor_2_bound;
      upper_taylor_n_bound = (double)1 / taylor_n_bound;
   }
}
