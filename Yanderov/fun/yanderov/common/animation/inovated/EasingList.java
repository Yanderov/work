package fun.Yanderov.common.animation.inovated;

public class EasingList {
   public static final double c1 = 1.70158;
   public static final double c2 = 2.5949095;
   public static final double c3 = 2.70158;
   public static final double c4 = 2.0943951023931953;
   public static final double c5 = 1.3962634015954636;
   public static final Easing SINE_IN = (value) -> (float)((double)1.0F - Math.cos((double)value * Math.PI / (double)2.0F));
   public static final Easing SINE_OUT = (value) -> (float)Math.sin((double)value * Math.PI / (double)2.0F);
   public static final Easing SINE_BOTH = (value) -> (float)(-(Math.cos(Math.PI * (double)value) - (double)1.0F) / (double)2.0F);
   public static final Easing CIRC_IN = (value) -> (float)((double)1.0F - Math.sqrt((double)1.0F - Math.pow((double)value, (double)2.0F)));
   public static final Easing CIRC_OUT = (value) -> (float)Math.sqrt((double)1.0F - Math.pow((double)value - (double)1.0F, (double)2.0F));
   public static final Easing CIRC_BOTH = (value) -> (float)((double)value < (double)0.5F ? ((double)1.0F - Math.sqrt((double)1.0F - Math.pow((double)2.0F * (double)value, (double)2.0F))) / (double)2.0F : (Math.sqrt((double)1.0F - Math.pow((double)-2.0F * (double)value + (double)2.0F, (double)2.0F)) + (double)1.0F) / (double)2.0F);
   public static final Easing ELASTIC_IN = (value) -> (double)value != (double)0.0F && (double)value != (double)1.0F ? (float)(Math.pow((double)-2.0F, (double)10.0F * (double)value - (double)10.0F) * Math.sin(((double)value * (double)10.0F - (double)10.75F) * 2.0943951023931953)) : value;
   public static final Easing ELASTIC_OUT = (value) -> (double)value != (double)0.0F && (double)value != (double)1.0F ? (float)(Math.pow((double)2.0F, (double)-10.0F * (double)value) * Math.sin(((double)value * (double)10.0F - (double)0.75F) * 2.0943951023931953) + (double)1.0F) : value;
   public static final Easing ELASTIC_BOTH = (value) -> (double)value != (double)0.0F && (double)value != (double)1.0F ? (float)((double)value < (double)0.5F ? -(Math.pow((double)2.0F, (double)20.0F * (double)value - (double)10.0F) * Math.sin(((double)20.0F * (double)value - (double)11.125F) * 1.3962634015954636)) / (double)2.0F : Math.pow((double)2.0F, (double)-20.0F * (double)value + (double)10.0F) * Math.sin(((double)20.0F * (double)value - (double)11.125F) * 1.3962634015954636) / (double)2.0F + (double)1.0F) : value;
   public static final Easing EXPO_IN = (value) -> (double)value != (double)0.0F ? (float)Math.pow((double)2.0F, (double)10.0F * (double)value - (double)10.0F) : value;
   public static final Easing EXPO_OUT = (value) -> (double)value != (double)1.0F ? (float)((double)1.0F - Math.pow((double)2.0F, (double)-10.0F * (double)value)) : value;
   public static final Easing EXPO_BOTH = (value) -> (double)value != (double)0.0F && (double)value != (double)1.0F ? (float)((double)value < (double)0.5F ? Math.pow((double)2.0F, (double)20.0F * (double)value - (double)10.0F) / (double)2.0F : ((double)2.0F - Math.pow((double)2.0F, (double)-20.0F * (double)value + (double)10.0F)) / (double)2.0F) : value;
   public static final Easing BACK_IN = (value) -> (float)(2.70158 * Math.pow((double)value, (double)3.0F) - 1.70158 * Math.pow((double)value, (double)2.0F));
   public static final Easing BACK_OUT = (value) -> (float)((double)1.0F + 2.70158 * Math.pow((double)value - (double)1.0F, (double)3.0F) + 1.70158 * Math.pow((double)value - (double)1.0F, (double)2.0F));
   public static final Easing NONE = (value) -> value;
   public static final Easing BACK_BOTH = (value) -> (float)((double)value < (double)0.5F ? Math.pow((double)2.0F * (double)value, (double)2.0F) * (7.189819 * (double)value - 2.5949095) / (double)2.0F : (Math.pow((double)2.0F * (double)value - (double)2.0F, (double)2.0F) * (3.5949095 * ((double)value * (double)2.0F - (double)2.0F) + 2.5949095) + (double)2.0F) / (double)2.0F);
   public static final Easing BOUNCE_OUT = (value) -> {
      float n1 = 7.5625F;
      float d1 = 2.75F;
      if ((double)value < (double)1.0F / (double)d1) {
         return (float)((double)n1 * Math.pow((double)value, (double)2.0F));
      } else {
         return (double)value < (double)2.0F / (double)d1 ? (float)((double)n1 * Math.pow((double)value - (double)1.5F / (double)d1, (double)2.0F) + (double)0.75F) : (float)((double)value < (double)2.5F / (double)d1 ? (double)n1 * Math.pow((double)value - (double)2.25F / (double)d1, (double)2.0F) + (double)0.9375F : (double)n1 * Math.pow((double)value - (double)2.625F / (double)d1, (double)2.0F) + (double)0.984375F);
      }
   };
   public static final Easing BOUNCE_IN = (value) -> (float)((double)1.0F - (double)BOUNCE_OUT.ease((float)((double)1.0F - (double)value)));
   public static final Easing BOUNCE_BOTH = (value) -> (float)((double)value < (double)0.5F ? ((double)1.0F - (double)BOUNCE_OUT.ease((float)((double)1.0F - (double)2.0F * (double)value))) / (double)2.0F : ((double)1.0F + (double)BOUNCE_OUT.ease((float)((double)2.0F * (double)value - (double)1.0F))) / (double)2.0F);
   public static final Easing QUINT_IN = (x) -> (double)x < (double)0.5F ? 16.0F * x * x * x * x * x : (float)((double)1.0F - Math.pow((double)(-2.0F * x + 2.0F), (double)5.0F) / (double)2.0F);

   @FunctionalInterface
   public interface Easing {
      float ease(float var1);
   }
}

