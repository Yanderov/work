package dev.client.yanderov.features.impl.movement;

import antidaunleak.api.annotation.Native;
import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.math.projection.Projection;

public class NoFallDamage extends Module {
   private final SelectSetting mode = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ñ‚Ð¸Ð¿")).value("SpookyTime").selected("SpookyTime");

   public NoFallDamage() {
      super("NoFallDamage", "No Fall Damage", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.mode});
   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginUltra
   )
   public void onPacket(PacketEvent e) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (mc.field_1724.field_6017 > 0.0F && Projection.getDistanceToGround() > (double)4.0F) {
            mc.field_1724.method_18800((double)0.0F, (double)0.0F, (double)0.0F);
         }

      }
   }

   public SelectSetting getMode() {
      return this.mode;
   }
}

