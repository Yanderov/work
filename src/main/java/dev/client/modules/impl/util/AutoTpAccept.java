package dev.client.modules.impl.util;

import dev.client.WildClient;
import dev.client.event.classes.ReceivePacketEvent;
import dev.client.event.interfaces.IReceivePacketable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import dev.client.modules.settings.impl.BooleanSetting;
import dev.client.util.IUtil;
import dev.client.util.other.Friend;
import java.util.Locale;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

@Environment(EnvType.CLIENT)
public class AutoTpAccept extends Module implements IReceivePacketable, IUtil {
   private final BooleanSetting onlyFriends = new BooleanSetting().name("Only Friends").value(false);

   public AutoTpAccept() {
      super(new ModuleBranding("AutoTpAccept", Category.UTIL, "Принимает запросы на телепортацию"));
      this.addSetting(this.onlyFriends);
   }

   public void onReceivePacket(ReceivePacketEvent receivePacketEvent) {
      if (mc.player != null && mc.world != null) {
         Packet rawPacket = receivePacketEvent.getPacket();
         if (rawPacket instanceof ChatMessageC2SPacket packet) {
            String raw = packet.chatMessage().toLowerCase(Locale.ROOT);
            if (raw.contains("просит телепортироваться к вам") || raw.contains("has requested teleport")) {
               if (this.onlyFriends.getValue()) {
                  boolean yes = false;

                  for(Friend friend : WildClient.INSTANCE.getFriendManager().getFriends()) {
                     if (raw.contains(friend.name().toLowerCase(Locale.ROOT))) {
                        yes = true;
                        break;
                     }
                  }

                  if (!yes) {
                     return;
                  }
               }

               mc.getNetworkHandler().sendChatCommand("tpaccept");
            }
         }

      }
   }
}

