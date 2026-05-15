package dev.client.yanderov;

import com.google.common.eventbus.EventBus;
import dev.client.yanderov.commands.CommandDispatcher;
import dev.client.yanderov.commands.manager.CommandRepository;
import dev.client.yanderov.common.discord.DiscordManager;
import dev.client.yanderov.common.repository.box.BoxESPRepository;
import dev.client.yanderov.common.repository.macro.MacroRepository;
import dev.client.yanderov.common.repository.rct.RCTRepository;
import dev.client.yanderov.common.repository.way.WayRepository;
import dev.client.yanderov.display.screens.clickgui.MenuScreen;
import dev.client.yanderov.display.screens.mainmenu.altscreen.impl.Account;
import dev.client.yanderov.display.screens.mainmenu.altscreen.impl.AccountRepository;
import dev.client.yanderov.features.module.ModuleProvider;
import dev.client.yanderov.features.module.ModuleRepository;
import dev.client.yanderov.features.module.ModuleSwitcher;
import dev.client.yanderov.main.client.ClientInfo;
import dev.client.yanderov.main.client.ClientInfoProvider;
import dev.client.yanderov.main.listener.ListenerRepository;
import dev.client.yanderov.mixins.client.IMinecraftClient;
import dev.client.yanderov.utils.client.chat.ChatMessage;
import dev.client.yanderov.utils.client.logs.Logger;
import dev.client.yanderov.utils.client.managers.api.draggable.DraggableRepository;
import dev.client.yanderov.utils.client.managers.event.EventManager;
import dev.client.yanderov.utils.client.managers.file.DirectoryCreator;
import dev.client.yanderov.utils.client.managers.file.FileController;
import dev.client.yanderov.utils.client.managers.file.FileRepository;
import dev.client.yanderov.utils.client.managers.file.exception.FileProcessingException;
import dev.client.yanderov.utils.client.managers.file.impl.AccountFile;
import dev.client.yanderov.utils.client.managers.file.impl.AutoBuyConfigFile;
import dev.client.yanderov.utils.client.sound.SoundManager;
import dev.client.yanderov.utils.connection.auracheckft.FTCheckClient;
import dev.client.yanderov.utils.connection.cloud.CloudConfigWebSocketClient;
import dev.client.yanderov.utils.connection.irc.IRCManager;
import dev.client.yanderov.utils.connection.tps.TPSCalculate;
import dev.client.yanderov.utils.display.scissor.ScissorAssist;
import dev.client.yanderov.utils.features.aura.striking.StrikerConstructor;
import java.io.File;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.session.Session;
import net.minecraft.client.session.report.AbuseReportContext;
import net.minecraft.client.session.report.ReporterEnvironment;
import net.minecraft.client.util.ProfileKeys;
import net.minecraft.client.util.SocialInteractionsManager;
import com.mojang.authlib.minecraft.UserApiService;

/**
 * Интеграционный класс для Yanderov компонентов в WildClient
 */
public class YanderovIntegration {
   private static YanderovIntegration instance;
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

   public YanderovIntegration() {
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

   public static YanderovIntegration getInstance() {
      if (instance == null) {
         instance = new YanderovIntegration();
      }
      return instance;
   }

   public void initialize() {
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

   private void loadCurrentAccount() {
      if (this.accountRepository.currentAccount != null && !this.accountRepository.currentAccount.isEmpty()) {
         Account currentAcc = this.accountRepository.accountList.stream()
            .filter((acc) -> acc.name.equals(this.accountRepository.currentAccount))
            .findFirst()
            .orElse(null);
         if (currentAcc != null) {
            this.setSession(currentAcc);
         }
      }
   }

   private void setSession(Account account) {
      Session newSession = new Session(
         account.name, 
         UUID.fromString(account.uuid), 
         "0", 
         Optional.empty(), 
         Optional.empty(), 
         Session.AccountType.MOJANG
      );
      IMinecraftClient mca = (IMinecraftClient) MinecraftClient.getInstance();
      mca.setSessionT(newSession);
      MinecraftClient.getInstance().getSessionProperties().clear();
      UserApiService apiService = UserApiService.OFFLINE;
      mca.setUserApiService(apiService);
      mca.setSocialInteractionsManagerT(new SocialInteractionsManager(MinecraftClient.getInstance(), apiService));
      mca.setProfileKeys(ProfileKeys.create(apiService, newSession, MinecraftClient.getInstance().runDirectory.toPath()));
      mca.setAbuseReportContextT(AbuseReportContext.create(ReporterEnvironment.ofIntegratedServer(), apiService));
   }

   private void initWebSocketClient() {
      try {
         this.cloudConfigClient = new CloudConfigWebSocketClient(new URI("ws://45.155.205.202:8080"));
         this.cloudConfigClient.connect();
      } catch (Exception var2) {
      }
   }

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
      File clientDirectory = new File(MinecraftClient.getInstance().runDirectory, "\\WildClient\\");
      File filesDirectory = new File(clientDirectory, "\\Files\\");
      this.clientInfoProvider = new ClientInfo("WildClient + Yanderov", "NoHurt", "Developer", clientDirectory, filesDirectory);
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
                  ChatMessage.ircmessageWithRed("Переподключение к серверу IRC не удалось");
               }
            } finally {
               this.reconnecting = false;
            }
         }
      }, 10L, 10L, TimeUnit.SECONDS);
   }

   // Getters
   public EventManager getEventManager() { return this.eventManager; }
   public EventBus getEventBus() { return this.eventBus; }
   public ModuleRepository getModuleRepository() { return this.moduleRepository; }
   public ModuleSwitcher getModuleSwitcher() { return this.moduleSwitcher; }
   public CommandRepository getCommandRepository() { return this.commandRepository; }
   public CommandDispatcher getCommandDispatcher() { return this.commandDispatcher; }
   public BoxESPRepository getBoxESPRepository() { return this.boxESPRepository; }
   public MacroRepository getMacroRepository() { return this.macroRepository; }
   public WayRepository getWayRepository() { return this.wayRepository; }
   public RCTRepository getRCTRepository() { return this.RCTRepository; }
   public ModuleProvider getModuleProvider() { return this.moduleProvider; }
   public DraggableRepository getDraggableRepository() { return this.draggableRepository; }
   public DiscordManager getDiscordManager() { return this.discordManager; }
   public FileRepository getFileRepository() { return this.fileRepository; }
   public FileController getFileController() { return this.fileController; }
   public ScissorAssist getScissorManager() { return this.scissorManager; }
   public ClientInfoProvider getClientInfoProvider() { return this.clientInfoProvider; }
   public ListenerRepository getListenerRepository() { return this.listenerRepository; }
   public StrikerConstructor getAttackPerpetrator() { return this.attackPerpetrator; }
   public CloudConfigWebSocketClient getCloudConfigClient() { return this.cloudConfigClient; }
   public FTCheckClient getFtCheckClient() { return this.ftCheckClient; }
   public IRCManager getIrcManager() { return this.ircManager; }
   public AccountRepository getAccountRepository() { return this.accountRepository; }
   public TPSCalculate getTpsCalculate() { return this.tpsCalculate; }
   public boolean isInitialized() { return this.initialized; }
   public boolean isShowIrcMessages() { return this.showIrcMessages; }
   public ScheduledExecutorService getReconnectScheduler() { return this.reconnectScheduler; }
   public boolean isReconnecting() { return this.reconnecting; }

   // Setters
   public void setShowIrcMessages(boolean showIrcMessages) { this.showIrcMessages = showIrcMessages; }
}
