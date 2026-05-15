package fun.Yanderov.features.impl.render;

import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import fun.Yanderov.common.repository.friend.FriendUtils;
import fun.Yanderov.events.render.WorldRenderEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.color.ColorAssist;
import fun.Yanderov.utils.math.calc.CalcVector;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_10142;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

public class FriendCrystal extends Module {
   private SliderSettings size = (new SliderSettings("Ð Ð°Ð·Ð¼ÐµÑ€", "Ð Ð°Ð·Ð¼ÐµÑ€ ÐºÑ€Ð¸ÑÑ‚Ð°Ð»Ð»Ð°")).setValue(0.12F).range(0.03F, 0.4F);
   private SliderSettings height = (new SliderSettings("Ð’Ñ‹ÑÐ¾Ñ‚Ð°", "Ð’Ñ‹ÑÐ¾Ñ‚Ð° ÐºÑ€Ð¸ÑÑ‚Ð°Ð»Ð»Ð° Ð½Ð°Ð´ Ð³Ð¾Ð»Ð¾Ð²Ð¾Ð¹")).setValue(0.6F).range(0.1F, 2.0F);

   public static FriendCrystal getInstance() {
      return (FriendCrystal)Instance.get(FriendCrystal.class);
   }

   public FriendCrystal() {
      super("Friend Crystal", "Friend Crystal", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.size, this.height});
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         class_4587 ms = e.getStack();
         class_4184 camera = mc.field_1773.method_19418();

         for(class_1657 player : mc.field_1687.method_18456()) {
            if (player != mc.field_1724 && FriendUtils.isFriend((class_1297)player)) {
               class_243 pos = CalcVector.lerpPosition(player);
               float crystalSize = this.size.getValue();
               float crystalHeight = this.height.getValue();
               ms.method_22903();
               ms.method_22904(pos.field_1352, pos.field_1351 + (double)player.method_17682() + (double)crystalHeight, pos.field_1350);
               RenderSystem.disableCull();
               RenderSystem.enableBlend();
               RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA);
               RenderSystem.setShader(class_10142.field_53876);
               int baseColor = (new Color(60, 255, 140, 230)).getRGB();
               this.drawCrystal(ms, baseColor, crystalSize, 0.3F, true);
               this.drawCrystal(ms, baseColor, crystalSize, 0.8F, false);
               RenderSystem.disableBlend();
               RenderSystem.enableCull();
               ms.method_22909();
            }
         }

      }
   }

   private void drawCrystal(class_4587 ms, int baseColor, float size, float alpha, boolean filled) {
      class_287 bufferBuilder = class_289.method_1348().method_60827(filled ? class_5596.field_27379 : class_5596.field_29344, class_290.field_1576);
      float s = size;
      float hPrism = size * 1.0F;
      float hPyramid = size * 1.5F;
      int numSides = 8;
      List<class_243> topVertices = new ArrayList();
      List<class_243> bottomVertices = new ArrayList();

      for(int i = 0; i < numSides; ++i) {
         float angle = (float)((Math.PI * 2D) * (double)i / (double)numSides);
         float x = (float)((double)s * Math.cos((double)angle));
         float z = (float)((double)s * Math.sin((double)angle));
         topVertices.add(new class_243((double)x, (double)(hPrism / 2.0F), (double)z));
         bottomVertices.add(new class_243((double)x, (double)(-hPrism / 2.0F), (double)z));
      }

      class_243 vTop = new class_243((double)0.0F, (double)(hPrism / 2.0F + hPyramid), (double)0.0F);
      class_243 vBottom = new class_243((double)0.0F, (double)(-hPrism / 2.0F - hPyramid), (double)0.0F);
      int finalColor = ColorAssist.setAlpha(baseColor, (int)(alpha * 255.0F));

      for(int i = 0; i < numSides; ++i) {
         class_243 v1 = (class_243)bottomVertices.get(i);
         class_243 v2 = (class_243)bottomVertices.get((i + 1) % numSides);
         class_243 v3 = (class_243)topVertices.get((i + 1) % numSides);
         class_243 v4 = (class_243)topVertices.get(i);
         this.drawQuad(ms, bufferBuilder, v1, v2, v3, v4, finalColor, filled);
      }

      for(int i = 0; i < numSides; ++i) {
         class_243 v1 = (class_243)topVertices.get(i);
         class_243 v2 = (class_243)topVertices.get((i + 1) % numSides);
         this.drawTriangle(ms, bufferBuilder, vTop, v1, v2, finalColor, filled);
      }

      for(int i = 0; i < numSides; ++i) {
         class_243 v1 = (class_243)bottomVertices.get(i);
         class_243 v2 = (class_243)bottomVertices.get((i + 1) % numSides);
         this.drawTriangle(ms, bufferBuilder, vBottom, v2, v1, finalColor, filled);
      }

      class_286.method_43433(bufferBuilder.method_60800());
   }

   private void drawTriangle(class_4587 ms, class_287 bb, class_243 v1, class_243 v2, class_243 v3, int color, boolean filled) {
      if (filled) {
         Matrix4f mat = ms.method_23760().method_23761();
         bb.method_22918(mat, (float)v1.field_1352, (float)v1.field_1351, (float)v1.field_1350).method_39415(color);
         bb.method_22918(mat, (float)v2.field_1352, (float)v2.field_1351, (float)v2.field_1350).method_39415(color);
         bb.method_22918(mat, (float)v3.field_1352, (float)v3.field_1351, (float)v3.field_1350).method_39415(color);
      }
   }

   private void drawQuad(class_4587 ms, class_287 bb, class_243 v1, class_243 v2, class_243 v3, class_243 v4, int color, boolean filled) {
      if (filled) {
         this.drawTriangle(ms, bb, v1, v2, v3, color, true);
         this.drawTriangle(ms, bb, v1, v3, v4, color, true);
      } else {
         Matrix4f mat = ms.method_23760().method_23761();
         bb.method_22918(mat, (float)v1.field_1352, (float)v1.field_1351, (float)v1.field_1350).method_39415(color);
         bb.method_22918(mat, (float)v2.field_1352, (float)v2.field_1351, (float)v2.field_1350).method_39415(color);
         bb.method_22918(mat, (float)v3.field_1352, (float)v3.field_1351, (float)v3.field_1350).method_39415(color);
         bb.method_22918(mat, (float)v4.field_1352, (float)v4.field_1351, (float)v4.field_1350).method_39415(color);
      }

   }
}

