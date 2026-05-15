package dev.client.yanderov.commands.defaults;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.commands.manager.CommandRepository;
import dev.client.yanderov.utils.client.managers.api.command.Command;
import dev.client.yanderov.utils.client.managers.api.command.IBaritoneChatControl;
import dev.client.yanderov.utils.client.managers.api.command.ICommand;
import dev.client.yanderov.utils.client.managers.api.command.argument.IArgConsumer;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandNotFoundException;
import dev.client.yanderov.utils.client.managers.api.command.helpers.Paginator;
import dev.client.yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.class_124;
import net.minecraft.class_2558;
import net.minecraft.class_2561;
import net.minecraft.class_2568;
import net.minecraft.class_5250;
import net.minecraft.class_2558.class_2559;
import net.minecraft.class_2568.class_5247;

public class HelpCommand extends Command {
   Yanderov main;

   protected HelpCommand(Yanderov main) {
      super("help");
      this.main = main;
   }

   public void execute(String label, IArgConsumer args) throws CommandException {
      args.requireMax(1);
      CommandRepository commandRepository = this.main.getCommandRepository();
      if (args.hasAny() && !args.is(Integer.class)) {
         String commandName = args.getString().toLowerCase();
         ICommand command = commandRepository.getCommand(commandName);
         if (command == null) {
            throw new CommandNotFoundException(commandName);
         }

         this.logDirect("");
         command.getLongDesc().forEach(this::logDirect);
         this.logDirect("");
         class_5250 returnComponent = class_2561.method_43470("ÐÐ°Ð¶Ð¼Ð¸Ñ‚Ðµ Ñ‡Ñ‚Ð¾ Ð±Ñ‹ Ð²ÐµÑ€Ð½ÑƒÑ‚ÑŒÑÑ Ð¾Ð±Ñ€Ð°Ñ‚Ð½Ð¾ Ð² Ð¼ÐµÐ½ÑŽ");
         returnComponent.method_10862(returnComponent.method_10866().method_10958(new class_2558(class_2559.field_11750, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label)));
         this.logDirect(new class_2561[]{returnComponent});
      } else {
         Paginator.paginate(args, (Paginator)(new Paginator((List)commandRepository.getRegistry().descendingStream().filter((commandx) -> !commandx.hiddenFromHelp()).collect(Collectors.toList()))), () -> this.logDirect("Ð”Ð¾ÑÑ‚ÑƒÐ¿Ð½Ñ‹Ðµ ÐºÐ¾Ð¼Ð°Ð½Ð´Ñ‹:"), (commandx) -> {
            String names = String.join("/", commandx.getNames());
            String name = (String)commandx.getNames().get(0);
            class_5250 shortDescComponent = class_2561.method_43470(" - " + commandx.getShortDesc());
            shortDescComponent.method_10862(shortDescComponent.method_10866().method_10977(class_124.field_1063));
            class_5250 namesComponent = class_2561.method_43470(names);
            namesComponent.method_10862(namesComponent.method_10866().method_10977(class_124.field_1068));
            class_5250 hoverComponent = class_2561.method_43470("");
            hoverComponent.method_10862(hoverComponent.method_10866().method_10977(class_124.field_1080));
            hoverComponent.method_10852(namesComponent);
            hoverComponent.method_27693("\n" + commandx.getShortDesc());
            hoverComponent.method_27693("\n\nÐÐ°Ð¶Ð¼Ð¸Ñ‚Ðµ, Ñ‡Ñ‚Ð¾Ð±Ñ‹ Ð¿Ñ€Ð¾ÑÐ¼Ð¾Ñ‚Ñ€ÐµÑ‚ÑŒ Ð¿Ð¾Ð»Ð½ÑƒÑŽ ÑÐ¿Ñ€Ð°Ð²ÐºÑƒ Ð¾ ÐºÐ¾Ð¼Ð°Ð½Ð´Ðµ");
            String var10000 = IBaritoneChatControl.FORCE_COMMAND_PREFIX;
            String clickCommand = var10000 + String.format("%s %s", label, commandx.getNames().get(0));
            class_5250 component = class_2561.method_43470(name);
            component.method_10862(component.method_10866().method_10977(class_124.field_1080));
            component.method_10852(shortDescComponent);
            component.method_10862(component.method_10866().method_10949(new class_2568(class_5247.field_24342, hoverComponent)).method_10958(new class_2558(class_2559.field_11750, clickCommand)));
            return component;
         }, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label);
      }

   }

   public Stream tabComplete(String label, IArgConsumer args) throws CommandException {
      return args.hasExactlyOne() ? (new TabCompleteHelper()).addCommands(YanderovIntegration.getInstance().getCommandRepository()).filterPrefix(args.getString()).stream() : Stream.empty();
   }

   public String getShortDesc() {
      return "ÐŸÑ€Ð¾ÑÐ¼Ð¾Ñ‚Ñ€ Ð²ÑÐµÑ… Ð´Ð¾ÑÑ‚ÑƒÐ¿Ð½Ñ‹Ñ… ÐºÐ¾Ð¼Ð°Ð½Ð´";
   }

   public List getLongDesc() {
      return Arrays.asList("Ð¡ Ð¿Ð¾Ð¼Ð¾Ñ‰ÑŒÑŽ ÑÑ‚Ð¾Ð¹ ÐºÐ¾Ð¼Ð°Ð½Ð´Ñ‹ Ð¼Ð¾Ð¶Ð½Ð¾ Ð¿Ñ€Ð¾ÑÐ¼Ð¾Ñ‚Ñ€ÐµÑ‚ÑŒ Ð¿Ð¾Ð´Ñ€Ð¾Ð±Ð½ÑƒÑŽ ÑÐ¿Ñ€Ð°Ð²Ð¾Ñ‡Ð½ÑƒÑŽ Ð¸Ð½Ñ„Ð¾Ñ€Ð¼Ð°Ñ†Ð¸ÑŽ Ð¾ Ñ‚Ð¾Ð¼, ÐºÐ°Ðº Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ñ‚ÑŒ Ð¾Ð¿Ñ€ÐµÐ´ÐµÐ»ÐµÐ½Ð½Ñ‹Ðµ ÐºÐ¾Ð¼Ð°Ð½Ð´Ñ‹", "", "Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ðµ:", "> help - ÐŸÐµÑ€ÐµÑ‡Ð¸ÑÐ»ÑÐµÑ‚ Ð²ÑÐµ ÐºÐ¾Ð¼Ð°Ð½Ð´Ñ‹ Ð¸ Ð¸Ñ… ÐºÑ€Ð°Ñ‚ÐºÐ¸Ðµ Ð¾Ð¿Ð¸ÑÐ°Ð½Ð¸Ñ.", "> help <command> - ÐžÑ‚Ð¾Ð±Ñ€Ð°Ð¶ÐµÐ½Ð¸Ðµ ÑÐ¿Ñ€Ð°Ð²Ð¾Ñ‡Ð½Ð¾Ð¹ Ð¸Ð½Ñ„Ð¾Ñ€Ð¼Ð°Ñ†Ð¸Ð¸ Ð¿Ð¾ ÐºÐ¾Ð½ÐºÑ€ÐµÑ‚Ð½Ð¾Ð¹ ÐºÐ¾Ð¼Ð°Ð½Ð´Ðµ.");
   }
}

