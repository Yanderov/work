package dev.client.yanderov.display.hud;

import antidaunleak.api.UserProfile;
import com.google.common.base.Suppliers;
import dev.client.yanderov.utils.client.managers.api.draggable.AbstractDraggable;
import dev.client.yanderov.utils.display.atlasfont.msdf.MsdfFont;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.font.Fonts;
import dev.client.yanderov.utils.display.shape.ShapeProperties;
import dev.client.yanderov.utils.display.systemrender.builders.Builder;
import dev.client.yanderov.utils.display.systemrender.renderers.impl.BuiltText;
import java.awt.Color;
import java.util.function.Supplier;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import org.joml.Matrix4f;

public class Watermark extends AbstractDraggable {
   private int fpsCount = 0;
   private static final Supplier ICONS_FONT = Suppliers.memoize(() -> MsdfFont.builder().atlas("icons").data("icons").build());
   private static final Supplier ICONS_FONT_1 = Suppliers.memoize(() -> MsdfFont.builder().atlas("clienticon1").data("clienticon1").build());
   private static final Supplier BOLD_FONT = Suppliers.memoize(() -> MsdfFont.builder().atlas("medium").data("medium").build());
   private static final Supplier ICONS = Suppliers.memoize(() -> MsdfFont.builder().atlas("medium").data("medium").build());
   private final String BRAND_WORD1 = "Yanderov  ";
   private final String BRAND_WORD2 = "Build 2.3 ";
   private String animatedBrand = "";
   private long lastBrandUpdate = 0L;
   private int brandPhase = 0;

   public Watermark() {
      super("Watermark", 10, 10, 92, 16, true);
   }

   public void tick() {
      this.fpsCount = mc.method_47599();
      this.updateBrandAnimation();
   }

   private void updateBrandAnimation() {
      long now = System.currentTimeMillis();
      long charDelay = 70L;
      long eraseDelay = 60L;
      long holdDelay = 650L;
      switch (this.brandPhase) {
         case 0:
            String target = "Yanderov  ";
            if (now - this.lastBrandUpdate < charDelay) {
               return;
            }

            this.lastBrandUpdate = now;
            if (this.animatedBrand.length() < target.length()) {
               this.animatedBrand = target.substring(0, this.animatedBrand.length() + 1);
            } else {
               this.brandPhase = 1;
               this.lastBrandUpdate = now;
            }
            break;
         case 1:
            if (now - this.lastBrandUpdate < holdDelay) {
               return;
            }

            this.brandPhase = 2;
            this.lastBrandUpdate = now;
            break;
         case 2:
            if (now - this.lastBrandUpdate < eraseDelay) {
               return;
            }

            this.lastBrandUpdate = now;
            if (!this.animatedBrand.isEmpty()) {
               this.animatedBrand = this.animatedBrand.substring(0, this.animatedBrand.length() - 1);
            } else {
               this.brandPhase = 3;
               this.lastBrandUpdate = now;
            }
            break;
         case 3:
            String target = "Build 2.3 ";
            if (now - this.lastBrandUpdate < charDelay) {
               return;
            }

            this.lastBrandUpdate = now;
            if (this.animatedBrand.length() < target.length()) {
               this.animatedBrand = target.substring(0, this.animatedBrand.length() + 1);
            } else {
               this.brandPhase = 4;
               this.lastBrandUpdate = now;
            }
            break;
         case 4:
            if (now - this.lastBrandUpdate < holdDelay) {
               return;
            }

            this.brandPhase = 5;
            this.lastBrandUpdate = now;
            break;
         case 5:
            if (now - this.lastBrandUpdate < eraseDelay) {
               return;
            }

            this.lastBrandUpdate = now;
            if (!this.animatedBrand.isEmpty()) {
               this.animatedBrand = this.animatedBrand.substring(0, this.animatedBrand.length() - 1);
            } else {
               this.brandPhase = 0;
               this.lastBrandUpdate = now;
            }
      }

   }

   public void drawDraggable(class_332 e) {
      class_4587 matrix = e.method_51448();
      Matrix4f matrix4f = matrix.method_23760().method_23761();
      String brand = this.animatedBrand;
      String point = " â€¢ ";
      String username = UserProfile.getInstance().profile("username");
      String fps = String.valueOf(this.fpsCount);
      String serverIp = mc.method_1558() != null ? mc.method_1558().field_3761 : "Singleplayer";
      String title = UserProfile.getInstance().profile("role");
      float brandWidth = Fonts.getSize(13, Fonts.Type.SEMI).getStringWidth(brand);
      float titleWidth = Fonts.getSize(12, Fonts.Type.DEFAULT).getStringWidth(title);
      float fpsWidth = Fonts.getSize(12, Fonts.Type.BOLD).getStringWidth(fps);
      float serverIpWidth = Fonts.getSize(12, Fonts.Type.BOLD).getStringWidth(serverIp);
      float icon2Width = ((MsdfFont)ICONS_FONT_1.get()).getWidth("D", 11.0F);
      float pointWidth = Fonts.getSize(12, Fonts.Type.BOLD).getStringWidth(point);
      float totalWidth = brandWidth + titleWidth + fpsWidth + serverIpWidth + icon2Width + pointWidth * 3.0F + 22.0F;
      this.setWidth((int)totalWidth + 14);
      blur.render(ShapeProperties.create(matrix, (double)this.getX(), (double)this.getY(), (double)(totalWidth + 14.0F), (double)(this.getHeight() + 4)).round(1.5F).quality(12.0F).color((new Color(0, 0, 0, 150)).getRGB()).build());
      rectangle.render(ShapeProperties.create(matrix, (double)this.getX(), (double)this.getY(), (double)(totalWidth + 14.0F), (double)(this.getHeight() + 4)).round(1.5F).thickness(0.1F).outlineColor((new Color(18, 19, 20, 35)).getRGB()).color((new Color(18, 19, 20, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(18, 19, 20, 75)).getRGB()).build());
      Fonts.getSize(13, Fonts.Type.SEMI).drawString(matrix, brand, (double)((float)this.getX() + 5.0F), (double)((float)this.getY() + 9.0F), (new Color(225, 225, 255, 255)).getRGB());
      float currentX = (float)this.getX() + brandWidth + 7.0F;
      rectangle.render(ShapeProperties.create(matrix, (double)currentX, (double)((float)this.getY() + 6.5F), (double)0.5F, (double)(this.getHeight() - 8)).round(0.0F).color(ColorAssist.getText(0.5F)).build());
      currentX += 4.0F;
      Fonts.getSize(13, Fonts.Type.DEFAULT).drawString(matrix, title, (double)(currentX + 2.5F), (double)((float)this.getY() + 9.0F), (new Color(255, 255, 255, 255)).getRGB());
      currentX += titleWidth + 9.0F;
      rectangle.render(ShapeProperties.create(matrix, (double)currentX, (double)((float)this.getY() + 6.5F), (double)0.5F, (double)(this.getHeight() - 8)).round(0.0F).color(ColorAssist.getText(0.5F)).build());
      currentX += 5.0F;
      Fonts.getSize(13, Fonts.Type.DEFAULT).drawString(matrix, fps, (double)currentX, (double)((float)this.getY() + 9.0F), (new Color(255, 255, 255, 255)).getRGB());
      currentX += fpsWidth + 2.0F;
      Fonts.getSize(17, Fonts.Type.ICONSTYPENEW).drawString(matrix, "w", (double)currentX, (double)((float)this.getY() + 9.0F), (new Color(225, 225, 255, 255)).getRGB());
      currentX += 10.0F;
      rectangle.render(ShapeProperties.create(matrix, (double)currentX, (double)((float)this.getY() + 6.5F), (double)0.5F, (double)(this.getHeight() - 8)).round(0.0F).color(ColorAssist.getText(0.5F)).build());
      currentX += 5.0F;
      Fonts.getSize(13, Fonts.Type.DEFAULT).drawString(matrix, serverIp, (double)currentX, (double)((float)this.getY() + 9.0F), (new Color(255, 255, 255, 255)).getRGB());
      currentX += serverIpWidth;
      ((BuiltText)Builder.text().font((MsdfFont)ICONS_FONT_1.get()).text("D").size(11.0F).color((new Color(225, 225, 255, 255)).getRGB()).build()).render(matrix4f, currentX + 2.5F, (float)this.getY() + 2.0F);
   }
}

