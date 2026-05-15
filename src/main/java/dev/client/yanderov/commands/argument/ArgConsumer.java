package dev.client.yanderov.commands.argument;

import dev.client.yanderov.utils.client.managers.api.command.argument.IArgConsumer;
import dev.client.yanderov.utils.client.managers.api.command.argument.ICommandArgument;
import dev.client.yanderov.utils.client.managers.api.command.datatypes.IDatatype;
import dev.client.yanderov.utils.client.managers.api.command.datatypes.IDatatypeContext;
import dev.client.yanderov.utils.client.managers.api.command.datatypes.IDatatypeFor;
import dev.client.yanderov.utils.client.managers.api.command.datatypes.IDatatypePost;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandInvalidTypeException;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandNotEnoughArgumentsException;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandTooManyArgumentsException;
import dev.client.yanderov.utils.client.managers.api.command.manager.ICommandManager;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class ArgConsumer implements IArgConsumer {
   private final ICommandManager manager;
   private final IDatatypeContext context;
   private final LinkedList args;
   private final Deque consumed;

   private ArgConsumer(ICommandManager manager, Deque args, Deque consumed) {
      this.manager = manager;
      this.context = new Context();
      this.args = new LinkedList(args);
      this.consumed = new LinkedList(consumed);
   }

   public ArgConsumer(ICommandManager manager, List args) {
      this(manager, new LinkedList(args), new LinkedList());
   }

   public LinkedList getArgs() {
      return this.args;
   }

   public Deque getConsumed() {
      return this.consumed;
   }

   public boolean has(int num) {
      return this.args.size() >= num;
   }

   public boolean hasAny() {
      return this.has(1);
   }

   public boolean hasAtMost(int num) {
      return this.args.size() <= num;
   }

   public boolean hasAtMostOne() {
      return this.hasAtMost(1);
   }

   public boolean hasExactly(int num) {
      return this.args.size() == num;
   }

   public boolean hasExactlyOne() {
      return this.hasExactly(1);
   }

   public ICommandArgument peek(int index) throws CommandNotEnoughArgumentsException {
      this.requireMin(index + 1);
      return (ICommandArgument)this.args.get(index);
   }

   public ICommandArgument peek() throws CommandNotEnoughArgumentsException {
      return this.peek(0);
   }

   public boolean is(Class type, int index) throws CommandNotEnoughArgumentsException {
      return this.peek(index).is(type);
   }

   public boolean is(Class type) throws CommandNotEnoughArgumentsException {
      return this.is(type, 0);
   }

   public String peekString(int index) throws CommandNotEnoughArgumentsException {
      return this.peek(index).getValue();
   }

   public String peekString() throws CommandNotEnoughArgumentsException {
      return this.peekString(0);
   }

   public Enum peekEnum(Class enumClass, int index) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      return this.peek(index).getEnum(enumClass);
   }

   public Enum peekEnum(Class enumClass) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      return this.peekEnum(enumClass, 0);
   }

   public Enum peekEnumOrNull(Class enumClass, int index) throws CommandNotEnoughArgumentsException {
      try {
         return this.peekEnum(enumClass, index);
      } catch (CommandInvalidTypeException var4) {
         return null;
      }
   }

   public Enum peekEnumOrNull(Class enumClass) throws CommandNotEnoughArgumentsException {
      return this.peekEnumOrNull(enumClass, 0);
   }

   public Object peekAs(Class type, int index) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      return this.peek(index).getAs(type);
   }

   public Object peekAs(Class type) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      return this.peekAs(type, 0);
   }

   public Object peekAsOrDefault(Class type, Object def, int index) throws CommandNotEnoughArgumentsException {
      try {
         return this.peekAs(type, index);
      } catch (CommandInvalidTypeException var5) {
         return def;
      }
   }

   public Object peekAsOrDefault(Class type, Object def) throws CommandNotEnoughArgumentsException {
      return this.peekAsOrDefault(type, def, 0);
   }

   public Object peekAsOrNull(Class type, int index) throws CommandNotEnoughArgumentsException {
      return this.peekAsOrDefault(type, (Object)null, index);
   }

   public Object peekAsOrNull(Class type) throws CommandNotEnoughArgumentsException {
      return this.peekAsOrNull(type, 0);
   }

   public Object peekDatatype(IDatatypeFor datatype) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      return this.copy().getDatatypeFor(datatype);
   }

   public Object peekDatatype(IDatatypePost datatype) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      return this.peekDatatype(datatype, (Object)null);
   }

   public Object peekDatatype(IDatatypePost datatype, Object original) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      return this.copy().getDatatypePost(datatype, original);
   }

   public Object peekDatatypeOrNull(IDatatypeFor datatype) {
      return this.copy().getDatatypeForOrNull(datatype);
   }

   public Object peekDatatypeOrNull(IDatatypePost datatype) {
      return this.copy().getDatatypePostOrNull(datatype, (Object)null);
   }

   public Object peekDatatypePost(IDatatypePost datatype, Object original) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      return this.copy().getDatatypePost(datatype, original);
   }

   public Object peekDatatypePostOrDefault(IDatatypePost datatype, Object original, Object def) {
      return this.copy().getDatatypePostOrDefault(datatype, original, def);
   }

   public Object peekDatatypePostOrNull(IDatatypePost datatype, Object original) {
      return this.peekDatatypePostOrDefault(datatype, original, (Object)null);
   }

   public Object peekDatatypeFor(Class datatype) {
      return this.copy().peekDatatypeFor(datatype);
   }

   public Object peekDatatypeForOrDefault(Class datatype, Object def) {
      return this.copy().peekDatatypeForOrDefault(datatype, def);
   }

   public Object peekDatatypeForOrNull(Class datatype) {
      return this.peekDatatypeForOrDefault(datatype, (Object)null);
   }

   public ICommandArgument get() throws CommandNotEnoughArgumentsException {
      this.requireMin(1);
      ICommandArgument arg = (ICommandArgument)this.args.removeFirst();
      this.consumed.add(arg);
      return arg;
   }

   public String getString() throws CommandNotEnoughArgumentsException {
      return this.get().getValue();
   }

   public Enum getEnum(Class enumClass) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      return this.get().getEnum(enumClass);
   }

   public Enum getEnumOrDefault(Class enumClass, Enum def) throws CommandNotEnoughArgumentsException {
      try {
         this.peekEnum(enumClass);
         return this.getEnum(enumClass);
      } catch (CommandInvalidTypeException var4) {
         return def;
      }
   }

   public Enum getEnumOrNull(Class enumClass) throws CommandNotEnoughArgumentsException {
      return this.getEnumOrDefault(enumClass, (Enum)null);
   }

   public Object getAs(Class type) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      return this.get().getAs(type);
   }

   public Object getAsOrDefault(Class type, Object def) throws CommandNotEnoughArgumentsException {
      try {
         T val = (T)this.peek().getAs(type);
         this.get();
         return val;
      } catch (CommandInvalidTypeException var4) {
         return def;
      }
   }

   public Object getAsOrNull(Class type) throws CommandNotEnoughArgumentsException {
      return this.getAsOrDefault(type, (Object)null);
   }

   public Object getDatatypePost(IDatatypePost datatype, Object original) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      try {
         return datatype.apply(this.context, original);
      } catch (Exception e) {
         e.printStackTrace();
         throw new CommandInvalidTypeException(this.hasAny() ? this.peek() : this.consumed(), datatype.getClass().getSimpleName(), e);
      }
   }

   public Object getDatatypePostOrDefault(IDatatypePost datatype, Object original, Object _default) {
      List<ICommandArgument> argsSnapshot = new ArrayList(this.args);
      List<ICommandArgument> consumedSnapshot = new ArrayList(this.consumed);

      try {
         return this.getDatatypePost(datatype, original);
      } catch (Exception var7) {
         this.args.clear();
         this.args.addAll(argsSnapshot);
         this.consumed.clear();
         this.consumed.addAll(consumedSnapshot);
         return _default;
      }
   }

   public Object getDatatypePostOrNull(IDatatypePost datatype, Object original) {
      return this.getDatatypePostOrDefault(datatype, original, (Object)null);
   }

   public Object getDatatypeFor(IDatatypeFor datatype) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      try {
         return datatype.get(this.context);
      } catch (Exception e) {
         e.printStackTrace();
         throw new CommandInvalidTypeException(this.hasAny() ? this.peek() : this.consumed(), datatype.getClass().getSimpleName(), e);
      }
   }

   public Object getDatatypeForOrDefault(IDatatypeFor datatype, Object def) {
      List<ICommandArgument> argsSnapshot = new ArrayList(this.args);
      List<ICommandArgument> consumedSnapshot = new ArrayList(this.consumed);

      try {
         return this.getDatatypeFor(datatype);
      } catch (Exception var6) {
         this.args.clear();
         this.args.addAll(argsSnapshot);
         this.consumed.clear();
         this.consumed.addAll(consumedSnapshot);
         return def;
      }
   }

   public Object getDatatypeForOrNull(IDatatypeFor datatype) {
      return this.getDatatypeForOrDefault(datatype, (Object)null);
   }

   public Stream tabCompleteDatatype(IDatatype datatype) {
      try {
         return datatype.tabComplete(this.context);
      } catch (CommandException var3) {
      } catch (Exception e) {
         e.printStackTrace();
      }

      return Stream.empty();
   }

   public String rawRest() {
      return this.args.size() > 0 ? ((ICommandArgument)this.args.getFirst()).getRawRest() : "";
   }

   public void requireMin(int min) throws CommandNotEnoughArgumentsException {
      if (this.args.size() < min) {
         throw new CommandNotEnoughArgumentsException(min + this.consumed.size());
      }
   }

   public void requireMax(int max) throws CommandTooManyArgumentsException {
      if (this.args.size() > max) {
         throw new CommandTooManyArgumentsException(max + this.consumed.size());
      }
   }

   public void requireExactly(int args) throws CommandException {
      this.requireMin(args);
      this.requireMax(args);
   }

   public boolean hasConsumed() {
      return !this.consumed.isEmpty();
   }

   public ICommandArgument consumed() {
      return (ICommandArgument)(!this.consumed.isEmpty() ? (ICommandArgument)this.consumed.getLast() : CommandArguments.unknown());
   }

   public String consumedString() {
      return this.consumed().getValue();
   }

   public ArgConsumer copy() {
      return new ArgConsumer(this.manager, this.args, this.consumed);
   }

   private final class Context implements IDatatypeContext {
      public ArgConsumer getConsumer() {
         return ArgConsumer.this;
      }
   }
}

