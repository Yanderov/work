package dev.client.util.render;

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
public final class MenuBackgroundRenderer {
   private static final Identifier BACKGROUND = Identifier.of("wild", "images/mainmenu/background.png");

   private MenuBackgroundRenderer() {
   }

   public static void render(DrawContext context, int width, int height, double alpha) {
      Matrix4f matrix = context.getMatrices().peek().getPositionMatrix();
      AbstractTexture texture = MinecraftClient.getInstance().getTextureManager().getTexture(BACKGROUND);
      Builder.texture()
         .size(new SizeState((float)width, (float)height))
         .radius(new QuadRadiusState(0.0F))
         .texture(0.0F, 0.0F, 1.0F, 1.0F, texture)
         .color(new QuadColorState(ColorUtil.setAlpha(alpha, Color.WHITE)))
         .build()
         .render(matrix, 0.0F, 0.0F);
   }
}
