package dev.client.yanderov.utils.display.systemrender.builders.states;

import java.awt.Color;

public record QuadColorState(int color1, int color2, int color3, int color4) {
   public static final QuadColorState TRANSPARENT = new QuadColorState(0, 0, 0, 0);
   public static final QuadColorState WHITE = new QuadColorState(-1, -1, -1, -1);

   public QuadColorState(Color color1, Color color2, Color color3, Color color4) {
      this(color1.getRGB(), color2.getRGB(), color3.getRGB(), color4.getRGB());
   }

   public QuadColorState(Color color) {
      this(color, color, color, color);
   }

   public QuadColorState(int color) {
      this(color, color, color, color);
   }

   public static QuadColorState fromRgba(int r, int g, int b, int a) {
      int packedColor = (a & 255) << 24 | (r & 255) << 16 | (g & 255) << 8 | b & 255;
      return new QuadColorState(packedColor);
   }

   private static Color interpolate(Color c1, Color c2, float ratio) {
      ratio = Math.max(0.0F, Math.min(1.0F, ratio));
      int r = (int)((float)c1.getRed() * (1.0F - ratio) + (float)c2.getRed() * ratio);
      int g = (int)((float)c1.getGreen() * (1.0F - ratio) + (float)c2.getGreen() * ratio);
      int b = (int)((float)c1.getBlue() * (1.0F - ratio) + (float)c2.getBlue() * ratio);
      int a = (int)((float)c1.getAlpha() * (1.0F - ratio) + (float)c2.getAlpha() * ratio);
      return new Color(r, g, b, a);
   }

   public static QuadColorState vertical(Color topColor, Color bottomColor) {
      return new QuadColorState(topColor, bottomColor, bottomColor, topColor);
   }

   public static QuadColorState vertical(int topColor, int bottomColor) {
      return new QuadColorState(topColor, bottomColor, bottomColor, topColor);
   }

   public static QuadColorState horizontal(Color leftColor, Color rightColor) {
      return new QuadColorState(leftColor, leftColor, rightColor, rightColor);
   }

   public static QuadColorState horizontal(int leftColor, int rightColor) {
      return new QuadColorState(leftColor, leftColor, rightColor, rightColor);
   }

   public static QuadColorState animatedVertical(Color color1, Color color2, double durationSeconds) {
      double progress = (double)System.currentTimeMillis() % (durationSeconds * (double)1000.0F) / (durationSeconds * (double)1000.0F);
      float blend = (float)(Math.sin(progress * (double)2.0F * Math.PI) * (double)0.5F + (double)0.5F);
      Color topColor = interpolate(color1, color2, blend);
      Color bottomColor = interpolate(color2, color1, blend);
      return vertical(topColor, bottomColor);
   }

   public static QuadColorState animatedHorizontal(Color color1, Color color2, double durationSeconds) {
      double progress = (double)System.currentTimeMillis() % (durationSeconds * (double)1000.0F) / (durationSeconds * (double)1000.0F);
      float blend = (float)(Math.sin(progress * (double)2.0F * Math.PI) * (double)0.5F + (double)0.5F);
      Color leftColor = interpolate(color1, color2, blend);
      Color rightColor = interpolate(color2, color1, blend);
      return horizontal(leftColor, rightColor);
   }
}

