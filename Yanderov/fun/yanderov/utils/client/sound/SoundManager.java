package fun.Yanderov.utils.client.sound;

import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_3414;
import net.minecraft.class_3419;
import net.minecraft.class_7923;

public final class SoundManager implements QuickImports {
   public static class_3414 OPEN_GUI = class_3414.method_47908(class_2960.method_60654("minecraft:gui_open"));
   public static class_3414 CLOSE_GUI = class_3414.method_47908(class_2960.method_60654("minecraft:gui_close"));
   public static class_3414 ENABLE_MODULE = class_3414.method_47908(class_2960.method_60654("minecraft:module_enable"));
   public static class_3414 DISABLE_MODULE = class_3414.method_47908(class_2960.method_60654("minecraft:module_disable"));
   public static class_3414 CATEGORY_CLICK = class_3414.method_47908(class_2960.method_60654("minecraft:guicategory_select"));
   public static class_3414 ORTHODOX = class_3414.method_47908(class_2960.method_60654("minecraft:kolokolnia_kill"));

   public static void init() {
      class_2378.method_10230(class_7923.field_41172, OPEN_GUI.comp_3319(), OPEN_GUI);
      class_2378.method_10230(class_7923.field_41172, CLOSE_GUI.comp_3319(), CLOSE_GUI);
      class_2378.method_10230(class_7923.field_41172, ENABLE_MODULE.comp_3319(), ENABLE_MODULE);
      class_2378.method_10230(class_7923.field_41172, DISABLE_MODULE.comp_3319(), DISABLE_MODULE);
      class_2378.method_10230(class_7923.field_41172, CATEGORY_CLICK.comp_3319(), CATEGORY_CLICK);
      class_2378.method_10230(class_7923.field_41172, ORTHODOX.comp_3319(), ORTHODOX);
   }

   public static void playSound(class_3414 sound) {
      playSound(sound, 1.0F, 1.0F);
   }

   public static void playSound(class_3414 sound, float volume, float pitch) {
      if (!PlayerInteractionHelper.nullCheck()) {
         mc.field_1687.method_8396(mc.field_1724, mc.field_1724.method_24515(), sound, class_3419.field_15245, volume, pitch);
      }

   }

   private SoundManager() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}

