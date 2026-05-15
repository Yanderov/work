package fun.Yanderov.utils.display.widget;

import fun.Yanderov.utils.display.font.Fonts;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import java.awt.Color;
import net.minecraft.class_332;

public class SliderRender implements QuickImports {
   public static void renderSlider(class_332 context, int x, int y, int width, int height, double value, boolean active, String text) {
      ShapeProperties animRect = ShapeProperties.create(context.method_51448(), (double)((float)x + (float)(width - width) / 2.0F), (double)((float)y + (float)(height - height) / 2.0F), (double)width, (double)height).round(4.0F).thickness(3.0F).outlineColor((new Color(150, 150, 150, 150)).getRGB()).color((new Color(18, 19, 20, 125)).getRGB(), (new Color(0, 2, 5, 125)).getRGB(), (new Color(0, 2, 5, 125)).getRGB(), (new Color(18, 19, 20, 125)).getRGB()).build();
      rectangle.render(animRect);
      ShapeProperties slider = ShapeProperties.create(context.method_51448(), (double)((float)((double)x + value * (double)(width - 7))), (double)(y + 1), (double)7.0F, (double)(height - 2)).round(2.5F).color((new Color(124, 124, 124, active ? 155 : 0)).getRGB()).build();
      rectangle.render(slider);
      if (text != null && !text.isEmpty()) {
         Fonts.getSize(18, Fonts.Type.DEFAULT).drawString(context.method_51448(), text, (double)((float)x - Fonts.getSize(18, Fonts.Type.DEFAULT).getStringWidth(text) / 2.0F + (float)width / 2.0F), (double)((float)y + 7.0F), Color.WHITE.getRGB());
      }

   }
}

