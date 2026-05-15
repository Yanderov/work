package dev.client.yanderov.features.impl.render;

import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.client.yanderov.Yanderov;
import dev.client.yanderov.common.animation.Animation;
import dev.client.yanderov.common.animation.Direction;
import dev.client.yanderov.common.animation.implement.Decelerate;
import dev.client.yanderov.events.player.RotationUpdateEvent;
import dev.client.yanderov.events.render.WorldRenderEvent;
import dev.client.yanderov.features.impl.combat.Aura;
import dev.client.yanderov.features.impl.combat.TriggerBot;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.ColorSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.utils.client.Instance;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.geometry.Render3D;
import dev.client.yanderov.utils.features.aura.striking.StrikeManager;
import dev.client.yanderov.utils.math.calc.CalcVector;
import dev.client.yanderov.utils.math.time.StopWatch;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_10142;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;
import org.joml.Vector4i;

public class TargetESP extends Module {
   private Animation esp_anim = (new Decelerate()).setMs(400).setValue((double)1.0F);
   private SelectSetting targetEspType = (new SelectSetting("ÐžÑ‚Ð¾Ð±Ñ€Ð°Ð¶ÐµÐ½Ð¸Ñ Ñ‚Ð°Ñ€Ð³ÐµÑ‚Ð°", "Ð’Ñ‹Ð±Ð¸Ñ€Ð°ÐµÑ‚ Ñ‚Ð¸Ð¿ Ñ†ÐµÐ»Ð¸ esp")).value("Cube", "Circle", "Ghosts", "Crystals").selected("Circle");
   private SelectSetting cubeType = (new SelectSetting("ÐšÐ°Ñ€Ñ‚Ð¸Ð½ÐºÐ° Ð´Ð»Ñ ÐºÑƒÐ±Ð°", "Ð’Ñ‹Ð±Ð¸Ñ€Ð°ÐµÑ‚ Ñ‚Ð¸Ð¿ ÐºÑƒÐ±Ð°")).value("1", "2", "3", "4", "5").visible(() -> this.targetEspType.isSelected("Cube"));
   public ColorSetting colorSetting = (new ColorSetting("Ð¦Ð²ÐµÑ‚", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ñ†Ð²ÐµÑ‚ Ð´Ð»Ñ esp")).setColor((new Color(255, 101, 57, 255)).getRGB());
   private class_1297 lastRenderedTarget = null;
   private final List crystalList = new ArrayList();
   private float rotationAngle = 0.0F;

   public static TargetESP getInstance() {
      return (TargetESP)Instance.get(TargetESP.class);
   }

   public TargetESP() {
      super("TargetEsp", "Target Esp", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.targetEspType, this.cubeType, this.colorSetting});
   }

   @EventHandler
   public void onRotationUpdate(RotationUpdateEvent e) {
      if (e.getType() == 2) {
         Render3D.updateTargetEsp();
      }

   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      StrikeManager attackHandler = YanderovIntegration.getInstance().getAttackPerpetrator().getAttackHandler();
      StopWatch attackTimer = attackHandler.getAttackTimer();
      class_1309 currentTarget = null;
      class_1309 lastTarget = null;
      if (Aura.getInstance().isState()) {
         currentTarget = Aura.getInstance().getTarget();
         lastTarget = Aura.getInstance().getLastTarget();
      } else if (TriggerBot.getInstance().isState()) {
         currentTarget = TriggerBot.getInstance().target;
         lastTarget = TriggerBot.getInstance().target;
      }

      this.esp_anim.setDirection(currentTarget != null ? Direction.FORWARDS : Direction.BACKWARDS);
      float anim = this.esp_anim.getOutput().floatValue();
      if (lastTarget != null && !this.esp_anim.isFinished(Direction.BACKWARDS)) {
         float red = 0.0F;
         switch (this.targetEspType.getSelected()) {
            case "Cube":
               Render3D.drawCube(lastTarget, anim, red, this.cubeType.getSelected());
               break;
            case "Circle":
               Render3D.drawCircle(e.getStack(), lastTarget, anim, red);
               break;
            case "Ghosts":
               Render3D.drawGhosts(lastTarget, anim, red, 0.62F);
               break;
            case "Crystals":
               if (this.crystalList.isEmpty() || lastTarget != this.lastRenderedTarget) {
                  this.createCrystals(lastTarget);
                  this.lastRenderedTarget = lastTarget;
               }

               this.renderCrystals(e.getStack(), lastTarget, anim, red);
               break;
            case "Ring":
               this.renderRing(e.getStack(), lastTarget, anim);
         }
      }

   }

   private void createCrystals(class_1297 target) {
      this.crystalList.clear();
      this.crystalList.add(new Crystal(target, new class_243((double)0.0F, 0.85, 0.8), new class_243((double)-49.0F, (double)0.0F, (double)40.0F)));
      this.crystalList.add(new Crystal(target, new class_243(0.2, 0.85, -0.675), new class_243((double)35.0F, (double)0.0F, (double)-30.0F)));
      this.crystalList.add(new Crystal(target, new class_243(0.6, 1.35, 0.6), new class_243((double)-30.0F, (double)0.0F, (double)35.0F)));
      this.crystalList.add(new Crystal(target, new class_243(-0.74, 1.05, 0.4), new class_243((double)-25.0F, (double)0.0F, (double)-30.0F)));
      this.crystalList.add(new Crystal(target, new class_243(0.74, 0.95, -0.4), new class_243((double)0.0F, (double)0.0F, (double)0.0F)));
      this.crystalList.add(new Crystal(target, new class_243(-0.475, 0.85, (double)-0.375F), new class_243((double)30.0F, (double)0.0F, (double)-25.0F)));
      this.crystalList.add(new Crystal(target, new class_243((double)0.0F, 1.35, -0.6), new class_243((double)45.0F, (double)0.0F, (double)0.0F)));
      this.crystalList.add(new Crystal(target, new class_243(0.85, 0.7, 0.1), new class_243((double)-30.0F, (double)0.0F, (double)30.0F)));
      this.crystalList.add(new Crystal(target, new class_243(-0.7, 1.35, -0.3), new class_243((double)0.0F, (double)0.0F, (double)0.0F)));
      this.crystalList.add(new Crystal(target, new class_243(-0.3, 1.35, 0.55), new class_243((double)0.0F, (double)0.0F, (double)0.0F)));
      this.crystalList.add(new Crystal(target, new class_243((double)-0.5F, 0.7, 0.7), new class_243((double)0.0F, (double)0.0F, (double)0.0F)));
      this.crystalList.add(new Crystal(target, new class_243((double)0.5F, 0.7, 0.7), new class_243((double)0.0F, (double)0.0F, (double)0.0F)));
      this.crystalList.add(new Crystal(target, new class_243(-0.7, (double)0.75F, (double)0.0F), new class_243((double)0.0F, (double)0.0F, (double)0.0F)));
      this.crystalList.add(new Crystal(target, new class_243(-0.2, 0.65, -0.7), new class_243((double)0.0F, (double)0.0F, (double)0.0F)));
   }

   private void renderCrystals(class_4587 ms, class_1297 target, float anim, float red) {
      if (target != null && !this.crystalList.isEmpty()) {
         RenderSystem.enableDepthTest();
         class_243 targetPos = CalcVector.lerpPosition(target);
         this.rotationAngle = (this.rotationAngle + 0.5F) % 360.0F;
         ms.method_22903();
         ms.method_22904(targetPos.field_1352, targetPos.field_1351, targetPos.field_1350);
         ms.method_22907(class_7833.field_40716.rotationDegrees(this.rotationAngle));
         class_4184 camera = mc.field_1773.method_19418();

         for(Crystal crystal : this.crystalList) {
            crystal.render(ms, anim, red, camera);
         }

         ms.method_22909();
         RenderSystem.enableDepthTest();
      }
   }

   private void renderRing(class_4587 ignored, class_1309 target, float anim) {
      if (target != null && mc != null && mc.field_1687 != null) {
         class_4184 camera = mc.method_1561().field_4686;
         class_243 targetPos = CalcVector.lerpPosition(target).method_1020(camera.method_19326());
         float height = target.method_17682();
         float width = target.method_17681();
         long time = System.currentTimeMillis();
         float t = (float)(time % 2000L) / 2000.0F;
         int baseColor = this.colorSetting.getColor();
         int steps = 10;
         int segments = 18;

         for(int yStep = 0; yStep <= steps; ++yStep) {
            float fracY = (float)yStep / (float)steps;
            float yPos = fracY * height;
            float layerAlphaMul = 0.4F + 0.6F * fracY;
            float radiusBase = Math.max(width * 1.1F, 0.5F);
            float radius = radiusBase * (1.0F + 0.12F * (float)Math.sin((double)(t + fracY) * Math.PI * (double)2.0F));
            float angleOffset = (t + fracY * 0.5F) * 360.0F;

            for(int i = 0; i < segments; ++i) {
               float angleDeg = 360.0F / (float)segments * (float)i + angleOffset;
               double rad = Math.toRadians((double)angleDeg);
               double px = targetPos.field_1352 + Math.cos(rad) * (double)radius;
               double pz = targetPos.field_1350 + Math.sin(rad) * (double)radius;
               class_4587 matrices = new class_4587();
               matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
               matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
               matrices.method_22904(px, targetPos.field_1351 + (double)yPos, pz);
               matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
               matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
               class_4587.class_4665 entry = matrices.method_23760().method_56822();
               float baseScale = 0.35F;
               float scalePulse = 0.15F * (float)Math.sin((double)time / (double)250.0F + (double)fracY * Math.PI * (double)2.0F);
               float scale = baseScale + scalePulse;
               float ringFade = 0.7F + 0.3F * (float)Math.sin((double)time / (double)300.0F + (double)(angleDeg * 0.1F));
               float alpha = class_3532.method_15363(0.6F * layerAlphaMul * ringFade * anim, 0.05F, 1.0F);
               int color = ColorAssist.setAlpha(baseColor, (int)(alpha * 255.0F));
               Render3D.drawTexture(entry, class_2960.method_60654("textures/features/particles/bloom.png"), -scale / 2.0F, -scale / 2.0F, scale, scale, new Vector4i(color), true);
            }
         }

      }
   }

   private void renderFire(class_4587 ms, class_1309 target, float anim, float red) {
      if (target != null && mc != null && mc.field_1687 != null) {
         class_243 base = CalcVector.lerpPosition(target);
         float height = target.method_17682();
         float maxY = height * 0.9F;
         long time = System.currentTimeMillis();
         RenderSystem.enableDepthTest();
         RenderSystem.enableBlend();
         RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA);
         RenderSystem.setShader(class_10142.field_53876);
         int baseColor = ColorAssist.interpolateColor(this.colorSetting.getColor(), (new Color(255, 140, 40)).getRGB(), red);
         ms.method_22903();
         ms.method_22904(base.field_1352, base.field_1351, base.field_1350);
         int layers = 3;

         for(int i = 0; i < layers; ++i) {
            float phase = (float)time / 350.0F + (float)i * 0.35F;
            float ft = phase - (float)Math.floor((double)phase);
            float y = ft * maxY;
            float fade = 1.0F - Math.abs(ft - 0.5F) * 2.0F;
            fade = class_3532.method_15363(fade, 0.0F, 1.0F);
            if (!(fade <= 0.02F)) {
               float localAnim = anim * fade;
               this.drawFireCube(ms, y, localAnim, baseColor, time + (long)i * 1234L);
            }
         }

         ms.method_22909();
         RenderSystem.disableBlend();
      }
   }

   private void drawFireCube(class_4587 ms, float y, float anim, int baseColor, long time) {
      float baseSize = 0.45F;
      float jitterAmp = baseSize * 0.09F;
      float edgeAlpha = 0.9F * anim;
      float fillAlpha = 0.22F * anim;
      int edgeColor = ColorAssist.setAlpha(baseColor, (int)(edgeAlpha * 255.0F));
      int fillColor = ColorAssist.setAlpha(baseColor, (int)(fillAlpha * 255.0F));
      class_243[] corners = new class_243[8];

      for(int i = 0; i < 8; ++i) {
         float sx = (i & 1) == 0 ? -1.0F : 1.0F;
         float sy = (i & 2) == 0 ? -1.0F : 1.0F;
         float sz = (i & 4) == 0 ? -1.0F : 1.0F;
         float j = (float)Math.sin((double)time * 0.003 + (double)i * 0.8) * jitterAmp;
         float k = (float)Math.cos((double)time * 0.0024 + (double)i * 0.6) * jitterAmp * 0.7F;
         float sizeX = baseSize + j;
         float sizeY = baseSize + k * 0.6F;
         float sizeZ = baseSize + j * 0.8F;
         float px = sx * sizeX;
         float py = sy * sizeY + y;
         float pz = sz * sizeZ;
         corners[i] = new class_243((double)px, (double)py, (double)pz);
      }

      class_287 fillBuf = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1576);
      Matrix4f m = ms.method_23760().method_23761();
      class_243 c000 = corners[0];
      class_243 c100 = corners[1];
      class_243 c010 = corners[2];
      class_243 c110 = corners[3];
      class_243 c001 = corners[4];
      class_243 c101 = corners[5];
      class_243 c011 = corners[6];
      class_243 c111 = corners[7];
      this.quad(fillBuf, m, c000, c100, c110, c010, fillColor);
      this.quad(fillBuf, m, c001, c011, c111, c101, fillColor);
      this.quad(fillBuf, m, c000, c001, c101, c100, fillColor);
      this.quad(fillBuf, m, c010, c110, c111, c011, fillColor);
      this.quad(fillBuf, m, c000, c010, c011, c001, fillColor);
      this.quad(fillBuf, m, c100, c101, c111, c110, fillColor);
      class_286.method_43433(fillBuf.method_60800());
      class_287 edgeBuf = class_289.method_1348().method_60827(class_5596.field_27377, class_290.field_1576);
      this.line(edgeBuf, m, c000, c100, edgeColor);
      this.line(edgeBuf, m, c000, c010, edgeColor);
      this.line(edgeBuf, m, c000, c001, edgeColor);
      this.line(edgeBuf, m, c111, c101, edgeColor);
      this.line(edgeBuf, m, c111, c110, edgeColor);
      this.line(edgeBuf, m, c111, c011, edgeColor);
      this.line(edgeBuf, m, c100, c101, edgeColor);
      this.line(edgeBuf, m, c010, c011, edgeColor);
      this.line(edgeBuf, m, c001, c011, edgeColor);
      this.line(edgeBuf, m, c100, c110, edgeColor);
      this.line(edgeBuf, m, c010, c110, edgeColor);
      this.line(edgeBuf, m, c001, c101, edgeColor);
      class_243 center = new class_243((double)0.0F, (double)y, (double)0.0F);
      float centerAlpha = 0.95F * anim;
      int centerColor = ColorAssist.setAlpha(baseColor, (int)(centerAlpha * 255.0F));
      this.line(edgeBuf, m, center, c000, centerColor);
      this.line(edgeBuf, m, center, c100, centerColor);
      this.line(edgeBuf, m, center, c010, centerColor);
      this.line(edgeBuf, m, center, c110, centerColor);
      this.line(edgeBuf, m, center, c001, centerColor);
      this.line(edgeBuf, m, center, c101, centerColor);
      this.line(edgeBuf, m, center, c011, centerColor);
      this.line(edgeBuf, m, center, c111, centerColor);
      class_286.method_43433(edgeBuf.method_60800());
   }

   private void quad(class_287 bb, Matrix4f m, class_243 a, class_243 b, class_243 c, class_243 d, int color) {
      bb.method_22918(m, (float)a.field_1352, (float)a.field_1351, (float)a.field_1350).method_39415(color);
      bb.method_22918(m, (float)b.field_1352, (float)b.field_1351, (float)b.field_1350).method_39415(color);
      bb.method_22918(m, (float)c.field_1352, (float)c.field_1351, (float)c.field_1350).method_39415(color);
      bb.method_22918(m, (float)d.field_1352, (float)d.field_1351, (float)d.field_1350).method_39415(color);
   }

   private void line(class_287 bb, Matrix4f m, class_243 a, class_243 b, int color) {
      bb.method_22918(m, (float)a.field_1352, (float)a.field_1351, (float)a.field_1350).method_39415(color);
      bb.method_22918(m, (float)b.field_1352, (float)b.field_1351, (float)b.field_1350).method_39415(color);
   }

   private static class Crystal {
      private final class_1297 entity;
      private final class_243 position;
      private final class_243 rotation;
      private final float size;
      private final float rotationSpeed;

      public Crystal(class_1297 entity, class_243 position, class_243 rotation) {
         this.entity = entity;
         this.position = position;
         this.rotation = rotation;
         this.size = 0.05F;
         this.rotationSpeed = 0.5F + (float)(Math.random() * (double)1.5F);
      }

      public void render(class_4587 ms, float anim, float red, class_4184 camera) {
         ms.method_22903();
         ms.method_22904(this.position.field_1352, this.position.field_1351, this.position.field_1350);
         float pulsation = 1.0F + (float)(Math.sin((double)System.currentTimeMillis() / (double)500.0F) * (double)0.1F);
         ms.method_22905(pulsation, pulsation, pulsation);
         float selfRotation = (float)(System.currentTimeMillis() % 36000L) / 100.0F * this.rotationSpeed;
         ms.method_22907(class_7833.field_40714.rotationDegrees((float)this.rotation.field_1352));
         ms.method_22907(class_7833.field_40716.rotationDegrees((float)this.rotation.field_1351 + selfRotation));
         ms.method_22907(class_7833.field_40718.rotationDegrees((float)this.rotation.field_1350));
         RenderSystem.disableCull();
         RenderSystem.enableBlend();
         RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA);
         RenderSystem.setShader(class_10142.field_53876);
         int baseColor = ColorAssist.interpolateColor(TargetESP.getInstance().colorSetting.getColor(), (new Color(255, 0, 0)).getRGB(), red);
         RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
         this.drawCrystal(ms, baseColor, 0.2F, true, anim);
         RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA);
         this.drawCrystal(ms, baseColor, 0.3F, true, anim);
         this.drawCrystal(ms, baseColor, 0.8F, false, anim);
         RenderSystem.depthMask(false);
         RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
         ms.method_22903();
         ms.method_22905(1.2F, 1.2F, 1.2F);
         this.drawCrystal(ms, baseColor, 0.3F, true, anim);
         ms.method_22909();
         this.drawBloomSphere(ms, baseColor, anim, camera);
         RenderSystem.depthMask(true);
         RenderSystem.disableBlend();
         RenderSystem.enableCull();
         ms.method_22909();
      }

      private void drawBloomSphere(class_4587 ms, int baseColor, float anim, class_4184 camera) {
         RenderSystem.setShader(class_10142.field_53880);
         RenderSystem.setShaderTexture(0, class_2960.method_60654("textures/features/particles/bloom.png"));
         RenderSystem.enableBlend();
         RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
         RenderSystem.depthMask(false);
         int bloomColor = ColorAssist.setAlpha(baseColor, (int)(10.0F * anim));
         float bloomSize = this.size * 13.0F;
         float pitch = camera.method_19329();
         float yaw = camera.method_19330();
         int segments = 6;

         for(int i = 0; i < segments; ++i) {
            ms.method_22903();
            float angle = 360.0F / (float)segments * (float)i;
            ms.method_22907(class_7833.field_40716.rotationDegrees(angle));
            ms.method_22907(class_7833.field_40716.rotationDegrees(-yaw));
            ms.method_22907(class_7833.field_40714.rotationDegrees(pitch));
            Matrix4f matrix = ms.method_23760().method_23761();
            class_287 bufferBuilder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
            bufferBuilder.method_22918(matrix, -bloomSize / 2.0F, -bloomSize / 2.0F, 0.0F).method_22913(0.0F, 1.0F).method_39415(bloomColor);
            bufferBuilder.method_22918(matrix, bloomSize / 2.0F, -bloomSize / 2.0F, 0.0F).method_22913(1.0F, 1.0F).method_39415(bloomColor);
            bufferBuilder.method_22918(matrix, bloomSize / 2.0F, bloomSize / 2.0F, 0.0F).method_22913(1.0F, 0.0F).method_39415(bloomColor);
            bufferBuilder.method_22918(matrix, -bloomSize / 2.0F, bloomSize / 2.0F, 0.0F).method_22913(0.0F, 0.0F).method_39415(bloomColor);
            class_286.method_43433(bufferBuilder.method_60800());
            ms.method_22909();
         }

         for(int i = 0; i < segments; ++i) {
            ms.method_22903();
            float angle = 360.0F / (float)segments * (float)i;
            ms.method_22907(class_7833.field_40714.rotationDegrees(90.0F));
            ms.method_22907(class_7833.field_40716.rotationDegrees(angle));
            ms.method_22907(class_7833.field_40716.rotationDegrees(-yaw));
            ms.method_22907(class_7833.field_40714.rotationDegrees(pitch));
            Matrix4f matrix = ms.method_23760().method_23761();
            class_287 bufferBuilder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
            bufferBuilder.method_22918(matrix, -bloomSize / 2.0F, -bloomSize / 2.0F, 0.0F).method_22913(0.0F, 1.0F).method_39415(bloomColor);
            bufferBuilder.method_22918(matrix, bloomSize / 2.0F, -bloomSize / 2.0F, 0.0F).method_22913(1.0F, 1.0F).method_39415(bloomColor);
            bufferBuilder.method_22918(matrix, bloomSize / 2.0F, bloomSize / 2.0F, 0.0F).method_22913(1.0F, 0.0F).method_39415(bloomColor);
            bufferBuilder.method_22918(matrix, -bloomSize / 2.0F, bloomSize / 2.0F, 0.0F).method_22913(0.0F, 0.0F).method_39415(bloomColor);
            class_286.method_43433(bufferBuilder.method_60800());
            ms.method_22909();
         }

         RenderSystem.depthMask(true);
         RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA);
      }

      private void drawCrystal(class_4587 ms, int baseColor, float alpha, boolean filled, float anim) {
         class_287 bufferBuilder = class_289.method_1348().method_60827(filled ? class_5596.field_27379 : class_5596.field_29344, class_290.field_1576);
         float s = this.size;
         float h_prism = this.size * 1.0F;
         float h_pyramid = this.size * 1.5F;
         int numSides = 8;
         List<class_243> topVertices = new ArrayList();
         List<class_243> bottomVertices = new ArrayList();

         for(int i = 0; i < numSides; ++i) {
            float angle = (float)((Math.PI * 2D) * (double)i / (double)numSides);
            float x = (float)((double)s * Math.cos((double)angle));
            float z = (float)((double)s * Math.sin((double)angle));
            topVertices.add(new class_243((double)x, (double)(h_prism / 2.0F), (double)z));
            bottomVertices.add(new class_243((double)x, (double)(-h_prism / 2.0F), (double)z));
         }

         class_243 vTop = new class_243((double)0.0F, (double)(h_prism / 2.0F + h_pyramid), (double)0.0F);
         class_243 vBottom = new class_243((double)0.0F, (double)(-h_prism / 2.0F - h_pyramid), (double)0.0F);
         int finalColor = ColorAssist.setAlpha(baseColor, (int)(alpha * 255.0F * anim));

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
            bb.method_22918(ms.method_23760().method_23761(), (float)v1.field_1352, (float)v1.field_1351, (float)v1.field_1350).method_39415(color);
            bb.method_22918(ms.method_23760().method_23761(), (float)v2.field_1352, (float)v2.field_1351, (float)v2.field_1350).method_39415(color);
            bb.method_22918(ms.method_23760().method_23761(), (float)v3.field_1352, (float)v3.field_1351, (float)v3.field_1350).method_39415(color);
         }

      }

      private void drawQuad(class_4587 ms, class_287 bb, class_243 v1, class_243 v2, class_243 v3, class_243 v4, int color, boolean filled) {
         if (filled) {
            this.drawTriangle(ms, bb, v1, v2, v3, color, true);
            this.drawTriangle(ms, bb, v1, v3, v4, color, true);
         } else {
            bb.method_22918(ms.method_23760().method_23761(), 100.0F, 100.0F, (float)v1.field_1350).method_39415(color);
         }

      }
   }
}

