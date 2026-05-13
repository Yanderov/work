package dev.client.ui.hud.impl;

import dev.client.WildClient;
import dev.client.managers.FontManager;
import dev.client.util.color.ColorUtil;
import dev.client.util.render.builders.Builder;
import dev.client.util.render.builders.states.QuadColorState;
import dev.client.util.render.builders.states.QuadRadiusState;
import dev.client.util.render.builders.states.SizeState;
import java.awt.Color;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;

@Environment(EnvType.CLIENT)
public class MusicElement {
   public void render(DrawContext drawContext) {
      if (!WildClient.INSTANCE.getMusicManager().isPlaying()) return;

      int width = drawContext.getScaledWindowWidth();
      int height = drawContext.getScaledWindowHeight();
      
      double w = 150.0D;
      double h = 35.0D;
      double x = (width / 2.0D) - (w / 2.0D);
      double y = 10.0D; // Top middle

      Matrix4f matrix = drawContext.getMatrices().peek().getPositionMatrix();

      // Background
      Builder.rectangle().size(new SizeState((float)w, (float)h)).color(new QuadColorState(new Color(0, 0, 0, 160))).radius(new QuadRadiusState(5.0F)).smoothness(1.15F).build().render(matrix, x, y);
      
      // Avatar
      AbstractTexture avatarTex = MinecraftClient.getInstance().getTextureManager().getTexture(Identifier.of("wild", "images/gui/music_avatar.png"));
      Builder.texture().size(new SizeState(24.0F, 24.0F)).radius(new QuadRadiusState(12.0F)).texture(0.0F, 0.0F, 1.0F, 1.0F, avatarTex).color(new QuadColorState(Color.WHITE)).build().render(matrix, x + 5.0D, y + 5.0D);

      String track = WildClient.INSTANCE.getMusicManager().getCurrentTrackName();
      if (track.length() > 25) track = track.substring(0, 22) + "...";
      
      Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text("Now Playing").color(Color.WHITE).size(6.0F).thickness(0.05F).build().render(matrix, x + 35.0D, y + 6.0D);
      Builder.text().font(FontManager.SUISSEINTREGULAR.get()).text(track).color(new Color(255, 255, 255, 180)).size(5.5F).thickness(0.05F).build().render(matrix, x + 35.0D, y + 15.0D);

      // Simple progress bar
      float progress = WildClient.INSTANCE.getMusicManager().getProgress();
      double barX = x + 35.0D;
      double barY = y + 25.0D;
      double barWidth = 105.0D;
      
      Builder.rectangle().size(new SizeState((float)barWidth, 1.5F)).color(new QuadColorState(new Color(255, 255, 255, 40))).radius(new QuadRadiusState(0.75F)).build().render(matrix, barX, barY);
      Builder.rectangle().size(new SizeState((float)(barWidth * progress), 1.5F)).color(new QuadColorState(Color.WHITE)).radius(new QuadRadiusState(0.75F)).build().render(matrix, barX, barY);
   }
}
