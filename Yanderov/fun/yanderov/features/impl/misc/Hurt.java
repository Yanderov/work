package fun.Yanderov.features.impl.misc;

import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import net.minecraft.class_2828;
import net.minecraft.class_634;

public class Hurt extends Module {
   public Hurt() {
      super("Hurt", ModuleCategory.MISC);
   }

   public void activate() {
      super.activate();
      if (mc != null && mc.field_1724 != null && mc.method_1562() != null && mc.field_1687 != null) {
         class_634 nh = mc.method_1562();
         double x = mc.field_1724.method_23317();
         double y = mc.field_1724.method_23318();
         double z = mc.field_1724.method_23321();
         boolean horizontalCollision = mc.field_1724.field_5976;
         int damage = 1;
         int repeats = 65 * damage;

         try {
            for(int i = 0; i < repeats; ++i) {
               nh.method_48296().method_10743(new class_2828.class_2829(x, y + 0.049, z, false, horizontalCollision));
               nh.method_48296().method_10743(new class_2828.class_2829(x, y, z, false, horizontalCollision));
            }

            nh.method_48296().method_10743(new class_2828.class_2829(x, y, z, true, horizontalCollision));
         } catch (Throwable var12) {
         }

         this.setState(false);
      } else {
         this.setState(false);
      }
   }
}

