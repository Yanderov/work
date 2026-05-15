package dev.client.yanderov.features.impl.player;

import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.events.player.RotationUpdateEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
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

