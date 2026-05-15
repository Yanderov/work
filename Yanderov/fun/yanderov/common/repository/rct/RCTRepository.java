package fun.Yanderov.common.repository.rct;

import fun.Yanderov.display.hud.Notifications;
import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.client.managers.event.EventManager;
import fun.Yanderov.utils.client.packet.network.Network;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.display.interfaces.QuickLogger;
import fun.Yanderov.utils.interactions.inv.InventoryTask;
import fun.Yanderov.utils.math.time.StopWatch;
import net.minecraft.class_124;
import net.minecraft.class_1707;
import net.minecraft.class_1713;
import net.minecraft.class_2596;
import net.minecraft.class_437;
import net.minecraft.class_476;
import net.minecraft.class_7439;

public class RCTRepository implements QuickImports, QuickLogger {
   private final StopWatch stopWatch = new StopWatch();
   private boolean lobby;
   private int anarchy;

   public RCTRepository(EventManager eventManager) {
      eventManager.register(this);
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (this.anarchy != 0) {
         class_2596 var3 = e.getPacket();
         if (var3 instanceof class_7439) {
            class_7439 message = (class_7439)var3;
            String text = message.comp_763().getString().toLowerCase();
            if (!text.contains("Ñ…Ð°Ð±") && text.contains("Ð½Ðµ ÑƒÐ´Ð°Ð»Ð¾ÑÑŒ")) {
               Notifications.getInstance().addList("[RCT] ÐÐ° Ð´Ð°Ð½Ð½ÑƒÑŽ Ð°Ð½Ð°Ñ€Ñ…Ð¸ÑŽ " + String.valueOf(class_124.field_1061) + "Ð½ÐµÐ»ÑŒÐ·Ñ" + String.valueOf(class_124.field_1070) + " Ð·Ð°Ð¹Ñ‚Ð¸", 3000L);
               this.anarchy = 0;
            }
         }
      }

   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.anarchy != 0) {
         if (!Network.isHolyWorld()) {
            this.anarchy = 0;
         } else {
            int currentAnarchy = Network.getAnarchy();
            if (this.lobby) {
               if (currentAnarchy == -1) {
                  this.lobby = false;
               } else {
                  mc.field_1724.field_3944.method_45730("hub");
               }

            } else if (currentAnarchy == this.anarchy) {
               this.anarchy = 0;
            } else {
               class_437 var4 = mc.field_1755;
               if (var4 instanceof class_476) {
                  class_476 screen = (class_476)var4;
                  if (screen.method_25440().getString().equals("Ð’Ñ‹Ð±Ð¾Ñ€ Ð›Ð°Ð¹Ñ‚ Ð°Ð½Ð°Ñ€Ñ…Ð¸Ð¸:")) {
                     boolean secondScreen = ((class_1707)screen.method_17577()).method_7629().method_5439() < 10;
                     int[] slots = this.anarchy < 15 ? new int[]{0, 0} : (this.anarchy < 33 ? new int[]{1, 14} : (this.anarchy < 48 ? new int[]{2, 32} : new int[]{3, 47}));
                     if (secondScreen) {
                        InventoryTask.clickSlot(slots[0], 0, class_1713.field_7790, false);
                     } else {
                        InventoryTask.clickSlot(17 + this.anarchy - slots[1], 0, class_1713.field_7790, false);
                     }

                     return;
                  }
               }

               if (this.stopWatch.every((double)500.0F)) {
                  mc.field_1724.field_3944.method_45730("lite");
               }

            }
         }
      }
   }

   public void reconnect(int anarchy) {
      if (anarchy > 0 && anarchy < 64) {
         this.anarchy = anarchy;
         this.lobby = true;
      } else {
         Notifications.getInstance().addList("[RCT] ÐÐµ Ð²ÐµÑ€Ð½Ñ‹Ð¹ " + String.valueOf(class_124.field_1061) + "Ð»Ð°Ð¹Ñ‚", 3000L);
      }

   }
}

