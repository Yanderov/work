package dev.client.yanderov.features.impl.player;

import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.TextSetting;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.math.time.StopWatch;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_2561;
import net.minecraft.class_640;

public class PlayerFinder extends Module implements QuickImports {
   private static final int[] ANARCHY_NUMBERS = new int[]{102, 103, 104, 105, 106, 107, 108, 109, 110, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 602, 603, 604, 605, 606};
   private static final int[] SPOOKYTIME_ANARCHIES = new int[]{101, 102, 103, 104, 105, 106, 107, 108, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 301, 302, 303, 304, 305, 306, 401, 402, 501, 502, 503, 504, 601, 602, 603};
   private final TextSetting playerName = new TextSetting("ÐÐ¸Ðº", "Pid0ras");
   private final SelectSetting serverMode = (new SelectSetting("Ð¡ÐµÑ€Ð²ÐµÑ€", "Ð’Ñ‹Ð±Ð¾Ñ€ ÑÐµÑ€Ð²ÐµÑ€Ð°")).value("Funtime", "SpookyTime").selected("Funtime");
   private final BooleanSetting searchPremium = (new BooleanSetting("ÐŸÐ¾Ð¸ÑÐº Ð¿Ñ€ÐµÐ¼Ð¸ÑƒÐ¼", "Ð˜ÑÐºÐ°Ñ‚ÑŒ Ñ‚Ð¾Ð»ÑŒÐºÐ¾ Ð¿Ñ€ÐµÐ¼Ð¸ÑƒÐ¼")).setValue(false);
   private int currentAnarchyIndex = 0;
   private boolean isInHub = false;
   private boolean hubCommandSent = false;
   private boolean serverCommandSent = false;
   private String currentServer = "";
   private final List checkedServers = new ArrayList();
   private final StopWatch hubTimer = new StopWatch();
   private final StopWatch serverTimer = new StopWatch();
   private final StopWatch connectionTimer = new StopWatch();

   public PlayerFinder() {
      super("PlayerFinder", ModuleCategory.PLAYER);
      this.setup(new Setting[]{this.playerName, this.serverMode, this.searchPremium});
   }

   public void activate() {
      super.activate();

      try {
         if (mc.field_1724 != null) {
            mc.field_1724.method_7346();
         }
      } catch (Throwable var2) {
      }

      this.currentAnarchyIndex = 0;
      this.isInHub = false;
      this.hubCommandSent = false;
      this.serverCommandSent = false;
      this.currentServer = "";
      this.checkedServers.clear();
      this.hubTimer.reset();
      this.serverTimer.reset();
      this.connectionTimer.reset();
   }

   public void deactivate() {
      super.deactivate();
      this.currentAnarchyIndex = 0;
      this.isInHub = false;
      this.hubCommandSent = false;
      this.serverCommandSent = false;
      this.currentServer = "";
      this.checkedServers.clear();
      this.hubTimer.reset();
      this.serverTimer.reset();
      this.connectionTimer.reset();
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            int[] anarchyList = this.serverMode.isSelected("SpookyTime") ? SPOOKYTIME_ANARCHIES : ANARCHY_NUMBERS;
            if (!this.isInHub && this.currentAnarchyIndex == 0) {
               if (!this.hubCommandSent) {
                  this.safeChat("/hub");
                  this.hubCommandSent = true;
                  this.hubTimer.reset();
               }

               if (this.hubTimer.finished((double)1500.0F)) {
                  this.isInHub = true;
                  this.serverTimer.reset();
               }

            } else {
               if (this.isInHub && this.currentAnarchyIndex < anarchyList.length) {
                  if (this.serverCommandSent) {
                     if (this.connectionTimer.finished((double)280.0F)) {
                        if (this.findPlayer(this.playerName.getText())) {
                           String var3 = this.playerName.getText();
                           this.printMsg("ÐÐ°ÑˆÑ‘Ð» Ð¸Ð³Ñ€Ð¾ÐºÐ° " + var3 + " Ð½Ð° Ð°Ð½Ð°Ñ€Ñ…Ð¸Ð¸ " + this.currentServer);
                           this.setState(false);
                           return;
                        }

                        this.serverCommandSent = false;
                        this.serverTimer.reset();
                     }

                     return;
                  }

                  if (!this.serverCommandSent && this.serverTimer.finished((double)1000.0F)) {
                     int var10001 = anarchyList[this.currentAnarchyIndex];
                     this.currentServer = "/an" + var10001;
                     if (!this.checkedServers.contains(this.currentServer)) {
                        this.safeChat(this.currentServer);
                        this.checkedServers.add(this.currentServer);
                        this.printMsg("ÐŸÑ€Ð¾Ð²ÐµÑ€ÑÑŽ " + this.currentServer);
                        ++this.currentAnarchyIndex;
                        this.serverCommandSent = true;
                        this.connectionTimer.reset();
                     } else {
                        ++this.currentAnarchyIndex;
                     }
                  }
               } else if (this.currentAnarchyIndex >= anarchyList.length) {
                  this.printMsg("Ð˜Ð³Ñ€Ð¾Ðº " + this.playerName.getText() + " Ð½Ðµ Ð½Ð°Ð¹Ð´ÐµÐ½.");
                  this.setState(false);
               }

            }
         }
      }
   }

   @EventHandler
   public void onPacket(PacketEvent event) {
      if (event.getType() == PacketEvent.Type.RECEIVE) {
         Object pkt = event.getPacket();

         try {
            String n = pkt.getClass().getSimpleName().toLowerCase();
            if (n.contains("disconnect")) {
               this.setState(false);
            }
         } catch (Throwable var4) {
         }

      }
   }

   private boolean findPlayer(String username) {
      if (mc.method_1562() == null) {
         return false;
      } else {
         for(class_640 e : mc.method_1562().method_2880()) {
            try {
               String name = e.method_2966().getName();
               if (name != null && name.equalsIgnoreCase(username)) {
                  return true;
               }
            } catch (Throwable var6) {
            }
         }

         return false;
      }
   }

   private void safeChat(String msg) {
      try {
         if (mc.method_1562() != null) {
            mc.method_1562().method_45729(msg);
         }
      } catch (Throwable var3) {
      }

   }

   private void printMsg(String s) {
      try {
         if (mc != null && mc.field_1724 != null) {
            mc.field_1724.method_7353(class_2561.method_30163(s), false);
         }
      } catch (Throwable var3) {
      }

   }
}

