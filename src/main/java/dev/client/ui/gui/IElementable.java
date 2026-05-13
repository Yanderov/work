package dev.client.ui.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;

@Environment(EnvType.CLIENT)
public interface IElementable {
   void mouseClick(int button);

   void render(double x, double y, double width, double height, double mouseX, double mouseY, DrawContext context);

   void button(double x, double y, double width, double height, double mouseX, double mouseY, double xStart, double yStart, double xEnd, double yEnd, DrawContext context, int button);
}
