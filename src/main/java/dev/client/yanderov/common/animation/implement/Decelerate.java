package dev.client.yanderov.common.animation.implement;

import dev.client.yanderov.common.animation.Animation;

public class Decelerate extends Animation {
   public double calculation(double value) {
      double x = value / (double)this.ms;
      return (double)1.0F - (x - (double)1.0F) * (x - (double)1.0F);
   }
}

