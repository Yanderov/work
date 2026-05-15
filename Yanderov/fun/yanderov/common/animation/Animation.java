package fun.Yanderov.common.animation;

import fun.Yanderov.utils.math.time.TimerUtil;

public class Animation implements AnimationCalculation {
   protected final TimerUtil counter = new TimerUtil();
   protected int ms;
   protected double value;
   protected Direction direction;

   public Animation() {
      this.direction = Direction.FORWARDS;
   }

   public void reset() {
      this.counter.resetCounter();
   }

   public boolean isDone() {
      return this.counter.isReached((long)this.ms);
   }

   public boolean isFinished(Direction direction) {
      return this.direction == direction && this.isDone();
   }

   public Direction getDirection() {
      return this.direction;
   }

   public void setDirection(Direction direction) {
      if (this.direction != direction) {
         this.direction = direction;
         this.adjustTimer();
      }

   }

   public boolean isDirection(Direction direction) {
      return this.direction == direction;
   }

   private void adjustTimer() {
      this.counter.setTime(System.currentTimeMillis() - ((long)this.ms - Math.min((long)this.ms, this.counter.getTime())));
   }

   public Double getOutput() {
      double time = ((double)1.0F - this.calculation((double)this.counter.getTime())) * this.value;
      return this.direction == Direction.FORWARDS ? this.endValue() : (this.isDone() ? (double)0.0F : time);
   }

   protected double endValue() {
      return this.isDone() ? this.value : this.calculation((double)this.counter.getTime()) * this.value;
   }

   public Animation setMs(int ms) {
      this.ms = ms;
      return this;
   }

   public Animation setValue(double value) {
      this.value = value;
      return this;
   }
}

