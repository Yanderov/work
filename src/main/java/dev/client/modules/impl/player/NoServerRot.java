package dev.client.modules.impl.player;

import dev.client.event.classes.ReceivePacketEvent;
import dev.client.event.interfaces.IReceivePacketable;
import dev.client.mixins.other.IPlayerPositionLookS2CPacket;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;

@Environment(EnvType.CLIENT)
public class NoServerRot extends Module implements IReceivePacketable, IUtil {

    public NoServerRot() {
        super(new ModuleBranding("NoServerRot", Category.PLAYER, "Не даёт серверу повернуть вашу голову"));
    }

    @Override
    public void onReceivePacket(ReceivePacketEvent event) {
        if (mc.player == null) return;

        if (event.getPacket() instanceof PlayerPositionLookS2CPacket packet) {
            IPlayerPositionLookS2CPacket accessor = (IPlayerPositionLookS2CPacket) packet;
            accessor.setYaw(mc.player.getYaw());
            accessor.setPitch(mc.player.getPitch());
        }
    }
}
