package fun.Yanderov.utils.display.shape;

public class BorderRadius {
   private final float topLeftRadius;
   private final float topRightRadius;
   private final float bottomRightRadius;
   private final float bottomLeftRadius;

   public BorderRadius(float topLeftRadius, float topRightRadius, float bottomRightRadius, float bottomLeftRadius) {
      this.topLeftRadius = topLeftRadius;
      this.topRightRadius = topRightRadius;
      this.bottomRightRadius = bottomRightRadius;
      this.bottomLeftRadius = bottomLeftRadius;
   }

   public float topLeftRadius() {
      return this.topLeftRadius;
   }

   public float topRightRadius() {
      return this.topRightRadius;
   }

   public float bottomRightRadius() {
      return this.bottomRightRadius;
   }

   public float bottomLeftRadius() {
      return this.bottomLeftRadius;
   }
}

