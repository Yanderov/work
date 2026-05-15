package dev.client.yanderov.commands.defaults;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleProvider;
import dev.client.yanderov.features.module.ModuleRepository;
import dev.client.yanderov.utils.client.chat.StringHelper;
import dev.client.yanderov.utils.client.managers.api.command.Command;
import dev.client.yanderov.utils.client.managers.api.command.IBaritoneChatControl;
import dev.client.yanderov.utils.client.managers.api.command.argument.IArgConsumer;
import dev.client.yanderov.utils.client.managers.api.command.datatypes.KeyDataType;
import dev.client.yanderov.utils.client.managers.api.command.datatypes.ModuleDataType;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandNotEnoughArgumentsException;
import dev.client.yanderov.utils.client.managers.api.command.helpers.Paginator;
import dev.client.yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_5250;

public class BindCommand extends Command {
   private final ModuleProvider moduleProvider;
   private final ModuleRepository moduleRepository;

   public BindCommand(Yanderov main) {
      super("bind");
      this.moduleRepository = main.getModuleRepository();
      this.moduleProvider = main.getModuleProvider();
   }

   public void execute(String label, IArgConsumer args) throws CommandException {
      switch (args.hasAny() ? args.getString().toLowerCase(Locale.US) : "list") {
         case "add" -> this.handleAddBind(args);
         case "remove" -> this.handleRemoveBind(args);
         case "list" -> this.handleListBinds(args, label);
         case "clear" -> this.handleClearBinds(args);
         case "set" -> this.handleSetBind(args);
      }

   }

   private void handleSetBind(IArgConsumer args) throws CommandException {
      args.requireMin(2);
      String target = args.getString().toLowerCase(Locale.US);
      if (target.equals("clickgui")) {
         int key = (Integer)((Map.Entry)args.getDatatypeFor(KeyDataType.INSTANCE)).getValue();
         BindCommand.ClickGuiManager.setClickGuiKey(key);
         String var10001 = String.valueOf(class_124.field_1060);
         this.logDirect(var10001 + "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð´Ð»Ñ Ð¾Ñ‚ÐºÑ€Ñ‹Ñ‚Ð¸Ñ ClickGUI Ð¸Ð·Ð¼ÐµÐ½ÐµÐ½Ð° Ð½Ð°: " + String.valueOf(class_124.field_1061) + StringHelper.getBindName(key).toLowerCase());
      } else {
         throw new CommandException("ÐÐµÐ¸Ð·Ð²ÐµÑÑ‚Ð½Ð°Ñ Ñ†ÐµÐ»ÑŒ Ð´Ð»Ñ ÑƒÑÑ‚Ð°Ð½Ð¾Ð²ÐºÐ¸ Ð±Ð¸Ð½Ð´Ð°: " + target);
      }
   }

   private void handleAddBind(IArgConsumer args) throws CommandException {
      args.requireMin(2);
      String moduleName = args.getString();
      int key = (Integer)((Map.Entry)args.getDatatypeFor(KeyDataType.INSTANCE)).getValue();
      Module module = this.moduleProvider.get(moduleName);
      module.setKey(key);
      String var10001 = String.valueOf(class_124.field_1060);
      this.logDirect(var10001 + "ÐœÐ¾Ð´ÑƒÐ»ÑŒ " + String.valueOf(class_124.field_1061) + moduleName + String.valueOf(class_124.field_1060) + " Ð¿Ñ€Ð¸Ð²ÑÐ·Ð°Ð½ Ðº ÐºÐ½Ð¾Ð¿ÐºÐµ " + String.valueOf(class_124.field_1061) + StringHelper.getBindName(key).toLowerCase());
   }

   private void handleRemoveBind(IArgConsumer args) throws CommandException {
      args.requireMax(1);
      String moduleName = args.getString();
      Module module = this.moduleProvider.get(moduleName);
      module.setKey(-1);
      String var10001 = String.valueOf(class_124.field_1060);
      this.logDirect(var10001 + "Ð‘Ð¸Ð½Ð´ Ð´Ð»Ñ Ð¼Ð¾Ð´ÑƒÐ»Ñ " + String.valueOf(class_124.field_1061) + moduleName + String.valueOf(class_124.field_1060) + " Ð±Ñ‹Ð» ÑƒÑÐ¿ÐµÑˆÐ½Ð¾ ÑƒÐ´Ð°Ð»ÐµÐ½!");
   }

   private void handleListBinds(IArgConsumer args, String label) throws CommandException {
      args.requireMax(1);
      List<Module> filtredList = this.moduleRepository.modules().stream().filter((module) -> module.getKey() != -1).toList();
      Paginator.paginate(args, (Paginator)(new Paginator(filtredList)), () -> this.logDirect("Ð¡Ð¿Ð¸ÑÐ¾Ðº Ð¼Ð¾Ð´ÑƒÐ»ÐµÐ¹:"), (module) -> {
         String names = module.getName();
         String keys = StringHelper.getBindName(module.getKey()).toLowerCase();
         String var10000 = String.valueOf(class_124.field_1080);
         class_5250 var3 = class_2561.method_43470(var10000 + "ÐÐ°Ð·Ð²Ð°Ð½Ð¸Ðµ: " + String.valueOf(class_124.field_1068) + names);
         String var10001 = String.valueOf(class_124.field_1080);
         return var3.method_10852(class_2561.method_43470(var10001 + " ÐšÐ»Ð°Ð²Ð¸ÑˆÐ°: " + String.valueOf(class_124.field_1068) + keys));
      }, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label);
   }

   private void handleClearBinds(IArgConsumer args) throws CommandException {
      args.requireMax(1);
      this.moduleRepository.modules().forEach((function) -> function.setKey(-1));
      this.logDirect("Ð’ÑÐµ Ð±Ð¸Ð½Ð´Ñ‹ Ð¼Ð¾Ð´ÑƒÐ»ÐµÐ¹ Ð±Ñ‹Ð»Ð¸ ÑƒÐ´Ð°Ð»ÐµÐ½Ñ‹.", class_124.field_1060);
   }

   public Stream tabComplete(String label, IArgConsumer args) throws CommandException {
      if (args.hasExactlyOne()) {
         return (new TabCompleteHelper()).sortAlphabetically().prepend("add", "remove", "list", "clear", "set").filterPrefix(args.getString()).stream();
      } else {
         String arg = args.getString();
         if (arg.equalsIgnoreCase("add")) {
            if (args.hasExactly(1)) {
               return args.tabCompleteDatatype(ModuleDataType.INSTANCE);
            }

            if (args.hasExactly(2)) {
               return args.tabCompleteDatatype(KeyDataType.INSTANCE);
            }
         } else if (arg.equalsIgnoreCase("set")) {
            if (args.hasExactly(1)) {
               return Stream.of("clickgui").filter((s) -> {
                  try {
                     return s.startsWith(args.getString().toLowerCase(Locale.US));
                  } catch (CommandNotEnoughArgumentsException e) {
                     throw new RuntimeException(e);
                  }
               });
            }

            if (args.hasExactly(2)) {
               return args.tabCompleteDatatype(KeyDataType.INSTANCE);
            }
         }

         return Stream.empty();
      }
   }

   public String getShortDesc() {
      return "Ð£Ð¿Ñ€Ð°Ð²Ð»ÐµÐ½Ð¸Ðµ Ð±Ð¸Ð½Ð´Ð°Ð¼Ð¸ Ð´Ð»Ñ Ð¼Ð¾Ð´ÑƒÐ»ÐµÐ¹ Ð¸ GUI.";
   }

   public List getLongDesc() {
      return Arrays.asList("Ð­Ñ‚Ð° ÐºÐ¾Ð¼Ð°Ð½Ð´Ð° Ð¿Ð¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ ÑƒÐ¿Ñ€Ð°Ð²Ð»ÑÑ‚ÑŒ Ð±Ð¸Ð½Ð´Ð°Ð¼Ð¸ Ð´Ð»Ñ Ð¼Ð¾Ð´ÑƒÐ»ÐµÐ¹ Ð¸ GUI, ÐºÐ¾Ñ‚Ð¾Ñ€Ñ‹Ðµ Ð±ÑƒÐ´ÑƒÑ‚ Ð°ÐºÑ‚Ð¸Ð²Ð¸Ñ€Ð¾Ð²Ð°Ñ‚ÑŒÑÑ Ð¿Ñ€Ð¸ Ð½Ð°Ð¶Ð°Ñ‚Ð¸Ð¸ Ð¾Ð¿Ñ€ÐµÐ´ÐµÐ»Ñ‘Ð½Ð½Ñ‹Ñ… ÐºÐ»Ð°Ð²Ð¸Ñˆ.", "", "Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ðµ:", "> bind add <module> <key> - ÐŸÑ€Ð¸Ð²ÑÐ·Ñ‹Ð²Ð°ÐµÑ‚ Ð¼Ð¾Ð´ÑƒÐ»ÑŒ Ðº ÑƒÐºÐ°Ð·Ð°Ð½Ð½Ð¾Ð¹ ÐºÐ»Ð°Ð²Ð¸ÑˆÐµ.", "> bind remove <module> - Ð£Ð´Ð°Ð»ÑÐµÑ‚ Ð¿Ñ€Ð¸Ð²ÑÐ·ÐºÑƒ Ð¼Ð¾Ð´ÑƒÐ»Ñ.", "> bind list - ÐŸÐ¾ÐºÐ°Ð·Ñ‹Ð²Ð°ÐµÑ‚ ÑÐ¿Ð¸ÑÐ¾Ðº Ð²ÑÐµÑ… Ñ‚ÐµÐºÑƒÑ‰Ð¸Ñ… Ð±Ð¸Ð½Ð´Ð¾Ð² Ð¼Ð¾Ð´ÑƒÐ»ÐµÐ¹.", "> bind clear - Ð£Ð´Ð°Ð»ÑÐµÑ‚ Ð²ÑÐµ Ð±Ð¸Ð½Ð´Ñ‹ Ð¼Ð¾Ð´ÑƒÐ»ÐµÐ¹.", "> bind set clickgui <key> - Ð˜Ð·Ð¼ÐµÐ½ÑÐµÑ‚ ÐºÐ»Ð°Ð²Ð¸ÑˆÑƒ Ð´Ð»Ñ Ð¾Ñ‚ÐºÑ€Ñ‹Ñ‚Ð¸Ñ ClickGUI.");
   }

   public class ClickGuiManager {
      public static int clickGuiKey = 344;

      public static void setClickGuiKey(int key) {
         clickGuiKey = key;
      }

      public static int getClickGuiKey() {
         return clickGuiKey;
      }
   }
}

