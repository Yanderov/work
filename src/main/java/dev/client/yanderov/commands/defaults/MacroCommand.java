package dev.client.yanderov.commands.defaults;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.common.repository.macro.MacroRepository;
import dev.client.yanderov.utils.client.chat.StringHelper;
import dev.client.yanderov.utils.client.managers.api.command.Command;
import dev.client.yanderov.utils.client.managers.api.command.IBaritoneChatControl;
import dev.client.yanderov.utils.client.managers.api.command.argument.IArgConsumer;
import dev.client.yanderov.utils.client.managers.api.command.datatypes.KeyDataType;
import dev.client.yanderov.utils.client.managers.api.command.datatypes.MacroDataType;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
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

public class MacroCommand extends Command {
   private final MacroRepository macroRepository;

   public MacroCommand(Yanderov main) {
      super("macro", "macros");
      this.macroRepository = main.getMacroRepository();
   }

   public void execute(String label, IArgConsumer args) throws CommandException {
      switch (args.hasAny() ? args.getString().toLowerCase(Locale.US) : "list") {
         case "add" -> this.handleAddMacro(args);
         case "remove" -> this.handleRemoveMacro(args);
         case "list" -> this.handleListMacros(args, label);
         case "clear" -> this.handleClearMacros(args);
      }

   }

   private void handleAddMacro(IArgConsumer args) throws CommandException {
      args.requireMin(3);
      int key = (Integer)((Map.Entry)args.getDatatypeFor(KeyDataType.INSTANCE)).getValue();
      String name = args.getString();
      String command = args.rawRest();
      if (this.macroRepository.hasMacro(name)) {
         this.logDirect("ÐœÐ°ÐºÑ€Ð¾Ñ Ñ Ñ‚Ð°ÐºÐ¸Ð¼ Ð¸Ð¼ÐµÐ½ÐµÐ¼ ÑƒÐ¶Ðµ ÐµÑÑ‚ÑŒ Ð² ÑÐ¿Ð¸ÑÐºÐµ!", class_124.field_1061);
      } else {
         this.macroRepository.addMacro(name, command, key);
         String var10001 = String.valueOf(class_124.field_1060);
         this.logDirect(var10001 + "Ð”Ð¾Ð±Ð°Ð²Ð»ÐµÐ½ Ð¼Ð°ÐºÑ€Ð¾Ñ Ñ Ð½Ð°Ð·Ð²Ð°Ð½Ð¸ÐµÐ¼ " + String.valueOf(class_124.field_1061) + name + String.valueOf(class_124.field_1060) + " Ñ ÐºÐ½Ð¾Ð¿ÐºÐ¾Ð¹ " + String.valueOf(class_124.field_1061) + StringHelper.getBindName(key).toLowerCase() + String.valueOf(class_124.field_1060) + " Ñ ÐºÐ¾Ð¼Ð°Ð½Ð´Ð¾Ð¹ " + String.valueOf(class_124.field_1061) + command);
      }
   }

   private void handleRemoveMacro(IArgConsumer args) throws CommandException {
      args.requireMax(1);
      String name = args.getString();
      if (this.macroRepository.hasMacro(name)) {
         this.macroRepository.deleteMacro(name);
         String var10001 = String.valueOf(class_124.field_1060);
         this.logDirect(var10001 + "ÐœÐ°ÐºÑ€Ð¾Ñ " + String.valueOf(class_124.field_1061) + name + String.valueOf(class_124.field_1060) + " Ð±Ñ‹Ð» ÑƒÑÐ¿ÐµÑˆÐ½Ð¾ ÑƒÐ´Ð°Ð»ÐµÐ½!");
      } else {
         this.logDirect("ÐœÐ°ÐºÑ€Ð¾Ñ Ñ Ñ‚Ð°ÐºÐ¸Ð¼ Ð¸Ð¼ÐµÐ½ÐµÐ¼ Ð½Ðµ Ð½Ð°Ð¹Ð´ÐµÐ½!", class_124.field_1061);
      }

   }

   private void handleListMacros(IArgConsumer args, String label) throws CommandException {
      args.requireMax(1);
      Paginator.paginate(args, (Paginator)(new Paginator(this.macroRepository.macroList)), () -> this.logDirect("Ð¡Ð¿Ð¸ÑÐ¾Ðº Ð¼Ð°ÐºÑ€Ð¾ÑÐ¾Ð²:"), (macro) -> {
         String names = macro.name();
         String keys = StringHelper.getBindName(macro.key()).toLowerCase();
         String command = macro.message();
         String var10000 = String.valueOf(class_124.field_1080);
         class_5250 var4 = class_2561.method_43470(var10000 + "ÐÐ°Ð·Ð²Ð°Ð½Ð¸Ðµ: " + String.valueOf(class_124.field_1068) + names);
         String var10001 = String.valueOf(class_124.field_1080);
         var4 = var4.method_10852(class_2561.method_43470(var10001 + " ÐšÐ»Ð°Ð²Ð¸ÑˆÐ°: " + String.valueOf(class_124.field_1068) + keys));
         var10001 = String.valueOf(class_124.field_1080);
         return var4.method_10852(class_2561.method_43470(var10001 + " ÐšÐ¾Ð¼Ð°Ð½Ð´Ð°: " + String.valueOf(class_124.field_1068) + command));
      }, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label);
   }

   private void handleClearMacros(IArgConsumer args) throws CommandException {
      args.requireMax(1);
      this.macroRepository.clearList();
      this.logDirect("Ð’ÑÐµ Ð¼Ð°ÐºÑ€Ð¾ÑÑ‹ Ð±Ñ‹Ð»Ð¸ ÑƒÐ´Ð°Ð»ÐµÐ½Ñ‹.", class_124.field_1060);
   }

   public Stream tabComplete(String label, IArgConsumer args) throws CommandException {
      if (args.hasAny() && args.hasExactlyOne()) {
         return (new TabCompleteHelper()).sortAlphabetically().prepend("add", "remove", "list", "clear").filterPrefix(args.getString()).stream();
      } else {
         if (args.hasAny()) {
            String arg = args.getString();
            if (arg.equalsIgnoreCase("add") && args.hasExactlyOne()) {
               return args.tabCompleteDatatype(KeyDataType.INSTANCE);
            }

            if (arg.equalsIgnoreCase("remove") && args.hasExactlyOne()) {
               return args.tabCompleteDatatype(MacroDataType.INSTANCE);
            }
         }

         return Stream.empty();
      }
   }

   public String getShortDesc() {
      return "ÐŸÐ¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ ÑƒÐ¿Ñ€Ð°Ð²Ð»ÑÑ‚ÑŒ Ð¼Ð°ÐºÑ€Ð¾ÑÐ°Ð¼Ð¸";
   }

   public List getLongDesc() {
      return Arrays.asList("Ð­Ñ‚Ð° ÐºÐ¾Ð¼Ð°Ð½Ð´Ð° Ð¿Ð¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ ÑƒÐ¿Ñ€Ð°Ð²Ð»ÑÑ‚ÑŒ Ð¼Ð°ÐºÑ€Ð¾ÑÐ°Ð¼Ð¸, ÐºÐ¾Ñ‚Ð¾Ñ€Ñ‹Ðµ Ð°Ð²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ Ð²Ð²Ð¾Ð´ÑÑ‚ Ð·Ð°Ð´Ð°Ð½Ð½Ñ‹Ðµ ÐºÐ¾Ð¼Ð°Ð½Ð´Ñ‹ Ð² Ñ‡Ð°Ñ‚.", "", "Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ðµ:", "> macro add <key> <name> <message> - Ð”Ð¾Ð±Ð°Ð²Ð»ÑÐµÑ‚ Ð½Ð¾Ð²Ñ‹Ð¹ Ð¼Ð°ÐºÑ€Ð¾Ñ, ÐºÐ¾Ñ‚Ð¾Ñ€Ñ‹Ð¹ Ð±ÑƒÐ´ÐµÑ‚ Ð°ÐºÑ‚Ð¸Ð²Ð¸Ñ€Ð¾Ð²Ð°Ñ‚ÑŒÑÑ Ð¿Ñ€Ð¸ Ð½Ð°Ð¶Ð°Ñ‚Ð¸Ð¸ Ð½Ð° ÑƒÐºÐ°Ð·Ð°Ð½Ð½ÑƒÑŽ ÐºÐ»Ð°Ð²Ð¸ÑˆÑƒ Ð¸ Ð²Ð²Ð¾Ð´Ð¸Ñ‚ÑŒ ÑƒÐºÐ°Ð·Ð°Ð½Ð½Ð¾Ðµ ÑÐ¾Ð¾Ð±Ñ‰ÐµÐ½Ð¸Ðµ.", "> macro remove <name> - Ð£Ð´Ð°Ð»ÑÐµÑ‚ Ð¼Ð°ÐºÑ€Ð¾Ñ Ñ ÑƒÐºÐ°Ð·Ð°Ð½Ð½Ñ‹Ð¼ Ð¸Ð¼ÐµÐ½ÐµÐ¼.", "> macro list - ÐžÑ‚Ð¾Ð±Ñ€Ð°Ð¶Ð°ÐµÑ‚ ÑÐ¿Ð¸ÑÐ¾Ðº Ð²ÑÐµÑ… Ñ‚ÐµÐºÑƒÑ‰Ð¸Ñ… Ð¼Ð°ÐºÑ€Ð¾ÑÐ¾Ð².", "> macro clear - Ð£Ð´Ð°Ð»ÑÐµÑ‚ Ð²ÑÐµ Ð¼Ð°ÐºÑ€Ð¾ÑÑ‹ Ð¸Ð· ÑÐ¿Ð¸ÑÐºÐ°.");
   }
}

