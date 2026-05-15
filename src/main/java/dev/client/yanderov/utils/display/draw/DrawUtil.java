package dev.client.yanderov.utils.display.draw;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.client.yanderov.utils.display.color.ColorRGBA;
import dev.client.yanderov.utils.display.shape.BorderRadius;
import net.minecraft.class_10142;
import net.minecraft.class_276;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

public class DrawUtil {
   private static final class_310 mc = class_310.method_1551();

   public static void drawLiquidRect(class_4587 matrices, float x, float y, float width, float height, BorderRadius borderRadius, ColorRGBA color, float cornerSmoothness, float fresnelPower, float fresnelAlpha, float baseAlpha, boolean fresnelInvert, float fresnelMix, float distortStrength) {
      if (mc != null && mc.method_1522() != null) {
         matrices.method_22903();
         Matrix4f matrix4f = matrices.method_23760().method_23761();
         class_276 screenFBO = mc.method_1522();
         int screenTexture = screenFBO.method_30277();
         RenderSystem.setShaderTexture(0, screenTexture);
         RenderSystem.setShader(class_10142.field_53880);
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableDepthTest();
         float scaleX = (float)screenFBO.field_1482 / (float)mc.method_22683().method_4486();
         float scaleY = (float)screenFBO.field_1481 / (float)mc.method_22683().method_4502();
         float fx = x * scaleX;
         float fy = y * scaleY;
         float fwidth = width * scaleX;
         float fheight = height * scaleY;
         fy = (float)screenFBO.field_1481 - fy - fheight;
         float u0 = fx / (float)screenFBO.field_1482;
         float v0 = fy / (float)screenFBO.field_1481;
         float u1 = (fx + fwidth) / (float)screenFBO.field_1482;
         float v1 = (fy + fheight) / (float)screenFBO.field_1481;
         class_287 builder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
         int argb = color.toARGB();
         int a = argb >> 24 & 255;
         int r = argb >> 16 & 255;
         int g = argb >> 8 & 255;
         int b = argb & 255;
         builder.method_22918(matrix4f, x, y, 0.0F).method_22913(u0, v1).method_1336(r, g, b, a);
         builder.method_22918(matrix4f, x, y + height, 0.0F).method_22913(u0, v0).method_1336(r, g, b, a);
         builder.method_22918(matrix4f, x + width, y + height, 0.0F).method_22913(u1, v0).method_1336(r, g, b, a);
         builder.method_22918(matrix4f, x + width, y, 0.0F).method_22913(u1, v1).method_1336(r, g, b, a);
         class_286.method_43433(builder.method_60800());
         RenderSystem.enableDepthTest();
         RenderSystem.disableBlend();
         matrices.method_22909();
      }
   }
}

