package dev.client.yanderov.main.listener.impl;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.events.item.UsingItemEvent;
import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.main.listener.Listener;
import dev.client.yanderov.utils.client.managers.api.draggable.AbstractDraggable;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.client.packet.network.Network;
import dev.client.yanderov.utils.interactions.inv.InventoryFlowManager;
import java.util.Objects;
import net.minecraft.class_2596;
import net.minecraft.class_2848;
import net.minecraft.class_2868;

public class EventListener implements Listener {
   public static boolean serverSprint;
   public static int selectedSlot;

   @EventHandler
   public void onTick(TickEvent e) {
      Network.tick();
      YanderovIntegration.getInstance().getAttackPerpetrator().tick();
      InventoryFlowManager.tick();
      YanderovIntegration.getInstance().getDraggableRepository().draggable().forEach(AbstractDraggable::tick);
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      class_2596 var10000 = e.getPacket();
      Objects.requireNonNull(var10000);
      class_2596 var2 = var10000;
      byte var3 = 0;
      //$FF: var3->value
      //0->net/minecraft/class_2848
      //1->net/minecraft/class_2868
      // TODO: Fix switch statement for var2
        if (var2 != null) {
            // // case 0:
            // class_2848 command = (class_2848)var2;
            // boolean var6;
            // switch (command.method_12365()) {
            // // case field_12981 -> var6 = true;
            // // case field_12985 -> var6 = false;
            // default -> var6 = serverSprint;
            // }

            // serverSprint = var6;
            // break;
            // // case 1:
            // class_2868 slot = (class_2868)var2;
            // selectedSlot = slot.method_12442();
      }

      Network.packet(e);
      YanderovIntegration.getInstance().getAttackPerpetrator().onPacket(e);
      YanderovIntegration.getInstance().getDraggableRepository().draggable().forEach((drag) -> drag.packet(e));
   }

   @EventHandler
   public void onUsingItemEvent(UsingItemEvent e) {
      YanderovIntegration.getInstance().getAttackPerpetrator().onUsingItem(e);
   }
}

