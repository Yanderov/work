package dev.client.yanderov.common.animation.implement;

import dev.client.yanderov.common.animation.Animation;

public class InOutBack extends Animation {
   public double calculation(double value) {
      double x = value / (double)this.ms;
      double c1 = 1.70158;
      double c2 = c1 * 1.525;
      return x < (double)0.5F ? Math.pow((double)2.0F * x, (double)2.0F) * ((c2 + (double)1.0F) * (double)2.0F * x - c2) / (double)2.0F : (Math.pow((double)2.0F * x - (double)2.0F, (double)2.0F) * ((c2 + (double)1.0F) * (x * (double)2.0F - (double)2.0F) + c2) + (double)2.0F) / (double)2.0F;
   }
}

