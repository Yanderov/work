package dev.client.ui.hud.impl;

import dev.client.WildClient;
import dev.client.managers.FontManager;
import dev.client.ui.draggable.impl.WatermarkDraggable;
import dev.client.ui.hud.HudElement;
import dev.client.util.IUtil;
import dev.client.util.render.builders.Builder;
import dev.client.util.render.builders.states.QuadColorState;
import dev.client.util.render.builders.states.QuadRadiusState;
import dev.client.util.render.builders.states.SizeState;
import dev.client.util.render.msdf.MsdfFont;
import dev.client.util.render.renderers.impl.BuiltBlur;
import dev.client.util.render.renderers.impl.BuiltRectangle;
import dev.client.util.render.renderers.impl.BuiltText;
import dev.client.util.render.renderers.impl.BuiltTexture;
import java.awt.Color;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;

@Environment(EnvType.CLIENT)
public class WatermarkElement extends HudElement implements IUtil {
   private double smoothedBps = 0;

   public WatermarkElement() {
      super(new WatermarkDraggable(), "Watermark");
   }

   public void render(DrawContext drawContext) {
      String clientName = "YanderovClient";
      String fps = MinecraftClient.getInstance().getCurrentFps() + "fps";
      String time = "15:00";
      String uid = "1488";
      
      // BPS Calculation & Smoothing
      double deltaX = mc.player.getX() - mc.player.prevX;
      double deltaZ = mc.player.getZ() - mc.player.prevZ;
      double currentBps = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ) * 20.0D;
      
      // Smooth the value using linear interpolation (lerp)
      smoothedBps = smoothedBps + (currentBps - smoothedBps) * 0.1D;
      if (smoothedBps < 0.05) smoothedBps = 0; // Snap to zero
      
      String bps = String.format("%.2f bps", smoothedBps);

      float size = 7.5F;
      float width = 145.25F + FontManager.SUISSEINTMEDIUM.get().getWidth(fps, size) 
                             + FontManager.SUISSEINTMEDIUM.get().getWidth(clientName, size) 
                             + FontManager.SUISSEINTMEDIUM.get().getWidth(time, size) 
                             + FontManager.SUISSEINTMEDIUM.get().getWidth(uid, size)
                             + FontManager.SUISSEINTMEDIUM.get().getWidth(bps, size);
                             
      this.draggable.setHeight(26);
      this.draggable.setWeight((int)width);
      Matrix4f matrix = drawContext.getMatrices().peek().getPositionMatrix();
      
      BuiltBlur blur = Builder.blur().size(new SizeState(width, 26.0F)).radius(new QuadRadiusState(8.0F)).blurRadius(12.0F).smoothness(1.0F).color(new QuadColorState(Color.white)).build();
      blur.render(matrix, (float)this.draggable.x, (float)this.draggable.y);
      BuiltRectangle rectangle = Builder.rectangle().size(new SizeState(width, 26.0F)).color(new QuadColorState(Color.black)).radius(new QuadRadiusState(8.0F)).smoothness(1.15F).build();
      rectangle.render(matrix, (float)this.draggable.x, (float)this.draggable.y);
      
      // Box for Logo
      rectangle = Builder.rectangle().size(new SizeState(19.0F, 18.0F)).color(new QuadColorState(new Color(255, 255, 255, 30))).radius(new QuadRadiusState(5.0F)).smoothness(1.15F).build();
      rectangle.render(matrix, (float)(this.draggable.x + 4), (float)(this.draggable.y + 4));
      
      AbstractTexture logoTex = MinecraftClient.getInstance().getTextureManager().getTexture(Identifier.of("wild", "images/gui/rightlogo.png"));
      BuiltTexture logo = (BuiltTexture)Builder.texture().size(new SizeState(14.0F, 14.0F)).texture(0.0F, 0.0F, 1.0F, 1.0F, logoTex).color(new QuadColorState(Color.WHITE)).build();
      logo.render(matrix, (float)(this.draggable.x + 6.5), (float)(this.draggable.y + 6.0), 0.0F);

      float widthName = FontManager.SUISSEINTMEDIUM.get().getWidth(clientName, size) + 25.0F;
      rectangle = Builder.rectangle().size(new SizeState(widthName, 18.0F)).color(new QuadColorState(new Color(255, 255, 255, 20))).radius(new QuadRadiusState(3.0F)).smoothness(1.15F).build();
      rectangle.render(matrix, (float)(this.draggable.x + 25), (float)(this.draggable.y + 4));
      
      BuiltText themeText = (BuiltText)Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text(clientName).color(Color.WHITE).size(7.5F).thickness(0.05F).build();
      themeText.render(matrix, (float)(this.draggable.x + 30), (float)(this.draggable.y + 8));
      
      float widthFps = FontManager.SUISSEINTMEDIUM.get().getWidth(fps, size) + 23.5F;
      rectangle = Builder.rectangle().size(new SizeState(widthFps, 18.0F)).color(new QuadColorState(new Color(255, 255, 255, 20))).radius(new QuadRadiusState(3.0F)).smoothness(1.15F).build();
      rectangle.render(matrix, (float)(this.draggable.x + 27) + widthName, (float)(this.draggable.y + 4));
      
      themeText = (BuiltText)Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text(fps).color(Color.WHITE).size(7.5F).thickness(0.05F).build();
      themeText.render(matrix, (float)(this.draggable.x + 32) + widthName, (float)(this.draggable.y + 8));

      // BPS Section
      float widthBps = FontManager.SUISSEINTMEDIUM.get().getWidth(bps, size) + 23.5F;
      rectangle = Builder.rectangle().size(new SizeState(widthBps, 18.0F)).color(new QuadColorState(new Color(255, 255, 255, 20))).radius(new QuadRadiusState(3.0F)).smoothness(1.15F).build();
      rectangle.render(matrix, (float)(this.draggable.x + 29) + widthName + widthFps, (float)(this.draggable.y + 4));

      themeText = (BuiltText)Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text(bps).color(Color.WHITE).size(7.5F).thickness(0.05F).build();
      themeText.render(matrix, (float)(this.draggable.x + 34) + widthName + widthFps, (float)(this.draggable.y + 8));
      
      float widthTime = FontManager.SUISSEINTMEDIUM.get().getWidth(time, size) + 23.5F;
      rectangle = Builder.rectangle().size(new SizeState(widthTime, 18.0F)).color(new QuadColorState(new Color(255, 255, 255, 20))).radius(new QuadRadiusState(3.0F)).smoothness(1.15F).build();
      rectangle.render(matrix, (float)(this.draggable.x + 31) + widthName + widthFps + widthBps, (float)(this.draggable.y + 4));
      
      themeText = (BuiltText)Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text(time).color(Color.WHITE).size(7.5F).thickness(0.05F).build();
      themeText.render(matrix, (float)(this.draggable.x + 36) + widthName + widthFps + widthBps, (float)(this.draggable.y + 8));
      
      float widthUid = FontManager.SUISSEINTMEDIUM.get().getWidth(uid, size) + 24.5F;
      rectangle = Builder.rectangle().size(new SizeState(widthUid, 18.0F)).color(new QuadColorState(new Color(255, 255, 255, 20))).radius(new QuadRadiusState(3.0F, 3.0F, 5.0F, 5.0F)).smoothness(1.15F).build();
      rectangle.render(matrix, (float)(this.draggable.x + 33) + widthName + widthFps + widthBps + widthTime, (float)(this.draggable.y + 4));
      
      themeText = (BuiltText)Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text(uid).color(Color.WHITE).size(7.5F).thickness(0.05F).build();
      themeText.render(matrix, (float)(this.draggable.x + 38) + widthName + widthFps + widthBps + widthTime, (float)(this.draggable.y + 8));
   }
}
