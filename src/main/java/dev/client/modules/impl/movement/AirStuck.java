package dev.client.modules.impl.movement;

import dev.client.event.classes.SendPacketEvent;
import dev.client.event.interfaces.ISendPacketable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

@Environment(EnvType.CLIENT)
public class AirStuck extends Module implements ISendPacketable, IUtil {
   public AirStuck() {
      super(new ModuleBranding("AirStuck", Category.MOVEMENT, "Вызывает зависание игрока в воздухе"));
   }

   public void onSendPacket(SendPacketEvent sendPacketEvent) {
      if (mc.player != null && mc.world != null) {
         mc.player.setVelocity(0.0D, 0.0D, 0.0D);
         mc.player.setMovementSpeed(0.0F);
         if (sendPacketEvent.getPacket() instanceof PlayerMoveC2SPacket) {
            sendPacketEvent.cancel();
         }
      }
   }
}

