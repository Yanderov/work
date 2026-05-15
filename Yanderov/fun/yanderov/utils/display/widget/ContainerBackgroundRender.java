package fun.Yanderov.utils.display.widget;

import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import java.awt.Color;
import net.minecraft.class_332;

public class ContainerBackgroundRender implements QuickImports {
   public static void renderDefault(class_332 context, int x, int y, int width, int height) {
      renderCustom(context, x, y, width, height, new Color(18, 19, 20, 245), new Color(0, 2, 5, 245), 8.0F);
   }

   public static void renderInventory(class_332 context, int x, int y, int width, int height) {
      renderCustom(context, x, y, width, height, new Color(25, 25, 30, 240), new Color(15, 15, 20, 240), 6.0F);
   }

   public static void renderFurnace(class_332 context, int x, int y, int width, int height) {
      renderCustom(context, x, y, width, height, new Color(30, 25, 20, 245), new Color(20, 15, 10, 245), 7.0F);
   }

   public static void renderEnchanting(class_332 context, int x, int y, int width, int height) {
      renderCustom(context, x, y, width, height, new Color(20, 15, 30, 250), new Color(10, 5, 20, 250), 10.0F);
   }

   public static void renderCustom(class_332 context, int x, int y, int width, int height, Color mainColor, Color outlineColor, float round) {
      ShapeProperties containerBg = ShapeProperties.create(context.method_51448(), (double)x, (double)y, (double)width, (double)height).round(round).color(mainColor.getRGB()).build();
      rectangle.render(containerBg);
      ShapeProperties outline = ShapeProperties.create(context.method_51448(), (double)(x - 1), (double)(y - 1), (double)(width + 2), (double)(height + 2)).round(round + 1.0F).color(outlineColor.getRGB()).build();
      rectangle.render(outline);
   }
}

