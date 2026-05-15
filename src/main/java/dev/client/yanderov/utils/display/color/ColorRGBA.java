package dev.client.yanderov.utils.display.color;

public class ColorRGBA {
   private final int red;
   private final int green;
   private final int blue;
   private final int alpha;

   public ColorRGBA(int red, int green, int blue, int alpha) {
      this.red = this.clamp(red);
      this.green = this.clamp(green);
      this.blue = this.clamp(blue);
      this.alpha = this.clamp(alpha);
   }

   private int clamp(int v) {
      if (v < 0) {
         return 0;
      } else {
         return v > 255 ? 255 : v;
      }
   }

   public int getRed() {
      return this.red;
   }

   public int getGreen() {
      return this.green;
   }

   public int getBlue() {
      return this.blue;
   }

   public int getAlpha() {
      return this.alpha;
   }

   public int toARGB() {
      return (this.alpha & 255) << 24 | (this.red & 255) << 16 | (this.green & 255) << 8 | this.blue & 255;
   }
}

