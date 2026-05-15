package fun.Yanderov.common.animation.implement;

import fun.Yanderov.common.animation.Animation;

public class OutBack extends Animation {
   public double calculation(double value) {
      double x = value / (double)this.ms;
      double c1 = 1.70158;
      double c3 = c1 + (double)1.0F;
      return (double)1.0F + c3 * Math.pow(x - (double)1.0F, (double)3.0F) + c1 * Math.pow(x - (double)1.0F, (double)2.0F);
   }
}

