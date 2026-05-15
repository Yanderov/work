package fun.Yanderov.utils.display.draw;

import com.mojang.blaze3d.systems.RenderSystem;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import net.minecraft.class_10142;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4587;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;
import org.joml.Vector4i;

public class DrawEngineImpl implements DrawEngine, QuickImports {
   public void quad(Matrix4f matrix4f, class_287 buffer, float x, float y, float width, float height) {
      buffer.method_22918(matrix4f, x, y, 0.0F);
      buffer.method_22918(matrix4f, x, y + height, 0.0F);
      buffer.method_22918(matrix4f, x + width, y + height, 0.0F);
      buffer.method_22918(matrix4f, x + width, y, 0.0F);
   }

   public void quad(Matrix4f matrix4f, class_287 buffer, float x, float y, float width, float height, int color) {
      buffer.method_22918(matrix4f, x, y, 0.0F).method_39415(color);
      buffer.method_22918(matrix4f, x, y + height, 0.0F).method_39415(color);
      buffer.method_22918(matrix4f, x + width, y + height, 0.0F).method_39415(color);
      buffer.method_22918(matrix4f, x + width, y, 0.0F).method_39415(color);
   }

   public void quad(Matrix4f matrix4f, float x, float y, float width, float height, int color) {
      RenderSystem.setShader(class_10142.field_53880);
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
      buffer.method_22918(matrix4f, x, y + height, 0.0F).method_22913(0.0F, 0.0F).method_39415(color);
      buffer.method_22918(matrix4f, x + width, y + height, 0.0F).method_22913(0.0F, 1.0F).method_39415(color);
      buffer.method_22918(matrix4f, x + width, y, 0.0F).method_22913(1.0F, 1.0F).method_39415(color);
      buffer.method_22918(matrix4f, x, y, 0.0F).method_22913(1.0F, 0.0F).method_39415(color);
      class_286.method_43433(buffer.method_60800());
   }

   public void quadTexture(class_4587.class_4665 entry, class_287 buffer, float x, float y, float width, float height, Vector4i color) {
      buffer.method_56824(entry, x, y + height, 0.0F).method_22913(0.0F, 0.0F).method_39415(color.x);
      buffer.method_56824(entry, x + width, y + height, 0.0F).method_22913(0.0F, 1.0F).method_39415(color.y);
      buffer.method_56824(entry, x + width, y, 0.0F).method_22913(1.0F, 1.0F).method_39415(color.w);
      buffer.method_56824(entry, x, y, 0.0F).method_22913(1.0F, 0.0F).method_39415(color.z);
   }
}

