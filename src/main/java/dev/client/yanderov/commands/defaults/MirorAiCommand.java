package dev.client.yanderov.commands.defaults;

import dev.client.yanderov.utils.client.managers.api.command.Command;
import dev.client.yanderov.utils.client.managers.api.command.argument.IArgConsumer;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
import dev.client.yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import dev.client.yanderov.utils.features.aura.miror.MirorAiManager;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.class_2561;
import net.minecraft.class_310;

public class MirorAiCommand extends Command {
   public MirorAiCommand() {
      super("minaraiai");
   }

   public void execute(String label, IArgConsumer args) throws CommandException {
      class_310 mc = class_310.method_1551();
      MirorAiManager mgr = MirorAiManager.getInstance();
      String sub = args.hasAny() ? args.getString().toLowerCase(Locale.US) : "";
      if (sub.isEmpty()) {
         this.sendUsage();
      } else {
         switch (sub) {
            case "start":
               mgr.startTrainingWithSlimes();
               this.send("Ð¾Ð±ÑƒÑ‡ÐµÐ½Ð¸Ðµ Ð½Ð°Ñ‡Ð°Ñ‚Ð¾. Ð’Ð¾ÐºÑ€ÑƒÐ³ Ð¿Ð¾ÑÐ²Ð¸Ð»Ð¸ÑÑŒ Ñ‚Ñ€ÐµÐ½Ð¸Ñ€Ð¾Ð²Ð¾Ñ‡Ð½Ñ‹Ðµ ÑÐ»Ð¸Ð·Ð½Ð¸.");
               break;
            case "stop":
               mgr.stopTrainingWithSlimes();
               this.send("Ð¾Ð±ÑƒÑ‡ÐµÐ½Ð¸Ðµ Ð¾ÑÑ‚Ð°Ð½Ð¾Ð²Ð»ÐµÐ½Ð¾, Ñ‚Ñ€ÐµÐ½Ð¸Ñ€Ð¾Ð²Ð¾Ñ‡Ð½Ñ‹Ðµ ÑÐ»Ð¸Ð·Ð½Ð¸ ÑƒÐ´Ð°Ð»ÐµÐ½Ñ‹.");
               break;
            case "save":
               args.requireMin(1);
               String name = args.getString();
               if (mgr.saveCurrentAs(name)) {
                  this.send("Ð¼Ð¾Ð´ÐµÐ»ÑŒ \"" + name + "\" ÑÐ¾Ñ…Ñ€Ð°Ð½ÐµÐ½Ð°, ÑÐ¸Ð¼Ð¿Ð»Ð¾Ð²: " + mgr.getCurrentSamplesCount());
               } else {
                  this.send("Ð½Ðµ ÑƒÐ´Ð°Ð»Ð¾ÑÑŒ ÑÐ¾Ñ…Ñ€Ð°Ð½Ð¸Ñ‚ÑŒ Ð¼Ð¾Ð´ÐµÐ»ÑŒ (Ð½ÐµÑ‚ Ð´Ð°Ð½Ð½Ñ‹Ñ… Ð¸Ð»Ð¸ Ð¾Ð±ÑƒÑ‡ÐµÐ½Ð¸Ðµ Ð½Ðµ Ð°ÐºÑ‚Ð¸Ð²Ð½Ð¾).");
               }
               break;
            case "list":
               List<String> models = mgr.listModels();
               if (models.isEmpty()) {
                  this.send("Ð¼Ð¾Ð´ÐµÐ»ÐµÐ¹ Ð½ÐµÑ‚");
               } else {
                  this.send("Ð¼Ð¾Ð´ÐµÐ»Ð¸: " + String.join(", ", models));
               }
               break;
            case "delete":
               args.requireMin(1);
               String name = args.getString();
               if (mgr.deleteModel(name)) {
                  this.send("Ð¼Ð¾Ð´ÐµÐ»ÑŒ \"" + name + "\" ÑƒÐ´Ð°Ð»ÐµÐ½Ð°");
               } else {
                  this.send("Ð½Ðµ ÑƒÐ´Ð°Ð»Ð¾ÑÑŒ ÑƒÐ´Ð°Ð»Ð¸Ñ‚ÑŒ Ð¼Ð¾Ð´ÐµÐ»ÑŒ \"" + name + "\"");
               }
               break;
            default:
               this.send("Ð½ÐµÐ¸Ð·Ð²ÐµÑÑ‚Ð½Ð°Ñ Ð¿Ð¾Ð´ÐºÐ¾Ð¼Ð°Ð½Ð´Ð°.");
               this.sendUsage();
         }

      }
   }

   public Stream tabComplete(String label, IArgConsumer args) throws CommandException {
      MirorAiManager mgr = MirorAiManager.getInstance();
      if (!args.hasAny()) {
         return (new TabCompleteHelper()).sortAlphabetically().prepend("start", "stop", "save", "list", "delete").stream();
      } else {
         String first = args.getString();
         if (args.hasExactlyOne()) {
            return (new TabCompleteHelper()).sortAlphabetically().prepend("start", "stop", "save", "list", "delete").filterPrefix(first).stream();
         } else if (!first.equalsIgnoreCase("save") && !first.equalsIgnoreCase("delete")) {
            return Stream.empty();
         } else {
            String partial = args.getString();
            List<String> models = mgr.listModels();
            if (models.isEmpty()) {
               return Stream.empty();
            } else {
               TabCompleteHelper helper = (new TabCompleteHelper()).sortAlphabetically();
               helper.prepend((String[])models.toArray(new String[0]));
               return helper.filterPrefix(partial).stream();
            }
         }
      }
   }

   public String getShortDesc() {
      return "Ð£Ð¿Ñ€Ð°Ð²Ð»ÐµÐ½Ð¸Ðµ Ð¾Ð±ÑƒÑ‡Ð°ÐµÐ¼Ð¾Ð¹ Ñ€Ð¾Ñ‚Ð°Ñ†Ð¸ÐµÐ¹ MinarAi (Ð¾Ð±ÑƒÑ‡ÐµÐ½Ð¸Ðµ, Ð¼Ð¾Ð´ÐµÐ»Ð¸).";
   }

   public List getLongDesc() {
      return Arrays.asList("minaraiAi â€“ Ð¾Ð±ÑƒÑ‡Ð°ÐµÐ¼Ð°Ñ Ñ€Ð¾Ñ‚Ð°Ñ†Ð¸Ñ Ð°ÑƒÑ€Ñ‹. ÐšÐ¾Ð¼Ð°Ð½Ð´Ð° ÑƒÐ¿Ñ€Ð°Ð²Ð»ÑÐµÑ‚ Ð¾Ð±ÑƒÑ‡ÐµÐ½Ð¸ÐµÐ¼ Ð¸ Ð¼Ð¾Ð´ÐµÐ»ÑÐ¼Ð¸.", "", "Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ðµ:", "> minaraiai start â€“ Ð½Ð°Ñ‡Ð°Ñ‚ÑŒ Ð¾Ð±ÑƒÑ‡ÐµÐ½Ð¸Ðµ, Ð·Ð°ÑÐ¿Ð°Ð²Ð½Ð¸Ñ‚ÑŒ Ð»Ð¾ÐºÐ°Ð»ÑŒÐ½Ñ‹Ñ… ÑÐ»Ð¸Ð·Ð½ÐµÐ¹ Ð²Ð¾ÐºÑ€ÑƒÐ³ Ð¸Ð³Ñ€Ð¾ÐºÐ°", "> minaraiai stop â€“ Ð¾ÑÑ‚Ð°Ð½Ð¾Ð²Ð¸Ñ‚ÑŒ Ð¾Ð±ÑƒÑ‡ÐµÐ½Ð¸Ðµ Ð¸ ÑƒÐ±Ñ€Ð°Ñ‚ÑŒ Ñ‚Ñ€ÐµÐ½Ð¸Ñ€Ð¾Ð²Ð¾Ñ‡Ð½Ñ‹Ñ… ÑÐ»Ð¸Ð·Ð½ÐµÐ¹", "> minaraiai save <name> â€“ ÑÐ¾Ñ…Ñ€Ð°Ð½Ð¸Ñ‚ÑŒ Ñ‚ÐµÐºÑƒÑ‰Ð¸Ðµ Ð¾Ð±ÑƒÑ‡ÐµÐ½Ð½Ñ‹Ðµ Ð´Ð°Ð½Ð½Ñ‹Ðµ Ð² Ð¼Ð¾Ð´ÐµÐ»ÑŒ <name>", "> minaraiai list â€“ Ð¿Ð¾ÐºÐ°Ð·Ð°Ñ‚ÑŒ ÑÐ¿Ð¸ÑÐ¾Ðº ÑÐ¾Ñ…Ñ€Ð°Ð½Ñ‘Ð½Ð½Ñ‹Ñ… Ð¼Ð¾Ð´ÐµÐ»ÐµÐ¹", "> minaraiai delete <name> â€“ ÑƒÐ´Ð°Ð»Ð¸Ñ‚ÑŒ Ð¼Ð¾Ð´ÐµÐ»ÑŒ <name>");
   }

   private void sendUsage() {
      this.send("ÐºÐ¾Ð¼Ð°Ð½Ð´Ñ‹:");
      this.send("> .minaraiai start  â€“ Ð½Ð°Ñ‡Ð°Ñ‚ÑŒ Ð¾Ð±ÑƒÑ‡ÐµÐ½Ð¸Ðµ Ð¸ Ð·Ð°ÑÐ¿Ð°Ð²Ð½Ð¸Ñ‚ÑŒ Ñ‚Ñ€ÐµÐ½Ð¸Ñ€Ð¾Ð²Ð¾Ñ‡Ð½Ñ‹Ñ… ÑÐ»Ð¸Ð·Ð½ÐµÐ¹");
      this.send("> .minaraiai stop   â€“ Ð¾ÑÑ‚Ð°Ð½Ð¾Ð²Ð¸Ñ‚ÑŒ Ð¾Ð±ÑƒÑ‡ÐµÐ½Ð¸Ðµ Ð¸ ÑƒÐ´Ð°Ð»Ð¸Ñ‚ÑŒ ÑÐ»Ð¸Ð·Ð½ÐµÐ¹");
      this.send("> .minaraiai save <name>   â€“ ÑÐ¾Ñ…Ñ€Ð°Ð½Ð¸Ñ‚ÑŒ Ð¼Ð¾Ð´ÐµÐ»ÑŒ");
      this.send("> .minaraiai list          â€“ ÑÐ¿Ð¸ÑÐ¾Ðº Ð¼Ð¾Ð´ÐµÐ»ÐµÐ¹");
      this.send("> .minaraiai delete <name> â€“ ÑƒÐ´Ð°Ð»Ð¸Ñ‚ÑŒ Ð¼Ð¾Ð´ÐµÐ»ÑŒ");
   }

   private void send(String msg) {
      class_310 mc = class_310.method_1551();
      if (mc.field_1724 != null) {
         mc.field_1724.method_7353(class_2561.method_43470(msg), false);
      }

   }
}

