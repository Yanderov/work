package dev.client.yanderov.features.impl.misc;

import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.AutoBuyableItem;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.manager.AutoBuyManager;
import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.features.autobuy.AfkHandler;
import dev.client.yanderov.utils.features.autobuy.AuctionHandler;
import dev.client.yanderov.utils.features.autobuy.BuyRequest;
import dev.client.yanderov.utils.features.autobuy.CommandSender;
import dev.client.yanderov.utils.features.autobuy.NetworkManager;
import dev.client.yanderov.utils.features.autobuy.PurchaseHandler;
import dev.client.yanderov.utils.features.autobuy.ServerManager;
import dev.client.yanderov.utils.features.autobuy.ServerSwitchHandler;
import dev.client.yanderov.utils.features.autobuy.StorageManager;
import dev.client.yanderov.utils.math.time.TimerUtil;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1707;
import net.minecraft.class_1735;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_437;
import net.minecraft.class_476;
import net.minecraft.class_7439;

public class AutoBuy extends Module {
   private SelectSetting leaveType = (new SelectSetting("Ð¢Ð¸Ð¿ Ð¾Ð±Ñ…Ð¾Ð´Ð°", "ÐŸÑ€Ð¾Ð²ÐµÑ€ÑÑŽÑ‰Ð¸Ð¹")).value("ÐŸÑ€Ð¾Ð²ÐµÑ€ÑÑŽÑ‰Ð¸Ð¹", "ÐŸÐ¾ÐºÑƒÐ¿Ð°ÑŽÑ‰Ð¸Ð¹");
   private SliderSettings timer2 = (new SliderSettings("Ð¢Ð°Ð¹Ð¼ÐµÑ€ Ð¾Ð±Ð½Ð¾Ð²Ð»ÐµÐ½Ð¸Ñ Ð°ÑƒÐºÑ†Ð¸Ð¾Ð½Ð°", "")).setValue(350.0F).range(350, 750);
   private BooleanSetting bypassDelay = new BooleanSetting("ÐžÐ±Ñ…Ð¾Ð´ Ð·Ð°Ð´ÐµÑ€Ð¶ÐºÐ¸ 1.16.5 Ð°Ð½ÐºÐ°Ñ…", "");
   private BooleanSetting bypassDelay1214 = new BooleanSetting("ÐžÐ±Ñ…Ð¾Ð´ Ð·Ð°Ð´ÐµÑ€Ð¶ÐºÐ¸ 1.21.4 Ð°Ð½ÐºÐ°Ñ…", "");
   private BooleanSetting autoStorage = new BooleanSetting("ÐÐ²Ñ‚Ð¾ÑÐºÐ»Ð°Ð´Ð¸Ñ€Ð¾Ð²Ð°Ð½Ð¸Ðµ", "");
   private AutoBuyManager autoBuyManager = AutoBuyManager.getInstance();
   private NetworkManager networkManager;
   private ServerManager serverManager;
   private StorageManager storageManager;
   private AuctionHandler auctionHandler;
   private AfkHandler afkHandler;
   private TimerUtil openTimer = TimerUtil.create();
   private TimerUtil updateTimer = TimerUtil.create();
   private TimerUtil buyTimer = TimerUtil.create();
   private TimerUtil switchTimer = TimerUtil.create();
   private TimerUtil enterDelayTimer = TimerUtil.create();
   private TimerUtil ahSpamTimer = TimerUtil.create();
   private TimerUtil connectionCheckTimer = TimerUtil.create();
   private TimerUtil auctionRequestTimer = TimerUtil.create();
   private boolean open = false;
   private boolean serverInAuction = false;
   private boolean justEntered = false;
   private boolean spammingAh = false;
   private boolean waitingForAuctionOpen = false;
   private List cachedEnabledItems = new ArrayList();

   public AutoBuy() {
      super("Auto Buy", "Auto Buy", ModuleCategory.MISC);
      this.timer2.visible(() -> this.leaveType.isSelected("ÐŸÐ¾ÐºÑƒÐ¿Ð°ÑŽÑ‰Ð¸Ð¹"));
      this.bypassDelay.visible(() -> this.leaveType.isSelected("ÐŸÐ¾ÐºÑƒÐ¿Ð°ÑŽÑ‰Ð¸Ð¹"));
      this.bypassDelay1214.visible(() -> this.leaveType.isSelected("ÐŸÐ¾ÐºÑƒÐ¿Ð°ÑŽÑ‰Ð¸Ð¹"));
      this.autoStorage.visible(() -> this.leaveType.isSelected("ÐŸÐ¾ÐºÑƒÐ¿Ð°ÑŽÑ‰Ð¸Ð¹"));
      this.setup(new Setting[]{this.leaveType, this.timer2, this.bypassDelay, this.bypassDelay1214, this.autoStorage});
      this.networkManager = new NetworkManager();
      this.serverManager = new ServerManager(this.bypassDelay, this.bypassDelay1214);
      this.storageManager = new StorageManager(this.autoStorage);
      this.auctionHandler = new AuctionHandler(this.autoBuyManager);
      this.afkHandler = new AfkHandler();
   }

   public void activate() {
      super.activate();
      this.resetTimers();
      this.resetState();
      if (this.leaveType.isSelected("ÐŸÐ¾ÐºÑƒÐ¿Ð°ÑŽÑ‰Ð¸Ð¹") && (this.bypassDelay.isValue() || this.bypassDelay1214.isValue())) {
         mc.field_1690.field_1837 = false;
      }

      this.cacheEnabledItems();
      this.networkManager.start(this.leaveType.getSelected());
   }

   public void deactivate() {
      super.deactivate();
      this.networkManager.stop();
      this.serverManager.reset();
      this.storageManager.reset();
      this.afkHandler.resetMovementKeys(mc.field_1690);
   }

   private void resetTimers() {
      this.openTimer.resetCounter();
      this.updateTimer.resetCounter();
      this.buyTimer.resetCounter();
      this.switchTimer.resetCounter();
      this.enterDelayTimer.resetCounter();
      this.ahSpamTimer.resetCounter();
      this.connectionCheckTimer.resetCounter();
      this.auctionRequestTimer.resetCounter();
      this.serverManager.resetTimers();
      this.storageManager.resetTimers();
      this.afkHandler.resetTimers();
   }

   private void resetState() {
      this.open = false;
      this.serverInAuction = false;
      this.justEntered = false;
      this.spammingAh = false;
      this.waitingForAuctionOpen = false;
      this.cachedEnabledItems.clear();
      this.networkManager.clearQueues();
      this.auctionHandler.clear();
   }

   private void cacheEnabledItems() {
      this.cachedEnabledItems.clear();

      for(AutoBuyableItem item : this.autoBuyManager.getAllItems()) {
         if (item.isEnabled()) {
            this.cachedEnabledItems.add(item);
         }
      }

   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      class_2596 var3 = e.getPacket();
      if (var3 instanceof class_7439 gameMessage) {
         class_2561 content = gameMessage.comp_763();
         String message = content.getString();
         if (message.contains("Ð’Ñ‹ ÑƒÐ¶Ðµ Ð¿Ð¾Ð´ÐºÐ»ÑŽÑ‡ÐµÐ½Ñ‹ Ðº ÑÑ‚Ð¾Ð¼Ñƒ ÑÐµÑ€Ð²ÐµÑ€Ñƒ!")) {
            this.serverManager.switchToNextServer(mc.field_1724, this.networkManager, this.leaveType.isSelected("ÐŸÐ¾ÐºÑƒÐ¿Ð°ÑŽÑ‰Ð¸Ð¹"));
            return;
         }

         if (this.leaveType.isSelected("ÐŸÐ¾ÐºÑƒÐ¿Ð°ÑŽÑ‰Ð¸Ð¹")) {
            PurchaseHandler.handlePurchaseMessage(message, this.autoBuyManager);
         }
      }

   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (this.autoBuyManager.isEnabled()) {
            this.handleConnectionStatus();
            this.afkHandler.handle(mc);
            this.storageManager.handle(mc, this.open);
            if (!this.storageManager.isActive()) {
               if (this.storageManager.handlePostStorage(mc, this.enterDelayTimer, this.ahSpamTimer)) {
                  this.justEntered = true;
               }

               boolean wasInHub = this.serverManager.isInHub();
               this.serverManager.updateHubStatus(mc.field_1687);
               if (this.serverManager.shouldJoinAnarchy(this.bypassDelay.isValue(), this.bypassDelay1214.isValue())) {
                  this.serverManager.joinAnarchyFromHub(mc.field_1724);
               }

               if (wasInHub && !this.serverManager.isInHub()) {
                  this.handleServerSwitch();
               }

               if ((this.serverManager.isWaitingForServerLoad() || ServerSwitchHandler.isWaitingForServerLoad()) && (ServerSwitchHandler.hasTimedOut() || !wasInHub && !this.serverManager.isInHub())) {
                  this.serverManager.setWaitingForServerLoad(false);
                  ServerSwitchHandler.setWaitingForServerLoad(false);
                  this.handleServerSwitch();
               }

               this.handleAhSpam();
               this.handleAuction();
               this.handleServerAutoSwitch();
               this.handleCheckerAuctionRequest();
            }
         }
      }
   }

   private void handleConnectionStatus() {
      if (this.leaveType.isSelected("ÐŸÑ€Ð¾Ð²ÐµÑ€ÑÑŽÑ‰Ð¸Ð¹") && this.connectionCheckTimer.hasTimeElapsed(5000L)) {
         if (!this.networkManager.isConnectedToServer()) {
            this.networkManager.start(this.leaveType.getSelected());
         }

         this.connectionCheckTimer.resetCounter();
      }

   }

   private void handleServerSwitch() {
      this.justEntered = true;
      this.enterDelayTimer.resetCounter();
      this.switchTimer.resetCounter();
      this.storageManager.resetMaxShulkers();
      this.waitingForAuctionOpen = false;
      this.auctionRequestTimer.resetCounter();
   }

   private void handleAhSpam() {
      if (this.bypassDelay.isValue() || this.bypassDelay1214.isValue()) {
         if (this.justEntered && this.enterDelayTimer.hasTimeElapsed(2000L) && !this.spammingAh) {
            this.spammingAh = true;
            this.ahSpamTimer.resetCounter();
         }

         if (this.spammingAh && !this.afkHandler.isPerformingAction() && this.ahSpamTimer.hasTimeElapsed(1250L)) {
            if (mc.field_1724.field_3944 != null) {
               CommandSender.sendCommand(mc.field_1724, "/ah");
            }

            this.ahSpamTimer.resetCounter();
         }
      }

   }

   private void handleCheckerAuctionRequest() {
      if (this.leaveType.isSelected("ÐŸÑ€Ð¾Ð²ÐµÑ€ÑÑŽÑ‰Ð¸Ð¹")) {
         if (!this.open && !this.waitingForAuctionOpen && this.auctionRequestTimer.hasTimeElapsed(3000L) && this.networkManager.isConnectedToServer()) {
            CommandSender.openAuction();
            this.waitingForAuctionOpen = true;
            this.auctionRequestTimer.resetCounter();
         }

         if (this.waitingForAuctionOpen && this.auctionRequestTimer.hasTimeElapsed(5000L)) {
            this.waitingForAuctionOpen = false;
            this.auctionRequestTimer.resetCounter();
         }
      }

   }

   private void handleAuction() {
      class_437 var2 = mc.field_1755;
      if (var2 instanceof class_476 screen) {
         String title = screen.method_25440().getString();
         int syncId = ((class_1707)screen.method_17577()).field_7763;
         List<class_1735> slots = ((class_1707)screen.method_17577()).field_7761;
         if (!title.contains("ÐÑƒÐºÑ†Ð¸Ð¾Ð½") && !title.contains("ÐÑƒÐºÑ†Ð¸Ð¾Ð½Ñ‹") && !title.contains("ÐŸÐ¾Ð¸ÑÐº")) {
            if (title.contains("ÐŸÐ¾Ð´Ð¾Ð·Ñ€Ð¸Ñ‚ÐµÐ»ÑŒÐ½Ð°Ñ Ñ†ÐµÐ½Ð°")) {
               this.auctionHandler.handleSuspiciousPrice(mc, syncId, slots);
               this.openTimer.resetCounter();
               this.buyTimer.resetCounter();
            } else {
               this.exitAuction();
            }
         } else {
            if (!this.open) {
               this.enterAuction();
               return;
            }

            this.storageManager.handleAuctionEnter();
            if (this.leaveType.isSelected("ÐŸÐ¾ÐºÑƒÐ¿Ð°ÑŽÑ‰Ð¸Ð¹")) {
               this.handleBuyerMode(screen, syncId, slots);
            } else if (this.leaveType.isSelected("ÐŸÑ€Ð¾Ð²ÐµÑ€ÑÑŽÑ‰Ð¸Ð¹")) {
               this.handleCheckerMode(slots);
            }
         }
      } else {
         this.exitAuction();
      }

   }

   private void enterAuction() {
      this.open = true;
      this.openTimer.resetCounter();
      this.updateTimer.resetCounter();
      this.buyTimer.resetCounter();
      this.storageManager.notifyAuctionEnter();
      this.serverInAuction = true;
      this.auctionHandler.clear();
      this.justEntered = false;
      this.spammingAh = false;
      this.waitingForAuctionOpen = false;
      this.storageManager.clearStorageCompleted();
      if (!this.storageManager.getPostStorageTimer().hasTimeElapsed(2000L)) {
         this.storageManager.disableStartStorage();
      }

      this.cacheEnabledItems();
      if (this.leaveType.isSelected("ÐŸÑ€Ð¾Ð²ÐµÑ€ÑÑŽÑ‰Ð¸Ð¹")) {
         this.networkManager.notifyAuctionEnter();
      }

      if (this.leaveType.isSelected("ÐŸÐ¾ÐºÑƒÐ¿Ð°ÑŽÑ‰Ð¸Ð¹")) {
         this.networkManager.requestAuctionOpen();
      }

   }

   private void exitAuction() {
      if (this.open) {
         this.open = false;
         this.serverInAuction = false;
         this.auctionHandler.clear();
         if (this.leaveType.isSelected("ÐŸÑ€Ð¾Ð²ÐµÑ€ÑÑŽÑ‰Ð¸Ð¹")) {
            this.networkManager.notifyAuctionLeave();
         }
      }

   }

   private void handleBuyerMode(class_476 screen, int syncId, List slots) {
      long clientCount = this.networkManager.getClientInAuctionCount();
      if (this.networkManager.getQueueSize() > 30) {
         this.auctionHandler.updateAuction(mc, syncId);
         this.networkManager.sendUpdateToClients();
         this.updateTimer.resetCounter();
         this.networkManager.clearQueues();
      } else {
         if (!this.storageManager.hasReachedMaxShulkers()) {
            BuyRequest request = this.networkManager.pollRequest();
            if (request != null) {
               this.auctionHandler.handleBuyRequest(mc, syncId, slots, request, this.networkManager);
            }
         }

         if (this.auctionHandler.shouldUpdate()) {
            this.auctionHandler.updateAuction(mc, syncId);
            this.networkManager.sendUpdateToClients();
            this.updateTimer.resetCounter();
            this.networkManager.clearQueues();
         }

         if (this.updateTimer.hasTimeElapsed((long)this.timer2.getValue()) && this.serverInAuction && clientCount > 0L && this.networkManager.isQueuesEmpty()) {
            this.auctionHandler.updateAuction(mc, syncId);
            this.networkManager.sendUpdateToClients();
            this.updateTimer.resetCounter();
         }

      }
   }

   private void handleCheckerMode(List slots) {
      List<class_1735> bestSlots = this.auctionHandler.findMatchingSlots(slots, this.cachedEnabledItems);
      if (!bestSlots.isEmpty()) {
         this.auctionHandler.processBestSlots(bestSlots, this.networkManager);
         this.buyTimer.resetCounter();
      }

   }

   private void handleServerAutoSwitch() {
      if (this.leaveType.isSelected("ÐŸÐ¾ÐºÑƒÐ¿Ð°ÑŽÑ‰Ð¸Ð¹") && (this.bypassDelay.isValue() || this.bypassDelay1214.isValue()) && !this.serverManager.isInHub() && this.switchTimer.hasTimeElapsed(60000L)) {
         this.serverManager.switchToNextServer(mc.field_1724, this.networkManager, true);
      }

   }
}

