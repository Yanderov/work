package dev.client.yanderov.display.screens.clickgui.components.implement.settings.select;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.common.animation.Animation;
import dev.client.yanderov.common.animation.Direction;
import dev.client.yanderov.common.animation.implement.Decelerate;
import dev.client.yanderov.display.screens.clickgui.components.implement.settings.AbstractSettingComponent;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.font.FontRenderer;
import dev.client.yanderov.utils.display.font.Fonts;
import dev.client.yanderov.utils.display.scissor.ScissorAssist;
import dev.client.yanderov.utils.display.shape.ShapeProperties;
import dev.client.yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class SelectComponent extends AbstractSettingComponent {
   private final SelectSetting setting;
   private final Map chipAnimations = new HashMap();
   private final Map chipHoverAnimations = new HashMap();
   private float chipsStartX;
   private float chipsStartY;
   private float chipsMaxW;
   private float chipsEndY;

   public SelectComponent(SelectSetting setting) {
      super(setting);
      this.setting = setting;
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      class_4587 matrices = context.method_51448();
      float headerH = 14.0F;
      float nameX = this.x + 8.0F;
      float nameY = this.y + 9.0F;
      float maxNameW = Math.max(0.0F, this.x + this.width - 6.0F - nameX);
      ScissorAssist scissor = YanderovIntegration.getInstance().getScissorManager();
      scissor.push(matrices.method_23760().method_23761(), nameX, this.y + 2.0F, maxNameW, headerH);
      Fonts.getSize(14, Fonts.Type.DEFAULT).drawStringWithScroll(matrices, this.setting.getName(), (double)nameX, (double)nameY, maxNameW, -2828575);
      scissor.pop();
      this.chipsStartX = this.x + 8.0F;
      this.chipsStartY = this.y + headerH + 4.0F;
      this.chipsMaxW = Math.max(40.0F, this.width - 17.0F);
      this.renderChips(matrices, mouseX, mouseY);
      this.height = (float)((int)Math.max(headerH + 6.0F, this.chipsEndY - this.y + 4.0F));
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      if (button == 0) {
         this.handleChipClick(mouseX, mouseY);
      }

      return super.mouseClicked(mouseX, mouseY, button);
   }

   public boolean isHover(double mouseX, double mouseY) {
      return Calculate.isHovered(mouseX, mouseY, (double)this.x, (double)this.y, (double)this.width, (double)this.height);
   }

   private void renderChips(class_4587 matrix, int mouseX, int mouseY) {
      FontRenderer font = Fonts.getSize(13, Fonts.Type.DEFAULT);
      List<String> list = this.setting.getList();
      float chipPadX = 4.0F;
      float chipH = 12.0F;
      float rowGap = 4.0F;
      float colGap = 4.0F;
      float cx = this.chipsStartX;
      float cy = this.chipsStartY;
      float maxX = this.chipsStartX + this.chipsMaxW;

      for(String s : list) {
         float textW = font.getStringWidth(s);
         float chipW = Math.min(this.chipsMaxW, textW + chipPadX * 2.0F);
         if (cx + chipW > maxX) {
            cx = this.chipsStartX;
            cy += chipH + rowGap;
         }

         boolean hovered = Calculate.isHovered((double)mouseX, (double)mouseY, (double)cx, (double)cy, (double)chipW, (double)chipH);
         Animation hoverAnim = (Animation)this.chipHoverAnimations.computeIfAbsent(s, (k) -> (new Decelerate()).setMs(120).setValue((double)1.0F));
         hoverAnim.setDirection(hovered ? Direction.FORWARDS : Direction.BACKWARDS);
         float hoverT = hoverAnim.getOutput().floatValue();
         hoverT = Math.max(0.0F, Math.min(1.0F, hoverT));
         boolean selected = this.setting.isSelected(s);
         Animation anim = (Animation)this.chipAnimations.computeIfAbsent(s, (k) -> (new Decelerate()).setMs(140).setValue((double)1.0F));
         anim.setDirection(selected ? Direction.FORWARDS : Direction.BACKWARDS);
         float t = anim.getOutput().floatValue();
         t = Math.max(0.0F, Math.min(1.0F, t));
         int bgOff = (new Color(78, 84, 148, 40)).getRGB();
         int bgOn = (new Color(78, 84, 148, 175)).getRGB();
         int outlineOff = (new Color(55, 52, 55, 180)).getRGB();
         int outlineOn = (new Color(91, 93, 99, 240)).getRGB();
         int textOff = -2828575;
         int textOn = -1;
         int bg = ColorAssist.lerp(t, bgOff, bgOn);
         int outline = ColorAssist.lerp(t, outlineOff, outlineOn);
         int text = ColorAssist.lerp(t, textOff, textOn);
         int bgHoverTint = ColorAssist.alpha(bg) << 24 | 16777215;
         int outlineHoverTint = ColorAssist.alpha(outline) << 24 | 16777215;
         bg = ColorAssist.lerp(hoverT * 0.18F, bg, bgHoverTint);
         outline = ColorAssist.lerp(hoverT * 0.35F, outline, outlineHoverTint);
         text = ColorAssist.lerp(hoverT * 0.25F, text, -1);
         rectangle.render(ShapeProperties.create(matrix, (double)cx, (double)cy, (double)chipW, (double)chipH).round(2.0F).thickness(1.5F).outlineColor(outline).color(bg).build());
         float tx = cx + (chipW - textW) / 2.0F;
         float textH = font.getStringHeight(s);
         float ty = cy + (chipH - textH) / 2.0F + textH - 9.5F;
         font.drawString(matrix, s, (double)tx, (double)ty, text);
         cx += chipW + colGap;
      }

      this.chipsEndY = cy + chipH;
   }

   private void handleChipClick(double mouseX, double mouseY) {
      FontRenderer font = Fonts.getSize(13, Fonts.Type.DEFAULT);
      List<String> list = this.setting.getList();
      float chipPadX = 4.0F;
      float chipH = 12.0F;
      float rowGap = 4.0F;
      float colGap = 4.0F;
      float cx = this.chipsStartX;
      float cy = this.chipsStartY;
      float maxX = this.chipsStartX + this.chipsMaxW;

      for(String s : list) {
         float textW = font.getStringWidth(s);
         float chipW = Math.min(this.chipsMaxW, textW + chipPadX * 2.0F);
         if (cx + chipW > maxX) {
            cx = this.chipsStartX;
            cy += chipH + rowGap;
         }

         if (Calculate.isHovered(mouseX, mouseY, (double)cx, (double)cy, (double)chipW, (double)chipH)) {
            this.setting.selected(s);
            return;
         }

         cx += chipW + colGap;
      }

   }
}

