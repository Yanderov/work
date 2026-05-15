package fun.Yanderov.utils.client.managers.api.command.argument;

import fun.Yanderov.utils.client.managers.api.command.exception.CommandInvalidTypeException;

public interface ICommandArgument {
   int getIndex();

   String getValue();

   String getRawRest();

   Enum getEnum(Class var1) throws CommandInvalidTypeException;

   Object getAs(Class var1) throws CommandInvalidTypeException;

   boolean is(Class var1);

   Object getAs(Class var1, Class var2, Object var3) throws CommandInvalidTypeException;

   boolean is(Class var1, Class var2, Object var3);
}

