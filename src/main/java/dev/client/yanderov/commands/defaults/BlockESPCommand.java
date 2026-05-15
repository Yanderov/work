package dev.client.yanderov.commands.defaults;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.features.impl.render.BlockESP;
import dev.client.yanderov.utils.client.chat.ChatMessage;
import dev.client.yanderov.utils.client.managers.api.command.Command;
import dev.client.yanderov.utils.client.managers.api.command.argument.IArgConsumer;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2960;
import net.minecraft.class_7923;

public class BlockESPCommand extends Command {
   public BlockESPCommand() {
      super("blockesp", "Ð£Ð¿Ñ€Ð°Ð²Ð»ÑÐµÑ‚ Ñ„ÑƒÐ½ÐºÑ†Ð¸ÐµÐ¹ Block ESP", "blockesp");
   }

   public void execute(String label, IArgConsumer args) throws CommandException {
      if (!args.hasAny()) {
         this.sendUsage();
      } else {
         BlockESP blockESP = (BlockESP)YanderovIntegration.getInstance().getModuleRepository().modules().stream().filter((module) -> module instanceof BlockESP).findFirst().orElse((Object)null);
         if (blockESP == null) {
            ChatMessage.brandmessage("ÐœÐ¾Ð´ÑƒÐ»ÑŒ Block ESP Ð½Ðµ Ð½Ð°Ð¹Ð´ÐµÐ½");
         } else {
            switch (args.getString().toLowerCase()) {
               case "add":
                  if (args.hasAny()) {
                     String blockToAdd = args.getString();
                     class_2248 block = (class_2248)class_7923.field_41175.method_63535(class_2960.method_12829(blockToAdd));
                     if (block != null && block != class_2246.field_10124) {
                        String registryName = class_7923.field_41175.method_10221(block).toString();
                        if (blockESP.getBlocksToHighlight().add(registryName)) {
                           ChatMessage.brandmessage("Ð”Ð¾Ð±Ð°Ð²Ð»ÐµÐ½ Ð±Ð»Ð¾Ðº " + registryName + " Ð² Block ESP");
                        } else {
                           ChatMessage.brandmessage("Ð‘Ð»Ð¾Ðº " + registryName + " ÑƒÐ¶Ðµ ÐµÑÑ‚ÑŒ Ð² Block ESP");
                        }
                     } else {
                        ChatMessage.brandmessage("Ð‘Ð»Ð¾Ðº " + blockToAdd + " Ð½Ðµ Ð½Ð°Ð¹Ð´ÐµÐ½");
                     }
                  } else {
                     ChatMessage.brandmessage("Ð£ÐºÐ°Ð¶Ð¸Ñ‚Ðµ Ð±Ð»Ð¾Ðº Ð´Ð»Ñ Ð´Ð¾Ð±Ð°Ð²Ð»ÐµÐ½Ð¸Ñ: .blockesp add <block>");
                  }
                  break;
               case "remove":
                  if (args.hasAny()) {
                     String blockToRemove = args.getString();
                     class_2248 block = (class_2248)class_7923.field_41175.method_63535(class_2960.method_12829(blockToRemove));
                     if (block != null && block != class_2246.field_10124) {
                        String registryName = class_7923.field_41175.method_10221(block).toString();
                        if (blockESP.getBlocksToHighlight().remove(registryName)) {
                           ChatMessage.brandmessage("Ð£Ð´Ð°Ð»Ñ‘Ð½ Ð±Ð»Ð¾Ðº " + registryName + " Ð¸Ð· Block ESP");
                        } else {
                           ChatMessage.brandmessage("Ð‘Ð»Ð¾Ðº " + blockToRemove + " Ð½Ðµ Ð½Ð°Ð¹Ð´ÐµÐ½ Ð² Block ESP");
                        }
                     } else {
                        ChatMessage.brandmessage("Ð‘Ð»Ð¾Ðº " + blockToRemove + " Ð½Ðµ Ð½Ð°Ð¹Ð´ÐµÐ½");
                     }
                  } else {
                     ChatMessage.brandmessage("Ð£ÐºÐ°Ð¶Ð¸Ñ‚Ðµ Ð±Ð»Ð¾Ðº Ð´Ð»Ñ ÑƒÐ´Ð°Ð»ÐµÐ½Ð¸Ñ: .blockesp remove <block>");
                  }
                  break;
               case "clear":
                  blockESP.getBlocksToHighlight().clear();
                  ChatMessage.brandmessage("Ð¡Ð¿Ð¸ÑÐ¾Ðº Block ESP Ð¾Ñ‡Ð¸Ñ‰ÐµÐ½");
                  break;
               case "list":
                  ChatMessage.brandmessage("Ð¡Ð¿Ð¸ÑÐ¾Ðº Ð²ÑÐµÑ… Ð±Ð»Ð¾ÐºÐ¾Ð² Ð² Minecraft:");
                  class_7923.field_41175.method_10235().stream().map(class_2960::toString).sorted().forEach((blockName) -> ChatMessage.brandmessage("- " + blockName));
                  break;
               default:
                  this.sendUsage();
            }

         }
      }
   }

   public void sendUsage() {
      ChatMessage.helpmessage("ÐŸÑ€Ð¸Ð¼ÐµÑ€ Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ñ ÐºÐ¾Ð¼Ð°Ð½Ð´Ñ‹ .blockesp:");
      ChatMessage.brandmessage(".blockesp add <block> - Ð”Ð¾Ð±Ð°Ð²Ð¸Ñ‚ÑŒ Ð±Ð»Ð¾Ðº Ð² Block ESP (Ð½Ð°Ð¿Ñ€Ð¸Ð¼ÐµÑ€: .blockesp add minecraft:diamond_ore)");
      ChatMessage.brandmessage(".blockesp remove <block> - Ð£Ð´Ð°Ð»Ð¸Ñ‚ÑŒ Ð±Ð»Ð¾Ðº Ð¸Ð· Block ESP (Ð½Ð°Ð¿Ñ€Ð¸Ð¼ÐµÑ€: .blockesp remove minecraft:diamond_ore)");
      ChatMessage.brandmessage(".blockesp clear - ÐžÑ‡Ð¸ÑÑ‚Ð¸Ñ‚ÑŒ ÑÐ¿Ð¸ÑÐ¾Ðº Block ESP");
      ChatMessage.brandmessage(".blockesp list - ÐŸÐ¾ÐºÐ°Ð·Ð°Ñ‚ÑŒ Ð²ÑÐµ Ð±Ð»Ð¾ÐºÐ¸ Ð² Minecraft");
   }

   public Stream tabComplete(String label, IArgConsumer args) throws CommandException {
      if (!args.hasAny()) {
         return Stream.of("add", "remove", "clear", "list");
      } else if (args.hasExactlyOne()) {
         String partial = args.peekString().toLowerCase();
         return Stream.of("add", "remove", "clear", "list").filter((cmd) -> cmd.startsWith(partial));
      } else {
         String subCommand = args.getString().toLowerCase();
         if (("add".equals(subCommand) || "remove".equals(subCommand)) && args.hasExactlyOne()) {
            String partial = args.peekString().toLowerCase();
            return class_7923.field_41175.method_10235().stream().map(class_2960::toString).filter((blockName) -> blockName.toLowerCase().startsWith(partial));
         } else {
            return Stream.empty();
         }
      }
   }

   public String getShortDesc() {
      return "Ð£Ð¿Ñ€Ð°Ð²Ð»ÑÐµÑ‚ Ñ„ÑƒÐ½ÐºÑ†Ð¸ÐµÐ¹ Block ESP.";
   }

   public List getLongDesc() {
      return Arrays.asList("Ð£Ð¿Ñ€Ð°Ð²Ð»ÑÐµÑ‚ Ð¿Ð¾Ð´ÑÐ²ÐµÑ‚ÐºÐ¾Ð¹ Ð±Ð»Ð¾ÐºÐ¾Ð² Ð² Ð¼Ð¾Ð´ÑƒÐ»Ðµ Block ESP.", "", "Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ðµ:", ".blockesp add <block> - Ð”Ð¾Ð±Ð°Ð²Ð¸Ñ‚ÑŒ Ð±Ð»Ð¾Ðº Ð² ÑÐ¿Ð¸ÑÐ¾Ðº Ð¿Ð¾Ð´ÑÐ²ÐµÑ‚ÐºÐ¸.", ".blockesp remove <block> - Ð£Ð´Ð°Ð»Ð¸Ñ‚ÑŒ Ð±Ð»Ð¾Ðº Ð¸Ð· ÑÐ¿Ð¸ÑÐºÐ° Ð¿Ð¾Ð´ÑÐ²ÐµÑ‚ÐºÐ¸.", ".blockesp clear - ÐžÑ‡Ð¸ÑÑ‚Ð¸Ñ‚ÑŒ ÑÐ¿Ð¸ÑÐ¾Ðº Ð¿Ð¾Ð´ÑÐ²ÐµÑ‡Ð¸Ð²Ð°ÐµÐ¼Ñ‹Ñ… Ð±Ð»Ð¾ÐºÐ¾Ð².", ".blockesp list - ÐŸÐ¾ÐºÐ°Ð·Ð°Ñ‚ÑŒ Ð²ÑÐµ Ð´Ð¾ÑÑ‚ÑƒÐ¿Ð½Ñ‹Ðµ Ð±Ð»Ð¾ÐºÐ¸.");
   }
}

