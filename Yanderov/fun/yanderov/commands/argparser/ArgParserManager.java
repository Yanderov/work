package fun.Yanderov.commands.argparser;

import fun.Yanderov.utils.client.managers.api.command.argparser.IArgParser;
import fun.Yanderov.utils.client.managers.api.command.argparser.IArgParserManager;
import fun.Yanderov.utils.client.managers.api.command.argument.ICommandArgument;
import fun.Yanderov.utils.client.managers.api.command.exception.CommandInvalidTypeException;
import fun.Yanderov.utils.client.managers.api.command.exception.CommandNoParserForTypeException;
import fun.Yanderov.utils.client.managers.api.command.registry.Registry;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public enum ArgParserManager implements IArgParserManager {
   INSTANCE;

   public final Registry registry = new Registry();

   private ArgParserManager() {
      List var10000 = DefaultArgParsers.ALL;
      Registry var10001 = this.registry;
      Objects.requireNonNull(var10001);
      var10000.forEach(var10001::register);
   }

   public IArgParser.Stateless getParserStateless(Class type) {
      Stream var10000 = this.registry.descendingStream();
      Objects.requireNonNull(IArgParser.Stateless.class);
      var10000 = var10000.filter(IArgParser.Stateless.class::isInstance);
      Objects.requireNonNull(IArgParser.Stateless.class);
      return (IArgParser.Stateless)var10000.map(IArgParser.Stateless.class::cast).filter((parser) -> parser.getTarget().isAssignableFrom(type)).findFirst().orElse((Object)null);
   }

   public IArgParser.Stated getParserStated(Class type, Class stateKlass) {
      Stream var10000 = this.registry.descendingStream();
      Objects.requireNonNull(IArgParser.Stated.class);
      var10000 = var10000.filter(IArgParser.Stated.class::isInstance);
      Objects.requireNonNull(IArgParser.Stated.class);
      var10000 = var10000.map(IArgParser.Stated.class::cast).filter((parser) -> parser.getTarget().isAssignableFrom(type)).filter((parser) -> parser.getStateType().isAssignableFrom(stateKlass));
      Objects.requireNonNull(IArgParser.Stated.class);
      return (IArgParser.Stated)var10000.map(IArgParser.Stated.class::cast).findFirst().orElse((Object)null);
   }

   public Object parseStateless(Class type, ICommandArgument arg) throws CommandInvalidTypeException {
      IArgParser.Stateless<T> parser = this.getParserStateless(type);
      if (parser == null) {
         throw new CommandNoParserForTypeException(type);
      } else {
         try {
            return parser.parseArg(arg);
         } catch (Exception var5) {
            throw new CommandInvalidTypeException(arg, type.getSimpleName());
         }
      }
   }

   public Object parseStated(Class type, Class stateKlass, ICommandArgument arg, Object state) throws CommandInvalidTypeException {
      IArgParser.Stated<T, S> parser = this.getParserStated(type, stateKlass);
      if (parser == null) {
         throw new CommandNoParserForTypeException(type);
      } else {
         try {
            return parser.parseArg(arg, state);
         } catch (Exception var7) {
            throw new CommandInvalidTypeException(arg, type.getSimpleName());
         }
      }
   }

   public Registry getRegistry() {
      return this.registry;
   }

   // $FF: synthetic method
   private static ArgParserManager[] $values() {
      return new ArgParserManager[]{INSTANCE};
   }
}

