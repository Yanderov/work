package dev.client.yanderov.display.hud;

import dev.client.yanderov.common.animation.Animation;
import dev.client.yanderov.common.animation.Direction;
import dev.client.yanderov.common.animation.implement.Decelerate;
import dev.client.yanderov.features.impl.combat.Aura;
import dev.client.yanderov.features.impl.render.Hud;
import dev.client.yanderov.utils.client.managers.api.draggable.AbstractDraggable;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.font.FontRenderer;
import dev.client.yanderov.utils.display.font.Fonts;
import dev.client.yanderov.utils.display.geometry.Render2D;
import dev.client.yanderov.utils.display.shape.ShapeProperties;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import dev.client.yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_10042;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_897;
import net.minecraft.class_922;

public class TargetHud extends AbstractDraggable {
   private final Animation scaleAnimation = (new Decelerate()).setMs(400).setValue((double)1.0F);
   private final Animation faceAlphaAnimation = (new Decelerate()).setMs(125).setValue((double)1.0F);
   private class_1309 lastTarget;
   private float health;

   public TargetHud() {
      super("Target Hud", 10, 40, 110, 80, true);
   }

   public boolean visible() {
      return this.scaleAnimation.isDirection(Direction.FORWARDS);
   }

   public void tick() {
      class_1309 auraTarget = Aura.getInstance().getTarget();
      boolean inChat = PlayerInteractionHelper.isChat(mc.field_1755);
      if (auraTarget == null && !inChat) {
         this.hideTargetHud();
         this.faceAlphaAnimation.setDirection(Direction.BACKWARDS);
      } else {
         this.showTargetHud();
         this.faceAlphaAnimation.setDirection(Direction.FORWARDS);
         if (auraTarget != null) {
            this.lastTarget = auraTarget;
         } else if (inChat) {
            this.lastTarget = mc.field_1724;
         }
      }

   }

   private void showTargetHud() {
      if (this.scaleAnimation.getDirection() != Direction.FORWARDS) {
         this.scaleAnimation.setDirection(Direction.FORWARDS);
      }

   }

   private void hideTargetHud() {
      if (this.scaleAnimation.getDirection() != Direction.BACKWARDS) {
         this.scaleAnimation.setDirection(Direction.BACKWARDS);
      }

   }

   public void drawDraggable(class_332 context) {
      if (Hud.getInstance().interfaceSettings.isSelected("Target Hud") && Hud.getInstance().state) {
         if (this.lastTarget != null) {
            class_4587 matrix = context.method_51448();
            float scaleAnim = this.scaleAnimation.getOutput().floatValue();
            if (!(scaleAnim <= 0.0F)) {
               Calculate.setAlpha(scaleAnim, () -> {
                  float offsetX = (1.0F - scaleAnim) * 10.0F;
                  float offsetY = (1.0F - scaleAnim) * 5.0F;
                  matrix.method_22903();
                  matrix.method_46416(offsetX, offsetY, 0.0F);
                  this.drawMainContent(context, matrix);
                  matrix.method_22909();
               });
            }
         }
      }
   }

   private void drawMainContent(class_332 context, class_4587 matrix) {
      float localScale = 1.2F;
      float centerX = (float)this.getX() + (float)this.getWidth() / 2.0F;
      float centerY = (float)this.getY() + (float)this.getHeight() / 2.0F;
      Calculate.scale(matrix, centerX, centerY, localScale, localScale, () -> {
         float headerHeight = 12.2F;
         float headerPaddingTop = 2.3F;
         float gap = 0.8F;
         float headerWidth = 54.0F;
         float lowerSectionY = (float)this.getY() + headerHeight + headerPaddingTop + gap;
         float headerX = (float)this.getX() + ((float)this.getWidth() - headerWidth) / 2.0F;
         blur.render(ShapeProperties.create(matrix, (double)headerX, (double)((float)this.getY() + headerPaddingTop), (double)headerWidth, (double)headerHeight).round(1.5F, 0.0F, 1.5F, 0.0F).quality(12.0F).color((new Color(0, 0, 0, 150)).getRGB()).build());
         rectangle.render(ShapeProperties.create(matrix, (double)headerX, (double)((float)this.getY() + headerPaddingTop), (double)headerWidth, (double)headerHeight).round(3.0F, 3.0F, 3.0F, 3.0F).thickness(0.1F).outlineColor((new Color(33, 33, 33, 255)).getRGB()).color((new Color(18, 19, 20, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(18, 19, 20, 75)).getRGB()).build());
         blur.render(ShapeProperties.create(matrix, (double)((float)this.getX() + 1.0F), (double)lowerSectionY, (double)((float)this.getWidth() - 2.0F), (double)43.6F).round(1.5F, 1.5F, 1.5F, 1.5F).quality(12.0F).color((new Color(0, 0, 0, 150)).getRGB()).build());
         rectangle.render(ShapeProperties.create(matrix, (double)((float)this.getX() + 1.0F), (double)lowerSectionY, (double)((float)this.getWidth() - 2.0F), (double)42.0F).round(1.5F, 1.5F, 1.5F, 1.5F).thickness(0.1F).outlineColor((new Color(33, 33, 33, 255)).getRGB()).color((new Color(18, 19, 20, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(18, 19, 20, 75)).getRGB()).build());
         this.drawInfo(context, matrix, headerPaddingTop);
         float contentShift = 0.0F;
         this.drawFace(context);
         this.drawLeftItems(context, matrix, contentShift);
         this.drawRightItems(context, matrix, contentShift);
         this.drawBottomSection(context, matrix);
      });
   }

   private void drawFace(class_332 context) {
      class_897<? super class_1309, ?> baseRenderer = mc.method_1561().method_3953(this.lastTarget);
      if (baseRenderer instanceof class_922 renderer) {
         class_10042 state = (class_10042)renderer.method_62425(this.lastTarget, tickCounter.method_60637(false));
         class_2960 textureLocation = renderer.method_3885(state);
         float headSize = 27.0F;
         float headX = (float)this.getX() + ((float)this.getWidth() - headSize) / 2.0F;
         float headY = (float)(this.getY() + 15 + 4);
         float faceAlpha = this.faceAlphaAnimation.getOutput().floatValue();
         Calculate.setAlpha(faceAlpha, () -> Render2D.drawTexture(context, textureLocation, headX, headY, headSize, 3.0F, 8, 8, 64, -1, ColorAssist.multRed(-1, 1.0F + (float)this.lastTarget.field_6235 / 4.0F)));
      }
   }

   private void drawInfo(class_332 context, class_4587 matrix, float paddingTop) {
      FontRenderer font = Fonts.getSize(12);
      String name = this.lastTarget.method_5477().getString();
      float nameX = (float)this.getX() + (float)this.getWidth() / 2.0F - font.getStringWidth(name) / 2.0F;
      font.drawString(matrix, name, (double)nameX, (double)((float)this.getY() + paddingTop + 4.7F), -1);
   }

   private void drawBottomSection(class_332 context, class_4587 matrix) {
      float hp = PlayerInteractionHelper.getHealth(this.lastTarget);
      float maxHp = this.lastTarget.method_6063();
      float barWidth = (float)(this.getWidth() - 10);
      float barHeight = 5.0F;
      float barX = (float)this.getX() + 5.7F;
      float barY = (float)(this.getY() + 50);
      float targetHealth = hp / maxHp * barWidth;
      this.health = Calculate.interpolateSmooth((double)1.0F, this.health, targetHealth);
      rectangle.render(ShapeProperties.create(matrix, (double)barX, (double)barY, (double)barWidth, (double)barHeight).round(1.0F).color(1140850688).build());
      rectangle.render(ShapeProperties.create(matrix, (double)barX, (double)barY, (double)class_3532.method_15363(this.health, 0.0F, barWidth), (double)barHeight).round(1.0F).color(-1).build());
      FontRenderer smallFont = Fonts.getSize(10);
      float infoY = barY + barHeight + 9.0F;
      String hpString = String.format("%.1f HP", hp + this.lastTarget.method_6067());
      this.drawTextWithOutline(matrix, smallFont, hpString, barX, infoY, -1);
      boolean winning = mc.field_1724.method_6032() >= this.lastTarget.method_6032();
      String status = winning ? "WIN" : "LOSE";
      int statusColor = winning ? -11141291 : -43691;
      float statusX = (float)(this.getX() + this.getWidth()) - smallFont.getStringWidth(status) - 5.0F;
      this.drawTextWithOutline(matrix, smallFont, status, statusX, infoY, statusColor);
   }

   private void drawTextWithOutline(class_4587 matrix, FontRenderer font, String text, float x, float y, int color) {
      font.drawString(matrix, text, (double)(x - 0.5F), (double)y, -16777216);
      font.drawString(matrix, text, (double)(x + 0.5F), (double)y, -16777216);
      font.drawString(matrix, text, (double)x, (double)(y - 0.5F), -16777216);
      font.drawString(matrix, text, (double)x, (double)(y + 0.5F), -16777216);
      font.drawString(matrix, text, (double)x, (double)y, color);
   }

   private void drawLeftItems(class_332 context, class_4587 matrix, float shift) {
      List<class_1799> armor = new ArrayList();
      Iterable var10000 = this.lastTarget.method_5661();
      Objects.requireNonNull(armor);
      var10000.forEach(armor::add);
      float bgSize = 13.0F;
      float spacing = 14.0F;
      float startX = (float)(this.getX() + 5);
      float startY = (float)(this.getY() + 19) + shift;

      for(int i = 0; i < 4; ++i) {
         float x = startX + (float)(i % 2) * spacing;
         float y = startY + (float)(i / 2) * spacing;
         blur.render(ShapeProperties.create(matrix, (double)x, (double)y, (double)bgSize, (double)bgSize).round(1.5F).color(1711276032).build());
         if (i < armor.size()) {
            class_1799 stack = (class_1799)armor.get(armor.size() - 1 - i);
            if (!stack.method_7960()) {
               Render2D.defaultDrawStack(context, stack, x, y, false, true, 0.7F);
            }
         }
      }

   }

   private void drawRightItems(class_332 context, class_4587 matrix, float shift) {
      List<class_1799> items = new ArrayList();
      if (!this.lastTarget.method_6047().method_7960()) {
         items.add(this.lastTarget.method_6047());
      }

      if (!this.lastTarget.method_6079().method_7960()) {
         items.add(this.lastTarget.method_6079());
      }

      float bgSize = 13.0F;
      float spacing = 14.0F;
      float startX = (float)(this.getX() + this.getWidth()) - spacing * 2.0F - 4.0F;
      float startY = (float)(this.getY() + 19) + shift;

      for(int i = 0; i < 4; ++i) {
         float x = startX + (float)(i % 2) * spacing;
         float y = startY + (float)(i / 2) * spacing;
         blur.render(ShapeProperties.create(matrix, (double)x, (double)y, (double)bgSize, (double)bgSize).round(1.5F).color(1711276032).build());
         if (i < items.size()) {
            Render2D.defaultDrawStack(context, (class_1799)items.get(i), x, y, false, true, 0.7F);
         }
      }

   }
}

