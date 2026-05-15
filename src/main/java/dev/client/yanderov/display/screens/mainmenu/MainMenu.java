package dev.client.yanderov.display.screens.mainmenu;

import antidaunleak.api.UserProfile;
import dev.client.yanderov.common.animation.Direction;
import dev.client.yanderov.common.animation.implement.Decelerate;
import dev.client.yanderov.display.screens.mainmenu.altscreen.AltScreen;
import dev.client.yanderov.utils.client.text.TextAnimation;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.font.Fonts;
import dev.client.yanderov.utils.display.geometry.Render2D;
import dev.client.yanderov.utils.display.gif.GifRender;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.display.shape.ShapeProperties;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_156;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_429;
import net.minecraft.class_437;
import net.minecraft.class_500;
import net.minecraft.class_526;

public class MainMenu extends class_437 implements QuickImports {
   public static MainMenu INSTANCE = new MainMenu();
   public int x;
   public int y;
   public int width;
   public int height;
   private final TextAnimation textAnimation = new TextAnimation();
   private boolean altVisible = false;
   private final GifRender gifRender = new GifRender("minecraft:gif/backgrounds/mainmenutype1", 1);
   private final Decelerate altFadeAnimation = new Decelerate();
   private final Decelerate mainFadeAnimation = new Decelerate();
   private AltScreen altScreen;
   private long lastToggleTime = 0L;
   private static final long TOGGLE_DELAY = 500L;
   private static final UserProfile userProfile = UserProfile.getInstance();
   private final List snowflakes = new ArrayList();
   private static final String TELEGRAM_URL = "https://t.me/HellsDLC";

   public MainMenu() {
      super(class_2561.method_30163("MainMenu"));
      this.altFadeAnimation.setMs(250).setValue((double)1.0F);
      this.mainFadeAnimation.setMs(250).setValue((double)1.0F);
      this.mainFadeAnimation.setDirection(Direction.FORWARDS);
      this.altFadeAnimation.setDirection(Direction.BACKWARDS);
   }

   public void method_25393() {
      super.method_25393();
      this.textAnimation.updateText();
      if (this.altScreen != null) {
         this.altScreen.tick();
      }

      this.updateSnow();
      if (this.altFadeAnimation.isFinished(Direction.BACKWARDS)) {
         this.altVisible = false;
      }

   }

   public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
      mc.field_1690.method_42474().method_41748(2);
      this.x = window.method_4486();
      this.y = window.method_4502();
      this.width = window.method_4486() + 2;
      this.height = window.method_4502() + 2;
      float cy = (float)this.height / 2.0F;
      float sy = cy - 25.0F;
      float sx = (float)(this.width / 2 - 50);
      float bs = 21.0F;
      this.gifRender.render(context.method_51448(), 0.0F, 0.0F, (float)this.width, (float)this.height);
      image.setTexture("textures/mainmenu/backmenu.png").render(ShapeProperties.create(context.method_51448(), (double)0.0F, (double)0.0F, (double)this.width, (double)this.height).color(-1).build());
      this.renderSnow(context);
      Double mainAlpha = this.mainFadeAnimation.getOutput();
      int mainAlphaInt = (int)((double)255.0F * mainAlpha);
      if (mainAlpha > (double)0.01F) {
         this.drawButton(context, sx, sy, 102.0F, 18.5F, "Single Player", mainAlphaInt);
         this.drawButton(context, sx, sy + bs, 102.0F, 18.5F, "Multi Player", mainAlphaInt);
         this.drawButton(context, sx, sy + bs * 2.0F, 102.0F, 18.5F, "Alt Screen", mainAlphaInt);
         this.drawButton(context, sx, sy + bs * 3.0F, 50.0F, 18.5F, "", mainAlphaInt);
         this.drawButton(context, sx + 52.0F, sy + bs * 3.0F, 50.0F, 18.5F, "", mainAlphaInt);
         Fonts.getSize(24, Fonts.Type.SEMI).drawCenteredString(context.method_51448(), "Yanderov", (double)(this.width / 2), (double)(sy - 62.0F), this.applyAlpha((new Color(200, 200, 200)).getRGB(), mainAlphaInt));
         Fonts.getSize(18, Fonts.Type.DEFAULT).drawCenteredString(context.method_51448(), "Yanderov Client", (double)(this.width / 2), (double)(sy - 40.0F), this.applyAlpha((new Color(200, 200, 200)).getRGB(), mainAlphaInt));
         Fonts.getSize(12, Fonts.Type.DEFAULT).drawCenteredString(context.method_51448(), this.textAnimation.getCurrentText(), (double)(this.width / 2), (double)(sy - 25.0F), this.applyAlpha((new Color(200, 200, 200)).getRGB(), mainAlphaInt));
         Fonts.getSize(12, Fonts.Type.DEFAULT).drawCenteredString(context.method_51448(), "Yanderov", (double)(this.width / 2 + 2), (double)(this.height - 7), this.applyAlpha(ColorAssist.getText(0.35F), mainAlphaInt));
         float tgWidth = 70.0F;
         float tgHeight = 18.5F;
         float tgX = (float)this.width - tgWidth - 10.0F;
         float tgY = 10.0F;
         rectangle.render(ShapeProperties.create(context.method_51448(), (double)tgX, (double)tgY, (double)tgWidth, (double)tgHeight).thickness(2.0F).round(4.0F).outlineColor(this.applyAlpha((new Color(100, 100, 100, 95)).getRGB(), mainAlphaInt)).color(this.applyAlpha((new Color(50, 50, 50, 55)).getRGB(), mainAlphaInt), this.applyAlpha((new Color(50, 50, 50, 55)).getRGB(), mainAlphaInt), this.applyAlpha((new Color(80, 80, 80, 95)).getRGB(), mainAlphaInt), this.applyAlpha((new Color(80, 80, 80, 95)).getRGB(), mainAlphaInt)).build());
         rectangle.render(ShapeProperties.create(context.method_51448(), (double)tgX, (double)tgY, (double)tgWidth, (double)1.0F).thickness(2.0F).round(5.0F).outlineColor(this.applyAlpha((new Color(100, 100, 100, 95)).getRGB(), mainAlphaInt)).color(this.applyAlpha((new Color(50, 50, 50, 5)).getRGB(), mainAlphaInt), this.applyAlpha((new Color(50, 50, 50, 255)).getRGB(), mainAlphaInt), this.applyAlpha((new Color(80, 80, 80, 255)).getRGB(), mainAlphaInt), this.applyAlpha((new Color(80, 80, 80, 5)).getRGB(), mainAlphaInt)).build());
         float tgTextY = tgY + (tgHeight - 6.0F) / 2.0F + 1.0F;
         Fonts.getSize(15, Fonts.Type.DEFAULT).drawCenteredString(context.method_51448(), "Telegram", (double)(tgX + tgWidth / 2.0F), (double)tgTextY, this.applyAlpha((new Color(200, 200, 200)).getRGB(), mainAlphaInt));
         rectangle.render(ShapeProperties.create(context.method_51448(), (double)8.0F, (double)(this.height - 27), (double)20.0F, (double)20.0F).thickness(2.0F).round(10.0F).outlineColor(this.applyAlpha((new Color(100, 100, 100, 95)).getRGB(), mainAlphaInt)).color(this.applyAlpha((new Color(50, 50, 50, 55)).getRGB(), mainAlphaInt), this.applyAlpha((new Color(50, 50, 50, 55)).getRGB(), mainAlphaInt), this.applyAlpha((new Color(80, 80, 80, 95)).getRGB(), mainAlphaInt), this.applyAlpha((new Color(80, 80, 80, 95)).getRGB(), mainAlphaInt)).build());
         Render2D.drawTexture(context, class_2960.method_60655("minecraft", "textures/mainmenu/steve.png"), 9.5F, (float)this.height - 25.5F, 17.0F, 7.0F, 32, 32, 32, this.applyAlpha((new Color(0, 0, 0, 255)).getRGB(), mainAlphaInt));
         rectangle.render(ShapeProperties.create(context.method_51448(), (double)22.0F, (double)(this.height - 13), (double)6.0F, (double)6.0F).thickness(2.0F).round(3.0F).outlineColor(this.applyAlpha((new Color(100, 100, 100, 95)).getRGB(), mainAlphaInt)).color(this.applyAlpha((new Color(50, 50, 50, 55)).getRGB(), mainAlphaInt), this.applyAlpha((new Color(50, 50, 50, 55)).getRGB(), mainAlphaInt), this.applyAlpha((new Color(80, 80, 80, 95)).getRGB(), mainAlphaInt), this.applyAlpha((new Color(80, 80, 80, 95)).getRGB(), mainAlphaInt)).build());
         rectangle.render(ShapeProperties.create(context.method_51448(), (double)23.0F, (double)(this.height - 12), (double)4.0F, (double)4.0F).round(2.0F).color(this.applyAlpha((new Color(1, 235, 1, 155)).getRGB(), mainAlphaInt)).build());
         Fonts.getSize(12, Fonts.Type.DEFAULT).drawString(context.method_51448(), "Username â–¸ " + userProfile.profile("username"), (double)35.0F, (double)((float)this.height - 21.5F), this.applyAlpha(ColorAssist.getText(0.35F), mainAlphaInt));
         Fonts.getSize(12, Fonts.Type.DEFAULT).drawString(context.method_51448(), "Uid â–¸ " + userProfile.profile("uid"), (double)35.0F, (double)((float)this.height - 14.5F), this.applyAlpha(ColorAssist.getText(0.35F), mainAlphaInt));
         String text = "Build â–¸ 2.3";
         float textWidth = Fonts.getSize(12, Fonts.Type.DEFAULT).getStringWidth(text);
         Fonts.getSize(12, Fonts.Type.DEFAULT).drawString(context.method_51448(), text, (double)((float)context.method_51421() - textWidth - 3.0F), (double)((float)context.method_51443() - 5.5F), this.applyAlpha(ColorAssist.getText(0.35F), mainAlphaInt));
      }

      Double altAlpha = this.altFadeAnimation.getOutput();
      if (this.altVisible || altAlpha > (double)0.01F) {
         float centerX = (float)this.width / 2.0F - 80.0F;
         float centerY = (float)this.height / 2.0F - 105.0F;
         if (this.altScreen == null) {
            this.altScreen = new AltScreen(centerX, centerY);
         } else {
            this.altScreen.updatePosition(centerX, centerY);
         }

         int altAlphaInt = (int)((double)255.0F * altAlpha);
         Color buttonColor = new Color(50, 50, 50, (int)((double)55.0F * altAlpha));
         Color outlineColor = new Color(100, 100, 100, (int)((double)95.0F * altAlpha));
         Color gradientColor = new Color(80, 80, 80, (int)((double)95.0F * altAlpha));
         Color textColor = new Color(200, 200, 200, altAlphaInt);
         Color bgColor = new Color(30, 30, 30, (int)((double)255.0F * altAlpha));
         this.altScreen.render(context, buttonColor, outlineColor, gradientColor, textColor, bgColor);
      }

      super.method_25394(context, mouseX, mouseY, delta);
   }

   private void drawButton(class_332 ctx, float x, float y, float w, float h, String label, int alpha) {
      rectangle.render(ShapeProperties.create(ctx.method_51448(), (double)x, (double)y, (double)w, (double)h).thickness(2.0F).round(4.0F).outlineColor(this.applyAlpha((new Color(100, 100, 100, 95)).getRGB(), alpha)).color(this.applyAlpha((new Color(50, 50, 50, 55)).getRGB(), alpha), this.applyAlpha((new Color(50, 50, 50, 55)).getRGB(), alpha), this.applyAlpha((new Color(80, 80, 80, 95)).getRGB(), alpha), this.applyAlpha((new Color(80, 80, 80, 95)).getRGB(), alpha)).build());
      rectangle.render(ShapeProperties.create(ctx.method_51448(), (double)x, (double)y, (double)w, (double)1.0F).thickness(2.0F).round(5.0F).outlineColor(this.applyAlpha((new Color(100, 100, 100, 95)).getRGB(), alpha)).color(this.applyAlpha((new Color(50, 50, 50, 5)).getRGB(), alpha), this.applyAlpha((new Color(50, 50, 50, 255)).getRGB(), alpha), this.applyAlpha((new Color(80, 80, 80, 255)).getRGB(), alpha), this.applyAlpha((new Color(80, 80, 80, 5)).getRGB(), alpha)).build());
      if (!label.isEmpty()) {
         Fonts.getSize(16, Fonts.Type.DEFAULT).drawCenteredString(ctx.method_51448(), label, (double)(this.width / 2), (double)(y + 7.0F), this.applyAlpha((new Color(200, 200, 200)).getRGB(), alpha));
      }

   }

   private int applyAlpha(int color, int alpha) {
      Color c = new Color(color, true);
      int newAlpha = (int)((double)c.getAlpha() / (double)255.0F * (double)alpha);
      return (new Color(c.getRed(), c.getGreen(), c.getBlue(), Math.min(255, newAlpha))).getRGB();
   }

   private void updateSnow() {
      if (this.width > 0 && this.height > 0) {
         while(this.snowflakes.size() < 120) {
            float sx = (float)ThreadLocalRandom.current().nextDouble((double)0.0F, (double)this.width);
            float sy = (float)ThreadLocalRandom.current().nextDouble((double)(-this.height), (double)0.0F);
            float spd = 0.6F + (float)ThreadLocalRandom.current().nextDouble() * 0.8F;
            float size = 1.2F + (float)ThreadLocalRandom.current().nextDouble() * 1.8F;
            this.snowflakes.add(new Snowflake(sx, sy, spd, size));
         }

         for(int i = 0; i < this.snowflakes.size(); ++i) {
            Snowflake s = (Snowflake)this.snowflakes.get(i);
            s.y += s.speed;
            s.x = (float)((double)s.x + Math.sin((double)((s.y + s.driftPhase) * 0.01F)) * (double)0.15F);
            if (s.y > (float)(this.height + 10)) {
               this.snowflakes.set(i, new Snowflake((float)ThreadLocalRandom.current().nextDouble((double)0.0F, (double)this.width), (float)ThreadLocalRandom.current().nextDouble((double)(-this.height), (double)0.0F), 0.6F + (float)ThreadLocalRandom.current().nextDouble() * 0.8F, 1.2F + (float)ThreadLocalRandom.current().nextDouble() * 1.8F));
            }
         }

      }
   }

   private void renderSnow(class_332 context) {
      if (!this.snowflakes.isEmpty()) {
         float fadeHeight = Math.max(40.0F, (float)this.height * 0.25F);

         for(Snowflake s : this.snowflakes) {
            float distToBottom = (float)this.height - s.y;
            float fade = Math.min(1.0F, Math.max(0.0F, distToBottom / fadeHeight));
            int baseAlpha = 200;
            int alpha = (int)((float)baseAlpha * fade);
            if (alpha > 3) {
               int color = (new Color(255, 255, 255, alpha)).getRGB();
               float size = s.size;
               rectangle.render(ShapeProperties.create(context.method_51448(), (double)s.x, (double)s.y, (double)size, (double)size).round(size / 2.0F).color(color).build());
            }
         }

      }
   }

   public boolean method_25402(double mx, double my, int btn) {
      float cy = (float)this.height / 2.0F;
      float sy = cy - 25.0F;
      float sx = (float)(this.width / 2 - 50);
      float bs = 21.0F;
      if (this.altVisible && this.altFadeAnimation.getOutput() > (double)0.5F && this.altScreen != null) {
         return this.altScreen.mouseClicked(mx, my, btn);
      } else {
         if (this.mainFadeAnimation.getOutput() > (double)0.5F && btn == 0) {
            float tgWidth = 70.0F;
            float tgHeight = 18.5F;
            float tgX = (float)this.width - tgWidth - 10.0F;
            float tgY = 10.0F;
            if (this.isIn(mx, my, tgX, tgY, tgWidth, tgHeight)) {
               class_156.method_668().method_670("https://t.me/HellsDLC");
               return true;
            }

            if (this.isIn(mx, my, sx, sy, 102.0F, 18.5F)) {
               mc.method_1507(new class_526(this));
               return true;
            }

            if (this.isIn(mx, my, sx, sy + bs, 102.0F, 18.5F)) {
               mc.method_1507(new class_500(this));
               return true;
            }

            if (this.isIn(mx, my, sx, sy + bs * 2.0F, 102.0F, 18.5F)) {
               this.toggleAlt();
               return true;
            }

            if (this.isIn(mx, my, sx + 52.0F, sy + bs * 3.0F, 50.0F, 18.5F)) {
               mc.method_1507(new class_429(this, mc.field_1690));
               return true;
            }

            if (this.isIn(mx, my, sx, sy + bs * 3.0F, 50.0F, 18.5F)) {
               mc.method_1490();
               return true;
            }
         }

         return super.method_25402(mx, my, btn);
      }
   }

   public boolean method_25401(double mx, double my, double h, double v) {
      return this.altVisible && this.altFadeAnimation.getOutput() > (double)0.5F && this.altScreen != null ? this.altScreen.mouseScrolled(mx, my, v) : super.method_25401(mx, my, h, v);
   }

   public boolean method_25403(double mx, double my, int btn, double dx, double dy) {
      return this.altVisible && this.altFadeAnimation.getOutput() > (double)0.5F && this.altScreen != null ? this.altScreen.mouseDragged(mx, my, btn) : super.method_25403(mx, my, btn, dx, dy);
   }

   public boolean method_25406(double mx, double my, int btn) {
      if (this.altScreen != null) {
         this.altScreen.mouseReleased();
      }

      return super.method_25406(mx, my, btn);
   }

   public boolean method_25400(char c, int m) {
      return this.altVisible && this.altFadeAnimation.getOutput() > (double)0.5F && this.altScreen != null ? this.altScreen.charTyped(c) : super.method_25400(c, m);
   }

   public boolean method_25404(int k, int s, int m) {
      if (k == 256) {
         if (this.altVisible) {
            this.toggleAlt();
            return true;
         } else {
            return false;
         }
      } else {
         return this.altVisible && this.altFadeAnimation.getOutput() > (double)0.5F && this.altScreen != null && this.altScreen.keyPressed(k) ? true : super.method_25404(k, s, m);
      }
   }

   private boolean isIn(double mx, double my, float x, float y, float w, float h) {
      return mx >= (double)x && mx <= (double)(x + w) && my >= (double)y && my <= (double)(y + h);
   }

   private void toggleAlt() {
      long currentTime = System.currentTimeMillis();
      if (currentTime - this.lastToggleTime >= 500L) {
         this.lastToggleTime = currentTime;
         if (!this.altVisible) {
            this.altVisible = true;
            this.altFadeAnimation.setDirection(Direction.FORWARDS);
            this.altFadeAnimation.reset();
            this.mainFadeAnimation.setDirection(Direction.BACKWARDS);
            this.mainFadeAnimation.reset();
            if (this.altScreen != null) {
               this.altScreen.reset();
            }
         } else {
            this.altFadeAnimation.setDirection(Direction.BACKWARDS);
            this.altFadeAnimation.reset();
            this.mainFadeAnimation.setDirection(Direction.FORWARDS);
            this.mainFadeAnimation.reset();
            if (this.altScreen != null) {
               this.altScreen.reset();
            }
         }

      }
   }

   private static class Snowflake {
      float x;
      float y;
      float speed;
      float size;
      float driftPhase;

      Snowflake(float x, float y, float speed, float size) {
         this.x = x;
         this.y = y;
         this.speed = speed;
         this.size = size;
         this.driftPhase = (float)ThreadLocalRandom.current().nextDouble((double)0.0F, (Math.PI * 2D));
      }
   }
}

