package dev.client.yanderov.utils.client.managers.api.command;

import dev.client.yanderov.utils.client.managers.api.command.argument.IArgConsumer;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
import dev.client.yanderov.utils.display.interfaces.QuickLogger;
import java.util.List;
import java.util.stream.Stream;

public interface ICommand extends QuickLogger {
   void execute(String var1, IArgConsumer var2) throws CommandException;

   Stream tabComplete(String var1, IArgConsumer var2) throws CommandException;

   String getShortDesc();

   List getLongDesc();

   List getNames();

   default boolean hiddenFromHelp() {
      return false;
   }
}

