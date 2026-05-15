package dev.client.yanderov.commands.defaults;

import dev.client.yanderov.common.repository.friend.FriendUtils;
import dev.client.yanderov.utils.client.managers.api.command.Command;
import dev.client.yanderov.utils.client.managers.api.command.IBaritoneChatControl;
import dev.client.yanderov.utils.client.managers.api.command.argument.IArgConsumer;
import dev.client.yanderov.utils.client.managers.api.command.datatypes.FriendDataType;
import dev.client.yanderov.utils.client.managers.api.command.datatypes.TabPlayerDataType;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
import dev.client.yanderov.utils.client.managers.api.command.helpers.Paginator;
import dev.client.yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_5250;

public class FriendCommand extends Command {
   protected FriendCommand() {
      super("friend");
   }

   public void execute(String label, IArgConsumer args) throws CommandException {
      String arg = args.hasAny() ? args.getString().toLowerCase(Locale.US) : "list";
      args.requireMax(1);
      if (arg.contains("add")) {
         String name = args.getString();
         if (!FriendUtils.isFriend(name)) {
            FriendUtils.addFriend(name);
            this.logDirect("Ð’Ñ‹ ÑƒÑÐ¿ÐµÑˆÐ½Ð¾ Ð´Ð¾Ð±Ð°Ð²Ð¸Ð»Ð¸ " + name + " Ð² ÑÐ¿Ð¸ÑÐ¾Ðº Ð´Ñ€ÑƒÐ·ÐµÐ¹!");
         } else {
            this.logDirect(name + " ÑƒÐ¶Ðµ ÐµÑÑ‚ÑŒ Ð² ÑÐ¿Ð¸ÑÐºÐµ Ð´Ñ€ÑƒÐ·ÐµÐ¹!", class_124.field_1061);
         }
      }

      if (arg.contains("remove")) {
         String name = args.getString();
         if (FriendUtils.isFriend(name)) {
            FriendUtils.removeFriend(name);
            this.logDirect("Ð’Ñ‹ ÑƒÑÐ¿ÐµÑˆÐ½Ð¾ ÑƒÐ´Ð°Ð»Ð¸Ð»Ð¸ " + name + " Ð¸Ð· ÑÐ¿Ð¸ÑÐºÐ° Ð´Ñ€ÑƒÐ·ÐµÐ¹!");
            return;
         }

         this.logDirect(name + " Ð½Ðµ Ð½Ð°Ð¹Ð´ÐµÐ½ Ð² ÑÐ¿Ð¸ÑÐºÐµ Ð´Ñ€ÑƒÐ·ÐµÐ¹", class_124.field_1061);
      }

      if (arg.contains("list")) {
         Paginator.paginate(args, (Paginator)(new Paginator(FriendUtils.getFriends())), () -> this.logDirect("Ð¡Ð¿Ð¸ÑÐ¾Ðº Ð´Ñ€ÑƒÐ·ÐµÐ¹:"), (friend) -> {
            String names = friend.getName();
            class_5250 namesComponent = class_2561.method_43470(names);
            namesComponent.method_10862(namesComponent.method_10866().method_10977(class_124.field_1068));
            return namesComponent;
         }, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label);
      }

      if (arg.contains("clear")) {
         FriendUtils.clear();
         this.logDirect("Ð¡Ð¿Ð¸ÑÐ¾Ðº Ð´Ñ€ÑƒÐ·ÐµÐ¹ Ð¾Ñ‡Ð¸Ñ‰ÐµÐ½.");
      }

   }

   public Stream tabComplete(String label, IArgConsumer args) throws CommandException {
      if (args.hasAny()) {
         String arg = args.getString();
         if (!args.hasExactlyOne()) {
            return (new TabCompleteHelper()).sortAlphabetically().prepend("add", "remove", "list", "clear").filterPrefix(arg).stream();
         }

         if (arg.equalsIgnoreCase("add")) {
            return args.tabCompleteDatatype(TabPlayerDataType.INSTANCE);
         }

         if (arg.equalsIgnoreCase("remove")) {
            return args.tabCompleteDatatype(FriendDataType.INSTANCE);
         }
      }

      return Stream.empty();
   }

   public String getShortDesc() {
      return "ÐŸÐ¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ ÑƒÐ¿Ñ€Ð°Ð²Ð»ÑÑ‚ÑŒ ÑÐ¿Ð¸ÑÐºÐ¾Ð¼ Ð´Ñ€ÑƒÐ·ÐµÐ¹";
   }

   public List getLongDesc() {
      return Arrays.asList("Ð¡ Ð¿Ð¾Ð¼Ð¾Ñ‰ÑŒÑŽ ÑÑ‚Ð¾Ð¹ ÐºÐ¾Ð¼Ð°Ð½Ð´Ñ‹ Ð¼Ð¾Ð¶Ð½Ð¾ Ð´Ð¾Ð±Ð°Ð²Ð»ÑÑ‚ÑŒ/ÑƒÐ´Ð°Ð»ÑÑ‚ÑŒ Ð´Ñ€ÑƒÐ·ÐµÐ¹ Ð² Ñ‡Ð¸Ñ‚Ðµ", "", "Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ðµ:", "> friend add <name> - Ð”Ð¾Ð±Ð°Ð²Ð»ÑÐµÑ‚ Ð¸Ð¼Ñ Ð² ÑÐ¿Ð¸ÑÐ¾Ðº Ð´Ñ€ÑƒÐ·ÐµÐ¹.", "> friend remove <name> - Ð£Ð´Ð°Ð»ÑÐµÑ‚ Ð¸Ð¼Ñ Ð¸Ð· ÑÐ¿Ð¸ÑÐºÐ° Ð´Ñ€ÑƒÐ·ÐµÐ¹.", "> friend list - Ð’Ð¾Ð·Ð²Ñ€Ð°Ñ‰Ð°ÐµÑ‚ ÑÐ¿Ð¸ÑÐ¾Ðº Ð´Ñ€ÑƒÐ·ÐµÐ¹", "> friend clear - ÐžÑ‡Ð¸Ñ‰Ð°ÐµÑ‚ ÑÐ¿Ð¸ÑÐ¾Ðº Ð´Ñ€ÑƒÐ·ÐµÐ¹.");
   }
}

