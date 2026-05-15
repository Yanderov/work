package fun.Yanderov.utils.client.managers.api.command.argument;

import fun.Yanderov.utils.client.managers.api.command.datatypes.IDatatype;
import fun.Yanderov.utils.client.managers.api.command.datatypes.IDatatypeFor;
import fun.Yanderov.utils.client.managers.api.command.datatypes.IDatatypePost;
import fun.Yanderov.utils.client.managers.api.command.exception.CommandException;
import fun.Yanderov.utils.client.managers.api.command.exception.CommandInvalidTypeException;
import fun.Yanderov.utils.client.managers.api.command.exception.CommandNotEnoughArgumentsException;
import fun.Yanderov.utils.client.managers.api.command.exception.CommandTooManyArgumentsException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Stream;

public interface IArgConsumer {
   LinkedList getArgs();

   Deque getConsumed();

   boolean has(int var1);

   boolean hasAny();

   boolean hasAtMost(int var1);

   boolean hasAtMostOne();

   boolean hasExactly(int var1);

   boolean hasExactlyOne();

   ICommandArgument peek(int var1) throws CommandNotEnoughArgumentsException;

   ICommandArgument peek() throws CommandNotEnoughArgumentsException;

   boolean is(Class var1, int var2) throws CommandNotEnoughArgumentsException;

   boolean is(Class var1) throws CommandNotEnoughArgumentsException;

   String peekString(int var1) throws CommandNotEnoughArgumentsException;

   String peekString() throws CommandNotEnoughArgumentsException;

   Enum peekEnum(Class var1, int var2) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   Enum peekEnum(Class var1) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   Enum peekEnumOrNull(Class var1, int var2) throws CommandNotEnoughArgumentsException;

   Enum peekEnumOrNull(Class var1) throws CommandNotEnoughArgumentsException;

   Object peekAs(Class var1, int var2) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   Object peekAs(Class var1) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   Object peekAsOrDefault(Class var1, Object var2, int var3) throws CommandNotEnoughArgumentsException;

   Object peekAsOrDefault(Class var1, Object var2) throws CommandNotEnoughArgumentsException;

   Object peekAsOrNull(Class var1, int var2) throws CommandNotEnoughArgumentsException;

   Object peekAsOrNull(Class var1) throws CommandNotEnoughArgumentsException;

   Object peekDatatype(IDatatypeFor var1) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   Object peekDatatype(IDatatypePost var1) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   Object peekDatatype(IDatatypePost var1, Object var2) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   Object peekDatatypeOrNull(IDatatypeFor var1);

   Object peekDatatypeOrNull(IDatatypePost var1);

   Object peekDatatypePost(IDatatypePost var1, Object var2) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   Object peekDatatypePostOrDefault(IDatatypePost var1, Object var2, Object var3);

   Object peekDatatypePostOrNull(IDatatypePost var1, Object var2);

   Object peekDatatypeFor(Class var1);

   Object peekDatatypeForOrDefault(Class var1, Object var2);

   Object peekDatatypeForOrNull(Class var1);

   ICommandArgument get() throws CommandNotEnoughArgumentsException;

   String getString() throws CommandNotEnoughArgumentsException;

   Enum getEnum(Class var1) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   Enum getEnumOrDefault(Class var1, Enum var2) throws CommandNotEnoughArgumentsException;

   Enum getEnumOrNull(Class var1) throws CommandNotEnoughArgumentsException;

   Object getAs(Class var1) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   Object getAsOrDefault(Class var1, Object var2) throws CommandNotEnoughArgumentsException;

   Object getAsOrNull(Class var1) throws CommandNotEnoughArgumentsException;

   Object getDatatypePost(IDatatypePost var1, Object var2) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   Object getDatatypePostOrDefault(IDatatypePost var1, Object var2, Object var3);

   Object getDatatypePostOrNull(IDatatypePost var1, Object var2);

   Object getDatatypeFor(IDatatypeFor var1) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   Object getDatatypeForOrDefault(IDatatypeFor var1, Object var2);

   Object getDatatypeForOrNull(IDatatypeFor var1);

   Stream tabCompleteDatatype(IDatatype var1);

   String rawRest();

   void requireMin(int var1) throws CommandNotEnoughArgumentsException;

   void requireMax(int var1) throws CommandTooManyArgumentsException;

   void requireExactly(int var1) throws CommandException;

   boolean hasConsumed();

   ICommandArgument consumed();

   String consumedString();

   IArgConsumer copy();
}

