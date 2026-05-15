package fun.Yanderov.commands.defaults;

import fun.Yanderov.Yanderov;
import fun.Yanderov.common.repository.way.WayRepository;
import fun.Yanderov.utils.client.managers.api.command.Command;
import fun.Yanderov.utils.client.managers.api.command.IBaritoneChatControl;
import fun.Yanderov.utils.client.managers.api.command.argument.IArgConsumer;
import fun.Yanderov.utils.client.managers.api.command.argument.ICommandArgument;
import fun.Yanderov.utils.client.managers.api.command.datatypes.WayDataType;
import fun.Yanderov.utils.client.managers.api.command.exception.CommandException;
import fun.Yanderov.utils.client.managers.api.command.helpers.Paginator;
import fun.Yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.class_124;
import net.minecraft.class_2338;
import net.minecraft.class_2561;
import net.minecraft.class_5250;

public class WayCommand extends Command implements QuickImports {
   private final WayRepository wayRepository;

   protected WayCommand(Yanderov main) {
      super("way");
      this.wayRepository = main.getWayRepository();
   }

   public void execute(String label, IArgConsumer args) throws CommandException {
      switch (args.hasAny() ? args.getString().toLowerCase(Locale.US) : "list") {
         case "add" -> this.handleAddWay(args);
         case "remove" -> this.handleRemoveWay(args);
         case "clear" -> this.handleClearWays(args);
         case "list" -> this.handleListWays(label, args);
      }

   }

   private void handleAddWay(IArgConsumer args) throws CommandException {
      args.requireMin(4);
      String name = args.getString();
      int x = (Integer)((ICommandArgument)args.getArgs().get(0)).getAs(Integer.class);
      int y = (Integer)((ICommandArgument)args.getArgs().get(1)).getAs(Integer.class);
      int z = (Integer)((ICommandArgument)args.getArgs().get(2)).getAs(Integer.class);
      if (this.wayRepository.hasWay(name)) {
         this.logDirect("ÐœÐµÑ‚ÐºÐ° Ñ Ñ‚Ð°ÐºÐ¸Ð¼ Ð¸Ð¼ÐµÐ½ÐµÐ¼ ÑƒÐ¶Ðµ ÐµÑÑ‚ÑŒ Ð² ÑÐ¿Ð¸ÑÐºÐµ!", class_124.field_1061);
      } else {
         String address = mc.method_1562() == null ? "vanilla" : (mc.method_1562().method_45734() == null ? "vanilla" : mc.method_1562().method_45734().field_3761);
         this.logDirect("Ð”Ð¾Ð±Ð°Ð²Ð»ÐµÐ½Ð° Ð¼ÐµÑ‚ÐºÐ° " + name + ", ÐšÐ¾Ð¾Ñ€Ð´Ð¸Ð½Ð°Ñ‚Ñ‹: (" + x + ", " + y + ", " + z + "), Ð¡ÐµÑ€Ð²ÐµÑ€: " + address, class_124.field_1080);
         this.wayRepository.addWay(name, new class_2338(x, y, z), address);
      }
   }

   private void handleRemoveWay(IArgConsumer args) throws CommandException {
      args.requireMax(1);
      String name = args.getString();
      if (this.wayRepository.hasWay(name)) {
         this.wayRepository.deleteWay(name);
         String var10001 = String.valueOf(class_124.field_1060);
         this.logDirect(var10001 + "ÐœÐµÑ‚ÐºÐ° " + String.valueOf(class_124.field_1061) + name + String.valueOf(class_124.field_1060) + " Ð±Ñ‹Ð»Ð° ÑƒÑÐ¿ÐµÑˆÐ½Ð° ÑƒÐ´Ð°Ð»ÐµÐ½Ð°!");
      } else {
         this.logDirect("ÐœÐµÑ‚ÐºÐ° Ñ Ð½Ð°Ð·Ð²Ð°Ð½Ð¸ÐµÐ¼ '" + name + "' Ð½Ðµ Ð½Ð°Ð¹Ð´ÐµÐ½Ð°!");
      }

   }

   private void handleListWays(String label, IArgConsumer args) throws CommandException {
      args.requireMax(1);
      Paginator.paginate(args, (Paginator)(new Paginator(this.wayRepository.wayList)), () -> this.logDirect("Ð¡Ð¿Ð¸ÑÐ¾Ðº Ð¼ÐµÑ‚Ð¾Ðº:"), (way) -> {
         String var10000 = String.valueOf(class_124.field_1080);
         class_5250 var1 = class_2561.method_43470(var10000 + "ÐÐ°Ð·Ð²Ð°Ð½Ð¸Ðµ: " + String.valueOf(class_124.field_1061) + way.name());
         String var10001 = String.valueOf(class_124.field_1080);
         class_5250 var2 = class_2561.method_43470(var10001 + " ÐšÐ¾Ð¾Ñ€Ð´Ð¸Ð½Ð°Ñ‚Ñ‹: " + String.valueOf(class_124.field_1068) + " (" + way.pos().method_10263() + ", " + way.pos().method_10264() + ", " + way.pos().method_10260() + ")");
         String var10002 = String.valueOf(class_124.field_1080);
         return var1.method_10852(var2.method_10852(class_2561.method_43470(var10002 + " Ð¡ÐµÑ€Ð²ÐµÑ€: " + String.valueOf(class_124.field_1068) + way.server())));
      }, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label);
   }

   private void handleClearWays(IArgConsumer args) throws CommandException {
      args.requireMax(1);
      this.wayRepository.clearList();
      this.logDirect(String.valueOf(class_124.field_1060) + "Ð’ÑÐµ Ð¼ÐµÑ‚ÐºÐ¸ Ð±Ñ‹Ð»Ð¸ ÑƒÐ´Ð°Ð»ÐµÐ½Ñ‹.");
   }

   public Stream tabComplete(String label, IArgConsumer args) throws CommandException {
      if (args.hasAny()) {
         String arg = args.getString();
         if (!arg.equalsIgnoreCase("remove")) {
            if (arg.equalsIgnoreCase("add")) {
               String string = args.has(5) ? "" : (args.has(4) ? "z" : (args.has(3) ? "y" : (args.has(2) ? "x" : "ÐÐ°Ð·Ð²Ð°Ð½Ð¸Ðµ")));
               return (new TabCompleteHelper()).sortAlphabetically().prepend(string).stream();
            }

            return (new TabCompleteHelper()).sortAlphabetically().prepend("add", "remove", "list", "clear").filterPrefix(arg).stream();
         }

         if (args.hasExactlyOne()) {
            return args.tabCompleteDatatype(WayDataType.INSTANCE);
         }
      }

      return Stream.empty();
   }

   public String getShortDesc() {
      return "ÐŸÐ¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ ÑÑ‚Ð°Ð²Ð¸Ñ‚ÑŒ Ð¼ÐµÑ‚ÐºÐ¸ Ð² Ð¼Ð¸Ñ€Ðµ";
   }

   public List getLongDesc() {
      return Arrays.asList("Ð¡ Ð¿Ð¾Ð¼Ð¾Ñ‰ÑŒÑŽ ÑÑ‚Ð¾Ð¹ ÐºÐ¾Ð¼Ð°Ð½Ð´Ñ‹ Ð¼Ð¾Ð¶Ð½Ð¾ Ð´Ð¾Ð±Ð°Ð²Ð»ÑÑ‚ÑŒ/ÑƒÐ´Ð°Ð»ÑÑ‚ÑŒ Ð¼ÐµÑ‚ÐºÐ¸ Ð² Ð¼Ð¸Ñ€Ðµ", "", "Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ðµ:", "> way add <name> <x> <y> <z> - Ð”Ð¾Ð±Ð°Ð²Ð»ÑÐµÑ‚ Ð¼ÐµÑ‚ÐºÑƒ", "> way remove <name> - Ð£Ð´Ð°Ð»ÑÐµÑ‚ Ð¼ÐµÑ‚ÐºÑƒ", "> way list - Ð’Ð¾Ð·Ð²Ñ€Ð°Ñ‰Ð°ÐµÑ‚ ÑÐ¿Ð¸ÑÐ¾Ðº Ð¼ÐµÑ‚Ð¾Ðº", "> way clear - ÐžÑ‡Ð¸Ñ‰Ð°ÐµÑ‚ ÑÐ¿Ð¸ÑÐ¾Ðº Ð¼ÐµÑ‚Ð¾Ðº.");
   }
}

