package fun.Yanderov.utils.features.autobuy;

import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.utils.math.time.TimerUtil;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_266;
import net.minecraft.class_269;
import net.minecraft.class_638;
import net.minecraft.class_746;
import net.minecraft.class_8646;

public class ServerManager {
   private List anarchyServers165 = new ArrayList();
   private List anarchyServers214 = new ArrayList();
   private int currentServerIndex = 0;
   private String currentServer = "";
   private boolean inHub = false;
   private boolean waitingForServerLoad = false;
   private TimerUtil hubCheckTimer = TimerUtil.create();
   private TimerUtil serverSwitchCooldown = TimerUtil.create();
   private BooleanSetting bypassDelay;
   private BooleanSetting bypassDelay1214;

   public ServerManager(BooleanSetting bypassDelay, BooleanSetting bypassDelay1214) {
      this.bypassDelay = bypassDelay;
      this.bypassDelay1214 = bypassDelay1214;
      this.initializeServers();
   }

   private void initializeServers() {
      this.anarchyServers165.addAll(List.of("/an102", "/an103", "/an104", "/an105", "/an106", "/an107"));

      for(int i = 203; i <= 221; ++i) {
         this.anarchyServers165.add("/an" + i);
      }

      for(int i = 302; i <= 313; ++i) {
         this.anarchyServers165.add("/an" + i);
      }

      this.anarchyServers165.addAll(List.of("/an502", "/an503", "/an504", "/an505", "/an506", "/an507", "/an602"));

      for(int i = 11; i <= 14; ++i) {
         this.anarchyServers214.add("/an" + i);
      }

      for(int i = 21; i <= 27; ++i) {
         this.anarchyServers214.add("/an" + i);
      }

      for(int i = 31; i <= 34; ++i) {
         this.anarchyServers214.add("/an" + i);
      }

      for(int i = 51; i <= 53; ++i) {
         this.anarchyServers214.add("/an" + i);
      }

      this.anarchyServers214.add("/an91");
   }

   public void resetTimers() {
      this.hubCheckTimer.resetCounter();
      this.serverSwitchCooldown.resetCounter();
   }

   public void reset() {
      this.currentServerIndex = 0;
      this.currentServer = "";
      this.inHub = false;
      this.waitingForServerLoad = false;
   }

   public void updateHubStatus(class_638 world) {
      this.inHub = this.isInHubInternal(world);
   }

   private boolean isInHubInternal(class_638 world) {
      if (world == null) {
         return true;
      } else {
         class_269 scoreboard = world.method_8428();
         class_266 objective = scoreboard.method_1189(class_8646.field_45157);
         if (objective == null) {
            return true;
         } else {
            String displayName = objective.method_1114().getString();
            return !displayName.contains("ÐÐ½Ð°Ñ€Ñ…Ð¸Ñ-");
         }
      }
   }

   private int getCurrentAnarchyNumber(class_638 world) {
      if (world == null) {
         return -1;
      } else {
         class_269 scoreboard = world.method_8428();
         class_266 objective = scoreboard.method_1189(class_8646.field_45157);
         if (objective != null) {
            String displayName = objective.method_1114().getString();
            if (displayName.contains("ÐÐ½Ð°Ñ€Ñ…Ð¸Ñ-")) {
               String[] parts = displayName.split("-");
               if (parts.length > 1) {
                  try {
                     return Integer.parseInt(parts[1].trim());
                  } catch (NumberFormatException var7) {
                     return -1;
                  }
               }
            }
         }

         return -1;
      }
   }

   private String getNextServer(List servers, class_638 world) {
      if (servers.isEmpty()) {
         return null;
      } else {
         int currentAnarchy = this.getCurrentAnarchyNumber(world);
         if (currentAnarchy != -1) {
            String currentServerCmd = "/an" + currentAnarchy;
            int currentIdx = servers.indexOf(currentServerCmd);
            if (currentIdx != -1) {
               this.currentServerIndex = currentIdx;
            }
         }

         this.currentServerIndex = (this.currentServerIndex + 1) % servers.size();
         return (String)servers.get(this.currentServerIndex);
      }
   }

   public void switchToNextServer(class_746 player, NetworkManager networkManager, boolean isBuyer) {
      if (this.serverSwitchCooldown.hasTimeElapsed(3000L)) {
         if (isBuyer) {
            List<String> availableServers = this.getAvailableServers();
            if (availableServers != null) {
               class_638 world = (class_638)player.method_37908();
               String newServer = this.getNextServer(availableServers, world);
               if (newServer != null) {
                  this.currentServer = newServer;
                  CommandSender.sendCommand(player, newServer);
                  networkManager.sendToAllClients("switch_server:" + newServer);
                  this.waitingForServerLoad = true;
                  this.serverSwitchCooldown.resetCounter();
               }

            }
         }
      }
   }

   public void joinAnarchyFromHub(class_746 player) {
      List<String> availableServers = this.getAvailableServers();
      if (availableServers != null && !availableServers.isEmpty()) {
         String server = (String)availableServers.get(0);
         CommandSender.sendCommand(player, server);
         this.waitingForServerLoad = true;
         this.hubCheckTimer.resetCounter();
      }
   }

   private List getAvailableServers() {
      if (this.bypassDelay1214.isValue()) {
         return new ArrayList(this.anarchyServers214);
      } else {
         return this.bypassDelay.isValue() ? new ArrayList(this.anarchyServers165) : null;
      }
   }

   public boolean shouldJoinAnarchy(boolean bypass165, boolean bypass1214) {
      return this.inHub && this.hubCheckTimer.hasTimeElapsed(3000L) && (bypass165 || bypass1214);
   }

   public boolean isInHub() {
      return this.inHub;
   }

   public boolean isWaitingForServerLoad() {
      return this.waitingForServerLoad;
   }

   public void setWaitingForServerLoad(boolean value) {
      this.waitingForServerLoad = value;
   }
}

