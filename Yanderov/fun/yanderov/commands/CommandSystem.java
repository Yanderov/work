package fun.Yanderov.commands;

import fun.Yanderov.commands.argparser.ArgParserManager;
import fun.Yanderov.utils.client.managers.api.command.ICommandSystem;
import fun.Yanderov.utils.client.managers.api.command.argparser.IArgParserManager;

public enum CommandSystem implements ICommandSystem {
   INSTANCE;

   public IArgParserManager getParserManager() {
      return ArgParserManager.INSTANCE;
   }

   // $FF: synthetic method
   private static CommandSystem[] $values() {
      return new CommandSystem[]{INSTANCE};
   }
}

