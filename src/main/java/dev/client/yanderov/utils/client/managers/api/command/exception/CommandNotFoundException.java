package dev.client.yanderov.utils.client.managers.api.command.exception;

import dev.client.yanderov.utils.client.managers.api.command.ICommand;
import dev.client.yanderov.utils.client.managers.api.command.argument.ICommandArgument;
import dev.client.yanderov.utils.display.interfaces.QuickLogger;
import java.util.List;

public class CommandNotFoundException extends CommandException implements QuickLogger {
   public final String command;

   public CommandNotFoundException(String command) {
      super(String.format("ÐšÐ¾Ð¼Ð°Ð½Ð´Ð° Ð½Ðµ Ð½Ð°Ð¹Ð´ÐµÐ½Ð°: %s", command));
      this.command = command;
   }

   public void handle(ICommand command, List args) {
      this.logDirect(this.getMessage());
   }
}

