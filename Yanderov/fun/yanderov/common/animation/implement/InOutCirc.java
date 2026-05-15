package fun.Yanderov.common.animation.implement;

import fun.Yanderov.common.animation.Animation;

public class InOutCirc extends Animation {
   public double calculation(double value) {
      double x = value / (double)this.ms;
      return x < (double)0.5F ? ((double)1.0F - Math.sqrt((double)1.0F - Math.pow((double)2.0F * x, (double)2.0F))) / (double)2.0F : (Math.sqrt((double)1.0F - Math.pow((double)-2.0F * x + (double)2.0F, (double)2.0F)) + (double)1.0F) / (double)2.0F;
   }
}

