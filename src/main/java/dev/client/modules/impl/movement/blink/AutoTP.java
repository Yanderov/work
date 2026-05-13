package dev.client.modules.impl.movement.blink;

import dev.client.WildClient;
import dev.client.event.CancellableEvent;
import dev.client.event.classes.SendPacketEvent;
import dev.client.event.classes.TickEvent;
import dev.client.modules.ClassMode;
import dev.client.modules.impl.movement.Blink;
import dev.client.util.math.TimerUtil;
import java.util.concurrent.CopyOnWriteArrayList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.packet.Packet;

@Environment(EnvType.CLIENT)
public class AutoTP extends ClassMode {
   private final CopyOnWriteArrayList<Packet> packets = new CopyOnWriteArrayList<>();
   private boolean canSend = true;
   private final TimerUtil timerUtil = new TimerUtil();

   public CopyOnWriteArrayList<Packet> getPackets() {
      return this.packets;
   }

   public void onEnable() {
      this.packets.clear();
   }

   public void onDisable() {
      this.canSend = true;

      for(Packet packet : this.packets) {
         mc.player.networkHandler.sendPacket(packet);
         this.packets.remove(packet);
      }

      this.packets.clear();
   }

   public void onEvent(CancellableEvent event) {
      if (event instanceof SendPacketEvent sendPacketEvent) {
         if (!this.canSend) {
            this.packets.add(sendPacketEvent.getPacket());
            event.cancel();
         } else if (!this.canSend || !this.packets.contains(sendPacketEvent.getPacket())) {
            this.packets.add(sendPacketEvent.getPacket());
            event.cancel();
         }
      }

      if (event instanceof TickEvent) {
         Blink blink = (Blink)WildClient.INSTANCE.getModuleManager().getByClass(Blink.class);
         long delay = this.canSend ? 150L : (long)(blink.delay.getValue() * 50.0F);
         if (this.timerUtil.isReached(delay)) {
            this.canSend = !this.canSend;
         }

         if (this.canSend) {
            for(Packet packet : this.packets) {
               mc.player.networkHandler.sendPacket(packet);
               this.packets.remove(packet);
            }
         }
      }

   }
}
