package fun.Yanderov.utils.display.font;

import com.mojang.blaze3d.systems.RenderSystem;
import fun.Yanderov.events.render.TextFactoryEvent;
import fun.Yanderov.utils.client.chat.StringHelper;
import fun.Yanderov.utils.client.managers.event.EventManager;
import fun.Yanderov.utils.display.color.ColorAssist;
import fun.Yanderov.utils.display.font.entry.DrawEntry;
import fun.Yanderov.utils.display.font.glyph.Glyph;
import fun.Yanderov.utils.display.font.glyph.GlyphMap;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.math.calc.Calculate;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import java.awt.Color;
import java.awt.Font;
import net.minecraft.class_10142;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_293.class_5596;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.apache.commons.lang3.mutable.MutableInt;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class FontRenderer implements QuickImports {
   private final Object2ObjectMap GLYPH_PAGE_CACHE = new Object2ObjectOpenHashMap();
   private final ObjectList maps = new ObjectArrayList();
   private Font font;

   public FontRenderer(Font font, float sizePx) {
      this.init(font, sizePx);
   }

   private void init(Font font, float sizePx) {
      this.font = font.deriveFont(sizePx * 2.0F);
   }

   private GlyphMap generateMap(char from, char to) {
      GlyphMap glyphMap = new GlyphMap(from, to, this.font, randomIdentifier(), 5);
      this.maps.add(glyphMap);
      return glyphMap;
   }

   private Glyph locateGlyph(char glyph) {
      ObjectListIterator var2 = this.maps.iterator();

      while(var2.hasNext()) {
         GlyphMap map = (GlyphMap)var2.next();
         if (map.contains(glyph)) {
            return map.getGlyph(glyph);
         }
      }

      char base = (char)Calculate.floorNearestMulN(glyph, 128);
      return this.generateMap(base, (char)(base + 128)).getGlyph(glyph);
   }

   public void drawText(class_4587 matrix, class_2561 text, double x, double y) {
      StringBuilder sb = new StringBuilder();
      this.findStyle(sb, text);
      this.drawString(matrix, sb.toString(), x, y, ColorAssist.getText());
   }

   public void findStyle(StringBuilder sb, class_2561 component) {
      class_2583 style = component.method_10866();
      if (component.method_10855().isEmpty()) {
         if (style.method_10973() != null) {
            sb.append(ColorAssist.formatting(style.method_10973().method_27716()));
         }

         sb.append(component.getString()).append(class_124.field_1070);
      } else {
         component.method_36136(style).forEach((text) -> this.findStyle(sb, text));
      }

   }

   public void drawStringWithScroll(class_4587 matrix, String text, double x, double y, float width, int color) {
      String separation = "  |  ";
      float textWidth = this.getStringWidth(text + separation);
      if (textWidth - width < 10.0F) {
         this.drawString(matrix, text, x, y, color);
      } else {
         this.drawString(matrix, text + separation + text, x - (double)Calculate.textScrolling(textWidth), y, color);
      }
   }

   public void drawString(class_4587 matrix, String text, double x, double y, int color) {
      TextFactoryEvent event = new TextFactoryEvent(text);
      EventManager.callEvent(event);
      text = event.getText();
      char[] chars = text.toCharArray();
      float xOffset = 0.0F;
      float yOffset = 0.0F;
      int lineStart = 0;
      StringBuilder stringColor = new StringBuilder();
      boolean colorFormat = false;
      boolean textColor = false;
      int clr = color;

      for(int i = 0; i < chars.length; ++i) {
         char c = chars[i];
         if (c == 167) {
            colorFormat = true;
         } else if (colorFormat) {
            colorFormat = false;
            char c1 = Character.toUpperCase(c);
            if (ColorAssist.colorCodes.containsKey(c1)) {
               clr = (new Color(ColorAssist.colorCodes.get(c1))).getRGB();
            } else if (c1 == 'R') {
               clr = color;
            }
         } else if (c == 9167) {
            if (textColor) {
               clr = (new Color(Integer.parseInt(stringColor.toString()))).getRGB();
               stringColor.setLength(0);
            }

            textColor = !textColor;
         } else if (textColor) {
            stringColor.append(c);
         } else if (c == '\n') {
            yOffset += this.getStringHeight(text.substring(lineStart, i)) - 2.0F;
            xOffset = 0.0F;
            lineStart = i + 1;
         } else {
            Glyph glyph = this.locateGlyph(c);
            if (glyph != null) {
               if (glyph.value() != ' ') {
                  class_2960 i1 = glyph.owner().bindToTexture;
                  DrawEntry entry = new DrawEntry(xOffset, yOffset, clr, glyph);
                  ((ObjectList)this.GLYPH_PAGE_CACHE.computeIfAbsent(i1, (integer) -> new ObjectArrayList())).add(entry);
               }

               xOffset += (float)glyph.width();
            }
         }
      }

      if (!this.GLYPH_PAGE_CACHE.isEmpty()) {
         this.drawGlyphs(matrix, x, y);
      }

      this.GLYPH_PAGE_CACHE.clear();
   }

   public void drawGradientString(class_4587 matrix, String text, double x, double y, int colorStart, int colorEnd) {
      TextFactoryEvent event = new TextFactoryEvent(text);
      EventManager.callEvent(event);
      text = event.getText();
      char[] chars = text.toCharArray();
      float xOffset = 0.0F;
      float yOffset = 0.0F;
      int lineStart = 0;
      int textLength = text.length();

      for(int i = 0; i < chars.length; ++i) {
         char c = chars[i];
         if (c == '\n') {
            yOffset += this.getStringHeight(text.substring(lineStart, i)) - 2.0F;
            xOffset = 0.0F;
            lineStart = i + 1;
         } else {
            Glyph glyph = this.locateGlyph(c);
            if (glyph != null) {
               if (glyph.value() != ' ') {
                  float t = (float)i / (float)(textLength - 1);
                  int color = this.interpolateColor(colorStart, colorEnd, t);
                  class_2960 i1 = glyph.owner().bindToTexture;
                  DrawEntry entry = new DrawEntry(xOffset, yOffset, color, glyph);
                  ((ObjectList)this.GLYPH_PAGE_CACHE.computeIfAbsent(i1, (integer) -> new ObjectArrayList())).add(entry);
               }

               xOffset += (float)glyph.width();
            }
         }
      }

      if (!this.GLYPH_PAGE_CACHE.isEmpty()) {
         this.drawGlyphs(matrix, x, y);
      }

      this.GLYPH_PAGE_CACHE.clear();
   }

   private void drawGlyphs(class_4587 matrix, double x, double y) {
      matrix.method_22903();
      matrix.method_22904(x, y - (double)3.0F, (double)0.0F);
      matrix.method_22905(0.5F, 0.5F, 1.0F);
      Matrix4f matrix4f = matrix.method_23760().method_23761();
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.setShader(class_10142.field_53880);
      ObjectIterator var7 = this.GLYPH_PAGE_CACHE.keySet().iterator();

      while(var7.hasNext()) {
         class_2960 identifier = (class_2960)var7.next();
         RenderSystem.setShaderTexture(0, identifier);
         class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
         ObjectListIterator var10 = ((ObjectList)this.GLYPH_PAGE_CACHE.get(identifier)).iterator();

         while(var10.hasNext()) {
            DrawEntry drawEntry = (DrawEntry)var10.next();
            float x1 = drawEntry.atX();
            float y1 = drawEntry.atY();
            Glyph glyph = drawEntry.toDraw();
            GlyphMap glyphMap = glyph.owner();
            float width = (float)glyph.width();
            float height = (float)glyph.height();
            float u1 = (float)glyph.u() / (float)glyphMap.width;
            float v1 = (float)glyph.v() / (float)glyphMap.height;
            float u2 = (float)(glyph.u() + glyph.width()) / (float)glyphMap.width;
            float v2 = (float)(glyph.v() + glyph.height()) / (float)glyphMap.height;
            int color = drawEntry.color();
            buffer.method_22918(matrix4f, x1 + 0.0F, y1 + height, 0.0F).method_22913(u1, v2).method_39415(color);
            buffer.method_22918(matrix4f, x1 + width, y1 + height, 0.0F).method_22913(u2, v2).method_39415(color);
            buffer.method_22918(matrix4f, x1 + width, y1 + 0.0F, 0.0F).method_22913(u2, v1).method_39415(color);
            buffer.method_22918(matrix4f, x1 + 0.0F, y1 + 0.0F, 0.0F).method_22913(u1, v1).method_39415(color);
         }

         class_286.method_43433(buffer.method_60800());
      }

      RenderSystem.disableBlend();
      matrix.method_22909();
   }

   public void drawCenteredString(class_4587 stack, String s, double x, double y, int color) {
      this.drawString(stack, s, (double)((int)(x - (double)(this.getStringWidth(s) / 2.0F))), (double)((float)y), color);
   }

   public float getStringWidth(class_2561 text) {
      return text != null ? this.getStringWidth(text.getString()) : 0.0F;
   }

   public float getStringWidth(String text) {
      TextFactoryEvent event = new TextFactoryEvent(text);
      EventManager.callEvent(event);
      text = event.getText();
      float currentLine = 0.0F;
      float maxPreviousLines = 0.0F;
      boolean ignore = false;

      for(char c : text.toCharArray()) {
         if (ignore) {
            ignore = false;
         } else if (c == 167) {
            ignore = true;
         } else if (c == '\n') {
            maxPreviousLines = Math.max(currentLine, maxPreviousLines);
            currentLine = 0.0F;
         } else {
            Glyph glyph = this.locateGlyph(c);
            currentLine += glyph == null ? 0.0F : (float)glyph.width();
         }
      }

      return Math.max(currentLine, maxPreviousLines) / 2.0F;
   }

   public float getStringHeight(class_2561 text) {
      return this.getStringHeight(text.getString());
   }

   public float getStringHeight(String text) {
      float currentLine = 0.0F;
      float previous = 0.0F;

      for(char c : (text.isEmpty() ? " " : text).toCharArray()) {
         if (c == '\n') {
            currentLine = currentLine == 0.0F ? (float)this.locateGlyph(' ').height() : currentLine;
            previous += currentLine;
            currentLine = 0.0F;
         } else {
            Glyph glyph = this.locateGlyph(c);
            currentLine = Math.max(glyph == null ? 0.0F : (float)glyph.height(), currentLine);
         }
      }

      return currentLine + previous;
   }

   public String trimToWidth(String text, int maxWidth, boolean backwards) {
      return backwards ? this.trimToWidthBackwards(text, maxWidth) : this.trimToWidth(text, maxWidth);
   }

   public String trimToWidth(String text, int maxWidth) {
      return text.substring(0, this.getTrimmedLength(text, maxWidth));
   }

   private int getTrimmedLength(String text, int maxWidth) {
      WidthLimitingVisitor visitor = new WidthLimitingVisitor((float)maxWidth);
      this.visitForwards(text, visitor);
      return visitor.getLength();
   }

   public String trimToWidthBackwards(String text, int maxWidth) {
      MutableFloat widthLeft = new MutableFloat((float)maxWidth);
      MutableInt trimmedLength = new MutableInt(text.length());

      for(int i = text.length() - 1; i >= 0; --i) {
         char c = text.charAt(i);
         Glyph glyph = this.locateGlyph(c);
         if (glyph != null) {
            widthLeft.subtract((float)glyph.width());
            if (widthLeft.floatValue() < 0.0F) {
               trimmedLength.setValue(i + 1);
               break;
            }
         }
      }

      return text.substring(trimmedLength.intValue());
   }

   private boolean visitForwards(String text, CharacterVisitor visitor) {
      int length = text.length();

      for(int i = 0; i < length; ++i) {
         char c = text.charAt(i);
         if (!visitor.accept(i, c)) {
            return false;
         }
      }

      return true;
   }

   private int interpolateColor(int colorStart, int colorEnd, float t) {
      float startAlpha = (float)(colorStart >> 24 & 255) / 255.0F;
      float startRed = (float)(colorStart >> 16 & 255) / 255.0F;
      float startGreen = (float)(colorStart >> 8 & 255) / 255.0F;
      float startBlue = (float)(colorStart & 255) / 255.0F;
      float endAlpha = (float)(colorEnd >> 24 & 255) / 255.0F;
      float endRed = (float)(colorEnd >> 16 & 255) / 255.0F;
      float endGreen = (float)(colorEnd >> 8 & 255) / 255.0F;
      float endBlue = (float)(colorEnd & 255) / 255.0F;
      float alpha = startAlpha + t * (endAlpha - startAlpha);
      float red = startRed + t * (endRed - startRed);
      float green = startGreen + t * (endGreen - startGreen);
      float blue = startBlue + t * (endBlue - startBlue);
      return (int)(alpha * 255.0F) << 24 | (int)(red * 255.0F) << 16 | (int)(green * 255.0F) << 8 | (int)(blue * 255.0F);
   }

   @Contract(
      value = "-> new",
      pure = true
   )
   public static @NotNull class_2960 randomIdentifier() {
      return class_2960.method_60655("Yanderov", "temp/" + StringHelper.randomString(32));
   }

   @Contract(
      value = "_ -> new",
      pure = true
   )
   public static int @NotNull [] RGBIntToRGB(int in) {
      int red = in >> 16 & 255;
      int green = in >> 8 & 255;
      int blue = in & 255;
      return new int[]{red, green, blue};
   }

   public FontRenderer setFont(Font font) {
      this.font = font;
      return this;
   }

   public Font getFont() {
      return this.font;
   }

   private class WidthLimitingVisitor implements CharacterVisitor {
      private float widthLeft;
      private int length;

      public WidthLimitingVisitor(float maxWidth) {
         this.widthLeft = maxWidth;
      }

      public boolean accept(int index, char c) {
         Glyph glyph = FontRenderer.this.locateGlyph(c);
         if (glyph != null) {
            this.widthLeft -= (float)glyph.width();
            if (this.widthLeft >= 0.0F) {
               this.length = index + 1;
               return true;
            }
         }

         return false;
      }

      public int getLength() {
         return this.length;
      }
   }

   @FunctionalInterface
   public interface CharacterVisitor {
      boolean accept(int var1, char var2);
   }
}

