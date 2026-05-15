package dev.client.yanderov.utils.client.managers.api.command.argparser;

import dev.client.yanderov.utils.client.managers.api.command.argument.ICommandArgument;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandInvalidTypeException;
import dev.client.yanderov.utils.client.managers.api.command.registry.Registry;

public interface IArgParserManager {
   IArgParser.Stateless getParserStateless(Class var1);

   IArgParser.Stated getParserStated(Class var1, Class var2);

   Object parseStateless(Class var1, ICommandArgument var2) throws CommandInvalidTypeException;

   Object parseStated(Class var1, Class var2, ICommandArgument var3, Object var4) throws CommandInvalidTypeException;

   Registry getRegistry();
}

