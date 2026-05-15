package fun.Yanderov.main.listener.impl;

import fun.Yanderov.Yanderov;
import fun.Yanderov.events.item.UsingItemEvent;
import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.main.listener.Listener;
import fun.Yanderov.utils.client.managers.api.draggable.AbstractDraggable;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.client.packet.network.Network;
import fun.Yanderov.utils.interactions.inv.InventoryFlowManager;
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
      Yanderov.getInstance().getAttackPerpetrator().tick();
      InventoryFlowManager.tick();
      Yanderov.getInstance().getDraggableRepository().draggable().forEach(AbstractDraggable::tick);
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
      switch (var2.typeSwitch<invokedynamic>(var2, var3)) {
         case 0:
            class_2848 command = (class_2848)var2;
            boolean var6;
            switch (command.method_12365()) {
               case field_12981 -> var6 = true;
               case field_12985 -> var6 = false;
               default -> var6 = serverSprint;
            }

            serverSprint = var6;
            break;
         case 1:
            class_2868 slot = (class_2868)var2;
            selectedSlot = slot.method_12442();
      }

      Network.packet(e);
      Yanderov.getInstance().getAttackPerpetrator().onPacket(e);
      Yanderov.getInstance().getDraggableRepository().draggable().forEach((drag) -> drag.packet(e));
   }

   @EventHandler
   public void onUsingItemEvent(UsingItemEvent e) {
      Yanderov.getInstance().getAttackPerpetrator().onUsingItem(e);
   }
}

