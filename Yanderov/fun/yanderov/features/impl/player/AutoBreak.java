package fun.Yanderov.features.impl.player;

import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import net.minecraft.class_1268;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_239;
import net.minecraft.class_2680;
import net.minecraft.class_3965;
import net.minecraft.class_239.class_240;

public class AutoBreak extends Module implements QuickImports {
   public AutoBreak() {
      super("AutoBreak", ModuleCategory.PLAYER);
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc.field_1724 != null && mc.field_1687 != null && mc.field_1761 != null) {
            try {
               if (mc.field_1755 != null) {
                  return;
               }
            } catch (Throwable var9) {
            }

            try {
               if (mc.field_1690 == null || !mc.field_1690.field_1886.method_1434()) {
                  return;
               }
            } catch (Throwable var10) {
               return;
            }

            class_239 target = mc.field_1765;
            if (target instanceof class_3965) {
               class_3965 bhr = (class_3965)target;
               if (target.method_17783() == class_240.field_1332) {
                  class_2338 bp = bhr.method_17777();
                  class_2680 state = mc.field_1687.method_8320(bp);
                  if (state != null && !state.method_26215()) {
                     class_2350 face = bhr.method_17780();

                     try {
                        mc.field_1761.method_2902(bp, face);
                        mc.field_1724.method_6104(class_1268.field_5808);
                     } catch (Throwable var8) {
                     }

                     return;
                  }

                  return;
               }
            }

         }
      }
   }
}

