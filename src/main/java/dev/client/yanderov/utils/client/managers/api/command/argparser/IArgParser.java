package dev.client.yanderov.utils.client.managers.api.command.argparser;

import dev.client.yanderov.utils.client.managers.api.command.argument.ICommandArgument;

public interface IArgParser {
   Class getTarget();

   public interface Stated extends IArgParser {
      Class getStateType();

      Object parseArg(ICommandArgument var1, Object var2) throws Exception;
   }

   public interface Stateless extends IArgParser {
      Object parseArg(ICommandArgument var1) throws Exception;
   }
}

