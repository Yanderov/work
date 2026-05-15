package dev.client.yanderov.features.impl.misc;

import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_418;

public class DeathCoords extends Module implements QuickImports {
   private boolean reported = false;

   public DeathCoords() {
      super("DeathCoords", ModuleCategory.MISC);
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc != null && mc.field_1724 != null) {
            boolean isDeadScreen = mc.field_1755 instanceof class_418;
            boolean isDead = false;

            try {
               isDead = mc.field_1724.method_6032() <= 0.0F;
            } catch (Throwable var10) {
            }

            if (isDeadScreen && isDead) {
               if (!this.reported) {
                  int x = (int)Math.floor(mc.field_1724.method_23317());
                  int y = (int)Math.floor(mc.field_1724.method_23318());
                  int z = (int)Math.floor(mc.field_1724.method_23321());
                  String msg = "ÐšÐ¾Ð¾Ñ€Ð´Ð¸Ð½Ð°Ñ‚Ñ‹: " + String.valueOf(class_124.field_1080) + "X: " + x + " Y: " + y + " Z: " + z + String.valueOf(class_124.field_1070);

                  try {
                     mc.field_1724.method_7353(class_2561.method_30163(msg), false);
                  } catch (Throwable var9) {
                  }

                  this.reported = true;
               }
            } else {
               this.reported = false;
            }

         }
      }
   }
}

