package dev.client.yanderov.commands.defaults;

import dev.client.yanderov.utils.client.chat.ChatMessage;
import dev.client.yanderov.utils.client.managers.api.command.Command;
import dev.client.yanderov.utils.client.managers.api.command.argument.IArgConsumer;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.class_310;

public class TabParserCommand extends Command {
   public TabParserCommand() {
      super("tabparser");
   }

   public void execute(String label, IArgConsumer args) throws CommandException {
      if (!args.hasAny()) {
         this.sendUsage();
      } else {
         String subCommand = args.getString().toLowerCase();
         if (subCommand.equals("dir")) {
            File outputFile = new File(class_310.method_1551().field_1697, "tabparser_results.txt");
            if (!outputFile.exists()) {
               ChatMessage.brandmessage("Ð¤Ð°Ð¹Ð» Ñ Ñ€ÐµÐ·ÑƒÐ»ÑŒÑ‚Ð°Ñ‚Ð°Ð¼Ð¸ Ð¿Ð°Ñ€ÑÐ¸Ð½Ð³Ð° Ð½Ðµ Ð½Ð°Ð¹Ð´ÐµÐ½! Ð¡Ð½Ð°Ñ‡Ð°Ð»Ð° Ð·Ð°Ð¿ÑƒÑÑ‚Ð¸Ñ‚Ðµ Ð¼Ð¾Ð´ÑƒÐ»ÑŒ Tab Parser.");
               return;
            }

            try {
               String os = System.getProperty("os.name").toLowerCase();
               if (os.contains("win")) {
                  Runtime.getRuntime().exec("explorer /select," + outputFile.getAbsolutePath());
               } else if (os.contains("mac")) {
                  Runtime.getRuntime().exec("open -R " + outputFile.getAbsolutePath());
               } else if (os.contains("nix") || os.contains("nux")) {
                  Runtime.getRuntime().exec("xdg-open " + outputFile.getParent());
               }

               ChatMessage.brandmessage("Ð¤Ð°Ð¹Ð» Ð¾Ñ‚ÐºÑ€Ñ‹Ñ‚: " + outputFile.getName());
            } catch (IOException e) {
               ChatMessage.brandmessage("ÐžÑˆÐ¸Ð±ÐºÐ° Ð¿Ñ€Ð¸ Ð¾Ñ‚ÐºÑ€Ñ‹Ñ‚Ð¸Ð¸ Ñ„Ð°Ð¹Ð»Ð°: " + e.getMessage());
            }
         } else {
            this.sendUsage();
         }

      }
   }

   public void sendUsage() {
      ChatMessage.helpmessage("ÐŸÑ€Ð¸Ð¼ÐµÑ€ Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ñ ÐºÐ¾Ð¼Ð°Ð½Ð´Ñ‹ .tabparser:");
      ChatMessage.brandmessage(".tabparser dir - ÐžÑ‚ÐºÑ€Ñ‹Ñ‚ÑŒ Ñ„Ð°Ð¹Ð» Ñ Ñ€ÐµÐ·ÑƒÐ»ÑŒÑ‚Ð°Ñ‚Ð°Ð¼Ð¸ Ð¿Ð°Ñ€ÑÐ¸Ð½Ð³Ð°");
   }

   public Stream tabComplete(String label, IArgConsumer args) throws CommandException {
      if (!args.hasAny()) {
         return Stream.of("dir");
      } else if (args.hasExactlyOne()) {
         String partial = args.peekString().toLowerCase();
         return Stream.of("dir").filter((cmd) -> cmd.startsWith(partial));
      } else {
         return Stream.empty();
      }
   }

   public String getShortDesc() {
      return "Ð£Ð¿Ñ€Ð°Ð²Ð»ÑÐµÑ‚ Ñ„ÑƒÐ½ÐºÑ†Ð¸ÐµÐ¹ Tab Parser.";
   }

   public List getLongDesc() {
      return Arrays.asList("Ð£Ð¿Ñ€Ð°Ð²Ð»ÑÐµÑ‚ Ð¼Ð¾Ð´ÑƒÐ»ÐµÐ¼ Ð¿Ð°Ñ€ÑÐ¸Ð½Ð³Ð° Ð¸Ð³Ñ€Ð¾ÐºÐ¾Ð² Ñ Ð´Ð¾Ð½Ð°Ñ‚Ð°Ð¼Ð¸.", "", "Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ðµ:", ".tabparser dir - ÐžÑ‚ÐºÑ€Ñ‹Ñ‚ÑŒ Ñ„Ð°Ð¹Ð» Ñ Ñ€ÐµÐ·ÑƒÐ»ÑŒÑ‚Ð°Ñ‚Ð°Ð¼Ð¸ Ð¿Ð°Ñ€ÑÐ¸Ð½Ð³Ð°.");
   }
}

