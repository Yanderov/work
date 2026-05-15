package dev.client.yanderov.commands.manager;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.commands.argument.ArgConsumer;
import dev.client.yanderov.commands.argument.CommandArguments;
import dev.client.yanderov.commands.defaults.DefaultCommands;
import dev.client.yanderov.utils.client.managers.api.command.ICommand;
import dev.client.yanderov.utils.client.managers.api.command.argument.ICommandArgument;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandUnhandledException;
import dev.client.yanderov.utils.client.managers.api.command.exception.ICommandException;
import dev.client.yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import dev.client.yanderov.utils.client.managers.api.command.manager.ICommandManager;
import dev.client.yanderov.utils.client.managers.api.command.registry.Registry;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Stream;
import net.minecraft.class_3545;

public class CommandRepository implements ICommandManager {
   private final Registry registry = new Registry();

   public CommandRepository() {
      List var10000 = DefaultCommands.createAll();
      Registry var10001 = this.registry;
      Objects.requireNonNull(var10001);
      var10000.forEach(var10001::register);
   }

   public Registry getRegistry() {
      return this.registry;
   }

   public ICommand getCommand(String name) {
      for(ICommand command : this.registry.entries) {
         if (command.getNames().contains(name.toLowerCase(Locale.US))) {
            return command;
         }
      }

      return null;
   }

   public boolean execute(String string) {
      return this.execute(expand(string));
   }

   public boolean execute(class_3545 expanded) {
      ExecutionWrapper execution = this.from(expanded);
      if (execution != null) {
         execution.execute();
      }

      return execution != null;
   }

   public Stream tabComplete(class_3545 expanded) {
      ExecutionWrapper execution = this.from(expanded);
      return execution == null ? Stream.empty() : execution.tabComplete();
   }

   public Stream tabComplete(String prefix) {
      class_3545<String, List<ICommandArgument>> pair = expand(prefix, true);
      String label = (String)pair.method_15442();
      List<ICommandArgument> args = (List)pair.method_15441();
      return args.isEmpty() ? (new TabCompleteHelper()).addCommands(YanderovIntegration.getInstance().getCommandRepository()).filterPrefix(label).stream() : this.tabComplete(pair);
   }

   private ExecutionWrapper from(class_3545 expanded) {
      String label = (String)expanded.method_15442();
      ArgConsumer args = new ArgConsumer(this, (List)expanded.method_15441());
      ICommand command = this.getCommand(label);
      return command == null ? null : new ExecutionWrapper(command, label, args);
   }

   private static class_3545 expand(String string, boolean preserveEmptyLast) {
      String label = string.split("\\s", 2)[0];
      List<ICommandArgument> args = CommandArguments.from(string.substring(label.length()), preserveEmptyLast);
      return new class_3545(label, args);
   }

   public static class_3545 expand(String string) {
      return expand(string, false);
   }

   private static final class ExecutionWrapper {
      private ICommand command;
      private String label;
      private ArgConsumer args;

      private ExecutionWrapper(ICommand command, String label, ArgConsumer args) {
         this.command = command;
         this.label = label;
         this.args = args;
      }

      private void execute() {
         try {
            this.command.execute(this.label, this.args);
         } catch (Throwable t) {
            ICommandException exception = (ICommandException)(t instanceof ICommandException ? (ICommandException)t : new CommandUnhandledException(t));
            exception.handle(this.command, this.args.getArgs());
         }

      }

      private Stream tabComplete() {
         try {
            return this.command.tabComplete(this.label, this.args);
         } catch (CommandException var2) {
         } catch (Throwable t) {
            t.printStackTrace();
         }

         return Stream.empty();
      }
   }
}

