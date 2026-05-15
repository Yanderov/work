package fun.Yanderov.commands.defaults;

import fun.Yanderov.common.repository.target.TargetRepository;
import fun.Yanderov.utils.client.managers.api.command.Command;
import fun.Yanderov.utils.client.managers.api.command.IBaritoneChatControl;
import fun.Yanderov.utils.client.managers.api.command.argument.IArgConsumer;
import fun.Yanderov.utils.client.managers.api.command.datatypes.TabPlayerDataType;
import fun.Yanderov.utils.client.managers.api.command.datatypes.TargetDataType;
import fun.Yanderov.utils.client.managers.api.command.exception.CommandException;
import fun.Yanderov.utils.client.managers.api.command.helpers.Paginator;
import fun.Yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import fun.Yanderov.utils.client.managers.api.command.manager.ICommandManager;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.class_124;
import net.minecraft.class_2561;

public class TargetCommand extends Command implements QuickImports {
   private final TargetRepository repository = TargetRepository.getInstance();

   public TargetCommand() {
      super("target");
   }

   public void execute(String label, IArgConsumer args) throws CommandException {
      if (!args.hasAny()) {
         this.handleListTargets(label, args);
      } else {
         switch (args.peekString().toLowerCase(Locale.US)) {
            case "add":
               args.get();
               this.handleAddTarget(args);
               break;
            case "remove":
               args.get();
               this.handleRemoveTarget(args);
               break;
            case "clear":
               args.get();
               this.handleClearTargets(args);
               break;
            case "list":
               args.get();
               this.handleListTargets(label, args);
               break;
            default:
               this.handleAddTarget(args);
         }

      }
   }

   private void handleAddTarget(IArgConsumer args) throws CommandException {
      args.requireMin(1);
      String name = args.getString();
      if (!this.repository.isTarget(name)) {
         this.repository.addTarget(name);
         this.logDirect("Ð˜Ð³Ñ€Ð¾Ðº " + String.valueOf(class_124.field_1060) + name + String.valueOf(class_124.field_1080) + " Ð´Ð¾Ð±Ð°Ð²Ð»ÐµÐ½ Ð² Ð¿Ñ€Ð¸Ð¾Ñ€Ð¸Ñ‚ÐµÑ‚Ð½Ñ‹Ð¹ ÑÐ¿Ð¸ÑÐ¾Ðº.", class_124.field_1080);
      } else {
         this.logDirect("Ð˜Ð³Ñ€Ð¾Ðº " + String.valueOf(class_124.field_1061) + name + String.valueOf(class_124.field_1080) + " ÑƒÐ¶Ðµ Ð² Ð¿Ñ€Ð¸Ð¾Ñ€Ð¸Ñ‚ÐµÑ‚Ð½Ð¾Ð¼ ÑÐ¿Ð¸ÑÐºÐµ.", class_124.field_1061);
      }

   }

   private void handleRemoveTarget(IArgConsumer args) throws CommandException {
      args.requireMin(1);
      String name = args.getString();
      if (this.repository.isTarget(name)) {
         this.repository.removeTarget(name);
         this.logDirect("Ð˜Ð³Ñ€Ð¾Ðº " + String.valueOf(class_124.field_1061) + name + String.valueOf(class_124.field_1080) + " ÑƒÐ´Ð°Ð»ÐµÐ½ Ð¸Ð· Ð¿Ñ€Ð¸Ð¾Ñ€Ð¸Ñ‚ÐµÑ‚Ð½Ð¾Ð³Ð¾ ÑÐ¿Ð¸ÑÐºÐ°.", class_124.field_1080);
      } else {
         this.logDirect("Ð˜Ð³Ñ€Ð¾Ðº " + String.valueOf(class_124.field_1061) + name + String.valueOf(class_124.field_1080) + " Ð½Ðµ Ð½Ð°Ð¹Ð´ÐµÐ½ Ð² Ð¿Ñ€Ð¸Ð¾Ñ€Ð¸Ñ‚ÐµÑ‚Ð½Ð¾Ð¼ ÑÐ¿Ð¸ÑÐºÐµ.", class_124.field_1061);
      }

   }

   private void handleClearTargets(IArgConsumer args) throws CommandException {
      args.requireMax(0);
      this.repository.clearTargets();
      this.logDirect("ÐŸÑ€Ð¸Ð¾Ñ€Ð¸Ñ‚ÐµÑ‚Ð½Ñ‹Ð¹ ÑÐ¿Ð¸ÑÐ¾Ðº Ñ†ÐµÐ»ÐµÐ¹ Ð¾Ñ‡Ð¸Ñ‰ÐµÐ½.", class_124.field_1060);
   }

   private void handleListTargets(String label, IArgConsumer args) throws CommandException {
      args.requireMax(1);
      Paginator.paginate(args, (Paginator)(new Paginator(this.repository.getTargets())), () -> this.logDirect("ÐŸÑ€Ð¸Ð¾Ñ€Ð¸Ñ‚ÐµÑ‚Ð½Ñ‹Ð¹ ÑÐ¿Ð¸ÑÐ¾Ðº Ñ†ÐµÐ»ÐµÐ¹:"), (targetName) -> {
         String var10000 = String.valueOf(class_124.field_1080);
         return class_2561.method_43470(var10000 + "- " + String.valueOf(class_124.field_1068) + targetName);
      }, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label + " list");
   }

   public Stream tabComplete(String label, IArgConsumer args) throws CommandException {
      if (args.hasExactlyOne()) {
         return (new TabCompleteHelper()).prepend("add", "remove", "list", "clear").filterPrefix(args.peekString()).addCommands((ICommandManager)mc.method_1562().method_2875()).stream();
      } else if (args.hasAtMost(2) && args.peekString(0).equalsIgnoreCase("add")) {
         return args.tabCompleteDatatype(TabPlayerDataType.INSTANCE);
      } else {
         return args.hasAtMost(2) && args.peekString(0).equalsIgnoreCase("remove") ? args.tabCompleteDatatype(TargetDataType.INSTANCE) : Stream.empty();
      }
   }

   public String getShortDesc() {
      return "Ð£Ð¿Ñ€Ð°Ð²Ð»ÑÐµÑ‚ Ð¿Ñ€Ð¸Ð¾Ñ€Ð¸Ñ‚ÐµÑ‚Ð½Ñ‹Ð¼Ð¸ Ñ†ÐµÐ»ÑÐ¼Ð¸ Ð´Ð»Ñ Aura.";
   }

   public List getLongDesc() {
      return Arrays.asList("Ð”Ð¾Ð±Ð°Ð²Ð»ÑÐµÑ‚ Ð¸Ð³Ñ€Ð¾ÐºÐ¾Ð² Ð² Ð¿Ñ€Ð¸Ð¾Ñ€Ð¸Ñ‚ÐµÑ‚Ð½Ñ‹Ð¹ ÑÐ¿Ð¸ÑÐ¾Ðº Ð´Ð»Ñ Ð¼Ð¾Ð´ÑƒÐ»Ñ Aura.", "", "Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ðµ:", "> target <Ð½Ð¸Ðº> - Ð”Ð¾Ð±Ð°Ð²Ð¸Ñ‚ÑŒ Ð¸Ð³Ñ€Ð¾ÐºÐ° Ð² Ð¿Ñ€Ð¸Ð¾Ñ€Ð¸Ñ‚ÐµÑ‚.", "> target add <Ð½Ð¸Ðº> - Ð”Ð¾Ð±Ð°Ð²Ð¸Ñ‚ÑŒ Ð¸Ð³Ñ€Ð¾ÐºÐ° Ð² Ð¿Ñ€Ð¸Ð¾Ñ€Ð¸Ñ‚ÐµÑ‚.", "> target remove <Ð½Ð¸Ðº> - Ð£Ð´Ð°Ð»Ð¸Ñ‚ÑŒ Ð¸Ð³Ñ€Ð¾ÐºÐ° Ð¸Ð· Ð¿Ñ€Ð¸Ð¾Ñ€Ð¸Ñ‚ÐµÑ‚Ð°.", "> target list - ÐŸÐ¾ÐºÐ°Ð·Ð°Ñ‚ÑŒ ÑÐ¿Ð¸ÑÐ¾Ðº Ð¿Ñ€Ð¸Ð¾Ñ€Ð¸Ñ‚ÐµÑ‚Ð½Ñ‹Ñ… Ð¸Ð³Ñ€Ð¾ÐºÐ¾Ð².", "> target clear - ÐžÑ‡Ð¸ÑÑ‚Ð¸Ñ‚ÑŒ ÑÐ¿Ð¸ÑÐ¾Ðº.");
   }
}

