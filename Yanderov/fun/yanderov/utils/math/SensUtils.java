package fun.Yanderov.utils.math;

import net.minecraft.class_310;

public class SensUtils {
   public static float getGCDValue() {
      class_310 mc = class_310.method_1551();
      if (mc != null && mc.field_1690 != null) {
         double sensitivity = (Double)mc.field_1690.method_42495().method_41753();
         double f = sensitivity * 0.6 + 0.2;
         double gcd = f * f * f * (double)8.0F;
         return (float)gcd;
      } else {
         return 0.0F;
      }
   }
}

