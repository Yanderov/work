package dev.client.yanderov.utils.client.managers.api.command.exception;

import dev.client.yanderov.utils.client.managers.api.command.ICommand;
import dev.client.yanderov.utils.client.managers.api.command.argument.ICommandArgument;
import dev.client.yanderov.utils.display.interfaces.QuickLogger;
import java.util.List;

public class CommandUnhandledException extends RuntimeException implements ICommandException, QuickLogger {
   public CommandUnhandledException(String message) {
      super(message);
   }

   public CommandUnhandledException(Throwable cause) {
      super(cause);
   }

   public void handle(ICommand command, List args) {
   }
}

