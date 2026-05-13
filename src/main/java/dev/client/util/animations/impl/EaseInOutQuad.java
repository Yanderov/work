package dev.client.util.animations.impl;

import dev.client.util.animations.Animation;
import dev.client.util.animations.Direction;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class EaseInOutQuad extends Animation {
   public EaseInOutQuad(int ms, double endPoint) {
      super(ms, endPoint);
   }

   public EaseInOutQuad(int ms, double endPoint, Direction direction) {
      super(ms, endPoint, direction);
   }

   protected double getEquation(double x1) {
      double x = x1 / (double)this.duration;
      return x < 0.5D ? 2.0D * Math.pow(x, 2.0D) : 1.0D - Math.pow(-2.0D * x + 2.0D, 2.0D) / 2.0D;
   }
}
