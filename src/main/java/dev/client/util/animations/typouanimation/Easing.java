package dev.client.util.animations.typouanimation;

import java.util.function.Function;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.apache.commons.lang3.StringUtils;

@Environment(EnvType.CLIENT)
public enum Easing {
   LINEAR((x) -> x),
   SIGMOID((x) -> 1.0D / (1.0D + Math.exp(-x))),
   EASE_IN_QUAD((x) -> x * x),
   EASE_OUT_QUAD((x) -> x * (2.0D - x)),
   EASE_IN_OUT_QUAD((x) -> x < 0.5D ? 2.0D * x * x : -1.0D + (4.0D - 2.0D * x) * x),
   EASE_IN_CUBIC((x) -> x * x * x),
   EASE_OUT_CUBIC((x) -> {
      double value = x - 1.0D;
      return value * value * value + 1.0D;
   }),
   EASE_IN_OUT_CUBIC((x) -> x < 0.5D ? 4.0D * x * x * x : (x - 1.0D) * (2.0D * x - 2.0D) * (2.0D * x - 2.0D) + 1.0D),
   EASE_IN_QUART((x) -> x * x * x * x),
   EASE_OUT_QUART((x) -> {
      double value = x - 1.0D;
      return 1.0D - value * value * value * value;
   }),
   EASE_IN_OUT_QUART((x) -> {
      double value = x - 1.0D;
      return x < 0.5D ? 8.0D * x * x * x * x : 1.0D - 8.0D * value * value * value * value;
   }),
   EASE_IN_QUINT((x) -> x * x * x * x * x),
   EASE_OUT_QUINT((x) -> {
      double value = x - 1.0D;
      return 1.0D + value * value * value * value * value;
   }),
   EASE_IN_OUT_QUINT((x) -> {
      double value = x - 1.0D;
      return x < 0.5D ? 16.0D * x * x * x * x * x : 1.0D + 16.0D * value * value * value * value * value;
   }),
   EASE_IN_SINE((x) -> 1.0D - Math.cos(x * Math.PI / 2.0D)),
   EASE_OUT_SINE((x) -> Math.sin(x * Math.PI / 2.0D)),
   EASE_IN_OUT_SINE((x) -> 1.0D - Math.cos(Math.PI * x / 2.0D)),
   EASE_IN_EXPO((x) -> x == 0.0D ? 0.0D : Math.pow(2.0D, 10.0D * x - 10.0D)),
   EASE_OUT_EXPO((x) -> x == 1.0D ? 1.0D : 1.0D - Math.pow(2.0D, -10.0D * x)),
   EASE_IN_OUT_EXPO((x) -> x == 0.0D ? 0.0D : (x == 1.0D ? 1.0D : (x < 0.5D ? Math.pow(2.0D, 20.0D * x - 10.0D) / 2.0D : (2.0D - Math.pow(2.0D, -20.0D * x + 10.0D)) / 2.0D))),
   EASE_IN_CIRC((x) -> 1.0D - Math.sqrt(1.0D - x * x)),
   EASE_OUT_CIRC((x) -> {
      double value = x - 1.0D;
      return Math.sqrt(1.0D - value * value);
   }),
   EASE_IN_OUT_CIRC((x) -> x < 0.5D ? (1.0D - Math.sqrt(1.0D - 4.0D * x * x)) / 2.0D : (Math.sqrt(1.0D - 4.0D * (x - 1.0D) * x) + 1.0D) / 2.0D),
   EASE_IN_BACK((x) -> 2.70158 * x * x * x - 1.70158 * x * x),
   EASE_OUT_BACK((x) -> 1.0D + 2.70158 * Math.pow(x - 1.0D, 3.0D) + 1.70158 * Math.pow(x - 1.0D, 2.0D)),
   EASE_IN_OUT_BACK((x) -> x < 0.5D ? Math.pow(2.0D * x, 2.0D) * (7.189819 * x - 2.5949095) / 2.0D : (Math.pow(2.0D * x - 2.0D, 2.0D) * (3.5949095 * (x * 2.0D - 2.0D) + 2.5949095) + 2.0D) / 2.0D),
   EASE_IN_ELASTIC((x) -> x == 0.0D ? 0.0D : (x == 1.0D ? 1.0D : -Math.pow(2.0D, 10.0D * x - 10.0D) * Math.sin((x * 10.0D - 10.75D) * 2.0943951023931953))),
   EASE_OUT_ELASTIC((x) -> x == 0.0D ? 0.0D : (x == 1.0D ? 1.0D : Math.pow(2.0D, -10.0D * x) * Math.sin((x * 10.0D - 0.75D) * 2.0943951023931953) * 0.5D + 1.0D)),
   EASE_IN_OUT_ELASTIC((x) -> x == 0.0D ? 0.0D : (x == 1.0D ? 1.0D : (x < 0.5D ? -(Math.pow(2.0D, 20.0D * x - 10.0D) * Math.sin((20.0D * x - 11.125D) * 1.3962634015954636)) / 2.0D : Math.pow(2.0D, -20.0D * x + 10.0D) * Math.sin((20.0D * x - 11.125D) * 1.3962634015954636) / 2.0D + 1.0D))),
   SHRINK_EASING((x) -> {
      float easeAmount = 1.3F;
      float shrink = easeAmount + 1.0F;
      return Math.max(0.0D, 1.0D + (double)shrink * Math.pow(x - 1.0D, 3.0D) + (double)easeAmount * Math.pow(x - 1.0D, 2.0D));
   });

   private final Function<Double, Double> function;

   private Easing(final Function<Double, Double> function) {
      this.function = function;
   }

   public double apply(double x) {
      return (Double)this.getFunction().apply(x);
   }

   public float apply(float x) {
      return ((Double)this.getFunction().apply((double)x)).floatValue();
   }

   public String toString() {
      return StringUtils.capitalize(super.toString().toLowerCase().replace("_", " "));
   }

   public Function<Double, Double> getFunction() {
      return this.function;
   }
}
