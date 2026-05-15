package dev.client.yanderov.common.animation;

public interface AnimationCalculation {
   default double calculation(double value) {
      return (double)0.0F;
   }
}

