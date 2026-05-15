package fun.Yanderov.common.animation.implement;

import fun.Yanderov.common.animation.Animation;

public class Decelerate extends Animation {
   public double calculation(double value) {
      double x = value / (double)this.ms;
      return (double)1.0F - (x - (double)1.0F) * (x - (double)1.0F);
   }
}

