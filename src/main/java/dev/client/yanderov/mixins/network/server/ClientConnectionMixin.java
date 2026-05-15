package dev.client.yanderov.mixins.network.server;

import dev.client.yanderov.common.proxy.Proxy;
import dev.client.yanderov.common.proxy.ProxyServer;
import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.utils.client.managers.event.EventManager;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.proxy.Socks5ProxyHandler;
import java.net.InetSocketAddress;
import net.minecraft.class_2535;
import net.minecraft.class_2547;
import net.minecraft.class_2596;
import net.minecraft.class_2598;
import net.minecraft.class_8762;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_2535.class})
public class ClientConnectionMixin {
   @Inject(
      method = {"handlePacket"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void handlePacketPre(class_2596 packet, class_2547 listener, CallbackInfo info) {
      PacketEvent packetEvent = new PacketEvent(packet, PacketEvent.Type.RECEIVE);
      EventManager.callEvent(packetEvent);
      if (packetEvent.isCancelled()) {
         info.cancel();
      }

   }

   @Inject(
      method = {"send(Lnet/minecraft/network/packet/Packet;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void sendPre(class_2596 packet, CallbackInfo info) {
      PacketEvent packetEvent = new PacketEvent(packet, PacketEvent.Type.SEND);
      EventManager.callEvent(packetEvent);
      if (packetEvent.isCancelled()) {
         info.cancel();
      }

   }

   @Inject(
      method = {"addHandlers"},
      at = {@At("RETURN")}
   )
   private static void addHandlersHook(ChannelPipeline pipeline, class_2598 side, boolean local, class_8762 packetSizeLogger, CallbackInfo ci) {
      Proxy proxy = ProxyServer.proxy;
      if (proxy != null && ProxyServer.proxyEnabled && side == class_2598.field_11942 && !local) {
         pipeline.addFirst(new ChannelHandler[]{new Socks5ProxyHandler(new InetSocketAddress(proxy.getIp(), proxy.getPort()), proxy.username, proxy.password)});
      }

   }
}

