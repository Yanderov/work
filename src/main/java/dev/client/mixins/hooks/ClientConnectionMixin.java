package dev.client.mixins.hooks;

import dev.client.WildClient;
import dev.client.event.classes.ReceivePacketEvent;
import dev.client.event.classes.SendPacketEvent;
import io.netty.channel.ChannelHandlerContext;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.PacketCallbacks;
import net.minecraft.network.packet.Packet;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({ClientConnection.class})
public class ClientConnectionMixin {
   @Inject(
      method = {"channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/packet/Packet;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   void receivePacket(ChannelHandlerContext channelHandlerContext, Packet<?> packet, CallbackInfo ci) {
      ReceivePacketEvent receivePacketEvent = new ReceivePacketEvent(packet);
      WildClient.INSTANCE.getEventManager().hookEvent(receivePacketEvent);
      if (receivePacketEvent.isCancelled()) {
         receivePacketEvent.resetCancel();
         ci.cancel();
      }

   }

   @Inject(
      method = {"send(Lnet/minecraft/network/packet/Packet;Lnet/minecraft/network/PacketCallbacks;Z)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   void receivePacket(Packet<?> packet, @Nullable PacketCallbacks callbacks, boolean flush, CallbackInfo ci) {
      SendPacketEvent sendPacketEvent = new SendPacketEvent(packet);
      WildClient.INSTANCE.getEventManager().hookEvent(sendPacketEvent);
      if (sendPacketEvent.isCancelled()) {
         sendPacketEvent.resetCancel();
         ci.cancel();
      }

   }
}
