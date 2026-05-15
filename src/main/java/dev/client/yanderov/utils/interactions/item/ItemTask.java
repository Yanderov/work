package dev.client.yanderov.utils.interactions.item;

import dev.client.yanderov.utils.display.interfaces.QuickImports;
import net.minecraft.class_1792;
import net.minecraft.class_1796;
import net.minecraft.class_1799;

public final class ItemTask implements QuickImports {
   public static int maxUseTick(class_1792 item) {
      return maxUseTick(item.method_7854());
   }

   public static int maxUseTick(class_1799 stack) {
      int var10000;
      switch (stack.method_7976()) {
         case field_8950:
         case field_8946:
            var10000 = 32;
            break;
         case field_8947:
         case field_8951:
            var10000 = 10;
            break;
         case field_8953:
            var10000 = 20;
            break;
         case field_8949:
            var10000 = 0;
            break;
         default:
            var10000 = stack.method_7935(mc.field_1724);
      }

      return var10000;
   }

   public static float getCooldownProgress(class_1792 item) {
      class_1796 cooldownManager = mc.field_1724.method_7357();
      class_1796.class_1797 entry = (class_1796.class_1797)cooldownManager.field_8024.get(item);
      return entry == null ? 0.0F : Math.max(0.0F, (float)(entry.comp_3084 - cooldownManager.field_8025) / 20.0F);
   }

   private ItemTask() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}

