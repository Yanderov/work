package dev.client.yanderov.common.animation.Easy;

public class EaseBackIn extends EasyAnimService {
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
      float shrink = this.easeAmount + 1.0F;
      return Math.max((double)0.0F, (double)1.0F + (double)shrink * Math.pow(x - (double)1.0F, (double)3.0F) + (double)this.easeAmount * Math.pow(x - (double)1.0F, (double)2.0F));
   }
}

