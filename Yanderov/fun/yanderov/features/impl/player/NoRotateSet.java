package fun.Yanderov.features.impl.player;

import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.RotationUpdateEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import net.minecraft.class_2708;

public class NoRotateSet extends Module implements QuickImports {
   private final SelectSetting mode = (new SelectSetting("Mode", "Ð ÐµÐ¶Ð¸Ð¼ Ð¾Ð±Ñ€Ð°Ð±Ð¾Ñ‚ÐºÐ¸ Ð¿Ð¾Ð²Ð¾Ñ€Ð¾Ñ‚Ð¾Ð² ÑÐµÑ€Ð²ÐµÑ€Ð°")).value("SilentAccept", "ResetRotation").selected("SilentAccept");
   private boolean receivedRotate = false;
   private float lastClientYaw = 0.0F;
   private float lastClientPitch = 0.0F;

   public NoRotateSet() {
      super("NoRotateSet", ModuleCategory.PLAYER);
      this.setup(new Setting[]{this.mode});
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (e.getType() == PacketEvent.Type.RECEIVE) {
         Object pkt = e.getPacket();
         if (pkt instanceof class_2708) {
            this.receivedRotate = true;
         }

      }
   }

   @EventHandler
   public void onRotation(RotationUpdateEvent e) {
      if (e.getType() == 0) {
         if (mc.field_1724 != null) {
            this.lastClientYaw = mc.field_1724.method_36454();
            this.lastClientPitch = mc.field_1724.method_36455();
         }

      } else if (e.getType() == 2) {
         if (this.receivedRotate && mc.field_1724 != null) {
            if (this.mode.isSelected("SilentAccept") || this.mode.isSelected("ResetRotation")) {
               try {
                  mc.field_1724.method_36456(this.lastClientYaw);
                  mc.field_1724.method_36457(this.lastClientPitch);
                  mc.field_1724.method_5847(this.lastClientYaw);
                  mc.field_1724.field_6283 = this.lastClientYaw;
               } catch (Throwable var3) {
               }
            }

            this.receivedRotate = false;
         }
      }
   }
}

