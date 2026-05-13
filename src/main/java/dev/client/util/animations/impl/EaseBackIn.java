package dev.client.util.animations.impl;

import dev.client.util.animations.Animation;
import dev.client.util.animations.Direction;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class EaseBackIn extends Animation {
   private final float easeAmount;

   public EaseBackIn(int ms, double endPoint, float easeAmount) {
      super(ms, endPoint);
      this.easeAmount = easeAmount;
   }

   public EaseBackIn(int ms, double endPoint, float easeAmount, Direction direction) {
      super(ms, endPoint, direction);
      this.easeAmount = easeAmount;
   }

   protected boolean correctOutput() {
      return true;
   }

   protected double getEquation(double x) {
      double x1 = x / (double)this.duration;
      float shrink = this.easeAmount + 1.0F;
      return Math.max(0.0D, 1.0D + (double)shrink * Math.pow(x1 - 1.0D, 3.0D) + (double)this.easeAmount * Math.pow(x1 - 1.0D, 2.0D));
   }
}
