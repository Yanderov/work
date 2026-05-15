package fun.Yanderov.features.impl.movement;

import antidaunleak.api.annotation.Native;
import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.utils.client.managers.event.EventHandler;
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
         switch (var2.typeSwitch<invokedynamic>(var2, var3)) {
            case 0:
               class_2828 move = (class_2828)var2;
               e.cancel();
            default:
         }
      }
   }
}

