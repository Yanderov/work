package dev.client.yanderov.commands.defaults;

import dev.client.yanderov.commands.CommandDispatcher;
import dev.client.yanderov.utils.client.managers.api.command.Command;
import dev.client.yanderov.utils.client.managers.api.command.argument.IArgConsumer;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
import dev.client.yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.class_124;

public class PrefixCommand extends Command implements QuickImports {
   protected PrefixCommand() {
      super("prefix");
   }

   public void execute(String label, IArgConsumer args) throws CommandException {
      String arg = args.hasAny() ? args.getString().toLowerCase(Locale.US) : "list";
      if (arg.equals("set")) {
         args.requireMin(1);
         String var10001 = String.valueOf(class_124.field_1061);
         this.logDirect("Ð£ÑÑ‚Ð°Ð½Ð¾Ð²Ð»ÐµÐ½ Ð¿Ñ€ÐµÑ„Ð¸ÐºÑ '" + var10001 + (CommandDispatcher.prefix = args.getString()) + String.valueOf(class_124.field_1080) + "'", class_124.field_1080);
      }

   }

   public Stream tabComplete(String label, IArgConsumer args) throws CommandException {
      if (args.hasAny()) {
         String arg = args.getString();
         return arg.equalsIgnoreCase("set") ? (new TabCompleteHelper()).sortAlphabetically().prepend("name").stream() : (new TabCompleteHelper()).sortAlphabetically().prepend("set").filterPrefix(arg).stream();
      } else {
         return Stream.empty();
      }
   }

   public String getShortDesc() {
      return "ÐŸÐ¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ Ð¼ÐµÐ½ÑÑ‚ÑŒ Ð¿Ñ€ÐµÑ„Ð¸ÐºÑ ÐºÐ¾Ð¼Ð°Ð½Ð´ Ð² Ð¼Ð¾Ð´Ðµ";
   }

   public List getLongDesc() {
      return Arrays.asList("Ð¡ Ð¿Ð¾Ð¼Ð¾Ñ‰ÑŒÑŽ ÑÑ‚Ð¾Ð¹ ÐºÐ¾Ð¼Ð°Ð½Ð´Ñ‹ Ð¼Ð¾Ð¶Ð½Ð¾ Ð¸Ð·Ð¼ÐµÐ½Ð¸Ñ‚ÑŒ Ð¿Ñ€ÐµÑ„Ð¸ÐºÑ ÐºÐ¾Ð¼Ð°Ð½Ð´ Ð² Ð¼Ð¾Ð´Ðµ", "", "Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ðµ:", "> prefix set <name> - ÑƒÑÑ‚Ð°Ð½Ð°Ð²Ð»Ð¸Ð²Ð°ÐµÑ‚ Ð¿Ñ€ÐµÑ„Ð¸ÐºÑ ÐºÐ¾Ð¼Ð°Ð½Ð´");
   }
}

