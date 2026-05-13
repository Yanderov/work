package dev.client.modules.impl.util;

import dev.client.event.classes.ReceivePacketEvent;
import dev.client.event.interfaces.IReceivePacketable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.PlayerModel;
import dev.client.modules.settings.impl.BooleanSetting;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class BetterChat extends Module implements IReceivePacketable {
   public final BooleanSetting antiSpam = new BooleanSetting().name("AntiSpam").value(false);
   private String lastMessage = null;
   private int spamCount = 0;
   private static final MinecraftClient mc = MinecraftClient.getInstance();

   public BetterChat() {
      super(new PlayerModel("BetterChat", Category.UTIL, "Запоминает историю чата и убирает повторные сообщения"));
      this.addSetting(this.antiSpam);
   }

   public void onReceivePacket(ReceivePacketEvent event) {
      Packet rawPacket = event.getPacket();
      if (rawPacket instanceof GameMessageS2CPacket packet) {
         String text = packet.content().getString();
         if (this.antiSpam.getValue()) {
            if (text.equals(this.lastMessage)) {
               event.cancel();
               ++this.spamCount;
               mc.inGameHud.getChatHud().addMessage(Text.literal(text + " §7[x" + this.spamCount + "]"));
            } else {
               this.lastMessage = text;
               this.spamCount = 1;
            }
         }

      }
   }
}

