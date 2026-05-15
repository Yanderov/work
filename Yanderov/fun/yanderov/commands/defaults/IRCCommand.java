package fun.Yanderov.commands.defaults;

import fun.Yanderov.Yanderov;
import fun.Yanderov.features.impl.misc.IRC;
import fun.Yanderov.utils.client.chat.ChatMessage;
import fun.Yanderov.utils.client.managers.api.command.Command;
import fun.Yanderov.utils.client.managers.api.command.argument.IArgConsumer;
import fun.Yanderov.utils.client.managers.api.command.exception.CommandException;
import fun.Yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import fun.Yanderov.utils.client.text.TextHelper;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_310;

public class IRCCommand extends Command {
   private static String selectedPrefix = null;

   protected IRCCommand() {
      super("irc");
   }

   public void execute(String label, IArgConsumer args) throws CommandException {
      IRC ircModule = (IRC)Yanderov.getInstance().getModuleRepository().modules().stream().filter((module) -> module instanceof IRC).map((module) -> (IRC)module).findFirst().orElse((Object)null);
      if (ircModule == null) {
         ChatMessage.ircmessageWithRed("ÐœÐ¾Ð´ÑƒÐ»ÑŒ IRC Ð½Ðµ Ð½Ð°Ð¹Ð´ÐµÐ½");
      } else if (!args.hasAny()) {
         this.sendUsage();
      } else {
         switch (args.getString().toLowerCase()) {
            case "prefix":
               if (args.hasAny()) {
                  String sub = args.getString().toLowerCase();
                  if (sub.equals("list")) {
                     this.displayPrefixList();
                  } else {
                     this.handlePrefixSelection(sub);
                  }
               } else {
                  ChatMessage.ircmessageWithRed("Ð£ÐºÐ°Ð¶Ð¸Ñ‚Ðµ Ð½Ð¾Ð¼ÐµÑ€ Ð¿Ñ€ÐµÑ„Ð¸ÐºÑÐ°: .irc prefix <1-10> Ð¸Ð»Ð¸ .irc prefix list");
               }
               break;
            case "clear":
               selectedPrefix = null;
               this.sendSetPrefix("");
               ChatMessage.ircmessage("ÐŸÑ€ÐµÑ„Ð¸ÐºÑ ÑÐ±Ñ€Ð¾ÑˆÐµÐ½");
               break;
            default:
               if (!ircModule.isState()) {
                  ChatMessage.ircmessageWithRed("ÐœÐ¾Ð´ÑƒÐ»ÑŒ IRC Ð¾Ñ‚ÐºÐ»ÑŽÑ‡ÐµÐ½");
                  return;
               }

               if (class_310.method_1551().field_1724 == null) {
                  ChatMessage.ircmessageWithRed("Ð˜Ð³Ñ€Ð¾Ðº Ð½Ðµ Ð¸Ð½Ð¸Ñ†Ð¸Ð°Ð»Ð¸Ð·Ð¸Ñ€Ð¾Ð²Ð°Ð½");
                  return;
               }

               String message = action + (args.hasAny() ? " " + args.rawRest() : "");
               ircModule.sendMessage(message);
         }

      }
   }

   public String getShortDesc() {
      return "ÐšÐ¾Ð¼Ð°Ð½Ð´Ð° Ð´Ð»Ñ ÑƒÐ¿Ñ€Ð°Ð²Ð»ÐµÐ½Ð¸Ñ IRC-Ñ‡Ð°Ñ‚Ð¾Ð¼: Ð¾Ñ‚Ð¿Ñ€Ð°Ð²ÐºÐ° ÑÐ¾Ð¾Ð±Ñ‰ÐµÐ½Ð¸Ð¹.";
   }

   public List getLongDesc() {
      return Arrays.asList("Ð­Ñ‚Ð° ÐºÐ¾Ð¼Ð°Ð½Ð´Ð° Ð¿Ð¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ ÑƒÐ¿Ñ€Ð°Ð²Ð»ÑÑ‚ÑŒ IRC-Ñ‡Ð°Ñ‚Ð¾Ð¼, Ð²ÐºÐ»ÑŽÑ‡Ð°Ñ Ð¾Ñ‚Ð¿Ñ€Ð°Ð²ÐºÑƒ ÑÐ¾Ð¾Ð±Ñ‰ÐµÐ½Ð¸Ð¹, Ð²ÐºÐ»ÑŽÑ‡ÐµÐ½Ð¸Ðµ/Ð²Ñ‹ÐºÐ»ÑŽÑ‡ÐµÐ½Ð¸Ðµ Ð¼Ð¾Ð´ÑƒÐ»Ñ Ð¸ Ð²Ñ‹Ð±Ð¾Ñ€ Ð¿Ñ€ÐµÑ„Ð¸ÐºÑÐ¾Ð² Ð´Ð»Ñ ÑÐ¾Ð¾Ð±Ñ‰ÐµÐ½Ð¸Ð¹.", "", "Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ðµ:", "> irc <ÑÐ¾Ð¾Ð±Ñ‰ÐµÐ½Ð¸Ðµ> - ÐžÑ‚Ð¿Ñ€Ð°Ð²Ð»ÑÐµÑ‚ ÑÐ¾Ð¾Ð±Ñ‰ÐµÐ½Ð¸Ðµ Ð² IRC-Ñ‡Ð°Ñ‚ (Ð½Ð°Ð¿Ñ€Ð¸Ð¼ÐµÑ€: .irc ÐŸÑ€Ð¸Ð²ÐµÑ‚, Ð¼Ð¸Ñ€!).", "> irc prefix <1-10> - Ð’Ñ‹Ð±Ð¸Ñ€Ð°ÐµÑ‚ Ð¿Ñ€ÐµÑ„Ð¸ÐºÑ Ð´Ð»Ñ ÑÐ¾Ð¾Ð±Ñ‰ÐµÐ½Ð¸Ð¹ (Ð½Ð°Ð¿Ñ€Ð¸Ð¼ÐµÑ€: .irc prefix 1).", "> irc prefix list - ÐžÑ‚Ð¾Ð±Ñ€Ð°Ð¶Ð°ÐµÑ‚ ÑÐ¿Ð¸ÑÐ¾Ðº Ð´Ð¾ÑÑ‚ÑƒÐ¿Ð½Ñ‹Ñ… Ð¿Ñ€ÐµÑ„Ð¸ÐºÑÐ¾Ð².", "> irc clear - Ð¡Ð±Ñ€Ð°ÑÑ‹Ð²Ð°ÐµÑ‚ Ð²Ñ‹Ð±Ñ€Ð°Ð½Ð½Ñ‹Ð¹ Ð¿Ñ€ÐµÑ„Ð¸ÐºÑ.");
   }

   private void handlePrefixSelection(String prefixNumber) {
      String[] prefixes = new String[]{"pikmi", "labuba", "zapen", "boost", "Yanderov", "panda", "smiley", "bibi", "benena", "blyabuba"};
      String[] displayNames = new String[]{"ÐŸÐ¸ÐºÐ¼Ð¸", "Ð›Ð°Ð±ÑƒÐ±Ð°", "Ð—Ð°Ð¿ÐµÐ½", "Ð‘ÑƒÑÑ‚", "Ð Ð¸Ñ‡", "ÐŸÐ°Ð½Ð´Ð°", "(â—'â—¡'â—)", "Ð‘Ð¸Ð±Ð¸...!", "Ð‘ÑÐ½ÐµÐ½Ð°", "Ð‘Ð»ÑÐ±ÑƒÐ±Ð°"};

      try {
         int index = Integer.parseInt(prefixNumber) - 1;
         if (index >= 0 && index < prefixes.length) {
            selectedPrefix = prefixes[index];
            if (class_310.method_1551().field_1724 != null) {
               class_2561 ircPrefix = TextHelper.applyPredefinedGradient("[IRC] ", "black_light_purple", true);
               class_2561 messageText = class_2561.method_43470("Ð£ÑÐ¿ÐµÑˆÐ½Ð¾ ÑƒÑÑ‚Ð°Ð½Ð¾Ð²Ð»ÐµÐ½ Ð¿Ñ€ÐµÑ„Ð¸ÐºÑ ").method_10862(class_2583.field_24360.method_10977(class_124.field_1068));
               class_2561 prefixText = this.getPrefixText(displayNames[index], "");
               class_2561 fullMessage = ircPrefix.method_27661().method_10852(messageText).method_10852(prefixText);
               class_310.method_1551().field_1724.method_7353(fullMessage, false);
            }

            this.sendSetPrefix(selectedPrefix);
         } else {
            ChatMessage.ircmessageWithRed("ÐÐµÐ²ÐµÑ€Ð½Ñ‹Ð¹ Ð½Ð¾Ð¼ÐµÑ€ Ð¿Ñ€ÐµÑ„Ð¸ÐºÑÐ°. Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·ÑƒÐ¹Ñ‚Ðµ .irc prefix list (1-10)");
         }
      } catch (NumberFormatException var9) {
         ChatMessage.ircmessageWithRed("ÐÐµÐ²ÐµÑ€Ð½Ñ‹Ð¹ Ð²Ð²Ð¾Ð´. Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·ÑƒÐ¹Ñ‚Ðµ Ñ‡Ð¸ÑÐ»Ð¾ Ð¾Ñ‚ 1 Ð´Ð¾ 10");
      }

   }

   private void displayPrefixList() {
      ChatMessage.ircmessage("Ð¡Ð¿Ð¸ÑÐ¾Ðº Ð¿Ñ€ÐµÑ„Ð¸ÐºÑÐ¾Ð²:");
      if (class_310.method_1551().field_1724 != null) {
         class_310.method_1551().field_1724.method_7353(class_2561.method_43470("1. ").method_10852(ChatMessage.ircprefixPikmi("")), false);
         class_310.method_1551().field_1724.method_7353(class_2561.method_43470("2. ").method_10852(ChatMessage.ircprefixLabuba("")), false);
         class_310.method_1551().field_1724.method_7353(class_2561.method_43470("3. ").method_10852(ChatMessage.ircprefixZapen("")), false);
         class_310.method_1551().field_1724.method_7353(class_2561.method_43470("4. ").method_10852(ChatMessage.ircprefixBoost("")), false);
         class_310.method_1551().field_1724.method_7353(class_2561.method_43470("5. ").method_10852(ChatMessage.ircprefixYanderov("")), false);
         class_310.method_1551().field_1724.method_7353(class_2561.method_43470("6. ").method_10852(ChatMessage.ircprefixPanda("")), false);
         class_310.method_1551().field_1724.method_7353(class_2561.method_43470("7. ").method_10852(ChatMessage.ircprefixSmiley("")), false);
         class_310.method_1551().field_1724.method_7353(class_2561.method_43470("8. ").method_10852(ChatMessage.ircprefixBibi("")), false);
         class_310.method_1551().field_1724.method_7353(class_2561.method_43470("9. ").method_10852(ChatMessage.ircprefixBenena("")), false);
         class_310.method_1551().field_1724.method_7353(class_2561.method_43470("10. ").method_10852(ChatMessage.ircprefixBlyabuba("")), false);
      }

   }

   public void sendUsage() {
      ChatMessage.helpmessage("ÐŸÑ€Ð¸Ð¼ÐµÑ€ Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ñ ÐºÐ¾Ð¼Ð°Ð½Ð´Ñ‹ .irc:");
      ChatMessage.brandmessage(".irc <ÑÐ¾Ð¾Ð±Ñ‰ÐµÐ½Ð¸Ðµ> - ÐžÑ‚Ð¿Ñ€Ð°Ð²Ð¸Ñ‚ÑŒ ÑÐ¾Ð¾Ð±Ñ‰ÐµÐ½Ð¸Ðµ Ð² IRC-Ñ‡Ð°Ñ‚ (Ð½Ð°Ð¿Ñ€Ð¸Ð¼ÐµÑ€: .irc ÐŸÑ€Ð¸Ð²ÐµÑ‚, Ð¼Ð¸Ñ€!)");
      ChatMessage.brandmessage(".irc prefix <1-10> - Ð’Ñ‹Ð±Ñ€Ð°Ñ‚ÑŒ Ð¿Ñ€ÐµÑ„Ð¸ÐºÑ Ð´Ð»Ñ ÑÐ¾Ð¾Ð±Ñ‰ÐµÐ½Ð¸Ð¹ (Ð½Ð°Ð¿Ñ€Ð¸Ð¼ÐµÑ€: .irc prefix 1)");
      ChatMessage.brandmessage(".irc prefix list - ÐŸÐ¾ÐºÐ°Ð·Ð°Ñ‚ÑŒ ÑÐ¿Ð¸ÑÐ¾Ðº Ð´Ð¾ÑÑ‚ÑƒÐ¿Ð½Ñ‹Ñ… Ð¿Ñ€ÐµÑ„Ð¸ÐºÑÐ¾Ð²");
      ChatMessage.brandmessage(".irc clear - Ð¡Ð±Ñ€Ð¾ÑÐ¸Ñ‚ÑŒ Ð²Ñ‹Ð±Ñ€Ð°Ð½Ð½Ñ‹Ð¹ Ð¿Ñ€ÐµÑ„Ð¸ÐºÑ");
   }

   private class_2561 getPrefixText(String prefixName, String message) {
      switch (prefixName) {
         case "ÐŸÐ¸ÐºÐ¼Ð¸" -> {
            return ChatMessage.ircprefixPikmi(message);
         }
         case "Ð›Ð°Ð±ÑƒÐ±Ð°" -> {
            return ChatMessage.ircprefixLabuba(message);
         }
         case "Ð—Ð°Ð¿ÐµÐ½" -> {
            return ChatMessage.ircprefixZapen(message);
         }
         case "Ð‘ÑƒÑÑ‚" -> {
            return ChatMessage.ircprefixBoost(message);
         }
         case "Ð Ð¸Ñ‡" -> {
            return ChatMessage.ircprefixYanderov(message);
         }
         case "ÐŸÐ°Ð½Ð´Ð°" -> {
            return ChatMessage.ircprefixPanda(message);
         }
         case "(â—'â—¡'â—)" -> {
            return ChatMessage.ircprefixSmiley(message);
         }
         case "Ð‘Ð¸Ð±Ð¸...!" -> {
            return ChatMessage.ircprefixBibi(message);
         }
         case "Ð‘ÑÐ½ÐµÐ½Ð°" -> {
            return ChatMessage.ircprefixBenena(message);
         }
         case "Ð‘Ð»ÑÐ±ÑƒÐ±Ð°" -> {
            return ChatMessage.ircprefixBlyabuba(message);
         }
         default -> {
            return class_2561.method_43470(message);
         }
      }
   }

   private void sendSetPrefix(String prefix) {
      if (Yanderov.getInstance().getIrcManager().getClient() != null && Yanderov.getInstance().getIrcManager().getClient().isOpen()) {
         Yanderov.getInstance().getIrcManager().getClient().sendSetPrefix(prefix);
      }

   }

   public static void setSelectedPrefix(String prefix) {
      selectedPrefix = prefix;
   }

   public Stream tabComplete(String label, IArgConsumer args) throws CommandException {
      if (!args.hasAny()) {
         return (new TabCompleteHelper()).sortAlphabetically().prepend("prefix", "clear").stream();
      } else if (args.hasAny() && args.hasExactlyOne()) {
         String partial = args.getString().toLowerCase();
         return (new TabCompleteHelper()).sortAlphabetically().prepend("prefix", "clear").filterPrefix(partial).stream();
      } else {
         if (args.hasAny()) {
            String arg = args.getString().toLowerCase();
            if (arg.equals("prefix")) {
               if (args.hasAny()) {
                  String partial = args.getString().toLowerCase();
                  return (new TabCompleteHelper()).sortAlphabetically().prepend("list", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10").filterPrefix(partial).stream();
               }

               return (new TabCompleteHelper()).sortAlphabetically().prepend("list", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10").stream();
            }
         }

         return Stream.empty();
      }
   }
}

