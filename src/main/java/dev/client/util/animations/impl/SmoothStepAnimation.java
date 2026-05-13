package dev.client.util.animations.impl;

import dev.client.util.animations.Animation;
import dev.client.util.animations.Direction;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class SmoothStepAnimation extends Animation {
   public SmoothStepAnimation(int ms, double endPoint) {
      super(ms, endPoint);
   }

   public SmoothStepAnimation(int ms, double endPoint, Direction direction) {
      super(ms, endPoint, direction);
   }

   protected double getEquation(double x) {
      double x1 = x / (double)this.duration;
      return -2.0D * Math.pow(x1, 3.0D) + 3.0D * Math.pow(x1, 2.0D);
   }
}
