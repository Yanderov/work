package fun.Yanderov;

import antidaunleak.api.annotation.Native;
import com.google.common.eventbus.EventBus;
import com.mojang.authlib.minecraft.UserApiService;
import fun.Yanderov.commands.CommandDispatcher;
import fun.Yanderov.commands.manager.CommandRepository;
import fun.Yanderov.common.discord.DiscordManager;
import fun.Yanderov.common.repository.box.BoxESPRepository;
import fun.Yanderov.common.repository.macro.MacroRepository;
import fun.Yanderov.common.repository.rct.RCTRepository;
import fun.Yanderov.common.repository.way.WayRepository;
import fun.Yanderov.display.screens.clickgui.MenuScreen;
import fun.Yanderov.display.screens.mainmenu.altscreen.impl.Account;
import fun.Yanderov.display.screens.mainmenu.altscreen.impl.AccountRepository;
import fun.Yanderov.features.module.ModuleProvider;
import fun.Yanderov.features.module.ModuleRepository;
import fun.Yanderov.features.module.ModuleSwitcher;
import fun.Yanderov.main.client.ClientInfo;
import fun.Yanderov.main.client.ClientInfoProvider;
import fun.Yanderov.main.listener.ListenerRepository;
import fun.Yanderov.mixins.client.IMinecraftClient;
import fun.Yanderov.utils.client.chat.ChatMessage;
import fun.Yanderov.utils.client.logs.Logger;
import fun.Yanderov.utils.client.managers.api.draggable.DraggableRepository;
import fun.Yanderov.utils.client.managers.event.EventManager;
import fun.Yanderov.utils.client.managers.file.DirectoryCreator;
import fun.Yanderov.utils.client.managers.file.FileController;
import fun.Yanderov.utils.client.managers.file.FileRepository;
import fun.Yanderov.utils.client.managers.file.exception.FileProcessingException;
import fun.Yanderov.utils.client.managers.file.impl.AccountFile;
import fun.Yanderov.utils.client.managers.file.impl.AutoBuyConfigFile;
import fun.Yanderov.utils.client.sound.SoundManager;
import fun.Yanderov.utils.connection.auracheckft.FTCheckClient;
import fun.Yanderov.utils.connection.cloud.CloudConfigWebSocketClient;
import fun.Yanderov.utils.connection.irc.IRCManager;
import fun.Yanderov.utils.connection.tps.TPSCalculate;
import fun.Yanderov.utils.display.scissor.ScissorAssist;
import fun.Yanderov.utils.features.aura.striking.StrikerConstructor;
import java.io.File;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.fabricmc.api.ModInitializer;
import net.minecraft.class_310;
import net.minecraft.class_320;
import net.minecraft.class_5520;
import net.minecraft.class_7569;
import net.minecraft.class_7574;
import net.minecraft.class_7853;
import net.minecraft.class_320.class_321;

public class Yanderov implements ModInitializer {
   public static Yanderov instance;
   private EventManager eventManager = new EventManager();
   private EventBus eventBus = new EventBus();
   private ModuleRepository moduleRepository;
   private ModuleSwitcher moduleSwitcher;
   private CommandRepository commandRepository;
   private CommandDispatcher commandDispatcher;
   private BoxESPRepository boxESPRepository;
   private MacroRepository macroRepository;
   private WayRepository wayRepository;
   private RCTRepository RCTRepository;
   private ModuleProvider moduleProvider;
   private DraggableRepository draggableRepository;
   private DiscordManager discordManager;
   private FileRepository fileRepository;
   private FileController fileController;
   private ScissorAssist scissorManager;
   private ClientInfoProvider clientInfoProvider;
   private ListenerRepository listenerRepository;
   private StrikerConstructor attackPerpetrator;
   private CloudConfigWebSocketClient cloudConfigClient;
   private FTCheckClient ftCheckClient;
   private IRCManager ircManager;
   private AccountRepository accountRepository;
   private TPSCalculate tpsCalculate;
   private boolean initialized;
   private boolean showIrcMessages;
   private ScheduledExecutorService reconnectScheduler;
   private boolean reconnecting;

   public Yanderov() {
      this.boxESPRepository = new BoxESPRepository(this.eventManager);
      this.macroRepository = new MacroRepository(this.eventManager);
      this.wayRepository = new WayRepository(this.eventManager);
      this.RCTRepository = new RCTRepository(this.eventManager);
      this.scissorManager = new ScissorAssist();
      this.attackPerpetrator = new StrikerConstructor();
      this.ircManager = new IRCManager();
      this.showIrcMessages = false;
      this.reconnecting = false;
   }

   public static Yanderov getInstance() {
      return instance;
   }

   @Native(
      type = Native.Type.VMProtectBeginMutation
   )
   public void onInitialize() {
      instance = this;
      this.initClientInfoProvider();
      this.initModules();
      this.initDraggable();
      this.initFileManager();
      this.initCommands();
      this.initListeners();
      this.initDiscordRPC();
      this.initWebSocketClient();
      this.initFTCheckClient();
      this.ircManager.connect();
      this.startReconnectTask();
      SoundManager.init();
      this.loadCurrentAccount();
      MenuScreen menuScreen = new MenuScreen();
      menuScreen.initialize();
      this.initialized = true;
      (new Thread(() -> {
         try {
            Thread.sleep(3000L);
         } catch (Exception var1) {
         }

      })).start();
   }

   @Native(
      type = Native.Type.VMProtectBeginMutation
   )
   private void loadCurrentAccount() {
      if (this.accountRepository.currentAccount != null && !this.accountRepository.currentAccount.isEmpty()) {
         Account currentAcc = (Account)this.accountRepository.accountList.stream().filter((acc) -> acc.name.equals(this.accountRepository.currentAccount)).findFirst().orElse((Object)null);
         if (currentAcc != null) {
            this.setSession(currentAcc);
         }
      }

   }

   @Native(
      type = Native.Type.VMProtectBeginMutation
   )
   private void setSession(Account account) {
      class_320 newSession = new class_320(account.name, UUID.fromString(account.uuid), "0", Optional.empty(), Optional.empty(), class_321.field_1988);
      IMinecraftClient mca = (IMinecraftClient)class_310.method_1551();
      mca.setSessionT(newSession);
      class_310.method_1551().method_53462().getProperties().clear();
      UserApiService apiService = UserApiService.OFFLINE;
      mca.setUserApiService(apiService);
      mca.setSocialInteractionsManagerT(new class_5520(class_310.method_1551(), apiService));
      mca.setProfileKeys(class_7853.method_46532(apiService, newSession, class_310.method_1551().field_1697.toPath()));
      mca.setAbuseReportContextT(class_7574.method_44599(class_7569.method_44586(), apiService));
   }

   @Native(
      type = Native.Type.VMProtectBeginUltra
   )
   private void initWebSocketClient() {
      try {
         this.cloudConfigClient = new CloudConfigWebSocketClient(new URI("ws://45.155.205.202:8080"));
         this.cloudConfigClient.connect();
      } catch (Exception var2) {
      }

   }

   @Native(
      type = Native.Type.VMProtectBeginUltra
   )
   private void initFTCheckClient() {
      try {
         this.ftCheckClient = new FTCheckClient(new URI("ws://45.155.205.202:6312"));
         this.ftCheckClient.connect();
      } catch (Exception var2) {
      }

   }

   private void initDraggable() {
      this.draggableRepository = new DraggableRepository();
      this.draggableRepository.setup();
   }

   private void initModules() {
      this.moduleRepository = new ModuleRepository();
      this.moduleRepository.setup();
      this.moduleProvider = new ModuleProvider(this.moduleRepository.modules());
      this.moduleSwitcher = new ModuleSwitcher(this.moduleRepository.modules(), this.eventManager);
   }

   private void initCommands() {
      this.commandRepository = new CommandRepository();
      this.commandDispatcher = new CommandDispatcher(this.eventManager);
   }

   private void initDiscordRPC() {
      String os = System.getProperty("os.name").toLowerCase();
      if (!os.contains("linux")) {
         this.discordManager = new DiscordManager();
         this.discordManager.init();
      }
   }

   private void initClientInfoProvider() {
      File clientDirectory = new File(class_310.method_1551().field_1697, "\\Yanderov\\");
      File filesDirectory = new File(clientDirectory, "\\Files\\");
      this.clientInfoProvider = new ClientInfo("Yanderov Build 2.3", "NoHurt", "Developer", clientDirectory, filesDirectory);
   }

   private void initFileManager() {
      DirectoryCreator directoryCreator = new DirectoryCreator();
      directoryCreator.createDirectories(this.clientInfoProvider.clientDir(), this.clientInfoProvider.filesDir());
      File autoBuyDir = new File(this.clientInfoProvider.clientDir(), "AutoBuy");
      if (!autoBuyDir.exists()) {
         autoBuyDir.mkdirs();
      }

      File customDir = new File(this.clientInfoProvider.clientDir(), "Custom");
      if (!customDir.exists()) {
         customDir.mkdirs();
      }

      this.fileRepository = new FileRepository();
      this.fileRepository.setup(this);
      this.accountRepository = new AccountRepository();
      this.fileRepository.getClientFiles().add(new AccountFile(this.accountRepository));
      this.fileRepository.getClientFiles().add(new AutoBuyConfigFile());
      this.fileController = new FileController(this.fileRepository.getClientFiles(), this.clientInfoProvider.filesDir());

      try {
         this.fileController.loadFiles();
      } catch (FileProcessingException e) {
         Logger.error("Failed to load files: " + e.getMessage());
      }

   }

   private void initListeners() {
      this.listenerRepository = new ListenerRepository();
      this.listenerRepository.setup();
      this.tpsCalculate = new TPSCalculate();
   }

   private void startReconnectTask() {
      this.reconnectScheduler = Executors.newSingleThreadScheduledExecutor();
      this.reconnectScheduler.scheduleAtFixedRate(() -> {
         if ((this.ircManager.getClient() == null || !this.ircManager.getClient().isOpen()) && !this.reconnecting) {
            this.reconnecting = true;

            try {
               this.ircManager.connect();
            } catch (Exception var5) {
               if (this.showIrcMessages) {
                  ChatMessage.ircmessageWithRed("ÐŸÐµÑ€ÐµÐ¿Ð¾Ð´ÐºÐ»ÑŽÑ‡ÐµÐ½Ð¸Ðµ Ðº ÑÐµÑ€Ð²ÐµÑ€Ñƒ IRC Ð½Ðµ ÑƒÐ´Ð°Ð»Ð¾ÑÑŒ");
               }
            } finally {
               this.reconnecting = false;
            }
         }

      }, 10L, 10L, TimeUnit.SECONDS);
   }

   public EventManager getEventManager() {
      return this.eventManager;
   }

   public EventBus getEventBus() {
      return this.eventBus;
   }

   public ModuleRepository getModuleRepository() {
      return this.moduleRepository;
   }

   public ModuleSwitcher getModuleSwitcher() {
      return this.moduleSwitcher;
   }

   public CommandRepository getCommandRepository() {
      return this.commandRepository;
   }

   public CommandDispatcher getCommandDispatcher() {
      return this.commandDispatcher;
   }

   public BoxESPRepository getBoxESPRepository() {
      return this.boxESPRepository;
   }

   public MacroRepository getMacroRepository() {
      return this.macroRepository;
   }

   public WayRepository getWayRepository() {
      return this.wayRepository;
   }

   public RCTRepository getRCTRepository() {
      return this.RCTRepository;
   }

   public ModuleProvider getModuleProvider() {
      return this.moduleProvider;
   }

   public DraggableRepository getDraggableRepository() {
      return this.draggableRepository;
   }

   public DiscordManager getDiscordManager() {
      return this.discordManager;
   }

   public FileRepository getFileRepository() {
      return this.fileRepository;
   }

   public FileController getFileController() {
      return this.fileController;
   }

   public ScissorAssist getScissorManager() {
      return this.scissorManager;
   }

   public ClientInfoProvider getClientInfoProvider() {
      return this.clientInfoProvider;
   }

   public ListenerRepository getListenerRepository() {
      return this.listenerRepository;
   }

   public StrikerConstructor getAttackPerpetrator() {
      return this.attackPerpetrator;
   }

   public CloudConfigWebSocketClient getCloudConfigClient() {
      return this.cloudConfigClient;
   }

   public FTCheckClient getFtCheckClient() {
      return this.ftCheckClient;
   }

   public IRCManager getIrcManager() {
      return this.ircManager;
   }

   public AccountRepository getAccountRepository() {
      return this.accountRepository;
   }

   public TPSCalculate getTpsCalculate() {
      return this.tpsCalculate;
   }

   public boolean isInitialized() {
      return this.initialized;
   }

   public boolean isShowIrcMessages() {
      return this.showIrcMessages;
   }

   public ScheduledExecutorService getReconnectScheduler() {
      return this.reconnectScheduler;
   }

   public boolean isReconnecting() {
      return this.reconnecting;
   }

   public void setEventManager(EventManager eventManager) {
      this.eventManager = eventManager;
   }

   public void setEventBus(EventBus eventBus) {
      this.eventBus = eventBus;
   }

   public void setModuleRepository(ModuleRepository moduleRepository) {
      this.moduleRepository = moduleRepository;
   }

   public void setModuleSwitcher(ModuleSwitcher moduleSwitcher) {
      this.moduleSwitcher = moduleSwitcher;
   }

   public void setCommandRepository(CommandRepository commandRepository) {
      this.commandRepository = commandRepository;
   }

   public void setCommandDispatcher(CommandDispatcher commandDispatcher) {
      this.commandDispatcher = commandDispatcher;
   }

   public void setBoxESPRepository(BoxESPRepository boxESPRepository) {
      this.boxESPRepository = boxESPRepository;
   }

   public void setMacroRepository(MacroRepository macroRepository) {
      this.macroRepository = macroRepository;
   }

   public void setWayRepository(WayRepository wayRepository) {
      this.wayRepository = wayRepository;
   }

   public void setRCTRepository(RCTRepository RCTRepository) {
      this.RCTRepository = RCTRepository;
   }

   public void setModuleProvider(ModuleProvider moduleProvider) {
      this.moduleProvider = moduleProvider;
   }

   public void setDraggableRepository(DraggableRepository draggableRepository) {
      this.draggableRepository = draggableRepository;
   }

   public void setDiscordManager(DiscordManager discordManager) {
      this.discordManager = discordManager;
   }

   public void setFileRepository(FileRepository fileRepository) {
      this.fileRepository = fileRepository;
   }

   public void setFileController(FileController fileController) {
      this.fileController = fileController;
   }

   public void setScissorManager(ScissorAssist scissorManager) {
      this.scissorManager = scissorManager;
   }

   public void setClientInfoProvider(ClientInfoProvider clientInfoProvider) {
      this.clientInfoProvider = clientInfoProvider;
   }

   public void setListenerRepository(ListenerRepository listenerRepository) {
      this.listenerRepository = listenerRepository;
   }

   public void setAttackPerpetrator(StrikerConstructor attackPerpetrator) {
      this.attackPerpetrator = attackPerpetrator;
   }

   public void setCloudConfigClient(CloudConfigWebSocketClient cloudConfigClient) {
      this.cloudConfigClient = cloudConfigClient;
   }

   public void setFtCheckClient(FTCheckClient ftCheckClient) {
      this.ftCheckClient = ftCheckClient;
   }

   public void setIrcManager(IRCManager ircManager) {
      this.ircManager = ircManager;
   }

   public void setAccountRepository(AccountRepository accountRepository) {
      this.accountRepository = accountRepository;
   }

   public void setTpsCalculate(TPSCalculate tpsCalculate) {
      this.tpsCalculate = tpsCalculate;
   }

   public void setInitialized(boolean initialized) {
      this.initialized = initialized;
   }

   public void setShowIrcMessages(boolean showIrcMessages) {
      this.showIrcMessages = showIrcMessages;
   }

   public void setReconnectScheduler(ScheduledExecutorService reconnectScheduler) {
      this.reconnectScheduler = reconnectScheduler;
   }

   public void setReconnecting(boolean reconnecting) {
      this.reconnecting = reconnecting;
   }
}

