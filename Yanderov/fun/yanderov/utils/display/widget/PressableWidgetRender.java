package fun.Yanderov.utils.display.widget;

import fun.Yanderov.common.animation.Animation;
import fun.Yanderov.common.animation.implement.Decelerate;
import fun.Yanderov.utils.display.font.Fonts;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import java.awt.Color;
import net.minecraft.class_332;

public class PressableWidgetRender implements QuickImports {
   private final Animation animation = (new Decelerate()).setMs(200).setValue((double)8.0F);

   public static void render(class_332 context, int x, int y, int width, int height, boolean active, String text) {
      ShapeProperties animRect = ShapeProperties.create(context.method_51448(), (double)((float)x + (float)(width - width) / 2.0F), (double)((float)y + (float)(height - height) / 2.0F), (double)width, (double)height).round(4.0F).thickness(3.0F).outlineColor((new Color(150, 150, 150, 150)).getRGB()).color((new Color(18, 19, 20, 125)).getRGB(), (new Color(0, 2, 5, 125)).getRGB(), (new Color(0, 2, 5, 125)).getRGB(), (new Color(18, 19, 20, 125)).getRGB()).build();
      rectangle.render(animRect);
      if (text != null && !text.isEmpty() && Fonts.getSize(18, Fonts.Type.DEFAULT).getStringWidth(text) <= (float)width) {
         Fonts.getSize(18, Fonts.Type.DEFAULT).drawString(context.method_51448(), text, (double)((float)x - Fonts.getSize(18, Fonts.Type.DEFAULT).getStringWidth(text) / 2.0F + (float)width / 2.0F), (double)((float)y + 7.0F), (new Color(255, 255, 255, 255)).getRGB());
      }

   }
}

