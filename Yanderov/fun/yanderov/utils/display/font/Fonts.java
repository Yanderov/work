package fun.Yanderov.utils.display.font;

import fun.Yanderov.Yanderov;
import java.awt.Font;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Fonts {
   private static final Map fontCache = new HashMap();

   public static FontRenderer create(float size, String name) {
      try {
         String path = "assets/minecraft/fonts/" + name + ".ttf";
         InputStream inputStream = Yanderov.class.getClassLoader().getResourceAsStream(path);
         Font baseFont;
         if (inputStream != null) {
            InputStream is = inputStream;

            try {
               baseFont = Font.createFont(0, is);
            } catch (Throwable var9) {
               if (inputStream != null) {
                  try {
                     is.close();
                  } catch (Throwable var8) {
                     var9.addSuppressed(var8);
                  }
               }

               throw var9;
            }

            if (inputStream != null) {
               inputStream.close();
            }
         } else {
            baseFont = new Font("Dialog", 0, Math.round(size));
         }

         Font font = baseFont.deriveFont(size / 2.0F);
         return new FontRenderer(font, size / 2.0F);
      } catch (Throwable $ex) {
         throw $ex;
      }
   }

   public static void init() {
      for(Type type : Fonts.Type.values()) {
         for(int size = 4; size <= 32; ++size) {
            fontCache.put(new FontKey(size, type), create((float)size, type.getType()));
         }
      }

   }

   public static FontRenderer getSize(int size) {
      return getSize(size, Fonts.Type.INST);
   }

   public static FontRenderer getSize(int size, Type type) {
      return (FontRenderer)fontCache.computeIfAbsent(new FontKey(size, type), (k) -> create((float)size, type.getType()));
   }

   public static enum Type {
      DEFAULT("sf_medium"),
      REGULAR("sf_regular"),
      SEMI("sf_semibold"),
      BOLD("sf_bold"),
      BOLDED("bold"),
      MANROPEEXTRABOLD("manropeextrabold"),
      MANROPEBOLD("manropebold"),
      RICHREGULAR("rich_regular"),
      ICONRICHREG("iconrichreg"),
      INST("suisseintl"),
      ICONS("icons"),
      ICONSTYPENEW("icon2"),
      GUIICONS("guiicons"),
      ICONSCATEGORY("categoryicons");

      private final String type;

      public String getType() {
         return this.type;
      }

      private Type(final String type) {
         this.type = type;
      }

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{DEFAULT, REGULAR, SEMI, BOLD, BOLDED, MANROPEEXTRABOLD, MANROPEBOLD, RICHREGULAR, ICONRICHREG, INST, ICONS, ICONSTYPENEW, GUIICONS, ICONSCATEGORY};
      }
   }

   private static record FontKey(int size, Type type) {
   }
}

