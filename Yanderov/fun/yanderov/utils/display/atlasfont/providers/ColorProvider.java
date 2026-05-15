package fun.Yanderov.utils.display.atlasfont.providers;

import java.awt.Color;

public final class ColorProvider {
   public static int pack(int red, int green, int blue, int alpha) {
      return (alpha & 255) << 24 | (red & 255) << 16 | (green & 255) << 8 | (blue & 255) << 0;
   }

   public static int[] unpack(int color) {
      return new int[]{color >> 16 & 255, color >> 8 & 255, color & 255, color >> 24 & 255};
   }

   public static float[] normalize(Color color) {
      return new float[]{(float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, (float)color.getAlpha() / 255.0F};
   }

   public static float[] normalize(int color) {
      int[] components = unpack(color);
      return new float[]{(float)components[0] / 255.0F, (float)components[1] / 255.0F, (float)components[2] / 255.0F, (float)components[3] / 255.0F};
   }
}

