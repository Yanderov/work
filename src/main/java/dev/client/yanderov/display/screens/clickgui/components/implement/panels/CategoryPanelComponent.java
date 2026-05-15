package dev.client.yanderov.display.screens.clickgui.components.implement.panels;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.common.animation.Animation;
import dev.client.yanderov.common.animation.Direction;
import dev.client.yanderov.common.animation.implement.Decelerate;
import dev.client.yanderov.display.screens.clickgui.MenuScreen;
import dev.client.yanderov.display.screens.clickgui.components.AbstractComponent;
import dev.client.yanderov.display.screens.clickgui.components.implement.module.PanelsModuleComponent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.utils.display.font.Fonts;
import dev.client.yanderov.utils.display.scissor.ScissorAssist;
import dev.client.yanderov.utils.display.shape.ShapeProperties;
import dev.client.yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import org.joml.Matrix4f;

public class CategoryPanelComponent extends AbstractComponent {
   private static final float HEADER_HEIGHT_RENDER = 18.0F;
   private static final float HEADER_HEIGHT_INPUT = 20.0F;
   private static final float CONTENT_PADDING_X = 6.0F;
   private static final float CONTENT_PADDING_TOP_RENDER = 4.0F;
   private static final float CONTENT_PADDING_TOP_INPUT = 5.0F;
   private static final float CONTENT_PADDING_BOTTOM_RENDER = 10.0F;
   private static final float CONTENT_PADDING_BOTTOM_INPUT = 11.0F;
   private static final float CONTENT_WIDTH_PADDING = 12.0F;
   private static final float TITLE_ICON_GAP = 4.0F;
   private static final float TITLE_CENTER_Y_DIV = 3.0F;
   private static final float TITLE_BASELINE_Y_OFFSET = 2.5F;
   private static final float LETTER_BLOCK_HEIGHT = 6.0F;
   private static final float MODULE_GAP_Y = 2.0F;
   private static final float SCROLL_STEP = 20.0F;
   private static final long SCROLLBAR_HIDE_DELAY_MS = 650L;
   private static final float SCROLLBAR_WIDTH = 3.0F;
   private static final float SCROLLBAR_X_OFFSET = 3.5F;
   private static final float SCROLLBAR_MIN_HANDLE_HEIGHT = 18.0F;
   private static final Map CATEGORY_ICONS;
   private final ModuleCategory category;
   private final List modules = new ArrayList();
   private float scroll = 0.0F;
   private float smoothedScroll = 0.0F;
   private final Animation scrollbarAnimation = (new Decelerate()).setMs(180).setValue((double)1.0F);
   private long lastScrollMs = 0L;

   public CategoryPanelComponent(ModuleCategory category) {
      this.category = category;

      for(Module m : YanderovIntegration.getInstance().getModuleRepository().modules()) {
         if (m.getCategory() == category) {
            this.modules.add(new PanelsModuleComponent(m));
         }
      }

      this.modules.sort(Comparator.comparing((o) -> o.getModule().getVisibleName().toLowerCase()));
   }

   private String getCategoryIcon() {
      return (String)CATEGORY_ICONS.getOrDefault(this.category, "");
   }

   private float getContentX() {
      return this.x + 6.0F;
   }

   private float getContentYForRender() {
      return this.y + 18.0F + 4.0F;
   }

   private float getContentYForInput() {
      return this.y + 20.0F + 5.0F;
   }

   private float getContentWidth() {
      return this.width - 12.0F;
   }

   private float getContentHeightForRender() {
      return this.height - 18.0F - 10.0F;
   }

   private float getContentHeightForInput() {
      return this.height - 20.0F - 11.0F;
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      class_4587 matrix = context.method_51448();
      Matrix4f positionMatrix = matrix.method_23760().method_23761();
      blur.render(ShapeProperties.create(matrix, (double)this.x, (double)this.y, (double)this.width, (double)this.height).round(7.5F).quality(32.0F).color((new Color(15, 15, 15, 220)).getRGB()).build());
      float headerH = 18.0F;
      rectangle.render(ShapeProperties.create(matrix, (double)this.x, (double)(this.y + headerH), (double)this.width, (double)0.5F).color((new Color(60, 60, 60, 180)).getRGB(), (new Color(60, 60, 60, 15)).getRGB(), (new Color(60, 60, 60, 180)).getRGB(), (new Color(60, 60, 60, 15)).getRGB()).build());
      String title = this.category.getReadableName().toUpperCase();
      String icon = this.getCategoryIcon();
      float gap = icon.isEmpty() ? 0.0F : 4.0F;
      float iconW = icon.isEmpty() ? 0.0F : Fonts.getSize(18, Fonts.Type.ICONSCATEGORY).getStringWidth(icon);
      float titleW = Fonts.getSize(14, Fonts.Type.DEFAULT).getStringWidth(title);
      float totalW = iconW + gap + titleW;
      float startX = this.x + (this.width - totalW) / 2.0F;
      float centerY = this.y + headerH / 3.0F;
      if (!icon.isEmpty()) {
         Fonts.getSize(18, Fonts.Type.ICONSCATEGORY).drawString(matrix, icon, (double)startX, (double)(centerY + 2.5F), (new Color(255, 255, 255, 255)).getRGB());
      }

      Fonts.getSize(14, Fonts.Type.DEFAULT).drawString(matrix, title, (double)(startX + iconW + gap), (double)(centerY + 2.5F), (new Color(255, 255, 255, 255)).getRGB());
      float contentX = this.getContentX();
      float contentY = this.getContentYForRender();
      float contentW = this.getContentWidth();
      float contentH = this.getContentHeightForRender();
      ScissorAssist scissor = YanderovIntegration.getInstance().getScissorManager();
      scissor.push(positionMatrix, contentX, contentY, contentW, contentH);
      String search = MenuScreen.INSTANCE.getSearchComponent().getText();
      boolean hasSearch = search != null && !search.isEmpty();
      String searchLower = hasSearch ? search.toLowerCase() : "";
      float renderScroll = (float)Math.round(this.smoothedScroll);
      float yOff = 0.0F;
      float totalH = 0.0F;
      char lastLetter = 0;

      for(PanelsModuleComponent m : this.modules) {
         if (!hasSearch || m.getModule().getVisibleName().toLowerCase().contains(searchLower)) {
            String vn = m.getModule().getVisibleName();
            char currentLetter = vn != null && !vn.isEmpty() ? Character.toUpperCase(vn.charAt(0)) : 35;
            if (currentLetter != lastLetter) {
               float letterH = 6.0F;
               float letterY = contentY + yOff + renderScroll;
               Fonts.getSize(10, Fonts.Type.DEFAULT).drawString(matrix, String.valueOf(currentLetter), (double)(contentX + 2.0F), (double)(letterY + 2.0F), -9473925);
               yOff += letterH;
               totalH += letterH;
               lastLetter = currentLetter;
            }

            float itemH = m.getComponentHeight();
            float itemY = contentY + yOff + renderScroll;
            m.position(contentX, itemY).size(contentW, itemH);
            m.render(context, mouseX, mouseY, delta);
            yOff += itemH + 2.0F;
            totalH += itemH + 2.0F;
         }
      }

      scissor.pop();
      float maxScroll = Math.max(0.0F, (float)Math.ceil((double)(totalH - contentH)));
      this.scroll = class_3532.method_15363(this.scroll, -maxScroll, 0.0F);
      this.smoothedScroll = Calculate.interpolateSmooth((double)2.0F, this.smoothedScroll, this.scroll);
      if (maxScroll > 0.0F) {
         if (System.currentTimeMillis() - this.lastScrollMs > 650L) {
            this.scrollbarAnimation.setDirection(Direction.BACKWARDS);
         }

         float sbA = this.scrollbarAnimation.getOutput().floatValue();
         if (sbA <= 0.01F) {
            return;
         }

         float barW = 3.0F;
         float barX = this.x + this.width - 3.5F;
         int trackA = class_3532.method_15340((int)(100.0F * sbA), 0, 255);
         int handleA = class_3532.method_15340((int)(180.0F * sbA), 0, 255);
         rectangle.render(ShapeProperties.create(matrix, (double)barX, (double)contentY, (double)barW, (double)contentH).round(2.0F).color((new Color(30, 30, 30, trackA)).getRGB()).build());
         float handleH = Math.max(18.0F, contentH * (contentH / (contentH + maxScroll)));
         float ratio = -renderScroll / maxScroll;
         float handleY = contentY + (contentH - handleH) * ratio;
         rectangle.render(ShapeProperties.create(matrix, (double)barX, (double)handleY, (double)barW, (double)handleH).round(2.0F).color((new Color(100, 100, 100, handleA)).getRGB()).build());
      }

   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      float contentX = this.getContentX();
      float contentY = this.getContentYForInput();
      float contentW = this.getContentWidth();
      float contentH = this.getContentHeightForInput();
      if (!Calculate.isHovered(mouseX, mouseY, (double)contentX, (double)contentY, (double)contentW, (double)contentH)) {
         return false;
      } else {
         String search = MenuScreen.INSTANCE.getSearchComponent().getText();
         boolean hasSearch = search != null && !search.isEmpty();
         String searchLower = hasSearch ? search.toLowerCase() : "";

         for(PanelsModuleComponent m : this.modules) {
            if ((!hasSearch || m.getModule().getVisibleName().toLowerCase().contains(searchLower)) && m.isHover(mouseX, mouseY)) {
               return m.mouseClicked(mouseX, mouseY, button);
            }
         }

         return false;
      }
   }

   public boolean mouseReleased(double mouseX, double mouseY, int button) {
      for(PanelsModuleComponent m : this.modules) {
         m.mouseReleased(mouseX, mouseY, button);
      }

      return false;
   }

   public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
      float contentX = this.getContentX();
      float contentY = this.getContentYForInput();
      float contentW = this.getContentWidth();
      float contentH = this.getContentHeightForInput();
      if (!Calculate.isHovered(mouseX, mouseY, (double)contentX, (double)contentY, (double)contentW, (double)contentH)) {
         return false;
      } else {
         this.scroll = (float)((double)this.scroll + amount * (double)20.0F);
         this.lastScrollMs = System.currentTimeMillis();
         this.scrollbarAnimation.setDirection(Direction.FORWARDS);

         for(PanelsModuleComponent m : this.modules) {
            m.mouseScrolled(mouseX, mouseY, amount);
         }

         return true;
      }
   }

   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      for(PanelsModuleComponent m : this.modules) {
         if (m.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
         }
      }

      return false;
   }

   public boolean charTyped(char chr, int modifiers) {
      for(PanelsModuleComponent m : this.modules) {
         m.charTyped(chr, modifiers);
      }

      return false;
   }

   static {
      CATEGORY_ICONS = Map.of(ModuleCategory.COMBAT, "A", ModuleCategory.MOVEMENT, "B", ModuleCategory.RENDER, "C", ModuleCategory.PLAYER, "D", ModuleCategory.MISC, "E", ModuleCategory.CONFIGS, "F", ModuleCategory.AUTOBUY, "H");
   }
}

