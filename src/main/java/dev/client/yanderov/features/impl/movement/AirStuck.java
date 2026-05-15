package dev.client.yanderov.features.impl.movement;

import antidaunleak.api.annotation.Native;
import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import java.util.Objects;
import net.minecraft.class_2596;
import net.minecraft.class_2828;

public class AirStuck extends Module {
   public AirStuck() {
      super("AirStuck", "Air Stuck", ModuleCategory.MOVEMENT);
   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginMutation
   )
   public void onPacket(PacketEvent e) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         mc.field_1724.method_18800((double)0.0F, (double)0.0F, (double)0.0F);
         mc.field_1724.method_6125(0.0F);
         class_2596 var10000 = e.getPacket();
         Objects.requireNonNull(var10000);
         class_2596 var2 = var10000;
         byte var3 = 0;
         //$FF: var3->value
         //0->net/minecraft/class_2828
         // TODO: Fix switch statement for var2
        if (var2 != null) {
            // // case 0:
            // class_2828 move = (class_2828)var2;
            // e.cancel();
            // // default:
         }
      }
   }
}

