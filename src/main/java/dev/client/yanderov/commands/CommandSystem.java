package dev.client.yanderov.commands;

import dev.client.yanderov.commands.argparser.ArgParserManager;
import dev.client.yanderov.utils.client.managers.api.command.ICommandSystem;
import dev.client.yanderov.utils.client.managers.api.command.argparser.IArgParserManager;

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

