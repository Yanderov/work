package fun.Yanderov.commands.defaults;

import fun.Yanderov.Yanderov;
import fun.Yanderov.features.module.ModuleRepository;
import fun.Yanderov.main.client.ClientInfoProvider;
import fun.Yanderov.utils.client.managers.api.command.Command;
import fun.Yanderov.utils.client.managers.api.command.IBaritoneChatControl;
import fun.Yanderov.utils.client.managers.api.command.argument.IArgConsumer;
import fun.Yanderov.utils.client.managers.api.command.datatypes.ConfigFileDataType;
import fun.Yanderov.utils.client.managers.api.command.exception.CommandException;
import fun.Yanderov.utils.client.managers.api.command.helpers.Paginator;
import fun.Yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import fun.Yanderov.utils.client.managers.file.FileController;
import fun.Yanderov.utils.client.managers.file.FileRepository;
import fun.Yanderov.utils.client.managers.file.exception.FileProcessingException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_5250;

public class ConfigCommand extends Command {
   private final FileController fileController;
   private final ClientInfoProvider clientInfoProvider;

   protected ConfigCommand(Yanderov main) {
      super("config", "cfg");
      this.fileController = main.getFileController();
      this.clientInfoProvider = main.getClientInfoProvider();
   }

   public void execute(String label, IArgConsumer args) throws CommandException {
      String arg = args.hasAny() ? args.getString().toLowerCase(Locale.US) : "list";
      args.requireMax(1);
      if (arg.contains("reset")) {
         ModuleRepository moduleRepository = Yanderov.getInstance().getModuleRepository();
         moduleRepository.modules().forEach((module) -> {
            module.setState(false);
            module.setKey(-1);
         });
         this.logDirect("ÐšÐ»Ð¸ÐµÐ½Ñ‚ ÑÐ±Ñ€Ð¾ÑˆÐµÐ½: Ð²ÑÐµ Ð¼Ð¾Ð´ÑƒÐ»Ð¸ Ð²Ñ‹ÐºÐ»ÑŽÑ‡ÐµÐ½Ñ‹ Ð¸ Ð±Ð¸Ð½Ð´Ñ‹ Ð¾Ñ‡Ð¸Ñ‰ÐµÐ½Ñ‹.", class_124.field_1060);
      }

      if (arg.contains("load")) {
         String name = args.getString();
         File customDir = new File(this.clientInfoProvider.clientDir(), "Custom");
         if ((new File(customDir, name + ".json")).exists()) {
            try {
               FileRepository fileRepository = new FileRepository();
               fileRepository.setup(Yanderov.getInstance());
               FileController fileController = new FileController(fileRepository.getClientFiles(), customDir);
               fileController.loadFile(name + ".json");
               this.logDirect(String.format("ÐšÐ¾Ð½Ñ„Ð¸Ð³ÑƒÑ€Ð°Ñ†Ð¸Ñ %s Ð·Ð°Ð³Ñ€ÑƒÐ¶ÐµÐ½Ð°!", name));
            } catch (FileProcessingException e) {
               this.logDirect(String.format("ÐžÑˆÐ¸Ð±ÐºÐ° Ð¿Ñ€Ð¸ Ð·Ð°Ð³Ñ€ÑƒÐ·ÐºÐµ ÐºÐ¾Ð½Ñ„Ð¸Ð³Ð°! Ð”ÐµÑ‚Ð°Ð»Ð¸: %s", e.getCause().getMessage()), class_124.field_1061);
            }
         } else {
            this.logDirect(String.format("ÐšÐ¾Ð½Ñ„Ð¸Ð³ÑƒÑ€Ð°Ñ†Ð¸Ñ %s Ð½Ðµ Ð½Ð°Ð¹Ð´ÐµÐ½Ð°!", name));
         }
      }

      if (arg.contains("save")) {
         String name = args.getString();

         try {
            File customDir = new File(this.clientInfoProvider.clientDir(), "Custom");
            FileRepository fileRepository = new FileRepository();
            fileRepository.setup(Yanderov.getInstance());
            FileController customFileController = new FileController(fileRepository.getClientFiles(), customDir);
            customFileController.saveFile(name + ".json");
            this.logDirect(String.format("ÐšÐ¾Ð½Ñ„Ð¸Ð³ÑƒÑ€Ð°Ñ†Ð¸Ñ %s ÑÐ¾Ñ…Ñ€Ð°Ð½ÐµÐ½Ð°!", name));
            System.out.println("loaded");
         } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.printf("error %s%n", e.getCause().getMessage());
            this.logDirect(String.format("ÐžÑˆÐ¸Ð±ÐºÐ° Ð¿Ñ€Ð¸ ÑÐ¾Ñ…Ñ€Ð°Ð½ÐµÐ½Ð¸Ð¸ ÐºÐ¾Ð½Ñ„Ð¸Ð³Ð°! Ð”ÐµÑ‚Ð°Ð»Ð¸: %s", e.getCause().getMessage()), class_124.field_1061);
         }
      }

      if (arg.contains("list")) {
         Paginator.paginate(args, (Paginator)(new Paginator(this.getConfigs())), () -> this.logDirect("Ð¡Ð¿Ð¸ÑÐ¾Ðº ÐºÐ¾Ð½Ñ„Ð¸Ð³Ð¾Ð²:"), (config) -> {
            class_5250 namesComponent = class_2561.method_43470(config);
            namesComponent.method_10862(namesComponent.method_10866().method_10977(class_124.field_1068));
            return namesComponent;
         }, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label);
      }

      if (arg.contains("dir")) {
         try {
            File customDir = new File(this.clientInfoProvider.clientDir(), "Custom");
            Runtime.getRuntime().exec("explorer " + customDir.getAbsolutePath());
         } catch (IOException e) {
            this.logDirect("ÐŸÐ°Ð¿ÐºÐ° Ñ ÐºÐ¾Ð½Ñ„Ð¸Ð³ÑƒÑ€Ð°Ñ†Ð¸ÑÐ¼Ð¸ Ð½Ðµ Ð½Ð°Ð¹Ð´ÐµÐ½Ð°!" + e.getMessage());
         }
      }

   }

   public Stream tabComplete(String label, IArgConsumer args) throws CommandException {
      if (args.hasAny()) {
         String arg = args.getString();
         if (!args.hasExactlyOne()) {
            return (new TabCompleteHelper()).sortAlphabetically().prepend("load", "save", "list", "dir", "reset").filterPrefix(arg).stream();
         }

         if (arg.equalsIgnoreCase("load")) {
            return args.tabCompleteDatatype(ConfigFileDataType.INSTANCE);
         }

         if (arg.equalsIgnoreCase("save")) {
            return args.tabCompleteDatatype(ConfigFileDataType.INSTANCE);
         }
      }

      return Stream.empty();
   }

   public String getShortDesc() {
      return "ÐŸÐ¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ Ð²Ð·Ð°Ð¸Ð¼Ð¾Ð´ÐµÐ¹ÑÑ‚Ð²Ð¾Ð²Ð°Ñ‚ÑŒ Ñ ÐºÐ¾Ð½Ñ„Ð¸Ð³Ð°Ð¼Ð¸ Ð² Ñ‡Ð¸Ñ‚Ðµ";
   }

   public List getLongDesc() {
      return Arrays.asList("Ð¡ Ð¿Ð¾Ð¼Ð¾Ñ‰ÑŒÑŽ ÑÑ‚Ð¾Ð¹ ÐºÐ¾Ð¼Ð°Ð½Ð´Ñ‹ Ð¼Ð¾Ð¶Ð½Ð¾ Ð·Ð°Ð³Ñ€ÑƒÐ¶Ð°Ñ‚ÑŒ/ÑÐ¾Ñ…Ñ€Ð°Ð½ÑÑ‚ÑŒ ÐºÐ¾Ð½Ñ„Ð¸Ð³Ð¸", "", "Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ðµ:", "> config load <name> - Ð—Ð°Ð³Ñ€ÑƒÐ¶Ð°ÐµÑ‚ ÐºÐ¾Ð½Ñ„Ð¸Ð³.", "> config save <name> - Ð¡Ð¾Ñ…Ñ€Ð°Ð½ÑÐµÑ‚ ÐºÐ¾Ð½Ñ„Ð¸Ð³.", "> config list - Ð’Ð¾Ð·Ð²Ñ€Ð°Ñ‰Ð°ÐµÑ‚ ÑÐ¿Ð¸ÑÐ¾Ðº ÐºÐ¾Ð½Ñ„Ð¸Ð³Ð¾Ð²", "> config dir - ÐžÑ‚ÐºÑ€Ñ‹Ð²Ð°ÐµÑ‚ Ð¿Ð°Ð¿ÐºÑƒ Ñ ÐºÐ¾Ð½Ñ„Ð¸Ð³Ð°Ð¼Ð¸.", "> config reset - Ð’Ñ‹ÐºÐ»ÑŽÑ‡Ð°ÐµÑ‚ Ð²ÑÐµ Ð¼Ð¾Ð´ÑƒÐ»Ð¸ Ð¸ Ð¾Ñ‡Ð¸Ñ‰Ð°ÐµÑ‚ Ð²ÑÐµ Ð±Ð¸Ð½Ð´Ñ‹ (Ñ‡Ð¸ÑÑ‚Ñ‹Ð¹ ÐºÐ¾Ð½Ñ„Ð¸Ð³).");
   }

   public List getConfigs() {
      List<String> configs = new ArrayList();
      File customDir = new File(Yanderov.getInstance().getClientInfoProvider().clientDir(), "Custom");
      File[] configFiles = customDir.listFiles();
      if (configFiles != null) {
         for(File configFile : configFiles) {
            if (configFile.isFile() && configFile.getName().endsWith(".json")) {
               String configName = configFile.getName().replace(".json", "");
               configs.add(configName);
            }
         }
      }

      return configs;
   }
}

