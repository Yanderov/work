package fun.Yanderov.utils.display.item;

import fun.Yanderov.utils.display.color.ColorAssist;
import fun.Yanderov.utils.display.font.FontRenderer;
import fun.Yanderov.utils.display.font.Fonts;
import fun.Yanderov.utils.display.geometry.Render2D;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import java.util.HashMap;
import net.minecraft.class_10444;
import net.minecraft.class_1058;
import net.minecraft.class_1087;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_811;
import net.minecraft.class_9848;

public final class ItemRender implements QuickImports {
   public static final HashMap SPRITE_CACHE = new HashMap();

   public static void drawItem(class_4587 matrix, class_1799 stack, float x, float y, boolean count, boolean bar) {
      CachedSprite cachedSprite = getSpriteTexture(stack);
      if (cachedSprite != null) {
         Render2D.drawSprite(matrix, cachedSprite.sprite, x, y, 16.0F, 16);
      }

      if (count) {
         drawCount(matrix, stack, x, y);
      }

      if (bar) {
         drawBar(matrix, stack, x, y);
      }

   }

   private static void drawCount(class_4587 matrix, class_1799 stack, float x, float y) {
      int count = stack.method_7947();
      String text = count > 1 ? "" + count : "";
      if (!text.isEmpty()) {
         FontRenderer font = Fonts.getSize(16);
         font.drawString(matrix, text, (double)(x + 16.0F - font.getStringWidth(text)), (double)(y + 11.0F), ColorAssist.getText());
      }

   }

   private static void drawBar(class_4587 matrix, class_1799 stack, float x, float y) {
      if (stack.method_31578()) {
         rectangle.render(ShapeProperties.create(matrix, (double)(x + 1.5F), (double)(y + 13.0F), (double)13.0F, (double)2.0F).color(-16777216).build());
         rectangle.render(ShapeProperties.create(matrix, (double)(x + 1.5F), (double)(y + 13.0F), (double)stack.method_31579(), (double)1.0F).color(class_9848.method_61334(stack.method_31580())).build());
      }

   }

   private static CachedSprite getSpriteTexture(class_1799 stack) {
      return (CachedSprite)SPRITE_CACHE.computeIfAbsent(stack, (key) -> {
         class_10444 state = new class_10444();
         mc.method_65386().method_65596(state, stack, class_811.field_4317, mc.field_1687, (class_1309)null, 0);
         class_1058 sprite = getFirstParticleSprite(state);
         if (sprite != null) {
            int glId = mc.method_1531().method_4619(sprite.method_45852()).method_4624();
            return new CachedSprite(sprite, glId, 16777215);
         } else {
            return null;
         }
      });
   }

   private static class_1058 getFirstParticleSprite(class_10444 state) {
      class_1087 model = state.field_55340[0].field_55346;
      return state.field_55339 != 0 && model != null ? model.method_4711() : null;
   }

   private ItemRender() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static record CachedSprite(class_1058 sprite, int glId, int color) {
   }
}

